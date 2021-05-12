package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;

public class PatientDetailActivity extends AppCompatActivity {

  private  TextView tvFullName;
  private  TextView tvDNI;
  private  TextView tvPhone;
  private  Patient patient;
  private  Toolbar toolbar;
  private  TextView toolabarTv;

    public static Intent newIntent(Context context, Patient patient){
        Intent intent = new Intent(context,PatientDetailActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, patient);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        initBindings();
        initData();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolabarTv.setText(getString(R.string.detail_title));
    }

    private void initBindings(){
    tvFullName = findViewById(R.id.tv_patient_detail_name_last_name);
    tvDNI = findViewById(R.id.tv_patient_detail_dni);
    tvPhone = findViewById(R.id.tv_patient_detail_phone);
    toolabarTv = findViewById(R.id.toolbar_title);
    toolbar = findViewById(R.id.toolbar);
    }

    private void initData(){
        patient = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        tvFullName.setText(patient.getName()+" "+patient.getLastName());
        tvDNI.setText(patient.getDni());
        tvPhone.setText(patient.getPhone());
    }

    public void onClickDeleteClient(View view){
       if (!Database.deletePatient(this, patient.getDni())) {
           Toast.makeText(this, getString(R.string.list_patient_failure_deleted), Toast.LENGTH_SHORT).show();
       } else{
           finish();
       }

    }

}