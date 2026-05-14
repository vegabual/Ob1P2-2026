package service;

import enums.Sentido;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import model.Tablero;
import model.Testeo;
import model.Tester;

public class Sistema {
    private Tablero tablero;
    private ArrayList<Tester> testers;

    //<editor-fold desc="Getters y Setters">
    //Getters
    /**
     * Getter del tablero de juego
     * @return Tablero de juego
     */
    public Tablero getTablero() {
        return tablero;
    }
    
    /**
     * Getter privado del listado de testers registrados
     * @return Lista de testers registrados
     */
    private ArrayList<Tester> getTesters() {
        return testers;
    }
    
    //Setters
    /**
     * Setter privado del tablero de juego
     * @param tablero Tablero de juego
     */
    private void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    /**
     * Setter privado del listado de testers registrados
     * @param testers Lista de testers registrados
     */
    private void setTesters(ArrayList<Tester> testers) {
        this.testers = testers;
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Constructores">
    /**
     * Constructor de sistema que incializa el tablero con las posiciones por defecto, e incializa el ArrayList de testers
     */
    public Sistema(){
        this.setTablero(new Tablero());
        this.setTesters(new ArrayList<Tester>());
    }
    //</editor-fold>
    
    /**
     * Registrar un tester en el listado con nombre unico.
     * @param nombre Nombre del tester
     * @param edad Edad del tester
     * @param experiencia Años de experiencia del tester
     * @return Si el registro fue exitoso
     */
    public boolean registrarTester(String nombre, int edad, int experiencia){
        boolean registrado = false;
        if(encontrarTester(nombre) == null){ //Si no hay un tester con el nombre provisto
            Tester t = new Tester(nombre, edad, experiencia); //Crear tester
            testers.add(t); //Agregarlo al listado
            registrado = true; //Marcar la accion como exitosa
        }
        return registrado;
    }
    
    /**
     * Encontrar un tester dado un nombre
     * @param nombre Nombre del tester
     * @return Tester con el nombre provisto. Si no se encuentra tester, devuelve null
     */
    public Tester encontrarTester(String nombre){
        Iterator<Tester> it = this.getTesters().iterator(); //Iterador para recorrer el ArrayList de testers
        boolean encontrado = false; //Auxiliar para detener la ejecucion del while en caso de encontrar el tester requerido
        Tester testerEncontrado = null; //Se incializa el tester a devolver
        while(it.hasNext() && !encontrado){ //Mientras haya elementos y no se haya encontrado el tester
            Tester testerAux = it.next(); //Se toma el tester actual
            if(testerAux.getNombre().equalsIgnoreCase(nombre)){ //Verifico si tiene el nombre que me piden, sin tomar en cuenta las mayusculas y minusculas
                testerEncontrado = testerAux;
            }
        }
        return testerEncontrado;
    }
    
    /**
     * Registrar tablero por defecto
     */
    public void registrarTablero(){
        this.getTablero().setDefault();
    }
    
    /**
     * Registrar tablero a partir de una matriz dada
     * @param matriz Matriz de tablero
     */
    public void registrarTablero(char[][] matriz){
        this.getTablero().setPosicionesFichas(matriz);
    }
    
    /**
     * Registrar un testeo con id autoincremental y el tablero actual
     * @param caso Caso a testear
     * @param tester Tester que ejecutara el caso
     * @param comentario Comentario al registrar el testeo
     * @return 
     */
    public int registrarTesteo(int caso, Tester tester, String comentario){
        char[][] tablero = this.getTablero().getPosicionesFichas().clone(); //Hago una copia de la matriz del tablero actual
        return tester.registrarTesteo(caso, comentario, tablero); //Registrar testeo
    }
    
    /**
     * Actualizar el tablero resultante de un testeo con el tablero actual. 
     * Si la matriz resultante es igual a la original, se mantiene en null
     * @param idTesteo Id del testeo a actualizar
     * @param tester Tester que ejecuto el test
     * @return Si fue exitosa la accion
     */
    public boolean actualizarTableroEnTesteo(int idTesteo, Tester tester){
        return tester.actualizarTableroEnTesteo(idTesteo, this.getTablero().getPosicionesFichas());
    }
    
    /**
     * Devuelve un listado con los testers registrados
     * @return String con la lista de los testers
     */
    public String testersAListado(ArrayList<Tester> testers){
        if(testers == null){ //Si no se pasa un listado de testers, es porque se quiere imprimir todos los testers registrados
            testers = this.getTesters();
        }
        Iterator<Tester> it = testers.iterator(); //Se define un iterador para recorrer la lista de testers
        String testerList = "";
        if(it.hasNext()){ //Primer valor
            testerList += "  " + it.next(); //Agrego la sangria y el testeo (que ya tiene definido un metodo toString)
        }
        
        while(it.hasNext()){ //Mientras hayan valores
            testerList += "\n  " + it.next(); //Agrego un salto de linea y el testeo (que ya tiene definido un metodo toString)
        }
        return testerList;
    }
    
    /**
     * Dado un tester, se devuelve un string con los testeos realizados
     * @param tester Tester cuyos testeos se van a listar
     * @return String con la lista de los testeos
     */
    public String testeosAListado(Tester tester){
        return tester.testeosAListado();
    }
    
    /**
     * Valida si hay algun tester registrados. Util para funcionalidades que necesitan asociarse a un tester.
     * @return Si hay testers registrados en el sistema
     */
    public boolean hayTestersCargados(){
        return this.getTesters().size() > 0;
    }
    
    /**
     * Devuelve el listado con el/los testers con mas tests ejecutados
     * @return String con el listado y su titulo
     */
    public String listadoTestersConMasTests(){
        ArrayList<Tester> testersCopia = (ArrayList<Tester>) this.getTesters().clone(); //Se hace una copia del listado, para no cambiar el orden original
        String listadoTesters = "Testers con mas tests realizados "; //Incializo el string con el titulo
        
        testersCopia.sort(new CriterioCantTesteosDesc()); //Se ordena con el criterio de ordenacion provisto
        
        Iterator<Tester> it = testersCopia.iterator(); //Se define un iterador para recorrer la lista de testers
        boolean terminoMayores = false; //Variable auxiliar para marcar que se terminaron de recorrer los tester con mayor cantidad de testeos
        int mayorCantidadTesteos = 0; //Variable auxiliar para guardar cual es el mayor numero de testeos realizados por un tester
        
        if(it.hasNext()){ //Primer item de la lista (el que tiene mas testeos por ordenamiento)
            mayorCantidadTesteos = testersCopia.get(0).getTesteos().size(); //Guarda cuanto es la mayor cantidad de testeos
            
            if(mayorCantidadTesteos == 1){ //Chequeo si necesito usar el plural de test
                listadoTesters += "(1 test):"; //Agrega que el maximo de tests corridos por tester es 1
            } else{
                listadoTesters += "(" + mayorCantidadTesteos + " tests):"; //Agrega el valor de cuantos testeos son los maximos corridos por tester en el titulo.
            }
        } else{
            terminoMayores = true;
        }
        while(it.hasNext() && !terminoMayores){ //Mientras sigan habiendo items, y no haya terminado de recorrer los testers con mayor cantidad de tests corridos
            Tester t = it.next(); //Guarda un puntero al siguiente item
            if(t.getTesteos().size() == mayorCantidadTesteos){ //Si tiene tantos testeos como el que tiene mas
                listadoTesters += "\n  " + t.toString(); //Se agrega al listado
            } else{
                terminoMayores = true; //Sino, es que ya se terminaron (pues la lista esta ordenada)
            }
        }
        return listadoTesters;
    }
    
    /**
     * Devuelve el listado con el/los testers que aun no han ejecutado tests
     * @return String con el listado y su titulo
     */
    public String listadoTestersConMenosTests(){
        String listadoTesters = ""; //Inicializo el string;
        int cantidadTesters = 0; //Contador para poder dar la informacion en el titulo
        Iterator<Tester> it = this.getTesters().iterator(); //Se define un iterador para recorrer la lista de testers
        
        while(it.hasNext()){ //Mientras hayan items en la lista
            Tester t = it.next(); //Guarda un puntero al siguiente item
            if(t.getTesteos().size() == 0){ //Si no tiene testeos, lo guarda
                cantidadTesters++; //Aumento en 1 el contador
                listadoTesters += "\n  " + t.toString(); //Se agrega el tester al listado
            } 
        }
        String titulo = "Testers que aun no han realizado tests (" + cantidadTesters + " testers): "; //Incializo el titulo asumiendo que hay multiples testers que no han corrido pruebas
        
        if(cantidadTesters == 0){
            titulo = "No hay testers que no hayan corrido pruebas!"; //Si ningun tester corrio pruebas, listadoTesters será vacio, por lo que no hay testers a mostrar
        } else if(cantidadTesters == 1) {
            titulo = "Testers que aun no han realizado tests (1 tester): "; //Si solo 1 tester corrio pruebas, arreglo el titulo para usar el singular
        }
        
        listadoTesters = titulo + listadoTesters; //Se agrega el titulo al comienzo del string, con la cantidad de testers sin testeos
        
        return listadoTesters;
    }
    
    /**
     * Inner class para agregar un criterio de comparacion para los testers.
     */
    private class CriterioCantTesteosDesc implements Comparator<Tester>{
        /**
         * Compara 2 Tester
         * @param tester1 Tester a comparar
         * @param tester2 Tester a comparar
         * @return La diferencia de testeos entre tester2 y tester1
         */
        @Override
        public int compare(Tester tester1, Tester tester2){
            return tester2.getTesteos().size() - tester1.getTesteos().size();
        }
    }
    
    //<editor-fold desc="Funcionalidades de juego">
    /**
     * Contar fichas del tablero
     * @param color Color de fichas a contar
     * @return Cantidad de fichas del color
     */
    public int contarFichas(char color){
        return this.getTablero().contarFichas(color);
    }
    
    /**
     * Validar movimiento individual, en caso de ser posible realizarse, se modifican la posicion de la ficha.
     * El movimiento implica desplazar la ficha desde la posicion dada en el sentido dada la cantidad de posiciones indicada. 
     * El camino debe estar libre completamente. La posicion final puede ser tanto vacia como del color contrincante, y en ese caso se "come" la ficha.
     * @param color Color de las fichas a mover
     * @param sentido Sentido en el que se movera la ficha
     * @param fila Fila de la ficha a mover
     * @param columna Columna de la ficha a mover
     * @param pasos Cantidad de pasos a moverse
     * @return Si el movimiento es legal y se realizo
     */
    public boolean validarMovimientoIndividual(char color, Sentido sentido, int fila, int columna, int pasos){
        return this.getTablero().validarMovimientoIndividual(color, sentido, fila, columna, pasos);
    }
    
    /**
     * Validar movimiento de grupo, en caso de ser posible realizarse, se modifican las posiciones de las fichas.
     * El movimiento implica desplazar todo el grupo (de un unico color dado) desde la posicion dada y del tamaño dado alineados segun la forma dada,
     * en el sentido y con la cantidad de posiciones indicada. El camino debe estar libre completamente y las posiciones finales vacias
     * @param color Color de las fichas a mover
     * @param forma Forma del grupo (H o V)
     * @param sentido Sentido en el que se movera el grupo
     * @param fila Fila de la primera ficha del grupo
     * @param columna Columna de la primera ficha del grupo
     * @param tamanio Tamaño del grupo
     * @param pasos Cantidad de pasos a moverse
     * @return Si el movimiento es legal, y se realizo
     */
    public boolean validarMovimientoEnGrupo(char color, char forma,  Sentido sentido, int fila, int columna, int tamanio, int pasos){
        return this.getTablero().validarMovimientoGrupo(color, forma, sentido, fila, columna, tamanio, pasos);
    }
    
    /**
     * Se retorna el string con el formato correspondiente al tablero
     * @return el string con el formato del tablero, de forma que al imprimir en pantalla se ve acorde a lo requerido en la letra
     */
    public String prepararTablero(){
        return this.getTablero().prepararTablero();
    }
    
    /**
     * Verifica que todas las fichas esten conectadas entre si. Esto es que cada ficha sea vecina horizontal vertical o diagonal a otra del mismo color, formando un unico grupo.
     * @param color Color de las fichas a revisar
     * @return Si todas las fichas forman un unico grupo conectado
     */
    public boolean verificarConexion(char ficha){
        return this.getTablero().verificarConexion(ficha);
    }
    //</editor-fold>
}
