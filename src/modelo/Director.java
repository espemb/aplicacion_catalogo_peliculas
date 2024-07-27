package src.modelo;

public class Director {
    private int id;
    private String nombre;
    private String foto;
    private String web;

    public Director(String nombre, String foto, String web) {
        this.nombre = nombre;
        this.foto = foto;
        this.web = web;
    }

    public Director(int id, String nombre, String foto, String web) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.web = web;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }

    public String getWeb() {
        return web;
    }
}
