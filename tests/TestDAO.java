package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

import src.modelo.Director;
import src.modelo.DirectoresDAO;
import src.modelo.Genero;
import src.modelo.Pelicula;
import src.modelo.PeliculasDAO;
import src.modelo.Utilidades;

public class TestDAO {
    @Test
    public void testJDBCConecta() {

        boolean laClaseJDBCExiste = false;
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Todo ha ido bien");
            laClaseJDBCExiste = true;
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC no encontrado");
            e.printStackTrace();
        }
        
        assertTrue(laClaseJDBCExiste);
    }


    @Test
    public void testCrearConexion() throws SQLException {
        Utilidades utils = new Utilidades();

        Connection conn = utils.getConnection("./data/videoteca_test.sqlite");
        assertNotNull(conn);
        conn.close();

        conn = utils.getConnection("./lolailoylere/videoteca_test.sqlite");
        assertNull(conn);
    }    


    @Test
    public void testInsertarDirector() throws SQLException {

        String sql0 = "DELETE FROM directores;";
        Connection conn0 = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL0 = conn0.createStatement();
        sentenciaSQL0.executeUpdate(sql0);
        conn0.close();

        DirectoresDAO dirDao = new DirectoresDAO("./data/videoteca_test.sqlite");

        Director dir = new Director("Woody Allen", "www.sufoto.com", "www.suweb.com");
        dirDao.insertarDirector(dir);

        String nombre = "";
        String foto = "";
        String web = "";

        String sql = "SELECT nombre, url_foto, url_web FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            nombre = resultado.getString("nombre");
            foto = resultado.getString("url_foto");
            web = resultado.getString("url_web");
        }

        assertEquals("Woody Allen", nombre);
        assertEquals("www.sufoto.com", foto);
        assertEquals("www.suweb.com", web);

        conn.close();
    }


    @Test
    public void testConsultarTodosDirectores() throws SQLException {    

        String sql0 = "DELETE FROM directores;";
        Connection conn0 = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL0 = conn0.createStatement();
        sentenciaSQL0.executeUpdate(sql0);
        conn0.close();
        
        DirectoresDAO dirDAO = new DirectoresDAO("./data/videoteca_test.sqlite");

        Director dir1 = new Director("Tarantino", "www.fototar.com", "www.webtar.com");
        Director dir2 = new Director("Kubrick", "www.fotokub.com", "www.webkub.com");
        Director dir3 = new Director("Spielberg", "www.fotospi.com", "www.webspi.com");

        dirDAO.insertarDirector(dir1);
        dirDAO.insertarDirector(dir2);
        dirDAO.insertarDirector(dir3);        

        ArrayList<Director> directores = dirDAO.dameTodos();

        assertEquals(3, directores.size());

        assertEquals("Kubrick", directores.get(0).getNombre());
        assertEquals("Spielberg", directores.get(1).getNombre());
        assertEquals("Tarantino", directores.get(2).getNombre());
    }


    @Test
    public void testBuscaDirectorId() throws SQLException {   

        String sql0 = "DELETE FROM directores;";
        Connection conn0 = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL0 = conn0.createStatement();
        sentenciaSQL0.executeUpdate(sql0);
        conn0.close();

        DirectoresDAO dirDAO = new DirectoresDAO("./data/videoteca_test.sqlite");

        Director dir1 = new Director("Tarantino", "www.fototar.com", "www.webtar.com");
        Director dir2 = new Director("Kubrick", "www.fotokub.com", "www.webkub.com");
        Director dir3 = new Director("Spielberg", "www.fotospi.com", "www.webspi.com");

        dirDAO.insertarDirector(dir1);
        dirDAO.insertarDirector(dir2);
        dirDAO.insertarDirector(dir3);

        String nombre = "";
        String foto = "";
        String web = "";
        int idUltimoInsertado = 0;

        String sql = "SELECT id, nombre, url_foto, url_web FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            idUltimoInsertado = resultado.getInt("id");
            nombre = resultado.getString("nombre");
            foto = resultado.getString("url_foto");
            web = resultado.getString("url_web");
        }

        Director directorEncontrado = dirDAO.buscaPorId(idUltimoInsertado);

        assertEquals("Spielberg", directorEncontrado.getNombre());
        assertEquals("www.fotospi.com", directorEncontrado.getFoto());
        assertEquals("www.webspi.com", directorEncontrado.getWeb());
    
        Director dir0 = dirDAO.buscaPorId(1000);
        assertEquals(null, dir0);
        
        conn.close();
    }


    @Test
    public void testBuscaDirectorNombre() throws SQLException {   
        
        DirectoresDAO dirDAO = new DirectoresDAO("./data/videoteca_test.sqlite");

        String nombre = "";
        String foto = "";
        String web = "";

        String sql = "SELECT id, nombre, url_foto, url_web FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            nombre = resultado.getString("nombre");
            foto = resultado.getString("url_foto");
            web = resultado.getString("url_web");
        }

        Director directorEncontrado = dirDAO.buscaPorNombre("Spielberg");

        assertEquals("Spielberg", directorEncontrado.getNombre());
        assertEquals("www.fotospi.com", directorEncontrado.getFoto());
        assertEquals("www.webspi.com", directorEncontrado.getWeb());
    
        Director dir0 = dirDAO.buscaPorNombre("Coppola");
        assertEquals(null, dir0);

        conn.close();
    }


    @Test
    public void testBorraDirectorPorId() throws SQLException {   

        DirectoresDAO dirDAO = new DirectoresDAO("./data/videoteca_test.sqlite");

        Director dir4 = new Director("Almodovar", "www.fotoalm.com", "www.webalm.com");
        dirDAO.insertarDirector(dir4);

        int idUltimoInsertado = 0;

        String sql = "SELECT id FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            idUltimoInsertado = resultado.getInt("id");
        }
        conn.close();

        dirDAO.borraPorId(idUltimoInsertado);

        Director directorBorrado = dirDAO.buscaPorId(idUltimoInsertado);
        assertNull(directorBorrado);
        conn.close();
    }


    @Test
    public void testModificaDirector() throws SQLException {   

        DirectoresDAO dirDAO = new DirectoresDAO("./data/videoteca_test.sqlite");
        
        Director dir5 = new Director("Scorsese", "www.fotosco.com", "www.websco.com");
        dirDAO.insertarDirector(dir5);

        int idUltimoInsertado = 0;
        String nombre = "";
        String foto = "";
        String web = "";

        String sql = "SELECT id FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            idUltimoInsertado = resultado.getInt("id");
        }
        conn.close();

        Director dir6 = new Director(idUltimoInsertado, "Scorssesse", "www.otrafoto.com", "www.otraweb.com");

        dirDAO.modificaDirector(dir6);

        Director directorModificado = dirDAO.buscaPorId(idUltimoInsertado);

        assertEquals("Scorssesse", directorModificado.getNombre());
        assertEquals("www.otrafoto.com", directorModificado.getFoto());
        assertEquals("www.otraweb.com", directorModificado.getWeb());
    }


    @Test
    public void testInsertarPelicula() throws SQLException {
        DirectoresDAO dirDAO = new DirectoresDAO("./data/videoteca_test.sqlite");
        Director dir = new Director("Woody Allen", "www.sufoto.com", "www.suweb.com");
        int idDirector = dir.getId();

        Genero accion = Genero.ACCION;

        PeliculasDAO pelDao = new PeliculasDAO("./data/videoteca_test.sqlite");
        Pelicula pel = new Pelicula("Harry Potter", dir.getId(), 2024, "www.hpcaratula.es", accion.getId(), false);

        dirDAO.insertarDirector(dir);
        pelDao.insertarPelicula(pel);

        String titulo = "";
        int id_director = 0;
        int anyo = 0;
        String caratula = "";
        int id_genero = 0;
        Boolean esAnimacion = null;

        String sql = "SELECT titulo, id_director, anyo, url_caratula, id_genero, es_animacion FROM peliculas ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            titulo = resultado.getString("titulo");
            id_director = resultado.getInt("id_director");
            anyo = resultado.getInt("anyo");
            caratula = resultado.getString("url_caratula");
            id_genero = resultado.getInt("id_genero");
            esAnimacion = resultado.getBoolean("es_animacion");
        }
        conn.close();

        assertEquals("Harry Potter", titulo);
        assertEquals(idDirector, id_director);
        assertEquals(2024, anyo);
        assertEquals("www.hpcaratula.es", caratula);
        assertEquals(1, id_genero);
        assertEquals(false, esAnimacion);
    }

    @Test
    public void testConsultarTodasPeliculasPorTituloAsc() throws SQLException {    

        String sql0 = "DELETE FROM peliculas;";
        Connection conn0 = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL0 = conn0.createStatement();
        sentenciaSQL0.executeUpdate(sql0);
        conn0.close();
        

        int idUltimoInsertado = 0;

        String sql = "SELECT id FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            idUltimoInsertado = resultado.getInt("id");
        }
        conn.close();

        Genero drama = Genero.DRAMA;
        Genero ficcion = Genero.FICCION;
        Genero aventura = Genero.AVENTURA;


        PeliculasDAO pelDAO = new PeliculasDAO("./data/videoteca_test.sqlite");

        Pelicula pel1 = new Pelicula("Titanic", idUltimoInsertado, 1998,  "www.fototit.com", drama.getId(), false);
        Pelicula pel2 = new Pelicula("Matrix", idUltimoInsertado,  1990, "www.fotomat.com", ficcion.getId(), false);
        Pelicula pel3 = new Pelicula("Troya", idUltimoInsertado, 1994,  "www.fototro.com", aventura.getId(), false);

        pelDAO.insertarPelicula(pel1);
        pelDAO.insertarPelicula(pel2);
        pelDAO.insertarPelicula(pel3);

        ArrayList<Pelicula> peliculas = pelDAO.dameTodas("titulo asc");

        assertEquals(3, peliculas.size());

        assertEquals("Matrix", peliculas.get(0).getTitulo());
        assertEquals("Titanic", peliculas.get(1).getTitulo());
        assertEquals("Troya", peliculas.get(2).getTitulo());
    }

    @Test
    public void testConsultarTodasPeliculasPorAnyoDesc() throws SQLException {    

        String sql0 = "DELETE FROM peliculas;";
        Connection conn0 = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL0 = conn0.createStatement();
        sentenciaSQL0.executeUpdate(sql0);
        conn0.close();
        

        int idUltimoInsertado = 0;

        String sql = "SELECT id FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            idUltimoInsertado = resultado.getInt("id");
        }
        conn.close();

        Genero drama = Genero.DRAMA;
        Genero ficcion = Genero.FICCION;
        Genero aventura = Genero.AVENTURA;


        PeliculasDAO pelDAO = new PeliculasDAO("./data/videoteca_test.sqlite");

        Pelicula pel1 = new Pelicula("Titanic", idUltimoInsertado, 1998,  "www.fototit.com", drama.getId(), false);
        Pelicula pel2 = new Pelicula("Matrix", idUltimoInsertado,  1990, "www.fotomat.com", ficcion.getId(), false);
        Pelicula pel3 = new Pelicula("Troya", idUltimoInsertado, 1994,  "www.fototro.com", aventura.getId(), false);

        pelDAO.insertarPelicula(pel1);
        pelDAO.insertarPelicula(pel2);
        pelDAO.insertarPelicula(pel3);

        ArrayList<Pelicula> peliculas = pelDAO.dameTodas("anyo desc");

        assertEquals(3, peliculas.size());

        assertEquals("Titanic", peliculas.get(0).getTitulo());
        assertEquals("Troya", peliculas.get(1).getTitulo());
        assertEquals("Matrix", peliculas.get(2).getTitulo());
    }

    @Test
    public void testConsultarTodasPeliculasPorIdGeneroAsc() throws SQLException {    

        String sql0 = "DELETE FROM peliculas;";
        Connection conn0 = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL0 = conn0.createStatement();
        sentenciaSQL0.executeUpdate(sql0);
        conn0.close();
        

        int idUltimoInsertado = 0;

        String sql = "SELECT id FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            idUltimoInsertado = resultado.getInt("id");
        }
        conn.close();

        Genero drama = Genero.DRAMA;
        Genero ficcion = Genero.FICCION;
        Genero aventura = Genero.AVENTURA;


        PeliculasDAO pelDAO = new PeliculasDAO("./data/videoteca_test.sqlite");

        Pelicula pel1 = new Pelicula("Titanic", idUltimoInsertado, 1998,  "www.fototit.com", drama.getId(), false);
        Pelicula pel2 = new Pelicula("Matrix", idUltimoInsertado,  1990, "www.fotomat.com", ficcion.getId(), false);
        Pelicula pel3 = new Pelicula("Troya", idUltimoInsertado, 1994,  "www.fototro.com", aventura.getId(), false);

        pelDAO.insertarPelicula(pel1);
        pelDAO.insertarPelicula(pel2);
        pelDAO.insertarPelicula(pel3);

        ArrayList<Pelicula> peliculas = pelDAO.dameTodas("id_genero asc");

        assertEquals(3, peliculas.size());

        assertEquals("Troya", peliculas.get(0).getTitulo());
        assertEquals("Titanic", peliculas.get(1).getTitulo());
        assertEquals("Matrix", peliculas.get(2).getTitulo());
    }



    @Test
    public void testBuscaPeliculaId() throws SQLException {   

        String sql0 = "DELETE FROM peliculas;";
        Connection conn0 = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL0 = conn0.createStatement();
        sentenciaSQL0.executeUpdate(sql0);
        conn0.close();

        int idUltimoInsertado = 0;

        String sql1 = "SELECT id FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn1 = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL1 = conn1.createStatement();
        ResultSet resultado1 = sentenciaSQL1.executeQuery(sql1);

        if (resultado1.next()) {
            idUltimoInsertado = resultado1.getInt("id");
        }
        conn1.close();


        Genero drama = Genero.DRAMA;
        Genero ficcion = Genero.FICCION;
        Genero aventura = Genero.AVENTURA;

        PeliculasDAO pelDAO = new PeliculasDAO("./data/videoteca_test.sqlite");

        Pelicula pel1 = new Pelicula("Titanic", idUltimoInsertado, 1998,  "www.fototit.com", drama.getId(), false);
        Pelicula pel2 = new Pelicula("Matrix", idUltimoInsertado,  1990, "www.fotomat.com", ficcion.getId(), false);
        Pelicula pel3 = new Pelicula("Troya", idUltimoInsertado, 1994,  "www.fototro.com", aventura.getId(), false);

        pelDAO.insertarPelicula(pel1);
        pelDAO.insertarPelicula(pel2);
        pelDAO.insertarPelicula(pel3);

        int id = 0;
        String titulo = "";
        int id_director = 0;
        int anyo = 0;
        String caratula = "";
        int id_genero = 0;
        Boolean esAnimacion = null;

        String sql = "SELECT id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion FROM peliculas ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            id = resultado.getInt("id");
            titulo = resultado.getString("titulo");
            id_director = resultado.getInt("id_director");
            anyo = resultado.getInt("anyo");
            caratula = resultado.getString("url_caratula");
            id_genero = resultado.getInt("id_genero");
            esAnimacion = resultado.getBoolean("es_animacion");
        }
        conn.close();

        Pelicula peliculaEncontrada = pelDAO.buscaPorId(id);

        assertEquals("Troya", peliculaEncontrada.getTitulo());
        assertEquals(1994, peliculaEncontrada.getAnyo());
        assertEquals("www.fototro.com", peliculaEncontrada.getCaratula());
        assertEquals(2, peliculaEncontrada.getIdGenero());
        assertEquals(false, peliculaEncontrada.esAnimacion());
    
        Pelicula pel0 = pelDAO.buscaPorId(1000);
        assertEquals(null, pel0);
    }

    @Test
    public void testBuscaPeliculaPorTitulo() throws SQLException {   
        
        PeliculasDAO pelDAO = new PeliculasDAO("./data/videoteca_test.sqlite");

        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");

        Pelicula peliculaEncontrada = pelDAO.buscaPorTitulo("Titanic");

        assertEquals("Titanic", peliculaEncontrada.getTitulo());
        assertEquals(1998, peliculaEncontrada.getAnyo());
        assertEquals("www.fototit.com", peliculaEncontrada.getCaratula());
        assertEquals(4, peliculaEncontrada.getIdGenero());
        assertEquals(false, peliculaEncontrada.esAnimacion());
    
        Pelicula pel0 = pelDAO.buscaPorTitulo("Origen");
        assertEquals(null, pel0);

        conn.close();
    }
    
    @Test
    public void testBorraPeliculaPorId() throws SQLException {   

        PeliculasDAO pelDAO = new PeliculasDAO("./data/videoteca_test.sqlite");

        int idUltimoInsertado = 0;

        String sql = "SELECT id FROM directores ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            idUltimoInsertado = resultado.getInt("id");
        }
        conn.close();

        pelDAO.borraPorId(idUltimoInsertado);

        Pelicula peliculaBorrada = pelDAO.buscaPorId(idUltimoInsertado);
        assertNull(peliculaBorrada);
        conn.close();
    }


    @Test
    public void testModificaPelicula() throws SQLException {   

        DirectoresDAO dirDAO = new DirectoresDAO("./data/videoteca_test.sqlite");
        Director dir = new Director("Scorsese", "www.fotosco.com", "www.websco.com");
        dirDAO.insertarDirector(dir);

        Genero accion = Genero.ACCION;

        PeliculasDAO pelDAO = new PeliculasDAO("./data/videoteca_test.sqlite");
        Pelicula pel = new Pelicula("Harry Potter", dir.getId(), 2024, "www.hpcaratula.es", accion.getId(), false);
        pelDAO.insertarPelicula(pel);

        int id = 0;
        String titulo = "";
        int id_director = 0;
        int anyo = 0;
        String caratula = "";
        int id_genero = 0;
        Boolean esAnimacion = null;

        String sql = "SELECT id, titulo, id_director, anyo, url_caratula, id_genero, es_animacion FROM peliculas ORDER BY id desc LIMIT(1);";
        Connection conn = new Utilidades().getConnection("./data/videoteca_test.sqlite");
        Statement sentenciaSQL = conn.createStatement();
        ResultSet resultado = sentenciaSQL.executeQuery(sql);

        if (resultado.next()) {
            id = resultado.getInt("id");
            titulo = resultado.getString("titulo");
            id_director = resultado.getInt("id_director");
            anyo = resultado.getInt("anyo");
            caratula = resultado.getString("url_caratula");
            id_genero = resultado.getInt("id_genero");
            esAnimacion = resultado.getBoolean("es_animacion");
        }
        conn.close();

        Pelicula pelModificada = new Pelicula(pel.getId(), "Harry Potter2", dir.getId(), 2025, "www.otracaratula.es", accion.getId(), true);

        pelDAO.modificaPelicula(pelModificada);

        assertEquals("Harry Potter2", pelModificada.getTitulo());
        assertEquals(dir.getId(), pelModificada.getIdDirector());
        assertEquals(2025, pelModificada.getAnyo());
        assertEquals("www.otracaratula.es", pelModificada.getCaratula());
        assertEquals(1, pelModificada.getIdGenero());
        assertEquals(true, pelModificada.esAnimacion());
    }

}