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
import Model.Semestre;

public class ReportesSeleccion extends AppCompatActivity {

    private Requester requester;
    private ArrayList<Semestre> semestres;
    private ArrayList<String> profesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_seleccion);

        setTitle("Generar Reportes");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#27AE8D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        requester = Requester.getInstance();

        try {
            semestres = requester.getSemestres();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar los semestres.",
                    Toast.LENGTH_SHORT).show();
        }

        try {
            profesores = requester.getProfesores();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar los profesores.",
                    Toast.LENGTH_SHORT).show();
        }
        cargarPeriodos();
        cargarAnios();
        cargarProfesores();
    }

    private void cargarPeriodos(){
        Spinner spPeriodos = (Spinner)findViewById(R.id.spinner);
        ArrayList<String> periodos = new ArrayList<>();
        ArrayAdapter<String> adapter;

        for (Semestre semestre : semestres){
            if (!periodos.contains(semestre.getPeriodo()))
                periodos.add(semestre.getPeriodo());
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, periodos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPeriodos.setAdapter(adapter);
    }

    private void cargarAnios(){
        Spinner spAnios = (Spinner)findViewById(R.id.spinner2);
        ArrayList<String> anios = new ArrayList<>();
        ArrayAdapter<String> adapter;

        for (Semestre semestre : semestres){
            if (!anios.contains(semestre.getAnio()))
                anios.add(semestre.getAnio());
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, anios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAnios.setAdapter(adapter);
    }

    private void cargarProfesores(){
        Spinner spProfesores = (Spinner)findViewById(R.id.spinner3);
        ArrayList<String> nombres = new ArrayList<>();
        ArrayAdapter<String> adapter;
        String[] parts;

        for (String profesor : profesores){
            parts = profesor.split("-");
            nombres.add(parts[1]);
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nombres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProfesores.setAdapter(adapter);
    }

    public void btnGenerarOnClick(View v) {
        Intent intent = new Intent(ReportesSeleccion.this, ReportesDetalle.class);
        startActivity(intent);
    }

}
