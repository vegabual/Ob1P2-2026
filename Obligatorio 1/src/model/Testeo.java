package model;

public class Testeo {
    private int id;
    private int caso;
    private Tester tester;
    private String comentario;
    private static int UltimoId = 0;

    //<editor-fold desc="Getters y Setters">
    //Getters
    public int getId() {
        return id;
    }

    public int getCaso() {
        return caso;
    }

    public Tester getTester() {
        return tester;
    }
    
    public String getComentario() {
        return comentario;
    }

    //Setters
    private void setId(int numero) {
        this.id = numero;
    }

    public void setCaso(int caso) {
        this.caso = caso;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    //</editor-fold>
    
    //<editor-fold desc="Constructores">
    public Testeo(int caso, Tester tester, String comentario){
        this.setId(++UltimoId);
        this.setCaso(caso);
        this.setTester(tester);
        this.setComentario(comentario);
    }
    //</editor-fold>
    
    @Override
    public String toString(){
        return "Testeo ID: " + this.getId() + " \nRealizado por: " + this.getTester() + "\nComentarios: " + this.getComentario();
    }
}
