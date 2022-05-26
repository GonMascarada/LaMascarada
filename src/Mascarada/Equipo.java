package Mascarada;

/**
 *
 * @author Gonzalo López Fernández
 */
public class Equipo {

    private String nombre;
    private String descripcion;
    private boolean enUso;
    private int ataque;
    private int defensa;
    private int vida;
    private int precio;

    public Equipo() {
        nombre = "";
    }

    /**
     * Crea un equipo dado un array con datos ordenados.
     *
     * @param datos Por orden Nombre, Descrición, Ataque, Defensa, Vida y
     * Precio.
     */
    public Equipo(String[] datos) {
        this.nombre = datos[0];
        this.descripcion = datos[1];
        this.ataque = Integer.parseInt(datos[2]);
        this.defensa = Integer.parseInt(datos[3]);
        this.vida = Integer.parseInt(datos[4]);
        this.precio = Integer.parseInt(datos[5]);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the enUso
     */
    public boolean isEnUso() {
        return enUso;
    }

    /**
     * @param enUso the enUso to set
     */
    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    /**
     * @return the ataque
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * @return the defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * @return the vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

}
