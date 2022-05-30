/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Mascarada.Equipo;
import Mascarada.Partida;
import Mascarada.Persona;
import Mascarada.Util;
import Mascarada.Vampire;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author Moru
 */
public class VistaFicha extends javax.swing.JFrame {
    
    private Partida partida;
    private ArrayList<Equipo> equipo;
      

    /**
     * Creates new form FichaAuspex
     */
    public VistaFicha(Persona p) throws IOException {
        initComponents();
        Util.centrar(this);
        
        
       
        if (p instanceof Vampire){
        estadoAnimo1.setVisible(true);
        labelEstadoAnimo.setVisible(true);
        jButton1.setVisible(false);
        
            Vampire v = (Vampire) p;
        vidaMax.setText(p.getVidaMax()+"");
        vida1.setText(p.getVidaActual()+"");
        String[] habiliades = ((Vampire) p).getHabilidades().split(";");
        habilidad1.setText(habiliades[0]);
        habilidad3.setText(habiliades[1]);
        nombre.setText(p.getNombre());
        clan.setText(((Vampire) p).getClan().getNombre());
        dineroDato.setText(p.getDinero()+"");
        ataqueDato1.setText(p.getAtaque()+"");
        defensaDato.setText(p.getDefensa()+"");
        estadoAnimo1.setText(p.getEstadoDeAnimo()+"");
        
        listaAmuletos.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaAtaque.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaDefensivos.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaEspecial.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        
        DefaultListModel listaAtaque = new DefaultListModel();
        DefaultListModel listaAmuletos = new DefaultListModel();
        DefaultListModel listaDefensivos = new DefaultListModel();
        DefaultListModel listaEspeciales = new DefaultListModel(); 
            
      
        
        equipo = p.getEquipacion();
        
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
        
        if(p.getEstadoDeAnimo()==Util.EA_PROTAGONISTA){
            //mostramos lo del protagonista
             estadoAnimo1.setVisible(false);
        labelEstadoAnimo.setVisible(false);
        listaAmuletos.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaAtaque.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaDefensivos.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaEspecial.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        
        DefaultListModel listaAtaque = new DefaultListModel();
        DefaultListModel listaAmuletos = new DefaultListModel();
        DefaultListModel listaDefensivos = new DefaultListModel();
        DefaultListModel listaEspeciales = new DefaultListModel(); 
            
      
        
        equipo = partida.getProtagonista().getEquipacion();
        
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
            
       
        vidaMax.setText(partida.getProtagonista().getVidaMax()+"");
        vida1.setText(partida.getProtagonista().getVidaActual()+"");
        String[] habiliades = partida.getProtagonista().getHabilidades().split(";");
        habilidad1.setText(habiliades[0]);
        habilidad3.setText(habiliades[1]);
        nombre.setText(partida.getProtagonista().getNombre());
        clan.setText(partida.getProtagonista().getClan().getNombre());
        dineroDato.setText(partida.getProtagonista().getDinero()+"");
        ataqueDato1.setText(partida.getProtagonista().getAtaque()+"");
        defensaDato.setText(partida.getProtagonista().getDefensa()+"");

        
        
        jButton1.setVisible(true);
        estadoAnimo1.setVisible(false);
        labelEstadoAnimo.setVisible(false);
        } else {
            //listas NOOOOOO seleccionables
        listaAmuletos.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaAtaque.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaDefensivos.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        listaEspecial.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        
        DefaultListModel listaAtaque = new DefaultListModel();
        DefaultListModel listaAmuletos = new DefaultListModel();
        DefaultListModel listaDefensivos = new DefaultListModel();
        DefaultListModel listaEspeciales = new DefaultListModel(); 
            
      
        
        equipo = partida.getEscena().getPnj().getEquipacion();
        
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
        
            //label de estado de ánimo
        estadoAnimo1.setVisible(true);
        labelEstadoAnimo.setVisible(true);
        vidaMax.setText(partida.getEscena().getPnj().getVidaMax()+"");
        vida1.setText(partida.getEscena().getPnj().getVidaActual()+"");
        habilidad1.setVisible(false);
        habilidad3.setVisible(false);
        nombre.setText(partida.getEscena().getPnj().getNombre());
        clan.setText(" Humano ");
        dineroDato.setText(partida.getEscena().getPnj().getDinero()+"");
        ataqueDato1.setText(partida.getEscena().getPnj().getAtaque()+"");
        defensaDato.setText(partida.getEscena().getPnj().getDefensa()+"");
        estadoAnimo1.setText(partida.getEscena().getPnj().getEstadoDeAnimo()+"");
        jButton1.setVisible(false);
        
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
        listaEspecial = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaAtaque = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaDefensivos = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaAmuletos = new javax.swing.JList<>();
        amuletos = new javax.swing.JLabel();
        ataque = new javax.swing.JLabel();
        defensivo = new javax.swing.JLabel();
        dineroDato = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        habilidad3 = new javax.swing.JLabel();
        dinero = new javax.swing.JLabel();
        labelEstadoAnimo = new javax.swing.JLabel();
        defensaDato = new javax.swing.JLabel();
        estadoAnimo1 = new javax.swing.JLabel();
        vidaMax = new javax.swing.JLabel();
        labelVidaMax = new javax.swing.JLabel();
        labelAtaque = new javax.swing.JLabel();
        ataqueDato1 = new javax.swing.JLabel();
        labelVida = new javax.swing.JLabel();
        vida1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        foto.setText("Foto");
        jPanel1.add(foto);
        foto.setBounds(10, 10, 180, 190);

        jLabel2.setText("Defensa");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(420, 140, 110, 30);

        objetoEspecial.setText("Objetos Especiales");
        jPanel1.add(objetoEspecial);
        objetoEspecial.setBounds(800, 270, 120, 20);

        clan.setText("Clan");
        jPanel1.add(clan);
        clan.setBounds(230, 50, 120, 20);

        habilidad1.setText("Habilidad1");
        jPanel1.add(habilidad1);
        habilidad1.setBounds(230, 80, 120, 20);

        listaEspecial.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaEspecial);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(770, 300, 200, 350);

        listaAtaque.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaAtaque);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(30, 300, 200, 350);

        listaDefensivos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listaDefensivos);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(270, 300, 200, 350);

