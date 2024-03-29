package Controlador;

import Modelo.GestorDeDatos;
import Mascarada.*;
import Vista.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Se encarga de abastecer a las interfaces con la información neceseria.
 * Gestiona todos los recursos.
 *
 * @author Gonzalo López Fernández
 */
public final class Controlador {

    private Partida partida;
    private final GestorDeDatos bbdd;
    private boolean conectado;

    public Controlador() throws IOException {
        bbdd = new GestorDeDatos();
        conectado = bbdd.isConectado();
    }

    /**
     * Devuelve la lista de todos los clanes disponibles.
     *
     * @return lista de todos los clanes de vampiro jugables.
     * @throws java.io.IOException
     */
    public ArrayList<Clan> getListaClanes() throws IOException {
        return bbdd.getListaClanes();
    }

    /**
     * Lista de habilidades de un clan en concreto.
     *
     * @param clan del que se quieren conocer las habilidades.
     * @return lista de títulos de habilidades.
     */
    public String[] getListaHabilidades(Clan clan) {
        HashMap<String, Boolean> habilidades = clan.getHabilidades();
        String[] lista = new String[habilidades.size()];
        Iterator<String> it = habilidades.keySet().iterator();
        for (int i = 0; i < lista.length; i++) {
            lista[i] = it.next();
        }
        return lista;
    }

    /**
     * Lista de todas las partidas que se han guardado.
     *
     * @param usuario
     * @return lista de todas las partidas.
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public ArrayList<Partida> getListaPartidas(String usuario) throws IOException, ParseException {
        return bbdd.getListaPartidas(usuario);
    }

    /**
     * Comprueba que el nombre no le pertenezca ya a otro vampiro.
     *
     * @param nombre a comprobar.
     * @return true si está disponible, false en otro caso.
     * @throws java.io.FileNotFoundException
     */
    public boolean comprobarNombrePersonaje(String nombre, String usuario) throws FileNotFoundException {
        return bbdd.comprobarNombrePersonaje(nombre, usuario);
    }

    /**
     * Reintenta la conexión con la base de datos.
     *
     * @param url
     * @param use
     * @param pas
     * @return
     */
    public boolean conectar(String url, String use, String pas) {
        return bbdd.conectar(url, use, pas);
    }

    /**
     * Inicia una nueva partida.
     *
     * @param clan del nuevo personaje.
     * @param nombre del nuevo personaje.
     * @param dificultad de la partida.
     * @param usuario
     * @throws java.io.IOException
     */
    public void iniciarNuevaPartida(Clan clan, String nombre, String dificultad, String usuario) throws IOException {
        String datos;
        Vampire vampire;
        ArrayList<String> textos;
        Escena e;
        String texto;

        //Datos del nuevo protagonista
        datos = nombre + ";" + Util.ATQ_VAM + ";" + Util.DEF_VAM + ";";
        datos += Util.VIDA_VAM + ";" + Util.VIDA_VAM + ";";
        datos += Util.DINERO + ";" + Util.EA_PROTAGONISTA + ";" + ";";
        datos += clan.getHabilidadesObtenidas();
        //Creamos al protagonista
        vampire = new Vampire(clan, datos.split(";"), new ArrayList<>());

        //Pedimos a la base de datos una partida con todos los datos iniciales.
        partida = bbdd.iniciarNuevaPartida(vampire, usuario);

        //Quitamos las opciones que no sean corrctas.
        e = partida.getEscena();
        e.setOpciones(evaluarOpciones(e.getOpciones()));
        partida.setEscena(e);

        //Insertamos el usuario al que pertenece
        partida.setUsuario(usuario);

        //Insertamos la dificultad
        switch (dificultad) {
            case "Facil" ->
                partida.setDificultad(Util.FACIL);
            case "Normal" ->
                partida.setDificultad(Util.NORMAL);
            case "Dificil" ->
                partida.setDificultad(Util.DIFICIL);
            case "Pesadilla" ->
                partida.setDificultad(Util.PESADILLA);
        }
        //Insertamos el texto correcto para esa escena.
        textos = bbdd.getTextos(partida.getEscena().getIdEscena());
        texto = getTextoCorrecto(textos);
        partida.getEscena().setTexto(texto);
        partida.setUltimaPista(texto);

        //Guardado incial de la partida.
        guardarPartida(true);
        // Mostramos la primera escena.
        lanzar();
    }

