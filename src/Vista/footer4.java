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
public class footer4 extends javax.swing.JPanel {
     ImageIcon botonRojo1=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1-muyestrecho.png"));
    ImageIcon botonRojo2=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2-muyestrecho.png"));

    private ArrayList<JButton> botones;
    /**
     * Creates new form footer2
     */
    public footer4() {
        initComponents();
        opcion1.setRolloverEnabled(true);
        opcion1.setIcon(botonRojo1);
        opcion1.setPressedIcon(botonRojo2);
        opcion2.setRolloverEnabled(true);
        opcion2.setIcon(botonRojo1);
        opcion2.setPressedIcon(botonRojo2);
        opcion3.setRolloverEnabled(true);
        opcion3.setIcon(botonRojo1);
        opcion3.setPressedIcon(botonRojo2);
        opcion4.setRolloverEnabled(true);
        opcion4.setIcon(botonRojo1);
        opcion4.setPressedIcon(botonRojo2);
    }
       public void setOpciones(ArrayList <Opcion> opciones){
         botones = new ArrayList<>();
        botones.add(opcion1);
        botones.add(opcion2);
        botones.add(opcion3);
        botones.add(opcion4);
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
        opcion2 = new javax.swing.JButton();
        opcion3 = new javax.swing.JButton();
        opcion4 = new javax.swing.JButton();

        setOpaque(false);

        opcion1.setText("jButton1");
        opcion1.setBorderPainted(false);
        opcion1.setContentAreaFilled(false);

        opcion2.setText("jButton1");
        opcion2.setBorderPainted(false);
        opcion2.setContentAreaFilled(false);

        opcion3.setText("jButton1");
        opcion3.setBorderPainted(false);
        opcion3.setContentAreaFilled(false);

        opcion4.setText("jButton1");
        opcion4.setBorderPainted(false);
        opcion4.setContentAreaFilled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(opcion3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(opcion4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(opcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcion4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcion3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton opcion1;
    private javax.swing.JButton opcion2;
    private javax.swing.JButton opcion3;
    private javax.swing.JButton opcion4;
    // End of variables declaration//GEN-END:variables
}
