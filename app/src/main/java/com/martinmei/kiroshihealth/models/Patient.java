package com.martinmei.kiroshihealth.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Patient implements Parcelable {
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
    public Patient(){}

    protected Patient(Parcel in) {
        dni = in.readString();
        dniDoc = in.readString();
        name = in.readString();
        lastName = in.readString();
        phone = in.readString();
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dni);
        dest.writeString(dniDoc);
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(phone);
    }
}
