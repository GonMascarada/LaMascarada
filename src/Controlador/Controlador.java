package Controlador;

import Modelo.BaseDeDatos;
import Mascarada.Clan;
import Mascarada.Escena;
import Mascarada.Opcion;
import Mascarada.Partida;
import Mascarada.Persona;
import Mascarada.Vampire;
import Vista.Eleccion_2;
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

    public Controlador() {
        bbdd = new BaseDeDatos();
        partida = new Partida(); //Habrá que borrarlo
    }

    /**
     * Devuelve la lista de todos los clanes disponibles.
     *
     * @return lista de todos los clanes de vampiro jugables.
     */
    public ArrayList<Clan> getListaClanes() {
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
    public ArrayList<Partida> getListaPartidas() {
        return bbdd.getListaPartidas();
    }

    /**
     * Comprueba que el nombre no le pertenezca ya a otro vampiro.
     *
     * @param nombre a comprobar.
     * @return true si está disponible, false en otro caso.
     */
    public boolean comprobarNombrePersonaje(String nombre) {
        return bbdd.comprobarNombrePersonaje(nombre);
    }

    /**
     * Inicia una nueva partida.
     *
     * @param clan del nuevo personaje.
     * @param nombre del nuevo personaje.
     */
    public void crearNuevaPartida(Clan clan, String nombre) {
        Vampire vampire = new Vampire(clan, nombre);
        getPartida().setProtagonista(vampire);
        Escena primera = bbdd.getEscena(0); //Primera escena
        getPartida().setEscena(primera);
        // Lanzar una nueva ventana con la escena.
    }

    /**
     * Lanza la nueva escena de una opción.
     *
     * @param opcion marca cuál será la nueva escena.
     */
    public void escoger(Opcion opcion) {
        Escena siguiente = bbdd.getEscena(opcion.getIdEscenaSiguiente());
        partida.setEscena(siguiente);
        //1.Evaluar la opción que se acaba de tomar. (Si cambia el estado de animo de alguien, si causa progreso, si aumenta la sed de sangre, la sospecha...)
        //2.Pedir a la base de datos todas las posibles condiciones.
        //3.Comprobar si se cumple alguna de las condiciones.
        //IMPORTANTE, SOLO SE PUEDE CUMPLIR UNA ÚNICA CONDICION.
        //4.Insertar a la escena el texto
        //5.Eliminar de la escena siguiente las opciones que no estén disponibles.
        //volver a añadir a opción condición
       //         y cambiarlo a int

       // lanzar(siguiente);
        //6.Obtener info extra y mostrarla si hay.
//        HashMap habilidades = partida.getHabilidades();
//        String extra = bbdd.getInfoExtra(siguiente.getIdEscena(), habilidades);
        // Si en extra hay algo, lanzar pop-up con esa info.
    }

    /**
     * Guarda la partida en el estado actual.
     */
    public void guardarPartida() {
        bbdd.guardarPartida(getPartida());
    }

    /**
     * Carga una partida.
     *
     * @param partida
     */
    public void cargarPartida(Partida partida) {
        this.partida = partida;
        Escena escena = partida.getEscena();
        lanzar(escena);
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
     * Resuelve un combate entre el protagonista y un enemigo.
     *
     * @param enemigo
     * @return true si se vence, false en otro caso.
     */
    private boolean pelear(Persona enemigo) {
        //Pasan cosas.
        if (getPartida().getVidaProtagonista() <= 0) {
            bbdd.getEscenaMuerte();
            return false;
        }
        return true;
    }

    /**
     * Muestra la interfaz acorde a la escena.
     *
     * @param escena a mostrar.
     */
    private void lanzar(Escena escena) {
        Eleccion_2 ventana = new Eleccion_2(this, escena);
        ventana.setVisible(true);
    }

    /**
     * @return the partida
     */
    public Partida getPartida() {
        return partida;
    }
}
