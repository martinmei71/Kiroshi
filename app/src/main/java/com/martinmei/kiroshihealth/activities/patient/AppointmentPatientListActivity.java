package com.martinmei.kiroshihealth.activities.patient;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.activities.patient.adapter.AppointmentPatientAdapter;
import com.martinmei.kiroshihealth.activities.patient.adapter.OnAppointmentListener;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Appointment;
import com.martinmei.kiroshihealth.models.Patient;

import java.util.List;

public class AppointmentPatientListActivity extends BaseActivity implements OnAppointmentListener {

    private RecyclerView recyclerviewAppointments;

    private TextView tvNoData;
    private Toolbar toolbar;
    private TextView tvToolbar;
    private List<Appointment> appointmentList;
    private Patient patient;
    private AppointmentPatientAdapter appointmentAdapter;

    public static Intent newIntent(Context context, Patient patient){
        Intent intent = new Intent(context, AppointmentPatientListActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, patient);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_patient_list);
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

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbar.setText(getString(R.string.list_appointment_title));
    }

    public void initBindings(){
        recyclerviewAppointments = findViewById(R.id.recycled_view_appointment_patient);
        tvNoData = findViewById(R.id.tv_appointment_list_no_data);
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
    }

    private void initData(){
        patient = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        appointmentList = Database.getAppointmentPatient(this, patient);
    }

    public void initAdapter(){
        appointmentAdapter = new AppointmentPatientAdapter( this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewAppointments.setLayoutManager(mLayoutManager);
        recyclerviewAppointments.setAdapter(appointmentAdapter);
    }

    private void showMessageNoDataIfIsNeeded(){
        if (appointmentList.isEmpty()){
            tvNoData.setVisibility(View.VISIBLE);
        }else {
            tvNoData.setVisibility(View.GONE);
        }
    }

    public void goToAppointmentDetail(Appointment appointment){
        startActivity(AppointmentDetailPatientActivity.newIntent(this, appointment));
    }

    private void updateUI(){
        List<Appointment> appointments = Database.getAppointmentPatient(this, patient);
        appointmentAdapter.updateAppointmentList(appointments);
        appointmentAdapter.notifyDataSetChanged();
        showMessageNoDataIfIsNeeded();
    }

    @Override
    public void onAppointmentItemClick(Appointment appointment) {
        goToAppointmentDetail(appointment);
    }
}