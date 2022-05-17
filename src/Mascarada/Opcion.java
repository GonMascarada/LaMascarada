package Mascarada;

public class Opcion {

    private int idOpcion;
    private String texto;
    private int accion;
    private String condiccion;
    private int tiempo;
    private String clan;
    private int idEscena;
    private int idEscenaSiguiente;

    /**
     * Crea una opcion con los datos proporcionados ordenadamente en una lista.
     *
     * @param datos Por orden: idOpcion, texto, accion, condiccion, tiempo,
     * clan, idEscena, idEscenaSiguiente.
     */
    public Opcion(String[] datos) {
        this.idOpcion = Integer.parseInt(datos[0]);
        this.texto = datos[1];
        this.accion = Integer.parseInt(datos[2]);
        this.condiccion = datos[3];
        this.tiempo = Integer.parseInt(datos[4]);
        this.clan = datos[5];
        this.idEscena = Integer.parseInt(datos[6]);
        this.idEscenaSiguiente = Integer.parseInt(datos[7]);
    }

    /**
     * Comprueba si esta opción causa un progreso en la historia del
     * protagonista.
     *
     * @return true si causa progreso, false en otro caso.
     */
    public boolean causaProgreso() {
        return false;
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
