package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Patient;

import java.util.List;

public class PatientsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);

        RecyclerView recycledview_patients = findViewById(R.id.recycled_view_patients);

        List<Patient> patients =Database.getPatients(this);



        PatientAdapter patientAdapter = new PatientAdapter(patients);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycledview_patients.setLayoutManager(mLayoutManager);
        recycledview_patients.setItemAnimator(new DefaultItemAnimator());
        recycledview_patients.setAdapter(patientAdapter);
    }
}