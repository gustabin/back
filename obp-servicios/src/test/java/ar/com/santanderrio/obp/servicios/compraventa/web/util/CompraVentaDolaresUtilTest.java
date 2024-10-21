/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class CompraVentaDolaresUtilTest.
 *
 * @author florencia.n.martinez
 */
public class CompraVentaDolaresUtilTest {

    /** The compra venta dolares util. */
    private CompraVentaDolaresUtil compraVentaDolaresUtil = new CompraVentaDolaresUtil();

    /**
     * Formatear saldos Y tipo cuenta de cuentas list test.
     */
    @Test
    public void formatearSaldosYTipoCuentaDeCuentasListTest() {
        List<CuentasAdhesionDebitoView> cuentas = obtenerCuentas();
        compraVentaDolaresUtil.formatearSaldosYTipoCuentaDeCuentasList(cuentas);
        Assert.assertEquals("-2.000,00", cuentas.get(0).getSaldoDolares());
    }

    /**
     * Obtener cuentas.
     *
     * @return the list
     */
    private List<CuentasAdhesionDebitoView> obtenerCuentas() {
        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>(2);
        CuentasAdhesionDebitoView c1 = new CuentasAdhesionDebitoView();
        c1.setSignoSaldoDolares("-");
        c1.setSaldoDolares("2.000,00");
        c1.setSaldoPesos("15.000,00");
        c1.setDescripcionTipoCuenta("Cuenta unica");
        CuentasAdhesionDebitoView c2 = new CuentasAdhesionDebitoView();
        c2.setSignoSaldoDolares("-");
        c2.setSaldoDolares("2.000,00");
        c2.setSaldoPesos("15.000,00");
        c2.setDescripcionTipoCuenta("Cuenta unica");
        cuentas.add(c1);
        cuentas.add(c2);
        return cuentas;
    }
}
