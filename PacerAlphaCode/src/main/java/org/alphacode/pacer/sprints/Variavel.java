package org.alphacode.pacer.sprints;

import java.time.LocalDate;

public class Variavel {
    private String criterio;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean checked; // Estado da checkbox

    public Variavel(String criterio, LocalDate dataInicio, LocalDate dataFim, boolean checked) {
        this.criterio = criterio;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.checked = checked;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
