/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class PagoPrestamoDAOImpl.
 */
@Component
public class PagoPrestamoDAOImpl implements PagoPrestamoDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoPrestamoDAOImpl.class);

	/** The Constant CANTIDAD_MAX_DECIMALES_IMPORTES. */
	private static final int CANTIDAD_MAX_DECIMALES_IMPORTES = 4;

	/** The Constant CANTIDAD_MAX_DECIMALES_TASAS. */
	private static final int CANTIDAD_MAX_DECIMALES_TASAS = 6;

	/** The Constant CANTIDAD_MAX_DECIMALES_COTIZACION. */
	private static final int CANTIDAD_MAX_DECIMALES_COTIZACION = 5;

	/** The Constant COD_ERROR_SALDOS_INSUFICIENTES_2. */
	private static final int COD_ERROR_SALDOS_INSUFICIENTES_2 = 10000515;

	/** The Constant COD_ERROR_SALDOS_INSUFICIENTES_1. */
	private static final int COD_ERROR_SALDOS_INSUFICIENTES_1 = 10002122;

	/** The Constant COD_ERROR_SALDOS_INSUFICIENTES_1. */
	//TODO Codigo de error random, habria que cambiarlo por el correcto
	private static final int COD_ERROR_IMPORTE_CUOTA_HIPOTECARIO_UVA = 10001922;
	
	/** The Constant CODIGO_ERROR_PAGO_CON_ANTERIORIDAD. */
	private static final int CODIGO_ERROR_PAGO_CON_ANTERIORIDAD = 10000100;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant SERVICIO_PAGPMOCUO. */
	private static final String SERVICIO_PAGPMOCUO = "PAGPMOCUO_";

	/** The Constant VERSION_PAGPMOCUO. */
	private static final String VERSION_PAGPMOCUO = "150";

	/** The Constant ENTERO_DOCE. */
	private static final int ENTERO_DOCE = 12;

	/** The Constant ENTERO_SIETE. */
	private static final int ENTERO_SIETE = 7;

	/** The Constant ENTERO_CATORCE. */
	private static final int ENTERO_CATORCE = 14;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.dao.PagoPrestamoDAO#pagar(ar.com.
	 * santanderrio.obp.servicios.pagos.entities.Prestamo,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
	 */
	@Override
	public ComprobantePrestamo pagar(Prestamo prestamo, Cuenta cuenta)
			throws PagoConAnterioridadException, NoTieneFondosSuficientesException, DAOException, ImporteCuotaHipotecarioUVaException {
		IatxResponse iatxResponse = new IatxResponse();
		try {
			IatxRequest request = buildIatxRequest(prestamo, cuenta);
			iatxResponse = iatxComm.exec(request);
			return buildResponse(iatxResponse);
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			throw new DAOException(e, iatxResponse.getErrorMessage());
		} catch (ImporteConvertException e) {
			throw new DAOException(e, iatxResponse.getErrorMessage());
		} catch (ParseException e) {
			throw new DAOException(e, iatxResponse.getErrorMessage());
		}

	}

	/**
	 * Builds the response.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the comprobante prestamo
	 * @throws PagoConAnterioridadException
	 *             the pago con anterioridad exception
	 * @throws NoTieneFondosSuficientesException
	 *             the no tiene fondos suficientes exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ParseException
	 *             the parse exception
	 * @throws ImporteCuotaHipotecarioUVaException 
	 */
	private ComprobantePrestamo buildResponse(IatxResponse iatxResponse) throws PagoConAnterioridadException,
			NoTieneFondosSuficientesException, ImporteConvertException, DAOException, ParseException, ImporteCuotaHipotecarioUVaException {
		ComprobantePrestamo resultado = new ComprobantePrestamo();
		if (iatxResponse.getErrorCode() == 0) {
			buildComprobantePrestamo(iatxResponse, resultado);
		} else if (iatxResponse.getErrorCode() == CODIGO_ERROR_PAGO_CON_ANTERIORIDAD) {
			throw new PagoConAnterioridadException();
		} else if (iatxResponse.getErrorCode() == COD_ERROR_SALDOS_INSUFICIENTES_1
				|| iatxResponse.getErrorCode() == COD_ERROR_SALDOS_INSUFICIENTES_2) {
			throw new NoTieneFondosSuficientesException();
		} else if (iatxResponse.getErrorCode() == COD_ERROR_IMPORTE_CUOTA_HIPOTECARIO_UVA) {
			throw new ImporteCuotaHipotecarioUVaException();
		} else if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			throw new DAOException();
		}
		return resultado;
	}

	/**
	 * Builds the comprobante prestamo.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @param resultado
	 *            the resultado
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @throws ParseException
	 *             the parse exception
	 */
	private void buildComprobantePrestamo(IatxResponse iatxResponse, ComprobantePrestamo resultado)
			throws ImporteConvertException, ParseException {
		resultado.setCodigoRetorno(String.valueOf(iatxResponse.getErrorCode()));
		resultado.setImporteDebito(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setCotizacionPrestamo(ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(),
				CANTIDAD_MAX_DECIMALES_COTIZACION));
		resultado.setNumeroRecibo(iatxResponse.getNextData());
		resultado.setVencimientoRecibo(iatxResponse.getNextData());
		resultado.setImporteTotalRecibo(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setCodigoMoneda(iatxResponse.getNextData());
		resultado.setTasaPrestamo(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_TASAS));
		resultado.setImporteAmortizacion(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImporteIntereses(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setOtrosImpuestos(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImporteSeguroVida(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImportePunitorios(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImporteComplementario(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImporteIVA(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImporteIVAAdicional(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImporteSeguroBien(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setGastos(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setComisiones(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImporteTotalSeguros(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setSaldoCapital(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImpuestoEndFinal(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setIngresosBrutos(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		String nio = iatxResponse.getNextData();
		if (StringUtils.isBlank(nio)) {
			nio = iatxResponse.getTrama().substring(161, 170);
		} else {
			nio = nio.substring(14, 22);
		}
		resultado.setNio(nio);
		resultado.setCodIVA(iatxResponse.getNextData());
		resultado.setDescripcionIVA(iatxResponse.getNextData());
		resultado.setLeyenda1(iatxResponse.getNextData());
		resultado.setLeyenda2(iatxResponse.getNextData());
		resultado.setTea(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_TASAS));
		resultado.setSaldoPrev(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImpAjussal(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setFactorIndex(iatxResponse.getNextData());
		resultado.setImpAjusCapmor(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImpAjusCap(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setFactorCapmor(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_TASAS));
		resultado.setCoeificiIndex(iatxResponse.getNextData());
		resultado.setRendicionCiaSegVida(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setCftna(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_TASAS));
		resultado.setImporteTotalImpuestos(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setImportaCobranzaExterna(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_IMPORTES));
		resultado.setCftnaSinImp(
				ISBANStringUtils.convertirStrToBigDecimal(iatxResponse.getNextData(), CANTIDAD_MAX_DECIMALES_TASAS));
		resultado.setCcc(iatxResponse.getNextData());
		String aux = iatxResponse.getNextData();
		resultado.setFechaLiquidacion(parsearFechaConAnio(aux));
		resultado.setNumRecibo(iatxResponse.getNextData());
		resultado.setUnidadExposicion(iatxResponse.getNextData());
		resultado.setCotCambioUniExpo(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 6, 5, false));
		resultado.setFecCotizacion(parsearFechaConAnio(iatxResponse.getNextData()));
		resultado.setImpCapital(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpIntereses(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpComision(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpGastos(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpSeguros(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpImpuestos(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpCobex(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpMora(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpCompensatori(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setSeguroVida(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setSeguroBien(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setIva1(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setIva2(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImpuestoEndeudamiento(
				ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setIngrBrutos(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImporteSinIVA(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImporteTotal(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setSaldoPrevio(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setAjusteSaldo(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setAjusteCapital(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setAjusteCapitalMora(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setImporteRendirCIA(ISBANStringUtils.stringToBigDecimal(iatxResponse.getNextData(), 13, 4, false));
		resultado.setOtrosSeguros(calcularOtrosSeguros(resultado));
	}

	/**
	 * Parsear fecha con anio.
	 *
	 * @param date
	 *            the date
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private Date parsearFechaConAnio(String date) throws ParseException {
		Date fecha = null;
		if (StringUtils.isNotEmpty(date) && !ISBANStringUtils.FECHA_NO_DISPONIBLE.equals(date)) {
			SimpleDateFormat fFecha = new SimpleDateFormat("yyyy-MM-dd");
			fecha = fFecha.parse(date);
		}
		return fecha;
	}

	/**
	 * Builds the iatx request.
	 *
	 * @param prestamo
	 *            the prestamo
	 * @param cuenta
	 *            the cuenta
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequest(Prestamo prestamo, Cuenta cuenta) {
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_PAGPMOCUO, VERSION_PAGPMOCUO);
		Cuenta cuentaPrestamo = prestamo.getCuenta();
		Cliente cliente = cuenta.getCliente();
		IatxRequestData requestData = new IatxRequestData(cliente);
		// Sucursal_Cuenta
		requestData.addBodyValue(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()));
		// Tipo Cuenta
		requestData.addBodyValue(cuenta.getTipoCuentaSinUnificar());
		// Nro Cuenta
		// 5102490853
		requestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(cuenta.getNroCuentaProducto(), ENTERO_SIETE));
		// Importe del Pago en la moneda del prestamo
		requestData.addBodyValue(
				ISBANStringUtils.formateadorSaldoPrestamo(prestamo.getImporteTotalCuota(), ENTERO_CATORCE));
		// sucursal del prestamo
		requestData.addBodyValue(cuentaPrestamo.getNroSucursal());
		// Tipo de Prestamo
		requestData.addBodyValue(cuentaPrestamo.getEstadoTarjetaCredito());
		// Numero de prestamo
		requestData.addBodyValue(
				ISBANStringUtils.formateadorConCerosIzq(cuentaPrestamo.getNroCuentaProducto(), ENTERO_DOCE));
		// Cotizacion Prestamo
		requestData.addBodyValue("00000000000");
		// Limite Seguro
		requestData.addBodyValue("00000000000000");
		// Nio
		requestData.addBodyValue("000000000000000000000000");
		iatxRequest.setData(requestData);
		return iatxRequest;
	}

	/**
	 * Calcular otros seguros.
	 *
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @return the big decimal
	 */
	private BigDecimal calcularOtrosSeguros(ComprobantePrestamo comprobantePrestamo) {

		BigDecimal otrosSeguros = new BigDecimal("0.0000");
		if (!isZero(comprobantePrestamo.getImporteTotalSeguros())) {
			BigDecimal resultado = comprobantePrestamo.getImporteTotalSeguros()
					.subtract(comprobantePrestamo.getImporteSeguroBien())
					.subtract(comprobantePrestamo.getImporteSeguroVida());
			if (isValorMinimoSeguros(resultado)) {
				otrosSeguros = resultado;

			}
		}
		return otrosSeguros;
	}

	/**
	 * Checks if is zero.
	 *
	 * @param value
	 *            the value
	 * @return true, if is zero
	 */
	private boolean isZero(BigDecimal value) {

		if (value != null) {
			int isZero = BigDecimal.ZERO.compareTo(value);
			return isZero == 0;
		}
		return true;

	}

	/**
	 * Checks if is valor minimo seguros.
	 *
	 * @param value
	 *            the value
	 * @return true, if is valor minimo seguros
	 */
	private boolean isValorMinimoSeguros(BigDecimal value) {
		BigDecimal valorMinimo = new BigDecimal("0.009");
		Boolean isMenosValorMinimo = true;
		if (value != null) {
			if (valorMinimo.compareTo(value) >= 0) {
				isMenosValorMinimo = false;
			}
		}
		return isMenosValorMinimo;
	}

}
