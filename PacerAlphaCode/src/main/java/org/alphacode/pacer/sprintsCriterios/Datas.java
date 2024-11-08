package org.alphacode.pacer.sprintsCriterios;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Datas {

    private IntegerProperty idSprint;
    private ObjectProperty<LocalDate> dataInicial;
    private ObjectProperty<LocalDate> dataFinal;

    public Datas(int idSprint, LocalDate dataInicial, LocalDate dataFinal) {
        this.idSprint = new SimpleIntegerProperty(idSprint);
        this.dataInicial = new SimpleObjectProperty<>(dataInicial);
        this.dataFinal = new SimpleObjectProperty<>(dataFinal);
    }

    public Datas(LocalDate dataInicial, LocalDate dataFinal) {
        this.dataInicial = new SimpleObjectProperty<>(dataInicial);
        this.dataFinal = new SimpleObjectProperty<>(dataFinal);
    }

    // Métodos para acessar as propriedades observáveis
    public IntegerProperty idSprintProperty() {
        return idSprint;
    }

    public ObjectProperty<LocalDate> dataInicialProperty() {
        return dataInicial;
    }

    public ObjectProperty<LocalDate> dataFinalProperty() {
        return dataFinal;
    }

    // Métodos getter e setter
    public int getIdSprint() {
        return idSprint.get();
    }

    public void setIdSprint(int idSprint) {
        this.idSprint.set(idSprint);
    }

    public LocalDate getDataInicial() {
        return dataInicial.get();
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial.set(dataInicial);
    }

    public LocalDate getDataFinal() {
        return dataFinal.get();
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal.set(dataFinal);
    }
}
