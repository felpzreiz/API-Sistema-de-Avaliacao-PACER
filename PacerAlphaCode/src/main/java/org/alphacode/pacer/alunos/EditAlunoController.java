package org.alphacode.pacer.alunos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditAlunoController {

    @FXML
    private Label Pacer;

    @FXML
    private Button cancelBtn;

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
    public void initialize() throws SQLException {

        carregarDados();

    }

    AlunoController controller = new AlunoController();


    @FXML
    public void carregarDados() throws SQLException {


    }


    @FXML
    void cancelBtn(ActionEvent event) {
        Stage stage = (Stage) editAluno.getScene().getWindow();
        stage.close();

    }

    @FXML
    void save(ActionEvent event) {

    }

}
