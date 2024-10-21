/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * The Class UltimoResumenDTOTest.
 *
 * @author florencia.n.martinez
 */
public class UltimoResumenDTOTest {

    /**
     * Gets the fecha cierre actual test.
     *
     * @return the fecha cierre actual test
     */
    @Test
    public void getFechaCierreActualTest() {
        Date fecha = new Date(06 - 10 - 2016);
        UltimoResumenDTO ultimoResumenDTO = new UltimoResumenDTO();
        ultimoResumenDTO.setFechaCierreActual(fecha);
        Assert.assertEquals(fecha, ultimoResumenDTO.getFechaCierreActual());
    }
}
