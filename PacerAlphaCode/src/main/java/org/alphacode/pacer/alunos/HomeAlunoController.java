package org.alphacode.pacer.alunos;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class HomeAlunoController {

    @FXML
    private AnchorPane HomeAluno;

    @FXML
    private TableColumn<?, ?> ataluno;

    @FXML
    private TableColumn<?, ?> atautonomia;

    @FXML
    private TableColumn<?, ?> atcolaboracao;

    @FXML
    private TableColumn<?, ?> atentrega;

    @FXML
    private TableColumn<?, ?> atproatividade;

    @FXML
    private GridPane gripavaliacao;

    @FXML
    private ImageView logofatec;

    @FXML
    private TableView<?> tbavaliacao;

    @FXML
    private Label textalpha;

    @FXML
    private Label textpacer;

}
