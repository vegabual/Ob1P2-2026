/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Tablero;

/**
 *
 * @author windows
 */
public class Sistema {
    public Tablero tablero;
    public TesterManager testers;

    public Tablero getTablero() {
        return tablero;
    }

    public TesterManager getTesters() {
        return testers;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    public void setTesters(TesterManager testers) {
        this.testers = testers;
    }
    
    public Sistema(){
        this.setTablero(new Tablero());
        this.setTesters(new TesterManager());
    }
    
}
