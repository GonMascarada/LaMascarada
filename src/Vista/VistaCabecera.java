/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Partida;
import Mascarada.Util;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Alumno
 */
public class VistaCabecera extends javax.swing.JPanel {

    private Controlador controlador;

    ImageIcon botonRojo1 = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1-4.png"));
    ImageIcon botonRojo2 = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2-4.png"));

    /**
     * origin Creates new form CabeceraImport
     *
     * @param controlador
     */
    public VistaCabecera(Controlador controlador) {
        initComponents();
        barraProgreso.setForeground(Color.white);
        barraSangre.setForeground(Color.red);
        barraSospecha.setForeground(Color.blue);
        barraVida.setForeground(Color.green);
        nombre.setForeground(Color.white);
        clan.setForeground(Color.white);
        habilidad1.setForeground(Color.white);
        habilidad2.setForeground(Color.white);
        hora.setForeground(Color.white);
        horaActual.setForeground(Color.white);
        sospecha.setForeground(Color.white);
        sedSangre.setForeground(Color.white);
        progreso.setForeground(Color.white);
        tiempoJugado.setForeground(Color.white);
        tiempoJugadoDato.setForeground(Color.white);
        vida.setForeground(Color.white);
        fichapersonaje.setForeground(Color.white);
        fichapersonaje.setBackground(Color.black);
        fichapersonaje.setIcon(botonRojo1);
        fichapersonaje.setPressedIcon(botonRojo2);
    }

