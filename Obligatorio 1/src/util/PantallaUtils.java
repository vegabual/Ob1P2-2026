package util;

import enums.Sentido;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import model.Tester;
import service.Sistema;

/**
 * Clase auxiliar con distintos metodos utiles para escritura y lectura de la pantalla de consola. 
 * Toda lectura y escritura dentro del programa será manejada con esta clase, de forma que se pueda cambiar facilmente de ser necesario.
 */
public class PantallaUtils {
    //<editor-fold desc="Parametros">
    private static PantallaUtils instancia = null;
    private static Scanner s = null;
    private static final String ANSI_RESET = "\u001B[0m";
    //</editor-fold>
    
    //<editor-fold desc="Getters y setters">
    /**
     * Getter privado para el scanner. En caso de que el scanner no haya sido incializado, lo hace en ese momento.
     * @return 
     */
    private static Scanner getScanner(){
        if(s == null){
            s = new Scanner(System.in);
        }
        
        return s;
    }
    //</editor-fold>
    
    //<editor-fold desc="Constructor - Settea utf-8">
    /**
     * Incializador estatico que se ejecuta al cargar la clase en memoria
     */
    static{
        try{
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        }
        catch(Exception e){
            System.out.println("Hubo un error al cargar UTF! " + e.getMessage());
        }
    }
    //</editor-fold>
    
    /**
     * Obtener entero entre 2 numeros desde el input. 
     * Si el input no es entero, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @return El entero ingresado por el usuario.
     */
    public static int getEnteroDeInput(String pedido, boolean conSaltoDeLinea){
        String input = "";
        boolean esCorrecto = false;
        
        do{
            String warning = "(Solo estan permitidos numeros enteros) ";
            
            input = getStringTrimmeadoDeInput(warning + pedido, conSaltoDeLinea);
            esCorrecto = stringEsParseableAEntero(input); //Chequea si el texto ingresado es un entero
            if(!esCorrecto){// Si se ingreso un valor que no esta aceptado, se muestra un mensaje de error
                imprimirTexto("ERROR: El valor ingresado no es un numero entero", true);
            }
        } while (!esCorrecto);
        
        return Integer.parseInt(input);
    }
    
    /**
     * Obtener entero entre 2 numeros desde el input. 
     * Si el input no es entero, o no esta entre los numeros requeridos 
     * (incluidos los bordes), seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param menorQue Numero mas pequeño aceptado.
     * @param mayorQue Numero mas grande aceptado.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @param mensajeDeError Mensaje de error a mostrar cuando el numero ingresado
     *      no es correcto. Si es null, se pone un mensaje por defecto
     * @return El entero ingresado por el usuario.
     */
    public static int getEnteroDeInputEntre(String pedido, int mayorQue, int menorQue, boolean conSaltoDeLinea, String mensajeDeError){
        boolean inputEsCorrecto = false;
        int numero = 0;
        
        if(mayorQue > menorQue){
            // Los numeros estan invertidos, arreglando error logico...
            int aux = mayorQue;
            mayorQue = menorQue;
            menorQue = aux;
        }
        if(mensajeDeError == null){
            mensajeDeError = "El valor ingresado debe estar entre " + mayorQue + " y " + menorQue;
        }
        
        do{
            numero = getEnteroDeInput(pedido, conSaltoDeLinea);    
            inputEsCorrecto = numero <= menorQue && numero >= mayorQue;
            if(!inputEsCorrecto){
                imprimirTexto("ERROR: " + mensajeDeError, true);
            }
        } while (!inputEsCorrecto);
        
        return numero;
    }
    
