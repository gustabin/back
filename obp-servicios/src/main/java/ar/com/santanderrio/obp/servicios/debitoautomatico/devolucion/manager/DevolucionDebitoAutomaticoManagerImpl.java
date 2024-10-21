package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.manager;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DatosComprobanteDevolucionDA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.bo.DevolucionDebitoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.DevolucionDebitoAutomaticoOutView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.DevolucionDebitoAutomaticoView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAInView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAOutView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class DevolucionDebitoAutomaticoManagerImpl.
 */
@Component
public class DevolucionDebitoAutomaticoManagerImpl implements DevolucionDebitoAutomaticoManager{

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;
	
	/** The devolucion debito automatico BO. */
	@Autowired
	private DevolucionDebitoAutomaticoBO devolucionDebitoAutomaticoBO;
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DevolucionDebitoAutomaticoManagerImpl.class);
	
	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	
	/**
	 * Solicitar devolucion debito automatico.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<DevolucionDebitoAutomaticoOutView> solicitarDevolucionDebitoAutomatico(DevolucionDebitoAutomaticoView devolucionView) {

		if(fechaHabilitadaParaDevolucion(devolucionView.getFechaDebito())) {
			LOGGER.info("El debito esta en fecha para proceder a solicitar devolucion");
			DevolucionDebitoAutomaticoOutView devolucionDA = new DevolucionDebitoAutomaticoOutView();
			devolucionDA.setTituloStack(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEVOLUCION_DEBITO_AUTOMATICO_TITULO_CONFIRMACION).getMensaje());
			devolucionDA.setMensajeInformativo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEVOLUCION_DEBITO_AUTOMATICO_MENSAJE_INFORMATIVO_CONFIRMACION).getMensaje());
			devolucionDA.setIdCliente(devolucionView.getIdCliente());
			devolucionDA.setFechaDevolucion(getFechaActual());
			devolucionDA.setFechaVencimiento(devolucionView.getFechaDebito());
			estadisticaManager.add(EstadisticasConstants.SOLICITUD_DEVOLUCION_DA_CONFIRMACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(devolucionDA);
		} else {
			estadisticaManager.add(EstadisticasConstants.SOLICITUD_DEVOLUCION_DA_CONFIRMACION, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.DEVOLUCION_DEBITO_AUTOMATICO_ERROR_EXCEDE_30_DIAS);
		}
	}
	
	/**
	 * Ejecutar solicitud devolucion DA.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<SolicitarDevolucionDAOutView> ejecutarSolicitudDevolucionDA(SolicitarDevolucionDAInView devolucionView) {
		
		String cuit = devolucionView.getCuitEmpresa();
		String servicio = devolucionView.getServicio();
		String partida = devolucionView.getPartida();
		String empresa = devolucionView.getEmpresa();
		Cliente cliente = sesionCliente.getCliente();
		String idCliente = devolucionView.getIdCliente();
		String fecha = devolucionView.getFecha();
		String importe = devolucionView.getImporte();
		
		DatosComprobanteDevolucionDA datosComprobante = armarComprobante(devolucionView);
		sesionParametros.setDatosComprobanteDevolucionDA(datosComprobante);
		
		return devolucionDebitoAutomaticoBO.ejecutarSolicitudDevolucionDA(cliente, cuit, servicio, partida, idCliente, fecha, empresa, importe); 
	}

	/**
	 * Gets the fecha actual.
	 *
	 * @return the fecha actual
	 */
	private String getFechaActual() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);
		return new DateTime().toString(fmt);
	}

	/**
	 * Fecha habilitada para devolucion.
	 *
	 * @param fechaDebitoString the fecha debito string
	 * @return true, if successful
	 */
	private boolean fechaHabilitadaParaDevolucion (String fechaDebitoString) {
		
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);

		DateTime fechaDebito = fmt.parseDateTime(fechaDebitoString);
		DateTime fechaActual = new DateTime();
		
		Interval interval = new Interval(fechaDebito, fechaActual);
		Duration duration = interval.toDuration();
		
		return duration.getStandardDays() < 30;		
	}
	
	/**
	 * Generar comprobante PDF.
	 *
	 * @param devolucionView the devolucion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> generarComprobantePDF(DevolucionDebitoAutomaticoView devolucionView) {
		LOGGER.info("Iniciando flujo de generacion de comprobante en PDF");
		DatosComprobanteDevolucionDA datosComprobante = sesionParametros.getDatosComprobanteDevolucionDA();
		Respuesta<Reporte> reporteRespuesta = devolucionDebitoAutomaticoBO.generarComprobantePDF(datosComprobante);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
			estadisticaManager.add(devolucionView.getDescargaPDF() ? EstadisticasConstants.SOLICITUD_DEVOLUCION_DESCARGAR_COMPROBANTE : EstadisticasConstants.SOLICITUD_DEVOLUCION_IMPRIMIR_COMPROBANTE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, "ERROR_GENERICO", CodigoMensajeConstantes.EXTRACCION_EFECTIVO_DESCARGA_PDF_ERROR);
			estadisticaManager.add(devolucionView.getDescargaPDF() ? EstadisticasConstants.SOLICITUD_DEVOLUCION_DESCARGAR_COMPROBANTE : EstadisticasConstants.SOLICITUD_DEVOLUCION_IMPRIMIR_COMPROBANTE, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}
		
	/**
	 * Armar comprobante.
	 *
	 * @param devolucionView the devolucion view
	 * @return the datos comprobante devolucion DA
	 */
	private DatosComprobanteDevolucionDA armarComprobante(SolicitarDevolucionDAInView devolucionView) {
		
		DatosComprobanteDevolucionDA comprobante = new DatosComprobanteDevolucionDA();
		
		comprobante.setEmpresa(devolucionView.getEmpresa());
		comprobante.setNroIdentificacion(devolucionView.getPartida());
		comprobante.setNroCuentaDebito(devolucionView.getNroCuenta());
		comprobante.setTipoCuentaDebito(devolucionView.getTipoCuenta());
		comprobante.setMonto(devolucionView.getImporte());
		comprobante.setFechaVencimiento(devolucionView.getFecha());
		comprobante.setFechaPago(devolucionView.getFecha());
		DateTime fechaHoy = new DateTime();
		comprobante.setFechaDevolucion(fechaHoy.toString(DATE_FORMAT));
		comprobante.setFechaHoraComprobante(fechaHoy.toString(DATE_FORMAT) + " - " + fechaHoy.toString("HH:mm:ss") + " hs.");
		
		return comprobante;
	}

	/**
	 * Grabar estadistica ver comprobante.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaVerComprobante() {
		estadisticaManager.add(EstadisticasConstants.SOLICITUD_DEVOLUCION_VER_COMPROBANTE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}
}