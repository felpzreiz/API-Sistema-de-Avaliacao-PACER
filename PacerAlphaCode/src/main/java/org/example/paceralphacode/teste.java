package org.example.paceralphacode;

import java.sql.SQLException;
import java.sql.Statement;
import conexao.OperacoesSQL;


public class teste {
    public static void main(String[] args) throws SQLException {
        OperacoesSQL teste =  new OperacoesSQL();
        Statement stm = teste.conectarBanco();
        OperacoesSQL.consultarDados(stm, "select * from aluno");
    }
}
