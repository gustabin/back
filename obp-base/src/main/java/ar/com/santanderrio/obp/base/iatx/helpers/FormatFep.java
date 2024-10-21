package ar.com.santanderrio.obp.base.iatx.helpers;

// TODO: Auto-generated Javadoc
/**
 * The Class FormatFep.
 */
public final class FormatFep {

	/**
	 * Instantiates a new format fep.
	 */
	private FormatFep() {
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
	/*
	 * Completa un entero con ceros a izquierda
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
	 * Fill str.
	 *
	 * @param s
	 *            the s
	 * @param size
	 *            the size
	 * @return the string
	 */
	/*
	 * Completa un string con espacios a la derecha
	 */
	public static String fillStr(String s, int size) {
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
}
