package ar.com.santanderrio.obp.servicios.prestamos.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.ServletRequestEvent;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextListener;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.prestamos.constants.Constants;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.dto.LiquidacionResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractPrestamosBffDAOImplTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	abstract PrestamosBffDAO getPrestamosDao();

	@Mock
	protected RestWebClient restWebClient;

	@Mock
	protected WebClient webClient;

	@Mock
	protected SesionParametros sesionParametros;

	@Mock
	protected MockHttpServletRequest httpServletRequest;

	@InjectMocks
	protected RequestContextListener listener;

	@Before
	public void init() throws DAOException {
		MockitoAnnotations.initMocks(this);
		ServletRequestEvent event = Mockito.mock(ServletRequestEvent.class);
		Mockito.when(event.getServletRequest()).thenReturn(httpServletRequest);
		listener.requestInitialized(event);
		Mockito.when(httpServletRequest.getAttribute(Constants.X_SAN_CORRELATION_ID_HEADER)).thenReturn(null);
		Mockito.when(httpServletRequest.getHeader(Constants.X_SAN_SESSION_ID_HEADER)).thenReturn("session-id");
		Mockito.when(webClient.type(Matchers.any(String.class))).thenReturn(webClient);
		Mockito.when(webClient.acceptEncoding(Matchers.any(String.class))).thenReturn(webClient);
		Mockito.when(webClient.accept(Matchers.any(String.class))).thenReturn(webClient);
		Mockito.when(webClient.header(Matchers.any(String.class), Matchers.any())).thenReturn(webClient);

		Mockito.when(sesionParametros.getJwt()).thenReturn("el-token-jwt");
		Mockito.when(restWebClient.obtenerClienteRest(Matchers.any(String.class))).thenReturn(webClient);
	}

	@Test
	public void encolarPrestamoOk() throws DAOException {
		EncolamientoRequestDTO mockRequestDTO = Mockito.mock(EncolamientoRequestDTO.class);
		EncolamientoResponseDTO mockResponseDTO = Mockito.mock(EncolamientoResponseDTO.class);

		Response mockResponse = Mockito.mock(Response.class);
		Mockito.when(mockResponse.readEntity(EncolamientoResponseDTO.class)).thenReturn(mockResponseDTO);

		Mockito.when(mockResponseDTO.getEstado()).thenReturn("OK");
		Mockito.when(mockResponseDTO.getId()).thenReturn("id-prestamo-encolado");

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.path(Mockito.anyString()).post(Mockito.any(EncolamientoRequestDTO.class)))
				.thenReturn(mockResponse);
		EncolamientoResponseDTO response = getPrestamosDao().encolarPrestamo(mockRequestDTO);
		assertNotNull(response);
		assertEquals("OK", response.getEstado());
		assertEquals("id-prestamo-encolado", response.getId());
	}

	@Test()
	public void encolarPrestamoConErrorThrowsGenericRestException() throws DAOException {
		EncolamientoRequestDTO mockRequestDTO = Mockito.mock(EncolamientoRequestDTO.class);
		GenericRestResponseDto mockResponseDTO = Mockito.mock(GenericRestResponseDto.class);

		Response mockResponse = Mockito.mock(Response.class);
		Mockito.when(mockResponse.getStatus()).thenReturn(400);
		Mockito.when(mockResponse.readEntity(GenericRestResponseDto.class)).thenReturn(mockResponseDTO);

		Mockito.when(mockResponseDTO.getCode()).thenReturn("error");
		Mockito.when(mockResponseDTO.getMessage()).thenReturn("message error");

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.post(Mockito.any(EncolamientoRequestDTO.class))).thenReturn(mockResponse);

		thrown.expect(GenericRestException.class);
		getPrestamosDao().encolarPrestamo(mockRequestDTO);
	}

	@SuppressWarnings("unchecked")
	@Test()
	public void encolarPrestamoConErrorThrowsDAOException() throws DAOException {
		EncolamientoRequestDTO mockRequestDTO = Mockito.mock(EncolamientoRequestDTO.class);
		GenericRestResponseDto mockResponseDTO = Mockito.mock(GenericRestResponseDto.class);

		Response mockResponse = Mockito.mock(Response.class);
		Mockito.when(mockResponse.getStatus()).thenReturn(400);
		Mockito.when(mockResponse.readEntity(GenericRestResponseDto.class)).thenThrow(NullPointerException.class);

		Mockito.when(mockResponseDTO.getCode()).thenReturn("error");
		Mockito.when(mockResponseDTO.getMessage()).thenReturn("message error");

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.post(Mockito.any(EncolamientoRequestDTO.class))).thenReturn(mockResponse);

		thrown.expect(DAOException.class);
		getPrestamosDao().encolarPrestamo(mockRequestDTO);
	}

	@Test()
	public void encolarPrestamoErrorConCorrelationIdPreviamenteGenerado() throws DAOException {
		EncolamientoRequestDTO mockRequestDTO = Mockito.mock(EncolamientoRequestDTO.class);
		GenericRestResponseDto mockResponseDTO = Mockito.mock(GenericRestResponseDto.class);

		Response mockResponse = Mockito.mock(Response.class);
		Mockito.when(mockResponse.getStatus()).thenReturn(400);
		Mockito.when(mockResponse.readEntity(GenericRestResponseDto.class)).thenReturn(mockResponseDTO);
		Mockito.when(httpServletRequest.getAttribute(Constants.X_SAN_CORRELATION_ID_HEADER))
				.thenReturn("un-correlation-id");

		Mockito.when(mockResponseDTO.getCode()).thenReturn("error");
		Mockito.when(mockResponseDTO.getMessage()).thenReturn("message error");

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.post(Mockito.any(EncolamientoRequestDTO.class))).thenReturn(mockResponse);

		thrown.expect(GenericRestException.class);
		getPrestamosDao().encolarPrestamo(mockRequestDTO);
	}

	@Test
	public void liquidarPrestamoOk() throws DAOException {
		LiquidacionResponseDTO mockResponseDTO = Mockito.mock(LiquidacionResponseDTO.class);

		Response mockResponse = Mockito.mock(Response.class);
		Mockito.when(mockResponse.readEntity(LiquidacionResponseDTO.class)).thenReturn(mockResponseDTO);

		Mockito.when(mockResponseDTO.getEstado()).thenReturn("OK");
		Mockito.when(mockResponseDTO.getNumeroPrestamo()).thenReturn("0123456789");

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.path(Mockito.anyString()).post(Mockito.any())).thenReturn(mockResponse);
		LiquidacionResponseDTO response = getPrestamosDao().liquidarPrestamo(TipoOfertaEnum.PREACORDADO, "00011012");
		assertNotNull(response);
		assertEquals("OK", response.getEstado());
		assertEquals("0123456789", response.getNumeroPrestamo());
	}

}
