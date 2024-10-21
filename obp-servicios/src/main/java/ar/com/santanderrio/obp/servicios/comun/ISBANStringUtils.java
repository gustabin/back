/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Class ISBANStringUtils.
 */
public final class ISBANStringUtils {

	private static final String COD_BCO = "0072";

	/** The Constant OFFSET_DNI_EN_CUIL. */
	private static final int OFFSET_DNI_EN_CUIL = 2;

	/** The Constant DOS_PUNTOS_STRING. */
	public static final String DOS_PUNTOS_STRING = ":";

	/** The Constant GUION_STRING. */
	public static final String GUION_STRING = "-";

	/** The Constant DIEZ_MIL. */
	private static final int DIEZ_MIL = 10000;

	/** The Constant ZERO_STR. */
	public static final String ZERO_STR = "0";

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ISBANStringUtils.class);

	/** The Constant POS_FIN_CUENTA. */
	private static final int POS_FIN_CUENTA = 22;

	/** The Constant POS_INI_CUENTA. */
	private static final int POS_INI_CUENTA = 8;

	/** The Constant POS_FIN_CODIGO_BANCO. */
	private static final int POS_FIN_CODIGO_BANCO = 8;

	/** The Constant POS_DIGITO_VERIFICADOR_CUENTA. */
	private static final int POS_DIGITO_VERIFICADOR_CUENTA = 13;

	/** The Constant LONGITUD_CUENTA. */
	private static final int LONGITUD_CUENTA = 14;

	/** The Constant POS_DIGITO_VERIFICADOR_SUCURSAL. */
	private static final int POS_DIGITO_VERIFICADOR_SUCURSAL = 7;

	/** The Constant POS_FIN_SUCURSAL. */
	private static final int POS_FIN_SUCURSAL = 7;

	/** The Constant POS_INI_SUCURSAL. */
	private static final int POS_INI_SUCURSAL = 4;

	/** The Constant POS_DIGITO_VERIFICADOR_BANCO. */
	private static final int POS_DIGITO_VERIFICADOR_BANCO = 3;

	/** The Constant POS_FIN_ID_BANCO. */
	private static final int POS_FIN_ID_BANCO = 3;

	/** The Constant LONGITUD_CODIGO_BANCO. */
	private static final int LONGITUD_CODIGO_BANCO = 8;

	/** The Constant DIGITO_VERIFICADOR_9. */
	private static final int DIGITO_VERIFICADOR_9 = 9;

	/**
	 * Posiciones para extraccion de sucursal, Nro.Cuenta y digito verificador:
	 */
	private static final int CUENTA_SUC_INI = 0;

	/** The Constant CUENTA_SUC_FIN. */
	private static final int CUENTA_SUC_FIN = 3;

	/** The Constant CUENTA_NRO_INI. */
	private static final int CUENTA_NRO_INI = 4;

	/** The Constant CUENTA_NRO_FIN. */
	private static final int CUENTA_NRO_FIN = 10;

	/** The Constant CUENTA_DVR_INI. */
	private static final int CUENTA_DVR_INI = 11;

	/** The Constant CUENTA_DVR_FIN. */
	private static final int CUENTA_DVR_FIN = 12;

	/** The Constant LONGITUD_CUIL. */
	private static final int LONGITUD_CUIL = 11;

	/** The Constant COMA_DECIMAL. */
	private static final String COMA_DECIMAL = ".";

	/** The Constant COMA_STRING. */
	private static final String COMA_STRING = ",";

	/** The Constant UTF8. */
	public static final String UTF8 = "UTF-8";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	/** The Constant FORMATO_FECHA. */
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	
	/** The Constant FORMATO_FECHA. */
	public static final String FORMATO_FECHA_GUIONES = "yyyy-MM-dd";

	/** The Constant FORMATO_FECHA. */
	public static final String FORMATO_FECHA_SIN_BARRAS = "ddMMyyyy";

	/** The Constant FORMATO_FECHA_ANIO_CORTO. */
	public static final String FORMATO_FECHA_ANIO_CORTO = "dd/MM/yy";

	/** The Constant FORMATO_FECHA_ANIO_HORA_CORTO. */
	public static final String FORMATO_FECHA_ANIO_HORA_CORTO = "dd/MM/yy - HH:mm";

	/** The Constant FORMATO_FECHA_DIA_MES. */
	private static final String FORMATO_FECHA_DIA_MES = "dd/MM";

	/** The Constant FORMATO_FECHA_CORTA_SIN_ANO. */
	private static final String FORMATO_FECHA_CORTA_SIN_ANIO = "dd/MM";

	/** The Constant FORMATO_FECHA_CORTA_SIN_ANO. */
	public static final String FORMATO_SIN_FECHA_ASOCIADA_CORTA_SIN_ANIO = "-/-";

	/** The Constant FORMATO_SIN_FECHA_ASOCIADA_CON_ANIO. */
	public static final String FORMATO_SIN_FECHA_ASOCIADA_CON_ANIO = "-/-/-";

	/** The Constant FECHA_NO_DISPONIBLE. */
	public static final String FECHA_NO_DISPONIBLE = "No disponible";

	/** The Constant CADENA_CARACETERES_ESPECIALES. */
	private static final String CADENA_CARACETERES_ESPECIALES = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";

	/** The Constant CADENA_CARACETERES_ESPECIALES_REMOVIDOS. */
	private static final String CADENA_CARACETERES_ESPECIALES_REMOVIDOS = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";

	/** The Constant IMPORTE_INVALIDO. */
	private static final String IMPORTE_INVALIDO = "Importe invalido: ";

	/** The Constant SUCURSAL_PAD_SIZE. */
	private static final int SUCURSAL_PAD_SIZE = 3;
	
	/** The Constant SUCURSAL_MAPS_PAD_SIZE. */
	private static final int SUCURSAL_MAPS_PAD_SIZE = 4;
	

	/** The Constant SUCURSAL_PAD_CHAR. */
	private static final String SUCURSAL_PAD_CHAR = ZERO_STR;

	/** The Constant CUENTA_PAD_SIZE. */
	private static final int CUENTA_PAD_SIZE = 7;

	/** The Constant SEPARATOR_BARRA_LATERAL. */
	public static final String SEPARATOR_BARRA_LATERAL = "/";

	/** The Constant SEPARATOR_DECIMALES. */
	private static final String SEPARATOR_DECIMALES = "\\.";

	/** The Constant TIPOCTA_VISA. */
	private static final String TIPOCTA_VISA = "07";

	/** The Constant TIPOCTA_AMEX. */
	private static final String TIPOCTA_AMEX = "42";

	/** The Constant TIPOCTA_MASTER. */
	private static final String TIPOCTA_MASTER = "06";

	/** The Constant CANTIDAD_CARACTERES_VISA_MASTER. */
	private static final int CANTIDAD_CARACTERES_VISA_MASTER = 4;

	/** The Constant PREFIJO_X. */
	private static final String PREFIJO_X = "XXXX";

	/** The Constant ENTERO_CERO. */
	private static final int ENTERO_CERO = 0;

	/** The Constant ENTERO_UNO. */
	private static final int ENTERO_UNO = 1;

	/** The Constant ENTERO_TRES. */
	private static final int ENTERO_TRES = 3;

	/** The Constant ENTERO_CUATRO. */
	private static final int ENTERO_CUATRO = 4;

	/** The Constant ENTERO_CINCO. */
	private static final int ENTERO_CINCO = 5;

	/** The Constant ENTERO_SEIS. */
	private static final int ENTERO_SEIS = 6;

	/** The Constant ENTERO_SIETE. */
	private static final int ENTERO_SIETE = 7;

	/** The Constant ENTERO_CUARENTA_Y_DOS. */
	private static final int ENTERO_CUARENTA_Y_DOS = 42;

	/** The Constant ENTERO_OCHO. */
	private static final int ENTERO_OCHO = 8;

	/** The Constant ENTERO_SETENTA_Y_SIETE. */
	private static final int ENTERO_SETENTA_Y_SIETE = 77;

	/** The Constant ENTERO_DIEZ. */
	private static final int ENTERO_DIEZ = 10;

	/** The Constant ENTERO_NUEVE. */
	private static final int ENTERO_NUEVE = 9;

	/** The Constant ENTERO_DOS. */
	private static final int ENTERO_DOS = 2;

	/** The Constant CBU_LENGTH. */
	private static final int CBU_LENGTH = 22;

	/** The Constant LONGITUD_CUENTA_COMPLETA. */
	private static final int LONGITUD_CUENTA_COMPLETA = 12;

	/** The Constant ESPACIO_STRING. */
	public static final String ESPACIO_STRING = " ";

	/** The Constant COMIENZO_FECHA. */
	private static final String COMIENZO_FECHA = "20";

	/** The Constant FORMATO_FECHA_HORA_COMPROBANTE. */
	public static final String FORMATO_FECHA_HORA_COMPROBANTE = "dd/MM/yyyy - HH:mm";

	/** The Constant FORMATO_FECHA_IATX. */
	public static final String FORMATO_FECHA_IATX = "yyyyMMdd";

	/** The Constant LARGO_IMPORTES. */
	private static final int LARGO_IMPORTES = 14;

	/** The Constant LONGITUD_DECIMALES. */
	private static final int LONGITUD_DECIMALES = 2;

	/** The Constant COD_BANCO_SRIO. */
	private static final String COD_BANCO_SRIO = "072";

	private static final Character CODIGO_CUENTA_RECAUDADORA='7';

	/** The Constant COD_CVU. */
	private static final String COD_CVU = "000";

	/** The Constant EMAIL_PATTERN. */
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/** The Constant MONEY_PATTERN. */
	private static final String MONEY_PATTERN = "(?=.)^\\$?(([1-9][0-9]{0,2}(.[0-9]{3})*)|[0-9]+)?(\\,[0-9]{1,2})?$";

	/**
	 * The Constant FORMATO_TIMESTAMP_IATX. Ejemplo:
	 * "2017-10-26-12.04.28.503846"
	 */
	private static final String FORMATO_TIMESTAMP_IATX = "yyyy-MM-dd-HH.mm.ss";

	/** The regex de Alias para sintaxis correcta. */
	private static String ALIAS_REGEX = "[A-Za-z0-9.-]*";
	
	private static final int LONGITUD_NRO_CTA = 12;
	
	private static final int LONGITUD_SUCURSAL = 4;

	/**
	 * Instantiates a new ISBAN string utils.
	 */
	private ISBANStringUtils() {

	}

	/**
	 * Gets the cbu length.
	 *
	 * @return the cbu length
	 */
	public static int getCbuLength() {
		return CBU_LENGTH;
	}

	/**
	 * Checks if is empty or null.
	 *
	 * @param input
	 *            the input
	 * @return true, if is empty or null
	 */
	public static boolean isEmptyOrNull(String input) {
		return null == input || input.isEmpty();
	}

	/**
	 * Normalizar caraceteres.
	 *
	 * @param input
	 *            the input
	 * @return the string
	 */
	public static String normalizarCaraceteres(String input) {
		String output = input;
		if (input != null) {
			output = output.trim();
			output = output.toLowerCase(Locale.getDefault());
			output = removerCaraceteresEspeciales(output);
		} else {
			output = EMPTY_STRING;
		}
		return output;
	}

	/**
	 * Normaliza el string reemplazando las vocales acentuadas por vocales
	 * normales, y la letra e~ne por la ene.
	 * 
	 * @param noASCII
	 *            el string con acentos y la letra e~ne
	 * @return el string normalizado
	 */
	public static String normalizarASCII(String noASCII) {
		if (noASCII == null) {
			return null;
		}
		return Normalizer.normalize(noASCII.replaceAll("\\p{M}", ""), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]",
				"");
	}

	/**
	 * Formatea el CUIT/CUIL. Debe recibir como parametro el numero de CUIT/CUIL
	 * de 11 digitos (sin los guiones). Ejemplo: 23452347891.
	 *
	 * @param cuitCuil
	 *            the cuit cuil
	 * @return the string
	 */
	public static String agregarGuionesANumeroCuitCuil(String cuitCuil) {
		if (cuitCuil != null) {
			StringBuilder sb = new StringBuilder();
			return sb.append(StringUtils.substring(cuitCuil, 0, 2)).append(GUION_STRING)
					.append(StringUtils.substring(cuitCuil, 2, 10)).append(GUION_STRING)
					.append(StringUtils.substring(cuitCuil, 10, 11)).toString();
		}
		return GUION_STRING;
	}

	/**
	 * Remover caraceteres especiales.
	 *
	 * @param input
	 *            the input
	 * @return the string
	 */
	public static String removerCaraceteresEspeciales(String input) {
		String output = input;
		for (int i = 0; i < CADENA_CARACETERES_ESPECIALES.length(); i++) {
			// Reemplazamos los caracteres especiales.
			output = output.replace(CADENA_CARACETERES_ESPECIALES.charAt(i),
					CADENA_CARACETERES_ESPECIALES_REMOVIDOS.charAt(i));
		} // for i
		return output;
	}

	/**
	 * Formatear sucursal.
	 *
	 * @param sucursal
	 *            the sucursal
	 * @return the string
	 */
	public static String formatearSucursal(String sucursal) {
		return StringUtils.leftPad(eliminarCeros(sucursal), SUCURSAL_PAD_SIZE, SUCURSAL_PAD_CHAR);
	}
	
	
	/**
	 * Formatear sucursalMaps.
	 *
	 * @param sucursal
	 *            the sucursal
	 * @return the string
	 */
	public static String formatearSucursalMaps(String sucursal) {
		return StringUtils.leftPad(eliminarCeros(sucursal), SUCURSAL_MAPS_PAD_SIZE, SUCURSAL_PAD_CHAR);
	}
	

	/**
	 * Formatear sucursal.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the string
	 */
	public static String formatNroCuenta(String nroCuenta) {
		return StringUtils.leftPad(ISBANStringUtils.eliminarCeros(nroCuenta),
				ISBANStringUtils.getLongitudCuentaCompleta(), SUCURSAL_PAD_CHAR);
	}

	/**
	 * Formatear documento.
	 *
	 * @param documento
	 *            the documento
	 * @return the string
	 */
	public static String formatearDocumento(String documento) {
		StringBuilder builder = new StringBuilder();
		if (documento != null) {
			builder.append(StringUtils.substring(documento, 0, documento.length() % 3)).append(".");
			String faltante = documento.substring(documento.length() % 3);
			while (faltante.trim().length() > 3) {
				builder.append(StringUtils.substring(faltante, 0, 3)).append(".");
				faltante = faltante.substring(3);
			}
			builder.append(faltante);
		}
		return builder.toString();
	}

	/**
	 * Formateador con ceros izq.
	 *
	 * @param valor
	 *            the valor
	 * @param cantidad
	 *            the cantidad
	 * @return the string
	 */
	public static String formateadorConCerosIzq(String valor, int cantidad) {
		return StringUtils.leftPad(eliminarCeros(valor), cantidad, SUCURSAL_PAD_CHAR);
	}

	/**
	 * Formatear numero cuenta. Ejemplo: 2579806/6
	 *
	 * @param cuentaProducto
	 *            the cuenta producto
	 * @return the string
	 */
	public static String formatearNumeroCuenta(String cuentaProducto) {
		String numeroCuenta = StringUtils.leftPad(eliminarCeros(cuentaProducto), CUENTA_PAD_SIZE, SUCURSAL_PAD_CHAR);
		numeroCuenta = numeroCuenta.substring(0, numeroCuenta.length() - 1) + SEPARATOR_BARRA_LATERAL
				+ numeroCuenta.substring(numeroCuenta.length() - 1);
		return numeroCuenta;
	}

	/**
	 * Eliminar ceros.
	 *
	 * @param nroSucursal
	 *            the nro sucursal
	 * @return the string
	 */
	public static String eliminarCeros(String nroSucursal) {
		return String.valueOf(Long.parseLong(nroSucursal));
	}

	/**
	 * Formatear saldo.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldo(BigDecimal saldo) {
		BigDecimal abs = saldo == null ? BigDecimal.ZERO : saldo.abs();
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("#,##0.00", simbolos);
		return df.format(abs);
	}

	/**
	 * Formatear saldo minimo 2 decimales maximo 7.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldoPrecioReferencia(BigDecimal saldo) {
		BigDecimal abs = saldo == null ? BigDecimal.ZERO : saldo.abs();
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("###,###,###,##0.00#####", simbolos);
		return df.format(abs);
	}

	/**
	 * Formatear saldo.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldoSinAbs(BigDecimal saldo) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.getDefault());
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("#,##0.00", simbolos);
		return df.format(saldo);
	}

	/**
	 * Formatear saldo con divisa.
	 *
	 * @param saldo
	 *            the saldo
	 * @param divisa
	 *            the divisa
	 * @return the string
	 */
	public static String formatearSaldoConDivisa(BigDecimal saldo, DivisaEnum divisa) {
		return formatearSaldoSinAbsConDivisa(saldo == null ? BigDecimal.ZERO : saldo.abs(), divisa);
	}

	/**
	 * Formatear saldo negado con divisa.
	 *
	 * @param saldo
	 *            the saldo
	 * @param divisa
	 *            the divisa
	 * @return the string
	 */
	public static String formatearSaldoNegadoConDivisa(BigDecimal saldo, DivisaEnum divisa) {
		return formatearSaldoSinAbsConDivisa(saldo == null ? BigDecimal.ZERO : saldo.negate(), divisa);
	}

	/**
	 * Formatear saldo sin abs con divisa.
	 *
	 * @param saldo
	 *            the saldo
	 * @param divisa
	 *            the divisa
	 * @return the string
	 */
	public static String formatearSaldoSinAbsConDivisa(BigDecimal saldo, DivisaEnum divisa) {
		return divisa.getSimbolo() + ESPACIO_STRING + formatearSaldoSinAbs(saldo == null ? BigDecimal.ZERO : saldo);
	}

	/**
	 * Formatear saldo parentesis.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldoParentesis(BigDecimal saldo) {
		String saldoString = formatearSaldo(saldo);
		if (saldo.compareTo(BigDecimal.ZERO) < 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("(");
			sb.append(saldoString);
			sb.append(")");
			saldoString = sb.toString();
		}
		return saldoString;
	}

	/**
	 * Formatear saldo con signo.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	public static String formatearSaldoConSigno(BigDecimal saldo) {
		return formatearSaldoSinAbs(saldo);
	}
	
	/**
	 * Formatear fecha yyyy-MM-dd.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatearFechaAnioMesDiaConGuiones(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_GUIONES);
		return sdf.format(date);
	}

	/**
	 * Formatear fecha.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatearFecha(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		return sdf.format(date);
	}

	/**
	 * Formatear fecha IATX.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatearFechaIATX(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_IATX);
		return sdf.format(date);
	}

	/**
	 * Devuelve la fecha y la hora separadas por un guion, para usar en el
	 * comprobante con el siguiente formato: "DD/MM/AAA - HH:MM".
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatearFechaConHoraParaComprobante(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_HORA_COMPROBANTE);
		return sdf.format(date);
	}

	/**
	 * Formatear fecha corta sin anio. Si hay error en el parseo se devuelve la
	 * cadena default "--/--".
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatearFechaSinAnio(String date) {
		SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA);
		String salida = inicializarFecha();
		if (StringUtils.isNotEmpty(date) && !FECHA_NO_DISPONIBLE.equals(date)) {
			try {
				Date fecha = fFecha.parse(date);
				fFecha = new SimpleDateFormat(FORMATO_FECHA_CORTA_SIN_ANIO);
				salida = fFecha.format(fecha);
			} catch (ParseException pe) {
				LOGGER.error("Error al formatear la fecha {} sin anio, se devuelve formato default {}.", date,
						FORMATO_SIN_FECHA_ASOCIADA_CORTA_SIN_ANIO, pe);
			}
		}
		LOGGER.info("Fecha {} formateada {}.", date, salida);
		return salida;
	}

	/**
	 * Formatear fecha corta sin anio. Si hay error en el parseo se devuelve la
	 * cadena default "-/-".
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatearFechaConAnio(String date) {

		String salida = inicializarFecha();
		if (StringUtils.isNotEmpty(date) && !FECHA_NO_DISPONIBLE.equals(date)) {
			try {
				SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA);
				Date fecha = fFecha.parse(date);
				salida = fFecha.format(fecha);
			} catch (ParseException pe) {
				LOGGER.error("Error al formatear la fecha {} con anio, se devuelve formato default {}.", date, pe);
			}
		}
		LOGGER.info("Fecha {} formateada {}.", date, salida);
		return salida;
	}

	/**
	 * Formatear fecha corta sin anio. Si hay error retorna exception
	 *
	 * @param date
	 *            the date
	 * @return the string
	 * @throws ParseException
	 *             the parse exception
	 */
	public static String formatearFechaAnio(String date) throws ParseException {
		if (StringUtils.isNotEmpty(date) && !FECHA_NO_DISPONIBLE.equals(date)) {
			SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA);
			Date fecha = fFecha.parse(date);
			return fFecha.format(fecha);
		}
		return null;
	}

	/**
	 * Formatear fecha con anio. Si hay error en el parseo se devuelve la cadena
	 * default "-/-".
	 *
	 * @param date
	 *            the date
	 * @return the string
	 * @throws ParseException
	 *             the parse exception
	 */
	public static String formatearFechaConAnio(Date date) throws ParseException {
		String salida = inicializarFecha();
		if (date != null) {
			SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA);
			salida = fFecha.format(date);
		}
		LOGGER.info("Fecha {} formateada {}.", date, salida);
		return salida;
	}

	/**
	 * Formatear fecha corta sin anio. Si hay error en el parseo se devuelve la
	 * cadena default "--/--".
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static Date formatearFechaPlanV(String date) {
		SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA);
		String salida = inicializarFecha();
		String dateConFormato = null;
		Date fecha = null;
		if (date != null && !date.isEmpty() && date.length() == 8) {
			String dia = (String) date.subSequence(0, 2);
			String mes = (String) date.subSequence(2, 4);
			String anio = (String) date.subSequence(4, 8);
			dateConFormato = dia + "/" + mes + "/" + anio;
			if (StringUtils.isNotEmpty(dateConFormato) && !FECHA_NO_DISPONIBLE.equals(dateConFormato)) {
				try {
					fecha = fFecha.parse(dateConFormato);
				} catch (ParseException pe) {
					LOGGER.error("Error al formatear la fecha {} sin anio, se devuelve formato default {}.", date,
							FORMATO_SIN_FECHA_ASOCIADA_CORTA_SIN_ANIO, pe);
				}
			}
		}
		LOGGER.info("Fecha {} formateada {}.", dateConFormato, salida);
		return fecha;
	}

	/**
	 * Comparar horas.
	 *
	 * @param horaInicial
	 *            the hora inicial
	 * @param horaFinal
	 *            the hora final
	 * @return true, if successful
	 * @throws ParseException
	 *             the parse exception
	 */
	public static boolean compararHoras(String horaInicial, String horaFinal) throws ParseException {
		Calendar calIni = obtenerNuevoCalendario();
		calIni.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaInicial.subSequence(0, 2).toString()));
		calIni.set(Calendar.MINUTE, Integer.parseInt(horaInicial.subSequence(3, 5).toString()));
		calIni.set(Calendar.SECOND, 0);

		Calendar calFin = obtenerNuevoCalendario();
		calFin.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaFinal.subSequence(0, 2).toString()));
		calFin.set(Calendar.MINUTE, Integer.parseInt(horaFinal.subSequence(3, 5).toString()));
		calFin.set(Calendar.SECOND, 0);

		Calendar calActual = obtenerNuevoCalendario();
		Date dateActual = calActual.getTime();

		return dateActual.after(calIni.getTime()) && dateActual.before(calFin.getTime());
	}

	/**
	 * Obtener nuevo calendario.
	 *
	 * @return the calendar
	 */
	private static Calendar obtenerNuevoCalendario() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal;
	}

	/**
	 * Checks if is null or blank.
	 *
	 * @param s
	 *            the s
	 * @return true, if is null or blank
	 */
	public static boolean isNullOrBlank(String s) {
		return s == null || "".equals(s.trim());
	}

	/**
	 * Checks if is null or empty.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return true, if is null or empty
	 */
	public static <T> boolean isNullOrEmpty(List<T> list) {
		return list != null && list.isEmpty();
	}

	/**
	 * Formatear fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 */
	public static Date formatearFecha(String fecha) {
		return formatearFecha(fecha, FORMATO_FECHA);
	}
	
	public static String formatearFechaConGuiones(String fecha) {
		Date date = formatearFecha(fecha, FORMATO_FECHA_GUIONES);
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		return sdf.format(date);
	}

	/**
	 * Formatear fecha anio corto.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 */
	public static Date formatearFechaAnioCorto(String fecha) {
		return formatearFecha(fecha, FORMATO_FECHA_ANIO_CORTO);
	}

	/**
	 * Formatear fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @param formato
	 *            the formato
	 * @return the date
	 */
	public static Date formatearFecha(String fecha, String formato) {
		SimpleDateFormat fFecha = new SimpleDateFormat(formato);
		Date fechaSalida = new Date();
		if (StringUtils.isNotEmpty(fecha) && !FECHA_NO_DISPONIBLE.equals(fecha)) {
			try {
				fechaSalida = fFecha.parse(fecha);
			} catch (ParseException pe) {
				LOGGER.error("Error al formatear la fecha {} sin anio, se devuelve formato default {}.", fecha,
						FORMATO_SIN_FECHA_ASOCIADA_CORTA_SIN_ANIO, pe);
				return null;
			}
		}
		return fechaSalida;
	}

	/**
	 * Formatear fecha dia mes.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 */
	public static Date formatearFechaDiaMes(String fecha) {
		SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA_DIA_MES);
		Date fechaSalida = new Date();
		if (StringUtils.isNotEmpty(fecha) && !FECHA_NO_DISPONIBLE.equals(fecha)) {
			try {
				fechaSalida = fFecha.parse(fecha);
			} catch (ParseException pe) {
				LOGGER.error("Error al formatear la fecha {} sin anio, se devuelve formato default {}.", fecha,
						FORMATO_SIN_FECHA_ASOCIADA_CORTA_SIN_ANIO, pe);
				return null;
			}
		}
		return fechaSalida;
	}

	/**
	 * Parsea fecha con anio.
	 *
	 * @param date
	 *            the date
	 * @return the Date
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Date parsearFechaConAnio(String date) throws ParseException {
		Date fecha = null;
		if (StringUtils.isNotEmpty(date) && !ISBANStringUtils.FECHA_NO_DISPONIBLE.equals(date)) {
			SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA);
			fecha = fFecha.parse(date);
		}
		return fecha;
	}

	/**
	 * Formatear fecha sin hora. Le da formato a la fecha Formato Entrada:
	 * AAAAMMDD Formato Salida: â€œDD/MM/AAAA".
	 *
	 * @param fechaAformatear
	 *            the fecha a formatear
	 * @return the string
	 */
	public static String formatearFechaSinHora(String fechaAformatear) {
		return fechaAformatear.substring(6, 8) + ISBANStringUtils.SEPARATOR_BARRA_LATERAL
				+ fechaAformatear.substring(4, 6) + ISBANStringUtils.SEPARATOR_BARRA_LATERAL
				+ fechaAformatear.substring(0, 4);
	}

	/**
	 * Formatear fecha.
	 *
	 * @param date
	 *            the date
	 * @param formatCustom
	 *            the format custom
	 * @return the string
	 */
	public static String formatearFecha(Date date, String formatCustom) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatCustom);
		return sdf.format(date);
	}

	/**
	 * Inicializa la fecha en caso de error.
	 * 
	 * @author florencia.n.martinez
	 * @return the string
	 */
	public static String inicializarFecha() {
		return FORMATO_SIN_FECHA_ASOCIADA_CON_ANIO;
	}

	/**
	 * Parsear timestamp IATX. ejemplo: "2017-10-26-12.04.28.503846"
	 * 
	 * @param date
	 *            the date
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Date parsearTimestampIATX(String date) throws ParseException {
		Date fecha = null;
		if (StringUtils.isNotEmpty(date) && !ISBANStringUtils.FECHA_NO_DISPONIBLE.equals(date)) {
			SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_TIMESTAMP_IATX);
			fecha = fFecha.parse(date);
		}
		return fecha;
	}

	/**
	 * Parsear fecha IATX.
	 * 
	 * @param date
	 *            the date
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Date parsearFechaIATX(String date) throws ParseException {
		Date fecha = null;
		if (StringUtils.isNotEmpty(date) && !ISBANStringUtils.FECHA_NO_DISPONIBLE.equals(date)) {
			SimpleDateFormat fFecha = new SimpleDateFormat(FORMATO_FECHA_IATX);
			fecha = fFecha.parse(date);
		}
		return fecha;
	}

	/**
	 * Importe pto fijo2 canonico.
	 *
	 * @param in
	 *            the in
	 * @return the string
	 */
	public static String importePtoFijo2Canonico(String in) {
		if (in == null) {
			return "";
		}

		String importeIATX = StringUtils.leftPad(in.trim(), 3, "0");
		int l = importeIATX.length();
		int cantDecimales = 2;

		boolean tieneSigno = importeIATX.charAt(l - 1) == '+';
		boolean isNegativo = importeIATX.charAt(l - 1) == '-';

		if (tieneSigno || isNegativo) {
			importeIATX = importeIATX.substring(0, --l);
		}

		int n;
		String s = importeIATX.substring(0, l - cantDecimales);
		for (n = 0; n < s.length(); ++n) {
			if (s.charAt(n) != '0' && s.charAt(n) != ' ') {
				break;
			}
		}
		String entera = s.substring(n, s.length());

		if (entera.length() == 0) {
			entera = ZERO_STR;
		}
		String decimal = importeIATX.substring(l - cantDecimales, l);
		return (isNegativo ? GUION_STRING : "") + entera + "." + decimal;
	}

	/**
	 * Convertir primer letra en mayuscula.
	 *
	 * @param original
	 *            the original
	 * @return the string
	 */
	public static String convertirPrimerLetraEnMayuscula(String original) {
		if (original == null || original.length() == 0) {
			return original;
		}
		return original.substring(0, 1).toUpperCase(Locale.getDefault()) + original.substring(1);
	}

	/**
	 * Tipo cuenta canonico.
	 *
	 * @param tipoNumerico
	 *            the tipo numerico
	 * @return the string
	 * @throws CuentaInvalidaException
	 *             the cuenta invalida exception
	 */
	public static String tipoCuentaCanonico(String tipoNumerico) throws CuentaInvalidaException {
		int tipo = Integer.parseInt(tipoNumerico);
		switch (tipo) {
		case ENTERO_CERO:
			return "CCP";
		case ENTERO_UNO:
			return "CAP";
		case ENTERO_TRES:
			return "CCD";
		case ENTERO_CUATRO:
			return "CAD";
		case ENTERO_CINCO:
			return "ABAE";
		case ENTERO_SEIS:
			return "RM";
		case ENTERO_SIETE:
			return "RV";
		case ENTERO_CUARENTA_Y_DOS:
			return "RA";
		case ENTERO_OCHO:
			return "CT";
		case ENTERO_SETENTA_Y_SIETE:
			return "AVIS";
		case ENTERO_DOS:
		case ENTERO_NUEVE:
		case ENTERO_DIEZ:
			return "CU";
		default:
			throw new CuentaInvalidaException("tipo de cuenta invalido : " + tipoNumerico);
		}
	}

	/**
	 * Tipo cuenta iatx.
	 *
	 * @param tipoCanonico
	 *            the tipo canonico
	 * @return the string
	 * @throws CuentaInvalidaException
	 *             the cuenta invalida exception
	 */
	public static String tipoCtaIatx(String tipoCanonico) throws CuentaInvalidaException {
		if ("CCP".equals(tipoCanonico)) {
			return "00";
		} else if ("CAP".equals(tipoCanonico)) {
			return "01";
		} else if ("CCD".equals(tipoCanonico)) {
			return "03";
		} else if ("CAD".equals(tipoCanonico)) {
			return "04";
		} else if ("CUP".equals(tipoCanonico)) {
			return "09";
		} else if ("CUD".equals(tipoCanonico)) {
			return "10";
		} else if ("CU".equals(tipoCanonico)) {
			return "02";
		} else {
			throw new CuentaInvalidaException("tipo de cuenta invalido : " + tipoCanonico);
		}
	}

	/**
	 * Realiza la mascara segun el tipo de tarjeta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param tipoTarjeta
	 *            the tipo tarjeta
	 * @return the string
	 */
	public static String mascaraTarjetaCredito(String tarjeta, String tipoTarjeta) {
		if (tipoTarjeta.equalsIgnoreCase(TIPOCTA_VISA) || tipoTarjeta.equalsIgnoreCase(TIPOCTA_MASTER)) {
			return PREFIJO_X + GUION_STRING + StringUtils.right(tarjeta, CANTIDAD_CARACTERES_VISA_MASTER);
		}
		if (tipoTarjeta.equalsIgnoreCase(TIPOCTA_AMEX)) {
			return PREFIJO_X + GUION_STRING + tarjeta.substring(tarjeta.length() - 6, tarjeta.length() - 1);
		}
		return tarjeta;

	}

	/**
	 * Validar cuil.
	 *
	 * @param cuilString
	 *            the cuil string
	 * @return true, if successful
	 */
	public static boolean validarCuil(String cuilString) {
		if (StringUtils.isEmpty(cuilString)) {
			return false;
		}
		String cuilStr = cuilString.replace(GUION_STRING, "");
		char[] cuil = cuilStr.toCharArray();
		if (cuil.length != LONGITUD_CUIL || !StringUtils.isNumeric(cuilStr)) {
			return false;
		} else {
			int[] mult = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
			int total = 0;
			for (int i = 0; i < mult.length; i++) {
				total += Character.getNumericValue(cuil[i]) * mult[i];
			}
			int mod = total % LONGITUD_CUIL;
			int digito;
			if (mod == 0) {
				digito = 0;
			} else if (mod == 1) {
				digito = DIGITO_VERIFICADOR_9;
			} else {
				digito = LONGITUD_CUIL - mod;
			}
			boolean cuitValido = digito == Character.getNumericValue(cuil[LONGITUD_CUIL - 1]);
			if (!cuitValido) {
				LOGGER.debug("El CUIT :" + cuilStr + " no es valido");
			}
			return cuitValido;
		}
	}

	/**
	 * Validar cuil con prefijo persona fisica.
	 *
	 * @param cuilString
	 *            the cuil string
	 * @return true, if successful
	 * @throws StringIndexOutOfBoundsException
	 *             the string index out of bounds exception
	 */
	public static boolean validarCuilConPrefijoPersonaFisica(String cuilString) throws StringIndexOutOfBoundsException {
		String cuilStr = cuilString.replace(GUION_STRING, "");
		String prefijo = cuilStr.substring(0, 2);
		if (!("20".equals(prefijo) || "23".equals(prefijo) || "24".equals(prefijo) || "27".equals(prefijo)
				|| "50".equals(prefijo) || "55".equals(prefijo))) {
			return false;
		}
		return validarCuil(cuilStr);
	}

	/**
	 * Agrega guiones al cuil. Si el cuil no es valido retorna null
	 *
	 * @param cuilString
	 *            the cuil string
	 * @return cuil formateado. null si es invalido
	 */
	public static String formatearCuil(String cuilString) {
		String formateado = null;
		if (validarCuil(cuilString)) {
			formateado = cuilString.replace(GUION_STRING, EMPTY_STRING);
			formateado = new StringBuilder().append(formateado.substring(0, 2)).append(GUION_STRING)
					.append(formateado.substring(OFFSET_DNI_EN_CUIL, formateado.length() - 1)).append(GUION_STRING)
					.append(formateado.substring(formateado.length() - 1, formateado.length())).toString();
		}
		return formateado;
	}

	/**
	 * Elimina los guiones del cuil. Si el cuil no es valido retorna null
	 *
	 * @param cuilString
	 *            the cuil string
	 * @return cuil formateado. null si es invalido
	 */
	public static String eliminarGuionesDeCuil(String cuilString) {
		String formateado = null;
		if (validarCuil(cuilString)) {
			formateado = cuilString.replace(GUION_STRING, EMPTY_STRING);
		}
		return formateado;
	}

	/**
	 * Validar CBU. Valida si el formato del CBU es correcto
	 *
	 * @param cbu
	 *            the cbu
	 * @return true, if successful
	 */
	public static boolean validarCBU(String cbu) {
		if (cbu == null || cbu.equalsIgnoreCase(EMPTY_STRING) || (cbu.length() != CBU_LENGTH)
				|| !StringUtils.isNumeric(cbu)) {
			return false;
		}
		boolean cbuValido = validarCodigoBanco(cbu.substring(0, POS_FIN_CODIGO_BANCO))
				&& validarCuenta(cbu.substring(POS_INI_CUENTA, POS_FIN_CUENTA));
		if (!cbuValido) {
			LOGGER.debug("El CBU :" + cbu + " no es valido");
		}
		return cbuValido;
	}

	/**
	 * Valida si el CBU es del banco SRio.
	 *
	 * @param cbu
	 *            the cbu
	 * @return true, if successful
	 */
	public static boolean validarCBURio(String cbu) {
		if (cbu != null && cbu.length() == ISBANStringUtils.CBU_LENGTH
				&& cbu.substring(0, ISBANStringUtils.POS_FIN_ID_BANCO).equals(COD_BANCO_SRIO)) {
			return true;
		}
		return false;
	}

	/**
	 * Valida si es CVU. Debe comenzar con 000
	 *
	 * @param cbu
	 *            the cbu
	 * @return true, if successful
	 */
	public static boolean validarCVU(String cbu) {
		if (cbu != null && cbu.length() == ISBANStringUtils.CBU_LENGTH
				&& cbu.substring(0, ISBANStringUtils.POS_FIN_ID_BANCO).equals(COD_CVU)) {
			return true;
		}
		return false;
	}

	/**
	 * Valida si el CBU es de una cuenta recaudadora.
	 *
	 * @param cbu
	 *            the cbu
	 * @return true, if successful
	 */
	public static boolean validarCBUCtaRecaudadora(String cbu) {
		return cbu != null && cbu.length() == ISBANStringUtils.CBU_LENGTH
				&& cbu.substring(0, ISBANStringUtils.POS_FIN_ID_BANCO).equals(COD_BANCO_SRIO) && cbu.charAt(8) == CODIGO_CUENTA_RECAUDADORA;
	}

	/**
	 * Transforma el importe de string a big decimal.
	 *
	 * @param importe
	 *            the importe
	 * @return the big decimal
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	public static BigDecimal convertirImporte(String importe) throws ImporteConvertException {
		if (importe == null || "".equalsIgnoreCase(importe)) {
			throw new ImporteConvertException();
		}
		importe = importe.trim().replaceAll("\u00A0", "");
		BigDecimal importeDecimal = null;
		if (StringUtils.countMatches(importe, ".") == 1 && StringUtils.countMatches(importe, ",") == 0) {
			try {
				importeDecimal = new BigDecimal(importe);
			} catch (NumberFormatException e) {
				throw new ImporteConvertException(e);
			}
		} else {
			Pattern patternMoney = Pattern.compile(MONEY_PATTERN);
			Matcher matcher = patternMoney.matcher(importe);
			if (!matcher.matches()) {
				throw new ImporteConvertException();
			}
			int comaPos = importe.indexOf(',');
			String entero = "";
			String decimal = "00";
			if (comaPos > -1) {
				entero = importe.substring(0, comaPos).replace(".", "");
				decimal = importe.substring(comaPos + 1);
			} else {
				entero = importe.replace(".", "");
			}

			try {
				importeDecimal = new BigDecimal(entero + "." + decimal);
			} catch (NumberFormatException e) {
				throw new ImporteConvertException(e);
			}
		}
		return importeDecimal;
	}

	public static BigDecimal convertirImporteConNCantDeDecimales(String importe, int cantDecimales) throws ImporteConvertException {
		BigDecimal importeConvertido = convertirImporte(importe);
		return importeConvertido.setScale(cantDecimales, BigDecimal.ROUND_DOWN);
	}

	public static String convertirImporteConNCantDeDecimalesATipoString(String importe, int cantDecimales) throws ImporteConvertException {
		return String.valueOf(convertirImporteConNCantDeDecimales(importe,cantDecimales));
	}
	/**
	 * Rutina de validacion de Email.
	 *
	 * @param email
	 *            the email
	 * @return true, if successful
	 */
	public static boolean validarEmail(String email) {
		Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = patternEmail.matcher(email);
		return matcher.matches();
	}

	/**
	 * Validar codigo banco.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the boolean
	 */
	private static Boolean validarCodigoBanco(String codigo) {
		if (codigo.length() != LONGITUD_CODIGO_BANCO) {
			return false;
		}
		char[] banco = codigo.substring(0, POS_FIN_ID_BANCO).toCharArray();
		Integer digitoVerificador1 = charToInt(codigo.toCharArray()[POS_DIGITO_VERIFICADOR_BANCO]);
		char[] sucursal = codigo.substring(POS_INI_SUCURSAL, POS_FIN_SUCURSAL).toCharArray();
		Integer digitoVerificador2 = charToInt(codigo.toCharArray()[POS_DIGITO_VERIFICADOR_SUCURSAL]);
		// CHECKSTYLE:OFF: MagicNumber
		Integer suma = charToInt(banco[0]) * 7 + charToInt(banco[1]) * 1 + charToInt(banco[2]) * 3
				+ digitoVerificador1 * 9 + charToInt(sucursal[0]) * 7 + charToInt(sucursal[1]) * 1
				+ charToInt(sucursal[2]) * 3;
		Integer diferencia = (10 - (suma % 10)) % 10;
		// CHECKSTYLE:ON: MagicNumber
		return diferencia.intValue() == digitoVerificador2.intValue();
	}

	/**
	 * Extraer numero de sucursal.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the String
	 */
	public static String extraerSucursal(String codigo) {
		return codigo.substring(CUENTA_SUC_INI, CUENTA_SUC_FIN);
	}

	/**
	 * Extraer numero de cuenta.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the String
	 */
	public static String extraerCuenta(String codigo) {
		return codigo.substring(CUENTA_NRO_INI, CUENTA_NRO_FIN) + codigo.substring(CUENTA_DVR_INI, CUENTA_DVR_FIN);
	}

	/**
	 * Validar cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private static Boolean validarCuenta(String cuenta) {
		if (cuenta.length() != LONGITUD_CUENTA) {
			return false;
		}
		Integer digitoVerificador = charToInt(cuenta.toCharArray()[POS_DIGITO_VERIFICADOR_CUENTA]);
		Integer suma;
		Integer diferencia;
		char[] cuentaArray = cuenta.toCharArray();
		// CHECKSTYLE:OFF: MagicNumber
		suma = charToInt(cuentaArray[0]) * 3 + charToInt(cuentaArray[1]) * 9 + charToInt(cuentaArray[2]) * 7
				+ charToInt(cuentaArray[3]) * 1 + charToInt(cuentaArray[4]) * 3 + charToInt(cuentaArray[5]) * 9
				+ charToInt(cuentaArray[6]) * 7 + charToInt(cuentaArray[7]) * 1 + charToInt(cuentaArray[8]) * 3
				+ charToInt(cuentaArray[9]) * 9 + charToInt(cuentaArray[10]) * 7 + charToInt(cuentaArray[11]) * 1
				+ charToInt(cuentaArray[12]) * 3;
		diferencia = (10 - (suma % 10)) % 10;
		// CHECKSTYLE:ON: MagicNumber
		return diferencia.intValue() == digitoVerificador.intValue();
	}

	/**
	 * Char to int.
	 *
	 * @param ch
	 *            the ch
	 * @return the integer
	 */
	private static Integer charToInt(char ch) {
		return Integer.parseInt(String.valueOf(ch));
	}

	/**
	 * Rellena con cuatro "X" los primeros digitos de la tarjeta para armar la
	 * mascara de la misma.
	 * 
	 * @author florencia.n.martinez
	 * @param numeroTarjeta
	 *            the numero de tarjeta
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String formatearNumeroTarjeta(String numeroTarjeta, int size) {
		return StringUtils.leftPad(numeroTarjeta, size, "X");
	}

	/**
	 * Gets the longitud cuenta completa.
	 *
	 * @return the longitudCuentaCompleta
	 */
	public static int getLongitudCuentaCompleta() {
		return LONGITUD_CUENTA_COMPLETA;
	}

	/***
	 * Formateador del saldo de un BigDecimal a String pero con la salvedad de
	 * que este metodo separa del importe, los decimales (en este caso 4) del
	 * los enteros. En el caso que los decimales sean menores a cuatro se los
	 * agrega. Segundo le rellena de ceros hasta llegar a la longitud maxima que
	 * le pasas por parametro para ser enviado a iatx valorCuota * 10000 y
	 * remover decimales, luego left pad a 0
	 * 
	 * @param valorCuota
	 *            valor a parsear en BigDecimal
	 * @param cantidad
	 *            longitud maximos que debe cumplir ese parametro para ser
	 *            enviado a iatx
	 * @return Devuelve un String con el valor ,los 4 decimales y la longitud
	 *         minima para ser enviado a iatx.
	 */
	public static String formateadorSaldoPrestamo(BigDecimal valorCuota, int cantidad) {
		return StringUtils.leftPad(
				valorCuota.multiply(new BigDecimal(DIEZ_MIL)).toPlainString().split(SEPARATOR_DECIMALES)[0], cantidad,
				ZERO_STR);
	}

	/**
	 * Parses the string to big decimal.
	 *
	 * @param importe
	 *            the importe
	 * @return the big decimal
	 */
	public static BigDecimal parseStringToBigDecimal(String importe) {
		String importeString = importe.trim();
		BigDecimal importeBigDecimal;
		boolean isNegativo = false;

		if (importeString.substring(importeString.length() - 1, importeString.length()).equals(GUION_STRING)) {
			isNegativo = true;
			importeString = importeString.substring(0, importeString.length() - 1);
		} else if ("+".equals(importeString.substring(importeString.length() - 1, importeString.length()))) {
			importeString = importeString.substring(0, importeString.length() - 1);
		}
		while (importeString.substring(0, 1).equals(ZERO_STR) && importeString.length() > 1) {
			importeString = importeString.substring(1, importeString.length());
		}
		String importeConDecimal;
		if (importeString.length() > 2) {
			String entero = importeString.substring(0, importeString.length() - 2);
			String decimal = importeString.substring(importeString.length() - 2, importeString.length());
			importeConDecimal = entero + COMA_DECIMAL + decimal;
		} else {
			importeConDecimal = "0" + COMA_DECIMAL + StringUtils.leftPad(importeString, 2, ZERO_STR);
		}
		if (isNegativo) {
			importeBigDecimal = new BigDecimal(importeConDecimal).negate();
		} else {
			importeBigDecimal = new BigDecimal(importeConDecimal);
		}
		return importeBigDecimal;
	}

	/**
	 * Convierte un String a BigDecimal. Tiene en cuenta cantidad de Enteros y
	 * cantidad de Decimales Tiene en cuenta si el Stirng viene con singo.
	 *
	 * @param value
	 *            the value
	 * @param cantEnteros
	 *            the cant enteros
	 * @param cantDecimales
	 *            the cant decimales
	 * @param tieneSigno
	 *            the tiene signo
	 * @return the big decimal
	 */
	public static BigDecimal stringToBigDecimal(String value, int cantEnteros, int cantDecimales, boolean tieneSigno) {

		String newValue = value;
		Boolean isNegativo = false;
		BigDecimal newImporte = null;

		if (tieneSigno) {
			newValue = value.substring(0, value.length() - 1);
			if (value.endsWith(GUION_STRING)) {
				isNegativo = true;
			}
		}
		try {
			int cantTotal = cantEnteros + cantDecimales;
			if (newValue.length() == cantTotal) {
				String valueFormated = newValue.substring(0, cantEnteros) + "."
						+ newValue.substring(cantEnteros, cantTotal);
				newImporte = ISBANStringUtils.convertirImporte(valueFormated);
				if (isNegativo) {
					newImporte = newImporte.negate();
				}
			}
		} catch (ImporteConvertException e) {
			LOGGER.info(e.getMessage(), e);
			return null;
		}
		return newImporte;
	}

	/**
	 * * Function que convierte de un String to BigDecimal con la cantidad de
	 * decimales que debe venir de iatx.
	 *
	 * @param imp
	 *            the imp
	 * @param cantDecimales
	 *            the cant decimales
	 * @return the big decimal
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	public static BigDecimal convertirStrToBigDecimal(String imp, int cantDecimales) throws ImporteConvertException {
		String importe = imp;

		if (importe == null || "".equalsIgnoreCase(importe)) {
			throw new ImporteConvertException();
		}
		String signo = "";
		if (importe.endsWith("+") || importe.endsWith("-")) {
			signo = importe.substring(importe.length() - 1);
			importe = importe.substring(0, importe.length() - 1);
		}

		BigDecimal importeDecimal = null;
		StringBuffer result = new StringBuffer();
		for (int i = importe.length(); i > 0; i--) {
			result.append(importe.charAt(i - 1));
			if (result.length() == cantDecimales) {
				result = result.append(".");
			}
		}

		result.append(signo);

		try {
			importeDecimal = new BigDecimal(StringUtils.reverse(result.toString()));
		} catch (NumberFormatException e) {
			throw new ImporteConvertException(e);
		}
		return importeDecimal;
	}

	/**
	 * Convertir str to big decimal sin exception.
	 *
	 * @param imp
	 *            the imp
	 * @param cantDecimales
	 *            the cant decimales
	 * @return the big decimal
	 */
	public static BigDecimal convertirStrToBigDecimalSinException(String imp, int cantDecimales) {
		try {
			return convertirStrToBigDecimal(imp, cantDecimales);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error parseando el importe:" + e);
			return null;
		}
	}

	/**
	 * Agregar barra numero prestamo.
	 *
	 * @param numeroPrestamo
	 *            the numero prestamo
	 * @return the string
	 */
	public static String agregarBarraNumeroPrestamo(String numeroPrestamo) {
		if (numeroPrestamo != null) {
			return numeroPrestamo.substring(0, numeroPrestamo.length() - 1) + "/"
					+ numeroPrestamo.substring(numeroPrestamo.length() - 1, numeroPrestamo.length());
		}
		return null;
	}

	/**
	 * Formatear nro prestamo.
	 *
	 * @param sucursal
	 *            the sucursal
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the string
	 */
	public static String formatearNroPrestamo(String sucursal, String nroCuenta) {
		String sucursalFormateada = formatearSucursal(sucursal);
		String numeroCuentaFormateada = agregarBarraNumeroPrestamo(formateadorConCerosIzq(nroCuenta, 12));

		return sucursalFormateada + GUION_STRING + numeroCuentaFormateada;
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
		return WordUtils.capitalizeFully(string);
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
		return StringUtils.lowerCase(string);
	}

	/**
	 * Parsea un string en formato "dd/MM/yyyy y retorna un objeto date que
	 * representa la fecha.
	 *
	 * @param date
	 *            the date
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Date parseFecha(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		return sdf.parse(date);
	}

	/**
	 * Formatea un String dado para que la primera letra de cada palabra quede
	 * con mayuscula. EJ: STRING DE PRUEBA / String De Prueba EJ 2: otro string
	 * de prueba / Otro String De Prueba
	 *
	 * @author mariano.g.pulera
	 * @param string
	 *            the string
	 * @return string
	 */
	public static String formateoStringPrimeraLetraMayuscula(String string) {
		String input = string;
		String result = "";
		char firstChar = input.charAt(0);
		result = result + Character.toUpperCase(firstChar);
		for (int i = 1; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			char previousChar = input.charAt(i - 1);
			if (previousChar == ' ') {
				result = result + Character.toUpperCase(currentChar);
			} else {
				result = result + Character.toLowerCase(currentChar);
			}
		}
		return result;
	}

	/**
	 * Formatea un importe que viene con muchos ceros adelante y signo detras
	 * EJ: 000000011265717+ // 112.657,17
	 *
	 * @author mariano.g.pulera
	 * @param monto
	 *            the monto
	 * @return the string
	 */
	public static String formatearSaldosConCerosYSignos(String monto) {
		if (monto == null) {
			return StringUtils.EMPTY;
		}
		String montoModificado = monto;
		montoModificado = monto.replaceAll("\\+", "");
		montoModificado = montoModificado.replaceAll("\\-", "");
		montoModificado = montoModificado.replaceAll("\\.", "");
		montoModificado = ISBANStringUtils.eliminarCeros(montoModificado);
		montoModificado = StringUtils.leftPad(montoModificado, 3, "0");
		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 2, ",").toString();

		for (int i = 6; i < montoModificado.length(); i = i + 3) {
			montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - i, ".").toString();
			i++;
		}
		return montoModificado;
	}

	/**
	 * Formatea un importe que viene con muchos ceros adelante y mantiene el
	 * signo EJ: -1000 // -1.000,00
	 *
	 * @param monto
	 *            the monto
	 * @return the string
	 */
	public static String formatearSaldosConCerosYSignos2(String monto) {
		if (monto == null) {
			return StringUtils.EMPTY;
		}
		String montoModificado = monto;
		montoModificado = monto.replaceAll("\\+", "");
		char c = montoModificado.charAt(0);
		montoModificado = monto.replaceAll("\\-", "");
		montoModificado = montoModificado.replaceAll("\\.", "");
		montoModificado = ISBANStringUtils.eliminarCeros(montoModificado);
		montoModificado = StringUtils.leftPad(montoModificado, 3, "0");
		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 2, ",").toString();
		for (int i = 6; i < montoModificado.length(); i = i + 3) {
			montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - i, ".").toString();
			i++;
		}
		montoModificado = c == '-' ? new StringBuilder(montoModificado).insert(0, c).toString() : montoModificado;
		return montoModificado;
	}

	public static String formatearSaldosConCerosYSignos(String monto, int cantidadDecimales) {
		if (monto == null) {
			return StringUtils.EMPTY;
		}
		String montoModificado = monto;
		montoModificado = monto.replaceAll("\\+", "");
		montoModificado = montoModificado.replaceAll("\\-", "");
		montoModificado = montoModificado.replaceAll("\\.", "");
		montoModificado = ISBANStringUtils.eliminarCeros(montoModificado);
		montoModificado = StringUtils.leftPad(montoModificado, 3, "0");
		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - cantidadDecimales, ",")
				.toString();

		for (int i = 4 + cantidadDecimales; i < montoModificado.length(); i = i + 3) {
			montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - i, ".").toString();
			i++;
		}
		return montoModificado;
	}

	public static String formatearSaldosConCerosYSignos2(String monto, int cantidadDecimales) {
		if (monto == null) {
			return StringUtils.EMPTY;
		}
		String montoModificado = monto;
		montoModificado = monto.replaceAll("\\+", "");
		char c = montoModificado.charAt(0);
		montoModificado = montoModificado.replaceAll("\\-", "");
		montoModificado = montoModificado.replaceAll("\\.", "");
		montoModificado = ISBANStringUtils.eliminarCeros(montoModificado);
		montoModificado = StringUtils.leftPad(montoModificado, 3, "0");
		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - cantidadDecimales, ",")
				.toString();

		for (int i = 4 + cantidadDecimales; i < montoModificado.length(); i = i + 3) {
			montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - i, ".").toString();
			i++;
		}
		montoModificado = c == '-' ? new StringBuilder(montoModificado).insert(0, c).toString() : montoModificado;
		return montoModificado;
	}

	/**
	 * Formatea un valor y lo prepara para usarse en un servicio. EJ: 250.00 //
	 * 000000000025000
	 *
	 * @author mariano.g.pulera
	 * @param valor
	 *            the valor
	 * @param cantidadRelleno
	 *            the cantidad
	 * @return the string
	 */
	public static String ajustadorBigDecimalIatx(BigDecimal valor, int cantidadRelleno) {
		if (valor == null) {
			return StringUtils.EMPTY;
		}
		int cantidadDecimales = 2;

		String[] enteroDecimal = StringUtils.split(valor.toString(), "\\.");
		String decimalConDosDigitos = enteroDecimal.length == 2
				? StringUtils.left(StringUtils.rightPad(enteroDecimal[1], cantidadDecimales, "0"), cantidadDecimales)
				: "00";

		return StringUtils.leftPad(enteroDecimal[0] + decimalConDosDigitos, cantidadRelleno, "0");
	}
	
	
	/**
	 * Formatea un valor y lo prepara para usarse en un servicio con la cantidad de decimales necesaria. 
	 * EJ: 250.00 en valor, 14 en cantidadRelleno y 4 en cantidadDecimales // 
	 * 00000002500000
	 *
	 * @param valor
	 * @param cantidadRelleno
	 * @param cantidadDecimales
	 * @return string
	 */
	public static String ajustadorBigDecimalIatx(BigDecimal valor, int cantidadRelleno, int cantidadDecimales) {
		if (valor == null) {
			return StringUtils.EMPTY;
		}
		String[] enteroDecimal = StringUtils.split(valor.toString(), "\\.");
		String decimalConDosDigitos = enteroDecimal.length == 2
				? StringUtils.left(StringUtils.rightPad(enteroDecimal[1], cantidadDecimales, "0"), cantidadDecimales)
				: StringUtils.leftPad("", cantidadDecimales, "0");

		return StringUtils.leftPad(enteroDecimal[0] + decimalConDosDigitos, cantidadRelleno, "0");
	}

	/**
	 * Formatea un valor y lo prepara para usarse en un servicio. EJ: 250.00 //
	 * 000000000025000
	 *
	 * @author mariano.g.pulera
	 * @param valor
	 *            the valor
	 * @param cantidad
	 *            the cantidad
	 * @return the string
	 */
	public static String ajustadorDoubleIatx(Double valor, int cantidad) {
		String valorString = valor.toString();
		valorString = valorString.replaceAll("\\.", "");
		valorString = StringUtils.leftPad(valorString, cantidad, "0");
		return valorString;
	}

	/**
	 * Adds the string.
	 *
	 * @param string1
	 *            the string 1
	 * @param string2
	 *            the string 2
	 * @return the string
	 */
	public static String addString(String string1, String string2) {
		return string1 + string2;
	}

	/**
	 * Revisa que un importe ingresado tenga los dos decimales. Si tiene los
	 * dos, no hace nada. Si tiene uno, agrega un 0 al final. Si no tiene
	 * ninguno, agrega ",00" (la coma con dos ceros) Si algun salame agrego solo
	 * la coma, agrega los dos ceros
	 * 
	 * EJ: 1.500 // 1.500,00 1.500,2 // 1.500,20 1.500,34 // 1.500,34 1.500, //
	 * 1.500,00
	 *
	 * @author mariano.g.pulera
	 * @param importe
	 *            the importe
	 * @return the string
	 */
	public static String agregadorDecimales(String importe) {

		String[] decimales = importe.split(",");
		if (decimales.length == 2) {
			if (decimales[1].length() == 1) {
				importe = importe + "0";
			}
		}

		if (decimales.length == 1 && importe.contains(",")) {
			importe = importe + "00";
		} else if (decimales.length == 1) {
			importe = importe + ",00";
		}
		return importe;

	}
	
	
	/**
	 * @param importe
	 * @return
	 */
	public static String agregadorDecimalesConPunto(String importe) {

		String[] decimales = importe.split("\\.");
		if (decimales.length == 2) {
			if (decimales[1].length() == 1) {
				importe = importe + "0";
			}
		}

		if (decimales.length == 1 && importe.contains(".")) {
			importe = importe + "00";
		} else if (decimales.length == 1) {
			importe = importe + ".00";
		}
		return importe;

	}

	/**
	 * Revisa que un importe ingresado tenga los dos decimales. Si tiene mas de dos, solo tome los dos primeros.
	 * Si tiene los dos, no hace nada. 
	 * Si tiene uno, agrega un 0 al final. 
	 * Si no tiene ninguno, agrega ",00" (la coma con dos ceros) 
	 * Si viene solo la coma, agrega los dos ceros
	 * 
	 * EJ: 1.500 // 1.500,00 1.500,2 // 1.500,20 1.500,34 // 1.500,34 1.500, //
	 * 1.500,00
	 *
	 * @author mariano.g.pulera
	 * @param importe
	 *            the importe
	 * @return the string
	 */
	public static String agregadorDecimalesConservaOrigen(String importe) {

		String[] decimales = importe.split(",");
		if (decimales.length == 2) {
			if(decimales[1].length() > 2) {
				importe = decimales[0] + "," + decimales[1].substring(0,2);
			} else if (decimales[1].length() == 1) {
				importe = importe + "0";
			}
		}

		if (decimales.length == 1 && importe.contains(",")) {
			importe = importe + "00";
		} else if (decimales.length == 1) {
			importe = importe + ",00";
		}
		return importe;

	}
	
	/** 
	 * Igual que el anterior pero lleva a 4 la cantidad de decimales
	 * 
	 */
	
	public static String agregadorDecimalesConservaOrigen4(String importe) {

		String[] decimales = importe.split(",");
		if (decimales.length == 2) {
			if(decimales[1].length() > 4) {
				importe = decimales[0] + "," + decimales[1].substring(0,4);
			} else if (decimales[1].length() == 1) {
				importe = importe + "000";
			}
		}

		if (decimales.length == 1 && importe.contains(",")) {
			importe = importe + "0000";
		} else if (decimales.length == 1) {
			importe = importe + ",0000";
		}
		return importe;

	}

	/**
	 * Normaliza un campo Alfanumerico a un tamaÃ±o dado (trunca o completa con
	 * espacios a la derecha).
	 *
	 * @param campo
	 *            the campo
	 * @param tamanio
	 *            the tamanio
	 * @return the string
	 * @author: b039542 - ignacio_valek@itrsa.com.ar - 20/09/2016
	 */
	public static String normalizarCampoAlfanumericoIatx(String campo, int tamanio) {

		if (tamanio < 0) {
			return campo;
		}
		if (campo == null) {
			campo = EMPTY_STRING;
		}
		if (campo.length() == tamanio) {
			return campo;
		}
		if (campo.length() < tamanio) {
			campo = StringUtils.rightPad(campo, tamanio);
		} else {
			campo = StringUtils.left(campo, tamanio);
		}

		return campo;
	}

	/**
	 * Agrega el punto divisor al monto
	 * 
	 * EJ: 1500,00 // 1.500,00
	 *
	 * @author mariano.g.pulera
	 * @param importe
	 *            the importe
	 * @return the string
	 */

	public static String agregadorPuntoDivisor(String importe) {

		for (int i = 6; i < importe.length(); i = i + 3) {
			importe = new StringBuilder(importe).insert(importe.length() - i, ".").toString();
			i++;
		}

		return importe;
	}

	/**
	 * Agrega el punto divisor al monto, pero cuando no se deben considerar
	 * decimales
	 * 
	 * EJ: 1500 // 1.500
	 *
	 * @author mariano.g.pulera
	 * @param importe
	 *            the importe
	 * @return the string
	 */
	public static String agregadorPuntoDivisorSinDecimales(String importe) {

		for (int i = 3; i < importe.length(); i = i + 3) {
			importe = new StringBuilder(importe).insert(importe.length() - i, ".").toString();
			i++;
		}

		return importe;
	}

	/**
	 * Formatear fecha descarga.
	 *
	 * @param fechaOriginal
	 *            the fecha original
	 * @return the string
	 */
	public static String formatearFechaDescarga(String fechaOriginal) {
		String fechaFormateada = "";
		String[] cadenaFecha = null;
		if (fechaOriginal != null && fechaOriginal.length() == 8) {
			char caracter = fechaOriginal.charAt(2);
			if (("/").equals(String.valueOf(caracter))) {
				cadenaFecha = fechaOriginal.split("/");
			}
			if (("-").equals(String.valueOf(caracter))) {
				cadenaFecha = fechaOriginal.split("-");
			}
			fechaFormateada = cadenaFecha[0] + "-" + cadenaFecha[1] + "-" + COMIENZO_FECHA + cadenaFecha[2];
		}
		return fechaFormateada;
	}

	/**
	 * Formatear saldo string a big decimal.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the big decimal
	 */
	public static BigDecimal formatearSaldoString(String saldo) {

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
	 * Formatear saldo string a big decimal.
	 *
	 * @param importeIATX
	 *            the importe IATX
	 * @return the big decimal
	 */
	public static String formatearSaldoTasaString(String importeIATX) {
		int cantDecimales = 6;
		importeIATX = importeIATX.trim();
		int l = importeIATX.length();
		if (l <= cantDecimales)
			return StringUtils.EMPTY;

		boolean isNegativo = importeIATX.charAt(l - 1) == '-';
		boolean tieneSigno = importeIATX.charAt(l - 1) == '+';
		if (isNegativo || tieneSigno)
			importeIATX = importeIATX.substring(0, --l);
		String entera = sacarCerosYBlancosIzq(importeIATX.substring(0, l - cantDecimales));
		if (entera.length() == 0)
			entera = "0";
		String decimal = importeIATX.substring(l - cantDecimales, l);
		return (isNegativo ? "-" : "") + entera + "." + decimal;

	}

	/**
	 * Formatear numero cuenta con sucursal.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public static String formatearNroCuentaProductoConSucursal(Cuenta cuenta) {
		if (cuenta == null) {
			return StringUtils.EMPTY;
		}
		String sucursal = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
		String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());

		return new StringBuilder().append(sucursal).append("-").append(numeroCuenta).toString();
	}

	/**
	 * Recibe el nro cuenta y la sucursal por separado y devuelve el formato
	 * completo cuenta: 000012345678, sucursal: 000240 resultado: 240-1234567/8.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param sucursal
	 *            the sucursal
	 * @return the string
	 */
	public static String formatearNroCuentaProductoConSucursal(String cuenta, String sucursal) {
		if (cuenta == null) {
			return StringUtils.EMPTY;
		}
		String sucursalFormateada = ISBANStringUtils.formatearSucursal(sucursal);
		String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta);

		return new StringBuilder().append(sucursalFormateada).append("-").append(numeroCuenta).toString();
	}

	
	/**
	 * Formatea numero de sucursal con cuatro ceros adelante
	 * @param cuenta
	 * @param sucursal
	 * @return
	 */
	public static String formatearNroCuentaProductoConSucursalMaps(String cuenta, String sucursal) {
		if (cuenta == null) {
			return StringUtils.EMPTY;
		}
		String sucursalFormateada = ISBANStringUtils.formatearSucursalMaps(sucursal);
		String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta);

		return new StringBuilder().append(sucursalFormateada).append("-").append(numeroCuenta).toString();
	}
		
	
	/**
	 * Recibe String con fecha formato DD/MM/YYYY y devuelve fecha en formato
	 * DD/MM/YY.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	public static String convertirFechaAFormatoMobile(String fecha) {
		String anioDosDigitos = StringUtils.right(fecha, 2);
		return new StringBuilder().append(StringUtils.left(fecha, 6)).append(anioDosDigitos).toString();
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
					throw new ISBANRuntimeException(IMPORTE_INVALIDO + importeIATX);
				}
				boolean isNegativo = importeIATX.charAt(l - ENTERO_UNO) == '-';
				boolean tieneSigno = importeIATX.charAt(l - ENTERO_UNO) == '+';
				if (isNegativo || tieneSigno) {
					importeIATX = importeIATX.substring(ENTERO_CERO, --l);
				}
				String e1 = sacarCerosYBlancosIzq(importeIATX.substring(0, l - cantDecimales));
				String e2 = sacarPuntos(e1);
				String entera = sacarComa(e2);
				if (entera.length() == ENTERO_CERO) {
					entera = ZERO_STR;
				}
				String decimal = importeIATX.substring(l - cantDecimales, l);
				return (isNegativo ? GUION_STRING : EMPTY_STRING) + entera + decimal;
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
		return s.replace(COMA_STRING, COMA_DECIMAL);
	}

	/**
	 * Sacar puntos.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	private static String sacarPuntos(String s) {
		return s.replace(COMA_DECIMAL, EMPTY_STRING);
	}

	/**
	 * Repeat.
	 *
	 * @param str
	 *            the str
	 * @param times
	 *            the times
	 * @return the string
	 */
	public static String repeat(String str, int times) {
		return new String(new char[times]).replace("\0", str);
	}

	/**
	 * Sacar ceros Y blancos izq.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public static String sacarCerosYBlancosIzq(String s) {
		int n;
		for (n = 0; n < s.length(); ++n) {
			if (s.charAt(n) != '0' && s.charAt(n) != ' ') {
				break;
			}
		}
		return s.substring(n, s.length());
	}

	/**
	 * Formatea un saldo a 4 decimales, por ej 999999 --> 99.9999 99999999 -->
	 * 9,999.9999
	 *
	 * @param cuotaPartes
	 *            the cuota partes
	 * @return the string
	 */
	public static String formatearSaldoCuatroDecimales(String cuotaPartes) {
		BigDecimal a = BigDecimal.valueOf(Long.valueOf(cuotaPartes));
		BigDecimal divisor = BigDecimal.valueOf(Long.valueOf(10000));
		BigDecimal b = a.divide(divisor);

		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');

		DecimalFormat df = new DecimalFormat("###,###,###.####", otherSymbols);
		return df.format(b);
	}

	/**
	 * Formatea el importe para pasarlo al servicio RESFCI_160 Param 99900
	 * return 00000009990000 Param 999.5 return 00000000099950 Param 999.25
	 * return 00000000099925
	 *
	 * @param importe
	 *            the importe
	 * @return importeFormateado
	 */
	public static String formatearImporteRescate(String importe) {
		String importeFormateado = null;
		if (importe.contains(".")) {
			int posComa = importe.indexOf('.', 0);
			int longImp = importe.length() - 1;
			int dif = longImp - posComa;
			if ((dif) == 1) {
				importeFormateado = StringUtils.replaceChars(importe, ".", "");
				importeFormateado = importeFormateado + "0";
			} else if ((dif) == 2) {
				importeFormateado = StringUtils.replaceChars(importe, ".", "");
			}
			return StringUtils.leftPad(importeFormateado, LARGO_IMPORTES, "0");
		}
		importeFormateado = importe + "00";
		return StringUtils.leftPad(importeFormateado, LARGO_IMPORTES, "0");
	}

	/**
	 * Si definicion de servicio dice N8(5,3) entonces seria: largo total: 8,
	 * decimales 3. Params: 1275.5, 8, 3 Return: 01275500
	 *
	 * @param numeroAformatear
	 *            the numero aformatear
	 * @param largoTotal
	 *            the largo total
	 * @param decimales
	 *            the decimales
	 * @return the string
	 */
	public static String formatearNumericosParaIatxSeparadoPorPunto(String numeroAformatear, int largoTotal,
			int decimales) {
		String importeFormateado = null;
		int decimalesRecibidos = 0;
		if (numeroAformatear.contains(".")) {
			int posComa = numeroAformatear.indexOf('.', 0);
			int longTotal = numeroAformatear.length() - 1;
			decimalesRecibidos = longTotal - posComa;
		}
		int relleno = decimales - decimalesRecibidos;
		if (relleno < 0) {
			numeroAformatear = StringUtils.substring(numeroAformatear, 0, numeroAformatear.length() + relleno);
		}
		importeFormateado = StringUtils.replaceChars(numeroAformatear, ".", "");
		while (relleno > 0) {
			importeFormateado = importeFormateado + "0";
			;
			relleno--;
		}
		importeFormateado = StringUtils.right(importeFormateado, largoTotal);
		return StringUtils.leftPad(importeFormateado, largoTotal, "0");
	}

	/**
	 * Obtiene el numero de cuenta a partir de un cbu.
	 *
	 * @param cbu
	 *            the cbu
	 * @return the string
	 */
	public static String obtenerNumeroCuentaDesdeCBU(String cbu) {
		String sucursal = StringUtils.substring(cbu, 4, 7);
		String numeroCuenta = StringUtils.substring(cbu, 14, 21);
		IdentificacionCuenta idCuenta = new IdentificacionCuenta(sucursal, numeroCuenta);
		return idCuenta.toString();
	}

	/**
	 * Obtiene una cuenta a partir de un cbu.
	 *
	 * @param cbu
	 *            the cbu
	 * @return the string
	 */
	public static IdentificacionCuenta obtenerCuentaDesdeCBU(String cbu) {
		String sucursal = StringUtils.substring(cbu, 4, 7);
		String numeroCuenta = StringUtils.substring(cbu, 14, 21);
		IdentificacionCuenta idCuenta = new IdentificacionCuenta(sucursal, numeroCuenta);
		return idCuenta;
	}

	/**
	 * Formatea una frase para que solo la inicial de la primera palabra este en
	 * mayuscula
	 * 
	 * EJ : ARTICULOS DEL HOGAR --> Articulos del hogar.
	 *
	 * @author mariano.g.pulera
	 * @param frase
	 *            the frase
	 * @return the string
	 */
	public static String formatearFraseInicialMayuscula(String frase) {

		String inicial = frase.substring(0, 1);
		String restoFrase = frase.substring(1, frase.length());

		return inicial + restoFrase.toLowerCase(Locale.getDefault());
	}

	/**
	 * Convierte un string a big decimal teniendo en cuenta las comas y los
	 * separadores de mil 15.000,55 => 15000.55
	 *
	 * @param importeSinFormatear
	 *            the importe sin formatear
	 * @return the big decimal
	 */
	public static BigDecimal convertirABigDecimal(String importeSinFormatear) {
		importeSinFormatear = importeSinFormatear.replaceAll("\\.", "");
		importeSinFormatear = importeSinFormatear.replace(",", ".");
		return new BigDecimal(importeSinFormatear);
	}

	/**
	 * Param 1000 return 1.000,00 Param 1000.1 return 1.000,10 Param 1000.10
	 * return 1.000,10 Param 1000.101 return 1.000,10
	 *
	 * @param saldoAFormatear
	 *            the saldo A formatear
	 * @return saldoFormateado
	 */
	public static String formatearConComaYDosDecimales(String saldoAFormatear) {
		if (saldoAFormatear.contains(".")) {
			String[] saldoSeparado = saldoAFormatear.split("\\.");
			saldoSeparado[1] = StringUtils.rightPad(saldoSeparado[1], LONGITUD_DECIMALES, "0");
			saldoSeparado[1] = StringUtils.left(saldoSeparado[1], LONGITUD_DECIMALES);
			saldoAFormatear = saldoSeparado[0].concat(saldoSeparado[1]);
			return ISBANStringUtils.formatearSaldosConCerosYSignos(saldoAFormatear.replace(".", ""));
		} else {
			return ISBANStringUtils.formatearSaldosConCerosYSignos(saldoAFormatear.concat("00"));
		}
	}

	/**
	 * 
	 * Formatea con coma y dos decimales llamando al metodo que mantiene el
	 * signo
	 * 
	 * Param 1000 return 1.000,00 Param 1000.1 return 1.000,10 Param 1000.10
	 * return 1.000,10 Param 1000.101 return 1.000,10
	 *
	 * @param saldoAFormatear
	 *            the saldo A formatear
	 * @return saldoFormateado
	 */
	public static String formatearConComaYDosDecimales2(String saldoAFormatear) {
		if (saldoAFormatear.contains(".")) {
			String[] saldoSeparado = saldoAFormatear.split("\\.");
			saldoSeparado[1] = StringUtils.rightPad(saldoSeparado[1], LONGITUD_DECIMALES, "0");
			saldoSeparado[1] = StringUtils.left(saldoSeparado[1], LONGITUD_DECIMALES);
			saldoAFormatear = saldoSeparado[0].concat(saldoSeparado[1]);
			return ISBANStringUtils.formatearSaldosConCerosYSignos2(saldoAFormatear.replace(".", ""));
		} else {
			return ISBANStringUtils.formatearSaldosConCerosYSignos2(saldoAFormatear.concat("00"));
		}
	}

	/**
	 * Lo mismo de arriba, pero le pasamos la cantidad de decimales que queremos
	 * por parametro
	 *
	 * @param saldoAFormatear
	 *            the saldo A formatear
	 * @return saldoFormateado
	 */

	public static String formatearConComaYVariosDecimales(String saldoAFormatear, int cantidadDecimales) {
		if (saldoAFormatear.contains(".")) {
			String[] saldoSeparado = saldoAFormatear.split("\\.");
			saldoSeparado[1] = StringUtils.rightPad(saldoSeparado[1], cantidadDecimales, "0");
			saldoSeparado[1] = StringUtils.left(saldoSeparado[1], cantidadDecimales);
			saldoAFormatear = saldoSeparado[0].concat(saldoSeparado[1]);
			return ISBANStringUtils.formatearSaldosConCerosYSignos(saldoAFormatear.replace(".", ""), cantidadDecimales);
		} else {
			return ISBANStringUtils.formatearSaldosConCerosYSignos(saldoAFormatear.concat("00"));
		}
	}

	/**
	 * Si el valor es un entero, lo formatea sin decimales, en caso contrario formatea con 2 decimales
	 *
	 * @param saldoAFormatear
	 *            the saldo A formatear
	 * @return saldoFormateado
	 */
	public static String formatearValorEnteroYDecimales(double valor, int decimales) {
		if(valor % 1 == 0.0) {
			return ISBANStringUtils.formatearConComaYVariosDecimales2(String.valueOf(valor), 0);
		}
		return ISBANStringUtils.formatearConComaYVariosDecimalesBis(String.valueOf(valor), decimales);
	}
	
	/**
	 * Formatea el numero con la cantidad de decimales recibidas, si el monto es nulo o vacio lo deja como esta.
	 *
	 * @param nroPorFormatear
	 *            the saldo A formatear
	 * @return nroFormateado
	 */
	public static String formatearConComaYVariosDecimales2(String nroPorFormatear, int cantidadDecimales) {
		if(StringUtils.isNotBlank(nroPorFormatear)&& NumberUtils.isNumber(nroPorFormatear)) {
	    	BigDecimal nroBigDecimal = NumberUtils.createBigDecimal(nroPorFormatear);
	    	NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es", "AR"));
	    	nf.setMinimumFractionDigits(cantidadDecimales);
	    	nf.setMaximumFractionDigits(cantidadDecimales);
	    	return nf.format(nroBigDecimal);
		}
		return nroPorFormatear;
	}

	/**
	 * Fill num.
	 *
	 * @param s
	 *            the s
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String fillNum(String s, int size) {
		int l = s.length();
		if (l >= size) {
			return s.substring(l - size, l);
		}
		StringBuilder ceros = new StringBuilder();
		for (int n = 0; n < size - l; ++n) {
			ceros.append("0");
		}
		return ceros + s;
	}

	/**
	 * Llena con ceros.
	 *
	 * @param size
	 *            the size
	 * @return the string
	 */
	static public String llenaConCeros(int size) {
		StringBuilder buf = new StringBuilder();

		for (int i = 0; i < size; i++) {
			buf.append('0');
		}

		return buf.toString();
	}

	/**
	 * Fill str.
	 *
	 * @param s
	 *            the s
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String fillStr(String s, int size) {
		s = StringUtils.defaultString(s);
		int l = s.length();
		if (l >= size) {
			return s.substring(l - size, l);
		}
		StringBuilder blancos = new StringBuilder();
		for (int n = 0; n < size - l; ++n) {
			blancos.append(" ");
		}
		return s + blancos;
	}

	/**
	 * Traer id combo dni.
	 *
	 * @param dni
	 *            the dni
	 * @return the string
	 */
	public static String traerIdComboDni(String dni) {
		String tipoDoc = "";
		if ("DNI".equals(dni)) {
			tipoDoc = "N ";
		} else if ("CUIT".equals(dni)) {
			tipoDoc = "T ";
		} else if ("EXT".equals(dni)) {
			tipoDoc = "X ";
		} else if ("PAS".equals(dni)) {
			tipoDoc = "P ";
		} else if ("CI".equals(dni)) {
			tipoDoc = "I ";
		} else if ("LC".equals(dni)) {
			tipoDoc = "C ";
		} else if ("LE".equals(dni)) {
			tipoDoc = "E ";
		}
		return tipoDoc;
	}

	/**
	 * Date without time.
	 *
	 * @param formatter
	 *            the formatter
	 * @return the date
	 */
	public static Date dateWithoutTime(String formatter) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatter);
		Date today = null;
		try {
			today = dateFormat.parse(dateFormat.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return today;
	}

	/**
	 * Formatear fecha ddMMyyyy a dd/MM/yyyy.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatearFechaConSeparadores(String date) {
		String dateConFormato = null;
		if (date != null && !date.isEmpty() && date.length() == 8) {
			String dia = (String) date.subSequence(0, 2);
			String mes = (String) date.subSequence(2, 4);
			String anio = (String) date.subSequence(4, 8);
			dateConFormato = dia + "/" + mes + "/" + anio;
		}
		return dateConFormato;
	}

	/**
	 * Cobertura canonico.
	 *
	 * @param coberturaIatx
	 *            the cobertura iatx
	 * @return the string
	 */
	public static String coberturaCanonico(String coberturaIatx) {
		if ("S".equals(coberturaIatx)) {
			return "1";
		} else if ("N".equals(coberturaIatx)) {
			return "0";
		} else {
			return "";
		}
	}

	/**
	 * Posicionamiento canonico.
	 *
	 * @param posicionamientoIatx
	 *            the posicionamiento iatx
	 * @return the string
	 */
	public static String posicionamientoCanonico(String posicionamientoIatx) {
		int pos = Integer.parseInt(posicionamientoIatx);
		switch (pos) {
		case 0:
		case 1:
		case 2:
			return String.valueOf(pos);
		default:
			return "";
		}

	}

	/**
	 * Diferencia en dias entre dos días.
	 *
	 * @param fechaFinal
	 *            the fecha final
	 * @param fechaInic
	 *            the fecha inic
	 * @return the int
	 */
	public static int diferenciaEnDias(Date fechaFinal, Date fechaInic) {
		GregorianCalendar fechaInicio = new GregorianCalendar();
		fechaInicio.setTime(fechaInic);
		GregorianCalendar fechaFin = new GregorianCalendar();
		fechaFin.setTime(fechaFinal);
		int dias = 0;
		if (fechaFin.get(Calendar.YEAR) == fechaInicio.get(Calendar.YEAR)) {
			dias = (fechaFin.get(Calendar.DAY_OF_YEAR) - fechaInicio.get(Calendar.DAY_OF_YEAR));
		} else {
			int rangoAnyos = fechaFin.get(Calendar.YEAR) - fechaInicio.get(Calendar.YEAR);
			for (int i = 0; i <= rangoAnyos; i++) {
				int diasAnio = fechaInicio.isLeapYear(fechaInicio.get(Calendar.YEAR)) ? 366 : 365;
				if (i == 0) {
					dias = 1 + dias + (diasAnio - fechaInicio.get(Calendar.DAY_OF_YEAR));
				} else if (i == rangoAnyos) {
					dias = dias + fechaFin.get(Calendar.DAY_OF_YEAR);
				} else {
					dias = dias + diasAnio;
				}
			}
		}
		return dias;
	}

	/**
	 * getTime() Returns the number of milliseconds since January 1, 1970,
	 * 00:00:00 GMT represented by this Date object.
	 * http://download.oracle.com/javase/1.4.2/docs/api/java/util/Date.html#
	 * getTime() Ciendo Mayo fecha2
	 *
	 * @param fecha2
	 *            the fecha 2
	 * @param fecha1
	 *            the fecha 1
	 * @return the long
	 */
	public static long difDiasEntre2fechas(Date fecha2, Date fecha1) {

		long difms = fecha2.getTime() - fecha1.getTime();
		long difd = difms / (1000 * 60 * 60 * 24);

		return difd;
	}

	/**
	 * Cargar cuenta destinatario.
	 *
	 * @param nroCuentaDestino
	 *            the nro cuenta destino
	 * @return the identificacion cuenta
	 */
	public static IdentificacionCuenta cargarCuentaDestinatario(String nroCuentaDestino) {
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		identificacionCuenta.setNroSucursal(ISBANStringUtils.extraerSucursal(nroCuentaDestino));
		identificacionCuenta.setNroCuentaProducto(ISBANStringUtils.extraerCuenta(nroCuentaDestino));
		return identificacionCuenta;
	}

	/**
	 * Validar formato alias. controla los caracteres permitidos del Alias.
	 *
	 * @param aliasTransferenciaView
	 *            the alias transferencia view
	 * @return true if is syntactically correct.
	 */
	public static boolean isFormatoAliasValido(String aliasTransferenciaView) {
		Pattern patron = Pattern.compile(ALIAS_REGEX);
		Matcher emparejador = patron.matcher(aliasTransferenciaView.trim());
		return emparejador.matches();
	}

	/**
	 * Obtener porcentaje.
	 *
	 * @param valorEvaluado
	 *            the valor evaluado
	 * @param valorTotal
	 *            the valor total
	 * @return the big decimal
	 */
	public static BigDecimal obtenerPorcentaje(BigDecimal valorEvaluado, BigDecimal valorTotal) {
		if (valorTotal.compareTo(BigDecimal.valueOf(0)) == 0) {
			return BigDecimal.valueOf(0);
		}
		return valorEvaluado.multiply(BigDecimal.valueOf(100)).divide(valorTotal, 2, RoundingMode.HALF_UP);
	}
	
	
/**/

	public static String formatearConComaYDosDecimalesBis(String saldoAFormatear) {
		if (saldoAFormatear.contains(".")) {
			String[] saldoSeparado = saldoAFormatear.split("\\.");
			saldoSeparado[1] = StringUtils.rightPad(saldoSeparado[1], LONGITUD_DECIMALES, "0");
			saldoSeparado[1] = StringUtils.left(saldoSeparado[1], LONGITUD_DECIMALES);
			saldoAFormatear = saldoSeparado[0].concat(saldoSeparado[1]);
			return ISBANStringUtils.formatearSaldosConCerosYSignosBis(saldoAFormatear.replace(".", ""));
		} else {
			return ISBANStringUtils.formatearSaldosConCerosYSignosBis(saldoAFormatear.concat("00"));
		}
	}

	public static String formatearSaldosConCerosYSignosBis(String monto) {
		if (monto == null) {
			return StringUtils.EMPTY;
		}
		String montoModificado = monto;
		montoModificado = monto.replaceAll("\\+", "");
		montoModificado = montoModificado.replaceAll("\\-", "");
		montoModificado = montoModificado.replaceAll("\\.", "");
		montoModificado = StringUtils.leftPad(montoModificado, 3, "0");
		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - 2, ",").toString();

		for (int i = 6; i < montoModificado.length(); i = i + 3) {
			montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - i, ".").toString();
			i++;
		}
		return montoModificado;
	}


