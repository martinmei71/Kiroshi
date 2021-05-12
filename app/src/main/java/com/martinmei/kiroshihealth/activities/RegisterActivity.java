package com.martinmei.kiroshihealth.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.extra.Constants;
import com.martinmei.kiroshihealth.extra.Utils;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private Spinner spUsers;
    private Spinner spFullNameDoctor;
    private EditText etTelephone;
    private EditText etName;
    private EditText etLastName;
    private EditText etDni;
    private EditText etSpecialty;
    private Button btnRegister;
    private Toolbar toolbar;
    private TextView tvToolbarTitle;
    private List<Doctor> doctors;
    private List <String> fullNameDoctorList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initBinding();
        initToolbar();
        initData();
        initSpinnerAdapter();
        setUpListeners();
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

    private void initBinding() {
        spUsers = findViewById(R.id.sp_selector_user_rua);
        spFullNameDoctor = findViewById(R.id.sp_dni_doctor_rua);
        etTelephone = findViewById(R.id.et_telephone_rua);
        etSpecialty = findViewById(R.id.et_specialty_rua);
        etName = findViewById(R.id.et_name_rua);
        etLastName = findViewById(R.id.et_last_name_rua);
        etDni = findViewById(R.id.et_dni_rua);
        btnRegister = findViewById(R.id.btn_registrarme_rua);
        toolbar = findViewById(R.id.toolbar);
        tvToolbarTitle = findViewById(R.id.toolbar_title);
    }


    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbarTitle.setText(getString(R.string.register_toolbar_title));
    }

    private void initData() {
        doctors = Database.getDoctors(this);
        fullNameDoctorList = new ArrayList<>();
        fullNameDoctorList.add(getString(R.string.register_select_option));
        for (Doctor doctor : doctors) {
            fullNameDoctorList.add(doctor.getName() + " " + doctor.getLastName());
        }
    }

    private void initUI() {
        spFullNameDoctor.setVisibility(View.GONE);
        etSpecialty.setVisibility(View.GONE);
        etTelephone.setVisibility(View.GONE);
    }

    private void initSpinnerAdapter() {
        ArrayAdapter<String> spinnerUserTypes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.users_array));
        spUsers.setAdapter(spinnerUserTypes);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_dropdown_item, fullNameDoctorList);
        spFullNameDoctor.setAdapter(spinnerArrayAdapter);
    }

    private void setUpListeners() {
        spUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spUsers.getSelectedItem().toString().equals(Constants.USER_DOCTOR)) {

                    spFullNameDoctor.setVisibility(View.GONE);
                    etSpecialty.setVisibility(View.VISIBLE);
                    etTelephone.setVisibility(View.GONE);

                } else if (spUsers.getSelectedItem().toString().equals(Constants.USER_PATIENT)) {

                    spFullNameDoctor.setVisibility(View.VISIBLE);
                    etSpecialty.setVisibility(View.GONE);
                    etTelephone.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnRegister.setOnClickListener(v -> {
            if(spUsers.getSelectedItemPosition() == 0){
                Toast.makeText(this, getString(R.string.register_select_option), Toast.LENGTH_SHORT).show();
            } else if (spUsers.getSelectedItem().toString().equals(Constants.USER_DOCTOR)) {
                if(validateDoctor() ) {
                    createDoctor();
                }

            } else if (spUsers.getSelectedItem().toString().equals(Constants.USER_PATIENT) ) {
                if(validatePatient()) {
                    createPatient();
                }
            }
        });
    }



    private void createPatient() {
        String dniDoc = doctors.get(spFullNameDoctor.getSelectedItemPosition() - 1).getDni();
        Patient patient = new Patient(etDni.getText().toString(), etName.getText().toString(), etLastName.getText().toString(), etTelephone.getText().toString(),dniDoc);
        Database.createPatient(this, patient);

    }

    private boolean validatePatient() {
        if (!Utils.validarDNI(etDni)) {
            Toast.makeText(this, getString(R.string.register_dni_invalid), Toast.LENGTH_SHORT).show();
            return false;
        } else if (Utils.isEmpty(etName) || Utils.isEmpty(etLastName)) {
            Toast.makeText(this, getString(R.string.register_incomlpete_data), Toast.LENGTH_SHORT).show();
            return false;
        }else if (!Utils.validarTelefono(etTelephone)) {
            Toast.makeText(this, getString(R.string.register_wrong_phone), Toast.LENGTH_SHORT).show();
            return false;
        } else  if(spFullNameDoctor.getSelectedItemPosition() == 0){
            Toast.makeText(this, getString(R.string.register_no_doctor_data), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateDoctor() {
         if (!Utils.validarDNI(etDni)) {
            Toast.makeText(this, getString(R.string.register_dni_invalid), Toast.LENGTH_SHORT).show();
            return false;
        } else if (Utils.isEmpty(etName) || Utils.isEmpty(etLastName) || Utils.isEmpty((etSpecialty))) {
            Toast.makeText(this, getString(R.string.register_incomlpete_data), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void createDoctor() {
        Doctor doctor = new Doctor(etDni.getText().toString(), etName.getText().toString(), etLastName.getText().toString(), etSpecialty.getText().toString());
        Database.createDoctor(this, doctor);
    }
}