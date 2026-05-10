package obligatorio1;

import enums.Color;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import model.Tablero;
import service.Menu;
import util.PantallaUtils;

public class Obligatorio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            
            PantallaUtils.imprimirAColor("********************************************", Color.PurpuraNegrita, true);
            PantallaUtils.imprimirAColor("*        Bienvenido al Obligatorio1        *", Color.PurpuraNegrita, true);
            PantallaUtils.imprimirAColor("*         Trabajo desarrollado por         *", Color.PurpuraNegrita, true);
            PantallaUtils.imprimirAColor("*          Lourdes Ayala - 354398          *", Color.PurpuraNegrita, true);
            PantallaUtils.imprimirAColor("*        Veronica Busiello - 212712        *", Color.PurpuraNegrita, true);
            PantallaUtils.imprimirAColor("********************************************", Color.PurpuraNegrita, true);
            
//            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
//            char[][] tablero = {
//                {'V','V','N','N','V','V','N','N','V','V'},
//                {'N','N','N','N','N','N','N','N','N','N'},
//                {'N','V','V','V','V','V','N','N','V','N'},
//                {'N','V','V','V','V','V','N','N','V','N'},
//                {'N','V','V','V','V','V','N','N','V','N'},
//                {'N','B','B','B','B','B','N','N','B','N'},
//                {'B','V','V','V','V','V','B','B','V','B'},
//                {'B','B','B','B','B','B','B','B','B','B'}
//            };
//            /*
//            PantallaUtils.imprimirTablero(tablero,Color.PurpuraNegrita);
//            Menu.mostrarMenu();*/
//            
//            Tablero tab1 = new Tablero();
//            System.out.println(tab1.prepararTablero());
//            
//            Tablero tab2 = new Tablero(tablero);
//            System.out.println(tab2.prepararTablero());
//            
            Menu.mostrarMenu();
        }
        catch(Exception e){
            PantallaUtils.imprimirAColor("Hubo un error! " + e.getMessage(), Color.Rojo, true);
        }
    }
    
}
