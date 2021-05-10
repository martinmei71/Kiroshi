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
    private EditText etCod;
    private EditText etName;
    private EditText etDescription;
    private TextView tvToolbarTitle;
    private Button buttonApply;
    private static Prescription prescription;
    private static String dniPatient;
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

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spOperations.getSelectedItemPosition()==0){
                     // Toast.makeText(this,getString(R.string.register_select_option), Toast.LENGTH_SHORT).show();
                } else if(spOperations.getSelectedItemPosition()==1){
                        createPrescription();
                } else if (spOperations.getSelectedItemPosition()==2){
                    deletePrescription();
                }
            }
        });
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
    etCod = findViewById(R.id.et_cod_prescription_pma);
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
        fullNamePatientList.add(getString(R.string.create_prescription_option));
        for (Patient patient : patients) {
            fullNamePatientList.add(patient.getName() + " " + patient.getLastName());
        }

        prescriptions = Database.getPrescriptions(this);
        fullPrescriptionList = new ArrayList<>();
        fullPrescriptionList.add(getString(R.string.create_prescription_option));
        for (Prescription prescription : prescriptions) {
            fullPrescriptionList.add(prescription.getName() + " " + prescription.getCodPrescription()+" DNI: " + prescription.getDniPatient());
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
                    etCod.setVisibility(View.GONE);
                    etName.setVisibility(View.GONE);
                    etDescription.setVisibility(View.GONE);
                    spPrescriptions.setVisibility(View.VISIBLE);
                    buttonApply.setText(getString(R.string.text_delete));
                    spPatients.setVisibility(View.GONE);

                } else if (spOperations.getSelectedItem().toString().equals(Constants.OPERATIONS_NUEVO)) {
                    spPatients.setVisibility(View.VISIBLE);
                    etCod.setVisibility(View.GONE);
                    etName.setVisibility(View.VISIBLE);
                    etDescription.setVisibility(View.VISIBLE);
                    spPrescriptions.setVisibility(View.GONE);
                    buttonApply.setText(getString(R.string.text_new));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });}

    private void createPrescription(){
        String dniPatient= patients.get(spPatients.getSelectedItemPosition() - 1).getDni();
        Integer cod = Integer.parseInt(etCod.getText().toString());
        prescription = new Prescription(cod,etName.getText().toString(), etDescription.getText().toString(),dniPatient);
        Database.createPrescription(this, prescription);
    }

    private void deletePrescription(){

        
    }
}