    /**
     * Inserta y muestra los datos más relevantes de una partida.
     *
     * @param controlador
     */
    public void insertarDatosPartida(Controlador controlador) {
        Partida partida = controlador.getPartida();
        this.controlador = controlador;
        nombre.setText(partida.getProtagonista().getNombre());
        String[] habiliades = partida.getProtagonista().getHabilidades().split(";");
        habilidad1.setText(habiliades[0]);
        habilidad2.setText(habiliades[1]);
        clan.setText(partida.getProtagonista().getClan().getNombre());
        hora.setText(partida.getFecha() + "");
        barraSangre.setValue(partida.getSedDeSangre());
        barraSangre.setMaximum(partida.getDificultad());
        barraProgreso.setValue(partida.getProgreso());
        barraProgreso.setMaximum(partida.getDificultad());
        barraSospecha.setValue(partida.getSospecha());
        barraSospecha.setMaximum(partida.getDificultad());
        barraVida.setValue(partida.getProtagonista().getVidaActual());
        barraVida.setMaximum(partida.getProtagonista().getVidaMax());
        tiempoJugadoDato.setText(partida.getTiempo() + "");
        ImageIcon ImagenEscena = new javax.swing.ImageIcon(getClass().getResource(partida.getProtagonista().getClan().getImagen()));
        Image image = ImagenEscena.getImage();
        Image newimg = image.getScaledInstance(foto.getWidth(), foto.getHeight(), java.awt.Image.SCALE_SMOOTH);
        ImagenEscena = new ImageIcon(newimg);
        foto.setIcon(ImagenEscena);
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
        fichapersonaje = new javax.swing.JButton();
        foto = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        clan = new javax.swing.JLabel();
        habilidad1 = new javax.swing.JLabel();
        habilidad2 = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        horaActual = new javax.swing.JLabel();
        sedSangre = new javax.swing.JLabel();
        barraSangre = new javax.swing.JProgressBar();
        sospecha = new javax.swing.JLabel();
        barraSospecha = new javax.swing.JProgressBar();
        vida = new javax.swing.JLabel();
        barraVida = new javax.swing.JProgressBar();
        progreso = new javax.swing.JLabel();
        barraProgreso = new javax.swing.JProgressBar();
        tiempoJugado = new javax.swing.JLabel();
        tiempoJugadoDato = new javax.swing.JLabel();
        fondoCabecera = new javax.swing.JLabel();

        setOpaque(false);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        fichapersonaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Rojo2-4.png"))); // NOI18N
        fichapersonaje.setText("Ficha del personaje");
        fichapersonaje.setBorderPainted(false);
        fichapersonaje.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fichapersonaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fichapersonajeMouseClicked(evt);
            }
        });
        fichapersonaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fichapersonajeActionPerformed(evt);
            }
        });
        jPanel1.add(fichapersonaje);
        fichapersonaje.setBounds(520, 180, 360, 40);

        foto.setText("Foto");
        jPanel1.add(foto);
        foto.setBounds(0, 10, 210, 200);

        nombre.setText("Nombre");
        jPanel1.add(nombre);
        nombre.setBounds(270, 20, 194, 25);

        clan.setText("Clan");
        jPanel1.add(clan);
        clan.setBounds(270, 50, 194, 24);

        habilidad1.setText("Habilidad 1");
        jPanel1.add(habilidad1);
        habilidad1.setBounds(270, 80, 120, 30);

        habilidad2.setText("Habilidad 2");
        jPanel1.add(habilidad2);
        habilidad2.setBounds(270, 110, 120, 30);

        hora.setText("xx:xx");
        jPanel1.add(hora);
        hora.setBounds(330, 140, 60, 30);

        horaActual.setText("Hora:");
        jPanel1.add(horaActual);
        horaActual.setBounds(270, 140, 30, 30);

        sedSangre.setText("Sed de sangre:");
        jPanel1.add(sedSangre);
        sedSangre.setBounds(520, 20, 120, 30);

        barraSangre.setBackground(new java.awt.Color(0, 0, 0));
        barraSangre.setValue(50);
        jPanel1.add(barraSangre);
        barraSangre.setBounds(630, 20, 250, 30);

        sospecha.setText("Sospecha:");
        jPanel1.add(sospecha);
        sospecha.setBounds(520, 60, 120, 30);

        barraSospecha.setBackground(new java.awt.Color(0, 0, 0));
        barraSospecha.setValue(50);
        jPanel1.add(barraSospecha);
        barraSospecha.setBounds(630, 60, 250, 30);

        vida.setText("Vida:");
        jPanel1.add(vida);
        vida.setBounds(520, 100, 120, 30);

        barraVida.setBackground(new java.awt.Color(0, 0, 0));
        barraVida.setValue(50);
        jPanel1.add(barraVida);
        barraVida.setBounds(630, 100, 250, 30);

        progreso.setText("Progreso:");
        jPanel1.add(progreso);
        progreso.setBounds(520, 140, 120, 30);

        barraProgreso.setBackground(new java.awt.Color(0, 0, 0));
        barraProgreso.setValue(50);
        jPanel1.add(barraProgreso);
        barraProgreso.setBounds(630, 140, 250, 30);

        tiempoJugado.setText("Tiempo jugado:");
        jPanel1.add(tiempoJugado);
        tiempoJugado.setBounds(270, 170, 90, 30);

        tiempoJugadoDato.setText("xx:xx");
        jPanel1.add(tiempoJugadoDato);
        tiempoJugadoDato.setBounds(370, 170, 60, 30);

        fondoCabecera.setText("jLabel1");
        jPanel1.add(fondoCabecera);
        fondoCabecera.setBounds(0, 0, 1000, 220);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fichapersonajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fichapersonajeMouseClicked
        VistaFicha ficha;
        try {
            ficha = new VistaFicha(controlador, true);
            ficha.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(VistaCabecera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fichapersonajeMouseClicked

    private void fichapersonajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fichapersonajeActionPerformed
        VistaFicha ficha;
        try {
            ficha = new VistaFicha(controlador, true);
            ficha.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(VistaCabecera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fichapersonajeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JProgressBar barraSangre;
    private javax.swing.JProgressBar barraSospecha;
    private javax.swing.JProgressBar barraVida;
    private javax.swing.JLabel clan;
    private javax.swing.JButton fichapersonaje;
    private javax.swing.JLabel fondoCabecera;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel habilidad1;
    private javax.swing.JLabel habilidad2;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel horaActual;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel progreso;
    private javax.swing.JLabel sedSangre;
    private javax.swing.JLabel sospecha;
    private javax.swing.JLabel tiempoJugado;
    private javax.swing.JLabel tiempoJugadoDato;
    private javax.swing.JLabel vida;
    // End of variables declaration//GEN-END:variables

}
