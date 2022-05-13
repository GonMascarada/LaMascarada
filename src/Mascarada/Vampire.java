package Mascarada;

import java.util.ArrayList;

public class Vampire extends Persona {

    private Clan clan;


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
     * @param Nombre
     * @param Ataque
     * @param Defensa
     * @param VidaMax
     * @param VidaActual
     * @param dinero
     * @param equipacion 
     * @param estadoDeAnimo 
     */
    public Vampire(Clan clan, String Nombre, int Ataque, int Defensa, int VidaMax, int VidaActual, int dinero, Equipo[] equipacion, int estadoDeAnimo) {
        super(Nombre, Ataque, Defensa, VidaMax, VidaActual, dinero, equipacion, estadoDeAnimo);
        this.clan = clan;
    }

    /**
     * @return the clan
     */
    public Clan getClan() {
        return clan;
    }
    
}
