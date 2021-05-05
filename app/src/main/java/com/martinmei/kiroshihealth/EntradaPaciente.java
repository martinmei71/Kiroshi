package com.martinmei.kiroshihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.martinmei.kiroshihealth.pacientes.PacientesActivity;

public class EntradaPaciente extends AppCompatActivity {

    private EditText dniP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_paciente);

        dniP = findViewById(R.id.et_dnip);
    }

    public void onClickEntrar(View v) {

        String nombre = null, dni, dniDoc = null;

        AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(this, "kiroshi", null, 1);

        SQLiteDatabase db = adminHelper.getWritableDatabase();

        dni = dniP.getText().toString();


        if (dni.length() == 0) {
            Toast.makeText(this, "Tienes datos obligatorios que rellenar", Toast.LENGTH_SHORT).show();
        } else {

            if (!Utils.validarDNI(dni)) {
                Toast.makeText(this, "El DNI NO ES VÁLIDO", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues valores = new ContentValues();

                String[] columnas = {"dniP", "nombre", "apellidos", "telefono", "dniDoc"};

                // Filtros de la consulta para aplicar en la clausula WHERE "codigo" = codigo
                String seleccion = "dniP" + " = ?";
                String[] condicion = {dni};

                //El resultado de la consulta de select se guarda en el Cursor
                Cursor cursorbuscar = db.query("pacientes", columnas, seleccion, condicion, null, null, null);

                while (cursorbuscar.moveToNext()) {
                    dni = cursorbuscar.getString(1);
                    nombre = cursorbuscar.getString(2);
                    dniDoc = cursorbuscar.getString(5);
                }
                cursorbuscar.close();
            }

            if(dniDoc.length()==0){

                Toast.makeText(this, "NO TE HEMOS ENCONTRADO", Toast.LENGTH_SHORT).show();

            }else{

                Intent intent = new Intent(EntradaPaciente.this, PacientesActivity.class);
                intent.putExtra("dniP", dni);
                intent.putExtra("nombre", nombre);
                intent.putExtra("dniDoc", dniDoc);
                startActivity(intent);

            }


        }

        //Cierro conexión con base de datos
        db.close();



    }
}
