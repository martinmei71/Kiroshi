package com.martinmei.kiroshihealth.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Patient;

import java.util.List;

public class PatientAdapter  extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {
    private List<Patient> patientList;

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public  PatientAdapter(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View patientRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_patient, viewGroup, false);
        return new MyViewHolder(patientRow);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.MyViewHolder myViewHolder, int i) {

        Patient patient = patientList.get(i);
        String dni = patient.getDni();
        String name = patient.getName().toUpperCase();
        String lastName = patient.getLastName().toUpperCase();
        String phone = patient.getPhone();

        myViewHolder.textViewDni.setText(dni);
        myViewHolder.textViewName.setText(name);
        myViewHolder.textViewLastName.setText(lastName);
        myViewHolder.textViewPhone.setText(phone);
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDni, textViewName, textViewLastName, textViewPhone;

        MyViewHolder(View itemView) {
            super(itemView);
            this.textViewDni = itemView.findViewById(R.id.tv_patient_row_dni);
            this.textViewName = itemView.findViewById(R.id.tv_patient_row_name);
            this.textViewLastName = itemView.findViewById(R.id.tv_patient_row_last_name);
            this.textViewPhone=itemView.findViewById(R.id.tv_patient_row_phone);
        }
    }
}