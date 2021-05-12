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

public class Database extends SQLiteOpenHelper {

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


    public Database(Context context){
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
        Database adminHelper = new Database(context);
        SQLiteDatabase db = adminHelper.getWritableDatabase();
        return db;
   }

   private static SQLiteDatabase initReadableDDBB(Context context){
        Database adminHelper = new Database(context);
        SQLiteDatabase db = adminHelper.getReadableDatabase();
        return db;
   }


   public static void createDoctor(Context context, Doctor doctor){

       ContentValues values = new ContentValues();

       values.put(DNI_DOC, doctor.getDni().toUpperCase());
       values.put(NAME, doctor.getName());
       values.put(LAST_NAME, doctor.getLastName());
       values.put(SPECIALTY, doctor.getSpecialty());

       SQLiteDatabase sqLiteDatabase = initWritableDDBB(context);
       sqLiteDatabase.insert(TABLE_DOCTOR, null, values);
   }

 public static void createPatient(Context context, Patient patient){

       ContentValues values = new ContentValues();

       values.put(DNI_PATIENT, patient.getDni().toUpperCase());
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
    public static Doctor getDoctor(Context context, String dni){
        Doctor doctorSend = null;

        List<Doctor> doctors = getDoctors(context);

        for(Doctor doctor : doctors){
            if(doctor.getDni().equals(dni.toUpperCase())) {
                doctorSend = new Doctor(doctor.getDni(), doctor.getName(), doctor.getLastName(), doctor.getSpecialty());
            }
        }
        return doctorSend;
    }

    public static boolean hasDoctor(Context context,String dni){
        return getDoctor(context, dni) != null;
    }


    public static List<Patient> getPatients(Context  context){

        SQLiteDatabase sqLiteDatabase= initReadableDDBB(context);

        Cursor cursor = sqLiteDatabase.query(TABLE_PATIENT, new String[]{DNI_PATIENT,NAME, LAST_NAME,PHONE,DNI_DOC},null,null,null,null,null);

        List<Patient> patients = new ArrayList<>();
        while(cursor.moveToNext()) {

            String dniPatient =cursor.getString(cursor.getColumnIndex(DNI_PATIENT));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));
            String dniDoc = cursor.getString(cursor.getColumnIndex(DNI_DOC));

            Patient patient = new Patient(dniPatient,name,lastName,phone,dniDoc);
            patients.add(patient);
        }
        cursor.close();
        sqLiteDatabase.close();
        return patients;
    }

    public static Patient getPatient(Context context, String dni){
        Patient patientSend = null;

        List<Patient> patients = getPatients(context);

        for(Patient patient : patients){
            if(patient.getDni().equals(dni.toUpperCase())) {
                patientSend = new Patient(patient.getDni(), patient.getName(), patient.getLastName(), patient.getPhone(), patient.getDniDoc());
            }
        }
        return patientSend;
    }

    public static boolean hasPatient(Context context,String dni){
        return getPatient(context, dni) != null;
    }

    public static List<Prescription> getPrescriptions(Context context){

        SQLiteDatabase sqLiteDatabase= initReadableDDBB(context);

        Cursor cursor = sqLiteDatabase.query(TABLE_PRESCRIPTION, new String[]{COD_PRESCRIPTION,NAME,DESCRIPTION,DNI_PATIENT},null,null,null,null,null);

        List<Prescription> prescriptions = new ArrayList<>();
        while(cursor.moveToNext()) {

            Integer cod = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COD_PRESCRIPTION)));
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
            String dni = cursor.getString(cursor.getColumnIndex(DNI_PATIENT));

            Prescription patient = new Prescription(cod,name,description,dni);
            prescriptions.add(patient);
        }
        cursor.close();
        sqLiteDatabase.close();
        return prescriptions;
    }

    public static Prescription getPrescription(Context context, String cod){
        Prescription prescriptionSend = null;

        List<Prescription> prescriptions = getPrescriptions(context);

        for(Prescription prescription : prescriptions){
            if(prescription.getCodPrescription().equals(Integer.parseInt(cod))) {
                prescriptionSend = new Prescription(prescription.getCodPrescription(),prescription.getName(),prescription.getDescription(),prescription.getDniPatient());
            }
        }
        return prescriptionSend;
    }

    public static boolean hasPrescription(Context context,String cod){
        return getPatient(context, cod) != null;
    }

    public static boolean deletePrescription(Context context,Integer codPrescription) {
        boolean verify = false;
        SQLiteDatabase sqLiteDatabase = initWritableDDBB(context);

        if (codPrescription == 0) {
            verify = false;
        } else {
            String selection = COD_PRESCRIPTION + " = ?";
            String[] condition = {codPrescription.toString()};
            int deletedRows = sqLiteDatabase.delete(TABLE_PRESCRIPTION, selection, condition);
            if (deletedRows == 0) {
                verify = false;
            } else {
                verify = true;
            }
        }

        sqLiteDatabase.close();
        return verify;
    }

    public static List<Appointment> getAppointments(Context  context){

        SQLiteDatabase sqLiteDatabase= initReadableDDBB(context);

        Cursor cursor = sqLiteDatabase.query(TABLE_APPOINTMENT, new String[]{COD_APPOINTMENT,SUBJECT, DATE,DNI_PATIENT,DNI_DOC},null,null,null,null,null);

        List<Appointment> appointments = new ArrayList<>();
        while(cursor.moveToNext()) {

            Integer codAppointment = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COD_APPOINTMENT)));
            String subject = cursor.getString(cursor.getColumnIndex(SUBJECT));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String dniPatient = cursor.getString(cursor.getColumnIndex(DNI_PATIENT));
            String dniDoc = cursor.getString(cursor.getColumnIndex(DNI_DOC));

            Appointment appointment = new Appointment(codAppointment,subject,date,dniPatient,dniDoc);
            appointments.add(appointment);
        }
        cursor.close();
        sqLiteDatabase.close();
        return appointments;
    }

}
