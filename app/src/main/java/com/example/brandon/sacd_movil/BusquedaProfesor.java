package com.example.brandon.sacd_movil;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BusquedaProfesor extends AppCompatActivity {
    ArrayList<String> nombresProfesores = new ArrayList<>();
    TableLayout tbProfesores;
    String currentText = "";
    //Requester requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_profesor);
        setTitle("Buscar Profesor");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#27AE8D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        nombresProfesores.add("Hola");
        nombresProfesores.add("Adios");
        nombresProfesores.add("Prueba");

        tbProfesores = (TableLayout)findViewById(R.id.tbProfesores);

        EditText txtBuscar = (EditText) findViewById(R.id.tbxBuscar);

        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentText = s.toString();

                try {
                    actualizarProfesores();
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Error al cargar los profesores.",
                            Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        try {
            actualizarProfesores();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar los profesores.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarProfesores ()throws Exception
    {
        cleanTable();

        ArrayList<String> tmpNombresProfesores = new ArrayList<>();
        TableRow auxRow;
        TextView auxProfesor;
        EspecialButton auxButton;

        if(!currentText.isEmpty()) {
            for (int i = 0; i < nombresProfesores.size(); i++) {
                if (nombresProfesores.get(i).toLowerCase().contains(currentText.toLowerCase()))
                    tmpNombresProfesores.add(nombresProfesores.get(i));
            }
        }else
            tmpNombresProfesores = nombresProfesores;


        for (String profesor : tmpNombresProfesores)
        {
            auxRow = new TableRow(this);
            auxProfesor = new TextView(this);
            auxProfesor.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics()));
            auxProfesor.setGravity(Gravity.CENTER);

            auxButton = new EspecialButton(this,profesor);
            auxButton.setText("Ver");


            /*auxButton.setOnClickListener(new Button.OnClickListener() {    //evento boton profesor
                public void onClick(View v) {
                    EspecialButton button = (EspecialButton)v;
                    TableLayout tbTratamiento = (TableLayout)findViewById(R.id.tbProfesores);

                    //clean table tratamientos
                    for(int count = tbTratamiento.getChildCount()-1; count >= 0; count--)
                    {
                        System.out.println(count);
                        tbTratamiento.removeViewAt(count);
                    }

                    try {
                        ArrayList<ArrayList<String>> tratamiento = requester.getTratamiento(button.getOwner());
                        TableRow auxRow;
                        TextView auxId;
                        TextView auxNombre;
                        EspecialButton btnVer;
                        for (ArrayList<String> producto : tratamiento)
                        {
                            auxRow = new TableRow(button.getContext());
                            auxId = new TextView(button.getContext());
                            auxId.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics()));
                            auxId.setGravity(Gravity.CENTER);

                            auxNombre = new TextView(button.getContext());
                            auxNombre.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics()));
                            auxNombre.setGravity(Gravity.CENTER);

                            btnVer = new EspecialButton(button.getContext(),producto.get(0));
                            btnVer.setOnClickListener(new Button.OnClickListener() { //evento boton Producto
                                public void onClick(View v) {
                                    EspecialButton button = (EspecialButton)v;
                                    Intent nextActivity = new Intent(v.getContext(), DetalleProductoActivity.class);
                                    nextActivity.putExtra("id",button.getOwner());
                                    startActivity(nextActivity);
                                }});

                            auxId.setText(producto.get(0));
                            auxNombre.setText(producto.get(1));
                            auxRow.addView(auxId);
                            auxRow.addView(auxNombre);
                            auxRow.addView(btnVer);

                            tbTratamiento.addView(auxRow);
                        }
                    }
                    catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Error al cargar el tratamiento",
                                Toast.LENGTH_SHORT).show();
                    }
                }});

            */
            auxProfesor.setText(profesor);
            auxRow.addView(auxProfesor);
            auxRow.addView(auxButton);

            tbProfesores.addView(auxRow);
        }
    }

    public void cleanTable(){
        for(int count = tbProfesores.getChildCount()-1; count >= 0; count--)
        {
            System.out.println(count);
            tbProfesores.removeViewAt(count);
        }

    }
}
