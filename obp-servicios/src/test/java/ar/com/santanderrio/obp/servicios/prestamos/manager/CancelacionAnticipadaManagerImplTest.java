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
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.prestamos.utils.CancelacionAnticipadaMapper;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaOutView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.CancelacionAnticipadaManagerImpl;

@RunWith(MockitoJUnitRunner.class)
public class CancelacionAnticipadaManagerImplTest {

	@Mock
	PrestamoBO prestamoBO;

	@Mock
	RespuestaFactory respuestaFactory;

	@Mock
	CancelacionAnticipadaMapper cancelacionAnticipadaMapper;

	@Mock
	EstadisticaManager estadisticaManager;

	@InjectMocks
	private CancelacionAnticipadaManagerImpl cancelacionAnticipadaManager;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(estadisticaManager.add(
				Mockito.eq(EstadisticasConstants.CANCELACION_PRESTAMO_CONFIRMACION), Mockito.anyString()))
				.thenReturn(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void given_cancelacionValida_when_cancelarAnticipadamente_returnRespuestaOK() throws DAOException {
		String numeroPrestamo = "1234567890";
		CancelacionAnticipadaInView cancelacionAnticipadaInView = new CancelacionAnticipadaInView();

		Respuesta respuestaExitosa = Mockito.mock(Respuesta.class);
		CancelacionAnticipadaResponseDTO cancelacionAnticipadaResponse = new CancelacionAnticipadaResponseDTO();
		CancelacionAnticipadaOutView cancelacionAnticipadaOutView = new CancelacionAnticipadaOutView();

		Mockito.when(prestamoBO.cancelarAnticipadamente(Mockito.any(CancelacionAnticipadaRequestDTO.class))).thenReturn(cancelacionAnticipadaResponse);
		Mockito.when(cancelacionAnticipadaMapper.from(cancelacionAnticipadaResponse)).thenReturn(cancelacionAnticipadaOutView);
		Mockito.when(respuestaFactory.crearRespuestaOk(cancelacionAnticipadaOutView)).thenReturn(respuestaExitosa);
		Respuesta response = cancelacionAnticipadaManager.cancelarAnticipadamente(numeroPrestamo, cancelacionAnticipadaInView);
		assertEquals(respuestaExitosa, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void given_aRestException_when_pagar_returnRespuestaERROR() throws DAOException {
		String numeroPrestamo = "1234567890";
		CancelacionAnticipadaInView cancelacionAnticipadaInView = new CancelacionAnticipadaInView();

		Respuesta respuestaError = Mockito.mock(Respuesta.class);
		GenericRestResponseDto genericRestResponseDto = new GenericRestResponseDto();
		GenericRestException genericRestException = new GenericRestException(genericRestResponseDto);

		Mockito.when(prestamoBO.cancelarAnticipadamente(Mockito.any(CancelacionAnticipadaRequestDTO.class))).thenThrow(genericRestException);

		Mockito.when(respuestaFactory.crearRespuestaErrorPersonalizado((Class<GenericRestResponseDto>) Mockito.any(),
				Mockito.anyString(), Mockito.anyString())).thenReturn(respuestaError);
		Respuesta response = cancelacionAnticipadaManager.cancelarAnticipadamente(numeroPrestamo, cancelacionAnticipadaInView);
		assertEquals(respuestaError, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void given_anExepcion_when_pagar_returnRespuestaERROR() throws DAOException {
		String numeroPrestamo = "1234567890";
		CancelacionAnticipadaInView cancelacionAnticipadaInView = new CancelacionAnticipadaInView();

		Respuesta respuestaError = Mockito.mock(Respuesta.class);

		DAOException daoException = new DAOException();

		Mockito.when(prestamoBO.cancelarAnticipadamente(Mockito.any(CancelacionAnticipadaRequestDTO.class))).thenThrow(daoException);

		Mockito.when(respuestaFactory.crearRespuestaErrorPersonalizado((Class<GenericRestResponseDto>) Mockito.any(),
				Mockito.anyString(), Mockito.anyString())).thenReturn(respuestaError);
		Respuesta response = cancelacionAnticipadaManager.cancelarAnticipadamente(numeroPrestamo, cancelacionAnticipadaInView);
		assertEquals(respuestaError, response);
	}

}
