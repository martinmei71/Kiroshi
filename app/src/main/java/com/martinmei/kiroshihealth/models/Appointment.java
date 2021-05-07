package com.martinmei.kiroshihealth.models;

public class Appointment {

    private Integer codMeet;
    private String subject;
    private String date;
    private String dniPatient;
    private String dniDoctor;

    public Appointment(Integer codMeet, String subject, String date, String dniPatient, String dniDoctor) {
        this.codMeet = codMeet;
        this.subject = subject;
        this.date = date;
        this.dniPatient = dniPatient;
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

    public String getDniPatient() {
        return dniPatient;
    }

    public String getDniDoctor() {
        return dniDoctor;
    }
}
