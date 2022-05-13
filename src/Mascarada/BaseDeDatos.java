package Mascarada;

import Vista.Inicio;
import java.io.InputStream;
import java.util.HashMap;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestiona el amacenamiento permanente de los datos.
 *
 * @author Gonzalo López Fernández
 */
public class BaseDeDatos {

    private boolean conectado;
    private Connection conn;
    private Statement stmt;

    public BaseDeDatos() {
        conectar();
        if (conectado) {
            //           sincronizar();
        }
    }

    /**
     * Devuelve la primera escena inicial de un clan.
     *
     * @param clan
     * @return primera escena.
     */
    public Escena getPrimeraEscena(Clan clan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Devuelve la escena por haber perido todos los puntos de vida.
     *
     * @return escena por muerte.
     */
    public Escena getEscenaMuerte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Devuleve una escena concreta.
     *
     * @param idEscena identificador de la escena.
     * @return escena requerida.
     */
    public Escena getEscena(int idEscena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Devuelve toda la información de un protagonista.
     *
     * @param nombre del protagonista.
     * @param idPartida
     * @return vampire protagonista.
     */
    public Vampire getProtagonista(String nombre, int idPartida) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/personaje.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        Vampire vampire;
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            if (linea[0].equals(nombre)) {
                Clan clan = getClan(linea[7]);
                vampire = new Vampire(clan, linea,)
            }
        }
    }

    /**
     * Guarda el estado actual de la partida.
     *
     * @param partida a guardar.
     */
    public void guardarPartida(Partida partida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Devuelve lista de todas las partidas guardadas en la base de datos.
     *
     * @return lista de partidas guardadas.
     */
    public ArrayList<Partida> getListaPartidas() {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/partida.csv");
        Scanner lector = new Scanner(inputStream);
        ArrayList<Partida> partidas = new ArrayList<>();
        String[] linea;
        Partida partida = new Partida();
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            partida.setIdPartida(Integer.parseInt(linea[0]));
            partida.setFecha(new java.util.Date(linea[1])); //Revisar
            partida.setTiempo(Integer.parseInt(linea[2]));
            partida.setProgreso(Integer.parseInt(linea[3]));
            partida.setSedDeSangre(Integer.parseInt(linea[4]));
            partida.setSospecha(Integer.parseInt(linea[5]));
            partida.setUltimaPista(linea[6]);
            partida.setEscena(getEscena(Integer.parseInt(linea[7])));
            partida.setProtagonista(getProtagonista(linea[8], partida.getIdPartida()));
            partidas.add(partida);
        }
        return partidas;
    }

    /**
     * Elimina todos los datos de una partida.
     *
     * @param partida
     */
    void borrarPartida(Partida partida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Devuelve, si hay, información extra de una escena por una habilidad.
     *
     * @param idEscena escena de la que se consulta si hay información extra.
     * @param clan habilidades que tiene el protagonista.
     * @return
     */
    String getInfoExtra(int idEscena, HashMap<String, Boolean> habilidades) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Devuelve la lista de clanes disponible para jugar.
     *
     * @return lista de clanes.
     */
    public ArrayList<Clan> getListaClanes() {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/clan.csv");
        Scanner lector = new Scanner(inputStream);
        ArrayList<Clan> clanes = new ArrayList<>();
        HashMap<String, Boolean> habilidades = new HashMap<>();
        String[] linea;
        Clan clan;
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            clan = new Clan(linea);
            clanes.add(clan);
        }
        return clanes;
    }

    /**
     * Devuelve un clan especifico dado el nombre
     *
     * @param nombre
     * @return lista de clanes.
     */
    public Clan getClan(String nombre) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/clan.csv");
        Scanner lector = new Scanner(inputStream);
        Clan clan = new Clan();
        HashMap<String, Boolean> habilidades = new HashMap<>();
        String[] linea;
        boolean encontrado = false;
        while ((lector.hasNext()&&!encontrado)) {
            linea = lector.nextLine().split(";");
            if(linea[0].equals(nombre)){
                clan = new Clan(linea);
                encontrado=true;
            }
        }
        return clan;
    }

    /**
     * Comprueba si un nombre ya está siendo usado por otro personaje.
     *
     * @param nombre
     * @return true si está disponible, false en otro caso.
     */
    public boolean comprobarNombrePersonaje(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Crea la sesión con la base de datos.
     */
    private void conectar() {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/conexionBD.csv");
        Scanner lector = new Scanner(inputStream);
        String[] conexion = new String[3];// url, user y pass en ese orden.
        String[] linea;

        //Se extraen los datos para la conexión de conexionBD.csv
        for (int i = 0; i < conexion.length; i++) {
            linea = lector.nextLine().split(";");
            System.out.println("Obtenido: " + linea[0]);
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
        } catch (Exception e) {
            System.out.println("Error: " + e);
            conectado = false;
        }
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
     * Comprueba que la copia local y la de la base de datos están actualizadas.
     * En caso de no estarlo, lo sincroniza.
     */
    private void sincronizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
