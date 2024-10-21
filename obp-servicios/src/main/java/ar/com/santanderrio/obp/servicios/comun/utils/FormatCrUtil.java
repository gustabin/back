/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * The Class FormatCrUtil.
 */
public class FormatCrUtil {

	/** The ci. */
	private String ci = "A";

	/** The cf. */
	private String cf = "A";

	/** The cs. */
	private String cs = "A";

	/** The e. */
	private int e = 3;

	/** The n. */
	private int n = 391;

	/** The long pin. */
	private static int longPin = 8;

	/** The long dni. */
	private static int longDni = 11;

	/** The long usr. */
	private static int longUsr = 20;

	/** The fill pin. */
	private static String fillPin = "0";

	/** The fill dni. */
	private static String fillDni = "0";

	/** The fill usr. */
	private static String fillUsr = " ";

	/** The f. */
	private int f;

	/** The g. */
	private int g;

	/** The c. */
	private int c;
	
	

	/**
	 * Cripto enc.
	 *
	 * @param tcstring
	 *            the tcstring
	 * @return the string
	 */
	public String criptoEnc(String tcstring) {
		String lcstring = tcstring;
		StringBuilder lcencrypt = new StringBuilder("");
		for (int i = 0; i < lcstring.length(); i++) {
			lcencrypt.append(criptoEncchar(lcstring.substring(i), e, n));
			lcencrypt.append(cs);
		}
		return lcencrypt.toString();
	}

	/**
	 * Cripto encchar.
	 *
	 * @param tcchar
	 *            the tcchar
	 * @param e
	 *            the e
	 * @param n
	 *            the n
	 * @return the integer
	 */
	private Integer criptoEncchar(String tcchar, int e, int n) {
		c = criptoASC(tcchar);
		if (e % 2 == 0) {
			g = 1;
			for (int i = 1; i <= e / 2; i++) {
				f = (c * c) % n;
				g = (f * g) % n;
			}
		} else {
			g = c;
			for (int i = 1; i <= e / 2; i++) {
				f = (c * c) % n;
				g = (f * g) % n;
			}
		}

		return g;
	}

	/**
	 * Cripto ASC.
	 *
	 * @param tcchar
	 *            the tcchar
	 * @return the int
	 */
	int criptoASC(String tcchar) {
		return Character.codePointAt(tcchar, 0);
	}

	/**
	 * Format PIN.
	 *
	 * @param pin
	 *            the pin
	 * @return the string
	 */
	public String formatPIN(String pin) {
		if (pin.length() > longPin) {
			return pin.substring(pin.length() - longPin);
		} else {
			return completarCarIzq(pin, fillPin, String.valueOf(longPin));
		}
	}

	/**
	 * Format DNI.
	 *
	 * @param dni
	 *            the dni
	 * @return the string
	 */
	public String formatDNI(String dni) {
		if (dni.length() > longDni) {
			return dni.substring(dni.length() - longDni);
		} else {
			return this.completarCarIzq(dni, fillDni, String.valueOf(longDni));
		}
	}

	/**
	 * Format USR.
	 *
	 * @param usr
	 *            the usr
	 * @return the string
	 */
	public String formatUSR(String usr) {
		if (usr.length() > longUsr) {
			return usr.substring(usr.length() - longUsr);
		} else {
			return this.completarCarDer(usr, fillUsr, String.valueOf(longUsr));
		}
	}

	/**
	 * Completar car izq.
	 *
	 * @param s
	 *            the s
	 * @param c
	 *            the c
	 * @param l
	 *            the l
	 * @return the string
	 */
	private String completarCarIzq(String s, String c, String l) {
		String res = s;

		while (res.length() < Double.valueOf(l)) {
			res = c + res;
		}
		return res;
	}

	/**
	 * Completar car der.
	 *
	 * @param s
	 *            the s
	 * @param c
	 *            the c
	 * @param l
	 *            the l
	 * @return the string
	 */
	private String completarCarDer(String s, String c, String l) {
		StringBuilder res = new StringBuilder(s);
		while (res.length() < Double.valueOf(l)) {
			res.append(c);
		}
		return res.toString();
	}

	/**
	 * Gets the ci.
	 *
	 * @return the ci
	 */
	String getCI() {
		return this.ci;
	}

	/**
	 * Gets the cf.
	 *
	 * @return the cf
	 */
	String getCF() {
		return this.cf;
	}

	/**
	 * Gets the cs.
	 *
	 * @return the cs
	 */
	String getCS() {
		return this.cs;
	}
	
	public static String formatearPesosDosDecimales(double monto) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.'); 
		DecimalFormat formateadorPesos = new DecimalFormat("#,##0.##$",otherSymbols);
		return formateadorPesos.format(monto);
	}
	
	public static String formatearPorcentajeSinDecimales(int porcentaje) {
		DecimalFormat formateadorPorcentaje = new DecimalFormat("###"); 
		return formateadorPorcentaje.format(porcentaje) + "%";
	}

}
