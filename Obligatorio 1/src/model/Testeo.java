package model;

public class Testeo {
    private int id;
    private int caso;
    private String comentario;
    private char[][] tableroOriginal;
    private char[][] tableroResultante;
    private static int UltimoId = 0; //Almacena el ultimo ID usado para el auto incremental

    //<editor-fold desc="Getters y Setters">
    //Getters
    /**
     * Getter para el ID del testeo
     * @return El ID del testeo
     */
    public int getId() {
        return id;
    }

    /**
     * Getter para el numero de caso probado
     * @return El numero de caso del testeo
     */
    public int getCaso() {
        return caso;
    }

    /**
     * Getter para el comentario provisto al correr el testeo
     * @return El comentario dado
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Getter para el tablero previo a correr el testeo
     * @return Lista de posiciones al comenzar el testeo
     */
    public char[][] getTableroOriginal() {
        return tableroOriginal;
    }

    /**
     * Getter para el tablero posterior a correr el testeo
     * @return Lista de posiciones al finalizar el testeo
     */
    public char[][] getTableroResultante() {
        return tableroResultante;
    }

    //Setters
    /**
     * Setter privado para el ID del testeo
     * @param id ID del testeo 
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Getter privado para el numero de caso probado
     * @param caso El numero de caso del testeo
     */
    private void setCaso(int caso) {
        this.caso = caso;
    }

    /**
     * Setter del comentario provisto al correr el testeo
     * @param comentario El comentario al respecto del testeo
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Setter privado para el tablero previo a correr el testeo
     * @param tableroOriginal Lista de posiciones al comenzar el testeo
     */
    private void setTableroOriginal(char[][] tableroOriginal) {
        this.tableroOriginal = tableroOriginal;
    }

    /**
     * Setter para el tablero posterior a correr el testeo
     * @param tableroResultante Lista de posiciones al finalizar el testeo
     */
    public void setTableroResultante(char[][] tableroResultante) {
        this.tableroResultante = tableroResultante;
    }
    
    //</editor-fold>
    
    //<editor-fold desc="Constructores">
    /**
     * Constructor para el testeo, con un ID autoincremental
     * @param caso Caso a testear
     * @param comentario Comentario sobre el testeo
     * @param tablero Tablero previo a correr el testeo
     */
    public Testeo(int caso, String comentario, char[][] tablero){
        this.setId(++UltimoId); //Sumar uno a UltimoID y asignar al testeo que se esta creando
        this.setCaso(caso);
        this.setComentario(comentario);
        this.setTableroOriginal(tablero);
    }
    //</editor-fold>
    
    /**
     * Valida si el tablero provisto es igual al tablero original
     * @param tablero Tablero actual a comparar con el tablero original
     * @return Si el tablero provisto es igual al original
     */
    public boolean esIgualAOriginal(char[][] tablero){
        boolean sonIguales = true;
        if (this.getTableroOriginal() != null && tablero != null){ //Si ninguna es vacia
            for(int fila = 0; fila < tablero.length; fila++){
                for(int col = 0; col < tablero[0].length && sonIguales; col++){
                    sonIguales = tablero[fila][col] == this.getTableroOriginal()[fila][col];
                }
            }
        }
        return sonIguales;
    }
    
    @Override
    public String toString(){
        return "Testeo ID: " + this.getId() + "\n  Caso probado: " + this.getCaso() + "\n  Comentarios: " + this.getComentario();
    }
}
