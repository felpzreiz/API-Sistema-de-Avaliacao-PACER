package org.alphacode.pacer.password;

import conexao.OperacoesSQL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.alphacode.pacer.alunoacess.TelaAlunoController;

import java.sql.SQLException;
import java.sql.Statement;

public class RedefinirSenha {

    Statement stm = OperacoesSQL.conectarBanco();
    TelaAlunoController controller = new TelaAlunoController();

    @FXML
    private ImageView Alpha;

    @FXML
    private AnchorPane PaneSenha;

    @FXML
    private Label alSenha;

    @FXML
    public Button exitPass;

    @FXML
    public Button saveBtn;

    @FXML
    private PasswordField confirmNew;

    @FXML
    private Label crSenha;

    @FXML
    private GridPane gridSenha;

    @FXML
    private PasswordField newPass;

    @FXML
    private PasswordField password;

    private String email;

    public RedefinirSenha() throws SQLException {
    }

    @FXML
    public String setEmail(String email) {
        this.email = email;
        return email;
    }


    public void exit() {
        Stage stage = (Stage) PaneSenha.getScene().getWindow();
        stage.close();
    }

    public void saveNewPass() {

        if (!password.getText().equals(getPassword())) {
            controller.showAlert("Alterar Senha", "Erro", "A senha atual informada está incorreta.");
        } else if (!confirmNew.getText().equals(newPass.getText())) {
            controller.showAlert("Alterar Senha", "Erro", "As senhas não coincidem. Por favor, tente novamente.");
        } else if (password.getText().equals(getPassword())) {
            if (newPass.getText().equals(confirmNew.getText())) {
                String newPassword = confirmNew.getText();
                OperacoesSQL.updatePassword(stm, newPassword, setEmail(email));
                controller.showAlert("Alterar Senha", "Sucesso", "Senha alterada com sucesso!");
                Stage stage = (Stage) PaneSenha.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    public String getPassword() {
        return OperacoesSQL.getPassword(stm, setEmail(email));
    }


}
