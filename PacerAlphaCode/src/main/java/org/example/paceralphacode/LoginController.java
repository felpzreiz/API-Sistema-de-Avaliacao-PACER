package org.example.paceralphacode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

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

    protected void idEmail() {
        idEmail.setText("professor@fatec.sp.gov.br");            //Defini que o email padrão
    }

    @FXML
    private PasswordField idSenha;

    protected void idSenha() {
        idSenha.setText("acessoprofessor");                                                        //Defini que a senha padrão
    }


    @FXML
    private AnchorPane login;

    @FXML
    private void onButtonClick() throws IOException {
        if (idEmail.getText().equals("professor@fatec.sp.gov.br") && idSenha.getText().equals("acessoprofessor")) {           // Verifica se o email e senha estão de acordo, em caso positivo irá iniciar uma nova cena
            FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("Home.fxml"));       //  Instancia uma nova cena que vai da Login.fxml para Home.fxml
            Scene scene = new Scene(fxmlLoader.load());                                                                                                           // Carrega a Classe FXML para criar uma Cena
            Stage newstage = new Stage();                                                                                                                                   // Stage é como Window, aqui é instanciado uma nova WINDOW
            newstage.setTitle("Home PACER");                                                                                                                          // Declarado o título da window
            newstage.setScene(scene);
            newstage.setMaximized(true);
            newstage.show();
                                                                                                                                                              /*  newstage.setScene(scene) -> Aqui o setScene coloca a "Cena dentro da Window"
                                                                                                                                                               newstage.setMaximized(true) -> Abre a pagina maximizada para o usuário
                                                                                                                                                               newstage.show() - > Executa a cena */
            Stage stage = (Stage) login.getScene().getWindow();                                                                 // Obtem a cena da janela anterior que é a de Login e fecha
            stage.close();

        } else if (idEmail.getText().equals("professor@fatec.sp.gov.br") != idSenha.getText().equals("acessoprofessor")) {                 // Verifica se a senha é diferente
            checkEmailSenha.setText("E-mail ou Senha incorreta, tente novamente.");                                                                                 //  No SceneBuilder foi colocado uma Label Invisivel com id: checkEmailSenha
            checkEmailSenha.setVisible(true);                                                                                                                                              //  Faz a Label Aparecer
        } else if (idEmail.getText().isEmpty() || idSenha.getText().isEmpty() || idEmail.getText().equals("") || idSenha.getText().equals("")) {                            //  Verifica se os campos estão vazios
            checkEmailSenha.setText("Preencha E-mail e Senha, tente novamente.");
            checkEmailSenha.setVisible(true);
        }
    }

    @FXML
    public void initialize() {                                                                                                                                              // Arquivo CSS para customizar os botões
        String css = Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm();        // Instancia o arquivo styles.css em uma String que está salva com os arquivos fxml
        btn.getStylesheets().add(css);                                                                                                                          // menuFix é uma estrutura com vários botões, aqui o arquivo css muda sua interface
    }
}