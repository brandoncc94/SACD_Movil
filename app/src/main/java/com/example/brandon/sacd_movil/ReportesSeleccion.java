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
    ArrayList<String> nombres;
    ArrayList<String> idProfes;
    private Spinner spPeriodos;
    private Spinner spAnios;
    private Spinner spProfesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_seleccion);

        setTitle("Generar Reportes");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#27AE8D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        spPeriodos = (Spinner)findViewById(R.id.spinner);
        spAnios = (Spinner)findViewById(R.id.spinner2);
        spProfesores = (Spinner)findViewById(R.id.spinner3);

        nombres = new ArrayList<>();
        idProfes = new ArrayList<>();

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
        if (semestres != null) {
            ArrayList<String> periodos = new ArrayList<>();
            ArrayAdapter<String> adapter;

            for (Semestre semestre : semestres) {
                if (!periodos.contains(semestre.getPeriodo()))
                    periodos.add(semestre.getPeriodo());
            }

            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_special_item, periodos);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spPeriodos.setAdapter(adapter);
        }
        else{
            Toast.makeText(getApplicationContext(), "Error al cargar los periodos.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarAnios(){
        if (semestres != null) {
            ArrayList<String> anios = new ArrayList<>();
            ArrayAdapter<String> adapter;

            for (Semestre semestre : semestres) {
                if (!anios.contains(semestre.getAnio()))
                    anios.add(semestre.getAnio());
            }

            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_special_item, anios);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAnios.setAdapter(adapter);
        }
        else{
            Toast.makeText(getApplicationContext(), "Error al cargar los anios.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarProfesores(){
        ArrayAdapter<String> adapter;
        String[] parts;

        for (String profesor : profesores){
            parts = profesor.split("-");
            idProfes.add(parts[0]);
            nombres.add(parts[1]);
        }

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_special_item, nombres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProfesores.setAdapter(adapter);
    }

    private String buscarIdProfe (String pNombre){
        String id = "";

        for(int i = 0; i<nombres.size() ;i++){
            if (nombres.get(i).equals(pNombre))
                id = idProfes.get(i);
        }

        return id;
    }

    public void btnGenerarOnClick(View v) {
        String id = buscarIdProfe(spProfesores.getSelectedItem().toString());
        Intent intent = new Intent(ReportesSeleccion.this, ReportesDetalle.class);
        intent.putExtra("idProfe", id);
        intent.putExtra("nombre", spProfesores.getSelectedItem().toString());
        intent.putExtra("periodo", spPeriodos.getSelectedItem().toString());
        intent.putExtra("anio", spAnios.getSelectedItem().toString());
        startActivity(intent);
    }

}
