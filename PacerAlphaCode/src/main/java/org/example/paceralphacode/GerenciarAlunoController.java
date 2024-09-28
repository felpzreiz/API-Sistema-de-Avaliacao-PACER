package org.example.paceralphacode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GerenciarAlunoController {


    @FXML
    private TextField addStudent;

    @FXML
    private Button buttonAddStudent;
    private ObservableList<Alunos> Alunos;
    @FXML
    private AnchorPane gGroups;

    @FXML
    private TableColumn<Alunos, String> tableViewStudent;

    @FXML
    private TableView<Alunos> viewStudent;




    private void buttonAddStudent() throws IOException {
        String email = this.addStudent.getText();
        if (email != null && !email.isEmpty()) {
            this.Alunos.add(new Alunos(email));
            this.addStudent.clear();
        }
    }
}