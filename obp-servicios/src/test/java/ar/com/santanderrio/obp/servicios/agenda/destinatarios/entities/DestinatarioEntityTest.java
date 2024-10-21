/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entities;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.DestinatarioEntityMock;

/**
 * The Class DestinatarioEntityTest.
 *
 * @author florencia.n.martinez
 */
public class DestinatarioEntityTest {

    /**
     * Gets the destinatario entity test.
     *
     * @return the destinatario entity test
     */
    @Test
    public void getDestinatarioEntityTest() {
        DestinatarioEntity entity = DestinatarioEntityMock.completarInfoDestinatarioEntity();
        Boolean r = validate(entity);
        Assert.assertTrue(r);
        Assert.assertTrue(entity.getTipoAgendaOcurrencia().matches("RIO|OB |EXT|TOD"));
        Assert.assertTrue(entity.getTipoCuentaDestinatario().matches("^00|01|02|03|04|09|10|  $"));
        Assert.assertTrue(entity.getSucursalCuentaDestinatario().matches("^[a-zA-Z0-9]{4}|[ ]{4}$"));
        Assert.assertTrue(entity.getNumeroCuentaDestinatario().matches("^[a-zA-Z0-9]{12}|[ ]{12}$"));
        Assert.assertTrue(entity.getCbuDestinatario().matches("^[a-zA-Z0-9]{22}|[ ]{22}$"));
        Assert.assertTrue(entity.getBancoDestinatario().matches("^[a-zA-ZáÁéÉíÍóÓúÚ0-9 ]{50}|[ ]{50}$"));
        Assert.assertTrue(entity.getTipoDocumentoDestinatario().matches("[a-zA-Z0-9]{1}[ ]{1}|[ ]{2}$"));
        Assert.assertTrue(entity.getDocumentoDestinatario().matches("^[a-zA-Z0-9]{11}|[ ]{11}$"));
        Assert.assertTrue(entity.getDescripcionCuentaDestinatario().matches("^[a-zA-Z0-9 ]{30}|[ ]{30}$"));
        Assert.assertTrue(entity.getCaracteristicasCuentaDestinatario().matches("^[a-zA-Z0-9]{10}|[ ]{10}$"));
        Assert.assertTrue(entity.getTitular().matches("^[a-zA-ZáÁéÉíÍóÓúÚñÑ0-9.,& ]{64}|[ ]{64}$"));
        Assert.assertTrue(entity.getDireccionCorreo().matches("(.+)@(.+ )|[ ]{100}"));
        Assert.assertTrue(entity.getTelefonoDestinatario().matches("^[a-zA-Z0-9]{16}|[ ]{16}$"));

        entity.setDireccionCorreo(entity.getDireccionCorreo().substring(3));
        r = validate(entity);
        Assert.assertTrue(!r);
    }

    /**
     * Validate.
     *
     * @param bean
     *            the bean
     * @return the boolean
     */
    private Boolean validate(DestinatarioEntity bean) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<DestinatarioEntity>> errores = validator.validate(bean);
            for (ConstraintViolation<DestinatarioEntity> cv : errores) {
                System.out.println(cv.getMessage());
            }
            return CollectionUtils.isEmpty(errores);
        } catch (Exception e) {
            return false;
        }
    }

}