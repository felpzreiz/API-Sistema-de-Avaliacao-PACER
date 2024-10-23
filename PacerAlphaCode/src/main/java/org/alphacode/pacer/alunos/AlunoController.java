package org.alphacode.pacer.alunos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexao.OperacoesSQL;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AlunoController {
    OperacoesSQL conexao = new OperacoesSQL();
    Statement stm = OperacoesSQL.conectarBanco();

    @FXML
    private TextField writeStudent;

    @FXML
    private Label ngroups;

    @FXML
    private Label nStudentsnull;

    @FXML
    public TextField writeStudent1;

    @FXML
    private Button buttonAddStudent;

    @FXML
    public Button refresh;

    @FXML
    public Button instruction;

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

    public AlunoController() throws SQLException {
    }

    public void initialize() throws SQLException {
        try {
            listaDados = FXCollections.observableArrayList();
            csvImport = new HashSet<>();

            viewName.setCellValueFactory(new PropertyValueFactory<>("nome"));
            viewEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            viewGroup.setCellValueFactory(new PropertyValueFactory<>("grupo"));
            carregarDados();
            nStudents();
            nStudentsnull();
            nGroup();
            style();
        } catch (SQLException e) {
            e.printStackTrace(); // Para ver a exceção específica
        }
    }

    @FXML
    private void buttonAddStudent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/alunos/novoAluno.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm());
        Stage stage = new Stage();
        stage.setTitle("Novo Aluno");
        stage.setScene(scene);
        stage.show();
        style();
        carregarDados();
        nStudents();
    }

    public void nStudents() {
        try {
            ResultSet nStudent = stm.executeQuery("SELECT COUNT(DISTINCT email) AS nAlunos FROM aluno WHERE email IS NOT NULL OR email = ''");
            if (nStudent.next()){
                int  qtdalunos = nStudent.getInt("nAlunos");
                nStudents.setText(String.valueOf(qtdalunos));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int qtd = listaDados.size();
        nStudents.setText(String.valueOf(qtd));

    }

    public void nStudentsnull() throws SQLException {
        try {
            ResultSet emptyResultSet = stm.executeQuery("SELECT COUNT(*) AS nogroup FROM aluno WHERE grupo IS NULL OR grupo = ''");
            if (emptyResultSet.next()) {
                int nSNull = emptyResultSet.getInt("nogroup");
                nStudentsnull.setText(String.valueOf(nSNull));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void nGroup() throws SQLException {
        try {
            ResultSet ngroup = stm.executeQuery("SELECT COUNT(DISTINCT grupo) AS qtdgrupo FROM aluno WHERE grupo IS NULL OR grupo <> ''");
            if (ngroup.next()) {
                int ngroupInt = ngroup.getInt("qtdgrupo");
                ngroups.setText(String.valueOf(ngroupInt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void removeSelectedStudent() {
        Alunos selectedStudent = viewStudent.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            OperacoesSQL.excluir(stm, selectedStudent.getEmail());
            System.out.println(selectedStudent.getEmail());
            carregarDados();
            nStudents();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um aluno.");
            alert.showAndWait();
        }
    }

    @FXML
    public void style() {
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm();
        buttonAddStudent.getStylesheets().add(css);
        buttonRemoveStudent.getStylesheets().add(css);
        buttonImportStudent.getStylesheets().add(css);
        buttonEditStudent.getStylesheets().add(css);
        buttonBuscarStudent.getStylesheets().add(css);
        viewStudent.getStylesheets().add(css);
        refresh.getStyleClass().add(css);

    }

    public void EditedSelectedStudent(ActionEvent actionEvent) throws IOException {
        Alunos selectedStudent = viewStudent.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/alunos/editAluno.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Editar Aluno");
            stage.setScene(scene);
            stage.show();
            nStudents();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um aluno.");
            alert.showAndWait();
        }
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
                String grupo = (texto.length > 2) ? texto[2].trim() : "";                                                                                          //Considerando que a posição 0 seja o nome .trim() ignora espaços vazios
                String repo = (texto.length > 3) ? texto[3].trim() : "";                                                                                               //  grupo recebe o a posição 1, porém para ignorar o vazio foi feito um operador ternario (condição) ? valor_se_verdadeiro : valor_se_falso
                // O arquivo dá erro caso encontre uma coluna vazia devido o array, deste modo considerei vazio
                if (!csvImport.contains(email)) {                                                                                // Por meio do HashSet eu verifico as duplicatas de acordo com os email que eu já adicionei
                    Alunos aluno = new Alunos(nome, email, grupo, repo);                                                                         // Instanciado um novo objeto aluno para receber os atributos
                    listaDados.add(aluno);                                                                                                    // adiciona os valores na lista observável
                    csvImport.add(email);
                    OperacoesSQL.inserir(stm, "'" + aluno.email + "', 'Senha123' ,'" + aluno.repo + "','" + aluno.grupo + "','" + aluno.nome + "'");// Guarda o email repetido para uma lista
                }
            }
            viewStudent.setItems(listaDados);                                                                                              // Envia os valores para a tabela
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setContentText("Dados importados com sucesso.");
            info.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buttonBuscarStudent(ActionEvent actionEvent) {
    }

    public void carregarDados() {
        String query = "SELECT senha,email,grupo,* FROM aluno";
        List<Alunos> alunosList = OperacoesSQL.consultarDados(stm, query);
        listaDados.clear(); // Limpa a lista atual antes de carregar novos dados
        listaDados.addAll(alunosList); // Adiciona os dados retornados à lista
        viewStudent.setItems(listaDados); // Define os itens da TableView
    }

    public void refreshBD(ActionEvent actionEvent) throws SQLException {
        carregarDados();
        nStudents();
        nStudentsnull();
        nGroup();
    }

    public void openIntruction(ActionEvent actionEvent) {
        try {
            File pdf = new File("PacerAlphaCode/src/main/resources/org/alphacode/pacer/arquivos/instAluno.pdf");
            if (pdf.exists()) {
                Desktop.getDesktop().open(pdf);
            } else {
                System.out.println("Arquivo não encontrado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