    /**
     * Lanza la nueva escena de una opción.
     *
     * @param opcion marca cuál será la nueva escena.
     * @throws java.io.IOException
     */
    public void escoger(Opcion opcion) throws IOException {
        Escena siguiente = bbdd.getEscena(opcion.getIdEscenaSiguiente(), partida.getIdPartida(), partida.getUsuario());
        String texto;
        ArrayList<String> textos;
        boolean seguir;
partida.setEscena(siguiente); //Actualizamos a la siguiente escena.
        // Evaluar la opción que se acaba de tomar.
        seguir = evaluarAccion(opcion.getAccion());
        partida.sumarTiempo(opcion.getTiempo());

        // Si se tienen que seguir lanzando escenas. 
        // Puede que evaluarAccion haya lanzado ya una alternativa (tienda).
        if (seguir) {
            partida.setEscena(siguiente); //Actualizamos a la siguiente escena.

            //Comprueba los textos, inserta el adecuado y comprueba las opciones.
            textos = evaularTextoYOpciones();

            // Mostrar la escena.
            lanzar();

            if (partida.getProtagonista().tieneAuspex()) {
                if (siguiente.hayPnj()) {
                    new VistaFicha(this, false).setVisible(true);
                }
            }

            // Si se tiene dechercho, obtener info extra y mostrarla si hay.
            if (partida.getProtagonista().tieneAnimalismo()) {
                texto = getTextoExtra(textos);
                if (!texto.equals("")) {
                    PopUpInfoExtra ventana = new PopUpInfoExtra(texto);
                    ventana.setVisible(true);
                }
            }
        }
    }

    private ArrayList<String> evaularTextoYOpciones() {
        ArrayList<String> textos;
        ArrayList<Opcion> opciones;
        Escena e = partida.getEscena();
        String texto;
        // Pedir a la base de datos todos los posibles textos.
        textos = bbdd.getTextos(e.getIdEscena());

        // Comprobar si se cumple alguna de las condiciones de los textos.
        //IMPORTANTE, SOLO SE PUEDE CUMPLIR UNA ÚNICA CONDICION.
        texto = getTextoCorrecto(textos);
        // Cambiamos -- por nombre del npc y ++ por el del protagonista.
        texto = texto.replace(".++", partida.getProtagonista().getNombre() + ": ");
        if (partida.getEscena().hayPnj()) {
            texto = texto.replace(".--", partida.getEscena().getPnj().getNombre() + ": ");
        }
        // Insertar a la escena el texto
        partida.getEscena().setTexto(texto);

        // Eliminar de la escena siguiente las opciones que no estén disponibles.
        opciones = evaluarOpciones(partida.getEscena().getOpciones());

        partida.getEscena().setOpciones(opciones);
        System.out.println("Escena: " + partida.getEscena().getIdEscena() + " tiene " + partida.getEscena().getOpciones().size() + " opciones.");
        return textos;
    }

