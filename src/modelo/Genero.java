package src.modelo;

public enum Genero {
    ACCION(1),
    AVENTURA(2),
    COMEDIA(3),
    DRAMA(4),
    FANTASIA(5),
    TERROR(6),
    FICCION(7),
    MUSICAL(8),
    SUSPENSE(9),
    WESTERN(10);

    private int id;

    private Genero(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
