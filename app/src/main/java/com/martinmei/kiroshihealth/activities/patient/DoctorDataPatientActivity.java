package com.martinmei.kiroshihealth.activities.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;

public class DoctorDataPatientActivity extends BaseActivity {

    private TextView tvName;
    private TextView tvLastName;
    private TextView tvDNI;
    private TextView tvSpecialty;
    private TextView tvToolbar;
    private Toolbar toolbar;
    private Patient patient;
    private Doctor doctor;

    public static Intent newIntent(Context context, Patient patient){
        Intent intent = new Intent(context, DoctorDataPatientActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, patient);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_data_patient);
        initBindings();
        initData();
        initUI();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.doctor_data_title));
    }
    private void initBindings(){
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
        tvDNI = findViewById(R.id.tv_dni_ddpa);
        tvName = findViewById(R.id.tv_name_ddpa);
        tvLastName = findViewById(R.id.tv_last_name_ddpa);
        tvSpecialty = findViewById(R.id.tv_specialty_ddpa);
    }

    private void initData(){
        patient = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        doctor = Database.getDoctor(this,patient.getDniDoc());
    }

    private void initUI(){
        tvDNI.setText(doctor.getDni());
        tvName.setText(doctor.getName());
        tvLastName.setText(doctor.getLastName());
        tvSpecialty.setText(doctor.getSpecialty());
    }
}