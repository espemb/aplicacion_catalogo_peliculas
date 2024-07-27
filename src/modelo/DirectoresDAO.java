package src.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DirectoresDAO {
    private String path;

    public DirectoresDAO(String path) {
        this.path = path;
    }

    public void insertarDirector(Director dir) throws SQLException {
        String sql = "INSERT into directores(nombre, url_foto, url_web) values(?,?,?)";
    
        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement((sql));

        sentenciaSQL.setString(1, dir.getNombre());
        sentenciaSQL.setString(2, dir.getFoto());
        sentenciaSQL.setString(3, dir.getWeb());

        sentenciaSQL.executeUpdate();
        conn.close();
    }

    public ArrayList<Director> dameTodos() throws SQLException {
        String sql = "SELECT id, nombre, url_foto, url_web from directores order by nombre asc";

        Connection conn = new Utilidades().getConnection(path);
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        ArrayList<Director> listaDirectores = new ArrayList<>();

        while (resultado.next()) {
            Director nuevo = new Director(resultado.getString("nombre"), resultado.getString("url_foto"), resultado.getString("url_web"));
            listaDirectores.add(nuevo);
        }
        conn.close();
        return listaDirectores;
    }

    public Director buscaPorId(int id) throws SQLException {
        String sql = "SELECT nombre, url_foto, url_web from directores WHERE id = ?";

        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);
        sentenciaSQL.setInt(1, id);
        ResultSet resultado = sentenciaSQL.executeQuery();
        
        if (resultado.next()) {
            String nombre = resultado.getString("nombre");
            String foto = resultado.getString("url_foto");
            String web = resultado.getString("url_web");
            
            Director dir = new Director(nombre, foto, web);
            conn.close();
            return dir;
        } else {
            return null;
        }
    }

    public Director buscaPorNombre(String name) throws SQLException {
        String sql = (String.format("SELECT id, nombre, url_foto, url_web from directores WHERE nombre = ?"));

        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);
        sentenciaSQL.setString(1, name);
        ResultSet resultado = sentenciaSQL.executeQuery();
        
        if (resultado.next()) {
            String nombre = resultado.getString("nombre");
            String foto = resultado.getString("url_foto");
            String web = resultado.getString("url_web");
            
            Director dir = new Director(nombre, foto, web);
            conn.close();
            return dir;
        } else {
            return null;
        }
    }

    public void borraPorId(int id) throws SQLException {
        String sql = "DELETE FROM directores WHERE id = ?";

        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);
        sentenciaSQL.setInt(1, id);
        sentenciaSQL.executeUpdate();
        conn.close();
    }

    public void modificaDirector(Director director) throws SQLException {
        String sql = "UPDATE directores set nombre = ?, url_foto = ?, url_web = ? where id = ?";

        Connection conn = new Utilidades().getConnection(path);
        PreparedStatement sentenciaSQL = conn.prepareStatement(sql);

        sentenciaSQL.setString(1, director.getNombre());
        sentenciaSQL.setString(2, director.getFoto());
        sentenciaSQL.setString(3, director.getWeb());
        sentenciaSQL.setInt(4, director.getId());

        sentenciaSQL.executeUpdate();
        conn.close();
    }    

}
