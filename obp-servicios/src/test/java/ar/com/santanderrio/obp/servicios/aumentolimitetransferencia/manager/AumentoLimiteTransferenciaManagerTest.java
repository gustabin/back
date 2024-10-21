package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.manager;

import static org.mockito.Mockito.times;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.api.calendar.CalendarApi;
import ar.com.santanderrio.obp.servicios.api.calendar.dto.CalendarApiDateDTO;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.customers.CustomersApi;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.ScoringApi;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.feature.AumentoLimiteTransferenciaInOutViewFeature;
import ar.com.santanderrio.obp.servicios.biocatch.BiocatchManager;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityName;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityType;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo.AumentoLimiteTransferenciaBO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaInDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaOutDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.AumentoLimiteTransferenciasManager;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.impl.AumentoLimiteTransferenciasManagerImpl;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AgendaDestinatarioLimiteTransferenciasView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoTransferenciaView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

@RunWith(MockitoJUnitRunner.class)
public class AumentoLimiteTransferenciaManagerTest {

	@InjectMocks
	private AumentoLimiteTransferenciasManager aumentoLimiteTransferenciasManager = new AumentoLimiteTransferenciasManagerImpl();

	@Mock
	private EstadisticaManager estadisticaManager;

	@Mock
	private AumentoLimiteTransferenciaBO aumentoLimiteTransferenciaBO;

	@Mock
	private DesafioOperacionRSA<AumentoLimiteTransferenciaInOutView> desafioOperacionRSA;

	@Mock
	private CuentaManager cuentaManager;

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private MensajeDAO mensajeDAO;

	@Mock
	private MensajeManager mensajeManager;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private SesionParametros sesionParametros;

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private AutentificacionManager autentificacionManager;

	@Mock
	private ClienteManager clienteManager;

	@Mock
	private ScoringApi scoringApi;

	@Mock
	private BiocatchManager biocatchManager;

	@Mock
	private CustomersApi customersApi;

	@Mock
	private CalendarApi calendarApi;


