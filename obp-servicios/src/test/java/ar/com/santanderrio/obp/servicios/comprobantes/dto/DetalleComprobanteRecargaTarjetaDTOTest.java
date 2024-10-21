package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteRecargaTarjetaDTOTest {

    @Test
    public void construirDetalleScompTarjetaRecargablePesos(){
        ComprobanteDTO detalle = new ComprobanteDTO();
        detalle.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        detalle.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        DetalleComprobanteScompRecargaTarjetaRecargableDTO detalleComprobanteDTO = new DetalleComprobanteScompRecargaTarjetaRecargableDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalle.setDetalle(detalleComprobanteDTO);
        detalle.setImportePesos(new BigDecimal("12"));
        DetalleComprobanteView detalleView = detalleComprobanteDTO.getView(detalle);
        Assert.assertNotNull(detalleView);
        Assert.assertEquals("Cuenta única", detalleView.getTipoCuentaOrigen());
    }
    
    @Test
    public void construirDetalleScompTarjetaRecargableDolares(){
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        comprobante.setCtaMedioDePagoPesos("CUENTA_UNICA");
        comprobante.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        comprobante.setTipoCtaMedioDePagoDolares(TipoCuenta.CUENTA_UNICA);
        DetalleComprobanteScompRecargaTarjetaRecargableDTO detalleComprobanteDTO = new DetalleComprobanteScompRecargaTarjetaRecargableDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        comprobante.setDetalle(detalleComprobanteDTO);
        comprobante.setImporteDolares(new BigDecimal("12"));
        DetalleComprobanteView detalleView = detalleComprobanteDTO.getView(comprobante);
        Assert.assertNotNull(detalleView);
        Assert.assertEquals("Cuenta única", detalleView.getTipoCuentaOrigen());
    }
    
    @Test
    public void hashTest(){
        DetalleComprobanteScompRecargaTarjetaRecargableDTO detalleComprobanteDTO = new DetalleComprobanteScompRecargaTarjetaRecargableDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setImporteAcreditado("importeAcreditado");
   
        DetalleComprobanteScompRecargaTarjetaRecargableDTO detalleComprobanteDTO2 = new DetalleComprobanteScompRecargaTarjetaRecargableDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setComisionTotal("comision");
        
        Assert.assertTrue(detalleComprobanteDTO.hashCode() != detalleComprobanteDTO2.hashCode());
        Assert.assertTrue(detalleComprobanteDTO2.hashCode() == detalleComprobanteDTO2.hashCode());
    }
    
    @Test
    public void equalsTest(){
        DetalleComprobanteScompRecargaTarjetaRecargableDTO detalleComprobanteDTO = new DetalleComprobanteScompRecargaTarjetaRecargableDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setImporteAcreditado("importeAcreditado");
   
        DetalleComprobanteScompRecargaTarjetaRecargableDTO detalleComprobanteDTO2 = new DetalleComprobanteScompRecargaTarjetaRecargableDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setComisionTotal("comision");
        
        Assert.assertTrue(!detalleComprobanteDTO.equals(detalleComprobanteDTO2));
        Assert.assertTrue(detalleComprobanteDTO.equals(detalleComprobanteDTO));
    }
}
