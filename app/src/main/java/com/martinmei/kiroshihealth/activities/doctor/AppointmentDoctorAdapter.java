package com.martinmei.kiroshihealth.activities.doctor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Appointment;

import java.util.List;

public class AppointmentDoctorAdapter extends RecyclerView.Adapter<AppointmentDoctorAdapter.AppointmentViewHolder> implements View.OnClickListener {
    private List<Appointment> appointmentList;
    private final OnAppointmentDoctorListener onAppointmentDoctorListener;

    public AppointmentDoctorAdapter(OnAppointmentDoctorListener onAppointmentDoctorListener) {
        this.onAppointmentDoctorListener = onAppointmentDoctorListener;
    }

    public void updateAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View appointmentROW = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_appointment, viewGroup, false);
        appointmentROW.setOnClickListener(this);
        return new AppointmentViewHolder(appointmentROW);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder appointmentViewHolder, int i) {
        Appointment appointment = appointmentList.get(i);
        appointmentViewHolder.textViewDniPatient.setText(appointment.getDniPatient());
        appointmentViewHolder.textViewSubject.setText(appointment.getSubject());
        appointmentViewHolder.textViewDate.setText(appointment.getDate());
        appointmentViewHolder.textViewCodMeet.setText(appointment.getCodMeet().toString());
        appointmentViewHolder.itemView.setTag(appointment);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    @Override
    public void onClick(View v) {
        Appointment appointment = (Appointment) v.getTag();
        onAppointmentDoctorListener.onAppointmentDoctorItemClick(appointment);
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDniPatient, textViewSubject, textViewDate, textViewCodMeet;
        AppointmentViewHolder(View itemView) {
            super(itemView);
            this.textViewDniPatient = itemView.findViewById(R.id.tv_row_appointment_dni_patient);
            this.textViewSubject = itemView.findViewById(R.id.tv_appointment_row_subject);
            this.textViewDate = itemView.findViewById(R.id.tv_row_appointment_date);
            this.textViewCodMeet = itemView.findViewById(R.id.tv_row_appointment_meet_code);

        }
    }
}

interface OnAppointmentDoctorListener{

    void onAppointmentDoctorItemClick(Appointment appointment);
}
