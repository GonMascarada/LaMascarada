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
    public static final int PROGRESO = 0;
    public static final int GUARDAR = 1;
    public static final int BORRAR = 2;
    public static final int CARGAR = 3;
    public static final int PELEAR = 4;
    public static final int ANALIZAR = 5;
    public static final int AGRADAR = 6;
    public static final int ENFADAR = 7;
    public static final int COMPRAR = 8;
    public static final int VENDER = 9;

    // Estados de ánimo de los NPC´s
    public static final int NORMAL = 0;
    public static final int AGRADECIDO = 1;
    public static final int ENFADADO = 2;
    public static final int MUERTO = 3;
}
