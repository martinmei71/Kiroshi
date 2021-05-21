package com.martinmei.kiroshihealth.activities.patient;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.extra.Utils;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class EditPatientDataActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView tvToolbar;
    private TextView tvDni;
    private EditText etName;
    private EditText etLastName;
    private EditText etPhone;
    private Spinner spDoctors;
    private Patient patient;
    private List <String> doctorsStringList = new ArrayList<>();
    private List <Doctor> doctors = new ArrayList<>();

    public static Intent newIntent(Context context, Patient patient){
        Intent intent = new Intent(context,EditPatientDataActivity  .class);
        intent.putExtra(Intent.EXTRA_INTENT, patient);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_data);
        initBindings();
        initData();
        initSpinner();
        initToolbar();
        initUI();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.my_data_doctor_title));
    }

    private void initSpinner() {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_dropdown_item, doctorsStringList);
        spDoctors.setAdapter(spinnerArrayAdapter);
    }

    private void initData() {
        patient = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        doctors = Database.getDoctors(this);
        for(Doctor doctor : doctors){
            doctorsStringList.add(doctor.getName());
        }
    }

    private void initBindings() {
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
        tvDni = findViewById(R.id.tv_dni_epda);
        etName = findViewById(R.id.et_name_epda);
        etLastName = findViewById(R.id.et_last_name_epda);
        etPhone = findViewById(R.id.et_phone_epda);
        spDoctors = findViewById(R.id.sp_doctores_epda);
    }

    private void initUI(){
        tvDni.setText(patient.getDni());
        etName.setText(patient.getName());
        etLastName.setText(patient.getLastName());
        etPhone.setText(patient.getPhone());
    }

    private String getDniSelectedDoctor(){
        return doctors.get((spDoctors.getSelectedItemPosition()-1)).getDni();
    }

    private boolean verification(){
        if (Utils.isEmpty(etName) || Utils.isEmpty(etLastName) || Utils.validatePhone(etPhone)) {
            if(Utils.isEmpty(etName)){
                etName.setError(getString(R.string.common_text_empty));
            }
            if(Utils.isEmpty(etLastName)){
                etLastName.setError(getString(R.string.common_text_empty));
            }
            if(Utils.isEmpty(etPhone)){
               etPhone.setError(getString(R.string.common_text_empty));
            }
            showErrorMessage(getString(R.string.error_at_operation));
            return false;
        }
        return true;
    }

    public void onClickUpdatePatient(View view){
        if(verification()){
            Patient patientUpdated = new Patient(patient.getDni(),etName.getText().toString(),etLastName.getText().toString(),getDniSelectedDoctor(),etPhone.getText().toString());
            Database.updatePatient(this,patientUpdated);
            showMessage(getString(R.string.text_saved));
            finish();
        }
    }
}