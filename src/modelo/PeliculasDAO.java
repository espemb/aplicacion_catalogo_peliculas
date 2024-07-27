package src.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeliculasDAO {
    private String path;

    public PeliculasDAO(String path) {
        this.path = path;
    }

    public void insertarPelicula(Pelicula pel) throws SQLException {
        String sql = "INSERT into peliculas(titulo, id_director, anyo, url_caratula, id_genero, es_animacion) values(?,?,?,?,?,?)";
    
        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement((sql));

        sentenciaSQL.setString(1, pel.getTitulo());
        sentenciaSQL.setInt(2, pel.getIdDirector());
        sentenciaSQL.setInt(3, pel.getAnyo());
        sentenciaSQL.setString(4, pel.getCaratula());
        sentenciaSQL.setInt(5, pel.getIdGenero());
        sentenciaSQL.setBoolean(6, pel.esAnimacion());    

        sentenciaSQL.executeUpdate();
        conn.close();
    }

    public ArrayList<Pelicula> dameTodas(String campo) throws SQLException {
        String sql = "SELECT titulo, id_director, anyo, url_caratula, id_genero, es_animacion FROM peliculas order by " + campo + ";"; // 2 = titulo, 4=anyo 6=genero

        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement((sql));
        ResultSet resultado = sentenciaSQL.executeQuery();

        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

        while (resultado.next()) {
            Pelicula nueva = new Pelicula(resultado.getString("titulo"), resultado.getInt("id_director"), resultado.getInt("anyo"), resultado.getString("url_caratula"), resultado.getInt("id_genero"), resultado.getBoolean("es_animacion")); 
            listaPeliculas.add(nueva);
        }
        conn.close();
        return listaPeliculas;
    }

    public Pelicula buscaPorId(int id) throws SQLException {
        String sql = "SELECT id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion from peliculas WHERE id = ?";

        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);
        sentenciaSQL.setInt(1, id);
        ResultSet resultado = sentenciaSQL.executeQuery();
        
        if (resultado.next()) {
            String titulo = resultado.getString("titulo");
            int id_director = resultado.getInt("id_director");
            int anyo = resultado.getInt("anyo");
            String caratula = resultado.getString("url_caratula");
            int id_genero = resultado.getInt("id_genero");
            boolean esAnimacion = resultado.getBoolean("es_animacion");
            
            Pelicula pel = new Pelicula(titulo, id_director, anyo, caratula, id_genero, esAnimacion);
            conn.close();
            return pel;
        } else {
            return null;
        }
    }

    public Pelicula buscaPorTitulo(String title) throws SQLException {
        
        String sql = "SELECT id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion from peliculas WHERE titulo = ?";
        
        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);
        sentenciaSQL.setString(1, title);
        ResultSet resultado = sentenciaSQL.executeQuery();
        
        if (resultado.next()) {
            String titulo = resultado.getString("titulo");
            int id_director = resultado.getInt("id_director");
            int anyo = resultado.getInt("anyo");
            String caratula = resultado.getString("url_caratula");
            int id_genero = resultado.getInt("id_genero");
            boolean esAnimacion = resultado.getBoolean("es_animacion");
            
            Pelicula pel = new Pelicula(titulo, id_director, anyo, caratula, id_genero, esAnimacion);
            conn.close();
            return pel;
        } else {
            return null;
        }
    }


    public void borraPorId(int id) throws SQLException {
        String sql = "DELETE FROM peliculas WHERE id = ?";

        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);
        sentenciaSQL.setInt(1, id);
        sentenciaSQL.executeUpdate();
        conn.close();
    }

    public void modificaPelicula(Pelicula pelicula) throws SQLException {
        String sql = "UPDATE peliculas set titulo = ?, id_director = ?, anyo = ?, url_caratula = ?, id_genero = ?, es_animacion= ? where id = ?";

        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setString(1, pelicula.getTitulo());
        sentenciaSQL.setInt(2, pelicula.getIdDirector());
        sentenciaSQL.setInt(3, pelicula.getAnyo());
        sentenciaSQL.setString(4, pelicula.getCaratula());
        sentenciaSQL.setInt(5, pelicula.getIdGenero());
        sentenciaSQL.setBoolean(6, pelicula.esAnimacion());
        sentenciaSQL.setInt(7, pelicula.getId());

        sentenciaSQL.executeUpdate();
        conn.close();
    }    

}