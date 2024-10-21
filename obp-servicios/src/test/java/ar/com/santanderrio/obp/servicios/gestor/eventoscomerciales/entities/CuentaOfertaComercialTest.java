/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CuentaOfertaComercialTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentaOfertaComercialTest {

    /**
     * Obtener cuenta oferta comercial test.
     */
    @Test
    public void obtenerCuentaOfertaComercialTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setIndex(0);
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta.setNroSucursal("0033");
        cuenta.setNroOrdenFirmante("001");
        cuenta.setCodigoAplicacion("INFI");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setClaseCuenta("N");
        cuenta.setFormaDeOperar("02");
        cuenta.setNroTarjetaCredito("00000000000000000000");
        cuenta.setEstadoTarjetaCredito("");
        cuenta.setCalidadDeParticipacion("");
        cuenta.setCodigoPaquete("2101");
        cuenta.setClasePaquete("14");
        cuenta.setSaldoCuenta("");
        cuenta.setSaldoCUPesos("77823.03");
        cuenta.setSaldoCUDls("993855.86");
        cuenta.setSaldoParaConformar("00000000000000+");
        cuenta.setDepositoEfectivo("00000000013000");
        cuenta.setSubproductoAltair("001");
        cuenta.setMonedaAltair("ARS");
        cuenta.setGrupoAfinidad("000000");
        cuenta.setOficinaAltair("0033");
        cuenta.setContratoAltair("0000007003612532");
        cuenta.setSucursalPaquete("0033");
        cuenta.setNumeroPaquete("000090000274577");
        cuenta.setConvenioPAS(false);
        cuenta.setHabilitadaParaTransferir(false);
        CuentaOfertaComercial cuentaOferta = new CuentaOfertaComercial(cuenta);

        Assert.assertEquals("09", cuentaOferta.getTipoCuenta());
        Assert.assertEquals("0033", cuentaOferta.getSucCuenta());
        Assert.assertEquals("000001234567", cuentaOferta.getNroCuenta());
        Assert.assertEquals("00000007782303+", cuentaOferta.getSaldoArs());
    }

    /**
     * Obtener cuenta oferta comercial CU dls test.
     */
    @Test
    public void obtenerCuentaOfertaComercialCUDlsTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setIndex(0);
        cuenta.setTipoCuentaSinUnificar("10");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        cuenta.setNroSucursal("0033");
        cuenta.setNroOrdenFirmante("001");
        cuenta.setCodigoAplicacion("INFI");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setClaseCuenta("N");
        cuenta.setFormaDeOperar("02");
        cuenta.setNroTarjetaCredito("00000000000000000000");
        cuenta.setEstadoTarjetaCredito("");
        cuenta.setCalidadDeParticipacion("");
        cuenta.setCodigoPaquete("2101");
        cuenta.setClasePaquete("14");
        cuenta.setSaldoCuenta("");
        cuenta.setSaldoCUPesos("77823.03");
        cuenta.setSaldoCUDls("993855.86");
        cuenta.setSaldoParaConformar("00000000000000+");
        cuenta.setDepositoEfectivo("00000000013000");
        cuenta.setSubproductoAltair("001");
        cuenta.setMonedaAltair("ARS");
        cuenta.setGrupoAfinidad("000000");
        cuenta.setOficinaAltair("0033");
        cuenta.setContratoAltair("0000007003612532");
        cuenta.setSucursalPaquete("0033");
        cuenta.setNumeroPaquete("000090000274577");
        cuenta.setConvenioPAS(false);
        cuenta.setHabilitadaParaTransferir(false);
        CuentaOfertaComercial cuentaOferta = new CuentaOfertaComercial(cuenta);

        Assert.assertEquals("10", cuentaOferta.getTipoCuenta());
        Assert.assertEquals("0033", cuentaOferta.getSucCuenta());
        Assert.assertEquals("000001234567", cuentaOferta.getNroCuenta());
        Assert.assertEquals("00000099385586+", cuentaOferta.getSaldoUds());
    }

}
