/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;

/**
 * @author sabrina.cis
 *
 */
public class ComprobanteAltaDestinatarioDTOTest {

    /**
     * Comprobante alta destinatario DTO equal hash string test.
     */
    @Test
    public void comprobanteAltaDestinatarioDTOEqualHashStringTest() {
        ComprobanteAltaDestinatarioDTO dto = new ComprobanteAltaDestinatarioDTO();
        dto.setHora("12");
        Assert.assertNotEquals(dto.hashCode(), 0);
        Assert.assertEquals(
                "ComprobanteAltaDestinatarioDTO[mensajeEfectivizacion=<null>,fecha=<null>,hora=12,nroComprobante=<null>,tieneError=false,tieneErrorCuentaInvalida=false,tieneErrorDestinatarioAgendado=false]",
                dto.toString());

        ComprobanteAltaDestinatarioDTO dto2 = new ComprobanteAltaDestinatarioDTO();

        Assert.assertTrue(!dto.equals(dto2));
        Assert.assertTrue(dto.equals(dto));
    }
    
    
    /**
     * Comprobante alta destinatario DTO equal hash string test.
     */
    @Test
    @Ignore
    public void constructorComprobanteAltaDestinatarioDTO() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("12345");
        outEntity.setFecha("20170331152409");
        outEntity.setHeaderTrama(null);
        outEntity.setHora(null);
        outEntity.setNroComprobante("000023");
        
        ComprobanteAltaDestinatarioDTO dto = new ComprobanteAltaDestinatarioDTO(outEntity);
        Assert.assertNotEquals(dto.hashCode(), 0);
        Assert.assertEquals(
                "ComprobanteAltaDestinatarioDTO[mensajeEfectivizacion=<null>,fecha=Fri Mar 31 00:00:00 ART 2017,hora=<null>,nroComprobante=000023,tieneError=false,tieneErrorCuentaInvalida=false,tieneErrorDestinatarioAgendado=false]",
                dto.toString());

        ComprobanteAltaDestinatarioDTO altaDestinatarioDTO2 = new ComprobanteAltaDestinatarioDTO();

        Assert.assertTrue(!dto.equals(altaDestinatarioDTO2));
        Assert.assertTrue(dto.equals(dto));
    } 
    
}
