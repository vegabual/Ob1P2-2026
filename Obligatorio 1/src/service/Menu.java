package service;

import enums.Color;
import util.PantallaUtils;

/**
 *
 * @author windows
 */
public class Menu {
    
    
    
    public static void mostrarMenu(){
        PantallaUtils.imprimirAColorln("*******************************************", Color.Verde);
        PantallaUtils.imprimirAColorln("*************** Bienvenido! ***************", Color.Verde);
        PantallaUtils.imprimirAColorln("*******************************************", Color.Verde);
        
        String request = "Que queres hacer? (Salir: 0, Registrar tester: 1, Registrar matriz de juego: 2, Registrar testeo: 3, Consulta de testers: 4, Estadisticas: 5): ";
        
        int option = PantallaUtils.getEnteroDeInputEntre(request,0,5);
        
        while (!(option == 0)){
            switch (option) {
                case 1 -> System.out.println("Registrar tester");
                case 2 -> System.out.println("Registrar matriz de juego");
                case 3 -> System.out.println("Registrar testeo");
                case 4 -> System.out.println("Consulta de testers");
                case 5 -> System.out.println("Estadisticas");
            }
            
            option =  PantallaUtils.getEnteroDeInputEntre(request,0,5);
        }
        PantallaUtils.imprimirAColorln("Gracias por participar del testing!", Color.Purpura);
    }
}
