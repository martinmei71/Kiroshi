package com.martinmei.kiroshihealth.models;

public class Doctor {

     private String dni ;
     private String name;
     private String lastName;
     private String specialty;

     public Doctor(String dni, String name, String lastName, String specialty) {
          this.dni = dni;
          this.name = name;
          this.lastName = lastName;
          this.specialty = specialty;
     }

     public String getDni() {
          return dni;
     }

     public String getName() {
          return name;
     }

     public String getLastName() {
          return lastName;
     }

     public String getSpecialty() {
          return specialty;
     }
}
