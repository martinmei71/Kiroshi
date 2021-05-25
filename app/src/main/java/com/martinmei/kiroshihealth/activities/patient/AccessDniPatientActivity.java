package com.martinmei.kiroshihealth.activities.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Patient;

public class AccessDniPatientActivity extends BaseActivity {

    private EditText dniP;
    private Toolbar toolbar;
    private TextView tvToolbar;
    private Patient patient;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,AccessDniPatientActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_dni_paciente);
        initBindings();
        initToolbar();
        initListener();
    }

    private void initBindings() {
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
        dniP = findViewById(R.id.et_dnip);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.login_patient_title));
    }

    public void onClickEnter(View v) {
        String dni = dniP.getText().toString();
        if (Database.hasPatient(this, dni)) {
            patient = Database.getPatient(this, dni);
            goToPatientMenu(patient);
        } else {
            showErrorMessage(getString(R.string.login_no_patient_data));
        }
    }
    private void initListener(){
        dniP.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                patient = Database.getPatient(this, dniP.getText().toString().toUpperCase());
                goToPatientMenu(patient);
                return true;
            }
            return false;
        });
    }

    private void goToPatientMenu(Patient patient){
        Intent intent = PatientsMenuActivity.newIntent(this,patient);
        startActivity(intent);
        finish();
    }

}
