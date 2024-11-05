package org.alphacode.pacer.alunoacess;

import conexao.OperacoesSQL;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TelaAlunoController {
    OperacoesSQL conexao = new OperacoesSQL();
    Statement stm = OperacoesSQL.conectarBanco();

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    @FXML
    private Label idAluno;

    @FXML
    public Label labelNome;

    @FXML
    public Label idEmail;

    @FXML
    public Label infoSprint;

    @FXML
    public Label infoPontos;

    @FXML
    public Label infoAvaliacao;

    @FXML
    private Button btnconfig;

    @FXML
    private Button btnsair;

    @FXML
    private Button btsalvar;

    @FXML
    private TableColumn<AlunosInterface, String> columnStudent;

    @FXML
    private AnchorPane dadosAluno;

    @FXML
    private GridPane gridavaliacao;

    @FXML
    private SplitPane homeAlunos;

    @FXML
    private ImageView logofatec;

    @FXML
    public Label nomeAluno;

    @FXML
    private Label nomegrupo;

    @FXML
    private Label qtPontos;

    @FXML
    private Label timeSprint;

    @FXML
    private Label pontosSprint;

    @FXML
    private Label qtdpontosDisp;

    @FXML
    private AnchorPane tabelaAlunos;

    @FXML
    private TableView<AlunosInterface> tableStudents;

    @FXML
    private Label textDuracao;

    @FXML
    private Label textSprint;

    @FXML
    private Label textpacer;

    @FXML
    private Label tpRestante;

    private ObservableList<AlunosInterface> listaAlunos;
    private List<AlunosInterface> alunos = OperacoesSQL.carregarNomes(stm);              // Declaro a lista de alunos diretamente com a query
    private Map<String, Integer> notaColunas;

    @FXML
    public void carregarAlunos() {       // Carrego o nome dos alunos com base nos Objetos AlunosInterface e Query
        listaAlunos.addAll(alunos);
    }

    @FXML
    public void initialize() {
        idEmail.setText("");
        listaAlunos = FXCollections.observableArrayList();                  // Inicio primeiro os alunos na coluna de estudantes
        List<String> colunas = OperacoesSQL.carregarColunas(stm);         //  Carrego as colunas para iniciar em seguida a tabela
        carregarAlunos();                                                                 // Trago o metodo de alunos
        notaColunas = new HashMap<>();
        initializeTable(colunas);                                     // Inicio as colunas dinamicas
        pontosSprint();
        style();
    }

    public TelaAlunoController() throws SQLException {
    }

    @FXML
    public void initializeTable(List<String> column) {                // O nome das colunas é considerada uma String
        tableStudents.setEditable(true);                                 // Tabela Editavél
        tableStudents.getColumns().clear();                               // Limpa os antigos registros de colunas
        columnStudent.setCellValueFactory(new PropertyValueFactory<>("nome"));      // Considera que a coluna de Alunos vai receber o getNome da classe AlunosInterface
        tableStudents.getColumns().add(columnStudent);            // Insere a coluna Alunos na Tabela
        columnStudent.setEditable(false);                           // Bloqueia a coluna de alunos na tabela

        for (String col : column) {

            notaColunas.put(col, listaAlunos.getFirst().getNotas().size());    // Mapeia o nome da coluna ao índice da nota
            TableColumn<AlunosInterface, Float> tableColumn = new TableColumn<>(col);


            tableColumn.setCellValueFactory(cellData -> {
                int nota = notaColunas.get(col);
                if (nota < cellData.getValue().getNotas().size()) {
                    return new SimpleFloatProperty(cellData.getValue().getNotas().get(nota).getNota()).asObject();
                } else {
                    return new SimpleFloatProperty(0.0f).asObject();
                }
            });

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            tableColumn.setOnEditCommit(event -> {
                AlunosInterface aluno = event.getRowValue();
                String colunaNome = event.getTableColumn().getText();
                int notaIndex = notaColunas.get(colunaNome);

                float notaAluno = event.getNewValue();             // Nota digitada

                System.out.println("Aluno: " + aluno.getNome() + " Nota digitada: " + notaAluno + " para " + colunaNome);

                float somaAtual = somaNotas();


                if (notaAluno >= 0 && notaAluno <= 3) {
                    if (pontosSprint() > somaNotas()) {
                        if (notaIndex >= aluno.getNotas().size()) {
                            aluno.addNotas(new Notas(notaAluno));
                        } else {
                            aluno.getNotas().get(notaIndex).setNota(notaAluno);
                        }
                        somaNotas();
                    } else {
                        Alert info = new Alert(Alert.AlertType.ERROR);
                        info.setTitle("Erro");
                        info.setHeaderText("ATENÇÃO! ");
                        info.setContentText("Quantidade de pontos excedida.");
                        info.show();
                    }
                } else {
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Erro");
                    info.setHeaderText("Nota inválida!");
                    info.setContentText("A nota deve estar entre 0 e 3.");
                    info.showAndWait();
                }
            });
            tableStudents.getColumns().add(tableColumn);
        }
        tableStudents.setItems(listaAlunos);
        tableStudents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) homeAlunos.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void acessConfig(ActionEvent actionEvent) {
    }

    @FXML
    public void style(){
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toString();
        tableStudents.getStylesheets().add(css);
    }

    public float somaNotas() {
        qtdpontosDisp.setVisible(true);
        float somaNotas = 0.0f;
        for (AlunosInterface aluno : listaAlunos) {
            for (Notas nota : aluno.getNotas()) {
                somaNotas += nota.getNota();
            }
        }
        qtdpontosDisp.setText(String.valueOf(somaNotas));
        return somaNotas;
    }

    public float pontosSprint() {
        infoPontos.setVisible(true);
        infoPontos.setText("10");
        float pontosSprint = 9f;
        return pontosSprint;
    }

    @FXML
    public void carregarDados(String email) throws SQLException {
        setEmail(email);
        idEmail.setText(this.email);
    }
}
