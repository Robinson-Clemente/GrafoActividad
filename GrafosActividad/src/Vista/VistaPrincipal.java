package Vista;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class VistaPrincipal extends javax.swing.JFrame {

    ArrayList<JButton> botones = new ArrayList<>();

    public VistaPrincipal() {
        initComponents();

        setSize(886, 650);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void agregarBoton(double x, double y) {

        String name = JOptionPane.showInputDialog("Digite el nombre de la ubicación");
        JButton boton = new JButton();
        boton.setName(name);
        boton.setText(name);
        x = x - 294.5;
        y = y - 194;
        boton.setBounds((int) x, (int) y, 40, 40);
        botones.add(boton);
        panel1.add(boton);
        panel1.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel(){

            public void paintComponent(Graphics g){

                ImageIcon imagein = new ImageIcon("Imagenes/imagen.png");
                Image i = imagein.getImage();
                g.drawImage(i, 0, 0,this.getWidth(),this.getHeight(),this);
            }

        };
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 720, 560));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("CÁLCULO DE RUTAS - Sancochito");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 450, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MousePressed
        boolean estado = false;
        Point punto = MouseInfo.getPointerInfo().getLocation();
        //Equilibrador en X ES 272 y en Y 172 tamaño del panel 720X560
        int x =(int)punto.getX()-272;
        int y=(int)punto.getY()-172;
        if (botones.isEmpty()) {
            agregarBoton(punto.getX(), punto.getY());
        } else {
            //continuar
            for (JButton boton : botones) {
                int valorx = (x-boton.getX());
                int valory = (y-boton.getY()); 
                //RANGOS DE 0 Y 40 TANTO EN X y EN Y PARA CADA BOTON
                if (valorx < 70 & (valorx *-1 < 30)) {
                    estado = true;
                    break;
                }
            }
            if (estado == true) {
                JOptionPane.showMessageDialog(null, "Ubicación muy cercana, reposicione");
            } else {
                agregarBoton(punto.getX(), punto.getY());
            }

        }

    }//GEN-LAST:event_panel1MousePressed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel1;
    // End of variables declaration//GEN-END:variables
}
