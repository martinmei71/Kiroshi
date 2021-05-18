package com.martinmei.kiroshihealth.activities.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;

import java.util.List;

public class PatientsListActivity extends AppCompatActivity implements OnPatientListener {

    public static Intent newIntent(Context context, Doctor doctor){
        Intent intent = new Intent(context,PatientsListActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, doctor);
        return intent;
    }

    private RecyclerView recyclerviewPatients;
    private TextView tvNoData;
    private Toolbar toolbar;
    private TextView tvToolbar;
    private List<Patient> patients;
    private Doctor doctor;
    private PatientAdapter patientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);
        initBindings();
        initData();
        initAdapter();
        initToolbar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.list_patient_title));
    }

    public void initBindings(){
        recyclerviewPatients = findViewById(R.id.recycled_view_patients_patient);
        tvNoData = findViewById(R.id.tv_patient_list_no_data);
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
    }

    private void initData(){
        doctor = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        patients = Database.getPatients(this);
    }

    public void initAdapter(){
            patientAdapter = new PatientAdapter( this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerviewPatients.setLayoutManager(mLayoutManager);
            recyclerviewPatients.setItemAnimator(new DefaultItemAnimator());
            recyclerviewPatients.setAdapter(patientAdapter);
    }

    private void showMessageNoDataIfIsNeeded(){
        if (patients.isEmpty()){
            tvNoData.setVisibility(View.VISIBLE);
        }else {
            tvNoData.setVisibility(View.GONE);
        }
    }

    public void goToPatientDetail(Patient patient){
        startActivity(PatientDetailActivity.newIntent(this, patient));
    }

    @Override
    public void onPatientItemClick(Patient patient) {
        goToPatientDetail(patient);
    }

    private void updateUI(){
        List<Patient> patients = Database.getDoctorPatients(this,doctor.getDni());
        patientAdapter.updatePatientList(patients);
        patientAdapter.notifyDataSetChanged();
        showMessageNoDataIfIsNeeded();
    }


}