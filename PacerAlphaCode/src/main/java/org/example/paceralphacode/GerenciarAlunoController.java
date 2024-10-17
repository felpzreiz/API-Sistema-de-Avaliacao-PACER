package org.example.paceralphacode;

import conexao.OperacoesSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GerenciarAlunoController {
    OperacoesSQL conexao = new OperacoesSQL();
    Statement stm = OperacoesSQL.conectarBanco();

    @FXML
    private TextField writeStudent;

    @FXML
    private Button buttonAddStudent;

    @FXML
    public Button buttonRemoveStudent;
    @FXML
    public Button buttonEditStudent;
    @FXML
    public Button buttonImportStudent;
    @FXML
    public Button buttonBuscarStudent;

    @FXML
    private Label checkStudent;

    @FXML
    private Label checkStudent1;

    @FXML
    private Label nStudents;

    @FXML
    private TableColumn<Alunos, String> viewEmail;

    @FXML
    private TableColumn<Alunos, String> viewGroup;

    @FXML
    private TableColumn<Alunos, String> viewName;

    @FXML
    private TableView<Alunos> viewStudent;


    @FXML
    private AnchorPane gAlunos;

    private ObservableList<Alunos> listaDados;
    private Set<String> csvImport;

    public GerenciarAlunoController() throws SQLException {
    }

    public void initialize() {

        listaDados = FXCollections.observableArrayList();
        csvImport = new HashSet<>();

        viewName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        viewEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        viewGroup.setCellValueFactory(new PropertyValueFactory<>("grupo"));

        style();
    }

    @FXML
    private void buttonAddStudent() {
        String email = writeStudent.getText();
        if (writeStudent.getText().isEmpty()) {
            checkStudent.setText("Informe o e-mail do aluno.");
            checkStudent.setVisible(true);
        } else {
            checkStudent.setVisible(false);
        }
        if (email != null && !email.isEmpty()) {
            Alunos novoAluno = new Alunos("", email, "", "");
            listaDados.add(novoAluno);
            writeStudent.clear();
            nStudents();
            OperacoesSQL.inserir(stm,"'" + novoAluno.email  + "','','',''");
        }

    }

    private void nStudents() {
        int qtd = listaDados.size();
        nStudents.setText(String.valueOf(qtd));
    }

    @FXML
    private void removeSelectedStudent() {
        Alunos selectedStudent = viewStudent.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            listaDados.remove(selectedStudent);
            checkStudent1.setVisible(false);
            nStudents();
        } else {
            checkStudent1.setText("Selecione um aluno para remover.");
            checkStudent1.setVisible(true);
        }
    }

    @FXML
    public void style() {
        String css = Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm();
        buttonAddStudent.getStylesheets().add(css);
        buttonRemoveStudent.getStylesheets().add(css);
        buttonImportStudent.getStylesheets().add(css);
        buttonEditStudent.getStylesheets().add(css);
        buttonBuscarStudent.getStylesheets().add(css);

    }

    public void EditedSelectedStudent(ActionEvent actionEvent) {
    }

    public void ImportSelectedStudent() {
        FileChooser search = new FileChooser();
        search.setTitle("Selecione o arquivo CSV");
        search.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

        File arquivoCSV = search.showOpenDialog(new Stage());
        if (arquivoCSV != null) {
            importCSV(arquivoCSV);
        }

    }

    public void importCSV(File arquivo) {
        String linha;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {                 // BufferedReader Lê o arquivo linha por linha - FileReader é o leitor do arquivo
            br.readLine();                                                                                                                 // br.read.Line é utilizado para ler linha por linha
            while ((linha = br.readLine()) != null) {                                                                       // Enquanto a linha for diferente de nula vai continuar lendo
                String[] texto = linha.split(";");                                                                  // Criado um Array com o nome texto ela recebe o texto delimitado pelo ";" linha.split é uma função da classe String para dividir a string

                String nome = (texto.length > 1) ? texto[0].trim() : "";
                String email = texto[1].trim();
                String grupo = (texto.length > 1) ? texto[2].trim() : "";                                                                                          //Considerando que a posição 0 seja o nome .trim() ignora espaços vazios
                String repo = (texto.length > 1) ? texto[3].trim() : "";                                                                                               //  grupo recebe o a posição 1, porém para ignorar o vazio foi feito um operador ternario (condição) ? valor_se_verdadeiro : valor_se_falso
                // O arquivo dá erro caso encontre uma coluna vazia devido o array, deste modo considerei vazio
                if (!csvImport.contains(email)) {                                                                                // Por meio do HashSet eu verifico as duplicatas de acordo com os email que eu já adicionei
                    Alunos aluno = new Alunos(nome, email, grupo, repo);                                                                         // Instanciado um novo objeto aluno para receber os atributos
                    listaDados.add(aluno);                                                                                                    // adiciona os valores na lista observável
                    csvImport.add(email);
                    OperacoesSQL.inserir(stm,"'" + aluno.nome  + "','" + aluno.email  + "','" + aluno.repo  + "','" + aluno.grupo  + "'");// Guarda o email repetido para uma lista
                }
            }
            viewStudent.setItems(listaDados);                                                                                              // Envia os valores para a tabela
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buttonBuscarStudent(ActionEvent actionEvent) {
    }
}

