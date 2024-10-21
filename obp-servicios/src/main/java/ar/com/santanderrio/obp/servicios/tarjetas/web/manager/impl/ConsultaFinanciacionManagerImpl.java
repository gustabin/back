/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ConsultaFinanciacionBo;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDetalleDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultarFinanciacionWrapper;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ConsultaFinanciacionManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.util.EstadisticasTarjetasUtil;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsultaFinanciacionDetalleView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsultaFinanciacionLineaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsultaFinanciacionView;

/**
 * 
 * The Class ConsultaFinanciacionManagerImpl.
 *
 *
 */
@Component
public class ConsultaFinanciacionManagerImpl implements ConsultaFinanciacionManager {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaFinanciacionManagerImpl.class);

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The Constant ERROR_ESTADISTICA. */
	public static final String ERROR_ESTADISTICA = "Error al grabar estadistica de Consulta Financiacion ";

	/** The Constant TEMPLATE. */
	private static final String TEMPLATE = "consultadeFinanciaciones";

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion tarjetas. */
	@Autowired
	private SesionTarjetas sesionTarjetas;

	/** The consulta financiacion bo. */
	@Autowired
	private ConsultaFinanciacionBo consultaFinanciacionBo;
	
	/** The registro sesion. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/**
	 * Gets the obtenerConsultaFinanciacion.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the Respuesta<ConsultaFinanciacionView>
	 */
	@Override
	public Respuesta<ConsultaFinanciacionView> obtenerConsultaFinanciacion(String cuenta) {
		try {
			Cliente cliente = sesionCliente.getCliente();
			LOGGER.info("Cliente en Sesion {}.", cliente.toString());
			IdentificacionCuenta idCuenta = new IdentificacionCuenta(cuenta);
			return generarRespuestaConsultaFinanciacion(idCuenta, cliente);
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return crearRespuestaErrorGenerico();
		}
	}

	/**
	 * Generar respuesta consulta financiacion.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	private Respuesta<ConsultaFinanciacionView> generarRespuestaConsultaFinanciacion(IdentificacionCuenta idCuenta,
			Cliente cliente) {
		try {
			Respuesta<ConsultaFinanciacionDTO> dto = consultaFinanciacionBo.obtenerListaFinanciacion(idCuenta, cliente);
			if (esRespuestaOK(dto)) {
				return crearRespuestaOK(idCuenta, cliente, dto.getRespuesta());
			} else {
				if (esRespuestaWARNING(dto)) {
					return crearRespuestaSinFinanciaciones(idCuenta, cliente);
				}
			}
			return crearRespuestaError(idCuenta, cliente);
		} catch (BusinessException e) {
			LOGGER.error("Error al obtener la consulta financiacion.", e);
			return crearRespuestaError(idCuenta, cliente);
		}
	}

	/**
	 * Genera una respuesta OK.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	private Respuesta<ConsultaFinanciacionView> crearRespuestaOK(IdentificacionCuenta idCuenta, Cliente cliente,
			ConsultaFinanciacionDTO dto) {
		crearEstadisticaOK(idCuenta, cliente);
		Respuesta<ConsultaFinanciacionView> view = respuestaFactory.crearRespuestaOk(ConsultaFinanciacionView.class, new ConsultaFinanciacionView(dto));
		sesionParametros.setConsultaFinanciacion(view.getRespuesta());
		return view;
	}

	/**
	 * TipoError.ERROR_SIN_FINANCIACIONES (1075)
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	private Respuesta<ConsultaFinanciacionView> crearRespuestaSinFinanciaciones(IdentificacionCuenta idCuenta,
			Cliente cliente) {
		crearEstadisticaOK(idCuenta, cliente);
		return respuestaFactory.crearRespuestaWarning(
				TagItemMensajeTarjetaEnum.CONSULTA_FINANCIACION.getDescripcionTag(), TipoError.ERROR_SIN_FINANCIACIONES,
				CodigoMensajeConstantes.CODIGO_ERROR_SIN_FINANCIACIONES);
	}

	/**
	 * Armar respuesta con ERROR. TipoError.ERROR_CARGA_FINANCIACIONES (1074)
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	public Respuesta<ConsultaFinanciacionView> crearRespuestaError(IdentificacionCuenta idCuenta, Cliente cliente) {
		crearEstadisticaError(idCuenta, cliente);
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CARGA_FINANCIACIONES,
				CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_FINANCIACION);
	}

	/**
	 * Respuesta ERROR TipoError.ERROR_CARGA_CUOTAS_PENDIENTE. (110005)
	 *
	 * @return the respuesta
	 */
	private Respuesta<ConsultaFinanciacionView> crearRespuestaErrorGenerico() {
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_FINANCIACION);
	}

	/**
	 * Crear estadistica consulta financiacion.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 */
	private void crearEstadisticaOK(IdentificacionCuenta idCuenta, Cliente cliente) {
		try {
			String marca = consultaFinanciacionBo.obtenerMarcaDeTarjeta(idCuenta, cliente);
			estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaConsultaFinanciacion(marca),
					EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
		} catch (BusinessException e) {
			LOGGER.info(ERROR_ESTADISTICA, e);
		}
	}

	/**
	 * Crear estadistica consulta financiacion error.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 */
	private void crearEstadisticaError(IdentificacionCuenta idCuenta, Cliente cliente) {
		try {
			String marca = consultaFinanciacionBo.obtenerMarcaDeTarjeta(idCuenta, cliente);
			estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaConsultaFinanciacion(marca),
					EstadisticasTarjetasUtil.getCodigoEstadisticaError());
		} catch (BusinessException e) {
			LOGGER.info(ERROR_ESTADISTICA, e);
		}
	}

	/**
	 * Gets the sesion tarjetas.
	 *
	 * @return the sesion tarjetas
	 */
	public SesionTarjetas getSesionTarjetas() {
		return sesionTarjetas;
	}

	/**
	 * Sets the sesion tarjetas.
	 *
	 * @param sesionTarjetas
	 *            the new sesion tarjetas
	 */
	public void setSesionTarjetas(SesionTarjetas sesionTarjetas) {
		this.sesionTarjetas = sesionTarjetas;
	}

	/**
	 * Es respuesta OK.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean esRespuestaOK(Respuesta<ConsultaFinanciacionDTO> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK);
	}

	/**
	 * Es respuesta WARNING.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean esRespuestaWARNING(Respuesta<ConsultaFinanciacionDTO> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.WARNING);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.ConsultaFinanciacionManager#descargarExcelConsultaFinanciacion()
	 */
	@Override
	public Respuesta<ReporteView> descargarExcelConsultaFinanciacion() {
		Respuesta<Reporte> reporte = obtenerReporte(sesionParametros.getConsultaFinanciacion());
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_EXCEL_FINANCIACION_RESUMEN,
					EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGA_EXCEL_FINANCIACION_RESUMEN,
					EstadisticasTarjetasUtil.getCodigoEstadisticaError());
		}
		return respuestaView;
	}

	/**
	 * Obtener reporte.
	 *
	 * @param financiacion
	 *            the financiacion
	 * @return the respuesta
	 */
	private Respuesta<Reporte> obtenerReporte(ConsultaFinanciacionView financiacion) {
		Cliente cliente = sesionCliente.getCliente();
		Date fechaActual = new Date();
		List<ConsultaFinanciacionDetalleDTO> listaConsultaFinanciaciones = obtenerConsultaFinanciaciones(financiacion);
		ConsultarFinanciacionWrapper consulta = new ConsultarFinanciacionWrapper(ISBANStringUtils.formatearFecha(fechaActual), listaConsultaFinanciaciones);
		return consultaFinanciacionBo.obtenerReporte(consulta, TEMPLATE, cliente);
	}

	/**
	 * Obtener consulta financiaciones.
	 *
	 * @param financiacion
	 *            the financiacion
	 * @return the list
	 */
	private List<ConsultaFinanciacionDetalleDTO> obtenerConsultaFinanciaciones(ConsultaFinanciacionView financiacion) {
		List<ConsultaFinanciacionDetalleDTO> listaConsultas = new ArrayList<ConsultaFinanciacionDetalleDTO>();
		for (ConsultaFinanciacionDetalleView financiaciones : financiacion.getListaConsultaFinanciaciones()) {
			ConsultaFinanciacionDetalleDTO consulta = new ConsultaFinanciacionDetalleDTO();
			consulta.setMarca(financiaciones.getMarca());
			consulta.setNumeroTarjeta(financiaciones.getNumeroTarjeta());
			List<ConsultaFinanciacionLineaDTO> listaFinanciaciones = setearLineas(financiaciones.getFinanciaciones());
			consulta.setFinanciaciones(listaFinanciaciones);
			listaConsultas.add(consulta);
		}
		
		return listaConsultas;
	}

