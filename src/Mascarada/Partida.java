package Mascarada;

import java.util.Date;
import java.util.HashMap;

/**
 * Almacena todos los datos relevantes de una partida.
 *
 * @author Gonzalo López Fernández
 */
public class Partida {

    private Vampire protagonista;
    private Escena escena;
    private int idPartida;
    private Date fecha;
    private int tiempo; //Se almacena en minutos
    private int progreso;
    private int sedDeSangre;
    private int sospecha;
    private String ultimaPista;

    public Partida() {
    }

    /**
     * Devuelve la vida actual del protagonista.
     *
     * @return
     */
    public int getVidaProtagonista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Devuelve las habilidades del protagonista por pertenecer a un clan.
     *
     * @return true si tiene la habilidad, false en otro caso.
     */
    public HashMap getHabilidades() {
        return protagonista.getClan().getHabilidades();
    }

    /**
     * @return the escena
     */
    public Escena getEscena() {
        return escena;
    }

    /**
     * @param protagonista the protagonista to set
     */
    public void setProtagonista(Vampire protagonista) {
        this.protagonista = protagonista;
    }

    /**
     * @param escena the escena to set
     */
    public void setEscena(Escena escena) {
        this.escena = escena;
    }

    /**
     * @param idPartida the idPartida to set
     */
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @param progreso the progreso to set
     */
    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    /**
     * @param sedDeSangre the sedDeSangre to set
     */
    public void setSedDeSangre(int sedDeSangre) {
        this.sedDeSangre = sedDeSangre;
    }

    /**
     * @param sospecha the sospecha to set
     */
    public void setSospecha(int sospecha) {
        this.sospecha = sospecha;
    }

    /**
     * @param ultimaPista the ultimaPista to set
     */
    public void setUltimaPista(String ultimaPista) {
        this.ultimaPista = ultimaPista;
    }
}
