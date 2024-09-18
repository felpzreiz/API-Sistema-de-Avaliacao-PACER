package org.example.paceralphacode;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class TelaController {

    @FXML
    private Label Pacer;

    @FXML
    private Button btn;

    @FXML
    protected void onButtonClick() {

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(50), btn);
        scaleTransition.setFromX(1.0);
        scaleTransition.setToX(0.9);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToY(0.9);
        scaleTransition.setCycleCount(1);
        btn.setOnMousePressed(event -> scaleTransition.play());

        btn.setText("Bem Vindo!");
    }

    @FXML
    private ImageView fatec;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private TextField idEmail;

    @FXML
    private PasswordField idSenha;

    @FXML
    private AnchorPane login;

    @FXML
    private Hyperlink newUser;

    @FXML
    protected void abrirnewUser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("newUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Usuario");
        stage.setScene(scene);
        stage.show();
    }


}
