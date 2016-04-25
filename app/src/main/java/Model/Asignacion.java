package Model;

/**
 * Created by Pablo on 24/04/2016.
 */
public class Asignacion {

    private String valorHoras;
    private Actividad actividad;

    public Asignacion(String pValorHoras, Actividad pActividad)
    {
        valorHoras = pValorHoras;
        actividad = pActividad;
    }

    public String getValorHoras()
    {
        return valorHoras;
    }

    public void setValorHoras(String pValorHoras)
    {
        valorHoras = pValorHoras;
    }

    public Actividad getActividad()
    {
        return actividad;
    }

    public void setActividad(Actividad pActividad)
    {
        actividad = pActividad;
    }

}
