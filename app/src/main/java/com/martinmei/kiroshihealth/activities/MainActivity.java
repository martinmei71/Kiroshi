package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.doctor.AccessDniDoctorActivity;
import com.martinmei.kiroshihealth.activities.patient.AccessDniPatientActivity;

public class MainActivity extends BaseActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickGoToNewUser(View view){
        startActivity(RegisterActivity.newIntent(this));
    }

    public void onClickGoToLoginDoctor(View view){
        startActivity(AccessDniDoctorActivity.newIntent(this));
    }

    public void onClickGoToLoginPaciente(View view){
        startActivity(AccessDniPatientActivity.newIntent(this));
    }


}