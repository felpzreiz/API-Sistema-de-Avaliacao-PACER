package org.example.paceralphacode;

import conexao.Conexao;

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
                System.out.println("Senha: " + result.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
