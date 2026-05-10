package util;

import enums.Color;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Clase auxiliar con distintos metodos utiles para escritura y lectura de la 
 * pantalla de consola.
 */
public class PantallaUtils {
    //<editor-fold desc="Parametros">
    private static PantallaUtils instancia = null;
    private static Scanner s = null;
    private static final String ANSI_RESET = "\u001B[0m";
    //</editor-fold>
    
    //<editor-fold desc="Getters y setters">
    private static Scanner getScanner(){
        if(s == null){
            s = new Scanner(System.in);
        }
        
        return s;
    }
    //</editor-fold>
    
    //<editor-fold desc="Constructor - Settea utf-8">
    static{
        try{
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        }
        catch(Exception e){
            System.out.println("Hubo un error! " + e.getMessage());
        }
    }
    //</editor-fold>
    
    /**
     * Obtener entero entre 2 numeros con un salto de linea entre el pedido y el 
     * numero a pedir. Si el input no es entero, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @return El entero ingresado por el usuario.
     */
    public static int getEnteroDeInput(String pedido, boolean conSaltoDeLinea){
        String input = "";
        boolean esCorrecto = false;
        
        do{
            String warning = stringAColor("(Solo estan permitidos numeros enteros) ",
                    Color.Amarillo);
            
            input = getStringTrimmeadoDeInput(warning + pedido, conSaltoDeLinea);
            esCorrecto = stringEsParseableAEntero(input); //Chequea si el texto ingresado es un entero
            if(!esCorrecto){// Si se ingreso un valor que no esta aceptado, se muestra un mensaje de error
                imprimirAColor("El valor ingresado no es un numero entero", 
                        Color.Rojo, true);
            }
        } while (!esCorrecto);
        
        return Integer.parseInt(input);
    }
    
    /**
     * Obtener entero entre 2 numeros. Si el input no es entero, o no esta entre 
     * los numeros requeridos (incluidos los bordes), seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param menorQue Numero mas pequeño aceptado.
     * @param mayorQue Numero mas grande aceptado.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @param mensajeDeError Mensaje de error a mostrar cuando el numero ingresado
     *      no es correcto. Si es null, se pone un mensaje por defecto
     * @return El entero ingresado por el usuario.
     */
    public static int getEnteroDeInputEntre(String pedido, int mayorQue, int 
            menorQue, boolean conSaltoDeLinea, String mensajeDeError){
        boolean inputEsCorrecto = false;
        int numero = 0;
        
        if(mayorQue > menorQue){
            // Los numeros estan invertidos, arreglando error logico...
            int aux = mayorQue;
            mayorQue = menorQue;
            menorQue = aux;
        }
        if(mensajeDeError == null){
            mensajeDeError = "El valor ingresado debe estar entre " + mayorQue 
                        + " y " + menorQue;
        }
        
        do{
            numero = getEnteroDeInput(pedido, conSaltoDeLinea);    
            inputEsCorrecto = numero <= menorQue && numero >= mayorQue;
            if(!inputEsCorrecto){
                imprimirAColor("ERROR: " + mensajeDeError, Color.Rojo, true);
            }
        } while (!inputEsCorrecto);
        
        return numero;
    }
    
