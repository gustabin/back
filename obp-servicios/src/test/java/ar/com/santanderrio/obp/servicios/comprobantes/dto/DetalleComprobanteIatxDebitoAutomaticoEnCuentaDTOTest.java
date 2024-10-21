package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteDebitoAutomaticoCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTOTest {

    @Test
    public void equalsTest(){
        DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle = new  DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO();
        detalle.setMotivoRechazo("rechazo");
        detalle.setAnotaciones("anotaciones");
        
        DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle2 = new  DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO();
        detalle2.setMotivoRechazo("rechazo");
        detalle2.setAnotaciones("anotaciones2");
        
        Assert.assertTrue(detalle.equals(detalle2));
        
        detalle2.setMotivoRechazo("rechazoOtro");
        Assert.assertTrue(!detalle.equals(detalle2));
        Assert.assertTrue(detalle.equals(detalle));
    }
    
    @Test
    public void hashTest(){
        DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle = new  DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO();
        detalle.setMotivoRechazo("rechazo");
        detalle.setAnotaciones("anotaciones");
        
        DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle2 = new  DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO();
        detalle2.setMotivoRechazo("rechazo");
        detalle2.setAnotaciones("anotaciones2");
        
        Assert.assertTrue(detalle.hashCode() == detalle2.hashCode());
        
        detalle2.setMotivoRechazo("rechazoOtro");
        Assert.assertTrue(detalle.hashCode() != detalle2.hashCode());
        Assert.assertTrue(detalle.hashCode() == detalle.hashCode());
    }
    
    @Test
    public void toStringTest(){
        DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle = new  DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO();
        detalle.setMotivoRechazo("rechazo 125");
        detalle.setAnotaciones("anotaciones");
        
        String res = detalle.toString();
        Assert.assertTrue(res.contains("motivoRechazo=rechazo 125"));
    }
    
    @Test
    public void getViewTest(){
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setFecha(new GregorianCalendar(2017, 4, 12).getTime());
        comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.DEBITO_AUTOMATICO_CUENTA);
        comprobanteDTO.setCtaMedioDePagoPesos("000-063917/0");
        comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        comprobanteDTO.setDestinatario("Credito ciudad Mock");
        comprobanteDTO.setImporteDolares(new BigDecimal(1200));
        comprobanteDTO.setImportePesos(null);
        
        DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle = new  DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO();
        detalle.setMotivoRechazo("rechazo 125");
        detalle.setAnotaciones("anotaciones");
        detalle.setNroCuentaOrigen("000-063917/0");
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_INFORME_DEBITOS_AUTOMATICOS_EN_CUENTA);
        detalle.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_DOLARES);
        detalle.setFechaDePago(new Date());
        detalle.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO,"20378668817"));
        DetalleComprobanteDebitoAutomaticoCuentaView  res = (DetalleComprobanteDebitoAutomaticoCuentaView)detalle.getView(comprobanteDTO);
        Assert.assertEquals(res.getNroCuentaOrigen(), "000-063917/0");
    }
    
    
}
