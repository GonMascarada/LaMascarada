/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Equipo;
import Mascarada.Partida;
import Mascarada.Util;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alumno
 */
public class VistaTienda extends javax.swing.JFrame {

    private Controlador controlador;
    private Partida partida;
    ImageIcon botonRojo1 = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1.png"));
    ImageIcon botonRojo2 = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2.png"));
    ImageIcon botonRojo3=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1-4.png"));
    ImageIcon botonRojo4=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2-4.png"));
    


    /**
     * Creates new form Tienda
     */
    public VistaTienda(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
        Util.centrar(this);
        
        jButton1.setRolloverEnabled(true);
        jButton1.setIcon(botonRojo1);
        jButton1.setPressedIcon(botonRojo2);
        jButton1.setForeground(Color.white);
        jButton1.setBackground(Color.black);
        
        jButton2.setRolloverEnabled(true);
        jButton2.setIcon(botonRojo1);
        jButton2.setPressedIcon(botonRojo2);
        jButton2.setForeground(Color.white);
        jButton2.setBackground(Color.black);
        
        salir.setRolloverEnabled(true);
        salir.setIcon(botonRojo1);
        salir.setPressedIcon(botonRojo2);
        salir.setForeground(Color.white);
        salir.setBackground(Color.black);
        
        accion.setRolloverEnabled(true);
        accion.setIcon(botonRojo1);
        accion.setPressedIcon(botonRojo2);
        accion.setForeground(Color.white);
        accion.setBackground(Color.black);
        
        
        jTable1.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        vistaCabecera1.insertarDatosPartida(controlador);
    }

    public void cargarDatos(List<Equipo> equipos) {
        String[] columnNames = {"Nombre", "Atributo", "Precio"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {

                return false;
            }
        ;
        };
        
        for (Equipo prod : equipos) {

            Object[] data = new Object[columnNames.length];

            data[0] = prod.getNombre();
            data[1] = prod.getAtributo();
            data[2] = prod.getPrecio();

            model.addRow(data);
        }
        jTable1.setModel(model);
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        accion = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);

        jButton1.setText("Tu inventario");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(630, 260, 280, 75);

        jLabel1.setText("Tienda");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(452, 226, 188, 33);

        jButton2.setText("Inventario del NPC");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(80, 260, 280, 78);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(6, 346, 970, 280);

        accion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        accion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionMouseClicked(evt);
            }
        });
        jPanel1.add(accion);
        accion.setBounds(50, 640, 353, 40);

        salir.setText("Salir");
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMouseClicked(evt);
            }
        });
        jPanel1.add(salir);
        salir.setBounds(540, 640, 353, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/fondoEscenas.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(-10, 0, 990, 700);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        accion.setText("Vender");
        cargarDatos(controlador.getPartida().getProtagonista().getEquipacion());
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        accion.setText("Comprar");
        cargarDatos(controlador.getPartida().getEscena().getPnj().getEquipacion());
    }//GEN-LAST:event_jButton2MouseClicked

    private void accionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accionMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_accionMouseClicked

    private void salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseClicked
        // TODO add your handling code here:
        controlador.cargarEscena(Util.ES_CALLEJON);
        this.dispose();
    }//GEN-LAST:event_salirMouseClicked

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
            java.util.logging.Logger.getLogger(VistaTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new VistaTienda(controlador).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
