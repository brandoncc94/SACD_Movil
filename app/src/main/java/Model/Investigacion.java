package Model;

/**
 * Created by Pablo on 24/04/2016.
 */
public class Investigacion extends Actividad{
    private String nombre;

    public Investigacion (String pId, String pTipo, String pHoras, String pNombre){
        super(pId, pTipo, pHoras);
        nombre = pNombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
}
