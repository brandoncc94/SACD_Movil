package Model;

public class Actividad {
    private String id;
    private String tipo; //cur - invest - admin
    private String horas;


    public Actividad(String pId, String pTipo, String pHoras)
    {
        id = pId;
        tipo = pTipo;
        horas = pHoras;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String pId)
    {
        id = pId;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String pTipo)
    {
        tipo = pTipo;
    }

    public String getHoras()
    {
        return horas;
    }

    public void setHoras(String pHoras)
    {
        horas = pHoras;
    }

}
