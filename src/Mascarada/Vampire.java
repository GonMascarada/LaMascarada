package Mascarada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Vampire extends Persona {

    private Clan clan;

    public Vampire() {
    }

    /**
     * Para cargar a un personaje.
     *
     * @param clan
     * @param datos Nombre, Ataque, Defensa, VidaMax, VidaActual, Dinero
     * @param equipacion
     */
    public Vampire(Clan clan, String[] datos, ArrayList<Equipo> equipacion) {
        super(datos, equipacion);
        this.clan = clan;
        clan.setHabilidad(datos[8]);
        clan.setHabilidad(datos[9]);
    }

    /**
     * @return the clan
     */
    public Clan getClan() {
        return clan;
    }

    /**
     * Devuelve una cadena con las dos habilidades separadas por un ";".
     *
     * @return
     */
    public String getHabilidades() {
        String resultado = "";
        HashMap<String, Boolean> habilidades = clan.getHabilidades();
        Iterator<String> it = habilidades.keySet().iterator();
        while (it.hasNext()) {
            String clave = it.next();
            if (habilidades.get(clave) == true) {
                resultado += clave + ";";
            }
        }
        return resultado;
    }
}
