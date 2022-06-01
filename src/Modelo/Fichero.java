package Modelo;

import Mascarada.*;
import Vista.VistaPartidas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.text.ParseException;
import java.util.*;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;

/**
 * Gestiona los ficheros en local.
 *
 * @author Gonzalo López Fernández
 */
public final class Fichero {

    /**
     * Borra de un archivo concreto dada su url, todos los elementos cuyo
     * idPartida sea uno en particular.
     *
     * @param url del archivo al purgar.
     * @param idPartida de los datos de partida a eliminar.
     * @param posicion del idPartida dentro del archivo.
     */
    private static void borrarPorFiltro(String url, int idPartida, int posicion) {
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
     * Cifra un texto, para que no pueda ser leido a simple vista.
     *
     * @param texto a cifrar.
     * @return texto cifrado.
     * @throws Exception
     */
    private static String cifrar(String texto) throws Exception {
        Key aesKey = new SecretKeySpec(Util.ENCRYPT_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] encrypted = cipher.doFinal(texto.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * Muestra por pantalla incoherencias en las escenas.
     *
     * @throws java.io.IOException
     */
    public static void comprobarConsistencia() throws IOException {
        HashMap<Integer, Integer> opcionesesenas = new HashMap<>();
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_OPCION);
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

    /**
     * Comprueba si existen la carpeta y los ficheros necesarios para alamacenar
     * los avances en el juego.
     *
     * @throws IOException
     */
    public static void comprobarFicherosDeGuardado() throws IOException {
        File directorio = new File(Util.URL_CARPETA);

        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        guardarEnFichero(Util.URL_PERSONAJE, Util.CABECERA_PERSONAJE);
        guardarEnFichero(Util.URL_EQ_PA_PE, Util.CABECERA_EQ_PA_PE);
        guardarEnFichero(Util.URL_PARTIDA, Util.CABECERA_PARTIDA);
        guardarEnFichero(Util.URL_BD, Util.CABECERA_BD);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        guardarEnFichero(Util.URL_ULTMA_MODIFICACION, String.valueOf(timestamp));
    }

    /**
     * Comprueba si un nombre ya está siendo usado por otro personaje.
     *
     * @param nombre
     * @return true si está disponible, false en otro caso.
     * @throws java.io.FileNotFoundException
     */
    public static boolean comprobarNombrePersonaje(String nombre) throws FileNotFoundException {
        File file = new File(Util.URL_PERSONAJE);
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
     * Descrifra un texto.
     *
     * @param texto a descifrar.
     * @return texto descifrado
     * @throws Exception
     */
    private static String descifrar(String texto) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(texto.replace("\n", ""));

        Key aesKey = new SecretKeySpec(Util.ENCRYPT_KEY.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        String decrypted = new String(cipher.doFinal(encryptedBytes));

        return decrypted;
    }

    /**
     * Elimina todos los datos de una partida.
     *
     * @param idPartida
     * @throws java.io.IOException
     */
    public static void eliminarPartida(int idPartida) throws IOException {
        borrarPorFiltro(Util.URL_PARTIDA, idPartida, 0);
        borrarPorFiltro(Util.URL_PERSONAJE, idPartida, 10);
        borrarPorFiltro(Util.URL_EQ_PA_PE, idPartida, 1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        guardarEnFichero(Util.URL_ULTMA_MODIFICACION, String.valueOf(timestamp));
    }

    /**
     * Genera un scrip con los inserts necesarios para la base de datos básica.
     */
    public static void escribirInsertsBD() throws IOException {
        ArrayList<String> texto;
        String insert, aux;
        String[] linea;
        File f = new File(Util.URL_INSERTS);
        FileWriter fw = new FileWriter(f); // Escritor
        if (!f.exists()) {
            f.createNewFile();
        }
        //fw.write("TRUNCATE TABLE `lamascarada`.`Clan`;\n");
        //fw.write("TRUNCATE TABLE `lamascarada`.`Habilidad`;\n");

        // Habilidades 
        texto = leerJar(Util.JAR_HABILIDAD);
        insert = "INSERT INTO `lamascarada`.`Habilidad` (`Nombre`, `Descripcion`) VALUES ('";
        for (int i = 0; i < texto.size(); i++) {
            linea = texto.get(i).split(";");
            aux = insert + linea[0] + "', '" + linea[1] + "');\n";
            fw.write(aux);
        }

        // Clan 
        texto = leerJar(Util.JAR_CLAN);
        insert = "INSERT INTO `lamascarada`.`Clan` (`Nombre`, `Descripcion`, `Imagen`, `Habilidad_1`, `Habilidad_2`, `Habilidad_3`, `Habilidad_4`) VALUES ('";
        for (int i = 0; i < texto.size(); i++) {
            linea = texto.get(i).split(";");
            aux = insert + linea[0] + "', '" + linea[1] + "', '";
            aux += linea[2] + "', '" + linea[3] + "', '" + linea[4] + "', '";
            aux += linea[5] + "', '" + linea[6] + "');\n";
            fw.write(aux);
        }

        // Personaje 
        texto = leerJar(Util.JAR_PERSONAJE);
        insert = "INSERT INTO `lamascarada`.`personaje` (`Nombre`, `Ataque`, `Defensa`, `VidaMax`, `Vida`, `Dinero`, `EstadoAnimo`, `NombreClan`, `NombreHabilidad1`, `NombreHabilidad2`) VALUES ('";
        for (int i = 0; i < texto.size(); i++) {
            linea = texto.get(i).split(";");
            aux = insert + linea[0] + "', '" + linea[1] + "', '";
            aux += linea[2] + "', '" + linea[3] + "', '" + linea[4] + "', '";
            aux += linea[5] + "', '" + linea[6] + "', '" + linea[7] + "', '";
            aux += linea[8] + "', '" + linea[9] + "');\n";
            fw.write(aux);
        }

        // Escena 
        texto = leerJar(Util.JAR_ESCENA);
        insert = "INSERT INTO `lamascarada`.`escena` (`IdEscena`, `Imagen`, `NombrePersonaje`) VALUES ('";
        for (int i = 0; i < texto.size(); i++) {
            linea = texto.get(i).split(";");
            aux = insert + linea[0] + "', '" + linea[1] + "', '";
            aux += linea[2] + "');\n";
            fw.write(aux);
        }

        fw.close(); // Cerramos el escritor.

    }

    private static void escribirObjetos(ArrayList<String> infoObjetos) {
        File f = new File(Util.URL_EQ_PA_PE);
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
     * Almacena los datos de una partida en el fichero correspondiente.
     *
     * @param partida
     */
    private static void escribirPartida(String info) {
        File f = new File(Util.URL_PARTIDA);
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
    private static void escribirPersonajes(String[] infoPersonajes) {
        File f = new File(Util.URL_PERSONAJE);
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

    /**
     * Sobre escribe un fichero con un texto.
     *
     * @param url del archivo.
     * @param texto a escribir.
     */
    public static void escribirTexto(String url, String texto) {
        File f = new File(url);
        try {
            FileWriter fw = new FileWriter(f); // Escritor
            fw.write(texto);
            fw.close(); // Cerramos el escritor.
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Devuelve un clan especifico dado el nombre
     *
     * @param nombre
     * @return lista de clanes.
     * @throws java.io.IOException
     */
    private static Clan getClan(String nombre) throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_CLAN);
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
     * Devuelve un equipo dado por su nombre.
     *
     * @param string nombre del equipo.
     * @return
     */
    private static Equipo getEquipo(String nombre) throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_EQUIPO);
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
     * Devuelve todo el equipo de un personaje en una partida.
     *
     * @param nombre del personaje.
     * @param idPartida de la partida en juego.
     * @return
     */
    private static ArrayList<Equipo> getEquipos(String nombrePersonaje, int idPartida) throws IOException {
        File file = new File(Util.URL_EQ_PA_PE);
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
    private static ArrayList<Equipo> getEquiposIniciales(String nombre) throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_EQ_PA_PE);
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
     * Devuleve una escena concreta.
     *
     * @param idEscena identificador de la escena.
     * @param idPartida
     * @return escena requerida.
     * @throws java.io.IOException
     */
    public static Escena getEscena(int idEscena, int idPartida) throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_ESCENA);
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
                if (linea[2].equals("No")) {
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
     * Comprueba si se debe guardar en el id actual, o si se ha causado progreso
     * en que id se debe hacer.
     *
     * @param idPartida actual.
     * @param progreso actual
     * @return el mismo idPartida si se deben sobreescribir esos datos, o un
     * nuevo id donde hacerlo.
     */
    private static int getIdGuardado(int idPartida, int progreso) throws FileNotFoundException {
        int id = 0;
        File file = new File(Util.URL_PARTIDA);
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
     * Devuelve, si hay, información extra de una escena por una habilidad.
     *
     * @param idEscena escena de la que se consulta si hay información extra.
     * @param habilidades lista de habilidades que tiene el protagonista.
     * @return el texto con la info extra.
     */
    private String getInfoExtra(int idEscena, HashMap<String, Boolean> habilidades) {
        System.out.println("Estamos trabajando en ello.");
        return "";
    }

    /**
     * Devuelve la lista de clanes disponible para jugar.
     *
     * @return lista de clanes.
     * @throws java.io.IOException
     */
    public static ArrayList<Clan> getListaClanes() throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_CLAN);
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
     * Devuelve lista de todas las partidas guardadas en la base de datos de los
     * protagonistas.
     *
     * @param usuario
     * @return lista de partidas guardadas.
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public static ArrayList<Partida> getListaPartidas(String usuario) throws IOException, ParseException {
        File file = new File(Util.URL_PERSONAJE);
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
            if (Integer.valueOf(linea[6]) == Util.EA_PROTAGONISTA) {
                idPartida = Integer.parseInt(linea[10]);
                partida = getPartida(idPartida, usuario); //Nos devuelve la partida sin el vampiro protagonista.
                if (partida.getUsuario().isBlank()) {
                    clan = getClan(linea[7]);
                    vampire = new Vampire(clan, linea, getEquipos(linea[0], idPartida));
                    partida.setProtagonista(vampire); //Le añadimos el personaje que acabamos de buscar.
                    partida.setPersonajes(getPNJs(idPartida));
                    partidas.add(partida); //Se añade la partida al listado.  
                }
            }
        }
        lector.close();
        return partidas;
    }

    /**
     * Devuelve un id de partida en desuso, si no hay, devuelve el siguiente
     * libre.
     *
     * @return un número de id libre.
     */
    private static int getNuevoIdPartida() throws IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        File file = new File(Util.URL_PARTIDA);
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
     * Devuelve todas las opciones que tiene una escena.
     *
     * @param idEscena de la escena sobre la que se quieren las opciones.
     * @return Arraylist de Opciones.
     */
    private static ArrayList<Opcion> getOpciones(int idEscena) throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_OPCION);
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
     * Devuelve un personaje concreto por su nombre en una partida.
     *
     * @param string
     * @return
     */
    private static Persona getPNJ(String nombre, int idPartida) throws FileNotFoundException, IOException {
        File file = new File(Util.URL_PERSONAJE);
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
     * Devuelve todos los personajes de una partida.
     *
     * @param idPartida de la partida.
     * @return lista de Personas.
     * @throws IOException
     */
    private static ArrayList<Persona> getPNJs(int idPartida) throws IOException {
        File file = new File(Util.URL_PERSONAJE);
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
     * Extrae del jar los pnjs en su estado básico para el juego. A todos se les
     * trata como modificados.
     *
     * @return lista de personas o vampiros
     */
    private static ArrayList<Persona> getPNJsIniciales() throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_PERSONAJE);
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
     * Devuelve una partida en concreto sin el protagonista.
     *
     * @param idPartida identifiador de la partida.
     * @return una partida.
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    private static Partida getPartida(int idPartida, String usuario) throws IOException, ParseException {
        File file = new File(Util.URL_PARTIDA);
        Scanner lector = new Scanner(file);
        String[] linea;
        Partida partida = new Partida();
        boolean encontrado = false;

        lector.nextLine(); //Salta la cabecera del documento
        while ((lector.hasNext() && (!encontrado))) {
            linea = lector.nextLine().split(";");
            if ((Integer.parseInt(linea[0]) == idPartida) && (linea[8].equals(usuario))) {
                partida.setIdPartida(Integer.parseInt(linea[0]));
                partida.setFecha(linea[1]); //Revisar
                partida.setTiempo(Integer.parseInt(linea[2]));
                partida.setProgreso(Integer.parseInt(linea[3]));
                partida.setSedDeSangre(Integer.parseInt(linea[4]));
                partida.setSospecha(Integer.parseInt(linea[5]));
                partida.setUltimaPista(linea[6]);
                partida.setEscena(getEscena(Integer.parseInt(linea[7]), idPartida));
                partida.setUsuario(linea[8]);
                encontrado = true;
            }
        }
        lector.close();
        return partida;
    }

    /**
     * Devuelve todos los textos posibles para una escena en concreto.
     *
     * @param idEscena de la escena.
     * @return una lista de parejas [condición, texto]
     * @throws java.io.IOException
     */
    public static ArrayList<String[]> getTextos(int idEscena) throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(Util.JAR_TEXTO_ESCENA);
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
            try (FileWriter fw = new FileWriter(archivo)) {
                fw.write(texto);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Guarda el estado actual de la partida.
     *
     * @param partida a guardar.
     * @throws java.io.FileNotFoundException
     */
    public static void guardarPartida(Partida partida) throws FileNotFoundException, IOException {
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        guardarEnFichero(Util.URL_ULTMA_MODIFICACION, String.valueOf(timestamp));
    }

    /**
     * Devuelve una Partida con todos los datos iniciales para poder jugar.Y
     * almacena los datos de la nueva partida.
     *
     * @param protagonista
     * @return Partida.
     * @throws java.io.IOException
     */
    public static Partida iniciarNuevaPartida(Vampire protagonista) throws IOException {
        Partida partida = new Partida();
        Escena primera;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Insercción protagonista, primera escena e id.
        partida.setIdPartida(getNuevoIdPartida());
        partida.setProtagonista(protagonista);
        primera = getEscena(0, partida.getIdPartida()); //Primera escena        
        partida.setEscena(primera);
        // Insercción de la hora actual.
        partida.setFecha(String.valueOf(timestamp));
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
     * Extrae el texto del fichero.
     *
     * @param file a leer.
     * @return texto leido.
     */
    public static ArrayList<String> leer(File file) {
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
     * Extrae el texto de un fichero alojado en el jar.
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static ArrayList<String> leerJar(String url) throws IOException {
        InputStream inputStream = VistaPartidas.class.getResourceAsStream(url);
        Scanner lector = new Scanner(inputStream);
        ArrayList<String> textos = new ArrayList<>();
        lector.nextLine(); //Salta la cabecera del documento
        while (lector.hasNext()) {
            textos.add(lector.nextLine());
        }
        lector.close();
        inputStream.close();
        return textos;
    }

}
