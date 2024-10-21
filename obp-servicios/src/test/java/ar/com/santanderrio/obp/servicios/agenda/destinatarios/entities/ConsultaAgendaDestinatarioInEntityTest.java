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

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.LlamadaAgendamientoEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class ConsultaAgendaDestinatarioInEntityTest.
 *
 * @author florencia.n.martinez
 */
public class ConsultaAgendaDestinatarioInEntityTest {

    /**
     * Gets the consulta agenda destinatario in entity.
     *
     * @return the consulta agenda destinatario in entity test
     */
    @Test
    public void getConsultaAgendaDestinatarioInEntityTest() {
        ConsultaAgendaDestinatarioInEntity inEntity = obtenerInEntity();
        Boolean r = validate(inEntity);
        Assert.assertTrue(r);
        Assert.assertTrue(inEntity.getTipoConsulta().matches("P|L"));
        Assert.assertTrue(inEntity.getLlamada().matches("PR|CN"));
        Assert.assertTrue(inEntity.getTipoCuentaCredito().matches("^00|01|02|03|04|09|10|  $"));
        Assert.assertTrue(inEntity.getSucursalCuentaCredito().matches("^[a-zA-Z0-9]{4}|    $"));
        Assert.assertTrue(inEntity.getNumeroCuentaCredito().matches("^[a-zA-Z0-9]{12}|[ ]{12}$"));
        Assert.assertTrue(inEntity.getCbu().matches("^[a-zA-Z0-9]{22}|[ ]{22}$"));
        Assert.assertTrue(inEntity.getTipoDocumentoDestinatario().matches("^[a-zA-Z0-9]{2}|[ ]{2}$"));
        Assert.assertTrue(inEntity.getDocumentoDestino().matches("^[a-zA-Z0-9]{11}|[ ]{11}$-"));
        Assert.assertTrue(inEntity.getTipoAgenda().matches("RIO|OB |EXT|TOD"));
        Assert.assertEquals(inEntity.getRealizarRellamado(), Boolean.TRUE);
    }

    /**
     * Validate.
     *
     * @param bean
     *            the bean
     * @return the boolean
     */
    private Boolean validate(ConsultaAgendaDestinatarioInEntity bean) {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<ConsultaAgendaDestinatarioInEntity>> errores = validator.validate(bean);
            for (ConstraintViolation<ConsultaAgendaDestinatarioInEntity> cv : errores) {
                System.out.println(cv.getMessage());
            }
            return CollectionUtils.isEmpty(errores);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene in entity.
     *
     * @return the consulta agenda destinatario in entity
     */
    private ConsultaAgendaDestinatarioInEntity obtenerInEntity() {
        ConsultaAgendaDestinatarioInEntity inEntity = new ConsultaAgendaDestinatarioInEntity();
        inEntity.setCliente(ClienteMock.completarInfoCliente());
        inEntity.setLlamada(LlamadaAgendamientoEnum.CONTINUAR.getCampo());
        inEntity.setTipoCuentaCredito("01");
        inEntity.setSucursalCuentaCredito("0123");
        inEntity.setNumeroCuentaCredito("001234567899");
        inEntity.setCbu("                      ");
        inEntity.setTipoDocumentoDestinatario("CU");
        inEntity.setDocumentoDestino("00123451234");
        inEntity.setTipoAgenda(TipoAgendaEnum.AGENDA_RIO.getCampo());
        return inEntity;
    }
}
