package Model;

/**
 * Created by Pablo on 24/04/2016.
 */
public class Grupo extends Actividad{
    private String id;
    private String nombre;
    private String numero;
    private String cantEstudiantes;

    public Grupo(String pId, String pNombre, String pTipo, String pHoras, String pNumero, String pCantEstudiantes)
    {
        super(pId, pTipo, pHoras);
        nombre = pNombre;
        numero = pNumero;
        cantEstudiantes = pCantEstudiantes;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String pNumero)
    {
        numero = pNumero;
    }

    public String getCantEstudiantes()
    {
        return cantEstudiantes;
    }

    public void setCantEstudiantes(String pCantEstudiantes)
    {
        cantEstudiantes = pCantEstudiantes;
    }

    public String getNombre()
    {
        return nombre;
    }
}
