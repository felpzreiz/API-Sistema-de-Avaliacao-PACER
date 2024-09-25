package org.example.paceralphacode;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.TextFieldTableCell;

public class TelaCadastroGrupo {

    @FXML
    private Button addaluno;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btsalvar;

    @FXML
    private TableColumn<User,String> email;

    @FXML
    private AnchorPane fundo;

    @FXML
    private TextField inseriremail;

    @FXML
    private TableView<User> table;
    private ObservableList<User>users;

}
