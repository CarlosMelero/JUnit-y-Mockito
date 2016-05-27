import static org.junit.Assert.*;
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
		assertEquals(1, TDA.anunciosPublicados());

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

	@Test
	public void test5() {
		TablonDeAnuncios TDA = mock(TablonDeAnuncios.class);
		IBaseDeDatosDePagos pagos = mock(IBaseDeDatosDePagos.class);
		IBaseDeDatosDeAnunciantes anunciantes = mock(IBaseDeDatosDeAnunciantes.class);
		Anuncio A = new Anuncio(" ", " ", "LA EMPRESA");
		Anuncio B = new Anuncio("titulo", " ", "LA EMPRESA");
		TDA.publicarAnuncio(A, anunciantes, pagos);
		TDA.publicarAnuncio(B, anunciantes, pagos);
		verify(TDA).buscarAnuncioPorTitulo(B.titulo_);
		assertEquals(2, TDA.anunciosPublicados());
	}
}
