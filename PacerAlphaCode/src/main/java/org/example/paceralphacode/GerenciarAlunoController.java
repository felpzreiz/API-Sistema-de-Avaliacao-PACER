package org.example.paceralphacode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import conexao.OperacoesSQL;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class GerenciarAlunoController {


    @FXML
    private TextField writeStudent;

    @FXML
    private Button buttonAddStudent;

    @FXML
    public Button buttonRemoveStudent;
    @FXML
    public Button buttonEditStudent;
    @FXML
    public Button buttonImportStudent;
    @FXML
    public Button buttonBuscarStudent;

    @FXML
    private Label checkStudent;

    @FXML
    private Label checkStudent1;

    @FXML
    private Label nStudents;

    private ObservableList<org.example.paceralphacode.Alunos> Alunos;

    @FXML
    private AnchorPane gAlunos;

    @FXML
    private TableColumn<org.example.paceralphacode.Alunos, String> tableViewStudent;

    @FXML
    private TableView<org.example.paceralphacode.Alunos> viewStudent;

    public void initialize() {
        Alunos = FXCollections.observableArrayList();

        tableViewStudent.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableViewStudent.setCellFactory(TextFieldTableCell.forTableColumn());

        tableViewStudent.setOnEditCommit(event -> {
            org.example.paceralphacode.Alunos aluno = event.getRowValue();
            aluno.setName(event.getNewValue());
        });

        buttonAddStudent.setOnAction(event -> {
            try {
                buttonAddStudent();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        viewStudent.setItems(Alunos);
        style();
    }


    @FXML
    private void buttonAddStudent() throws SQLException {
        String Aluno = writeStudent.getText();
        if (writeStudent.getText().isEmpty()) {
            checkStudent.setText("Informe o e-mail do aluno.");
            checkStudent.setVisible(true);
        } else {
            checkStudent.setVisible(false);
        }
        if (Aluno != null && !Aluno.isEmpty()) {
            Alunos.add(new Alunos(Aluno));
            writeStudent.clear();
            nStudents();
        }
        
        OperacoesSQL teste = new OperacoesSQL();
        Statement stm = teste.conectarBanco();
        OperacoesSQL.inserir(stm, Aluno);
        
    }

    private void nStudents() {
        int qtd = Alunos.size();
        nStudents.setText(String.valueOf(qtd));
    }

    @FXML
    private void removeSelectedStudent() {
        org.example.paceralphacode.Alunos selectedStudent = viewStudent.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            Alunos.remove(selectedStudent);
            checkStudent1.setVisible(false);
            nStudents();
        } else {
            checkStudent1.setText("Selecione um aluno para remover.");
            checkStudent1.setVisible(true);
        }
    }

    @FXML
    public void style() {
        String css = Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm();
        buttonAddStudent.getStylesheets().add(css);
        buttonRemoveStudent.getStylesheets().add(css);
        buttonImportStudent.getStylesheets().add(css);
        buttonEditStudent.getStylesheets().add(css);
        buttonBuscarStudent.getStylesheets().add(css);


    }

    public void EditedSelectedStudent(ActionEvent actionEvent) {
    }

    public void ImportSelectedStudent(ActionEvent actionEvent) {
    }

    public void buttonBuscarStudent(ActionEvent actionEvent) {
    }
}

