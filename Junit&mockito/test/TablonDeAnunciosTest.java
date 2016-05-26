import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junio2013.Anuncio;
import junio2013.IBaseDeDatosDeAnunciantes;
import junio2013.IBaseDeDatosDePagos;
import junio2013.TablonDeAnuncios;


public class TablonDeAnunciosTest {

	private static final IBaseDeDatosDeAnunciantes IBaseDeDatosDeAnunciantes = null;
	private static final IBaseDeDatosDePagos IBaseDeDatosDePagos = null;

	@Test
	public void Test1() {
		TablonDeAnuncios TDA = new TablonDeAnuncios();
		assertEquals(1, TDA.anunciosPublicados());
	}

	@Test
	public void Test2() {
		TablonDeAnuncios TDA = new TablonDeAnuncios();
		Anuncio A = new Anuncio("","","LA EMPRESA");
		TDA.publicarAnuncio(A, IBaseDeDatosDeAnunciantes, IBaseDeDatosDePagos);
		assertEquals(2, TDA.anunciosPublicados());
	}
}
