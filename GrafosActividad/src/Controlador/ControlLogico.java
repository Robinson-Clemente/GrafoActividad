

package Controlador;

// Control Logico de Enlace

import Modelo.Enlace;


public class ControlLogico {
    
    private Enlace[][] matriz = null;
    
    private ControlLogico(){}
    
    private static ControlLogico instancia = null;
    
    
    public static ControlLogico getInstancia(){
    
        if(instancia==null){
        
           instancia = new ControlLogico();
        
        }
       return instancia;
    }
    
    
    public  void agregarFila_Columna(){
    // Primero llenamos con datos por defualt la matriz (Evitar NullPointer)       
  
        Enlace nueva[][] = new Enlace[matriz.length + 1][matriz[0].length + 1];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                nueva[i][j] = new Enlace();
            }
        }
        
        
        if(matriz != null) {
            // Matriz a Nueva
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    nueva[i][j] = matriz[i][j];
                }
            }

        }

        matriz = nueva;
    }

    public Enlace[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Enlace[][] matriz) {
        this.matriz = matriz;
    }
    
    
    
    
    
    
}
