/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Clan;
import Mascarada.Partida;
import java.awt.Color;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Vista inicial del juego, permite iniciar una nueva partida, cargar y borrar
 * partidas anteriores.
 *
 * @author Moru
 * @author Gonzalo López Fernández
 */
public final class Inicio extends javax.swing.JFrame {

    private final Controlador controlador;

    private final ArrayList<Clan> clanes;

    private ArrayList<Partida> partidas;

    /**
     * Creates new form Inicio
     *
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public Inicio() throws IOException, ParseException {
        initComponents();
        partidas = new ArrayList<>();
        //Elementos del Jlist
        //Elemento que me trae la info
        controlador = new Controlador();
        //Lista de la informacion de los clanes
        clanes = controlador.getListaClanes();
        //Hago invisible el label de errores.
        jLabelError.setVisible(false);

        //ArrayList para el Jlist
        DefaultListModel modelo = new DefaultListModel();
        //Traigo los nombres de los clanes al Jlist gracias a controlador y los seteo con el modelo

        for (int i = 0; i < clanes.size(); i++) {
            modelo.addElement(clanes.get(i).getNombre());
        }
        ListaClanes1.setModel(modelo);
        ListaClanes1.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        // Carga las listas de partidas.
        refrescarListasPartidas();

        //Pestaña de carga
        jTableCargar.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jTableBorrar.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

    /**
     * Muestra en los paneles de carga y borrado un listado actualizado de las
     * partidas.
     *
     * @throws IOException
     * @throws ParseException
     */
    private void refrescarListasPartidas() throws IOException, ParseException {
        //Lista de partidas para borrar y cargar
        partidas = controlador.getListaPartidas();
        if (partidas.size() > 0) {
            cargarDatosPartidas(jTableCargar);
            cargarDatosPartidas(jTableBorrar);
            //Hace que SI se pueda acceder a las pestañas de cargar y borrar
            TabbedMain.setEnabledAt(1, true);
            TabbedMain.setEnabledAt(2, true);
        } else {
            //Hace que NO se pueda acceder a las pestañas de cargar y borrar
            TabbedMain.setEnabledAt(1, false);
            TabbedMain.setEnabledAt(2, false);
        }
    }