/**
 * Setear lineas.
 *
 * @param financiaciones
 *            the financiaciones
 * @return the list
 */
private List<ConsultaFinanciacionLineaDTO> setearLineas(List<ConsultaFinanciacionLineaView> financiaciones) {
	List<ConsultaFinanciacionLineaDTO> lineas = new ArrayList<ConsultaFinanciacionLineaDTO>();
	for (ConsultaFinanciacionLineaView consulta : financiaciones) {
		ConsultaFinanciacionLineaDTO linea = new ConsultaFinanciacionLineaDTO();
		linea.setFechaSolicitud(ISBANStringUtils.formatearFecha(consulta.getFechaSolicitud()));
		linea.setNumeroDeSolicitud(consulta.getNumeroDeSolicitud());
		linea.setMontoTotalDelPlanEnPesos(ISBANStringUtils.convertirABigDecimal(consulta.getMontoTotalDelPlanEnPesos()));
		linea.setMontoCuotaDelPlan(ISBANStringUtils.convertirABigDecimal(consulta.getMontoCuotaDelPlan()));
		linea.setCantidadCuotas(Integer.parseInt(consulta.getCantidadCuotas()));
		linea.setCuotasPagadas(Integer.parseInt(consulta.getCuotasPendientesALiquidar()));
		linea.setTasaAnualAplicada(ISBANStringUtils.convertirABigDecimal(consulta.getTasaAnualAplicada()));
		linea.setCostoFinanciero(ISBANStringUtils.convertirABigDecimal(consulta.getCostoFinanciero()));
		linea.setFechaExcel(consulta.getFechaSolicitud());
		linea.setCostoFinancieroExcel(consulta.getCostoFinanciero());
		linea.setMontoCuotaDelPlanExcel(consulta.getMontoCuotaDelPlan());
		linea.setMontoTotalDelPlanEnPesosExcel(consulta.getMontoTotalDelPlanEnPesos());
		linea.setTasaAnualAplicadaExcel(consulta.getTasaAnualAplicada());
		lineas.add(linea);
	}
	return lineas;
}

}
