package org.example.paceralphacode;

import java.sql.SQLException;
import java.sql.Statement;

public class teste {
    public static void main(String[] args) throws SQLException {
        OperacoesSQL teste =  new OperacoesSQL();

        Statement stm = teste.conectarBanco();

        OperacoesSQL.inserir(stm, "insert into aluno values ('gabriel@fatec','123')");

        OperacoesSQL.consultarDados(stm, "select * from aluno");
    }
}
