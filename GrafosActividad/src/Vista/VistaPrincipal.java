package Vista;


import Controlador.ControlLogico;
import Controlador.ControlUbicacion;
import Modelo.Ubicacion;
import Modelo.Enlace;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;




public class VistaPrincipal extends javax.swing.JFrame {

    private static ArrayList<JButton> botones = new ArrayList<>();
    private ControlUbicacion controlub = ControlUbicacion.getInstancia();
    private ControlLogico contrologic = ControlLogico.getInstancia();
    private Ubicacion uno = null;
    private Ubicacion dos = null;
   

    public VistaPrincipal() {
        initComponents();      
        setSize(886, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarGrupo();
    }
    
    
    private void iniciarGrupo(){
        radioUno.setSelected(true);
        grupoboton.add(radioUno);
        grupoboton.add(radioDos);
    }
    
    public static int getSizeBotones(){
         int size = botones.size();

        return size;
    }
    
    private  void agregarBoton(double x, double y) {   
        
      String nombre="";
      int prioridad=0;
      boolean estado = false;
      
        while(estado==false){  
           try{
          nombre = JOptionPane.showInputDialog(null,"Digite el nombre(UN CARACTER)"); 
          int n = Integer.parseInt(nombre);
          if(n<=0 | n>=0){
              estado = false;
          }         
           }catch(NumberFormatException e){
           
              estado = true;
           }
        }
          estado=false;
          while(estado==false){  
           try{
          prioridad = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite la prioridad")); 
          String str = String.valueOf(prioridad); 
          estado = true;
           }catch(NumberFormatException w){
           
              estado = false;
           }
        }
               
        //Creamos Boton
        JButton boton = new JButton();       
        boton.setName(String.valueOf(nombre.charAt(0)));
        boton.setText(String.valueOf(nombre.charAt(0)));        
        // El -20 es para centrar en X y Y
        boton.setBounds((int) x-20, (int) y-20, 40, 40);
        boton.setBackground(Color.white);
        
        estado = false;
        for(JButton b : botones){
            if(boton.getName().equals(b.getName())){
                estado = true;
                break;
            }
            
        }
        
        if(estado){
             JOptionPane.showMessageDialog(null,"Ubicacion existente",null,2);
        }else{
            
        botones.add(boton);
        
        // Creamos la Ubicacion
        Ubicacion temp = new Ubicacion(nombre, x, y, prioridad,botones.size()-1);
        controlub.agregar(temp);        
        contrologic.agregarFila_Columna();       
        panel1.add(boton);
        
        // Creamos un action listener a el boton
        boton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              
              String nombre = boton.getName();
              int indice=0;
              for (int i = 0; i < botones.size(); i++) {
                 if(nombre.equals(botones.get(i).getName())){
                     indice = i;
                     break;
                 }                 
              }
              if(radioUno.isSelected()){
                  crearEnlance(controlub.getListaub().get(indice)); 
              }else if(radioDos.isSelected()){
                  // CODIGO DE RECORRIDO
                  
              }
              
               
          }
      });
    
