package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;

@RunWith(MockitoJUnitRunner.class)
public class PagoMisCuentasVEPBuilderTest {
    @Test
    public void crearAltaPMCVEP() {
        Cliente cliente = new Cliente();
        cliente.setNup("12315123");
        cliente.setDni("37866881");
        cliente.setTipoDocumento("N");

        PagoInEntity pagoInEntity = new PagoInEntity();
        pagoInEntity.setEmpresaNombreFantasia("UNICEF");
        pagoInEntity.setMonto("150.00");
        pagoInEntity.setIdentificacion("20378668817");
        pagoInEntity.setFechaDePago(new Date());
        pagoInEntity.setTipoCuenta("09");
        pagoInEntity.setSucursalCuenta("000");
        pagoInEntity.setNumeroCuenta("134125125");
        pagoInEntity.setNumeroFactura("12412542335876234875");

        PagoPMC pagoResponse = new PagoPMC();
        pagoResponse.setFechaHoraBody("20170809");
        pagoResponse.setNumeroControl("1248124987");
        pagoResponse.setComprobantePorServicio("210375910827355719");

        PagoMisCuentasVEPBuilder builder = new PagoMisCuentasVEPBuilder(cliente).setPagoInEntity(pagoInEntity)
                .setPagoResponse(pagoResponse).setIdentificacion2("1324516341");

        AltaComprobanteRequest alta = builder.buildComprobanteRequest();
        Assert.assertEquals("13", alta.getSubTipoComprobante());
    }
}
