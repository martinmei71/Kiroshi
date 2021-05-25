package com.martinmei.kiroshihealth.activities.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.martinmei.kiroshihealth.BuildConfig;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Doctor;

public class AccessDniDoctorActivity extends BaseActivity {

    private EditText etDniDoc;
    private Toolbar toolbar;
    private TextView tvToolbar;
    private Doctor doctor;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,AccessDniDoctorActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_dni_doctor);
        initBindings();
        initToolbar();
        initListener();
    }

    private void initBindings(){
        etDniDoc = findViewById(R.id.et_dniDoc_ED);
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
    }

    public void onClickLoginDoctor(View v){
        String dni = etDniDoc.getText().toString().toUpperCase();
        if (Database.hasDoctor(this, dni)) {
            doctor = Database.getDoctor(this, dni);
            goToDoctorMenu(doctor);
        } else {
            showErrorMessage(getString(R.string.login_no_doctor_data));
        }
    }

    public void goToDoctorMenu(Doctor doctor){
        startActivity(DoctorMenuActivity.newIntent(this, doctor));
        finish();
    }

    private void initListener(){
        etDniDoc.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                doctor = Database.getDoctor(this, etDniDoc.getText().toString().toUpperCase());
                goToDoctorMenu(doctor);
                return true;
            }
            return false;
        });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.login_doctor_title));
    }

}