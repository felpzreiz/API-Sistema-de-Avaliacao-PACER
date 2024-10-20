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

    public static List<Alunos> consultarDados(Statement stm, String query){ // METHOD PARA FAZER UM SELECT.
        List<Alunos> listaAlunos = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery(query);
            while(result.next()){ // result.next() roda enquanto existirem dados no banco.
                String nome = result.getString("senha");
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
        String insereAluno = "INSERT INTO aluno VALUES (" + query + ")";

        try {
            stm.executeQuery(insereAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void alterar(Statement stm, String query){}

    public static void excluir(Statement stm, String email){
        String excluiAluno = "DELETE FROM aluno WHERE email = '" + email + "'";
        try {
            stm.executeQuery(excluiAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
