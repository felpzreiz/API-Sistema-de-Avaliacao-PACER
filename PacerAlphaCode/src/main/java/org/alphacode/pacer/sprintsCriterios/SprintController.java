package org.alphacode.pacer.sprintsCriterios;

import conexao.OperacoesSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;
import org.alphacode.pacer.alunoacess.TelaAlunoController;
import org.alphacode.pacer.alunos.AlunoController;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class SprintController {


    OperacoesSQL conexao = new OperacoesSQL();
    Statement stm = OperacoesSQL.conectarBanco();


    @FXML
    private AnchorPane pageSprintCriterios;

    @FXML
    private DatePicker addDataF;

    @FXML
    public Text textCriterios;

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

    public SprintController() throws SQLException {
    }


    public void initialize() throws SQLException {
        style();
        criterios();
        criterios.setItems(lista);
        exibirInstrucoes();

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
    void criterios() throws SQLException {
        List<Criterios> criteriosC = OperacoesSQL.carregarCriterios(stm);
        lista.addAll(criteriosC);
        criterios.setItems(lista);
    }

    @FXML
    void addC(ActionEvent event) {
        if (nomeCriterio.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campo vazio.");
            alert.setContentText("Informe um critério.");
            alert.show();
        } else {
            String coluna = nomeCriterio.getText().trim();
            Criterios novoCriterio = new Criterios(coluna);
            column.add(coluna);
            lista.add(novoCriterio);
            criterios.setItems(lista);
            nomeCriterio.clear();
            OperacoesSQL.inserirCriterio(stm, coluna);
        }
    }

    @FXML
    void deleteC(ActionEvent event) {
        Criterios select = criterios.getSelectionModel().getSelectedItem();

        if (select == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhum Critério Selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um critério para remover.");
            alert.show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar Remoção");
        confirm.setHeaderText("Você tem certeza que deseja remover este critério?");
        confirm.setContentText("Critério: " + select.getNomeCriterio());

        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        confirm.getButtonTypes().setAll(btnSim, btnNao);

        confirm.showAndWait().ifPresent(response -> {
            if (response == btnSim) {
                lista.remove(select);
                column.remove(select.getNomeCriterio());
                criterios.getSelectionModel().clearSelection();
                OperacoesSQL.deleteCriterio(stm, select.getNomeCriterio());
            }
        });
    }

    @FXML
    void editarsprint(ActionEvent event) {

    }

    private void exibirInstrucoes() {
        String instrucoes =
                """
                        Instruções para Uso de Critérios e Sprints:
                        
                        1. Defina os Critérios:
                           - Antes de iniciar uma Sprint, é fundamental estabelecer os critérios de avaliação.
                        
                        2. Adicionar um Critério:
                           - Informe o critério desejado no campo apropriado e clique em **Adicionar**.
                        
                        3. Remover um Critério:
                           - Para remover um critério, selecione o item desejado na lista e clique em **Remover**.
                        
                        * ATENÇÃO!
                        Uma vez que o período de avaliação comece, não será possível remover critérios.
                        Certifique-se de que todos os critérios estejam corretos antes de iniciar a avaliação.""";

        textCriterios.setText(instrucoes); // Define o texto das instruções
    }


    @FXML
    public void style() {
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm();
        criterios.getStylesheets().add(css);
    }
}