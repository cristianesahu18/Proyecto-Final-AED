/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
      
/**
 *
 * @author abel
 */
public class AjedrezApp extends javax.swing.JFrame {

    /**
     * Creates new form AjedrezApp
     */
       Timer TB=new Timer();
       Timer TN=new Timer();
       Timer Tturno=new Timer();
       
       int tb=0;
       int tn=0;
       Rey blanco,negro;
       boolean turno=true;
       int pasosreyblanco=0,pasosreynegro=0;
       int ganan=-1;
       
       JugadorVirtual jugadorVirtualnegro=null;
       JugadorVirtual jugadorVirtualblanco=null;
       Jugadores DialogJ;
       
       public AjedrezApp() {
        initComponents();
        Jugadores DialogJ=new Jugadores(this,true);
        DialogJ.setLocationRelativeTo(null);
        DialogJ.setVisible(true);
        
        blanco=new Rey(1,3,0,"rey blanco",null,this);
        blanco.rey=blanco;
        negro=new Rey(2,3,7,"rey negro",null,this);
        negro.rey=negro;
        if (DialogJ.negras.isSelected()) jugadorVirtualnegro=new JugadorVirtual(negro, this);
        if (DialogJ.blancas.isSelected())jugadorVirtualblanco=new JugadorVirtual(blanco, this);
        
        this.setLocationRelativeTo(null);
        
       jblanco.setBackground(Color.GREEN);
       jnegro.setBackground(Color.red);
       texto.setText("Es el turno de las blancas!!!!!\n");
       for(int x=0;x<8;x++)for(int y=2;y<6;y++) new NULA(0,x,y,"",blanco,this);    
              
       new Torre(1,0,0,"torre blanca",blanco,this);
       new Torre(1,7,0,"torre blanca",blanco,this);
       new Caballo(1,1,0,"caballo blanco",blanco,this);
       new Caballo(1,6,0,"caballo blanco",blanco,this); 
       new Alfil(1,2,0,"alfil blanco",blanco,this);
       new Alfil(1,5,0,"alfil blanco",blanco,this); 
       new Reina(1,4,0,"reina blanca",blanco,this);
       
       new  Peon(1,0,1,"peon blanco",blanco,this);
       new  Peon(1,1,1,"peon blanco",blanco,this);
       new  Peon(1,2,1,"peon blanco",blanco,this);
       new  Peon(1,3,1,"peon blanco",blanco,this);
       new  Peon(1,4,1,"peon blanco",blanco,this);
       new  Peon(1,5,1,"peon blanco",blanco,this);
       new  Peon(1,6,1,"peon blanco",blanco,this);
       new  Peon(1,7,1,"peon blanco",blanco,this);
       
       
       new Torre(2,0,7,"torre negra",negro,this);
       new Torre(2,7,7,"torre negra",negro,this);
       new Caballo(2,1,7,"caballo negro",negro,this);
       new Caballo(2,6,7,"caballo negro",negro,this); 
       new Alfil(2,2,7,"alfil negro",negro,this);
       new Alfil(2,5,7,"alfil negro",negro,this); 
       new Reina(2,4,7,"reina negra",negro,this);
       
       
       new  Peon(2,0,6,"peon negro",negro,this);
       new  Peon(2,1,6,"peon negro",negro,this);
       new  Peon(2,2,6,"peon negro",negro,this);
       new  Peon(2,3,6,"peon negro",negro,this);
       new  Peon(2,4,6,"peon negro",negro,this);
       new  Peon(2,5,6,"peon negro",negro,this);
       new  Peon(2,6,6,"peon negro",negro,this);
       new  Peon(2,7,6,"peon negro",negro,this);
       
       
      this.setSize(1200,830);
      tablero.setSize(8*95,8*95);
      this.validate();
      this.repaint();
      
      TimerTask tkb=new TimerTask() {public void run() {tb++;tiempoB.setText(tb+" seg");}};
      TB.scheduleAtFixedRate(tkb,0,1000);
      
      TimerTask task=new TimerTask() {public void run() {
          
          if (ganan!=-1) Tturno.cancel();
                                         try
    {if (!turno&&jugadorVirtualnegro!=null) jugadorVirtualnegro.juega();
    else if(turno&&jugadorVirtualblanco!=null) jugadorVirtualblanco.juega();
    }catch(Exception ex){ex.printStackTrace();}
     jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum()); 
     jScrollPane1.repaint();                              
      }};
      
      Tturno.scheduleAtFixedRate(task,0,2000);
      
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablero = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto = new javax.swing.JEditorPane();
        jblanco = new javax.swing.JButton();
        jnegro = new javax.swing.JButton();
        tiempoB = new javax.swing.JLabel();
        tiempoN = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setPreferredSize(new java.awt.Dimension(1200, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablero.setBackground(new java.awt.Color(0, 0, 0));
        tablero.setPreferredSize(new java.awt.Dimension(760, 800));
        tablero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(tablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 760));

        texto.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(texto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 360, 540));

        jblanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/rey blanco.png"))); // NOI18N
        jblanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jblancoActionPerformed(evt);
            }
        });
        getContentPane().add(jblanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 570, 150, 140));

        jnegro.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jnegro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figuras/rey negro.png"))); // NOI18N
        getContentPane().add(jnegro, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 570, 150, 140));

        tiempoB.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        getContentPane().add(tiempoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(824, 720, 130, 40));

        tiempoN.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        getContentPane().add(tiempoN, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 720, 140, 40));

        jPanel1.setLayout(new java.awt.GridLayout(8, 1));

        jLabel1.setText("1");
        jPanel1.add(jLabel1);

        jLabel2.setText("2");
        jPanel1.add(jLabel2);

        jLabel3.setText("3");
        jPanel1.add(jLabel3);

        jLabel4.setText("4");
        jPanel1.add(jLabel4);

        jLabel5.setText("5");
        jPanel1.add(jLabel5);

        jLabel6.setText("6");
        jPanel1.add(jLabel6);

        jLabel7.setText("7");
        jPanel1.add(jLabel7);

        jLabel8.setText("8");
        jPanel1.add(jLabel8);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 30, 760));

        jPanel2.setLayout(new java.awt.GridLayout(1, 8));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("A");
        jPanel2.add(jLabel10);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("B");
        jPanel2.add(jLabel11);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("C");
        jPanel2.add(jLabel12);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("D");
        jPanel2.add(jLabel13);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("E");
        jPanel2.add(jLabel14);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("F");
        jPanel2.add(jLabel9);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("G");
        jPanel2.add(jLabel15);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("H");
        jPanel2.add(jLabel16);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 760, 760, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jblancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jblancoActionPerformed
  
        // TODO add your handling code here:
    }//GEN-LAST:event_jblancoActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AjedrezApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjedrezApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjedrezApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjedrezApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             AjedrezApp a=new AjedrezApp();
             a.setVisible(true);
             //a.jugadorVirtualblanco.juega();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jblanco;
    public javax.swing.JButton jnegro;
    public javax.swing.JPanel tablero;
    public javax.swing.JEditorPane texto;
    public javax.swing.JLabel tiempoB;
    public javax.swing.JLabel tiempoN;
    // End of variables declaration//GEN-END:variables

}