package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.martinmei.kiroshihealth.ddbb.AdminSQLiteOpenHelper;
import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.extra.Utils;

public class EntrarDoctorActivity extends AppCompatActivity {


    private EditText dniDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_doctor);
        dniDoc = findViewById(R.id.et_dni_doctor_rua);
    }


    public void onClicEntrarDoctor(View v){


            String nombre = null, dni;

            AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(this);

            SQLiteDatabase db = adminHelper.getWritableDatabase();

            dni = dniDoc.getText().toString();


            if (dni.length() == 0) {
                Toast.makeText(this, "Tienes datos obligatorios que rellenar", Toast.LENGTH_SHORT).show();
            } else {

                if (!Utils.validarDNI(dniDoc)) {
                    Toast.makeText(this, "El DNI NO ES VÁLIDO", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues valores = new ContentValues();

                    String[] columnas = {"dniDoc", "nombre", "apellidos", "especialidad"};

                    // Filtros de la consulta para aplicar en la clausula WHERE "codigo" = codigo
                    String seleccion = "dniDoc" + " = ?";
                    String[] condicion = {dni};

                    //El resultado de la consulta de select se guarda en el Cursor
                    Cursor cursorbuscar = db.query("doctores", columnas, seleccion, condicion, null, null, null);

                    while (cursorbuscar.moveToNext()) {
                        dni = cursorbuscar.getString(1);
                        nombre = cursorbuscar.getString(2);
                    }
                    cursorbuscar.close();
                }

                if(dniDoc.length()==0){

                    Toast.makeText(this, "NO TE HEMOS ENCONTRADO", Toast.LENGTH_SHORT).show();

                }else{

                    Intent intent = new Intent(EntrarDoctorActivity.this, PacientesActivity.class); // <-- TODO Cambiar Pacientes Activity to Doctores Activity
                    intent.putExtra("dniDoc", dni);
                    intent.putExtra("nombre", nombre);

                    startActivity(intent);

                }


            }

            //Cierro conexión con base de datos
            db.close();
    }

}