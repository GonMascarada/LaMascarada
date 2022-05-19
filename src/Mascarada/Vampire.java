package Mascarada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Vampire extends Persona {

    private Clan clan;

    public Vampire() {
    }

    /**
     * Para generar un nuevo protagonista.
     *
     * @param clan
     * @param Nombre
     */
    public Vampire(Clan clan, String Nombre) {
        super(Nombre, Utilidades.ATQ_VAM, Utilidades.DEF_VAM, Utilidades.VIDA_VAM);
        this.clan = clan;
    }

    /**
     * Para generar un NPC básico.
     *
     * @param clan
     * @param estadoDeAnimo
     * @param Nombre
     * @param Ataque
     * @param Defensa
     * @param VidaMax
     */
    public Vampire(Clan clan, int estadoDeAnimo, String Nombre, int Ataque, int Defensa, int VidaMax) {
        super(Nombre, Ataque, Defensa, VidaMax);
        this.clan = clan;
    }

    /**
     * Para cargar a un protagonista.
     *
     * @param clan
     * @param datos Nombre, Ataque, Defensa, VidaMax, VidaActual, Dinero
     * @param equipacion
     */
    public Vampire(Clan clan, String[] datos, ArrayList<Equipo> equipacion) {
        super(datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), equipacion);
        this.clan = clan;
    }

    /**
     * Para cargar un NPC.
     *
     * @param clan
     * @param nombre
     * @param ataque
     * @param defensa
     * @param vidaMax
     * @param vidaActual
     * @param dinero
     * @param equipacion
     * @param estadoDeAnimo
     */
    public Vampire(Clan clan, String nombre, int ataque, int defensa, int vidaMax, int vidaActual, int dinero, ArrayList<Equipo> equipacion, int estadoDeAnimo) {
        super(nombre, ataque, defensa, vidaMax, vidaActual, dinero, equipacion, estadoDeAnimo);
        this.clan = clan;
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
