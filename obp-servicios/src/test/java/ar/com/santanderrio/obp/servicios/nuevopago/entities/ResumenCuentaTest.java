package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * The Class ResumenCuentaTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResumenCuentaTest {

    /**
     * Setter getter test.
     */
    @Test
    public void setterGetterTest() {
        ResumenCuenta resumenCuenta = new ResumenCuenta();

        resumenCuenta.setCuentaPrincipal(true);
        resumenCuenta.setDescripcionTipoCuenta("Tipo prueba");
        resumenCuenta.setNumero("35");
        resumenCuenta.setSaldoPesos("12314");
        resumenCuenta.setTipoCuentaAbreviado("Tipo pr");

        Assert.assertEquals(true, resumenCuenta.getCuentaPrincipal());
        Assert.assertEquals("Tipo prueba", resumenCuenta.getDescripcionTipoCuenta());
        Assert.assertEquals("35", resumenCuenta.getNumero());
        Assert.assertEquals("12314", resumenCuenta.getSaldoPesos());
        Assert.assertEquals("Tipo pr", resumenCuenta.getTipoCuentaAbreviado());
    }

    /**
     * Equals ok test.
     */
    @Test
    public void equalsOkTest() {
        ResumenCuenta resumenCuenta = new ResumenCuenta();
        ResumenCuenta resumenCuenta2 = new ResumenCuenta();

        resumenCuenta.setCuentaPrincipal(true);
        resumenCuenta.setDescripcionTipoCuenta("Tipo prueba");
        resumenCuenta.setNumero("35");
        resumenCuenta.setSaldoPesos("12314");
        resumenCuenta.setTipoCuentaAbreviado("Tipo pr");

        resumenCuenta2.setCuentaPrincipal(true);
        resumenCuenta2.setDescripcionTipoCuenta("Tipo prueba");
        resumenCuenta2.setNumero("35");
        resumenCuenta2.setSaldoPesos("12314");
        resumenCuenta2.setTipoCuentaAbreviado("Tipo pr");

        Assert.assertEquals(true, resumenCuenta.equals(resumenCuenta2));
    }

    /**
     * Not equals null test.
     */
    @Test
    public void notEqualsNullTest() {
        ResumenCuenta resumenCuenta = new ResumenCuenta();
        ResumenCuenta resumenCuenta2 = null;

        resumenCuenta.setCuentaPrincipal(true);
        resumenCuenta.setDescripcionTipoCuenta("Tipo prueba");
        resumenCuenta.setNumero("35");
        resumenCuenta.setSaldoPesos("12314");
        resumenCuenta.setTipoCuentaAbreviado("Tipo pr");

        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
    }

    /**
     * Not equals different class test.
     */
    @Test
    public void notEqualsDifferentClassTest() {
        ResumenCuenta resumenCuenta = new ResumenCuenta();
        String resumenCuenta2 = "asdasd";

        resumenCuenta.setCuentaPrincipal(true);
        resumenCuenta.setDescripcionTipoCuenta("Tipo prueba");
        resumenCuenta.setNumero("35");
        resumenCuenta.setSaldoPesos("12314");
        resumenCuenta.setTipoCuentaAbreviado("Tipo pr");

        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
    }

    /**
     * Not equals cuenta principal test.
     */
    @Test
    public void notEqualsCuentaPrincipalTest() {
        ResumenCuenta resumenCuenta = new ResumenCuenta();
        ResumenCuenta resumenCuenta2 = new ResumenCuenta();

        resumenCuenta.setCuentaPrincipal(null);
        resumenCuenta.setDescripcionTipoCuenta(null);
        resumenCuenta.setNumero(null);
        resumenCuenta.setSaldoPesos(null);
        resumenCuenta.setTipoCuentaAbreviado(null);

        resumenCuenta2.setCuentaPrincipal(null);
        resumenCuenta2.setDescripcionTipoCuenta(null);
        resumenCuenta2.setNumero(null);
        resumenCuenta2.setSaldoPesos(null);
        resumenCuenta2.setTipoCuentaAbreviado(null);

        Assert.assertEquals(true, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta2.setCuentaPrincipal(true);
        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta.setCuentaPrincipal(false);
        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta.setCuentaPrincipal(true);

        resumenCuenta2.setNumero("23");
        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta.setNumero("24");
        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta.setNumero("23");
        Assert.assertEquals(true, resumenCuenta.equals(resumenCuenta2));

        resumenCuenta2.setSaldoPesos("23,50");
        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta.setSaldoPesos("27,50");
        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta.setSaldoPesos("23,50");
        Assert.assertEquals(true, resumenCuenta.equals(resumenCuenta2));

        resumenCuenta2.setDescripcionTipoCuenta("Descripcion de prueba");
        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta.setDescripcionTipoCuenta("Descripcion de prueba distinta");
        Assert.assertEquals(false, resumenCuenta.equals(resumenCuenta2));
        resumenCuenta.setDescripcionTipoCuenta("Descripcion de prueba");
        Assert.assertEquals(true, resumenCuenta.equals(resumenCuenta2));

        resumenCuenta2 = resumenCuenta;
        Assert.assertEquals(true, resumenCuenta.equals(resumenCuenta2));
    }

    /**
     * Hash code test.
     */
    @Test
    public void hashCodeTest() {
        ResumenCuenta resumenCuenta = new ResumenCuenta();

        resumenCuenta.setCuentaPrincipal(null);
        resumenCuenta.setDescripcionTipoCuenta(null);
        resumenCuenta.setNumero(null);
        resumenCuenta.setSaldoPesos(null);
        resumenCuenta.setTipoCuentaAbreviado("Tipo pr");

        Assert.assertNotEquals(resumenCuenta.hashCode(), 0);

        resumenCuenta.setCuentaPrincipal(true);
        resumenCuenta.setDescripcionTipoCuenta("Tipo prueba");
        resumenCuenta.setNumero("35");
        resumenCuenta.setSaldoPesos("12314");

        int res = 31 + resumenCuenta.getCuentaPrincipal().hashCode();
        res = res * 31 + resumenCuenta.getNumero().hashCode();
        res = res * 31 + resumenCuenta.getSaldoPesos().hashCode();
        res = res * 31 + resumenCuenta.getDescripcionTipoCuenta().hashCode();

        Assert.assertNotEquals(resumenCuenta.hashCode(), 0);
    }

}