    /**
     * Metodo auxiliar para chequear si un String se puede convertir a entero sin riesgo de 
     * una excepcion.
     * @param str String a convertir.
     * @return Si se puede convertir.
     */
    private static boolean stringEsParseableAEntero(String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
    /**
     * Obtener string por consola
     * @param pedido Mensaje que muestra al pedir el string
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @return El string ingresado por el usuario
     */
    public static String getStringTrimmeadoDeInput(String pedido, boolean conSaltoDeLinea){
        System.out.print(pedido);
        if(conSaltoDeLinea){//Si se pide con salto de linea, imprime un salto de linea
            System.out.println();
        }
        
        return getScanner().nextLine().trim();
    }
    
    /**
     * Obtener un booleano de consola
     * @param pedido Mensaje que muestra al pedir el booleano
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @return 
     */
    public static boolean getBooleanDeInput(String pedido, boolean conSaltoDeLinea){
        String input = "";
        boolean esCorrecto = false;
        boolean siONo = false;
        
        do{
            String warning = stringAColor("(S/N): ", Color.Amarillo);
            
            input = getStringTrimmeadoDeInput(pedido + warning, conSaltoDeLinea);
            esCorrecto = input.equalsIgnoreCase("S") || input.equalsIgnoreCase("Si") 
                    || input.equalsIgnoreCase("N") || input.equalsIgnoreCase("No"); //Chequea si el texto ingresado es S o N
            if(!esCorrecto){// Si se ingreso un valor que no esta aceptado, se muestra un mensaje de error
                imprimirAColor("El valor ingresado no es correcto. Ingrese "
                        + "unicamente S o N.", Color.Rojo, true);
            }
        } while (!esCorrecto);
        
        return input.equalsIgnoreCase("S") || input.equalsIgnoreCase("Si");
    }
    
    /**
     * Imprime texto a color en consola.
     * @param texto Texto a imprimir.
     * @param col Color en el que imprimira el texto.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea luego de la impresion
     */
    public static void imprimirAColor(String texto, Color col, boolean conSaltoDeLinea){
        System.out.print(col + texto + ANSI_RESET);
        if(conSaltoDeLinea){
            System.out.println();
        }
    }
    
    /**
     * Imprime un salto de linea
     */
    public static void imprimirSaltoDeLinea(){
        System.out.println();
    }
    
    /**
     * Devuelve texto con formato de color para consola.
     * @param texto Texto a pasar al formato.
     * @param col Color en el que imprimira el texto.
     */
    public static String stringAColor(String texto, Color col){
        return col + texto + ANSI_RESET;
    }
    
    /**
     * Imprime matriz con formato de tablero de juego
     * @param mat Matriz a imprimir
     * @param accent Color para los acentos (los bordes de la matriz)
     */
    public static void imprimirTablero(char[][] mat, Color accent){
        for(int row = 0; row < mat.length;row++){
            imprimirAColor("+", accent, false);
            for(int col = 0; col<mat[row].length;col++){
                imprimirAColor("---+", accent,false);
            }
            imprimirSaltoDeLinea();
            imprimirAColor("|", accent,false);
            for(int col = 0; col<mat[row].length;col++){
                if( mat[row][col] == 'V'){
                    imprimirAColor("   |", accent,false);
                } else{
                    System.out.print(" " + mat[row][col] + " ");
                    imprimirAColor("|", accent,false);
                }
            }
            imprimirSaltoDeLinea();
        }
        imprimirAColor("+", accent,false);
        for(int col = 0; col < mat[0].length;col++){
            imprimirAColor("---+", accent,false);
        }
        imprimirSaltoDeLinea();
    }
    
    /**
     * Lee una matriz de char fila a fila utilizando el input de consola
     * @param filas Cantidad de filas de la matriz
     * @param columnas Cantidad de columnas de la matriz
     * @return Matriz de char de las dimensiones requeridas
     */
    public static char[][] leerMatrizDeConsola(int filas, int columnas){
        char[][] mat = new char[filas][columnas];
        String lector;
        for(int f = 0; f < filas; f++){ //Repito por cantidad de filas requeridas
            String pedido = stringAColor("Ingrese fila numero:  " + f, Color.Amarillo);
            lector = getStringTrimmeadoDeInput(pedido, false);
            boolean filaValida = false;
            while (!filaValida){ //Pedir valores mientras no sea valida la columna
                while(lector.length() != columnas){ //Mientras no se ingresen tantos caracteres como columnas, volver a pedir fila
                    imprimirAColor("La matriz debe tener  " + columnas + " columnas", Color.Rojo, true);
                    lector = getStringTrimmeadoDeInput(pedido, false);
                }
                filaValida = true; //Asumo que la fila esta correcta hasta que se demuestre lo contrario
                for(int c = 0; c < columnas && filaValida; c++){ //Recorro el string que se ingreso
                    char caracter = lector.charAt(c);
                    if(caracter == 'V' || caracter == 'B' || caracter == 'N'){ //Si el valor ingresado esta dentro de lo correcto, lo ingreso a la matriz
                        mat[f][c] = caracter;
                    } else{ //Sino, pongo filaValida en false, y vuelvo a pedir la fila completa
                        filaValida = false;
                        imprimirAColor("La matriz solo acepta 'B', 'N' o 'V' columnas", Color.Rojo, true);
                        lector = getStringTrimmeadoDeInput(pedido, false);
                    }
                }
            }
        }
        return mat;
    }
}
