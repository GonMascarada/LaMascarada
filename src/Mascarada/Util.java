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
    public static final String URL_PERSONAJE_LOCAL = "C:\\Users\\Public\\Documents\\La Mascarada\\personaje_local.csv";
    public static final String URL_EQ_PA_PE = "C:\\Users\\Public\\Documents\\La Mascarada\\equipo-partida-personaje.csv";
    public static final String URL_EQ_PA_PE_LOCAL = "C:\\Users\\Public\\Documents\\La Mascarada\\equipo-partida-personaje_local.csv";
    public static final String URL_PARTIDA = "C:\\Users\\Public\\Documents\\La Mascarada\\partida.csv";
    public static final String URL_PARTIDA_LOCAL = "C:\\Users\\Public\\Documents\\La Mascarada\\partida_local.csv";
    public static final String URL_ULTMA_MODIFICACION = "C:\\Users\\Public\\Documents\\La Mascarada\\ultimaModificacion.csv";
    public static final String URL_BD = "C:\\Users\\Public\\Documents\\La Mascarada\\bd.csv";
    public static final String URL_INSERTS = "C:\\Users\\Public\\Documents\\script.sql";

    //Url´s a los dicheros del JAR
    public static final String JAR_TEXTO_ESCENA = "/Ficheros/texto-escena.csv";
    public static final String JAR_CLAN = "/Ficheros/clan.csv";
    public static final String JAR_ESCENA = "/Ficheros/escena.csv";
    public static final String JAR_EQUIPO = "/Ficheros/equipo.csv";
    public static final String JAR_EQ_PA_PE = "/Ficheros/equipo-partida-personaje.csv";
    public static final String JAR_OPCION = "/Ficheros/opcion.csv";
    public static final String JAR_PERSONAJE = "/Ficheros/personaje.csv";
    public static final String JAR_HABILIDAD = "/Ficheros/habilidad.csv";
    public static final String JAR_FUNCIONES = "/Ficheros/LaMascaradaFunciones.sql";
    public static final String JAR_TABLAS = "/Ficheros/LaMascaradaTablas.sql";

    // Cabecera ficheros
    public static final String CABECERA_PERSONAJE = "﻿Nombre;Ataque;Defensa;VidaMax;Vida;Dinero;EstadoDeAnimo;NombreDeClan;Habilidad1;Habilidad2;IdPartida;Usuario";
    public static final String CABECERA_EQ_PA_PE = "﻿NombreEquipo;IdPartida;NombrePersonaje;EnUso;Usuario";
    public static final String CABECERA_PARTIDA = "﻿IdPartida;Fecha;Tiempo;Progreso;SedDeSangre;Sospecha;ÚltimaPista;IdEscena;Usuario;Dificultad";
    public static final String CABECERA_BD = "3306;;root";

    //Base de datos
    public static final String RUTA = "jdbc:mysql://localhost:";
    public static final String TABLA = "/lamascarada";

    //Consultas
    public static final String IN_PARTIDA = "INSERT INTO `lamascarada`.`partida` (`IdPartida`, `Fecha`, `Tiempo`, `Progreso`, `SedSangre`, `Sospecha`, `UltimaPista`, `IdEscena`, `Usuario`, `Dificultad`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String IN_PERSONAJE = "INSERT INTO `personaje_en_partida` (`Nombre`, `Ataque`, `Defensa`, `VidaMax`, `Vida`, `Dinero`, `EstadoAnimo`, `NombreClan`, `NombreHabilidad1`, `NombreHabilidad2`, `IdPartida`, `Usuario`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String IN_EQ_PA_PE = "INSERT INTO `equipo_partida_personaje_en_partida` (`NombreEquipo`, `IdPartida`, `NombrePersonaje`, `EnUso`, `Usuario`) VALUES (?, ?, ?, ?, ?);";
    public static final String UP_FECHA = "UPDATE `usuario` SET `Ultima_Modificacion` = ? WHERE `usuario`.`Usuario` = ?;";
    public static final String IN_USUARIO = "INSERT INTO `usuario` (`Usuario`, `Pass`, `Ultima_Modificacion`) VALUES (?, ?, current_timestamp());";
    public static final String SE_USUARIO = "SELECT comprobarNombreUsuarioDisponible(?) as resultado;";
    public static final String SE_HORA = "SELECT Ultima_Modificacion FROM `usuario` WHERE Usuario=?;";
    public static final String DE_PARTIDA = "call borrarDatosPartida(?);";
    public static final String SE_PARTIDA = "SELECT * FROM `partida` WHERE Usuario = ?;";
    public static final String SE_PERSONAJE = "SELECT * FROM `personaje_en_partida` WHERE Usuario = ?;";
    public static final String SE_EQ_PA_PE = "SELECT * FROM `equipo_partida_personaje_en_partida` WHERE Usuario = ?;";

    // Constantes de gestión
    public static final int DINERO = 0;
    public static final int ATQ_HUM = 5;
    public static final int DEF_HUM = 2;
    public static final int VIDA_HUM = 10;
    public static final int ATQ_VAM = 10;
    public static final int DEF_VAM = 4;
    public static final int VIDA_VAM = 20;
    public static final int FACIL = 10;
    public static final int NORMAL = 6;
    public static final int DIFICIL = 4;
    public static final int PESADILLA = 2;

    // Escenas predefinidas
    public static final int ES_MUERTE = 2;
    public static final int ES_BAR = 500;
    public static final int ES_MOTEL = 750;
    public static final int ES_PUB = 1000;
    public static final int ES_CASA_COLINA = 1250;
    public static final int ES_PERIODICO = 1500;
    public static final int ES_BIBLIOTECA = 1750;
    public static final int ES_CALLEJON = 2000;
    public static final int ES_IGLESIA = 2250;
    public static final int ES_CASA_ABANDONADA = 2500;
    public static final int ES_PLAZA = 2750;
    public static final int ES_COLEGIO = 3000;

    // Acciones de las opciones
    public static final int AC_CONTINUAR = 0;
    public static final int AC_PROGRESO = 1;
    public static final int AC_GUARDAR = 2;
    public static final int AC_SALIR = 3;
    public static final int AC_GUARDAR_Y_SALIR = 4;
    public static final int AC_PELEAR = 5;
    public static final int AC_CURAR = 6;
    public static final int AC_AGRADAR = 7;
    public static final int AC_ENFADAR = 8;
    public static final int AC_COMPRAR = 9;
    public static final int AC_VENDER = 10;
    public static final int AC_SOSPECHA = 11;
    public static final int AC_SEDSANGRE = 12;
    public static final int AC_OBTENER_MAPA_Y_PROGRESO = 13;
    public static final int AC_OBTENER_PASS = 14;
    public static final int AC_OBTENER_LLAVE_Y_PROGRESO = 15;
    public static final int AC_OBTENER_NOTA_Y_PROGRESO = 16;
    public static final int AC_OBTENER_COLGANTE = 17;
    public static final int AC_OBTENER_PISTA = 18;
    public static final int AC_FIN = 19;
    public static final int AC_OBTENER_MAPA_Y_AGRADAR_Y_PROGRESO = 20;
    public static final int AC_OBTENER_MAPA_Y_ENFADAR_PROGRESO = 21;
    public static final int AC_OBTENER_LLAVE_Y_AGRADAR_Y_PROGRESO = 22;
    public static final int AC_OBTENER_LLAVE_Y_ENFADAR_PROGRESO = 23;
    public static final int AC_LECTURA_MANOS = 24;
    public static final int AC_LECTURA_MANOS_AGRADAR_Y_PROGRESO = 25;
    public static final int AC_OBTENER_MASCARILLA_Y_PROGRESO = 26;
    public static final int AC_OBTENER_PISTOLA = 27;
    public static final int AC_OBTENER_COLGANTE_ROJO = 28;
    public static final int AC_OBTENER_LLAVE = 29;
    public static final int AC_OBTENER_MAPA = 30;
    public static final int AC_OBTENER_MASCARILLA = 31;
    public static final int AC_OBTENER_NOTA = 32;

    public static final int AC_MOSTRAR_MAPA = 50;
    public static final int AC_MOSTRAR_TIENDA = 51;

    // Condiciones de las opciones y los textos de las escenas
    public static final int SI_ESTANDAR = 0; // El texto normal
    public static final int SI_EXTRA = 1; // Textro extra
    public static final int SI_AGRADADO = 2; // Texto contento
    public static final int SI_ENFADADO = 3; // Texto enfadado
    public static final int SI_MAPA = 4; // Si se tiene un mapa.
    public static final int SI_PASS = 5; // Si se tiene una contraseña.  
    public static final int SI_LLAVE = 6;
    public static final int SI_NOTA = 7;
    public static final int SI_ANIMALISMO = 8;
    public static final int SI_PISTA = 9;
    public static final int SI_NO_MAPA = 10;
    public static final int SI_MASCARILLA = 11;
    public static final int SI_NO_MASCARILLA = 12;
    public static final int SI_NO_PASS = 13;
    public static final int SI_COLGANTE_ROJO = 14;
    public static final int SI_BRUJAH = 30;
    public static final int SI_BRUJAH_O_VENTRUE_Y_NO_LLAVE = 31;
    public static final int SI_BRUJAH_O_VENTRUE = 32;
    public static final int SI_BRUJAH_Y_NO_PISTOLA = 33;
    public static final int SI_TREMERE = 60;
    public static final int SI_NO_TREMERE = 61;
    public static final int SI_TREMERE_Y_NO_LLAVE = 62;
    public static final int SI_TREMERE_Y_NO_COLGANTE_ROJO = 63;
    public static final int SI_NOSFERATU = 90;
    public static final int SI_NO_NOSFERATU = 91;
    public static final int SI_NOSFERATU_U_OTRO_EA = 92;
    public static final int SI_NOSFERATU_Y_NO_LLAVE = 93;
    public static final int SI_NO_NOSFERATU_Y_PASS_Y_NO_AGRADADO = 94;
    public static final int SI_NOSFERATU_Y_MASCARILLA = 95;
    public static final int SI_NOSFERATU_Y_NO_MASCARILLA = 96;
    public static final int SI_VENTRUE = 120;
    public static final int SI_VENTRUE_Y_NO_PISTOLA = 121;

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
