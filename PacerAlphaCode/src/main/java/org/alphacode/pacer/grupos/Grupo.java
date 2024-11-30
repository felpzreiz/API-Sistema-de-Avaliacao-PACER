package org.alphacode.pacer.grupos;

public class Grupo {

    String nomeGrupo;

    public Grupo(String grupo) {
        this.nomeGrupo = grupo;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    @Override
    public String toString() {
        return nomeGrupo;
    }
}
