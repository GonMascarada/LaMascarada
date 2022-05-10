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

    /**
     * Genera un objeto con un Nombre, una Definición, si en ese momento está en
     * uso, el plus a Ataque, Defensa y Vida, y Precio que tiene.
     *
     * @param nombre del objeto.
     * @param descripcion del objeto.
     * @param enUso true si se está usando, false en caso contrario.
     * @param atributos Longitud 4: Ataque, Defensa, Vida y Precio.
     */
    public Equipo(String nombre, String descripcion, boolean enUso, int[] atributos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.enUso = enUso;
    }

}
