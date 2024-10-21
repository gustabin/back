/**
 * 
 */
package ar.com.santanderrio.obp.base.signer.token;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Los tipos de los atributos deben ser String, sino no se agregan a generado.
 * 
 * @author sergio.e.goldentair
 *
 */
public class StringToken extends Token {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StringToken.class);

	/**
	 * Instantiates a new string token.
	 */
	public StringToken() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.base.signer.Token#tokenToString()
	 */
	@Override
	public String toString() {
		StringBuilder salida = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(StringTokenFormat.class)) {
				field.setAccessible(true);
				obtenerValor(field, salida);
			} else {
				LOGGER.info(
						"El atributo {} de tipo {}, no tiene la marca StringTokenFormat para ser incluido en la salida.",
						field.getName(), field.getType());
			}
		}
		return salida.toString();
	}

	/**
	 * Cargar el valor del atributo si es del tipo string y no es nulo.
	 *
	 * @param field
	 *            the field
	 * @param salida
	 *            the salida
	 */
	private void obtenerValor(Field field, StringBuilder salida) {
		try {
			Object atributo = field.get(this);
			if (atributo != null && field.getType().equals(String.class)) {
				salida.append(field.getAnnotation(StringTokenFormat.class).value()).append(atributo).append("\r\n");
			} else {
				LOGGER.info("No se pudo agregar a la salida el atributo {} de tipo {}.", field.getName(),
						field.getType());
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("El argumento pasado para obtener el valor del field {} es ilegal", field.getName(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error("Se intenta acceder al field {} de manera incorrecta", field.getName(), e);
		}
	}

}
