package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteCompraVentaDolarDTOTest {
    
    @Test
    public void getViewCompraTest(){
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setFecha(new GregorianCalendar(2017, 4, 12).getTime());
        comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.COMPRA_DOLARES);
        comprobanteDTO.setCtaMedioDePagoPesos("000-063917/0");
        comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        comprobanteDTO.setDestinatario("Credito ciudad Mock");
        comprobanteDTO.setImporteDolares(new BigDecimal(1200));
        comprobanteDTO.setImportePesos(null);
        
        DetalleComprobanteCompraVentaDolarDTO comprobante = new DetalleComprobanteCompraVentaDolarDTO();
        comprobante.setTipoComprobante(TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES);
        comprobante.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_DOLARES);
        comprobante.setCotizacionAplicada(new BigDecimal("14"));
        comprobante.setSaldoDebitado(new BigDecimal("187"));
        DetalleComprobanteView res = comprobante.getView(comprobanteDTO);
        
        Assert.assertEquals(res.getTipoComprobante(),"CompraVentaDolares");
        
    }

    
    @Test
    public void getViewVentaTest(){
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setFecha(new GregorianCalendar(2017, 4, 12).getTime());
        comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.VENTA_DOLARES);
        comprobanteDTO.setCtaMedioDePagoPesos("000-063917/0");
        comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        comprobanteDTO.setDestinatario("Credito ciudad Mock");
        comprobanteDTO.setImporteDolares(null);
        comprobanteDTO.setImportePesos(new BigDecimal(1200));
        
        DetalleComprobanteCompraVentaDolarDTO comprobante = new DetalleComprobanteCompraVentaDolarDTO();
        comprobante.setTipoComprobante(TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES);
        comprobante.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_DOLARES);
        comprobante.setCotizacionAplicada(new BigDecimal("12"));
        comprobante.setSaldoDebitado(new BigDecimal("11"));
        DetalleComprobanteView res = comprobante.getView(comprobanteDTO);
        
        Assert.assertEquals(res.getTipoComprobante(),"CompraVentaDolares");
    }

    
}
