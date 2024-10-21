package org.alphacode.pacer.grupos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

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

    public ObservableValue<String> emailProperty() {
        return null;
    }
}
