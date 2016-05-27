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
		Anuncio A=new Anuncio("Prueba1","AnuncioEmpresa1","LA EMPRESA");
		Anuncio B=new Anuncio("Prueba2","AnuncioEmpresa2","LA EMPRESA");
		TDA.publicarAnuncio(A, null,null);
		TDA.publicarAnuncio(B, null,null);
		int publicados=TDA.anunciosPublicados();
		TDA.buscarAnuncioPorTitulo("Prueba2");
		assertEquals(TDA.anunciosPublicados(),publicados);
	}
	
	@Test
	public void test6(){
		Anuncio A=new Anuncio("Prueba","AnuncioEmpresa","LA EMPRESA");
		Anuncio B=new Anuncio("Prueba2","AnuncioEmpresa2","LA EMPRESA");
		TDA.publicarAnuncio(A, null,null);
		TDA.publicarAnuncio(B, null,null);
		TDA.borrarAnuncio("Prueba", "LA EMPRESA");
		Anuncio nulo=TDA.buscarAnuncioPorTitulo("Prueba");
		assertNull(nulo);
	}
}
