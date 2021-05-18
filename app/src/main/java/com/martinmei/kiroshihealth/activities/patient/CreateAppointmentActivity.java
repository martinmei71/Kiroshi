package com.martinmei.kiroshihealth.activities.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Appointment;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;

public class CreateAppointmentActivity extends AppCompatActivity {

    public static Intent newIntent(Context context, Patient patient){
        Intent intent = new Intent(context,CreateAppointmentActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, patient);
        return intent;
    }

    private EditText etDate;
    private EditText etSubject;
    private Toolbar toolbar;
    private TextView tvToolbar;
    private TextView tvDni;
    private TextView tvDoctorFullName;
    private Patient patient;
    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
        initBindings();
        initData();
        initToolbar();
        initUI();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.my_data_doctor_title));
    }

    private void initBindings(){
        etDate = findViewById(R.id.et_date_caa);
        etSubject = findViewById(R.id.et_subject_caa);
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
        tvDni = findViewById(R.id.tv_dni_caa);
        tvDoctorFullName = findViewById(R.id.tv_doctor_name_caa);
    }

    private void initData(){
        patient = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        doctor = Database.getDoctor(this, patient.getDniDoc());
    }

    private void initUI(){
        tvDni.setText(patient.getDni());
        tvDoctorFullName.setText(doctor.getName()+" "+doctor.getLastName());
    }

    public void onClickCreateAppointment(View view){
        Appointment appointment = new Appointment(etSubject.getText().toString(),etDate.getText().toString(),patient.getDni(),patient.getDniDoc());
        Database.createAppointment(this,appointment);
    }

}