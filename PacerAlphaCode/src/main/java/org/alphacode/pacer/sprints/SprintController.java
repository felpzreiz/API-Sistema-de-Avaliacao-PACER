package org.alphacode.pacer.sprints;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SprintController {

    @FXML
    private TextField addCriterio;

    @FXML
    private DatePicker addData;

    @FXML
    private Button btnAdicionarC;

    @FXML
    private Button btnRemoverC;

    @FXML
    private ListView<String> listViewCriterios;

    private ObservableList<String> criterios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listViewCriterios.setItems(criterios);
    }

    @FXML
    void Adicionarcriterio(ActionEvent event) {

        String criterioText = addCriterio.getText();


        if (criterioText != null && !criterioText.isEmpty()) {
            // Adiciona o critério à lista
            criterios.add(criterioText);
            // Limpa o TextField
            addCriterio.clear();
        } else {}
    }

    @FXML
    void Removercriterio(ActionEvent event) {

        String selecionado = listViewCriterios.getSelectionModel().getSelectedItem();
        if (selecionado != null) {

            criterios.remove(selecionado);
        } else {}
    }


    public void editarsprint(ActionEvent event) {
    }

    public void Removersprint(ActionEvent event) {
    }

    public void acaocriterio(ActionEvent event) {
    }

    public void acaodata(ActionEvent event) {
    }
}
