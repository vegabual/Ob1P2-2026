/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio1;

import enums.Color;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import service.Menu;
import util.PantallaUtils;

/**
 *
 * @author windows
 */
public class Obligatorio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
            String[][] tablero = {
                {"V","V","N","N","V","V","N","N","V","V"},
                {"N","N","N","N","N","N","N","N","N","N"},
                {"N","V","V","V","V","V","N","N","V","N"},
                {"N","V","V","V","V","V","N","N","V","N"},
                {"N","V","V","V","V","V","N","N","V","N"},
                {"N","B","B","B","B","B","N","N","B","N"},
                {"B","V","V","V","V","V","B","B","V","B"},
                {"B","B","B","B","B","B","B","B","B","B"}
            };

            PantallaUtils.imprimirTablero(tablero,Color.PurpuraNegrita);
            Menu.mostrarMenu();
        }
        catch(Exception e){
            System.out.println("Hubo un error! " + e.getMessage());
        }
    }
    
}
