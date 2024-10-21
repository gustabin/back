package ar.com.santanderrio.obp.servicios.prestamos.manager;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PagoCuotaMapper;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaOutView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PagoCuotaManagerImpl;

@RunWith(MockitoJUnitRunner.class)
public class PagoCuotaManagerImplTest {

	@Mock
	PrestamoBO prestamoBO;

	@Mock
	RespuestaFactory respuestaFactory;

	@Mock
	PagoCuotaMapper pagoCuotaMapper;

	@Mock
	EstadisticaManager estadisticaManager;

	@InjectMocks
	private PagoCuotaManagerImpl pagoCuotaManager;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(estadisticaManager.add(
				Mockito.eq(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_PRESTAMO), Mockito.anyString()))
				.thenReturn(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void given_unaCuotaValida_when_pagar_returnRespuestaOK() throws DAOException {
		String numeroPrestamo = "1234567890";
		PagoCuotaInView pagoCuotaInView = new PagoCuotaInView();

		Respuesta respuestaExitosa = Mockito.mock(Respuesta.class);
		PagoCuotaResponseDTO pagoCuotaResponseDTO = new PagoCuotaResponseDTO();
		PagoCuotaOutView pagoCuotaOut = new PagoCuotaOutView();

		Mockito.when(prestamoBO.pagarCuota(Mockito.any(PagoCuotaRequestDTO.class))).thenReturn(pagoCuotaResponseDTO);
		Mockito.when(pagoCuotaMapper.from(pagoCuotaResponseDTO)).thenReturn(pagoCuotaOut);
		Mockito.when(respuestaFactory.crearRespuestaOk(pagoCuotaOut)).thenReturn(respuestaExitosa);
		Respuesta response = pagoCuotaManager.pagar(numeroPrestamo, pagoCuotaInView);
		assertEquals(respuestaExitosa, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void given_aRestException_when_pagar_returnRespuestaERROR() throws DAOException {
		String numeroPrestamo = "1234567890";
		PagoCuotaInView pagoCuotaInView = new PagoCuotaInView();

		Respuesta respuestaError = Mockito.mock(Respuesta.class);
		GenericRestResponseDto genericRestResponseDto = new GenericRestResponseDto();
		GenericRestException genericRestException = new GenericRestException(genericRestResponseDto);

		Mockito.when(prestamoBO.pagarCuota(Mockito.any(PagoCuotaRequestDTO.class))).thenThrow(genericRestException);

		Mockito.when(respuestaFactory.crearRespuestaErrorPersonalizado((Class<GenericRestResponseDto>) Mockito.any(),
				Mockito.anyString(), Mockito.anyString())).thenReturn(respuestaError);
		Respuesta response = pagoCuotaManager.pagar(numeroPrestamo, pagoCuotaInView);
		assertEquals(respuestaError, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void given_anExepcion_when_pagar_returnRespuestaERROR() throws DAOException {
		String numeroPrestamo = "1234567890";
		PagoCuotaInView pagoCuotaInView = new PagoCuotaInView();

		Respuesta respuestaError = Mockito.mock(Respuesta.class);

		DAOException daoException = new DAOException();

		Mockito.when(prestamoBO.pagarCuota(Mockito.any(PagoCuotaRequestDTO.class))).thenThrow(daoException);

		Mockito.when(respuestaFactory.crearRespuestaErrorPersonalizado((Class<GenericRestResponseDto>) Mockito.any(),
				Mockito.anyString(), Mockito.anyString())).thenReturn(respuestaError);
		Respuesta response = pagoCuotaManager.pagar(numeroPrestamo, pagoCuotaInView);
		assertEquals(respuestaError, response);
	}

}
