package com.martinmei.kiroshihealth.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Appointment implements Parcelable {

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
    public Appointment( String subject, String date, String dniPatient, String dniDoctor) {
        this.subject = subject;
        this.date = date;
        this.dniPatient = dniPatient;
        this.dniDoctor = dniDoctor;
    }

    protected Appointment(Parcel in) {
        if (in.readByte() == 0) {
            codMeet = null;
        } else {
            codMeet = in.readInt();
        }
        subject = in.readString();
        date = in.readString();
        dniPatient = in.readString();
        dniDoctor = in.readString();
    }

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (codMeet == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(codMeet);
        }
        dest.writeString(subject);
        dest.writeString(date);
        dest.writeString(dniPatient);
        dest.writeString(dniDoctor);
    }
}
