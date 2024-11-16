package org.alphacode.pacer.password;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RedefinirSenha {



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
    private PasswordField confirmarSenha;

    @FXML
    private Label crSenha;

    @FXML
    private GridPane gridSenha;

    @FXML
    private PasswordField nvSenha;

    @FXML
    private PasswordField nvSenha1;


    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) PaneSenha.getScene().getWindow();
        stage.close();
    }

    public void saveNewPass(ActionEvent actionEvent) {
    }
}
