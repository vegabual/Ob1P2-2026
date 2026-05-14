package service;

import enums.Color;
import enums.Sentido;
import model.Tester;
import util.PantallaUtils;

public class Menu {
    private static final Color COLOR_MENU = Color.Celeste;
    private static final String BORDE_ENCUADRE = "--------------------------------------------"; //Separador utilizado a lo largo del menu para generar recuadros y separaciones
    private static final String SEPARADOR = PantallaUtils.stringAColor("********************************************", COLOR_MENU); //Separador utilizado a lo largo del menu para generar recuadros y separaciones
    private static Sistema sistema = new Sistema();

    //<editor-fold desc="Getters y Setters">
    /**
     * Getter statico privado para el sistema
     * @return El sistema 
     */
    private static Sistema getSistema() {
        return sistema;
    }
    //</editor-fold>
    
    /**
     * Despliega el menu y pide la opcion a ejecutar hasta que no se ingrese el 0 para salir
     */
    public static void mostrarMenu(){
        //Listado de opciones
        String pedido = "\nQue queres hacer? "
                + "\n1- Registrar tester "
                + "\n2- Registrar matriz de juego "
                + "\n3- Registrar testeo "
                + "\n4- Consulta de testers "
                + "\n5- Estadisticas"
                + "\n0- Salir "
                + "\n" + SEPARADOR + "\nOpcion: ";
        String mensajeDeError = "Solo se aceptan los numeros con opciones del menu (0-5)";//Mensaje de error a mostrar si se ingresa una opcion invalida
        
        imprimirTitulo(); //Imprime el titulo del menu
        int option = PantallaUtils.getEnteroDeInputEntre(pedido,0,5, false, mensajeDeError); //Pedir una opcion numerica del 0 al 5
        
        while (option != 0){ //Mientras el usuario no seleccione la opcion para salir
            switch (option) { //Depende la opcion, se ejecuta un metodo para realizar la accion requerida
                case 1 -> registrarTesterPorConsola();
                case 2 -> registrarMatrizPorConsola();
                case 3 -> registrarTesteo();
                case 4 -> consultarTesters();
                case 5 -> verEstadisticas();
            }
            
            imprimirTitulo(); // Luego de realizar la accion requerida, se imprime el titulo nuevamente
            option =  PantallaUtils.getEnteroDeInputEntre(pedido,0,5, true, mensajeDeError); //Se vuelve a mostrar el listado de opciones
        }
        PantallaUtils.imprimirTexto("Gracias por participar del testing!", COLOR_MENU, true); //Al salir, agradecer por participar
    }
    
    /**
     * Imprime el cabezal MENU en un encuadrado
     */
    private static void imprimirTitulo(){
        PantallaUtils.imprimirEncuadrado("MENU", BORDE_ENCUADRE);
    }
    
    /**
     * Registrar un tester. Pide nombre, edad y experiencia del tester, y lo agrega al sistema
     * No admite testers ya en el sistema, edades por debajo de 0 o superiores a 150, y la experiencia debe ser menor a la edad del tester
     */
    private static void registrarTesterPorConsola(){
        PantallaUtils.imprimirEncuadrado("Registrar tester", BORDE_ENCUADRE);//Imprimir el titulo de la opcion
        String nombre = PantallaUtils.getStringTrimmeadoDeInput("Ingrese el nombre del tester: ", false); //Pedir nombre del tester
        int edad = PantallaUtils.getEnteroDeInputEntre("Ingrese la edad del tester: ", 0, 100, false, "La edad debe estar entre 0 y 100"); //Pedir edad del tester, entre 0 y 150 años.  Si no se ingresa un dato valido, sigue pidiendo
        int experiencia = PantallaUtils.getEnteroDeInputEntre("Ingrese los años de experiencia del tester: ", 0, edad, false, "La experiencia debe estar entre 0 y " + edad);  //Pedir experiencia entre 0 y la edad provista. Si no se ingresa un dato valido, sigue pidiendo
        
        if(getSistema().registrarTester(nombre, edad, experiencia)){ //Si es posible agregar el tester, imprimir mensaje de exito
            PantallaUtils.imprimirTexto("El tester fue agregado correctamente", true);
        } else{ //Si no se pudo crear, es porque ya existe uno con el mismo nombre, mostrar mensaje de error
            PantallaUtils.imprimirTexto("ERROR: Ya existe un tester con el nombre " + nombre, true);
        }
    }
    
