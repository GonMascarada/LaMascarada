package Modelo;

import Mascarada.Clan;
import Mascarada.Escena;
import Mascarada.Partida;
import Mascarada.Vampire;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Gestiona el amacenamiento permanente de los datos.
 *
 * @author Gonzalo López Fernández
 */
public class GestorDeDatos {

    private Fichero fichero;
    private BaseDeDatos bd;
    private boolean conectado;

    public GestorDeDatos() throws IOException {
        fichero = new Fichero();
        bd = new BaseDeDatos();
    }

    /**
     * Comprueba si un nombre ya está siendo usado por otro personaje.
     *
     * @param nombre
     * @return true si está disponible, false en otro caso.
     * @throws java.io.FileNotFoundException
     */
    public boolean comprobarNombrePersonaje(String nombre) throws FileNotFoundException {
        return fichero.comprobarNombrePersonaje(nombre);
    }

    /**
     * Elimina todos los datos de una partida.
     *
     * @param idPartida
     * @throws java.io.IOException
     */
    public void eliminarPartida(int idPartida) throws IOException {
        fichero.eliminarPartida(idPartida);
        bd.sincronizar();
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
        return fichero.getEscena(idEscena, idPartida);
    }

    /**
     * Devuelve la lista de clanes disponible para jugar.
     *
     * @return lista de clanes.
     * @throws java.io.IOException
     */
    public ArrayList<Clan> getListaClanes() throws IOException {
        return fichero.getListaClanes();
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
        return fichero.getListaPartidas();
    }

    /**
     * Devuelve todos los textos posibles para una escena en concreto.
     *
     * @param idEscena de la escena.
     * @return una lista de parejas [condición, texto]
     * @throws java.io.IOException
     */
    public ArrayList<String[]> getTextos(int idEscena) throws IOException {
        return fichero.getTextos(idEscena);
    }

    /**
     * Guarda el estado actual de la partida.
     *
     * @param partida a guardar.
     * @throws java.io.FileNotFoundException
     */
    public void guardarPartida(Partida partida) throws IOException {
        fichero.guardarPartida(partida);
        bd.sincronizar();
    }

    /**
     * Devuelve una Partida con todos los datos iniciales para poder jugar.Y
     * almacena los datos de la nueva partida.
     *
     * @param vampire protagonista
     * @return Partida.
     * @throws java.io.IOException
     */
    public Partida iniciarNuevaPartida(Vampire vampire) throws IOException {
        return fichero.iniciarNuevaPartida(vampire);
    }

}
