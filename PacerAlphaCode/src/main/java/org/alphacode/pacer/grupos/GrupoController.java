package org.alphacode.pacer.grupos;

import conexao.OperacoesSQL;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.alphacode.pacer.ExecuteApplication;
import org.alphacode.pacer.alunoacess.AlunosInterface;
import org.alphacode.pacer.alunos.Alunos;


import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class GrupoController {

    Statement stm = OperacoesSQL.conectarBanco();

    private String grupoSelecao;

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    private Integer idGrupo;

    @FXML
    public Button buttonEditGroup;

    @FXML
    private Button buttonImportGroup;

    @FXML
    public Button buttonRemoveGroup;

    @FXML
    private ListView<Grupo> telagrupos;

    @FXML
    public Button botaoaddgrupo;

    @FXML
    private AnchorPane gGroups;

    private ObservableList<Grupo> grupos; // Declare a ObservableList

    @FXML
    private Label nameSelectedGroup;

    @FXML
    private TableView<Alunos> tableGrupoSelecionado;

    @FXML
    private TableColumn<Alunos, String> viewEmail;

    @FXML
    private TableColumn<Alunos, String> viewName;

    private ObservableList<Alunos> listaDados;
    private ObservableList<AlunosInterface> listaDados1;

    @FXML
    private ChoiceBox<String> SprintChoice;
    ArrayList<String> sprints = new ArrayList<String>();

    @FXML
    private TextField pontosGrupo;

    @FXML
    private Label nameSelectedGroup1;

    @FXML
    private TableView<AlunosInterface> tableResults;

    private ObservableList<AlunosInterface> resultados;

    @FXML
    private TableColumn<AlunosInterface, String> nomeAluno;

    @FXML
    private TableColumn<AlunosInterface, Float> viewMedia;

    public GrupoController() throws SQLException {
    }

    @FXML
    private TableView<Sprint> tableSprints;

    @FXML
    private TableColumn<Sprint, Double> viewPontos;

    @FXML
    private TableColumn<Sprint, Integer> viewSprint;

    ObservableList<Sprint> dataSprint;

    @FXML
    private Button buttonAddPoints;

    @FXML
    void initialize() {
        getSprint();
        resultados = FXCollections.observableArrayList();
        listaDados1 = FXCollections.observableArrayList();
        grupos = FXCollections.observableArrayList(); // Inicializa a ObservableList
        telagrupos.setItems(grupos); // Associa a ObservableList à ListView
        carregarDados();

        dataSprint = FXCollections.observableArrayList();
        tableSprints.setItems(dataSprint);

        telagrupos.setCellFactory(lv -> new ListCell<Grupo>() {
            @Override
            protected void updateItem(Grupo grupo, boolean empty) {
                super.updateItem(grupo, empty);
                if (empty || grupo == null) {
                    setText(null);
                } else {
                    setText(grupo.getNomeGrupo()); // Exibe o nome do grupo
                }
            }
        });

        listaDados = FXCollections.observableArrayList();

        viewName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        viewEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        viewSprint.setCellValueFactory(new PropertyValueFactory<>("sprint"));
        viewPontos.setCellValueFactory(new PropertyValueFactory<>("pontos"));

        SprintChoice.getItems().addAll(sprints);
        SprintChoice.setOnAction(this::getSprintChoice);
    }

    @FXML
    public void initializeTable(List<String> column) {
        tableResults.getColumns().clear();
        nomeAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeAluno.setVisible(true);
        nomeAluno.setResizable(true);
        tableResults.getColumns().add(nomeAluno);


        for (String col : column) {
            TableColumn<AlunosInterface, String> coluna = new TableColumn<>(col);

            coluna.setCellValueFactory(cellData -> {
                AlunosInterface criterio = cellData.getValue();
                Object value = criterio.getParametro(col);
                return new SimpleStringProperty(value != null ? value.toString() : "");
            });

            tableResults.getColumns().add(coluna);
        }
        tableResults.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }


    @FXML
    void adicionargrupo(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/grupos/CadastroGrupo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        TelaCadastroGrupoController controller = fxmlLoader.getController();
        controller.setDialogStage(dialog, ""); // Passa a ListView para o controller

        dialog.setTitle("Defina o nome do Grupo");
        dialog.setScene(scene);
        dialog.show(); // Exibe a nova janela
    }

    @FXML
    void removeSelectedGroup(ActionEvent event) {
        Grupo selectedGroup = telagrupos.getSelectionModel().getSelectedItem();
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
                Boolean prova = OperacoesSQL.lookGroup(stm, selectedGroup.nomeGrupo);
                if (prova == false) {
                    // Se o usuário confirmar, remove o grupo
                    grupos.remove(selectedGroup); // Remove da ObservableList
                    OperacoesSQL.excluirGrupo(stm, selectedGroup.nomeGrupo);
                } else {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Restrição: Grupo em atividade");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Não é possível excluir esse grupo. Remova todos os membros e tente novamente.");
                    alerta.showAndWait();
                }
            }
        } else {
            // Alerta se nenhum grupo estiver selecionado
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nenhum grupo selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um grupo para remover.");
            alert.showAndWait();
        }
    }

    @FXML
    void ImportSelectedGroup(ActionEvent event) {
        // Implementação do método de importação
    }

    public void EditedSelectedGroup(ActionEvent actionEvent) throws IOException {
        Grupo grupoSelecionado = telagrupos.getSelectionModel().getSelectedItem();
        if (grupoSelecionado != null) {
            nameSelectedGroup.setText("Grupo selecionado: " + grupoSelecionado.nomeGrupo);
            tableGrupoSelecionado.setItems(listaDados);
            carregarAlunos(grupoSelecionado.nomeGrupo);
            setGrupoSelecionado(grupoSelecionado.nomeGrupo);
            System.out.println(getGrupoSelecionado());
            setIdGrupo(OperacoesSQL.SelectIDGrupo(stm, getGrupoSelecionado()));
            carregarSprints(idGrupo);
            List<String> colunas = OperacoesSQL.carregarColunas(stm);
            initializeTable(colunas);
            carregarResultados(idGrupo);
        }
    }

    public void buttonEditGroup(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void carregarDados() {
        List<Grupo> gruposList = OperacoesSQL.consultarDadosGrupos(stm);
        grupos.clear(); // Limpa a lista atual antes de carregar novos dados
        grupos.addAll(gruposList); // Adiciona os dados retornados à lista
        telagrupos.setItems(grupos); // Define os itens da TableView
    }

    public void carregarAlunos(String grupo) {
        List<Alunos> alunosList = OperacoesSQL.consultarDadosAlunos(stm, grupo);
        List<AlunosInterface> alunosInterfaceList = OperacoesSQL.consultarDadosAlunos1(stm, grupo);

        listaDados.clear(); // Limpa a lista atual antes de carregar novos dados
        listaDados1.clear();
        listaDados.addAll(alunosList); // Adiciona os dados retornados à lista
        listaDados1.addAll(alunosInterfaceList);
        tableGrupoSelecionado.setItems(listaDados); // Define os itens da TableView
        tableResults.setItems(listaDados1);
    }

    public void getSprint() {
        ArrayList<String> sprints = OperacoesSQL.consultarSprints(stm);
        this.sprints = sprints;
    }

    public Integer getSprintChoice(ActionEvent event) {
        Integer sprint = Integer.parseInt(SprintChoice.getValue());
        return (sprint);
    }

    public void setGrupoSelecionado(String grupo) {
        this.grupoSelecao = grupo;
    }

    public String getGrupoSelecionado() {
        return (grupoSelecao);
    }


    @FXML
    void addPointsGroup(ActionEvent event) {
        OperacoesSQL.insertPontosGrupos(stm, getSprintChoice(event), getGrupoSelecionado(), Double.parseDouble(pontosGrupo.getText()));
        carregarSprints(getIdGrupo());
    }

    void carregarSprints(Integer id) {
        List<Sprint> sprint = OperacoesSQL.carregarSprints(stm, id);
        dataSprint.clear();
        dataSprint.addAll(sprint);
        tableSprints.setItems(dataSprint);
    }

    void carregarResultados(Integer id) {
        List<AlunosInterface> lista = OperacoesSQL.getRAvaliacao(stm, id);

        int nAlunos = lista.size();

        for (AlunosInterface aluno : lista) {
            aluno.carregarNotas(nAlunos);
        }
        resultados.clear();
        resultados.addAll(lista);
        tableResults.setItems(resultados);

    }
}