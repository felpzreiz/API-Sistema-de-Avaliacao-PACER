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

import javafx.event.ActionEvent;

import java.io.IOException;


public class GerenciarAlunoController {




    @FXML
    private Button buttonAddStudent;
    private ObservableList<Alunos> alunos;
    @FXML
    private AnchorPane gGroups;

    @FXML
    private TableColumn<Alunos, String> tableViewStudent;

    @FXML
    private TextField whriteStudent;

    @FXML
    private TableView<Alunos> viewStudent;
    private TelaCadastroGrupoController Alunos;

    public void initialize() {
        alunos = FXCollections.observableArrayList();


        tableViewStudent.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableViewStudent.setCellFactory(TextFieldTableCell.forTableColumn());



        buttonAddStudent.setOnAction(event -> buttonAddStudent());


        viewStudent.setItems(alunos);
    }
    private void buttonAddStudent() {
        String email = whriteStudent.getText();
        if (email != null && !email.isEmpty()) {
            alunos.add(new Alunos(email));
            whriteStudent.clear();
        }
    }

    @FXML
    void ActionbuttonAddStudent(ActionEvent event) {

    }


    @FXML
    void ActionwhriteStrudent(ActionEvent event) {

    }

}