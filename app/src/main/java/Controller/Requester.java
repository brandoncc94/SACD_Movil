package Controller;

import Database.Connection;
import Model.Actividad;
import Model.ActvAdmin;
import Model.Asignacion;
import Model.Grupo;
import Model.Investigacion;
import Model.Plaza;
import Model.Semestre;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class Requester {

    private static Requester requester;
    private static Connection connection;

    private Requester()
    {
        connection = Connection.getInstance();
    }

    public static Requester getInstance()
    {
        if(requester == null)
        {
            requester = new Requester();
        }
        return requester;
    }

    public ArrayList<String> getProfeInfo(String pIdProfe) throws Exception
    {
        String nombre;
        double horasAsig;

        ArrayList<String> profesor = new ArrayList<String>();
        String request = "http://proyecto_softw.comxa.com/WebService/getProfeInfo.php?idProfe="+pIdProfe;
        JSONObject obj = connection.getObject(request);

        System.out.println(obj);

        int  estado = obj.getInt("estado");

        if(estado == 1)
        {
            System.out.println("PRUEBA ESTADO BUENO");
            JSONArray info = obj.getJSONArray("datos");
            for (int i = 0; i < info.length(); i++)
            {
                nombre = info.getJSONObject(i).getString("nombre");
                horasAsig = info.getJSONObject(i).getDouble("horasAsig");
                profesor = new ArrayList<String>();
                profesor.add(nombre);
                profesor.add(String.valueOf(horasAsig));
            }

            return profesor;
        }

        else
        {
            System.out.println("ESTADO MALO");
            String info = obj.getString("datos");
            System.out.println(info);
            return null;
        }
    }

    public ArrayList<Plaza> getPlazas(String pIdProfe) throws Exception
    {
        int numPlaza;
        double porcentaje;
        boolean isPropiedad;
        String modo;

        ArrayList<Plaza> plazasList = new ArrayList<Plaza>();
        Plaza auxPlaza;
        String request = "http://proyecto_softw.comxa.com/WebService/getPlazasProf.php?idProfe="+pIdProfe;
        JSONObject obj = connection.getObject(request);

        System.out.println(obj);

        int  estado = obj.getInt("estado");

        if(estado == 1)
        {
            System.out.println("PRUEBA ESTADO BUENO");
            JSONArray info = obj.getJSONArray("info");


            for (int i = 0; i < info.length(); i++)
            {
                try {
                    numPlaza = info.getJSONObject(i).getInt("plaza");
                    porcentaje = info.getJSONObject(i).getDouble("porcentaje");
                    modo = info.getJSONObject(i).getString("modo");
                    System.out.println("el MODO es " + modo);
                    auxPlaza = new Plaza(numPlaza, porcentaje, modo);
                    plazasList.add(auxPlaza);
                }
                catch (Exception e)
                {
                    System.out.println("EXCEPCION " + e.getMessage());
                }
            }

            return plazasList;
        }

        else
        {
            System.out.println("ESTADO MALO");
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }

    public ArrayList<String> getProfesores() throws Exception
    {
        String nombre,id, horas;
        ArrayList<String> listaProfesores = new ArrayList<String>();
        String request = "http://proyecto_softw.comxa.com/WebService/obtenerProfesores.php?profesor=all";
        JSONObject obj = connection.getObject(request);
        System.out.println("Prueba");
        System.out.println(obj);
        System.out.println("Prueba");
        int estado = obj.getInt("estado");
        if(estado == 1){
            JSONArray info = obj.getJSONArray("info");
            for (int i = 0; i < info.length(); i++){
                id = info.getJSONObject(i).getString("id_profesor");
                nombre = info.getJSONObject(i).getString("nom_profesor");
                horas = info.getJSONObject(i).getString("num_horas_asign");
                listaProfesores.add(id + "-" + nombre + "-" + horas);
            }
            System.out.println(listaProfesores);
            return listaProfesores;
        }else{
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }

    public ArrayList<Semestre> getSemestres() throws Exception
    {
        String id, periodo, anio;
        ArrayList<Semestre> listaSemestres = new ArrayList<>();
        String request = "http://proyecto_softw.comxa.com/WebService/getSemestres.php";

        JSONObject obj = connection.getObject(request);
        System.out.println("Prueba");
        System.out.println(obj);
        System.out.println("Prueba");

        int estado = obj.getInt("estado");
        if(estado == 1){
            JSONArray info = obj.getJSONArray("info");
            for (int i = 0; i < info.length(); i++){
                id = info.getJSONObject(i).getString("id_semestre");
                anio = info.getJSONObject(i).getString("num_año");
                periodo = info.getJSONObject(i).getString("num_periodo");
                listaSemestres.add(new Semestre(id,anio,periodo));
            }
            System.out.println(listaSemestres);
            return listaSemestres;
        }else{
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }

    public ArrayList<ArrayList<String>> getResultadosBusqueda (String pCriterio) throws Exception
    {
        String id;
        String nombre;
        String cantidad;

        ArrayList<ArrayList<String>> resultados = new ArrayList<ArrayList<String>>();
        ArrayList<String> producto = new ArrayList<String>();
        String request = "http://macrobioticasaludnatural.uphero.com/WebService/main.php?consulta=4" +
                "&producto="+pCriterio;

        JSONObject obj = connection.getObject(request);

        int estado = obj.getInt("estado");
        if(estado == 1){
            JSONArray info = obj.getJSONArray("info");
            for (int i = 0; i < info.length(); i++){
                id = info.getJSONObject(i).getString("idProducto");
                nombre = info.getJSONObject(i).getString("nombre");
                cantidad = "20";//info.getJSONObject(i).getString("precio");
                producto = new ArrayList<String>();
                producto.add(id);
                producto.add(nombre);
                producto.add(cantidad);
                resultados.add(producto);
            }
            return resultados;
        }else{
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }


    public static ArrayList<Asignacion> getAsignaciones(String pIdProfe, String pPeriodo, String pAño)throws Exception {
        String idActividad, numValorHoras;
        ArrayList<Asignacion> listaAsignaciones = new ArrayList<>();
        String request = "http://proyecto_softw.comxa.com/WebService/getAsignaciones.php?idProfe=" + pIdProfe + "&periodo=" + pPeriodo + "&anio=" + pAño;

        JSONObject obj = connection.getObject(request);
        System.out.println("Prueba");
        System.out.println(obj);
        System.out.println("Prueba");

        int estado = obj.getInt("estado");
        if (estado == 1) {
            JSONArray info = obj.getJSONArray("info");
            for (int i = 0; i < info.length(); i++) {
                idActividad = info.getJSONObject(i).getString("id_actividad");
                numValorHoras = info.getJSONObject(i).getString("num_valor_horas");
                listaAsignaciones.add(new Asignacion(numValorHoras, getActividad(idActividad)));
            }
            System.out.println(listaAsignaciones);
            return listaAsignaciones;
        } else {
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }


    public static Actividad getActividad(String pIdActividad)throws Exception {
        String tipo = "";
        String request = "http://proyecto_softw.comxa.com/WebService/getActividad.php?idActividad=" + pIdActividad;

        JSONObject obj = connection.getObject(request);
        System.out.println("Prueba");
        System.out.println(obj);
        System.out.println("Prueba");

        int estado = obj.getInt("estado");
        if (estado == 1) {
            JSONArray info = obj.getJSONArray("info");
            for (int i = 0; i < info.length(); i++) {
                tipo = info.getJSONObject(i).getString("txt_tipo");
            }

            if(tipo.equals("INVE")){
                Investigacion inve = getInvestigacion(pIdActividad);
                return inve;
            }
            else if(tipo.equals("ADMI")){
                ActvAdmin admi = getAdministrativa(pIdActividad);
                return admi;
            }
            else{
                Grupo grupo = getGrupo(pIdActividad);
                return grupo;
            }

        } else {
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }

    public static Investigacion getInvestigacion(String pIdActividad)throws Exception {
        String nombre = "";
        String horas = "";
        String request = "http://proyecto_softw.comxa.com/WebService/getInvestigacion.php?idActividad=" + pIdActividad;

        JSONObject obj = connection.getObject(request);
        System.out.println("Prueba");
        System.out.println(obj);
        System.out.println("Prueba");

        int estado = obj.getInt("estado");
        if (estado == 1) {
            JSONArray info = obj.getJSONArray("info");
            for (int i = 0; i < info.length(); i++) {
                nombre = info.getJSONObject(i).getString("nom_investig");
                horas = info.getJSONObject(i).getString("can_horas");
            }

            return new Investigacion(pIdActividad, "INVE", horas, nombre);

        } else {
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }

    public static ActvAdmin getAdministrativa(String pIdActividad)throws Exception {
        String nombre = "";
        String horas = "";
        String request = "http://proyecto_softw.comxa.com/WebService/getAdministrativa.php?idActividad=" + pIdActividad;

        JSONObject obj = connection.getObject(request);
        System.out.println("Prueba");
        System.out.println(obj);
        System.out.println("Prueba");

        int estado = obj.getInt("estado");
        if (estado == 1) {
            JSONArray info = obj.getJSONArray("info");
            for (int i = 0; i < info.length(); i++) {
                nombre = info.getJSONObject(i).getString("nom_adminitrativ");
                horas = info.getJSONObject(i).getString("can_horas");
            }

            return new ActvAdmin(pIdActividad, "ADMI", horas, nombre);

        } else {
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }

    public static Grupo getGrupo(String pIdActividad)throws Exception {
        String nombre = "";
        String numero = "";
        String cantidad = "";
        String request = "http://proyecto_softw.comxa.com/WebService/getGrupo.php?idActividad=" + pIdActividad;

        JSONObject obj = connection.getObject(request);
        System.out.println("Prueba");
        System.out.println(obj);
        System.out.println("Prueba");

        int estado = obj.getInt("estado");
        if (estado == 1) {
            JSONArray info = obj.getJSONArray("info");
            for (int i = 0; i < info.length(); i++) {
                nombre = info.getJSONObject(i).getString("nom_curso");
                numero = info.getJSONObject(i).getString("num_grupo");
                cantidad = info.getJSONObject(i).getString("num_estudiantes");
            }

            return new Grupo(pIdActividad, nombre, "GRUP", "", numero, cantidad);

        } else {
            String info = obj.getString("info");
            System.out.println(info);
            return null;
        }
    }



}
