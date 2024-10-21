/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class TarjCredAdicUtils.
 */
public final class TarjCredAdicUtils {

	/** The Constant FMT_DDMMYYYY. */
	public static final String FMT_DDMMYYYY = "ddMMyyyy";

	/**
	 * Instantiates a new tarj cred adic utils.
	 */
	private TarjCredAdicUtils() {
		// No se publica ya que es una clase estatica
	}

	/**
	 * Fechas iguales.
	 *
	 * @param date1
	 *            the date 1
	 * @param date2
	 *            the date 2
	 * @return true, if successful
	 */
	public static boolean fechasIguales(String date1, String date2) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
		try {
			Date dt = sdf1.parse(date1);
			Date dtAdic = sdf2.parse(date2);
			if (dt.compareTo(dtAdic) != 0) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;
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
	 * Calcula edad.
	 *
	 * @param fn
	 *            the fn
	 * @return the int
	 * @throws ParseException
	 *             the parse exception
	 */
	public static int calculaEdad(String fn) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(FMT_DDMMYYYY);
		Date dtNac = sdf.parse(fn);
		Calendar calFn = Calendar.getInstance();
		calFn.setTime(dtNac);
		Calendar cal = Calendar.getInstance();
		int anio = cal.get(Calendar.YEAR) - calFn.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH) - calFn.get(Calendar.MONTH);
		int dia = cal.get(Calendar.DATE) - calFn.get(Calendar.DATE);
		if (mes < 0 || (mes == 0 && dia < 0)) {
			anio--;
		}
		return anio;
	}

	/**
	 * Gets the nro tel.
	 *
	 * @param nro
	 *            the nro
	 * @return the nro tel
	 */
	private static String getNroTel(String nro) {
		if (StringUtils.isEmpty(nro)) {
			return StringUtils.rightPad("", 8);
		}

		String numero = nro;
		String[] numeroSplit = numero.split("-");
		if (numeroSplit.length == 3) {
			numero = StringUtils.isEmpty(numeroSplit[2]) ? StringUtils.rightPad("", 8)
					: ISBANStringUtils.fillStr(numeroSplit[2], 8);
		} else if (numeroSplit.length == 2) {
			numero = StringUtils.isEmpty(numeroSplit[1]) ? StringUtils.rightPad("", 8)
					: ISBANStringUtils.fillStr(numeroSplit[1], 8);
		} else if (numeroSplit.length == 1) {
			// Este if no deberia ejecutarse pero puede venir solo el numero del
			// backend
			numero = StringUtils.isEmpty(numeroSplit[0]) ? StringUtils.rightPad("", 8)
					: ISBANStringUtils.fillStr(numeroSplit[0], 8);
		} else {
			numero = StringUtils.rightPad("", 8);
		}

		return numero;

	}

	/**
	 * Gets the caract tel.
	 *
	 * @param nro
	 *            the nro
	 * @return the caract tel
	 */
	private static String getCaractTel(String nro) {
		String numero = nro.trim();
		String[] numeroSplit = numero.split("-");
		if (numeroSplit.length == 3) {
			numero = StringUtils.isEmpty(numeroSplit[1]) ? StringUtils.rightPad("", 4)
					: ISBANStringUtils.fillStr(numeroSplit[1], 4);
		} else if (numeroSplit.length == 2) {
			numero = StringUtils.isEmpty(numeroSplit[0]) ? StringUtils.rightPad("", 4)
					: ISBANStringUtils.fillStr(numeroSplit[0], 4);
		} else {
			numero = ISBANStringUtils.fillStr("", 4);
		}
		return numero;
	}

	/**
	 * Gets the telefono para mostrar.
	 *
	 * @param prefijo
	 *            the prefijo
	 * @param telefonoCompleto
	 *            the telefono completo
	 * @return the telefono para mostrar
	 */
	public static String getTelefonoParaMostrar(String prefijo, String telefonoCompleto) {
		String prefijoaux = StringUtils.isNotEmpty(prefijo) ? prefijo : "";
		telefonoCompleto = completaGuion(telefonoCompleto);
		String caracteristica = "";
		if (getCaractTel(telefonoCompleto).trim().length() > 0) {
			caracteristica = getCaractTel(telefonoCompleto).trim();
		}

		String numeroTelefono = getNroTel(telefonoCompleto);

		return prefijoaux + "-" + caracteristica + "-" + numeroTelefono;
	}

	/**
	 * Completa guion.
	 *
	 * @param nro
	 *            the nro
	 * @return the string
	 */
	private static String completaGuion(String nro) {
		if (StringUtils.isEmpty(nro)) {
			return "";
		}

		StringBuilder salida = new StringBuilder();
		if (nro.length() > 4 && nro.indexOf("-") == -1) {
			for (int i = 0; i < nro.length(); i++) {
				if (i == (nro.length() - 4)) {
					salida.append("-").append(nro.charAt(i));
				} else {
					salida.append(nro.charAt(i));
				}
			}
		} else {
			salida.append(nro);
		}

		return salida.toString();
	}

}
