package org.alphacode.pacer.home;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import conexao.OperacoesSQL;
import org.alphacode.pacer.alunos.Alunos;
import org.alphacode.pacer.grupos.Grupo;
import org.alphacode.pacer.password.RedefinirSenha;
import org.alphacode.pacer.sprintsCriterios.Datas;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.lang.Integer.valueOf;

public class HomeController {



    Statement stm = OperacoesSQL.conectarBanco();

    @FXML
    private AnchorPane Homepage;

    @FXML
    private Button botaogerenciaraluno;

    @FXML
    private Button instruction;

    @FXML
    private Button alphaCode;

    @FXML
    private Button btnConfig;

    @FXML
    public TableView<Datas> tableviewGrupoH;

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
    private TableView<Datas> viewSprintH;

    @FXML
    private Button botaohome;

    @FXML
    private Button exit;

    @FXML
    private ImageView fatec;

    @FXML
    private ImageView grupimage;

    @FXML
    private VBox menuFix;

    @FXML
    private SplitPane pacer;

    @FXML
    private ListView<Grupo> viewGrupoH;

    @FXML
    private Button gerarrelatoriogrupo;

    @FXML
    private Button gerarrelatorioaluno;

    @FXML
    private ListView<String> viewCriteriosH;

    @FXML
    private TextField searchG;

    @FXML
    private ChoiceBox<String> sprintAluno;

    @FXML
    private ChoiceBox<String> sprintGrupo;


    private ObservableList<Datas> dataSprint = FXCollections.observableArrayList();
    private ObservableList<String> dadosaluno = FXCollections.observableArrayList();
    private ObservableList<String> dadoscriterios = FXCollections.observableArrayList();
    private ObservableList<Grupo> dadosgrupos = FXCollections.observableArrayList();
    ArrayList<String> sprints = new ArrayList<>();
    private FilteredList<Grupo> filteredDados;

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
        sprintAluno.setTooltip(new Tooltip("Selecione uma Sprint"));
        sprintGrupo.setTooltip(new Tooltip("Selecione uma Sprint"));
        searchG.setTooltip(new Tooltip("Informe o nome do Grupo"));
        btnConfig.setTooltip(new Tooltip("Alterar senha de Usuário"));
        instruction.setTooltip(new Tooltip("Manual do Usuário"));
        exit.setTooltip(new Tooltip("Sair"));

        sprintAluno.getItems().addAll(sprints);
        sprintAluno.setOnAction(this::getSprintAluno);

        sprintGrupo.getItems().addAll(sprints);
        sprintGrupo.setOnAction(this::getSprintGrupo);


        searchG.textProperty().addListener((observable, oldValue, newValue) -> {
            filterGroups(newValue);
        });


