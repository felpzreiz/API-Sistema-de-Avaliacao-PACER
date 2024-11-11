package org.alphacode.pacer.home;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.alphacode.pacer.ExecuteApplication;
import conexao.OperacoesSQL;
import org.alphacode.pacer.sprintsCriterios.Datas;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class HomeController {

    @FXML
    private AnchorPane Homepage;

    @FXML
    private Button botaogerenciaraluno;

    @FXML
    private Button alphaCode;

    @FXML
    private Button btnConfig;

    @FXML
    public TableView<Datas> tablegruposhome;

    @FXML
    private Button botaogerenciargrupos;

    @FXML
    private Button botaogerenciarsprints;

    @FXML
    private ImageView logo;

    @FXML
    private TableColumn<Datas, Integer> nSprintReplica;

    @FXML
    private TableColumn<Datas, String> inicioSprintReplica;  // Alterado para String para formatar a data

    @FXML
    private TableColumn<Datas, String> fimSprintReplica;  // Alterado para String para formatar a data

    @FXML
    private TableColumn<Datas, String> statusSprintReplica;

    @FXML
    private TableView<Datas> tableSprintReplica;

    @FXML
    private Button botaohome;

    @FXML
    private Button exit;

    @FXML
    private TableView tablesprinthome;

    @FXML
    private ImageView fatec;

    @FXML
    private ImageView SprintImage;

    @FXML
    private ImageView grupimage;

    @FXML
    private VBox menuFix;

    @FXML
    private SplitPane pacer;

    @FXML
    void exitPacer(ActionEvent event) throws IOException {
        Stage stage = (Stage) pacer.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/alphacode/pacer/home/Login.fxml"));
        Parent login = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle("Login");
        Scene scene = new Scene(login, 545, 620);
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();
    }

    @FXML
    void pageAlunos(ActionEvent event) throws IOException, SQLException {
        Homepage.getChildren().clear();
        Parent gAlunos = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/alunos/GerenciarAluno.fxml")));
        Homepage.getChildren().add(gAlunos);
    }

    @FXML
    void pageGroup(ActionEvent event) throws IOException {
        Homepage.getChildren().clear();
        Parent gGroup = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/grupos/Grupo.fxml")));
        Homepage.getChildren().add(gGroup);
    }

    @FXML
    void pageHome(ActionEvent event) throws IOException {
        pacer.getItems().clear();
        Parent Pacer = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/home/Home.fxml")));
        pacer.getItems().add(Pacer);
    }

    @FXML
    void pageSprint(ActionEvent event) throws IOException {
        Homepage.getChildren().clear();
        Parent gSprints = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/sprintsCriterios/SprintCriterios.fxml")));
        Homepage.getChildren().add(gSprints);
    }

    @FXML
    void pageCriterios(ActionEvent event) throws IOException {
        Homepage.getChildren().clear();
        Parent gCriterios = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/criterios/Criterios.fxml")));
        Homepage.getChildren().add(gCriterios);
    }

    @FXML
    public void initialize() throws SQLException {
        // Arquivo CSS para customizar os botões
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm();
        menuFix.getStylesheets().add(css);

        // Carregar as sprints na tabela
        carregarSprints();
    }

    private void carregarSprints() throws SQLException {
        // Criar uma lista de sprints
        OperacoesSQL operacoesSQL = new OperacoesSQL();
        Statement stm = operacoesSQL.conectarBanco();
        ObservableList<Datas> sprints = FXCollections.observableArrayList();

        // Carregar as sprints do banco de dados
        List<Datas> datas = operacoesSQL.carregarDatas(stm);
        sprints.addAll(datas);

        // Configurar as colunas da tabela
        nSprintReplica.setCellValueFactory(new PropertyValueFactory<>("idSprint"));

        // Para a coluna de data inicial, usar o formato "dd/MM/yyyy"
        inicioSprintReplica.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = cellData.getValue().getDataInicial().format(formatter);
            return new SimpleStringProperty(formattedDate);
        });

        // Para a coluna de data final, usar o formato "dd/MM/yyyy"
        fimSprintReplica.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = cellData.getValue().getDataFinal().format(formatter);
            return new SimpleStringProperty(formattedDate);
        });

        // Definindo status "Ativo" (você pode modificar isso conforme necessário)
        statusSprintReplica.setCellValueFactory(cellData -> new SimpleStringProperty("Ativo"));

        // Adicionar as sprints à tabela
        tableSprintReplica.setItems(sprints);

    }

    public void infoAlphaCode(ActionEvent actionEvent) {
    }

    public void openConfig(ActionEvent actionEvent) {
    }
}
