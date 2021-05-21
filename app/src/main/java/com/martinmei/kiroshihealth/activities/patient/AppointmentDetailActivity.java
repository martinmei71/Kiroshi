package com.martinmei.kiroshihealth.activities.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Appointment;

public class AppointmentDetailActivity extends BaseActivity {

    private TextView tvCode;
    private TextView tvDate;
    private TextView tvSubject;
    private TextView tvPatientData;
    private TextView tvToolbar;
    private Toolbar toolbar;
    private Appointment appointment;

    public static Intent newIntent(Context context, Appointment appointment){
        Intent intent = new Intent(context, AppointmentDetailActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, appointment);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);
        initBindings();
        initData();
        initToolbar();
        initUI();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.list_appointment_title));
    }

    private void initBindings(){
        tvCode = findViewById(R.id.tv_code_daa);
        tvDate = findViewById(R.id.tv_date_daa);
        tvSubject = findViewById(R.id.tv_subject_daa);
        tvPatientData = findViewById(R.id.tv_patient_daa);
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
    }

    private void initData(){
        appointment = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
    }

    private void initUI(){
        tvCode.setText(appointment.getCodMeet().toString());
        tvSubject.setText(appointment.getSubject());
        tvDate.setText(appointment.getDate());
        tvPatientData.setText(appointment.getDniPatient());
    }

    public void onClickDeleteButton(View view){
        if(!Database.deleteAppointment(this,appointment.getCodMeet())){
            showErrorMessage(getString(R.string.list_appointment_failure_deleted));
        } else {
            showMessage(getString(R.string.text_saved));
            finish();
        }
    }

}