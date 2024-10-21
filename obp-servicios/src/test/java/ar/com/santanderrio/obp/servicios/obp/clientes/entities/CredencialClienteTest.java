/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.clientes.entities;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;

/**
 * The Class CredencialClienteTest.
 *
 * @author Jonatan_Bocian
 */
public class CredencialClienteTest {

    /**
     * Test.
     */
    @Test
    public void test() {
        CredencialCliente cc = new CredencialCliente();
        Assert.assertNotNull(cc);
    }
}
