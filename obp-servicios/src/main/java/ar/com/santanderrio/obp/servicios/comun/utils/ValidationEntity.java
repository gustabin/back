/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

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
 *
 * @author florencia.n.martinez
 */
public class ValidationEntity extends Entity {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5207150919962452811L;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationEntity.class);

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
                LOGGER.info("Error de validacion {}, en el atributo: {}.", bean,cv.getPropertyPath());
                LOGGER.info("{}.", cv.getMessage());
            }
            return CollectionUtils.isEmpty(errores);
        } catch (Exception e) {
            LOGGER.error("Error en el llamado a iatx. ", e);
            return Boolean.FALSE;
        }
    }

}