        panel1.updateUI();
        }
    }

    
    private void crearUbicacion(int x, int y){
        
        boolean estado = false;  
        if (botones.isEmpty()) {
            agregarBoton(x,y);
        } else {
            
            for (JButton boton : botones) {
                int valorx = (x-boton.getX());
                int valory = (y-boton.getY()); 
  
                if ((valorx < 70 & (valorx *-1 < 30)) &(valory < 70 & (valory *-1 < 30))) {
                    estado = true;
                    break;
                }
            }
            if (estado == true) {
                JOptionPane.showMessageDialog(null, "Ubicación muy cercana, reposicione");
            } else {
                agregarBoton(x,y);
            }
        }
    }
    
    
    private void crearEnlance(Ubicacion element){
    
        // Como me dijo profe!!
        
        if(uno == null){            
           uno = element;
        }else if(uno!=null & dos==null){            
           dos = element;
          
         if(contrologic.getMatriz()[uno.getIndice()][dos.getIndice()].getDistancia()!=-1){
         
             JOptionPane.showMessageDialog(null,"El enlace ya existe",null,2);
              uno= null;
              dos= null;
         }else{
            // Realizamos el enlace
            
            //Calculamos la ditancia usando la formula del modulo de un vector
            // o distancia entre 2 puntos en el plano X,Y
            
            double distancia=0;
            double tiempoPare=0;
            double velocidadMax=0;
            double tiempo=0;
            double x = dos.getCoox() - uno.getCooy();
            double y = dos.getCooy() - uno.getCooy();
            x = Math.pow(x, 2);
            y = Math.pow(y, 2);
            distancia = x + y;
            Math.sqrt(distancia);

            boolean status = false;
            while(status == false) {
                try {
                    tiempoPare = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "Digite  tiempo de PARE"));
                    
                    velocidadMax = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "Digite velocidad Máxima"));
                    
                    String str = String.valueOf(tiempoPare);
                    status = true;
                } catch (NumberFormatException w) {
                    status = false;
                }
            }
            // Formula de movimiento uniforme rectilineo
            
            tiempo = distancia/velocidadMax;
            Enlace temp = new Enlace(distancia,velocidadMax,tiempoPare,true,tiempo+tiempoPare);
             JOptionPane.showMessageDialog(null,"Se enlanzará (Origen:)"+dos.getNombre()+" a"+
                    "(Destino:) "+uno.getNombre());
            String respuesta = JOptionPane.showInputDialog("¿desea un doble enlance?\n"+uno.getNombre()+"<---->"+dos.getNombre()
                    +"\n S/N");
            // SEGUIR AQUI!!
            if(respuesta.equalsIgnoreCase("S")){
            
                contrologic.agregarDobleEnlace(temp, uno.getIndice(), dos.getIndice());
               
            }else if(respuesta.equalsIgnoreCase("N")){
            
               contrologic.agregarEnlace(temp, uno.getIndice(), dos.getIndice());
               
            }
            uno= null;
            dos= null;
        }
        }      
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoboton = new javax.swing.ButtonGroup();
        panel1 = new javax.swing.JPanel(){

            public void paintComponent(Graphics g){

                ImageIcon imagein = new ImageIcon("Imagenes/imagen.png");
                Image i = imagein.getImage();
                g.drawImage(i, 0, 0,this.getWidth(),this.getHeight(),this);
            }

        };
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        radioUno = new javax.swing.JRadioButton();
        radioDos = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel1MousePressed(evt);
            }
        });

        label1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label1MousePressed(evt);
            }
        });

        label2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label2MousePressed(evt);
            }
        });

        label3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label3MousePressed(evt);
            }
        });

        label4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label4MousePressed(evt);
            }
        });

        label5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                label5MousePressed(evt);
            }
        });

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 720, 560));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("CÁLCULO DE RUTAS - Sancochito");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 450, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        radioUno.setText("MODO ENLACE");

        radioDos.setText("MODO RECORRIDO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioUno)
                    .addComponent(radioDos))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(radioUno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(radioDos)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 130, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MousePressed
          crearUbicacion(evt.getX(),evt.getY());          
    }//GEN-LAST:event_panel1MousePressed

    private void label1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label1MousePressed
           JOptionPane.showMessageDialog(null,"Espacio prohibido");
    }//GEN-LAST:event_label1MousePressed

    private void label3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label3MousePressed
           JOptionPane.showMessageDialog(null,"Espacio prohibido");
    }//GEN-LAST:event_label3MousePressed

    private void label2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label2MousePressed
           JOptionPane.showMessageDialog(null,"Espacio prohibido");
    }//GEN-LAST:event_label2MousePressed

    private void label4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label4MousePressed
           JOptionPane.showMessageDialog(null,"Espacio prohibido");
    }//GEN-LAST:event_label4MousePressed

    private void label5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label5MousePressed
           JOptionPane.showMessageDialog(null,"Espacio prohibido");
    }//GEN-LAST:event_label5MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
           JOptionPane.showMessageDialog(null,"Espacio prohibido");
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
           JOptionPane.showMessageDialog(null,"Espacio prohibido");
    }//GEN-LAST:event_jLabel3MousePressed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        
    }//GEN-LAST:event_formComponentMoved

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
      
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
    
     
    }//GEN-LAST:event_formMouseMoved
  
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup grupoboton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JPanel panel1;
    private javax.swing.JRadioButton radioDos;
    private javax.swing.JRadioButton radioUno;
    // End of variables declaration//GEN-END:variables
}
