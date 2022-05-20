package Mascarada;

/**
 * Compendio de constantes comunes para todo el programa. Contiene los dátos
 * estandar.
 *
 * @author Gonzalo López Fernández
 */
public abstract class Utilidades {

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
    public static final int OP_PROGRESO = 0;
    public static final int OP_GUARDAR = 1;
    public static final int OP_BORRAR = 2;
    public static final int OP_CARGAR = 3;
    public static final int OP_PELEAR = 4;
    public static final int OP_ANALIZAR = 5;
    public static final int OP_AGRADAR = 6;
    public static final int OP_ENFADAR = 7;
    public static final int OP_COMPRAR = 8;
    public static final int OP_VENDER = 9;
    public static final int OP_SOSPECHA = 10;
    public static final int OP_SEDSANGRE = 11;
    public static final int OP_OBTENER_MAPA = 12;
    public static final int OP_OBTENER_PASS = 13;
    public static final int OP_OBTENER_LLAVE = 14;
    public static final int OP_OBTENER_NOTA = 15;

    // Condiciones de las escenas
    public static final int SI_ESTANDAR = 0; // El texto normal
    public static final int SI_EXTRA = 1; // Textro extra
    public static final int SI_AGRADADO = 2; // Texto contento
    public static final int SI_ENFADADO = 3; // Texto enfadado

    // Condiciones de las opciones
    //public static final int ESTANDAR = 0; // Aparecerá siempre
    //public static final int SI_AGRADADO = 2; // Opción si está contento
    //public static final int SI_ENFADADO = 3; // Opción si está enfadado
    public static final int SI_MAPA = 4; // Si se tiene un mapa.
    public static final int SI_PASS = 5; // Si se tiene una contraseña.  
    public static final int SI_LLAVE = 6;
    public static final int SI_NOTA = 7;

    // Estados de ánimo de los NPC´s
    public static final int EA_PROTAGONISTA = 0;
    public static final int EA_NORMAL = 1;
    public static final int EA_AGRADECIDO = 2;
    public static final int EA_ENFADADO = 3;
    public static final int EA_MUERTO = 4;
}
