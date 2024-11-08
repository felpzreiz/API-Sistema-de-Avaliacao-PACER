package org.alphacode.pacer.alunoacess;

import conexao.OperacoesSQL;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TelaAlunoController {
    OperacoesSQL conexao = new OperacoesSQL();
    Statement stm = OperacoesSQL.conectarBanco();


    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void getEmail(String email) {
        this.email = email;
    }

    private String grupo;

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGrupo(String s) {
        return grupo;
    }

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
    private List<AlunosInterface> alunos;              // Declaro a lista de alunos diretamente com a query
    private Map<String, Integer> notaColunas;

    @FXML
    public void carregarAlunos(String email) throws SQLException {       // Carrego o nome dos alunos com base nos Objetos AlunosInterface e Query
        alunos = OperacoesSQL.alunosGrupo(stm, email);
        listaAlunos.addAll(alunos);
    }

    @FXML
    public void initialize() throws SQLException {
        idEmail.setText("");
        listaAlunos = FXCollections.observableArrayList();                  // Inicio primeiro os alunos na coluna de estudantes
        List<String> colunas = OperacoesSQL.carregarColunas(stm);         //  Carrego as colunas para iniciar em seguida a tabela         // Trago o metodo de alunos
        notaColunas = new HashMap<>();
        initializeTable(colunas);                                     // Inicio as colunas dinamicas
        pontosSprint();
        style();
    }

    public TelaAlunoController() throws SQLException {
    }

    public void initializeTable(List<String> column) {                // O nome das colunas é considerada uma String
        tableStudents.setEditable(true);                                 // Tabela Editavél
        tableStudents.getColumns().clear();                               // Limpa os antigos registros de colunas
        columnStudent.setCellValueFactory(new PropertyValueFactory<>("nome"));      // Considera que a coluna de Alunos vai receber o getNome da classe AlunosInterface
        tableStudents.getColumns().add(columnStudent);            // Insere a coluna Alunos na Tabela
        columnStudent.setEditable(false);                           // Bloqueia a coluna de alunos na tabela

        notaColunas = new HashMap<>();
        int colunaX = 1;
        for (String col : column) {

            notaColunas.put(col, colunaX);
            ;// Mapeia o nome da coluna ao índice da nota
            System.out.println("Coluna: " + col + " com índice de nota: " + colunaX);
            TableColumn<AlunosInterface, Float> tableColumn = new TableColumn<>(col);

            tableColumn.setCellValueFactory(cellData -> {
                int notaIndex = notaColunas.get(col);  // Obtém o índice da nota correspondente
                AlunosInterface aluno = cellData.getValue();

                if (notaIndex < aluno.getNotas().size()) {
                    return new SimpleFloatProperty(aluno.getNotas().get(notaIndex).getNota()).asObject();
                } else {
                    aluno.addNotas(new Notas(0.0f));  // Criação da nota com valor inicial 0
                    return new SimpleFloatProperty(0.0f).asObject();  // Retorna o valor padrão 0
                }
            });

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            tableColumn.setOnEditCommit(event -> {
                AlunosInterface aluno = event.getRowValue();
                String colunaNome = event.getTableColumn().getText();
                int indiceNota = notaColunas.get(colunaNome);

                String stringNota = event.getNewValue().toString().replace(',', '.');

                try {

                    float notaDigitada = Float.parseFloat(stringNota);      // Nota digitada

                    //System.out.println("Aluno: " + aluno.getNome() + " Nota digitada: " + notaDigitada + " para " + colunaNome);


                    if (notaDigitada >= 0 && notaDigitada <= 3) {

                        float somaAtual = +notaDigitada + somaNotas();

                        if (pontosSprint() >= somaAtual) {
                            if (indiceNota >= aluno.getNotas().size()) {
                                aluno.addNotas(new Notas(notaDigitada));
                            } else {
                                aluno.getNotas().get(indiceNota).setNota(notaDigitada);
                            }
                            tableStudents.refresh();
                            somaNotas();

                        } else {
                            showAlert("Erro!", "ATENÇÃO", "Quantidade de pontos excedida.");
                        }
                    } else {

                        showAlert("Erro!", "Nota inválida!", "A nota deve estar entre 0 e 3.");

                    }
                } catch (NumberFormatException e) {
                    showAlert("Formato inválido", "Formato de nota incorreto", "Por favor, insira um número válido (por exemplo: 1.5 ou 1,5).");
                }
            });
            tableStudents.getColumns().add(tableColumn);
            colunaX++;
        }
        tableStudents.setItems(listaAlunos);
        tableStudents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) homeAlunos.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/alphacode/pacer/home/Login.fxml"));
        Parent login = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Login");
        Scene scene = new Scene(login, 545, 620);
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();
    }

    @FXML
    public void acessConfig(ActionEvent actionEvent) {
    }

    @FXML
    public void style() {
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
        float pontosSprint = 10f;
        return pontosSprint;
    }

    @FXML
    public void carregarDados(String email) throws SQLException {
        setEmail(email);
        idEmail.setText(this.email);
        labelNome.setText(OperacoesSQL.nomeAluno(stm, email));
        nomegrupo.setText(OperacoesSQL.carregarInfo(stm, email));
        String idGrupo = OperacoesSQL.idGrupo(stm, email);

        System.out.println("Aqui funciona: " + idGrupo);
    }


    @FXML
    public void salvarNotas(ActionEvent actionEvent) {
        for (AlunosInterface aluno : listaAlunos) {
            String alunoAvaliado = aluno.getNome();

            for (Map.Entry<String, Integer> nota : notaColunas.entrySet()) {
                String colunaNome = nota.getKey();
                int notaIndex = nota.getValue();

                float notaId;
                if (notaIndex < aluno.getNotas().size()) {
                    notaId = aluno.getNotas().get(notaIndex).getNota();
                } else {
                    notaId = 0.0f;
                }

                System.out.println(alunoAvaliado + " " + colunaNome + " " + notaId);
            }
        }


    }

    @FXML
    public void showAlert(String title, String header, String content) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(title);
        info.setHeaderText(header);
        info.setContentText(content);
        info.show();
    }

}