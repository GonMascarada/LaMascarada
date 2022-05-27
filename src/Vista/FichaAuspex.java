/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Equipo;
import Mascarada.Partida;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Moru
 */
public class FichaAuspex extends javax.swing.JFrame {
    
    private Controlador controlador;
    private ArrayList<Equipo> equipo;

    /**
     * Creates new form FichaAuspex
     */
    public FichaAuspex() throws IOException {
        initComponents();
        controlador = new Controlador();
        //Lista de la informacion de los clanes
        equipo = controlador.getPartida().getProtagonista().getEquipacion();
        vidaBarra.setForeground(Color.green);
        ataqueBarra1.setForeground(Color.red);
        if(animoBarra.getValue()<=25) {
            animoBarra.setForeground(Color.red);
        }else if (animoBarra.getValue()>25&&animoBarra.getValue()<=75) {
            animoBarra.setForeground(Color.yellow);
        }else{
            animoBarra.setForeground(Color.green);
        }
        DefaultListModel listaAtaque = new DefaultListModel();
        DefaultListModel listaAmuletos = new DefaultListModel();
        DefaultListModel listaDefensivos = new DefaultListModel();
        DefaultListModel listaEspeciales = new DefaultListModel();
        
         for (int i = 0; i < equipo.size(); i++) {
            listaAtaque.addElement(equipo.get(i).getNombre());
            listaAmuletos.addElement(equipo.get(i).getNombre());
            listaDefensivos.addElement(equipo.get(i).getNombre());
            listaEspeciales.addElement(equipo.get(i).getNombre());

        }
        this.listaAmuletos.setModel(listaAmuletos);
        this.listaAtaque.setModel(listaAtaque);
        this.listaDefensivos.setModel(listaDefensivos);
        this.listaEspecial.setModel(listaEspeciales);
              
    }
     public void datosFichaAuspex( Partida partida){
        nombre.setText(partida.getProtagonista().getNombre());
        String[]hab1y2=partida.getProtagonista().getNombre().split(";");
        habilidad1.setText(hab1y2[1]);
        habilidad2.setText(hab1y2[2]);
        clan.setText(partida.getProtagonista().getClan().getNombre());
        ataqueBarra1.setValue(partida.getProtagonista().getAtaque());
        vidaBarra.setMaximum(partida.getProtagonista().getVidaMax());
        vidaBarra.setValue(partida.getProtagonista().getVidaActual());
        animoBarra.setValue(partida.getProtagonista().getEstadoDeAnimo());
        
        
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
        objetoEspecial = new javax.swing.JLabel();
        clan = new javax.swing.JLabel();
        habilidad1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaEspecial = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAtaque = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaDefensivos = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaAmuletos = new javax.swing.JList<>();
        ataquePuntos = new javax.swing.JLabel();
        amuletos = new javax.swing.JLabel();
        ataque = new javax.swing.JLabel();
        defensivo = new javax.swing.JLabel();
        habilidad2 = new javax.swing.JLabel();
        estadoAnimo = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        vidaBarra = new javax.swing.JProgressBar();
        animoBarra = new javax.swing.JProgressBar();
        vida1 = new javax.swing.JLabel();
        ataqueBarra1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        foto.setText("Foto");
        jPanel1.add(foto);
        foto.setBounds(10, 10, 180, 190);

        objetoEspecial.setText("Objetos Especiales");
        jPanel1.add(objetoEspecial);
        objetoEspecial.setBounds(750, 250, 120, 20);

        clan.setText("Clan");
        jPanel1.add(clan);
        clan.setBounds(230, 80, 120, 20);

        habilidad1.setText("Habilidad1");
        jPanel1.add(habilidad1);
        habilidad1.setBounds(230, 120, 120, 20);

        listaEspecial.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaEspecial);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(670, 270, 310, 420);

        listaAtaque.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaAtaque);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(20, 270, 310, 420);

        listaDefensivos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listaDefensivos);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(340, 270, 320, 420);

        listaAmuletos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listaAmuletos);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(370, 30, 600, 170);

        ataquePuntos.setText("Ataque");
        jPanel1.add(ataquePuntos);
        ataquePuntos.setBounds(20, 210, 120, 20);

        amuletos.setText("Amuletos");
        jPanel1.add(amuletos);
        amuletos.setBounds(590, 10, 120, 20);

        ataque.setText("Objetos de Ataque");
        jPanel1.add(ataque);
        ataque.setBounds(110, 250, 120, 20);

        defensivo.setText("Objetos Defensivos");
        jPanel1.add(defensivo);
        defensivo.setBounds(450, 250, 120, 20);

        habilidad2.setText("Habilidad2");
        jPanel1.add(habilidad2);
        habilidad2.setBounds(230, 160, 120, 20);

        estadoAnimo.setText("Estado de animo");
        jPanel1.add(estadoAnimo);
        estadoAnimo.setBounds(600, 210, 110, 20);

        nombre.setText("Nombre");
        jPanel1.add(nombre);
        nombre.setBounds(230, 40, 60, 20);
        jPanel1.add(vidaBarra);
        vidaBarra.setBounds(70, 210, 220, 19);
        jPanel1.add(animoBarra);
        animoBarra.setBounds(700, 210, 270, 19);

        vida1.setText("Vida");
        jPanel1.add(vida1);
        vida1.setBounds(310, 210, 50, 20);
        jPanel1.add(ataqueBarra1);
        ataqueBarra1.setBounds(350, 210, 230, 19);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FichaAuspex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FichaAuspex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FichaAuspex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FichaAuspex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FichaAuspex().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FichaAuspex.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amuletos;
    private javax.swing.JProgressBar animoBarra;
    private javax.swing.JLabel ataque;
    private javax.swing.JProgressBar ataqueBarra1;
    private javax.swing.JLabel ataquePuntos;
    private javax.swing.JLabel clan;
    private javax.swing.JLabel defensivo;
    private javax.swing.JLabel estadoAnimo;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel habilidad1;
    private javax.swing.JLabel habilidad2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listaAmuletos;
    private javax.swing.JList<String> listaAtaque;
    private javax.swing.JList<String> listaDefensivos;
    private javax.swing.JList<String> listaEspecial;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel objetoEspecial;
    private javax.swing.JLabel vida1;
    private javax.swing.JProgressBar vidaBarra;
    // End of variables declaration//GEN-END:variables
}
