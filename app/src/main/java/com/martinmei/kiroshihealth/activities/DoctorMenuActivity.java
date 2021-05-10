package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.models.Doctor;

public class DoctorMenuActivity extends AppCompatActivity {


    private TextView titleTv;
    private Doctor doctor;


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
        initUI();
    }

    private void initBindings(){
        titleTv = findViewById(R.id.textview_title_dma);
    }

    private void initData() {
      doctor = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
    }

    public void onClickManagementPrescriptions(View view){
        startActivity(PrescriptionManagementActivity.newIntent(this));
    }

    private void initUI(){

        titleTv.setText("¡ Hola de nuevo "+doctor.getName()+" !");

    }

}