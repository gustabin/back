package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.CasuisticaEdicionDestinatarioImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.EdicionDestinatarioBOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class EdicionDestinatarioBOTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class EdicionDestinatarioBOTest {

	/** The editor. */
	@InjectMocks
	private EdicionDestinatarioBO editor = new EdicionDestinatarioBOImpl();

	/** The agenda destinatario DAO. */
	@Mock
	private AgendaDestinatarioDAO agendaDestinatarioDAO;

	/** The casuistica. */
	@InjectMocks
	@Spy
	private CasuisticaEdicionDestinatario casuistica = new CasuisticaEdicionDestinatarioImpl();

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The mensaje BO. */
	@Mock
	private MensajeBO mensajeBO;

	/** The Constant CODIGO_OK. */
	private static final String CODIGO_OK = "00000000";

	/**
	 * Crear respuesta otros bancos ok test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void crearRespuestaOtrosBancosOkTest() throws DAOException {
		Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
				Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(crearSalidaError());
		Respuesta<ComprobanteAltaDestinatarioDTO> res = editor.editarDestinatario(crearDestinatario("OB "),
				"190.12.1.133", new Cliente(), crearDatos());
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
		Assert.assertEquals("14:02", res.getRespuesta().getHora());
		Assert.assertEquals("9CA6FE4", res.getRespuesta().getNroComprobante());
	}

	/**
	 * Crear respuesta rio ok test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void crearRespuestaRioOkTest() throws DAOException {
		Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
				Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(crearSalidaError());
		Respuesta<ComprobanteAltaDestinatarioDTO> res = editor.editarDestinatario(crearDestinatario("RIO"),
				"190.12.1.133", new Cliente(), crearDatos());
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
		Assert.assertEquals("14:02", res.getRespuesta().getHora());
		Assert.assertEquals("9CA6FE4", res.getRespuesta().getNroComprobante());
	}

	/**
	 * Crear respuesta envio efectivo ok test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void crearRespuestaEnvioEfectivoOkTest() throws DAOException {
		Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
				Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(crearSalidaError());
		Respuesta<ComprobanteAltaDestinatarioDTO> res = editor.editarDestinatario(crearDestinatario("EXT"),
				"190.12.1.133", new Cliente(), crearDatos());
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
		Assert.assertEquals("14:02", res.getRespuesta().getHora());
		Assert.assertEquals("9CA6FE4", res.getRespuesta().getNroComprobante());
	}

	/**
	 * Crear salida error.
	 *
	 * @return the agenda destinatario out entity
	 */
	private AgendaDestinatarioOutEntity crearSalidaError() {
		AgendaDestinatarioOutEntity salida = new AgendaDestinatarioOutEntity();
		salida.setCodigoRetornoExtendido(CODIGO_OK);
		salida.setFecha("16/02/1993");
		salida.setHora("14:02");
		salida.setNroComprobante("9CA6FE4");
		return salida;
	}

	/**
	 * Crear respuesta otros bancos DAO exception test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void crearRespuestaOtrosBancosDAOExceptionTest() throws DAOException {
		Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
				Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenThrow(new DAOException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Respuesta<ComprobanteAltaDestinatarioDTO> res = editor.editarDestinatario(crearDestinatario("OB "),
				"190.12.1.133", new Cliente(), crearDatos());
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals("ERROR_SERVICIO", res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Crear respuesta rio DAO exception test.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void crearRespuestaRioDAOExceptionTest() throws DAOException {
		Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
				Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenThrow(new DAOException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Respuesta<ComprobanteAltaDestinatarioDTO> res = editor.editarDestinatario(crearDestinatario("RIO"),
				"190.12.1.133", new Cliente(), crearDatos());
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals("ERROR_SERVICIO", res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	/**
	 * Crear datos.
	 *
	 * @return the confirmacion alta destinatario view
	 */
	private ConfirmacionAltaDestinatarioView crearDatos() {
		ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
		datosEntrada.setDireccionCorreo("pepe.10pepe@gmail.com");
		datosEntrada.setReferencia("cuenta pepe");
		datosEntrada.setId("12");
		datosEntrada.setTitular("pepe");
		return datosEntrada;
	}

	/**
	 * Crear destinatario.
	 *
	 * @param tipoAgenda
	 *            the tipo agenda
	 * @return the destinatario entity
	 */
	private DestinatarioEntity crearDestinatario(String tipoAgenda) {
		DestinatarioEntity destinatario = new DestinatarioEntity();
		destinatario.setTipoAgendaOcurrencia(tipoAgenda);
		return destinatario;
	}

}
