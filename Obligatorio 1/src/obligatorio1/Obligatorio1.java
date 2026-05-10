package obligatorio1;

import service.Menu;
import util.PantallaUtils;

public class Obligatorio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            
            PantallaUtils.imprimirTexto("********************************************", true);
            PantallaUtils.imprimirTexto("*        Bienvenido al Obligatorio1        *", true);
            PantallaUtils.imprimirTexto("*         Trabajo desarrollado por         *", true);
            PantallaUtils.imprimirTexto("*          Lourdes Ayala - 354398          *", true);
            PantallaUtils.imprimirTexto("*        Veronica Busiello - 212712        *", true);
            PantallaUtils.imprimirTexto("********************************************", true);
            
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
            PantallaUtils.imprimirTexto("Hubo un error! " + e.getMessage(), true);
        }
    }
    
}
