/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activity.InvalidActivityException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
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
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
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
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.DescuentoChequesBO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.ReporteDescuentoChequesPDFBO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesSimuladosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleHistorialOperacionesDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionPrecargadaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionesPrecargadasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequeAceptadoViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DatosCesionView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleHistorialOperacionesView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.HistorialOperacionesView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.TasasIndicativasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class DescuentoChequesManagerImpl.
 */
@Component
public class DescuentoChequesManagerImpl implements DescuentoChequesManager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The descuento bo. */
	@Autowired
	private DescuentoChequesBO descuentoBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The reporte BO. */
	@Autowired
	private ReporteDescuentoChequesPDFBO reporteBO;
	
	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The alta cheques. */
	@Autowired
	private AltaChequesBO altaCheques;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DescuentoChequesManagerImpl.class);

	/** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
	private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

	/** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
	private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#obtenerMontoCesion()
	 */
	@Override
	public Respuesta<DatosCesionView> obtenerMontoCesion() {
		LOGGER.info("Obteniendo cliente de sesion");
		DatosCesionView view = null;
		Respuesta<DatosCesionDTO> res = descuentoBO.obtenerMontoCalificadoCesion(sesionCliente.getCliente());
		if (EstadoRespuesta.OK.equals(res.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CABECERA_INICIO_DESCUENTO_CHEQUES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			sesionParametros.setIsCalificado(true);
			view = new DatosCesionView(res.getRespuesta());
			guardarDatosCesionEnSesion(res.getRespuesta());
			return respuestaFactory.crearRespuestaOk(DatosCesionView.class, view);
		}
		sesionParametros.setIsCalificado(false);
		if (EstadoRespuesta.ERROR.equals(res.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CABECERA_INICIO_DESCUENTO_CHEQUES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(EstadisticasConstants.CABECERA_INICIO_DESCUENTO_CHEQUES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		view = new DatosCesionView();
		guardarDatosCesionEnSesion(res.getRespuesta());
		return respuestaFactory.crearRespuestaWarning(DatosCesionView.class, new DatosCesionView(),
				res.getItemsMensajeRespuesta());
	}

	/**
	 * Guardar datos cesion en sesion.
	 *
	 * @param datosCesionDTO
	 *            the datos cesion DTO
	 */
	private void guardarDatosCesionEnSesion(DatosCesionDTO datosCesionDTO) {
		sesionParametros.setCesionCheques(getJsonValueAsString(datosCesionDTO));

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#obtenerTasasIndicativas()
	 */
	@Override
	public Respuesta<TasasIndicativasView> obtenerTasasIndicativas() {
		LOGGER.info("Obteniendo cliente de sesion");
		Respuesta<TasasIndicativasDTO> res = descuentoBO.obtenerTasasIndicativas(sesionCliente.getCliente());
		if (EstadoRespuesta.OK.equals(res.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TASAS_INDICATIVAS_DESCUENTO_CHEQUES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(TasasIndicativasView.class,
					new TasasIndicativasView(res.getRespuesta()));
		}
		estadisticaManager.add(EstadisticasConstants.TASAS_INDICATIVAS_DESCUENTO_CHEQUES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(TasasIndicativasView.class, res.getItemsMensajeRespuesta());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#obtenerOperacionesPrecargadas(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.OperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<OperacionesPrecargadasView> obtenerOperacionesPrecargadas(OperacionesPrecargadasView operacionIn) {
		sesionParametros.setFalloLegales(false);
		String ultimoRegistro = obtenerUltimoRegistro(operacionIn.getPrimerLlamada());
		Respuesta<OperacionesPrecargadasDTO> res = descuentoBO.obtenerOperacionesPrecargadas(sesionCliente.getCliente(),
				ultimoRegistro);
		if (EstadoRespuesta.OK.equals(res.getEstadoRespuesta())) {
			guardarDatosEnSesion(operacionIn.getPrimerLlamada(), res.getRespuesta().getDto());
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_OPERACIONES_PRECARGADAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(OperacionesPrecargadasView.class,
					new OperacionesPrecargadasView(res.getRespuesta(), sesionParametros.getRegistroSession().isMobile()));
		}
		if (!sesionParametros.getIsCalificado() && res.getItemsMensajeRespuesta().get(0).getTipoError()
				.equals(TipoError.ERROR_OP_PRECARGADAS_SIN_OPERACIONES.getDescripcion())) {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_OPERACIONES_PRECARGADAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaError("noCalificado", TipoError.ERROR_OP_PRECARGADAS_NO_CALIFICADO,
					CodigoMensajeConstantes.ERROR_OP_PRECARGADAS_NO_CALIFICADO);
		}
		LOGGER.info("ML en este punto habilita menu");
		if (!sesionParametros.getIsCalificadoModuloDescCheques()) {
			LOGGER.info("ML evaluo getIsCalificadoModuloDescCheques !" + sesionParametros.getIsCalificadoModuloDescCheques());
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_OPERACIONES_PRECARGADAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("errorGenerico", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_OP_PRECARGADAS_ERROR_GENERICO);
		}
		if (TipoError.ERROR_OP_PRECARGADAS_SIN_OPERACIONES.getDescripcion()
				.equals(res.getItemsMensajeRespuesta().get(0).getTipoError())) {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_OPERACIONES_PRECARGADAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaError(OperacionesPrecargadasView.class,
					res.getItemsMensajeRespuesta());
		}
		estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_OPERACIONES_PRECARGADAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(OperacionesPrecargadasView.class, res.getItemsMensajeRespuesta());
	}

	/**
	 * Guardar datos en sesion.
	 *
	 * @param primerLlamada
	 *            the primer llamada
	 * @param dto
	 *            the dto
	 */
	private void guardarDatosEnSesion(boolean primerLlamada, List<OperacionPrecargadaDTO> dto) {
		HashSaverAux hashSaver;
		if (primerLlamada) {
			hashSaver = new HashSaverAux();
		} else {
			hashSaver = getObjectFromJsonString(sesionParametros.getListaClavesJson(), HashSaverAux.class);
			if (hashSaver == null) {
				hashSaver = new HashSaverAux();
			}
		}
		for (OperacionPrecargadaDTO operacion : dto) {
			hashSaver.getData().add(operacion.getNumeroOperacion());
		}
		sesionParametros.setListaClavesJson(getJsonValueAsString(hashSaver));
	}

	/**
	 * Gets the json value as string.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return the json value as string
	 */
	public <T> String getJsonValueAsString(T object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonGenerationException e) {
			LOGGER.error("Error generando el json para guardar en sesion :" + e);
		} catch (JsonMappingException e) {
			LOGGER.error("Error mapeando el json para guardar en sesion :" + e);
		} catch (IOException e) {
			LOGGER.error("Error guardando el json :" + e);
		}
		return null;
	}

	/**
	 * Gets the object from json string.
	 *
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            the json
	 * @param classOfT
	 *            the class of T
	 * @return the object from json string
	 */
	public <T> T getObjectFromJsonString(String json, Class<T> classOfT) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

		try {
			return objectMapper.readValue(json, classOfT);
		} catch (JsonGenerationException e) {
			LOGGER.error("Error generando el json para guardar en sesion :" + e);
		} catch (JsonMappingException e) {
			LOGGER.error("Error mapeando el json para guardar en sesion :" + e);
		} catch (IOException e) {
			LOGGER.error("Error guardando el json :" + e);
		}

		return null;
	}

	/**
	 * Obtener ultimo registro.
	 *
	 * @param primerEntrada
	 *            the primer entrada
	 * @return the string
	 */
	private String obtenerUltimoRegistro(boolean primerEntrada) {
		String ultimoRegistro;
		if (primerEntrada) {
			ultimoRegistro = ISBANStringUtils.repeat(" ", 10);
			sesionParametros.setValidacionHash(ultimoRegistro);
		} else {
			ultimoRegistro = sesionParametros.getValidacionHash();
		}
		return ultimoRegistro;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#eliminarOperacion(java.lang.String)
	 */
	@Override
	public Respuesta<Void> eliminarOperacion(String nroTramite) {
		Respuesta<Void> respuesta = descuentoBO.eliminarOperacion(sesionCliente.getCliente(), nroTramite);
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_ELIMINACION_OPERACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_ELIMINACION_OPERACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#obtenerDetalleOperacionesPrecargadas(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<DetalleOperacionesPrecargadasView> obtenerDetalleOperacionesPrecargadas(
			DetalleOperacionesPrecargadasView operacionIn) throws InvalidActivityException {
		HashSaverAux hashSaver = getObjectFromJsonString(sesionParametros.getListaClavesJson(), HashSaverAux.class);
		if (hashSaver == null) {
			hashSaver = new HashSaverAux();
		}
		if (!hashSaver.getData().contains(operacionIn.getNumeroTramite())) {
			throw new InvalidActivityException();
		}
		sesionParametros.setContador(null);
		Respuesta<DetalleOperacionesPrecargadasDTO> resDTO = descuentoBO.obtenerDetalleOperacionesPrecargadas(
				sesionCliente.getCliente(), operacionIn.getNumeroTramite(),
				sesionParametros.getCuentaSeleccionadaParaTransferencia("NROCUENTA"), operacionIn.getEsSimulacion());
		String codigoEstadistica = EstadisticasConstants.DESCUENTO_CHEQUES_DETALLE_OPERACIONES_PREC;
		if (operacionIn.getEsSimulacion()) {
			codigoEstadistica = EstadisticasConstants.DESCUENTO_CHEQUES_SIMULACION_TASAS;
		}
		if (EstadoRespuesta.OK.equals(resDTO.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			Respuesta<DetalleOperacionesPrecargadasView> res = respuestaFactory.crearRespuestaOk(
					DetalleOperacionesPrecargadasView.class,
					new DetalleOperacionesPrecargadasView(resDTO.getRespuesta()));
			sesionParametros.setComprobanteJson(getJsonValueAsString(res.getRespuesta()));
			return res;
		} else if (EstadoRespuesta.WARNING.equals(resDTO.getEstadoRespuesta())) {
			Respuesta<DetalleOperacionesPrecargadasView> res = respuestaFactory.crearRespuestaWarning(
					new DetalleOperacionesPrecargadasView(resDTO.getRespuesta()), resDTO.getItemsMensajeRespuesta());
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			sesionParametros.setComprobanteJson(getJsonValueAsString(res.getRespuesta()));
			return res;
		}
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(DetalleOperacionesPrecargadasView.class,
				resDTO.getItemsMensajeRespuesta());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#obtenerHistorialOperaciones(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.HistorialOperacionesView)
	 */
	@Override
	public Respuesta<HistorialOperacionesView> obtenerHistorialOperaciones(HistorialOperacionesView operacionIn) {
		String estadistica = EstadisticasConstants.DESCUENTO_CHEQUES_HISTORIAL_OPERACIONES;
		if (operacionIn.getEstadoFiltro() != null) {
			estadistica = EstadisticasConstants.DESCUENTO_CHEQUES_HISTORIAL_OPERACIONES_FILTRO;
		}
		String ultimoRegistro = obtenerUltimoRegistro(operacionIn.getPrimerLlamada());
		Respuesta<OperacionesPrecargadasDTO> res = descuentoBO.obtenerHistorialOperaciones(sesionCliente.getCliente(),
				ultimoRegistro, operacionIn.getEstadoFiltro());
		if (EstadoRespuesta.OK.equals(res.getEstadoRespuesta())) {
			guardarDatosEnSesion(operacionIn.getPrimerLlamada(), res.getRespuesta().getDto());
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(HistorialOperacionesView.class,
					new HistorialOperacionesView(res.getRespuesta(), sesionParametros.getRegistroSession().isMobile()));
		}
		if (EstadoRespuesta.WARNING.equals(res.getEstadoRespuesta())) {
			guardarDatosEnSesion(operacionIn.getPrimerLlamada(), res.getRespuesta().getDto());
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaWarning(new HistorialOperacionesView(res.getRespuesta(), sesionParametros.getRegistroSession().isMobile()),
					res.getItemsMensajeRespuesta());
		}
		if (!sesionParametros.getIsCalificado()) {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_HISTORIAL_OPERACIONES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			Respuesta<HistorialOperacionesView> resView = respuestaFactory
					.crearRespuestaError(HistorialOperacionesView.class, "", "noCalificado");
			resView.getItemsMensajeRespuesta().get(0)
					.setTipoError(TipoError.ERROR_SERVICIO_HISTORICO_SIN_OPERACIONES.getDescripcion());
			return resView;
		}
		if (TipoError.ERROR_SERVICIO_HISTORICO_SIN_OPERACIONES.getDescripcion()
				.equals(res.getItemsMensajeRespuesta().get(0).getTipoError())
				|| TipoError.ERROR_HISTORIAL_FILTRO_SIN_OPERACIONES.getDescripcion()
						.equals(res.getItemsMensajeRespuesta().get(0).getTipoError())) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaFactory.crearRespuestaError(HistorialOperacionesView.class, res.getItemsMensajeRespuesta());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#obtenerDetalleHistorialOperaciones(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView)
	 */
	@Override
	public Respuesta<DetalleHistorialOperacionesView> obtenerDetalleHistorialOperaciones(
			DetalleOperacionesPrecargadasView operacionIn) throws InvalidActivityException {
		HashSaverAux hashSaver = getObjectFromJsonString(sesionParametros.getListaClavesJson(), HashSaverAux.class);
		if (hashSaver == null) {
			hashSaver = new HashSaverAux();
		}
		if (!hashSaver.getData().contains(operacionIn.getNumeroTramite())) {
			throw new InvalidActivityException();
		}
		Respuesta<DetalleHistorialOperacionesDTO> resDTO = descuentoBO.obtenerDetalleHistorialOperaciones(
				sesionCliente.getCliente(), operacionIn.getNumeroTramite(),
				sesionParametros.getCuentaSeleccionadaParaTransferencia("NROCUENTA"));
		if (EstadoRespuesta.OK.equals(resDTO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_DETALLE_HISTORIAL_OPERACIONES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(DetalleHistorialOperacionesView.class,
					new DetalleHistorialOperacionesView(resDTO.getRespuesta()));
		}
		estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_DETALLE_HISTORIAL_OPERACIONES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(DetalleHistorialOperacionesView.class,
				resDTO.getItemsMensajeRespuesta());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#descargarOperacionPDF()
	 */
	@Override
	public Respuesta<ReporteView> descargarOperacionPDF() {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerOperacionPDF(getObjectFromJsonString(sesionParametros.getComprobanteJson(),
				DetalleOperacionesPrecargadasView.class));
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_PRECARGADAS_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_PRECARGADAS_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#obtenerSimulacionDeTasas(ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut)
	 */
	@Override
	public Respuesta<AltaChequesViewOut> obtenerSimulacionDeTasas(AltaChequesViewOut chequesView)
			throws InvalidActivityException {

		Respuesta<DetalleOperacionesPrecargadasDTO> resDTO = descuentoBO.obtenerDetalleOperacionesPrecargadas(
				sesionCliente.getCliente(), chequesView.getNumeroDeOperacion(),
				sesionParametros.getCuentaSeleccionadaParaTransferencia("NROCUENTA"), true);
		if (resDTO.getRespuesta() != null && !resDTO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_SIMULACION_TASAS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			sesionParametros.setValidacionHash(getJsonValueAsString(resDTO.getRespuesta()));
			if (resDTO.getRespuesta().getChequesRechazados().isEmpty()) {
				return respuestaFactory.crearRespuestaOk(AltaChequesViewOut.class,
						new AltaChequesViewOut(resDTO.getRespuesta()));
			}
			return respuestaFactory.crearRespuestaWarning(AltaChequesViewOut.class,
					new AltaChequesViewOut(resDTO.getRespuesta()), resDTO.getItemsMensajeRespuesta());
		}
		estadisticaManager.add(EstadisticasConstants.DESCUENTO_CHEQUES_SIMULACION_TASAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(AltaChequesViewOut.class, resDTO.getItemsMensajeRespuesta());
	}

	/**
	 * The Class HashSaverAux.
	 */
	static class HashSaverAux implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 2752600900945895593L;

		/** The data. */
		Set<String> data = new HashSet<String>();

		/**
		 * Gets the data.
		 *
		 * @return the data
		 */
		public Set<String> getData() {
			return data;
		}

		/**
		 * Sets the data.
		 *
		 * @param data
		 *            the new data
		 */
		public void setData(Set<String> data) {
			this.data = data;
		}

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.web.manager.DescuentoChequesManager#obtenerEfectivizacionDeTasas()
	 */
	@Override
	public Respuesta<AltaChequesViewOut> obtenerEfectivizacionDeTasas() {
		if (sesionParametros.getContador() == null) {
			sesionParametros.setContador(new ContadorIntentos(2));
		}
		String cuentaString = sesionParametros.getCuentaSeleccionadaParaTransferencia("NROCUENTA");
		cuentaString = cuentaString.split("-")[1].replace("/", "");
		Cuenta cuentaSeleccionada = sesionCliente.getCliente().getCuentaSiContieneNumero(cuentaString);
		Respuesta<ChequesSimuladosDTO> dto = altaCheques.simularAltaCheques(sesionCliente.getCliente(),
				sesionParametros.getSubproductoPaquete(),
				getObjectFromJsonString(sesionParametros.getCesionCheques(), DatosCesionDTO.class), cuentaSeleccionada,
				new AltaChequesViewIn(getObjectFromJsonString(sesionParametros.getValidacionHash(),
						DetalleOperacionesPrecargadasDTO.class)));
		String estadistica = EstadisticasConstants.FEEEDBACK_SIMULACION_TASAS;
		sesionParametros.setEnSimulacionTasas(true);
		if (EstadoRespuesta.OK.equals(dto.getEstadoRespuesta())) {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			sesionParametros.setContador(null);
			AltaChequesViewOut viewOut = new AltaChequesViewOut(dto.getRespuesta());
			guardarHashEnSesion(viewOut);
			return respuestaFactory.crearRespuestaOk(AltaChequesViewOut.class, viewOut);
		} else if (EstadoRespuesta.ERROR.equals(dto.getEstadoRespuesta())) {
			if(TipoError.ERROR_LEGALES.getDescripcion().equals(dto.getItemsMensajeRespuesta().get(0).getTipoError())){
				sesionParametros.setFalloLegales(true);
				sesionParametros.setEnSimulacionTasas(true);
				AltaChequesViewOut feedback = new AltaChequesViewOut();
				feedback.setMensajeFeedback(
						mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_TASAS_OK).getMensaje());
				estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(AltaChequesViewOut.class, feedback);
			}
			setearEstadoReintento(dto, false);
			if ("errorGenericoConTimeOut".equals(dto.getItemsMensajeRespuesta().get(0).getTag())||"fueraDeHorario".equals(dto.getItemsMensajeRespuesta().get(0).getTag())) {
				estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			} else {
				estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			return respuestaFactory.crearRespuestaError(AltaChequesViewOut.class, dto.getItemsMensajeRespuesta());
		}
		estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		AltaChequesViewOut viewOut = new AltaChequesViewOut(dto.getRespuesta());
		guardarHashEnSesion(viewOut);
		setearEstadoReintento(dto, false);
		sesionParametros.setContador(null);
		return respuestaFactory.crearRespuestaWarning(viewOut, dto.getItemsMensajeRespuesta());
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

}
