package conexao;

import org.alphacode.pacer.alunoacess.AlunosInterface;
import org.alphacode.pacer.alunoacess.Notas;
import org.alphacode.pacer.alunos.Alunos;
import org.alphacode.pacer.grupos.Grupo;
import org.alphacode.pacer.grupos.Sprint;
import org.alphacode.pacer.sprintsCriterios.Criterios;
import org.alphacode.pacer.sprintsCriterios.Datas;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacoesSQL {

    //MÉTODOS PARA A TELA ALUNOCONTROLLER
    public static Statement conectarBanco() throws SQLException {
        Conexao conexao = new Conexao();
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pacer",
                "adminpacer", "AdminPacer1234");
        ;
        conexao.conexaoBD();
        Statement stm = con.createStatement();
        return (stm);
    }

    public static List<Alunos> consultarDados(Statement stm) { // METHOD PARA FAZER UM SELECT.
        List<Alunos> listaAlunos = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT senha,email,grupo,* FROM aluno ORDER BY id ASC");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                String nome = result.getString("nome");
                String email = result.getString("email");
                String grupo = result.getString("grupo");

                // Cria um novo objeto Alunos e adiciona à lista1
                Alunos aluno = new Alunos(nome, email, grupo);
                listaAlunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlunos; // Retorna a lista de alunos
    }

    public static void inserirAluno(Statement stm, String email, String git, String grupo, String nome) { // METHOD PARA FAZER UM INSERT.
        String insereAluno = "INSERT INTO aluno(email,git,grupo,nome) VALUES ('" + email + "','" + git + "','" + grupo + "','" + nome + "')";

        try {
            stm.executeUpdate(insereAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAluno(Statement stm, String email, String git, String grupo, String nome, Integer id) {
        String updateAluno = "UPDATE aluno SET email = '" + email
                + "', git = '" + git
                + "', grupo = '" + grupo
                + "', nome = '" + nome
                + "' WHERE id = '" + id
                + "'";
        try {
            stm.executeUpdate(updateAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void excluir(Statement stm, String email) {
        String excluiAluno =
                "DELETE FROM aluno WHERE email = '" + email + "';" +
                        "DELETE FROM usuario WHERE email = '" + email + "'";
        try {
            stm.executeUpdate(excluiAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer SelectIDEdit(Statement stm, String email) {
        Integer idAluno = 0;
        try {
            ResultSet result = stm.executeQuery("SELECT id FROM aluno WHERE email = '" + email + "'");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                Integer Id = Integer.parseInt(result.getString("id"));

                idAluno = Id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAluno; //
    }

    public static void inserirUsuario(Statement stm, String email, String senha) { // METHOD PARA FAZER UM INSERT.
        String insereUsuario = "INSERT INTO usuario(email,senha) VALUES ('" + email + "','" + senha + "')";

        try {
            stm.executeUpdate(insereUsuario);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //MÉTODOS PARA A TELA GRUPOCONTROLLER
    public static List<Grupo> consultarDadosGrupos(Statement stm) { // METHOD PARA FAZER UM SELECT.
        List<Grupo> listaGrupos = new ArrayList<>();

        try {
            ResultSet result = stm.executeQuery("SELECT nome_grupo FROM grupo ORDER BY id ASC");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                String grupo = result.getString("nome_grupo");

                // Cria um novo objeto Alunos e adiciona à lista1
                Grupo novoGrupo = new Grupo(grupo);
                listaGrupos.add(novoGrupo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaGrupos; // Retorna a lista de alunos
    }

    //MÉTODOS PARA A TELA SRINTCONTROLLER
    public static List<Criterios> carregarCriterios(Statement stm) {
        List<Criterios> listaCriterios = new ArrayList<>();
        try {
            ResultSet rs = stm.executeQuery("SELECT * FROM criterios ORDER BY criterio ASC ");
            while (rs.next()) {
                String criterio = rs.getString("criterio");
                listaCriterios.add(new Criterios(criterio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCriterios;

    }

    //MÉTODOS PARA A TELA SRINTCONTROLLER
    public static void inserirCriterio(Statement stm, String query) {
        String novoCriterio = "INSERT INTO criterios (criterio) VALUES  ('" + query + "')";
        try {
            stm.execute(novoCriterio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //MÉTODOS PARA A TELA SRINTCONTROLLER
    public static void deleteCriterio(Statement stm, String query) {
        String deleteCriterio = "DELETE FROM criterios WHERE criterio = ('" + query + "')";
        try {
            stm.execute(deleteCriterio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //MÉTODOS PARA ATUALUZAR A TABELA DINAMICA EM TELAALUNO
    public static List<String> carregarColunas(Statement stm) {
        List<String> listaColunas = new ArrayList<>();
        try {
            ResultSet rs = stm.executeQuery("SELECT * FROM criterios ORDER BY criterio ASC");
            while (rs.next()) {
                String coluna = rs.getString("criterio");
                listaColunas.add(new String(coluna));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaColunas;

    }

    //MÉTODOS PARA ADICIONAR SPRINTS
    public static void addSprint(Statement stm, int idSprint, LocalDate dataInicial, LocalDate dataFinal) {
        String query = "INSERT INTO sprint (sprint, data_inicio, data_fim, fim_avaliacao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = stm.getConnection().prepareStatement(query)) {
            ps.setInt(1, idSprint);
            ps.setObject(2, dataInicial);
            ps.setObject(3, dataFinal);
            ps.setObject(4, dataFinal.plusDays(7));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //MÉTODOS PARA CARREGAR AS DATAS DAS SPRINTS ---------------------
    public static List<Datas> carregarDatas(Statement stm) {
        List<Datas> listaDatas = new ArrayList<>();
        try {
            ResultSet rs = stm.executeQuery("SELECT  sprint, data_inicio, data_fim, fim_avaliacao FROM  sprint ORDER BY(sprint)");
            while (rs.next()) {
                int idSprint = rs.getInt("sprint");
                LocalDate dataInicial = rs.getDate("data_inicio").toLocalDate();
                LocalDate dataFinal = rs.getDate("data_fim").toLocalDate();
                LocalDate dataFinalAv = rs.getDate("fim_avaliacao").toLocalDate();
                listaDatas.add(new Datas(idSprint, dataInicial, dataFinal, dataFinalAv));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDatas;


    }

    public static void deleteSprint(Statement stm, int idSprint) {
        String deleteDate = "DELETE FROM sprint WHERE sprint = ('" + idSprint + "')";
        try {
            stm.execute(deleteDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //FIM DOS MÉTODOS PARA CARREGAR AS DATAS DAS SPRINTS -------------

    //MÉTODOS PARA A TELA DE GRUPOS ----------------------------------

    public static Boolean testPointsSprint(Statement stm, Integer sprint, String grupo) {
        Boolean resultado = false;
        try {
            ResultSet result = stm.executeQuery("SELECT count(pontos_grupo.id) " +
                    "FROM pontos_grupo " +
                    "LEFT JOIN grupo ON pontos_grupo.id_grupo = grupo.id " +
                    "LEFT JOIN sprint ON pontos_grupo.id_sprint = sprint.id " +
                    "WHERE grupo.nome_grupo = '" + grupo + "' " +
                    "AND sprint.sprint = " + sprint );
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                Integer Contagem = Integer.parseInt(result.getString("count"));

                if (Contagem == 0) {
                    resultado = false;
                } else {
                    resultado = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado; //
    }

    public static Boolean lookGroup(Statement stm, String grupo) {
        Boolean resultado = false;
        try {
            ResultSet result = stm.executeQuery("SELECT count(id) FROM aluno WHERE grupo = '" + grupo + "'");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                Integer Contagem = Integer.parseInt(result.getString("count"));

                if (Contagem == 0) {
                    resultado = false;
                } else {
                    resultado = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado; //
    }

    public static void excluirGrupo(Statement stm, String grupo) {
        String excluiGrupo = "DELETE FROM grupo WHERE nome_grupo = '" + grupo + "'";
        try {
            stm.executeUpdate(excluiGrupo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserirGrupo(Statement stm, String grupo) { // METHOD PARA FAZER UM INSERT.
        String insereUsuario = "INSERT INTO grupo(nome_grupo)" +
                "SELECT '" + grupo + "'" +
                "WHERE NOT EXISTS (" +
                "    SELECT nome_grupo" +
                "    FROM grupo" +
                "    WHERE nome_grupo = '" + grupo + "'" +
                ");";

        try {
            stm.executeUpdate(insereUsuario);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Alunos> consultarDadosAlunos(Statement stm, String grupo) { // METHOD PARA FAZER UM SELECT.
        List<Alunos> listaAlunos = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT senha,email,grupo,* FROM aluno WHERE grupo='" + grupo + "'ORDER BY id ASC");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                String nome = result.getString("nome");
                String email = result.getString("email");

                // Cria um novo objeto Alunos e adiciona à lista1
                Alunos aluno = new Alunos(nome, email, grupo);
                listaAlunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlunos; // Retorna a lista de alunos
    }

    public static List<AlunosInterface> consultarDadosAlunos1(Statement stm, String grupo) { // METHOD PARA FAZER UM SELECT.
        List<AlunosInterface> listaAlunos = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT senha,email,grupo,* FROM aluno WHERE grupo='" + grupo + "'ORDER BY id ASC");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                String nome = result.getString("nome");
                String email = result.getString("email");

                // Cria um novo objeto Alunos e adiciona à lista1
                AlunosInterface aluno = new AlunosInterface(nome);
                listaAlunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlunos; // Retorna a lista de alunos
    }

    public static ArrayList<String> consultarSprints(Statement stm) {
        ArrayList<String> sprints = new ArrayList<String>();

        try {
            ResultSet result = stm.executeQuery("SELECT sprint FROM sprint ORDER BY sprint ASC");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                Integer sprint = result.getInt("sprint");
                sprints.add(sprint.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sprints;
    }

    public static void insertPontosGrupos(Statement stm, Integer sprint, String grupo, Double nota) { // METHOD PARA FAZER UM INSERT.
        String inserePontos = "INSERT INTO pontos_grupo(id_sprint, id_grupo, pontos) " +
                "VALUES (" +
                "(SELECT id FROM sprint WHERE sprint=" + sprint + ")," +
                "(SELECT id FROM grupo WHERE nome_grupo='" + grupo + "'), " + nota + ")\n";

        try {
            stm.executeUpdate(inserePontos);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Sprint> carregarSprints(Statement stm, Integer id) {
        List<Sprint> listaSprints = new ArrayList<>();
        try {
            ResultSet rs = stm.executeQuery("SELECT sprint.sprint, pontos_grupo.id_grupo, pontos_grupo.pontos " +
                    "FROM sprint " +
                    "INNER JOIN pontos_grupo " +
                    "ON pontos_grupo.id_sprint = sprint.id " +
                    "WHERE id_grupo = " + id + "");
            while (rs.next()) {
                int sprint = rs.getInt("sprint");
                Double pontos = (double) rs.getInt("pontos");

                listaSprints.add(new Sprint(sprint, pontos));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaSprints;


    }

    public static Integer SelectIDGrupo(Statement stm, String grupoteste) {
        Integer idGrupo = 0;
        try {
            String query = "SELECT id FROM grupo WHERE nome_grupo='" + grupoteste + "'";
            ResultSet result = stm.executeQuery(query);
            if (result.next()) { // Muda para if, pois esperamos apenas um valor
                idGrupo = Integer.parseInt(result.getString("id"));
            } else {
                System.out.println("Nenhum resultado encontrado para o grupo: " + grupoteste);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idGrupo;
    }
    //FIM DOS MÉTODOS PARA A TELA DE GRUPOS --------------------------

    //MÉTODOS PARA A TELA DE LOGIN -----------------------------------

    public static Boolean getUser(Statement stm, String email, String senha) {
        Boolean user = false;
        try {
            ResultSet result = stm.executeQuery("SELECT count(*) FROM usuario WHERE email = '" + email + "' AND senha = '" + senha + "'");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                if (result.getInt("count") == 1) {
                    user = true;
                } else {
                    user = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user; //
    }

    public static String getEmailUser(Statement stm, String email) {
        String email1 = "";
        try {
            ResultSet result = stm.executeQuery("SELECT email FROM usuario WHERE email = '" + email + "'");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                email1 = result.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email1; //
    }

    //FIM DOS MÉTODOS PARA A TELA DE LOGIN ---------------------------


    //MÉTODOS PARA A TELA DE AVAVALIAÇÃO ALUNO -----------------------------------

    public static String nomeAluno(Statement stm, String email) {
        String nAluno = "";
        String query = "select nome from aluno where email = '" + email + "'";
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                nAluno = result.getString("nome");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nAluno;

    }

    public static String carregarInfo(Statement stm, String idAluno) {
        String query = "select grupo from aluno where email =    '" + idAluno + "'";
        String grupo = null;
        try {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                grupo = result.getString("grupo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupo;
    }


    public static String idGrupo(Statement stm, String email) {
        String nomeGrupo = "";
        String idGrupo = "";
        String query = "select (grupo) from aluno where email = '" + email + "'";

        try {
            ResultSet result = stm.executeQuery(query);
            if (result.next()) {
                nomeGrupo = result.getString("grupo");
            }
            result.close();

            if (!nomeGrupo.isEmpty()) {
                String query1 = "select (id) from grupo where nome_grupo = '" + nomeGrupo + "'";
                ResultSet result1 = stm.executeQuery(query1);
                if (result1.next()) {
                    idGrupo = result1.getString("id");
                }
                result1.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGrupo;
    }

    //MÉTODOS PARA ATUALUZAR A TABELA DINAMICA EM TELAALUNO
    public static List<AlunosInterface> carregarNomes(Statement stm) {
        List<AlunosInterface> listaNomes = new ArrayList<>();
        try {
            ResultSet rs = stm.executeQuery("SELECT nome FROM aluno");
            while (rs.next()) {
                String aluno = rs.getString("nome");
                listaNomes.add(new AlunosInterface(aluno));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaNomes;
    }

    public static List<AlunosInterface> alunosGrupo(Statement stm, String email) throws SQLException {
        List<AlunosInterface> listaAlunos = new ArrayList<>();
        String grupo = "";
        String query = "select grupo from aluno where email = '" + email + "'";

        try {
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                grupo = rs.getString("grupo");

            }
            String query1 = "select nome from aluno where grupo = '" + grupo + "'";
            ResultSet rs1 = stm.executeQuery(query1);
            while (rs1.next()) {
                String nome = rs1.getString("nome");
                listaAlunos.add(new AlunosInterface(nome));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlunos;
    }

    public static int getIdAlunoAvaliado(Statement stm, String aluno) {
        int id = 0;
        String query = "select id from aluno where nome = '" + aluno + "'";

        try {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int getIdGrupo(Statement stm, String nome) {
        int id = 0;
        String grupo = "";
        String query = "select grupo from aluno where nome = '" + nome + "'";

        try {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                grupo = rs.getString("grupo");
            }
            String query1 = "select id from grupo where nome_grupo = '" + grupo + "'";
            ResultSet rs1 = stm.executeQuery(query1);
            while (rs1.next()) {
                id = rs1.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int getIdGrupoEmail(Statement stm, String email) {
        int id = 0;
        String grupo = "";
        String query = "select grupo from aluno where email = '" + email + "'";

        try {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                grupo = rs.getString("grupo");
            }
            String query1 = "select id from grupo where nome_grupo = '" + grupo + "'";
            ResultSet rs1 = stm.executeQuery(query1);
            while (rs1.next()) {
                id = rs1.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int getIdCriterio(Statement stm, String criterio) {
        int id = 0;
        String query = "select id from criterios where criterio = '" + criterio + "'";
        try {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static int getIdSprint(Statement stm) {
        int idSprint = 0;
        String query = "select id from sprint where current_date between data_fim + 1 and fim_avaliacao";
        try {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                idSprint = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idSprint;
    }

    public static float getPontosSprint(Statement stm, int idGrupo, int idSprint) {
        float pontosS = 0f;


        String query = "select pontos from pontos_grupo where id_grupo = ? and id_sprint = ?";

        try (PreparedStatement ps = stm.getConnection().prepareStatement(query)) {
            ps.setInt(1, idGrupo);
            ps.setInt(2, idSprint);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pontosS = rs.getFloat("pontos");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pontosS;
    }

    public static int getNSprint(Statement stm) {
        int sprint = 0;
        String query = "SELECT sprint " +
                "FROM sprint " +
                "WHERE CURRENT_DATE BETWEEN data_fim + 1 AND fim_avaliacao";
        try (PreparedStatement ps = stm.getConnection().prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sprint = rs.getInt("sprint");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sprint;
    }

    public static Sprint getSprintID(Statement stm, Integer idSprint) throws SQLException {
        Sprint sprint = null;
        String query = "select sprint, data_inicio, data_fim, fim_avaliacao from sprint where sprint = ?";
        try (PreparedStatement ps = stm.getConnection().prepareStatement(query)) {
            ps.setInt(1, idSprint);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sprint = new Sprint(
                        rs.getInt("sprint"), rs.getDate("data_inicio").toLocalDate(),
                        rs.getDate("data_fim").toLocalDate(), rs.getDate("fim_avaliacao").toLocalDate()
                );
            }
        }
        return sprint;
    }

    public static List<AlunosInterface> getRAvaliacao(Statement stm, Integer idGrupo) {
        List<AlunosInterface> listaAlunos = new ArrayList<>();

        String query = "select aluno.nome, coalesce(avg(avaliacao.nota), 0) as nota, aluno.grupo, criterios.criterio " +
                "from aluno " +
                "inner join avaliacao on avaliacao.id_aluno_avaliado = aluno.id " + "inner join criterios on avaliacao.id_criterio = criterios.id where avaliacao.id_grupo = " + idGrupo + " " +
                "group by aluno.id, aluno.nome, aluno.grupo, criterios.id " +
                "order by aluno.nome";
        try {
            ResultSet rs = stm.executeQuery(query);
            Map<String, AlunosInterface> alunosMap = new HashMap<>();
            while (rs.next()) {
                String nome = rs.getString("nome");
                Float nota = rs.getFloat("nota");
                String criterio = rs.getString("criterio");

                Notas notas = new Notas(criterio, nota);

                AlunosInterface aluno = alunosMap.get(nome);
                if (aluno == null) {
                    aluno = new AlunosInterface(nome);
                    alunosMap.put(nome, aluno);
                }
                aluno.addNotas(notas);
            }
            listaAlunos.addAll(alunosMap.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAlunos;

    }

    public static Boolean checkAvaliacao(Statement stm, Integer idAluno, Integer idSprint) throws SQLException {
        boolean check = false;
        String query = "select id_aluno_avaliador, id_sprint from avaliacao where id_aluno_avaliador = ? and id_sprint = ?";

        try (PreparedStatement ps = stm.getConnection().prepareStatement(query)) {
            ps.setInt(1, idAluno);
            ps.setInt(2, idSprint);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id_aluno_avaliador") == idAluno && rs.getInt("id_sprint") == idSprint) {
                    check = true;
                }
            }
        }
        return check;
    }

    //MÉTODOS PARA ALTERAÇÃO DE SENHA DO USUÁRIO

    public static void updatePassword(Statement stm, String senha, String email) {
        String query = "update usuario set senha = '" + senha + "' where email = '" + email + "'";
        try {
            stm.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPassword(Statement stm, String email) {
        String password = "";
        String query = "select senha from usuario where email = '" + email + "'";

        try {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                password = rs.getString("senha");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static Integer getCountStudents(Statement stm, Integer idGrupo) {
        System.out.println(idGrupo);
        Integer count = 0;
        String query = "with teste as(" +
                "select nome_grupo " +
                "from grupo " +
                "where id = " + idGrupo + " " +
                ") select count(email) as num_alunos " +
                "from aluno " +
                "inner join teste on teste.nome_grupo = aluno.grupo " +
                "where aluno.grupo = teste.nome_grupo";

        try {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("num_alunos");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static Boolean testDateSprint(Statement stm, LocalDate start_date, LocalDate end_date) {
        Boolean resultado = false;
        try {
            ResultSet result = stm.executeQuery("SELECT count(id) " +
                    "FROM sprint " +
                    "WHERE '"+ start_date +"' BETWEEN data_inicio AND data_fim " +
                    "OR '"+ end_date +"' BETWEEN data_inicio AND data_fim");
            while (result.next()) { // result.next() roda enquanto existirem dados no banco.
                Integer Contagem = Integer.parseInt(result.getString("count"));

                if (Contagem == 0) {
                    resultado = false;
                } else {
                    resultado = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado; //
    }

}