	Mensaje mensaje = new Mensaje();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void verificarTokenAsociadoTest() {
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(true));
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Respuesta<AgendaDestinatarioLimiteTransferenciasView> respuestaTieneToken = aumentoLimiteTransferenciasManager
				.verificarTokenAsociado();
		Assert.assertEquals(respuestaTieneToken.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void verificarTokenNoAsociadoTest() {
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Respuesta<AgendaDestinatarioLimiteTransferenciasView> respuestaTieneToken = aumentoLimiteTransferenciasManager
				.verificarTokenAsociado();
		Assert.assertEquals(respuestaTieneToken.getEstadoRespuesta(), EstadoRespuesta.WARNING);
	}

	@Test
	public void obtenerAgendaDestinatariosOKTest() {
		Respuesta<AgendaDestinatarioLimiteTransferenciasView> respuestaFinal = new Respuesta<AgendaDestinatarioLimiteTransferenciasView>();
		Respuesta<AgendaDestinatarioView> respuestaAgendaDestinatariosManager = new Respuesta<AgendaDestinatarioView>();
		respuestaAgendaDestinatariosManager.setRespuesta(new AgendaDestinatarioView(AumentoLimiteTransferenciaInOutViewFeature.obtenerDestinatarioDTO()));
		respuestaAgendaDestinatariosManager.setEstadoRespuesta(EstadoRespuesta.OK);
		List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
		respuestaAgendaDestinatariosManager.setItemMensajeRespuesta(mensajesList);
		respuestaAgendaDestinatariosManager.setRespuestaVacia(false);
		respuestaAgendaDestinatariosManager.setSkipLog(true);
		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
		respuestaFinal = aumentoLimiteTransferenciasManager
				.obtenerAgendaDestinatarios(respuestaAgendaDestinatariosManager);
		Assert.assertEquals(respuestaFinal.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertNotNull(respuestaFinal.getRespuesta().getListaDestinatarios());
	}

	@Test
	public void obtenerAgendaDestinatariosERRORTest() {
		Respuesta<AgendaDestinatarioLimiteTransferenciasView> respuestaFinal = new Respuesta<AgendaDestinatarioLimiteTransferenciasView>();
		Respuesta<AgendaDestinatarioView> respuestaAgendaDestinatariosManager = new Respuesta<AgendaDestinatarioView>();
		respuestaAgendaDestinatariosManager.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
		respuestaAgendaDestinatariosManager.setItemMensajeRespuesta(mensajesList);
		respuestaAgendaDestinatariosManager.setRespuestaVacia(false);
		respuestaAgendaDestinatariosManager.setSkipLog(true);
		respuestaFinal = aumentoLimiteTransferenciasManager
				.obtenerAgendaDestinatarios(respuestaAgendaDestinatariosManager);
		Assert.assertEquals(respuestaFinal.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		Assert.assertNull(respuestaFinal.getRespuesta().getListaDestinatarios());
	}

	@Test
	public void obtenerInformacionDestinatarioOK() throws IllegalAccessException {

		DestinatarioView destinatarioView = new DestinatarioView(AumentoLimiteTransferenciaInOutViewFeature.obtenerDestinatarioAgendaDto());
		Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
		CuentasView cuentasView = new CuentasView();
		cuentasView.setCuentas(AumentoLimiteTransferenciaInOutViewFeature.obtenerCuentasAdhesionDebito());
		respuestaCuentasView.setRespuesta(cuentasView);
		respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasView);
		Respuesta<AumentoTransferenciaView> respuestaFinal = new Respuesta<AumentoTransferenciaView>();
		Respuesta<TransferenciaView> respuestaTransferenciaManager = new Respuesta<TransferenciaView>();
		TransferenciaView transferenciaRespuesta = new TransferenciaView();
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		respuestaTransferenciaManager.setRespuesta(transferenciaRespuesta);
		respuestaTransferenciaManager.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaTransferenciaManager.setItemMensajeRespuesta(AumentoLimiteTransferenciaInOutViewFeature.obtenerListaMensajesRespuesta());
		respuestaTransferenciaManager.setRespuestaVacia(false);
		respuestaTransferenciaManager.setSkipLog(false);
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinPesos", "350000", true);
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinDolares", "19445", true);
		respuestaFinal = aumentoLimiteTransferenciasManager.obtenerInformacionDestinatario(destinatarioView);
		Assert.assertEquals(respuestaFinal.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void altaSolicitudAumentoLimiteTransferenciaSinDesafioRespuestaOK()
			throws IllegalAccessException, DAOException {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinPesos", "350000", true);
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "valorDesafio", 2, true);
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));
		Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(null);

		Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
		autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(
						autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(autentificacion);

		Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = new Respuesta<AutentificacionDTO>();
		respuestaAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(autentificacionManager
						.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(respuestaAutentificacionDTO);
		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaView = new Respuesta<AumentoLimiteTransferenciaInOutView>();
		respuestaView.setRespuesta(view);
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(AumentoLimiteTransferenciaInOutView.class),
				Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class)))
				.thenReturn(respuestaView);

		Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaBO = new Respuesta<AumentoLimiteTransferenciaOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AumentoLimiteTransferenciaOutDTO respuesta = new AumentoLimiteTransferenciaOutDTO();
		respuesta.setNroGestion("1265361523");
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(aumentoLimiteTransferenciaBO
						.altaSolicitudAumentoLimiteTransferencia(Matchers.any(AumentoLimiteTransferenciaInDTO.class)))
				.thenReturn(respuestaBO);
		when(sesionParametros.getValidacionHashTIS()).thenReturn(HashUtils.obtenerHash(AumentoLimiteTransferenciaInOutViewFeature.crearMapaDeLaVista(AumentoLimiteTransferenciaInOutViewFeature.obtenerView())));

		when(biocatchManager.getScore(
						sesionCliente.getCliente().getNup(),
						sesionCliente.getIpCliente(),
						ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
						ActivityType.CHANGE_ACCOUNT_LIMIT)).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildBiocatchResponseDataDTO());

