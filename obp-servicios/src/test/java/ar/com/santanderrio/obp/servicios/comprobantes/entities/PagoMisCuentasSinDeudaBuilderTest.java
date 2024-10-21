package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class PagoMisCuentasSinDeudaBuilderTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoMisCuentasSinDeudaBuilderTest {

    /**
     * Crear alta PMC sin deuda.
     */
    @Test
    public void crearAltaPMCSinDeuda() {
        Cliente cliente = new Cliente();
        cliente.setNup("12315123");
        cliente.setDni("37866881");
        cliente.setTipoDocumento("N");

        PagoInEntity pagoInEntity = new PagoInEntity();
        pagoInEntity.setEmpresaNombreFantasia("UNICEF");
        pagoInEntity.setMonto("150.00");
        pagoInEntity.setIdentificacion("20378668817");
        pagoInEntity.setFechaDePago(new Date());
        pagoInEntity.setTipoCuenta("09");
        pagoInEntity.setSucursalCuenta("000");
        pagoInEntity.setNumeroCuenta("134125125");
        pagoInEntity.setNumeroFactura("12412542335");

        PagoPMC pagoResponse = new PagoPMC();
        pagoResponse.setFechaHoraBody("20170809");
        pagoResponse.setNumeroControl("1248124987");
        pagoResponse.setComprobantePorServicio("210375910827355719");

        PagoMisCuentasSinDeudaBuilder builder = new PagoMisCuentasSinDeudaBuilder(cliente).setPagoInEntity(pagoInEntity)
                .setPagoResponse(pagoResponse);

        AltaComprobanteRequest alta = builder.buildComprobanteRequest();
        Assert.assertEquals("12", alta.getSubTipoComprobante());
    }

    /**
     * Crear alta PMC sin deuda pago con TC visa test.
     */
    @Test
    public void crearAltaPMCSinDeudaPagoConTCVisaTest() {
        Cliente cliente = ClienteMock.completarInfoCliente();
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setFiid("UNCF");
        nuevoPago.setCodigoPagoElectronico("111111");
        nuevoPago.setMonto("20");
        nuevoPago.setFacturaNumero("                    ");
        nuevoPago.setReintentar("true");
        nuevoPago.setIsFromCalendario(Boolean.FALSE);
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000017024994");
        cuenta.setNroSucursal("0037");
        cuenta.setMonedaAltair("ARS");
        cuenta.setTipoCuenta("07");
        cuenta.setNroTarjetaCredito("00004050710032218971");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        PagoInEntity pagoInEntity = new PagoInEntity();
        pagoInEntity.setCodigoEmpresa("UNCF");
        pagoInEntity.setTipoSeleccion("I");
        pagoInEntity.setTipoMonto("3");
        pagoInEntity.setNumeroFactura(StringUtils.repeat(" ", 20));
        pagoInEntity.setMontoMensajeFeedback("$ 20,00");
        pagoInEntity.setEmpresaNombreFantasia("UNICEF");
        pagoInEntity.setSucursalCuenta(cuenta.getNroSucursal());
        pagoInEntity.setNumeroCuenta(cuenta.getNroCuentaProducto().substring(4));
        pagoInEntity.setMoneda("ARS");
        pagoInEntity.setMonto("20");
        pagoInEntity.setIdentificacion("111111");
        pagoInEntity.setTipoCuenta("07");
        pagoInEntity.setNroTarjeta("1234567890123456");
        pagoInEntity.setTipoCuentaTC(TipoCuenta.VISA.getCodigo());

        PagoPMC pago = new PagoPMC();
        pago.setCodigoEmpresa("UNCF");
        pago.setComprobantePorServicio("000000002222");
        pago.setEstadoPago(0);
        pago.setFechaHoraBody("20180208110622");
        pago.setIdentificacion("111111");
        pago.setMoneda("ARS");
        pago.setMonto("00000000002000");
        pago.setNumeroControl("1234");
        pago.setNumeroCuenta("000017024994");
        pago.setNumeroFactura("                    ");
        pago.setOperacionDescripcion("pago");
        pago.setReintentar(Boolean.FALSE);
        pago.setRespuestaOK(Boolean.TRUE);
        pago.setSucursalCuenta("0037");
        pago.setTipoCuenta("07");
        pago.setTipoMonto("0");
        pago.setTipoSeleccion("I");

        PagoMisCuentasSinDeudaBuilder builder = new PagoMisCuentasSinDeudaBuilder(cliente).setPagoInEntity(pagoInEntity)
                .setPagoResponse(pago);

        AltaComprobanteRequest alta = builder.buildComprobanteRequest();

        Assert.assertEquals("36", alta.getSubTipoComprobante());
    }

    /**
     * Crear alta PMC sin deuda pago con TC amex test.
     */
    @Test
    public void crearAltaPMCSinDeudaPagoConTCAmexTest() {
        Cliente cliente = ClienteMock.completarInfoCliente();
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setFiid("UNCF");
        nuevoPago.setCodigoPagoElectronico("111111");
        nuevoPago.setMonto("20");
        nuevoPago.setFacturaNumero("                    ");
        nuevoPago.setReintentar("true");
        nuevoPago.setIsFromCalendario(Boolean.FALSE);
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000017024994");
        cuenta.setNroSucursal("0037");
        cuenta.setMonedaAltair("ARS");
        cuenta.setTipoCuenta("07");
        cuenta.setNroTarjetaCredito("00004050710032218971");
        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        PagoInEntity pagoInEntity = new PagoInEntity();
        pagoInEntity.setCodigoEmpresa("UNCF");
        pagoInEntity.setTipoSeleccion("I");
        pagoInEntity.setTipoMonto("3");
        pagoInEntity.setNumeroFactura(StringUtils.repeat(" ", 20));
        pagoInEntity.setMontoMensajeFeedback("$ 20,00");
        pagoInEntity.setEmpresaNombreFantasia("UNICEF");
        pagoInEntity.setSucursalCuenta(cuenta.getNroSucursal());
        pagoInEntity.setNumeroCuenta(cuenta.getNroCuentaProducto().substring(4));
        pagoInEntity.setMoneda("ARS");
        pagoInEntity.setMonto("20");
        pagoInEntity.setIdentificacion("111111");
        pagoInEntity.setTipoCuenta("07");
        pagoInEntity.setNroTarjeta("1234567890123456");
        pagoInEntity.setTipoCuentaTC(TipoCuenta.AMEX.getCodigo());

        PagoPMC pago = new PagoPMC();
        pago.setCodigoEmpresa("UNCF");
        pago.setComprobantePorServicio("000000002222");
        pago.setEstadoPago(0);
        pago.setFechaHoraBody("20180208110622");
        pago.setIdentificacion("111111");
        pago.setMoneda("ARS");
        pago.setMonto("00000000002000");
        pago.setNumeroControl("1234");
        pago.setNumeroCuenta("000017024994");
        pago.setNumeroFactura("                    ");
        pago.setOperacionDescripcion("pago");
        pago.setReintentar(Boolean.FALSE);
        pago.setRespuestaOK(Boolean.TRUE);
        pago.setSucursalCuenta("0037");
        pago.setTipoCuenta("42");
        pago.setTipoMonto("0");
        pago.setTipoSeleccion("I");

        PagoMisCuentasSinDeudaBuilder builder = new PagoMisCuentasSinDeudaBuilder(cliente).setPagoInEntity(pagoInEntity)
                .setPagoResponse(pago);

        AltaComprobanteRequest alta = builder.buildComprobanteRequest();

        Assert.assertEquals("36", alta.getSubTipoComprobante());
    }
}
