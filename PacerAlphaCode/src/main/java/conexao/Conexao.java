package conexao;

import java.sql.*;

public class Conexao {

    // esse métod não pode ser main!

    public void conexaoBD(){
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pacer",
                    "adminpacer", "AdminPacer1234"); // DRIVER E ACESSOS AO BANCO DE DADOS.
            if (conexao != null) {
                Statement stm = conexao.createStatement();
            }else{
                System.out.println("Erro ao conectar ao Banco de Dados!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // esse métod não ficará aqui, e sim em uma classe fora, onde estarão todos os métodos.

    public static void consultarDados(Statement stm){ // METHOD PARA FAZER UM SELECT.
        String query = "select * from aluno"; // QUERY DESEJADA.
        try {
            ResultSet result = stm.executeQuery(query);
            while(result.next()){ // result.next() roda enquanto existirem dados no banco.
                System.out.println("Senha: " + result.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}