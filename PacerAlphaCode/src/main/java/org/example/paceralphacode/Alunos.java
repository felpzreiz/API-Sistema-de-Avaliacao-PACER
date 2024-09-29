package org.example.paceralphacode;

import javafx.beans.property.SimpleStringProperty;

public class Alunos {
    private final SimpleStringProperty name;

    public Alunos(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return this.name;
    }
}
