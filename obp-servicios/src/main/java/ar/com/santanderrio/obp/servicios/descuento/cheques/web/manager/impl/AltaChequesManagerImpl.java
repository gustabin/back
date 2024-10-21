/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.AltaChequesBO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.ReporteDescuentoChequesPDFBO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAltaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesSimuladosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.AltaChequesManager;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequeAceptadoViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DatosCesionView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AltaChequesManagerImpl.
 */
@Component
public class AltaChequesManagerImpl implements AltaChequesManager {

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The alta cheques. */
	@Autowired
	private AltaChequesBO altaCheques;

	/** The pdf bo. */
	@Autowired
	private ReporteDescuentoChequesPDFBO pdfBo;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AltaChequesManagerImpl.class);

	/** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
	private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

	/** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
	private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/** The Constant MSJ_INFO_VALIDANDO_HASH. */
	private static final String MSJ_INFO_VALIDANDO_HASH = "Validando el hash {} de la sesion con el hash de la entidad {}";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.
	 * AltaChequesManager#simularAltaCheques(ar.com.santanderrio.obp.servicios.
	 * descuento.cheques.web.view.AltaChequesViewIn)
	 */
	@Override
	public Respuesta<AltaChequesViewOut> simularAltaCheques(AltaChequesViewIn chequesView) {
		if (sesionParametros.getContador() == null) {
			sesionParametros.setContador(new ContadorIntentos(2));
		}
		String cuentaString = sesionParametros.getCuentaSeleccionadaParaTransferencia("NROCUENTA");
		cuentaString = cuentaString.split("-")[1].replace("/", "");
		Cuenta cuentaSeleccionada = sesionCliente.getCliente().getCuentaSiContieneNumero(cuentaString);
		Respuesta<ChequesSimuladosDTO> dto = altaCheques.simularAltaCheques(sesionCliente.getCliente(),
				sesionParametros.getSubproductoPaquete(), recuperarDeJson(sesionParametros.getCesionCheques()),
				cuentaSeleccionada, chequesView);
		String estadistica = EstadisticasConstants.DESCUENTO_CHEQUES_ALTA_SIMULACION;
		sesionParametros.setEnSimulacionTasas(false);
		if (EstadoRespuesta.OK.equals(dto.getEstadoRespuesta())) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			sesionParametros.setValidacionHash(dto.getRespuesta().getNumeroDeOperacion().toString());
			sesionParametros.setContador(null);
			return respuestaFactory.crearRespuestaOk(AltaChequesViewOut.class,
					new AltaChequesViewOut(dto.getRespuesta()));
		} else if (EstadoRespuesta.ERROR.equals(dto.getEstadoRespuesta())) {
			setearEstadoReintento(dto, false);
			if ("simulacionAltaRechazado".equals(dto.getItemsMensajeRespuesta().get(0).getTag())) {
				estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
			} else {
				estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuestaFactory.crearRespuestaError(AltaChequesViewOut.class, dto.getItemsMensajeRespuesta());
		}
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		sesionParametros.setValidacionHash(dto.getRespuesta().getNumeroDeOperacion().toString());
		setearEstadoReintento(dto, false);
		sesionParametros.setContador(null);
		return respuestaFactory.crearRespuestaWarning(new AltaChequesViewOut(dto.getRespuesta()),
				dto.getItemsMensajeRespuesta());
	}

	/**
	 * Agrega al itemMensajeRespuesta el mensaje que activa el reintento o no
	 * reintento correspondiente dependiendo de si el tipo de error es el
	 * indicado.
	 *
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            la respuesta cuyo itemMensajeRespuesta va a ser modificado
	 * @param esEfectivizacion
	 *            the es efectivizacion
	 */
	private <T> void setearEstadoReintento(Respuesta<T> dto, boolean esEfectivizacion) {
		if (TipoError.ERROR_GENERICO.getDescripcion().equals(dto.getItemsMensajeRespuesta().get(0).getTipoError())
				|| TipoError.ERROR_FECHA.getDescripcion().equals(dto.getItemsMensajeRespuesta().get(0).getTipoError())
				|| esEfectivizacion) {
			if (sesionParametros.getContador().permiteReintentar()) {
				dto.getItemsMensajeRespuesta().get(0).setDetalleTipoError("reintentar");
			} else {
				dto.getItemsMensajeRespuesta().get(0).setDetalleTipoError("continuar");
			}
		}

	}

	/**
	 * Transforma un String dado en el objeto DatosCesionDTO a devolver.
	 *
	 * @param cesionCheques
	 *            the cesion cheques
	 * @return the datos cesion DTO
	 */
	private DatosCesionDTO recuperarDeJson(String cesionCheques) {
		try {
			return new ObjectMapper().readValue(cesionCheques, DatosCesionDTO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.
	 * AltaChequesManager#efectuarAltaCheques()
	 */
	@Override
	public Respuesta<AltaChequesViewOut> efectuarAltaCheques() {
		if (sesionParametros.getContador() == null) {
			sesionParametros.setContador(new ContadorIntentos(2));
		}
		Respuesta<ChequesAltaDTO> dto = altaCheques.efectuarAltaCheques(sesionCliente.getCliente(),
				sesionParametros.getValidacionHash(),
				sesionParametros.getCuentaSeleccionadaParaTransferencia("NROCUENTA"));
		if (EstadoRespuesta.OK.equals(dto.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_ALTA_EFECTIVIZACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			AltaChequesViewOut altaView = new AltaChequesViewOut(dto.getRespuesta());
			guardarHashEnSesion(altaView);
			return respuestaFactory.crearRespuestaOk(AltaChequesViewOut.class, altaView);
		}
		if (EstadoRespuesta.ERROR.equals(dto.getEstadoRespuesta()) && TipoError.ERROR_LEGALES.getDescripcion()
				.equals(dto.getItemsMensajeRespuesta().get(0).getTipoError())) {
			sesionParametros.setFalloLegales(true);
			AltaChequesViewOut feedback = new AltaChequesViewOut();
			feedback.setMensajeFeedback(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ALTA_CHEQUES_OK).getMensaje());
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_ALTA_EFECTIVIZACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(AltaChequesViewOut.class, feedback);
		}
		setearEstadoReintento(dto, true);
		estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_ALTA_EFECTIVIZACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(AltaChequesViewOut.class, dto.getItemsMensajeRespuesta());
	}

	/**
	 * Guardar hash en sesion.
	 *
	 * @param altaView
	 *            the alta view
	 */
	private void guardarHashEnSesion(AltaChequesViewOut altaView) {
		String hashView = HashUtils.obtenerHash(crearMapaDeAltaView(altaView));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sesionParametros.setValidacionHash(hashView);

	}

	/**
	 * Validar hash alta cheques.
	 *
	 * @param nuevoPago
	 *            the nuevo pago
	 */
	private void validarHashAltaCheques(AltaChequesViewOut nuevoPago) {
		String inputHash = HashUtils.obtenerHash(crearMapaDeAltaView(nuevoPago));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sesionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Crear mapa de alta view.
	 *
	 * @param altaView
	 *            the alta view
	 * @return the map
	 */
	private Map<String, String> crearMapaDeAltaView(AltaChequesViewOut altaView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, String> mapaAtributos = new HashMap<String, String>();
		mapaAtributos.put("importeAcreditar", altaView.getImporteAcreditar());
		mapaAtributos.put("importeTotal", altaView.getImporteTotal());
		mapaAtributos.put("cuentaCredito", altaView.getCuentaCredito());
		mapaAtributos.put("numeroDeOperacion", altaView.getNumeroDeOperacion());
		mapaAtributos.put("importeTotalCheque", altaView.getImporteTotalCheque());
		mapaAtributos.put("importeCheque", altaView.getImporteCheque());
		mapaAtributos.put("importeImpuestos", altaView.getImporteImpuestos());
		mapaAtributos.put("importeIntereses", altaView.getImporteIntereses());
		mapaAtributos.put("importeNeto", altaView.getImporteNeto());
		mapaAtributos.put("importeAcreditarCheque", altaView.getImporteAcreditarCheque());
		int indicadorCheque = 0;
		for (AltaChequeAceptadoViewOut aceptados : altaView.getListaAceptados()) {
			mapaAtributos.put("numeroCheque" + indicadorCheque, aceptados.getNumeroCheque());
			mapaAtributos.put("fechaPago" + indicadorCheque, aceptados.getFechaPago());
			mapaAtributos.put("importeTotal" + indicadorCheque, aceptados.getImporteTotal());
			mapaAtributos.put("importeCheque" + indicadorCheque, aceptados.getImporteCheque());
			mapaAtributos.put("importeImpuestos" + indicadorCheque, aceptados.getImporteImpuestos());
			mapaAtributos.put("importeIntereses" + indicadorCheque, aceptados.getImporteIntereses());
			mapaAtributos.put("importeNeto" + indicadorCheque, aceptados.getImporteNeto());
			mapaAtributos.put("importeAAcreditar" + indicadorCheque, aceptados.getImporteAAcreditar());
			indicadorCheque++;
		}
		mapaAtributos.put("fechaAlta", altaView.getFechaAlta());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.
	 * AltaChequesManager#grabarInicioSolicitud(ar.com.santanderrio.obp.
	 * servicios. descuento.cheques.web.view.DatosCesionView)
	 */
	@Override
	public void grabarInicioSolicitud(DatosCesionView view) {
		sesionParametros.setContador(null);
		if (view.isErrorEstadistica()) {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_INICIO_SOLICITUD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_INICIO_SOLICITUD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.AltaChequesManager#visualizarAltaCheques(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut)
	 */
	@Override
	public Respuesta<AltaChequesViewOut> visualizarAltaCheques(AltaChequesViewOut chequesView) {
		if (sesionParametros.getFalloLegales()) {
			if (sesionParametros.getEnSimulacionTasas()) {
				estadisticaManager.add(EstadisticasConstants.VISUALIZACION_SIMULACION_TASAS,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else {
				estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_ALTA_VISUALIZACION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuestaFactory.crearRespuestaError("errorLegales", TipoError.ERROR_LEGALES,
					CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS);
		}
		validarHashAltaCheques(chequesView);
		if (sesionParametros.getEnSimulacionTasas()) {
			estadisticaManager.add(EstadisticasConstants.VISUALIZACION_SIMULACION_TASAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_ALTA_VISUALIZACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaFactory.crearRespuestaOk(AltaChequesViewOut.class, chequesView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.AltaChequesManager#descargarPDF(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut)
	 */
	@Override
	public Respuesta<ReporteView> descargarPDF(AltaChequesViewOut chequesView) {
		validarHashAltaCheques(chequesView);
		Respuesta<Reporte> reporte = pdfBo.obtenerOperacionPDF(chequesView);
		String estadistica = EstadisticasConstants.DESCUENTO_CHEQUES_PRECARGADAS_PDF;
		if (sesionParametros.getEnSimulacionTasas()) {
			estadistica = EstadisticasConstants.DESCARGA_PDF_SIMULACION_TASAS;
		}
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}

}
