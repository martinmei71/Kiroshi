package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.martinmei.kiroshihealth.ddbb.AdminSQLiteOpenHelper;
import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.extra.Constants;
import com.martinmei.kiroshihealth.extra.Utils;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;

public class RegisterActivity extends AppCompatActivity {

    private Spinner spUsers;
    private EditText etDniDoc;
    private EditText etTelephone;
    private EditText etName;
    private EditText etLastName;
    private EditText etDni;
    private EditText etSpecialty;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initBinding();

        initSpinnerAdapter();

        setUpListeners();

        initUI();

    }


    private void initBinding() {
        spUsers = findViewById(R.id.sp_selector_user_rua);
        etDniDoc = findViewById(R.id.et_dni_doctor_rua);
        etTelephone = findViewById(R.id.et_telephone_rua);
        etSpecialty = findViewById(R.id.et_especiality_rua);
        etName = findViewById(R.id.et_name_rua);
        etLastName = findViewById(R.id.et_last_name_rua);
        etDni = findViewById(R.id.et_dni_rua);
        btnRegister = findViewById(R.id.btn_registrarme_rua);
    }

    private void initUI() {
        etDniDoc.setVisibility(View.GONE);
        etSpecialty.setVisibility(View.GONE);
        etTelephone.setVisibility(View.GONE);
    }

    private void initSpinnerAdapter() {
        ArrayAdapter<String> spinnerUserTypes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.users_array));
        spUsers.setAdapter(spinnerUserTypes);
    }

    private void setUpListeners() {
        spUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spUsers.getSelectedItem().toString().equals(Constants.USER_DOCTOR)) {

                    etDniDoc.setVisibility(View.GONE);
                    etSpecialty.setVisibility(View.VISIBLE);
                    etTelephone.setVisibility(View.GONE);

                } else if (spUsers.getSelectedItem().toString().equals(Constants.USER_PATIENT)) {

                    etDniDoc.setVisibility(View.VISIBLE);
                    etSpecialty.setVisibility(View.GONE);
                    etTelephone.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRegister.setOnClickListener(v -> {
            if(spUsers.getSelectedItemPosition() == 0){
                Toast.makeText(this, getString(R.string.register_select_option), Toast.LENGTH_SHORT).show();
            } else if (spUsers.getSelectedItem().toString().equals(Constants.USER_DOCTOR)) {
                if(validateDoctor() ) {
                    createDoctor();
                }

            } else if (spUsers.getSelectedItem().toString().equals(Constants.USER_PATIENT) ) {
                if(validatePatient()) {
                    createPatient();
                }
            }
        });
    }

    private void createPatient() {

        Patient patient = new Patient(etDni.getText().toString(), etName.getText().toString(), etLastName.getText().toString(), etTelephone.getText().toString(), etDniDoc.getText().toString());
        AdminSQLiteOpenHelper.createPatient(this, patient);

    }

    private boolean validatePatient() {
        if (!Utils.validarDNI(etDni) || !Utils.validarDNI(etDniDoc)) {
            Toast.makeText(this, getString(R.string.register_dni_invalid), Toast.LENGTH_SHORT).show();
            return false;
        } else if (Utils.isEmpty(etName) || Utils.isEmpty(etLastName)) {
            Toast.makeText(this, getString(R.string.register_incomlpete_data), Toast.LENGTH_SHORT).show();
            return false;
        }else if (!Utils.validarTelefono(etTelephone)) {
            Toast.makeText(this, getString(R.string.register_wrong_phone), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateDoctor() {
         if (!Utils.validarDNI(etDni)) {
            Toast.makeText(this, getString(R.string.register_dni_invalid), Toast.LENGTH_SHORT).show();
            return false;
        } else if (Utils.isEmpty(etName) || Utils.isEmpty(etLastName) || Utils.isEmpty((etSpecialty))) {
            Toast.makeText(this, getString(R.string.register_incomlpete_data), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void createDoctor() {
        Doctor doctor = new Doctor(etDni.getText().toString(), etName.getText().toString(), etLastName.getText().toString(), etSpecialty.getText().toString());

        AdminSQLiteOpenHelper.createDoctor(this, doctor);
    }
}