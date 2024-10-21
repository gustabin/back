/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * The Class GotoLinkTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class GotoLinkTest {

    /**
     * Gets the goto link sin parametros. Goto: gotoPagoTarjetaCredito().
     *
     * @return the goto link
     */
    @Test
    public void getGotoLinkSinParametros() {
        GotoLink gotoLink = new GotoLink("gotoPagoTarjetaCredito()");

        Assert.assertEquals("gotoPagoTarjetaCredito()", gotoLink.getLink());
        Assert.assertEquals("", gotoLink.getParametros());
    }

    /**
     * Gets the goto link. Goto: gotoLanding() - con parametros.
     *
     * @return the goto link
     */
    @Test
    public void getGotoLinkConParametros() {
        GotoLink gotoLink = new GotoLink("gotoLanding(Inversiones;www.santanderrio.com.ar;gotoPagoTarjetaCredito())");

        Assert.assertEquals("gotoLanding()", gotoLink.getLink());
        Assert.assertEquals("Inversiones;www.santanderrio.com.ar;gotoPagoTarjetaCredito()", gotoLink.getParametros());
    }

    /**
     * Gets the goto link con goto sin boton.
     *
     * @return the goto link con goto sin boton
     */
    @Test
    public void getGotoLinkConGotoSinBoton() {
        GotoLink gotoLink = new GotoLink("gotoX()");

        Assert.assertNull(gotoLink.getLink());
        Assert.assertEquals("", gotoLink.getParametros());
    }

    /**
     * Gets the goto link con length mayor 2.
     *
     * @return the goto link con length mayor 2
     */
    @Test
    public void getGotoLinkConLengthMayor2() {
        GotoLink gotoLink = new GotoLink("gotoLandingInversiones;www.santanderrio.com.ar;gotoPagoTarjetaCredito,gotoX");

        Assert.assertNull(gotoLink.getLink());
        Assert.assertNull(gotoLink.getParametros());
    }

    /**
     * Gets the goto link sin link.
     *
     * @return the goto link sin link
     */
    @Test
    public void getGotoLinkSinLink() {
        GotoLink gotoLink = new GotoLink("");

        Assert.assertNull(gotoLink.getLink());
        Assert.assertNull(gotoLink.getParametros());
    }

    /**
     * Gets the goto link.
     *
     * @return the goto link
     */
    @Test
    public void getGotoLink() {
        GotoLink gotoLink = new GotoLink("gotoLink(https://santanderrio.andreani.com;E)");

        Assert.assertEquals("gotoLink()", gotoLink.getLink());
        Assert.assertEquals("https://santanderrio.andreani.com;E", gotoLink.getParametros());
    }
    
    /**
     * goes to link. Goto: gotoProductoSuperclub() - con parametros.
     *
     * @return the goto link
     */
    @Test
    public void getGotoLinkConParamsCanjeProdSC() {
        GotoLink gotoLink = new GotoLink("gotoProductoSuperclub(Canje;www.santanderrio.com.ar;idProducto)");

        Assert.assertEquals("gotoProductoSuperclub()", gotoLink.getLink());
        Assert.assertEquals("Canje;www.santanderrio.com.ar;idProducto", gotoLink.getParametros());
    }
}
