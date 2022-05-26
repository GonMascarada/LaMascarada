package Controlador;

import Modelo.BaseDeDatos;
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

    private BaseDeDatos bbdd;

    public Controlador() throws IOException {
        bbdd = new BaseDeDatos();
        partida = new Partida(); //Habrá que borrarlo
    }

    /**
     * Devuelve la lista de todos los clanes disponibles.
     *
     * @return lista de todos los clanes de vampiro jugables.
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
     * @return lista de todas las partidas.
     */
    public ArrayList<Partida> getListaPartidas() throws IOException, ParseException {
        return bbdd.getListaPartidas();
    }

    /**
     * Comprueba que el nombre no le pertenezca ya a otro vampiro.
     *
     * @param nombre a comprobar.
     * @return true si está disponible, false en otro caso.
     */
    public boolean comprobarNombrePersonaje(String nombre) throws FileNotFoundException {
        return bbdd.comprobarNombrePersonaje(nombre);
    }

    /**
     * Inicia una nueva partida.
     *
     * @param clan del nuevo personaje.
     * @param nombre del nuevo personaje.
     * @param dificultad de la partida.
     */
    public void iniciarNuevaPartida(Clan clan, String nombre, String dificultad) throws IOException {
        String datos;
        Vampire vampire;
        ArrayList<String[]> textos;
        String texto;

        //Datos del nuevo protagonista
        datos = nombre + ";" + Utilidades.ATQ_VAM + ";" + Utilidades.DEF_VAM + ";";
        datos += Utilidades.VIDA_VAM + ";" + Utilidades.VIDA_VAM + ";";
        datos += Utilidades.DINERO + ";" + Utilidades.EA_PROTAGONISTA + ";" + ";";
        datos += clan.getHabilidadesObtenidas();
        //Creamos al protagonista
        vampire = new Vampire(clan, datos.split(";"), new ArrayList<Equipo>());

        //Pedimos a la base de datos una partida con todos los datos iniciales.
        partida = bbdd.iniciarNuevaPartida(vampire);

        //Insertamos el texto correcto para esa escena.
        textos = bbdd.getTextos(partida.getEscena().getIdEscena());
        texto = getTextoCorrecto(textos);
        partida.getEscena().setTexto(texto);
        partida.setUltimaPista(texto);

        //Guardado incial de la partida.
        guardarPartida();
        // Mostramos la primera escena.
        lanzar();
    }

    /**
     * Lanza la nueva escena de una opción.
     *
     * @param opcion marca cuál será la nueva escena.
     */
    public void escoger(Opcion opcion) throws IOException {
        Escena siguiente = bbdd.getEscena(opcion.getIdEscenaSiguiente());
        ArrayList<String[]> textos;
        ArrayList<Opcion> opciones;
        String texto;

        //1.Evaluar la opción que se acaba de tomar.
        evaluarAccion(opcion);

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
            if (!evaluarCondicionDeOpcion(opciones.get(i).getCondiccion())) {
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

    /**
     * Guarda la partida en el estado actual.
     */
    public void guardarPartida() throws FileNotFoundException {
        // 1. Se eliminan los pnc´s que no han sufrido cambios.
        partida.borrarNpcsInalterados();

        // 2. Se almacena todos los datos de la partida en la base de datos.
        bbdd.guardarPartida(partida);
    }

    /**
     * Carga una partida.
     *
     * @param partida
     */
    public void cargarPartida(Partida partida) throws IOException {
        this.partida = partida;
        this.partida.setPersonajes(bbdd.getPNJs(partida.getIdPartida()));
        System.out.println("Opciones" + partida.getEscena().getOpciones().size());
        lanzar();
    }

    /**
     * Borra de la base de datos una partida.
     *
     * @param partida
     */
    private void borrarPartida(Partida partida) {
        bbdd.borrarPartida(partida);
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
    private void evaluarAccion(Opcion opcion) throws FileNotFoundException {
        switch (opcion.getAccion()) {
            case Utilidades.OP_PROGRESO -> {
                partida.setProgreso(partida.getProgreso() + 1);
                bbdd.guardarPartida(partida);
            }
            case Utilidades.OP_SOSPECHA -> {
                partida.setSospecha(partida.getSospecha() + 1);
            }
            case Utilidades.OP_SEDSANGRE -> {
                partida.setSedDeSangre(partida.getSedDeSangre() + 1);
            }
            case Utilidades.OP_AGRADAR -> {
                if (partida.getEscena().hayPnj()) {
                    partida.getEscena().getPnj().setEstadoDeAnimo(Utilidades.EA_AGRADECIDO);
                }
            }
            case Utilidades.OP_ENFADAR -> {
                if (partida.getEscena().hayPnj()) {
                    partida.getEscena().getPnj().setEstadoDeAnimo(Utilidades.EA_ENFADADO);
                }
            }
            case Utilidades.OP_OBTENER_MAPA -> {
                if (partida.getEscena().hayPnj()) {
                    Equipo e = partida.getEscena().getPnj().delObjeto("Mapa");
                    if (e.getNombre().equals("")){
                        System.out.println("Error al obtener el mapa, el npc no lo tiene.");
                    }
                    partida.getProtagonista().addObjeto(e);
                }
            }

        }
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
            if (condicion == Utilidades.SI_ESTANDAR) {
                texto = textos.get(indice)[1];
            } else if (evaluarCondicionDeTexto(condicion)) {
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
            if (condicion == Utilidades.SI_EXTRA) {
                texto = textos.get(indice)[1];
                encontrado = true;
            }
            indice++;
        } while ((!encontrado) && (indice < textos.size()));
        return texto;
    }

    /**
     * Comprueba si se cumple la condición de un texto para ser mostrado.
     *
     * @param condicion a comprobar.
     * @return true si se cumple la condición, false en otro caso.
     */
    private boolean evaluarCondicionDeTexto(int condicion) {
        boolean cumplida = false;
        Escena escena = partida.getEscena();
        switch (condicion) {
            case Utilidades.SI_AGRADADO -> {
                if (escena.hayPnj()) {
                    if (escena.getPnj().getEstadoDeAnimo() == Utilidades.EA_AGRADECIDO) {
                        cumplida = true;
                    }
                }
            }
            case Utilidades.SI_ENFADADO -> {
                if (escena.hayPnj()) {
                    if (escena.getPnj().getEstadoDeAnimo() == Utilidades.EA_ENFADADO) {
                        cumplida = true;
                    }
                }
            }
        }
        return cumplida;
    }

    /**
     * Comprueba si se cumple la condición de un texto para ser mostrado.
     *
     * @param condicion a comprobar.
     * @return true si se cumple la condición, false en otro caso.
     */
    private boolean evaluarCondicionDeOpcion(int condicion) {
        boolean cumplida = false;
        Escena escena = partida.getEscena();
        switch (condicion) {
            case Utilidades.SI_ESTANDAR -> {
                cumplida = true;
            }
            case Utilidades.SI_AGRADADO -> {
                if (escena.hayPnj()) {
                    if (escena.getPnj().getEstadoDeAnimo() == Utilidades.EA_AGRADECIDO) {
                        cumplida = true;
                    }
                }
            }
            case Utilidades.SI_ENFADADO -> {
                if (escena.hayPnj()) {
                    if (escena.getPnj().getEstadoDeAnimo() == Utilidades.EA_ENFADADO) {
                        cumplida = true;
                    }
                }
            }
            case Utilidades.SI_MAPA -> {
                if (buscarEquipo("Mapa")) {
                    System.out.println("Mapa encontrado.");
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
}
