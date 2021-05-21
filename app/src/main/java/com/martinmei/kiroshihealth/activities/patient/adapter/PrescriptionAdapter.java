package com.martinmei.kiroshihealth.activities.patient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Prescription;

import java.util.List;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.PrescriptionViewHolder> implements View.OnClickListener {
    private List<Prescription> prescriptionList;
    private final OnPrescriptionListener onPrescriptionListener;

    public  PrescriptionAdapter (OnPrescriptionListener onPrescriptionListener) {
        this.onPrescriptionListener = onPrescriptionListener;
    }

    public void updatePatientList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    @NonNull
    @Override
    public PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View prescriptionRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_prescription, viewGroup, false);
        prescriptionRow.setOnClickListener(this);
        return new PrescriptionViewHolder(prescriptionRow);
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionViewHolder prescriptionViewHolder, int i) {
        Prescription prescription = prescriptionList.get(i);
        String cod = prescription.getCodPrescription().toString();
        String subject = prescription.getName().toUpperCase();
        String description = prescription.getDescription();
        prescriptionViewHolder.tvCode.setText(cod);
        prescriptionViewHolder.tvSubject.setText(subject);
        prescriptionViewHolder.tvDescription.setText(description);
        prescriptionViewHolder.itemView.setTag(prescription);

    }

    @Override
    public int getItemCount() {return prescriptionList.size();}

    @Override
    public void onClick(View v) {
        Prescription prescription = (Prescription) v.getTag();
        onPrescriptionListener.onPrescriptionItemClick(prescription);
    }

    public class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView tvCode, tvSubject, tvDescription;
        PrescriptionViewHolder(View itemView) {
            super(itemView);
            this.tvCode = itemView.findViewById(R.id.tv_cod_rwp);
            this.tvSubject= itemView.findViewById(R.id.tv_subject_rwp);
            this.tvDescription = itemView.findViewById(R.id.tv_description_rwp);
        }
    }
}

