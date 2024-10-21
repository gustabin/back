/**
 * 
 */
package ar.com.santanderrio.obp.base.webservice.interceptor;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Enmascarar palabras sensibles del inbound | outbound que se logueara por el
 * interceptor para operaciones <b>REST</b>.
 * 
 * @author sergio.e.goldentair
 *
 */
public class RestMaskLoggingUtils {
	
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RestMaskLoggingUtils.class);

	/** Mascara para poder filtrar los datos sensibles del json. */
	private static final String MASK_PATTERN = "\"({})\"( )?:( )?\"(.+?)\"";

	/** Mascara para poder reemplazar los datos sensibles del json. */
	private static final String MASK_PATTERN_REEMPLAZO = "\"{0}\" : \"*****\"";

	/** Mascara para poder filtrar los datos sensibles del form. */
	private static final String MASK_PATTERN_FORM = "({})=[^&\\n]*( )?";

	/** Mascara para poder reemplazar los datos sensibles del form. */
	private static final String MASK_PATTERN_REEMPLAZO_FORM = "{0}=*****";

	/** The Constant MASK_PATTERN_HEADER. */
	private static final String MASK_PATTERN_HEADER = "({})( )?=\\[[^\\]]*\\]";

	/** The Constant MASK_PATTERN_REEMPLAZO_HEADER. */
	private static final String MASK_PATTERN_REEMPLAZO_HEADER = "{0} =[*****]";

	/**
	 * Constructor privado para el utils.
	 */
	private RestMaskLoggingUtils() {
		super();
	}

	/**
	 * Obtener la mascara que se le aplica a la trama a loguear y luego se
	 * enmascara segun la mascara obtenida.
	 *
	 * @param originalLogString
	 *            the original log string
	 * @param palabrasSensibles
	 *            the palabras sensibles
	 * @return the string
	 */
	public static String procesarLogRest(String originalLogString, String[] palabrasSensibles) {
		LOGGER.info("Se procede a manipular el payload Rest");
		String mascara = obtenerMascara(palabrasSensibles, MASK_PATTERN);
		String maskedLog = enmascarar(originalLogString, mascara, MASK_PATTERN_REEMPLAZO);
		String mascaraHeader = obtenerMascara(palabrasSensibles, MASK_PATTERN_HEADER);
		maskedLog = enmascarar(maskedLog, mascaraHeader, MASK_PATTERN_REEMPLAZO_HEADER);
		String mascaraForm = obtenerMascara(palabrasSensibles, MASK_PATTERN_FORM);
		return enmascarar(maskedLog, mascaraForm, MASK_PATTERN_REEMPLAZO_FORM);
	}

	/**
	 * Obtener de las palabras sensibles recibidas la mascara al aplicarle el
	 * pattern de filtro.
	 *
	 * @param palabrasSensibles
	 *            the palabras sensibles
	 * @param maskPattern
	 *            the mask Pattern
	 * @return the string
	 */
	private static String obtenerMascara(String[] palabrasSensibles, String maskPattern) {
		if (ArrayUtils.isNotEmpty(palabrasSensibles)) {
			StringBuilder builder = new StringBuilder();
			for (String palabraSensible : palabrasSensibles) {
				builder.append(maskPattern.replace("{}", palabraSensible.trim()));
				builder.append("|");
			}
			builder.setLength(builder.length() - 1);
			String mascara = builder.toString();
			LOGGER.debug("Se utilizara de filtro la mascara {}.", mascara);
			return mascara;
		}
		return null;
	}

	/**
	 * Si la mascara esta presente se aplica sino se mantiene el log original.
	 *
	 * @param originalLogString
	 *            the original log string
	 * @param mascara
	 *            the mascara
	 * @param maskPatternReemplazo
	 *            the mask Pattern Reemplazo
	 * @return the string
	 */
	private static String enmascarar(String originalLogString, String mascara, String maskPatternReemplazo) {
		if (StringUtils.isNotEmpty(mascara)) {
			Pattern pattern = Pattern.compile(mascara, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(originalLogString);
			String mascaraReemplazo = obtenerMascaraDeReemplazo(matcher, maskPatternReemplazo);
			LOGGER.debug("Se enmascara con el siguiente valor de reemplazo {}.", mascaraReemplazo);
			return matcher.replaceAll(mascaraReemplazo);
		} else {
			LOGGER.debug("No se enmascaro ningun dato sensible.");
			return originalLogString;
		}
	}

	/**
	 * Obtener mascara de reemplazo.
	 *
	 * @param matcher
	 *            the matcher
	 * @param maskPatternReemplazo
	 *            the mask Pattern Reemplazo
	 * @return the string
	 */
	private static String obtenerMascaraDeReemplazo(Matcher matcher, String maskPatternReemplazo) {
		int cantidadMatchs = matcher.groupCount();
		StringBuilder parametros = new StringBuilder();
		for (int match = 0; match < cantidadMatchs; match++) {
			if (match % 2 == 1) {
				parametros.append("$").append(match);
			}
		}
		return MessageFormat.format(maskPatternReemplazo, parametros.toString());
	}

}
