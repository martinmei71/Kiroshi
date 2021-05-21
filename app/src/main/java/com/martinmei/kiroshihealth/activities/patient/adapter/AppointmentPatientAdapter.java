package com.martinmei.kiroshihealth.activities.patient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Appointment;

import java.util.List;

public class AppointmentPatientAdapter extends RecyclerView.Adapter<AppointmentPatientAdapter.AppointmentViewHolder> implements View.OnClickListener {
    private List<Appointment> appointmentList;
    private final OnAppointmentListener onAppointmentListener;

    public AppointmentPatientAdapter(OnAppointmentListener onAppointmentListener) {
        this.onAppointmentListener = onAppointmentListener;
    }

    public void updateAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View appointmentRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_appointment, viewGroup, false);
        appointmentRow.setOnClickListener(this);
        return new AppointmentViewHolder(appointmentRow);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder appointmentViewHolder, int i) {
        Appointment appointment = appointmentList.get(i);
        appointmentViewHolder.tvCode.setText(appointment.getCodMeet().toString());
        appointmentViewHolder.tvSubject.setText(appointment.getSubject());
        appointmentViewHolder.tvDate.setText(appointment.getDate());
        appointmentViewHolder.tvDoctor.setText(appointment.getDniDoctor());
        appointmentViewHolder.itemView.setTag(appointment);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    @Override
    public void onClick(View v) {
        Appointment appointment = (Appointment) v.getTag();
        onAppointmentListener.onAppointmentItemClick(appointment);
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvCode, tvSubject, tvDate, tvDoctor;

        AppointmentViewHolder(View itemView) {
            super(itemView);
            this.tvCode = itemView.findViewById(R.id.tv_row_appointment_meet_code);
            this.tvSubject = itemView.findViewById(R.id.tv_appointment_row_subject);
            this.tvDate = itemView.findViewById(R.id.tv_row_appointment_date);
            this.tvDoctor = itemView.findViewById(R.id.tv_row_appointment_dni_patient);
        }
    }
}

