package com.martinmei.kiroshihealth;

public class Paciente {
    private String dniP;
    private String dniDoc;
    private String nombre;
    private String apellidos;
    private Integer telefono;

    public Paciente(String dniP, String dniDoc, String nombre, String apellidos, Integer telefono){
        this.dniP = dniP;
        this.dniDoc = dniDoc;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }
    public Paciente(){

    }

    public String getDniP() {
        return dniP;
    }

    public void setDniP(String dniP) {
        this.dniP = dniP;
    }

    public String getDniDoc() {
        return dniDoc;
    }

    public void setDniDoc(String dniDoc) {
        this.dniDoc = dniDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
}
