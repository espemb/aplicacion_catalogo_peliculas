package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.modelo.Artista;
import src.modelo.Director;
import src.modelo.Genero;
import src.modelo.Pelicula;

public class TestModelos {
    @Test
    public void testCrearEnumGenero() {
        Genero gen = Genero.ACCION;
        assertEquals(Genero.ACCION, gen);
    }

    @Test
    public void testGeneroIdAsociado() {
        Genero accion = Genero.ACCION;
        assertEquals(accion.getId(), 1);
    }

    @Test
    public void testCrearDirector() {
        Director dir = new Director("nombre", "www.sufoto.com", "www.suweb.com");
        assertEquals(dir.getNombre(), "nombre");
        assertEquals(dir.getFoto(), "www.sufoto.com");        
        assertEquals(dir.getWeb(), "www.suweb.com");
    }

    @Test
    public void testCrearArtista() {
        Artista art = new Artista("nombre", "www.sufoto.com", "www.suweb.com");
        assertEquals(art.getNombre(), "nombre");
        assertEquals(art.getFoto(), "www.sufoto.com");        
        assertEquals(art.getWeb(), "www.suweb.com");
    }

    @Test
    public void testCrearPelicula() {
        Director dir = new Director("nombre", "www.sufoto.com", "www.suweb.com");
        dir.setId(1);
        Genero fantasia = Genero.FANTASIA;

        Pelicula pel = new Pelicula("titulo", dir.getId(), 2024, "www.sucaratula.com", fantasia.getId(), true);
        assertEquals("titulo", pel.getTitulo());
        assertEquals(1, pel.getIdDirector());        
        assertEquals(2024, pel.getAnyo());
        assertEquals("www.sucaratula.com", pel.getCaratula());
        assertEquals(5, pel.getIdGenero());
        assertEquals(true, pel.esAnimacion());
    }
}
