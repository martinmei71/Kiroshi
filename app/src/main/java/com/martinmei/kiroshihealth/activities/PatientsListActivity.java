package com.martinmei.kiroshihealth.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Patient;

import java.util.List;

public class PatientsListActivity extends AppCompatActivity implements OnPatientListener {

  private  RecyclerView recycledviewPatients;
  private TextView tvNoData;
  private Toolbar toolbar;
  private TextView tvToolbar;
  private List<Patient> patients;
  private  PatientAdapter patientAdapter;

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

    // COUSO DA FLECHA PARA IR ATRAS NA TOOLBAR
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
        recycledviewPatients = findViewById(R.id.recycled_view_patients);
        tvNoData = findViewById(R.id.tv_patient_list_no_data);
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
    }

    private void initData(){
        patients = Database.getPatients(this);
    }

    public void initAdapter(){
            patientAdapter = new PatientAdapter( this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycledviewPatients.setLayoutManager(mLayoutManager);
            recycledviewPatients.setItemAnimator(new DefaultItemAnimator());
            recycledviewPatients.setAdapter(patientAdapter);

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
        List<Patient> patients = Database.getPatients(this);
        patientAdapter.updatePatientList(patients);
        patientAdapter.notifyDataSetChanged();
        showMessageNoDataIfIsNeeded();
    }


}