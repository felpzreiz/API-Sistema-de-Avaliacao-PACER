package org.alphacode.pacer.grupos;

import conexao.OperacoesSQL;
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
import org.alphacode.pacer.alunos.Alunos;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrupoController {
    Statement stm = OperacoesSQL.conectarBanco();

    private String grupoSelecao;

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

    @FXML
    private ChoiceBox<String> SprintChoice;
    ArrayList<String> sprints = new ArrayList<String>();

    @FXML
    private TextField pontosGrupo;

    public GrupoController() throws SQLException {
    }

    @FXML
    private TableView<String> tableSprints;

    @FXML
    private Button buttonAddPoints;

    @FXML
    void initialize() {
        getSprint();

        grupos = FXCollections.observableArrayList(); // Inicializa a ObservableList
        telagrupos.setItems(grupos); // Associa a ObservableList à ListView
        carregarDados();

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

        SprintChoice.getItems().addAll(sprints);
        SprintChoice.setOnAction(this::getSprintChoice);
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
                }else{
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

    public void buttonEditGroup(MouseEvent mouseEvent) {
        // Implementação para editar grupo
    }

    public void EditedSelectedGroup(ActionEvent actionEvent) throws IOException {
        Grupo grupoSelecionado = telagrupos.getSelectionModel().getSelectedItem();
        if (grupoSelecionado != null) {
            nameSelectedGroup.setText("Grupo selecionado: " + grupoSelecionado.nomeGrupo);
            tableGrupoSelecionado.setItems(listaDados);
            carregarAlunos(grupoSelecionado.nomeGrupo);
            setGrupoSelecionado(grupoSelecionado.nomeGrupo);
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
        listaDados.clear(); // Limpa a lista atual antes de carregar novos dados
        listaDados.addAll(alunosList); // Adiciona os dados retornados à lista
        tableGrupoSelecionado.setItems(listaDados); // Define os itens da TableView
    }

    public void getSprint(){
        ArrayList<String> sprints = OperacoesSQL.consultarSprints(stm);
        this.sprints = sprints;
    }

    public Integer getSprintChoice(ActionEvent event) {
        Integer sprint = Integer.parseInt(SprintChoice.getValue());
        return(sprint);
    }

    public void setGrupoSelecionado(String grupo) {
        this.grupoSelecao = grupo;
    }

    public String getGrupoSelecionado(){
        return(grupoSelecao);
    };

    @FXML
    void addPointsGroup(ActionEvent event) {
        OperacoesSQL.insertPontosGrupos(stm, getSprintChoice(event), getGrupoSelecionado(), Double.parseDouble(pontosGrupo.getText()));
    }
}
