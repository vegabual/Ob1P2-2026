package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Tester {
    private String nombre;
    private int edad;
    private int experiencia;
    private ArrayList<Testeo> testeos;

    //<editor-fold desc="Getters y Setters">
    //Getters
    /**
     * Getter para el nombre del tester
     * @return Nombre del tester
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Getter para la edad del tester
     * @return Edad del tester
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Getter para la experiencia del tester
     * @return Los años de experiencia del tester
     */
    public int getExperiencia() {
        return experiencia;
    }
    
    /**
     * Getter para el listado de testeos realizados por el tester
     * @return Listado de testeos
     */
    public ArrayList<Testeo> getTesteos() {
        return testeos;
    }
    
    //Setters
    /**
     * Setter para el nombre del tester
     * @param nombre Nombre del tester
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Setter para la edad del tester
     * @param edad Edad del tester
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Setter para experiencia del tester
     * @param experiencia Años de experiencia del tester
     */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    /**
     * Setter privado de los testeos realizados por el testers
     * @param testeos Listado de testeos
     */
    private void setTesteos(ArrayList<Testeo> testeos) {
        this.testeos = testeos;
    }
    //</editor-fold>
    
    //<editor-fold desc="Constructores">
    /**
     * Constructor para el tester
     * @param nombre Nombre del tester
     * @param edad Edad del tester
     * @param experiencia Años de experiencia del tester
     */
    public Tester(String nombre, int edad, int experiencia){
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setExperiencia(experiencia);
        this.setTesteos(new ArrayList<Testeo>());
    }
    //</editor-fold>
    
    /**
     * Agrega un testeo a la lista de testeos realizados por el tester
     * @param caso Caso a testear
     * @param comentario Comentario al correr el testeo
     * @param tablero Tablero previo a correr el testeo
     * @return Id del testeo
     */
    public int registrarTesteo(int caso, String comentario, char[][]tablero){
        char[][] tableroCopia = tablero.clone(); //Hace una copia de la matriz de tablero
        Testeo nuevoTesteo = new Testeo(caso, comentario, tableroCopia); //Crea el testeo
        this.getTesteos().add(nuevoTesteo); //Se agrega a la lista
        return nuevoTesteo.getId(); //Devuelve el ID autoincremental del testeo
    }
    
    /**
     * Actualiza el testeo con la matriz resultante si la misma es distinta a la original
     * @param idTesteo Id del testeo a actualizar
     * @param tablero Tablero resultante a guardar
     * @return Si fue posible actualizar la matriz. En caso de no ser necesario actualizarla tambien devuelve true.
     */
    public boolean actualizarTableroEnTesteo(int idTesteo, char[][] tablero){
        Testeo test = encontrarTesteo(idTesteo); //Busca el testeo
        boolean actualizacionExitosa = false;
        if(test != null){ //Si no encuentra el testeo provisto, devuelve false
            char[][] tableroCopia = tablero.clone(); //Copia la matriz del tablero
            actualizacionExitosa = true; //La accion fua exitosa se necesite cambiar la matriz o no
            if(!test.esIgualAOriginal(tablero)){ //Solo modifica la matriz si el caso hizo una modificacion en el tablero
                test.setTableroResultante(tableroCopia); //Actualizar tablero resultante
            }
        }
        return actualizacionExitosa; //Devolver resultado de la accion
    }
    
    /**
     * Encontrar un testeo Segun el ID del mismo
     * @param id ID del testeo a buscar
     * @return El testeo requerido. En caso de no encontrarlo, devuelve null
     */
    private Testeo encontrarTesteo(int id){
        Iterator<Testeo> it = this.getTesteos().iterator(); //Se define un iterador para recorrer la lista de testeo
        boolean encontrado = false; //Auxiliar para detener la ejecucion del while en caso de encontrar el testeo requerido
        Testeo testeoEncontrado = null; //Incializacion del testeo a devolver
        while(it.hasNext() && !encontrado){ //Mientras haya un valor siguiente, y no se encuentre el testeo
            Testeo testeoAux = it.next(); //Se toma el testeo actual
            if(testeoAux.getId() == id){ //Verifico si es el que me piden
                testeoEncontrado = testeoAux; 
            }
        }
        return testeoEncontrado;
    }
    
    /**
     * Devuelve un string con el listado de los testeos del tester
     * @return String con la lista de los testeos
     */
    public String testeosAListado(){
        Iterator<Testeo> it = this.getTesteos().iterator(); //Se define un iterador para recorrer la lista de testeos
        String testeosList = "Listado de testeos:"; //Titulo del listado
        while(it.hasNext()){ //Mientras hayan valores
            testeosList += "\n- " + it.next(); //Agrego un salto de linea, un guion y el testeo (que ya tiene definido un metodo toString)
        }
        return testeosList;
    }
    
    @Override
    public String toString(){
        String expToString;
        switch (this.getExperiencia()) { //Dependiendo cuanta experiencia tenga el tester cambia el texto a mostrar
            case 0 -> expToString = " Sin experiencia previa";
            case 1 -> expToString = " 1 año de experiencia";
            default -> expToString = this.getExperiencia() + " años de experiencia";
        }
        
        String edadToString;
        switch (this.getEdad()) { //Dependiendo la edad del tester cambia el texto a mostrar
            case 0 -> edadToString = " Aun no cumple el año";
            case 1 -> edadToString = " 1 año";
            default -> edadToString = this.getEdad() + " años";
        }
        
        return "Tester " + this.getNombre() + " - "  + edadToString +  " - " + expToString;
    }
    
}
