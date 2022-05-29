package Controlador;

import Modelo.*;
import Mascarada.Partida;

import Vista.VistaTienda;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Main para hacer pruebas
 *
 * @author Gonzalo López Fernández
 */
public class Prueba {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
        Controlador controlador = new Controlador();
        GestorDeDatos bd = new GestorDeDatos();

        
        Fichero.comprobarConsistencia();

        ArrayList<Partida> partidas = bd.getListaPartidas("Admin");
        Partida p = partidas.get(0);
        controlador.cargarPartida(p);

        VistaTienda tienda = new VistaTienda(controlador);
        tienda.setVisible(true);

    }
}
