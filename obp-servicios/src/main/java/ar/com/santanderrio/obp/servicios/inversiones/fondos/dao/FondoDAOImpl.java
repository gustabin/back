/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import java.util.List;

import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.singleton.FondosCotizacionesSingleton;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.exception.FondoSinMovimientosException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMayorAlMaximoException;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteSuscripcionFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFCIOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaTenenciaFDCInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SucursalCuentaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SuscripcionFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SuscripcionFondoOutEntity;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ServicioDeshabilitadoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class FondoDAOImpl.
 *
 * @author
 */
@Component("fondoDAO")
public class FondoDAOImpl extends IatxBaseDAO implements FondoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FondoDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant LARGO_SUCURSAL. */
	private static final int LARGO_SUCURSAL = 3;

	/** The Constant LARGO_NRO_CUENTA. */
	private static final int LARGO_NRO_CUENTA = 10;

	/** The Constant FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int FUERA_HORARIO_CODIGO_RETORNO = 90000024;

	private static final int SERVICIO_FONDOS_DESHABILITADO = 10099906;

	/** The Constant FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int FUERA_HORARIO_CODIGO_RETORNO2 = 90000023;

	/** The Constant FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int FUERA_HORARIO_CODIGO_RETORNO3 = 90000059;

	/** The Constant FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO = 99000024;

	/** The Constant CODIGO_ERROR_CUENTA_SIN_OPERAR. */
	private static final int CODIGO_ERROR_CUENTA_SIN_OPERAR = 10000117;

	/** The Constant CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO. */
	private static final int CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO = 90000100;

	/** The Constant CODIGO_ERROR_IMPORTE_MAYOR_AL_MAXIMO. */
	private static final int CODIGO_ERROR_IMPORTE_MAYOR_AL_MAXIMO = 90000101;

	/** The Constant ID_SISTEMA. */
	private static final String ID_SISTEMA = "FCI";

	private static final String ID_SISTEMA_CCO = "CCO";

	/** The Constant CODIGO_ERROR_SALDO_INSUFICIENTE_1A. */
	private static final int CODIGO_ERROR_SALDO_INSUFICIENTE = 10000515;

	/** The Constant CODIGO_ERROR_SALDO_INSUFICIENTE_1A. */
	private static final int CODIGO_ERROR_SALDO_INSUFICIENTE_USD = 10002122;

	/** The Constant CODIGO_ERROR_SIN_MOVIMIENTOS. */
	private static final int CODIGO_ERROR_SIN_MOVIMIENTOS = 10000155;

	/** The Constant ID_SISTEMA_SALDO_INSUFICIENTE_1. */
	private static final String ID_SISTEMA_SALDO_INSUFICIENTE = "ABG";

	/** The Constant TIMEOUT_EXCEPTION. */
	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

	/** The Constant FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int FUERA_HORARIO_CCO = 18888801;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	@Autowired
	private SesionCliente sesionCliente;

	@Autowired
	private FondosCotizacionesSingleton cotizacionesSingleton;

	/**
	 * Consultar iatx para Fondo.
	 *
	 * @param entity the entity
	 * @return entidad con la informacion de retorno de iatx de Fondo
	 * @throws DAOException the DAO exception
	 */
	@Override
	public SuscripcionFondoOutEntity suscribir(SuscripcionFondoInEntity entity) throws DAOException {
		String servicio = "CNSSUSFCI_";
		String version = "130";

		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		SuscripcionFondoOutEntity suscripcionFondoOutEntity = new SuscripcionFondoOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSSUSFCI(entity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				suscripcionFondoOutEntity = processTrama(iatxResponse.getTrama(), SuscripcionFondoOutEntity.class);
			} else {
				manejarErroresIatx(errorCode, iatxResponse.getErrorSystem());
			}
		} catch (IatxException e) {
			if (e.getMessage().equals(TIMEOUT_EXCEPTION)) {
				LOGGER.error(e.getMessage(), e);
				throw new TimeOutException(e.getMessage());
			} else {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
		}
		return suscripcionFondoOutEntity;
	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio iatx.
	 * 
	 * @param entity the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSSUSFCI(SuscripcionFondoInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getTerminalSafe());
		iatxRequestData.addBodyValue(entity.getCodigoFondo());
		iatxRequestData.addBodyValue(entity.getCodigoCliente());
		iatxRequestData.addBodyValue(entity.getCodigoAgente());
		iatxRequestData.addBodyValue(entity.getCodigoCanal());
		iatxRequestData.addBodyValue(entity.getImporteBruto());
		iatxRequestData.addBodyValue(entity.getPorcentajeComision());
		iatxRequestData.addBodyValue(entity.getFormaDePago());
		iatxRequestData.addBodyValue(entity.getNombreBanco());
		iatxRequestData.addBodyValue(entity.getNumeroCheque());
		iatxRequestData.addBodyValue(entity.getTipoCuentaDebito());
		iatxRequestData.addBodyValue(entity.getSucursalCuentaDebito());
		iatxRequestData.addBodyValue(entity.getNumeroCuentaDebito());
		iatxRequestData.addBodyValue(entity.getImpreSolicitud());
		iatxRequestData.addBodyValue(entity.getCotizacionCambio());
		iatxRequestData.addBodyValue(entity.getFechaRescateFuturo());
		iatxRequestData.addBodyValue(entity.getCodigoCustodiaActual());
		iatxRequestData.addBodyValue(entity.getDiaClearingCheques());
		iatxRequestData.addBodyValue(entity.getMoneda());
		return iatxRequestData;
	}

	/**
	 * Consultar cotizaciones de fondos. servicio CNSCOTIFCI version 120
	 *
	 * @return entidad con la informacion de retorno de iatx de Fondo
	 * @throws DAOException the DAO exception
	 */
	@Cacheable(cacheNames = {CacheConstants.Names.CACHE_FONDOS_COTIZACIONES})
	@Override
	public FondoOutEntity consultarCotizaciones() throws DAOException {
		Cliente cliente = sesionCliente.getCliente();
		String servicio = "CNSCOTIFCI";
		String version = "120";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = new IatxRequestData(cliente);
			iatxRequest.setData(iatxRequestData);
			IatxResponse rta = iatxComm.exec(iatxRequest);
			int errorCode = rta.getErrorCode();
			if ( errorCode == OK_CODIGO_RETORNO ) {
				FondoOutEntity outEntity = processTrama(rta.getTrama(), FondoOutEntity.class);
				cotizacionesSingleton.setOutEntity(outEntity);
				cotizacionesSingleton.setCleanCache(false);
				return outEntity;
			} else if(errorCode == FUERA_HORARIO_CCO){
				cotizacionesSingleton.setCleanCache(true);
				if(cotizacionesSingleton.getOutEntity() != null) {
					LOGGER.debug("Codigo de error de IATX: " + errorCode + ", retornando ultimo valor de cotizaciones conocido");
					return cotizacionesSingleton.getOutEntity();
				} else {
					LOGGER.debug("Codigo de error de IATX: " + errorCode);
					throw new DAOException();
				}
			} else {
				LOGGER.debug("Codigo de error de IATX: " + errorCode);
				cotizacionesSingleton.setCleanCache(true);
				throw new DAOException();
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			cotizacionesSingleton.setCleanCache(true);
			throw new DAOException(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			cotizacionesSingleton.setCleanCache(true);
			throw new DAOException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Cacheable(cacheNames = CacheConstants.Names.CACHE_NUP_FONDOS_CNSTENVAL, key = "#cliente.nup")
	@Override
	public ConsultaTenenciaFCIOutEntity consultaTenenciaFCI(Cliente cliente, ConsultaTenenciaFCIInEntity entity)
			throws DAOException {
		String servicio = "CNSTENVAL_";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaTenenciaFCIOutEntity consultaTenenciaFCIOutEntity = new ConsultaTenenciaFCIOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSTENVAL(cliente, entity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse rta = iatxComm.exec(iatxRequest);
			int errorCode = rta.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaTenenciaFCIOutEntity = processTrama(rta.getTrama(), ConsultaTenenciaFCIOutEntity.class);
			} else {
				throw new DAOException(rta.getErrorMessage(), String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return consultaTenenciaFCIOutEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO#
	 * consultaTenenciaFDC(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.
	 * ConsultaTenenciaFDCInEntity)
	 */
	@Override
	public ConsultaTenenciaFCIOutEntity consultaTenenciaFDC(Cliente cliente, ConsultaTenenciaFDCInEntity entity)
			throws DAOException {
		String servicio = "CNSTENVAL_";
		String version = "120";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ConsultaTenenciaFCIOutEntity consultaTenenciaFCIOutEntity = new ConsultaTenenciaFCIOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSTENVALv120(cliente, entity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse rta = iatxComm.exec(iatxRequest);
			int errorCode = rta.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				consultaTenenciaFCIOutEntity = processTrama(rta.getTrama(), ConsultaTenenciaFCIOutEntity.class);
			} else {
				throw new DAOException(rta.getErrorMessage(), String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return consultaTenenciaFCIOutEntity;
	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio CNSTENVAL.
	 * 
	 * @param cliente the cliente
	 * @param entity  the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSTENVAL(Cliente cliente, ConsultaTenenciaFCIInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getTipoPapelEnum().toString());
		iatxRequestData.addBodyValue(entity.getEspecieCodigo());
		iatxRequestData.addBodyValue(entity.getFechaPeriodo());
		iatxRequestData.addBodyValue(entity.getHoraPeriodo());
		completarDatosColeccion(entity.getSucursalCuentaList(), iatxRequestData);
		return iatxRequestData;
	}

	/**
	 * Generate request data CNSTENVA lv 120.
	 *
	 * @param cliente the cliente
	 * @param entity  the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSTENVALv120(Cliente cliente, ConsultaTenenciaFDCInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getTipoPapelEnum().toString());
		iatxRequestData.addBodyValue(entity.getEspecieCodigo());
		iatxRequestData.addBodyValue(entity.getFechaPeriodo());
		iatxRequestData.addBodyValue(entity.getHoraPeriodo());
		iatxRequestData.addBodyValue(entity.getClienteExciti());
		completarDatosColeccion(entity.getSucursalCuentaList(), iatxRequestData);
		return iatxRequestData;
	}

	/**
	 * Recorre la coleccion para obtener sucursal y nro. cuenta
	 * 
	 * @param sucursalCuentaList the sucursal cuenta list
	 * @param iatxRequestData    the iatx request data
	 * @return the iatx request data
	 */
	private IatxRequestData completarDatosColeccion(List<SucursalCuentaEntity> sucursalCuentaList,
			IatxRequestData iatxRequestData) {
		iatxRequestData.addBodyValue(StringUtils.leftPad(String.valueOf(sucursalCuentaList.size()), 3, '0'));
		for (SucursalCuentaEntity sucursaCuenta : sucursalCuentaList) {
			iatxRequestData.addBodyValue(StringUtils.right(sucursaCuenta.getSucursal(), LARGO_SUCURSAL));
			iatxRequestData.addBodyValue(StringUtils.right(sucursaCuenta.getNroCuenta(), LARGO_NRO_CUENTA));
		}
		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO#
	 * consultarMovimientos(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.
	 * MovimientosFondoInEntity)
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO#
	 * consultarMovimientos(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.
	 * MovimientosFondoInEntity)
	 */
	@Override
	public MovimientosFondoOutEntity consultarMovimientos(Cliente cliente, MovimientosFondoInEntity fondoAConsultar)
			throws DAOException {
		String servicio = "CNSMOVIFCI";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		MovimientosFondoOutEntity movimientosFondoOutEntity = new MovimientosFondoOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSMOVIFCI(cliente, fondoAConsultar);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				movimientosFondoOutEntity = processTrama(iatxResponse.getTrama(), MovimientosFondoOutEntity.class);
			} else {
				LOGGER.error(iatxResponse.getErrorMessage());
				manejarErroresIatx(errorCode, iatxResponse.getErrorSystem());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return movimientosFondoOutEntity;
	}

	/**
	 * Generate request data CNSMOVIFCI.
	 *
	 * @param cliente         the cliente
	 * @param fondoAConsultar the fondo A consultar
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSMOVIFCI(Cliente cliente, MovimientosFondoInEntity fondoAConsultar) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(fondoAConsultar.getCodigoFondo());
		requestData.addBodyValue(fondoAConsultar.getCodigoCliente());
		requestData.addBodyValue(fondoAConsultar.getCodigoAgente());
		requestData.addBodyValue(fondoAConsultar.getCodigoCanal());
		requestData.addBodyValue(fondoAConsultar.getCodigoOperador());
		requestData.addBodyValue(fondoAConsultar.getFechaDesde());
		requestData.addBodyValue(fondoAConsultar.getFechaHasta());
		return requestData;
	}

	/**
	 * Logica errores comprobante suscripcion fondo.
	 *
	 * @param iatxResponse the iatx response
	 * @param errorCode    the error code
	 * @throws DAOException the DAO exception
	 */
	public void logicaErroresComprobanteSuscripcionFondo(IatxResponse iatxResponse, int errorCode) throws DAOException {
		if (FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO == errorCode
				&& ID_SISTEMA.equalsIgnoreCase(iatxResponse.getErrorSystem())) {
			throw new FueraDeHorarioException();
		}
		if ((CODIGO_ERROR_SALDO_INSUFICIENTE == errorCode || CODIGO_ERROR_SALDO_INSUFICIENTE_USD == errorCode)
				&& ID_SISTEMA_SALDO_INSUFICIENTE.equalsIgnoreCase(iatxResponse.getErrorSystem())) {
			throw new SaldoInsuficienteException();
		}
		if (CODIGO_ERROR_CUENTA_SIN_OPERAR == errorCode) {
			throw new CuentaSinOperarException();
		}

		if (FUERA_HORARIO_CCO == errorCode && ID_SISTEMA_CCO.equalsIgnoreCase(iatxResponse.getErrorSystem())) {
			throw new FueraDeHorarioException();
		}
		LOGGER.debug("Codigo de error no esperado de iatx en servicio SUSFCI____. ");
		throw new DAOException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SuscripcionFondoOutEntity comprobanteSuscripcionFondo(Cliente cliente,
			ComprobanteSuscripcionFondoInEntity entity) throws DAOException {
		String servicio = "SUSFCI____";
		String version = "150";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		SuscripcionFondoOutEntity suscripcionFondoOutEntity = new SuscripcionFondoOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataComporbanteSUSFCI(cliente, entity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				suscripcionFondoOutEntity = processTrama(iatxResponse.getTrama(), SuscripcionFondoOutEntity.class);
			} else {
				logicaErroresComprobanteSuscripcionFondo(iatxResponse, errorCode);
			}

		} catch (IatxException e) {
			if (e.getMessage().equals(TIMEOUT_EXCEPTION)) {
				LOGGER.error(e.getMessage(), e);
				throw new TimeOutException(e.getMessage());
			} else {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
		}
		return suscripcionFondoOutEntity;
	}

	/**
	 * Generate request data comporbante SUSFCI.
	 *
	 * @param cliente           the cliente
	 * @param comprobanteEntity the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataComporbanteSUSFCI(Cliente cliente,
			ComprobanteSuscripcionFondoInEntity comprobanteEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(comprobanteEntity.getTerminalSafe());
		iatxRequestData.addBodyValue(comprobanteEntity.getCodigoFondo());
		iatxRequestData.addBodyValue(comprobanteEntity.getCodigoCliente());
		iatxRequestData.addBodyValue(comprobanteEntity.getCodigoAgente());
		iatxRequestData.addBodyValue(comprobanteEntity.getCodigoCanal());
		iatxRequestData.addBodyValue(comprobanteEntity.getImporteBruto());
		iatxRequestData.addBodyValue(comprobanteEntity.getPorcentajeComision());
		iatxRequestData.addBodyValue(comprobanteEntity.getFormaDePago());
		iatxRequestData.addBodyValue(comprobanteEntity.getNombreBanco());
		iatxRequestData.addBodyValue(comprobanteEntity.getNumeroCheque());
		iatxRequestData.addBodyValue(comprobanteEntity.getTipoCuentaDebito());
		iatxRequestData.addBodyValue(comprobanteEntity.getSucursalCuentaDebito());
		iatxRequestData.addBodyValue(comprobanteEntity.getNumeroCuentaDebito());
		iatxRequestData.addBodyValue(comprobanteEntity.getImpreSolicitud());
		iatxRequestData.addBodyValue(comprobanteEntity.getCotizacionCambio());
		iatxRequestData.addBodyValue(comprobanteEntity.getFechaRescateFuturo());
		iatxRequestData.addBodyValue(comprobanteEntity.getCodigoCustodiaActual());
		iatxRequestData.addBodyValue(comprobanteEntity.getDiaClearingCheques());
		iatxRequestData.addBodyValue(comprobanteEntity.getMoneda());
		iatxRequestData.addBodyValue(comprobanteEntity.getNroCertificReversar());
		iatxRequestData.addBodyValue(comprobanteEntity.getMontoReversar());
		return iatxRequestData;
	}

	/**
	 * Maneja los errores del servicio "CNSSUSFCI_" "130".
	 *
	 * @param errorCode   the error code
	 * @param errorSystem the error system
	 * @throws DAOException the DAO exception
	 */
	private void manejarErroresIatx(int errorCode, String errorSystem) throws DAOException {
		if (ID_SISTEMA.equalsIgnoreCase(errorSystem)) {
			if (FUERA_HORARIO_CODIGO_RETORNO == errorCode || FUERA_HORARIO_CODIGO_RETORNO2 == errorCode
					|| FUERA_HORARIO_CODIGO_RETORNO3 == errorCode) {
				throw new FueraDeHorarioException();
			}
			if (CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO == errorCode) {
				throw new ImporteMenorAlMinimoException();
			}
			if (CODIGO_ERROR_IMPORTE_MAYOR_AL_MAXIMO == errorCode) {
				throw new ImporteMayorAlMaximoException();
			}
			if (CODIGO_ERROR_SIN_MOVIMIENTOS == errorCode) {
				throw new FondoSinMovimientosException();
			}
			if (SERVICIO_FONDOS_DESHABILITADO == errorCode) {
				throw new ServicioDeshabilitadoException();
			}
		} else if (ID_SISTEMA_CCO.equalsIgnoreCase(errorSystem)) {
			if (SERVICIO_FONDOS_DESHABILITADO == errorCode) {
				throw new ServicioDeshabilitadoException();
			}
			if (FUERA_HORARIO_CCO == errorCode) {
				throw new FueraDeHorarioException();
			}
		}
		LOGGER.debug("Codigo de error no esperado de iatx en servicio CNSSUSFCI. ");
		throw new DAOException();
	}
}
