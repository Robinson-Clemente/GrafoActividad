package Controlador;

// Control Logico de Enlace
import Modelo.Enlace;
import Vista.VistaPrincipal;

public class ControlLogico {

    private Enlace[][] matriz = new Enlace[1][1];

    private ControlLogico() {
    }

    private static ControlLogico instancia = null;

    public static ControlLogico getInstancia() {

        if (instancia == null) {
            instancia = new ControlLogico();
        }
        return instancia;
    }

//    
//    private void inicializarMatriz(){
//    
//        for (int i = 0; i < matriz.length; i++) {
//            for (int j = 0; j < matriz[0].length; j++) {
//                matriz[i][j] = new Enlace();
//            }
//        }
//    
//    }
    public void agregarEnlace(Enlace enlace, int uno, int dos) {
        matriz[uno][dos] = enlace;
    }

    public void agregarDobleEnlace(Enlace enlace, int uno, int dos) {
        matriz[uno][dos] = enlace;
        matriz[dos][uno] = enlace;
    }

    public void agregarFila_Columna() {
        // Primero llenamos con datos por defualt la matriz (Evitar NullPointer)    
        Enlace nueva[][] = new Enlace[1][1];
        nueva[0][0] = new Enlace();

        if (!(VistaPrincipal.getSizeBotones() <= 1)) {
            nueva = new Enlace[matriz.length + 1][matriz[0].length + 1];
            for (int i = 0; i < nueva.length; i++) {
                for (int j = 0; j < nueva[0].length; j++) {
                    nueva[i][j] = new Enlace();
                }
            }

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
