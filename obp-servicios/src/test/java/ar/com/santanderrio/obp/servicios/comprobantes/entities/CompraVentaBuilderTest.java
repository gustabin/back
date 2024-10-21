package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteCompraVentaDolar;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

@RunWith(MockitoJUnitRunner.class)
public class CompraVentaBuilderTest {

    @Test
    public void crearCompraBuilder() {
        Cliente cliente = new Cliente();
        cliente.setNup("12315123");
        cliente.setDni("37866881");
        cliente.setTipoDocumento("N");
        
        ComprobanteCompraVentaDTO comprobanteDTO = new ComprobanteCompraVentaDTO();
        comprobanteDTO.setCuentaDestinoTipo("09");
        comprobanteDTO.setCuentaDestinoNumero("000-123456/7");
        comprobanteDTO.setCuentaOrigenTipo("09");
        comprobanteDTO.setCuentaOrigenNumero("000-123456/7");
        comprobanteDTO.setFechaHoraServicio("20170809151400");
        comprobanteDTO.setEsCompra(true);
        comprobanteDTO.setImportePesosAcreditado(new BigDecimal(12));
        comprobanteDTO.setImporteDolaresDebitado(new BigDecimal(14));
        comprobanteDTO.setCotizacionAplicada(new BigDecimal("17"));
        comprobanteDTO.setNumeroOperacion("123571257");
        comprobanteDTO.setNumeroComprobante("10518275125981A");
        
        Cuenta cuentaDestino = new Cuenta();
        cuentaDestino.setTipoCuenta("09");
        
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setTipoCuenta("02");
        
        ParametrosOperacion parametros = new ParametrosOperacion();
        parametros.setCuentaDestino(cuentaDestino );
        parametros.setCuentaOrigen(cuentaOrigen);
        
        CompraVentaBuilder builder = new CompraVentaBuilder(cliente).setComprobanteDTO(comprobanteDTO ).setParametros(parametros);
        AltaComprobanteRequest alta = builder.buildComprobanteRequest();
        Assert.assertEquals("31", alta.getSubTipoComprobante());
        Assert.assertTrue(((ComprobanteCompraVentaDolar)alta.getComprobante()).getImporteAcreditado().contains("u$s"));
    }
    
    @Test
    public void crearVentaBuilder() {
        Cliente cliente = new Cliente();
        cliente.setNup("12315123");
        cliente.setDni("37866881");
        cliente.setTipoDocumento("N");
        
        ComprobanteCompraVentaDTO comprobanteDTO = new ComprobanteCompraVentaDTO();
        comprobanteDTO.setCuentaDestinoTipo("09");
        comprobanteDTO.setCuentaDestinoNumero("000-123456/7");
        comprobanteDTO.setCuentaOrigenTipo("09");
        comprobanteDTO.setCuentaOrigenNumero("000-123456/7");
        comprobanteDTO.setFechaHoraServicio("20170809151400");
        comprobanteDTO.setEsCompra(false);
        comprobanteDTO.setImportePesosAcreditado(new BigDecimal(12));
        comprobanteDTO.setImporteDolaresDebitado(new BigDecimal(14));
        comprobanteDTO.setCotizacionAplicada(new BigDecimal("17"));
        comprobanteDTO.setNumeroOperacion("123571257");
        comprobanteDTO.setNumeroComprobante("10518275125981A");
        
        Cuenta cuentaDestino = new Cuenta();
        cuentaDestino.setTipoCuenta("09");
        
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setTipoCuenta("02");
        
        ParametrosOperacion parametros = new ParametrosOperacion();
        parametros.setCuentaDestino(cuentaDestino );
        parametros.setCuentaOrigen(cuentaOrigen);
        
        CompraVentaBuilder builder = new CompraVentaBuilder(cliente).setComprobanteDTO(comprobanteDTO ).setParametros(parametros);
        AltaComprobanteRequest alta = builder.buildComprobanteRequest();
        Assert.assertEquals("31", alta.getSubTipoComprobante());
        Assert.assertTrue(((ComprobanteCompraVentaDolar)alta.getComprobante()).getImporteDebitado().contains("u$s"));
    }
}
