/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Escena;
import Mascarada.Opcion;
import Mascarada.Util;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Moru
 * @author Gonzalo López Fernández
 */
public class VistaEscena extends javax.swing.JFrame {
    
    ImageIcon FondoTexto=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo texto.png"));
    private Controlador controlador;
    private Escena escena;

    public VistaEscena() {
        initComponents();
        Util.centrar(this);
    }

    public VistaEscena(Controlador controlador) {
        initComponents();
        this.controlador = controlador;
        this.escena = controlador.getPartida().getEscena();
        ArrayList<Opcion> opciones = escena.getOpciones();
        
        ImageIcon ImagenEscena=new javax.swing.ImageIcon(getClass().getResource(controlador.getPartida().getEscena().getImagen()));
        Image image = ImagenEscena.getImage();
        Image newimg = image.getScaledInstance( foto.getWidth(),foto.getHeight(),  java.awt.Image.SCALE_SMOOTH); 
        ImagenEscena = new ImageIcon(newimg);
        foto.setIcon(ImagenEscena);
                
        texto.setText(escena.getTexto());
        texto.setIcon(FondoTexto);
        texto.setForeground(Color.white);
        cabecera1.insertarDatosPartida(controlador.getPartida());
        switch (opciones.size()) {
            case 1 -> {
                 jPanel1.add(new footer1(controlador, this)).setBounds(0, 590, 1000, 115);
                 
            }
            case 2 -> {
                jPanel1.add(new footer2(controlador, this)).setBounds(0, 590, 1000, 115);
            }
            case 3 -> {
                jPanel1.add(new footer3(controlador, this)).setBounds(0, 590, 1000, 115);
            }
            case 4 -> {
                jPanel1.add(new footer4(controlador, this)).setBounds(0, 590, 1000, 115);
            }
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

        jPanel1 = new javax.swing.JPanel();
        foto = new javax.swing.JLabel();
        texto = new javax.swing.JLabel();
        cabecera1 = new Vista.VistaCabecera();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setLayout(null);

        foto.setText("jLabel1");
        jPanel1.add(foto);
        foto.setBounds(30, 260, 400, 300);

        texto.setBackground(new java.awt.Color(0, 0, 0));
        texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Fondo texto.png"))); // NOI18N
        texto.setText("Texto");
        texto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        texto.setInheritsPopupMenu(false);
        jPanel1.add(texto);
        texto.setBounds(480, 260, 490, 300);
        jPanel1.add(cabecera1);
        cabecera1.setBounds(0, 0, 1000, 220);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/fondoEscenas.jpg"))); // NOI18N
        fondo.setText("jLabel1");
        jPanel1.add(fondo);
        fondo.setBounds(0, 0, 1000, 700);

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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.out.println("Dew");
    }//GEN-LAST:event_formWindowClosed

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
    private Vista.VistaCabecera cabecera1;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel foto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel texto;
    // End of variables declaration//GEN-END:variables
}
