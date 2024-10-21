/**
 * 
 */
package ar.com.santanderrio.obp.base.respuesta;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Class RespuestaTest.
 *
 * @author Jonatan_Bocian
 */
public class RespuestaTest {

    /**
     * Test.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void test() {
        Respuesta resp = new Respuesta();
        Assert.assertNotNull(resp);
    }

}
