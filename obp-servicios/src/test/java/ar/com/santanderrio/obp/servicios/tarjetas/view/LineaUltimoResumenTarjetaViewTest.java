/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LineaUltimoResumenTarjetaView;

/**
 * The Class LineaUltimoResumenTarjetaViewTest.
 *
 * @author florencia.n.martinez
 */
public class LineaUltimoResumenTarjetaViewTest {

    /** The linea. */
    private LineaUltimoResumenTarjetaView linea = new LineaUltimoResumenTarjetaView();

    /**
     * Dado un importe negativo en formato BigDecimal, cuando se invoca al
     * metodo "getImporte", obtengo un importe negativo en formato String.
     */
    @Test
    public void dadoImporteNegativoBigDecimalCuandoInvocaGetImporteObtengoImporteNegativoString() {
        linea.setImportePesos(BigDecimal.valueOf(-5847.13));
        Assert.assertEquals("-5.847,13", linea.getImportePesos());
    }

    /**
     * Dado un importe positivo en formato BigDecimal, cuando se invoca al
     * metodo "getImporte", obtengo un importe positivo en formato String.
     */
    @Test
    public void dadoImportePositivoBigDecimalCuandoInvocaGetImporteObtengoImportePositivoString() {
        linea.setImporteDolares(BigDecimal.valueOf(5847.13));
        Assert.assertEquals("5.847,13", linea.getImporteDolares());
    }
}
