/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class ValidationEntity.
 */
public class ValidationEntity extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5207150919962452811L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationEntity.class);

	/** The Constant ERROR_VALIDACION_VIEW. */
	private static final String ERROR_VALIDACION_VIEW = "Error en el objeto de entrada, en el atributo: {}.";

	/** The Constant ERROR_GENERICO. */
	private static final String ERROR_GENERICO = "Error en el objeto de entrada. ";

	/**
	 * Validate.
	 *
	 * @param <E>
	 *            the element type
	 * @param bean
	 *            the bean
	 * @return the boolean
	 */
	public static <E> Boolean validate(E bean) {
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Set<ConstraintViolation<E>> errores = factory.getValidator().validate(bean);
			for (ConstraintViolation<E> cv : errores) {
				LOGGER.error(ERROR_VALIDACION_VIEW, cv.getPropertyPath());
				LOGGER.error("{}.", cv.getMessage());
			}
			return CollectionUtils.isEmpty(errores);
		} catch (Exception e) {
			LOGGER.error(ERROR_GENERICO, e);
			return Boolean.FALSE;
		}
	}

}