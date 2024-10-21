/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.utils;

/**
 * Utils para manejo de strings con servicios de perfil.
 *
 * @author Silvina_Luque
 */
public final class PerfilUtils {

	/**
	 * Instantiates a new perfil utils.
	 */
	private PerfilUtils() {
		super();
	}

	/**
	 * Llena con blancos.
	 *
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String llenaConBlancos(int size) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < size; i++) {
			buffer.append(' ');
		}
		return buffer.toString();
	}

	/**
	 * Reemplaza la Ñ por el caracter especial #.
	 *
	 * @param cadena
	 *            the cadena
	 * @return the string
	 */

	public static String str2StrDomicilios(String cadena) {
		String result = "";
		result = cadena.replace("Ñ", "#");
		return result;
	}
}
