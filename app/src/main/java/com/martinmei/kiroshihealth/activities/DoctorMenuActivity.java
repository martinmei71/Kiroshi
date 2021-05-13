package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Doctor;

public class DoctorMenuActivity extends AppCompatActivity {

   private TextView tvToolbar;
   private TextView titleTv;
   private Doctor doctor;
   private Toolbar toolbar;



    public static Intent newIntent(Context context, Doctor doctor){
        Intent intent = new Intent(context,DoctorMenuActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, doctor);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_menu);
        initBindings();
        initData();
        initToolbar();
        initUI();
    }

    private void initBindings(){
        titleTv = findViewById(R.id.textview_title_dma);
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
    }

    private void initData() {
        doctor = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.menu_doctor_title));
    }

    public void onClickManagementPrescriptions(View view){
        Intent intent = PrescriptionManagementActivity.newIntent(this);
    }
    public void onClickShowPatientList(View view){
        Intent intent = PatientsListActivity.newIntent(this);
    }
    public void onClickShowMyDoctorData(View view){
        Intent intent = MyDataDoctorActivity.newIntent(this,doctor);
    }

    private void initUI(){
        titleTv.setText("¡ Hola de nuevo "+doctor.getName()+" !");
    }

}