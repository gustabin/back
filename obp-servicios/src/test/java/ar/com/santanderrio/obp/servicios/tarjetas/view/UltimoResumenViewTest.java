/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.tarjetas.web.view.UltimoResumenView;

/**
 * The Class UltimoResumenViewTest.
 *
 * @author florencia.n.martinez
 */
public class UltimoResumenViewTest {

    /** The view. */
    private UltimoResumenView view = new UltimoResumenView();

    /**
     * Dado un nro tarjeta en String, cuando se invoca al metodo
     * "getNumeroTarjeta", obtengo el nro tarjeta de UltimoResumenView.
     */
    @Test
    public void dadoNroTarjetaCuandoInvocaGetNumeroTarjetaObtengoNroTarjetaDelView() {
        String nroTarjeta = "XXXX-1234";
        view.setNumeroTarjeta(nroTarjeta);
        Assert.assertEquals(nroTarjeta, view.getNumeroTarjeta());
    }
}
