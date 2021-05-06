package com.martinmei.kiroshihealth.models;

public class Appointment {

    private Integer codMeet;
    private String subject;
    private String date;
    private String dni;
    private String dniDoctor;

    public Appointment(Integer codMeet, String subject, String date, String dni, String dniDoctor) {
        this.codMeet = codMeet;
        this.subject = subject;
        this.date = date;
        this.dni = dni;
        this.dniDoctor = dniDoctor;
    }

    public Integer getCodMeet() {
        return codMeet;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getDni() {
        return dni;
    }

    public String getDniDoctor() {
        return dniDoctor;
    }
}
