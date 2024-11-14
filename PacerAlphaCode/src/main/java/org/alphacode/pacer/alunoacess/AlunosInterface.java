package org.alphacode.pacer.alunoacess;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlunosInterface {

    private StringProperty nome;
    private List<Notas> notas;
    private Map<String, Object> parametros;

    public AlunosInterface(StringProperty nome, List<Notas> notas) {
        this.nome = nome;
        this.notas = notas;
    }

    public AlunosInterface(String nome) {
        this.nome = new SimpleStringProperty(nome);
        this.notas = new ArrayList<>();
        this.parametros = new HashMap<>();
    }

    public AlunosInterface(String nome, List<Notas> notas) {
        this.nome = new SimpleStringProperty(nome);
        this.notas = notas != null ? notas : new ArrayList<>();
        this.parametros = new HashMap<>();
    }


    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(StringProperty nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public List<Notas> getNotas() {
        return notas;
    }

    public void setNotas(List<Notas> notas) {
        this.notas = notas;
    }

    public void addNotas(Notas nota) {
        this.notas.add(nota);
    }

    public void setParametro(String chave, Object valor) {
        parametros.put(chave, valor);
    }

    public Object getParametro(String chave) {
        return parametros.get(chave);
    }

    public void carregarNotas(int nAlunos) {


        for (Notas nota : notas) {
            if (nota.getCriterio() != null && nota.getNota() != null) {
                Float media = nota.getNota() / nAlunos;
                media = (float) Math.round(media * 10) / 10;

                this.setParametro(nota.getCriterio(), media);
            } else {
                System.out.println("Erro: critério é null.");
            }
        }
    }

}
