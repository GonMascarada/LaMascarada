package Mascarada;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Compendio de constantes comunes para todo el programa. Contiene los dátos
 * estandar.
 *
 * @author Gonzalo López Fernández
 */
public final class Util {

    // Ulr´s a los ficheros
    public static final String URL_CARPETA = "C:\\Users\\Public\\Documents\\La Mascarada";
    public static final String URL_PERSONAJE = "C:\\Users\\Public\\Documents\\La Mascarada\\personaje.csv";
    public static final String URL_EQ_PA_PE = "C:\\Users\\Public\\Documents\\La Mascarada\\equipo-partida-personaje.csv";
    public static final String URL_PARTIDA = "C:\\Users\\Public\\Documents\\La Mascarada\\partida.csv";
    public static final String URL_ULTMA_MODIFICACION = "C:\\Users\\Public\\Documents\\La Mascarada\\ultimaModificacion.csv";
    public static final String URL_BD = "C:\\Users\\Public\\Documents\\La Mascarada\\bd.csv";

    // Cabecera ficheros
    public static final String CABECERA_PERSONAJE = "﻿Nombre;Ataque;Defensa;VidaMax;Vida;Dinero;EstadoDeAnimo;NombreDeClan;Habilidad1;Habilidad2;IdPartida";
    public static final String CABECERA_EQ_PA_PE = "﻿NombreEquipo;IdPartida;NombrePersonaje;EnUso";
    public static final String CABECERA_PARTIDA = "﻿IdPartida;Fecha;Tiempo;Progreso;SedDeSangre;Sospecha;ÚltimaPista;IdEscena;Usuario";
    public static final String CABECERA_BD = "﻿jdbc:mysql://localhost:3306/lamascarada;;root";

    // Constantes de gestión
    public static final int SED_MAX = 5;
    public static final int SOSPECHA_MAX = 5;
    public static final int PROGRESO_MAX = 5;

    //Códigos de actualización
    public static final int ACTUALIZADO = 0;
    public static final int INSERT = 0;
    public static final int UPDATE = 0;
    public static final int DELETE = 0;

    // Atributos de un personaje
    public static final int ATQ = 5;
    public static final int DEF = 2;
    public static final int VIDA = 10;
    public static final int DINERO = 0;
    public static final int ATQ_VAM = 10;
    public static final int DEF_VAM = 4;
    public static final int VIDA_VAM = 20;

    // Atributos equipo
    //Armas - ATQ
    public static final int[] PISTOLA = {10, 0, 0, 500};
    public static final int[] BATE = {5, 0, 0, 250};
    public static final int[] NAVAJA = {4, 0, 0, 200};
    public static final int[] PUÑO_USA = {3, 0, 0, 150};
    public static final int[] BOTELLA = {2, 0, 0, 0};
    //Armaduras - DEF
    public static final int[] ANTIBALAS = {0, 9, 0, 450};
    public static final int[] ROPA_GRUESA = {0, 4, 0, 200};
    //Amuletos - VIDA
    public static final int[] COLGANTE = {0, 0, 9, 450};
    public static final int[] BRAZALETE = {0, 0, 6, 300};
    public static final int[] ANILLO = {0, 0, 3, 150};

    // Acciones de las opciones
    public static final int OP_CONTINUAR = 0;
    public static final int OP_PROGRESO = 1;
    public static final int OP_GUARDAR = 2;
    public static final int OP_BORRAR = 3;
    public static final int OP_CARGAR = 4;
    public static final int OP_PELEAR = 5;
    public static final int OP_ANALIZAR = 6;
    public static final int OP_AGRADAR = 7;
    public static final int OP_ENFADAR = 8;
    public static final int OP_COMPRAR = 9;
    public static final int OP_VENDER = 10;
    public static final int OP_SOSPECHA = 11;
    public static final int OP_SEDSANGRE = 12;
    public static final int OP_OBTENER_MAPA = 13;
    public static final int OP_OBTENER_PASS = 14;
    public static final int OP_OBTENER_LLAVE = 15;
    public static final int OP_OBTENER_NOTA = 16;
    public static final int OP_OBTENER_COLGANTE = 17;
    public static final int OP_OBTENER_PISTA = 18;
    public static final int OP_FIN = 19;

    // Condiciones de las opciones y los textos de las escenas
    public static final int SI_ESTANDAR = 0; // El texto normal
    public static final int SI_EXTRA = 1; // Textro extra
    public static final int SI_AGRADADO = 2; // Texto contento
    public static final int SI_ENFADADO = 3; // Texto enfadado
    public static final int SI_MAPA = 4; // Si se tiene un mapa.
    public static final int SI_PASS = 5; // Si se tiene una contraseña.  
    public static final int SI_LLAVE = 6;
    public static final int SI_NOTA = 7;
    public static final int SI_AZUCARILLO = 8;
    public static final int SI_PISTA = 9;
    public static final int SI_MAPA_BRUJAH = 10;
    public static final int SI_BRUJAH = 11;
    public static final int SI_PISTA_BRUJAH = 12;

    // Estados de ánimo de los NPC´s
    public static final int EA_PROTAGONISTA = 0;
    public static final int EA_NORMAL = 1;
    public static final int EA_AGRADECIDO = 2;
    public static final int EA_ENFADADO = 3;
    public static final int EA_MUERTO = 4;

    // Criptado y desencriptado 
    public static String ENCRYPT_KEY = "pipipi";
    
    // Centrar ventana
    public static void centrar (JFrame j){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - j.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - j.getHeight()) / 2);
        j.setLocation(x, y);
    }

}
