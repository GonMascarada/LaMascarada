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
public class footer1 extends javax.swing.JPanel{
    
    ImageIcon botonRojo1=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1.png"));
    ImageIcon botonRojo2=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2.png"));

    private ArrayList<JButton> botones;
    /**
     * Creates new form footer2
     */
    public footer1() {
        initComponents();
        opcion1.setRolloverEnabled(true);
        opcion1.setIcon(botonRojo1);
        opcion1.setPressedIcon(botonRojo2);

    }
    
    public void setOpciones(ArrayList <Opcion> opciones){
         botones = new ArrayList<>();
        botones.add(opcion1);
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

        opcion1 = new javax.swing.JButton();

        setOpaque(false);

        opcion1.setBorderPainted(false);
        opcion1.setContentAreaFilled(false);
        opcion1.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton opcion1;
    // End of variables declaration//GEN-END:variables
}
