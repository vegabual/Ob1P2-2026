/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author windows
 */
public class Tester {
    private String nombre;
    private int edad;
    private int experiencia;

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
    
    //Constructor
    public Tester(String nombre, int edad, int experiencia){
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setExperiencia(experiencia);
    }
}
