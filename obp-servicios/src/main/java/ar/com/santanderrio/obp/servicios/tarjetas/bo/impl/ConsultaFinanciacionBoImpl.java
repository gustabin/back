/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.SolicitudPlanV;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ConsultaFinanciacionBo;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.ConsultaFinanciacionDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDetalleDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionLineaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ConsultaFinanciacionBoImpl.
 */
@Component
public class ConsultaFinanciacionBoImpl extends TarjetasBOImpl implements ConsultaFinanciacionBo {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaFinanciacionBoImpl.class);

	/** The Constant ERROR_CODE_SIN_CONSUMOS. */
	private static final String ERROR_CODE_SIN_CONSUMOS = "fc1:1";

	/** The consulta financiacion DAO. */
	@Autowired
	private ConsultaFinanciacionDAO consultaFinanciacionDAO;
	
	/** The reporte dao. */
	@Autowired
	private ReporteDAO reporteDAO;

	/**
	 * Obtiene la lista de los planes vigentes, estan en curso y no se
	 * liquidaron aun para la tarjeta seleccionada. Visa o Amex. Recargables no.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<ConsultaFinanciacionDTO> obtenerListaFinanciacion(IdentificacionCuenta identificacionCuenta,
			Cliente cliente) throws BusinessException {
		try {
			Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
			List<SolicitudPlanV> respuestaFinanciacion = obtenerRespuestaFinanciacionWS(cuenta);
			List<ConsultaFinanciacionDetalleDTO> financiaciones = obtenerFinanciaciones(cuenta, respuestaFinanciacion);
			return armarRespuestaOk(financiaciones);
		} catch (DAOException e) {
			LOGGER.error("ERROR al obtener los datos de las financiaciones de la tarjeta titular {}.", cliente, e);
			return armarRespuestaErrorWS(e);
		} catch (Exception e) {
			LOGGER.error("ERROR al obtener los datos de las tarjetas del Cliente {}. {}.", cliente, e);
			throw new BusinessException(e);
		}
	}

	/**
	 * Obtener respuesta financiacion WS.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	private List<SolicitudPlanV> obtenerRespuestaFinanciacionWS(Cuenta cuenta) throws DAOException {
		return consultaFinanciacionDAO.obtenerListaFinanciacion(cuenta);
	}

	/**
	 * Obtener financiaciones.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param respuestaFinanciacion
	 *            the respuesta financiacion
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	private List<ConsultaFinanciacionDetalleDTO> obtenerFinanciaciones(Cuenta cuenta,
			List<SolicitudPlanV> respuestaFinanciacion) throws TarjetaBOUtilsException {
		String marca = TarjetaUtils.getMarca(cuenta);
		List<ConsultaFinanciacionDetalleDTO> listaConsultaFinanciacion = new ArrayList<ConsultaFinanciacionDetalleDTO>();
		List<String> listaNumeroTarjeta = obtenerNumerosTarjetas(cuenta, marca, respuestaFinanciacion);
		for (int i = 0; i < listaNumeroTarjeta.size(); i++) {
			String numeroTarjeta = TarjetaBOUtils.cortarNumeroTarjeta(listaNumeroTarjeta.get(i), marca);
			ConsultaFinanciacionDetalleDTO consultaFinanciacion = obtenerConsultaFinanciacion(marca, numeroTarjeta,
					respuestaFinanciacion);
			if(CollectionUtils.isNotEmpty(consultaFinanciacion.getFinanciaciones())){
				listaConsultaFinanciacion.add(consultaFinanciacion);
			}
		}
		return listaConsultaFinanciacion;
	}

	/**
	 * Esta en.
	 *
	 * @param spv
	 *            the spv
	 * @param listaNumeroTarjeta
	 *            the lista numero tarjeta
	 * @return true, if successful
	 */
	private boolean estaEn(SolicitudPlanV spv, List<String> listaNumeroTarjeta) {
		return listaNumeroTarjeta.contains(spv.getNumeroTarjeta());
	}

	/**
	 * Obtener consulta financiacion.
	 *
	 * @param marca
	 *            the marca
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @param respuestaFinanciacion
	 *            the respuesta financiacion
	 * @return the consulta financiacion DTO
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	private ConsultaFinanciacionDetalleDTO obtenerConsultaFinanciacion(String marca, String numeroTarjeta,
			List<SolicitudPlanV> respuestaFinanciacion) throws TarjetaBOUtilsException {
		ConsultaFinanciacionDetalleDTO consultaFinanciacionDTO = new ConsultaFinanciacionDetalleDTO();
		consultaFinanciacionDTO.setMarca(marca);
		consultaFinanciacionDTO.setNumeroTarjeta(TarjetaBOUtils.formatearNumeroTarjeta(numeroTarjeta, marca));
		consultaFinanciacionDTO.setFinanciaciones(obtenerFinanciaciones(numeroTarjeta, respuestaFinanciacion));
		return consultaFinanciacionDTO;
	}

	/**
	 * Obtener financiaciones.
	 *
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @param respuestaFinanciacion
	 *            the respuesta financiacion
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	private List<ConsultaFinanciacionLineaDTO> obtenerFinanciaciones(String numeroTarjeta,
			List<SolicitudPlanV> respuestaFinanciacion) throws TarjetaBOUtilsException {
		List<ConsultaFinanciacionLineaDTO> financiaciones = new ArrayList<ConsultaFinanciacionLineaDTO>();
		for (int i = 0; i < respuestaFinanciacion.size(); i++) {
			SolicitudPlanV spv = respuestaFinanciacion.get(i);
			if (spv != null && esNumeroTarjeta(numeroTarjeta, spv)) {
				ConsultaFinanciacionLineaDTO financiacion = new ConsultaFinanciacionLineaDTO();
				financiacion.setCostoFinanciero(BigDecimal.valueOf(spv.getCFT()));
				financiacion.setCantidadCuotas(spv.getCantidadCuotas());
				financiacion.setCuotasPendientesALiquidar(spv.getCuotasPendientesDeLiquidar());
				financiacion.setFechaSolicitud(ISBANStringUtils.formatearFechaPlanV(spv.getFechaSolicitud()));
				financiacion.setMontoCuotaDelPlan(BigDecimal.valueOf(spv.getImporteCuota()));
				financiacion.setMontoTotalDelPlanEnPesos(BigDecimal.valueOf(spv.getMontoSolicitud()));
				financiacion.setNumeroDeSolicitud(spv.getNumeroComprobante());
				financiacion.setTasaAnualAplicada(BigDecimal.valueOf(spv.getTNA()));
				financiaciones.add(financiacion);
			}
		}
		return financiaciones;
	}

	/**
	 * Es numero tarjeta.
	 *
	 * @param nt1
	 *            the nt 1
	 * @param spv
	 *            the spv
	 * @return the boolean
	 */
	private Boolean esNumeroTarjeta(String nt1, SolicitudPlanV spv) {
		return nt1.equals(spv.getNumeroTarjeta());
	}

	/**
	 * Obtener numeros tarjetas.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param marca
	 *            the marca
	 * @param respuestaFinanciacion
	 *            the respuesta financiacion
	 * @return the list
	 */
	private List<String> obtenerNumerosTarjetas(Cuenta cuenta, String marca,
			List<SolicitudPlanV> respuestaFinanciacion) {
		String numeroTarjetaTitular = TarjetaBOUtils.cortarNumeroTarjeta(cuenta.getNroTarjetaCredito(), marca);
		List<String> listaNumeroTarjeta = new ArrayList<String>();
		listaNumeroTarjeta.add(numeroTarjetaTitular);
		for (int i = 0; i < respuestaFinanciacion.size(); i++) {
			SolicitudPlanV spv = respuestaFinanciacion.get(i);
			if (!estaEn(spv, listaNumeroTarjeta)) {
				listaNumeroTarjeta.add(spv.getNumeroTarjeta());
			}
		}
		return listaNumeroTarjeta;
	}

	/**
	 * Armar respuesta ok.
	 *
	 * @param financiaciones
	 *            the financiaciones
	 * @return the respuesta
	 */
	private Respuesta<ConsultaFinanciacionDTO> armarRespuestaOk(List<ConsultaFinanciacionDetalleDTO> financiaciones) {
		ConsultaFinanciacionDTO dto = new ConsultaFinanciacionDTO();
		dto.setDetallesFinanciaciones(financiaciones);
		return getRespuestaFactory().crearRespuestaOk(ConsultaFinanciacionDTO.class, dto);
	}

	/**
	 * Armar respuesta con ERROR. TipoError.ERROR_CARGA_FINANCIACIONES (1074)
	 *
	 * @return the respuesta
	 */
	public Respuesta<ConsultaFinanciacionDTO> armarRespuestaError() {
		return getRespuestaFactory().crearRespuestaError(null, TipoError.ERROR_CARGA_FINANCIACIONES,
				CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_FINANCIACION);
	}

	/**
	 * TipoError.ERROR_SIN_FINANCIACIONES (1075)
	 *
	 * @return the respuesta
	 */
	private Respuesta<ConsultaFinanciacionDTO> armarRespuestaSinFinanciaciones() {
		return getRespuestaFactory().crearRespuestaWarning(
				TagItemMensajeTarjetaEnum.CONSULTA_FINANCIACION.getDescripcionTag(), TipoError.ERROR_SIN_FINANCIACIONES,
				CodigoMensajeConstantes.CODIGO_ERROR_SIN_FINANCIACIONES);
	}

	/**
	 * Armar respuesta error WS.
	 *
	 * @param e
	 *            the e
	 * @return the respuesta
	 */
	private Respuesta<ConsultaFinanciacionDTO> armarRespuestaErrorWS(DAOException e) {
		String errorCode = e.getErrorCode();
		if ((errorCode != null) && (ERROR_CODE_SIN_CONSUMOS.equals(errorCode))) {
			return armarRespuestaSinFinanciaciones();
		}
		return armarRespuestaError();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.ConsultaFinanciacionBo#obtenerReporte(java.lang.Object, java.lang.String, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Reporte> obtenerReporte(Object body, String proceso, Cliente cliente) {
		return reporteDAO.obtenerReporte(body, proceso, cliente,false);
	}

}
