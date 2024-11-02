package conexao;

import org.alphacode.pacer.alunoacess.AlunosInterface;
import org.alphacode.pacer.alunos.Alunos;
import org.alphacode.pacer.grupos.Grupo;
import org.alphacode.pacer.sprintsCriterios.Criterios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String excluiAluno = "DELETE FROM aluno WHERE email = '" + email + "'";
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



        /*public static void inserirGrupo(Statement stm, String grupo){
        String insereGrupo = "INSERT INTO grupo(nome_grupo) VALUES ('"+grupo+"')";
        try {
            stm.executeUpdate(insereGrupo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}