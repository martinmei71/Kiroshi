package com.martinmei.kiroshihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewUserActivity extends AppCompatActivity {

    private Spinner selector_usuarios;
    private EditText etDniDoc;
    private EditText etTelefono;
    public EditText etNombre;
    private EditText etApellido;
    private EditText etdni;
    private EditText etEspecialidad;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


        // REFERENCIAS XML
        selector_usuarios = findViewById(R.id.spinner_selector_user);
        etDniDoc = findViewById(R.id.et_dni_doctor);
        etTelefono = findViewById(R.id.et_telefono);
        etEspecialidad = findViewById(R.id.et_especialidad);

        //WORKING SET
        ArrayAdapter<String> spinnerUserTypes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.shoes));

        selector_usuarios.setAdapter(spinnerUserTypes);

        if (selector_usuarios.getSelectedItem().toString() == "Doctor") {
            etDniDoc.setVisibility(View.GONE);
            etEspecialidad.setVisibility(View.VISIBLE);
            etTelefono.setVisibility(View.GONE);
        }else if(selector_usuarios.getSelectedItem().toString() == "Paciente"){
            etDniDoc.setVisibility(View.VISIBLE);
            etEspecialidad.setVisibility(View.GONE);
            etTelefono.setVisibility(View.VISIBLE);
        }

    }

    private void regitroPaciente(View view) {

        AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(this, "kiroshi", null, 1);

        SQLiteDatabase db = adminHelper.getWritableDatabase();


        String dniP = etdni.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String telefono = etTelefono.getText().toString();
        String dniDoc = etDniDoc.getText().toString();


        if (dniP.length() == 0 || nombre.length() == 0 || telefono.length() == 0 || apellido.length() == 0 || dniDoc.length() == 0) {
            Toast.makeText(this, "Tienes datos obligatorios que rellenar", Toast.LENGTH_SHORT).show();
        } else {

            if (!Utils.validarDNI(dniP) || !Utils.validarTelefono(telefono) || !Utils.validarDNI(dniDoc)) {
                Toast.makeText(this, "Los datos no son válidos ", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues valores = new ContentValues();

                valores.put("dniP", dniP );
                valores.put("nombre", nombre);
                valores.put("apellidos", apellido);
                valores.put("telefono", telefono);
                valores.put("dniDoc", dniDoc);


                db.insert("pacientes", null, valores);


                //Mensaje informativo
                Toast.makeText(this, "Paciente "+nombre+" ha sido registrado correctamente", Toast.LENGTH_SHORT).show();

                //Cerramos la activity
                finish();
            }
        }


    }

    private void regitroDoctor(View view) {

        AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(this, "kiroshi", null, 1);

        SQLiteDatabase db = adminHelper.getWritableDatabase();


        String especialidad = etEspecialidad.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String telefono = etTelefono.getText().toString();
        String dniDoc = etDniDoc.getText().toString();


        if (especialidad.length() == 0 || nombre.length() == 0  || apellido.length() == 0 || dniDoc.length() == 0) {
            Toast.makeText(this, "Tienes datos obligatorios que rellenar", Toast.LENGTH_SHORT).show();
        } else {

            if ( !Utils.validarDNI(dniDoc)) {
                Toast.makeText(this, "Los datos no son válidos ", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues valores = new ContentValues();

                valores.put("dniDoc", dniDoc );
                valores.put("nombre", nombre);
                valores.put("apellidos", apellido);
                valores.put("especialidad", especialidad);



                db.insert("doctores", null, valores);


                //Mensaje informativo
                Toast.makeText(this, "Doctor "+nombre+" ha sido registrado correctamente", Toast.LENGTH_SHORT).show();

                //Cerramos la activity
                finish();
            }
        }


    }
}