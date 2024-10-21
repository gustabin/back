package ar.com.santanderrio.obp.servicios.comex.transfext.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexLiquidacionOrdenPagoBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.impl.ComexConsultasBOImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.CargaDocumentoDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoConfiguracionOrdenPagoDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConfiguracionLiquidacionOrdenPagoDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarOrdenPagoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexLiquidacionOrdenPagoManager;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ComprobanteSolicitudOrdenPagoView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConceptoLiquidacionOrdenPagoView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.CuentaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DocumentacionAdjuntaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.FeedbackProcesarOrdenPago;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.InicioLiquidacionOrdenPagoView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarOrdenPagoInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@Component
public class ComexLiquidacionOrdenPagoManagerImpl implements ComexLiquidacionOrdenPagoManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComexLiquidacionOrdenPagoManagerImpl.class);

	@Autowired
	private ComexLiquidacionOrdenPagoBO comexLiquidacionOrdenPagoBO;

	@Autowired
	private ComexConsultasBOImpl comexConsultasBO;

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private SesionCliente sesionCliente;

	@Autowired
	private SesionParametros sesionParametros;

	@Autowired
	private EstadisticaManager estadisticaManager;

	@Autowired
	private MensajeBO mensajeBO;

	@Autowired
	private LegalBO legalBO;

	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The max file size. */
	@Value("${COMEX.MAX.FILESIZE}")
	private String maxFileSize;

	/** The max file size. */
	@Value("${COMEX.TIPO.ARCHIVOS.ADJ}")
	private String tiposDeArchivosPermitidos;

	@Value("${COMEX.MAX.FILES.ATACHED}")
	private String cantidadMaxArchivos;

	private final String ubicacion = "Normativa.pdf";

	private final String nombreArchivo = "Normativa";

	private static final String PUNTO_ESPACIO = ". ";
	private static final String ESPACIO_GUION = " - ";
	private static final String FORMATO_FECHA_HORA = "dd/MM/yy - HH:mm";
	private static final String NO = "No";
	private static final String SI = "Sí";

	@Override
	public Respuesta<InicioLiquidacionOrdenPagoView> inicioLiquidacionOrdenPagoView() {
		Respuesta<String> respuestaLegal = legalBO.buscarLegal(CodigoMensajeConstantes.COMEX_ORDEN_PAGO_LEGAL);
		if (EstadoRespuesta.OK.equals(respuestaLegal.getEstadoRespuesta())) {
			try {
				Respuesta<ConfiguracionLiquidacionOrdenPagoDTO> respuestaBO = comexLiquidacionOrdenPagoBO
						.inicioLiquidacionOrdenPago(sesionCliente.getCliente());
				estadisticaManager.add(EstadisticasConstants.CONFIGURACION_COMEX_ORDEN_PAGO,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(obtenerInicioLiquidacionOrdenPagoView(
						respuestaBO.getRespuesta(), respuestaLegal.getRespuesta()));
			} catch (BusinessException e) {
				LOGGER.info("Error al obtener conceptos");
			}
		}
		estadisticaManager.add(EstadisticasConstants.CONFIGURACION_COMEX_ORDEN_PAGO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		throw new RobotException("Error al consultar legal o conceptos");
	}

	private InicioLiquidacionOrdenPagoView obtenerInicioLiquidacionOrdenPagoView(
			ConfiguracionLiquidacionOrdenPagoDTO respuesta, String legal) {
		InicioLiquidacionOrdenPagoView inicioView = new InicioLiquidacionOrdenPagoView();
		inicioView.setConceptos(obtenerConceptosView(respuesta.getConceptos()));
		inicioView.setCuentasPesos(obtenerCuentasView(respuesta.getCuentasPesos(), false));
		inicioView.setCuentasPesosDolares(obtenerCuentasView(respuesta.getCuentasPesosDolares(), true));
		inicioView.setLegal(legal);
		inicioView.setCantidadMaxArchivos(cantidadMaxArchivos);
		inicioView.setSizeMax(maxFileSize);
		inicioView.setExtensionesPermitidas(tiposDeArchivosPermitidos);

		return inicioView;
	}

	private List<CuentaView> obtenerCuentasView(List<Cuenta> cuentas, Boolean esListaCuentasDolar) {
		List<CuentaView> cuentasView = new ArrayList<CuentaView>();
		for (Cuenta cuenta : cuentas) {
			CuentaView cuentaView = new CuentaView();
			IdentificacionCuenta idCuenta = new IdentificacionCuenta(cuenta.getNroSucursal(),
					cuenta.getNroCuentaProducto());
			cuentaView.setNumero(idCuenta.toString());
			cuentaView.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcionConMoneda());
			cuentasView.add(cuentaView);
			// cuentas unicas vienen como CUENTA_UNICA_PESOS, por cada una creo
			// un view para
			// cta unica u$s
			if (esListaCuentasDolar && cuenta.getTipoCuentaEnum().equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
				CuentaView cuentaUnicaDolarView = new CuentaView();
				cuentaUnicaDolarView.setNumero(idCuenta.toString());
				cuentaUnicaDolarView
						.setDescripcionTipoCuenta(TipoCuenta.CUENTA_UNICA_DOLARES.getDescripcionConMoneda());
				cuentasView.add(cuentaUnicaDolarView);
			}
		}
		return cuentasView;
	}

	private List<ConceptoLiquidacionOrdenPagoView> obtenerConceptosView(
			List<ConceptoConfiguracionOrdenPagoDTO> conceptos) {
		List<ConceptoLiquidacionOrdenPagoView> conceptosView = new ArrayList<ConceptoLiquidacionOrdenPagoView>();
		for (ConceptoConfiguracionOrdenPagoDTO conceptoDTO : conceptos) {
			ConceptoLiquidacionOrdenPagoView conceptoView = new ConceptoLiquidacionOrdenPagoView();
			conceptoView.setNombre(conceptoDTO.getNombre());
			conceptoView.setMuestraCuentasDolares(conceptoDTO.getAdmiteCanje());
			conceptoView.setDocumentacionObligatoria(conceptoDTO.getDocumentacionObligatoria());
			conceptoView.setIdConcepto(conceptoDTO.getIdConcepto());
			conceptoView.setMuestraVinculante(conceptoDTO.getMuestraVinculante());
			conceptosView.add(conceptoView);
		}
		return conceptosView;
	}

	@Override
	public Respuesta<Boolean> limpiarConceptos() {
		return comexLiquidacionOrdenPagoBO.limpiarConceptos();
	}

	@Override
	public Respuesta<ArchivoTransferenciaView> adjuntarArchivo(DocumentacionAdjuntaView documentacionAdjuntaView) {
		// valido el tamanio del archivo y el tipo
		if (documentacionAdjuntaView.getArchivos().get(0).getByteArray() == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ARCHIVO_VACIO, "");
		}
		if (!this.validarMaxFileSizeAllowed(documentacionAdjuntaView.getArchivos().get(0).getByteArray())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIZE_EXCEDIDO,
					CodigoMensajeConstantes.TAMANIO_MAXIMO_EXCEDIDO);
		}
		if (!this.validarTipoDeArchivoValido(documentacionAdjuntaView.getArchivos().get(0).getTipoArchivo())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_EXTENSION_INVALIDA,
					CodigoMensajeConstantes.EXTENSION_INVALIDA);
		}
		AdjuntarArchivosDTO adjuntarArchivosInDto = new AdjuntarArchivosDTO();
		adjuntarArchivosInDto.setArchivo(documentacionAdjuntaView.getArchivos().get(0));
		adjuntarArchivosInDto
				.setNroTransferencia(StringUtils.isNotBlank(documentacionAdjuntaView.getArchivos().get(0).getId())
						? Integer.parseInt(documentacionAdjuntaView.getArchivos().get(0).getId())
						: null);
		// verifico si el archivo tiene virus
		Respuesta<Boolean> respuesta = this.comexConsultasBO.verificarArchivo(adjuntarArchivosInDto);
		// si tiene virus se devuelve error
		if (!respuesta.getRespuesta()) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDAR_ARCHIVO_VIRUS, "");
		}
		Respuesta<CargaDocumentoDTO> respuestaCargarDoc = this.comexLiquidacionOrdenPagoBO
				.adjuntarArchivos(adjuntarArchivosInDto);
		if (respuestaCargarDoc.getRespuesta() == null) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ADJUNTAR_ARCHIVO,
					CodigoMensajeConstantes.ERROR_SERVICIO_ADJUNTAR);
		} else {
			ArchivoTransferenciaView archivoTransferenciaView = new ArchivoTransferenciaView();
			archivoTransferenciaView.setNroTransferencia(respuestaCargarDoc.getRespuesta().getNroTransferencia());
			archivoTransferenciaView.setHoja(String.valueOf(respuestaCargarDoc.getRespuesta().getHoja()));
			return respuestaFactory.crearRespuestaOk(archivoTransferenciaView);
		}
	}

	private Respuesta<CargaDocumentoDTO> mockAdjuntar() {
		CargaDocumentoDTO dto = new CargaDocumentoDTO();
		dto.setHoja(1);
		dto.setNroTransferencia(1234);
		return respuestaFactory.crearRespuestaOk(dto);
	}

	/**
	 * Validar max file size allowed.
	 * 
	 * @param archivo
	 *            the archivo
	 * @return true, if successful
	 */
	private boolean validarMaxFileSizeAllowed(byte[] archivo) {
		try {
			Integer tamanioMaximoArchivo = Integer.parseInt(this.maxFileSize);
			Integer tamanioArchivoEnKb = (int) Math.ceil((double) archivo.length / 1024);
			if (tamanioArchivoEnKb <= tamanioMaximoArchivo) {
				return true;
			} else {
				LOGGER.error("Error el archivo a adjuntar supera el tamaño maximo permitido de: " + tamanioArchivoEnKb);

			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * Validar tipo de archivo valido.
	 * 
	 * @param tipoArchivo
	 *            the tipo archivo
	 * @return true, if successful
	 */
	private boolean validarTipoDeArchivoValido(String tipoArchivo) {
		String archivosPermitidos[] = this.tiposDeArchivosPermitidos.split(",");
		for (int i = 0; i < archivosPermitidos.length; i++) {
			if (archivosPermitidos[i].equals(tipoArchivo)) {
				return true;
			}
		}
		LOGGER.error("Error el archivo a adjuntar no posee un tipo permitido");

		return false;
	}

	@Override
	public Respuesta<FeedbackProcesarOrdenPago> procesarOrden(ProcesarOrdenPagoInView procesarOrdenPagoInView) {
		estadisticaManager.add(EstadisticasConstants.COMEX_ORDEN_PAGO_ACEPTACION_TYC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		if (procesarOrdenPagoInView.getConcepto().getMuestraVinculante()) {
			estadisticaManager.add(
					procesarOrdenPagoInView.getEmpresaVinculada() ? EstadisticasConstants.COMEX_ORDEN_PAGO_VINCULANTE_SI
							: EstadisticasConstants.COMEX_ORDEN_PAGO_VINCULANTE_NO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		try {
			Respuesta<ProcesarOrdenPagoOutDTO> respuestaProcesarPago = comexLiquidacionOrdenPagoBO
					.procesarOrdenPago(sesionCliente.getCliente(), procesarOrdenPagoInView);
			FeedbackProcesarOrdenPago feedbackView = obtenerFeedbackProcesarOrdenPago(
					respuestaProcesarPago.getRespuesta(), procesarOrdenPagoInView);
			estadisticaManager.add(EstadisticasConstants.FEEDBACK_COMEX_ORDEN_PAGO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			armarComprobanteEnSesion(procesarOrdenPagoInView, feedbackView);
			return respuestaFactory.crearRespuestaOk(feedbackView);
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.FEEDBACK_COMEX_ORDEN_PAGO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(FeedbackProcesarOrdenPago.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(obtenerItemMensajeErrorProcesarOrden(e))));
		}
	}

	private ItemMensajeRespuesta obtenerItemMensajeErrorProcesarOrden(BusinessException e) {
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		Mensaje mensaje = mensajeBO
				.obtenerMensajePorCodigoConErrorGenerico(CodigoMensajeConstantes.COMEX_ORDEN_PAGO_FEEDBACK_ERROR);
		if (StringUtils.isNotBlank(e.getMessage())) {
			String codigoErrorServicio = StringUtils.substringBefore(e.getMessage(), "-");
			String codigoMensaje = obtenerCodigoMensaje(codigoErrorServicio);
			if (codigoMensaje != null) {
				Mensaje msj = mensajeBO.obtenerMensajePorCodigoConErrorGenerico(codigoMensaje);
				item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
				item.setMensaje(mensaje.getMensaje() + PUNTO_ESPACIO + msj.getMensaje());
			} else {
				item.setTipoError(TipoError.ERROR_NO_CONTEMPLADO.getDescripcion());
				item.setMensaje(mensaje.getMensaje());
			}
		} else {
			item.setTipoError(TipoError.ERROR_NO_CONTEMPLADO.getDescripcion());
			item.setMensaje(mensaje.getMensaje());
		}
		return item;
	}

	private String obtenerCodigoMensaje(String codigoError) {
		if (StringUtils.isNumeric(codigoError)) {
			switch (Integer.valueOf(codigoError)) {
			case 1:
				return CodigoMensajeConstantes.COMEX_ORDEN_PAGO_CUENTA_INVALIDA;
			case 2:
				return CodigoMensajeConstantes.COMEX_ORDEN_PAGO_CONCEPTO;
			case 3:
				return CodigoMensajeConstantes.COMEX_ORDEN_PAGO_MONTO;
			case 4:
				return CodigoMensajeConstantes.COMEX_ORDEN_PAGO_NUP;
			case 5:
				return CodigoMensajeConstantes.COMEX_ORDEN_PAGO_OPERACION;
			}
		}
		return null;
	}

	private Respuesta<ProcesarOrdenPagoOutDTO> respuestaMock() throws BusinessException {
		ProcesarOrdenPagoOutDTO dto = new ProcesarOrdenPagoOutDTO();
		dto.setNroForm(1234);
		return respuestaFactory.crearRespuestaOk(dto);
	}

	private void armarComprobanteEnSesion(ProcesarOrdenPagoInView procesarOrdenPagoInView,
			FeedbackProcesarOrdenPago feedbackView) {
		ComprobanteSolicitudOrdenPagoView comprobanteView = new ComprobanteSolicitudOrdenPagoView();
		comprobanteView.setNumeroReferencia(procesarOrdenPagoInView.getNumeroReferencia());
		comprobanteView.setMonto(procesarOrdenPagoInView.getImporte());
		comprobanteView.setConcepto(procesarOrdenPagoInView.getConcepto().getNombre());
		comprobanteView.setNroCuentaDestino(procesarOrdenPagoInView.getCuenta().getNumero());
		comprobanteView.setTipoCuentaDestino(procesarOrdenPagoInView.getCuenta().getDescripcionTipoCuenta());
		comprobanteView.setDocumentacionAdjunta(
				obtenerNombresArchivosAdjuntos(procesarOrdenPagoInView.getDocumentacionAdjunta()));
		comprobanteView.setNroComprobante(feedbackView.getNroComprobante());
		comprobanteView.setTituloComprobante(CabeceraComprobantesEnum.SOLICITUD_LIQUIDACION_ORDEN_PAGO.getDetalle());
		if (procesarOrdenPagoInView.getConcepto().getMuestraVinculante()) {
			comprobanteView.setEmpresaVinculada(procesarOrdenPagoInView.getEmpresaVinculada() ? SI : NO);
		}
		comprobanteView.setFechaOperacion(feedbackView.getFechaHoraActual());
		sesionParametros.setDetalleComprobanteView(comprobanteView);
	}

	private String obtenerNombresArchivosAdjuntos(List<ReporteView> documentacionAdjunta) {
		StringBuilder sb = new StringBuilder();
		for (ReporteView reporteView : documentacionAdjunta) {
			if (StringUtils.isNotBlank(sb)) {
				sb.append(ESPACIO_GUION);
				sb.append(reporteView.getNombre());
			} else {
				sb.append(reporteView.getNombre());
			}
		}
		return sb.toString();
	}

	private FeedbackProcesarOrdenPago obtenerFeedbackProcesarOrdenPago(ProcesarOrdenPagoOutDTO procesarOrdenPagoOutDTO,
			ProcesarOrdenPagoInView procesarOrdenPagoInView) {
		FeedbackProcesarOrdenPago feedbackView = new FeedbackProcesarOrdenPago();
		Mensaje msj = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.COMEX_ORDEN_PAGO_FEEDBACK_OK,
				procesarOrdenPagoInView.getNumeroReferencia());
		feedbackView.setMensajeOK(msj.getMensaje());
		feedbackView.setNroComprobante(String.valueOf(procesarOrdenPagoOutDTO.getNroForm()));
		feedbackView.setFechaHoraActual(new SimpleDateFormat(FORMATO_FECHA_HORA).format(new Date()));
		return feedbackView;
	}

	@Override
	public Respuesta<Void> borrarArchivo(ArchivoTransferenciaView documentacionAdjuntaView) {
		AdjuntarArchivosDTO adjuntarArchivosInDto = new AdjuntarArchivosDTO();
		adjuntarArchivosInDto.setNroTransferencia(documentacionAdjuntaView.getNroTransferencia());
		adjuntarArchivosInDto.setHoja(documentacionAdjuntaView.getHoja());
		Respuesta<Boolean> responseBorrarDoc = this.comexLiquidacionOrdenPagoBO.borrarDoc(adjuntarArchivosInDto);
		if (responseBorrarDoc.getRespuesta()) {
			return this.respuestaFactory.crearRespuestaOk(null);
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ELIMINAR_ARCHIVO_ADJUNTO,
					CodigoMensajeConstantes.ERROR_SERVICIO_ELIMINAR_ADJUNTO);
		}
	}

	@Override
	public Respuesta<Void> configuracionOrdenPagoExt() {
		estadisticaManager.add(EstadisticasConstants.CONFIRMACION_COMEX_ORDEN_PAGO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView> descargarComprobantePDF(
			Boolean esDescarga) {
		Respuesta<Reporte> reporte;
		Respuesta<ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(sesionParametros.getDetalleComprobanteView());
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView reporteView = ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView
					.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(
					esDescarga ? EstadisticasConstants.DESCARGAR_COMPROBANTE_COMEX_ORDEN_PAGO
							: EstadisticasConstants.IMPRIMIR_COMPROBANTE_COMEX_ORDEN_PAGO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(
					ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView.class,
					reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(
					esDescarga ? EstadisticasConstants.DESCARGAR_COMPROBANTE_COMEX_ORDEN_PAGO
							: EstadisticasConstants.IMPRIMIR_COMPROBANTE_COMEX_ORDEN_PAGO,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	@Override
	public Respuesta<Void> verComprobante() {
		estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_COMEX_ORDEN_PAGO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView> descargarNormativa() {
		Respuesta<Reporte> reporte;
		Respuesta<ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView> respuestaView;
		reporte = reporteBO.obtenerArchivoPDF(ubicacion, nombreArchivo);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView reporteView = ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView
					.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(
					ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView.class,
					reporte.getItemsMensajeRespuesta());
		}
		return respuestaView;
	}

	@Override
	public Respuesta<Void> estadisticaAdjuntar() {
		estadisticaManager.add(EstadisticasConstants.COMEX_ADJUNTAR_ARCHIVO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<String> obtenerMensajeVinculante() {
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_AYUDA_VINCULANTE);
		if (StringUtils.isNotBlank(mensaje.getMensaje())) {
			return respuestaFactory.crearRespuestaOk(mensaje.getMensaje());
		}
		throw new RobotException("Error al consultar el mensaje " + CodigoMensajeConstantes.MENSAJE_AYUDA_VINCULANTE);
	}

}
