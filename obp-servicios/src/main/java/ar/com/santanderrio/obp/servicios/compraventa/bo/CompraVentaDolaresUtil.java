/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CompraVentaDolaresUtil.
 *
 * @author sabrina.cis
 */
@Component("compraVentaDolaresUtil")
public class CompraVentaDolaresUtil {

	/** The Constant SUCURSAL_ORIGEN_FUERA_HORARIO. */
	public static final String SUCURSAL_ORIGEN_FUERA_HORARIO = "sucursalFueraHorario";

	/**
	 * (cuenta origen en pesos) IDECLTSDO.tipo_cuenta= [01|02], enviar ACAH.
	 * IDECLTSDOtipo_cuenta=00 enviar ACTE (cuenta origen en dólares)
	 * IDECLTSDO.tipo_cuenta= [04|02] enviar ACAD. IDECLTSDO.tipo_cuenta=03
	 * enviar ACCD.
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	public String obtenerAplicacion(Cuenta cuentaOrigen, String tipoOperacion) {
		Integer codigoTipoCuenta = obtenerCodigoTipoCuenta(cuentaOrigen);

		if (esAplicacionACAH(codigoTipoCuenta, tipoOperacion)) {
			return CompraVentaStringUtil.TIPO_APLICACION_ACAH;
		}

		if (esAplicacionACTE(codigoTipoCuenta)) {
			return CompraVentaStringUtil.TIPO_APLICACION_ACTE;
		}

		if (esAplicacionACAD(codigoTipoCuenta, tipoOperacion)) {
			return CompraVentaStringUtil.TIPO_APLICACION_ACAD;
		}

		if (esAplicacionACCD(codigoTipoCuenta)) {
			return CompraVentaStringUtil.TIPO_APLICACION_ACCD;
		}
		return null;
	}

	/**
	 * Obtiene el codigo del tipo de cuenta, para la cuenta que ingresa por
	 * parametro.
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the integer
	 */
	public Integer obtenerCodigoTipoCuenta(Cuenta cuentaOrigen) {
		TipoCuenta tipoCuenta = cuentaOrigen.getTipoCuentaEnum();
		return tipoCuenta.getCodigo();
	}

	/**
	 * Es aplicacion ACAH.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the boolean
	 */
	private Boolean esAplicacionACAH(Integer codigoTipoCuenta, String tipoOperacion) {
		return esCajaDeAhorroEnPesos(codigoTipoCuenta)
				|| esDelTipoCuentaUnica(codigoTipoCuenta) && esTipoOperacionCompra(tipoOperacion);
	}

