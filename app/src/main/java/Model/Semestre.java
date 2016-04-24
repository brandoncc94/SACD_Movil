package Model;

public class Semestre {
    private String id;
    private String anio;
    private String periodo;

    public Semestre (String pId, String pAnio, String pPeriodo){
        id = pId;
        anio = pAnio;
        periodo = pPeriodo;
    }

    public String getId(){
        return id;
    }

    public String getAnio(){
        return anio;
    }

    public String getPeriodo(){
        return periodo;
    }
}
