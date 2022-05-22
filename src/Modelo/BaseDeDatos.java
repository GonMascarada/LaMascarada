package Modelo;

import Mascarada.*;
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
     * Devuelve la escena por haber perido todos los puntos de vida.
     *
     * @return escena por muerte.
     */
    public Escena getEscenaMuerte() {
        return getEscena(999999999); //Habrá que marcar cual es la escena de muerte.
    }

    /**
     * Devuelve todos los textos posibles para una escena en concreto.
     *
     * @param idEscena de la escena.
     * @return una lista de parejas [condición, texto]
     */
    public ArrayList<String[]> getTextos(int idEscena) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/texto-escena.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        ArrayList<String[]> textos = new ArrayList<>();
        String[] texto = new String[2];
        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            if (Integer.valueOf(linea[0]) == idEscena) {
                texto[0] = linea[1];
                texto[1] = linea[2];
                textos.add(texto);
            }
        }
        return textos;
    }

    /**
     * Devuleve una escena concreta.
     *
     * @param idEscena identificador de la escena.
     * @return escena requerida.
     */
    public Escena getEscena(int idEscena) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/escena.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        Escena escena = new Escena();
        ArrayList<Opcion> opciones;
        boolean encontrado = false;
        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && !encontrado)) {
            linea = lector.nextLine().split(";");
            if (Integer.parseInt(linea[0]) == idEscena) {
                encontrado = true;
                opciones = getOpciones(idEscena);
                escena = new Escena(linea, opciones);
            }
        }
        return escena;
    }

    /**
     * Devuelve todas las opciones que tiene una escena.
     *
     * @param idEscena de la escena sobre la que se quieren las opciones.
     * @return Arraylist de Opciones.
     */
    private ArrayList<Opcion> getOpciones(int idEscena) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/opcion.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        ArrayList<Opcion> opciones = new ArrayList<>();
        Opcion opcion;
        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            if (Integer.valueOf(linea[6]) == idEscena) {
                opcion = new Opcion(linea);
                opciones.add(opcion);
            }
        }
        return opciones;
    }

    /**
     * Devuelve todo el equipo de un personaje en una partida.
     *
     * @param nombre del personaje.
     * @param idPartida de la partida en juego.
     * @return
     */
    private ArrayList<Equipo> getEquipos(String nombrePersonaje, int idPartida) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/equipo-partida-personaje.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        ArrayList<Equipo> equipos = new ArrayList<>();
        Equipo equipo;
        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            if ((linea[1].equals(idPartida) && (linea[2].equals(nombrePersonaje)))) {
                equipo = getEquipo(linea[0]);
                equipo.setEnUso(Boolean.valueOf(linea[3]));
                equipos.add(equipo);
            }
        }
        return equipos;
    }

    /**
     * Devuelve un equipo dado por su nombre.
     *
     * @param string nombre del equipo.
     * @return
     */
    private Equipo getEquipo(String nombre) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/equipo.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        Equipo equipo = new Equipo();
        boolean encontrado = false;
        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && !encontrado)) {
            linea = lector.nextLine().split(";");
            if (linea[0].equals(nombre)) {
                encontrado = true;
                equipo = new Equipo(linea);
            }
        }
        return equipo;
    }

    /**
     * Devuelve lista de todas las partidas guardadas en la base de datos de los
     * protagonistas.
     *
     * @return lista de partidas guardadas.
     */
    public ArrayList<Partida> getListaPartidas() {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/personaje.csv");
        Scanner lector = new Scanner(inputStream);
        ArrayList<Partida> partidas = new ArrayList<>();
        String[] linea;
        int idPartida;
        Vampire vampire;
        Clan clan;
        Partida partida;
        lector.nextLine(); //Salta la cabecera del documento
        //Buscamos en personaje.csv todos los personajes que sean protagonistas.
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            // Si el estado de animo es PROTAGONISTA recuperaremos su partida.
            if (Integer.valueOf(linea[6]) == Utilidades.EA_PROTAGONISTA) {
                clan = getClan(linea[7]);
                idPartida = Integer.parseInt(linea[10]);
                vampire = new Vampire(clan, linea, getEquipos(linea[0], idPartida));
                partida = getPartida(idPartida); //Nos devuelve la partida sin el vampiro protagonista.
                partida.setProtagonista(vampire); //Le añadimos el personaje que acabamos de buscar.                
                partidas.add(partida); //Se añade la partida al listado.
            }
        }
        return partidas;
    }

    public ArrayList<Persona> getPNJs(int idPartida) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/personaje.csv");
        Scanner lector = new Scanner(inputStream);
        ArrayList<Persona> pnjs = new ArrayList<>();
        String[] linea;
        Clan clan;
        lector.nextLine(); //Salta la cabecera del documento
        //Buscamos en personaje.csv todos los personajes que pertenezcan a la partida.
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            // Si el estado de animo es PROTAGONISTA recuperaremos su partida.
            if (Integer.parseInt(linea[10]) == idPartida) {
                ArrayList<Equipo> equipos = getEquipos(linea[0], idPartida);
                if (linea[7].equals("Humano")) {
                    pnjs.add(new Persona(linea, equipos));
                } else {
                    clan = getClan(linea[7]);
                    pnjs.add(new Vampire(clan, linea, equipos));
                }
            }
        }
        return pnjs;
    }

    /**
     * Devuelve una partida en concreto sin el protagonista.
     *
     * @param idPartida identifiador de la partida.
     * @return una partida.
     */
    public Partida getPartida(int idPartida) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/partida.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        Partida partida = new Partida();
        boolean encontrado = false;
        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && (!encontrado))) {
            linea = lector.nextLine().split(";");
            if (Integer.parseInt(linea[0]) == idPartida) {
                partida.setIdPartida(Integer.parseInt(linea[0]));
                partida.setFecha(new java.util.Date(linea[1])); //Revisar
                partida.setTiempo(Integer.parseInt(linea[2]));
                partida.setProgreso(Integer.parseInt(linea[3]));
                partida.setSedDeSangre(Integer.parseInt(linea[4]));
                partida.setSospecha(Integer.parseInt(linea[5]));
                partida.setUltimaPista(linea[6]);
                partida.setEscena(getEscena(Integer.parseInt(linea[7])));
                encontrado = true;
            }
        }
        return partida;
    }

    /**
     * Devuelve, si hay, información extra de una escena por una habilidad.
     *
     * @param idEscena escena de la que se consulta si hay información extra.
     * @param habilidades lista de habilidades que tiene el protagonista.
     * @return el texto con la info extra.
     */
    public String getInfoExtra(int idEscena, HashMap<String, Boolean> habilidades) {
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
        String[] linea;
        Clan clan;
        lector.nextLine(); //Salta la cabecera del documento
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
        String[] linea;
        boolean encontrado = false;
        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && !encontrado)) {
            linea = lector.nextLine().split(";");
            if (linea[0].equals(nombre)) {
                clan = new Clan(linea);
                encontrado = true;
            }
        }
        return clan;
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
     * Guarda el estado actual de la partida.
     *
     * @param partida a guardar.
     */
    public void guardarPartida(Partida partida) {
        int id;
        // 1. Comprobar si hay que sobreescribir los datos de esta partidas.
        // O si tiene mayor progreso, crear una partida nueva.
        id = getIdGuardado(partida.getIdPartida(), partida.getProgreso());
        
        // 2.Guardar los datos de la partida.
        
        // 3.Guardar los datos de los personajes, protagonista incluido.
        
        // 4.Guardar los datos de los objetos de cada personaje.
        
        // 5.Sincronizar los datos locales con la base de datos.
        sincronizar();
    }

    /**
     * Elimina todos los datos de una partida.
     *
     * @param partida
     */
    public void borrarPartida(Partida partida) {
        System.out.println("Estamos trabajando en ello :(");
        sincronizar();
    }

    /**
     * Comprueba que la copia local y la de la base de datos están actualizadas.
     * En caso de no estarlo, lo sincroniza.
     */
    private void sincronizar() {
        System.out.println("Estamos trabajando en ello :(");
    }

    /**
     * Comprueba si un nombre ya está siendo usado por otro personaje.
     *
     * @param nombre
     * @return true si está disponible, false en otro caso.
     */
    public boolean comprobarNombrePersonaje(String nombre) {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/personaje.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        boolean disponible = true;
        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && disponible)) {
            linea = lector.nextLine().split(";");
            if (linea[0].equals(nombre)) {
                disponible = false;
            }
        }
        return disponible;
    }

    /**
     * Comprueba si se debe guardar en el id actual, o si se ha causado progreso
     * en que id se debe hacer.
     *
     * @param idPartida actual.
     * @param progreso actual
     * @return el mismo idPartida si se deben sobreescribir esos datos, o un
     * nuevo id donde hacerlo.
     */
    private int getIdGuardado(int idPartida, int progreso) {
        int id = 0;
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/partida.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            id = Integer.parseInt(linea[0]);
            if (id == idPartida) {
                if (Integer.parseInt(linea[3]) == progreso) {
                    return idPartida;
                }
            }
        }
        id++;
        return id;
    }

}
