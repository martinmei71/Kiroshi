package com.martinmei.kiroshihealth.activities.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.patient.AppointmentDetailActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Appointment;
import com.martinmei.kiroshihealth.models.Doctor;

import java.util.List;

public class ApointmentListDoctorActivity extends AppCompatActivity implements OnAppointmentDoctorListener {

    private RecyclerView recyclerviewAppointments;

    private Toolbar toolbar;
    private TextView tvToolbar;
    private TextView tvNoData;
    private List<Appointment> appointments;
    private Doctor doctor;
    private AppointmentDoctorAdapter appointmentDoctorAdapter;

    public static Intent newIntent(Context context, Doctor doctor){
        Intent intent = new Intent(context, ApointmentListDoctorActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, doctor);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apointment_list);
        initBindings();
        initData();
        initAdapter();
        initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
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
        tvToolbar.setText(getString(R.string.list_appointment_title));
    }
    private void initBindings(){
        recyclerviewAppointments = findViewById(R.id.recycled_view_appointments);
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
        tvNoData = findViewById(R.id.tv_appointment_list_no_data);
    }

    private void initData(){
        doctor = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        appointments = Database.getAppointmentDoctor(this, doctor);
    }

    private void initAdapter(){
        appointmentDoctorAdapter = new AppointmentDoctorAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewAppointments.setLayoutManager(mLayoutManager);
        recyclerviewAppointments.setAdapter(appointmentDoctorAdapter);
    }

    private void updateUI(){
        List<Appointment> appointments = Database.getAppointmentDoctor(this, doctor);
        appointmentDoctorAdapter.updateAppointmentList(appointments);
        appointmentDoctorAdapter.notifyDataSetChanged();
        showMessageNoDataIfIsNeeded();
    }

    private void showMessageNoDataIfIsNeeded(){
        if (appointments.isEmpty()){
            tvNoData.setVisibility(View.VISIBLE);
        }else {
            tvNoData.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAppointmentDoctorItemClick(Appointment appointment) {
        startActivity(AppointmentDetailActivity.newIntent(this, appointment));
    }
}