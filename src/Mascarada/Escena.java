package Mascarada;

import java.util.ArrayList;


public class Escena {
    private int idEscena;
    private String texto;
    private String imagen;
    private ArrayList<Opcion> opciones;

    public Escena() {
    }
    
    public Escena(String[] datos, ArrayList<Opcion> opciones) {
        this.idEscena = Integer.valueOf(datos[0]);
        this.texto = datos[1];
        this.imagen = datos[2];
        this.opciones = opciones;
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
    
    
}
