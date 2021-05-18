package com.martinmei.kiroshihealth.activities.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Patient;
import com.martinmei.kiroshihealth.models.Prescription;

import java.util.List;

public class PatientPrescriptionsListActivity extends AppCompatActivity implements OnPrescriptionListener {

    private TextView tvNoData;
    private TextView tvToolbar;
    private RecyclerView recyclerviewPrescriptions;
    private Toolbar toolbar;
    private List <Prescription> prescriptions;
    private PrescriptionAdapter prescriptionAdapter;
    private Patient patient;

    public static Intent newIntent(Context context, Patient patient){
        Intent intent = new Intent(context, PatientPrescriptionsListActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, patient);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prescriptions_list);
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
        tvToolbar.setText(getString(R.string.list_patient_title));
    }
    private void initBindings(){
        recyclerviewPrescriptions = findViewById(R.id.rw_prescriptions_ppla);
        tvNoData = findViewById(R.id.tv_no_data_ppla);
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
    }

    private void initData(){
        patient = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
        prescriptions = Database.getPatientPrescriptions(this,patient.getDni());
    }

    private void initAdapter(){
        prescriptionAdapter = new PrescriptionAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerviewPrescriptions.setLayoutManager(mLayoutManager);
        recyclerviewPrescriptions.setItemAnimator(new DefaultItemAnimator());
        recyclerviewPrescriptions.setAdapter(prescriptionAdapter);
    }

    private void showMessageNoDataIfIsNeeded(){
        if (prescriptions.isEmpty()){
            tvNoData.setVisibility(View.VISIBLE);
        }else {
            tvNoData.setVisibility(View.GONE);
        }
    }

    public void goToPrescriptionDetail(Prescription prescription){
        startActivity(DetailPrescriptionActivity.newIntent(this, prescription));
    }

    @Override
    public void onPrescriptionItemClick(Prescription prescription) {
       goToPrescriptionDetail(prescription);
    }

    private void updateUI(){
        List<Prescription> prescriptions = Database.getPatientPrescriptions(this,patient.getDni());
        prescriptionAdapter.updatePatientList(prescriptions);
        prescriptionAdapter.notifyDataSetChanged();
        showMessageNoDataIfIsNeeded();
    }
}