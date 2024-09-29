package org.example.paceralphacode;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TelaCadastroGrupo {

    @FXML
    private Button addaluno;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btsalvar;

    @FXML
    private TableColumn<org.example.paceralphacode.User,String> email;

    @FXML
    private AnchorPane fundo;

    @FXML
    private TextField inseriremail;

    @FXML
    private TableView<org.example.paceralphacode.User> table;
    private ObservableList<User>users;

}
