package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCVEPView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteIatxPMCVepDTOTest {

    @Test
    public void getViewTest() {
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante.setDestinatario("Visa xxxx-1234");
        comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_PUNTUAL);
        comprobante.setImporteDolares(new BigDecimal("12.00"));
        comprobante.setImportePesos(new BigDecimal("14.50"));
        comprobante.setTipoCtaMedioDePagoDolares(TipoCuenta.CAJA_AHORRO_DOLARES);
        comprobante.setTipoCtaMedioDePagoPesos(TipoCuenta.CAJA_AHORRO_PESOS);
        comprobante.setDetalle(new DetalleComprobanteIatxPagoDeTarjetaCreditoDTO());
        comprobante.getDetalle().setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_PAGO_PUNTUAL);
        comprobante.getDetalle()
                .setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_SANTANDER_RIO.getDetalle());

        DetalleComprobanteIatxPMCVepDTO detalle = new DetalleComprobanteIatxPMCVepDTO();
        detalle.setFechaDePago(new Date());
        detalle.setNroCuentaOrigen("1234567");
        detalle.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_DOLARES);
        detalle.setCuit(new CuitDTO(CuitEnum.CUIT_DESTINATARIO_TRANSFERENCIAS, "20137886657"));
        detalle.setHoraDePago("12:00");
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_VEP);
        DetalleComprobantePMCVEPView view = (DetalleComprobantePMCVEPView) detalle.getView(comprobante);

        Assert.assertEquals("20137886657", view.getCuit());
    }
}
