package com.martinmei.kiroshihealth.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Prescription implements Parcelable {

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
    public Prescription( String name, String description, String dniPatient) {
        this.name = name;
        this.description = description;
        this.dniPatient = dniPatient;
    }

    protected Prescription(Parcel in) {
        if (in.readByte() == 0) {
            codPrescription = null;
        } else {
            codPrescription = in.readInt();
        }
        name = in.readString();
        description = in.readString();
        dniPatient = in.readString();
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

    public static final Creator<Prescription> CREATOR = new Creator<Prescription>() {
        @Override
        public Prescription createFromParcel(Parcel in) {
            return new Prescription(in);
        }

        @Override
        public Prescription[] newArray(int size) {
            return new Prescription[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (codPrescription == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(codPrescription);
        }
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(dniPatient);
    }
}