    /**
     * Configura el tablero de juego. Pregunta si quiere usar el tablero por defecto. En caso de responder que no se piden las 8 filas de 10 columas que constituyen el tablero con las posiciones del juego
     */
    private static void registrarMatrizPorConsola(){
        PantallaUtils.imprimirEncuadrado("Registrar matriz", BORDE_ENCUADRE); //Imprimir el titulo de la opcion
        boolean tableroDefault = PantallaUtils.getBooleanDeInput("Desea usar el tablero por defecto? ", false); //Se pregunta si se desea usar la matriz por defecto. Si no se ingresa S o N, sigue pidiendo
        if(tableroDefault){ //Si se pide usar el tablero por defecto, se reestablece con la matriz por defecto
            getSistema().registrarTablero();
        } else{ //Entonces se usara un tablero ingresado por el usuario
            char[][] nuevoTablero = PantallaUtils.leerMatrizDeConsola(8, 10); //Pedir las 8 filas con 10 columnas del tablero. Si se ingresa una fila con una cantidad de columas incorrecta, sigue pidiendo.
            getSistema().registrarTablero(nuevoTablero); //Configurar el tablero ingresado
        }
    }
    
    /**
     * Registrar un testeo. Pide caso a ejecutar, tester y el comentario. Luego de registrar el testeo, se piden los datos requeridos del caso, y se ejecuta, para luego guardar el tablero resultante en el testeo correspondiente
     */
    private static void registrarTesteo(){
        PantallaUtils.imprimirEncuadrado("Registrar testeo", BORDE_ENCUADRE);//Imprimir el titulo de la opcion
            
        if(getSistema().hayTestersCargados()){//Si hay testers cargados en el sistema
            //Pedir caso
            //Se muestra el listado de los casos posibles+
            PantallaUtils.imprimirTexto("Casos posibles para probar:", true);
            PantallaUtils.imprimirTexto("  Caso 1- Contar fichas", true);
            PantallaUtils.imprimirTexto("  Caso 2- Validar movimiento individual", true);
            PantallaUtils.imprimirTexto("  Caso 3- Validar movimiento en grupo", true);
            PantallaUtils.imprimirTexto("  Caso 4- Preparar tablero", true);
            PantallaUtils.imprimirTexto("  Caso 5- Verificar conexion", true);
            int caso = PantallaUtils.getEnteroDeInputEntre("Ingrese el caso a probar: ", 1, 5, false, "El caso ingresado no existe, solo se aceptan numeros del 1 al 5"); //Se pide el caso dentro de los posibles. Si no se ingresa un dato valido, sigue pidiendo
            
            //Pedir tester
            //Se muestra listado de testers en el sistema
            PantallaUtils.imprimirTexto("Listado de testers:", true); //Titulo del listado
            PantallaUtils.imprimirTexto(getSistema().testersAListado(null), true);
            Tester tester = PantallaUtils.getTesterDeInput(getSistema(), "Ingrese el nombre del tester: ", false); // Se pide al tester. Si se ingresa el nombre de un tester que aun no esta registrado, sigue pidiendo.
            
            //Pedir comentario
            String comentario = PantallaUtils.getStringTrimmeadoDeInput("Comentario: ", false);
            
            //Registrar testeo y guardar el ID
            int testId = getSistema().registrarTesteo(caso, tester, comentario); //Guardar testeo, con el tablero previo a ejecutar el caso
            
            //Correr caso
            switch (caso) {
                case 1-> registrarTesteoCaso1();
                case 2-> registrarTesteoCaso2();
                case 3-> registrarTesteoCaso3();
                case 4-> registrarTesteoCaso4();
                case 5-> registrarTesteoCaso5();
            }
            
            //Guarda el tablero luego del caso
            getSistema().actualizarTableroEnTesteo(testId, tester);
        } else{ //Entonces no hay testers, no es posible registrar un testeo
            PantallaUtils.imprimirTexto("No hay testers cargados, cargar al menos un tester antes de registrar testeos.", true);
        }
    }
    
