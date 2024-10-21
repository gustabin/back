package ar.com.santanderrio.obp.servicios.cuentas.bo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.CuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CuentasBOHasCuentasActivasTest.
 *
 * @author B039543
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasBOHasCuentasActivasTest {

    /** The cuentas BO. */
    private CuentaBOImpl cuentasBO = new CuentaBOImpl();

    /**
     * hasCuentasMonetariasActivas test.
     */
    @Test
    public void hasCuentasMonetariasActivas() {

        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        Cuenta cuenta4 = new Cuenta();
        Cuenta cuenta5 = new Cuenta();

        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta2.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
        cuenta3.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        cuenta4.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        cuenta5.setTipoCuentaEnum(TipoCuenta.CAJA_DE_SEGURIDAD);
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cuentas.add(cuenta4);
        cuentas.add(cuenta5);
        cliente.setCuentas(cuentas);

        Boolean hasCuentasMonetariasActivas = cuentasBO.hasCuentasMonetariasActivas(cliente);

        assertTrue(hasCuentasMonetariasActivas);
    }

    /**
     * hasCuentasMonetariasActivas test.
     */
    @Test
    public void notHasCuentasMonetariasActivas() {

        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();

        cuenta1.setTipoCuenta("15");
        cuentas.add(cuenta1);
        cliente.setCuentas(cuentas);

        Boolean hasCuentasMonetariasActivas = cuentasBO.hasCuentasMonetariasActivas(cliente);

        assertTrue(!hasCuentasMonetariasActivas);
    }

}
