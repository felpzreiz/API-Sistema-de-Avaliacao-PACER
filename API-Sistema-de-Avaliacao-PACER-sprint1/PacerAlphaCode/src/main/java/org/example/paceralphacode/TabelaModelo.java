package org.example.paceralphacode;

import javafx.beans.property.SimpleStringProperty;

public class TabelaModelo {
    private final SimpleStringProperty  tabelaemails;

    public TabelaModelo() {
        this.tabelaemails = new SimpleStringProperty();
    }

    // Métodos getters e setters
    public String getEmail() {
        return  tabelaemails.get();
    }

    public void setEmail(String value) {
        tabelaemails.set(value);
    }
}
