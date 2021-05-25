package com.martinmei.kiroshihealth.activities.patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.activities.BaseActivity;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Patient;

public class PatientsMenuActivity extends BaseActivity {

   private Toolbar toolbar;
   private TextView tvToolbar;
   private TextView tvTitle;
   private Patient patient;
   private ImageView ivLogo;


    public static Intent newIntent(Context context, Patient patient){
        Intent intent = new Intent(context,PatientsMenuActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, patient);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_menu);
        initBindings();
        initData();
        initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
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
                goToMyData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initData() {
        patient = getIntent().getParcelableExtra(Intent.EXTRA_INTENT);
    }

    private void updateData() {
        patient = Database.getPatient(this, patient.getDni());
    }

    private  void initBindings(){
        toolbar = findViewById(R.id.toolbar);
        tvToolbar = findViewById(R.id.toolbar_title);
        tvTitle = findViewById(R.id.tv_title_pm);
        ivLogo = findViewById(R.id.image_logo);
    }

    private void initUI(){
        tvTitle.setText("¡ Hola "+patient.getName()+" !");
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivLogo.setVisibility(View.GONE);
        tvToolbar.setText(getString(R.string.menu_patient_title));
    }

    public void goToCreateAppointment(View view){
       startActivity(CreateAppointmentActivity.newIntent(this, patient));
    }

    public void goToMyData(){
        startActivity(MyPatientDataActivity.newIntent(this, patient));
    }

    public void goToPrescriptionList(View view){
        startActivity(PatientPrescriptionsListActivity.newIntent(this, patient));
    }

    public void goToAppointmentList(View view){
        startActivity(AppointmentPatientListActivity.newIntent(this, patient));
    }
    public void goToDoctorDataList(View view){
        startActivity(DoctorDataPatientActivity.newIntent(this, patient));
    }

}