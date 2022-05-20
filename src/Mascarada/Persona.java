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
     * Para cargar un personaje.
     *
     * @param datos 0-Nombre, 1-Ataque, 2-Defensa, 3-VidaMax, 4-VidaActual,
     * 5-Dinero, 6-Estado de ánimo
     * @param equipacion
     * @param estadoDeAnimo
     */
    public Persona(String[] datos, ArrayList<Equipo> equipacion) {
        this.nombre = datos[0];
        this.ataque = Integer.parseInt(datos[1]);
        this.defensa = Integer.parseInt(datos[2]);
        this.vidaMax = Integer.parseInt(datos[3]);
        this.vidaActual = Integer.parseInt(datos[4]);
        this.dinero = Integer.parseInt(datos[5]);
        this.equipacion = equipacion;
        this.estadoDeAnimo = Integer.parseInt(datos[6]);
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
