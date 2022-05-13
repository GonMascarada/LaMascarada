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
    //Falta un arraylist de todas las personas a las que ha alterado. 

    public Partida() {
    }

    @Override
    public String toString() {
        String resultado;
        resultado = idPartida + ";" + fecha + ";" + tiempo + ";" + progreso + ";";
        resultado += sedDeSangre + ";" + sospecha + ";" + ultimaPista + ";";
        resultado += protagonista.getHabilidades() + ";" + escena.getIdEscena();
        resultado += ";" + protagonista.getNombre();
        return resultado;
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
        return getProtagonista().getClan().getHabilidades();
    }

    /**
     * @return the protagonista
     */
    public Vampire getProtagonista() {
        return protagonista;
    }

    /**
     * @param protagonista the protagonista to set
     */
    public void setProtagonista(Vampire protagonista) {
        this.protagonista = protagonista;
    }

    /**
     * @return the escena
     */
    public Escena getEscena() {
        return escena;
    }

    /**
     * @param escena the escena to set
     */
    public void setEscena(Escena escena) {
        this.escena = escena;
    }

    /**
     * @return the idPartida
     */
    public int getIdPartida() {
        return idPartida;
    }

    /**
     * @param idPartida the idPartida to set
     */
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @return the progreso
     */
    public int getProgreso() {
        return progreso;
    }

    /**
     * @param progreso the progreso to set
     */
    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    /**
     * @return the sedDeSangre
     */
    public int getSedDeSangre() {
        return sedDeSangre;
    }

    /**
     * @param sedDeSangre the sedDeSangre to set
     */
    public void setSedDeSangre(int sedDeSangre) {
        this.sedDeSangre = sedDeSangre;
    }

    /**
     * @return the sospecha
     */
    public int getSospecha() {
        return sospecha;
    }

    /**
     * @param sospecha the sospecha to set
     */
    public void setSospecha(int sospecha) {
        this.sospecha = sospecha;
    }

    /**
     * @return the ultimaPista
     */
    public String getUltimaPista() {
        return ultimaPista;
    }

    /**
     * @param ultimaPista the ultimaPista to set
     */
    public void setUltimaPista(String ultimaPista) {
        this.ultimaPista = ultimaPista;
    }

}
