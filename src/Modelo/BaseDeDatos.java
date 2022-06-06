package Modelo;

import Mascarada.Equipo;
import Mascarada.Partida;
import Mascarada.Persona;
import Mascarada.Util;
import Mascarada.Vampire;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
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
     * @param usuario
     * @param password
     * @return
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public boolean comprobarCredenciales(String usuario, String password) throws SQLException, IOException, ParseException {
        PreparedStatement stmt = conn.prepareStatement("select comprobarUsuario(\"" + usuario + "\", \"" + password + "\") as resultado;");
        ResultSet r = stmt.executeQuery();
        int resultado;
        r.next();
        resultado = r.getInt("resultado");
        stmt.close();
        if (resultado == 1) {
            sincronizar(usuario);
        }
        return resultado == 1;
    }

    /**
     * Comprueba si un nombre de usuario está disponible.
     *
     * @param usuario
     * @return true si está disponible, false en otro caso.
     * @throws java.sql.SQLException
     */
    public boolean comprobarNombreUsuario(String usuario) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(Util.SE_USUARIO);
        stmt.setString(1, usuario);
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
            Fichero.escribirTexto(Util.URL_BD, conexion, false);
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
     * @throws java.sql.SQLException
     */
    public void crearNuevoUsuario(String usuario, String pass) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(Util.IN_USUARIO);
        stmt.setString(1, usuario);
        stmt.setString(2, pass);
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
     * Inserta todos los datos de una partida en la base de datos.
     *
     * @param partida
     */
    void insertarPartida(Partida partida) throws SQLException {
        ArrayList<Persona> personajes;
        ArrayList<Equipo> equipamiento;
        Persona pj;
        String[] habilidades;
        int idPartida = partida.getIdPartida();
        String usuario = partida.getUsuario();
        // 1.Guardar los datos de la partida.
        Timestamp timestamp = Timestamp.valueOf(partida.getFecha());
        PreparedStatement stmt = conn.prepareStatement(Util.IN_PARTIDA);
        stmt.setInt(1, idPartida);
        stmt.setTimestamp(2, timestamp);
        stmt.setInt(3, partida.getTiempo());
        stmt.setInt(4, partida.getProgreso());
        stmt.setInt(5, partida.getSedDeSangre());
        stmt.setInt(6, partida.getSospecha());
        stmt.setString(7, partida.getUltimaPista());
        stmt.setInt(8, partida.getEscena().getIdEscena());
        stmt.setString(9, usuario);
        stmt.setInt(10, partida.getDificultad());
        stmt.executeUpdate();
        stmt.close();
        // 2.Guardar los datos de los personajes, protagonista incluido.
        personajes = partida.getPersonajes();
        personajes.add(partida.getProtagonista());
        stmt = conn.prepareStatement(Util.IN_PERSONAJE);
        for (int i = 0; i < personajes.size(); i++) {
            pj = personajes.get(i);
            stmt.setString(1, pj.getNombre());
            stmt.setInt(2, pj.getAtaque());
            stmt.setInt(3, pj.getDefensa());
            stmt.setInt(4, pj.getVidaMax());
            stmt.setInt(5, pj.getVidaActual());
            stmt.setInt(6, pj.getDinero());
            stmt.setInt(7, pj.getEstadoDeAnimo());
            if (pj instanceof Vampire vampire) {
                habilidades = vampire.getHabilidades().split(";");
                stmt.setString(8, vampire.getClan().getNombre());
                stmt.setString(9, habilidades[0]);
                stmt.setString(10, habilidades[1]);
            } else {
                stmt.setString(8, "Humano");
                stmt.setString(9, "No");
                stmt.setString(10, "No");
            }
            stmt.setInt(11, idPartida);
            stmt.setString(12, usuario);
            stmt.executeUpdate();
        }
        stmt.close();
        // 3.Guardar los datos de los objetos de cada personaje.
        stmt = conn.prepareStatement(Util.IN_EQ_PA_PE);
        for (int i = 0; i < personajes.size(); i++) {
            pj = personajes.get(i);
            equipamiento = pj.getEquipacion();
            for (int j = 0; j < equipamiento.size(); j++) {
                stmt.setString(1, equipamiento.get(j).getNombre());
                stmt.setInt(2, idPartida);
                stmt.setString(3, pj.getNombre());
                stmt.setBoolean(4, equipamiento.get(j).isEnUso());
                stmt.setString(5, usuario);
                stmt.executeUpdate();
            }
        }
        stmt.close();

        // 4.Actualizar la última modificación
        stmt = conn.prepareStatement(Util.UP_FECHA);
        stmt.setTimestamp(1, Timestamp.valueOf(partida.getFecha()));
        stmt.setString(2, partida.getUsuario());
        stmt.close();
    }

    /**
     * @return the conectado
     */
    public boolean isConectado() {
        return conectado;
    }

    /**
     * Escribe en los ficheros en local la última versión de la base de datos.
     *
     * @param usuario
     * @param idPartida
     */
    private void seleccionarPartidas(String usuario) throws SQLException {
        PreparedStatement stmt;
        ResultSet r;
        String linea;
        //Partidas
        stmt = conn.prepareStatement(Util.SE_PARTIDA);
        stmt.setString(1, usuario);
        r = stmt.executeQuery();
        while (r.next()) {
            linea = "\n" + r.getInt("IdPartida") + ";";
            linea += String.valueOf(r.getTimestamp("Fecha")) + ";";
            linea += r.getInt("Tiempo") + ";";
            linea += r.getInt("Progreso") + ";";
            linea += r.getInt("SedSangre") + ";";
            linea += r.getInt("Sospecha") + ";";
            linea += r.getString("UltimaPista") + ";";
            linea += r.getInt("IdEscena") + ";";
            linea += r.getString("Usuario") + ";";
            linea += r.getString("Dificultad");
            Fichero.escribirTexto(Util.URL_PARTIDA, linea, true);
        }
        r.close();
        stmt.close();

        //Personajes
        stmt = conn.prepareStatement(Util.SE_PERSONAJE);
        stmt.setString(1, usuario);
        r = stmt.executeQuery();
        while (r.next()) {
            linea = "\n" + r.getString("Nombre") + ";";
            linea += r.getInt("Ataque") + ";";
            linea += r.getInt("Defensa") + ";";
            linea += r.getInt("VidaMax") + ";";
            linea += r.getInt("Vida") + ";";
            linea += r.getInt("Dinero") + ";";
            linea += r.getInt("EstadoAnimo") + ";";
            linea += r.getString("NombreClan") + ";";
            linea += r.getString("NombreHabilidad1") + ";";
            linea += r.getString("NombreHabilidad2") + ";";
            linea += r.getInt("IdPartida") + ";";
            linea += r.getString("Usuario");
            Fichero.escribirTexto(Util.URL_PERSONAJE, linea, true);
        }
        r.close();
        stmt.close();

        //Equipos
        stmt = conn.prepareStatement(Util.SE_EQ_PA_PE);
        stmt.setString(1, usuario);
        r = stmt.executeQuery();
        while (r.next()) {
            linea = "\n" + r.getString("NombreEquipo") + ";";
            linea += r.getInt("IdPartida") + ";";
            linea += r.getString("NombrePersonaje") + ";";
            linea += r.getBoolean("EnUso") + ";";
            linea += r.getString("Usuario");
            Fichero.escribirTexto(Util.URL_EQ_PA_PE, linea, true);
        }
        r.close();
        stmt.close();
    }

    /**
     * Comprueba que la copia local y la de la base de datos están
     * actualizadas.En caso de no estarlo, lo sincroniza.
     *
     * @param usuario
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public void sincronizar(String usuario) throws SQLException, IOException, ParseException {
        Timestamp local, bd;
        File f = new File(Util.URL_ULTMA_MODIFICACION);
        ArrayList<String> texto = Fichero.leer(f);
        PreparedStatement stmt;
        ArrayList<Partida> partidas;
        //Última hora local
        local = Timestamp.valueOf(texto.get(0));
        //Última hora en base de datos
        stmt = conn.prepareStatement(Util.SE_HORA);
        stmt.setString(1, usuario);
        ResultSet r = stmt.executeQuery();
        r.next();
        bd = r.getTimestamp("Ultima_Modificacion");
        stmt.close();

        long diferencia = local.getTime() - bd.getTime();
        //Comparo las horas
        if (diferencia > 30000) {
            if (local.after(bd)) {
                //Subo el local a la bd
                //1. Borrar todos los datos de la partida
                stmt = conn.prepareStatement(Util.DE_PARTIDA);
                stmt.setString(1, usuario);
                stmt.executeUpdate();
                //2. Insertar en la bd los datos almacenados en ficheros
                partidas = Fichero.getListaPartidas(usuario);
                for (Partida partida : partidas) {
                    insertarPartida(partida);
                }
            } else if (local.before(bd)) {
                // Bajo bd a local
                seleccionarPartidas(usuario);
            }
        }
        local = new Timestamp(System.currentTimeMillis());
        Fichero.escribirTexto(Util.URL_ULTMA_MODIFICACION, String.valueOf(local), false);
        stmt = conn.prepareStatement(Util.UP_FECHA);
        stmt.setTimestamp(1, local);
        stmt.setString(2, usuario);
        stmt.executeUpdate();
        stmt.close();
    }

}
