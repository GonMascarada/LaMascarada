/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Mascarada.Partida;
import java.awt.Color;

/**
 *
 * @author Alumno
 */
public class CargaBorrar extends javax.swing.JPanel {

    /**
     * Creates new form CabeceraImport
     */
    public CargaBorrar() {
        initComponents();
        barraProgreso.setForeground(Color.white);
        barraSangre.setForeground(Color.red);
        barraSospecha.setForeground(Color.blue);
        barraVida.setForeground(Color.green);
    }
 public void datosPartida( Partida partida){
        nombre.setText(partida.getProtagonista().getNombre());
        String[]hab1y2=partida.getProtagonista().getNombre().split(";");
        habilidad1.setText(hab1y2[1]);
        habilidad2.setText(hab1y2[2]);
        clan.setText(partida.getProtagonista().getClan().getNombre());
        hora1.setText(partida.getFecha()+"");
        barraSangre.setValue(partida.getSedDeSangre());
        barraProgreso.setValue(partida.getProgreso());
        barraSospecha.setValue(partida.getSospecha());
        barraVida.setValue(partida.getVidaProtagonista());
        tiempoJugado.setText(partida.getTiempo()+"");
        
        
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
        nombre = new javax.swing.JLabel();
        clan = new javax.swing.JLabel();
        habilidad1 = new javax.swing.JLabel();
        habilidad2 = new javax.swing.JLabel();
        tiempoJugado = new javax.swing.JLabel();
        horaJuego = new javax.swing.JLabel();
        SedSangre = new javax.swing.JLabel();
        barraSangre = new javax.swing.JProgressBar();
        Sospecha = new javax.swing.JLabel();
        barraSospecha = new javax.swing.JProgressBar();
        Vida = new javax.swing.JLabel();
        barraVida = new javax.swing.JProgressBar();
        Progreso = new javax.swing.JLabel();
        barraProgreso = new javax.swing.JProgressBar();
        tiempojuego = new javax.swing.JLabel();
        seleccionar = new javax.swing.JButton();
        hora1 = new javax.swing.JLabel();

        jPanel1.setLayout(null);

        foto.setText("Foto");
        jPanel1.add(foto);
        foto.setBounds(0, 10, 250, 200);

        nombre.setText("Nombre");
        jPanel1.add(nombre);
        nombre.setBounds(280, 10, 194, 25);

        clan.setText("Clan");
        jPanel1.add(clan);
        clan.setBounds(280, 40, 194, 24);

        habilidad1.setText("Habilidad 1");
        jPanel1.add(habilidad1);
        habilidad1.setBounds(280, 70, 120, 30);

        habilidad2.setText("Habilidad 2");
        jPanel1.add(habilidad2);
        habilidad2.setBounds(280, 100, 120, 30);

        tiempoJugado.setText("xx:xx");
        jPanel1.add(tiempoJugado);
        tiempoJugado.setBounds(400, 170, 60, 30);

        horaJuego.setText("Hora:");
        jPanel1.add(horaJuego);
        horaJuego.setBounds(280, 140, 30, 30);

        SedSangre.setText("Sed de sangre:");
        jPanel1.add(SedSangre);
        SedSangre.setBounds(520, 20, 120, 30);

        barraSangre.setToolTipText("");
        barraSangre.setValue(50);
        jPanel1.add(barraSangre);
        barraSangre.setBounds(630, 20, 250, 30);

        Sospecha.setText("Sospecha:");
        jPanel1.add(Sospecha);
        Sospecha.setBounds(520, 60, 120, 30);

        barraSospecha.setValue(50);
        jPanel1.add(barraSospecha);
        barraSospecha.setBounds(630, 60, 250, 30);

        Vida.setText("Vida:");
        jPanel1.add(Vida);
        Vida.setBounds(520, 100, 120, 30);

        barraVida.setValue(50);
        jPanel1.add(barraVida);
        barraVida.setBounds(630, 100, 250, 30);

        Progreso.setText("Progreso:");
        jPanel1.add(Progreso);
        Progreso.setBounds(520, 140, 120, 30);

        barraProgreso.setValue(50);
        jPanel1.add(barraProgreso);
        barraProgreso.setBounds(630, 140, 250, 30);

        tiempojuego.setText("Tiempo de juego");
        jPanel1.add(tiempojuego);
        tiempojuego.setBounds(280, 180, 110, 20);

        seleccionar.setText("Seleccionar");
        jPanel1.add(seleccionar);
        seleccionar.setBounds(830, 180, 140, 40);

        hora1.setText("xx:xx");
        jPanel1.add(hora1);
        hora1.setBounds(330, 140, 60, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Progreso;
    private javax.swing.JLabel SedSangre;
    private javax.swing.JLabel Sospecha;
    private javax.swing.JLabel Vida;
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JProgressBar barraSangre;
    private javax.swing.JProgressBar barraSospecha;
    private javax.swing.JProgressBar barraVida;
    private javax.swing.JLabel clan;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel habilidad1;
    private javax.swing.JLabel habilidad2;
    private javax.swing.JLabel hora1;
    private javax.swing.JLabel horaJuego;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nombre;
    private javax.swing.JButton seleccionar;
    private javax.swing.JLabel tiempoJugado;
    private javax.swing.JLabel tiempojuego;
    // End of variables declaration//GEN-END:variables
}
