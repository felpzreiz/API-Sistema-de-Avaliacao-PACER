package org.alphacode.pacer.alunos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.*;

import conexao.OperacoesSQL;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;
import org.alphacode.pacer.grupos.Aluno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

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
    public Button buttonCleanFilter;

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
    private FilteredList<Alunos> filteredDados;

    private String selecaoNome;
    private String selecaoEmail;
    private String selecaoRepo;
    private String selecaoGrupo;

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
            viewStudent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            refreshBD();
            refresh.setTooltip(new Tooltip("Atualizar Banco de Dados"));
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
            if (nStudent.next()) {
                int qtdalunos = nStudent.getInt("nAlunos");
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
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmar Remoção");
            confirm.setHeaderText("Deseja realmente excluir o aluno?");
            confirm.setContentText("Aluno: " + selectedStudent.getNome());

            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não");
            confirm.getButtonTypes().setAll(btnSim, btnNao);

            confirm.showAndWait().ifPresent(response -> {
                if (response == btnSim) {
                    OperacoesSQL.excluir(stm, selectedStudent.getEmail());
                    carregarDados();
                    nStudents();
                }
            });
        } else {
            showAlert("Erro!", null, "Selecione um aluno.");
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
        buttonCleanFilter.getStylesheets().add(css);
        viewStudent.getStylesheets().add(css);
        refresh.getStyleClass().add(css);

    }

    @FXML
    public void EditedSelectedStudent(ActionEvent actionEvent) throws IOException, SQLException {


        Alunos selectedStudent = viewStudent.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            this.selecaoNome = selectedStudent.getNome();
            this.selecaoEmail = selectedStudent.getEmail();
            this.selecaoGrupo = selectedStudent.getGrupo();

            //AQUI PEGA O REPOSITÓRIO, POIS ELE NÃO ESTÁ NA TABELA DE ALUNOCONTROLLER.JAVA
            try {
                ResultSet nStudent = stm.executeQuery("SELECT git AS git FROM aluno WHERE email = '" + selectedStudent.getEmail() + "'");
                if (nStudent.next()) {
                    String git = nStudent.getString("git");
                    this.selecaoRepo = String.valueOf(git);
                    System.out.println(selecaoRepo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            abrirEdit();
        } else {
            showAlert("Erro!", null, "Selecione um aluno.");
        }
    }

    public void abrirEdit() throws IOException, SQLException {
        if (selecaoNome != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/alunos/editAluno.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm());

            EditAlunoController editController = fxmlLoader.getController();//ATENÇÃO PARA ESSA LINHA NA HORA DE ABRIR O CONTROLLER
            editController.setAluno(selecaoNome, selecaoEmail, selecaoGrupo, selecaoRepo);
            editController.carregarDados();

            Stage stage = new Stage();
            stage.setTitle("Editar Aluno");
            stage.setScene(scene);
            stage.show();
            nStudents();
        } else {
            showAlert("Erro!", null, "Selecione um aluno.");
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
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) { // BufferedReader Lê o arquivo linha por linha - FileReader é o leitor do arquivo
            br.readLine(); // br.read.Line é utilizado para ler linha por linha

            boolean dadosImportados = false;
            boolean erroImportacao = false;

            while ((linha = br.readLine()) != null) { // Enquanto a linha for diferente de nula vai continuar lendo
                String[] texto = linha.split(";"); // Criado um Array com o nome texto ela recebe o texto delimitado pelo ";" linha.split é uma função da classe String para dividir a string


                if (texto.length < 4 || texto[0].trim().isEmpty() || texto[1].trim().isEmpty() ||
                        texto[2].trim().isEmpty() || texto[3].trim().isEmpty()) {
                    erroImportacao = true;
                    continue;
                }

                String nome = texto[0].trim();
                String email = texto[1].trim();
                String grupo = texto[2].trim();   //Considerando que a posição 0 seja o nome .trim() ignora espaços vazios
                String repo = texto[3].trim(); //  grupo recebe o a posição 1, porém para ignorar o vazio foi feito um operador ternario (condição) ? valor_se_verdadeiro : valor_se_falso
                // O arquivo dá erro caso encontre uma coluna vazia devido o array, deste modo considerei vazio
                if (!csvImport.contains(email)) {// Por meio do HashSet eu verifico as duplicatas de acordo com os email que eu já adicionei
                    Alunos aluno = new Alunos(nome, email, grupo, repo); // Instanciado um novo objeto aluno para receber os atributos
                    listaDados.add(aluno); // adiciona os valores na lista observável
                    csvImport.add(email);
                    OperacoesSQL.inserirUsuario(stm, aluno.email, createPassword(aluno.email));
                    OperacoesSQL.inserirAluno(stm, aluno.email, aluno.repo, aluno.grupo, aluno.nome);// Guarda o email repetido para uma lista
                    OperacoesSQL.inserirGrupo(stm, aluno.grupo);

                    dadosImportados = true;
                }
            }
            if (erroImportacao) {
                showAlert("Erro ao Importar CSV", "Campos Vazios Detectados",
                        "O CSV contém campos vazios ou inválidos. Dados incompletos não foram importados.");
            } else if (dadosImportados) {
                showAlert("Atenção!", "Importação Completa", "Dados importados com sucesso!");
            }
            csvImport.clear();
            refreshBD();
            viewStudent.setItems(listaDados);// Envia os valores para a tabela
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Falha ao ler o arquivo", "Ocorreu um erro ao tentar ler o arquivo CSV.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Falha na operação SQL", "Ocorreu um erro ao inserir os dados no banco de dados.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Erro desconhecido", "Ocorreu um erro inesperado durante a importação.");
        }
    }

    public void buttonBuscarStudent(ActionEvent actionEvent) {
        try {
            writeStudent1.setVisible(true);
            buttonCleanFilter.setVisible(true);
            filteredDados = new FilteredList<>(listaDados, p -> true);                                  // FiltredList é instanciada parar manipular a listaDados - (P->TRUE) é um parametro para iniciar o filtro com todos os valores
            writeStudent1.textProperty().addListener((observable, oldValue, newValue) -> {      // o addListener observa o TextField de busca e é feito um lambda para verificar as alterações
                filteredDados.setPredicate(alunos -> {                                                    // Atualiza a logica do filtro e considera o item buscado como aluno
                    if (newValue == null || newValue.isEmpty()) {                                     // Observa o campo do TextField
                        return true;
                    }
                    String min = newValue.toLowerCase();                                                  // Realiza a busca com base em letras minusculas.
                    return alunos.getNome().toLowerCase().contains(min) ||
                            alunos.getEmail().toLowerCase().contains(min) ||
                            alunos.getGrupo().toLowerCase().contains(min);
                });
            });
            viewStudent.setItems(filteredDados);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        List<Alunos> alunosList = OperacoesSQL.consultarDados(stm);
        listaDados.clear(); // Limpa a lista atual antes de carregar novos dados
        listaDados.addAll(alunosList); // Adiciona os dados retornados à lista
        viewStudent.setItems(listaDados); // Define os itens da TableView
    }

    public void refreshBD() throws SQLException {
        carregarDados();
        nStudents();
        nStudentsnull();
        nGroup();
    }

    public void cleanFilter(ActionEvent actionEvent) {
        writeStudent1.clear();
        writeStudent1.setVisible(false);
        buttonCleanFilter.setVisible(false);
    }

    public static String createPassword(String email) {
        String regex = "@";
        String[] array = email.split(regex);

        return array[0];
    }


    @FXML
    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }


}


