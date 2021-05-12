package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.martinmei.kiroshihealth.BuildConfig;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Doctor;

public class AccessDniDoctorActivity extends AppCompatActivity {

    private EditText etDniDoc;
    private Toolbar toolbar;
    private TextView tvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_dni_doctor);
        initBindings();
        initToolbar();
    }

    private void initBindings(){
        etDniDoc = findViewById(R.id.et_dniDoc_ED);
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
        if(BuildConfig.DEBUG) {
            etDniDoc.setText("49333972F");
        }
    }

    public void onClickLoginDoctor(View v){
        String dni = etDniDoc.getText().toString();
        if(Database.hasDoctor(this, dni)) {
            Doctor doctor = Database.getDoctor(this, dni);
            goToDoctorMenu(doctor);
        }else{
            Toast.makeText(this, getString(R.string.login_no_doctor_data), Toast.LENGTH_SHORT).show();
        }
    }

    public void goToDoctorMenu(Doctor doctor){
        startActivity(DoctorMenuActivity.newIntent(this, doctor));

    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.login_doctor_title));
    }

}