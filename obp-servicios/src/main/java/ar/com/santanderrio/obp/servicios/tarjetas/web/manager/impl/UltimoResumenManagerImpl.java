/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ResumenAnteriorBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.UltimoResumenBo;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.UltimoResumenManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.util.EstadisticasTarjetasUtil;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.UltimoResumenView;

/**
 * The Class UltimoResumenManagerImpl.
 */
@Component
public class UltimoResumenManagerImpl extends TarjetasManagerImpl implements UltimoResumenManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UltimoResumenManagerImpl.class);

	/** The ultimo resumen tarjeta service. */
	@Autowired
	private UltimoResumenBo ultimoResumenBO;

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The resumen anterior. */
	@Autowired
	private ResumenAnteriorBO resumenAnteriorBO;

	/**
	 * Obtiene el ultimo resumen.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<UltimoResumenView> obtenerUltimoResumen(String cuenta) {
		Respuesta<UltimoResumenView> respuesta = new Respuesta<UltimoResumenView>();
		try {
			Cliente cliente = sesionCliente.getCliente();
			LOGGER.info("Cliente en Sesion {}.", cliente.toString());
			respuesta = generarRespuestaUltimoResumen(cliente, new IdentificacionCuenta(cuenta));
		} catch (BusinessException e) {
			LOGGER.error("Business Exception", e);
			armarRespuestaError();
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.UltimoResumenManager#descargarResumenActualOnDemand(java.lang.String)
	 */
	@Override
	public Respuesta<ReporteView> descargarResumenActualOnDemand(String nroCuenta) {
		Cliente cliente = sesionCliente.getCliente();
		LOGGER.info("Cliente en Sesion {}.", cliente);
		IdentificacionCuenta cuenta = new IdentificacionCuenta(nroCuenta);
		Respuesta<ReporteView> ultimoReporte = resumenAnteriorBO.obtenerResumenAnterior(cuenta, cliente);
		if (EstadoRespuesta.OK.equals(ultimoReporte.getEstadoRespuesta())) {
			this.getEstadisticaManager().add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGA_RESUMEN_PUNTUAL_ONDEMAND_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			this.getEstadisticaManager().add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGA_RESUMEN_PUNTUAL_ONDEMAND_PDF,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return ultimoReporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * UltimoResumenManager#verDetalleUltimoResumen()
	 */
	@Override
	public Respuesta<Void> verDetalleUltimoResumen() {
		crearLogEstadistica(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_CONSUMO,
				EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/**
	 * Generar respuesta ultimo resumen.
	 *
	 * @param cliente
	 *            the cliente
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<UltimoResumenView> generarRespuestaUltimoResumen(Cliente cliente,
			IdentificacionCuenta identificacionCuenta) throws BusinessException {
		Respuesta<UltimoResumenDTO> respuestaDTO = ultimoResumenBO.obtenerUltimoResumen(cliente, identificacionCuenta);
		Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
		Boolean esMaster = cuenta.esTarjetaMaster();
		if (esRespuestaOK(respuestaDTO)) {
			return crearRespuestaOK(respuestaDTO, esMaster);
		} else if (esRespuestaSinUltimoResumen(respuestaDTO)) {
			return armarRespuestaErrorSinUltimoResumen(respuestaDTO, esMaster);
		} else if (esRespuestaWarning(respuestaDTO)) {
			return armarRespuestaWarningSinConsumos(respuestaDTO, esMaster);
		}
		return armarRespuestaErrorServicio(respuestaDTO, esMaster);
	}

	/**
	 * Es respuesta sin ultimo resumen.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the boolean
	 */
	private Boolean esRespuestaSinUltimoResumen(Respuesta<UltimoResumenDTO> respuestaDTO) {
		if (esRespuestaError(respuestaDTO) && esTipoErrorSinUltimoResumen(respuestaDTO)) {
			return true;
		}
		return false;
	}

	/**
	 * Crear respuesta OK.
	 *
	 * @param dto
	 *            the dto
	 * @param esMaster
	 *            the es master
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenView> crearRespuestaOK(Respuesta<UltimoResumenDTO> dto, Boolean esMaster) {
		crearEstadisticaOK(esMaster);
		return casuistica.crearRespuestaOk(UltimoResumenView.class, new UltimoResumenView(dto.getRespuesta()));
	}

	/**
	 * Armar respuesta error sin ultimo resumen.
	 *
	 * @param dto
	 *            the dto
	 * @param esMaster
	 *            the es master
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenView> armarRespuestaErrorSinUltimoResumen(Respuesta<UltimoResumenDTO> dto,
			Boolean esMaster) {
		crearEstadisticaOK(esMaster);
		return casuistica.crearRespuestaError(UltimoResumenView.class, dto.getItemsMensajeRespuesta());
	}

	/**
	 * Armar respuesta error servicio.
	 *
	 * @param dto
	 *            the dto
	 * @param esMaster
	 *            the es master
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenView> armarRespuestaErrorServicio(Respuesta<UltimoResumenDTO> dto,
			Boolean esMaster) {
		crearEstadisticaError(esMaster);
		return casuistica.crearRespuestaError(UltimoResumenView.class, dto.getItemsMensajeRespuesta());
	}

	/**
	 * Armar respuesta warning sin consumos.
	 *
	 * @param dto
	 *            the dto
	 * @param esMaster
	 *            the es master
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenView> armarRespuestaWarningSinConsumos(Respuesta<UltimoResumenDTO> dto,
			Boolean esMaster) {
		crearEstadisticaOK(esMaster);
		return casuistica.crearRespuestaWarning(UltimoResumenView.class, new UltimoResumenView(dto.getRespuesta()),
				dto.getItemsMensajeRespuesta());
	}

	/**
	 * Armar respuesta error.
	 *
	 * @return the respuesta
	 */
	private Respuesta<UltimoResumenView> armarRespuestaError() {
		crearEstadisticaError();
		return casuistica.crearRespuestaError(TagItemMensajeTarjetaEnum.ULTIMORESUMEN.getDescripcionTag(),
				TipoError.ERROR_CARGA_ULTIMO_RESUMEN, CodigoMensajeConstantes.CODIGO_ERROR_CARGA_ULTIMO_RESUMEN);
	}

	/**
	 * Crear estadistica OK.
	 *
	 * @param esMaster
	 *            the es master
	 */
	private void crearEstadisticaOK(Boolean esMaster) {
		String estadistica = EstadisticasTarjetasUtil.getCodigoEstadisticaUltimoResumen();
		if (esMaster) {
			estadistica = EstadisticasConstants.ULTIMO_RESUMEN_TARJETA_MASTER;
		}
		this.getEstadisticaManager().add(estadistica, EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear estadistica error.
	 *
	 * @param esMaster
	 *            the es master
	 */
	private void crearEstadisticaError(Boolean esMaster) {
		String estadistica = EstadisticasTarjetasUtil.getCodigoEstadisticaUltimoResumen();
		if (esMaster) {
			estadistica = EstadisticasConstants.ULTIMO_RESUMEN_TARJETA_MASTER;
		}
		this.getEstadisticaManager().add(estadistica, EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear estadistica error.
	 */
	private void crearEstadisticaError() {
		this.getEstadisticaManager().add(EstadisticasTarjetasUtil.getCodigoEstadisticaUltimoResumen(),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Es respuesta error.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the boolean
	 */
	private Boolean esRespuestaError(Respuesta<UltimoResumenDTO> respuestaDTO) {
		if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return true;
		}
		return false;
	}

	/**
	 * Es respuesta warning.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the boolean
	 */
	private Boolean esRespuestaWarning(Respuesta<UltimoResumenDTO> respuestaDTO) {
		if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return true;
		}
		return false;
	}

	/**
	 * Es tipo error sin ultimo resumen.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the boolean
	 */
	private Boolean esTipoErrorSinUltimoResumen(Respuesta<UltimoResumenDTO> respuestaDTO) {
		String tipoError = respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError();
		if ("ERROR_SIN_ULTIMO_RESUMEN".equals(tipoError)) {
			return true;
		}
		return false;
	}

}
