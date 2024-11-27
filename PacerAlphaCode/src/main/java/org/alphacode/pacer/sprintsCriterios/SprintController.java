package org.alphacode.pacer.sprintsCriterios;

import conexao.OperacoesSQL;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class SprintController {

    Statement stm = OperacoesSQL.conectarBanco();

    @FXML
    private AnchorPane pageSprintCriterios;

    @FXML
    private DatePicker addDataF;

    @FXML
    public Text textCriterios;

    @FXML
    public Text textSprints;

    @FXML
    private DatePicker addDataI;

    @FXML
    private Button btnAddC;

    @FXML
    private Button btnAdicionarS;

    @FXML
    private Button btnDeleteC;

    @FXML
    private Button btnRemoverS;

    @FXML
    private Button btnStartSprint;

    @FXML
    private Button btnEncerrarSprint;

    @FXML
    private TableColumn<Datas, Integer> nSprint;  // Alterado para Integer

    @FXML
    private TableColumn<Datas, String> inicioSprint;  // Alterado para String

    @FXML
    private TableColumn<Datas, String> fimSprint;  // Alterado para String

    @FXML
    private TableColumn<Datas, String> fimAvaliacao;  // Para mostrar o status da sprint (se necessário)

    @FXML
    private ListView<Criterios> criterios;

    @FXML
    private TextField nomeCriterio;

    @FXML
    private TableView<Datas> tableSprint;

    private ObservableList<String> column = FXCollections.observableArrayList();
    private ObservableList<Criterios> lista = FXCollections.observableArrayList();
    private ObservableList<Datas> dataSprint = FXCollections.observableArrayList();

    public SprintController() throws SQLException {
    }

    public void initialize() {
        // Configuração das colunas da TableView
        nSprint.setCellValueFactory(new PropertyValueFactory<>("idSprint"));
        inicioSprint.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataInicial())));
        fimSprint.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataFinal())));
        fimAvaliacao.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataFinalAv())));

        tableSprint.setItems(dataSprint);
        criterios.setItems(lista);

        style();
        criterios();
        carregarDatas();
        exibirInstrucoes();
    }

    private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data != null ? data.format(formatter) : "";  // Retorna uma string vazia caso o valor seja null
    }

    @FXML
    void addData(ActionEvent event) {
        LocalDate dataInicial = addDataI.getValue();
        LocalDate dataFinal = addDataF.getValue();
        LocalDate dataFinalAv = addDataF.getValue().plusDays(7);

        if (dataInicial != null && dataFinal != null) {
            if(OperacoesSQL.testDateSprint(stm, dataInicial, dataFinal) == true){
                showWarning("Erro!", "Datas escolhidas conflitantes", "Verifique as datas selecionadas");
            }else{
                if (dataFinal.isAfter(dataInicial)) {
                    int idSprint = dataSprint.size() + 1;

                    Datas novaData = new Datas(idSprint, dataInicial, dataFinal, dataFinalAv);
                    dataSprint.add(novaData);
                    OperacoesSQL.addSprint(stm, idSprint, dataInicial, dataFinal);
                    tableSprint.refresh();
                } else {
                    showWarning("Erro!", "Datas escolhidas conflitantes", "Verifique as datas selecionadas");
                }
            }
        } else {
            showWarning("Erro!", "Campo de data vazio.","Informe a data inicial e data final." );
        }

        addDataI.setValue(null);
        addDataF.setValue(null);
    }

    @FXML
    void deleteSprint(ActionEvent event) {
        Datas dataSelect = tableSprint.getSelectionModel().getSelectedItem();
        int idData = tableSprint.getSelectionModel().getSelectedItem().getIdSprint();

        if (dataSelect == null) {
            showWarning("Erro!","Nenhum período selecionado.",  "Selecione um período para remover.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar Remoção");
        confirm.setHeaderText("Você tem certeza que deseja remover esta data?");
        confirm.setContentText("Data inicial: " + dataSelect.getDataInicial() + " Data Final: " + dataSelect.getDataFinal());

        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        confirm.getButtonTypes().setAll(btnSim, btnNao);

        confirm.showAndWait().ifPresent(response -> {
            if (response == btnSim) {
                dataSprint.remove(dataSelect);
                contSprint();
                tableSprint.refresh();
                OperacoesSQL.deleteSprint(stm, idData);
            }
        });
    }

    @FXML
    void criterios() {
        List<Criterios> criteriosC = OperacoesSQL.carregarCriterios(stm);
        lista.addAll(criteriosC);
        criterios.setItems(lista);
    }

    @FXML
    void carregarDatas() {
        List<Datas> datas = OperacoesSQL.carregarDatas(stm);
        dataSprint.addAll(datas);
        tableSprint.setItems(dataSprint);
    }

    @FXML
    void addC(ActionEvent event) {
        if(OperacoesSQL.getStatus(stm) == false){
            if (nomeCriterio.getText().isEmpty()) {
                showWarning("Erro!", "Campo vazio.", "Informe um critério.");
            } else {
                String coluna = nomeCriterio.getText().trim();
                Criterios novoCriterio = new Criterios(coluna);
                column.add(coluna);
                lista.add(novoCriterio);
                criterios.setItems(lista);
                nomeCriterio.clear();
                OperacoesSQL.inserirCriterio(stm, coluna);
            }
        }else{
            showWarning("Erro!", "Existem sprints ativas.", "Não é possível manipular criterios, quando existem sprints ativas.");
        }
    }

    @FXML
    void deleteC(ActionEvent event) {
        Criterios select = criterios.getSelectionModel().getSelectedItem();

        if (select == null) {
            showWarning("Erro!", "Nenhum Critério Selecionado.","Selecione um critério para remover.");
            return;
        }


        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar Remoção");
        confirm.setHeaderText("Você tem certeza que deseja remover este critério?");
        confirm.setContentText("Critério: " + select.getNomeCriterio());

        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        confirm.getButtonTypes().setAll(btnSim, btnNao);

        confirm.showAndWait().ifPresent(response -> {
            if (response == btnSim) {
                lista.remove(select);
                column.remove(select.getNomeCriterio());
                criterios.getSelectionModel().clearSelection();
                OperacoesSQL.deleteCriterio(stm, select.getNomeCriterio());
            }
        });
    }

    @FXML
    public void style() {
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toExternalForm();
        criterios.getStylesheets().add(css);
    }

    private void exibirInstrucoes() {
        String instrucoesC =
                """
                        Instruções para Uso de Critérios:
                        
                        1. Defina os Critérios:
                           - Antes de iniciar uma Sprint, é fundamental estabelecer os critérios de avaliação.
                        
                        2. Adicionar um Critério:
                           - Informe o critério desejado no campo apropriado e clique em **Adicionar**.
                        
                        3. Remover um Critério:
                           - Para remover um critério, selecione o item desejado na lista e clique em **Remover**.
                        
                        * ATENÇÃO!
                        Uma vez que o período de avaliação comece, não será possível remover critérios.
                        Certifique-se de que todos os critérios estejam corretos antes de iniciar a avaliação.""";

        String instrucoesS =
                """
                            Instruções para Uso de Sprint:
                        
                        1. Preenchimento das Datas:
                           - Selecione a **Data de Início** e a **Data Final** da Sprint.
                           - Se necessário, é possível remover as datas já escolhidas.
                        
                        2. Iniciar o Período de Avaliação:
                           - Para iniciar os períodos de avaliação, clique em **Iniciar Sprint**.
                           - Uma vez iniciado, o período de avaliação não pode ser alterado.
                        
                        3. Encerrar a Sprint:
                           - O botão **Encerrar Sprint** pode ser utilizado caso deseje interromper o período de avaliação.
                           - Lembre-se: após iniciar, não será possível adicionar, remover as datas.
                        
                        * ATENÇÃO!
                        Garanta que todas as informações estejam corretas antes de iniciar a avaliação, pois as mudanças não serão permitidas após o início.""";

        textCriterios.setText(instrucoesC); // Define o texto das instruções
        textSprints.setText(instrucoesS);
    }

    public void contSprint() {
        for (int i = 0; i < dataSprint.size(); i++) {
            dataSprint.get(i).setIdSprint(i + 1); // Recalcula o ID de cada sprint
        }
    }

    public void acaodataI(ActionEvent actionEvent) {
    }

    public void acaodataF(ActionEvent actionEvent) {
    }

    public void startSprint(ActionEvent actionEvent) {
        Datas selectedSprint = tableSprint.getSelectionModel().getSelectedItem();
        OperacoesSQL.updateStatus(stm, selectedSprint.getIdSprint());
    }

    public void encerrarSprint(ActionEvent actionEvent) {
    }

    @FXML
    public void showWarning(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}