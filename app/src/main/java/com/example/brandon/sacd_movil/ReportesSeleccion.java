package com.example.brandon.sacd_movil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import Controller.Requester;

public class ReportesSeleccion extends AppCompatActivity {

    Requester requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_seleccion);

        setTitle("Generar Reportes");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#27AE8D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        requester = Requester.getInstance();
        cargarProfesores();
    }

    private void cargarSemestres(){
        Spinner spSemestres = (Spinner)findViewById(R.id.spinner);
        ArrayList<String> semestres = new ArrayList<>();
        try {
            semestres = requester.getProfesores();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar los profesores.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarAnios(){
        Spinner spAnios = (Spinner)findViewById(R.id.spinner2);
        ArrayList<String> semestres = new ArrayList<>();
        try {
            semestres = requester.getProfesores();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar los profesores.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarProfesores(){
        Spinner spProfesores = (Spinner)findViewById(R.id.spinner3);
        ArrayList<String> semestres = new ArrayList<>();
        ArrayAdapter<String> adapter;

        try {
            semestres = requester.getProfesores();
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, semestres);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spProfesores.setAdapter(adapter);

        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar los profesores.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void btnGenerarOnClick(View v) {
        Intent intent = new Intent(ReportesSeleccion.this, ReportesDetalle.class);
        startActivity(intent);
    }

}
