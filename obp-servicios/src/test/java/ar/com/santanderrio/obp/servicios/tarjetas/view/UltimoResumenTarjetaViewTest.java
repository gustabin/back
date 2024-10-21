/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.tarjetas.web.view.UltimoResumenTarjetaView;

/**
 * The Class UltimoResumenTarjetaViewTest.
 *
 * @author florencia.n.martinez
 */
public class UltimoResumenTarjetaViewTest {

    /** The view. */
    private UltimoResumenTarjetaView view = new UltimoResumenTarjetaView();

    /**
     * Dado un nro tarjeta en String, cuando se invoca al metodo
     * "getNumeroTarjeta", obtengo el nro tarjeta de UltimoResumenTarjetaView.
     */
    @Test
    public void dadoNroTarjetaCuandoInvocaGetNumeroTarjetaObtengoNroTarjetaDelView() {
        String nroTarjeta = "XXXX-1234";
        view.setNumeroTarjeta(nroTarjeta);
        Assert.assertEquals(nroTarjeta, view.getNumeroTarjeta());
    }
}
