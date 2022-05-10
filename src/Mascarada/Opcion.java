package Mascarada;

public class Opcion {

    private int idOpcion;
    private String texto;
    private int accion;
    private String clan;
    private int tiempo;
    private String condiccion;
    private int idEscena;
    private int idEscenaSiguiente;

    public Opcion(int idOpcion, String texto, int accion, String clan, int tiempo, String condiccion, int idEscena, int idEscenaSiguiente) {
        this.idOpcion = idOpcion;
        this.texto = texto;
        this.accion = accion;
        this.clan = clan;
        this.tiempo = tiempo;
        this.condiccion = condiccion;
        this.idEscena = idEscena;
        this.idEscenaSiguiente = idEscenaSiguiente;
    }

    /**
     * Comprueba si esta opción causa un progreso en la historia del
     * protagonista.
     *
     * @return true si causa progreso, false en otro caso.
     */
    public boolean causaProgreso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the idOpcion
     */
    public int getIdOpcion() {
        return idOpcion;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @return the accion
     */
    public int getAccion() {
        return accion;
    }

    /**
     * @return the clan
     */
    public String getClan() {
        return clan;
    }

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @return the condiccion
     */
    public String getCondiccion() {
        return condiccion;
    }

    /**
     * @return the idEscena
     */
    public int getIdEscena() {
        return idEscena;
    }

    /**
     * @return the idEscenaSiguiente
     */
    public int getIdEscenaSiguiente() {
        return idEscenaSiguiente;
    }

}
