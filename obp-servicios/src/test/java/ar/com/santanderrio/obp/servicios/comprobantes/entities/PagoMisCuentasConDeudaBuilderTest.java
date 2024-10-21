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
 * The Class PagoMisCuentasConDeudaBuilderTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoMisCuentasConDeudaBuilderTest {

    /**
     * Crear alta PMC con deuda.
     */
    @Test
    public void crearAltaPMCConDeuda() {
        Cliente cliente = new Cliente();
        cliente.setNup("12315123");
        cliente.setDni("37866881");
        cliente.setTipoDocumento("N");

        PagoInEntity pagoInEntity = new PagoInEntity();
        pagoInEntity.setEmpresaNombreFantasia("TELECOM");
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

        PagoMisCuentasConDeudaBuilder builder = new PagoMisCuentasConDeudaBuilder(cliente).setPagoInEntity(pagoInEntity)
                .setPagoResponse(pagoResponse);

        AltaComprobanteRequest alta = builder.buildComprobanteRequest();
        Assert.assertEquals("11", alta.getSubTipoComprobante());
    }

    /**
     * Crear alta PMC con deuda pago con TC visa test.
     */
    @Test
    public void crearAltaPMCConDeudaPagoConTCVisaTest() {
        Cliente cliente = ClienteMock.completarInfoCliente();
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setFiid("MVSR");
        nuevoPago.setCodigoPagoElectronico("20378668817");
        nuevoPago.setMonto("150.00");
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
        pagoInEntity.setCodigoEmpresa("MVSR");
        pagoInEntity.setTipoSeleccion("I");
        pagoInEntity.setTipoMonto("3");
        pagoInEntity.setNumeroFactura(StringUtils.repeat(" ", 20));
        pagoInEntity.setMontoMensajeFeedback("$ 150,00");
        pagoInEntity.setEmpresaNombreFantasia("MOVISTAR");
        pagoInEntity.setSucursalCuenta(cuenta.getNroSucursal());
        pagoInEntity.setNumeroCuenta(cuenta.getNroCuentaProducto().substring(4));
        pagoInEntity.setMoneda("ARS");
        pagoInEntity.setFechaDePago(new Date());
        pagoInEntity.setMonto("150");
        pagoInEntity.setIdentificacion("20378668817");
        pagoInEntity.setTipoCuenta("07");
        pagoInEntity.setNroTarjeta("1234567890123456");
        pagoInEntity.setTipoCuentaTC(TipoCuenta.VISA.getCodigo());

        PagoPMC pago = new PagoPMC();
        pago.setCodigoEmpresa("MVSR");
        pago.setComprobantePorServicio("000000002222");
        pago.setEstadoPago(0);
        pago.setFechaHoraBody("20180208110622");
        pago.setIdentificacion("20378668817");
        pago.setMoneda("ARS");
        pago.setMonto("00000000015000");
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

        PagoMisCuentasConDeudaBuilder builder = new PagoMisCuentasConDeudaBuilder(cliente).setPagoInEntity(pagoInEntity)
                .setPagoResponse(pago);

        AltaComprobanteRequest alta = builder.buildComprobanteRequest();

        Assert.assertEquals("38", alta.getSubTipoComprobante());
    }

    /**
     * Crear alta PMC con deuda pago con TC amex test.
     */
    @Test
    public void crearAltaPMCConDeudaPagoConTCAmexTest() {
        Cliente cliente = ClienteMock.completarInfoCliente();
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setFiid("MVSR");
        nuevoPago.setCodigoPagoElectronico("20378668817");
        nuevoPago.setMonto("150.00");
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
        pagoInEntity.setCodigoEmpresa("MVSR");
        pagoInEntity.setTipoSeleccion("I");
        pagoInEntity.setTipoMonto("3");
        pagoInEntity.setNumeroFactura(StringUtils.repeat(" ", 20));
        pagoInEntity.setMontoMensajeFeedback("$ 150,00");
        pagoInEntity.setEmpresaNombreFantasia("MOVISTAR");
        pagoInEntity.setSucursalCuenta(cuenta.getNroSucursal());
        pagoInEntity.setNumeroCuenta(cuenta.getNroCuentaProducto().substring(4));
        pagoInEntity.setMoneda("ARS");
        pagoInEntity.setFechaDePago(new Date());
        pagoInEntity.setMonto("150");
        pagoInEntity.setIdentificacion("20378668817");
        pagoInEntity.setTipoCuenta("42");
        pagoInEntity.setNroTarjeta("1234567891234560");
        pagoInEntity.setTipoCuentaTC(TipoCuenta.AMEX.getCodigo());

        PagoPMC pago = new PagoPMC();
        pago.setCodigoEmpresa("MVSR");
        pago.setComprobantePorServicio("000000002222");
        pago.setEstadoPago(0);
        pago.setFechaHoraBody("20180208110622");
        pago.setIdentificacion("20378668817");
        pago.setMoneda("ARS");
        pago.setMonto("00000000015000");
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

        PagoMisCuentasConDeudaBuilder builder = new PagoMisCuentasConDeudaBuilder(cliente).setPagoInEntity(pagoInEntity)
                .setPagoResponse(pago);

        AltaComprobanteRequest alta = builder.buildComprobanteRequest();

        Assert.assertEquals("38", alta.getSubTipoComprobante());
    }
}
