package src.modelo;

public class Pelicula {
    private int id;
    private String titulo;
    private int idDirector;
    private int anyo;
    private String caratula;
    private int idGenero;
    private boolean esAnimacion;

    public Pelicula(String titulo, int idDirector, int anyo, String caratula, int idGenero, boolean esAnimacion) {
        this.titulo = titulo;
        this.idDirector = idDirector;
        this.anyo = anyo;
        this.caratula = caratula;
        this.idGenero = idGenero;
        this.esAnimacion = esAnimacion;
    }
    
    public Pelicula(int id, String titulo, int idDirector, int anyo, String caratula, int idGenero, boolean esAnimacion) {
        this.id = id;
        this.titulo = titulo;
        this.idDirector = idDirector;
        this.anyo = anyo;
        this.caratula = caratula;
        this.idGenero = idGenero;
        this.esAnimacion = esAnimacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getIdDirector() {
        return idDirector;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public int getAnyo() {
        return anyo;
    }

    public String getCaratula() {
        return caratula;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public boolean esAnimacion() {
        return esAnimacion;
    }
}
