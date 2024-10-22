package org.alphacode.pacer.grupos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;

import java.io.IOException;

public class TelaDefinirNomeGrupo {
    @FXML
    private Label campoobrigatorio2;

    @FXML
    private VBox fundodefinirgrupo;

    @FXML
    private Button botaoSalvar;

    @FXML
    private TextField grupoNomeField;

    @FXML
    void handleSalva(ActionEvent event) throws IOException {
        if (grupoNomeField.getText().isEmpty()) {
            campoobrigatorio2.setText("Campo Obrigatório");
            campoobrigatorio2.setVisible(true);
        } else {


            FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/grupos/CadastroGrupo.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage newstage = new Stage();
            newstage.setTitle("Cadastrar Grupo");
            newstage.setScene(scene);
            newstage.show();

            Stage stage = (Stage) fundodefinirgrupo.getScene().getWindow();
            stage.close();
        }


    }

    public void setDialogStage(Stage dialog, ListView<String> telagrupos) {
    }
}
