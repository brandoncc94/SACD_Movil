package com.example.brandon.sacd_movil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Prueba extends AppCompatActivity {

    String idProfesor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        //Así agarra el ID del profesor cuando presiona el botón VER
        Bundle bundle = getIntent().getExtras();
        idProfesor = bundle.getString("id");

        System.out.println("El ID es: " + idProfesor);
    }
}
