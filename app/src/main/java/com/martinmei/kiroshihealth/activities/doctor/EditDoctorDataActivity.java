package com.martinmei.kiroshihealth.activities.doctor;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.extra.Utils;
import com.martinmei.kiroshihealth.models.Doctor;

public class EditDoctorDataActivity extends BaseActivity {

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

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.common_edit_title));
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
            if(Utils.isEmpty(etLastName)) {
                etLastName.setError(getString(R.string.common_text_empty));
            }
            if(Utils.isEmpty(etName)){
                etName.setError(getString(R.string.common_text_empty));
            }
            if(Utils.isEmpty(etSpecialty)){
                etSpecialty.setError(getString(R.string.common_text_empty));
            }
            return false;
        }
        return true;
    }

    public void onClickUpdateDataDoctor(View view){
        if(validateDoctorForm()){
            Doctor doctorUpdated = new Doctor(doctor.getDni(),etName.getText().toString(),etLastName.getText().toString(),etSpecialty.getText().toString());
           if(Database.updateDoctor(this, doctorUpdated)){
               showMessage(getString(R.string.text_saved));
               finish();
           } else {
               showErrorMessage(getString(R.string.error_at_operation));
           }


        }
    }

}