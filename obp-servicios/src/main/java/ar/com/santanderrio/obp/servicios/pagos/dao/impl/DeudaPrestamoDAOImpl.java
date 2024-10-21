/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditInEntity;

/**
 * The Class DeudaPrestamoDAOImpl.
 */
@Component
public class DeudaPrestamoDAOImpl implements DeudaPrestamoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DeudaPrestamoDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The servicio cnspmocuo. */
	private String servicioCnspmocuo = "CNSPMOCUO_";

	/** The version cnspmocuo. */
	private String versionCnspmocuo = "140";

	/** The Constant CODIGO_DE_ERROR_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_ERROR_DESCONOCIDO_MSG = "Codigo de error iatx desconocido";

	/** The Constant CODIGO_DE_ERROR_INICIO_STRING. */
	private static final String CODIGO_DE_ERROR_INICIO_STRING = "[codigo_error=";

	/** The Constant CODIGO_DE_ERROR_FIN_STRING. */
	private static final String CODIGO_DE_ERROR_FIN_STRING = "]";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant SIN_FECHA_1. */
	private static final String SIN_FECHA_1 = "********";

	/** The Constant ERROR_AL_FORMATEAR_FECHA. */
	private static final String ERROR_AL_FORMATEAR_FECHA = "Error al formatear fecha";

	/** The Constant ENTERO_DOCE. */
	private static final int ENTERO_DOCE = 12;

	/** The date formatter. */
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPrestamoDAO#
	 * consultarDeudaDePrestamo(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
	 */
	@Override
	public Prestamo consultarDeudaDePrestamo(Cliente cliente, Cuenta cuenta) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(servicioCnspmocuo, versionCnspmocuo);
		Prestamo prestamo = null;
		try {
			IatxRequestData iatxRequestData = generarIatxRequestDataDeudaPrestamo(cliente, cuenta);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				prestamo = parsearResponseDeudaPrestamo(iatxResponse);
			} else {
				throw new DAOException(
						generarCodigoErrorDesconocidoMensaje(errorCode) + " " + iatxResponse.getErrorMessage(), String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return prestamo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPrestamoDAO#
	 * consultarDeudaDePrestamo(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, java.lang.String)
	 */
	@Override
	public Prestamo consultarDeudaDePrestamo(Cliente cliente, String numeroPrestamo) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(servicioCnspmocuo, versionCnspmocuo);
		Prestamo prestamo = null;
		try {
			IatxRequestData iatxRequestData = generarIatxRequestDataDeudaPrestamo(cliente, numeroPrestamo);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				prestamo = parsearResponseDeudaPrestamo(iatxResponse);
			} else {
				throw new DAOException(
						generarCodigoErrorDesconocidoMensaje(errorCode) + " " + iatxResponse.getErrorMessage() ,"");
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return prestamo;
	}

	/**
	 * Parsear response deuda prestamo.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the prestamo
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Prestamo parsearResponseDeudaPrestamo(IatxResponse iatxResponse) throws DAOException {

		Prestamo prestamo = new Prestamo();

		prestamo.setCuentaRelacionada(iatxResponse.getNextData());
		prestamo.setVencimientoCuota(formatearFecha(iatxResponse.getNextData()));
		prestamo.setNumeroRecibo(iatxResponse.getNextData());
		prestamo.setImporteAmortizacion(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImporteIntereses(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setComisionesPendientes(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setGastosPendientes(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImporteTotalSeguros(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImpuestosPendientes(iatxResponse.getNextData());
		iatxResponse.getNextData();
		prestamo.setImportesPunitorios(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setInteresCompensatorioPendiente(
				ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setTasaPrestamo(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 3, 6, false));
		prestamo.setImporteSeguroVida(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImporteSeguroDelBien(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImporteIVA(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImporteIVAAdicional(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImporteEndeudamiento(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setIngresosBrutos(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setOtrosImpuestos(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImporteTotalCuota(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		String divisa = iatxResponse.getNextData();
		if (!"".equalsIgnoreCase(divisa)) {
			prestamo.setDivisa(DivisaEnum.PESO);
		} else {
			prestamo.setDivisa(DivisaEnum.fromCodigoString(divisa));
		}
		prestamo.setSaldoPrevio(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setFactorIndex(iatxResponse.getNextData());
		prestamo.setImporteAjussal(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setImporteAjuscap(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setImporteAjusteCapmor(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setFactorCapmor(iatxResponse.getNextData());
		prestamo.setIndex(iatxResponse.getNextData());
		prestamo.setRendicionSeguroVida(iatxResponse.getNextData());
		prestamo.setTasaAnualEfectiva(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 3, 6, false));
		prestamo.setCostoFinancieroTotal(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 3, 6, false));
		prestamo.setCostoFinancieroTotalSinImpuestos(
				ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 3, 6, false));
		// se agregan campos en vers 140 del serv
		prestamo.setCtaRelacionada(iatxResponse.getNextData());
		prestamo.setFecLiqRecibo(formatearFecha(iatxResponse.getNextData()));
		prestamo.setNroRecibo(iatxResponse.getNextData());
		prestamo.setNroExp(iatxResponse.getNextData());
		prestamo.setCotizCambio(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 6, 5, false));
		prestamo.setFecCotiz(formatearFecha(iatxResponse.getNextData()));
		prestamo.setCapitalPend(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setInteresesPend(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setComisionesPend(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setGastosPend(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setSegurosPend(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setImpuestosPend(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setCobranzaExt(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setIntMoraPend(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setIntCompensPend(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		prestamo.setSeguroVida(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setSeguroBien(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setIva1(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setIva2(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setImpEndeudamiento(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setIngresosBrut(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setImpSinIVA(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setImporteTotal(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setSalPrevio(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setAjusteSaldo(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setAjusteCuotaCap(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setAjusteCuotaMora(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));
		prestamo.setImporteRendir(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, true));

		return prestamo;
	}

	/**
	 * Generar iatx request data deuda prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the iatx request data
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxRequestData generarIatxRequestDataDeudaPrestamo(Cliente cliente, Cuenta cuenta) throws DAOException {

		IatxRequestData requestData = new IatxRequestData(cliente);

		requestData.addBodyValue(cuenta.getNroSucursal());
		requestData.addBodyValue(StringUtils.right(cuenta.getEstadoTarjetaCredito(), NumberUtils.INTEGER_ONE));
		requestData.addBodyValue(StringUtils.right(cuenta.getNroCuentaProducto(), ENTERO_DOCE));

		return requestData;
	}

	/**
	 * Generar iatx request data deuda prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroPrestamo
	 *            the numero prestamo
	 * @return the iatx request data
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxRequestData generarIatxRequestDataDeudaPrestamo(Cliente cliente, String numeroPrestamo)
			throws DAOException {

		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(numeroPrestamo.substring(0, 4));
		requestData.addBodyValue("0");
		requestData.addBodyValue(numeroPrestamo.substring(5, numeroPrestamo.length()).replace("/", ""));

		return requestData;
	}

	/**
	 * Generar codigo error desconocido mensaje.
	 *
	 * @param errorCode
	 *            the error code
	 * @return the string
	 */
	private String generarCodigoErrorDesconocidoMensaje(int errorCode) {
		return new StringBuilder().append(CODIGO_DE_ERROR_DESCONOCIDO_MSG).append(CODIGO_DE_ERROR_INICIO_STRING)
				.append(errorCode).append(CODIGO_DE_ERROR_FIN_STRING).toString();
	}

	/**
	 * Formatear fecha.
	 *
	 * @param vencimientoString
	 *            the vencimiento string
	 * @return the date
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Date formatearFecha(String vencimientoString) throws DAOException {
		Date fecha = new Date();
		if (vencimientoString == null) {
			return null;
		}
		StringBuilder vencimiento = new StringBuilder(vencimientoString.replace("-", ""));

		if (vencimiento.length() == NumberUtils.INTEGER_ZERO || SIN_FECHA_1.equals(vencimientoString)) {
			fecha = null;
		} else {
			try {
				// workaround por si viene la trama en 0
				if (Integer.parseInt(vencimiento.toString()) > 0) {
					fecha = dateFormatter.parse(vencimiento.toString());
				}
			} catch (ParseException e) {
				arrojarExcepcionFormatoFecha(e);
			} catch (NumberFormatException e) {
				arrojarExcepcionFormatoFecha(e);
			}
		}
		return fecha;
	}

	/**
	 * Arrojar excepcion formato fecha.
	 *
	 * @param e
	 *            the e
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void arrojarExcepcionFormatoFecha(Exception e) throws DAOException {
		LOGGER.error(ERROR_AL_FORMATEAR_FECHA, e);
		throw new DAOException(e, ERROR_AL_FORMATEAR_FECHA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPrestamoDAO#
	 * consultarDeudaDePrestamoOpenCredit(ar.com.santanderrio.obp.servicios.
	 * prestamos.entity.ConsultaPrestamoOpenCreditInEntity)
	 */
	@Override
	public Prestamo consultarDeudaDePrestamoOpenCredit(
			ConsultaPrestamoOpenCreditInEntity consultaPrestamoOpenCreditInEntity) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(servicioCnspmocuo, versionCnspmocuo);
		Prestamo prestamo = null;
		try {
			IatxRequestData iatxRequestData = generarIatxRequestDataPrestamoOpenCredit(
					consultaPrestamoOpenCreditInEntity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				prestamo = parsearResponseDeudaPrestamo(iatxResponse);
			} else {
				throw new DAOException(
						generarCodigoErrorDesconocidoMensaje(errorCode) + " " + iatxResponse.getErrorMessage());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return prestamo;
	}

	/**
	 * Generar iatx request data deuda prestamo open credit OLYMPUS.
	 *
	 * @param consultaPrestamoOpenCreditInEntity
	 *            the consulta prestamo open credit in entity
	 * @return the iatx request data
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxRequestData generarIatxRequestDataPrestamoOpenCredit(
			ConsultaPrestamoOpenCreditInEntity consultaPrestamoOpenCreditInEntity) throws DAOException {
		IatxRequestData requestData = new IatxRequestData(consultaPrestamoOpenCreditInEntity.getCliente());
		requestData.addBodyValue(consultaPrestamoOpenCreditInEntity.getSucursal());
		requestData.addBodyValue(consultaPrestamoOpenCreditInEntity.getTipoPrestamo());
		requestData
				.addBodyValue(StringUtils.right(consultaPrestamoOpenCreditInEntity.getNumeroPrestamo(), ENTERO_DOCE));
		return requestData;
	}
}
