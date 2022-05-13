package Vista;

import Mascarada.BaseDeDatos;
import Mascarada.Clan;
import Mascarada.Controlador;
import Mascarada.Partida;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main para hacer pruebas
 *
 * @author Gonzalo López Fernández
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Controlador controlador = new Controlador();
        BaseDeDatos bd = new BaseDeDatos();

        System.out.println("getListaClanes");
        ArrayList<Clan> clanes = bd.getListaClanes();
        for (int i = 0; i < clanes.size(); i++) {
            System.out.println(clanes.get(i).toString());
        }
        
        System.out.println("\n\ngetListaPartidas");
        ArrayList<Partida> partidas = bd.getListaPartidas();
        for (int i = 0; i < partidas.size(); i++) {
            System.out.println(partidas.get(i).toString());
        }  

    }
}
