import static org.junit.Assert.*;

import org.junit.Test;

import junio2013.TablonDeAnuncios;

public class TablonDeAnunciosTest {

	@Test
	public void Test1() {
		TablonDeAnuncios TDA = new TablonDeAnuncios();
		assertEquals("No hay anuncios", 1, TDA.anunciosPublicados());
	}

}
