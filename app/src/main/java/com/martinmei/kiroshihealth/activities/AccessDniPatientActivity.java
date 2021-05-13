package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Patient;

public class AccessDniPatientActivity extends AppCompatActivity {

    private EditText dniP;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,AccessDniPatientActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_dni_paciente);

        dniP = findViewById(R.id.et_dnip);
    }

    public void onClickEntrar(View v) {
        String dni = dniP.getText().toString();
        if(Database.hasPatient(this, dni)) {
            Patient patient = Database.getPatient(this, dni);
            goToPatientMenu(patient);
        }else{
            Toast.makeText(this, getString(R.string.login_no_patient_data), Toast.LENGTH_SHORT).show();
        }
    }

    public void goToPatientMenu(Patient patient){
        Intent intent = new Intent(this, PatientsMenuActivity.class);
        startActivity(intent);
    }
}
