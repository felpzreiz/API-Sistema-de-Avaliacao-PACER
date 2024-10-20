package org.alphacode.pacer.grupos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;

import java.io.IOException;

public class TelaDefinirNomeGrupo {

    public TextField grupoNomeField;

    @FXML
    private Button botaocancelar;

    @FXML
    private Button botaoconfirmar;

    @FXML
    private Label campoobrigatorio2;

    @FXML
    private AnchorPane fundodefinirgrupo;

    @FXML
    private Label insiraonomedogrupo;

    @FXML
    private TextField nomedogrupo;
    @FXML
    public Button botaoSalvar;

    @FXML
    void btcancelar(ActionEvent event) {
        Stage stage = (Stage) fundodefinirgrupo.getScene().getWindow();
        stage.close();


    }

    @FXML
    void btconfirmar(ActionEvent event) throws IOException {
        if (nomedogrupo.getText().isEmpty()) {
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

    @FXML
    void labalnomedogupo(ActionEvent event) {


    }


    public void setDialogStage(Stage dialog, ListView<String> telagrupos) {

    }

    public void handleSalva(ActionEvent actionEvent) {
    }
}