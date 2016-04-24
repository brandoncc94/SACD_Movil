package Model;

/**
 * Created by Fabiola on 23/4/2016.
 */
public class Plaza {

    private int numero;
    private double horas;
    private boolean isPropiedad;

    public Plaza(int numero, double horas, boolean isPropiedad) {
        this.numero = numero;
        this.horas = horas;
        this.isPropiedad = isPropiedad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public boolean getPropiedad() {
        return isPropiedad;
    }

    public void setPropiedad(boolean propiedad) {
        this.isPropiedad = propiedad;
    }
}



