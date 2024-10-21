/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.clientes.entities;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;

/**
 * The Class ClienteTest.
 *
 * @author Jonatan_Bocian
 */
@RunWith(MockitoJUnitRunner.class)
public class ClienteTest {

    /** The cuentas. */
    private List<Cuenta> cuentas;

    /** The nro cuenta 1. */
    private String nroCuenta1 = "00003777920020216960";

    /** The nro cuenta 2. */
    private String nroCuenta2 = "00004050710080531614";

    /**
     * Inits the.
     */
    @Before
    public void init() {
        cuentas = new ArrayList<Cuenta>();
        Cuenta cuentaTarjeta1 = new Cuenta();
        Cuenta cuentaTarjeta2 = new Cuenta();
        cuentaTarjeta1.setNroTarjetaCredito(nroCuenta1);
        cuentaTarjeta1.setTipoCuenta(TipoCuentaTarjeta.TIPOCTA_AMEX.getCodigo());
        cuentaTarjeta1.setTipoCuentaEnum(TipoCuenta.AMEX);
        
        cuentaTarjeta2.setNroTarjetaCredito(nroCuenta2);
        cuentaTarjeta2.setTipoCuenta(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        cuentaTarjeta2.setTipoCuentaEnum(TipoCuenta.VISA);
        
        cuentas.add(cuentaTarjeta1);
        cuentas.add(cuentaTarjeta2);
    }

    /**
     * Test.
     */
    @Test
    public void test() {
        Cliente cliente = new Cliente();
        Assert.assertNotNull(cliente);
    }

    /**
     * Gets the tarjeta para amex ok.
     *
     * @return the tarjeta para amex ok
     */
    @Test
    public void getTarjetaParaAmexOk() {

        // When
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);
        String nroTarjetaEval = "American Express XXXX-21696";

        // Then
        Cuenta cuentaTarjeta = cliente.getTarjeta(nroTarjetaEval);

        // Expected
        Assert.assertNotNull(cuentaTarjeta);
        Assert.assertTrue("La cuenta tarjeta encontrada no corresponde",
                nroCuenta1.equals(cuentaTarjeta.getNroTarjetaCredito()));
    }

    /**
     * Gets the tarjeta para visa OK.
     *
     * @return the tarjeta para visa OK
     */
    @Test
    public void getTarjetaParaVisaOK() {

        // When
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);
        String nroTarjetaEval = "VISA XXXX-1614";

        // Then
        Cuenta cuentaTarjeta = cliente.getTarjeta(nroTarjetaEval);

        // Expected
        Assert.assertNotNull(cuentaTarjeta);
        Assert.assertTrue("La cuenta tarjeta encontrada no corresponde",
                nroCuenta2.equals(cuentaTarjeta.getNroTarjetaCredito()));
    }

    /**
     * Gets the tarjeta para amex error tarjeta no encontrada.
     *
     * @return the tarjeta para amex error tarjeta no encontrada
     */
    @Test
    public void getTarjetaParaAmexErrorTarjetaNoEncontrada() {

        // When
        Cliente cliente = new Cliente();
        String nroTarjetaEval = "American Express XXXX-21697";
        cliente.setCuentas(cuentas);

        // Then
        Cuenta cuentaTarjeta = cliente.getTarjeta(nroTarjetaEval);

        // Expected
        Assert.assertNull(cuentaTarjeta);
    }

    /**
     * Gets the tarjeta para visa error tarjeta no encontrada.
     *
     * @return the tarjeta para visa error tarjeta no encontrada
     */
    @Test
    public void getTarjetaParaVisaErrorTarjetaNoEncontrada() {

        // When
        Cliente cliente = new Cliente();
        String nroTarjetaEval = "VISA XXXX-1615";
        cliente.setCuentas(cuentas);

        // Then
        Cuenta cuentaTarjeta = cliente.getTarjeta(nroTarjetaEval);

        // Expected
        Assert.assertNull(cuentaTarjeta);

    }

    /**
     * Gets the tarjeta nro tarjeta null.
     *
     * @return the tarjeta nro tarjeta null
     */
    @Test
    public void getTarjetaNroTarjetaNull() {

        // When
        Cliente cliente = new Cliente();
        String nroTarjetaEval = "";
        cliente.setCuentas(cuentas);

        // Then
        Cuenta cuentaTarjeta = cliente.getTarjeta(nroTarjetaEval);

        // Expected
        Assert.assertNull(cuentaTarjeta);

    }

    /**
     * Obtener prestamos disponibles.
     */
    @Test
    public void obtenerPrestamosDisponibles() {
        Cliente cliente = new Cliente();

    }

}
