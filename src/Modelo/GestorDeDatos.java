package Modelo;

import Mascarada.Clan;
import Mascarada.Equipo;
import Mascarada.Escena;
import Mascarada.Partida;
import Mascarada.Util;
import Mascarada.Vampire;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestiona el amacenamiento permanente de los datos.
 *
 * @author Gonzalo López Fernández
 */
public class GestorDeDatos {

    private BaseDeDatos bd;

    public GestorDeDatos() {
        try {
            Fichero.comprobarFicherosDeGuardado();
            bd = new BaseDeDatos();
        } catch (IOException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Comprueba en la base de datos si el usuario y la contraseña son
     * correctos.
     *
     * @param text
     * @param password
     * @return
     */
    public boolean comprobarCredenciales(String text, String password) {
        try {
            return bd.comprobarCredenciales(text, password);
        } catch (SQLException | IOException | ParseException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Comprueba si un nombre ya está siendo usado por otro personaje.
     *
     * @param nombre
     * @param usuario
     * @return true si está disponible, false en otro caso.
     */
    public boolean comprobarNombrePersonaje(String nombre, String usuario) {
        try {
            return Fichero.comprobarNombrePersonaje(nombre, usuario);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Comprueba si un nombre de usuario está disponible.
     *
     * @param usuario
     * @return true si está disponible, false en otro caso.
     */
    public boolean comprobarNombreUsuario(String usuario) {
        try {
            return bd.comprobarNombreUsuario(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Reintenta la conexión con la base de datos.
     *
     * @param url
     * @param use
     * @param pas
     * @return
     */
    public boolean conectar(String url, String use, String pas) {
        return bd.conectar(url, use, pas);
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario
     * @param pass
     */
    public void crearNuevoUsuario(String usuario, String pass) {
        try {
            bd.crearNuevoUsuario(usuario, pass);
        } catch (SQLException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Elimina todos los datos de una partida.
     *
     * @param usuario
     * @param idPartida
     * @throws java.sql.SQLException
     */
    public void eliminarPartida(String usuario, int idPartida) throws SQLException {
        try {
            Fichero.eliminarPartida(usuario, idPartida);            
            bd.sincronizar(usuario);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve la configuración con la base de datos almacenada en el fichero
     * bd.csv
     *
     * @return Texto con la url, usuario y contraseña.
     */
    public String getConfiguracionBD() {
        return bd.getConfiguracionBD();
    }

    /**
     * Devuelve un objeto concreto dado su nombre.
     *
     * @param nombre
     * @return
     */
    public Equipo getEquipo(String nombre) {
        try {
            return Fichero.getEquipo(nombre);
        } catch (IOException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Devuleve una escena concreta.
     *
     * @param idEscena identificador de la escena.
     * @param idPartida
     * @param usuario
     * @return escena requerida.
     */
    public Escena getEscena(int idEscena, int idPartida, String usuario) {
        try {
            return Fichero.getEscena(idEscena, idPartida, usuario);
        } catch (IOException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Devuelve la información sobre las dos habilidades.
     *
     * @param habilidades
     * @return
     */
    public String getInfoHabilidades(String[] habilidades) {
        String resultado = "";
        try {
            ArrayList<String> texto = Fichero.leerJar(Util.JAR_HABILIDAD, true);
            String[] linea;
            for (int i = 0; i < texto.size(); i++) {
                linea = texto.get(i).split(";");
                if (linea[0].equals(habilidades[0])) {
                    resultado += "<html>"+linea[0] + " - " + linea[1] + "</br>";
                } else if (linea[0].equals(habilidades[1])) {
                    resultado += "</br>"+linea[0] + " - " + linea[1] + "</html>";
                }
            }
            Fichero.getListaClanes();
        } catch (IOException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    /**
     * Devuelve la lista de clanes disponible para jugar.
     *
     * @return lista de clanes.
     */
    public ArrayList<Clan> getListaClanes() {
        try {
            return Fichero.getListaClanes();
        } catch (IOException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Devuelve lista de todas las partidas guardadas en la base de datos de los
     * protagonistas.
     *
     * @param usuario
     * @return lista de partidas guardadas.
     */
    public ArrayList<Partida> getListaPartidas(String usuario) {
        try {
            return Fichero.getListaPartidas(usuario);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Devuelve todos los textos posibles para una escena en concreto.
     *
     * @param idEscena de la escena.
     * @return una lista de parejas [condición, texto]
     */
    public ArrayList<String> getTextos(int idEscena) {
        try {
            return Fichero.getTextos(idEscena);
        } catch (IOException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Guarda el estado actual de la partida.
     *
     * @param partida a guardar.
     * @param nuevaPartida true si es una nueva partida, false en otro caso.
     */
    public void guardarPartida(Partida partida, boolean nuevaPartida) throws SQLException {
        String usuario = partida.getUsuario();
        try {
            Fichero.guardarPartida(partida);
            if (bd.isConectado()) {
                if (nuevaPartida) {
                    try {
                        bd.insertarPartida(partida);
                    } catch (SQLException ex) {
                        Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    bd.sincronizar(usuario);
                }
            }

        } catch (IOException | ParseException ex) {
            Logger.getLogger(GestorDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve una Partida con todos los datos iniciales para poder jugar.Y
     * almacena los datos de la nueva partida.
     *
     * @param vampire protagonista
     * @param usuario
     * @return Partida.
     */
    public Partida iniciarNuevaPartida(Vampire vampire, String usuario) {
        try {
            return Fichero.iniciarNuevaPartida(vampire, usuario);

        } catch (IOException ex) {
            Logger.getLogger(GestorDeDatos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return true si se ha conectado, false en otro caso.
     */
    public boolean isConectado() {
        return bd.isConectado();
    }

}
