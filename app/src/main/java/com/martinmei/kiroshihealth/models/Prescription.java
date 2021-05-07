package com.martinmei.kiroshihealth.models;

public class Prescription {

    private Integer codPrescription ;
    private String name;
    private String description;
    private String dniPatient;

    public Prescription(Integer codPrescription, String name, String description, String dniPatient) {
        this.codPrescription = codPrescription;
        this.name = name;
        this.description = description;
        this.dniPatient = dniPatient;
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

    public String getDniPatient() {
        return dniPatient;
    }
}
