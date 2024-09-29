package org.example.paceralphacode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TelaCadastroGrupoController {

    @FXML
    private TableView<org.example.paceralphacode.User> tabelaemails;

    @FXML
    private TableColumn<org.example.paceralphacode.User, String> email;

    @FXML
    private TextField inseriremail;
    @FXML
    private AnchorPane fundo;

    @FXML
    private Button addaluno;
    @FXML
    private Button btcancelar;
    @FXML
    private Button btsalvar;

    private ObservableList<org.example.paceralphacode.User> users;

    public void initialize() {
        users = FXCollections.observableArrayList();

        // Configurando a coluna para exibir os nomes dos alunos
        email.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        /*Permitindo edição dos nomes
        tableUsers.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setName(event.getNewValue());
        });*/

        // Adicionando alunos ao clicar no botão
        addaluno.setOnAction(event -> addAluno());

        // Configurar a tabela para aceitar a lista de usuários
        tabelaemails.setItems(users);
    }

    private void addAluno() {
        String email = inseriremail.getText();
        if (email != null && !email.isEmpty()) {
            users.add(new User(email));  // Adiciona o novo aluno à lista
            inseriremail.clear();           // Limpa o campo de entrada
        }
    }

    public void cancelar(ActionEvent actionEvent) {
        Stage stage = (Stage)fundo.getScene().getWindow();
        stage.close();
    }

    public void salvar(ActionEvent actionEvent) {
    }

    public void inseriremail2(ActionEvent actionEvent) {

    }

    public void add(ActionEvent actionEvent) {
    }
}
