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

import java.util.Objects;

public class TelaCadastroGrupoController {

    @FXML
    private TableView<org.example.paceralphacode.User> tabelaemails;

    @FXML
    private TableColumn<org.example.paceralphacode.User, String> nometable;
    @FXML
    private TableColumn<org.example.paceralphacode.User, String> email;

    @FXML
    private TextField inseriremail;
    @FXML
    private AnchorPane fundo;

    @FXML
    private Button buttonRemoveStudent1;
    @FXML
    private Button addaluno1;
    @FXML
    private Button btcancelar;
    @FXML
    private Button btsalvar;

    private ObservableList<org.example.paceralphacode.User> users;

    public void initialize() {
        users = FXCollections.observableArrayList();
        nometable.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        // Aplicar classes CSS aos botões
        addaluno1.getStyleClass().add("buttonAddStudent");
        buttonRemoveStudent1.getStyleClass().add("buttonRemoveStudent1");
        btsalvar.getStyleClass().add("btsalvar");
        btcancelar.getStyleClass().add("btcancelar");

        addaluno1.setOnAction(event -> addAluno1());
        tabelaemails.setItems(users);

        style1();
    }

    private void addAluno1() {
        String emailInput = inseriremail.getText();
        if (emailInput != null && !emailInput.isEmpty()) {
            users.add(new User(emailInput));
            inseriremail.clear();
        }
    }
    public void cancelar(ActionEvent actionEvent) {
        Stage stage = (Stage) fundo.getScene().getWindow();
        stage.close();
    }
    public void salvar(ActionEvent actionEvent) {

    }
    public void inseriremail2(ActionEvent actionEvent) {

    }
    public void add(ActionEvent actionEvent) {

    }
    @FXML
    void removeSelectedStudent1(ActionEvent event) {
        org.example.paceralphacode.User alunoSelecionado = tabelaemails.getSelectionModel().getSelectedItem();
        if (alunoSelecionado != null) {
            users.remove(alunoSelecionado);
        }
    }
    public void style1() {
        String css = Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm();
        addaluno1.getStylesheets().add(css);
        buttonRemoveStudent1.getStylesheets().add(css);
        btsalvar.getStylesheets().add(css);
        btcancelar.getStylesheets().add(css);


    }
}
