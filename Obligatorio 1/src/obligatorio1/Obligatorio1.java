package obligatorio1;

import service.Menu;
import service.Sistema;
import util.PantallaUtils;

public class Obligatorio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            //Imprimir cabezal del programa, dando la bienvenida y detallando los nombres de las desarrolladoras
            PantallaUtils.imprimirTexto("********************************************", true);
            PantallaUtils.imprimirTexto("*        Bienvenido al Obligatorio1        *", true);
            PantallaUtils.imprimirTexto("*         Trabajo desarrollado por         *", true);
            PantallaUtils.imprimirTexto("*          Lourdes Ayala - 354398          *", true);
            PantallaUtils.imprimirTexto("*        Veronica Busiello - 212712        *", true);
            PantallaUtils.imprimirTexto("********************************************", true);
            
            // Mostrar el menu
            Menu.mostrarMenu();
        }
        catch(Exception e){
            //En caso de un error, se le hace un catch, y se imprime el mensaje que da la excepcion
            PantallaUtils.imprimirTexto("Hubo un error! " + e.getMessage(), true);
        }
    }
    
}
