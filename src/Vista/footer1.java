/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Opcion;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Alumno
 */
public class footer1 extends javax.swing.JPanel{
    
    ImageIcon botonRojo1=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1.png"));
    ImageIcon botonRojo2=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2.png"));
    
    private Controlador controlador;
    private VistaEscena vista;
    private ArrayList<Opcion> opciones;


    /**
     * Creates new form footer2
     */
    public footer1(Controlador controlador, VistaEscena vista) {
        initComponents();
        this.controlador = controlador;
        this.vista = vista;
        this.opciones = controlador.getPartida().getEscena().getOpciones();
        
        opcion1.setRolloverEnabled(true);
        opcion1.setIcon(botonRojo1);
        opcion1.setPressedIcon(botonRojo2);
        opcion1.setText(opciones.get(0).getTexto());
        opcion1.setForeground(Color.white);

    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcion1 = new javax.swing.JButton();

        setOpaque(false);

        opcion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Rojo1.png"))); // NOI18N
        opcion1.setBorderPainted(false);
        opcion1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        opcion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcion1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(376, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void opcion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcion1MouseClicked
        // TODO add your handling code here:
        controlador.escoger(opciones.get(0));
        vista.dispose();
    }//GEN-LAST:event_opcion1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton opcion1;
    // End of variables declaration//GEN-END:variables
}
