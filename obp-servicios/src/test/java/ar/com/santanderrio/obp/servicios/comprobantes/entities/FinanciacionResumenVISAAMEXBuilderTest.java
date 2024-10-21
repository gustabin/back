package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

@RunWith(MockitoJUnitRunner.class)
public class FinanciacionResumenVISAAMEXBuilderTest {

    @Test
    public void crearFinanciacionResumenVISABuilder() {
        Cliente cliente = new Cliente();
        cliente.setNup("12315123");
        cliente.setDni("37866881");
        cliente.setTipoDocumento("N");
        
        
        
        FinanciacionResumenVISAAMEXBuilder builder = new FinanciacionResumenVISAAMEXBuilder(cliente);
        AltaComprobanteRequest  builderAlta = builder.buildComprobanteRequest();
        Assert.assertEquals("19",builderAlta.getSubTipoComprobante());
    }
}
