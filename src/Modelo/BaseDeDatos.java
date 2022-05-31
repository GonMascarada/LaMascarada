package Modelo;

import Mascarada.Partida;
import Mascarada.Util;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
     * Comprueba en la base de datos si el usuario y la contraseña son
     * correctos.
     *
     * @param text
     * @param password
     * @return
     * @throws java.sql.SQLException
     */
    public boolean comprobarCredenciales(String text, String password) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select comprobarUsuario(\"" + text + "\", \"" + password + "\") as resultado;");
        ResultSet r = stmt.executeQuery();
        int resultado;
        r.next();
        resultado = r.getInt("resultado");
        stmt.close();
        return resultado == 1;
    }

    /**
     * Comprueba si un nombre de usuario está disponible.
     *
     * @param usuario
     * @return true si está disponible, false en otro caso.
     */
    public boolean comprobarNombreUsuario(String usuario) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select comprobarNombreUsuarioDisponible(\"" + usuario + "\") as resultado;");
        ResultSet r = stmt.executeQuery();
        int resultado;
        r.next();
        resultado = r.getInt("resultado");
        stmt.close();;
        return resultado == 1;
    }

    /**
     * Crea la sesión con la base de datos.
     */
    private void conectar() throws IOException {
        String[] conexion = getConfiguracionBD().split(";");
        String servidor = Util.RUTA + conexion[0] + Util.TABLA;
        try {
            //se carga la clase del Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Se establece la conexión
            conn = DriverManager.getConnection(servidor, conexion[2], conexion[1]);

            //Se establece la sesión
            stmt = conn.createStatement();

            conectado = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
            conectado = false;
        }
    }

    /**
     * Reintenta la conexión con la base de datos.
     *
     * @param puerto
     * @param use
     * @param pas
     * @return
     */
    public boolean conectar(String puerto, String use, String pas) {
        String conexion;
        String servidor = Util.RUTA + puerto + Util.TABLA;
        try {
            //se carga la clase del Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Se establece la conexión
            conn = DriverManager.getConnection(servidor, use, pas);

            //Se establece la sesión
            stmt = conn.createStatement();

            conectado = true;
            conexion = puerto + ";" + pas + ";" + use;
            Fichero.escribiTexto(Util.URL_BD, conexion);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
            conectado = false;
        }
        return conectado;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario
     * @param pass
     */
    public void crearNuevoUsuario(String usuario, String pass) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuario VALUES ('" + usuario + "', '" + pass + "', current_timestamp());");
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Devuelve la configuración con la base de datos almacenada en el fichero
     * bd.csv
     *
     * @return Texto con la url, usuario y contraseña.
     */
    public String getConfiguracionBD() {
        File f = new File(Util.URL_BD);
        ArrayList<String> texto = Fichero.leer(f);
        return texto.get(0); //Solo tiene una línea
    }

    /**
     * Inserta en base de datos una nueva partida.
     *
     * @param partida
     */
    void insertarNuevaPartida(Partida partida) throws SQLException {
        // 1.Guardar los datos de la partida.
        System.out.println(Util.IN_PARTIDA);
        Timestamp timestamp = Timestamp.valueOf(partida.getFecha());
        PreparedStatement stmt = conn.prepareStatement(Util.IN_PARTIDA);
        System.out.println("IdPartida: " + partida.getIdPartida());
        System.out.println("Fecha: " + timestamp);
        System.out.println("Tiempo: " + partida.getTiempo());
        System.out.println("Progreso: " + partida.getProgreso());
        System.out.println("SedSangre: " + partida.getSedDeSangre());
        System.out.println("Sospecha: " + partida.getSospecha());
        System.out.println("UltimaPista: " + partida.getUltimaPista());
        System.out.println("UltimaPista tamaño: " + partida.getUltimaPista().length());
        System.out.println("IdEscena: " + partida.getEscena().getIdEscena());
        System.out.println("Usuario: " + partida.getUsuario());
        stmt.setInt(1, partida.getIdPartida());
        stmt.setTimestamp(2, timestamp);
        stmt.setInt(3, partida.getTiempo());
        stmt.setInt(4, partida.getProgreso());
        stmt.setInt(5, partida.getSedDeSangre());
        stmt.setInt(6, partida.getSospecha());
        stmt.setString(7, partida.getUltimaPista());
        stmt.setInt(8, partida.getEscena().getIdEscena());
        stmt.setString(9, partida.getUsuario());
        stmt.executeUpdate();

        // 2.Guardar los datos de los personajes, protagonista incluido.
        //escribirPersonajes(partida.getInfoPersonajes());
        // 3.Guardar los datos de los objetos de cada personaje.
        //escribirObjetos(partida.getInfoObjetos());
        // 4.Actualizar la última modificación
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
