package Mascarada;

import java.util.ArrayList;

public class Escena {

    private int idEscena;
    private String texto;
    private String imagen;
    private ArrayList<Opcion> opciones;
    private Persona pnj;

    public Escena() {
    }

    public Escena(String[] datos, ArrayList<Opcion> opciones) {
        this.idEscena = Integer.valueOf(datos[0]);
        this.imagen = datos[1];
        this.opciones = opciones;
    }

    public Escena(String[] datos, ArrayList<Opcion> opciones, Persona pnj) {
        this.idEscena = Integer.valueOf(datos[0]);
        this.imagen = datos[1];
        this.opciones = opciones;
        this.pnj = pnj;
    }

    /**
     * @return the idEscena
     */
    public int getIdEscena() {
        return idEscena;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @return the opciones
     */
    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    /**
     * @return the pnj
     */
    public Persona getPnj() {
        return pnj;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }

    /**
     * Comprueba que la escena tenga pnj.
     *
     * @return true si hay pnj, false en otro caso.
     */
    public boolean hayPnj() {
        try {
            pnj.getNombre();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param pnj the pnj to set
     */
    public void setPnj(Persona pnj) {
        this.pnj = pnj;
    }
}
