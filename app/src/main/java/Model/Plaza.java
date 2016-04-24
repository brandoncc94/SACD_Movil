package Model;

/**
 * Created by Fabiola on 23/4/2016.
 */
public class Plaza {

    private int numero;
    private float horas;
    private String modo;

    public Plaza(int numero, float horas, String modo) {
        this.numero = numero;
        this.horas = horas;
        this.modo = modo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getHoras() {
        return horas;
    }

    public void setHoras(float horas) {
        this.horas = horas;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
}



