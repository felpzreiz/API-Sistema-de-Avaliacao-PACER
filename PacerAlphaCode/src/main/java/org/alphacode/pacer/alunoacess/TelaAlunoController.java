package org.alphacode.pacer.alunoacess;

import conexao.OperacoesSQL;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.converter.FloatStringConverter;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelaAlunoController {
    OperacoesSQL conexao = new OperacoesSQL();
    Statement stm = OperacoesSQL.conectarBanco();

    @FXML
    private Button btconfigurar;

    @FXML
    private Button btsair;

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
    private Label nomealuno;

    @FXML
    private Label nomegrupo;

    @FXML
    private Label qtPontos;

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
        listaAlunos = FXCollections.observableArrayList();                  // Inicio primeiro os alunos na coluna de estudantes
        List<String> colunas = OperacoesSQL.carregarColunas(stm);         //  Carrego as colunas para iniciar em seguida a tabela
        carregarAlunos();                                                                 // Trago o metodo de alunos
        notaColunas = new HashMap<>();
        initializeTable(colunas);                                     // Inicio as colunas dinamicas
    }

    public TelaAlunoController() throws SQLException {
    }

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

                if (notaAluno >= 0 && notaAluno <= 3) {
                    if (notaIndex >= aluno.getNotas().size()) {
                        aluno.addNotas(new Notas(notaAluno));
                    } else {
                        aluno.getNotas().get(notaIndex).setNota(notaAluno);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Nota inválida!");
                    alert.setContentText("A nota deve estar entre 0 e 3.");
                    alert.showAndWait();
                }
            });
            tableStudents.getColumns().add(tableColumn);
        }
        tableStudents.setItems(listaAlunos);
    }


}