    /**
     * Guarda la partida en el estado actual.
     *
     * @param nuevaPartida true si es una nueva partida, false en otro caso.
     * @throws java.io.FileNotFoundException
     */
    public void guardarPartida(boolean nuevaPartida) throws FileNotFoundException, IOException {
        try {
            // 1. Se eliminan los pnc´s que no han sufrido cambios.
            //partida.borrarNpcsInalterados();

            // 2. Se almacena todos los datos de la partida en la base de datos.
            bbdd.guardarPartida(partida, nuevaPartida);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga una partida.
     *
     * @param partida
     * @throws java.io.IOException
     */
    public void cargarPartida(Partida partida) throws IOException {
        this.partida = partida;
        Escena e = partida.getEscena();
        e.setOpciones(evaluarOpciones(e.getOpciones()));
        partida.setEscena(e);
        System.out.println("Reviso partida");
        System.out.println(partida.getProtagonista().getNombre());
        System.out.println(partida.getEscena().getIdEscena());
        System.out.println(partida.getEscena().getImagen());
        System.out.println(partida.getEscena().hayPnj());
        System.out.println(partida.getIdPartida());
        System.out.println(partida.getFecha());
        System.out.println(partida.getTiempo());
        System.out.println(partida.getProgreso());
        System.out.println(partida.getPersonajes().size());
        System.out.println(partida.getUsuario());
        System.out.println(partida.getDificultad());
        
        
       // System.out.println(partida.get);
        lanzar();
    }

    /**
     * Borra de la base de datos una partida.
     *
     * @param usuario
     * @param idPartida
     * @throws java.io.IOException
     */
    public void eliminarPartida(String usuario, int idPartida) throws IOException {
        try {
            bbdd.eliminarPartida(usuario, idPartida);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muestra la interfaz acorde a la escena.
     *
     */
    private void lanzar() {
        VistaEscena ventana = new VistaEscena(this);
        ventana.setVisible(true);
    }

    /**
     * @return the partida
     */
    public Partida getPartida() {
        return partida;
    }

    /**
     * Evalua las consecuencias de haber tomado una decisión.
     *
     * Si cambia el estado de animo de alguien, si causa progreso, si aumenta la
     * sed de sangre, la sospecha...
     *
     * @param opcion la opción tomada.
     */
    private boolean evaluarAccion(int accion) throws FileNotFoundException, IOException {
        boolean seguir = true;
        boolean aux1, aux2, aux3;

        switch (accion) {
            case Util.AC_PROGRESO -> {
                try {
                    partida.setProgreso(partida.getProgreso() + 1);                
                    bbdd.guardarPartida(partida, false);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case Util.AC_GUARDAR -> {
                guardarPartida(false);
            }
            case Util.AC_SALIR -> {
                try {
                    seguir = false;
                    new VistaPartidas(this, partida.getUsuario()).setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case Util.AC_GUARDAR_Y_SALIR -> {
                aux1 = evaluarAccion(Util.AC_GUARDAR);
                aux2 = evaluarAccion(Util.AC_SALIR);
                seguir = aux1 && aux2;
            }
            case Util.AC_CURAR -> {
                partida.setVidaAlMaximo();
            }
            case Util.AC_SOSPECHA -> {
                partida.setSospecha(partida.getSospecha() + 1);
            }
            case Util.AC_SEDSANGRE -> {
                partida.setSedDeSangre(partida.getSedDeSangre() + 1);
            }
            case Util.AC_PELEAR -> {
                String pelea = "";
                int dañoProta, dañoEnemi;
                Boolean turnoProta = false;
                Vampire prota = partida.getProtagonista();
                Persona enemi = partida.getEscena().getPnj();
                String nomProta = prota.getNombre();
                String nomEnemi = enemi.getNombre();
                if (enemi instanceof Vampire vEnemi) {
                    dañoProta = calcularDaño(prota.getAtaque(), vEnemi.getDefensa());
                    dañoEnemi = calcularDaño(vEnemi.getAtaque(), prota.getDefensa());
                } else {
                    dañoProta = calcularDaño(prota.getAtaque(), enemi.getDefensa());
                    dañoEnemi = calcularDaño(enemi.getAtaque(), prota.getDefensa());
                }
                if (Math.random() < 0.5) {
                    turnoProta = true;
                }
                while ((prota.getVidaActual() > 0) && (enemi.getVidaActual() > 0)) {
                    if (turnoProta) {
                        pelea += nomProta + "(" + prota.getVidaActual() + "//" + prota.getVidaMax() + ") ha causado " + dañoProta + " puntos de daño.\n";
                        enemi.setVidaActual(enemi.getVidaActual() - dañoProta);
                    } else {
                        pelea += nomEnemi + "(" + enemi.getVidaActual() + "//" + enemi.getVidaMax() + ") ha causado " + dañoEnemi + " puntos de daño.\n";
                        prota.setVidaActual(prota.getVidaActual() - dañoEnemi);
                    }
                    turnoProta = !turnoProta;
                }
                new PopUpCombate(pelea).setVisible(true);
            }
            case Util.AC_AGRADAR -> {
                partida.getEscena().getPnj().setEstadoDeAnimo(Util.EA_AGRADECIDO);
            }
            case Util.AC_ENFADAR -> {
                partida.getEscena().getPnj().setEstadoDeAnimo(Util.EA_ENFADADO);
            }
            case Util.AC_OBTENER_LLAVE -> {
                if (partida.getEscena().hayPnj()) {
                    Equipo e = partida.getEscena().getPnj().delObjeto("Llave");
                    partida.getProtagonista().addObjeto(e);
                }
            }
            case Util.AC_OBTENER_MAPA -> {
                if (partida.getEscena().hayPnj()) {
                    Equipo e = partida.getEscena().getPnj().delObjeto("Mapa");
                    partida.getProtagonista().addObjeto(e);
                }
            }
            case Util.AC_OBTENER_MASCARILLA -> {
                if (partida.getEscena().hayPnj()) {
                    Equipo e = partida.getEscena().getPnj().delObjeto("Mascarilla");
                    partida.getProtagonista().addObjeto(e);
                }
            }
            case Util.AC_OBTENER_PISTOLA -> {
                if (partida.getEscena().hayPnj()) {
                    Equipo e = partida.getEscena().getPnj().delObjeto("Pistola");
                    partida.getProtagonista().addObjeto(e);
                }
            }
            case Util.AC_OBTENER_PASS -> {
                if (partida.getEscena().hayPnj()) {
                    Equipo e = partida.getEscena().getPnj().delObjeto("Contraseña");
                    partida.getProtagonista().addObjeto(e);
                }
            }
            case Util.AC_OBTENER_LECTURA -> {//No de un pnj
                Equipo e = bbdd.getEquipo("Lectura de manos");
                partida.getProtagonista().addObjeto(e);
            }
            case Util.AC_OBTENER_NOTA -> { //No de un pnj
                Equipo e = bbdd.getEquipo("Nota");
                partida.getProtagonista().addObjeto(e);
            }
            case Util.AC_OBTENER_COLGANTE_ROJO -> { //No de un pnj
                Equipo e = bbdd.getEquipo("Colgante Rojo");
                partida.getProtagonista().addObjeto(e);
            }            
            case Util.AC_OBTENER_PASS_Y_PROGRESO -> { 
                aux1 = evaluarAccion(Util.AC_OBTENER_PASS);
                aux2 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2;
            }
            case Util.AC_OBTENER_LLAVE_Y_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_OBTENER_LLAVE);
                aux2 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2;
            }
            case Util.AC_OBTENER_LLAVE_Y_AGRADAR_Y_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_OBTENER_LLAVE);
                aux2 = evaluarAccion(Util.AC_AGRADAR);
                aux3 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2 && aux3;
            }
            case Util.AC_OBTENER_LLAVE_Y_ENFADAR_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_OBTENER_LLAVE);
                aux2 = evaluarAccion(Util.AC_ENFADAR);
                aux3 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2 && aux3;
            }
            case Util.AC_OBTENER_MAPA_Y_AGRADAR_Y_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_OBTENER_MAPA);
                aux2 = evaluarAccion(Util.AC_AGRADAR);
                aux3 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2 && aux3;
            }
            case Util.AC_OBTENER_MAPA_Y_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_OBTENER_MAPA);
                aux3 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux3;
            }
            case Util.AC_OBTENER_MAPA_Y_ENFADAR_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_OBTENER_MAPA);
                aux2 = evaluarAccion(Util.AC_ENFADAR);
                aux3 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2 && aux3;
            }
            case Util.AC_OBTENER_MASCARILLA_Y_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_OBTENER_MASCARILLA);
                aux2 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2;
            }
            case Util.AC_OBTENER_NOTA_Y_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_OBTENER_NOTA);
                aux2 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2;
            }
            case Util.AC_LECTURA_MANOS -> {
                String texto = bbdd.getInfoHabilidades(partida.getProtagonista().getHabilidades().split(";"));
                new PopUpInfoExtra(texto).setVisible(true);
            }
            case Util.AC_LECTURA_MANOS_AGRADAR_Y_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_LECTURA_MANOS);
                aux2 = evaluarAccion(Util.AC_AGRADAR);
                aux3 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2 && aux3;
            }
            case Util.AC_LECTURA_MANOS_AC_OBTENER_LECTURA_Y_PROGRESO -> {
                aux1 = evaluarAccion(Util.AC_LECTURA_MANOS);
                aux2 = evaluarAccion(Util.AC_OBTENER_LECTURA);
                aux3 = evaluarAccion(Util.AC_PROGRESO);
                seguir = aux1 && aux2 && aux3;
            }
            case Util.AC_FIN -> {
                try {
                    seguir = false;
                    new VistaPartidas(this, partida.getUsuario()).setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case Util.AC_MOSTRAR_MAPA -> {
                seguir = false;
                new VistaMapa(this).setVisible(true);
            }
            case Util.AC_MOSTRAR_TIENDA -> {
                seguir = false;
                new VistaTienda(this).setVisible(true);
            }
        }

        if (evaularMecanica()) {
            Escena e = bbdd.getEscena(Util.ES_MUERTE, partida.getIdPartida(), partida.getUsuario());
            partida.setEscena(e);
        }
        return seguir;
    }

    /**
     * Evalua si uno de los valores de la mecánica ha alcanzado el máximo.
     *
     * @param valor true si se debe detener el juego, false en otro caso.
     * @return
     */
    private boolean evaularMecanica() {
        int dif = partida.getDificultad();
        return (partida.getSedDeSangre() >= dif)
                || (partida.getSospecha() >= dif)
                || (partida.getProtagonista().getVidaActual() <= 0);
    }

    /**
     * Calcula el daño que se hace en funcion del ataque del atacante y la
     * defensa del defensor.
     *
     * @param ataque del atacante.
     * @param defensa del defensor.
     * @return
     */
    private int calcularDaño(int ataque, int defensa) {
        int daño = ataque - defensa;
        if (daño < 0) {
            daño = 0;
        }
        return daño;
    }

    /**
     * Evalua las condicones para ver qué texto se debe mostrar.
     *
     * @param textos lista cuyos campos son [condición, texto]
     * @return el texto oportuno.
     */
    private String getTextoCorrecto(ArrayList<String> textos) {
        String texto = "";
        String[] aux;
        int indice = 0;
        int condicion;
        do {
            aux = textos.get(indice).split(";");
            condicion = Integer.parseInt(aux[0]);
            if (condicion == Util.SI_ESTANDAR) {
                texto = aux[1];
            } else if (condicion == Util.SI_EXTRA) {
                texto = aux[1];
                new PopUpInfoExtra(texto).setVisible(true);
            } else if (evaluarCondicion(condicion)) {
                texto = aux[1];
            }
            indice++;
        } while (indice < textos.size());
        return texto;
    }

    /**
     * Si hay info extra en la escena devuelve su texto.
     *
     * @param textos lista cuyos campos son [condición, texto]
     * @return el texto oportuno.
     */
    private String getTextoExtra(ArrayList<String> textos) {
        String texto = "";
        boolean encontrado = false;
        int indice = 0;
        int condicion;
        String[] aux;
        do {
            aux = textos.get(indice).split(";");
            condicion = Integer.parseInt(aux[0]);
            if (condicion == Util.SI_ANIMALISMO) {
                texto = aux[1];
                encontrado = true;
            }
            indice++;
        } while ((!encontrado) && (indice < textos.size()));
        return texto;
    }

    /**
     * Comprueba si se cumple la condición.
     *
     * @param condicion a comprobar.
     * @return true si se cumple la condición, false en otro caso.
     */
    private boolean evaluarCondicion(int condicion) {
        boolean cumplida = false;
        boolean aux1, aux2, aux3;
        Escena escena = partida.getEscena();
        String clan = partida.getProtagonista().getClan().getNombre();
        switch (condicion) {
            case Util.SI_ESTANDAR -> {
                cumplida = true;
            }
            case Util.SI_AGRADADO -> {
                if (escena.getPnj().getEstadoDeAnimo() == Util.EA_AGRADECIDO) {
                    System.out.println(escena.getPnj().getNombre() + " agradecido.");
                    cumplida = true;
                }
                System.out.println(escena.getPnj().getNombre() + " NO agradecido.");
            }
            case Util.SI_ENFADADO -> {
                if (escena.getPnj().getEstadoDeAnimo() == Util.EA_ENFADADO) {
                    cumplida = true;
                }
            }
            case Util.SI_BRUJAH -> {
                if (clan.equals("Brujah")) {
                    cumplida = true;
                }
            }
            case Util.SI_TREMERE -> {
                if (clan.equals("Tremere")) {
                    cumplida = true;
                }
            }
            case Util.SI_NOSFERATU -> {
                if (clan.equals("Nosferatu")) {
                    cumplida = true;
                }
            }
            case Util.SI_VENTRUE -> {
                if (clan.equals("Ventrue")) {
                    cumplida = true;
                }
            }
            case Util.SI_BRUJAH_O_VENTRUE -> {
                if ((clan.equals("Brujah")) || (clan.equals("Ventrue"))) {
                    cumplida = true;
                }
            }
            case Util.SI_MAPA -> {
                if (buscarEquipo("Mapa")) {
                    cumplida = true;
                }
            }
            case Util.SI_PASS -> {
                if (buscarEquipo("Contraseña")) {
                    cumplida = true;
                }
            }
            case Util.SI_NO_PASS -> {
                cumplida = !evaluarCondicion(Util.SI_PASS);
            }
            case Util.SI_LLAVE -> {
                if (buscarEquipo("Llave")) {
                    cumplida = true;
                }
            }
            case Util.SI_NOTA -> {
                if (buscarEquipo("Nota")) {
                    cumplida = true;
                }
            }
            case Util.SI_PISTA -> {
                if (buscarEquipo("Nota")) {
                    cumplida = true;
                }
            }
            case Util.SI_MASCARILLA -> {
                if (buscarEquipo("Mascarilla")) {
                    cumplida = true;
                }
            }
            case Util.SI_COLGANTE_ROJO -> {
                if (buscarEquipo("Colgante Rojo")) {
                    cumplida = true;
                }
            }
            case Util.SI_LECTURA -> {
                if (buscarEquipo("Lectura de manos")) {
                    cumplida = true;
                }
            }
            case Util.SI_PISTOLA -> {
                if (buscarEquipo("Pistola")) {
                    cumplida = true;
                }
            }
            case Util.SI_NO_MASCARILLA -> {
                cumplida = !evaluarCondicion(Util.SI_MASCARILLA);
            }
            case Util.SI_NO_MAPA -> {
                cumplida = !evaluarCondicion(Util.SI_MAPA);
            }
            case Util.SI_NO_NOSFERATU -> {
                cumplida = !evaluarCondicion(Util.SI_NOSFERATU);
            }
            case Util.SI_NOSFERATU_U_OTRO_EA -> {
                //Ser nosferatu o estar contento o enfadado
                aux1 = evaluarCondicion(Util.SI_NOSFERATU);
                aux2 = evaluarCondicion(Util.SI_AGRADADO);
                aux3 = evaluarCondicion(Util.SI_ENFADADO);
                cumplida = aux1 && (aux2 || aux3);
            }
            case Util.SI_NO_NOSFERATU_Y_PASS_Y_NO_AGRADADO -> {
                //No ser nosferatu, tener la contrasña y el pnj no esté agradado
                aux1 = evaluarCondicion(Util.SI_NO_NOSFERATU);
                aux2 = evaluarCondicion(Util.SI_PASS);
                aux3 = !evaluarCondicion(Util.SI_AGRADADO);
                cumplida = aux1 && aux2 && aux3;
            }
            case Util.SI_NO_NOSFERATU_Y_PASS_Y_NO_LECTURA -> {
                aux1 = evaluarCondicion(Util.SI_NO_NOSFERATU);
                aux2 = evaluarCondicion(Util.SI_PASS);
                aux3 = !evaluarCondicion(Util.SI_LECTURA);
                cumplida = aux1 && aux2 && aux3;
            }
            case Util.SI_NOSFERATU_Y_NO_MASCARILLA -> {
                //Ser nosferatu y no tener mascarilla.
                aux1 = evaluarCondicion(Util.SI_NOSFERATU);
                aux2 = !evaluarCondicion(Util.SI_MASCARILLA);
                cumplida = aux1 && aux2;
            }

            case Util.SI_VENTRUE_Y_NO_PISTOLA -> {
                //Ser Ventrue y no tener pistola.
                aux1 = evaluarCondicion(Util.SI_VENTRUE);
                aux2 = !evaluarCondicion(Util.SI_PISTOLA);
                cumplida = aux1 && aux2;
            }
            case Util.SI_NOSFERATU_Y_MASCARILLA -> {
                //Ser nosferatu y tener mascarilla.
                aux1 = evaluarCondicion(Util.SI_NOSFERATU);
                aux2 = evaluarCondicion(Util.SI_MASCARILLA);
                cumplida = aux1 && aux2;
            }
            case Util.SI_TREMERE_Y_NO_COLGANTE_ROJO -> {
                //Ser tremere y no tener colgante rojo.
                aux1 = evaluarCondicion(Util.SI_TREMERE);
                aux2 = !evaluarCondicion(Util.SI_COLGANTE_ROJO);
                cumplida = aux1 && aux2;
            }
            case Util.SI_NOSFERATU_Y_NO_LLAVE -> {
                aux1 = evaluarCondicion(Util.SI_NOSFERATU);
                aux2 = !evaluarCondicion(Util.SI_LLAVE);
                cumplida = aux1 && aux2;
            }
            case Util.SI_TREMERE_Y_NO_LLAVE -> {
                aux1 = evaluarCondicion(Util.SI_TREMERE);
                aux2 = !evaluarCondicion(Util.SI_LLAVE);
                cumplida = aux1 && aux2;
            }
            case Util.SI_BRUJAH_O_VENTRUE_Y_NO_LLAVE -> {
                aux1 = evaluarCondicion(Util.SI_BRUJAH);
                aux2 = evaluarCondicion(Util.SI_VENTRUE);
                aux3 = !evaluarCondicion(Util.SI_LLAVE);
                cumplida = (aux1 || aux2) && aux3;
            }
            case Util.SI_BRUJAH_Y_NO_PISTOLA -> {
                aux1 = evaluarCondicion(Util.SI_BRUJAH);
                aux2 = !evaluarCondicion(Util.SI_PISTOLA);
                cumplida = aux1 && aux2;
            }
            case Util.SI_LLAVE_Y_NO_NOTA -> {
                aux1 = evaluarCondicion(Util.SI_LLAVE);
                aux2 = !evaluarCondicion(Util.SI_NOTA);
                cumplida = aux1 && aux2;
            }
        }
        return cumplida;
    }

    /**
     * Busca en el inventario del protagonista un objeto.
     *
     * @param equipo nombre del objeto.
     * @return true si lo tiene, false en otro caso.
     */
    private boolean buscarEquipo(String equipo) {
        ArrayList<Equipo> equipos = partida.getProtagonista().getEquipacion();
        boolean encontrado = false;
        int indice = 0;
        while ((!encontrado) && (indice < equipos.size())) {
            if (equipos.get(indice).getNombre().equals(equipo)) {
                encontrado = true;
            }
            indice++;
        }
        return encontrado;
    }

    /**
     * @return the conectado
     */
    public boolean isConectado() {
        return conectado;
    }

    /**
     * Devuelve la configuración con la base de datos almacenada en el fichero
     * bd.csv
     *
     * @return Texto con la url, usuario y contraseña.
     */
    public String getConfiguracionBD() {
        return bbdd.getConfiguracionBD();
    }

    /**
     * Comprueba en la base de datos si el usuario y la contraseña son
     * correctos.
     *
     * @param text
     * @param password
     * @return
     */
    public boolean comprobarCredenciales(String text, String password) {
        return bbdd.comprobarCredenciales(text, password);
    }

    /**
     * Comprueba si un nombre de usuario está disponible.
     *
     * @param usuario
     * @return true si está disponible, false en otro caso.
     */
    public boolean comprobarNombreUsuario(String usuario) {
        if (!usuario.equals("Local")) {
            return bbdd.comprobarNombreUsuario(usuario);
        }
        return false;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario
     * @param pass
     */
    public void crearNuevoUsuario(String usuario, String pass) {
        bbdd.crearNuevoUsuario(usuario, pass);
    }

    /**
     * Carga una escena en concreto.
     *
     * @param id
     */
    public void cargarEscena(int id) {
        Escena escena = bbdd.getEscena(id, partida.getIdPartida(), partida.getUsuario());
        partida.setEscena(escena);
        evaularTextoYOpciones();
        lanzar();
    }

    /**
     * Elimina las opciones que no se deben mostrar.
     *
     * @param opciones
     * @return
     */
    private ArrayList<Opcion> evaluarOpciones(ArrayList<Opcion> opciones) {
        for (int i = opciones.size() - 1; i >= 0; i--) {
            if (!evaluarCondicion(opciones.get(i).getCondiccion())) {
                opciones.remove(i);
            }
        }
        return opciones;
    }
}
