package com.example.brandon.sacd_movil;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ReportesDetalle extends AppCompatActivity {
    private String idProfe, nombre, periodo, anio;

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

        setTitle(nombre);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#27AE8D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        TextView txtPeriodo = (TextView)findViewById(R.id.txtPeriodo);
        TextView txtAnio = (TextView)findViewById(R.id.txtAnio);

        txtPeriodo.setText(periodo);
        txtAnio.setText(anio);

    }
}
