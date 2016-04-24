package com.example.brandon.sacd_movil;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Controller.Requester;
import Model.Plaza;


public class Perfil extends AppCompatActivity {

    private ArrayList<Plaza> listaPlazas = new ArrayList<>();
    TableLayout tbPlazas;
    Requester requester;
    String idProfesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Obtener id del profesor
        /*Bundle bundle = getIntent().getExtras();
        idProfesor = bundle.getString("id");*/

        //Set profe info
        ArrayList<String> profesorInfo = new ArrayList<String>();
        profesorInfo.add("Maria Arguedas");
        profesorInfo.add("25");

        requester = Requester.getInstance();
        try
        {
            profesorInfo = requester.getProfeInfo("1"); //poner idProfesor
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar información.",
                    Toast.LENGTH_SHORT).show();
        }

        setTitle(profesorInfo.get(0));
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#27AE8D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        //Horas asignadas
        TextView horasAsig = (TextView)findViewById(R.id.lblHorasAsig);
        horasAsig.setText(profesorInfo.get(1));

        //Obtener Plazas
        listaPlazas.add(new Plaza(001,40, true));
        listaPlazas.add(new Plaza(002,10, false));

        try
        {
            listaPlazas = requester.getPlazas("1"); //poner idProfesor
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error al cargar Plazas.",
                    Toast.LENGTH_SHORT).show();
        }

        tbPlazas = (TableLayout)findViewById(R.id.tbPlazas);
        actualizarPlazas();

        //calcular horas mínimas
        //Horas asignadas
        TextView horasMin = (TextView)findViewById(R.id.lblHorasMin);
        horasMin.setText(String.valueOf(getHorasMin()));

    }

    private double getHorasMin()
    {
        double horasMin = 0;
        for (Plaza plaza : listaPlazas)
        {
            if(plaza.getPropiedad())
                horasMin += plaza.getHoras();
        }

        return horasMin;
    }

    private void actualizarPlazas()
    {
        cleanTable();
        TableRow auxRow = new TableRow(this);
        TextView auxNum = new TextView(this);;
        TextView auxHoras = new TextView(this);;
        TextView auxModalidad = new TextView(this);;


        //Header de la tabla
        auxRow.setBackgroundColor(Color.parseColor("#27AE8D"));//(getResources().getColor(R.color.colorAccent));

        auxNum.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
        auxNum.setGravity(Gravity.LEFT);
        auxNum.setText("Num");
        auxNum.setTextColor(Color.WHITE);
        auxNum.setTypeface(null, Typeface.BOLD);
        auxRow.addView(auxNum);

        auxHoras.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
        auxHoras.setGravity(Gravity.LEFT);
        auxHoras.setText("Horas");
        auxHoras.setTextColor(Color.WHITE);
        auxHoras.setTypeface(null, Typeface.BOLD);
        auxRow.addView(auxHoras);

        auxModalidad.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
        auxModalidad.setGravity(Gravity.LEFT);
        auxModalidad.setText("Modo");
        auxModalidad.setTextColor(Color.WHITE);
        auxModalidad.setTypeface(null, Typeface.BOLD);
        auxRow.addView(auxModalidad);

        auxRow.setMinimumHeight(120);
        auxRow.setGravity(Gravity.CENTER_VERTICAL);
        auxRow.setPadding(100,10,10,10);
        tbPlazas.addView(auxRow);

        for (Plaza plaza : listaPlazas)
        {
            auxRow = new TableRow(this);

            auxNum = new TextView(this);
            auxNum.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
            auxNum.setGravity(Gravity.CENTER);
            auxNum.setText(String.valueOf(plaza.getNumero()));

            auxHoras = new TextView(this);
            auxHoras.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
            auxHoras.setGravity(Gravity.CENTER);
            auxHoras.setText(String.valueOf(plaza.getHoras()));

            auxModalidad = new TextView(this);
            auxModalidad.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
            auxModalidad.setGravity(Gravity.CENTER);
            if(plaza.getPropiedad())
                auxModalidad.setText("Propiedad");
            else
                auxModalidad.setText("Interina");

            auxRow.addView(auxNum);
            auxRow.addView(auxHoras);
            auxRow.addView(auxModalidad);

            auxRow.setMinimumHeight(120);
            auxRow.setGravity(Gravity.CENTER_VERTICAL);

            tbPlazas.addView(auxRow);
        }
    }


    public void cleanTable(){
        for(int count = tbPlazas.getChildCount()-1; count >= 0; count--)
        {
            System.out.println(count);
            tbPlazas.removeViewAt(count);
        }

    }
}