        // Configuração das colunas da table
        nSprint.setCellValueFactory(new PropertyValueFactory<>("idSprint"));
        inicioSprint.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataInicial())));
        fimSprint.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataFinal())));
        fimAvaliacao.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataFinalAv())));
        viewSprintH.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        viewSprintH.setItems(dataSprint);
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


    public void filterGroups(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            viewGrupoH.setItems(dadosgrupos);
        } else {

            FilteredList<Grupo> filteredList = new FilteredList<>(dadosgrupos, grupo ->
                    grupo.getNomeGrupo().toLowerCase().contains(filterText.toLowerCase())
            );
            viewGrupoH.setItems(filteredList);
        }
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
        viewSprintH.getItems().clear();
        List<Datas> datas = OperacoesSQL.carregarDatas(stm);
        dataSprint.addAll(datas);
        viewSprintH.setItems(dataSprint);
        List<Alunos> listaalunos = OperacoesSQL.dadosaluno(stm);
        dadosaluno.clear();  // Limpa a lista antes de adicionar novos dados

        for (Alunos aluno : listaalunos) {
            dadosaluno.add(aluno.getNome());  // Adiciona o nome do aluno (String)
        }

    }

    @FXML
    void carregarCriterios() {
        viewCriteriosH.getItems().clear();
        List<String> listaCriterios = OperacoesSQL.dadosCriterios(stm);
        dadoscriterios.clear();
        dadoscriterios.addAll(listaCriterios);
        viewCriteriosH.setItems(dadoscriterios);
    }

    @FXML
    void carregarGrupos() {
        viewGrupoH.getItems().clear();
        List<Grupo> listaGrupos = OperacoesSQL.dadosGrupos(stm);
        dadosgrupos.clear();
        dadosgrupos.addAll(listaGrupos);
        viewGrupoH.setItems(dadosgrupos);

    }

    public Integer getSprintAluno(ActionEvent event) {
        String sprint = sprintAluno.getValue();
        if (sprint == null || sprint.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(sprint);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Integer getSprintGrupo(ActionEvent event) {
        String sprint = sprintGrupo.getValue();
        if (sprint == null || sprint.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(sprint);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void getSprint() {
        ArrayList<String> sprints = OperacoesSQL.consultarSprints(stm);
        this.sprints = sprints;
    }


    public void alunoCSV(ActionEvent actionEvent) {
        try {
            if (getSprintAluno(actionEvent) != null) {
                try {
                    int sprintId = OperacoesSQL.getIdSprintChoice(stm, getSprintAluno(actionEvent));
                    FileChooser file = new FileChooser();
                    file.setTitle("Salvar Arquivo CSV");
                    file.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

                    File path = file.showSaveDialog(null);

                    if (path != null) {
                        String filePath = path.getAbsolutePath();
                        OperacoesSQL.gerarCSVAll(stm, filePath, sprintId);
                        showOk("Relatório CSV", null, "Salvo com Sucesso!");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Erro!", null, "Erro ao gerar o relatório CSV.");
                    throw new RuntimeException(e);
                }
            } else {
                showAlert("Erro!", null, "Informe a Sprint desejada!");
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void grupoCSV(ActionEvent actionEvent) {
        try {
            if (getSprintGrupo(actionEvent) != null) {
                try {
                    String nomeG = viewGrupoH.getSelectionModel().getSelectedItem().toString();
                    int grupoId = OperacoesSQL.getIdGroupName(stm, nomeG);
                    int grupoId1 = OperacoesSQL.getIdGroupName(stm, nomeG);
                    int sprintId = OperacoesSQL.getIdSprintChoice(stm, getSprintGrupo(actionEvent));

                    FileChooser file = new FileChooser();
                    file.setTitle("Salvar Arquivo CSV");
                    file.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

                    File path = file.showSaveDialog(null);

                    if (path != null) {
                        String filePath = path.getAbsolutePath();
                        OperacoesSQL.gerarCSVGroup(stm, filePath, grupoId, sprintId, grupoId1);

                        File csvFile = new File(filePath);
                        if (csvFile.length() > 0) {
                            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                                String linha;
                                int contLinhas = 0;
                                boolean linha2 = true;

                                while ((linha = br.readLine()) != null) {
                                    contLinhas++;
                                    if (contLinhas == 2 && !linha.trim().isEmpty()) {
                                        linha2 = false;
                                    }
                                }

                                if (contLinhas == 1 || linha2) {
                                    showAlert("Aviso!", "O arquivo CSV está vazio.", "Não foram identificadas avaliações para o grupo selecionado.");
                                } else {
                                    showOk("Relatório CSV", null, "Salvo com Sucesso!");
                                }
                            }
                        } else {
                            showAlert("Erro!", null, "O arquivo CSV está vazio.");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Erro!", null, "Erro ao gerar o relatório CSV.");
                }
            } else {
                showAlert("Erro!", null, "Informe a Sprint desejada!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro!", null, "Erro inesperado.");
        }
    }


    public void openIntruction(ActionEvent actionEvent) {
        try {
            File pdf = new File("PacerAlphaCode/src/main/resources/org/alphacode/pacer/arquivos/ManualProfessor.pdf");
            if (pdf.exists()) {
                Desktop.getDesktop().open(pdf);
            } else {
                System.out.println("Arquivo não encontrado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    public void showOk(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
