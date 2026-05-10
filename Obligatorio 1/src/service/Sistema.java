package service;

import java.util.ArrayList;
import java.util.Iterator;
import model.Tablero;
import model.Testeo;
import model.Tester;

public class Sistema {
    private Tablero tablero;
    private ArrayList<Tester> testers;
    private ArrayList<Testeo> testeos;

    //<editor-fold desc="Getters y Setters">
    public Tablero getTablero() {
        return tablero;
    }

    private ArrayList<Tester> getTesters() {
        return testers;
    }

    private ArrayList<Testeo> getTesteos() {
        return testeos;
    }
    
    private void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    private void setTesters(ArrayList<Tester> testers) {
        this.testers = testers;
    }

    private void setTesteos(ArrayList<Testeo> testeos) {
        this.testeos = testeos;
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Constructores">
    public Sistema(){
        this.setTablero(new Tablero());
        this.setTesters(new ArrayList<Tester>());
        this.setTesteos(new ArrayList<Testeo>());
    }
    //</editor-fold>
    
    public boolean registrarTester(String nombre, int edad, int experiencia){
        boolean registrado = false;
        if(encontrarTester(nombre) == null){
            Tester t = new Tester(nombre, edad, experiencia);
            testers.add(t);
            registrado = true;
        }
        return registrado;
    }
    
    public Tester encontrarTester(String nombre){
        Iterator<Tester> it = this.getTesters().iterator();
        boolean encontrado = false;
        Tester testerEncontrado = null;
        while(it.hasNext() && !encontrado){
            Tester testerAux = it.next();
            if(testerAux.getNombre().equalsIgnoreCase(nombre)){
                testerEncontrado = testerAux;
            }
        }
        return testerEncontrado;
    }
    
    public boolean registrarTablero(){
        this.getTablero().setDefault();
        return true;
    }
    
    public boolean registrarTablero(char[][] matriz){
        this.getTablero().setMatriz(matriz);
        return true;
    }
    
    public void registrarTesteo(int caso, Tester tester, String comentario){
        Testeo nuevoTesteo = new Testeo(caso, tester, comentario);
        this.getTesteos().add(nuevoTesteo);
        
        System.out.println(this.getTesteos());
    }
}
