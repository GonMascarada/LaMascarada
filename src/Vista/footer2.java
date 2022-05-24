/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Mascarada.Opcion;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Alumno
 */
public class footer2 extends javax.swing.JPanel{
    ImageIcon botonRojo1=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1.png"));
    ImageIcon botonRojo2=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2.png"));


    private ArrayList<JButton> botones;
    /**
     * Creates new form footer2
     */
    public footer2() {
        initComponents();
        
        
        opcion1.setRolloverEnabled(true);
        opcion1.setIcon(botonRojo1);
        opcion1.setPressedIcon(botonRojo2);

        opcion2.setRolloverEnabled(true);
        opcion2.setIcon(botonRojo1);
        opcion2.setPressedIcon(botonRojo2);

        
    }
    
    public void setOpciones(ArrayList <Opcion> opciones){
         botones = new ArrayList<>();
        botones.add(opcion1);
        botones.add(opcion2);
        for (int i = 0; i < botones.size(); i++) {
            botones.get(i).setText(opciones.get(i).getTexto());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcion2 = new javax.swing.JButton();
        opcion1 = new javax.swing.JButton();

        setOpaque(false);

        opcion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Sin título-1.png"))); // NOI18N
        opcion2.setText("jButton1");
        opcion2.setBorderPainted(false);
        opcion2.setContentAreaFilled(false);
        opcion2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        opcion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion2ActionPerformed(evt);
            }
        });

        opcion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Sin título-1.png"))); // NOI18N
        opcion1.setText("jButton1");
        opcion1.setContentAreaFilled(false);
        opcion1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        opcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(opcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcion2, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(opcion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void opcion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcion2ActionPerformed

    private void opcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcion1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton opcion1;
    private javax.swing.JButton opcion2;
    // End of variables declaration//GEN-END:variables
}
