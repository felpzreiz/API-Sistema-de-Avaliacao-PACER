package org.example.paceralphacode;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleStringProperty name;

    public User(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
}