    /**
     * Metodo auxiliar para chequear si un String se puede convertir a entero sin 
     * riesgo de una excepcion.
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
     * Metodo auxiliar para chequear si un String se puede convertir a el enumerado 
     * Sentido sin riesgo de una excepcion.
     * @param str String a convertir.
     * @return Si se puede convertir.
     */
    private static boolean stringEsParseableASentido(String str){
        try{
            Sentido s = Sentido.valueOf(str);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
    /**
     * Obtener string por consola y trimmea los bordes.
     * @param pedido Texto para mostrar el pedido al usuario.
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
     * Si el input no es S o N, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @return 
     */
    public static boolean getBooleanDeInput(String pedido, boolean conSaltoDeLinea){
        String input = "";
        boolean esCorrecto = false;
        boolean siONo = false;
        
        do{
            String warning = "(S/N): ";
            
            input = getStringTrimmeadoDeInput(pedido + warning, conSaltoDeLinea);
            esCorrecto = input.equalsIgnoreCase("S") || input.equalsIgnoreCase("Si") || input.equalsIgnoreCase("N") || input.equalsIgnoreCase("No"); //Chequea si el texto ingresado es S o N
            if(!esCorrecto){// Si se ingreso un valor que no esta aceptado, se muestra un mensaje de error
                imprimirTexto("ERROR: El valor ingresado no es correcto. Ingrese unicamente S o N.", true);
            }
        } while (!esCorrecto);
        
        return input.equalsIgnoreCase("S") || input.equalsIgnoreCase("Si");
    }
    
    /**
     * Obtiene un tester a partir del nombre ingresado en input
     * Si el input no corresponde con el nombre de un tester, seguira pidiendo.
     * @param sistema Sistema donde buscará el tester
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * @return 
     */
    public static Tester getTesterDeInput(Sistema sistema, String pedido, boolean conSaltoDeLinea){
        String input = "";
        Tester testerIngresado = null;
        
        do{
            input = getStringTrimmeadoDeInput(pedido, conSaltoDeLinea);
            testerIngresado = sistema.encontrarTester(input); //Busca tester por nombre
            if(testerIngresado == null){// Si no se encuentra, se muestra un mensaje de error
                imprimirTexto("ERROR: No se encontro un tester llamado: . Por favor ingrese un tester existente", true);
            }
        } while (testerIngresado == null);
        
        return testerIngresado;
    }
    
    /**
     * Obtiene un color de ficha a partir del input.
     * Si el input no es valido, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * @return Color ingresado ("N" o "B")
     */
    public static char getColorDeInput(String pedido, boolean conSaltoDeLinea){
        String input = "";
        boolean esCorrecto = false;
        
        do{
            String warning = " (B/N): ";
            input = getStringTrimmeadoDeInput(pedido, conSaltoDeLinea).toUpperCase();
            esCorrecto = (input.equals("B") || input.equals("N")); //Chequea si el texto ingresado es B o N
            if(!esCorrecto){// Si se ingreso un valor que no esta aceptado, se muestra un mensaje de error
                imprimirTexto("El color ingresado no es valido. Los colores validos son 'B' o 'N'", true);
            }
        } while (!esCorrecto);
        
        return input.charAt(0);
    }
    
    /**
     * Obtener sentido desde el input. 
     * Si el input no corresponde a un sentido, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @return El entero ingresado por el usuario.
     */
    public static Sentido getSentidoDeInput(String pedido, boolean conSaltoDeLinea, String warning){
        String input = "";
        boolean esCorrecto = false;
        
        do{
            if(warning == null){
                warning = " (S/N/E/O/SO/SE/NO/NE): ";
            }
            
            input = getStringTrimmeadoDeInput(pedido + warning, conSaltoDeLinea).toUpperCase();
            esCorrecto = stringEsParseableASentido(input); //Chequea si el texto ingresado es un entero
            if(!esCorrecto){// Si se ingreso un valor que no esta aceptado, se muestra un mensaje de error
                imprimirTexto("ERROR: El valor ingresado no es se corresponde con un sentido valido", true);
            }
        } while (!esCorrecto);
        
        return Sentido.valueOf(input);
    }
    
    /**
     * Obtener sentido desde el input, admitiendo solo movimientos ortogonales. 
     * Si el input no corresponde a un sentido, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @return El entero ingresado por el usuario.
     */
    public static Sentido getSentidoOrtogonalDeInput(String pedido, boolean conSaltoDeLinea){
        String input = "";
        boolean esCorrecto = false;
        Sentido sentido;
        
        do{
            sentido = getSentidoDeInput(pedido, conSaltoDeLinea, " (S/N/E/O): ");    
            esCorrecto = (sentido == Sentido.N) || (sentido == Sentido.S) || (sentido == Sentido.E) || (sentido == Sentido.O);
            if(!esCorrecto){
                imprimirTexto("ERROR: El sentido seleccionado no es ortogonal", true);
            }
        } while (!esCorrecto);
        
        return sentido;
    }
    
    /**
     * Obtener forma de grupo desde el input. 
     * Si el input no corresponde a una forma valida, seguira pidiendo.
     * @param pedido Texto para mostrar el pedido al usuario.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea 
     * entre el pedido y el input a ingresar
     * @return El entero ingresado por el usuario.
     */
    public static char getFormaDeInput(String pedido, boolean conSaltoDeLinea){
        String input = "";
        boolean esCorrecto = false;
        
        do{
            String warning = " (H/V):";
            
            input = getStringTrimmeadoDeInput(pedido + warning, conSaltoDeLinea).toUpperCase();
            esCorrecto = input.equals("H") || input.equals("V"); //Chequea si el texto ingresado es H o V
            if(!esCorrecto){// Si se ingreso un valor que no esta aceptado, se muestra un mensaje de error
                imprimirTexto("ERROR: El valor ingresado no es se corresponde con una forma valida.", true);
            }
        } while (!esCorrecto);
        
        return input.charAt(0);
    }
    
    /**
     * Imprime texto en consola.
     * @param texto Texto a imprimir.
     * @param conSaltoDeLinea Booleano que indica si debe haber salto de linea luego de la impresion
     */
    public static void imprimirTexto(String texto, boolean conSaltoDeLinea){
        System.out.print(texto);
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
     * Imprime matriz con formato de tablero de juego
     * @param mat Matriz a imprimir
     */
    public static void imprimirTablero(char[][] mat){
        for(int row = 0; row < mat.length;row++){
            imprimirTexto("+", false);
            for(int col = 0; col<mat[row].length;col++){
                imprimirTexto("---+", false);
            }
            imprimirSaltoDeLinea();
            imprimirTexto("|", false);
            for(int col = 0; col<mat[row].length;col++){
                if( mat[row][col] == 'V'){
                    imprimirTexto("   |", false);
                } else{
                    System.out.print(" " + mat[row][col] + " ");
                    imprimirTexto("|", false);
                }
            }
            imprimirSaltoDeLinea();
        }
        imprimirTexto("+", false);
        for(int col = 0; col < mat[0].length;col++){
            imprimirTexto("---+", false);
        }
        imprimirSaltoDeLinea();
    }
    
    /**
     * Imprime un texto centrado en un encuadre
     * @param texto Texto a imprimir
     * @param bordesHorizontales String que oficiara de bordes horizontales
     */
    public static void imprimirEncuadrado(String texto, String bordesHorizontales){
        if(bordesHorizontales.length() - texto.length() - 2 >= 0){ //Si el texto cabe en el encuadre
            PantallaUtils.imprimirTexto(bordesHorizontales, true); //Se imprime el borde superior del encuadre
            int espaciosAImprimir = bordesHorizontales.length() - texto.length() - 2; //Definir cantidad de espacios que se deben escribir para mantener el encuadre
            for(int i = 0; i < espaciosAImprimir/2;i++){ //Se agregan la mitad de los espacios al principio del texto
                texto = " " + texto;
            }
            texto = "|" + texto; //Se agrega el borde izquierdo
            if(espaciosAImprimir % 2 == 1){ // Si tiene cantidad impar de espacios, agregar uno para mantener bien el borde
                espaciosAImprimir++;
            }
            for(int i = 0; i < espaciosAImprimir/2;i++){ //Se agrega la segunda mitad de los espacios al final del texto
                texto += " ";
            }
            texto += "|"; //Se agrega el borde derecho
            PantallaUtils.imprimirTexto(texto, true); //Se imprime el texto centrado con sus bordes laterales
            PantallaUtils.imprimirTexto(bordesHorizontales, true); //Se imprime el borde inferior del encuadre
        } else { //En caso de que no entre el texto dentro del encuadre, se imprime el texto entre los bordes, sin bordes laterales
            PantallaUtils.imprimirTexto(bordesHorizontales, true); //Se imprime el borde superior del encuadre
            PantallaUtils.imprimirTexto(texto, true); //Se imprime el texto centrado con sus bordes laterales
            PantallaUtils.imprimirTexto(bordesHorizontales, true); //Se imprime el borde inferior del encuadre
        }
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
            String pedido = "Ingrese fila numero  " + (f + 1) + ": ";
            lector = getStringTrimmeadoDeInput(pedido, false);
            boolean filaValida = false;
            while (!filaValida){ //Pedir valores mientras no sea valida la columna
                while(lector.length() != columnas){ //Mientras no se ingresen tantos caracteres como columnas, volver a pedir fila
                    imprimirTexto("La matriz debe tener  " + columnas + " columnas",  true);
                    lector = getStringTrimmeadoDeInput(pedido, false);
                }
                filaValida = true; //Asumo que la fila esta correcta hasta que se demuestre lo contrario
                for(int c = 0; c < columnas && filaValida; c++){ //Recorro el string que se ingreso
                    char caracter = Character.toUpperCase(lector.charAt(c));
                    if(caracter == 'V' || caracter == 'B' || caracter == 'N'){ //Si el valor ingresado esta dentro de lo correcto, lo ingreso a la matriz
                        mat[f][c] = caracter;
                    } else{ //Sino, pongo filaValida en false, y vuelvo a pedir la fila completa
                        filaValida = false;
                        imprimirTexto("La matriz solo acepta 'B', 'N' o 'V' columnas", true);
                        lector = getStringTrimmeadoDeInput(pedido, false);
                    }
                }
            }
        }
        return mat;
    }
}
