package conexao;

import org.alphacode.pacer.alunos.Alunos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperacoesSQL {

    public static Statement conectarBanco() throws SQLException {
        Conexao conexao = new Conexao();
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pacer",
                "adminpacer", "AdminPacer1234");;
        conexao.conexaoBD();
        Statement stm = con.createStatement();
        return(stm);
    }

    public static List<Alunos> consultarDados(Statement stm){ // METHOD PARA FAZER UM SELECT.
        List<Alunos> listaAlunos = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT senha,email,grupo,* FROM aluno ORDER BY id ASC");
            while(result.next()){ // result.next() roda enquanto existirem dados no banco.
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

    public static void inserir(Statement stm, String query){ // METHOD PARA FAZER UM INSERT.
        String insereAluno = "INSERT INTO aluno(email,senha,git,grupo,nome) VALUES (" + query + ")";

        try {
            stm.executeUpdate(insereAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAluno(Statement stm, String email, String git, String grupo, String nome, Integer id){
        String updateAluno = "UPDATE aluno SET email = '"+ email
                +"', git = '"+ git
                +"', grupo = '"+ grupo
                +"', nome = '"+ nome
                +"' WHERE id = '"+ id
                +"'";
        try {
            stm.executeUpdate(updateAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void excluir(Statement stm, String email){
        String excluiAluno = "DELETE FROM aluno WHERE email = '" + email + "'";
        try {
            stm.executeUpdate(excluiAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer SelectIDEdit(Statement stm, String email){
        Integer idAluno = 0;
        try {
            ResultSet result = stm.executeQuery("SELECT id FROM aluno WHERE email = '" + email + "'");
            while(result.next()){ // result.next() roda enquanto existirem dados no banco.
                Integer Id = Integer.parseInt(result.getString("id"));

                idAluno = Id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAluno; //
    }
}
