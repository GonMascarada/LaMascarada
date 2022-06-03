/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Mascarada.Clan;
import Mascarada.Partida;
import Mascarada.Util;
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
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Vista inicial del juego, permite iniciar una nueva partida, cargar y borrar
 * partidas anteriores.
 *
 * @author Moru
 * @author Gonzalo López Fernández
 */
public final class VistaPartidas extends javax.swing.JFrame {
    
    ImageIcon botonRojo1=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo1.png"));
    ImageIcon botonRojo2=new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Rojo2.png"));

    private final Controlador controlador;
    private final ArrayList<Clan> clanes;
    private ArrayList<Partida> partidas;
    private String usuario;

    /**
     * Creates new form Inicio
     *
     * @param controlador
     * @param usuario
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public VistaPartidas(Controlador controlador, String usuario) throws IOException, ParseException {
        initComponents();
        
        jButtonBorrar.setRolloverEnabled(true);
        jButtonBorrar.setIcon(botonRojo1);
        jButtonBorrar.setPressedIcon(botonRojo2);
        jButtonBorrar.setForeground(Color.white);
        jButtonBorrar.setBackground(Color.black);
        
        
        jButtonCargar.setRolloverEnabled(true);
        jButtonCargar.setIcon(botonRojo1);
        jButtonCargar.setPressedIcon(botonRojo2);
        jButtonCargar.setForeground(Color.white);
        jButtonCargar.setBackground(Color.black);
        
        Crear.setRolloverEnabled(true);
        Crear.setIcon(botonRojo1);
        Crear.setPressedIcon(botonRojo2);
        Crear.setForeground(Color.white);
        Crear.setBackground(Color.black);
        
        jCheckHabilidad1.setVisible(false);
        jCheckHabilidad2.setVisible(false);
        jCheckHabilidad3.setVisible(false);
        jCheckHabilidad4.setVisible(false);

        Util.centrar(this);
        partidas = new ArrayList<>();
        this.controlador = controlador;
        this.usuario = usuario;

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
        jListClanes.setModel(modelo);
        jListClanes.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        // Carga las listas de partidas.
        refrescarListasPartidas(usuario);

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
    private void refrescarListasPartidas(String usuario) throws IOException, ParseException {
        //Lista de partidas para borrar y cargar
        partidas = controlador.getListaPartidas(usuario);
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
        //Cambio colores
        Color fondo = Color.BLACK;
        Color fondoClaro = Color.DARK_GRAY;
        Color texto = Color.WHITE;
        TabbedMain.setBackground(fondo);
        TabbedMain.setForeground(texto);
        TabbedMain.setBackgroundAt(0, fondo);
        TabbedMain.setBackgroundAt(1, fondo);
        TabbedMain.setBackgroundAt(2, fondo);
        TabbedMain.setForegroundAt(0, texto);
        TabbedMain.setForegroundAt(1, texto);
        TabbedMain.setForegroundAt(2, texto);
        jPanelFondo.setBackground(fondo);
        jPanelCrearPartida.setBackground(fondo);
        jPanelCargarPartida.setBackground(fondo);
        jPanelBorrarPartida.setBackground(fondo);
        jLabelError.setForeground(texto);
        jLabelClanes.setForeground(texto);
        jLabelDificultad.setForeground(texto);
        jLabelNombre.setForeground(texto);
        jListClanes.setBackground(fondoClaro);
        jListClanes.setForeground(texto);
        jTextDescripcionClan.setBackground(fondoClaro);
        jTextDescripcionClan.setForeground(texto);
        jCheckHabilidad1.setBackground(fondo);
        jCheckHabilidad1.setForeground(texto);
        jCheckHabilidad2.setBackground(fondo);
        jCheckHabilidad2.setForeground(texto);
        jCheckHabilidad3.setBackground(fondo);
        jCheckHabilidad3.setForeground(texto);
        jCheckHabilidad4.setBackground(fondo);
        jCheckHabilidad4.setForeground(texto);
        jTextNombre.setBackground(fondoClaro);
        jTextNombre.setForeground(texto);
        jComboBoxDificultad.setBackground(fondoClaro);
        jComboBoxDificultad.setForeground(texto);
        jTableCargar.setBackground(fondo);
        jTableCargar.setForeground(texto);
        jTableBorrar.setBackground(fondo);
        jTableBorrar.setForeground(texto);
        jTableCargar.getTableHeader().setBackground(fondo);
        jTableCargar.getTableHeader().setForeground(texto);
        jTableBorrar.getTableHeader().setBackground(fondo);
        jTableBorrar.getTableHeader().setForeground(texto);
        jScrollPaneCargar.getViewport().setBackground(fondo);
        jScrollPaneBorrar.getViewport().setBackground(fondo);
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

        jPanelFondo = new javax.swing.JPanel();
        TabbedMain = new javax.swing.JTabbedPane();
        jPanelCrearPartida = new javax.swing.JPanel();
        jLabelClanes = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jTextNombre = new javax.swing.JTextField();
        jLabelDificultad = new javax.swing.JLabel();
        jComboBoxDificultad = new javax.swing.JComboBox<>();
        Crear = new javax.swing.JButton();
        Eleccion = new javax.swing.JScrollPane();
        jListClanes = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextDescripcionClan = new javax.swing.JTextPane();
        jCheckHabilidad1 = new javax.swing.JCheckBox();
        jCheckHabilidad2 = new javax.swing.JCheckBox();
        jCheckHabilidad3 = new javax.swing.JCheckBox();
        jCheckHabilidad4 = new javax.swing.JCheckBox();
        jLabelError = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelCargarPartida = new javax.swing.JPanel();
        jScrollPaneCargar = new javax.swing.JScrollPane();
        jTableCargar = new javax.swing.JTable();
        jButtonCargar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanelBorrarPartida = new javax.swing.JPanel();
        jButtonBorrar = new javax.swing.JButton();
        jScrollPaneBorrar = new javax.swing.JScrollPane();
        jTableBorrar = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);

        jPanelFondo.setLayout(null);

        TabbedMain.setBackground(new java.awt.Color(0, 0, 0));
        TabbedMain.setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanelCrearPartida.setPreferredSize(new java.awt.Dimension(1000, 700));
        jPanelCrearPartida.setLayout(null);

        jLabelClanes.setText("Seleccione un clan:");
        jPanelCrearPartida.add(jLabelClanes);
        jLabelClanes.setBounds(319, 36, 210, 27);

        jLabelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombre.setText("Introduzca un nombre");
        jPanelCrearPartida.add(jLabelNombre);
        jLabelNombre.setBounds(356, 377, 315, 28);

        jTextNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNombreActionPerformed(evt);
            }
        });
        jPanelCrearPartida.add(jTextNombre);
        jTextNombre.setBounds(356, 411, 315, 28);

        jLabelDificultad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDificultad.setText("Seleccione la dificultad");
        jPanelCrearPartida.add(jLabelDificultad);
        jLabelDificultad.setBounds(356, 445, 315, 29);

        jComboBoxDificultad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facil", "Normal", "Dificil", "Pesadilla" }));
        jComboBoxDificultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDificultadActionPerformed(evt);
            }
        });
        jPanelCrearPartida.add(jComboBoxDificultad);
        jComboBoxDificultad.setBounds(356, 480, 315, 26);

        Crear.setText("Crear");
        Crear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        jPanelCrearPartida.add(Crear);
        Crear.setBounds(360, 550, 280, 70);

        jListClanes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Brujah", "Ventru", "Tremere", "Nosferatu" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListClanes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListClanesMouseClicked(evt);
            }
        });
        jListClanes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListClanesValueChanged(evt);
            }
        });
        Eleccion.setViewportView(jListClanes);

        jPanelCrearPartida.add(Eleccion);
        Eleccion.setBounds(65, 81, 180, 166);

        jTextDescripcionClan.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTextDescripcionClan);

        jPanelCrearPartida.add(jScrollPane1);
        jScrollPane1.setBounds(319, 81, 611, 166);
        jPanelCrearPartida.add(jCheckHabilidad1);
        jCheckHabilidad1.setBounds(443, 265, 119, 18);
        jPanelCrearPartida.add(jCheckHabilidad2);
        jCheckHabilidad2.setBounds(443, 289, 119, 18);
        jPanelCrearPartida.add(jCheckHabilidad3);
        jCheckHabilidad3.setBounds(443, 313, 119, 18);
        jPanelCrearPartida.add(jCheckHabilidad4);
        jCheckHabilidad4.setBounds(443, 337, 119, 18);

        jLabelError.setText("jLabel1");
        jPanelCrearPartida.add(jLabelError);
        jLabelError.setBounds(356, 517, 315, 16);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/fondoplano.jpg"))); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelCrearPartida.add(jLabel1);
        jLabel1.setBounds(0, 0, 1000, 670);

        TabbedMain.addTab("Crear Partida", jPanelCrearPartida);

        jPanelCargarPartida.setPreferredSize(new java.awt.Dimension(1000, 700));
        jPanelCargarPartida.setLayout(null);

        jTableCargar.setForeground(new java.awt.Color(0, 0, 0));
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
        jScrollPaneCargar.setViewportView(jTableCargar);

        jPanelCargarPartida.add(jScrollPaneCargar);
        jScrollPaneCargar.setBounds(0, 0, 1000, 500);

        jButtonCargar.setText("Cargar");
        jButtonCargar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        jPanelCargarPartida.add(jButtonCargar);
        jButtonCargar.setBounds(372, 552, 280, 70);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/fondoplano.jpg"))); // NOI18N
        jLabel3.setText("jLabel1");
        jPanelCargarPartida.add(jLabel3);
        jLabel3.setBounds(0, 0, 1000, 700);

        TabbedMain.addTab("Cargar Partida", jPanelCargarPartida);

        jPanelBorrarPartida.setPreferredSize(new java.awt.Dimension(1000, 700));
        jPanelBorrarPartida.setLayout(null);

        jButtonBorrar.setText("Borrar");
        jButtonBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        jPanelBorrarPartida.add(jButtonBorrar);
        jButtonBorrar.setBounds(369, 555, 280, 70);

        jTableBorrar.setBackground(new java.awt.Color(0, 0, 0));
        jTableBorrar.setForeground(new java.awt.Color(255, 255, 255));
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
        jScrollPaneBorrar.setViewportView(jTableBorrar);

        jPanelBorrarPartida.add(jScrollPaneBorrar);
        jScrollPaneBorrar.setBounds(0, 0, 1000, 500);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/fondoplano.jpg"))); // NOI18N
        jLabel4.setText("jLabel1");
        jPanelBorrarPartida.add(jLabel4);
        jLabel4.setBounds(0, 0, 1000, 700);

        TabbedMain.addTab("Borrar Partida", jPanelBorrarPartida);

        jPanelFondo.add(TabbedMain);
        TabbedMain.setBounds(0, 0, 1000, 700);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNombreActionPerformed

    private void jComboBoxDificultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDificultadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDificultadActionPerformed

    private void CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CrearActionPerformed

    private void jListClanesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListClanesMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jListClanesMouseClicked

    private void jListClanesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListClanesValueChanged
        Clan clan;
        String nombreClan = jListClanes.getSelectedValue();
        int contador = 0;
        HashMap<String, Boolean> habilidades;
        Iterator<String> it;
        String[] nombres = new String[4];
        for (int i = 0; i < clanes.size(); i++) {
            clan = clanes.get(i);
            if (clan.getNombre().equalsIgnoreCase(nombreClan)) {
                jTextDescripcionClan.setText(clanes.get(i).getDescripcion());
                habilidades = clan.getHabilidades();
                it = habilidades.keySet().iterator();

                while (it.hasNext()) {
                    String nombreHabilidad = it.next();
                    nombres[contador] = nombreHabilidad;
                    contador++;
                }
            }
        }
        jCheckHabilidad1.setText(nombres[0]);
        jCheckHabilidad2.setText(nombres[1]);
        jCheckHabilidad3.setText(nombres[2]);
        jCheckHabilidad4.setText(nombres[3]);

        jCheckHabilidad1.setVisible(true);
        jCheckHabilidad2.setVisible(true);
        jCheckHabilidad3.setVisible(true);
        jCheckHabilidad4.setVisible(true);
    }//GEN-LAST:event_jListClanesValueChanged

    private void CrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CrearMouseClicked
        int indice, contador;
        String error = "";
        try {
            if (controlador.comprobarNombrePersonaje(jTextNombre.getText())) {
                indice = jListClanes.getSelectedIndex();
                contador = 0;
                if (indice != -1) {
                    if (jCheckHabilidad1.isSelected()) {
                        contador++;
                    }
                    if (jCheckHabilidad2.isSelected()) {
                        contador++;
                    }
                    if (jCheckHabilidad3.isSelected()) {
                        contador++;
                    }
                    if (jCheckHabilidad4.isSelected()) {
                        contador++;
                    }
                    if (contador == 2) {
                        if (jCheckHabilidad1.isSelected()) {
                            clanes.get(indice).setHabilidad(jCheckHabilidad1.getText());
                        }
                        if (jCheckHabilidad2.isSelected()) {
                            clanes.get(indice).setHabilidad(jCheckHabilidad2.getText());
                        }
                        if (jCheckHabilidad3.isSelected()) {
                            clanes.get(indice).setHabilidad(jCheckHabilidad3.getText());
                        }
                        if (jCheckHabilidad4.isSelected()) {
                            clanes.get(indice).setHabilidad(jCheckHabilidad4.getText());
                        }
                        try {
                            controlador.iniciarNuevaPartida(clanes.get(indice), jTextNombre.getText(), jComboBoxDificultad.getSelectedItem().toString(), usuario);
                            this.dispose();
                        } catch (IOException ex) {
                            Logger.getLogger(VistaPartidas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VistaPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_CrearMouseClicked

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarActionPerformed
        try {
            Partida p = partidas.get(jTableCargar.getSelectedRow());
            controlador.cargarPartida(p);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(VistaPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCargarActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        int[] seleccionados = jTableBorrar.getSelectedRows();
        for (int i = 0; i < seleccionados.length; i++) {
            try {
                controlador.eliminarPartida(partidas.get(seleccionados[i]).getIdPartida());
            } catch (IOException ex) {
                Logger.getLogger(VistaPartidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        VistaPartidas inicio;
        try {
            inicio = new VistaPartidas(controlador, usuario);
            inicio.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(VistaPartidas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(VistaPartidas.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(VistaPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPartidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>*/

 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new VistaPartidas(controlador).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Crear;
    private javax.swing.JScrollPane Eleccion;
    private javax.swing.JTabbedPane TabbedMain;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JCheckBox jCheckHabilidad1;
    private javax.swing.JCheckBox jCheckHabilidad2;
    private javax.swing.JCheckBox jCheckHabilidad3;
    private javax.swing.JCheckBox jCheckHabilidad4;
    private javax.swing.JComboBox<String> jComboBoxDificultad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelClanes;
    private javax.swing.JLabel jLabelDificultad;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JList<String> jListClanes;
    private javax.swing.JPanel jPanelBorrarPartida;
    private javax.swing.JPanel jPanelCargarPartida;
    private javax.swing.JPanel jPanelCrearPartida;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneBorrar;
    private javax.swing.JScrollPane jScrollPaneCargar;
    private javax.swing.JTable jTableBorrar;
    private javax.swing.JTable jTableCargar;
    private javax.swing.JTextPane jTextDescripcionClan;
    private javax.swing.JTextField jTextNombre;
    // End of variables declaration//GEN-END:variables
}
