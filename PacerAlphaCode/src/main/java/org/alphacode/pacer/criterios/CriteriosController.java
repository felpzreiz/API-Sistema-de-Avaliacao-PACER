package org.alphacode.pacer.criterios;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CriteriosController {

    @FXML
    private TextField addStudent;

    @FXML
    private Button buttonAddStudent;

    @FXML
    private AnchorPane gCriterio;

    @FXML
    private TableColumn<?, ?> tableViewStudent;

    @FXML
    private TableView<?> viewStudent;

}