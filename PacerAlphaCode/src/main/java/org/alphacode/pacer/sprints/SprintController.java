package org.alphacode.pacer.sprints;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.beans.property.SimpleBooleanProperty;
import java.time.LocalDate;

public class SprintController {

    @FXML
    private TextField addCriterio;

    @FXML
    private DatePicker addDataF; // Data de Fim

    @FXML
    private DatePicker addDataI; // Data de Início

    @FXML
    private Button btnAdicionarC; // Adicionar Variável
    @FXML
    private Button btnAdicionarS; // Adicionar Sprint
    @FXML
    private Button btnEditar; // Editar Sprint
    @FXML
    private Button btnRemoverC; // Remover Variável
    @FXML
    private Button btnRemoverS; // Remover Sprint

    @FXML
    private TableColumn<Variavel, String> clnCriterio; // Coluna para a variável
    @FXML
    private TableColumn<Variavel, LocalDate> clnDataF; // Coluna para Data de Fim
    @FXML
    private TableColumn<Variavel, LocalDate> clnDataI; // Coluna para Data de Início
    @FXML
    private TableColumn<Variavel, Boolean> clncheck; // Coluna para checkbox
    @FXML
    private TableColumn<Variavel, Integer> clnSprint; // Coluna para número da Sprint

    @FXML
    private TableView<Variavel> tblSprint; // Tabela para exibir as variáveis

    private final ObservableList<Variavel> variaveis = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        // Configuração das colunas
        clnCriterio.setCellValueFactory(new PropertyValueFactory<>("criterio"));
        clnDataF.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        clnDataI.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        clncheck.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isChecked()));
        clncheck.setCellFactory(CheckBoxTableCell.forTableColumn(clncheck));

        tblSprint.setItems(variaveis);
    }

    @FXML
    void Adicionarcriterio(ActionEvent event) {
        String criterioTexto = addCriterio.getText();
        if (!criterioTexto.isEmpty()) {
            Variavel novaVariavel = new Variavel(criterioTexto, null, null, false); // Checkbox desmarcada
            variaveis.add(novaVariavel);
            addCriterio.clear(); // Limpa o campo de texto após adicionar
            tblSprint.refresh(); // Atualiza a tabela para refletir a nova entrada
        }
    }

    @FXML
    void Adicionardatas(ActionEvent event) {
        LocalDate dataInicio = addDataI.getValue();
        LocalDate dataFim = addDataF.getValue();

        Variavel selectedVariavel = tblSprint.getSelectionModel().getSelectedItem();
        if (selectedVariavel != null) {
            if (dataInicio != null) {
                selectedVariavel.setDataInicio(dataInicio);
            }
            if (dataFim != null) {
                selectedVariavel.setDataFim(dataFim);
            }
            tblSprint.refresh(); // Atualiza a tabela após adicionar as datas
            updateSprintNumbers(); // Atualiza os números da coluna de sprint
        }
    }

    @FXML
    void Removercriterio(ActionEvent event) {
        Variavel selectedVariavel = tblSprint.getSelectionModel().getSelectedItem();
        if (selectedVariavel != null) {
            variaveis.remove(selectedVariavel);
            tblSprint.refresh(); // Atualiza a tabela após a remoção
            updateSprintNumbers(); // Atualiza os números da coluna de sprint
        }
    }

    @FXML
    void Removersprint(ActionEvent event) {
        Variavel selectedVariavel = tblSprint.getSelectionModel().getSelectedItem();
        if (selectedVariavel != null) {
            selectedVariavel.setDataInicio(null); // Remove a data de início
            selectedVariavel.setDataFim(null); // Remove a data de fim
            tblSprint.refresh(); // Atualiza a tabela após a remoção das datas
            updateSprintNumbers(); // Atualiza os números da coluna de sprint
        }
    }

    @FXML
    void editarsprint(ActionEvent event) {
        LocalDate dataInicio = addDataI.getValue();
        LocalDate dataFim = addDataF.getValue();

        Variavel selectedVariavel = tblSprint.getSelectionModel().getSelectedItem();
        if (selectedVariavel != null) {
            if (dataInicio != null) {
                selectedVariavel.setDataInicio(dataInicio);
            }
            if (dataFim != null) {
                selectedVariavel.setDataFim(dataFim);
            }
            tblSprint.refresh(); // Atualiza a tabela após a edição das datas
            updateSprintNumbers(); // Atualiza os números da coluna de sprint
        }
    }

    private void updateSprintNumbers() {
        for (int i = 0; i < variaveis.size(); i++) {
            Variavel v = variaveis.get(i);
            if (v.getDataInicio() != null || v.getDataFim() != null) {
                v.setCriterio("Sprint " + (i + 1)); // Atualiza o critério para exibir o número da sprint
            }
        }
        tblSprint.refresh(); // Atualiza a tabela para refletir as mudanças
    }
}