    private static void consultarTesters(){
        PantallaUtils.imprimirEncuadrado("Consulta de testers", BORDE_ENCUADRE);
    }
    
    private static void verEstadisticas(){
        PantallaUtils.imprimirEncuadrado("Estadisticas", BORDE_ENCUADRE);//Imprimir el titulo de la opcion
        
        if(getSistema().hayTestersCargados()){
            PantallaUtils.imprimirTexto(getSistema().listadoTestersConMasTests(), true);
            
            PantallaUtils.imprimirTexto(getSistema().listadoTestersConMenosTests(), true);
        }
    }
    
    //<editor-fold desc="Casos de prueba">
    /**
     * Registrar Caso 1 - Contar fichas. Se pide las fichas a contar
     */
    private static void registrarTesteoCaso1() {
        PantallaUtils.imprimirEncuadrado("CASO 1 - Contar fichas", BORDE_ENCUADRE);
        
        char color = PantallaUtils.getColorDeInput("Ingrese el color a contar: ", false); //Pide un color. Si se ingresa un color invalido, sigue pidiendo
        int cantFichas = getSistema().contarFichas(color); //Ejecutar caso
        PantallaUtils.imprimirTexto("Hay " + cantFichas + " fichas del tipo " + color, true); //Imprimir resultado del caso
        PantallaUtils.imprimirTexto(getSistema().prepararTablero(), true); //Imprimir matriz resultante
    }

    /**
     * Registrar Caso 2 - Validar movimiento individual. Se pide: color, sentido, fila, columna y pasos, para luego validar movimiento imdividual. 
     * En caso de ser valido, se actualiza la posicion de la ficha
     */
    private static void registrarTesteoCaso2() {
        PantallaUtils.imprimirEncuadrado("CASO 2 - Validar movimiento individual", BORDE_ENCUADRE);
        
        //Pedidos por consola
        char color = PantallaUtils.getColorDeInput("Ingrese el color a mover: ", false); //Pide el color de la ficha a mover. Si se ingresa un dato invalido, pide de nuevo
        Sentido sentido = PantallaUtils.getSentidoDeInput("Ingrese el sentido del movimiento", false, null); //Pide el sentido del movimiento. Si se ingresa un dato invalido, pide de nuevo
        int fila = PantallaUtils.getEnteroDeInputEntre("Ingrese la fila de la ficha a mover (0-7): ", 0, 7, false, "La fila debe estar entre 0 y 7"); //Pide la fila de la ficha a mover entre las filas posibles. Si se ingresa un dato invalido, pide de nuevo
        int columna = PantallaUtils.getEnteroDeInputEntre("Ingrese la columna de la ficha a mover (0-9): ", 0, 9, false, "La fila debe estar entre 0 y 9"); //Pide la columna de la ficha a mover entre las columnas posibles. Si se ingresa un dato invalido, pide de nuevo
        int pasos = PantallaUtils.getEnteroDeInputEntre("Ingrese la cantidad de posiciones a mover: ", 1, 10, false, "Las posiciones debe estar entre 1 y 10"); //Pide la cantidad de posiciones a mover. Limita el input al movimiento mas largo posible en el tablero (10)
        
        boolean movimientoValido = getSistema().validarMovimientoIndividual(color, sentido, fila, columna, pasos); //Valida movimiento individual. Si es valido se actualiza el tablero
        if(movimientoValido){ //Si el movimiento fue valido, se muestra mensaje de exito
            PantallaUtils.imprimirTexto("El movimiento fue valido", true);
        } else{ //Sino, mensaje de error
            PantallaUtils.imprimirTexto("El movimiento fue invalido", true);
        }
        PantallaUtils.imprimirTexto(getSistema().prepararTablero(), true); //Se imprime la matriz resultante
    }
    