	/**
	 * Es aplicacion ACTE.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the boolean
	 */
	private Boolean esAplicacionACAD(Integer codigoTipoCuenta, String tipoOperacion) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_04)
				|| esDelTipoCuentaUnica(codigoTipoCuenta) && esTipoOperacionVenta(tipoOperacion);
	}

	/**
	 * Es aplicacion ACAD.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	private Boolean esAplicacionACTE(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_00);
	}

	/**
	 * Es aplicacion ACCD.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	private Boolean esAplicacionACCD(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_03);
	}

	/**
	 * tipo Operacion Compra.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the boolean
	 */
	public Boolean esTipoOperacionCompra(String tipoOperacion) {
		return StringUtils.equals(tipoOperacion, CompraVentaStringUtil.OPERACION_COMPRA);
	}

	/**
	 * tipo Operacion Venta.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the boolean
	 */
	public Boolean esTipoOperacionVenta(String tipoOperacion) {
		return StringUtils.equals(tipoOperacion, CompraVentaStringUtil.OPERACION_VENTA);
	}

	/**
	 * 00: Cuenta Corriente en Pesos.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaCorrienteEnPesos(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_00);
	}

	/**
	 * 01: Caja de Ahorro en Pesos.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	public Boolean esCajaDeAhorroEnPesos(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_01);
	}

	/**
	 * 03: Cuenta Corriente en Dolares.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaCorrienteEnDolares(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_03);
	}

	/**
	 * 04: Caja de Ahorro en Dolares.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	public Boolean esCajaDeAhorroEnDolares(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_04);
	}

	/**
	 * 02 o 09 o 10: Cuenta Unica (pesos + dolares).
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	public Boolean esDelTipoCuentaUnica(Integer codigoTipoCuenta) {
		return esCuentaUnica(codigoTipoCuenta) || esCuentaUnicaEnPesos(codigoTipoCuenta)
				|| esCuentaUnicaEnDolares(codigoTipoCuenta);
	}

	/**
	 * 02: Cuenta Unica.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaUnica(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_02);
	}

	/**
	 * 09: Cuenta Unica en pesos.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaUnicaEnPesos(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_09);
	}

	/**
	 * 10: Cuenta Unica en dolares.
	 *
	 * @param codigoTipoCuenta
	 *            the codigo tipo cuenta
	 * @return the boolean
	 */
	public Boolean esCuentaUnicaEnDolares(Integer codigoTipoCuenta) {
		return codigoTipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_10);
	}

	/**
	 * Servicio CNSCOTCN 100: campo de entrada CUENTA, Cuenta débito operante
	 * 
	 * Se obtiene de IDECLTSDO.Nro_cuenta_Producto, se retorna los 12 digitos de
	 * la derecha.
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the string
	 */
	public String obtenerCuentaDebitoOperante(Cuenta cuentaOrigen) {
		return StringUtils.right(cuentaOrigen.getNroCuentaProducto(), CompraVentaStringUtil.DOCE);
	}

	/**
	 * Si Operación seleccionada es Compra Enviar N. Si Operación seleccionada
	 * es Venta Enviar E
	 *
	 * @param opcionOperacion
	 *            the opcion operacion
	 * @return the string
	 */
	public String obtenerOpcionTomaCotizacion(String opcionOperacion) {
		if (opcionOperacion.equals(CompraVentaStringUtil.OPERACION_COMPRA)) {
			return CompraVentaStringUtil.OPERACION_COMPRA_ENVIO;
		}
		if (opcionOperacion.equals(CompraVentaStringUtil.OPERACION_VENTA)) {
			return CompraVentaStringUtil.OPERACION_VENTA_ENVIO;
		}
		return null;
	}

	/**
	 * Obtener detalle operacion.
	 *
	 * @param opcionOperacion
	 *            the opcion operacion
	 * @return the string
	 */
	public static String obtenerDetalleOperacion(String opcionOperacion) {
		if (opcionOperacion.equals(CompraVentaStringUtil.OPERACION_COMPRA)) {
			return CompraVentaStringUtil.DETALLE_COMPRA;
		}
		if (opcionOperacion.equals(CompraVentaStringUtil.OPERACION_VENTA)) {
			return CompraVentaStringUtil.DETALLE_VENTA;
		}
		return null;
	}

	/**
	 * Si Operación seleccionada es Compra Enviar V (Cliente Compra – Banco
	 * Vende). Si Operación seleccionada es Venta Enviar C (Cliente Vende –
	 * Banco Compra)
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	public String obtenerTipoOperacion(String tipoOperacion) {
		if (tipoOperacion.equals(CompraVentaStringUtil.OPERACION_COMPRA)) {
			return CompraVentaStringUtil.OPERACION_VENTA;
		}
		if (tipoOperacion.equals(CompraVentaStringUtil.OPERACION_VENTA)) {
			return CompraVentaStringUtil.OPERACION_COMPRA;
		}
		return null;
	}

	/**
	 * Enviar los ultimos 3 digitos de IDECLTSDO. Nro_Sucursal
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the string
	 */
	public String obtenerSegmento(Cuenta cuentaOrigen) {
		return StringUtils.right(cuentaOrigen.getNroSucursal(), CompraVentaStringUtil.TRES);
	}

	/**
	 * subString.
	 *
	 * @param dato
	 *            the dato
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the string
	 */
	public String subString(String dato, int start, int end) {
		return StringUtils.substring(dato, start, end);
	}

	/**
	 * De la Cuenta Origen seleccionada en el selector o la cuenta Origen
	 * default: IDECLTSDO. Nro_Sucursal
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public String obtenerSucursalCtaDolar(Cuenta cuenta) {
		return cuenta.getNroSucursal();
	}

	/**
	 * Obtener numero cta dolar.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public String obtenerNumeroCtaDolar(Cuenta cuenta) {
		return cuenta.getNroCuentaProducto();
	}

	/**
	 * Genera un String que representa un decimal, con cantidad de decimales
	 * segun el parametro ingresado.
	 *
	 * @param dato
	 *            the dato
	 * @param digitosParteEntera
	 *            indica la cantodad de digitos que representa la parte entera
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String generarNumeroDecimal(String dato, int digitosParteEntera) throws CompraVentaDolaresException {
		int dos = 2;
		String entero = subString(dato, 0, digitosParteEntera);
		String decimal = subString(dato, digitosParteEntera, dato.length());
		StringBuilder result = new StringBuilder();
		result.append(entero);
		result.append(".");
		result.append(decimal);
		Double numDecimal = new Double(verificarSignoFinalDecimal(result));
		return String.format("%." + dos + "f", BigDecimal.valueOf(numDecimal));

	}

	/**
	 * Verificar signo final decimal.
	 *
	 * @param result
	 *            the result
	 * @return the string
	 */
	private String verificarSignoFinalDecimal(StringBuilder result) {
		if (StringUtils.endsWith(result.toString(), "+")) {
			return StringUtils.remove(result.toString(), '+');
		}
		return result.toString();
	}

	/**
	 * Parcea el dato de cotizacion con dos decimales.
	 *
	 * @param dato
	 *            the dato
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String parcearCotizacionDosDecimales(String dato) throws CompraVentaDolaresException {
		return generarNumeroDecimal(dato, CompraVentaStringUtil.CUATRO);
	}

	/**
	 * Parcea el dato de cotizacion con cuatro decimales.
	 *
	 * @param dato
	 *            the dato
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String parcearCotizacionCuatroDecimales(String dato) throws CompraVentaDolaresException {
		return generarNumeroDecimal(dato, CompraVentaStringUtil.CUATRO);
	}

	/**
	 * Sucursal de la cuenta origen seleccionada en pesos/dolar
	 * IDECLTSDO.Nro_Sucursal Retorna los 3 digitos de la derecha
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public String obtenerSucursalTresDigitos(Cuenta cuenta) {
		return StringUtils.right(cuenta.getNroSucursal(), CompraVentaStringUtil.TRES);
	}

	/**
	 * Número de la cuenta origen en pesos/dolares IDECLTSDO.
	 * Nro_cuenta_Producto Retorna los 12 dígitos de la derecha
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public String obtenerNumeroCtaDoceDigitos(Cuenta cuenta) {
		return StringUtils.right(cuenta.getNroCuentaProducto(), CompraVentaStringUtil.DOCE);
	}

	/**
	 * Obtiene campo Aplicacion Dolar para el servicio de simulacion.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	public String obtenerAplicacionDolar(Cuenta cuenta, String tipoOperacion) {
		if (StringUtils.equals(tipoOperacion, CompraVentaStringUtil.OPERACION_COMPRA)) {
			return CompraVentaStringUtil.TIPO_APLICACION_ACAD;
		} else if (StringUtils.equals(tipoOperacion, CompraVentaStringUtil.OPERACION_VENTA)) {

			Integer tipoCuenta = getTipoCuentaAplicacionDolar(cuenta);

			if (tipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_04)
					|| tipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_10)
					|| (tipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_02))) {
				return CompraVentaStringUtil.TIPO_APLICACION_ACAD;
			} else if (tipoCuenta.equals(CompraVentaStringUtil.TIPO_CUENTA_03)) {
				return CompraVentaStringUtil.TIPO_APLICACION_ACCD;
			}
		}
		return null;
	}

	/**
	 * Gets the tipo cuenta aplicacion dolar.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the tipo cuenta aplicacion dolar
	 */
	private Integer getTipoCuentaAplicacionDolar(Cuenta cuenta) {
		Integer tipoCuenta = cuenta.getTipoCuentaEnum().getCodigo();
		if (!cuenta.getTipoCuentaSinUnificarDls().isEmpty()) {
			tipoCuenta = Integer.parseInt(cuenta.getTipoCuentaSinUnificarDls());
		}
		return tipoCuenta;
	}

	/**
	 * Indicador turismo o atesoramiento. Retorna “T”
	 *
	 * @return the string
	 */
	public String obtenerIndTuAtesora() {
		return CompraVentaStringUtil.IND_TU_ATESORA;
	}

	/**
	 * Indicador turismo o atesoramiento. Retorna “A”
	 *
	 * @return the string
	 */
	public String obtenerIndTuAtesoraA() {
		return CompraVentaStringUtil.IND_TU_ATESORA_VENTA;
	}

	/**
	 * Fecha SIMCPVTACN.FECHVAL Formato dd/mm/aaaa
	 *
	 * @param fechval
	 *            the fechval
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String obtenerFechVal(String fechval) throws CompraVentaDolaresException {
		try {
			SimpleDateFormat f1 = new SimpleDateFormat(CompraVentaStringUtil.FECHA_GUION);
			SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");
			return f2.format(f1.parse(fechval));
		} catch (ParseException e) {
			throw new CompraVentaDolaresException(e);
		}

	}

	/**
	 * Fecha actual del día Enviar formato: AAAA-MM-DD.
	 *
	 * @return the string
	 */
	public String obtenerFechVal() {
		Date fecha = new Date();
		SimpleDateFormat sm = new SimpleDateFormat(CompraVentaStringUtil.FECHA_GUION);
		return sm.format(fecha);
	}

	/**
	 * Código de operación para el débito Retorna 2312.
	 *
	 * @return the string
	 */
	public String obtenerCodigoDebi() {
		return CompraVentaStringUtil.CODIGO_DEBITO;
	}

	/**
	 * Código de operación para el credito Retorna 2254.
	 *
	 * @return the string
	 */
	public String obtenerCodigoCre() {
		return CompraVentaStringUtil.CODIGO_CREDITO;
	}

	/**
	 * Si el importe ingresado es en pesos Enviar [Importe ingresado] Si el
	 * importe ingresado es en dólares Enviar [Importe
	 * Ingresado]*CNSCOTCN.COTIZ-SALIDA
	 * 
	 * Se trunca parte decimal a 2 decimales.
	 *
	 * @param importe
	 *            the importe
	 * @param cotizacion
	 *            the cotizacion
	 * @param isDolar
	 *            the is dolar
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String obtenerImporteDebito(String importe, String cotizacion, Boolean isDolar)
			throws CompraVentaDolaresException {
		double importeFinal;
		try {
			String cot1 = StringUtils.replace(cotizacion, ",", ".");
			String imp1 = StringUtils.replace(importe, ",", ".");
			double numDecimal = Double.parseDouble(imp1);
			if (isDolar) {
				double cotizacionDecimal = Double.parseDouble(cot1);
				importeFinal = numDecimal * cotizacionDecimal;
			} else {
				importeFinal = numDecimal;
			}
			return importeAString(importeFinal);
		} catch (NumberFormatException i) {
			throw new CompraVentaDolaresException(i);
		} catch (NullPointerException i) {
			throw new CompraVentaDolaresException(i);
		}
	}

	/**
	 * Importe A string.
	 *
	 * @param importeFinal
	 *            the importe final
	 * @return the string
	 */
	private String importeAString(double importeFinal) {
		Locale local = Locale.getDefault();
		Locale.setDefault(Locale.ENGLISH);
		DecimalFormat decimal = new DecimalFormat("#.00");
		Locale.setDefault(local);
		String importeDebito = decimal.format(importeFinal);
		String importeDebitoParteDecimal = StringUtils.substringAfter(importeDebito, ".");
		String importeDebitoParteEntera = StringUtils.substringBefore(importeDebito, ".");
		String importeDebitoParteEnteraFormateada = StringUtils.leftPad(importeDebitoParteEntera, 13, "0");
		StringBuilder stb = new StringBuilder();
		stb.append(importeDebitoParteEnteraFormateada).append(importeDebitoParteDecimal);
		return stb.toString();
	}

	/**
	 * Si el importe ingresado es en pesos Enviar [Importe ingresado] Si el
	 * importe ingresado es en dólares Enviar [Importe
	 * Ingresado]*CNSCOTCN.COTIZ-SALIDA
	 * 
	 * Se trunca parte decimal a 2 decimales.
	 *
	 * @param importe
	 *            the importe
	 * @param cotizacion
	 *            the cotizacion
	 * @param isDolar
	 *            the is dolar
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String obtenerImporteDebitoVenta(String importe, String cotizacion, Boolean isDolar)
			throws CompraVentaDolaresException {
		double importeFinal;
		try {
			String cot1 = StringUtils.replace(cotizacion, ",", ".");
			String imp1 = StringUtils.replace(importe, ",", ".");
			double numDecimal = Double.parseDouble(imp1);
			if (!isDolar) {
				double cotizacionDecimal = Double.parseDouble(cot1);
				importeFinal = numDecimal / cotizacionDecimal;
			} else {
				importeFinal = numDecimal;
			}
			return importeAString(importeFinal);
		} catch (NumberFormatException i) {
			throw new CompraVentaDolaresException(i);
		} catch (NullPointerException i) {
			throw new CompraVentaDolaresException(i);
		}
	}

	/**
	 * Return T.
	 *
	 * @return the string
	 */
	public String obtenerCodTributa() {
		return CompraVentaStringUtil.IND_TU_ATESORA;
	}

	/**
	 * Return N.
	 *
	 * @return the string
	 */
	public String obtenerCodTributaVenta() {
		return CompraVentaStringUtil.COD_TRIBUTA;
	}

	/**
	 * Razón social IDECLTSDO.nombre + ” ” + IDECLTSDO.primer_apellido Quita
	 * espacios adelante y atrás de cada dato.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the string
	 */
	public String obtenerNomApell(Cliente cliente) {
		StringBuilder resut = new StringBuilder();
		resut.append(cliente.getNombre());
		resut.append(CompraVentaStringUtil.ESPACIO);
		resut.append(cliente.getApellido1());
		return StringUtils.leftPad(resut.toString(), 60, " ");
	}

	/**
	 * Código de concepto de la compra/venta bajo los cuales se controla o no
	 * los límites de cambios (856 / 665). Retorna 000856.
	 *
	 * @return the string
	 */
	public String obtenerCodigoConcepto() {
		return CompraVentaStringUtil.CODIGO_CONCEPTO;
	}

	/**
	 * Obtiene campo de entrada Aplicacion Pesos para el servicio de simulacion.
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the string
	 */
	public String obtenerAplicacionPesos(Cuenta cuentaOrigen) {
		TipoCuenta tipoCuenta = cuentaOrigen.getTipoCuentaEnum();
		if (tipoCuenta.getCodigo().equals(CompraVentaStringUtil.TIPO_CUENTA_01)
				|| tipoCuenta.getCodigo().equals(CompraVentaStringUtil.TIPO_CUENTA_09)
				|| tipoCuenta.getCodigo().equals(CompraVentaStringUtil.TIPO_CUENTA_02)) {
			return CompraVentaStringUtil.TIPO_APLICACION_ACAH;
		} else if (tipoCuenta.getCodigo().equals(CompraVentaStringUtil.TIPO_CUENTA_00)) {
			return CompraVentaStringUtil.TIPO_APLICACION_ACTE;
		}
		return null;
	}

	/**
	 * Retorna la fecha actual en formato yyyy-MM-dd.
	 *
	 * @return the string
	 */
	public String obtenerFechaValor() {
		return new SimpleDateFormat(CompraVentaStringUtil.FECHA_GUION).format(new Date());
	}

	/**
	 * Obtener cnd cliente.
	 *
	 * @return the string
	 */
	public String obtenerCndCliente() {
		return CompraVentaStringUtil.CONDICION_TITULAR;
	}

	/**
	 * Obtiene la primer letra del tipo de documento Tipo de documento esta
	 * formado por dos alfanumericos( Primer byte D:CDI, L:CUIL, T:CUIT).
	 *
	 * @param tipoDocumento
	 *            the tipo documento
	 * @return the string
	 */
	public String obtenerTipoDocumento(String tipoDocumento) {
		String primerByte = primerByte(tipoDocumento);
		if (primerByte.equals(CompraVentaStringUtil.TIPO_DOCUMENTO_L)
				|| primerByte.equals(CompraVentaStringUtil.TIPO_DOCUMENTO_T)
				 || primerByte.equals(CompraVentaStringUtil.TIPO_DOCUMENTO_D)
				 ) {

			return primerByte;
		}
		return null;
	}

	/**
	 * Obtiene el primer caracter del dato ingresado.
	 *
	 * @param dato
	 *            the dato
	 * @return the string
	 */
	private String primerByte(String dato) {
		return StringUtils.right(dato, CompraVentaStringUtil.UNO);
	}

	/**
	 * Recibe la cotizacion salida del servicio iatx de cotizacion y formatea a
	 * String con dos decimales y coma COTIZ-SALIDA N11(4e,7d) Cotizacion de
	 * salida.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String obtenerCotizacionString(String cotizacion) throws CompraVentaDolaresException {
		return reemplazarPuntoPorComaEnCotizacion(parcearCotizacionDosDecimales(cotizacion));
	}

	/**
	 * Reemplazar punto por coma en cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the string
	 */
	private String reemplazarPuntoPorComaEnCotizacion(String cotizacion) {
		return StringUtils.replace(cotizacion, CompraVentaStringUtil.PUNTO, CompraVentaStringUtil.COMA);
	}

	/**
	 * Obtiene IMPORTE_COTI (entrada para servicio de simulacion). Recibe
	 * cotizacion String 2 digitos enteros, 2 decimales separados por coma o
	 * cero y rellena con ceros. Devuelve en formato N15(11e,4d). Rellena con
	 * ceros.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the string
	 */
	public String obtenerImporteCoti(String cotizacion) {
		String parteDecimal = StringUtils.substringAfter(cotizacion, ",");
		parteDecimal = StringUtils.rightPad(parteDecimal, 4, "0");
		String parteEntera = StringUtils.substringBefore(cotizacion, ",");
		parteEntera = StringUtils.leftPad(parteEntera, 11, "0");
		StringBuilder importeCoti = new StringBuilder();
		importeCoti.append(parteEntera).append(parteDecimal);
		return importeCoti.toString();
	}

	/**
	 * Retorna el caracter de la izquierda.
	 *
	 * @param docBcra
	 *            the doc bcra
	 * @return the string
	 */
	public String obtenerDocBcra(String docBcra) {
		return StringUtils.left(docBcra, CompraVentaStringUtil.LONGITUD_TPODOC_BCRA);
	}

	/**
	 * Numeral 16 bytes (13 representa la parte entera,2 representa la parte
	 * decimal, signo).
	 *
	 * @param importe
	 *            the importe
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String convertirImporte(String importe) throws CompraVentaDolaresException {
		return generarNumeroDecimal(importe, CompraVentaStringUtil.TRECE);
	}

	/**
	 * Numeral 16 bytes (11 representa la parte entera,4 representa la parte
	 * decimal, signo).
	 *
	 * @param impcoti
	 *            the impcoti
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String convertirImpCoti(String impcoti) throws CompraVentaDolaresException {
		return generarNumeroDecimal(impcoti, CompraVentaStringUtil.ONCE);
	}

	/**
	 * Le da formato a la fecha Formato Entrada: AAAAMMDDHHMMSS Formato Salida:
	 * “DD/MM/AAAA – HH:MM.
	 *
	 * @param fechaAformatear
	 *            the fecha aformatear
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String formatearFecha(String fechaAformatear) throws CompraVentaDolaresException {
		try {
			return fechaAformatear.substring(6, 8) + "/" + fechaAformatear.substring(4, 6) + "/"
					+ fechaAformatear.substring(0, 4) + " - " + fechaAformatear.substring(8, 10) + ":"
					+ fechaAformatear.substring(10, 12);
		} catch (IndexOutOfBoundsException i) {
			throw new CompraVentaDolaresException(i);
		} catch (NullPointerException i) {
			throw new CompraVentaDolaresException(i);
		}

	}

	/**
	 * Le da formato a la fecha Formato Entrada: AAAAMMDD Formato Salida:
	 * “DD/MM/AAAA".
	 *
	 * @param fechaAformatear
	 *            the fecha aformatear
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String formatearFechaSinHora(String fechaAformatear) throws CompraVentaDolaresException {
		try {
			return ISBANStringUtils.formatearFechaSinHora(fechaAformatear);
		} catch (IndexOutOfBoundsException i) {
			throw new CompraVentaDolaresException(i);
		} catch (NullPointerException i) {
			throw new CompraVentaDolaresException(i);
		}
	}

	/**
	 * Obtine hora: HH:MM.
	 *
	 * @param fechaAformatear
	 *            the fecha aformatear
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String obtenerHora(String fechaAformatear) throws CompraVentaDolaresException {
		try {
			return fechaAformatear.substring(8, 10) + ":" + fechaAformatear.substring(10, 12);
		} catch (IndexOutOfBoundsException i) {
			throw new CompraVentaDolaresException(i);
		} catch (NullPointerException i) {
			throw new CompraVentaDolaresException(i);
		}
	}

	/**
	 * Obtener numero operacion venticuatro digitos.
	 *
	 * @param numeroOperacion
	 *            the numero operacion
	 * @return the string
	 */
	public String obtenerNumeroOperacionVenticuatroDigitos(String numeroOperacion) {
		return StringUtils.right(numeroOperacion, CompraVentaStringUtil.VEINTICUATRO);
	}

	/**
	 * Obtener numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numero comprobante
	 * @return the string
	 */
	public String obtenerNumeroComprobante(String numeroComprobante) {
		return StringUtils.right(numeroComprobante, CompraVentaStringUtil.OCHO);
	}

	/**
	 * Setear espacios.
	 *
	 * @param espacio
	 *            the espacio
	 * @return the string
	 */
	public String setearEspacios(int espacio) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < espacio; i++) {
			sb.insert(i, " ");
		}
		return sb.toString();
	}

	/**
	 * Numeral 16 bytes (11 representa la parte entera,4 representa la parte
	 * decimal, signo).
	 *
	 * @param impcoti
	 *            the impcoti
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String convertirSimulacionImporteAComprar(String impcoti) throws CompraVentaDolaresException {
		String f1 = generarNumeroDecimal(impcoti, CompraVentaStringUtil.TRECE);
		return reemplazarPuntoPorComaEnCotizacion(f1);
	}

	/**
	 * Convertir simulacion importe A comprar con punto.
	 *
	 * @param impcoti
	 *            the impcoti
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String convertirSimulacionImporteAComprarConPunto(String impcoti) throws CompraVentaDolaresException {
		return generarNumeroDecimal(impcoti, CompraVentaStringUtil.TRECE);
	}

	/**
	 * N16(11e,4d,signo) cotización utilizada para la contra- valoración
	 * SIMCPVTACN.IMPCOTI
	 *
	 * @param impcoti
	 *            the impcoti
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String obtenerCotizacionAplicable(String impcoti) throws CompraVentaDolaresException {
		String f1 = generarNumeroDecimal(impcoti, CompraVentaStringUtil.ONCE);
		return reemplazarPuntoPorComaEnCotizacion(f1);
	}

	/**
	 * N16(11e,4d,signo) cotización utilizada para la contra- valoración
	 * SIMCPVTACN.IMPCOTI
	 *
	 * @param impcoti
	 *            the impcoti
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String obtenerCotizacionAplicableVenta(String impcoti) throws CompraVentaDolaresException {
		String f1 = generarNumeroDecimal(impcoti, CompraVentaStringUtil.CUATRO);
		return reemplazarPuntoPorComaEnCotizacion(f1);
	}

	/**
	 * Obtener cotizacion aplicable venta con punto.
	 *
	 * @param impcoti
	 *            the impcoti
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String obtenerCotizacionAplicableVentaConPunto(String impcoti) throws CompraVentaDolaresException {
		return generarNumeroDecimal(impcoti, CompraVentaStringUtil.CUATRO);
	}

	/**
	 * N16(13e,2d,signo) total importe cargo $ + SIMCPVTACN.TOTCARG
	 *
	 * @param totCarg
	 *            the tot carg
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String convertirSimulacionImporteDebitar(String totCarg) throws CompraVentaDolaresException {
		String f1 = generarNumeroDecimal(totCarg, CompraVentaStringUtil.TRECE);
		return reemplazarPuntoPorComaEnCotizacion(f1);
	}

	/**
	 * Convertir simulacion importe debitar con punto.
	 *
	 * @param totCarg
	 *            the tot carg
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String convertirSimulacionImporteDebitarConPunto(String totCarg) throws CompraVentaDolaresException {
		return generarNumeroDecimal(totCarg, CompraVentaStringUtil.TRECE);
	}

	/**
	 * Obtener importe cred operacion compra.
	 *
	 * @param importeCredito
	 *            the importe credito
	 * @return the string
	 */
	public String obtenerImporteCredOperacionCompra(String importeCredito) {
		String importeFormateado = StringUtils.remove(importeCredito, ",");
		return StringUtils.leftPad(importeFormateado, 15, "0");
	}

	/**
	 * Código de operación para el credito Retorna 2254.
	 *
	 * @return the string
	 */
	public String obtenerCodigoCreVenta() {
		return CompraVentaStringUtil.CODIGO_CREDITO_VENTA;
	}

	/**
	 * Código de operación para el débito Retorna 2312.
	 *
	 * @return the string
	 */
	public String obtenerCodigoDebiVenta() {
		return CompraVentaStringUtil.CODIGO_DEBITO_VENTA;
	}
	
	/**
	 * N15(13e,2d,signo) SIMCPVTACN.IMPIMPU
	 *
	 * @param totCarg
	 *            the tot carg
	 * @return the string
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public String convertirSimulacionImporteImpuesto(String impimpu) throws CompraVentaDolaresException {
		String f1 = generarNumeroDecimal(impimpu, CompraVentaStringUtil.TRECE);
		return reemplazarPuntoPorComaEnCotizacion(f1);
	}
	
}