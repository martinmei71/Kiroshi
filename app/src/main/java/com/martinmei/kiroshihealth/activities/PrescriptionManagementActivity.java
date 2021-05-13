package com.martinmei.kiroshihealth.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.extra.Constants;
import com.martinmei.kiroshihealth.models.Patient;
import com.martinmei.kiroshihealth.models.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionManagementActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spOperations;
    private Spinner spPatients;
    private Spinner spPrescriptions;
    private EditText etName;
    private EditText etDescription;
    private TextView tvToolbarTitle;
    private Button buttonApply;
    private Prescription prescription;
    private List<Patient> patients;
    private List <String> fullNamePatientList;
    private List<Prescription> prescriptions;
    private List <String> fullPrescriptionList;


    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,PrescriptionManagementActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_management);

        initBindings();

        initToolbar();

        initData();

        initSpinnerAdapter();

        setUpListeners();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initBindings(){
    spOperations = findViewById(R.id.sp_operation_pma);
    etName = findViewById(R.id.et_name_pma);
    etDescription = findViewById(R.id.et_description_pma);
    spPatients = findViewById(R.id.sp_select_patient_pma);
    buttonApply = findViewById(R.id.btn_apply_pma);
    spPrescriptions = findViewById(R.id.sp_select_prescription_pma);
    toolbar = findViewById(R.id.toolbar);
    tvToolbarTitle = findViewById(R.id.toolbar_title);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvToolbarTitle.setText(getString(R.string.create_prescription_toolbar_text));
    }

    private void initData(){
        patients = Database.getPatients(this);
        fullNamePatientList = new ArrayList<>();
        fullNamePatientList.add(getString(R.string.create_prescription_patient_sp_option));
        for (Patient patient : patients) {
            fullNamePatientList.add(patient.getName() + " " + patient.getLastName());
        }

        prescriptions = Database.getPrescriptions(this);
        fullPrescriptionList = new ArrayList<>();
        fullPrescriptionList.add(getString(R.string.create_prescription_prescription_sp_option));
        for (Prescription prescription : prescriptions) {
            fullPrescriptionList.add(prescription.getName() + ", con codigo " + prescription.getCodPrescription()+" a " + prescription.getDniPatient());
        }
    }

    private void initSpinnerAdapter() {
        ArrayAdapter<String> spinnerUserTypes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, fullNamePatientList);
        spPatients.setAdapter(spinnerUserTypes);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.operations_array));
        spOperations.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerPrescriptionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, fullPrescriptionList);
        spPrescriptions.setAdapter(spinnerPrescriptionsAdapter);
    }

    private void setUpListeners() {
        spOperations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spOperations.getSelectedItem().toString().equals(Constants.OPERATIONS_BORRAR)) {
                    spPatients.setVisibility(View.GONE);
                    etName.setVisibility(View.GONE);
                    etDescription.setVisibility(View.GONE);
                    spPrescriptions.setVisibility(View.VISIBLE);
                    buttonApply.setText(getString(R.string.text_delete));
                    spPatients.setVisibility(View.GONE);

                } else if (spOperations.getSelectedItem().toString().equals(Constants.OPERATIONS_NUEVO)) {
                    spPatients.setVisibility(View.VISIBLE);
                    etName.setVisibility(View.VISIBLE);
                    etDescription.setVisibility(View.VISIBLE);
                    spPrescriptions.setVisibility(View.GONE);
                    buttonApply.setText(getString(R.string.text_new));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        buttonApply.setOnClickListener(v -> {
            if(spOperations.getSelectedItemPosition()==0){
                Toast.makeText(PrescriptionManagementActivity.this, getString(R.string.register_select_option), Toast.LENGTH_SHORT).show();
            } else if(spOperations.getSelectedItemPosition()==1){
                createPrescription();
            } else if (spOperations.getSelectedItemPosition()==2){
                deletePrescription();
            }
        });

    }

    private void createPrescription(){
        String dniPatient= patients.get(spPatients.getSelectedItemPosition() - 1).getDni();
        prescription = new Prescription(etName.getText().toString(), etDescription.getText().toString(),dniPatient);
        Database.createPrescription(this, prescription);

    }

    private void deletePrescription(){
        Integer cod= prescriptions.get(spPrescriptions.getSelectedItemPosition() - 1).getCodPrescription();
        if(Database.deletePrescription(this, cod)){
            Toast.makeText(this,getString(R.string.create_prescription_delete_text), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getString(R.string.create_prescription_failure_delete_text), Toast.LENGTH_SHORT).show();
        }
    }
}