package org.alphacode.pacer.alunoacess;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class AlunosInterface {

    private StringProperty nome;
    private List<Notas> notas;

    public AlunosInterface(String nome) {
        this.nome = new SimpleStringProperty(nome);
        this.notas = new ArrayList<Notas>();
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(StringProperty nome) {
        this.nome = nome;
    }

    public List<Notas> getNotas() {
        return notas;
    }

    public void setNotas(List<Notas> notas) {
        this.notas = notas;
    }

    public void addNota(Notas nota) {
        this.notas.add(nota);
    }
}
