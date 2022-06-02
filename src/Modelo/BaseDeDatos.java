package Modelo;

import Mascarada.Partida;
import Mascarada.Util;
import Mascarada.Vampire;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Gestiona la recolección, insercción, actualización y borrado de datos en la
 * base de datos.
 *
 * @author Gonzalo López Fernández
 */
public class BaseDeDatos {

    private Connection conn;
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
        stmt.close();
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

            conectado = true;
            conexion = puerto + ";" + pas + ";" + use;
            Fichero.escribirTexto(Util.URL_BD, conexion);
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
        Vampire pj;
        String [] habilidades;
        // 1.Guardar los datos de la partida.
        System.out.println(Util.IN_PARTIDA);
        Timestamp timestamp = Timestamp.valueOf(partida.getFecha());
        PreparedStatement stmt = conn.prepareStatement(Util.IN_PARTIDA);
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
        stmt.close();
        // 2.Guardar los datos de los personajes, protagonista incluido.
        System.out.println(Util.IN_PERSONAJE);
        pj = partida.getProtagonista();
        habilidades = pj.getHabilidades().split(";");
        stmt = conn.prepareStatement(Util.IN_PERSONAJE);
        stmt.setString(1, pj.getNombre());
        stmt.setInt(2, pj.getAtaque());
        stmt.setInt(3, pj.getDefensa());
        stmt.setInt(4, pj.getVidaMax());
        stmt.setInt(5, pj.getVidaActual());
        stmt.setInt(6, pj.getDinero());
        stmt.setInt(7, pj.getEstadoDeAnimo());
        stmt.setString(8, pj.getClan().getNombre());
        stmt.setString(9, habilidades[0]);
        stmt.setString(10, habilidades[1]);
        stmt.setInt(11, partida.getIdPartida());
        stmt.executeUpdate();
        stmt.close();
        // 3.Guardar los datos de los objetos de cada personaje.

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
