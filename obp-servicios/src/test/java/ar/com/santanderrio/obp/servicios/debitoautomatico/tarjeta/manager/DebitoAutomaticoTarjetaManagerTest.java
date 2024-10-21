/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.manager;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.bo.DebitoAutomaticoTarjetaBO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DatoClienteDebitoTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DebitoAutomaticoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.manager.impl.DebitoAutomaticoTarjetaManagerImpl;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock.DebitoAutomaticoTarjetaDTOMock;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock.HashDebitoAutomaticoTarjetaViewMock;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock.ParametroAdhesionDebitoTarjetaInViewMock;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ComprobanteDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class DebitoAutomaticoTarjetaManagerTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class DebitoAutomaticoTarjetaManagerTest {

	/** The debito automatico tarjeta manager. */
	@InjectMocks
	private DebitoAutomaticoTarjetaManagerImpl debitoAutomaticoTarjetaManager;

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The debito automatico tarjeta BO. */
	@Mock
	private DebitoAutomaticoTarjetaBO debitoAutomaticoTarjetaBO;

	/** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/**
	 * Obtener adhesion debito tarjeta OK visa.
	 */
	@Test
	public void obtenerAdhesionDebitoTarjetaOKVisaTest() {
		Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = respuestaFactory
				.crearRespuestaOk(DebitoAutomaticoTarjetaDTO.class, DebitoAutomaticoTarjetaDTOMock.completarInfo());

		Mockito.when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
		Mockito.when(sesionParametros.getValidacionHash())
				.thenReturn(HashUtils.obtenerHash(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInVisa()));
		Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
		Mockito.when(debitoAutomaticoTarjetaBO.obtenerAdhesionDebitoTarjeta(Matchers.any(Cliente.class),
				Matchers.any(DatoClienteDebitoTarjetaInDTO.class), Matchers.anyString())).thenReturn(respuestaDTO);
		Mockito.when(estadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(Boolean.TRUE);

		Respuesta<ComprobanteDebitoAutomaticoTarjetaView> respuestaView = debitoAutomaticoTarjetaManager
				.obtenerAdhesionDebitoTarjeta(ParametroAdhesionDebitoTarjetaInViewMock.completarInfoParametrosVisa());

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
		Assert.assertEquals("20-30321396-5", respuestaView.getRespuesta().getCuitEmpresa());
		Assert.assertEquals("Telecom", respuestaView.getRespuesta().getEmpresa());
		Assert.assertEquals("VISA XXXX-1234", respuestaView.getRespuesta().getMedioPago());
		Assert.assertEquals(
				"<p>La solicitud de adhesión a débito automático de <b>Telecom</b> en tarjeta de crédito <b>VISA XXXX - 1234</b> se realizó con éxito.</p>",
				respuestaView.getRespuesta().getMensajeFeedback());
		Assert.assertEquals("5796228542", respuestaView.getRespuesta().getNroCuentaCliente());
		Assert.assertEquals("3363", respuestaView.getRespuesta().getNroDeComprobante());
	}

	/**
	 * Obtener adhesion debito tarjeta OK Amex.
	 */
	@Test
	public void obtenerAdhesionDebitoTarjetaOKAmexTest() {
		Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = respuestaFactory
				.crearRespuestaOk(DebitoAutomaticoTarjetaDTO.class, DebitoAutomaticoTarjetaDTOMock.completarInfo());

		Mockito.when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
		Mockito.when(sesionParametros.getValidacionHash())
				.thenReturn(HashUtils.obtenerHash(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInAmex()));
		Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
		Mockito.when(debitoAutomaticoTarjetaBO.obtenerAdhesionDebitoTarjeta(Matchers.any(Cliente.class),
				Matchers.any(DatoClienteDebitoTarjetaInDTO.class), Matchers.anyString())).thenReturn(respuestaDTO);
		Mockito.when(estadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(Boolean.TRUE);

		Respuesta<ComprobanteDebitoAutomaticoTarjetaView> respuestaView = debitoAutomaticoTarjetaManager
				.obtenerAdhesionDebitoTarjeta(
						ParametroAdhesionDebitoTarjetaInViewMock.completarInfoParametrosAmexReintentarFalse());

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
		Assert.assertEquals("20-30321396-5", respuestaView.getRespuesta().getCuitEmpresa());
		Assert.assertEquals("Telecom", respuestaView.getRespuesta().getEmpresa());
		Assert.assertEquals("AMEX XXXX-21696", respuestaView.getRespuesta().getMedioPago());
		Assert.assertEquals(
				"<p>La solicitud de adhesión a débito automático de <b>Telecom</b> en tarjeta de crédito <b>VISA XXXX - 1234</b> se realizó con éxito.</p>",
				respuestaView.getRespuesta().getMensajeFeedback());
		Assert.assertEquals("5796228542", respuestaView.getRespuesta().getNroCuentaCliente());
		Assert.assertEquals("3363", respuestaView.getRespuesta().getNroDeComprobante());
	}

	/**
	 * Obtener adhesion debito tarjeta warning timeout.
	 */
	@Test
	public void obtenerAdhesionDebitoTarjetaWarningTimeoutTest() {
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeErrorTimeout());

		Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = respuestaFactory.crearRespuestaError(
				DebitoAutomaticoTarjetaDTO.class, StringUtils.EMPTY, TipoError.TIMEOUT,
				CodigoMensajeConstantes.ERROR_TIMEOUT_FEEDBACK_ADHESION_DEBITO_TARJETA);

		Mockito.when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
		Mockito.when(sesionParametros.getValidacionHash())
				.thenReturn(HashUtils.obtenerHash(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInAmex()));
		Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
		Mockito.when(debitoAutomaticoTarjetaBO.obtenerAdhesionDebitoTarjeta(Matchers.any(Cliente.class),
				Matchers.any(DatoClienteDebitoTarjetaInDTO.class), Matchers.anyString())).thenReturn(respuestaDTO);
		Mockito.when(estadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(Boolean.TRUE);

		Respuesta<ComprobanteDebitoAutomaticoTarjetaView> respuestaView = debitoAutomaticoTarjetaManager
				.obtenerAdhesionDebitoTarjeta(ParametroAdhesionDebitoTarjetaInViewMock.completarInfoParametrosAmex());

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaView.getEstadoRespuesta());
		Assert.assertEquals(TipoError.WARNING_TIMEOUT.getDescripcion(),
				respuestaView.getItemsMensajeRespuesta().get(0).getTipoError());
		Assert.assertEquals(
				"<b>Ocurrió un error en nuestros servicios</b>. Por favor, volvé a ingresar en unos minutos.",
				respuestaView.getItemsMensajeRespuesta().get(0).getMensaje());
	}

	/**
	 * Obtener adhesion debito tarjeta warning error generico.
	 */
	@Test
	public void obtenerAdhesionDebitoTarjetaWarningErrorGenericoTest() {
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeErrorGenerico());

		Respuesta<DebitoAutomaticoTarjetaDTO> respuestaDTO = respuestaFactory.crearRespuestaError(
				DebitoAutomaticoTarjetaDTO.class, StringUtils.EMPTY, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_FEEDBACK_ADHESION_DEBITO_TARJETA);

		Mockito.when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
		Mockito.when(sesionParametros.getValidacionHash())
				.thenReturn(HashUtils.obtenerHash(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInAmex()));
		Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
		Mockito.when(debitoAutomaticoTarjetaBO.obtenerAdhesionDebitoTarjeta(Matchers.any(Cliente.class),
				Matchers.any(DatoClienteDebitoTarjetaInDTO.class), Matchers.anyString())).thenReturn(respuestaDTO);
		Mockito.when(estadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(Boolean.TRUE);

		Respuesta<ComprobanteDebitoAutomaticoTarjetaView> respuestaView = debitoAutomaticoTarjetaManager
				.obtenerAdhesionDebitoTarjeta(
						ParametroAdhesionDebitoTarjetaInViewMock.completarInfoParametrosAmexReintentarFalse());

		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaView.getEstadoRespuesta());
		Assert.assertEquals(TipoError.WARNING_REINTENTOS.getDescripcion(),
				respuestaView.getItemsMensajeRespuesta().get(0).getTipoError());
		Assert.assertEquals(
				"<p>No pudimos realizar la solicitud de adhesión a débito automático de <b>Telecom</b>.</p>",
				respuestaView.getItemsMensajeRespuesta().get(0).getMensaje());
	}

	/**
	 * Obtener adhesion debito tarjeta error hash invalido.
	 */
	@Test
	public void obtenerAdhesionDebitoTarjetaErrorHashInvalidoTest() {
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeErrorGenerico());
		Mockito.when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
		Mockito.when(sesionParametros.getValidacionHash())
				.thenReturn(HashUtils.obtenerHash(HashDebitoAutomaticoTarjetaViewMock.completarInfoHashInAmexII()));
		Mockito.when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
		Mockito.when(estadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(Boolean.FALSE);

		Respuesta<ComprobanteDebitoAutomaticoTarjetaView> respuestaView = debitoAutomaticoTarjetaManager
				.obtenerAdhesionDebitoTarjeta(ParametroAdhesionDebitoTarjetaInViewMock.completarInfoParametrosAmex());
		Assert.assertNotNull(respuestaView);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion(),
				respuestaView.getItemsMensajeRespuesta().get(0).getTipoError());
		Assert.assertEquals(
				"<p>No pudimos realizar la solicitud de adhesión a débito automático de <b>Telecom</b>.</p>",
				respuestaView.getItemsMensajeRespuesta().get(0).getMensaje());
	}

	/**
	 * Grabar estadistica comprobante OK.
	 */
	@Test
	public void grabarEstadisticaComprobanteOKTest() {
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

		Boolean estadisticaOK = debitoAutomaticoTarjetaManager.grabarEstadisticaComprobante();

		Assert.assertNotNull(estadisticaOK);
		Assert.assertTrue(estadisticaOK);
	}

	/**
	 * Grabar estadistica comprobante error.
	 */
	@Test
	public void grabarEstadisticaComprobanteErrorTest() {
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.FALSE);

		Boolean estadisticaError = debitoAutomaticoTarjetaManager.grabarEstadisticaComprobante();

		Assert.assertNotNull(estadisticaError);
		Assert.assertFalse(estadisticaError);
	}

	/**
	 * Obtener mensaje error generico.
	 *
	 * @return the mensaje
	 */
	private Mensaje obtenerMensajeErrorGenerico() {
		Mensaje msj = new Mensaje();
		msj.setCodigo("1529");
		msj.setMensaje("<p>No pudimos realizar la solicitud de adhesión a débito automático de <b>Telecom</b>.</p>");
		return msj;
	}

	/**
	 * Obtener mensaje error timeout.
	 *
	 * @return the mensaje
	 */
	private Mensaje obtenerMensajeErrorTimeout() {
		Mensaje msj = new Mensaje();
		msj.setCodigo("1534");
		msj.setMensaje("<b>Ocurrió un error en nuestros servicios</b>. Por favor, volvé a ingresar en unos minutos.");
		return msj;
	}
}
