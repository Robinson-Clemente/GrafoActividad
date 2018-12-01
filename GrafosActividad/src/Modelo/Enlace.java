
package Modelo;


public class Enlace {
    
    private double distancia;
    private double velocidadMax;
    private double tiempoPare;
    private boolean estado;
    
        
    // Para evitar los nullpointer al recorrer la matriz
    
   public Enlace(double distancia, double velocidadMax, double tiempoPare, boolean estado){
   
       this.distancia= distancia;
       this.velocidadMax = velocidadMax;
       this.tiempoPare = tiempoPare;
       this.estado = estado;
   
   }
    
    public Enlace(){    
    distancia=-1;
    velocidadMax=-1;
    tiempoPare=-1;
    estado=false;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(double velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public double getTiempoPare() {
        return tiempoPare;
    }

    public void setTiempoPare(double tiempoPare) {
        this.tiempoPare = tiempoPare;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    

    
    
}
