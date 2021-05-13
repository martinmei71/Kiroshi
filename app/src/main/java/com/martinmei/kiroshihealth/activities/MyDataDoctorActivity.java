package com.martinmei.kiroshihealth.activities;

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
import com.martinmei.kiroshihealth.models.Doctor;

public class MyDataDoctorActivity extends AppCompatActivity {

    private TextView tvFullName;
    private TextView tvDNI;
    private TextView tvSpecialty;
    private TextView tvToolbar;
    private Toolbar toolbar;
    private Doctor doctor;

    public static Intent newIntent(Context context, Doctor doctor){
        Intent intent = new Intent(context,MyDataDoctorActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, doctor);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data_doctor);
        initBindings();
        initData();
        initToolbar();
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
        tvToolbar.setText(getString(R.string.my_data_doctor_title));
    }

    private void initBindings() {
        tvFullName = findViewById(R.id.tv_full_name_mdda);
        tvDNI = findViewById(R.id.tv_dni_mdda);
        tvSpecialty = findViewById(R.id.tv_specialty_mdda);
    }

    private void initData() {
        doctor = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        tvFullName.setText(doctor.getName()+" "+doctor.getLastName());
        tvDNI.setText(doctor.getDni());
        tvSpecialty.setText(doctor.getSpecialty());
    }

    public void  goToEditDoctor(View view){
        Intent intent = EditDoctorDataActivity.newIntent(this,doctor);
    }

}