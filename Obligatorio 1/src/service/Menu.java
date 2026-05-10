package service;

import model.Tester;
import util.PantallaUtils;

public class Menu {
    
    private static Sistema sistema = new Sistema();

    //<editor-fold desc="Getters y Setters">
    private static Sistema getSistema() {
        return sistema;
    }
    //</editor-fold>
    
    public static void mostrarMenu(){
        
        String pedido = "\nQue queres hacer? "
                + "\n0- Salir "
                + "\n1- Registrar tester "
                + "\n2- Registrar matriz de juego "
                + "\n3- Registrar testeo "
                + "\n4- Consulta de testers "
                + "\n5- Estadisticas"
                + "\n-------------------------------\nOpcion: ";
        String mensajeDeError = "Solo se aceptan los numeros con opciones de menu (0-5)";
        
        imprimirTitulo();
        int option = PantallaUtils.getEnteroDeInputEntre(pedido,0,5, false, mensajeDeError);
        
        while (option != 0){
            switch (option) {
                case 1 -> registrarTesterPorConsola();
                case 2 -> registrarMatrizPorConsola();
                case 3 -> registrarTesteo();
                case 4 -> System.out.println("Consulta de testers");
                case 5 -> System.out.println("Estadisticas");
            }
            
            imprimirTitulo();
            option =  PantallaUtils.getEnteroDeInputEntre(pedido,0,5, true, mensajeDeError);
        }
        PantallaUtils.imprimirTexto("Gracias por participar del testing!", true);
    }
    
    private static void imprimirTitulo(){
            PantallaUtils.imprimirTexto("--------------------------------------------", true);
            PantallaUtils.imprimirTexto("|                   MENU                   |", true);
            PantallaUtils.imprimirTexto("--------------------------------------------", true);
    }
    
    private static void registrarTesterPorConsola(){
        PantallaUtils.imprimirTexto("--------------------------------------------", true);
        String nombre = PantallaUtils.getStringTrimmeadoDeInput("Ingrese el nombre del tester: ", false);
        int edad = PantallaUtils.getEnteroDeInputEntre("Ingrese la edad del tester: ", 0, 100, false, "La edad debe estar entre 0 y 100");
        int experiencia = PantallaUtils.getEnteroDeInputEntre("Ingrese la edad del tester: ", 0, edad, false, "La experiencia debe estar entre 0 y " + edad);
        
        if(getSistema().registrarTester(nombre, edad, experiencia)){
            PantallaUtils.imprimirTexto("El tester fue agregado correctamente", true);
        } else{
            PantallaUtils.imprimirTexto("ERROR: Ya existe un tester con el nombre " + nombre, true);
        }
    }
    
    private static void registrarMatrizPorConsola(){
        PantallaUtils.imprimirTexto("--------------------------------------------", true);
        boolean tableroDefault = PantallaUtils.getBooleanDeInput("Desea usar el tablero por defecto? ", false);
        if(tableroDefault){
            getSistema().registrarTablero();
        } else{
            char[][] nuevoTablero = PantallaUtils.leerMatrizDeConsola(8, 10);
            getSistema().registrarTablero(nuevoTablero);
        }
    }
    
    private static void registrarTesteo(){
        PantallaUtils.imprimirTexto("--------------------------------------------", true);
        int caso = PantallaUtils.getEnteroDeInputEntre("Ingrese el caso a probar: ", 1, 5, false, "El caso ingresado no existe, solo se aceptan numeros del 1 al 5");
        Tester tester = seleccionarTester();
        String comentario = PantallaUtils.getStringTrimmeadoDeInput("Comentario: ", false);
        getSistema().registrarTesteo(caso, tester, comentario);
        switch (caso) {
            case 1-> registrarTesteoCaso1();
            case 2-> registrarTesteoCaso2();
            case 3-> registrarTesteoCaso3();
            case 4-> registrarTesteoCaso4();
            case 5-> registrarTesteoCaso5();
        }
    }

    private static void registrarTesteoCaso1() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void registrarTesteoCaso2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private static void registrarTesteoCaso3() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void registrarTesteoCaso4() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void registrarTesteoCaso5() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static Tester seleccionarTester() {
        getSistema().registrarTester("Vero", 32, 10);
        Tester t = getSistema().encontrarTester("Vero");
        return t;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
