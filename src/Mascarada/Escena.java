package Mascarada;


public class Escena {
    private int idEscena;
    private String texto;
    private String imagen;
    private Opcion[] opciones;

    public Escena(int idEscena, String texto, String imagen, Opcion[] opciones) {
        this.idEscena = idEscena;
        this.texto = texto;
        this.imagen = imagen;
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
    public Opcion[] getOpciones() {
        return opciones;
    }
    
    
}
