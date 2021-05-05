package com.martinmei.kiroshihealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botonNuevo ;
    private Button botonDoctor ;
    private Button botonPaciente ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonNuevo=findViewById(R.id.button_new);
        botonDoctor=findViewById(R.id.button_doctor);
        botonPaciente=findViewById(R.id.button_paciente);

        botonNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewUser(v);
            }
        });

        botonPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginPaciente(v);
            }
        });

        botonDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginDoctor(v);
            }
        });


    }


    // METODO NUEVO USUARIO
    public void goToNewUser(View view){
        Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
        startActivity(intent);
    }

    // ARRANCAR DOCTOR
    public void goToLoginDoctor(View view){
        Intent intent = new Intent(MainActivity.this, EntrarDoctor.class);
        startActivity(intent);
    }
    // ARRANCAR PACIENTE
    public void goToLoginPaciente(View view){
        Intent intent = new Intent(MainActivity.this, EntradaPaciente.class);
        startActivity(intent);
    }


}