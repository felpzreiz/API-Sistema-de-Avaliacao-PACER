package conexao;

import java.sql.*;

public class OperacoesSQL {

    public static Statement conectarBanco() throws SQLException {
        Conexao conexao = new Conexao();
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pacer",
                "adminpacer", "AdminPacer1234");;
        conexao.conexaoBD();
        Statement stm = con.createStatement();
        return(stm);
    }

    public static void consultarDados(Statement stm, String query){ // METHOD PARA FAZER UM SELECT.
        try {
            ResultSet result = stm.executeQuery(query);
            while(result.next()){ // result.next() roda enquanto existirem dados no banco.
                System.out.println("Email: " + result.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void inserir(Statement stm, String query){ // METHOD PARA FAZER UM SELECT.
        String insereAluno = "INSERT INTO aluno (email) VALUES ('" + query + "')";

        try {
            stm.executeQuery(insereAluno);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
