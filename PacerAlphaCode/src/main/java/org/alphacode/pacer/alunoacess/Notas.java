package org.alphacode.pacer.alunoacess;

public class Notas {
    private Float nota;
    private String criterio;


    public Notas(String criterio, Float nota) {
        this.criterio = criterio;
        this.nota = nota;
    }

    public Notas(Float nota) {
        this.nota = nota;
    }


    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
}
