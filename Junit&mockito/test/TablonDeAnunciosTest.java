import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junio2013.Anuncio;
import junio2013.IBaseDeDatosDeAnunciantes;
import junio2013.IBaseDeDatosDePagos;
import junio2013.TablonDeAnuncios;

public class TablonDeAnunciosTest {
	TablonDeAnuncios TDA;

	@Before
	public void creaTablonInicial() {
		TDA = new TablonDeAnuncios();
	}

	@After
	public void eliminaTablonInicial() {
		TDA = null;
	}

	@Test
	public void Test1() {
		assertEquals(1, TDA.anunciosPublicados());
	}

	@Test
	public void Test2() {
		Anuncio A = new Anuncio(" ", " ", "LA EMPRESA");
		TDA.publicarAnuncio(A, null, null);
		assertEquals(2, TDA.anunciosPublicados());
	}

	@Test
	public void Test3() {
		IBaseDeDatosDePagos pagos = mock(IBaseDeDatosDePagos.class);
		IBaseDeDatosDeAnunciantes anunciantes = mock(IBaseDeDatosDeAnunciantes.class);
		when(anunciantes.buscarAnunciante("Carlos")).thenReturn(true);
		when(pagos.anuncianteTieneSaldo("Carlos")).thenReturn(false);
		Anuncio A = new Anuncio(" ", " ", "Carlos");
		TDA.publicarAnuncio(A, anunciantes, pagos);
		assertEquals(TDA.anunciosPublicados(), 1);

	}

	@Test
	public void test4() {
		IBaseDeDatosDePagos pagos = mock(IBaseDeDatosDePagos.class);
		IBaseDeDatosDeAnunciantes anunciantes = mock(IBaseDeDatosDeAnunciantes.class);
		when(anunciantes.buscarAnunciante("Carlos")).thenReturn(true);
		when(pagos.anuncianteTieneSaldo("Carlos")).thenReturn(true);
		Anuncio A = new Anuncio(" ", " ", "Carlos");
		TDA.publicarAnuncio(A, anunciantes, pagos);
		verify(pagos).anuncioPublicado("Carlos");

	}
}
