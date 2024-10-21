/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesMock;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LimitesYDisponiblesView;

/**
 * The Class LimitesYDisponiblesViewTest.
 *
 * @author florencia.n.martinez
 */
public class LimitesYDisponiblesViewTest {

    /** The view. */
    private LimitesYDisponiblesView view = new LimitesYDisponiblesView();

    /**
     * Gets the limites y disponibles con legales.
     *
     * @return the limites y disponibles con legales
     */
    @Test
    public void getLimitesYDisponiblesConLegales() {
        LimitesYDisponiblesDTO limitesYDisponibles = LimitesYDisponiblesMock.completarInfoLimitesYDisponibles();
        view = new LimitesYDisponiblesView(limitesYDisponibles);

        Assert.assertEquals(limitesYDisponibles.getAdelantoEfectivoPesos(), view.getAdelantoEfectivoPesos());
        Assert.assertEquals("Legales...", view.getLegales());
    }

    /**
     * Gets the limites y disponibles sin legales.
     *
     * @return the limites y disponibles sin legales
     */
    @Test
    public void getLimitesYDisponiblesSinLegales() {
        LimitesYDisponiblesDTO limitesYDisponibles = LimitesYDisponiblesMock.completarInfoLimitesYDisponibles();
        limitesYDisponibles.setLegales(null);
        view = new LimitesYDisponiblesView(limitesYDisponibles);

        Assert.assertEquals(limitesYDisponibles.getAdelantoEfectivoPesos(), view.getAdelantoEfectivoPesos());
        Assert.assertNull(view.getLegales());
    }
}
