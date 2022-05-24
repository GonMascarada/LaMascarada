/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Escena;
import Mascarada.Opcion;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Moru
 */
public class VistaEscena extends javax.swing.JFrame {

    private JPanel footer;
    private Controlador controlador;
    private Escena escena;

    public VistaEscena() {
        initComponents();
        int opciones = 2;
        switch (opciones) {
            case 1:
                footer = new Vista.footer1();
                
                break;
            case 2:
                footer = new Vista.footer2();
                break;
            case 3:
                footer = new Vista.footer3();
                
                break;
            case 4:
                footer = new Vista.footer4();
                break;
            case 6:
                footer = new Vista.footer6();
                break;
        }

        jPanel1.add(footer);
        footer.setBounds(0, 590, 1000, 115);
    }

    public VistaEscena(Controlador controlador) {
        initComponents();
        this.controlador = controlador;
        this.escena = controlador.getPartida().getEscena();
        ArrayList<Opcion> opciones = escena.getOpciones();

        texto.setText(escena.getTexto());
        texto.setBackground(Color.red);
        cabecera1.insertarDatosPartida(controlador.getPartida());
        switch (opciones.size()) {
            case 1 -> {
                footer = new footer1();
                footer1 f = (footer1) footer;
                f.setOpciones(opciones);
                f.setVisible(true);
            }
            case 2 -> {
                footer = new footer2();
                footer2 f = (footer2) footer;
                f.setOpciones(opciones);
            }
            case 3 -> {
                footer = new footer3();
                footer3 f = (footer3) footer;
                f.setOpciones(controlador, this);
            }
            case 4 -> {
                footer = new footer4();
                footer4 f = (footer4) footer;
                f.setOpciones(opciones);
            }
            case 6 -> {
                footer = new footer6();
                footer6 f = (footer6) footer;
                f.setOpciones(opciones);
            }
        }
        jPanel1.add(footer);
        footer.setBounds(0, 590, 1000, 115);
        footer.setVisible(true);
        footer.setLayout(null);
        footer.setVisible(true);
        
        
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
        foto = new javax.swing.JLabel();
        texto = new javax.swing.JLabel();
        cabecera1 = new Vista.Cabecera();
        fondo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        foto.setText("jLabel1");
        jPanel1.add(foto);
        foto.setBounds(0, 200, 440, 380);

        texto.setText("Texto");
        jPanel1.add(texto);
        texto.setBounds(460, 210, 500, 380);
        jPanel1.add(cabecera1);
        cabecera1.setBounds(0, 0, 1000, 220);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/fondoEscenas.jpg"))); // NOI18N
        fondo.setText("jLabel1");
        jPanel1.add(fondo);
        fondo.setBounds(0, 0, 1000, 700);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 590, 1000, 110);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaEscena().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vista.Cabecera cabecera1;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel foto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel texto;
    // End of variables declaration//GEN-END:variables
}
