package Modelo;

import Mascarada.*;
import Vista.Inicio;
import java.io.File;


import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Gestiona el amacenamiento permanente de los datos.
 *
 * @author Gonzalo López Fernández
 */
public class BaseDeDatos {

    private static final String URL_PERSONAJE = "C:\\Users\\Public\\Documents\\La Mascarada\\personaje.csv";
    private static final String URL_EQ_PA_PE = "C:\\Users\\Public\\Documents\\La Mascarada\\equipo-partida-personaje.csv";
    private static final String URL_PARTIDA = "C:\\Users\\Public\\Documents\\La Mascarada\\partida.csv";
    private static final String URL_ULTMA_MODIFICACION = "C:\\Users\\Public\\Documents\\La Mascarada\\ultimaModificacion.csv";
    private boolean conectado;
    private Connection conn;
    private Statement stmt;

    public BaseDeDatos() throws IOException {
        File directorio = new File("C:\\Users\\Public\\Documents\\La Mascarada");

        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        guardarEnFichero(URL_PERSONAJE,
                "﻿Nombre;Ataque;Defensa;VidaMax;Vida;Dinero;EstadoDeAnimo;NombreDeClan;Habilidad1;Habilidad2;IdPartida");
        guardarEnFichero(URL_EQ_PA_PE,
                "﻿NombreEquipo;IdPartida;NombrePersonaje;EnUso");
        guardarEnFichero(URL_PARTIDA,
                "﻿IdPartida;Fecha;Tiempo;Progreso;SedDeSangre;Sospecha;ÚltimaPista;IdEscena");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        guardarEnFichero(URL_ULTMA_MODIFICACION,
                dtf.format(LocalDateTime.now())
        );

        //conectar();
        if (conectado) {
            //           sincronizar();
        }
    }

    /**
     * Si el archivo en la url no existe, entonces lo crea, y escribe el texto.
     *
     * @param url de archivo que comprobar si existe.
     * @param texto a escribir en el archivo.
     * @throws IOException
     */
    private static void guardarEnFichero(String url, String texto) throws IOException {
        File archivo = new File(url);
        if (!archivo.exists()) {
            archivo.createNewFile();
            try {
                FileWriter fw = new FileWriter(archivo); // Escritor
                fw.write(texto);
                fw.close(); // Cerramos el escritor.
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Devuelve la escena por haber perido todos los puntos de vida.
     *
     * @return escena por muerte.
     * @throws java.io.IOException
     */
    public Escena getEscenaMuerte() throws IOException {
        return getEscena(999999999, 0); //Habrá que marcar cual es la escena de muerte.
    }

    /**
     * Devuelve todos los textos posibles para una escena en concreto.
     *
     * @param idEscena de la escena.
     * @return una lista de parejas [condición, texto]
     * @throws java.io.IOException
     */
    public ArrayList<String[]> getTextos(int idEscena) throws IOException {
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
        lector.close();
        inputStream.close();
        return textos;
    }

    /**
     * Devuleve una escena concreta.
     *
     * @param idEscena identificador de la escena.
     * @param idPartida
     * @return escena requerida.
     * @throws java.io.IOException
     */
    public Escena getEscena(int idEscena, int idPartida) throws IOException {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/escena.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        Escena escena = new Escena();
        Persona persona;
        ArrayList<Opcion> opciones;
        boolean encontrado = false;
        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && !encontrado)) {
            linea = lector.nextLine().split(";");
            if (Integer.parseInt(linea[0]) == idEscena) {
                encontrado = true;
                opciones = getOpciones(idEscena);
                if (linea[2].equals("0")) {
                    escena = new Escena(linea, opciones);
                } else {
                    persona = getPNJ(linea[2], idPartida);
                    escena = new Escena(linea, opciones, persona);
                }
            }
        }
        lector.close();
        inputStream.close();
        return escena;
    }

    /**
     * Devuelve todas las opciones que tiene una escena.
     *
     * @param idEscena de la escena sobre la que se quieren las opciones.
     * @return Arraylist de Opciones.
     */
    private ArrayList<Opcion> getOpciones(int idEscena) throws IOException {
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
        lector.close();
        inputStream.close();
        return opciones;
    }

    /**
     * Devuelve todo el equipo de un personaje en una partida.
     *
     * @param nombre del personaje.
     * @param idPartida de la partida en juego.
     * @return
     */
    private ArrayList<Equipo> getEquipos(String nombrePersonaje, int idPartida) throws IOException {
        File file = new File(URL_EQ_PA_PE);
        Scanner lector = new Scanner(file);

        String[] linea;
        ArrayList<Equipo> equipos = new ArrayList<>();
        Equipo equipo;
        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            if ((Integer.parseInt(linea[1]) == idPartida) && (linea[2].equals(nombrePersonaje))) {
                equipo = getEquipo(linea[0]);
                equipo.setEnUso(Boolean.valueOf(linea[3]));
                equipos.add(equipo);
            }
        }
        lector.close();
        return equipos;
    }

