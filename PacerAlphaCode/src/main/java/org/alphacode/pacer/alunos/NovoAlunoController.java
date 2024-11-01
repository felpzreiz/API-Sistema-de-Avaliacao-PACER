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

import java.sql.SQLException;
import java.sql.Statement;

public class NovoAlunoController {
    OperacoesSQL conexao = new OperacoesSQL();
    Statement stm = OperacoesSQL.conectarBanco();
    AlunoController gerenciarAluno = new AlunoController();

    @FXML
    private Label Pacer;

    @FXML
    private Button cancelBtnNew;

    @FXML
    private TextField email;

    @FXML
    private ImageView fatec;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField fullname;

    @FXML
    private TextField git;

    @FXML
    private TextField group;

    @FXML
    private AnchorPane novoAluno;

    @FXML
    private Button saveBtnNew;

    public NovoAlunoController() throws SQLException {
    }

    @FXML
    void cancelBtn(ActionEvent event) {
        Stage stage = (Stage) novoAluno.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {

        if (email.getText().isEmpty()) {
            infoLabel.setText("Email obrigatório!");
            infoLabel.setVisible(true);
        } else {
            fullname.getText();
            email.getText();
            group.getText();
            git.getText();

            Alunos aluno = new Alunos(fullname.getText(), email.getText(), group.getText(), git.getText());
            OperacoesSQL.inserirAluno(stm, aluno.email, aluno.repo, aluno.grupo, aluno.nome);
            OperacoesSQL.inserirUsuario(stm, aluno.email, AlunoController.createPassword(aluno.email));

            Stage stage = (Stage) novoAluno.getScene().getWindow();
            stage.close();

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setContentText("Aluno adicionado com sucesso!");
            info.show();

        }


    }

}