        listaAmuletos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(listaAmuletos);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(520, 300, 200, 350);

        amuletos.setText("Amuletos");
        jPanel1.add(amuletos);
        amuletos.setBounds(560, 270, 120, 20);

        ataque.setText("Objetos de Ataque");
        jPanel1.add(ataque);
        ataque.setBounds(70, 270, 120, 20);

        defensivo.setText("Objetos Defensivos");
        jPanel1.add(defensivo);
        defensivo.setBounds(320, 270, 120, 20);
        jPanel1.add(dineroDato);
        dineroDato.setBounds(290, 150, 50, 20);

        nombre.setText("Nombre");
        jPanel1.add(nombre);
        nombre.setBounds(230, 20, 60, 20);

        habilidad3.setText("Habilidad2");
        jPanel1.add(habilidad3);
        habilidad3.setBounds(230, 110, 120, 20);

        dinero.setText("Dinero");
        jPanel1.add(dinero);
        dinero.setBounds(230, 150, 50, 20);

        labelEstadoAnimo.setText("Estado de animo");
        jPanel1.add(labelEstadoAnimo);
        labelEstadoAnimo.setBounds(420, 60, 110, 30);
        jPanel1.add(defensaDato);
        defensaDato.setBounds(570, 140, 110, 30);
        jPanel1.add(estadoAnimo1);
        estadoAnimo1.setBounds(580, 60, 110, 30);
        jPanel1.add(vidaMax);
        vidaMax.setBounds(880, 20, 110, 30);

        labelVidaMax.setText("Vida max:");
        jPanel1.add(labelVidaMax);
        labelVidaMax.setBounds(740, 20, 110, 30);

        labelAtaque.setText("Ataque");
        jPanel1.add(labelAtaque);
        labelAtaque.setBounds(420, 100, 110, 30);
        jPanel1.add(ataqueDato1);
        ataqueDato1.setBounds(580, 100, 110, 30);

        labelVida.setText("Vida actual:");
        jPanel1.add(labelVida);
        labelVida.setBounds(420, 20, 110, 30);
        jPanel1.add(vida1);
        vida1.setBounds(580, 20, 110, 30);

        jButton1.setText("Guardar seleccionados");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(710, 120, 210, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/fondoEscenas.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(1, 0, 1000, 700);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        
       listaAmuletos.getSelectedValues();
       listaAtaque.getSelectedValues();
       listaDefensivos.getSelectedValues();
       listaEspecial.getSelectedValues();
    }//GEN-LAST:event_jButton1MouseClicked

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
    private javax.swing.JLabel ataqueDato1;
    private javax.swing.JLabel clan;
    private javax.swing.JLabel defensaDato;
    private javax.swing.JLabel defensivo;
    private javax.swing.JLabel dinero;
    private javax.swing.JLabel dineroDato;
    private javax.swing.JLabel estadoAnimo1;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel habilidad1;
    private javax.swing.JLabel habilidad3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelAtaque;
    private javax.swing.JLabel labelEstadoAnimo;
    private javax.swing.JLabel labelVida;
    private javax.swing.JLabel labelVidaMax;
    private javax.swing.JList<String> listaAmuletos;
    private javax.swing.JList<String> listaAtaque;
    private javax.swing.JList<String> listaDefensivos;
    private javax.swing.JList<String> listaEspecial;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel objetoEspecial;
    private javax.swing.JLabel vida1;
    private javax.swing.JLabel vidaMax;
    // End of variables declaration//GEN-END:variables
}