public static String formatearConComaYVariosDecimalesBis(String saldoAFormatear, int cantidadDecimales) {
		if (saldoAFormatear.contains(".")) {
			String[] saldoSeparado = saldoAFormatear.split("\\.");
			saldoSeparado[1] = StringUtils.rightPad(saldoSeparado[1], cantidadDecimales, "0");
			saldoSeparado[1] = StringUtils.left(saldoSeparado[1], cantidadDecimales);
			saldoAFormatear = saldoSeparado[0].concat(saldoSeparado[1]);
			return formatearSaldosConCerosYSignosBisDos(saldoAFormatear.replace(".", ""), cantidadDecimales);
		} else {
			return ISBANStringUtils.formatearSaldosConCerosYSignosBis(saldoAFormatear.concat("00"));
		}
	}


public static String formatearSaldosConCerosYSignosBisDos(String monto, int cantidadDecimales) {
		if (monto == null) {
			return StringUtils.EMPTY;
		}
		String montoModificado = monto;
		montoModificado = monto.replaceAll("\\+", "");
		montoModificado = montoModificado.replaceAll("\\-", "");
		montoModificado = montoModificado.replaceAll("\\.", "");
		montoModificado = StringUtils.leftPad(montoModificado, 3, "0");
		montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - cantidadDecimales, ",")
				.toString();

		for (int i = 4 + cantidadDecimales; i < montoModificado.length(); i = i + 3) {
			montoModificado = new StringBuilder(montoModificado).insert(montoModificado.length() - i, ".").toString();
			i++;
		}
		return montoModificado;
	}

