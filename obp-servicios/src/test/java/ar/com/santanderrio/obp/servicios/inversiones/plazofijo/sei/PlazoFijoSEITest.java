package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.PlazoFijoManager;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.RecomendacionInView;

@RunWith(MockitoJUnitRunner.class)
public class PlazoFijoSEITest {

	@InjectMocks
	private PlazoFijoSEIImpl plazoFijoSEIImpl = new PlazoFijoSEIImpl();
	
	@Mock
	private PlazoFijoManager plazoFijoManager;
	
	@Test
	public void testObtenerRecomendacionManager() {
		Respuesta<RecomendacionResponseEntity> respuesta =  new Respuesta<RecomendacionResponseEntity>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		
		RecomendacionInView viewRequest = new RecomendacionInView();
		
		when(plazoFijoManager.obtenerRecomendacionManager(viewRequest)).thenReturn(respuesta);
		
		Respuesta<RecomendacionResponseEntity> respuestaSei = plazoFijoSEIImpl.obtenerRecomendacion(viewRequest);
		System.out.println(respuestaSei.getEstadoRespuesta());
		
		assertEquals(respuesta, respuestaSei);
		
	}
	
}