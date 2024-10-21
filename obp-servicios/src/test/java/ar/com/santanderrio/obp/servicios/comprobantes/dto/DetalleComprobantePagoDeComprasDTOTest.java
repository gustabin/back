package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePagoComprasView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobantePagoDeComprasDTOTest {

    @Test
    public void getViewTest() {
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante.setDestinatario("Visa xxxx-1234");
        comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_PUNTUAL);
        comprobante.setImportePesos(new BigDecimal("14.50"));
        comprobante.setTipoCtaMedioDePagoPesos(TipoCuenta.CAJA_AHORRO_PESOS);

        DetalleComprobantePagoDeComprasDTO detalle = new DetalleComprobantePagoDeComprasDTO();
        detalle.setLabelDinamico("NUMERO DE DNI");
        detalle.setNroBoleta("21911477773576");
        detalle.setNroOrden("2086B00003316");
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_PAGO_PUNTUAL);
        comprobante.setDetalle(detalle);

        DetalleComprobantePagoComprasView view = (DetalleComprobantePagoComprasView) detalle.getView(comprobante);
        Assert.assertEquals("Caja de Ahorro en Pesos", view.getTipoCuentaOrigen());
    }
}
