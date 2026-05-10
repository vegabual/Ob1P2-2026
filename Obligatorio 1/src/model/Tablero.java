package model;

import enums.Sentido;

public class Tablero {
    private char[][] matriz;
    
    //<editor-fold desc="Getters y Setters">
    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }
    //</editor-fold>
    
    //<editor-fold desc="Constructores">
    public Tablero(){
        setDefault();
    }
    
    public Tablero(char[][] matriz){
        setMatriz(matriz);
    }
    //</editor-fold>
    
    public void setDefault(){
        char[][] defaultMat  = {
                {'V','V','N','N','V','V','N','N','V','V'},
                {'N','N','N','N','N','N','N','N','N','N'},
                {'N','N','V','V','N','N','V','V','N','N'},
                {'V','V','V','V','V','V','V','V','V','V'},
                {'V','V','V','V','V','V','V','V','V','V'},
                {'B','B','V','V','B','B','V','V','B','B'},
                {'B','B','B','B','B','B','B','B','B','B'},
                {'V','V','B','B','V','V','B','B','V','V'}
        };
        this.setMatriz(defaultMat);
    }
    
    public int contarFichas(char color){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean validarMovimientoIndividual(char color, char forma, Sentido sentido, int fila, int columna, int tamanio, int pasos){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean validarMovimientoEnGrupo(char color, Sentido sentido, int fila, int columna, int pasos){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Se retorna el string con el formato correspondiente al tablero
     * @return el string con el formato del tablero, de forma que al imprimir en pantalla se ve acorde a lo requerido en la letra
     */
    public String prepararTablero(){
        String toStr = "";
        
        for(int fila = 0; fila < this.getMatriz().length;fila++){//Recorro las filas
            toStr += "+"; //Ingreso el + de la esquina superior izquierda
            for(int col = 0; col<this.getMatriz()[fila].length;col++){ //por cada columna ingreso el borde superior de la celda
                toStr += "---+"; //Borde superior
            }
            toStr += "\n|";//Ingreso el salto de linea y el borde izquierdo de la matriz
            for(int col = 0; col<this.getMatriz()[fila].length;col++){ //Recorro las columnas
                if( this.getMatriz()[fila][col] == 'V'){ //Si es V, dejo el espacio vacio
                    toStr += "   |";
                } else{ //Sino, ingreso la ficha, y el borde derecho del bloque
                    toStr += " " + this.getMatriz()[fila][col] + " ";
                    toStr += "|";
                }
            }
            toStr += "\n"; //Ingreso el salto de linea al terminar de recorrer las columas y pasar a la siguiente fila
        }
        //Una vez termino de ingresar las fichas, ingreso el borde inferior
        toStr += "+"; //Ingreso el + de la esquina inferior izquierda
        for(int col = 0; col < this.getMatriz()[0].length;col++){ //Por cada columna, ingreso el borde inferior de la celda
            toStr += "---+"; //Borde inferior
        }
        toStr += "\n"; //Salto de linea al terminar de ingresar el tablero
        
        return toStr;
    }
    
    public boolean verificarConexion(char ficha){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
