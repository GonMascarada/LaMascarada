package Mascarada;

import java.util.HashMap;

/**
 * Gestiona el amacenamiento permanente de los datos.
 *
 * @author Gonzalo López Fernández
 */
public class BaseDeDatos {

    public BaseDeDatos() {
        sincronizar();
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
     * @param idEscenaSiguiente identificador de la escena.
     * @return escena requerida.
     */
    public Escena getEscena(int idEscenaSiguiente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Partida[] getListaPartidas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private void sincronizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
