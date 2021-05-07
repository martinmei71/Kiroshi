package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.martinmei.kiroshihealth.ddbb.AdminSQLiteOpenHelper;
import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.extra.Utils;

public class LoginDoctorActivity extends AppCompatActivity {


    private EditText dniDoc;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doctor);
        dniDoc = findViewById(R.id.sp_dni_doctor_rua);
    }


    public void onClickLoginDoctor(View v){


            String nombre = null, dni;

            AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(this);

            SQLiteDatabase db = adminHelper.getWritableDatabase();

            dni = dniDoc.getText().toString();


            if (dni.length() == 0) {
                Toast.makeText(this, getString(R.string.register_incomlpete_data), Toast.LENGTH_SHORT).show();
            } else {

                if (!Utils.validarDNI(dniDoc)) {
                    Toast.makeText(this, getString(R.string.register_dni_invalid), Toast.LENGTH_SHORT).show();
                } else {

                }
                  // Cursor cursor = AdminSQLiteOpenHelper.searchDoctor(this,dni);


                if(dniDoc.length()==0){

                    Toast.makeText(this, getString(R.string.common_data_not_found), Toast.LENGTH_SHORT).show();

                }else{

                    Intent intent = new Intent(this, PatientsActivity.class); // <-- TODO Cambiar Pacientes Activity to Doctores Activity
                    intent.putExtra("dniDoc", dni);
                    intent.putExtra("nombre", nombre);

                    startActivity(intent);

                }


            }

            //Cierro conexión con base de datos
            db.close();
    }

}