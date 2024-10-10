package org.example.paceralphacode;

import java.sql.SQLException;

public class teste {
    public static void main(String[] args) throws SQLException {
        OperacoesSQL teste =  new OperacoesSQL();

        OperacoesSQL.consultarDados(teste.conectarBanco(), "select * from aluno");
    }
}
