package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * The Class PagoMisCuentasViewTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoMisCuentasViewTest {

    /** The pago mis cuentas view. */
    private PagoMisCuentasView pagoMisCuentasView = new PagoMisCuentasView();

    /**
     * Setter getter test.
     */
    @Test
    public void setterGetterTest() {
        pagoMisCuentasView.setCodigoEmpresa("12");
        pagoMisCuentasView.setCuitEmpleador("20-37866888-1");
        pagoMisCuentasView.setPeriodoPago("122024");
        pagoMisCuentasView.setSucursal("34");
        pagoMisCuentasView.setNumeroCuenta("12314");
        pagoMisCuentasView.setMoneda("14");

        Assert.assertEquals(pagoMisCuentasView.getCodigoEmpresa(), "12");
        Assert.assertEquals(pagoMisCuentasView.getCuitEmpleador(), "20-37866888-1");
        Assert.assertEquals(pagoMisCuentasView.getPeriodoPago(), "122024");
        Assert.assertEquals(pagoMisCuentasView.getSucursal(), "34");
        Assert.assertEquals(pagoMisCuentasView.getNumeroCuenta(), "12314");
        Assert.assertEquals(pagoMisCuentasView.getMoneda(), "14");
    }
}