    /**
     * Extrae del jar el equipo inicial de un personaje.
     *
     * @param nombre del personaje.
     * @return lista de su inventario.
     */
    private ArrayList<Equipo> getEquiposIniciales(String nombre) throws IOException {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/equipo-partida-personaje.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        ArrayList<Equipo> equipos = new ArrayList<>();
        Equipo equipo;
        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            if (linea[1].equals(nombre)) {
                equipo = getEquipo(linea[0]);
                equipo.setEnUso(Boolean.valueOf(linea[2]));
                equipos.add(equipo);
            }
        }
        lector.close();
        inputStream.close();
        return equipos;
    }

    /**
     * Devuelve un equipo dado por su nombre.
     *
     * @param string nombre del equipo.
     * @return
     */
    private Equipo getEquipo(String nombre) throws IOException {
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
        lector.close();
        inputStream.close();
        return equipo;
    }

    /**
     * Devuelve lista de todas las partidas guardadas en la base de datos de los
     * protagonistas.
     *
     * @return lista de partidas guardadas.
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public ArrayList<Partida> getListaPartidas() throws IOException, ParseException {
        File file = new File(URL_PERSONAJE);
        Scanner lector = new Scanner(file);
        ArrayList<Partida> partidas = new ArrayList<>();
        String[] linea;
        int idPartida;
        Vampire vampire;
        Clan clan;
        Partida partida;
        lector.nextLine(); //Salta la cabecera del documento
        //Buscamos en personaje.csv todos los personajes que sean protagonistas.
        while (lector.hasNext()) {
            String aux = lector.nextLine();
            linea = aux.split(";");
            // Si el estado de animo es PROTAGONISTA recuperaremos su partida.
            if (Integer.valueOf(linea[6]) == Utilidades.EA_PROTAGONISTA) {
                clan = getClan(linea[7]);
                idPartida = Integer.parseInt(linea[10]);
                vampire = new Vampire(clan, linea, getEquipos(linea[0], idPartida));
                partida = getPartida(idPartida); //Nos devuelve la partida sin el vampiro protagonista.
                partida.setProtagonista(vampire); //Le añadimos el personaje que acabamos de buscar.
                partida.setPersonajes(getPNJs(idPartida));
                partidas.add(partida); //Se añade la partida al listado.
            }
        }
        lector.close();
        return partidas;
    }

    /**
     * Devuelve todos los personajes de una partida.
     *
     * @param idPartida de la partida.
     * @return lista de Personas.
     * @throws IOException
     */
    public ArrayList<Persona> getPNJs(int idPartida) throws IOException {
        File file = new File(URL_PERSONAJE);
        Scanner lector = new Scanner(file);
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
        lector.close();
        return pnjs;
    }

    /**
     * Devuelve un personaje concreto por su nombre en una partida.
     *
     * @param string
     * @return
     */
    private Persona getPNJ(String nombre, int idPartida) throws FileNotFoundException, IOException {
        File file = new File(URL_PERSONAJE);
        Scanner lector = new Scanner(file);
        String[] linea;
        Persona persona = new Persona();
        Clan clan;
        boolean encontrado = false;

        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && (!encontrado))) {
            linea = lector.nextLine().split(";");
            if ((linea[0].equals(nombre)) && (Integer.parseInt(linea[10]) == idPartida)) {
                ArrayList<Equipo> equipos = getEquipos(linea[0], idPartida);
                if (linea[7].equals("Humano")) {
                    persona = new Persona(linea, equipos);
                } else {
                    clan = getClan(linea[7]);
                    persona = new Vampire(clan, linea, equipos);
                }
                encontrado = true;
            }
        }
        lector.close();
        return persona;
    }

    /**
     * Devuelve una partida en concreto sin el protagonista.
     *
     * @param idPartida identifiador de la partida.
     * @return una partida.
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public Partida getPartida(int idPartida) throws IOException, ParseException {
        File file = new File(URL_PARTIDA);
        Scanner lector = new Scanner(file);
        String[] linea;
        Partida partida = new Partida();
        boolean encontrado = false;

        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && (!encontrado))) {
            linea = lector.nextLine().split(";");
            if (Integer.parseInt(linea[0]) == idPartida) {
                partida.setIdPartida(Integer.parseInt(linea[0]));
                partida.setFecha(linea[1]); //Revisar
                partida.setTiempo(Integer.parseInt(linea[2]));
                partida.setProgreso(Integer.parseInt(linea[3]));
                partida.setSedDeSangre(Integer.parseInt(linea[4]));
                partida.setSospecha(Integer.parseInt(linea[5]));
                partida.setUltimaPista(linea[6]);
                partida.setEscena(getEscena(Integer.parseInt(linea[7]), idPartida));
                encontrado = true;
            }
        }
        lector.close();
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
     * @throws java.io.IOException
     */
    public ArrayList<Clan> getListaClanes() throws IOException {
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
        lector.close();
        inputStream.close();
        return clanes;
    }

    /**
     * Devuelve un clan especifico dado el nombre
     *
     * @param nombre
     * @return lista de clanes.
     * @throws java.io.IOException
     */
    public Clan getClan(String nombre) throws IOException {
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
        lector.close();
        inputStream.close();
        return clan;
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
     * @throws java.io.FileNotFoundException
     */
    public void guardarPartida(Partida partida) throws FileNotFoundException, IOException {
        int id;
        // 1. Comprobar si hay que sobreescribir los datos de esta partidas.
        // O si tiene mayor progreso, crear una partida nueva.
        id = getIdGuardado(partida.getIdPartida(), partida.getProgreso());
        partida.setIdPartida(id);

        // 2.Guardar los datos de la partida.
        escribirPartida(partida.getInfoPartida());

        // 3.Guardar los datos de los personajes, protagonista incluido.
        escribirPersonajes(partida.getInfoPersonajes());

        // 4.Guardar los datos de los objetos de cada personaje.
        escribirObjetos(partida.getInfoObjetos());

        // 5.Actualizar la última modificación
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        guardarEnFichero(URL_ULTMA_MODIFICACION,
                dtf.format(LocalDateTime.now())
        );

        // 6.Sincronizar los datos locales con la base de datos.
        sincronizar();
    }

    /**
     * Almacena los datos de una partida en el fichero correspondiente.
     *
     * @param partida
     */
    private void escribirPartida(String info) {
        File f = new File(URL_PARTIDA);
        try {
            FileWriter fw = new FileWriter(f, true); // Escritor
            fw.write("\n" + info);
            fw.close(); // Cerramos el escritor.
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Almacena los datos de todos los personajes involucrados en una partida en
     * el fichero correspondiente.
     *
     * @param infoPersonajes
     */
    private void escribirPersonajes(String[] infoPersonajes) {
        File f = new File(URL_PERSONAJE);
        try {
            FileWriter fw = new FileWriter(f, true); // Escritor
            for (int i = 0; i < infoPersonajes.length; i++) {
                fw.write("\n" + infoPersonajes[i]);
            }
            fw.close(); // Cerramos el escritor.
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void escribirObjetos(ArrayList<String> infoObjetos) {
        File f = new File(URL_EQ_PA_PE);
        try {
            FileWriter fw = new FileWriter(f, true); // Escritor
            for (int i = 0; i < infoObjetos.size(); i++) {
                fw.write(infoObjetos.get(i));
            }
            fw.close(); // Cerramos el escritor.
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Elimina todos los datos de una partida.
     *
     * @param idPartida
     * @throws java.io.IOException
     */
    public void eliminarPartida(int idPartida) throws IOException {
        borrarPorFiltro(URL_PARTIDA, idPartida, 0);
        borrarPorFiltro(URL_PERSONAJE, idPartida, 10);
        borrarPorFiltro(URL_EQ_PA_PE, idPartida, 1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        guardarEnFichero(URL_ULTMA_MODIFICACION,
                dtf.format(LocalDateTime.now())
        );
        sincronizar();
    }

    /**
     * Borra de un archivo concreto dada su url, todos los elementos cuyo
     * idPartida sea uno en particular.
     *
     * @param url del archivo al purgar.
     * @param idPartida de los datos de partida a eliminar.
     * @param posicion del idPartida dentro del archivo.
     */
    private void borrarPorFiltro(String url, int idPartida, int posicion) {
        File f = new File(url);
        ArrayList<String> texto = leer(f);
        String[] linea;
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(texto.get(0)); //Escribimos la cabecera.
            for (int i = 1; i < texto.size(); i++) {
                linea = texto.get(i).split(";");
                if (Integer.parseInt(linea[posicion]) != idPartida) {
                    fw.write("\n" + texto.get(i));
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Extrae el texto del fichero.
     *
     * @param file a leer.
     * @return texto leido.
     */
    private ArrayList<String> leer(File file) {
        ArrayList<String> texto = new ArrayList<>();
        try {
            Scanner lector = new Scanner(file);
            while (lector.hasNext()) {
                texto.add(lector.nextLine());
            }
            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return texto;
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
     * @throws java.io.FileNotFoundException
     */
    public boolean comprobarNombrePersonaje(String nombre) throws FileNotFoundException {
        File file = new File(URL_PERSONAJE);
        Scanner lector = new Scanner(file);
        String[] linea;
        boolean disponible = true;
        String aux = nombre.replace(" ", "");
        if ((nombre.contains(";")) || (aux.equals(""))) {
            return false;
        } else {
            lector.nextLine(); //Salta la cabecera del documento
            while ((lector.hasNext() && disponible)) {
                linea = lector.nextLine().split(";");
                if (linea[0].equals(nombre)) {
                    disponible = false;
                }
            }
        }
        lector.close();
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
    private int getIdGuardado(int idPartida, int progreso) throws FileNotFoundException {
        int id = 0;
        File file = new File(URL_PARTIDA);
        Scanner lector = new Scanner(file);
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
        lector.close();
        return id;
    }

    /**
     * Devuelve una Partida con todos los datos iniciales para poder jugar.Y
     * almacena los datos de la nueva partida.
     *
     * @param protagonista
     * @return Partida.
     * @throws java.io.IOException
     */
    public Partida iniciarNuevaPartida(Vampire protagonista) throws IOException {
        Partida partida = new Partida();
        Escena primera;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

        // Insercción protagonista, primera escena e id.
        partida.setIdPartida(getNuevoIdPartida());
        partida.setProtagonista(protagonista);
        primera = getEscena(0, partida.getIdPartida()); //Primera escena        
        partida.setEscena(primera);
        // Insercción de la hora actual.
        partida.setFecha(dtf.format(LocalDateTime.now()));
        // Insercción del tiempo, progreso, sed de sangre y sospecha.
        partida.setTiempo(0);
        partida.setProgreso(0);
        partida.setSedDeSangre(0);
        partida.setSospecha(0);
        // Insercción de los npc´s
        ArrayList<Persona> pnjs = getPNJsIniciales();
        partida.setPersonajes(pnjs);
        return partida;
    }

    /**
     * Devuelve un id de partida en desuso, si no hay, devuelve el siguiente
     * libre.
     *
     * @return un número de id libre.
     */
    private int getNuevoIdPartida() throws IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        File file = new File(URL_PARTIDA);
        Scanner lector = new Scanner(file);
        String[] linea;
        int id;

        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            ids.add(Integer.parseInt(linea[0]));
        }
        Collections.sort(ids);
        id = 0;
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == id) {
                id++;
            } else {
                return id;
            }
        }
        lector.close();
        return id;
    }

    /**
     * Extrae del jar los pnjs en su estado básico para el juego. A todos se les
     * trata como modificados.
     *
     * @return lista de personas o vampiros
     */
    private ArrayList<Persona> getPNJsIniciales() throws IOException {
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/personaje.csv");
        Scanner lector = new Scanner(inputStream);
        ArrayList<Persona> pnjs = new ArrayList<>();
        String[] linea;
        Clan clan;
        lector.nextLine(); //Salta la cabecera del documento

        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            ArrayList<Equipo> equipos = getEquiposIniciales(linea[0]);
            if (linea[7].equals("Humano")) {
                Persona p = new Persona(linea, equipos);
                //Fuerzo que hayan sido modificados
                p.setVidaActual(p.getVidaActual());
                pnjs.add(p);
            } else {
                clan = getClan(linea[7]);
                Vampire v = new Vampire(clan, linea, equipos);
                v.setVidaActual(v.getVidaActual());
                pnjs.add(v);
            }

        }
        lector.close();
        inputStream.close();
        return pnjs;
    }

    /**
     * Muestra por pantalla incoherencias en las escenas.
     *
     * @throws java.io.IOException
     */
    public void comprobarConsistencia() throws IOException {
        HashMap<Integer, Integer> opcionesesenas = new HashMap<>();
        InputStream inputStream = Inicio.class.getResourceAsStream("/Ficheros/opcion.csv");
        Scanner lector = new Scanner(inputStream);
        String[] linea;
        int actual, siguente, aux;
        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            linea = lector.nextLine().split(";");
            actual = Integer.parseInt(linea[6]);
            siguente = Integer.parseInt(linea[7]);
            if (!opcionesesenas.containsKey(siguente)) {
                opcionesesenas.put(siguente, 0);
            }
            if (!opcionesesenas.containsKey(actual)) {
                opcionesesenas.put(actual, 1);
            } else {
                opcionesesenas.put(actual, opcionesesenas.get(actual) + 1);
            }
        }
        Iterator<Integer> it = opcionesesenas.keySet().iterator();
        while (it.hasNext()) {
            aux = it.next();
            if (opcionesesenas.get(aux) == 0) {
                System.out.println("No hay opciones para: " + aux);
            }
        }
        lector.close();
        inputStream.close();
    }
}
