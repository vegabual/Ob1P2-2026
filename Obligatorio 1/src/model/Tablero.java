package model;

import enums.Sentido;

public class Tablero {
    private char[][] posicionesFichas;
    
    //<editor-fold desc="Getters y Setters">
    /**
     * Getter para la matriz de posiciones de las fichas
     * @return Matriz del tablero representando las posiciones donde se encuentran las fichas
     */
    public char[][] getPosicionesFichas() {
        return posicionesFichas;
    }
    
    /**
     * Setter para la matriz de posiciones de las fichas
     * @param matriz Matriz del tablero representando las posiciones donde se encuentran las fichas
     */
    public void setPosicionesFichas(char[][] matriz) {
        this.posicionesFichas = matriz.clone(); //Se asigna una copia de la matriz provista a las posiciones ingresadas
    }
    //</editor-fold>
    
    //<editor-fold desc="Constructores">
    /**
     * Constructor vacio
     */
    public Tablero(){
        setDefault();
    }
    
    /**
     * Constructor a partir de una matriz dada
     * @param posicionesFichas Matriz de posiciones de las fichas
     */
    public Tablero(char[][] posicionesFichas){
        setPosicionesFichas(posicionesFichas);
    }
    //</editor-fold>
    
    /**
     * Volver las piezas a su posicion por defecto:
     * V V N N V V N N V V
     * N N N N N N N N N N
     * N N V V N N V V N N
     * V V V V V V V V V V
     * V V V V V V V V V V
     * B B V V B B V V B B
     * B B B B B B B B B B
     * V V B B V V B B V V
     */
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
        this.setPosicionesFichas(defaultMat);
    }
    
    /**
     * Caso 1 - Cuenta la cantidad de piezas de un color dado en el tablero
     * @param color Color de pieza a contar
     * @return Cantidad de piezas del color en el tablero
     */
    public int contarFichas(char color){
        int contador = 0; //para ir contando las fichas

        for(int i = 0; i < this.getPosicionesFichas().length; i++) { //filas
            for(int j = 0; j < this.getPosicionesFichas()[i].length; j++) { //columnas
                if(this.getPosicionesFichas()[i][j] == color) { // compara el lugar de la matriz con el color dado
                    contador++; //si son iguales suma 1 al contador
                }
            }
        }

        return contador; //devuelve el contador que es un int
    }
        
    /**
     * Caso 2 - Validar movimiento individual, en caso de ser posible realizarse, se modifican la posicion de la ficha.
     * El movimiento implica desplazar la ficha desde la posicion dada en el sentido dada la cantidad de posiciones indicada. 
     * El camino debe estar libre completamente. La posicion final puede ser tanto vacia como del color contrincante, y en ese caso se "come" la ficha.
     * @param color Color de las fichas a mover
     * @param sentido Sentido en el que se movera la ficha
     * @param fila Fila de la ficha a mover
     * @param columna Columna de la ficha a mover
     * @param pasos Cantidad de pasos a moverse
     * @return Si el movimiento es legal, y se realizo
     */
    public boolean validarMovimientoIndividual(char color, Sentido sentido, int fila, int columna, int pasos) {
        boolean movimientoValido = true;
        //verificar la ficha dada
        if(this.getPosicionesFichas()[fila][columna] != color) { //Si el char que hay en el lugar pedido en la matriz no es igual al color dado por usuario
            return false; //devuelvo false porque la ficha no coincide
        }

        //si la ficha es correcta
        //verificar el sentido
        if(color == 'N') { //Si el color es negro
            if(sentido.equals(Sentido.N) || sentido.equals(Sentido.NE) || sentido.equals(Sentido.NO)) {
                movimientoValido =  false;  //devuevle falso si es Negro y el sentido pedido es Norte
            }
        } else if(color == 'B') { //Si el color es blanco
            if(sentido.equals(Sentido.S) || sentido.equals(Sentido.SE) || sentido.equals(Sentido.SO)) {
                movimientoValido = false; //devuevle falso si es Blanco y el sentido pedido es Sur
            }
        }
        
        movimientoValido &= validarPasosEnEspaciosVacios(color, sentido, fila, columna, pasos, false);
                
        if(movimientoValido){ //Si no se paso para afuera de la matriz, chequeo que los pasos sean correctos
            int df = sentidoADireccionFila(sentido);
            int dc = sentidoADireccionColumna(sentido);

            int nuevaFila = fila + (df * pasos); //a la fila dada le sumo los pasos pedidos multiplicados por la direccion del movimiento
            int nuevaColumna = columna + (dc * pasos); //idem a las filas
            
            //verificar si se puede hacer el movimiento
            for(int i = 1; i < pasos && movimientoValido; i++) { //verifico segun los pasos dados

                int f = fila + (df * i); //a la fila dada le sumo los pasos pedidos multiplicados por la direccion del movimiento
                int c = columna + (dc * i); //idem a las filas

                if(this.getPosicionesFichas()[f][c] != 'V') { //si el espacio final no es vacio
                    movimientoValido =  false;
                }
            }

            if(this.getPosicionesFichas()[nuevaFila][nuevaColumna] == color) { //si el espacio final tiene el mismo color
                movimientoValido =  false;
            }

            if(movimientoValido){
                //guardar movimiento
                this.getPosicionesFichas()[nuevaFila][nuevaColumna] = color; //guardo la que se movio
                this.getPosicionesFichas()[fila][columna] = 'V'; //pongo vacio el lugar de donde salio
            }
        }

        return movimientoValido; //devuelve tru si se puede hacer el movimiento
        
    }//fin caso 2

    /**
     * Metodo auxiliar para obtener un int que describe como se deben mover los indices de la fila segun el sentido dado.
     * @param sentido Sentido del movimiento
     * @return Si el movimiento es hacia abajo devuelve 1, si es hacia la arriba devuelve -1
     */
    private int sentidoADireccionFila(Sentido sentido){
        int direccionFila = 0; 

        //para la direccion del movimiento
        //segun lo que pida, perimero se hace esta cuenta y mas abajo se suman estos a las filas y columnas pedidas
        switch(sentido) { //uso esto para no repetir mucho codigo
            case Sentido.N:
                direccionFila = -1; //resto 1 si va al norte
                break;
            case Sentido.S:
                direccionFila = 1; //sumo 1 si va al sur
                break;
            case Sentido.E:
                direccionFila = 0; //No se mueve en sentido de filas
                break;
            case Sentido.O:
                direccionFila = 0; //No se mueve en sentido de filas
                break;
            case Sentido.NE:
                direccionFila = -1; //resto 1 si va al norte
                break;
            case Sentido.NO:
                direccionFila = -1; //resto 1  si va al norte
                break;
            case Sentido.SE:
                direccionFila = 1; //sumo 1 si va al sur
                break;
            case sentido.SO:
                direccionFila = 1; //sumo uno si va al sur
                break;
        }
        return direccionFila;
    }
    
    /**
     * Metodo auxiliar para obtener un int que describe como se deben mover los indices de la columna segun el sentido dado.
     * @param sentido Sentido del movimiento
     * @return Si el movimiento es hacia la derecha devuelve 1, si es hacia la izquierda devuelve -1
     */
    private int sentidoADireccionColumna(Sentido sentido){
        int direccionColumna = 0; //direccion columna

        //para la direccion del movimiento
        //segun lo que pida, perimero se hace esta cuenta y mas abajo se suman estos a las filas y columnas pedidas
        switch(sentido) { //uso esto para no repetir mucho codigo
            case Sentido.N:
                direccionColumna = 0; //No se mueve en sentido de columnas
                break;
            case Sentido.S:
                direccionColumna = 0; //No se mueve en sentido de columnas
                break;
            case Sentido.E:
                direccionColumna = 1; //sumo 1 si va al este
                break;
            case Sentido.O:
                direccionColumna = -1; //resto 1 si va al oeste
                break;
            case Sentido.NE:
                direccionColumna = 1; //sumo 1 si va al este
                break;
            case Sentido.NO:
                direccionColumna = -1; //resto 1 si va al oeste
                break;
            case Sentido.SE:
                direccionColumna = 1; //sumo 1 si va al este
                break;
            case sentido.SO:
                direccionColumna = -1; //resto 1 si va al oeste
                break;
        }
        return direccionColumna;
    }
    
    /**
     * Caso 3 - Validar movimiento de grupo, en caso de ser posible realizarse, se modifican las posiciones de las fichas.
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
    public boolean validarMovimientoGrupo(char color, char forma, Sentido sentido, int fila, int columna, int tamanio, int pasos) {
        boolean movimientoValido = true;
        
        //Chequeo que el sentido sea valido para movimiento en grupo
        if(sentido.equals(Sentido.NO) || sentido.equals(Sentido.NE) || sentido.equals(Sentido.SO) || sentido.equals(Sentido.SE)){//Movimientos diagonales no aceptados
                movimientoValido = false; //devuelve false
        }
        
        //Chequeo sentido para la forma de grupo
        if(forma == 'H') { //si es horizontal solo se mueve en sentido vertical
            if(!(sentido.equals(Sentido.N) || sentido.equals(Sentido.S))) { //si no es norte o sur
                movimientoValido = false; //devuelve false
            }
        } if(forma =='V') { //si es vertical solo se mueve en sentido horizontal
            if(!(sentido.equals(Sentido.E) || sentido.equals(Sentido.O))) { //si no es este u oeste
                movimientoValido = false; //devuelve false
            }
        }

        //Chequeo sentido para el color del grupo
        if(color == 'B' && sentido.equals(Sentido.S)) { //si el color es blanco y el sentido es sur
            movimientoValido = false; //devuelve false
        }
        else if(color =='N' && sentido.equals(Sentido.N)) { //si el color es negro y el sentido es norte
            movimientoValido = false; //devuelve false
        }
        
        //Chequeo que el grupo es todo del color dado
        movimientoValido &= validarGrupo(tamanio, fila, columna, forma, color); //Si movimientoValido es false, no evalua validarGrupo. movimientoValido = movimientoValido && validarGrupo(tamanio, fila, columna, forma, color);

        //Chequeo que los pasos que deben hacer las fichas estan libres
        for(int i = 0; i < tamanio && movimientoValido; i++) { //segun el tamaño dado
            int filaFicha = fila;
            int columnaFicha = columna;
            if(forma == 'H') {
                columnaFicha += i; //le sumo a columnaFicha la i y lo guardo en columnaFicha, para columnas
            } else {
                filaFicha += i; //le sumo a filaFicha la i y lo guardo en filaFicha, para filas
            }
            movimientoValido = validarPasosEnEspaciosVacios(color, sentido, filaFicha, columnaFicha, pasos, true);
        }
        
        //Si cumple todos los requerimientos previos, mover fichas
        if(movimientoValido){
            int df = sentidoADireccionFila(sentido);
            int dc = sentidoADireccionColumna(sentido);
            
            //Vacio los lugares originales para mover
            for(int i = 0; i < tamanio; i++) { //segun el tamaño dado

                int f = fila;
                int c = columna;

                if(forma == 'H') {
                    c += i; //le sumo a a c la i y lo guardo en c, para columnas
                } else {
                    f += i; //le sumo a f la i y lo guardo en f, para filas
                }

                this.getPosicionesFichas()[f][c] = 'V'; //pongo en V el lugar de donde salio la fiucha
            }

            //guardo el grupo en su lugar final
            for(int i = 0; i < tamanio; i++) {

                int f = fila;
                int c = columna;

                if(forma == 'H') { //si es horizontal
                    c += i; //le sumo a a c la i y lo guardo en c, para columnas
                } else {
                    f += i; //le sumo a f la i y lo guardo en f, para filas
                }

                int nf = f + (df * pasos); //a las filas les sumo la direccion multiplicado por los pasos
                int nc = c + (dc * pasos); //idem a filas

                this.getPosicionesFichas()[nf][nc] = color;
            }
        }
        
        return movimientoValido; //devuelve movimiento valido
        
    }//fin caso 3
    
    /**
     * Metodo auxiliar que valida si un grupo consiste de fichas del mismo color
     * @param tamanio Tamaño del grupo
     * @param fila Fila de la primera ficha del grupo
     * @param columna Columna de la primera ficha del grupo
     * @param forma Forma del grupo (H o V)
     * @param color Color del grupo
     * @return Si el grupo consiste de fichas del mismo color
     */
    private boolean validarGrupo(int tamanio, int fila, int columna, char forma, char color){
        boolean grupoValido = true;
        //verificar que el grupo sea valido para la H
        for(int i = 0; i < tamanio; i++) { //segun el tamaño dado, voy verificando lugar a lugar sumandole i a las filas y columnas

            int filaFicha = fila;
            int columnaFicha = columna;

            if(forma == 'H') { //si es horizontal
                columnaFicha += i; //le sumo a a c la i y lo guardo en columnaFicha, para columnas
            }
            else { //entonces es vertical
                filaFicha += i; //le sumo a f la i y lo guardo en filaFicha, para filas
            }
            
            //verifico los bordes
            if (filaFicha < 0 || filaFicha >= this.getPosicionesFichas().length || columnaFicha < 0 || columnaFicha >= this.getPosicionesFichas()[0].length) { //si se pasa de los bordes
                grupoValido = false; //devuelve false
            }

            if(this.getPosicionesFichas()[filaFicha][columnaFicha] != color) { //si la posicion en la matriz es distinto del color dado, ya que todos deben ser iguales
                grupoValido = false; //devuelve false
            }
        }
        return grupoValido;
    }
    
    /**
     * Metodo auxiliar que dado un color, sentido, fila, columna y cantidad de pasos, valida que si camino esta despejado para hacer el movimiento
     * @param color Color a mover
     * @param sentido Sentido en el que se movera la ficha
     * @param fila Fila donde se encuentra la ficha
     * @param columna Columna donde se encuentra la ficha
     * @param pasos Cantidad de pasos a moverse
     * @param puedeTerminarEnColor Booleano que determina si el movimiento debe terminar en un espacio vacio
     * @return Si el movimiento es valido o no, SIN MODIFICAR LAS POSICIONES DE LAS FICHAS
     */
    private boolean validarPasosEnEspaciosVacios(char color, Sentido sentido, int fila, int columna, int pasos, boolean debeTerminarVacio){
        boolean movimientoValido = true;
        
        int df = sentidoADireccionFila(sentido);
        int dc = sentidoADireccionColumna(sentido);

        int nuevaFila = fila + (df * pasos); //a la fila dada le sumo los pasos pedidos multiplicados por la direccion del movimiento
        int nuevaColumna = columna + (dc * pasos); //idem a las filas

        //verificar los bordes
        if(nuevaFila < 0 || nuevaFila >= this.getPosicionesFichas().length || nuevaColumna < 0 || nuevaColumna >= this.getPosicionesFichas()[0].length) { //verifico que no salga de la matriz
            movimientoValido =  false; //si se sale devulve false
        }
        
        if(movimientoValido){ //Si no se paso para afuera de la matriz, chequeo que los pasos sean correctos
            //verificar si se puede hacer el movimiento
            for(int i = 1; i < pasos && movimientoValido; i++) { //verifico segun los pasos dados

                int f = fila + (df * i); //a la fila dada le sumo los pasos pedidos multiplicados por la direccion del movimiento
                int c = columna + (dc * i); //idem a las filas

                if(this.getPosicionesFichas()[f][c] != 'V') { //si el espacio final no es vacio
                    movimientoValido =  false;
                }
            }
            
            if(debeTerminarVacio){
                if(this.getPosicionesFichas()[nuevaFila][nuevaColumna] != 'V') { //si el espacio final no es vacio
                    movimientoValido = false;
                }
            }
            else if(this.getPosicionesFichas()[nuevaFila][nuevaColumna] == color) { //si el espacio final tiene el mismo color
                movimientoValido =  false;
            }
        }
        return movimientoValido;
    }
    
    /**
     * Caso 4 - Se retorna el string con el formato correspondiente al tablero
     * @return el string con el formato del tablero, de forma que al imprimir en pantalla se ve acorde a lo requerido en la letra
     */
    public String prepararTablero(){
        String toStr = "";
        
        for(int fila = 0; fila < this.getPosicionesFichas().length;fila++){//Recorro las filas
            toStr += "+"; //Ingreso el + de la esquina superior izquierda
            for(int col = 0; col < this.getPosicionesFichas()[fila].length;col++){ //por cada columna ingreso el borde superior de la celda
                toStr += "---+"; //Borde superior
            }
            toStr += "\n|";//Ingreso el salto de linea y el borde izquierdo de la matriz
            for(int col = 0; col < this.getPosicionesFichas()[fila].length;col++){ //Recorro las columnas
                if( this.getPosicionesFichas()[fila][col] == 'V'){ //Si es V, dejo el espacio vacio
                    toStr += "   |";
                } else{ //Sino, ingreso la ficha, y el borde derecho del bloque
                    toStr += " " + this.getPosicionesFichas()[fila][col] + " ";
                    toStr += "|";
                }
            }
            toStr += "\n"; //Ingreso el salto de linea al terminar de recorrer las columas y pasar a la siguiente fila
        }
        //Una vez termino de ingresar las fichas, ingreso el borde inferior
        toStr += "+"; //Ingreso el + de la esquina inferior izquierda
        for(int col = 0; col < this.getPosicionesFichas()[0].length;col++){ //Por cada columna, ingreso el borde inferior de la celda
            toStr += "---+"; //Borde inferior
        }
        toStr += "\n"; //Salto de linea al terminar de ingresar el tablero
        
        return toStr;
    }
    
    /**
     * Caso 5 - Verifica que todas las fichas esten conectadas entre si. Esto es que cada ficha sea vecina horizontal vertical o diagonal a otra del mismo color, formando un unico grupo.
     * @param color Color de las fichas a revisar
     * @return Si todas las fichas forman un unico grupo conectado
     */
    public boolean verificarConexion(char color){
        boolean estanConectados = false;
        
        int cantTotalFichas = contarFichas(color); //cuento el total de fichas
        
        if(cantTotalFichas == 1){ //Si solo hay una ficha esta conectada
            estanConectados = true;
        } else if(cantTotalFichas > 1){ // Si hay mas fichas, contar fichas y chequear que sean = a la cantidad total
            boolean encontrada = false;
            int filaFicha = 0;
            int columnaFicha = 0;
            
            //Encontrar primera ficha
            for(int fila = 0; fila < this.getPosicionesFichas().length && !encontrada; fila++){
                for(int columna = 0; columna < this.getPosicionesFichas()[0].length && !encontrada; columna++){
                    if(this.getPosicionesFichas()[fila][columna] == color){
                        filaFicha = fila;
                        columnaFicha = columna;
                        encontrada = true;
                    }
                }
            }
            
            int cantGrupo = contarGrupoConectado(filaFicha, columnaFicha); //contar las fichas conectadas a la primera ficha encontrada
            
            estanConectados = cantGrupo == cantTotalFichas; //Si la cantidad de fichas conectadas es igual a la cantidad total de fichas, estan todas conectadas
        }
        return estanConectados;
    }
    
    /**
     * Metodo auxiliar que cuenta las fichas de un color conectadas tanto ortogonalmente como diagonalmente
     * @param fila Fila de la ficha a contar
     * @param columna Columna de la ficha a contar
     * @return Cantidad de fichas conectadas a la fila y columna dada
     */
    private int contarGrupoConectado(int fila, int columna){
        boolean[][] contada = new boolean[this.getPosicionesFichas().length][this.getPosicionesFichas()[0].length]; //Matriz auxiliar para marcar las posiciones que ya fueron contadas
        char color = this.getPosicionesFichas()[fila][columna];
        
        return contarGrupoConectado(fila, columna, color, contada);
    }
    
    /**
     * Metodo auxiliar que cuenta recursivamente las fichas de un color conectadas tanto ortogonalmente como diagonalmente
     * @param fila Fila de la ficha a contar
     * @param columna Columna de la ficha a contar
     * @param color Color a validar
     * @param contada Matriz booleana donde se ira actualizando cuales posiciones han sido contadas
     * @return Cantidad de fichas conectadas a la fila y columna dada
     */
    private int contarGrupoConectado(int fila, int columna, char color, boolean[][] contada){
        int cantCeldas = 0;
        if(this.getPosicionesFichas()[fila][columna] == color && !contada[fila][columna]){ //Si la ficha es del color requerido, y no fue contada aun
            cantCeldas++;
            contada[fila][columna] = true;
            
            //Contar fichas abajo
            if(fila + 1 < this.getPosicionesFichas().length){// Si hay lugares abajo
                cantCeldas += contarGrupoConectado(fila + 1, columna, color, contada); //sumar celdas conectadas por abajo
                if(columna + 1 < this.getPosicionesFichas()[0].length){ // si hay lugares para abajo
                    cantCeldas += contarGrupoConectado(fila + 1, columna + 1, color, contada); //sumar celdas conectadas por la diagonal inferior derecha
                }
                if(columna - 1 >= 0){ //si hay lugares para arriba
                    cantCeldas += contarGrupoConectado(fila + 1, columna - 1, color, contada); //sumar celdas conectadas por la diagonal inferior izquierda
                }
            }
            
            //Contar fichas arriba
            if(fila - 1 >= 0){// Si hay lugares arriba
                cantCeldas += contarGrupoConectado(fila - 1, columna, color, contada); //sumar celdas conectadas por abajo
                if(columna + 1 < this.getPosicionesFichas()[0].length){ // si hay lugares para abajo
                    cantCeldas += contarGrupoConectado(fila - 1, columna + 1, color, contada); //sumar celdas conectadas por la diagonal superior derecha
                }
                if(columna - 1 >= 0){ //si hay lugares para arriba
                    cantCeldas += contarGrupoConectado(fila - 1, columna - 1, color, contada); //sumar celdas conectadas por la diagonal superior izquierda
                }
            }
            
            //Contar fichas derecha
            if(columna + 1 < this.getPosicionesFichas()[0].length){ // si hay lugares para la derecha
                cantCeldas += contarGrupoConectado(fila, columna + 1, color, contada); //sumar celdas conectadas por arriba
            }
            
            //Contar fichas izquierda
            if(columna - 1 > 0){ //si hay lugares para arriba
                cantCeldas += contarGrupoConectado(fila, columna - 1, color, contada); //sumar celdas conectadas por abajo
            }
        }
        
        return cantCeldas;
    }
}
