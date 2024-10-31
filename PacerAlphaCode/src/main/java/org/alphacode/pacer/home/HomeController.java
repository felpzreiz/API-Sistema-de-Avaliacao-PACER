package org.alphacode.pacer.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class HomeController {

    @FXML
    private AnchorPane Homepage;    // A manipulação de paginas é definida pela AnchorPane que muda conforme a opção do VBOX menuFIX

    @FXML
    private Button botaogerenciaraluno;

    @FXML
    private Button botaogerenciargrupos;

    @FXML
    private Button botaogerenciarsprints;

    @FXML
    private ImageView logo;

    @FXML
    private Button botaohome;

    @FXML
    private Button exit;

    @FXML
    private ImageView fatec;

    @FXML
    private ImageView SprintImage;

    @FXML
    private ImageView grupimage;

    @FXML
    private Button botaocriterio;

    @FXML
    private VBox menuFix;

    @FXML
    void exitPacer(ActionEvent event) {
        Stage stage = (Stage) pacer.getScene().getWindow();
        stage.close();
    }

    @FXML
    private SplitPane pacer;    // A SplitPane é a interface que permite a separação do menu na vertical esquerda (VBOX menuFix) e a manipulação dos dados do lado direito (AnchorPane Homepage).

    @FXML
    void pageAlunos(ActionEvent event) throws IOException, SQLException {                                                                       //  Método pageAlunos() Abre a interface a direita da pagina correspondente
        Homepage.getChildren().clear();                                                                                                           //  getChildren é como obter os filhos "imagine uma lista de sequencia de paginas", o filho seria o que esta abaixo, o clear limpa o que está na (AnchorPane Homepage)
        Parent gAlunos = FXMLLoader.load(getClass().getResource("/org/alphacode/pacer/alunos/GerenciarAluno.fxml"));    // Parent é uma Classe para organizar as estruturas graficas. Aqui ele obtem a AnchorPane gAlunos que se encontra no arquivo fxml
        Homepage.getChildren().add(gAlunos);                                                                                           //  Aqui a Homepagen muda para a outra AnchorPane
    }

    @FXML
    void pageGroup(ActionEvent event) throws IOException {
        Homepage.getChildren().clear();
        Parent gGroup = FXMLLoader.load(getClass().getResource("/org/alphacode/pacer/grupos/Grupo.fxml"));
        Homepage.getChildren().add(gGroup);
    }

    @FXML
    void pageHome(ActionEvent event) throws IOException {                                                                //  Para voltar a pagina Home devido ser uma SplitPane é preciso obter os dados da pagina inteira
        pacer.getItems().clear();                                                                                                                 //  O SplitPane está com id:pacer, desse modo o pacer obtem todos os itens grafico e limpa pois está atuando com outras AnchorPane
        Parent Pacer = FXMLLoader.load(getClass().getResource("/org/alphacode/pacer/home/Home.fxml"));                 //  Instancia a ordem gráfica e obtem a home.fxml que é a pagina inicial
        pacer.getItems().add(Pacer);                                                                                                     //  O getItems obtem todas as informações da tela, desse modo retorna todos os objetos contendo na pacer(SplitPane)
    }

    @FXML
    void pageSprint(ActionEvent event) throws IOException {
        Homepage.getChildren().clear();
        Parent gSprints = FXMLLoader.load(getClass().getResource("/org/alphacode/pacer/sprintsCriterios/SprintCriterios.fxml"));
        Homepage.getChildren().add(gSprints);
    }

    @FXML
    public void initialize() {                                                                                                                                                  // Arquivo CSS para customizar os botões
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm();                   // Instancia o arquivo styles.css em uma String que está salva com os arquivos fxml
        menuFix.getStylesheets().add(css);                                                                                                                           // menuFix é uma estrutura com vários botões, aqui o arquivo css muda sua interface
    }

    @FXML
    void pageCriterios(ActionEvent event) throws IOException {
        Homepage.getChildren().clear();
        Parent gCriterios = FXMLLoader.load(getClass().getResource("/org/alphacode/pacer/criterios/Criterios.fxml"));
        Homepage.getChildren().add(gCriterios);

    }


}
