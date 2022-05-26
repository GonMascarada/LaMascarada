/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Escena;
import Mascarada.Opcion;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

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
            case 6 -> {
                jPanel1.add(new footer6(controlador, this)).setBounds(0, 590, 1000, 115);
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
        cabecera1 = new Vista.Cabecera();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        foto.setText("jLabel1");
        jPanel1.add(foto);
        foto.setBounds(0, 260, 440, 300);

        texto.setBackground(new java.awt.Color(0, 0, 0));
        texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Fondo texto.png"))); // NOI18N
        texto.setText("Texto");
        jPanel1.add(texto);
        texto.setBounds(460, 260, 500, 300);
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
    private javax.swing.JLabel texto;
    // End of variables declaration//GEN-END:variables
}
