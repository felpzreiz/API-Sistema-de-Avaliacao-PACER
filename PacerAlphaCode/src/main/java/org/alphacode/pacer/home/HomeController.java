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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import conexao.OperacoesSQL;
import org.alphacode.pacer.alunos.Alunos;
import org.alphacode.pacer.password.RedefinirSenha;
import org.alphacode.pacer.sprintsCriterios.Datas;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeController {



    Statement stm = OperacoesSQL.conectarBanco();

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
    private TableColumn<Datas, Integer> nSprint;

    @FXML
    private TableColumn<Datas, String> inicioSprint;  // Alterado para String para formatar a data

    @FXML
    private TableColumn<Datas, String> fimSprint;  // Alterado para String para formatar a data

    @FXML
    private TableColumn<Datas, String> fimAvaliacao;

    @FXML
    private TableView<Datas> tableSprint;

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
    private TableView tableSprinthome;

    @FXML
    private ListView gruposhome;

    @FXML
    private Button gerarrelatoriogrupo;

    @FXML
    private Button gerarrelatorioaluno;

    @FXML
    private ListView alunoshome;

    @FXML
    private ListView criterioshome;

    @FXML
    private ChoiceBox<String> sprintAluno;

    @FXML
    private ChoiceBox<String> sprintGrupo;


    private ObservableList<Datas> dataSprint = FXCollections.observableArrayList();
    private ObservableList<String> dadosaluno = FXCollections.observableArrayList();
    private ObservableList<String> dadoscriterios = FXCollections.observableArrayList();
    private ObservableList<String> dadosgrupos = FXCollections.observableArrayList();
    ArrayList<String> sprints = new ArrayList<>();

    public HomeController() throws SQLException {
    }

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
    public void initialize() {
        getSprint();

        sprintAluno.getItems().addAll(sprints);
        sprintAluno.setOnAction(this::getSprintAluno);

        sprintGrupo.getItems().addAll(sprints);
        sprintGrupo.setOnAction(this::getSprintGrupo);


        // Configuração das colunas da table
        nSprint.setCellValueFactory(new PropertyValueFactory<>("idSprint"));
        inicioSprint.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataInicial())));
        fimSprint.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataFinal())));
        fimAvaliacao.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataFinalAv())));
        tableSprint.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        tableSprint.setItems(dataSprint);
        carregarDatas();
        carregarCriterios();  // Carrega os critérios na ListView
        carregarGrupos();  // Carrega os grupos na ListView
        style();  // Estilo do menu
    }



    @FXML
    public void style() {
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm();
        menuFix.getStylesheets().add(css);
    }


    private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data != null ? data.format(formatter) : "";  // Retorna uma string vazia caso o valor seja null
    }

    public void infoAlphaCode(ActionEvent actionEvent) {
    }

    public void openConfig(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/alphacode/pacer/password/RedefinirSenha.fxml"));
            Parent root = loader.load();
            RedefinirSenha controller = loader.getController();
            controller.setEmail("professor@fatec.sp.gov.br");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Senha do Usuario");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    void carregarDatas() {
        tableSprint.getItems().clear();
        List<Datas> datas = OperacoesSQL.carregarDatas(stm);
        dataSprint.addAll(datas);
        tableSprint.setItems(dataSprint);
        List<Alunos> listaalunos = OperacoesSQL.dadosaluno(stm);
        dadosaluno.clear();  // Limpa a lista antes de adicionar novos dados

        for (Alunos aluno : listaalunos) {
            dadosaluno.add(aluno.getNome());  // Adiciona o nome do aluno (String)
        }

        alunoshome.setItems(dadosaluno);  // Atualiza a ListView com os novos dados
    }
    @FXML
    void carregarCriterios() {
        criterioshome.getItems().clear();  // Limpa a ListView de critérios

        // Obtenha a lista de critérios do banco de dados
        List<String> listaCriterios = OperacoesSQL.dadosCriterios(stm);

        // Limpa a lista de critérios atual antes de adicionar novos dados
        dadoscriterios.clear();

        // Adiciona os critérios na lista
        dadoscriterios.addAll(listaCriterios);  // Adiciona todos os critérios de uma vez

        // Atualiza a ListView com os novos dados
        criterioshome.setItems(dadoscriterios);

        // Debug: Imprime os critérios para verificar se está funcionando
        for (String criterio : dadoscriterios) {
            System.out.println(criterio);  // Imprime os critérios para depuração
        }
    }
    @FXML
    void carregarGrupos() {
        gruposhome.getItems().clear();  // Limpa a ListView de grupos

        // Obtenha a lista de grupos do banco de dados
        List<String> listaGrupos = OperacoesSQL.dadosGrupos(stm);

        // Limpa a lista de grupos atual antes de adicionar novos dados
        dadosgrupos.clear();

        // Adiciona os grupos na lista
        dadosgrupos.addAll(listaGrupos);  // Adiciona todos os grupos de uma vez

        // Atualiza a ListView com os novos dados
        gruposhome.setItems(dadosgrupos);

        // Debug: Imprime os grupos para verificar se está funcionando
        for (String grupo : dadosgrupos) {
            System.out.println(grupo);  // Imprime os grupos para depuração
        }
    }

    public Integer getSprintAluno(ActionEvent event) {
        Integer sprint = Integer.parseInt(sprintAluno.getValue());
        return (sprint);
    }

    public Integer getSprintGrupo(ActionEvent event) {
        Integer sprint = Integer.parseInt(sprintGrupo.getValue());
        return (sprint);
    }

    public void getSprint() {
        ArrayList<String> sprints = OperacoesSQL.consultarSprints(stm);
        this.sprints = sprints;
    }



    public void alunoCSV(ActionEvent actionEvent) {
        try {
            FileChooser file = new FileChooser();
            file.setTitle("Salvar Arquivo CSV");
            file.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

            File path = file.showSaveDialog(null);

            if (path != null) {
                String filePath = path.getAbsolutePath();
                int alunoId = 1;
                int sprintId = 31;
                OperacoesSQL.gerarCSV(stm, filePath, alunoId, sprintId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }





}
