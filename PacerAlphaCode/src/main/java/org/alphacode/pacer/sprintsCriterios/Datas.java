package org.alphacode.pacer.sprintsCriterios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datas {

    private int idSprint;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private LocalDate dataFinalAv;

    // Construtor com ID, data inicial e data final
    public Datas(int idSprint, LocalDate dataInicial, LocalDate dataFinal) {
        this.idSprint = idSprint;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    // Construtor sem o ID da sprint
    public Datas(LocalDate dataInicial, LocalDate dataFinal) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Datas(int idSprint, LocalDate dataInicial, LocalDate dataFinal, LocalDate dataFinalAv) {
        this.idSprint = idSprint;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.dataFinalAv = dataFinalAv;
    }

    // Getters e Setters
    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    // Método adicional para formatar as datas, caso precise exibir como String em outro lugar
    public String getDataInicialFormatada() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataInicial.format(dateFormat);
    }

    public String getDataFinalFormatada() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataFinal.format(dateFormat);
    }

    public LocalDate getDataFinalAv() {
        return dataFinalAv;
    }

    public void setDataFinalAv(LocalDate dataFinalAv) {
        this.dataFinalAv = dataFinalAv;
    }
}