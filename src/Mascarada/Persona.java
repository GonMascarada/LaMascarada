package Mascarada;

import java.util.ArrayList;

public class Persona {

    private String nombre;
    private int ataque;
    private int defensa;
    private int vidaMax;
    private int vidaActual;
    private int dinero;
    private ArrayList<Equipo> equipacion;
    private int estadoDeAnimo;

    public Persona() {
    }
    
    /**
     * Para generar un humano básico.
     *
     * @param Nombre
     */
    public Persona(String Nombre) {
        this.nombre = Nombre;
        this.ataque = Utilidades.ATQ;
        this.defensa = Utilidades.DEF;
        this.vidaMax = Utilidades.VIDA;
        this.vidaActual = vidaMax;
        this.dinero = Utilidades.DINERO;
        this.estadoDeAnimo = Utilidades.NORMAL;
    }

    /**
     * Para generar una persona nueva específica.
     *
     * @param Nombre
     * @param Ataque
     * @param Defensa
     * @param VidaMax
     */
    public Persona(String Nombre, int Ataque, int Defensa, int VidaMax) {
        this.nombre = Nombre;
        this.ataque = Ataque;
        this.defensa = Defensa;
        this.vidaMax = VidaMax;
        this.vidaActual = VidaMax;
        this.dinero = Utilidades.DINERO;
        this.estadoDeAnimo = Utilidades.NORMAL;
    }

    /**
     * Para cargar al protagonista.
     *
     * @param Nombre
     * @param Ataque
     * @param Defensa
     * @param VidaMax
     * @param VidaActual
     * @param dinero
     * @param equipacion
     */
    public Persona(String Nombre, int Ataque, int Defensa, int VidaMax, int VidaActual, int dinero, ArrayList<Equipo> equipacion) {
        this.nombre = Nombre;
        this.ataque = Ataque;
        this.defensa = Defensa;
        this.vidaMax = VidaMax;
        this.vidaActual = VidaActual;
        this.dinero = dinero;
        this.equipacion = equipacion;
    }

    /**
     * Para cargar una persona.
     *
     * @param Nombre
     * @param Ataque
     * @param Defensa
     * @param VidaMax
     * @param VidaActual
     * @param dinero
     * @param equipacion
     * @param estadoDeAnimo
     */
    public Persona(String Nombre, int Ataque, int Defensa, int VidaMax, int VidaActual, int dinero, ArrayList<Equipo> equipacion, int estadoDeAnimo) {
        this.nombre = Nombre;
        this.ataque = Ataque;
        this.defensa = Defensa;
        this.vidaMax = VidaMax;
        this.vidaActual = VidaActual;
        this.dinero = dinero;
        this.equipacion = equipacion;
        this.estadoDeAnimo = estadoDeAnimo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
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
     * @return the vidaMax
     */
    public int getVidaMax() {
        return vidaMax;
    }

    /**
     * @return the vidaActual
     */
    public int getVidaActual() {
        return vidaActual;
    }

    /**
     * @return the dinero
     */
    public int getDinero() {
        return dinero;
    }

    /**
     * @return the equipacion
     */
    public ArrayList<Equipo> getEquipacion() {
        return equipacion;
    }

    /**
     * @return the estadoDeAnimo
     */
    public int getEstadoDeAnimo() {
        return estadoDeAnimo;
    }
    
    
    
}
