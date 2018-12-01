
package Modelo;


public class Ubicacion {
    
    private String nombre;
    private double coox;
    private double cooy;
    private int  prioridad;    
    private int indice;
    
    public Ubicacion(String nombre, double coox, double cooy, int prioridad, int indice){
    
        this.nombre = nombre;
        this.coox = coox;
        this.cooy = cooy;
        this.indice = indice;
    
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCoox() {
        return coox;
    }

    public void setCoox(double coox) {
        this.coox = coox;
    }

    public double getCooy() {
        return cooy;
    }

    public void setCooy(double cooy) {
        this.cooy = cooy;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    
    
    
}
