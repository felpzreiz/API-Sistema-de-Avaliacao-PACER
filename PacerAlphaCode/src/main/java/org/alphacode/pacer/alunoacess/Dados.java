package org.alphacode.pacer.alunoacess;

public class Dados {

    int idAvaliador;
    int idAvaliado;
    int idGrupo;
    int idSprint;
    int idAluno;

    public Dados(int idAvaliador, int idAvaliado, int idGrupo) {
        this.idAvaliador = idAvaliador;
        this.idAvaliado = idAvaliado;
        this.idGrupo = idGrupo;

    }


    public int getIdAvaliador() {
        return idAvaliador;
    }

    public void setIdAvaliador(int idAvaliador) {
        this.idAvaliador = idAvaliador;
    }

    public int getIdAvaliado() {
        return idAvaliado;
    }

    public void setIdAvaliado(int idAvaliado) {
        this.idAvaliado = idAvaliado;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }


}
