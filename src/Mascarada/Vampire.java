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

    @Override
    public String toString() {
        String resultado = super.getInfoBasica();
        resultado += clan.getNombre() + ";" + clan.getHabilidadesObtenidas();
        return resultado;
    }

    /**
     * Comprueba si se tiene la habilidad de Celeridad.
     *
     * @return true si la tiene, false en otro caso.
     */
    public boolean tieneCeleridad() {
        if (clan.getHabilidades().containsKey("Celeridad")) {
            if (clan.getHabilidades().get("Celeridad")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si se tiene la habilidad de Auspex.
     *
     * @return true si la tiene, false en otro caso.
     */
    public boolean tieneAuspex() {
        if (clan.getHabilidades().containsKey("Animalismo")) {
            if (clan.getHabilidades().get("Animalismo")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si se tiene la habilidad de Animalismo.
     *
     * @return true si la tiene, false en otro caso.
     */
    public boolean tieneAnimalismo() {
        if (clan.getHabilidades().containsKey("Auspex")) {
            if (clan.getHabilidades().get("Auspex")) {
                return true;
            }
        }
        return false;
    }
}
