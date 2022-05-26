package Controlador;

import Modelo.BaseDeDatos;
import Mascarada.Partida;
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
     */
    public static void main(String[] args) throws IOException, ParseException {
        Controlador controlador = new Controlador();
        BaseDeDatos bd = new BaseDeDatos();
/*
        System.out.println("getListaClanes");
        ArrayList<Clan> clanes = bd.getListaClanes();
        for (int i = 0; i < clanes.size(); i++) {
            System.out.println(clanes.get(i).toString());
        }

        System.out.println("\n\ngetListaPartidas");
// */       ArrayList<Partida> partidas = bd.getListaPartidas();
 /*       for (int i = 0; i < partidas.size(); i++) {
            System.out.println(partidas.get(i).toString());
        }
*/        
          
        bd.comprobarConsistencia();
 
        Partida p = partidas.get(0);     
        controlador.cargarPartida(p);
        System.out.print("Objetos pnj "+ controlador.getPartida().getEscena().getPnj().getNombre()+": ");
        //System.out.print(controlador.getPartida().getEscena().getPnj().getInfoEquipo(1));
        
         Tienda tienda = new Tienda(controlador);
        tienda.setVisible(true);
        
        //String prueba = "0;Ir al bar;0;0;20;0;0;1;";
        //String[] datos = prueba.split(";");
        //Opcion opcion = new Opcion(datos);
        //controlador.escoger(opcion);

    }
}
