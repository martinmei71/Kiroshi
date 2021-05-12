package com.martinmei.kiroshihealth.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Appointment;

import java.util.List;

public class AppointmentAdapter  extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder> {
    private List<Appointment> appointmentList;

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public  AppointmentAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View appointmentROW = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_appointment, viewGroup, false);
                return new MyViewHolder(appointmentROW);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Appointment appointment = appointmentList.get(i);
        String dniPatient = appointment.getDniPatient();
        String subject = appointment.getSubject().toUpperCase();
        String date = appointment.getDate().toUpperCase();
        Integer codMeet = appointment.getCodMeet();

        myViewHolder.textViewDniPatient.setText(dniPatient);
        myViewHolder.textViewSubject.setText(subject);
        myViewHolder.textViewDate.setText(date);
        myViewHolder.textViewCodMeet.setText(codMeet);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDniPatient, textViewSubject, textViewDate, textViewCodMeet;

        MyViewHolder(View itemView) {
            super(itemView);
            this.textViewDniPatient = itemView.findViewById(R.id.tv_row_appointment_dni_patient);
            this.textViewSubject = itemView.findViewById(R.id.tv_appointment_row_subject);
            this.textViewDate = itemView.findViewById(R.id.tv_row_appointment_date);
            this.textViewCodMeet = itemView.findViewById(R.id.tv_row_appointment_date);

        }
    }
}
