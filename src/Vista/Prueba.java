package Vista;

import Mascarada.BaseDeDatos;
import Mascarada.Clan;
import Mascarada.Controlador;
import java.util.ArrayList;

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
    }
}
