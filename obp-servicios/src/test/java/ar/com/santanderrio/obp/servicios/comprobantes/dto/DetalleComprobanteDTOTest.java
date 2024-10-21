package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

@RunWith(MockitoJUnitRunner.class)
public class DetalleComprobanteDTOTest {

    @Test
    public void construirDetallePagoTarjetaVISA() {
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante.setDestinatario("Visa xxxx-1234");
        comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_TARJETA);
        comprobante.setTipoCuentaDestino(TipoCuenta.VISA);
        comprobante.setImporteDolares(new BigDecimal("12.00"));
        comprobante.setImportePesos(new BigDecimal("14.50"));
        comprobante.setDetalle(new DetalleComprobanteIatxPagoDeTarjetaCreditoDTO());
        comprobante.getDetalle().setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PAGO_TARJETA);
        comprobante.getDetalle()
                .setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_SANTANDER_RIO.getDetalle());

        DetalleComprobanteView detalle = comprobante.getDetalle().getView(comprobante);
        Assert.assertEquals("12,00", detalle.getImporteDolares());
        Assert.assertEquals("14,50", detalle.getImportePesos());
    }

    @Test
    public void construirDetallePagoTarjetaAMEX() {
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante.setDestinatario("Visa xxxx-1234");
        comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_TARJETA);
        comprobante.setTipoCuentaDestino(TipoCuenta.AMEX);
        comprobante.setImporteDolares(new BigDecimal("12.00"));
        comprobante.setDetalle(new DetalleComprobanteIatxPagoDeTarjetaCreditoDTO());
        comprobante.getDetalle().setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PAGO_TARJETA);
        comprobante.getDetalle()
                .setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_SANTANDER_RIO.getDetalle());

        DetalleComprobanteView view = comprobante.getDetalle().getView(comprobante);
        Assert.assertEquals("12,00", view.getImporteDolares());
    }

    @Test
    public void construirDetallePagoTarjetaVISASinDolares() {
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(new Date());
        comprobante.setDestinatario("Visa xxxx-1234");
        comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_TARJETA);
        comprobante.setTipoCuentaDestino(TipoCuenta.VISA);
        comprobante.setImportePesos(new BigDecimal("14.50"));
        comprobante.setDetalle(new DetalleComprobanteIatxPagoDeTarjetaCreditoDTO());
        comprobante.getDetalle().setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PAGO_TARJETA);
        comprobante.getDetalle()
                .setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_SANTANDER_RIO.getDetalle());

        DetalleComprobanteView detalle = comprobante.getDetalle().getView(comprobante);
        Assert.assertEquals("14,50", detalle.getImportePesos());
    }

    @Test
    public void equalsTest() {
        CuitDTO cuit = new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "13287665548");

        DetalleComprobanteDTO detalle = new DetalleComprobanteDTO();
        detalle.setAnotaciones("anotaciones");
        detalle.setCuit(cuit);
        detalle.setFechaDePago(new Date());
        detalle.setIdentificacion("identificacion");
        detalle.setImporteDolar(new BigDecimal("12"));
        detalle.setInformacionAdicional("infoAdicional");
        detalle.setMotivoRechazo("rechazo");
        detalle.setNroComprobante("123423415345");
        detalle.setNroCuentaOrigen("1234567");
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES);
        detalle.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_DOLARES);
        detalle.setTituloComprobante("titulo de comprobante");

        CuitDTO cuit2 = new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "13287665548");

        DetalleComprobanteDTO detalle2 = new DetalleComprobanteDTO();
        detalle2.setAnotaciones("anotaciones");
        detalle2.setCuit(cuit2);
        detalle2.setFechaDePago(new Date());
        detalle2.setIdentificacion("identificacion");
        detalle2.setImporteDolar(new BigDecimal("12"));
        detalle2.setInformacionAdicional("infoAdicional");
        detalle2.setMotivoRechazo("rechazo");
        detalle2.setNroComprobante("1234235345");
        detalle2.setNroCuentaOrigen("1235567");
        detalle2.setTipoComprobante(TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES);
        detalle2.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_DOLARES);
        detalle2.setTituloComprobante("titulo de comprobante");

        Assert.assertTrue(!detalle.equals(detalle2));
        Assert.assertTrue(detalle.equals(detalle));
    }

    @Test
    public void hashTest() {
        CuitDTO cuit = new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "13287665548");

        DetalleComprobanteDTO detalle = new DetalleComprobanteDTO();
        detalle.setAnotaciones("anotaciones");
        detalle.setCuit(cuit);
        detalle.setFechaDePago(new Date());
        detalle.setIdentificacion("identificacion");
        detalle.setImporteDolar(new BigDecimal("12"));
        detalle.setInformacionAdicional("infoAdicional");
        detalle.setMotivoRechazo("rechazo");
        detalle.setNroComprobante("123423415345");
        detalle.setNroCuentaOrigen("1234567");
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES);
        detalle.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_DOLARES);
        detalle.setTituloComprobante("titulo de comprobante");

        CuitDTO cuit2 = new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "13287665548");

        DetalleComprobanteDTO detalle2 = new DetalleComprobanteDTO();
        detalle2.setAnotaciones("anotaciones");
        detalle2.setCuit(cuit2);
        detalle2.setFechaDePago(new Date());
        detalle2.setIdentificacion("identificacion");
        detalle2.setImporteDolar(new BigDecimal("12"));
        detalle2.setInformacionAdicional("infoAdicional");
        detalle2.setMotivoRechazo("rechazo");
        detalle2.setNroComprobante("1234235345");
        detalle2.setNroCuentaOrigen("1235567");
        detalle2.setTipoComprobante(TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES);
        detalle2.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_DOLARES);
        detalle2.setTituloComprobante("titulo de comprobante");

        Assert.assertTrue(detalle.hashCode() != detalle2.hashCode());
        Assert.assertTrue(detalle.hashCode() == detalle.hashCode());
    }

    @Test
    public void toStringTest() {
        CuitDTO cuit = new CuitDTO(CuitEnum.CUIT_DESTINATARIO, "13287665548");

        DetalleComprobanteDTO detalle = new DetalleComprobanteDTO();
        detalle.setAnotaciones("anotaciones");
        detalle.setearHora(new Date(), true);
        detalle.setCuit(cuit);
        detalle.setFechaDePago(new Date());
        detalle.setIdentificacion("identificacion");
        detalle.setImporteDolar(new BigDecimal("12"));
        detalle.setInformacionAdicional("infoAdicional");
        detalle.setMotivoRechazo("rechazo");
        detalle.setNroComprobante("123423415345");
        detalle.setNroCuentaOrigen("1234567");
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES);
        detalle.setTipoCuentaOrigen(TipoCuenta.CAJA_AHORRO_DOLARES);
        detalle.setTituloComprobante("titulo de comprobante");

        String res = detalle.toString();

        Assert.assertTrue(res.contains("nroCuentaOrigen= 1234567"));
    }

}
