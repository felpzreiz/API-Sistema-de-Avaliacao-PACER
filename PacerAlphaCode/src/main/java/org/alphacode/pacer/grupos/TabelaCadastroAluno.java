package org.alphacode.pacer.grupos;

import javafx.beans.property.SimpleStringProperty;

public class TabelaCadastroAluno {
    private final SimpleStringProperty  viewStudent;

    public TabelaCadastroAluno() {
        this.viewStudent = new SimpleStringProperty();
    }

    // Métodos getters e setters
    public String getEmail() {
        return  viewStudent.get();
    }

    public void setEmail(String value) {
        viewStudent.set(value);
    }
}
