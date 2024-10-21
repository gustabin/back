package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteDebitoAutomaticoTarjetaDTOTest {
    
    @Test
    public void obtenerViewPagoTarjetaVISAPesos(){
        DetalleComprobanteDebitoAutomaticoTarjetaDTO dto = new DetalleComprobanteDebitoAutomaticoTarjetaDTO();
        dto.setTituloComprobante("Pago de tarjeta VISA");
        dto.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setDetalle(dto);
        comprobante.setFecha(new Date());
        comprobante.setImportePesos(new BigDecimal("12.00"));
        comprobante.setCtaMedioDePagoPesos("12314891247914");
        comprobante.setDestinatario("juan ortiz");
        
        DetalleComprobanteDebitoAutomaticoTarjetaView view = (DetalleComprobanteDebitoAutomaticoTarjetaView)dto.getView(comprobante);
        Assert.assertEquals("juan ortiz", view.getEmpresa());
        Assert.assertEquals(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(12.00)), view.getImportePesos());
        Assert.assertEquals("12314891247914", view.getMedioDePago());
    }
    
    @Test
    public void obtenerViewPagoTarjetaVISADolares(){
        DetalleComprobanteDebitoAutomaticoTarjetaDTO dto = new DetalleComprobanteDebitoAutomaticoTarjetaDTO();
        dto.setTituloComprobante("Pago de tarjeta VISA");
        dto.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setDetalle(dto);
        comprobante.setFecha(new Date());
        comprobante.setImporteDolares(new BigDecimal("12.00"));
        comprobante.setCtaMedioDePagoDolares("12314891247914");
        comprobante.setDestinatario("juan ortiz");
        
        DetalleComprobanteDebitoAutomaticoTarjetaView view = (DetalleComprobanteDebitoAutomaticoTarjetaView)dto.getView(comprobante);
        Assert.assertEquals("juan ortiz", view.getEmpresa());
        Assert.assertEquals(ISBANStringUtils.formatearSaldoConSigno(new BigDecimal(12.00)), view.getImporteDolares());
        Assert.assertEquals("12314891247914", view.getMedioDePago());
    }
    
    @Test
    public void hashTest(){
        DetalleComprobanteDebitoAutomaticoTarjetaDTO detalleComprobanteDTO = new DetalleComprobanteDebitoAutomaticoTarjetaDTO();
        detalleComprobanteDTO.setTituloComprobante("Pago de tarjeta VISA");
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
        detalleComprobanteDTO.setNombreTarjeta("nombre tarjeta");
   
        DetalleComprobanteDebitoAutomaticoTarjetaDTO detalleComprobanteDTO2 = new DetalleComprobanteDebitoAutomaticoTarjetaDTO();
        detalleComprobanteDTO2.setTituloComprobante("Pago de tarjeta VISA");
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
        detalleComprobanteDTO2.setDescripcionDebito("descDebito");
        
        Assert.assertTrue(detalleComprobanteDTO.hashCode() != detalleComprobanteDTO2.hashCode());
        Assert.assertTrue(detalleComprobanteDTO2.hashCode() == detalleComprobanteDTO2.hashCode());
    }
    
    @Test
    public void equalsTest(){
        DetalleComprobanteDebitoAutomaticoTarjetaDTO detalleComprobanteDTO = new DetalleComprobanteDebitoAutomaticoTarjetaDTO();
        detalleComprobanteDTO.setTituloComprobante("Pago de tarjeta VISA");
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
        detalleComprobanteDTO.setNombreTarjeta("nombre tarjeta");
   
        DetalleComprobanteDebitoAutomaticoTarjetaDTO detalleComprobanteDTO2 = new DetalleComprobanteDebitoAutomaticoTarjetaDTO();
        detalleComprobanteDTO2.setTituloComprobante("Pago de tarjeta VISA");
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
        detalleComprobanteDTO2.setDescripcionDebito("descDebito");
        
        Assert.assertTrue(!detalleComprobanteDTO.equals(detalleComprobanteDTO2));
        Assert.assertTrue(detalleComprobanteDTO.equals(detalleComprobanteDTO));
    }
}
