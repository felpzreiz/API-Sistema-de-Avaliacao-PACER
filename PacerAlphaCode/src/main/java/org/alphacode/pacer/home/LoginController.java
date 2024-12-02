package org.alphacode.pacer.home;

import conexao.OperacoesSQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;
import org.alphacode.pacer.alunoacess.TelaAlunoController;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class LoginController {
    Statement stm = OperacoesSQL.conectarBanco();

    @FXML
    private Label Pacer;

    @FXML
    private Label checkEmailSenha;

    @FXML
    private Button btn;

    @FXML
    private ImageView fatec;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private TextField idEmail;

    public LoginController() throws SQLException {
    }


    @FXML
    private PasswordField idSenha;


    @FXML
    private AnchorPane login;

    @FXML
    private void onButtonClick() throws IOException, SQLException {
        if ((idEmail.getText() != null && !Objects.equals(idEmail.getText(), "")) && (idSenha.getText() != null && !Objects.equals(idSenha.getText(), ""))) {
            Boolean user = OperacoesSQL.getUser(stm, idEmail.getText(), idSenha.getText());
            if (user == true) {
                if (Objects.equals(OperacoesSQL.getEmailUser(stm, idEmail.getText()), "professor@fatec.sp.gov.br")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/home/Home.fxml"));       //  Instancia uma nova cena que vai da Login.fxml para Home.fxml
                    Scene scene = new Scene(fxmlLoader.load());                                                                                                           // Carrega a Classe FXML para criar uma Cena
                    Stage newstage = new Stage();                                                                                                                                   // Stage é como Window, aqui é instanciado uma nova WINDOW
                    newstage.setTitle("Home Professor");                                                                                                                          // Declarado o título da window
                    newstage.setScene(scene);
                    newstage.setMaximized(true);
                    newstage.show();

                    Stage stage = (Stage) login.getScene().getWindow();
                    stage.close();
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/alunoacess/TelaAluno.fxml"));       //  Instancia uma nova cena que vai da Login.fxml para Home.fxml
                    Scene scene = new Scene(fxmlLoader.load());                                                                                                           // Carrega a Classe FXML para criar uma Cena

                    TelaAlunoController controller = fxmlLoader.getController();
                    controller.setEmail(idEmail.getText());
                    controller.carregarDados(idEmail.getText());
                    controller.carregarAlunos(idEmail.getText());

                    Stage newstage = new Stage();                                                                                                                                   // Stage é como Window, aqui é instanciado uma nova WINDOW
                    newstage.setTitle("Home");                                                                                                                          // Declarado o título da window
                    newstage.setScene(scene);
                    newstage.setMaximized(true);
                    newstage.show();

                    Stage stage = (Stage) login.getScene().getWindow();
                    stage.close();
                }
            } else {
                checkEmailSenha.setText("E-mail ou Senha incorreta, tente novamente.");
                checkEmailSenha.setVisible(true);
            }
        } else {
            checkEmailSenha.setText("Preencha E-mail e Senha, tente novamente.");
            checkEmailSenha.setVisible(true);
        }
    }

    @FXML
    public void initialize() {                                                                                                                                              // Arquivo CSS para customizar os botões
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm();        // Instancia o arquivo styles.css em uma String que está salva com os arquivos fxml
        btn.getStylesheets().add(css);                                                                                                                          // menuFix é uma estrutura com vários botões, aqui o arquivo css muda sua interface
    }
}