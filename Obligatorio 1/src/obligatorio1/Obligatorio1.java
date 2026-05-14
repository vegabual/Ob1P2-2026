package obligatorio1;

import enums.Color;
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
            PantallaUtils.imprimirTexto("********************************************", Color.Purpura, true);
            PantallaUtils.imprimirTexto(PantallaUtils.stringAColor("*", Color.Purpura) + "       Bienvenido al Obligatorio 1        " + PantallaUtils.stringAColor("*", Color.Purpura), true);
            PantallaUtils.imprimirTexto(PantallaUtils.stringAColor("*", Color.Purpura) + "         Trabajo desarrollado por         " + PantallaUtils.stringAColor("*", Color.Purpura), true);
            PantallaUtils.imprimirTexto(PantallaUtils.stringAColor("*", Color.Purpura) + "          Lourdes Ayala - 354398          " + PantallaUtils.stringAColor("*", Color.Purpura), true);
            PantallaUtils.imprimirTexto(PantallaUtils.stringAColor("*", Color.Purpura) + "        Veronica Busiello - 212712        " + PantallaUtils.stringAColor("*", Color.Purpura), true);
            PantallaUtils.imprimirTexto("********************************************", Color.Purpura, true);
            
            // Mostrar el menu
            Menu.mostrarMenu();
        }
        catch(Exception e){
            //En caso de un error, se le hace un catch, y se imprime el mensaje que da la excepcion
            PantallaUtils.imprimirTexto("Hubo un error! " + e.getMessage(), true);
        }
    }
    
}
