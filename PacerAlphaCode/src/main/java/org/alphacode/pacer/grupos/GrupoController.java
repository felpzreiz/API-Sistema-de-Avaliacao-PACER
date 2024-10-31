package org.alphacode.pacer.grupos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;

import java.io.IOException;
import java.util.Optional;

public class GrupoController {
    @FXML
    public Button buttonEditGroup;

    @FXML
    private Button buttonImportGroup;

    @FXML
    public Button buttonRemoveGroup;

    @FXML
    private ListView<String> telagrupos;

    @FXML
    public Button botaoaddgrupo;

    @FXML
    private AnchorPane gGroups;

    @FXML
    void adicionargrupo(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/grupos/CadastroGrupo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setTitle("Defina o nome do Grupo");
        dialog.setScene(scene);
        dialog.show(); // Exibe a nova janela
    }
    @FXML
    void removeSelectedGroup(ActionEvent event) {
        String selectedGroup = telagrupos.getSelectionModel().getSelectedItem();
        if (selectedGroup != null) {
            // Criação do alerta
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Você realmente deseja remover este grupo?");
            alert.setContentText("Grupo: " + selectedGroup);

            // Adiciona botões para confirmação
            ButtonType yesButton = new ButtonType("Sim");
            ButtonType noButton = new ButtonType("Não");
            alert.getButtonTypes().setAll(yesButton, noButton);

            // Mostra o alerta e espera pela resposta do usuário
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                // Se o usuário confirmar, remove o grupo
                telagrupos.getItems().remove(selectedGroup);
            }
        } else {
            // Aqui você pode adicionar um alerta se nenhum grupo estiver selecionado
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nenhum grupo selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um grupo para remover.");
            alert.showAndWait();
        }
    }


    @FXML
    void ImportSelectedGroup(ActionEvent event) {

    }


    public void buttonEditGroup(MouseEvent mouseEvent) {

    }

    public void EditedSelectedGroup(ActionEvent actionEvent) throws IOException {
        String grupoSelecionado = telagrupos.getSelectionModel().getSelectedItem();
        if (grupoSelecionado != null) {
            Stage dialog = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/grupos/CadastroGrupo.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            dialog.setTitle("Editar Grupo");

            TelaCadastroGrupoController controller = fxmlLoader.getController();
            controller.setDialogStage(dialog, grupoSelecionado); // Passa o grupo selecionado

            dialog.setScene(scene);
            dialog.show();
        }
    }
    }

