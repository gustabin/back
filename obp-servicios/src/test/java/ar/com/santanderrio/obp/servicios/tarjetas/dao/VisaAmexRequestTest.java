/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.VisaAmexRequest;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenVisaAmexFactory;

/**
 * @author sabrina.cis
 *
 */
public class VisaAmexRequestTest {
    /** The tarjeta DAO. */
    @InjectMocks
    private VisaAmexRequest visaAmexRequest = new VisaAmexRequest();

    /** The sign. */
    @Mock
    private Sign sign;

    /** The token visa amex factory. */
    @Mock
    private TokenVisaAmexFactory tokenVisaAmexFactory;

    /**
     * Iniciar mocks.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws BusinessException
     *             the business exception
     */
    @Before
    public void iniciarMocks() throws IllegalAccessException, BusinessException {

        FieldUtils.writeDeclaredField(visaAmexRequest, "fechaAnterior", "180", true);
        FieldUtils.writeDeclaredField(visaAmexRequest, "appEncoding", "UTF-8", true);

    }

    /**
     * Valida la fecha para que se arme 180 dias antes del dia actual.
     */
    @Test
    @Ignore
    public void asignarParametrosParaComprobantesTest() {
        MultiValueMap<String, String> parametersv = new LinkedMultiValueMap<String, String>();
        TransaccionDTO dto = new TransaccionDTO();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 6);
        dto.setFechaDesde(cal.getTime());
        dto.setFechaHasta(new Date());
        visaAmexRequest.asignarParametrosParaComprobantes(parametersv, dto, new Cliente());     
       
		SimpleDateFormat sdf = new SimpleDateFormat("M");
        Assert.assertEquals(sdf.format(dto.getFechaDesde()), parametersv.get("mesDesde").get(0));
    }
}
