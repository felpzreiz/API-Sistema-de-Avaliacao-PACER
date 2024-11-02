package org.alphacode.pacer.grupos;

import conexao.OperacoesSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TelaCadastroGrupoController extends GrupoController implements Initializable {
    OperacoesSQL conexao = new OperacoesSQL();
    Statement stm = OperacoesSQL.conectarBanco();

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
    private TableColumn<Aluno, String> NameColumn;

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

    private ListView<String> telagrupos;

    public TelaCadastroGrupoController() throws SQLException {
    }

    public void setDialogStage(Stage dialogStage, ListView<String> telagrupos) {
        this.dialogStage = dialogStage;
        this.telagrupos = telagrupos;
    }

    public void setDialogStage(Stage dialogStage, String grupoSelecionado) {
        this.dialogStage = dialogStage;
        this.grupoSelecionado = grupoSelecionado;
        nomedogrupo.setText(grupoSelecionado);
    }

    @FXML
    public void initialize() {
        // Configure a coluna da tabela
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tabelaemails.setItems(FXCollections.observableArrayList());
    }

    @FXML
    private void add() {
        String email = inseriremail.getText();
        if (email != null && !email.trim().isEmpty()) {
            Aluno aluno = new Aluno(email);
            tabelaemails.getItems().add(aluno);
            inseriremail.clear();
        }
    }

    @FXML
    private void salvar() {
        String grupoNome = nomedogrupo.getText();
        if (grupoNome != null && !grupoNome.trim().isEmpty()) {
            OperacoesSQL.inserirGrupo(stm, nomedogrupo.getText());
            dialogStage.close();
           // carregarDados();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nome do Grupo Inválido");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, insira um nome válido para o grupo.");
            alert.showAndWait();
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
