package com.martinmei.kiroshihealth.activities.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;

public class MyPatientDataActivity extends BaseActivity {

    private Patient patient;
    private Toolbar toolbar;
    private TextView tvPatientFullName;
    private TextView tvPhone;
    private TextView tvToolbar;
    private TextView tvDni;
    private TextView tvDoctorName;


    public static Intent newIntent(Context context, Patient patient){
        Intent intent = new Intent(context,MyPatientDataActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, patient);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient_data);
        initBindings();
        initData();
        initToolbar();
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        patient = Database.getPatient(this,patient.getDni());
        initUI();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.my_data_doctor_title));
    }

    private void initData() {
        patient = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
    }

    private void initBindings() {
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        tvDni = findViewById(R.id.tv_dni_mpda);
        tvPatientFullName = findViewById(R.id.tv_full_name_mpda);
        tvPhone = findViewById(R.id.tv_phone_mpda);
        tvDoctorName = findViewById(R.id.tv_doctor_name_mpda);
    }

    private String searchDoctorName(){
        Doctor doctor = Database.getDoctor(this, patient.getDniDoc());
        String name = doctor.getName()+" "+doctor.getLastName();
        return name;
    }

    private void initUI(){
        tvPatientFullName.setText(patient.getName()+" "+patient.getLastName());
        tvDoctorName.setText(searchDoctorName());
        tvPhone.setText(patient.getPhone());
        tvDni.setText(patient.getDni());
    }

    public void goToEditPatientData(View view){
        startActivity(EditPatientDataActivity.newIntent(this,patient));
    }
}