/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.bo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.QueryTimeoutException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.bo.impl.DebitoAutomaticoTarjetaBOImpl;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dao.DebitoAutomaticoTarjetaDAO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DebitoAutomaticoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.ClienteDebitoTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock.DatoClienteDebitoTarjetaInDTOMock;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock.SolicitudAdhesionDebitoTarjetaEntityMock;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class DebitoAutomaticoTarjetaBOTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class DebitoAutomaticoTarjetaBOTest {

	/** The Constant MSJ_FEEDBACK. */
	private static final String MSJ_FEEDBACK = "<p>La solicitud de adhesión a débito automático de <b>{0}</b> en tarjeta de crédito <b>{1}</b> se realizó con éxito.</p>";

	/** The Constant DAO_EXCEPTION. */
	private static final String DAO_EXCEPTION = "Error DAO Exception.";

	/** The Constant MSJ_ERROR_GENERICO. */
	private static final String MSJ_ERROR_GENERICO = "<p>No pudimos realizar la solicitud de adhesión a débito automático de <b>{0}</b>.</p>";

	/** The Constant QUERY_TIMEOUT_EXCEPTION. */
	private static final String QUERY_TIMEOUT_EXCEPTION = "Error QueryTimeoutException.";

	/** The Constant MSJ_ERROR_TIMEOUT. */
	private static final String MSJ_ERROR_TIMEOUT = "Mensaje error timeout.";

	/** The debito automatico tarjeta BO. */
	@InjectMocks
	private DebitoAutomaticoTarjetaBOImpl debitoAutomaticoTarjetaBO;

	/** The debito automatico tarjeta DAO. */
	@Mock
	private DebitoAutomaticoTarjetaDAO debitoAutomaticoTarjetaDAO;

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;
	
	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/**
	 * Dado cliente dato cliente debito tarjeta in DTOY nro tarjeta enmascarado cuando invoca obtener adhesion debito tarjeta obtengo respuesta OK debito automatico tarjeta DTO.
	 *
	 * @throws DAOException the DAO exception
	 * @throws QueryTimeoutException the query timeout exception
	 */
	@Test
	public void dadoClienteDatoClienteDebitoTarjetaInDTOYNroTarjetaEnmascaradoCuandoInvocaObtenerAdhesionDebitoTarjetaObtengoRespuestaOKDebitoAutomaticoTarjetaDTO()
			throws DAOException, QueryTimeoutException {
		Mockito.when(
				debitoAutomaticoTarjetaDAO.solicitarAdhesionTarjeta(Matchers.any(ClienteDebitoTarjetaInEntity.class)))
				.thenReturn(SolicitudAdhesionDebitoTarjetaEntityMock.completarInfo());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeFeedback());
		
		Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = debitoAutomaticoTarjetaBO.obtenerAdhesionDebitoTarjeta(
				ClienteMock.infoCliente(), DatoClienteDebitoTarjetaInDTOMock.completarInfo(), "VISA XXXX - 1234");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
		Assert.assertNotNull(respuestaDTO.getRespuesta());
		Assert.assertEquals("3363", respuestaDTO.getRespuesta().getComprobante());
		Assert.assertEquals("<p>La solicitud de adhesión a débito automático de <b>Telecom</b> en tarjeta de crédito <b>VISA XXXX - 1234</b> se realizó con éxito.</p>", respuestaDTO.getRespuesta().getMensajeFeedback());
	}
	
	/**
	 * Dado cliente dato cliente debito tarjeta in DTOY nro tarjeta enmascarado cuando invoca obtener adhesion debito tarjeta obtengo respuesta error generico.
	 *
	 * @throws DAOException the DAO exception
	 * @throws QueryTimeoutException the query timeout exception
	 */
	@Test
	public void dadoClienteDatoClienteDebitoTarjetaInDTOYNroTarjetaEnmascaradoCuandoInvocaObtenerAdhesionDebitoTarjetaObtengoRespuestaErrorGenerico()
			throws DAOException, QueryTimeoutException {
		Mockito.when(
				debitoAutomaticoTarjetaDAO.solicitarAdhesionTarjeta(Matchers.any(ClienteDebitoTarjetaInEntity.class)))
				.thenThrow(new DAOException(DAO_EXCEPTION));
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeErrorGenerico());
		
		Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = debitoAutomaticoTarjetaBO.obtenerAdhesionDebitoTarjeta(
				ClienteMock.infoCliente(), DatoClienteDebitoTarjetaInDTOMock.completarInfo(), "VISA XXXX - 1234");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
		Assert.assertNull(respuestaDTO.getRespuesta());
		Assert.assertEquals("<p>No pudimos realizar la solicitud de adhesión a débito automático de <b>Telecom</b>.</p>", respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(), respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
	}
	
	/**
	 * Dado cliente dato cliente debito tarjeta in DTOY nro tarjeta enmascarado cuando invoca obtener adhesion debito tarjeta obtengo respuesta error timeout.
	 *
	 * @throws DAOException the DAO exception
	 * @throws QueryTimeoutException the query timeout exception
	 */
	@Test
	public void dadoClienteDatoClienteDebitoTarjetaInDTOYNroTarjetaEnmascaradoCuandoInvocaObtenerAdhesionDebitoTarjetaObtengoRespuestaErrorTimeout()
			throws DAOException, QueryTimeoutException {
		Mockito.when(
				debitoAutomaticoTarjetaDAO.solicitarAdhesionTarjeta(Matchers.any(ClienteDebitoTarjetaInEntity.class)))
				.thenThrow(new QueryTimeoutException(QUERY_TIMEOUT_EXCEPTION));
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeErrorTimeout());
		
		Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = debitoAutomaticoTarjetaBO.obtenerAdhesionDebitoTarjeta(
				ClienteMock.infoCliente(), DatoClienteDebitoTarjetaInDTOMock.completarInfo(), "VISA XXXX - 1234");

		Assert.assertNotNull(respuestaDTO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
		Assert.assertNull(respuestaDTO.getRespuesta());
		Assert.assertEquals("Mensaje error timeout.", respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
		Assert.assertEquals(TipoError.TIMEOUT.getDescripcion(), respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
	}

/**
 * Obtener mensaje error timeout.
 *
 * @return the mensaje
 */
//	TODO CORREGIR COD ERROR DE MSJ y msj
	private Mensaje obtenerMensajeErrorTimeout() {
		Mensaje msj = new Mensaje();
		msj.setCodigo("1529");
		msj.setMensaje(MSJ_ERROR_TIMEOUT);
		return msj;
	}
	
	/**
	 * Obtener mensaje error generico.
	 *
	 * @return the mensaje
	 */
	private Mensaje obtenerMensajeErrorGenerico() {
		Mensaje msj = new Mensaje();
		msj.setCodigo("1529");
		msj.setMensaje(MSJ_ERROR_GENERICO);
		return msj;
	}
	
	/**
	 * Obtener mensaje feedback.
	 *
	 * @return the mensaje
	 */
	private Mensaje obtenerMensajeFeedback() {
		Mensaje msj = new Mensaje();
		msj.setCodigo("1528");
		msj.setMensaje(MSJ_FEEDBACK);
		return msj;
	}
}
