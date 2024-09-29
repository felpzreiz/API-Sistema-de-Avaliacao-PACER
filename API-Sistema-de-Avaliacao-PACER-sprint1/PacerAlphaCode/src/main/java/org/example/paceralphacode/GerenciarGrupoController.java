package org.example.paceralphacode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class GerenciarGrupoController {
    @FXML

     private Button botaoaddgrupo;

    @FXML
    private AnchorPane gGroups;

    @FXML
    void adicionargrupo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("DefinirNomeGrupo.fxml"));       //  Instancia uma nova cena que vai da Login.fxml para Home.fxml
        Scene scene = new Scene(fxmlLoader.load());                                                                                                           // Carrega a Classe FXML para criar uma Cena
        Stage newstage = new Stage();                                                                                                                                   // Stage é como Window, aqui é instanciado uma nova WINDOW
        newstage.setTitle("Definir nome");                                                                                                                          // Declarado o título da window
        newstage.setScene(scene);
        newstage.show();

        }


    }


