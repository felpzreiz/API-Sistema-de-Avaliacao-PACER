package org.alphacode.pacer.grupos;

import javafx.beans.property.SimpleStringProperty;

public class Aluno {
    private final SimpleStringProperty email;

    public Aluno(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
}
