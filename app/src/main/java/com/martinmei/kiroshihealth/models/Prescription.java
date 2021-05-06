package com.martinmei.kiroshihealth.models;

public class Prescription {

    private Integer codPrescription ;
    private String name;
    private String description;
    private String dni;

    public Prescription(Integer codPrescription, String name, String description, String dni) {
        this.codPrescription = codPrescription;
        this.name = name;
        this.description = description;
        this.dni = dni;
    }

    public Integer getCodPrescription() {
        return codPrescription;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDni() {
        return dni;
    }
}
