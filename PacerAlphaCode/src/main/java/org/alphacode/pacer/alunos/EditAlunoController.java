package org.alphacode.pacer.alunos;

import conexao.OperacoesSQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class EditAlunoController {
    Statement stm = OperacoesSQL.conectarBanco();

    public void setAluno(String nome, String email, String grupo, String repo) {
        this.nome = nome;
        this.emailAluno = email;
        this.grupo = grupo;
        this.repo = repo;
    }

    private String nome;
    private String emailAluno;
    private String grupo;
    private String repo;
    private Integer idAluno;

    @FXML
    private Label Pacer;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label nomeAluno;

    @FXML
    private AnchorPane editAluno;

    @FXML
    private TextField email;

    @FXML
    private ImageView fatec;

    @FXML
    private TextField fullname;

    @FXML
    private TextField git;

    @FXML
    private TextField group;

    @FXML
    private Label infoLabel;

    @FXML
    private Button saveBtn;

    public EditAlunoController() throws SQLException {
    }

    @FXML
    public void initialize() throws SQLException, IOException {
        nomeAluno.setText("");

        fullname.setText("");
        email.setText("");
        group.setText("");
        git.setText("");
    }

    @FXML
    public void carregarDados() throws SQLException {
        nomeAluno.setText("Aluno selecionado: " + nome);

        this.idAluno = OperacoesSQL.SelectIDEdit(stm, emailAluno);

        fullname.setText(nome);
        email.setText(emailAluno);
        group.setText(grupo);
        git.setText(repo);
    }

    @FXML
    void cancelBtn(ActionEvent event) {
        Stage stage = (Stage) editAluno.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        OperacoesSQL.updateAluno(stm, email.getText(), git.getText(), group.getText(), fullname.getText(), idAluno);

        Stage stage = (Stage) editAluno.getScene().getWindow();
        stage.close();

        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setContentText("Aluno editado com sucesso!");
        info.show();
    }
}
