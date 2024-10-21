/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoCuentaTarjetaEnum;
import ar.com.santanderrio.obp.servicios.monedero.exception.FormatException;

/**
 * The Class MonederoUtils.
 */
public class MonederoUtils {

	/** The Constant CODIGO_APLICACION_ABAE. */
	public static final String CODIGO_APLICACION_ABAE = "ABAE";

	/** The Constant CODIGO_APLICACION_AVIP. */
	public static final String CODIGO_APLICACION_AVIP = "AVIP";

	/** The Constant CODIGO_APLICACION_AEXP. */
	public static final String CODIGO_APLICACION_AEXP = "AEXP";

	/** The Constant NRO_ORDEN_FIRMANTE_DEB. */
	public static final String NRO_ORDEN_FIRMANTE_DEB = "001";

	/** The Constant CODIGO_CNSCTAMONE_ABAE. */
	public static final String CODIGO_CNSCTAMONE_ABAE = "05";

	/** The Constant CODIGO_CNSCTAMONE_AVIP_1. */
	public static final String CODIGO_CNSCTAMONE_AVIP_1 = "06";

	/** The Constant CODIGO_CNSCTAMONE_AVIP_2. */
	public static final String CODIGO_CNSCTAMONE_AVIP_2 = "07";

	/** The Constant CODIGO_CNSCTAMONE_AEXP. */
	public static final String CODIGO_CNSCTAMONE_AEXP = "42";

	/** The Constant MARCA_RECARGA_ABAE. */
	public static final String MARCA_RECARGA_ABAE = "VD";

	/** The Constant MARCA_RECARGA_AVIP. */
	public static final String MARCA_RECARGA_AVIP = "VC";

	/** The Constant MARCA_RECARGA_AEXP. */
	public static final String MARCA_RECARGA_AEXP = "AB";

	/** The Constant SEXO_MASCULINO_PERSFIS. */
	public static final String SEXO_MASCULINO_PERSFIS = "H";

	/** The Constant SEXO_FEMENIMO_PERSFIS. */
	public static final String SEXO_FEMENIMO_PERSFIS = "M";

	/** The Constant LIMITE_INFERIOR_EDAD. */
	public static final int LIMITE_INFERIOR_EDAD = 16;

	/** The Constant LIMITE_INFERIOR_EDAD_VALIDO. */
	public static final int LIMITE_INFERIOR_EDAD_VALIDO = 18;

	/** The Constant LIMITE_SUPERIOR_EDAD_VALIDO. */
	public static final int LIMITE_SUPERIOR_EDAD_VALIDO = 71;

	/** The Constant ID_MARCA_TARJETA_AMEX. */
	private static final String ID_MARCA_TARJETA_AMEX = "AB";

	/** The Constant ID_MARCA_TARJETA_VISA_DEBITO. */
	private static final String ID_MARCA_TARJETA_VISA_DEBITO = "VD";

	/** The Constant ID_MARCA_TARJETA_VISA_CREDITO. */
	private static final String ID_MARCA_TARJETA_VISA_CREDITO = "VC";

	/** The Constant RV. */
	private static final String RV = "RV";

	/** The Constant VD. */
	private static final String VD = "VD";

	/** The Constant VC. */
	private static final String VC = "VC";

	/** The Constant AB. */
	private static final String AB = "AB";

	/** The Constant RA. */
	private static final String RA = "RA";

	/** The Constant TIPO_TARJETA. */
	private static final String TIPO_TARJETA = "  ";

	/** The Constant NUM_TARJETA. */
	private static final String NUM_TARJETA = "                ";

