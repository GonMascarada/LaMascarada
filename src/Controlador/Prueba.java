package Controlador;

import Modelo.GestorDeDatos;
import Mascarada.Partida;
import Vista.Inicio;
import Vista.Tienda;
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

        ArrayList<Partida> partidas = bd.getListaPartidas();
        bd.comprobarConsistencia();

        Partida p = partidas.get(0);
        controlador.cargarPartida(p);

        Tienda tienda = new Tienda(controlador);
        tienda.setVisible(true);

        Inicio inicio = new Inicio();
        inicio.setVisible(true);
    }
}
