/*
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
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CuotasPendientesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuotasPendientesTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.CuotasPendienteManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.util.EstadisticasTarjetasUtil;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesLineaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesView;

// TODO: Auto-generated Javadoc
/**
 *
 * The Class CuotasPendienteManagerImpl.
 *
 */
@Component
public class CuotasPendienteManagerImpl implements CuotasPendienteManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuotasPendienteManagerImpl.class);

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The Constant ERROR_ESTADISTICA. */
	public static final String ERROR_ESTADISTICA = "Error al grabar estadistica de Cuotas Pendientes ";

	/** The Constant TEMPLATE. */
	private static final String TEMPLATE = "consultadeCuotasPendientes";

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The registro sesion. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The CuotasPendientesBO. */
	@Autowired
	private CuotasPendientesBO cuotasPendientesBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/**
	 * Obtiene una respuesta con los datos de la cuotas pendientes de la tarjeta
	 * ingresada por parametro.
	 *
	 * @param tarjetaActiva
	 *            the tarjeta activa
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CuotasPendientesView> obtenerCuotasPendientes(TarjetaActivaView tarjetaActiva) {
		try {
			Cliente cliente = sesionCliente.getCliente();
			LOGGER.info("Cliente en Sesion {}.", cliente.toString());
			IdentificacionCuenta idCuenta = new IdentificacionCuenta(tarjetaActiva.getTarjetaActiva());
			return generarRespuestaView(idCuenta, cliente);
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return crearRespuestaErrorGenerico();
		}
	}

	/**
	 * Invoca al bo para obtener las cuotas pendientes de la identificacion de
	 * cuenta. Genera las respuesta
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesView> generarRespuestaView(IdentificacionCuenta idCuenta, Cliente cliente) {
		try {
			Respuesta<CuotasPendientesDTO> respuestaDTO = cuotasPendientesBO.obtenerCuotasPendientes(idCuenta, cliente);
			if (esEstadoRespuestaOK(respuestaDTO)) {
				Respuesta<CuotasPendientesView> view = crearRespuestaOk(respuestaDTO, idCuenta, cliente);
				sesionParametros.setResCuotasPendientes(view);
				return view;
			}
			if (esEstadoRespuestaError(respuestaDTO) && esTipoErrorSinCuotasPendientes(respuestaDTO)) {
				return crearRespuestaErrorSinCuotas(idCuenta, cliente);
			}
			return crearRespuestaError(idCuenta, cliente);
		} catch (BusinessException e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return crearRespuestaError(idCuenta, cliente);
		}
	}

	/**
	 * Respuesta OK.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesView> crearRespuestaOk(Respuesta<CuotasPendientesDTO> respuestaBO,
			IdentificacionCuenta idCuenta, Cliente cliente) {
		crearEstadisticaOK(idCuenta, cliente);
		return respuestaFactory.crearRespuestaOk(CuotasPendientesView.class,
				new CuotasPendientesView(respuestaBO.getRespuesta()));
	}

	/**
	 * Respuesta ERROR TipoError.ERROR_CARGA_CUOTAS_PENDIENTE. (110005)
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesView> crearRespuestaErrorSinCuotas(IdentificacionCuenta idCuenta,
			Cliente cliente) {
		crearEstadisticaOK(idCuenta, cliente);
		return respuestaFactory.crearRespuestaError(null, TipoError.SIN_CUOTAS_PENDIENTES,
				CodigoMensajeConstantes.TARJETA_SIN_CUOTAS_PENDIENTES);
	}

	/**
	 * Respuesta ERROR TipoError.ERROR_CARGA_CUOTAS_PENDIENTE. (110005)
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesView> crearRespuestaError(IdentificacionCuenta idCuenta, Cliente cliente) {
		crearEstadisticaERROR(idCuenta, cliente);
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.TARJETA_ERROR_CARGA_CUOTAS_PENDIENTES);
	}

	/**
	 * Respuesta ERROR TipoError.ERROR_CARGA_CUOTAS_PENDIENTE. (110005)
	 *
	 * @return the respuesta
	 */
	private Respuesta<CuotasPendientesView> crearRespuestaErrorGenerico() {
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.TARJETA_ERROR_CARGA_CUOTAS_PENDIENTES);
	}

	/**
	 * Crear estadistica.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 */
	private void crearEstadisticaOK(IdentificacionCuenta idCuenta, Cliente cliente) {
		try {
			String marca = cuotasPendientesBO.obtenerMarcaDeTarjeta(idCuenta, cliente);
			estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaCuotasPendientes(marca),
					EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
		} catch (BusinessException e) {
			LOGGER.info(ERROR_ESTADISTICA, e);
		}
	}

	/**
	 * Crear estadistica.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @param cliente
	 *            the cliente
	 */
	private void crearEstadisticaERROR(IdentificacionCuenta idCuenta, Cliente cliente) {
		try {
			String marca = cuotasPendientesBO.obtenerMarcaDeTarjeta(idCuenta, cliente);
			estadisticaManager.add(EstadisticasTarjetasUtil.getCodigoEstadisticaCuotasPendientes(marca),
					EstadisticasTarjetasUtil.getCodigoEstadisticaError());
		} catch (BusinessException e) {
			LOGGER.info(ERROR_ESTADISTICA, e);
		}
	}

	/**
	 * Es estado respuesta OK.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean esEstadoRespuestaOK(Respuesta<CuotasPendientesDTO> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK);
	}

	/**
	 * Es estado respuesta error.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean esEstadoRespuestaError(Respuesta<CuotasPendientesDTO> respuesta) {
		return respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR);
	}

	/**
	 * Es tipo error sin cuotas pendientes.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean esTipoErrorSinCuotasPendientes(Respuesta<CuotasPendientesDTO> respuesta) {
		return respuesta.getItemsMensajeRespuesta().get(0).getTipoError()
				.equals(TipoError.SIN_CUOTAS_PENDIENTES.getDescripcion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * CuotasPendienteManager#descargarExcelCuotasPendientes()
	 */
	@Override
	public Respuesta<ReporteView> descargarExcelCuotasPendientes() {
		Respuesta<Reporte> reporte = obtenerReporte(sesionParametros.getResCuotasPendientes().getRespuesta());
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_EXCEL_CUOTAS_PENDIENTES,
					EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGA_EXCEL_CUOTAS_PENDIENTES,
					EstadisticasTarjetasUtil.getCodigoEstadisticaError());
		}
		return respuestaView;
	}

	/**
	 * Obtener reporte.
	 *
	 * @param cuotas
	 *            the cuotas
	 * @return the respuesta
	 */
	private Respuesta<Reporte> obtenerReporte(CuotasPendientesView cuotas) {
		Cliente cliente = sesionCliente.getCliente();
		Date fechaActual = new Date();
		List<CuotasPendientesTarjetaDTO> listaCuotas = new ArrayList<CuotasPendientesTarjetaDTO>();
		cargarCuotasPendientes(cuotas, listaCuotas);
		CuotasPendientesDTO cuotasPendientes = new CuotasPendientesDTO(
				ISBANStringUtils.convertirABigDecimal(cuotas.getTotalCuotasPendientes()), listaCuotas,
				ISBANStringUtils.formatearFecha(fechaActual));
		return cuotasPendientesBO.obtenerReporte(cuotasPendientes, TEMPLATE, cliente);
	}

	/**
	 * Cargar cuotas pendientes.
	 *
	 * @param cuotas
	 *            the cuotas
	 * @param listaCuotas
	 *            the lista cuotas
	 */
	private void cargarCuotasPendientes(CuotasPendientesView cuotas, List<CuotasPendientesTarjetaDTO> listaCuotas) {
		for (CuotasPendientesTarjetaView cuotasView : cuotas.getTarjetasCuotasPendientes()) {
			CuotasPendientesTarjetaDTO cuota = new CuotasPendientesTarjetaDTO();
			cuota.setMarca(cuotasView.getMarca());
			cuota.setNumero(cuotasView.getNumero());
			cuota.setEsTitular(cuotasView.getEsTitular());
			cuota.setNombreAdicional(cuotasView.getNombreAdicional());
			cuota.setTotal(ISBANStringUtils.convertirABigDecimal(cuotasView.getTotal()));
			cuota.setLineasCuotasPendientes(obtenerLineas(cuotasView.getLineasCuotasPendientes()));
			listaCuotas.add(cuota);
		}

	}

	/**
	 * Obtener lineas.
	 *
	 * @param lineasCuotasPendientes
	 *            the lineas cuotas pendientes
	 * @return the list
	 */
	private List<CuotasPendientesLineaDTO> obtenerLineas(List<CuotasPendientesLineaView> lineasCuotasPendientes) {
		List<CuotasPendientesLineaDTO> lineas = new ArrayList<CuotasPendientesLineaDTO>();
		for (CuotasPendientesLineaView linea : lineasCuotasPendientes) {
			CuotasPendientesLineaDTO consulta = new CuotasPendientesLineaDTO();
			consulta.setFecha(ISBANStringUtils.formatearFecha(linea.getFecha()));
			consulta.setFechaExcel(linea.getFecha());
			consulta.setEstablecimiento(linea.getEstablecimiento());
			consulta.setCuotasPendientes(Integer.parseInt(linea.getCuotasPendientes()));
			consulta.setRestante(ISBANStringUtils.convertirABigDecimal(linea.getRestante()));
			consulta.setRestanteExcel(linea.getRestante());
			consulta.setComprobante(linea.getOperacion());
			consulta.setPlanDeCuotas(linea.getCantidadCuotas());
			lineas.add(consulta);
		}

		return lineas;
	}
}
