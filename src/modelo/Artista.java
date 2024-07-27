package src.modelo;

public class Artista {
    private String nombre;
    private String foto;
    private String web;

    public Artista(String nombre, String foto, String web) {
        this.nombre = nombre;
        this.foto = foto;
        this.web = web;
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
