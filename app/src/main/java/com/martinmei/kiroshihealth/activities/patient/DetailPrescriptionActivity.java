package com.martinmei.kiroshihealth.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Prescription;

public class DetailPrescriptionActivity extends AppCompatActivity {

    private TextView tvCode;
    private TextView tvName;
    private TextView tvDescription;
    private TextView tvToolbar;
    private Toolbar toolbar;
    private Prescription prescription;

    public static Intent newIntent (Context context, Prescription prescription){
        Intent intent = new Intent(context,DetailPrescriptionActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, prescription);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_prescription);
        initBindings();
        initData();
        initUI();
    }

    private void initBindings(){
        tvCode = findViewById(R.id.tv_code_dpa);
        tvName = findViewById(R.id.tv_name_dpa);
        tvDescription = findViewById(R.id.tv_description_dpa);
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
    }

    private void initData(){
        prescription = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
    }

    private void initUI(){
        tvCode.setText(prescription.getCodPrescription());
        tvName.setText(prescription.getName());
        tvDescription.setText(prescription.getDescription());
    }

    public void onClickDeletePrescription(View view){
        Database.deletePrescription(this,prescription.getCodPrescription());
    }
}