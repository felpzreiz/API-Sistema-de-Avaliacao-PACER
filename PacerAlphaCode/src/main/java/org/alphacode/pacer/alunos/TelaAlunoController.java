package org.alphacode.pacer.alunos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class TelaAlunoController {

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
    private Button btconfigurar;

    @FXML
    private Button btsair;

    @FXML
    private Button btsalvar;

    @FXML
    private AnchorPane dadosAluno;

    @FXML
    private GridPane gridavaliacao;

    @FXML
    private ImageView logofatec;

    @FXML
    private Label nomealuno;

    @FXML
    private Label nomegrupo;

    @FXML
    private Label qtPontos;

    @FXML
    private SplitPane split;

    @FXML
    private AnchorPane tabelaAlunos;

    @FXML
    private TableView<?> tbavaliacao;

    @FXML
    private Label textDuracao;

    @FXML
    private Label textSprint;

    @FXML
    private Label textpacer;

    @FXML
    private Label tpRestante;

}
