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
}