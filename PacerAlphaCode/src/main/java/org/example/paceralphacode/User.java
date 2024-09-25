package org.example.paceralphacode;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleStringProperty email;
    public User(String email) {
        this.email = new SimpleStringProperty(email);
    }
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
    public SimpleStringProperty emailProperty() {
        return email;
    }



}
