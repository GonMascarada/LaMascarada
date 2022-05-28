package Modelo;

import Vista.Inicio;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Gestiona la recolección, insercción, actualización y borrado de datos en la
 * base de datos.
 *
 * @author Gonzalo López Fernández
 */
public class BaseDeDatos {

    private Connection conn;
    private Statement stmt;
    private boolean conectado;

    public BaseDeDatos() throws IOException {
        conectado = false;
        conectar();
    }

    /**
     * Cierra la sesión y la conexión.
     *
     * @throws SQLException
     */
    public void cerrar() throws SQLException {
        stmt.close(); //finalizar la sesión
        conn.close(); //finalizar la conexión
    }

    /**
     * Crea la sesión con la base de datos.
     */
    private void conectar() throws IOException {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/conexionBD.csv");
        Scanner lector = new Scanner(inputStream);
        String[] conexion = new String[3];// url, user y pass en ese orden.
        String[] linea;

        //Se extraen los datos para la conexión de conexionBD.csv
        for (int i = 0; i < conexion.length; i++) {
            linea = lector.nextLine().split(";");
            conexion[i] = linea[0]; //Primer campo valor, segundo clave.
        }

        try {
            //se carga la clase del Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Se establece la conexión
            conn = DriverManager.getConnection(conexion[0], conexion[1], conexion[2]);

            //Se establece la sesión
            stmt = conn.createStatement();

            conectado = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
            conectado = false;
        }
        lector.close();
        inputStream.close();
    }

    /**
     * @return the conectado
     */
    public boolean isConectado() {
        return conectado;
    }

    /**
     * Comprueba que la copia local y la de la base de datos están actualizadas.
     * En caso de no estarlo, lo sincroniza.
     */
    public void sincronizar() {
        System.out.println("Estamos trabajando en ello :(");
    }

}
