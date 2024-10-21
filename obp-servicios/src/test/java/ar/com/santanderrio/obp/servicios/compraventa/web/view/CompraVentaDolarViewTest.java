/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.view;

import org.junit.Assert;
import org.junit.Test;

/**
 * The Class CompraVentaDolarViewTest.
 *
 * @author florencia.n.martinez
 */
public class CompraVentaDolarViewTest {

    /**
     * Genera el objeto CompraVentaDolarView para probar que los datos de salida
     * sean en el formato correcto.
     */
    // TODO Faltan validaciones de CuentaView
    @Test
    public void generarCompraVentaDolarView() {
        CompraVentaDolarView compraVentaDolarView = generarCompraVentaDolarViewGenerico(15.78, "15,78");
        Assert.assertTrue(compraVentaDolarView.getCotizacion().toString().matches("[0-9]{2}.[0-9]{1,2}"));
        Assert.assertTrue(compraVentaDolarView.getCotizacionString().matches("[0-9]{2}.[0-9]{2}"));
    }

    /**
     * Genera el objeto CompraVentaDolarView generico.
     *
     * @param cotizacion
     *            the cotizacion
     * @param cotizacionString
     *            the cotizacion string
     * @return the compra venta dolar view
     */
    // TODO Faltan validaciones de CuentaView
    private CompraVentaDolarView generarCompraVentaDolarViewGenerico(Double cotizacion, String cotizacionString) {
        CompraVentaDolarView compraVentaDolarView = new CompraVentaDolarView();
        compraVentaDolarView.setCotizacion(cotizacion);
        compraVentaDolarView.setCotizacionString(cotizacionString);
        return compraVentaDolarView;
    }
}
