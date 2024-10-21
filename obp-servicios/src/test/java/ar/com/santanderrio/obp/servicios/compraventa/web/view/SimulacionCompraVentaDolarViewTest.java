/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.view;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;

/**
 * The Class SimulacionCompraVentaDolarViewTest.
 *
 * @author florencia.n.martinez
 */
public class SimulacionCompraVentaDolarViewTest {

    /**
     * Prueba que los atributos del objeto SimulacionCompraVentaDolarView para
     * la compra de dolares coincidan con el formato adecuado.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
	@Ignore
    @Test
    public void generarSimulacionCompraDolarViewConAlias() throws CompraVentaDolaresException {
        SimulacionCompraVentaDolarView simulacionCompraVentaDolarView = new SimulacionCompraVentaDolarView(
                generarDTOCompra());
        Assert.assertTrue(simulacionCompraVentaDolarView.getImporteCompraDolar()
                .matches("^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$"));
        Assert.assertNull(simulacionCompraVentaDolarView.getImporteVentaDolar());
        Assert.assertTrue(simulacionCompraVentaDolarView.getNroCuentaOrigen().matches("[0-9]{3}-[0-9]{6}/[0-9]{1}"));
        Assert.assertTrue(
                simulacionCompraVentaDolarView.getTipoCuentaOrigen().matches("[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getNroCuentaDestino().matches("[0-9]{3}-[0-9]{6}/[0-9]{1}"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getTipoCuentaDestino()
                .matches("[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getCotizacion().matches("([1-9]\\d|\\d),[0-9]{2}"));
        Assert.assertNull(simulacionCompraVentaDolarView.getImporteAcreditarPesos());
        Assert.assertTrue(simulacionCompraVentaDolarView.getImporteDebitarPesos()
                .matches("^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getEsCompra());
        Assert.assertTrue(!simulacionCompraVentaDolarView.getEsVenta());
        Assert.assertTrue(
                simulacionCompraVentaDolarView.getFecha().matches("([0-2]\\d|3[0-1])/(1[0-2]|0\\d)/(19|20(\\d{2}))"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getLegales()
                .matches("[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)|\n]+"));
    }

    /**
     * Prueba que los atributos del objeto SimulacionCompraVentaDolarView para
     * la venta de dolares coincidan con el formato adecuado.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void generarSimulacionVentaDolarViewSinAlias() throws CompraVentaDolaresException {
        SimulacionCompraVentaDolarView simulacionCompraVentaDolarView = new SimulacionCompraVentaDolarView(
                generarDTOVenta());
        Assert.assertNull(simulacionCompraVentaDolarView.getImporteCompraDolar());
        Assert.assertTrue(simulacionCompraVentaDolarView.getImporteVentaDolar()
                .matches("^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getNroCuentaOrigen().matches("[0-9]{3}-[0-9]{6}/[0-9]{1}"));
        Assert.assertTrue(
                simulacionCompraVentaDolarView.getTipoCuentaOrigen().matches("[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getNroCuentaDestino().matches("[0-9]{3}-[0-9]{6}/[0-9]{1}"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getTipoCuentaDestino()
                .matches("[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getCotizacion().matches("([1-9]\\d|\\d),[0-9]{2}"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getImporteAcreditarPesos()
                .matches("^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$"));
        Assert.assertNull(simulacionCompraVentaDolarView.getImporteDebitarPesos());
        Assert.assertTrue(!simulacionCompraVentaDolarView.getEsCompra());
        Assert.assertTrue(simulacionCompraVentaDolarView.getEsVenta());
        Assert.assertTrue(
                simulacionCompraVentaDolarView.getFecha().matches("([0-2]\\d|3[0-1])/(1[0-2]|0\\d)/(19|20(\\d{2}))"));
        Assert.assertTrue(simulacionCompraVentaDolarView.getLegales()
                .matches("[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)|\n]+"));
    }

    /**
     * Genera el objeto SimulacionCompraVentaDTO para la venta de dolares.
     *
     * @return the simulacion compra venta DTO
     */
    public static SimulacionCompraVentaDTO generarDTOVenta() {
        SimulacionCompraVentaDTO simulacionCompraDTO = new SimulacionCompraVentaDTO();
        simulacionCompraDTO.setImporteCompraDolar(null);
        simulacionCompraDTO.setImporteVentaDolar(BigDecimal.valueOf(2309.00));
        simulacionCompraDTO.setTipoCuentaDestino("Caja de Ahorro en $");
        simulacionCompraDTO.setTipoCuentaOrigen("Caja de Ahorro en u$s");
        simulacionCompraDTO.setCotizacion(BigDecimal.valueOf(15.50));
        simulacionCompraDTO.setImporteAcreditarPesos(BigDecimal.valueOf(3455.54));
        simulacionCompraDTO.setImporteDebitarPesos(null);
        simulacionCompraDTO.setFecha(new Date());
        simulacionCompraDTO.setEsCompra(Boolean.FALSE);
        simulacionCompraDTO.setEsVenta(Boolean.TRUE);
        simulacionCompraDTO.setLegales("Por la presente declaro bajo juramento que:"
                + "1) Las informaciones consignadas son exactas y verdaderas, en los términos previstos en el Régimen Penal Cambiario, del cual tengo pleno conocimiento de sus normas y sanciones."
                + "2) Al día de la fecha no me encuentro inhabilitado a operar en cambios en los términos de la normativa aplicable del BCRA"
                + "Asimismo, me comprometo por la presente a mantener indemne a Banco Santander Río S.A., por cualquier y contra cualesquiera responsabilidades y daños y perjuicios de cualquier clase o naturaleza, incurridos por el incumplimiento de la Declaración Jurada arriba realizada.");
        simulacionCompraDTO.setNumeroOperacion("1234456677");
        simulacionCompraDTO.setNumeroComprobante("45656565");
        simulacionCompraDTO.setNumeroCuentaDestino("123-456789/0");
        simulacionCompraDTO.setNumeroCuentaOrigen("789-455633/4");

        return simulacionCompraDTO;
    }

