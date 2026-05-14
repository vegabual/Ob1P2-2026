package enums;



/**
 * Enumerodo para definir colores para la impresion en consola
 */
public enum Color {
    Negro("\u001B[30m"),
    Rojo("\u001B[31m"),
    Verde("\u001B[32m"),
    Amarillo("\u001B[33m"),
    Azul("\u001B[34m"),
    Purpura("\u001B[35m"),
    Celeste("\u001B[36m"),
    Blanco("\u001B[37m"),
    PurpuraNegrita("\033[1;35m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}