package org.example.paceralphacode;

import javafx.beans.property.SimpleStringProperty;

public class table {
    private final SimpleStringProperty coluna1;
    public table(){
        this.coluna1 = new SimpleStringProperty();
    }
    public String getColuna1() {
        return coluna1.get();
    }
    public void setColuna1(String coluna1) {
        this.coluna1.set(coluna1);
    }




}
