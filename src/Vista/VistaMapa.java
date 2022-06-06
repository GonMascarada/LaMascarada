/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Util;
import java.awt.Color;

/**
 *
 * @author Alumno
 */
public class VistaMapa extends javax.swing.JFrame {

    
    private Controlador controlador;
    /**
     * Creates new form Mapa
     * @param controlador
     */
    public VistaMapa(Controlador controlador) {
        initComponents();
        this.controlador= controlador;
        Util.centrar(this);
      
         
        jButtonBiblioteca.setForeground(Color.white);
        jButtonBiblioteca.setBackground(Color.black);
        
        jButtonColegio.setForeground(Color.white);
        jButtonColegio.setBackground(Color.black);
        
        jButtonMansion.setForeground(Color.white);
        jButtonMansion.setBackground(Color.black);
        
        jButtonMotel.setForeground(Color.white);
        jButtonMotel.setBackground(Color.black);
        jButtonCasaabAbandonada.setForeground(Color.white);
        jButtonCasaabAbandonada.setBackground(Color.black);
        
        jButtonPub.setForeground(Color.white);
        jButtonPub.setBackground(Color.black);
        
        jButtonPeriodico.setForeground(Color.white);
        jButtonPeriodico.setBackground(Color.black);

        jButtonCallejonTienda.setForeground(Color.white);
        jButtonCallejonTienda.setBackground(Color.black);
        
        jButtonBar.setForeground(Color.white);
        jButtonBar.setBackground(Color.black);
        
        jButtonIglesia.setForeground(Color.white);
        jButtonIglesia.setBackground(Color.black);
        
        jButtonPlazaCentral.setForeground(Color.white);
        jButtonPlazaCentral.setBackground(Color.black);
        

    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonBiblioteca = new javax.swing.JButton();
        jButtonColegio = new javax.swing.JButton();
        jButtonMansion = new javax.swing.JButton();
        jButtonCasaabAbandonada = new javax.swing.JButton();
        jButtonPub = new javax.swing.JButton();
        jButtonPeriodico = new javax.swing.JButton();
        jButtonCallejonTienda = new javax.swing.JButton();
        jButtonBar = new javax.swing.JButton();
        jButtonIglesia = new javax.swing.JButton();
        jButtonPlazaCentral = new javax.swing.JButton();
        jButtonMotel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);

        jButtonBiblioteca.setText("Biblioteca");
        jButtonBiblioteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBibliotecaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBiblioteca);
        jButtonBiblioteca.setBounds(690, 600, 90, 50);

        jButtonColegio.setText("Colegio");
        jButtonColegio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColegioActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonColegio);
        jButtonColegio.setBounds(840, 110, 130, 40);

        jButtonMansion.setText("Mansión");
        jPanel1.add(jButtonMansion);
        jButtonMansion.setBounds(870, 550, 100, 40);

        jButtonCasaabAbandonada.setText("Casa abandonada");
        jButtonCasaabAbandonada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCasaabAbandonadaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCasaabAbandonada);
        jButtonCasaabAbandonada.setBounds(840, 280, 150, 40);

        jButtonPub.setText("Pub");
        jButtonPub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPubActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPub);
        jButtonPub.setBounds(310, 100, 70, 40);

        jButtonPeriodico.setText("Periodico");
        jButtonPeriodico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPeriodicoActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPeriodico);
        jButtonPeriodico.setBounds(170, 530, 120, 40);

        jButtonCallejonTienda.setText("Callejón");
        jButtonCallejonTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCallejonTiendaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCallejonTienda);
        jButtonCallejonTienda.setBounds(60, 80, 120, 40);

        jButtonBar.setText("Sally´s");
        jButtonBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBar);
        jButtonBar.setBounds(790, 410, 80, 40);

        jButtonIglesia.setText("Iglesia");
        jButtonIglesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIglesiaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonIglesia);
        jButtonIglesia.setBounds(180, 280, 120, 40);

        jButtonPlazaCentral.setText("Plaza central");
        jButtonPlazaCentral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlazaCentralActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonPlazaCentral);
        jButtonPlazaCentral.setBounds(520, 410, 120, 40);

        jButtonMotel.setText("Motel");
        jButtonMotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMotelActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonMotel);
        jButtonMotel.setBounds(310, 390, 90, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/mapa.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1000, 700);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBibliotecaActionPerformed
        controlador.cargarEscena(Util.ES_BIBLIOTECA);     
        this.dispose();
    }//GEN-LAST:event_jButtonBibliotecaActionPerformed

    private void jButtonMotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMotelActionPerformed
        controlador.cargarEscena(Util.ES_MOTEL);
        this.dispose();
    }//GEN-LAST:event_jButtonMotelActionPerformed

    private void jButtonPubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPubActionPerformed
        controlador.cargarEscena(Util.ES_PUB);
        this.dispose();
    }//GEN-LAST:event_jButtonPubActionPerformed

    private void jButtonIglesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIglesiaActionPerformed
        controlador.cargarEscena(Util.ES_IGLESIA); 
        this.dispose();
    }//GEN-LAST:event_jButtonIglesiaActionPerformed

    private void jButtonCallejonTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCallejonTiendaActionPerformed
        controlador.cargarEscena(Util.ES_CALLEJON); 
        this.dispose();
    }//GEN-LAST:event_jButtonCallejonTiendaActionPerformed

    private void jButtonPeriodicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPeriodicoActionPerformed
        controlador.cargarEscena(Util.ES_PERIODICO); 
        this.dispose();
    }//GEN-LAST:event_jButtonPeriodicoActionPerformed

    private void jButtonPlazaCentralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlazaCentralActionPerformed
        controlador.cargarEscena(Util.ES_PLAZA); 
        this.dispose();
    }//GEN-LAST:event_jButtonPlazaCentralActionPerformed

    private void jButtonBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarActionPerformed
        controlador.cargarEscena(Util.ES_BAR); 
        this.dispose();
    }//GEN-LAST:event_jButtonBarActionPerformed

    private void jButtonCasaabAbandonadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCasaabAbandonadaActionPerformed
        controlador.cargarEscena(Util.ES_CASA_ABANDONADA); 
        this.dispose();
    }//GEN-LAST:event_jButtonCasaabAbandonadaActionPerformed

    private void jButtonColegioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColegioActionPerformed
        controlador.cargarEscena(Util.ES_COLEGIO); 
        this.dispose();
    }//GEN-LAST:event_jButtonColegioActionPerformed

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
            java.util.logging.Logger.getLogger(VistaMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new VistaMapa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBar;
    private javax.swing.JButton jButtonBiblioteca;
    private javax.swing.JButton jButtonCallejonTienda;
    private javax.swing.JButton jButtonCasaabAbandonada;
    private javax.swing.JButton jButtonColegio;
    private javax.swing.JButton jButtonIglesia;
    private javax.swing.JButton jButtonMansion;
    private javax.swing.JButton jButtonMotel;
    private javax.swing.JButton jButtonPeriodico;
    private javax.swing.JButton jButtonPlazaCentral;
    private javax.swing.JButton jButtonPub;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
