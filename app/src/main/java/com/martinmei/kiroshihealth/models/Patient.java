package com.martinmei.kiroshihealth.models;

public class Patient {
    private String dni;
    private String dniDoc;
    private String name;
    private String lastName;
    private String phone;

    public Patient(String dni, String dniDoc, String name, String lastName, String phone){
        this.dni = dni;
        this.dniDoc = dniDoc;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }
    public Patient(){

    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dniP) {
        this.dni = dniP;
    }

    public String getDniDoc() {
        return dniDoc;
    }

    public void setDniDoc(String dniDoc) {
        this.dniDoc = dniDoc;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String apellidos) {
        this.lastName = apellidos;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String telefono) {
        this.phone = telefono;
    }
}
