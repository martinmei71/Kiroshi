package com.martinmei.kiroshihealth.models;

public class Patient {
    private String dni;
    private String dniDoc;
    private String name;
    private String lastName;
    private String phone;

    public Patient(String dni, String name, String lastName, String phone, String dniDoc){
        this.dni = dni;
        this.dniDoc = dniDoc;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getDni() {
        return dni;
    }

    public String getDniDoc() {
        return dniDoc;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }


}
