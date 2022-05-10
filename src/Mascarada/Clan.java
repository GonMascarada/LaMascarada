package Mascarada;

import java.util.HashMap;

public abstract class Clan {

    private String nombre;
    private String descripcion;
    private HashMap<String, Boolean> habilidades = new HashMap<>();

    public Clan(String nombre, String descripcion, HashMap<String, Boolean> habilidades) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilidades = habilidades;
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
     * @return the habilidades
     */
    public HashMap<String, Boolean> getHabilidades() {
        return habilidades;
    }
}
