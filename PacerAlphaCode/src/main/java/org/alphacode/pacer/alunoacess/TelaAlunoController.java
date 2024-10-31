package org.alphacode.pacer.alunoacess;

import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.converter.FloatStringConverter;

import java.util.ArrayList;
import java.util.List;

public class TelaAlunoController {

    @FXML
    private Button btconfigurar;

    @FXML
    private Button btsair;

    @FXML
    private Button btsalvar;

    @FXML
    private TableColumn<AlunosInterface, String> columnStudent;

    @FXML
    private AnchorPane dadosAluno;

    @FXML
    private GridPane gridavaliacao;

    @FXML
    private SplitPane homeAlunos;

    @FXML
    private ImageView logofatec;

    @FXML
    private Label nomealuno;

    @FXML
    private Label nomegrupo;

    @FXML
    private Label qtPontos;

    @FXML
    private AnchorPane tabelaAlunos;

    @FXML
    private TableView<AlunosInterface> tableStudents;

    @FXML
    private Label textDuracao;

    @FXML
    private Label textSprint;

    @FXML
    private Label textpacer;

    @FXML
    private Label tpRestante;


    private ObservableList<AlunosInterface> listaAlunos;

    public void initializeTable(ObservableList<String> column) {
        listaAlunos = FXCollections.observableArrayList();
        columnStudent.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableStudents.setItems(listaAlunos);
        tableStudents.setEditable(true);
        teste();

        for (String col : column) {
            TableColumn<AlunosInterface, Float> tableColumn = new TableColumn<>(col);
            tableColumn.setCellValueFactory(cellData -> {
                int notaAluno = column.indexOf(col);
                if (notaAluno < cellData.getValue().getNotas().size()) {
                    return new SimpleFloatProperty(cellData.getValue().getNotas().get(notaAluno).getNota()).asObject();

                } else {
                    return null;
                }
            });
            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            tableColumn.setOnEditCommit(event -> {
                AlunosInterface aluno = event.getRowValue();
                int valor = column.indexOf(col);
                float nota = event.getNewValue();

                if (nota >= 0 && nota <= 3) {
                    if (valor >= aluno.getNotas().size()) {
                        aluno.addNota(new Notas(event.getNewValue()));
                    } else {
                        aluno.getNotas().get(valor).setNota(event.getNewValue());
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.show();
                }
            });
            tableStudents.getColumns().add(tableColumn);
        }
    }

    @FXML
    public void teste() {
        List<AlunosInterface> novosAlunos = new ArrayList<>();
        novosAlunos.add(new AlunosInterface("José Wesley"));
        novosAlunos.add(new AlunosInterface("Miguel"));
        novosAlunos.add(new AlunosInterface("Felipe"));
        adicionarAlunos(novosAlunos);
    }

    public void adicionarAlunos(List<AlunosInterface> alunos) {
        listaAlunos.addAll(alunos);
    }

}
