package org.alphacode.pacer.sprintsCriterios;

public class Criterios {
    String nomeCriterio;

    public String getNomeCriterio() {
        return nomeCriterio;
    }

    public void setNomeCriterio(String nomeCriterio) {
        this.nomeCriterio = nomeCriterio;
    }

    public Criterios(String nomeCriterio) {
        this.nomeCriterio = nomeCriterio;
    }

    @Override
    public String toString() {
        return  nomeCriterio;
    }
}
