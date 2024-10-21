/**
 * 
 */
package ar.com.santanderrio.obp.base.signer.token;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class PipeToken.
 *
 * @author sergio.e.goldentair
 */
public class PipeToken extends Token {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PipeToken.class);

	/**
	 * Instantiates a new pipe token.
	 */
	public PipeToken() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder tokenAFirmar = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			if (i == 0) {
				tokenAFirmar.append(obtenerValue(fields[i]));
			} else {
				tokenAFirmar.append("|").append(obtenerValue(fields[i]));
			}
		}
		return tokenAFirmar.toString();
	}

	/**
	 * Obtener value.
	 *
	 * @param field
	 *            the field
	 * @return the string
	 */
	private String obtenerValue(Field field) {
		try {
			String atributoValue = (String) field.get(this);
			if (atributoValue != null) {
				return atributoValue;
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("El argumento pasado para obtener el valor del field {} es ilegal", field.getName(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Se intenta acceder al field {} de manera incorrecta", field.getName(), e);
		}
		return "";
	}

}
