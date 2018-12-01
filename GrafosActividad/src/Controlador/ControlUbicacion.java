
package Controlador;

import Modelo.Ubicacion;
import java.util.ArrayList;


public class ControlUbicacion {
    
  private ArrayList<Ubicacion> listaub = new ArrayList<>();

    public ArrayList<Ubicacion> getListaub() {
        return listaub;       
    }

    public void setListaub(ArrayList<Ubicacion> listaub) {
        this.listaub = listaub;
    }
      
    
    public void agregar (Ubicacion element){
    
       listaub.add(element);
    
    }
    
  private ControlUbicacion(){} 
    
    private static  ControlUbicacion instancia = null;
            
    public static ControlUbicacion getInstancia(){
    
      if(instancia==null){
      
        instancia = new ControlUbicacion();
      }
    
    return instancia;
    }


}     
    

