package com.martinmei.kiroshihealth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombreDB, SQLiteDatabase.CursorFactory factory, int version){
        super(context, nombreDB, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CONSULTA_CREAR_TABLA_DOCTORES =
                "CREATE TABLE doctores (" +
                        "dniDoc TEXT PRIMARY KEY," +
                        "nombre TEXT,"+
                        "apellidos TEXT," +
                        "especialidad TEXT)";
        db.execSQL(CONSULTA_CREAR_TABLA_DOCTORES);


        final String CONSULTA_CREAR_TABLA_PACIENTES =
                "CREATE TABLE pacientes (" +
                        "dniP TEXT PRIMARY KEY," +
                        "nombre TEXT," +
                        "apellidos TEXT," +
                        "telefono TEXT," +
                        "dniDoc TEXT," +
                        "FOREIGN KEY(dniDoc) REFERENCES doctores(dniDoc))";

        db.execSQL(CONSULTA_CREAR_TABLA_PACIENTES);

        final String CONSULTA_CREAR_TABLA_MEDICAMENTOS =
                "CREATE TABLE medicamentos (" +
                        "codMedicamento INTEGER PRIMARY KEY," +
                        "nombre TEXT," +
                        "descripcion TEXT," +
                        "dniP TEXT," +
                        "FOREIGN KEY(dniP) REFERENCES pacientes(dniP))";

        db.execSQL(CONSULTA_CREAR_TABLA_MEDICAMENTOS);

        final String CONSULTA_CREAR_TABLA_CITAS =
                "CREATE TABLE medicamentos (" +
                        "codCita INTEGER PRIMARY KEY," +
                        "asunto TEXT," +
                        "fecha TEXT," +
                        "dniD TEXT," +
                        "dniP TEXT," +
                        "FOREIGN KEY(dniP) REFERENCES pacientes(dniP)," +
                        "FOREIGN KEY(dniD) REFERENCES doctores(dniD))";

        db.execSQL(CONSULTA_CREAR_TABLA_CITAS);

    }

    //Método para hacer cambio de versión de base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //gestion de cache online y cambio de version de base de datos
        // -> de momento no nos interesa realizar acciones en este caso
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
