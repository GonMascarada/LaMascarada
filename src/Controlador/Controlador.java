package Controlador;

import Modelo.GestorDeDatos;
import Mascarada.*;
import Vista.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
    public boolean comprobarNombrePersonaje(String nombre) throws FileNotFoundException {
        return bbdd.comprobarNombrePersonaje(nombre);
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
     * @throws java.io.IOException
     */
    public void iniciarNuevaPartida(Clan clan, String nombre, String dificultad, String usuario) throws IOException {
        String datos;
        Vampire vampire;
        ArrayList<String[]> textos;
        String texto;

        //Datos del nuevo protagonista
        datos = nombre + ";" + Util.ATQ_VAM + ";" + Util.DEF_VAM + ";";
        datos += Util.VIDA_VAM + ";" + Util.VIDA_VAM + ";";
        datos += Util.DINERO + ";" + Util.EA_PROTAGONISTA + ";" + ";";
        datos += clan.getHabilidadesObtenidas();
        //Creamos al protagonista
        vampire = new Vampire(clan, datos.split(";"), new ArrayList<>());

        //Pedimos a la base de datos una partida con todos los datos iniciales.
        partida = bbdd.iniciarNuevaPartida(vampire);

        //Insertamos el usuario al que pertenece
        partida.setUsuario(usuario);

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
        Escena siguiente = bbdd.getEscena(opcion.getIdEscenaSiguiente(), partida.getIdPartida());
        ArrayList<String[]> textos;
        ArrayList<Opcion> opciones;
        String texto;
        boolean seguir;

        //1.Evaluar la opción que se acaba de tomar.
        seguir = evaluarAccion(opcion);
        partida.sumarTiempo(opcion.getTiempo());

        // Si se tienen que seguir lanzando escenas. 
        // Puede que evaluarAccion haya lanzado ya una alternativa (tienda).
        if (seguir) {
            partida.setEscena(siguiente); //Actualizamos a la siguiente escena.
            //2.Pedir a la base de datos todos los posibles textos.
            textos = bbdd.getTextos(siguiente.getIdEscena());

            //3.Comprobar si se cumple alguna de las condiciones de los textos.
            //IMPORTANTE, SOLO SE PUEDE CUMPLIR UNA ÚNICA CONDICION.
            texto = getTextoCorrecto(textos);

            //3.1 Cambiamos -- por nombre del npc y ++ por el del protagonista.
            texto = texto.replace("++", partida.getProtagonista().getNombre() + ": ");
            if (partida.getEscena().hayPnj()) {
                texto = texto.replace("--", partida.getEscena().getPnj().getNombre() + ": ");
            }

            //4.Insertar a la escena el texto
            partida.getEscena().setTexto(texto);

            //5.Eliminar de la escena siguiente las opciones que no estén disponibles.
            opciones = partida.getEscena().getOpciones();
            for (int i = opciones.size() - 1; i >= 0; i--) {
                if (!evaluarCondicion(opciones.get(i).getCondiccion())) {
                    opciones.remove(i);
                }
            }
            partida.getEscena().setOpciones(opciones);
            System.out.println("Escena: " + partida.getEscena().getIdEscena() + " tiene " + partida.getEscena().getOpciones().size() + " opciones.");
            // 6.Mostrar la escena.
            lanzar();

            // 7. Si se tiene dechercho, obtener info extra y mostrarla si hay.
            if (comprobarAnimalismo()) {
                texto = getTextoExtra(textos);
                if (!texto.equals("")) {
                    PopUpInfoExtra ventana = new PopUpInfoExtra(texto);
                    ventana.setVisible(true);
                }
            }
        }
    }

    /**
     * Guarda la partida en el estado actual.
     *
     * @param nuevaPartida true si es una nueva partida, false en otro caso.
     * @throws java.io.FileNotFoundException
     */
    public void guardarPartida(boolean nuevaPartida) throws FileNotFoundException, IOException {
        // 1. Se eliminan los pnc´s que no han sufrido cambios.
        partida.borrarNpcsInalterados();

        // 2. Se almacena todos los datos de la partida en la base de datos.
        bbdd.guardarPartida(partida, nuevaPartida);
    }

    /**
     * Carga una partida.
     *
     * @param partida
     * @throws java.io.IOException
     */
    public void cargarPartida(Partida partida) throws IOException {
        this.partida = partida;

        lanzar();
    }

    /**
     * Borra de la base de datos una partida.
     *
     * @param idPartida
     * @throws java.io.IOException
     */
    public void eliminarPartida(int idPartida) throws IOException {
        bbdd.eliminarPartida(idPartida);
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
    private boolean evaluarAccion(Opcion opcion) throws FileNotFoundException, IOException {
        boolean seguir = true;
        switch (opcion.getAccion()) {
            case Util.AC_PROGRESO -> {
                partida.setProgreso(partida.getProgreso() + 1);
                bbdd.guardarPartida(partida, false);
            }
            case Util.AC_SOSPECHA -> {
                partida.setSospecha(partida.getSospecha() + 1);
            }
            case Util.AC_SEDSANGRE -> {
                partida.setSedDeSangre(partida.getSedDeSangre() + 1);
            }
            case Util.AC_AGRADAR -> {
                if (partida.getEscena().hayPnj()) {
                    partida.getEscena().getPnj().setEstadoDeAnimo(Util.EA_AGRADECIDO);
                }
            }
            case Util.AC_ENFADAR -> {
                if (partida.getEscena().hayPnj()) {
                    partida.getEscena().getPnj().setEstadoDeAnimo(Util.EA_ENFADADO);
                }
            }
            case Util.AC_FIN -> {
                seguir = false;
                //Lanzar escena fin
            }
            case Util.AC_MOSTRAR_MAPA -> {
                seguir = false;
                new Mapa().setVisible(true);
            }
            case Util.AC_MOSTRAR_TIENDA -> {
                seguir = false;
                new VistaTienda(this).setVisible(true);
            }
            case Util.AC_OBTENER_MAPA -> {
                System.out.println("Vamos a obtener el mapa");
                if (partida.getEscena().hayPnj()) {
                    Equipo e = partida.getEscena().getPnj().delObjeto("Mapa");
                    if (e.getNombre().equals("")) {
                        System.out.println("Error al obtener el mapa, el npc no lo tiene.");
                    }
                    partida.getProtagonista().addObjeto(e);
                    System.out.println(partida.getProtagonista().getInfoEquipo(partida.getIdPartida()));
                }
            }
        }
        return seguir;
    }

    /**
     * Evalua las condicones para ver qué texto se debe mostrar.
     *
     * @param textos lista cuyos campos son [condición, texto]
     * @return el texto oportuno.
     */
    private String getTextoCorrecto(ArrayList<String[]> textos) {
        String texto = "";
        boolean encontrado = false;
        int indice = 0;
        int condicion;
        do {
            condicion = Integer.parseInt(textos.get(indice)[0]);
            if (condicion == Util.SI_ESTANDAR) {
                texto = textos.get(indice)[1];
            } else if (evaluarCondicion(condicion)) {
                texto = textos.get(indice)[1];
                encontrado = true;
            }
            indice++;
        } while ((!encontrado) && (indice < textos.size()));
        return texto;
    }

    /**
     * Si hay info extra en la escena devuelve su texto.
     *
     * @param textos lista cuyos campos son [condición, texto]
     * @return el texto oportuno.
     */
    private String getTextoExtra(ArrayList<String[]> textos) {
        String texto = "";
        boolean encontrado = false;
        int indice = 0;
        int condicion;
        do {
            condicion = Integer.parseInt(textos.get(indice)[0]);
            if (condicion == Util.SI_EXTRA) {
                texto = textos.get(indice)[1];
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
        Escena escena = partida.getEscena();
        String clan = partida.getProtagonista().getClan().getNombre();
        switch (condicion) {
            case Util.SI_ESTANDAR -> {
                cumplida = true;
            }
            case Util.SI_AGRADADO -> {
                if (escena.hayPnj()) {
                    if (escena.getPnj().getEstadoDeAnimo() == Util.EA_AGRADECIDO) {
                        cumplida = true;
                    }
                }
            }
            case Util.SI_ENFADADO -> {
                if (escena.hayPnj()) {
                    if (escena.getPnj().getEstadoDeAnimo() == Util.EA_ENFADADO) {
                        cumplida = true;
                    }
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
            case Util.SI_MAPA -> {
                System.out.println("Compruebo si tiene mapa.");
                if (buscarEquipo("Mapa")) {
                    System.out.println("Mapa encontrado.");
                    cumplida = true;
                }
            }
            case Util.SI_PASS -> {
                System.out.println("Compruebo si tiene Contraseña.");
                if (buscarEquipo("Contraseña")) {
                    System.out.println("Contraseña encontrado.");
                    cumplida = true;
                }
            }
            case Util.SI_LLAVE -> {
                System.out.println("Compruebo si tiene Llave.");
                if (buscarEquipo("Llave")) {
                    System.out.println("Llave encontrado.");
                    cumplida = true;
                }
            }
            case Util.SI_NOTA -> {
                System.out.println("Compruebo si tiene Nota.");
                if (buscarEquipo("Nota")) {
                    System.out.println("Nota encontrado.");
                    cumplida = true;
                }
            }
            case Util.SI_AZUCARILLO -> {
                System.out.println("Compruebo si tiene Azucarillo.");
                if (buscarEquipo("Azucarillo")) {
                    System.out.println("Azucarillo encontrado.");
                    cumplida = true;
                }
            }
            case Util.SI_PISTA -> {
                System.out.println("Compruebo si tiene Nota.");
                if (buscarEquipo("Nota")) {
                    System.out.println("Nota encontrado.");
                    cumplida = true;
                }
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
     * Comprueba si el protagonista tiene la habilidad de animalismo.
     *
     * @return true si tiene la habilidad, false en otro caso.
     */
    private boolean comprobarAnimalismo() {
        Clan clan = partida.getProtagonista().getClan();
        HashMap<String, Boolean> habilidades;
        boolean derecho = false;
        if (clan.getNombre().equals("Nosferatu")) {
            habilidades = clan.getHabilidades();
            if (habilidades.containsKey("Animalismo")) {
                if (habilidades.get("Animalismo")) {
                    return true;
                }
            }
        }
        return derecho;
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
        return bbdd.comprobarNombreUsuario(usuario);
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

}
