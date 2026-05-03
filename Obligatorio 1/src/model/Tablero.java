/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author windows
 */
public class Tablero {
    private char[][] matriz;

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }
    
    public Tablero(){
        setDefault();
    }
    
    public Tablero(char[][] matriz){
        setMatriz(matriz);
    }
    
    public void setDefault(){
        char[][] defaultMat  = {
                {'V','V','N','N','V','V','N','N','V','V'},
                {'N','N','N','N','N','N','N','N','N','N'},
                {'N','V','V','V','V','V','N','N','V','N'},
                {'N','V','V','V','V','V','N','N','V','N'},
                {'N','V','V','V','V','V','N','N','V','N'},
                {'N','B','B','B','B','B','N','N','B','N'},
                {'B','V','V','V','V','V','B','B','V','B'},
                {'B','B','B','B','B','B','B','B','B','B'}
        };
        this.setMatriz(defaultMat);
    }
}
