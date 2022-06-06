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
    private int estadoDeAnimoOriginal;
    private int estadoDeAnimoActual;
    private boolean cambiado; //Controla si se ha cambiado algo en su ficha.

    public Persona() {
    }

    /**
     * Para cargar un personaje.
     *
     * @param datos 0-Nombre, 1-Ataque, 2-Defensa, 3-VidaMax, 4-VidaActual,
     * 5-Dinero, 6-Estado de ánimo
     * @param equipacion
     */
    public Persona(String[] datos, ArrayList<Equipo> equipacion) {
        this.nombre = datos[0];
        this.ataque = Integer.parseInt(datos[1]);
        this.defensa = Integer.parseInt(datos[2]);
        this.vidaMax = Integer.parseInt(datos[3]);
        this.vidaActual = Integer.parseInt(datos[4]);
        this.dinero = Integer.parseInt(datos[5]);
        this.equipacion = equipacion;
        this.estadoDeAnimoOriginal = Integer.parseInt(datos[6]);
        estadoDeAnimoActual = estadoDeAnimoOriginal;
        cambiado = false;
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
        return estadoDeAnimoActual;
    }

    /**
     * @return the actualizado
     */
    public boolean isCambiado() {
        // Si el estado de ánimo se ha actualizado, hay que actualizar el pnj
        if (estadoDeAnimoActual != estadoDeAnimoOriginal) {
            return true;
        }
        return cambiado;
    }

    /**
     * @param estadoDeAnimo the estadoDeAnimo to set
     */
    public void setEstadoDeAnimo(int estadoDeAnimo) {
        this.estadoDeAnimoActual = estadoDeAnimo;
    }

    /**
     * @param vidaActual the vidaActual to set
     */
    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
        cambiado = true;
    }

    /**
     * @param dinero the dinero to set
     */
    public void setDinero(int dinero) {
        this.dinero = dinero;
        cambiado = true;
    }

    /**
     * @param equipacion the equipacion to set
     */
    public void setEquipacion(ArrayList<Equipo> equipacion) {
        this.equipacion = equipacion;
        cambiado = true;
        actualizar();
    }

    /**
     * Devuelve la información más básica sobre un personaje.
     *
     * @return
     */
    public String getInfoBasica() {
        String resultado;
        resultado = nombre + ";" + ataque + ";" + defensa + ";" + vidaMax + ";";
        resultado += vidaActual + ";" + dinero + ";" + estadoDeAnimoActual + ";";
        return resultado;
    }

    /**
     * Devuelve una lista de todos los equipos del personaje con el formato
     * adecuado para poder ser escritos en equipo-partida-personaje.csv
     *
     * @param idPartida
     * @param usuario
     * @return
     */
    public String getInfoEquipo(int idPartida, String usuario) {
        String resultado = "";
        Equipo e;
        for (int i = 0; i < equipacion.size(); i++) {
            e = equipacion.get(i);
            resultado += "\n" + e.getNombre() + ";" + idPartida + ";";
            resultado += nombre + ";" + e.isEnUso() + ";" + usuario;
        }
        return resultado;
    }

    @Override
    public String toString() {
        String resultado = getInfoBasica();
        resultado += "Humano" + ";" + ";" + ";";
        return resultado;
    }

    /**
     * Quita un objeto al personaje, y lo retorna.
     *
     * @param nombre del objeto
     * @return objeto pedido
     */
    public Equipo delObjeto(String nombre) {
        Equipo e = new Equipo();
        for (int i = 0; i < equipacion.size(); i++) {
            if (equipacion.get(i).getNombre().equals(nombre)) {
                cambiado = true;
                return equipacion.remove(i);
            }
        }
        return e;
    }

    /**
     * Añade un objeto al iventario del personaje.
     *
     * @param equipo
     */
    public void addObjeto(Equipo equipo) {
        equipacion.add(equipo);
        cambiado = true;
    }

    /**
     * Funcion para la actualizacion de stats trabajara conjuntamente con el
     * metodo equipar y desequipar
     */
    private void actualizar() {
        Equipo e;
        for (int i = 0; i < equipacion.size(); i++) {
            e = equipacion.get(i);
            if (e.isEnUso()) {
                if (e.getAtaque() != 0) {
                    ataque = Util.ATQ_VAM + e.getAtaque();
                } else if (e.getDefensa() != 0) {
                    defensa = Util.DEF_VAM + e.getDefensa();
                } else if (e.getVida() != 0) {
                    vidaMax = Util.VIDA_VAM + e.getVida();
                    if (vidaActual > vidaMax) {
                        vidaActual = vidaMax;
                    }
                }
            }
        }
    }

    /**
     * Pone un solo objeto en uso de un tipo.
     *
     * @param nombre
     * @param tipo
     */
    public void equipar(String nombre, int tipo) {
        Equipo e;
        for (int i = 0; i < equipacion.size(); i++) {
            e = equipacion.get(i);
            switch (tipo) {
                case Util.ARMA -> {
                    if (e.getAtaque() != 0) {
                        if (e.getNombre().equals(nombre)) {
                            equipacion.get(i).setEnUso(true);
                        } else {
                            equipacion.get(i).setEnUso(false);
                        }
                    }
                }
                case Util.ARMADURA -> {
                    if (e.getDefensa() != 0) {
                        if (e.getNombre().equals(nombre)) {
                            equipacion.get(i).setEnUso(true);
                        } else {
                            equipacion.get(i).setEnUso(false);
                        }
                    }
                }
                case Util.AMULERTO -> {
                    if (e.getVida() != 0) {
                        if (e.getNombre().equals(nombre)) {
                            equipacion.get(i).setEnUso(true);
                        } else {
                            equipacion.get(i).setEnUso(false);
                        }
                    }
                }
            }
        }
        actualizar();
    }

}