    /**
     * Registrar Caso 3 - Validar movimiento en grupo. Se pide: color, sentido, forma, fila, columna, tamaño del grupo y pasos, para luegor valida movimiento en grupo. 
     * En caso de ser valido, se actualizan las posiciones de las fichas
     */
    private static void registrarTesteoCaso3() {
        PantallaUtils.imprimirEncuadrado("CASO 3 - Validar movimiento en grupo", BORDE_ENCUADRE);
        
        char color = PantallaUtils.getColorDeInput("Ingrese el color a mover: ", false);//Pide el color de la ficha a mover. Si se ingresa un dato invalido, pide de nuevo
        Sentido sentido = PantallaUtils.getSentidoOrtogonalDeInput("Ingrese el sentido del movimiento", false);//Pide el sentido del movimiento. Si se ingresa un dato invalido, pide de nuevo
        char forma = PantallaUtils.getFormaDeInput("Ingrese la forma del grupo ", false); //Pide la forma del grupo. Si no se ingresa H o V, pide de nuevo.
        int fila = PantallaUtils.getEnteroDeInputEntre("Ingrese la fila de la ficha a mover: ", 0, 7, false, "La fila debe estar entre 0 y 7"); //Pide la fila de la ficha a mover entre las filas posibles. Si se ingresa un dato invalido, pide de nuevo
        int columna = PantallaUtils.getEnteroDeInputEntre("Ingrese la columna de la ficha a mover: ", 0, 9, false, "La fila debe estar entre 0 y 9"); //Pide la columna de la ficha a mover entre las columnas posibles. Si se ingresa un dato invalido, pide de nuevo
        int tamanio = PantallaUtils.getEnteroDeInputEntre("Ingrese la cantidad de fichas a mover: ", 1, 10, false, "La cantidad de fichas debe estar entre 1 y 10"); //Pide el tamaño del grupo. Limita el input al grupo mas largo posible en el tablero (10)
        int pasos = PantallaUtils.getEnteroDeInputEntre("Ingrese la cantidad de posiciones a mover: ", 1,10, false, "Las posiciones debe estar entre 1 y 10"); //Pide la cantidad de posiciones a mover. Limita el input al movimiento mas largo posible en el tablero (10)
        
        boolean movimientoValido = getSistema().validarMovimientoEnGrupo(color, forma, sentido, fila, columna, tamanio, pasos); //Valida el movimiento en grupo. En caso de ser valido se actualiza el tablero
        if(movimientoValido){ //Si el movimiento fue valido, se muestra mensaje de exito
            PantallaUtils.imprimirTexto("El movimiento fue valido", true);
        } else{ //Sino, mensaje de error
            PantallaUtils.imprimirTexto("El movimiento fue invalido", true);
        }
        PantallaUtils.imprimirTexto(getSistema().prepararTablero(), true); //Se imprime la matriz resultante
    }
    
    /**
     * Registrar Caso 4- Preparar tablero. Se devuelve el string del tablero con el formato requerido y se imprime.
     */
    private static void registrarTesteoCaso4() {
        PantallaUtils.imprimirEncuadrado("CASO 4 - Preparar tablero", BORDE_ENCUADRE);
        
        PantallaUtils.imprimirTexto(getSistema().prepararTablero(), true); //Imprime el string devuelto por el caso 4
    }

    /**
     * Registrar Caso 5- Verificar conexion. Pide un color y valida si todas las fichas de ese color se encuentran conectadas entre si.
     */
    private static void registrarTesteoCaso5() {
        PantallaUtils.imprimirEncuadrado("CASO 5 - Verificar conexion", BORDE_ENCUADRE);
        
        char color = PantallaUtils.getColorDeInput("Ingrese el color a revisar: ", false); //Pide un color. Si se ingresa un color invalido, sigue pidiendo
        boolean hayConexion = getSistema().verificarConexion(color); //Verifica si hay conexion
        if(hayConexion){ //Si hay conexion, imprime que las fichas estan conectadas
            PantallaUtils.imprimirTexto("Las fichas " + color + " estan conectadas", true);
        } else{ //Sino, imprime que no lo estan
            PantallaUtils.imprimirTexto("Las fichas " + color + " no estan conectadas", true);
        }
        PantallaUtils.imprimirTexto(getSistema().prepararTablero(), true); //Imprime el tablero resultante.
    }
    //</editor-fold>
}