	/** The Constant GUION_STRING. */
	public static final String GUION_STRING = "-";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MonederoUtils.class);

	/**
	 * Traer desc sexo.
	 *
	 * @param sexoServicio
	 *            the sexo servicio
	 * @return the string
	 */
	public static String traerDescSexo(String sexoServicio) {
		return SEXO_MASCULINO_PERSFIS.equals(sexoServicio) ? "M"
				: SEXO_FEMENIMO_PERSFIS.equals(sexoServicio) ? "F" : "";
	}

	/**
	 * Traer tipo doc cod interno cliente.
	 *
	 * @param tipoDoc
	 *            the tipo doc
	 * @return the string
	 */
	// revisar esto
	public static String traerTipoDocCodInternoCliente(String tipoDoc) {
		String desc = "";
		if ("N".equals(tipoDoc)) {
			desc = "01";
		} else if ("C".equals(tipoDoc)) {
			desc = "02";
		} else if ("E".equals(tipoDoc)) {
			desc = "03";
		} else if ("T".equals(tipoDoc)) {
			desc = "10";
		}

		return desc;
	}

	/**
	 * Dd mm aaaa 2 canonico con barra.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	public static String ddMmAaaa2CanonicoConBarra(String fecha) {
		if (fecha.length() != 8) {
			try {
				throw new FormatException("fecha invalida : " + fecha);
			} catch (FormatException e) {
				// Nothing
			}
		}
		return fecha.substring(0, 2) + "/" + fecha.substring(2, 4) + "/" + fecha.substring(4, 8);
	}

	/**
	 * Puntos DNI.
	 *
	 * @param cadena
	 *            the cadena
	 * @return the string
	 */
	public static String puntosDNI(String cadena) {

		// ID 4640 - Incidencia 60 - 04/06/2013
		String pattern = "###,###";
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		myFormatter.setGroupingSize(3);
		return myFormatter.format(new BigDecimal(cadena.trim())).replace(",", ".");
		// FIN ID 4640 - Incidencia 60
	}

	/**
	 * Resuelve marca recarga.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public static String resuelveMarcaRecarga(Cuenta cuenta) {

		String marcaRecarga = "";

		if (CODIGO_APLICACION_ABAE.equals(cuenta.getCodigoAplicacion())
				|| CODIGO_CNSCTAMONE_ABAE.equalsIgnoreCase(cuenta.getTipoCuenta())) {
			marcaRecarga = MARCA_RECARGA_ABAE;
		} else if (CODIGO_APLICACION_AEXP.equals(cuenta.getCodigoAplicacion())
				|| CODIGO_CNSCTAMONE_AEXP.equals(cuenta.getTipoCuenta())) {
			marcaRecarga = MARCA_RECARGA_AEXP;
		} else if (CODIGO_APLICACION_AVIP.equals(cuenta.getCodigoAplicacion())
				|| CODIGO_CNSCTAMONE_AVIP_2.equals(cuenta.getTipoCuenta())) {
			marcaRecarga = MARCA_RECARGA_AVIP;
		}
		return marcaRecarga;
	}

	/**
	 * Resuelve marca recarga secundaria.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @param marcaRecarga
	 *            the marca recarga
	 * @return the string
	 */
	public static String resuelveMarcaRecargaSecundaria(List<Cuenta> cuentas, String marcaRecarga) {
		String mark = "  ";
		boolean trjdebito = false;

		Cuenta listTRJ = obtieneCuentaTarjetaDebito(cuentas);

		if (listTRJ != null && CODIGO_CNSCTAMONE_ABAE.equals(listTRJ.getTipoCuenta())) {
			trjdebito = true;
		}

		for (Iterator<Cuenta> iterator = cuentas.iterator(); iterator.hasNext();) {
			Cuenta cuenta = iterator.next();

			if (VD.equals(marcaRecarga) && RV.equals(cuenta.getTipoCuenta())) {
				mark = VC;
				break;
			} else if (VC.equals(marcaRecarga) && trjdebito) {
				mark = VD;
				break;
			}

		}
		return mark;
	}

	/**
	 * Resuelve nro tarjeta origen recarga secundaria.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoTarjeta
	 *            the tipo tarjeta
	 * @return the string
	 */
	public static String resuelveNroTarjetaOrigenRecargaSecundaria(Cliente cliente, String tipoTarjeta) {
		String numTarjeta = "";
		if (tipoTarjeta.equals(TIPO_TARJETA)) {
			return numTarjeta = NUM_TARJETA;
		}

		if (VC.equals(tipoTarjeta)) {
			tipoTarjeta = RV;
		} else if (AB.equals(tipoTarjeta)) {
			tipoTarjeta = RA;
		}

		List<Cuenta> cuentas = cliente.getCuentas();

		try {
			Cuenta listTRJ = obtieneCuentaTarjetaDebito(cuentas);
			if (tipoTarjeta.equals(VD)) {
				return listTRJ.getNroTarjetaCredito().substring(4, 20);
			}

			for (Iterator<Cuenta> iterator = cuentas.iterator(); iterator.hasNext();) {
				Cuenta cuenta = iterator.next();
				String tipo = cuenta.getProductoAltair();
				String nro = cuenta.getNroTarjetaCredito();

				String codigo = TipoCuentaTarjetaEnum.fromCodigo(tipo).getDescripcion();

				/**
				 * TIPOCTA_MASTER("06", "MasterCard", ""), TIPOCTA_VISA("07",
				 * "VISA", "VISA"), TIPOCTA_AMEX("42", "American Express",
				 * "AMEX");
				 */
				if (tipoTarjeta.equals(codigo)) {
					numTarjeta = nro;
					break;
				}
			}
		} catch (Exception e) {
			// nothing
		}
		return numTarjeta.substring(4, 20);

	}

	/**
	 * Obtiene cuenta tarjeta debito.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the cuenta
	 */
	private static Cuenta obtieneCuentaTarjetaDebito(List<Cuenta> cuentas) {

		for (Iterator<Cuenta> iterator = cuentas.iterator(); iterator.hasNext();) {
			Cuenta cuenta = (Cuenta) iterator.next();
			if (CODIGO_CNSCTAMONE_ABAE.equals(cuenta.getTipoCuenta())
					&& CODIGO_APLICACION_ABAE.equalsIgnoreCase(cuenta.getCodigoAplicacion())) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Valida fecha ingresada.
	 *
	 * @param fechaIngresada
	 *            the fecha ingresada
	 * @param fecha
	 *            the fecha
	 * @return true, if successful
	 */
	public static boolean validaFechaIngresada(String fechaIngresada, String fecha) {
		boolean respuesta = false;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dFechaIngresada;
		Date dFechaAComparar;

		try {
			dFechaIngresada = sdf.parse(fechaIngresada);
			dFechaAComparar = sdf.parse(fecha);

			if (dFechaIngresada.compareTo(dFechaAComparar) == 0) {
				respuesta = true;
			}

		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return respuesta;
	}

	/**
	 * Mismo nup.
	 *
	 * @param nupIngresado
	 *            the nup ingresado
	 * @param nup
	 *            the nup
	 * @return true, if successful
	 */
	public static boolean mismoNup(String nupIngresado, String nup) {
		boolean respuesta = false;
		if (nupIngresado.equals(nup)) {
			respuesta = true;
		}
		return respuesta;
	}

	/**
	 * Llena con ceros.
	 *
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String llenaConCeros(int size) {
		StringBuilder buf = new StringBuilder();

		for (int i = 0; i < size; i++) {
			buf.append('0');
		}

		return buf.toString();
	}

	/**
	 * Llena con blancos.
	 *
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String llenaConBlancos(int size) {
		StringBuilder buf = new StringBuilder();

		for (int i = 0; i < size; i++) {
			buf.append(' ');
		}

		return buf.toString();
	}

	/**
	 * Gets the intervalo edades.
	 *
	 * @param dia
	 *            the dia
	 * @param mes
	 *            the mes
	 * @param ano
	 *            the ano
	 * @return the intervalo edades
	 */
	public boolean getIntervaloEdades(String dia, String mes, String ano) {

		Calendar calPersona = Calendar.getInstance();
		calPersona.set(Calendar.MONTH, Integer.parseInt(mes));
		calPersona.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
		calPersona.set(Calendar.YEAR, Integer.parseInt(ano));

		Calendar calActual = Calendar.getInstance();
		calActual.setTime(new Date());

		int edadPersona;

		// si la fecha ingresada es anterior a la actual
		if (calActual.compareTo(calPersona) > 0) {

			if (calActual.get(Calendar.MONTH) >= calPersona.get(Calendar.MONTH)
					&& calActual.get(Calendar.DAY_OF_MONTH) >= calPersona.get(Calendar.DAY_OF_MONTH)) {
				edadPersona = calActual.get(Calendar.YEAR) - calPersona.get(Calendar.YEAR);
			} else {
				edadPersona = calActual.get(Calendar.YEAR) - calPersona.get(Calendar.YEAR) - 1;
			}

			if (edadPersona >= LIMITE_INFERIOR_EDAD_VALIDO && edadPersona <= LIMITE_SUPERIOR_EDAD_VALIDO) {
				return true;
			}
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Calcular anios desde hoy.
	 *
	 * @param d1Str
	 *            the d 1 str
	 * @return the float
	 */
	public static float calcularAniosDesdeHoy(String d1Str) {
		SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);
		try {
			Date d1 = sdf.parse(d1Str);
			Date d2 = new Date();

			long diff = Math.abs(d1.getTime() - d2.getTime());
			return diff / (float) (365 * 24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return -1;
	}

	/**
	 * Gets the marca tarjeta.
	 *
	 * @param idMarcaTarjeta
	 *            the id marca tarjeta
	 * @return the marca tarjeta
	 */
	public static String getMarcaTarjeta(String idMarcaTarjeta) {
		if (ID_MARCA_TARJETA_VISA_CREDITO.equals(idMarcaTarjeta)
				|| ID_MARCA_TARJETA_VISA_DEBITO.equals(idMarcaTarjeta)) {
			return "VISA";
		} else if (ID_MARCA_TARJETA_AMEX.equals(idMarcaTarjeta)) {
			return "American Express";
		}
		return null;
	}

	/**
	 * Gets the masked num.
	 *
	 * @param idMarcaTarjeta
	 *            the id marca tarjeta
	 * @param lastDig
	 *            the last dig
	 * @return the masked num
	 */
	public static String getMaskedNum(String idMarcaTarjeta, String lastDig) {
		if (ID_MARCA_TARJETA_VISA_CREDITO.equals(idMarcaTarjeta)
				|| ID_MARCA_TARJETA_VISA_DEBITO.equals(idMarcaTarjeta)) {
			return "XXXX-" + lastDig;
		} else if (ID_MARCA_TARJETA_AMEX.equals(idMarcaTarjeta)) {
			return "XXXXXX-" + lastDig;
		}
		return "";
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @param idMarcaTarjeta
	 *            the id marca tarjeta
	 * @return the tipo tarjeta
	 */
	public static String getTipoTarjeta(String idMarcaTarjeta) {
		if (ID_MARCA_TARJETA_VISA_CREDITO.equals(idMarcaTarjeta) || ID_MARCA_TARJETA_AMEX.equals(idMarcaTarjeta)) {
			return "crédito";
		} else if (ID_MARCA_TARJETA_VISA_DEBITO.equals(idMarcaTarjeta)) {
			return "débito";
		}
		return null;
	}

	/**
	 * Estado civil iatx codigo.
	 *
	 * @param estado
	 *            the estado
	 * @return the string
	 */
	public static String estadoCivilIatxCodigo(String estado) {

		if ("Soltero".equals(estado)) {
			return "S";
		} else if ("Casado".equals(estado)) {
			return "C";
		} else if ("Divorciado".equals(estado)) {
			return "D";
		} else if ("Viudo".equals(estado)) {
			return "V";
		} else {
			return "O";
		}
	}

	/**
	 * Formatearyyyy M mdd.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 * @throws ParseException
	 *             the parse exception
	 */
	public static String formatearyyyyMMdd(String date) throws ParseException {
		SimpleDateFormat sdfFrom = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfTo = new SimpleDateFormat("yyyyMMdd");
		return sdfTo.format(sdfFrom.parse(date));
	}

	/**
	 * Obtener cuenta monedero.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuenta
	 */
	public static Cuenta obtenerCuentaMonedero(Cliente cliente) {
		/**
		 * Cuenta cuenta = sesionCliente.getCliente().getCuentas().get(0);
		 * String cuentaStr =
		 * cuenta.getSucursalPaquete().substring(1)+"-"+cuenta.getNroCuentaProducto().substring(9,
		 * 15)+"/"+cuenta.getNroCuentaProducto().substring(15);
		 * respuestaView.getRespuesta().getDatosSolicitanteView().setNroCuenta(cuentaStr);
		 */

		Cuenta cuentaRet = null;
		// con esta logica encuentro la cuenta titular monedero
		String subprodutoAltair;
		String codigoTitularidad;
		String productoAltair;
		for (int n = 0; n < cliente.getCuentas().size(); ++n) {
			Cuenta cuenta = cliente.getCuentas().get(n);
			subprodutoAltair = cuenta.getSubproductoAltair();
			codigoTitularidad = cuenta.getCodigoTitularidad();
			productoAltair = cuenta.getProductoAltair();

			if ("MONE".equalsIgnoreCase(subprodutoAltair) && "TI".equalsIgnoreCase(codigoTitularidad)
					&& "43".equalsIgnoreCase(productoAltair)) {
				cuentaRet = cuenta;
				break;
			}
		}

		return cuentaRet;
	}

	/**
	 * Obtener cuenta monedero.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuenta
	 */
	public static Cuenta obtenerCuentaMonederoAdicional(Cliente cliente) {
		/**
		 * Cuenta cuenta = sesionCliente.getCliente().getCuentas().get(0);
		 * String cuentaStr =
		 * cuenta.getSucursalPaquete().substring(1)+"-"+cuenta.getNroCuentaProducto().substring(9,
		 * 15)+"/"+cuenta.getNroCuentaProducto().substring(15);
		 * respuestaView.getRespuesta().getDatosSolicitanteView().setNroCuenta(cuentaStr);
		 */

		Cuenta cuentaRet = null;
		// con esta logica encuentro la cuenta titular monedero
		String subprodutoAltair;
		String codigoTitularidad;
		String productoAltair;
		for (int n = 0; n < cliente.getCuentas().size(); ++n) {
			Cuenta cuenta = cliente.getCuentas().get(n);
			subprodutoAltair = cuenta.getSubproductoAltair();
			codigoTitularidad = cuenta.getCodigoTitularidad();
			productoAltair = cuenta.getProductoAltair();

			if ("MONE".equalsIgnoreCase(subprodutoAltair) 
					&& "AD".equalsIgnoreCase(codigoTitularidad)
					&& "43".equalsIgnoreCase(productoAltair)) {
				cuentaRet = cuenta;
				break;
			}
		}

		return cuentaRet;
	}

	
	/**
	 * Obtiene cuenta por tag nro.
	 *
	 * @param tagNro
	 *            the tag nro
	 * @param cuentas
	 *            the cuentas
	 * @return the cuenta
	 */
	public static Cuenta obtieneCuentaPorTagNro(String tagNro, List<Cuenta> cuentas) {
		Cuenta cuenta = null;
		for (Iterator<Cuenta> iterator = cuentas.iterator(); iterator.hasNext();) {
			cuenta = (Cuenta) iterator.next();
			if (tagNro.equals(cuenta.getNroTarjetaCredito().trim())) {
				break;
			}
		}
		return cuenta;
	}
}
