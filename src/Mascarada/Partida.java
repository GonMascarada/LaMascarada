package Mascarada;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Almacena todos los datos relevantes de una partida.
 *
 * @author Gonzalo López Fernández
 */
public class Partida {

    private Vampire protagonista;
    private Escena escena;
    private int idPartida;
    private String fecha;
    private int tiempo; //Se almacena en minutos
    private int progreso;
    private int sedDeSangre;
    private int sospecha;
    private String ultimaPista;
    private ArrayList<Persona> personajes;

    public Partida() {
    }

    /**
     * Elimita todos los npc´s que no han sufrido cambios en sus atributos.
     */
    public void borrarNpcsInalterados() {
        for (int i = personajes.size() - 1; i >= 0; i--) {
            if (!personajes.get(i).isCambiado()) {
                personajes.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        String resultado;
        resultado = idPartida + ";" + fecha + ";" + tiempo + ";" + progreso + ";";
        resultado += sedDeSangre + ";" + sospecha + ";" + ultimaPista + ";";
        resultado += protagonista.getHabilidades() + ";" + escena.getIdEscena();
        resultado += ";" + protagonista.getNombre();
        return resultado;
    }

    /**
     * Devuelve las habilidades del protagonista por pertenecer a un clan.
     *
     * @return true si tiene la habilidad, false en otro caso.
     */
    public HashMap getHabilidades() {
        return getProtagonista().getClan().getHabilidades();
    }

    /**
     * @return the protagonista
     */
    public Vampire getProtagonista() {
        return protagonista;
    }

    /**
     * @param protagonista the protagonista to set
     */
    public void setProtagonista(Vampire protagonista) {
        this.protagonista = protagonista;
    }

    /**
     * @return the escena
     */
    public Escena getEscena() {
        return escena;
    }

    /**
     * @param escena the escena to set
     */
    public void setEscena(Escena escena) {
        this.escena = escena;
    }

    /**
     * @return the idPartida
     */
    public int getIdPartida() {
        return idPartida;
    }

    /**
     * @param idPartida the idPartida to set
     */
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @return the progreso
     */
    public int getProgreso() {
        return progreso;
    }

    /**
     * @param progreso the progreso to set
     */
    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    /**
     * @return the sedDeSangre
     */
    public int getSedDeSangre() {
        return sedDeSangre;
    }

    /**
     * @param sedDeSangre the sedDeSangre to set
     */
    public void setSedDeSangre(int sedDeSangre) {
        this.sedDeSangre = sedDeSangre;
    }

    /**
     * @return the sospecha
     */
    public int getSospecha() {
        return sospecha;
    }

    /**
     * @param sospecha the sospecha to set
     */
    public void setSospecha(int sospecha) {
        this.sospecha = sospecha;
    }

    /**
     * @return the ultimaPista
     */
    public String getUltimaPista() {
        return ultimaPista;
    }

    /**
     * @param ultimaPista the ultimaPista to set
     */
    public void setUltimaPista(String ultimaPista) {
        this.ultimaPista = ultimaPista;
    }

    /**
     * @return the personajes
     */
    public ArrayList<Persona> getPersonajes() {
        return personajes;
    }

    /**
     * @param personajes the personajes to set
     */
    public void setPersonajes(ArrayList<Persona> personajes) {
        this.personajes = personajes;
    }

    /**
     * Devuelve la información sobre una partida con el formato correcto para
     * ser almacenado en partida.csv
     *
     * @return
     */
    public String getInfoPartida() {
        String resultado;
        resultado = idPartida + ";" + fecha + ";" + tiempo + ";" + progreso;
        resultado += ";" + sedDeSangre + ";" + sospecha + ";" + ultimaPista + ";";
        resultado += escena.getIdEscena();
        return resultado;
    }

    /**
     * Devuelve la información de los npcs con el formato adecuado.
     *
     * @return
     */
    public String[] getInfoPersonajes() {
        String[] pjs = new String[this.personajes.size() + 1];
        //El primero será el protagonista
        pjs[0] = protagonista.toString() + idPartida;
        //Tras él, el resto de pnj´s
        for (int i = 0; i < personajes.size(); i++) {
            if (personajes.get(i) instanceof Persona) {
                pjs[i + 1] = personajes.get(i).toString() + idPartida;
            } else {
                Vampire v = (Vampire) personajes.get(i);
                pjs[i + 1] = v.toString() + idPartida;
            }
        }
        return pjs;
    }

    /**
     * Devuelve una lista en la que cada registro contiene la información de
     * todo el equipo de un personaje, con el formato adecuado para ser escrito
     * en equipo-partida-personaje.csv
     *
     * @return
     */
    public ArrayList<String> getInfoObjetos() {
        ArrayList<String> resultado = new ArrayList<>();
        resultado.add(protagonista.getInfoEquipo(idPartida));
        for (int i = 0; i < personajes.size(); i++) {
            resultado.add(personajes.get(i).getInfoEquipo(idPartida));
        }
        return resultado;
    }

    /**
     * Devuelve una lista con los datos de la partida.
     *
     * @return
     */
    public java.util.List getDatos() {
        java.util.List<String> lista = new ArrayList<>();
        lista.add(protagonista.getNombre());
        lista.add(protagonista.getClan().getNombre());
        String[] hab = protagonista.getHabilidades().split(";");
        lista.add(hab[0]);
        lista.add(hab[1]);
        lista.add(String.valueOf(tiempo));
        lista.add(String.valueOf(fecha));
        return lista;
    }
}
