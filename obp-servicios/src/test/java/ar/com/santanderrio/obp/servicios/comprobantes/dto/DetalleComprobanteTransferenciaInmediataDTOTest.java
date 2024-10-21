package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteTransferenciaInmediataDTOTest {

    @Test
    public void construirDetalleScompRioRioPesos() {
        ComprobanteDTO detalle = new ComprobanteDTO();
        detalle.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalle.setDetalle(detalleComprobanteDTO);
        detalle.setImportePesos(new BigDecimal("12"));
        DetalleComprobanteView detalleView = detalleComprobanteDTO.getView(detalle);
        Assert.assertNotNull(detalleView); 
    }

    @Test
    public void construirDetalleScompRioRioDolares() {
        ComprobanteDTO detalle = new ComprobanteDTO();
        detalle.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalle.setDetalle(detalleComprobanteDTO);
        detalle.setImporteDolares(new BigDecimal("12"));
        DetalleComprobanteView detalleView = detalleComprobanteDTO.getView(detalle);
        Assert.assertNotNull(detalleView); 
    }

    @Test
    public void construirDetalleScompRioRioTBancoPesos() {
        ComprobanteDTO detalle = new ComprobanteDTO();
        detalle.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalle.setDetalle(detalleComprobanteDTO);
        detalle.setImportePesos(new BigDecimal("12"));
        DetalleComprobanteView detalleView = detalleComprobanteDTO.getView(detalle);
        Assert.assertNotNull(detalleView); 
    }

    @Test
    public void construirDetalleScompRioOBDolares() {
        ComprobanteDTO detalle = new ComprobanteDTO();
        detalle.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));
        detalle.setDetalle(detalleComprobanteDTO);
        detalle.setImporteDolares(new BigDecimal("12"));
        DetalleComprobanteView detalleView = detalleComprobanteDTO.getView(detalle);
        Assert.assertNotNull(detalleView); 
    }

    @Test
    public void construirDetalleScompRioOBPesos() {
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));
        comprobante.setDetalle(detalleComprobanteDTO);
        comprobante.setImportePesos(new BigDecimal("12"));
        DetalleComprobanteView detalleView = detalleComprobanteDTO.getView(comprobante);
        Assert.assertNotNull(detalleView);
    }

    @Test
    public void hashTest() {
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));
        detalleComprobanteDTO.setRecordatorio("recordatorio");
        detalleComprobanteDTO.setNroComprobante("124517");
        
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO2 = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO2.setNroComprobante("1241241517");
        
        Assert.assertTrue(detalleComprobanteDTO.hashCode() != detalleComprobanteDTO2.hashCode());
        Assert.assertTrue(detalleComprobanteDTO2.hashCode() == detalleComprobanteDTO2.hashCode());
    }
    
    @Test
    public void hashTest2() {
        ComprobanteDTO comprobante1 = new ComprobanteDTO();
        comprobante1.setFecha(new Date());
        comprobante1.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        comprobante1.setImportePesos(new BigDecimal("100.00"));
        comprobante1.setDestinatario("Freyer, Daniela Soledad");
        comprobante1.setCtaMedioDePagoPesos("033-361253/2");
        comprobante1.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA_PESOS);
        comprobante1.setTieneError(false);
        
        ComprobanteDTO comprobante2 = new ComprobanteDTO();
        comprobante2.setFecha(new Date());
        comprobante2.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
        comprobante2.setImportePesos(new BigDecimal("100.00"));
        comprobante2.setDestinatario("Freyer, Daniela Soledad");
        comprobante2.setCtaMedioDePagoPesos("033-361253/2");
        comprobante2.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA_PESOS);
        comprobante2.setTieneError(false);
        
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));
        detalleComprobanteDTO.setRecordatorio("recordatorio");
        detalleComprobanteDTO.setNroComprobante("124517");
        comprobante1.setDetalle(detalleComprobanteDTO);
        
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO2 = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO2.setNroComprobante("1241241517");
        comprobante2.setDetalle(detalleComprobanteDTO2);
        
        Assert.assertTrue(detalleComprobanteDTO.hashCode() != detalleComprobanteDTO2.hashCode());
        Assert.assertTrue(detalleComprobanteDTO2.hashCode() == detalleComprobanteDTO2.hashCode());
        Assert.assertTrue(comprobante1.hashCode() != comprobante2.hashCode());
    }

    @Test
    public void equalsTest() {
        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));
        detalleComprobanteDTO.setRecordatorio("recordatorio");
        detalleComprobanteDTO.setNroComprobante("124517");

        DetalleComprobanteTransferenciaInmediataDTO detalleComprobanteDTO2 = new DetalleComprobanteTransferenciaInmediataDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO2.setNroComprobante("2132");
        
        Assert.assertTrue(!detalleComprobanteDTO.equals(detalleComprobanteDTO2));
        Assert.assertTrue(detalleComprobanteDTO.equals(detalleComprobanteDTO));
    }
    
    
}
