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
    public static final String URL_INSERTS = "C:\\Users\\Public\\Documents\\La Mascarada\\script.sql";

    //Url´s a los dicheros del JAR
    public static final String JAR_TEXTO_ESCENA = "/Ficheros/texto-escena.csv";
    public static final String JAR_CLAN = "/Ficheros/clan.csv";
    public static final String JAR_ESCENA = "/Ficheros/escena.csv";
    public static final String JAR_EQUIPO = "/Ficheros/equipo.csv";
    public static final String JAR_EQ_PA_PE = "/Ficheros/equipo-partida-personaje.csv";
    public static final String JAR_OPCION = "/Ficheros/opcion.csv";
    public static final String JAR_PERSONAJE = "/Ficheros/personaje.csv";
    public static final String JAR_HABILIDAD = "/Ficheros/habilidad.csv";

    // Cabecera ficheros
    public static final String CABECERA_PERSONAJE = "﻿Nombre;Ataque;Defensa;VidaMax;Vida;Dinero;EstadoDeAnimo;NombreDeClan;Habilidad1;Habilidad2;IdPartida";
    public static final String CABECERA_EQ_PA_PE = "﻿NombreEquipo;IdPartida;NombrePersonaje;EnUso";
    public static final String CABECERA_PARTIDA = "﻿IdPartida;Fecha;Tiempo;Progreso;SedDeSangre;Sospecha;ÚltimaPista;IdEscena;Usuario";
    public static final String CABECERA_BD = "3306;;root";

    //Base de datos
    public static final String RUTA = "jdbc:mysql://localhost:";
    public static final String TABLA = "/lamascarada";

    //Consultas
    public static final String IN_PARTIDA = "INSERT INTO `lamascarada`.`partida` (`IdPartida`, `Fecha`, `Tiempo`, `Progreso`, `SedSangre`, `Sospecha`, `UltimaPista`, `IdEscena`, `Usuario`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    // Constantes de gestión
    public static final int DINERO = 0;
    public static final int ATQ_VAM = 10;
    public static final int DEF_VAM = 4;
    public static final int VIDA_VAM = 20;
    public static final int SED_MAX = 5;
    public static final int SOSPECHA_MAX = 5;
    public static final int PROGRESO_MAX = 5;

    // Acciones de las opciones
    public static final int AC_CONTINUAR = 0;
    public static final int AC_PROGRESO = 1;
    public static final int AC_GUARDAR = 2;
    public static final int AC_BORRAR = 3;
    public static final int AC_CARGAR = 4;
    public static final int AC_PELEAR = 5;
    public static final int AC_ANALIZAR = 6;
    public static final int AC_AGRADAR = 7;
    public static final int AC_ENFADAR = 8;
    public static final int AC_COMPRAR = 9;
    public static final int AC_VENDER = 10;
    public static final int AC_SOSPECHA = 11;
    public static final int AC_SEDSANGRE = 12;
    public static final int AC_OBTENER_MAPA = 13;
    public static final int AC_OBTENER_PASS = 14;
    public static final int AC_OBTENER_LLAVE = 15;
    public static final int AC_OBTENER_NOTA = 16;
    public static final int AC_OBTENER_COLGANTE = 17;
    public static final int AC_OBTENER_PISTA = 18;
    public static final int AC_FIN = 19;

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
    public static final int SI_TREMERE = 60;
    public static final int SI_NOSFERATU = 90;
    public static final int SI_VENTRUE = 120;

    // Estados de ánimo de los NPC´s
    public static final int EA_PROTAGONISTA = 0;
    public static final int EA_NORMAL = 1;
    public static final int EA_AGRADECIDO = 2;
    public static final int EA_ENFADADO = 3;
    public static final int EA_MUERTO = 4;

    // Criptado y desencriptado 
    public static String ENCRYPT_KEY = "pipipi";

    // Centrar ventana
    public static void centrar(JFrame j) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - j.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - j.getHeight()) / 2);
        j.setLocation(x, y);
    }

}
