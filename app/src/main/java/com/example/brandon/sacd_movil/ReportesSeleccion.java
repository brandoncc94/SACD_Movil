package com.example.brandon.sacd_movil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReportesSeleccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_seleccion);

        setTitle("Generar Reportes");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#27AE8D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }

    public void btnGenerarOnClick(View v) {
        Intent intent = new Intent(ReportesSeleccion.this, ReportesDetalle.class);
        startActivity(intent);
    }

}
