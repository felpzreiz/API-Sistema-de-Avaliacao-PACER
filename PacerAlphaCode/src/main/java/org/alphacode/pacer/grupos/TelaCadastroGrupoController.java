package org.alphacode.pacer.grupos;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaCadastroGrupoController {


    @FXML
    private TextField nomedogrupo;

    @FXML
    public AnchorPane fundo;

    @FXML
    private TextField inseriremail;

    @FXML
    private TableView<Aluno> tabelaemails;

    @FXML
    private TableColumn<Aluno, String> emailColumn;

    @FXML
    private TableColumn<Aluno, String> NameColumn;// Declare a coluna para email

    @FXML
    private Button buttonRemoveStudent1;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btsalvar;

    @FXML
    private Button addaluno1;

    private Stage dialogStage;

    private String grupoSelecionado;

    public void setDialogStage(Stage dialogStage, String grupoSelecionado) {
        this.dialogStage = dialogStage;
        this.grupoSelecionado = grupoSelecionado;
        nomedogrupo.setText(grupoSelecionado); // Preenche o campo com o nome do grupo
    }

    @FXML
    public void initialize() {
        // Configure a coluna da tabela
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tabelaemails.setItems(FXCollections.observableArrayList()); // Inicializa a tabela com uma lista vazia
    }

    @FXML
    private void add() {
        String email = inseriremail.getText();
        if (email != null && !email.trim().isEmpty()) {
            Aluno aluno = new Aluno(email);
            tabelaemails.getItems().add(aluno); // Adiciona o aluno à tabela
            inseriremail.clear(); // Limpa o campo após adicionar
        }
    }

    @FXML
    private void salvar() {
        String novoNomeGrupo = nomedogrupo.getText();
        dialogStage.close();
    }

    @FXML
    private void cancelar() {
        dialogStage.close();
    }

    public void nomedogrupo1(javafx.event.ActionEvent actionEvent) {
    }

    public void inseriremail2(javafx.event.ActionEvent actionEvent) {
    }

    public void removeSelectedStudent1(javafx.event.ActionEvent actionEvent) {
        Aluno selectedStudent = tabelaemails.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            tabelaemails.getItems().remove(selectedStudent);
        }
    }

}

