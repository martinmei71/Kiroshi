package com.martinmei.kiroshihealth.activities.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.extra.Utils;
import com.martinmei.kiroshihealth.models.Doctor;

public class EditDoctorDataActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etLastName;
    private EditText etSpecialty;
    private Toolbar toolbar;
    private TextView tvToolbar;
    private TextView tvDni;
    private Doctor doctor;

    public static Intent newIntent(Context context, Doctor doctor){
        Intent intent = new Intent(context,EditDoctorDataActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, doctor);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor_data);
        initBindings();
        initToolbar();
        initData();
        initUI();
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
        tvToolbar.setText(getString(R.string.edit));
    }

    private void initBindings() {
        etName = findViewById(R.id.et_name_edda);
        etLastName = findViewById(R.id.et_last_name_edda);
        etSpecialty = findViewById(R.id.et_specialty_edda);
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
        tvDni = findViewById(R.id.tv_dni_edda);
    }

    private void initUI() {
        etName.setText(doctor.getName());
        etLastName.setText(doctor.getLastName());
        etSpecialty.setText(doctor.getSpecialty());
        tvDni.setText(doctor.getDni());
    }

    private  void initData(){
        doctor = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
    }

    private boolean validateDoctorForm(){
        if (Utils.isEmpty(etName) || Utils.isEmpty(etLastName) || Utils.isEmpty(etSpecialty)) {
            Toast.makeText(this, getString(R.string.incomlpete_data), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void onClickUpdateDataDoctor(View view){
        if(validateDoctorForm()){
            Doctor doctorUpdated = new Doctor(doctor.getDni(),etName.getText().toString(),etLastName.getText().toString(),etSpecialty.getText().toString());
            Database.updateDoctor(this,doctorUpdated);
            finish();
        }
    }

}