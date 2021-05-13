package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.martinmei.kiroshihealth.R;
import com.martinmei.kiroshihealth.ddbb.Database;
import com.martinmei.kiroshihealth.models.Appointment;

import java.util.List;

public class ApointmentListDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apointment_list);
        RecyclerView recycledview_appointments = findViewById(R.id.recycled_view_apointments);

        List<Appointment> appointments = Database.getAppointments(this);


        AppointmentDoctorAdapter appointmentDoctorAdapter = new AppointmentDoctorAdapter(appointments);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycledview_appointments.setLayoutManager(mLayoutManager);
        recycledview_appointments.setItemAnimator(new DefaultItemAnimator());
        recycledview_appointments.setAdapter(appointmentDoctorAdapter);
    }
}