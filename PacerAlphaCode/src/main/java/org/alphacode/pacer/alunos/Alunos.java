package org.alphacode.pacer.alunos;

public class Alunos {

    String nome, email, grupo, repo;
    Double nota;

    public Alunos(String nome, String email, String grupo, String repo) {
        this.nome = nome;
        this.email = email;
        this.grupo = grupo;
        this.repo = repo;
    }

    public Alunos(String nome, String email, String grupo) {
        this.nome = nome;
        this.email = email;
        this.grupo = grupo;
    }

    public Alunos(String email) {
    }

    public Alunos(String nome, Double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}




