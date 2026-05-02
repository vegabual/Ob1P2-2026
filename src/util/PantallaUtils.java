package util;

import enums.Color;
import java.util.Scanner;

/**
 * Clase auxiliar con distintos metodos utiles para escritura y lectura de la 
 * pantalla de consola.
 */
public class PantallaUtils {
    //<editor-fold desc="Parametros">
    private Scanner s;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_NEGRO = "\u001B[30m";
    private static final String ANSI_ROJO = "\u001B[31m";
    private static final String ANSI_VERDE = "\u001B[32m";
    private static final String ANSI_AMARILLO = "\u001B[33m";
    private static final String ANSI_AZUL = "\u001B[34m";
    private static final String ANSI_PURPURA = "\u001B[35m";
    private static final String ANSI_CELESTE = "\u001B[36m";
    private static final String ANSI_BLANCO = "\u001B[37m";
    
    //</editor-fold>
    
    //<editor-fold desc="Getters y setters">
    private void setScanner(Scanner s) {
        this.s = s;
    }
    
    private Scanner getScanner(){
        return this.s;
    }
    //</editor-fold>
    
    public PantallaUtils() {
        setScanner(new Scanner(System.in));
    }
    
    /**
     * Obtener entero entre 2 numeros. Si el input no es entero, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @return El entero ingresado por el usuario.
     */
    public int getEnteroDeInput(String pedido){
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
    public int getEnteroDeInputEntre(String pedido, int mayorQue, int menorQue){
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
    public boolean stringEsParseableAEntero(String str){
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
    public void imprimirAColor(String texto, Color col){
        System.out.print(getAnsi(col) + texto + ANSI_RESET);
    }
    
    /**
     * Imprime texto a color en consola con un enter al final.
     * @param texto Texto a imprimir.
     * @param col Color en el que imprimira el texto.
     */
    public void imprimirAColorln(String texto, Color col){
        System.out.println(getAnsi(col) + texto + ANSI_RESET);
    }
    
    /**
     * Obtiene el ansi del color a partir del nombre del color
     * @param nombre Nombre del color requerido.
     * @return Ansi del color.
     */
    private String getAnsi(Color nombre){
        String ansi = ANSI_RESET;
        switch (nombre) {
            case Negro -> ansi = ANSI_NEGRO;
            case Rojo -> ansi = ANSI_ROJO;
            case Verde -> ansi = ANSI_VERDE;
            case Amarillo -> ansi = ANSI_AMARILLO;
            case Azul -> ansi = ANSI_AZUL;
            case Purpura -> ansi = ANSI_PURPURA;
            case Celeste -> ansi = ANSI_CELESTE;
            case Blanco -> ansi = ANSI_BLANCO;
        }
        return ansi;
    }
}
