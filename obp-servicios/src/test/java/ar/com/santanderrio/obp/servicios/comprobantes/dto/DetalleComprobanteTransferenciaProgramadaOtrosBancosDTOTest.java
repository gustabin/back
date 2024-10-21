package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaProgramadaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteTransferenciaProgramadaOtrosBancosDTOTest {
    @Test
    public void construirDetalle7x24OBPesos() {
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante.setImportePesos(new BigDecimal("12.00"));
        comprobante.setCtaMedioDePagoPesos("12");
        comprobante.setTipoCtaMedioDePagoPesos(TipoCuenta.CAJA_AHORRO_PESOS);
        comprobante.setNecesitaMoneda(false);
        DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
        detalle.setNroCuentaOrigen("123123415412");
        detalle.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_PESOS);
        detalle.setNroCuentaDestino("123123415412");
        detalle.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalle.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "20378668817"));
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_OTROS_BANCOS);
        comprobante.setDetalle(detalle);

        DetalleComprobanteTransferenciaProgramadaView res = (DetalleComprobanteTransferenciaProgramadaView) detalle
                .getView(comprobante);

        Assert.assertNotNull(res);
    }

    @Test
    public void construirDetalle7x24OBDolares() {
        // Es un test para probar el caso en que la mayoria de los campos no se
        // manden(los que accionan condicionales)
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante.setImporteDolares(new BigDecimal("12.00"));
        comprobante.setCtaMedioDePagoDolares("12");
        comprobante.setTipoCtaMedioDePagoDolares(TipoCuenta.CAJA_AHORRO_PESOS);
        comprobante.setNecesitaMoneda(false);
        DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
        detalle.setNroCuentaOrigen("123123415412");
        detalle.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_PESOS);
        detalle.setNroCuentaDestino("123123415412");
        detalle.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalle.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "20378668817"));
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_OTROS_BANCOS);
        comprobante.setDetalle(detalle);

        DetalleComprobanteTransferenciaProgramadaView res = (DetalleComprobanteTransferenciaProgramadaView) detalle
                .getView(comprobante);

        Assert.assertNotNull(res);
        Assert.assertEquals("CBU", res.getTipoCuentaDestino());

    }

    @Test
    public void hashTest() {
        DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));
        detalleComprobanteDTO.setEstado("estado");
        detalleComprobanteDTO.setNroComprobante("2145231234");

        DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalleComprobanteDTO2 = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO2.setDescCuenta("descCuenta");
        detalleComprobanteDTO.setNroComprobante("21452312");
        
        Assert.assertTrue(detalleComprobanteDTO.hashCode() != detalleComprobanteDTO2.hashCode());
        Assert.assertTrue(detalleComprobanteDTO2.hashCode() == detalleComprobanteDTO2.hashCode());
    }

    @Test
    public void equalsTest() {
        DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalleComprobanteDTO = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
        detalleComprobanteDTO.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        detalleComprobanteDTO.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "123456123"));
        detalleComprobanteDTO.setEstado("estado");
        detalleComprobanteDTO.setNroComprobante("1234");

        DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalleComprobanteDTO2 = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
        detalleComprobanteDTO2.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        detalleComprobanteDTO2.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalleComprobanteDTO2.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        detalleComprobanteDTO2.setDescCuenta("descCuenta");
        detalleComprobanteDTO2.setNroComprobante("1234123");
        Assert.assertTrue(!detalleComprobanteDTO.equals(detalleComprobanteDTO2));
        Assert.assertTrue(detalleComprobanteDTO.equals(detalleComprobanteDTO));
    }
}
