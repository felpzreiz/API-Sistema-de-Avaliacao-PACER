package org.alphacode.pacer.alunoacess;

import conexao.OperacoesSQL;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import org.alphacode.pacer.grupos.Sprint;
import org.alphacode.pacer.password.RedefinirSenha;
import org.alphacode.pacer.sprintsCriterios.Datas;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class TelaAlunoController {


    Statement stm = OperacoesSQL.conectarBanco();

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }


    public String getEmail() {
        return this.email;
    }

    private String grupo;

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGrupo(String grupo) {
        return grupo;
    }

    @FXML
    private Label idAluno;

    @FXML
    public Label labelNome;

    @FXML
    public Label idEmail;

    @FXML
    public Label infoSprint;

    @FXML
    public Label infoPontos;

    @FXML
    public Label infoAvaliacao;

    @FXML
    private Button btnconfig;

    @FXML
    private Button btnsair;

    @FXML
    private Button btsalvar;

    @FXML
    private TableView<Datas> tableSprint;

    @FXML
    private TableColumn<Datas, Integer> nSprint;

    @FXML
    private TableColumn<Datas, String> inicioSprint;

    @FXML
    private TableColumn<Datas, String> fimSprint;

    @FXML
    private TableColumn<Datas, String> fimAvaliacao;

    @FXML
    private TableView<Sprint> tableSprints;

    @FXML
    private TableColumn<Sprint, Double> viewPontos;

    @FXML
    private TableColumn<Sprint, Integer> viewSprint;

    @FXML
    private TableColumn<AlunosInterface, String> columnStudent;

    @FXML
    private AnchorPane dadosAluno;

    @FXML
    private GridPane gridavaliacao;

    @FXML
    private SplitPane homeAlunos;

    @FXML
    private ImageView logofatec;

    @FXML
    public Label nomeAluno;

    @FXML
    private Label nomegrupo;

    @FXML
    private Label qtPontos;


    @FXML
    private Label pontosSprint;

    @FXML
    private Label qtdpontosDisp;

    @FXML
    private AnchorPane tabelaAlunos;

    @FXML
    private TableView<AlunosInterface> tableStudents;

    @FXML
    private Label textSprint;

    @FXML
    private Label textpacer;

    @FXML
    private Button instruction;

    private ObservableList<AlunosInterface> listaAlunos;
    private List<AlunosInterface> alunos;              // Declaro a lista de alunos diretamente com a query
    private Map<String, Integer> notaColunas;
    private ObservableList<Datas> dataSprint = FXCollections.observableArrayList();
    private ObservableList<Sprint> sprintPoint;

    @FXML
    public void carregarAlunos(String email) throws SQLException {       // Carrego o nome dos alunos com base nos Objetos AlunosInterface e Query
        alunos = OperacoesSQL.alunosGrupo(stm, email);
        listaAlunos.addAll(alunos);
    }

    @FXML
    public void initialize() {
        idEmail.setText("");
        listaAlunos = FXCollections.observableArrayList();
        sprintPoint = FXCollections.observableArrayList();           // Inicio primeiro os alunos na coluna de estudantes
        List<String> colunas = OperacoesSQL.carregarColunas(stm);         //  Carrego as colunas para iniciar em seguida a tabela         // Trago o metodo de alunos
        notaColunas = new HashMap<>();
        initializeTable(colunas);                                     // Inicio as colunas dinamicas
        nSprint.setCellValueFactory(new PropertyValueFactory<>("idSprint"));
        inicioSprint.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataInicial())));
        fimSprint.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataFinal())));
        fimAvaliacao.setCellValueFactory(cellData -> new SimpleStringProperty(formatarData(cellData.getValue().getDataFinalAv())));
        viewSprint.setCellValueFactory(new PropertyValueFactory<>("sprint"));
        viewPontos.setCellValueFactory(new PropertyValueFactory<>("pontos"));
        tableSprints.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        tableSprint.setItems(dataSprint);
        carregarDatas();
        btnconfig.setTooltip(new Tooltip("Alterar senha de Usuário"));
        btnsair.setTooltip(new Tooltip("Sair"));
        instruction.setTooltip(new Tooltip("Manual do Usuário"));
        style();
    }

    private String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data != null ? data.format(formatter) : "";  // Retorna uma string vazia caso o valor seja null
    }

    public TelaAlunoController() throws SQLException {
    }

    public void initializeTable(List<String> column) {                // O nome das colunas é considerada uma String
        tableStudents.setEditable(true);                                 // Tabela Editavél
        tableStudents.getColumns().clear();                               // Limpa os antigos registros de colunas
        columnStudent.setCellValueFactory(new PropertyValueFactory<>("nome"));      // Considera que a coluna de Alunos vai receber o getNome da classe AlunosInterface
        tableStudents.getColumns().add(columnStudent);            // Insere a coluna Alunos na Tabela
        columnStudent.setEditable(false);                           // Bloqueia a coluna de alunos na tabela

        notaColunas = new HashMap<>();
        int colunaX = 1;
        for (String col : column) {

            notaColunas.put(col, colunaX);
            // Mapeia o nome da coluna ao índice da nota

            TableColumn<AlunosInterface, Float> tableColumn = new TableColumn<>(col);

            tableColumn.setCellValueFactory(cellData -> {
                int notaIndex = notaColunas.get(col);  // Obtém o índice da nota correspondente
                AlunosInterface aluno = cellData.getValue();

                if (notaIndex < aluno.getNotas().size()) {
                    return new SimpleFloatProperty(aluno.getNotas().get(notaIndex).getNota()).asObject();
                } else {
                    aluno.addNotas(new Notas(0.0f));  // Criação da nota com valor inicial 0
                    return new SimpleFloatProperty(0.0f).asObject();  // Retorna o valor padrão 0
                }
            });

            tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            tableColumn.setOnEditCommit(event -> {
                AlunosInterface aluno = event.getRowValue();
                String colunaNome = event.getTableColumn().getText();
                int indiceNota = notaColunas.get(colunaNome);

                float notaDigitada = event.getNewValue();

                try {
                    try {
                        if (notaDigitada >= 0 && notaDigitada <= 3) {
                            if (indiceNota < aluno.getNotas().size()) {
                                float notaAnterior = aluno.getNotas().get(indiceNota).getNota();
                                if (pontosSprint() >= somaNotas() - notaAnterior + notaDigitada) {
                                    aluno.getNotas().get(indiceNota).setNota(notaDigitada);
                                } else {
                                    showAlert("Erro!", "ATENÇÃO", "Quantidade de pontos excedida.");
                                }

                            } else {
                                if (pontosSprint() >= somaNotas() + notaDigitada) {
                                    if (indiceNota >= aluno.getNotas().size()) {
                                        aluno.addNotas(new Notas(notaDigitada));
                                    } else {
                                        aluno.getNotas().get(indiceNota).setNota(notaDigitada);
                                    }
                                }
                            }
                        } else {
                            showAlert("Erro!", "Nota inválida!", "A nota deve estar entre 0 e 3.");
                        }
                        somaNotas();
                        tableStudents.refresh();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            tableStudents.getColumns().add(tableColumn);
            colunaX++;

        }
        tableStudents.setItems(listaAlunos);
        tableStudents.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    @FXML
    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) homeAlunos.getScene().getWindow();
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
    public void acessConfig(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/alphacode/pacer/password/RedefinirSenha.fxml"));

            Parent root = loader.load();
            RedefinirSenha controller = loader.getController();
            controller.setEmail(idEmail.getText());

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
    public void style() {
        String css = Objects.requireNonNull(getClass().getResource("/org/alphacode/pacer/styles.css")).toString();
        tableStudents.getStylesheets().add(css);
    }

    public float somaNotas() {
        qtdpontosDisp.setVisible(true);
        float somaNotas = 0;
        for (AlunosInterface aluno : listaAlunos) {
            for (Notas nota : aluno.getNotas()) {
                somaNotas += nota.getNota();
            }
        }
        qtdpontosDisp.setText(String.valueOf(somaNotas));

        return somaNotas;
    }

    public float pontosSprint() {
        int idSprint = OperacoesSQL.getIdSprint(stm);
        int idGrupo = OperacoesSQL.getIdGrupoEmail(stm, getEmail());
        float pontos = OperacoesSQL.getPontosSprint(stm, idGrupo, idSprint);
        infoPontos.setVisible(true);
        infoPontos.setText(String.valueOf(pontos));
        return pontos;
    }

    @FXML
    public void carregarDados(String email) {
        setEmail(email);
        idEmail.setText(this.email);
        labelNome.setText(OperacoesSQL.nomeAluno(stm, email));
        nomegrupo.setText(OperacoesSQL.carregarInfo(stm, email));
        int idSprint = OperacoesSQL.getNSprint(stm);
        infoSprint.setText(String.valueOf(idSprint));
        int idGrupo = OperacoesSQL.getIdGrupoEmail(stm, email);
        carregarSprints(idGrupo);
        pontosSprint();
    }

    @FXML
    void carregarDatas() {
        tableSprint.getItems().clear();
        List<Datas> datas = OperacoesSQL.carregarDatas(stm);
        dataSprint.addAll(datas);
        tableSprint.setItems(dataSprint);
    }

    @FXML
    void carregarSprints(Integer id) {
        tableSprints.getItems().clear();
        List<Sprint> sprint = OperacoesSQL.carregarSprints(stm, id);
        sprintPoint.clear();
        sprintPoint.addAll(sprint);
        tableSprints.setItems(sprintPoint);
    }

    @FXML
    public void iniciarAv(ActionEvent actionEvent) throws SQLException {
        try {
            LocalDate now = LocalDate.now();

            int idSprint = OperacoesSQL.getNSprint(stm);
            System.out.println(idSprint);
            Sprint sprint = OperacoesSQL.getSprintID(stm, idSprint);

            if (sprint == null) {
                showAlert("Erro", "Sprint não encontrada", "Não foi possível encontrar uma sprint com o ID: " + idSprint);
                return;
            }
            System.out.println(fimSprint);
            System.out.println(fimAvaliacao);
            LocalDate fimSprint = sprint.getData_fim();
            LocalDate fimAvaliacao = sprint.getFim_avaliacao();

            if (now.isAfter(fimSprint) && now.isBefore(fimAvaliacao)) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Iniciar Avaliação");
                dialog.setHeaderText("Inicie a avaliação da sprint");
                dialog.setContentText("Digite o número da sprint:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(sprintNum -> {
                    try {
                        int numeroSprint = Integer.parseInt(sprintNum);
                        System.out.println("Avaliação da Sprint " + numeroSprint + " iniciada!");
                        iniciarAvaliacaoNoBanco(numeroSprint);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                showAlert("Erro", "Período de Avaliação Inválido", "O período de avaliação ainda não começou ou já terminou.");
            }
        } catch (NullPointerException e) {
            System.err.println();
            e.printStackTrace();
        }
    }


    @FXML
    private void iniciarAvaliacaoNoBanco(int numeroSprint) {


    }

    @FXML
    public void salvarNotas(ActionEvent actionEvent) throws SQLException {

        int idAvaliador = OperacoesSQL.SelectIDEdit(stm, this.email);
        int idSprint = OperacoesSQL.getIdSprint(stm);
        boolean check = OperacoesSQL.checkAvaliacao(stm, idAvaliador, idSprint);
        if (check) {
            showAlert("Atenção!", "Erro!", "A avaliação para a Sprint atual já foi realizada.");
        } else {

            boolean isConfirmed = confirm("Avaliação", "Salvar Avaliação.",
                    "Ao confirmar, as notas serão enviadas e não poderão ser alteradas após o envio.");

            if (isConfirmed) {
                Connection con = null;
                PreparedStatement pstmt = null;
                try {
                    con = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/pacer", "adminpacer", "AdminPacer1234"
                    );
                    String query = "Insert into avaliacao (id_aluno_avaliador, id_aluno_avaliado, id_grupo, id_sprint, id_criterio, nota) VALUES (?,?,?,?,?,?)";
                    pstmt = con.prepareStatement(query);

                    for (AlunosInterface aluno : listaAlunos) {
                        String alunoAvaliado = aluno.getNome();

                        for (Map.Entry<String, Integer> nota : notaColunas.entrySet()) {
                            String colunaNome = nota.getKey();
                            int notaIndex = nota.getValue();

                            float notaId;
                            if (notaIndex < aluno.getNotas().size()) {
                                notaId = aluno.getNotas().get(notaIndex).getNota();
                            } else {
                                notaId = 0.0f;
                            }

                            LocalDate LocalDate = java.time.LocalDate.now();
                            idAvaliador = OperacoesSQL.SelectIDEdit(stm, this.email);
                            int idAvaliado = OperacoesSQL.getIdAlunoAvaliado(stm, alunoAvaliado);
                            int idGrupo = OperacoesSQL.getIdGrupo(stm, alunoAvaliado);
                            idSprint = OperacoesSQL.getIdSprint(stm);
                            int idCriterio = OperacoesSQL.getIdCriterio(stm, colunaNome);

                            pstmt.setInt(1, idAvaliador);
                            pstmt.setInt(2, idAvaliado);
                            pstmt.setInt(3, idGrupo);
                            pstmt.setInt(4, idSprint);
                            pstmt.setInt(5, idCriterio);
                            pstmt.setFloat(6, notaId);
                            pstmt.executeUpdate();

                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (pstmt != null) pstmt.close();
                    if (con != null) con.close();

                    showAlert("Avaliação", "Concluída com Sucesso", "A avaliação foi concluída com sucesso. Não há mais ações pendentes.");
                }
            }
        }
    }

    public void openIntruction(ActionEvent actionEvent) {
        try {
            File pdf = new File("PacerAlphaCode/src/main/resources/org/alphacode/pacer/arquivos/ManualAluno.pdf");
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
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(title);
        info.setHeaderText(header);
        info.setContentText(content);
        info.show();
    }

    @FXML
    public boolean confirm(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

}