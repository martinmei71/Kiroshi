package com.martinmei.kiroshihealth.activities.doctor.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Patient;

import java.util.List;

public class PatientAdapter  extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> implements View.OnClickListener {
    private List<Patient> patientList;
    private final OnPatientListener onPatientListener;

    public  PatientAdapter(OnPatientListener onPatientListener) {
        this.onPatientListener = onPatientListener;
    }

    public void updatePatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View patientRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_patient, viewGroup, false);
        patientRow.setOnClickListener(this);
        return new PatientViewHolder(patientRow);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder patientViewHolder, int i) {
            Patient patient = patientList.get(i);
            String dni = patient.getDni();
            String name = patient.getName().toUpperCase();
            String lastName = patient.getLastName().toUpperCase();
            String phone = patient.getPhone();
            patientViewHolder.textViewDni.setText("💳 " + dni);
            patientViewHolder.textViewName.setText(name);
            patientViewHolder.textViewLastName.setText(lastName);
            patientViewHolder.textViewPhone.setText("📞 " + phone);
            patientViewHolder.itemView.setTag(patient);
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    @Override
    public void onClick(View v) {
        Patient patient = (Patient) v.getTag();
        onPatientListener.onPatientItemClick(patient);
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDni, textViewName, textViewLastName, textViewPhone;
        PatientViewHolder(View itemView) {
            super(itemView);
            this.textViewDni = itemView.findViewById(R.id.tv_patient_row_dni);
            this.textViewName = itemView.findViewById(R.id.tv_patient_row_name);
            this.textViewLastName = itemView.findViewById(R.id.tv_patient_row_last_name);
            this.textViewPhone=itemView.findViewById(R.id.tv_patient_row_phone);
        }
    }
}

