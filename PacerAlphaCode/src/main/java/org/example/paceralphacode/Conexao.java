package org.example.paceralphacode;

import java.sql.*;

public class Conexao {
    public static void main(String[] args) throws SQLException {
        Connection conexao = null;
        try{
            Class.forName("org.postgresql.Driver");//INDICA O DRIVER DO POSTGRES
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pacer", "AdminPacer", "AdminPacer1234");//CONEXAO COM O BANCO, COM USUARIO E SENHA
            ResultSet rsCliente = conexao.createStatement().executeQuery("select * from cliente");
            while(rsCliente.next()){
                System.out.println("Nome: " + rsCliente.getString("nome_cli") + " | Endereço: " + rsCliente.getString("endereco_cli"));
            }
        }catch(ClassNotFoundException ex){
            System.out.println("Driver do Banco de Dados não encontrado.");
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar ao Banco de Dados: " + ex.getMessage());
        } finally {
            if(conexao != null){
                conexao.close();
            }
        }
    }
}