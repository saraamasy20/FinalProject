package com.example.learneasily.models;

public class Teachers {

    String firstName ;
    String lastName;
    String email ;
    String FIN;
    String passwoed;

    public Teachers() {
    }

    public Teachers(String firstName, String lastName, String email, String FIN, String passwoed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.FIN = FIN;
        this.passwoed = passwoed;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFIN() {
        return FIN;
    }

    public void setFIN(String FIN) {
        this.FIN = FIN;
    }

    public String getPasswoed() {
        return passwoed;
    }

    public void setPasswoed(String passwoed) {
        this.passwoed = passwoed;
    }
}