    /**
     * Carga en el jTable que se le pase una lista de las partidas.
     *
     * @param table
     */
    private void cargarDatosPartidas(JTable table) {
        String[] columnNames = {"Nombre", "Clan", "Habilidad 1", "Habilidad 2", "Tiempo jugado", "Último guardado"};
        // Evita que se puedan editar las celdas de la tabla
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        for (Partida p : partidas) {
            model.addRow(p.getDatos());
        }
        table.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FondoTotal = new javax.swing.JPanel();
        TabbedMain = new javax.swing.JTabbedPane();
        CrearPartida = new javax.swing.JPanel();
        LabelClanes = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        TextNombre = new javax.swing.JTextField();
        Labeldificultad = new javax.swing.JLabel();
        BoxDificultad = new javax.swing.JComboBox<>();
        Crear = new javax.swing.JButton();
        Eleccion = new javax.swing.JScrollPane();
        ListaClanes1 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        DescripcionClan = new javax.swing.JTextPane();
        Habilidad1 = new javax.swing.JCheckBox();
        Habilidad2 = new javax.swing.JCheckBox();
        Habilidad3 = new javax.swing.JCheckBox();
        Habilidad4 = new javax.swing.JCheckBox();
        jLabelError = new javax.swing.JLabel();
        CargarPartida = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCargar = new javax.swing.JTable();
        jButtonCargar = new javax.swing.JButton();
        BorrarPartida = new javax.swing.JPanel();
        jButtonBorrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableBorrar = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 700));
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);

        FondoTotal.setLayout(null);

        TabbedMain.setPreferredSize(new java.awt.Dimension(1000, 700));

        CrearPartida.setPreferredSize(new java.awt.Dimension(1000, 700));

        LabelClanes.setText("Seleccione un clan:");

        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNombre.setText("Introduzca un nombre");

        TextNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNombreActionPerformed(evt);
            }
        });

        Labeldificultad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labeldificultad.setText("Seleccione la dificultad");

        BoxDificultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facil", "Normal", "Dificil", "Pesadilla" }));
        BoxDificultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxDificultadActionPerformed(evt);
            }
        });

        Crear.setText("Crear");
        Crear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CrearMouseClicked(evt);
            }
        });
        Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearActionPerformed(evt);
            }
        });

        ListaClanes1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Brujah", "Ventru", "Tremere", "Nosferatu" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaClanes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaClanes1MouseClicked(evt);
            }
        });
        ListaClanes1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaClanes1ValueChanged(evt);
            }
        });
        Eleccion.setViewportView(ListaClanes1);

        jScrollPane1.setViewportView(DescripcionClan);

        jLabelError.setText("jLabel1");

        javax.swing.GroupLayout CrearPartidaLayout = new javax.swing.GroupLayout(CrearPartida);
        CrearPartida.setLayout(CrearPartidaLayout);
        CrearPartidaLayout.setHorizontalGroup(
            CrearPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearPartidaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Eleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(CrearPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelClanes, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(CrearPartidaLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(Habilidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(Habilidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(Habilidad3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(Habilidad4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(CrearPartidaLayout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addGroup(CrearPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Labeldificultad, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(BoxDificultad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextNombre)
                    .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Crear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CrearPartidaLayout.setVerticalGroup(
            CrearPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearPartidaLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(LabelClanes, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CrearPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Eleccion)
                    .addComponent(jScrollPane1))
                .addGap(39, 39, 39)
                .addGroup(CrearPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Habilidad1)
                    .addComponent(Habilidad2)
                    .addComponent(Habilidad3)
                    .addComponent(Habilidad4))
                .addGap(50, 50, 50)
                .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Labeldificultad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoxDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jLabelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Crear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        TabbedMain.addTab("Crear Partida", CrearPartida);

        CargarPartida.setPreferredSize(new java.awt.Dimension(1000, 700));

        jTableCargar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTableCargar);

        jButtonCargar.setText("Cargar");
        jButtonCargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCargarMouseClicked(evt);
            }
        });
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CargarPartidaLayout = new javax.swing.GroupLayout(CargarPartida);
        CargarPartida.setLayout(CargarPartidaLayout);
        CargarPartidaLayout.setHorizontalGroup(
            CargarPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addGroup(CargarPartidaLayout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CargarPartidaLayout.setVerticalGroup(
            CargarPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CargarPartidaLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButtonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        TabbedMain.addTab("Cargar Partida", CargarPartida);

        BorrarPartida.setPreferredSize(new java.awt.Dimension(1000, 700));

        jButtonBorrar.setText("Borrar");
        jButtonBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonBorrarMouseClicked(evt);
            }
        });
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });

        jTableBorrar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTableBorrar);

        javax.swing.GroupLayout BorrarPartidaLayout = new javax.swing.GroupLayout(BorrarPartida);
        BorrarPartida.setLayout(BorrarPartidaLayout);
        BorrarPartidaLayout.setHorizontalGroup(
            BorrarPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BorrarPartidaLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BorrarPartidaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(331, 331, 331))
        );
        BorrarPartidaLayout.setVerticalGroup(
            BorrarPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BorrarPartidaLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        TabbedMain.addTab("Borrar Partida", BorrarPartida);

        FondoTotal.add(TabbedMain);
        TabbedMain.setBounds(0, 0, 1000, 700);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FondoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FondoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNombreActionPerformed

    private void BoxDificultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxDificultadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxDificultadActionPerformed

    private void CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CrearActionPerformed

    private void ListaClanes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaClanes1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_ListaClanes1MouseClicked

    private void ListaClanes1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaClanes1ValueChanged
        Clan clan;
        String nombreClan = ListaClanes1.getSelectedValue();
        int contador = 0;
        HashMap<String, Boolean> habilidades;
        Iterator<String> it;
        String[] nombres = new String[4];
        for (int i = 0; i < clanes.size(); i++) {
            clan = clanes.get(i);
            if (clan.getNombre().equalsIgnoreCase(nombreClan)) {
                DescripcionClan.setText(clanes.get(i).getDescripcion());
                habilidades = clan.getHabilidades();
                it = habilidades.keySet().iterator();

                while (it.hasNext()) {
                    String nombreHabilidad = it.next();
                    nombres[contador] = nombreHabilidad;
                    contador++;
                }
            }
        }
        Habilidad1.setText(nombres[0]);
        Habilidad2.setText(nombres[1]);
        Habilidad3.setText(nombres[2]);
        Habilidad4.setText(nombres[3]);
    }//GEN-LAST:event_ListaClanes1ValueChanged

    private void CrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CrearMouseClicked
        int indice, contador;
        String error = "";
        try {
            if (controlador.comprobarNombrePersonaje(TextNombre.getText())) {
                indice = ListaClanes1.getSelectedIndex();
                contador = 0;
                if (indice != -1) {
                    if (Habilidad1.isSelected()) {
                        contador++;
                    }
                    if (Habilidad2.isSelected()) {
                        contador++;
                    }
                    if (Habilidad3.isSelected()) {
                        contador++;
                    }
                    if (Habilidad4.isSelected()) {
                        contador++;
                    }
                    if (contador == 2) {
                        if (Habilidad1.isSelected()) {
                            clanes.get(indice).setHabilidad(Habilidad1.getText());
                        }
                        if (Habilidad2.isSelected()) {
                            clanes.get(indice).setHabilidad(Habilidad2.getText());
                        }
                        if (Habilidad3.isSelected()) {
                            clanes.get(indice).setHabilidad(Habilidad3.getText());
                        }
                        if (Habilidad4.isSelected()) {
                            clanes.get(indice).setHabilidad(Habilidad4.getText());
                        }
                        try {
                            controlador.iniciarNuevaPartida(clanes.get(indice), TextNombre.getText(), BoxDificultad.getSelectedItem().toString());
                            this.dispose();
                        } catch (IOException ex) {
                            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        error = "Selecciona dos habilidades.";
                    }
                } else {
                    error = "Selecciona un clan.";
                }
            } else {
                error = "El nombre no está disponible.";
            }
            jLabelError.setText(error);
            jLabelError.setVisible(true);
            jLabelError.setForeground(Color.red);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_CrearMouseClicked

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarActionPerformed
        try {
            Partida p = partidas.get(jTableCargar.getSelectedRow());
            controlador.cargarPartida(p);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        int[] seleccionados = jTableBorrar.getSelectedRows();
        for (int i = 0; i < seleccionados.length; i++) {
            try {
                controlador.eliminarPartida(partidas.get(seleccionados[i]).getIdPartida());
            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Inicio inicio;
        try {
            inicio = new Inicio();
            inicio.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonBorrarActionPerformed

    private void jButtonCargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCargarMouseClicked
        // TODO add your handling code here:

        System.out.println("Trabajando en la carga de partidas");
    }//GEN-LAST:event_jButtonCargarMouseClicked

    private void jButtonBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBorrarMouseClicked
        // TODO add your handling code here:
        System.out.println("Trabajando en el borrado de partidas");
    }//GEN-LAST:event_jButtonBorrarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>*/

 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        new Inicio().setVisible(true);
                    } catch (ParseException ex) {
                        Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BorrarPartida;
    private javax.swing.JComboBox<String> BoxDificultad;
    private javax.swing.JPanel CargarPartida;
    private javax.swing.JButton Crear;
    private javax.swing.JPanel CrearPartida;
    private javax.swing.JTextPane DescripcionClan;
    private javax.swing.JScrollPane Eleccion;
    private javax.swing.JPanel FondoTotal;
    private javax.swing.JCheckBox Habilidad1;
    private javax.swing.JCheckBox Habilidad2;
    private javax.swing.JCheckBox Habilidad3;
    private javax.swing.JCheckBox Habilidad4;
    private javax.swing.JLabel LabelClanes;
    private javax.swing.JLabel Labeldificultad;
    private javax.swing.JList<String> ListaClanes1;
    private javax.swing.JTabbedPane TabbedMain;
    private javax.swing.JTextField TextNombre;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableBorrar;
    private javax.swing.JTable jTableCargar;
    private javax.swing.JLabel labelNombre;
    // End of variables declaration//GEN-END:variables
}
