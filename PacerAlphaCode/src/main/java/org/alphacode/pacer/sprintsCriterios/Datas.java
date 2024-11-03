package org.alphacode.pacer.sprintsCriterios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datas {

    private int idSprint;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public Datas(int idSprint, LocalDate dataInicial, LocalDate dataFinal) {
        this.idSprint = idSprint;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Datas(LocalDate dataInicial, LocalDate dataFinal) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }


    public String getDataInicial() {
        DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataInicial.format(dateFormat);
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataFinal.format(dateFormat);
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
}
