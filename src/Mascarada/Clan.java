package Mascarada;

import java.util.HashMap;
import java.util.Iterator;

public class Clan {

    private String nombre;
    private String descripcion;
    private String imagen;
    private HashMap<String, Boolean> habilidades = new HashMap<>();

    public Clan() {
    }

    public Clan(String[] datos) {
        nombre = datos[0];
        descripcion = datos[1];
        imagen = datos[2];
        habilidades.put(datos[3], false);
        habilidades.put(datos[4], false);
        habilidades.put(datos[5], false);
        habilidades.put(datos[6], false);
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
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @return the habilidades
     */
    public HashMap<String, Boolean> getHabilidades() {
        return habilidades;
    }

    @Override
    public String toString() {
        String resultado = nombre + ";" + descripcion + ";";
        resultado += imagen + ";";
        Iterator<String> it = habilidades.keySet().iterator();
        while (it.hasNext()) {
            resultado += it.next() + ";";
        }
        return resultado;
    }
}
