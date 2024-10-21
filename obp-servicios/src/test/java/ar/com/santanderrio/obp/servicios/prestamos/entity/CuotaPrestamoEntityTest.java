/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.servicios.prestamos.mock.CuotaPrestamoEntityMock;

/**
 * The Class CuotaPrestamoEntityTest.
 *
 * @author florencia.n.martinez
 */
public class CuotaPrestamoEntityTest {

    /**
     * Gets the cuota prestamo entity test.
     *
     * @return the cuota prestamo entity test
     */
    @Test
    public void getCuotaPrestamoEntityTest() {
        CuotaPrestamoEntity entity = CuotaPrestamoEntityMock.completarInfo();
        Boolean r = validate(entity);
        Assert.assertTrue(r);
        Assert.assertTrue(entity.getTae().matches("^[0-9]{9}$"));
    }

    /**
     * Gets the cuota prestamo entity error test.
     *
     * @return the cuota prestamo entity error test
     */
    @Test
    public void getCuotaPrestamoEntityErrorTest() {
        CuotaPrestamoEntity entity = CuotaPrestamoEntityMock.completarInfo();
        entity.setTae("2016-02-10");
        Boolean r = validate(entity);
        Assert.assertFalse(r);
        Assert.assertFalse(entity.getTae().matches("^[0-9]{9}$"));
    }

    /**
     * Validate.
     *
     * @param bean
     *            the bean
     * @return the boolean
     */
    private Boolean validate(CuotaPrestamoEntity bean) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<CuotaPrestamoEntity>> errores = validator.validate(bean);
            for (ConstraintViolation<CuotaPrestamoEntity> cv : errores) {
                System.out.println(cv.getMessage());
            }
            return CollectionUtils.isEmpty(errores);
        } catch (Exception e) {
            return false;
        }
    }
}
