package com.martinmei.kiroshihealth.activities;

import android.os.Parcel;
import android.os.Parcelable;

import com.martinmei.kiroshihealth.extra.Constants;

public class DoctorMenuInput implements Parcelable {
    private String dni;
    private String name;
    private String lastName;
    private String specialty;


    public DoctorMenuInput(String dni, String name, String lastName, String specialty) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.specialty = specialty;
    }


    protected DoctorMenuInput(Parcel in) {
        dni = in.readString();
        name = in.readString();
        lastName = in.readString();
        specialty = in.readString();
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

    public static final Creator<DoctorMenuInput> CREATOR = new Creator<DoctorMenuInput>() {
        @Override
        public DoctorMenuInput createFromParcel(Parcel in) {
            return new DoctorMenuInput(in);
        }

        @Override
        public DoctorMenuInput[] newArray(int size) {
            return new DoctorMenuInput[size];
        }
    };
}
