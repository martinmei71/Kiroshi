package com.martinmei.kiroshihealth.ddbb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.martinmei.kiroshihealth.extra.Constants;
import com.martinmei.kiroshihealth.models.Appointment;
import com.martinmei.kiroshihealth.models.Doctor;
import com.martinmei.kiroshihealth.models.Patient;
import com.martinmei.kiroshihealth.models.Prescription;

import java.util.ArrayList;
import java.util.List;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static String TABLE_DOCTOR = "doctor";
    private static String TABLE_PATIENT = "patient";
    private static String TABLE_PRESCRIPTION = "prescription";
    private static String TABLE_APPOINTMENT = "meet";
    private static String NAME = "name";
    private static String LAST_NAME = "last_name";
    private static String DNI_PATIENT = "dni_patient";
    private static String SPECIALTY = "specialty";
    private static String PHONE = "phone";
    private static String DNI_DOC = "dni_doc";
    private static String COD_APPOINTMENT = "cod_meet";
    private static String COD_PRESCRIPTION = "cod_prescription";
    private static String DESCRIPTION = "description";
    private static String SUBJECT = "subject";
    private static String DATE = "date";


    public AdminSQLiteOpenHelper(Context context){
        super(context, Constants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String QUERY_CREATE_DOCTOR =
                "CREATE TABLE " + TABLE_DOCTOR + " ("+
                        DNI_DOC + " TEXT PRIMARY KEY," +
                        NAME + " TEXT,"+
                       LAST_NAME + " TEXT," +
                       SPECIALTY + " TEXT)";
        db.execSQL(QUERY_CREATE_DOCTOR);


        final String QUERY_CREATE_PATIENT =
                "CREATE TABLE patient (" +
                        DNI_PATIENT + " TEXT PRIMARY KEY," +
                        NAME + " TEXT," +
                        LAST_NAME + " TEXT," +
                        PHONE + " TEXT," +
                        DNI_DOC + " TEXT," +
                        "FOREIGN KEY ("+DNI_DOC+") REFERENCES "+ TABLE_DOCTOR +" ("+DNI_DOC+"))";

        db.execSQL(QUERY_CREATE_PATIENT);

        final String QUERY_CREATE_PRESCRIPTION =
                "CREATE TABLE "+TABLE_PRESCRIPTION+" (" +
                        COD_PRESCRIPTION+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NAME+" TEXT," +
                        DESCRIPTION+" TEXT," +
                        DNI_PATIENT+" TEXT," +
                        "FOREIGN KEY ("+DNI_PATIENT+") REFERENCES "+TABLE_PATIENT+" ("+DNI_PATIENT+"))";

        db.execSQL(QUERY_CREATE_PRESCRIPTION);

        final String QUERY_CREATE_APPOINTMENT =
                "CREATE TABLE "+ TABLE_APPOINTMENT +" (" +
                        COD_APPOINTMENT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        SUBJECT + " TEXT," +
                        DATE + " TEXT," +
                        DNI_PATIENT + " TEXT," +
                        DNI_DOC + " TEXT," +
                        "FOREIGN KEY ("+DNI_PATIENT+") REFERENCES "+TABLE_PATIENT+" ("+DNI_PATIENT+")," +
                        "FOREIGN KEY ("+DNI_DOC+") REFERENCES "+TABLE_DOCTOR+" ("+DNI_DOC+"))";

        db.execSQL(QUERY_CREATE_APPOINTMENT);

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

    private static SQLiteDatabase initWritableDDBB(Context context){
        AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = adminHelper.getWritableDatabase();
        return db;
   }

   private static SQLiteDatabase initReadableDDBB(Context context){
        AdminSQLiteOpenHelper adminHelper = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = adminHelper.getReadableDatabase();
        return db;
   }


   public static void createDoctor(Context context, Doctor doctor){

       ContentValues values = new ContentValues();

       values.put(DNI_DOC, doctor.getDni());
       values.put(NAME, doctor.getName());
       values.put(LAST_NAME, doctor.getLastName());
       values.put(SPECIALTY, doctor.getSpecialty());

       SQLiteDatabase sqLiteDatabase = initWritableDDBB(context);
       sqLiteDatabase.insert(TABLE_DOCTOR, null, values);
   }

 public static void createPatient(Context context, Patient patient){

       ContentValues values = new ContentValues();

       values.put(DNI_PATIENT, patient.getDni());
       values.put(NAME, patient.getName());
       values.put(LAST_NAME, patient.getLastName());
       values.put(PHONE, patient.getPhone());
       values.put(DNI_DOC, patient.getDniDoc());

       SQLiteDatabase sqLiteDatabase= initWritableDDBB(context);
       sqLiteDatabase.insert(TABLE_PATIENT, null, values);
   }
    public static void createPrescription(Context context, Prescription prescription){

        ContentValues values = new ContentValues();

        values.put(COD_PRESCRIPTION, prescription.getCodPrescription());
        values.put(NAME, prescription.getName());
        values.put(DESCRIPTION, prescription.getDescription());
        values.put(DNI_PATIENT, prescription.getDniPatient());

        SQLiteDatabase sqLiteDatabase= initWritableDDBB(context);
        sqLiteDatabase.insert(TABLE_PRESCRIPTION, null, values);
    }

    public static void createAppointment(Context context, Appointment appointment){

        ContentValues values = new ContentValues();

        values.put(COD_APPOINTMENT, appointment.getCodMeet());
        values.put(SUBJECT, appointment.getSubject());
        values.put(DATE, appointment.getDate());
        values.put(DNI_PATIENT, appointment.getDniPatient());
        values.put(DNI_DOC, appointment.getDniDoctor());

        SQLiteDatabase sqLiteDatabase= initWritableDDBB(context);
        sqLiteDatabase.insert(TABLE_APPOINTMENT, null, values);
    }

    public static List<Doctor> getDoctors(Context  context){

        SQLiteDatabase sqLiteDatabase= initReadableDDBB(context);

        Cursor cursor = sqLiteDatabase.query(TABLE_DOCTOR, new String[]{DNI_DOC,NAME, LAST_NAME,SPECIALTY},null,null,null,null,null);

        List<Doctor> doctors = new ArrayList<>();
        while(cursor.moveToNext()) {

            String dniDoc =cursor.getString(cursor.getColumnIndex(DNI_DOC));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME));
            String specialty = cursor.getString(cursor.getColumnIndex(SPECIALTY));

            Doctor doctor = new Doctor(dniDoc, name, lastName, specialty);
            doctors.add(doctor);
        }
        cursor.close();

        sqLiteDatabase.close();
        return doctors;
    }

}