		when(customersApi.getCustomerById(
				sesionCliente.getCliente().getNup()))
				.thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
		when(scoringApi.getScoring(anyString())).thenReturn(1f);
		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
	}


	@Test
	public void altaSolicitudAumentoLimiteTransferenciaConDesafioRespuestaWARNING()
			throws IllegalAccessException, DAOException {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinPesos", "350000", true);
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "valorDesafio", 2, true);
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));

		Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(null);

		Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
		autentificacion.setEstadoRespuesta(EstadoRespuesta.WARNING);
		AutentificacionDTO authDTO = new AutentificacionDTO();
		authDTO.setTipoDesafio(TipoDesafioEnum.TOKEN);
		autentificacion.setRespuesta(authDTO);
		autentificacion.setItemMensajeRespuesta(AumentoLimiteTransferenciaInOutViewFeature.obtenerListaMensajesRespuesta());
		Mockito.when(
						autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(autentificacion);

		Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = new Respuesta<AutentificacionDTO>();
		respuestaAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(autentificacionManager
						.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(respuestaAutentificacionDTO);

		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaView = new Respuesta<AumentoLimiteTransferenciaInOutView>();
		respuestaView.setRespuesta(view);
		respuestaView.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(AumentoLimiteTransferenciaInOutView.class),
				Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class)))
				.thenReturn(respuestaView);

		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(AumentoLimiteTransferenciaInOutViewFeature.crearMapaDeLaVista(view)));

		Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaBO = new Respuesta<AumentoLimiteTransferenciaOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AumentoLimiteTransferenciaOutDTO respuesta = new AumentoLimiteTransferenciaOutDTO();
		respuesta.setNroGestion("1265361523");
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(aumentoLimiteTransferenciaBO
						.altaSolicitudAumentoLimiteTransferencia(Matchers.any(AumentoLimiteTransferenciaInDTO.class)))
				.thenReturn(respuestaBO);
		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
		when(scoringApi.getScoring(anyString())).thenReturn(1f);

		when(biocatchManager.getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
				ActivityType.CHANGE_ACCOUNT_LIMIT)).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildBiocatchResponseDataDTO());

		when(customersApi.getCustomerById(
				sesionCliente.getCliente().getNup()))
				.thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);

		// TODO reemplazar al habilitar RSA en el manager
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaFinal.getEstadoRespuesta());
		// Assert.assertEquals(EstadoRespuesta.OK,
		// respuestaFinal.getEstadoRespuesta());
	}

	@Test
	public void altaSolicitudAumentoLimiteTransferenciaFechaFueraDeRangoERROR()
			throws IllegalAccessException, DAOException {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;

		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		DateTime fecha = new DateTime(new Date());
		view.setFechaEjecucion(ISBANStringUtils.formatearFecha(fecha.plusDays(3).toDate()));

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);
		Assert.assertEquals(respuestaFinal.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void altaSolicitudAumentoLimiteTransferenciaImporteEnPesosPorDebajoDelLimiteMinimoERROR()
			throws IllegalAccessException, DAOException {
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinPesos", "350000", true);
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;

		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		view.setImporte("200000");

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);
		Assert.assertEquals(respuestaFinal.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void altaSolicitudAumentoLimiteTransferenciaImporteEnDolaresPorDebajoDelLimiteMinimoERROR()
			throws IllegalAccessException, DAOException {
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinDolares", "19445", true);
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;

		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		view.setMoneda("dolares");
		view.setImporte("18000");

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);
		Assert.assertEquals(respuestaFinal.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void generarComprobanteAumentoLimiteTransferenciaOK() {
		Mockito.when(sesionParametros.getDatosComprobanteAumentoLimiteTransferencia())
				.thenReturn(new DatosComprobanteAumentoLimiteTransferencia());
		Reporte reporte = new Reporte();
		reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		reporte.setNombre("NOMBRE.PDF");
		Respuesta<Reporte> respuestaBO = new Respuesta<Reporte>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaBO.setRespuesta(reporte);
		Mockito.when(aumentoLimiteTransferenciaBO.generarComprobanteAumentoLimiteTransferencia(
				Matchers.any(DatosComprobanteAumentoLimiteTransferencia.class))).thenReturn(respuestaBO);

		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
		respuesta = aumentoLimiteTransferenciasManager.generarComprobanteAumentoLimiteTransferencia();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void generarComprobanteAumentoLimiteTransferenciaERROR() {
		Mockito.when(sesionParametros.getDatosComprobanteAumentoLimiteTransferencia())
				.thenReturn(new DatosComprobanteAumentoLimiteTransferencia());
		Respuesta<Reporte> respuestaBO = new Respuesta<Reporte>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(aumentoLimiteTransferenciaBO.generarComprobanteAumentoLimiteTransferencia(
				Matchers.any(DatosComprobanteAumentoLimiteTransferencia.class))).thenReturn(respuestaBO);
		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
		respuesta = aumentoLimiteTransferenciasManager.generarComprobanteAumentoLimiteTransferencia();

		Assert.assertNull(respuesta.getRespuesta());
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	@Test
	public void altaSolicitudAumentoLimiteTransferencia_obtenerValoresBiocatchOK()
			throws IllegalAccessException, DAOException {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinPesos", "350000", true);
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "valorDesafio", 2, true);
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));
		Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(null);

		Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
		autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(
						autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(autentificacion);

		Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = new Respuesta<AutentificacionDTO>();
		respuestaAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(autentificacionManager
						.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(respuestaAutentificacionDTO);
		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaView = new Respuesta<AumentoLimiteTransferenciaInOutView>();
		respuestaView.setRespuesta(view);
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(AumentoLimiteTransferenciaInOutView.class),
				Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class)))
				.thenReturn(respuestaView);

		Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaBO = new Respuesta<AumentoLimiteTransferenciaOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AumentoLimiteTransferenciaOutDTO respuesta = new AumentoLimiteTransferenciaOutDTO();
		respuesta.setNroGestion("1265361523");
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(aumentoLimiteTransferenciaBO
						.altaSolicitudAumentoLimiteTransferencia(Matchers.any(AumentoLimiteTransferenciaInDTO.class)))
				.thenReturn(respuestaBO);
		when(sesionParametros.getValidacionHashTIS()).thenReturn(HashUtils.obtenerHash(AumentoLimiteTransferenciaInOutViewFeature.crearMapaDeLaVista(AumentoLimiteTransferenciaInOutViewFeature.obtenerView())));

		when(biocatchManager.getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
				ActivityType.CHANGE_ACCOUNT_LIMIT)).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildBiocatchResponseDataDTO());

		when(customersApi.getCustomerById(
				sesionCliente.getCliente().getNup()))
				.thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);

		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());

		BiocatchResponseDataDTO biocatchResponseDataDTO = respuestaFinal.getRespuesta().getBiocatchResponseDataDTO();

		Assert.assertEquals(30, biocatchResponseDataDTO.getScore().longValue());
		Assert.assertEquals("Allow", biocatchResponseDataDTO.getPolicyAction());
		Assert.assertEquals(3502, biocatchResponseDataDTO.getPolicyID().longValue());
		Assert.assertEquals("DEF_OBP_PERSONAS", biocatchResponseDataDTO.getPolicyName());
		Assert.assertFalse(biocatchResponseDataDTO.isBot());
		Assert.assertFalse(biocatchResponseDataDTO.isEmulator());
		Assert.assertFalse(biocatchResponseDataDTO.isMobileRat());
		Assert.assertFalse(biocatchResponseDataDTO.isRat());
		Assert.assertFalse(biocatchResponseDataDTO.isRecentRat());
		Assert.assertEquals(57, biocatchResponseDataDTO.getVoiceScam().longValue());
		Assert.assertEquals(0, biocatchResponseDataDTO.getMax30DayScore().longValue());
		Assert.assertEquals("R102", biocatchResponseDataDTO.getRiskFactor().get(0));
		Assert.assertEquals("R103", biocatchResponseDataDTO.getRiskFactor().get(1));
		Assert.assertEquals("", biocatchResponseDataDTO.getRiskFactor().get(2));
		Assert.assertEquals("G011", biocatchResponseDataDTO.getGenFactor().get(0));
		Assert.assertEquals("G030", biocatchResponseDataDTO.getGenFactor().get(1));
		Assert.assertEquals("", biocatchResponseDataDTO.getGenFactor().get(2));

		Mockito.verify(biocatchManager, times(1)).getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
				ActivityType.CHANGE_ACCOUNT_LIMIT);
	}

	@Test
	public void altaSolicitudAumentoLimiteTransferencia_lanzaDAOException_luegoObtieneBiocatchNull()
			throws IllegalAccessException, DAOException {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinPesos", "350000", true);
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "valorDesafio", 2, true);
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));
		Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(null);

		Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
		autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(
						autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(autentificacion);

		Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = new Respuesta<AutentificacionDTO>();
		respuestaAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(autentificacionManager
						.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(respuestaAutentificacionDTO);
		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaView = new Respuesta<AumentoLimiteTransferenciaInOutView>();
		respuestaView.setRespuesta(view);
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(AumentoLimiteTransferenciaInOutView.class),
				Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class)))
				.thenReturn(respuestaView);

		Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaBO = new Respuesta<AumentoLimiteTransferenciaOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AumentoLimiteTransferenciaOutDTO respuesta = new AumentoLimiteTransferenciaOutDTO();
		respuesta.setNroGestion("1265361523");
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(aumentoLimiteTransferenciaBO
						.altaSolicitudAumentoLimiteTransferencia(Matchers.any(AumentoLimiteTransferenciaInDTO.class)))
				.thenReturn(respuestaBO);
		when(sesionParametros.getValidacionHashTIS()).thenReturn(HashUtils.obtenerHash(AumentoLimiteTransferenciaInOutViewFeature.crearMapaDeLaVista(AumentoLimiteTransferenciaInOutViewFeature.obtenerView())));

		when(customersApi.getCustomerById(
				sesionCliente.getCliente().getNup()))
				.thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);

		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());

		BiocatchResponseDataDTO biocatchResponseDataDTO = respuestaFinal.getRespuesta().getBiocatchResponseDataDTO();

		Assert.assertNull(biocatchResponseDataDTO);

		Mockito.verify(biocatchManager, times(1)).getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
				ActivityType.CHANGE_ACCOUNT_LIMIT);
	}

	@Test
	public void altaSolicitudAumentoLimiteTransferencia_obtenerCantDiasUltimoCambioEmailNotNull()
			throws IllegalAccessException, DAOException, ParseException {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinPesos", "350000", true);
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "valorDesafio", 2, true);
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));
		Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(null);

		Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
		autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(
						autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(autentificacion);

		Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = new Respuesta<AutentificacionDTO>();
		respuestaAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(autentificacionManager
						.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(respuestaAutentificacionDTO);
		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaView = new Respuesta<AumentoLimiteTransferenciaInOutView>();
		respuestaView.setRespuesta(view);
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(AumentoLimiteTransferenciaInOutView.class),
				Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class)))
				.thenReturn(respuestaView);

		Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaBO = new Respuesta<AumentoLimiteTransferenciaOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AumentoLimiteTransferenciaOutDTO respuesta = new AumentoLimiteTransferenciaOutDTO();
		respuesta.setNroGestion("1265361523");
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(aumentoLimiteTransferenciaBO
						.altaSolicitudAumentoLimiteTransferencia(Matchers.any(AumentoLimiteTransferenciaInDTO.class)))
				.thenReturn(respuestaBO);
		when(sesionParametros.getValidacionHashTIS()).thenReturn(HashUtils.obtenerHash(AumentoLimiteTransferenciaInOutViewFeature.crearMapaDeLaVista(AumentoLimiteTransferenciaInOutViewFeature.obtenerView())));

		when(biocatchManager.getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
				ActivityType.CHANGE_ACCOUNT_LIMIT)).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildBiocatchResponseDataDTO());

		when(customersApi.getCustomerById(
				sesionCliente.getCliente().getNup()))
				.thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail2());

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);

		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
		Assert.assertNotNull( respuestaFinal.getRespuesta().getCantDiasUltimoCambioMail());

		Mockito.verify(biocatchManager, times(1)).getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
				ActivityType.CHANGE_ACCOUNT_LIMIT);

		Mockito.verify(customersApi, times(1)).getCustomerById(
				sesionCliente.getCliente().getNup());
	}

	@Test
	public void altaSolicitudAumentoLimiteTransferencia_obtenerCantDiasUltimoCambioEmailUpdateAtNull()
			throws IllegalAccessException, DAOException, ParseException {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "importeMinPesos", "350000", true);
		FieldUtils.writeField(aumentoLimiteTransferenciasManager, "valorDesafio", 2, true);
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));
		Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(null);

		Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
		autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(
						autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(autentificacion);

		Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = new Respuesta<AutentificacionDTO>();
		respuestaAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(autentificacionManager
						.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(respuestaAutentificacionDTO);
		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaView = new Respuesta<AumentoLimiteTransferenciaInOutView>();
		respuestaView.setRespuesta(view);
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(AumentoLimiteTransferenciaInOutView.class),
				Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class)))
				.thenReturn(respuestaView);

		Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaBO = new Respuesta<AumentoLimiteTransferenciaOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		AumentoLimiteTransferenciaOutDTO respuesta = new AumentoLimiteTransferenciaOutDTO();
		respuesta.setNroGestion("1265361523");
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(aumentoLimiteTransferenciaBO
						.altaSolicitudAumentoLimiteTransferencia(Matchers.any(AumentoLimiteTransferenciaInDTO.class)))
				.thenReturn(respuestaBO);
		when(sesionParametros.getValidacionHashTIS()).thenReturn(HashUtils.obtenerHash(AumentoLimiteTransferenciaInOutViewFeature.crearMapaDeLaVista(AumentoLimiteTransferenciaInOutViewFeature.obtenerView())));

		when(biocatchManager.getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
				ActivityType.CHANGE_ACCOUNT_LIMIT)).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildBiocatchResponseDataDTO());

		when(customersApi.getCustomerById(
				sesionCliente.getCliente().getNup()))
				.thenThrow(new ApiException(new ErrorResponse()));

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);

		respuestaFinal = aumentoLimiteTransferenciasManager.altaSolicitudAumentoLimiteTransferencia(view);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
		Assert.assertNull( respuestaFinal.getRespuesta().getCantDiasUltimoCambioMail());

		Mockito.verify(biocatchManager, times(1)).getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
				ActivityType.CHANGE_ACCOUNT_LIMIT);

		Mockito.verify(customersApi, times(1)).getCustomerById(
				sesionCliente.getCliente().getNup());
	}

	private AumentoLimiteTransferenciaInOutView mockSeteoDatos(){
		when(sesionCliente.getCliente()).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.obtenerCliente(false));
		Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(null);

		Respuesta<AutentificacionDTO> autentificacion = new Respuesta<AutentificacionDTO>();
		autentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(autentificacion);

		Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = new Respuesta<AutentificacionDTO>();
		respuestaAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(autentificacionManager.ejecutarMetodoAutenticacionNotificandoRSA(Matchers.any(AutentificacionDTO.class)))
				.thenReturn(respuestaAutentificacionDTO);
		AumentoLimiteTransferenciaInOutView view = AumentoLimiteTransferenciaInOutViewFeature.obtenerView();
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaView = new Respuesta<AumentoLimiteTransferenciaInOutView>();
		respuestaView.setRespuesta(view);
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(AumentoLimiteTransferenciaInOutView.class),
				Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class)))
				.thenReturn(respuestaView);

		when(sesionParametros.getValidacionHash())
				.thenReturn(HashUtils.obtenerHash(AumentoLimiteTransferenciaInOutViewFeature.crearMapaDeLaVista(AumentoLimiteTransferenciaInOutViewFeature.obtenerView())));

		when(biocatchManager.getScore(
				sesionCliente.getCliente().getNup(),
				sesionCliente.getIpCliente(),
				ActivityName.TRANSFERENCIA,
				ActivityType.WIRE_PAYMENT)).thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildBiocatchResponseDataDTO());

		when(customersApi.getCustomerById(
				sesionCliente.getCliente().getNup()))
				.thenReturn(AumentoLimiteTransferenciaInOutViewFeature.buildCustomersCambioMail1());

		Mensaje mensajeFeedback = new Mensaje();
		mensajeFeedback.setMensaje("%s %s %s");
		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);
		when(scoringApi.getScoring(anyString())).thenReturn(1f);

		return view;
	}

	@Test
	public void obtenerFechasHabilitadasOKReglaInmediataListaOrdenada() {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		List<CalendarApiDateDTO> responseCalendarApi =new ArrayList<CalendarApiDateDTO>();
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-02",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-03",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-04",true));

		AumentoLimiteTransferenciaInOutView view =mockSeteoDatos();
		Mockito.when(sesionParametros.getReglaRsaTis()).thenReturn("TIS_Aprobada");
		Mockito.when(calendarApi.getDates()).thenReturn(responseCalendarApi);

		respuestaFinal = aumentoLimiteTransferenciasManager.obtenerFechasHabilitadas(view);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
		Assert.assertEquals("2023-11-04", respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas().get(0).toString());
		Assert.assertNotNull(respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas());
	}

	@Test
	public void obtenerFechasHabilitadasOKReglaInmediataListaNoOrdenada() {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		List<CalendarApiDateDTO> responseCalendarApi =new ArrayList<CalendarApiDateDTO>();
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-03",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-02",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-06",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-13",true));

		AumentoLimiteTransferenciaInOutView view =mockSeteoDatos();
		Mockito.when(sesionParametros.getReglaRsaTis()).thenReturn("TIS_Aprobada");
		Mockito.when(calendarApi.getDates()).thenReturn(responseCalendarApi);

		respuestaFinal = aumentoLimiteTransferenciasManager.obtenerFechasHabilitadas(view);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
		Assert.assertEquals("2023-11-06", respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas().get(0).toString());
		Assert.assertEquals("2023-11-13", respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas().get(1).toString());
		Assert.assertNotNull(respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas());
	}

	@Test
	public void obtenerFechasHabilitadasOKReglaPostergadaListaOrdenada() {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		List<CalendarApiDateDTO> responseCalendarApi =new ArrayList<CalendarApiDateDTO>();
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-02",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-03",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-06",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-07",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-08",true));

		AumentoLimiteTransferenciaInOutView view =mockSeteoDatos();
		Mockito.when(sesionParametros.getReglaRsaTis()).thenReturn("TIS_NoAprobada");
		Mockito.when(calendarApi.getDates()).thenReturn(responseCalendarApi);

		respuestaFinal = aumentoLimiteTransferenciasManager.obtenerFechasHabilitadas(view);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
		Assert.assertEquals("2023-11-07", respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas().get(0).toString());
		Assert.assertEquals("2023-11-08", respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas().get(1).toString());
		Assert.assertNotNull(respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas());
	}

	@Test
	public void obtenerFechasHabilitadasOKReglaPostergadaListaNoOrdenada() {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		List<CalendarApiDateDTO> responseCalendarApi =new ArrayList<CalendarApiDateDTO>();
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-07",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-06",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-02",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-03",true));
		responseCalendarApi.add(new CalendarApiDateDTO("2023-11-08",true));

		AumentoLimiteTransferenciaInOutView view =mockSeteoDatos();
		Mockito.when(sesionParametros.getReglaRsaTis()).thenReturn("TIS_NoAprobada");
		Mockito.when(calendarApi.getDates()).thenReturn(responseCalendarApi);

		respuestaFinal = aumentoLimiteTransferenciasManager.obtenerFechasHabilitadas(view);

		Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
		Assert.assertEquals("2023-11-07", respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas().get(0).toString());
		Assert.assertEquals("2023-11-08", respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas().get(1).toString());
		Assert.assertNotNull(respuestaFinal.getRespuesta().getTisFechasHabilitadasResponse().getFechasHabilitadas());
	}
}
