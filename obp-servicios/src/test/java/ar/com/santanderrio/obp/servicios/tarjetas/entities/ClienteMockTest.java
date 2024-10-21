/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class ClienteMockTest.
 *
 * @author florencia.n.martinez
 */
public class ClienteMockTest {

    /**
     * Dado un apellido y un nombre del cliente, cuando se invoca al metodo
     * "completarInfoCliente", obtengo un Cliente.
     */
    @Test
    public void dadoApellidoYNombreClienteCuandoInvocaCompletarInfoClienteObtengoCliente() {
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = CuentaMock.completarInfoCuenta();

        Assert.assertEquals("Godoy", cliente.getApellido1());
        Assert.assertEquals("Silvia", cliente.getNombre());
        Assert.assertEquals(cuenta.getCodigoTitularidad(), cliente.getCuentas().get(0).getCodigoTitularidad());
    }
}
