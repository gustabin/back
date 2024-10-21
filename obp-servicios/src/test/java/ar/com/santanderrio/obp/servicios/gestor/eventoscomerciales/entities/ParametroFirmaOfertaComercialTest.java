/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class ParametroFirmaOfertaComercialTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class ParametroFirmaOfertaComercialTest {

    /**
     * Obtener parametro CU dls test.
     */
    @Test
    public void obtenerParametroCUDlsTest() {
        Cliente cliente = new Cliente();
        cliente.setNup("0123342");
        cliente.setUsuarioRacf("usuario");
        cliente.setPasswordRacf("password");
        cliente.setDni("23124567");
        cliente.setTipoDocumento("N ");
        cliente.setTipoPersona("L");
        cliente.setFechaNacimiento("12/09/1978");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setIndex(0);
        cuenta.setTipoCuentaSinUnificar("10");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        cuenta.setNroSucursal("0033");
        cuenta.setNroOrdenFirmante("001");
        cuenta.setCodigoAplicacion("INFI");
        cuenta.setCodigoTitularidad("TI");
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
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        ParametroFirmaOfertaComercial parametro = new ParametroFirmaOfertaComercial(cliente, "desktop");

        Assert.assertNotNull(parametro);
        Assert.assertEquals("2", parametro.getCantCuentas());
    }

    /**
     * Obtener parametro CU pesos test.
     */
    @Test
    public void obtenerParametroCUPesosTest() {
        Cliente cliente = new Cliente();
        cliente.setNup("0123342");
        cliente.setUsuarioRacf("usuario");
        cliente.setPasswordRacf("password");
        cliente.setDni("23124567");
        cliente.setTipoDocumento("N ");
        cliente.setTipoPersona("L");
        cliente.setFechaNacimiento("12/09/1978");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setIndex(0);
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta.setNroSucursal("0033");
        cuenta.setNroOrdenFirmante("001");
        cuenta.setCodigoAplicacion("INFI");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setClaseCuenta("N");
        cuenta.setCodigoTitularidad("TI");
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
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        ParametroFirmaOfertaComercial parametro = new ParametroFirmaOfertaComercial(cliente, "desktop");

        Assert.assertNotNull(parametro);
        Assert.assertEquals("2", parametro.getCantCuentas());
    }

}
