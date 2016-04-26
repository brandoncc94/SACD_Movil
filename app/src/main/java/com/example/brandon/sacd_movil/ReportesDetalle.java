package com.example.brandon.sacd_movil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Controller.Requester;
import Model.ActvAdmin;
import Model.Asignacion;
import Model.Grupo;
import Model.Investigacion;

public class ReportesDetalle extends AppCompatActivity {
    private String idProfe, nombre, periodo, anio;
    private Requester requester;
    private TableLayout tbAsignaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_detalle);

        //Obtener nombre del profesor
        Bundle bundle = getIntent().getExtras();
        idProfe = bundle.getString("idProfe");
        nombre = bundle.getString("nombre");
        periodo = bundle.getString("periodo");
        anio = bundle.getString("anio");

        requester = Requester.getInstance();
        tbAsignaciones = (TableLayout)findViewById(R.id.tbAsignaciones);

        setTitle(nombre);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#27AE8D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        TextView txtPeriodo = (TextView)findViewById(R.id.txtPeriodo);
        TextView txtAnio = (TextView)findViewById(R.id.txtAnio);

        txtPeriodo.setText(periodo);
        txtAnio.setText(anio);

        cargarAsignaciones();

    }

    private void cargarAsignaciones()
    {
        try {
            ArrayList<Asignacion> asignaciones = requester.getAsignaciones(idProfe, periodo, anio);
            double horasTotal = 0;
            double porcTotal = 0;

            for (Asignacion asignacion : asignaciones) {
                if (asignacion.getActividad().getTipo() == "INVE") {
                    Investigacion inve = (Investigacion) asignacion.getActividad();
                    double porcInve = Double.parseDouble(inve.getHoras()) * 100;
                    porcInve = porcInve / 40;

                    horasTotal += Double.parseDouble(inve.getHoras());
                    porcTotal += porcInve;

                } else if (asignacion.getActividad().getTipo() == "ADMI") {
                    ActvAdmin admi = (ActvAdmin) asignacion.getActividad();
                    double porcAdmi = Double.parseDouble(admi.getHoras()) * 100;
                    porcAdmi = porcAdmi / 40;

                    horasTotal += Double.parseDouble(admi.getHoras());
                    porcTotal += porcAdmi;

                } else {
                    Grupo grupo = (Grupo) asignacion.getActividad();
                    double porcGrupo = Double.parseDouble(asignacion.getValorHoras()) * 100;
                    porcGrupo = porcGrupo / 40;

                    horasTotal += Double.parseDouble(asignacion.getValorHoras());
                    porcTotal += porcGrupo;
                }
            }

            TextView txtCargaHoras = (TextView) findViewById(R.id.txtCargHoras);
            TextView txtCargaPorcentaje = (TextView) findViewById(R.id.txtCargPorcentaje);

            txtCargaHoras.setText(String.valueOf(horasTotal));
            txtCargaPorcentaje.setText(String.valueOf(porcTotal));

            actualizarAsignaciones(asignaciones);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar las asignaciones.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarAsignaciones (ArrayList<Asignacion> pAsignaciones)throws Exception
    {
        cleanTable();
        TableRow auxRow = new TableRow(this);
        TextView tipo = new TextView(this);
        TextView nombre = new TextView(this);
        TextView horas = new TextView(this);
        TextView porcent = new TextView(this);

        //Header de la tabla
        auxRow.setBackgroundColor(Color.parseColor("#27AE8D"));

        tipo.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()));
        tipo.setGravity(Gravity.CENTER);
        tipo.setText("Tipo");
        tipo.setTextColor(Color.WHITE);
        tipo.setTypeface(null, Typeface.BOLD);
        auxRow.addView(tipo);

        nombre.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics()));
        nombre.setGravity(Gravity.CENTER);
        nombre.setText("Nombre");
        nombre.setTextColor(Color.WHITE);
        nombre.setTypeface(null, Typeface.BOLD);
        auxRow.addView(nombre);

        horas.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
        horas.setGravity(Gravity.CENTER);
        horas.setText("Horas");
        horas.setTextColor(Color.WHITE);
        horas.setTypeface(null, Typeface.BOLD);
        auxRow.addView(horas);

        porcent.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
        porcent.setGravity(Gravity.CENTER);
        porcent.setText("%");
        porcent.setTextColor(Color.WHITE);
        porcent.setTypeface(null, Typeface.BOLD);
        auxRow.addView(porcent);

        auxRow.setMinimumHeight(120);
        auxRow.setGravity(Gravity.CENTER_VERTICAL);
        //auxRow.setPadding(100,10,10,10);
        tbAsignaciones.addView(auxRow);

        for (Asignacion asignacion : pAsignaciones) {
            auxRow = new TableRow(this);
            auxRow.setPadding(15,15,15,15);
            tipo = new TextView(this);
            tipo.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()));
            tipo.setGravity(Gravity.CENTER);
            nombre = new TextView(this);
            nombre.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics()));
            nombre.setGravity(Gravity.CENTER);
            horas = new TextView(this);
            horas.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
            horas.setGravity(Gravity.CENTER);
            porcent = new TextView(this);
            porcent.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
            porcent.setGravity(Gravity.CENTER);

            double porc = Double.parseDouble(asignacion.getValorHoras()) * 100;
            porc = porc / 40;

            horas.setText(asignacion.getValorHoras());
            porcent.setText(String.valueOf(porc));

            if (asignacion.getActividad().getTipo() == "INVE") {
                Investigacion inve = (Investigacion) asignacion.getActividad();
                tipo.setText("I");
                nombre.setText(inve.getNombre());

            } else if (asignacion.getActividad().getTipo() == "ADMI") {
                ActvAdmin admi = (ActvAdmin) asignacion.getActividad();
                tipo.setText("A");
                nombre.setText(admi.getNombre());

            } else {
                Grupo grupo = (Grupo) asignacion.getActividad();
                tipo.setText("G");
                nombre.setText(grupo.getNombre());
            }

            auxRow.addView(tipo);
            auxRow.addView(nombre);
            auxRow.addView(horas);
            auxRow.addView(porcent);

            tbAsignaciones.addView(auxRow);
        }
    }

    public void cleanTable(){
        for(int count = tbAsignaciones.getChildCount()-1; count >= 0; count--)
        {
            System.out.println(count);
            tbAsignaciones.removeViewAt(count);
        }

    }
}
