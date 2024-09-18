package org.example.paceralphacode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class NewUserController {

    @FXML
    private Label Pacer;

    @FXML
    private TextField acessCode;

    protected void acessCode() {
        acessCode.setText("professor");
    }

    @FXML
    private Button cancelBtn;

    @FXML
    protected void cancelBtn() {

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button confirmeCode;

    @FXML
    protected void confirmeCode() throws IOException {

        if (acessCode.getText().equals("professor")) {
            FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("newUser1.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Cadastro de Usuário");
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Código Incorreto");
            alert.setHeaderText(null);
            alert.setContentText("Código Incorreto. Tente novamente.");
            alert.showAndWait();
        }
    }

    @FXML
    private ImageView fatec;

}

