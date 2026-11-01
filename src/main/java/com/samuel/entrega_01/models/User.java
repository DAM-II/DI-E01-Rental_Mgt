package com.samuel.entrega_01.models;

import java.time.LocalDate;

public class User {
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String dni;
    private String telephone;
    private LocalDate registerDate;

    public User(String firstName, String lastName, String secondSurname, String dni, String telephone) {
        this.firstName = firstName;
        this.firstSurname = lastName;
        this.secondSurname = secondSurname;
        this.dni = dni;
        this.telephone = telephone;
        this.registerDate = LocalDate.now();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public String getDni() {
        return dni;
    }

    public String getTelephone() {
        return telephone;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    @Override
    public String toString(){
        return firstName + " " + firstSurname;
    }

}
