package model;

import java.util.ArrayList;

public class Tester {
    private String nombre;
    private int edad;
    private int experiencia;

    //<editor-fold desc="Getters y Setters">
    //Getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getExperiencia() {
        return experiencia;
    }
    
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    //</editor-fold>
    
    //<editor-fold desc="Constructores">
    public Tester(String nombre, int edad, int experiencia){
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setExperiencia(experiencia);
    }
    //</editor-fold>
    
    @Override
    public String toString(){
        String expToString;
        switch (this.getExperiencia()) {
            case 0 -> expToString = " Sin experiencia previa";
            case 1 -> expToString = this.getExperiencia() + " 1 año de experiencia";
            default -> expToString = this.getExperiencia() + " años de experiencia";
        }
        
        String edadToString;
        switch (this.getEdad()) {
            case 0 -> edadToString = " Aun no cumple el año";
            case 1 -> edadToString = this.getEdad() + " 1 año";
            default -> edadToString = this.getEdad() + " años";
        }
        
        return "Tester " + this.getNombre() + " - "  
                + edadToString +  " - " + expToString;
    }
    
}
