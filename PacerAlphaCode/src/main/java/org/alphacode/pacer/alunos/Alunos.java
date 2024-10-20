package org.alphacode.pacer.alunos;

public class Alunos {

    String nome, email, grupo, repo;

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


}




