package util;

import enums.Color;
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
    
    /**
     * Obtener entero entre 2 numeros. Si el input no es entero, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @return El entero ingresado por el usuario.
     */
    public static int getEnteroDeInput(String pedido){
        String input = "";
        boolean esCorrecto = false;
        
        do{
            imprimirAColor("(Solo estan permitidos numeros enteros) ", Color.Amarillo);
            System.out.print(pedido);
            input = getScanner().nextLine();
            esCorrecto = stringEsParseableAEntero(input);
            if(!esCorrecto){
                imprimirAColorln("El valor ingresado no es un numero entero", Color.Rojo);
            }
        } while (!esCorrecto);
        
        return Integer.parseInt(input);
    }
    
    /**
     * Obtener entero entre 2 numeros. Si el input no es entero, o no esta entre 
     * los numeros requeridos, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param menorQue Numero mas pequeño aceptado.
     * @param mayorQue Numero mas grande aceptado.
     * @return El entero ingresado por el usuario.
     */
    public static int getEnteroDeInputEntre(String pedido, int mayorQue, int menorQue){
        boolean inputEsCorrecto = false;
        int numero = 0;
        
        if(mayorQue > menorQue){
            // Los numeros estan invertidos, arreglando error logico...
            int aux = mayorQue;
            mayorQue = menorQue;
            menorQue = aux;
        }
        
        do{
            numero = getEnteroDeInput(pedido);
            inputEsCorrecto = numero <= menorQue && numero >= mayorQue;
            if(!inputEsCorrecto){
                imprimirAColorln("El valor ingresado debe estar entre " + mayorQue 
                        + " y " + menorQue, Color.Rojo);
            }
        } while (!inputEsCorrecto);
        
        return numero;
    }
    
    /**
     * Metodo para chequear si un String se puede convertir a entero sin riesgo de 
     * una excepcion.
     * @param str String a convertir.
     * @return Si se puede convertir.
     */
    public static boolean stringEsParseableAEntero(String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
    /**
     * Imprime texto a color en consola.
     * @param texto Texto a imprimir.
     * @param col Color en el que imprimira el texto.
     */
    public static void imprimirAColor(String texto, Color col){
        System.out.print(col + texto + ANSI_RESET);
    }
    
    /**
     * Imprime texto a color en consola con un enter al final.
     * @param texto Texto a imprimir.
     * @param col Color en el que imprimira el texto.
     */
    public static void imprimirAColorln(String texto, Color col){
        System.out.println(col + texto + ANSI_RESET);
    }
    
    /**
     * Imprime matriz con formato de tablero de juego
     * @param mat Matriz a imprimir
     * @param accent Color para los acentos (los bordes de la matriz)
     */
    public static void imprimirTablero(String[][] mat, Color accent){
        for(int row = 0; row < mat.length;row++){
            imprimirAColor("+", accent);
            for(int col = 0; col<mat[row].length;col++){
                imprimirAColor("---+", accent);
            }
            System.out.println("");
            imprimirAColor("|", accent);
            for(int col = 0; col<mat[row].length;col++){
                if( mat[row][col].equals("V")){
                    imprimirAColor("   |", accent);
                } else{
                    System.out.print(" " + mat[row][col] + " ");
                    imprimirAColor("|", accent);
                }
            }
            System.out.println("");
        }
        imprimirAColor("+", accent);
        for(int col = 0; col < mat[0].length;col++){
            imprimirAColor("---+", accent);
        }
        System.out.println("");
        
    }
}
