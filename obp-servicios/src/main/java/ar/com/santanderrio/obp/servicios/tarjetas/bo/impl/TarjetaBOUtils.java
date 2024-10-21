/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.FechaTarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class TarjetaBOUtils.
 */
public final class TarjetaBOUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaBOUtils.class);

	/** The Constant IMPORTE_IMVALIDO. */
	private static final String IMPORTE_IMVALIDO = "Importe invalido: ";

	/** The Constant CERO. */
	public static final String CERO = "0";

	/** The Constant ESPACIO_BLANCO. */
	public static final String ESPACIO_BLANCO = " ";

	/** The Constant CERO_FLOTANTE. */
	public static final String CERO_FLOTANTE = "0,00";

	/** The Constant CERO. */
	public static final Float CERO_FLOAT = (float) 0;

	/**
	 * Instantiates a new tarjeta BO utils.
	 */
	private TarjetaBOUtils() {
		super();
	}

	/**
	 * Cantidad tarjetas titulares.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the integer
	 */
	public static Integer cantidadTarjetasTitulares(List<Cuenta> cuentas) {
		int c1 = 0;
		for (int i = 0; i < cuentas.size(); i++) {
			if (esTitularCuenta(cuentas.get(i))) {
				c1 = c1 + 1;
			}
		}
		return c1;
	}

	/**
	 * Obtener fecha por cierre.
	 *
	 * @param fechas
	 *            the fechas
	 * @return the string
	 */
	public static String obtenerFechaPorCierre(List<FechaTarjetaEntity> fechas) {
		String r1 = null;
		for (int i = 0; i < fechas.size(); i++) {
			FechaTarjetaEntity t1 = fechas.get(i);
			if (t1 != null && TarjetaUtils.FECHA_CIERRE.equals(t1.getDescripcion().trim())) {
				r1 = t1.getCierre();
			}
		}
		return r1;
	}

	/**
	 * Obtener fecha por vencimiento.
	 *
	 * @param fechas
	 *            the fechas
	 * @return the string
	 */
	public static String obtenerFechaPorVencimiento(List<FechaTarjetaEntity> fechas) {
		String r1 = null;
		for (int i = 0; i < fechas.size(); i++) {
			FechaTarjetaEntity t1 = fechas.get(i);
			if (t1 != null && TarjetaUtils.FECHA_VENCIMIENTO.equals(t1.getDescripcion().trim())) {
				r1 = t1.getVencimiento();
			}
		}
		return r1;
	}

	/**
	 * Convierte un string dado a camelcase.
	 *
	 * @author florencia.n.martinez
	 * @param string
	 *            the string
	 * @return String
	 */
	public static String convertirStringToCamelcase(String string) {
		return ISBANStringUtils.convertirStringToCamelcase(string);
	}

	/**
	 * Convierte un string dado a lowercase.
	 *
	 * @author florencia.n.martinez
	 * @param string
	 *            the string
	 * @return String
	 */
	public static String convertirStringToLowercase(String string) {
		return ISBANStringUtils.convertirStringToLowercase(string);
	}

	/**
	 * Filtrar visa titular.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	public static List<Cuenta> filtrarVisaTitular(List<Cuenta> cuentas) {
		return filtrarCuentasSegunTipo(TipoCuenta.VISA.getCodigo(), TarjetaUtils.CODIGO_TITULARIDAD_TITULAR, cuentas);
	}

	/**
	 * Filtrar amex titular.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	public static List<Cuenta> filtrarAmexTitular(List<Cuenta> cuentas) {
		return filtrarCuentasSegunTipo(TipoCuenta.AMEX.getCodigo(), TarjetaUtils.CODIGO_TITULARIDAD_TITULAR, cuentas);
	}

	/**
	 * Filtrar mastercard titular.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	public static List<Cuenta> filtrarMastercardTitular(List<Cuenta> cuentas) {
		return filtrarCuentasSegunTipo(TipoCuenta.MASTERCARD.getCodigo(), TarjetaUtils.CODIGO_TITULARIDAD_TITULAR,
				cuentas);
	}

	/**
	 * Filtrar visa adicional.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	public static List<Cuenta> filtrarVisaAdicional(List<Cuenta> cuentas) {
		return filtrarCuentasSegunTipo(TipoCuenta.VISA.getCodigo(), TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL, cuentas);
	}

	/**
	 * Filtrar amex adicional.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the collection<? extends cuenta>
	 */
	public static List<Cuenta> filtrarAmexAdicional(List<Cuenta> cuentas) {
		return filtrarCuentasSegunTipo(TipoCuenta.AMEX.getCodigo(), TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL, cuentas);
	}

	/**
	 * Filtrar Mastercard adicional.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the collection<? extends cuenta>
	 */
	public static List<Cuenta> filtrarMastercardAdicional(List<Cuenta> cuentas) {
		return filtrarCuentasSegunTipo(TipoCuenta.MASTERCARD.getCodigo(), TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL,
				cuentas);
	}

	/**
	 * Filtrar recargables.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the collection<? extends cuenta>
	 */
	public static Collection<? extends Cuenta> filtrarRecargables(List<Cuenta> cuentas) {
		return filtrarCuentasRecargables(TipoCuenta.VISA.getCodigo(), TipoCuenta.VISA_RECARGABLE.getCodigo(),
				TarjetaUtils.VISA_RECARGABLE, cuentas);
	}

	/**
	 * Filtrar cuentas segun tipo.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param titularidad
	 *            the titularidad
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	static List<Cuenta> filtrarCuentasSegunTipo(Integer tipoCuenta, String titularidad, List<Cuenta> cuentas) {
		List<Cuenta> preorden = new ArrayList<Cuenta>();
		for (int i = 0; i < cuentas.size(); i++) {
			Cuenta c1 = cuentas.get(i);
			if (esTipoCuentaYTitularidad(tipoCuenta, titularidad, c1)) {
				preorden.add(c1);
			}
		}
		return preorden;
	}

	/**
	 * Filtrar cuentas recargables.
	 *
	 * @param tipoCuenta1
	 *            the tipo cuenta 1
	 * @param tipoCuenta2
	 *            the tipo cuenta 2
	 * @param claseCuenta
	 *            the clase cuenta
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	static List<Cuenta> filtrarCuentasRecargables(Integer tipoCuenta1, Integer tipoCuenta2, String claseCuenta,
			List<Cuenta> cuentas) {
		List<Cuenta> preorden = new ArrayList<Cuenta>();
		for (int i = 0; i < cuentas.size(); i++) {
			Cuenta c1 = cuentas.get(i);
			if (esTipoCuentaYClaseCuenta(tipoCuenta1, tipoCuenta2, claseCuenta, c1)) {
				preorden.add(c1);
			}
		}
		return preorden;
	}

	/**
	 * Filtrar cuentas de tipo cuenta tarjeta.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static List<Cuenta> filtrarCuentasDeTipoCuentaTarjeta(List<Cuenta> cuentas) throws TarjetaBOUtilsException {
		List<Cuenta> tarjetas = new ArrayList<Cuenta>();
		for (int i = 0; i < cuentas.size(); i++) {
			Cuenta c = cuentas.get(i);
			if (esTipoCuentaTarjeta(c)) {
				tarjetas.add(c);
			}
		}
		LOGGER.info("Productos del Cliente en Sesion Filtrados por Tipo Tarjeta {}.", tarjetas.toString());
		return tarjetas;
	}
	
	
	/**
	 * Filtrar cuentas de tipo No cuenta tarjeta.
	 * @param cuentas
	 * @return
	 * @throws TarjetaBOUtilsException
	 */
	
	public static List<Cuenta> filtrarCuentasDeTipoNoCuentaTarjeta(List<Cuenta> cuentas) throws TarjetaBOUtilsException {
		List<Cuenta> tarjetas = new ArrayList<Cuenta>();
		for (int i = 0; i < cuentas.size(); i++) {
			Cuenta c = cuentas.get(i);
			if (!esTipoCuentaTarjeta(c)) {
				tarjetas.add(c);
			}
		}
		LOGGER.info("Productos del Cliente en Sesion Filtrados por Tipo No Tarjeta {}.", tarjetas.toString());
		return tarjetas;
	}

	/**
	 * Es titular cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public static Boolean esTitularCuenta(Cuenta cuenta) {

		if (TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad().trim())) {

			return true;
		}
		return false;
	}

	/**
	 * Es adicional cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public static Boolean esAdicionalCuenta(Cuenta cuenta) {
		if (esVisaRecargable(cuenta) && "001".equals(cuenta.getNroOrdenFirmante())) {
			return false;
		} else if (isStringEquals(TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL, cuenta.getCodigoTitularidad().trim())) {
			return true;
		}
		return isStringEquals(TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL, cuenta.getCodigoTitularidad().trim());
	}

	/**
	 * Es visa recargable.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	static Boolean esVisaRecargable(Cuenta cuenta) {
		return esTipoCuentaYClaseCuenta(TipoCuenta.VISA.getCodigo(), TipoCuenta.VISA_RECARGABLE.getCodigo(),
				TarjetaUtils.VISA_RECARGABLE, cuenta);
	}

	/**
	 * Es titularidad.
	 *
	 * @param definicionTitularidad
	 *            the definicion titularidad
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	static Boolean esTitularidad(String definicionTitularidad, Cuenta cuenta) {
		return isStringEquals(definicionTitularidad, cuenta.getCodigoTitularidad().trim());
	}

	/**
	 * Es clase cuenta.
	 *
	 * @param definicionClaseCuenta
	 *            the definicion clase cuenta
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	static Boolean esClaseCuenta(String definicionClaseCuenta, Cuenta cuenta) {
		return isStringEquals(definicionClaseCuenta, cuenta.getClaseCuenta().trim());
	}

	/**
	 * Es tipo cuenta tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public static Boolean esTipoCuentaTarjeta(Cuenta cuenta) {
		// PARA LOS CODIGOS DE CUENTA AUN NO CONTEMPLADOS DENTRO DE LA
		// APLICACION.
		if (cuenta.getTipoCuentaEnum() == null) {
			return Boolean.FALSE;
		}

		TipoCuenta tipo = cuenta.getTipoCuentaEnum();
		return TipoCuenta.VISA.equals(tipo) || TipoCuenta.AMEX.equals(tipo) || TipoCuenta.MASTERCARD.equals(tipo);
	}

	/**
	 * Es tipo cuenta visa.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public static Boolean esTipoCuentaVisa(Cuenta cuenta) {
		if (cuenta.getTipoCuentaEnum() == null) {
			return Boolean.FALSE;
		}
		return TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum());
	}

	/**
	 * Es tipo cuenta amex.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public static Boolean esTipoCuentaAmex(Cuenta cuenta) {
		if (cuenta.getTipoCuentaEnum() == null) {
			return Boolean.FALSE;
		}

		return TipoCuenta.AMEX.equals(cuenta.getTipoCuentaEnum());
	}

	/**
	 * Es tipo cuenta.
	 *
	 * @param definicionTipoCuenta
	 *            the definicion tipo cuenta
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	static Boolean esTipoCuenta(Integer definicionTipoCuenta, Cuenta cuenta) {
		Long tipo = Long.parseLong(cuenta.getTipoCuenta().trim());
		Long definicion = new Long(definicionTipoCuenta);
		return definicion.equals(tipo);
	}

	/**
	 * Es tipo cuenta Y titularidad.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param titularidad
	 *            the titularidad
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	static Boolean esTipoCuentaYTitularidad(Integer tipoCuenta, String titularidad, Cuenta cuenta) {
		return esTipoCuenta(tipoCuenta, cuenta) && (esTitularidad(titularidad, cuenta));
	}

	/**
	 * Es tipo cuenta Y clase cuenta.
	 *
	 * @param tipoCuenta1
	 *            the tipo cuenta 1
	 * @param tipoCuenta2
	 *            the tipo cuenta 2
	 * @param claseCuenta
	 *            the clase cuenta
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public static Boolean esTipoCuentaYClaseCuenta(Integer tipoCuenta1, Integer tipoCuenta2, String claseCuenta,
			Cuenta cuenta) {
		return (esTipoCuenta(tipoCuenta1, cuenta) || esTipoCuenta(tipoCuenta2, cuenta))
				&& (esClaseCuenta(claseCuenta, cuenta));
	}

	/**
	 * Checks if is string equals.
	 *
	 * @param s1
	 *            the s 1
	 * @param s2
	 *            the s 2
	 * @return the boolean
	 */
	public static Boolean isStringEquals(String s1, String s2) {
		return (s2.trim()).equals(s1.trim());
	}

	/**
	 * Es lista vacia.
	 *
	 * @param col
	 *            the col
	 * @return the boolean
	 */
	static Boolean esListaVacia(Collection<?> col) {
		return col.isEmpty();
	}

	/**
	 * Gets the codigo error al consultar disponibles.
	 *
	 * @return the codigo error al consultar disponibles
	 */

	public static String getCodigoErrorAlConsultarDisponiblesYConsumos() {
		return CodigoMensajeConstantes.CODIGO_ERROR_DATOS_CABECERA_TARJETA;
	}

	/**
	 * Gets the codigo error al consultar disponibles.
	 *
	 * @return the codigo error al consultar disponibles
	 */
	public static String getCodigoErrorAlConsultarDisponibles() {
		return CodigoMensajeConstantes.CODIGO_ERROR_CARGA_DISPONIBLES;
	}

	/**
	 * Gets the codigo error al consultar adicionales.
	 *
	 * @return the codigo error al consultar adicionales
	 */
	public static String getCodigoErrorAlConsultarAdicionales() {
		return CodigoMensajeConstantes.ERROR_CABECERA_TARJETA_ADICIONAL;
	}

	/**
	 * Retorna codigo de la base de datos para error de disponibles y consumos
	 * (error total).
	 *
	 * @return the codigo error total
	 */
	public static String getCodigoErrorTotal() {
		return CodigoMensajeConstantes.CODIGO_ERROR_TARJETA_ERROR_TOTAL;
	}

	/**
	 * Gets the codigo error al consultar consumos.
	 *
	 * @return the codigo error al consultar consumos
	 */
	public static String getCodigoErrorAlConsultarConsumos() {
		return CodigoMensajeConstantes.CODIGO_ERROR_CARGA_CONSUMOS;
	}

	/**
	 * Gets the codigo error al modificar favorito.
	 *
	 * @return the codigo error al modificar favorito
	 */
	public static String getCodigoErrorAlModificarFavorito() {
		return CodigoMensajeConstantes.CODIGO_ERROR_TARJETA_FAVORITO;
	}

	/**
	 * Gets the codigo error al modificar alias.
	 *
	 * @return the codigo error al modificar alias
	 */
	public static String getCodigoErrorAlModificarAlias() {
		return CodigoMensajeConstantes.CODIGO_ERROR_TARJETA_ALIAS;
	}

	/**
	 * Gets the codigo error sin consumos.
	 *
	 * @return the codigo error sin consumos
	 */
	@Deprecated
	public static String getCodigoErrorSinConsumos() {
		return CodigoMensajeConstantes.CODIGO_ERROR_SIN_CONSUMOS;
	}

	/**
	 * Gets the codigo error consumos pendientes confirmacion.
	 *
	 * @return the codigo error consumos pendientes confirmacion
	 */
	public static String getCodigoErrorConsumosPendientesConfirmacion() {
		return CodigoMensajeConstantes.CODIGO_ERROR_CONSUMOS_PENDIENTES_CONFIRMACION;
	}

	/**
	 * Gets the codigo tooltip favorito.
	 *
	 * @return the codigo tooltip favorito
	 */
	public static String getCodigoTooltipFavorito() {
		return CodigoMensajeConstantes.CODIGO_TOOLTIP_FAVORITO;
	}

	/**
	 * Gets the codigo tooltip no favorito.
	 *
	 * @return the codigo tooltip no favorito
	 */
	public static String getCodigoTooltipNoFavorito() {
		return CodigoMensajeConstantes.CODIGO_TOOLTIP_NO_FAVORITO;
	}

	/**
	 * Gets the codigo error generico.
	 *
	 * @return the codigo error generico
	 */
	public static String getCodigoErrorGenerico() {
		return CodigoMensajeConstantes.CODIGO_ERROR_GENERICO;
	}

	/**
	 * Gets the codigo error carga ultimo resumen.
	 *
	 * @return the codigo error carga ultimo resumen
	 */
	public static String getCodigoErrorCargaUltimoResumen() {
		return CodigoMensajeConstantes.CODIGO_ERROR_CARGA_ULTIMO_RESUMEN;
	}

	/**
	 * Gets the codigo error sin ultimo resumen.
	 *
	 * @return the codigo error sin ultimo resumen
	 */
	public static String getCodigoErrorSinUltimoResumen() {
		return CodigoMensajeConstantes.CODIGO_ERROR_SIN_ULTIMO_RESUMEN;
	}

	/**
	 * Obtiene el codigo de error de No tenes tarjetas.
	 * 
	 * @return String
	 */
	public static String getCodigoErrorSinTarjetas() {
		return CodigoMensajeConstantes.CODIGO_ERROR_SIN_TARJETAS;
	}

	/**
	 * Convierte el numero de la tarjeta a la mascara necesaria. Ej. nroTarjeta
	 * 00004509950008734695, marca VISA, resultado de la ejecucion:
	 * 4509950008734695
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param marca
	 *            the marca
	 * @return the string
	 */
	public static String cortarNumeroTarjeta(String nroTarjeta, String marca) {
		return TarjetaUtils.cortarNumeroTarjetaComoTarjetaActiva(nroTarjeta, marca);
	}

	/**
	 * Formatear numero tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @param marca
	 *            the marca
	 * @return the string
	 */
	public static String formatearNumeroTarjeta(String nroTarjeta, String marca) {
		return TarjetaUtils.cortarYFormatearNumeroTarjeta(nroTarjeta, marca);
	}

	/**
	 * Formatear saldo.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldo(BigDecimal saldo) {
		String r1 = null;
		r1 = ISBANStringUtils.formatearSaldo(saldo);
		return r1;
	}

	/**
	 * Formatear saldo sin abs.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldoSinAbs(BigDecimal saldo) {
		String r1 = null;
		r1 = ISBANStringUtils.formatearSaldoSinAbs(saldo);
		return r1;
	}

	/**
	 * Formatear fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	public static String formatearFecha(String fecha) {
		return ISBANStringUtils.formatearFechaConAnio(fecha);
	}

	/**
	 * Formatear fecha completa.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 */
	public static Date formatearFechaCompleta(String fecha) {
		return ISBANStringUtils.formatearFecha(fecha);
	}

	/**
	 * Formatear fecha dia mes.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 */
	public static Date formatearFechaDiaMes(String fecha) {
		return ISBANStringUtils.formatearFechaDiaMes(fecha);
	}

	/**
	 * Parsea fecha con anio.
	 *
	 * @param date
	 *            the date
	 * @return the Date
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static Date parsearFechaConAnio(String date) throws TarjetaBOUtilsException {
		try {
			return ISBANStringUtils.parsearFechaConAnio(date);
		} catch (ParseException pe) {
			LOGGER.error("Error al parsear la fecha {} con anio  {}.", pe);
			throw new TarjetaBOUtilsException();
		}
	}

	/**
	 * Convierte un String a Integer.
	 *
	 * @param numCadena
	 *            the num cadena
	 * @return the integer
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static Integer parsearEntero(String numCadena) throws TarjetaBOUtilsException {
		try {
			return Integer.parseInt(numCadena);
		} catch (NumberFormatException pe) {
			LOGGER.error("Error al parsear de String a Entero  {}.", pe);
			throw new TarjetaBOUtilsException();
		}
	}

	/**
	 * Convierte un String a Integer.
	 *
	 * @param numero
	 *            the numero
	 * @return the integer
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static String parsearString(Integer numero) throws TarjetaBOUtilsException {
		try {
			return Integer.toString(numero);
		} catch (NumberFormatException pe) {
			LOGGER.error("Error al parsear de Entero a String  {}.", pe);
			throw new TarjetaBOUtilsException();
		}
	}

	/**
	 * Convertir saldo.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static BigDecimal convertirSaldo(String saldo) throws TarjetaBOUtilsException {
		String s1 = importePtoFijo2Canonico(saldo, 2);
		if (s1 != null) {
			if (saldo.indexOf('-') != -1) {
				return new BigDecimal(s1);
			}
			return new BigDecimal(s1);
		}
		return null;
	}

	/**
	 * Obtener identificacion cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the identificacion cuenta
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static IdentificacionCuenta obtenerIdentificacionCuenta(String cuenta) throws TarjetaBOUtilsException {
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		String[] numeroCuenta = cuenta.split("-");
		StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
		cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);
		identificacionCuenta.setNroSucursal(numeroCuenta[0]);
		identificacionCuenta.setNroCuentaProducto(cuentaProducto.toString());
		return identificacionCuenta;
	}

	/**
	 * Obtener tarjetas visa titulares.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	static List<ResumenTarjetaDTO> obtenerTarjetasVisaTitulares(List<ResumenTarjetaDTO> tarjetas)
			throws TarjetaBOUtilsException {
		try {
			List<ResumenTarjetaDTO> tarjetasTitularesVisa = new ArrayList<ResumenTarjetaDTO>();
			for (int i = 0; i < tarjetas.size(); i++) {
				ResumenTarjetaDTO rt = tarjetas.get(i);
				if (esTarjetaTitular(rt, TarjetaUtils.MARCA_VISA)) {
					tarjetasTitularesVisa.add(rt);
				}
			}
			return tarjetasTitularesVisa;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener Tarjetas Visa Titulares {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Obtener tarjetas mastercard titulares.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	static List<ResumenTarjetaDTO> obtenerTarjetasMastercardTitulares(List<ResumenTarjetaDTO> tarjetas)
			throws TarjetaBOUtilsException {
		try {
			List<ResumenTarjetaDTO> tarjetasTitularesMastercard = new ArrayList<ResumenTarjetaDTO>();
			for (int i = 0; i < tarjetas.size(); i++) {
				ResumenTarjetaDTO rt = tarjetas.get(i);
				if (esTarjetaTitular(rt, TarjetaUtils.MARCA_MASTERCARD)) {
					tarjetasTitularesMastercard.add(rt);
				}
			}
			return tarjetasTitularesMastercard;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener Tarjetas Mastercard Titulares {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Obtener tarjetas mastercard adicionales.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	static List<ResumenTarjetaDTO> obtenerTarjetasMastercardAdicionales(List<ResumenTarjetaDTO> tarjetas)
			throws TarjetaBOUtilsException {
		try {
			List<ResumenTarjetaDTO> tarjetasAdicionalesMastercard = new ArrayList<ResumenTarjetaDTO>();
			for (int i = 0; i < tarjetas.size(); i++) {
				ResumenTarjetaDTO rt = tarjetas.get(i);
				if (esTarjetaAdicional(rt, TarjetaUtils.MARCA_MASTERCARD)) {
					tarjetasAdicionalesMastercard.add(rt);
				}
			}
			return tarjetasAdicionalesMastercard;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener Tarjetas Mastercard Adicionales {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Obtener tarjetas amex titulares.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	static List<ResumenTarjetaDTO> obtenerTarjetasAmexTitulares(List<ResumenTarjetaDTO> tarjetas)
			throws TarjetaBOUtilsException {
		try {
			List<ResumenTarjetaDTO> tarjetasTitularesAmex = new ArrayList<ResumenTarjetaDTO>();
			for (ResumenTarjetaDTO rt : tarjetas) {
				if (esTarjetaTitular(rt, TarjetaUtils.MARCA_AMEX)) {
					tarjetasTitularesAmex.add(rt);
				}
			}
			return tarjetasTitularesAmex;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener Tarjetas Amex Titulares {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Obtener visa adicional ordenadas por fecha.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	static List<ResumenTarjetaDTO> obtenerVisaAdicionalOrdenadasPorFecha(List<ResumenTarjetaDTO> tarjetas)
			throws TarjetaBOUtilsException {
		try {
			List<ResumenTarjetaDTO> tarjetasVisaAdicionales = obtenerTarjetasVisaAdicionales(tarjetas);
			ordenarTarjetasPorFecha(tarjetasVisaAdicionales);
			return tarjetasVisaAdicionales;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener Visa Adicional Ordenadas Por Fecha {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Obtener amex adicional ordenadas por fecha.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	static List<ResumenTarjetaDTO> obtenerAmexAdicionalOrdenadasPorFecha(List<ResumenTarjetaDTO> tarjetas)
			throws TarjetaBOUtilsException {
		try {
			List<ResumenTarjetaDTO> tarjetasAmexAdicionales = obtenerTarjetasAmexAdicionales(tarjetas);
			ordenarTarjetasPorFecha(tarjetasAmexAdicionales);
			return tarjetasAmexAdicionales;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener Amex Adicional Ordenadas Por Fecha {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Obtener visa recargable ordenadas por fecha.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	static List<ResumenTarjetaDTO> obtenerVisaRecargableOrdenadasPorFecha(List<ResumenTarjetaDTO> tarjetas)
			throws TarjetaBOUtilsException {
		try {
			List<ResumenTarjetaDTO> tarjetasVisaRecargable = obtenerTarjetasVisaRecargable(tarjetas);
			ordenarTarjetasPorFecha(tarjetasVisaRecargable);
			return tarjetasVisaRecargable;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener Visa Recargable Ordenadas Por Fecha {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Obtener alias.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	static String obtenerAlias(Cuenta cuenta) {
		if (!StringUtils.isEmpty(cuenta.getAlias())) {
			return cuenta.getAlias();
		} else {
			return null;
		}
	}

	/**
	 * Filtrar visas Y recargables.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static List<Cuenta> filtrarVisasYRecargables(List<Cuenta> cuentas) throws TarjetaBOUtilsException {
		try {
			List<Cuenta> cuentasVisas = filtrarVisaTitular(cuentas);
			cuentasVisas.addAll(filtrarVisaAdicional(cuentas));

			return cuentasVisas;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener los Productos del Cliente en Sesion Filtrados por Tipo Tarjeta Visa {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Filtrar amexs.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static List<Cuenta> filtrarAmexs(List<Cuenta> cuentas) throws TarjetaBOUtilsException {
		try {
			List<Cuenta> cuentasAmexs = filtrarAmexTitular(cuentas);
			cuentasAmexs.addAll(filtrarAmexAdicional(cuentas));
			return cuentasAmexs;
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener los Productos del Cliente en Sesion Filtrados por Tipo Tarjeta Amex {}.", e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Filtrar amexs.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	public static List<Cuenta> filtrarMastercard(List<Cuenta> cuentas) throws TarjetaBOUtilsException {
		try {
			List<Cuenta> cuentasMastercard = filtrarMastercardTitular(cuentas);
			cuentasMastercard.addAll(filtrarMastercardAdicional(cuentas));
			return cuentasMastercard;
		} catch (NullPointerException e) {
			LOGGER.error(
					"Error al obtener los Productos del Cliente en Sesion Filtrados por Tipo Tarjeta Mastercard {}.",
					e);
			throw new TarjetaBOUtilsException(e);
		}
	}

	/**
	 * Es favorita.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	static Boolean esFavorita(Cuenta cuenta) {
		return cuenta.getIsFavorita();
	}

	/**
	 * Obtener nro cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	static String obtenerNroCuenta(Cuenta cuenta) {
		Boolean isAmex = esTipoCuentaAmex(cuenta);
		return TarjetaUtils.obtenerNroCuenta(isAmex, cuenta);
	}

	/**
	 * Sacar puntos.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	/*
	 * Sacar ceros y blancos izq.
	 */
	private static String sacarPuntos(String s) {
		return s.replace(".", "");
	}

	/**
	 * Sacar ceros Y blancos izq.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	/*
	 * Sacar ceros y blancos izq.
	 */
	private static String sacarCerosYBlancosIzq(String s) {
		int n;
		for (n = 0; n < s.length(); ++n) {
			if (s.charAt(n) != '0' && s.charAt(n) != ' ') {
				break;
			}
		}
		return s.substring(n, s.length());
	}

	/**
	 * Importe pto fijo 2 canonico.
	 *
	 * @param in
	 *            the in
	 * @param cantDecimales
	 *            the cant decimales
	 * @return the string
	 */
	private static String importePtoFijo2Canonico(String in, int cantDecimales) {
		try {
			if (in != null) {
				String importeIATX = in.trim();
				int l = importeIATX.length();
				if (l <= cantDecimales) {
					throw new ISBANRuntimeException(IMPORTE_IMVALIDO + importeIATX);
				}
				boolean isNegativo = importeIATX.charAt(l - 1) == '-';
				boolean tieneSigno = importeIATX.charAt(l - 1) == '+';
				if (isNegativo || tieneSigno) {
					importeIATX = importeIATX.substring(0, --l);
				}
				String e1 = sacarCerosYBlancosIzq(importeIATX.substring(0, l - cantDecimales));
				String e2 = sacarPuntos(e1);
				String entera = sacarComa(e2);
				if (entera.length() == 0) {
					entera = CERO;
				}
				String decimal = importeIATX.substring(l - cantDecimales, l);
				return (isNegativo ? "-" : "") + entera + decimal;
			}
			return null;
		} catch (ISBANRuntimeException e) {
			LOGGER.error("Error al calcular el importePtoFijoCanonico {}.", e);
			return null;
		}
	}

	/**
	 * Sacar coma.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	private static String sacarComa(String s) {
		return s.replace(",", ".");
	}

	/**
	 * Obtener tarjetas visa adicionales.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 */
	private static List<ResumenTarjetaDTO> obtenerTarjetasVisaAdicionales(List<ResumenTarjetaDTO> tarjetas) {
		List<ResumenTarjetaDTO> tarjetasAdicionalesVisa = new ArrayList<ResumenTarjetaDTO>();
		for (ResumenTarjetaDTO rt : tarjetas) {
			if (esTarjetaAdicional(rt, TarjetaUtils.MARCA_VISA)) {
				tarjetasAdicionalesVisa.add(rt);
			}
		}
		return tarjetasAdicionalesVisa;
	}

	/**
	 * Obtener tarjetas amex adicionales.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 */
	private static List<ResumenTarjetaDTO> obtenerTarjetasAmexAdicionales(List<ResumenTarjetaDTO> tarjetas) {
		List<ResumenTarjetaDTO> tarjetasAdicionalesAmex = new ArrayList<ResumenTarjetaDTO>();
		for (ResumenTarjetaDTO rt : tarjetas) {
			if (esTarjetaAdicional(rt, TarjetaUtils.MARCA_AMEX)) {
				tarjetasAdicionalesAmex.add(rt);
			}
		}
		return tarjetasAdicionalesAmex;
	}

	/**
	 * Obtener tarjetas visa recargable.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 */
	private static List<ResumenTarjetaDTO> obtenerTarjetasVisaRecargable(List<ResumenTarjetaDTO> tarjetas) {
		List<ResumenTarjetaDTO> tarjetasVisaRecargable = new ArrayList<ResumenTarjetaDTO>();
		for (ResumenTarjetaDTO rt : tarjetas) {
			if (esVisaRecargable(rt)) {
				tarjetasVisaRecargable.add(rt);
			}
		}
		return tarjetasVisaRecargable;
	}

	/**
	 * Ordenar tarjetas por fecha.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 */
	private static void ordenarTarjetasPorFecha(List<ResumenTarjetaDTO> tarjetas) {
		Collections.sort(tarjetas, new Comparator<ResumenTarjetaDTO>() {
			@Override
			public int compare(ResumenTarjetaDTO rt1, ResumenTarjetaDTO rt2) {
				if (rt1.getDetalle().getFechaDesde() == null || rt2.getDetalle().getFechaDesde() == null) {
					return 0;
				}
				return rt1.getDetalle().getFechaDesde().compareTo(rt2.getDetalle().getFechaDesde());
			}
		});
	}

	/**
	 * Es tarjeta titular.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param marcaTarjeta
	 *            the marca tarjeta
	 * @return the boolean
	 */
	private static Boolean esTarjetaTitular(ResumenTarjetaDTO tarjeta, String marcaTarjeta) {
		return tarjeta.getDetalle().getMarca().equals(marcaTarjeta) && tarjeta.getDetalle().getIsTitular();
	}

	/**
	 * Es tarjeta Adicional.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param marcaTarjeta
	 *            the marca tarjeta
	 * @return the boolean
	 */
	private static Boolean esTarjetaAdicional(ResumenTarjetaDTO tarjeta, String marcaTarjeta) {
		return tarjeta.getDetalle().getMarca().equals(marcaTarjeta) && tarjeta.getDetalle().getIsAdicional();
	}

	/**
	 * Es visa recargable.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the boolean
	 */
	private static Boolean esVisaRecargable(ResumenTarjetaDTO tarjeta) {
		return tarjeta.getDetalle().getMarca().equals(TarjetaUtils.MARCA_VISA_RECARGABLE);
	}

	/**
	 * Convierte la fecha de tipo Date ingresada por parametro al español.
	 *
	 * @param fechaDate
	 *            the fecha date
	 * @return the string
	 */
	public static String convertirFechaAlEspaniol(Date fechaDate) {
		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy",
				obtenerConfiguracionFechaEspaniol());
		return formateador.format(fechaDate);
	}

	/**
	 * Obtiene la configuracion de fecha en espaniol con meses en mayuscula.
	 *
	 * @return the date format symbols
	 */
	private static DateFormatSymbols obtenerConfiguracionFechaEspaniol() {
		DateFormatSymbols sym = DateFormatSymbols.getInstance(new Locale("es", "ES"));
		sym.setMonths(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
				"Septiembre", "Octubre", "Noviembre", "Diciembre" });
		return sym;
	}

	/**
	 * Es cuenta recargable.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	public static Boolean esCuentaRecargable(Cuenta cuenta) {
		return TarjetaUtils.CUENTA_RECARGABLE.equals(cuenta.getSubproductoAltair());
	}

    /**
	 * Filtrar cuentas de tipo banelco.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
    public static List<Cuenta> filtrarCuentasDeTipoBanelco(List<Cuenta> cuentas) {
        List<Cuenta> tarjetas = new ArrayList<Cuenta>();
        for (int i = 0; i < cuentas.size(); i++) {
            Cuenta c = cuentas.get(i);
            if (!esTipoCuentaBanelco(c)) {
                tarjetas.add(c);
            }
        }
        return tarjetas;
    }

    /**
	 * Es tipo cuenta banelco.
	 *
	 * @param c
	 *            the c
	 * @return true, if successful
	 */
    private static boolean esTipoCuentaBanelco(Cuenta c) {
        if (c.getTipoCuentaEnum() == null) {
            return Boolean.FALSE;
        }
        TipoCuenta tipo = c.getTipoCuentaEnum();
        return TipoCuenta.BANELCO.equals(tipo);
    }

    /**
	 * Es cuenta de valida de reemplazo de tarjeta.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return List<Cuenta>
	 */
	public static boolean esValidoReemplazoDeTarjeta(Cuenta cuenta) {
		
		boolean esTitular = cuenta.esTitular();
		boolean esTipoCuenta = ( cuenta.esVisa() || cuenta.esAmex() );
		boolean estadoTarjetaCredito = cuenta.esEstadoTarjetaCredito();
		
		return esTitular && esTipoCuenta && estadoTarjetaCredito;
	}
	
	
    public static String buscarTarjeta (Cliente cliente) {

    	String numeroTarjeta = StringUtils.EMPTY;
    	
    	for (Cuenta cuenta : cliente.getCuentas()) {
    		if (cuenta.esTarjetaDeCredito() && cuenta.getIsFavorita() && !TarjetaBOUtils.esAdicionalCuenta(cuenta)) {
    			numeroTarjeta = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
    		}
    	}
    	
    	for (Cuenta cuenta : cliente.getCuentas()) {
    		if (cuenta.esTarjetaDeCredito() && !TarjetaBOUtils.esAdicionalCuenta(cuenta) && StringUtils.isBlank(numeroTarjeta)) {
    			numeroTarjeta = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
    		}
    	}
    	
    	return numeroTarjeta;
    	
    }

}