/**
 * verifica si la parte decimal es todo cero.
 * @param valor
 * @param vectorAux
 * @return
 */
	public static boolean esTodoCero(String valor, Vector<String> vectorAux) {
		vectorAux.add("00");
		vectorAux.add("000");
		vectorAux.add("0000");
		vectorAux.add("00000");
		vectorAux.add("000000");
		vectorAux.add("0000000");
		vectorAux.add("00000000");
		vectorAux.add("000000000");
		vectorAux.add("0000000000");
		vectorAux.add("00000000000");
		return   vectorAux.contains(valor);
	}

		
	public static boolean esCero(String valor) {
		String[] partenumerica = StringUtils.split(valor, " ");
		String numerica = partenumerica[1];
		numerica = numerica.replace(".", "");
		numerica = numerica.replace(",", "");
		return BigDecimal.ZERO.equals(new BigDecimal(numerica));	
	}
	
	
	public static boolean esCeroDouble(Double valor) {
		Double ceroDouble = 0.0;
		int cero = 0;
		return (cero == valor.compareTo(ceroDouble));	
	}
	
	public static String formatearFechaPrecancelableUVA(String fechaAltaPF) {
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaAltaPF);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(date1);
		} catch (ParseException e) {
			LOGGER.error("No se pudo formatear la fecha", e);
			return "";
		}
	}
	
	/**
	 * Obtiene la cuenta en formato IATX a partir de la cuenta Santander
	 * @param nroCtaProd
	 * @return
	 */
	public static String obtenerFormatoCuentaIATX(String sucursal, String nroCtaProd) {
		
		String cuentaAltair = StringUtils.rightPad(sucursal, LONGITUD_SUCURSAL, ZERO_STR) + StringUtils.right(nroCtaProd, LONGITUD_NRO_CTA);
		return COD_BCO + cuentaAltair;
		
	}
	
	/**
	 * Obtiene formato numerico con coma y dos decimales como minimo
	 * Ej: valor -> 030000000, cantDecimalesOrigen -> 6 ==> 30,00
	 * Ej: valor -> 00000000300305589, cantDecimalesOrigen -> 4 ==> 30.030,5589
	 * @param valor
	 * @param cantDecimalesOrigen
	 * @return
	 */
	public static String formatearNumericoConPuntoComaYDosdecimales(String valor, int cantDecimalesOrigen) {
				
		String formatoDecimales = "";
		String cantCeros = "";
		for (int i = 0; i < cantDecimalesOrigen; i++) {
			formatoDecimales += "#";
			cantCeros += "0";
		}
		BigDecimal a = BigDecimal.valueOf(Long.valueOf(valor));
		BigDecimal divisor = BigDecimal.valueOf(Double.valueOf("1" + cantCeros));
		BigDecimal b = a.divide(divisor);

		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		

		DecimalFormat df = new DecimalFormat("###,###,###,###." + formatoDecimales, otherSymbols);
		return ISBANStringUtils.agregadorDecimalesConservaOrigen(df.format(b));
		
	}
	
	/**
	 * Ofusco un email.
	 *
	 * @param email
	 * 
	 */
	public static String ofuscarMail(String email) {
		if (email != null && !email.equals(StringUtils.EMPTY)) {
			StringBuilder sb = new StringBuilder(email);
			for (int i = 3; i < sb.length() && sb.charAt(i) != '@'; ++i) {
				sb.setCharAt(i, '*');
			}
			
			email = sb.toString();
		}
		return email;
	}

}