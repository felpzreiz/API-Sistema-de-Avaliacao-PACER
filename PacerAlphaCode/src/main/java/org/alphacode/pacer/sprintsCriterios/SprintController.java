package org.alphacode.pacer.sprintsCriterios;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;
import org.alphacode.pacer.alunoacess.TelaAlunoController;
import org.alphacode.pacer.alunos.AlunoController;

import java.io.IOException;

public class SprintController {


    @FXML
    private DatePicker addDataF;

    @FXML
    public Button teste;

    @FXML
    private DatePicker addDataI;

    @FXML
    private Button btnAddC;

    @FXML
    private Button btnAdicionarS;

    @FXML
    private Button btnDeleteC;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnRemoverS;

    @FXML
    private TableColumn<?, ?> clnDataF;

    @FXML
    private TableColumn<?, ?> clnDataI;

    @FXML
    private TableColumn<?, ?> clnSprint;

    @FXML
    private ListView<Criterios> criterios;

    @FXML
    private TextField nomeCriterio;

    @FXML
    private TableView<?> tblSprint;

    private ObservableList<String> column = FXCollections.observableArrayList();
    private ObservableList<Criterios> lista = FXCollections.observableArrayList();


    public void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/alphacode/pacer/alunosacess/TelaAluno.fxml"));
        AlunoController controller = loader.getController();

        criterios.setItems(lista);
    }


    @FXML
    void Adicionardatas(ActionEvent event) {

    }

    @FXML
    void Removersprint(ActionEvent event) {

    }

    @FXML
    void acaodataF(ActionEvent event) {

    }

    @FXML
    void acaodataI(ActionEvent event) {

    }

    @FXML
    void addC(ActionEvent event) {
        String coluna = nomeCriterio.getText().trim();
        Criterios novoCriterio = new Criterios(coluna);
        column.add(coluna);
        lista.add(novoCriterio);
        criterios.setItems(lista);
        nomeCriterio.clear();





    }

    @FXML
    void deleteC(ActionEvent event) {
        Criterios select = criterios.getSelectionModel().getSelectedItem();
        lista.remove(select);
        column.remove(select.getNomeCriterio());
        criterios.getSelectionModel().clearSelection();

    }

    @FXML
    void editarsprint(ActionEvent event) {

    }


    public void openteste(ActionEvent actionEvent) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/alphacode/pacer/alunoacess/TelaAluno.fxml"));
            Parent root = loader.load();

            TelaAlunoController controller = loader.getController();
            controller.initializeTable(column);


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