    /**
     * Genera el objeto SimulacionCompraVentaDTO para la compra de dolares.
     *
     * @return the simulacion compra venta DTO
     */
    public static SimulacionCompraVentaDTO generarDTOCompra() {
        SimulacionCompraVentaDTO simulacionCompraDTO = new SimulacionCompraVentaDTO();
        simulacionCompraDTO.setImporteCompraDolar(BigDecimal.valueOf(0.7));
        simulacionCompraDTO.setImporteVentaDolar(null);
        simulacionCompraDTO.setTipoCuentaOrigen("Caja de Ahorro en $");
        simulacionCompraDTO.setTipoCuentaDestino("Caja de Ahorro en u$s");
        simulacionCompraDTO.setCotizacion(BigDecimal.valueOf(15.56));
        simulacionCompraDTO.setImporteAcreditarPesos(null);
        simulacionCompraDTO.setImporteDebitarPesos(BigDecimal.valueOf(213.45));
        simulacionCompraDTO.setFecha(new Date());
        simulacionCompraDTO.setEsCompra(Boolean.TRUE);
        simulacionCompraDTO.setEsVenta(Boolean.FALSE);
        simulacionCompraDTO.setLegales("Por la presente declaro bajo juramento que:"
                + "1) Las informaciones consignadas son exactas y verdaderas, en los términos previstos en el Régimen Penal Cambiario, del cual tengo pleno conocimiento de sus normas y sanciones."
                + "2) Al día de la fecha no me encuentro inhabilitado a operar en cambios en los términos de la normativa aplicable del BCRA"
                + "Asimismo, me comprometo por la presente a mantener indemne a Banco Santander Río S.A., por cualquier y contra cualesquiera responsabilidades y daños y perjuicios de cualquier clase o naturaleza, incurridos por el incumplimiento de la Declaración Jurada arriba realizada.");
        simulacionCompraDTO.setNumeroOperacion("1234456677");
        simulacionCompraDTO.setNumeroComprobante("45656565");
        simulacionCompraDTO.setNumeroCuentaOrigen("123-456789/0");
        simulacionCompraDTO.setNumeroCuentaDestino("789-455633/4");
        return simulacionCompraDTO;
    }
}