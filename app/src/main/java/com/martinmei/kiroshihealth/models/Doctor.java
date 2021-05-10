package com.martinmei.kiroshihealth.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Doctor implements Parcelable {

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



     protected Doctor(Parcel in) {
          dni = in.readString();
          name = in.readString();
          lastName = in.readString();
          specialty = in.readString();
     }

     @Override
     public int describeContents() {
          return 0;
     }

     @Override
     public void writeToParcel(Parcel dest, int flags) {
          dest.writeString(dni);
          dest.writeString(name);
          dest.writeString(lastName);
          dest.writeString(specialty);
     }

     public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
          @Override
          public Doctor createFromParcel(Parcel in) {
               return new Doctor(in);
          }

          @Override
          public Doctor[] newArray(int size) {
               return new Doctor[size];
          }
     };
}
