package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;

public class PatientsMenuActivity extends AppCompatActivity {

   private Toolbar toolbar;
   private TextView tvToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        initBindings();

        initData();

        initToolbar();

    }

    private void initData() {
        tvToolbar.setText(getString(R.string.menu_patient_title));
    }

    private  void initBindings(){
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.list_patient_title));
    }

    public void goToAppointmentManagementMenu(View view){


    }

}