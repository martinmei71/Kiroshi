package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.martinmei.kiroshihealth.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickGoToNewUser(View view){
        Intent intent = PrescriptionManagementActivity.newIntent(this);
    }

    public void onClickGoToLoginDoctor(View view){
        Intent intent = AccessDniDoctorActivity.newIntent(this);
    }

    public void onClickGoToLoginPaciente(View view){
        Intent intent = AccessDniPatientActivity.newIntent(this);
    }


}