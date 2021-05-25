package com.martinmei.kiroshihealth.activities.doctor;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Doctor;

public class DoctorMenuActivity extends BaseActivity {

   private TextView tvToolbar;
   private TextView titleTv;
   private Doctor doctor;
   private Toolbar toolbar;
   private ImageView ivLogo;

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

    @Override
    protected void onResume() {
        super.onResume();
        doctor = Database.getDoctor(this,doctor.getDni());
        initUI();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_my_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_data_button:
                onClickShowMyDoctorData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initBindings(){
        titleTv = findViewById(R.id.textview_title_dma);
        tvToolbar = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        ivLogo = findViewById(R.id.image_logo);
    }

    private void initData() {
        doctor = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivLogo.setVisibility(View.GONE);
        tvToolbar.setText(getString(R.string.menu_doctor_title));
    }

    public void onClickManagementPrescriptions(View view){
        Intent intent = PrescriptionManagementActivity.newIntent(this);
        startActivity(intent);
    }
    public void onClickShowPatientList(View view){
        Intent intent = PatientsListActivity.newIntent(this, doctor);
        startActivity(intent);
    }
    public void onClickShowMyDoctorData(){
        Intent intent = MyDataDoctorActivity.newIntent(this,doctor);
        startActivity(intent);
    }
    public void onClickAppointmentList(View view){
        Intent intent = AppointmentListDoctorActivity.newIntent(this,doctor);
        startActivity(intent);
    }

    private void initUI(){
        titleTv.setText("¡ Hola de nuevo "+doctor.getName()+" !");
    }

}