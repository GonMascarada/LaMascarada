/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author Moru
 */
public class Cabecera extends javax.swing.JFrame {

    /**
     * Creates new form Cabecera
     */
    public Cabecera() {
        initComponents();
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
        Foto = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        Clan = new javax.swing.JLabel();
        Habilidad1 = new javax.swing.JLabel();
        Habilidad2 = new javax.swing.JLabel();
        Hora = new javax.swing.JLabel();
        horaActual = new javax.swing.JLabel();
        SedSangre = new javax.swing.JLabel();
        BarraSangre = new javax.swing.JProgressBar();
        Sospecha = new javax.swing.JLabel();
        BarraSospecha = new javax.swing.JProgressBar();
        Vida = new javax.swing.JLabel();
        barraVida = new javax.swing.JProgressBar();
        Progreso = new javax.swing.JLabel();
        barraProgreso = new javax.swing.JProgressBar();
        LugarActual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        Foto.setText("Foto");
        jPanel1.add(Foto);
        Foto.setBounds(23, 18, 149, 128);

        Nombre.setText("Nombre");
        jPanel1.add(Nombre);
        Nombre.setBounds(23, 164, 194, 25);

        Clan.setText("Clan");
        jPanel1.add(Clan);
        Clan.setBounds(23, 207, 194, 24);

        Habilidad1.setText("Habilidad 1");
        jPanel1.add(Habilidad1);
        Habilidad1.setBounds(280, 30, 120, 30);

        Habilidad2.setText("Habilidad 2");
        jPanel1.add(Habilidad2);
        Habilidad2.setBounds(280, 90, 120, 30);

        Hora.setText("xx:xx");
        jPanel1.add(Hora);
        Hora.setBounds(330, 140, 60, 30);

        horaActual.setText("Hora:");
        jPanel1.add(horaActual);
        horaActual.setBounds(280, 140, 30, 30);

        SedSangre.setText("Sed de sangre:");
        jPanel1.add(SedSangre);
        SedSangre.setBounds(520, 20, 120, 30);
        jPanel1.add(BarraSangre);
        BarraSangre.setBounds(630, 20, 250, 30);

        Sospecha.setText("Sospecha:");
        jPanel1.add(Sospecha);
        Sospecha.setBounds(520, 60, 120, 30);
        jPanel1.add(BarraSospecha);
        BarraSospecha.setBounds(630, 60, 250, 30);

        Vida.setText("Vida:");
        jPanel1.add(Vida);
        Vida.setBounds(520, 100, 120, 30);
        jPanel1.add(barraVida);
        barraVida.setBounds(630, 100, 250, 30);

        Progreso.setText("Progreso:");
        jPanel1.add(Progreso);
        Progreso.setBounds(520, 140, 120, 30);
        jPanel1.add(barraProgreso);
        barraProgreso.setBounds(630, 140, 250, 30);

        LugarActual.setText("Lugar en el que te encuentras:");
        jPanel1.add(LugarActual);
        LugarActual.setBounds(280, 210, 600, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

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
            java.util.logging.Logger.getLogger(Cabecera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cabecera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cabecera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cabecera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cabecera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar BarraSangre;
    private javax.swing.JProgressBar BarraSospecha;
    private javax.swing.JLabel Clan;
    private javax.swing.JLabel Foto;
    private javax.swing.JLabel Habilidad1;
    private javax.swing.JLabel Habilidad2;
    private javax.swing.JLabel Hora;
    private javax.swing.JLabel LugarActual;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Progreso;
    private javax.swing.JLabel SedSangre;
    private javax.swing.JLabel Sospecha;
    private javax.swing.JLabel Vida;
    private javax.swing.JProgressBar barraProgreso;
    private javax.swing.JProgressBar barraVida;
    private javax.swing.JLabel horaActual;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
