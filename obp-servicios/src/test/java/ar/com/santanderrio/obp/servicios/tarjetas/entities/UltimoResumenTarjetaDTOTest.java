/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.junit.Assert;
import org.junit.Test;

/**
 * The Class UltimoResumenTarjetaDTOTest.
 *
 * @author florencia.n.martinez
 */
public class UltimoResumenTarjetaDTOTest {

    /**
     * Gets the numero tarjera test.
     *
     * @return the numero tarjera test
     */
    @Test
    public void getNumeroTarjeraTest() {
        String nroTarjeta = "XXXX-1234";
        UltimoResumenTarjetaDTO ultimoResumenTarjetaDTO = new UltimoResumenTarjetaDTO();
        ultimoResumenTarjetaDTO.setNumeroTarjeta(nroTarjeta);
        Assert.assertEquals(nroTarjeta, ultimoResumenTarjetaDTO.getNumeroTarjeta());
    }
}
