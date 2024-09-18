package org.example.paceralphacode;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NewUser1Controller {

    @FXML
    private Label Pacer;

    @FXML
    private Button cancelBtn;

    @FXML
    protected void cancelBtn() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button confirmBtn;

    @FXML
    private ImageView fatec;

}
