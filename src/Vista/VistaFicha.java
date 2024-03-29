package Vista;

import Controlador.Controlador;
import Mascarada.Equipo;
import Mascarada.Persona;
import Mascarada.Util;
import Mascarada.Vampire;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Moru
 * @author Gonzalo López Fernández
 */
public class VistaFicha extends javax.swing.JFrame {
    ImageIcon botonRojo1 = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1.png"));
    ImageIcon botonRojo2 = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2.png"));


    private Controlador controlador;
    private Persona personaje;

    /**
     * Creates new form FichaAuspex
     *
     * @param controlador
     * @param esProtagonista
     * @throws java.io.IOException
     */
    public VistaFicha(Controlador controlador, boolean esProtagonista) throws IOException {
        initComponents();
        Util.centrar(this);
        
        jButtonsSalir.setRolloverEnabled(true);
        jButtonsSalir.setIcon(botonRojo1);
        jButtonsSalir.setPressedIcon(botonRojo2);
        jButtonsSalir.setForeground(Color.white);
        jButtonsSalir.setBackground(Color.black);
        
        this.controlador = controlador;
        if (esProtagonista) {
            personaje = controlador.getPartida().getProtagonista();
        } else {
            personaje = controlador.getPartida().getEscena().getPnj();
            labelEstadoAnimo.setVisible(true);
            estadoAnimoDato.setVisible(true);
            switch (personaje.getEstadoDeAnimo()) {
                case Util.EA_AGRADECIDO ->
                    estadoAnimoDato.setText("Contento");
                case Util.EA_ENFADADO ->
                    estadoAnimoDato.setText("Enfadado");
                case Util.EA_NORMAL ->
                    estadoAnimoDato.setText("Normal");
            }
            jListAmuletos.setEnabled(false);
            jListAtaque.setEnabled(false);
            jListDefensivos.setEnabled(false);
            jListEspecial.setEnabled(false);
        }
        iniciarComponentes();
        mostrarEquipacion();
        nombre.setText(personaje.getNombre());
        dineroDato.setText(personaje.getDinero() + "");
        actualizarEstadisticasBasicas();

        if (personaje instanceof Vampire vampire) {
            String[] habiliades = (vampire.getHabilidades().split(";"));
            habilidad1.setText(habiliades[0]);
            habilidad2.setText(habiliades[1]);
            clan.setText(vampire.getClan().getNombre());
            ImageIcon ImagenEscena = new javax.swing.ImageIcon(getClass().getResource(vampire.getClan().getImagen()));
            Image image = ImagenEscena.getImage();
            Image newimg = image.getScaledInstance(foto.getWidth(), foto.getHeight(), java.awt.Image.SCALE_SMOOTH);
            ImagenEscena = new ImageIcon(newimg);
            foto.setIcon(ImagenEscena);
        } else {
            habilidad1.setVisible(false);
            habilidad2.setVisible(false);
            clan.setText("Humano");
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
        jLabel2 = new javax.swing.JLabel();
        objetoEspecial = new javax.swing.JLabel();
        clan = new javax.swing.JLabel();
        habilidad1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListEspecial = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListAtaque = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListDefensivos = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListAmuletos = new javax.swing.JList<>();
        amuletos = new javax.swing.JLabel();
        ataque = new javax.swing.JLabel();
        defensivo = new javax.swing.JLabel();
        dineroDato = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        habilidad2 = new javax.swing.JLabel();
        dinero = new javax.swing.JLabel();
        labelEstadoAnimo = new javax.swing.JLabel();
        defensaDato = new javax.swing.JLabel();
        estadoAnimoDato = new javax.swing.JLabel();
        vidaMaxDato = new javax.swing.JLabel();
        labelVidaMax = new javax.swing.JLabel();
        labelAtaque = new javax.swing.JLabel();
        ataqueDato = new javax.swing.JLabel();
        labelVida = new javax.swing.JLabel();
        vidaDato = new javax.swing.JLabel();
        jButtonsSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        foto.setText("Foto");
        jPanel1.add(foto);
        foto.setBounds(10, 10, 180, 190);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Defensa");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(420, 50, 110, 30);

        objetoEspecial.setForeground(new java.awt.Color(255, 255, 255));
        objetoEspecial.setText("Objetos Especiales");
        jPanel1.add(objetoEspecial);
        objetoEspecial.setBounds(800, 270, 120, 20);

        clan.setBackground(new java.awt.Color(0, 0, 0));
        clan.setForeground(new java.awt.Color(255, 255, 255));
        clan.setText("Clan");
        jPanel1.add(clan);
        clan.setBounds(240, 50, 120, 20);

        habilidad1.setBackground(new java.awt.Color(0, 0, 0));
        habilidad1.setForeground(new java.awt.Color(255, 255, 255));
        habilidad1.setText("Habilidad1");
        jPanel1.add(habilidad1);
        habilidad1.setBounds(240, 80, 120, 20);

        jListEspecial.setBackground(new java.awt.Color(0, 0, 0));
        jListEspecial.setForeground(new java.awt.Color(255, 255, 255));
        jListEspecial.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListEspecial);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(770, 300, 200, 350);

        jListAtaque.setBackground(new java.awt.Color(0, 0, 0));
        jListAtaque.setForeground(new java.awt.Color(255, 255, 255));
        jListAtaque.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListAtaque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListAtaqueMouseClicked(evt);
            }
        });
        jListAtaque.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListAtaqueValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListAtaque);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(30, 300, 200, 350);

        jListDefensivos.setBackground(new java.awt.Color(0, 0, 0));
        jListDefensivos.setForeground(new java.awt.Color(255, 255, 255));
        jListDefensivos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListDefensivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListDefensivosMouseClicked(evt);
            }
        });
        jListDefensivos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListDefensivosValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jListDefensivos);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(270, 300, 200, 350);

        jListAmuletos.setBackground(new java.awt.Color(0, 0, 0));
        jListAmuletos.setForeground(new java.awt.Color(255, 255, 255));
        jListAmuletos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListAmuletos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListAmuletosMouseClicked(evt);
            }
        });
        jListAmuletos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListAmuletosValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(jListAmuletos);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(520, 300, 200, 350);

        amuletos.setForeground(new java.awt.Color(255, 255, 255));
        amuletos.setText("Amuletos");
        jPanel1.add(amuletos);
        amuletos.setBounds(560, 270, 120, 20);

        ataque.setForeground(new java.awt.Color(255, 255, 255));
        ataque.setText("Objetos de Ataque");
        jPanel1.add(ataque);
        ataque.setBounds(70, 270, 120, 20);

        defensivo.setForeground(new java.awt.Color(255, 255, 255));
        defensivo.setText("Objetos Defensivos");
        jPanel1.add(defensivo);
        defensivo.setBounds(320, 270, 120, 20);

        dineroDato.setBackground(new java.awt.Color(0, 0, 0));
        dineroDato.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(dineroDato);
        dineroDato.setBounds(290, 150, 50, 20);

        nombre.setBackground(new java.awt.Color(0, 0, 0));
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setText("Nombre");
        jPanel1.add(nombre);
        nombre.setBounds(230, 20, 60, 20);

        habilidad2.setBackground(new java.awt.Color(0, 0, 0));
        habilidad2.setForeground(new java.awt.Color(255, 255, 255));
        habilidad2.setText("Habilidad2");
        jPanel1.add(habilidad2);
        habilidad2.setBounds(240, 110, 120, 20);

        dinero.setBackground(new java.awt.Color(0, 0, 0));
        dinero.setForeground(new java.awt.Color(255, 255, 255));
        dinero.setText("Dinero");
        jPanel1.add(dinero);
        dinero.setBounds(240, 150, 50, 20);

        labelEstadoAnimo.setBackground(new java.awt.Color(0, 0, 0));
        labelEstadoAnimo.setForeground(new java.awt.Color(255, 255, 255));
        labelEstadoAnimo.setText("Estado de animo:");
        jPanel1.add(labelEstadoAnimo);
        labelEstadoAnimo.setBounds(420, 140, 110, 30);

        defensaDato.setBackground(new java.awt.Color(0, 0, 0));
        defensaDato.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(defensaDato);
        defensaDato.setBounds(570, 50, 110, 30);

        estadoAnimoDato.setBackground(new java.awt.Color(0, 0, 0));
        estadoAnimoDato.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(estadoAnimoDato);
        estadoAnimoDato.setBounds(570, 140, 110, 30);

        vidaMaxDato.setBackground(new java.awt.Color(0, 0, 0));
        vidaMaxDato.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(vidaMaxDato);
        vidaMaxDato.setBounds(570, 80, 110, 30);

        labelVidaMax.setBackground(new java.awt.Color(0, 0, 0));
        labelVidaMax.setForeground(new java.awt.Color(255, 255, 255));
        labelVidaMax.setText("Vida max:");
        jPanel1.add(labelVidaMax);
        labelVidaMax.setBounds(420, 80, 110, 30);

        labelAtaque.setBackground(new java.awt.Color(0, 0, 0));
        labelAtaque.setForeground(new java.awt.Color(255, 255, 255));
        labelAtaque.setText("Ataque");
        jPanel1.add(labelAtaque);
        labelAtaque.setBounds(420, 20, 110, 30);

        ataqueDato.setBackground(new java.awt.Color(0, 0, 0));
        ataqueDato.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(ataqueDato);
        ataqueDato.setBounds(570, 20, 110, 30);

        labelVida.setBackground(new java.awt.Color(0, 0, 0));
        labelVida.setForeground(new java.awt.Color(255, 255, 255));
        labelVida.setText("Vida actual:");
        jPanel1.add(labelVida);
        labelVida.setBounds(420, 110, 110, 30);

        vidaDato.setBackground(new java.awt.Color(0, 0, 0));
        vidaDato.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(vidaDato);
        vidaDato.setBounds(570, 110, 110, 30);

        jButtonsSalir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonsSalir.setText("Salir");
        jButtonsSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonsSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonsSalirMouseClicked(evt);
            }
        });
        jButtonsSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonsSalir);
        jButtonsSalir.setBounds(680, 50, 275, 79);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/fondoplano.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(1, 0, 1000, 700);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonsSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonsSalirActionPerformed

    private void jButtonsSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonsSalirMouseClicked
        // TODO add your handling code here:

        jListAmuletos.getSelectedValues();

        jListDefensivos.getSelectedValues();
        jListEspecial.getSelectedValues();
    }//GEN-LAST:event_jButtonsSalirMouseClicked

    private void jListAtaqueValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListAtaqueValueChanged

    }//GEN-LAST:event_jListAtaqueValueChanged

    private void jListDefensivosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListDefensivosValueChanged

    }//GEN-LAST:event_jListDefensivosValueChanged

    private void jListAmuletosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListAmuletosValueChanged

    }//GEN-LAST:event_jListAmuletosValueChanged

    private void jListAtaqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListAtaqueMouseClicked
        String nombre = jListAtaque.getSelectedValue();
        personaje.equipar(nombre, Util.ARMA);
        controlador.getPartida().setProtagonista((Vampire) personaje);
        mostrarEquipacion();
        actualizarEstadisticasBasicas();
    }//GEN-LAST:event_jListAtaqueMouseClicked

    private void jListDefensivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListDefensivosMouseClicked
        String nombre = jListDefensivos.getSelectedValue();
        personaje.equipar(nombre, Util.ARMADURA);
        controlador.getPartida().setProtagonista((Vampire) personaje);
        mostrarEquipacion();
        actualizarEstadisticasBasicas();
    }//GEN-LAST:event_jListDefensivosMouseClicked

    private void jListAmuletosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListAmuletosMouseClicked
        String nombre = jListAmuletos.getSelectedValue();
        personaje.equipar(nombre, Util.AMULERTO);
        controlador.getPartida().setProtagonista((Vampire) personaje);
        mostrarEquipacion();
        actualizarEstadisticasBasicas();
    }//GEN-LAST:event_jListAmuletosMouseClicked

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
            java.util.logging.Logger.getLogger(VistaFicha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaFicha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaFicha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaFicha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amuletos;
    private javax.swing.JLabel ataque;
    private javax.swing.JLabel ataqueDato;
    private javax.swing.JLabel clan;
    private javax.swing.JLabel defensaDato;
    private javax.swing.JLabel defensivo;
    private javax.swing.JLabel dinero;
    private javax.swing.JLabel dineroDato;
    private javax.swing.JLabel estadoAnimoDato;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel habilidad1;
    private javax.swing.JLabel habilidad2;
    private javax.swing.JButton jButtonsSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jListAmuletos;
    private javax.swing.JList<String> jListAtaque;
    private javax.swing.JList<String> jListDefensivos;
    private javax.swing.JList<String> jListEspecial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelAtaque;
    private javax.swing.JLabel labelEstadoAnimo;
    private javax.swing.JLabel labelVida;
    private javax.swing.JLabel labelVidaMax;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel objetoEspecial;
    private javax.swing.JLabel vidaDato;
    private javax.swing.JLabel vidaMaxDato;
    // End of variables declaration//GEN-END:variables

    private void iniciarComponentes() {
        jListAmuletos.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jListAtaque.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jListDefensivos.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jListEspecial.setEnabled(false);
        labelEstadoAnimo.setVisible(false);
        estadoAnimoDato.setVisible(false);
    }

    private void mostrarEquipacion() {
        DefaultListModel listaAtaque = new DefaultListModel();
        DefaultListModel listaAmuletos = new DefaultListModel();
        DefaultListModel listaDefensivos = new DefaultListModel();
        DefaultListModel listaEspeciales = new DefaultListModel();
        Equipo e;
        int atq = 0, def = 0, vid = 0;
        ArrayList<Equipo> inventario = personaje.getEquipacion();
        for (int i = 0; i < inventario.size(); i++) {
            e = inventario.get(i);
            if (e.getAtaque() != 0) {
                listaAtaque.addElement(e.getNombre());
                if (e.isEnUso()) {
                    atq = i;
                }
            } else if (e.getDefensa() != 0) {
                listaDefensivos.addElement(e.getNombre());
                if (e.isEnUso()) {
                    def = i;
                }
            } else if (e.getVida() != 0) {
                listaAmuletos.addElement(e.getNombre());
                if (e.isEnUso()) {
                    vid = i;
                }
            } else {
                listaEspeciales.addElement(e.getNombre());
            }
        }
        jListAmuletos.setModel(listaAmuletos);
        jListAtaque.setModel(listaAtaque);
        jListDefensivos.setModel(listaDefensivos);
        jListEspecial.setModel(listaEspeciales);

        jListAtaque.setSelectedIndex(atq);
        jListDefensivos.setSelectedIndex(def);
        jListAmuletos.setSelectedIndex(vid);
    }

    private void actualizarEstadisticasBasicas() {
        ataqueDato.setText(personaje.getAtaque() + "");
        defensaDato.setText(personaje.getDefensa() + "");
        vidaDato.setText(personaje.getVidaActual() + "");
        vidaMaxDato.setText(personaje.getVidaMax() + "");
    }
}
