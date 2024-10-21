package ar.com.santanderrio.obp.servicios.prestamos.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.servlet.ServletRequestEvent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextListener;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.constants.Constants;

@RunWith(MockitoJUnitRunner.class)
public class PrestamosUtilsTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private MockHttpServletRequest httpServletRequest;

	@InjectMocks
	private RequestContextListener listener;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void obtenerSessionIdDesdeHttpServlet() throws DAOException {
		ServletRequestEvent event = Mockito.mock(ServletRequestEvent.class);
		Mockito.when(event.getServletRequest()).thenReturn(httpServletRequest);

		listener.requestInitialized(event);

		Mockito.when(httpServletRequest.getHeader(Constants.X_SAN_SESSION_ID_HEADER)).thenReturn("session-id");
		String sessionId = PrestamosUtils.getSessionId();
		assertEquals("session-id", sessionId);
		listener.requestDestroyed(event);
	}

	@Test
	public void obtenerSessionIdError() throws DAOException {
		Mockito.when(httpServletRequest.getHeader(Constants.X_SAN_SESSION_ID_HEADER)).thenReturn("session-id");
		String sessionId = PrestamosUtils.getSessionId();
		assertNull(sessionId);
	}

	@Test
	public void obtenerCorrelationIdDesdeHttpServlet() throws DAOException {
		ServletRequestEvent event = Mockito.mock(ServletRequestEvent.class);
		Mockito.when(event.getServletRequest()).thenReturn(httpServletRequest);

		listener.requestInitialized(event);

		Mockito.when(httpServletRequest.getAttribute(Constants.X_SAN_CORRELATION_ID_HEADER))
				.thenReturn("correlation-id");
		String correlationId = PrestamosUtils.getCorrelationId();
		assertEquals("correlation-id", correlationId);
		listener.requestDestroyed(event);
	}

	@Test
	public void obtenerCorrelationIdCuandoNoExiste() throws DAOException {
		ServletRequestEvent event = Mockito.mock(ServletRequestEvent.class);
		Mockito.when(event.getServletRequest()).thenReturn(httpServletRequest);

		listener.requestInitialized(event);

		Mockito.when(httpServletRequest.getHeader(Constants.X_SAN_CORRELATION_ID_HEADER)).thenReturn(null);
		String correlationId = PrestamosUtils.getCorrelationId();
		assertNotNull(correlationId);
		assertNotEquals("correlation-id", correlationId);
		listener.requestDestroyed(event);
	}

	@Test
	public void obtenerCorrelationIdFallback() throws DAOException {
		Mockito.when(httpServletRequest.getHeader(Constants.X_SAN_CORRELATION_ID_HEADER)).thenReturn(null);
		String correlationId = PrestamosUtils.getCorrelationId();
		assertNotNull(correlationId);
		assertNotEquals("correlation-id", correlationId);
	}

}
