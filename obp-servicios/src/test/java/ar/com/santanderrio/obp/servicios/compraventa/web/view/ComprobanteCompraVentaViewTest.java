/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.view;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;

/**
 * The Class ComprobanteCompraVentaDolaresViewTest.
 *
 * @author florencia.n.martinez
 */
public class ComprobanteCompraVentaViewTest {

    /**
     * Prueba que los atributos del objeto ComprobanteCompraVentaView para la
     * compra de dolares coincidan con el formato adecuado.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void generarComprobanteViewParaCompra() throws CompraVentaDolaresException {
        ComprobanteCompraVentaView comprobanteCompraView = generarComprobanteCompraView();
        Assert.assertTrue(comprobanteCompraView.getImporteCompraDolar()
                .matches("^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$"));
        Assert.assertNull(comprobanteCompraView.getImporteVentaDolar());
        Assert.assertTrue(comprobanteCompraView.getNroCuentaOrigen().matches("[0-9]{3}-[0-9]{6}/[0-9]{1}"));
        Assert.assertTrue(
                comprobanteCompraView.getTipoCuentaOrigen().matches("[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+"));
        Assert.assertTrue(comprobanteCompraView.getNroCuentaDestino().matches("[0-9]{3}-[0-9]{6}/[0-9]{1}"));
        Assert.assertTrue(
                comprobanteCompraView.getTipoCuentaDestino().matches("[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+"));
        Assert.assertTrue(comprobanteCompraView.getCotizacion().matches("([1-9]\\d|\\d),[0-9]{2}"));
        Assert.assertNull(comprobanteCompraView.getImporteAcreditarPesos());
        Assert.assertTrue(comprobanteCompraView.getImporteDebitarPesos()
                .matches("^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$"));
        Assert.assertTrue(comprobanteCompraView.getEsCompra());
        Assert.assertTrue(!comprobanteCompraView.getEsVenta());
        Assert.assertTrue(comprobanteCompraView.getFecha().matches("([0-2]\\d|3[0-1])/(1[0-2]|0\\d)/(19|20(\\d{2}))"));
        Assert.assertTrue(comprobanteCompraView.getHora().matches("([0-1][0-9]|2[0-3]):([0-5][0-9])"));
        Assert.assertTrue(comprobanteCompraView.getLegalesUno()
                .matches("[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)]+"));
        Assert.assertTrue(comprobanteCompraView.getLegalesDos()
                .matches("[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)]+"));
        Assert.assertTrue(comprobanteCompraView.getNumeroComprobante().matches("[0-9]+"));
        Assert.assertTrue(comprobanteCompraView.getNumeroOperacion().matches("[0-9]+"));
    }

    /**
     * Prueba que los atributos del objeto ComprobanteCompraVentaView para la
     * venta de dolares coincidan con el formato adecuado.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void generarComprobanteViewParaVenta() throws CompraVentaDolaresException {
        ComprobanteCompraVentaView comprobanteCompraView = generarComprobanteVentaView();
        Assert.assertNull(comprobanteCompraView.getImporteCompraDolar());
        Assert.assertTrue(comprobanteCompraView.getImporteVentaDolar()
                .matches("^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$"));
        Assert.assertTrue(comprobanteCompraView.getNroCuentaOrigen().matches("[0-9]{3}-[0-9]{6}/[0-9]{1}"));
        Assert.assertTrue(
                comprobanteCompraView.getTipoCuentaOrigen().matches("[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+"));
        Assert.assertTrue(comprobanteCompraView.getNroCuentaDestino().matches("[0-9]{3}-[0-9]{6}/[0-9]{1}"));
        Assert.assertTrue(
                comprobanteCompraView.getTipoCuentaDestino().matches("[a-z|A-Z|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| ]+"));
        Assert.assertTrue(comprobanteCompraView.getCotizacion().matches("([1-9]\\d|\\d),[0-9]{2}"));
        Assert.assertTrue(comprobanteCompraView.getImporteAcreditarPesos()
                .matches("^(([1-9]\\d?((\\.\\d{3}){0,3}))|(\\d)|([1-9]\\d{2}((\\.\\d{3}){0,2})))(,\\d{1,2})$"));
        Assert.assertNull(comprobanteCompraView.getImporteDebitarPesos());
        Assert.assertTrue(!comprobanteCompraView.getEsCompra());
        Assert.assertTrue(comprobanteCompraView.getEsVenta());
        Assert.assertTrue(comprobanteCompraView.getFecha().matches("([0-2]\\d|3[0-1])/(1[0-2]|0\\d)/(19|20(\\d{2}))"));
        Assert.assertTrue(comprobanteCompraView.getHora().matches("([0-1][0-9]|2[0-3]):([0-5][0-9])"));
        Assert.assertTrue(comprobanteCompraView.getLegalesUno()
                .matches("[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)]+"));
        Assert.assertTrue(comprobanteCompraView.getLegalesDos()
                .matches("[a-z|A-Z|0-9|á|Á|é|É|í|Í|ó|Ó|ú|Ú|ñ|Ñ|$| |.|,|:|;|(|)]+"));
        Assert.assertTrue(comprobanteCompraView.getNumeroComprobante().matches("[0-9]+"));
        Assert.assertTrue(comprobanteCompraView.getNumeroOperacion().matches("[0-9]+"));
    }

    /**
     * Genera el objeto ComprobanteCompraVentaView para la venta de dolares.
     *
     * @return the comprobante compra venta view
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    public static ComprobanteCompraVentaView generarComprobanteVentaView() throws CompraVentaDolaresException {
        ComprobanteCompraVentaView comprobanteVentaView = new ComprobanteCompraVentaView();
        comprobanteVentaView.setImporteVentaDolar(BigDecimal.valueOf(2309.00));
        comprobanteVentaView.setNroCuentaOrigen("123-456789/0");
        comprobanteVentaView.setTipoCuentaOrigen("Caja de Ahorro en u$s");
        comprobanteVentaView.setNroCuentaDestino("789-455633/4");
        comprobanteVentaView.setTipoCuentaDestino("Caja de Ahorro en $");
        comprobanteVentaView.setCotizacion(BigDecimal.valueOf(15.56));
        comprobanteVentaView.setImporteAcreditarPesos(BigDecimal.valueOf(3455.54));
        comprobanteVentaView.setEsVenta(true);
        comprobanteVentaView.setEsCompra(false);
        comprobanteVentaView.setFecha(new Date());
        comprobanteVentaView.setHora("14:07");
        comprobanteVentaView.setLegalesDos("Por la presente declaro bajo juramento que:"
                + "1) Las informaciones consignadas son exactas y verdaderas, en los términos previstos en el Régimen Penal Cambiario, del cual tengo pleno conocimiento de sus normas y sanciones."
                + "2) Al día de la fecha no me encuentro inhabilitado a operar en cambios en los términos de la normativa aplicable del BCRA"
                + "Asimismo, me comprometo por la presente a mantener indemne a Banco Santander Río S.A., por cualquier y contra cualesquiera responsabilidades y daños y perjuicios de cualquier clase o naturaleza, incurridos por el incumplimiento de la Declaración Jurada arriba realizada.");
        comprobanteVentaView.setLegalesUno("Conserve este ticket como comprobante S.E.U.O.");
        comprobanteVentaView.setLegalesUno("Conserve este ticket como comprobante S.E.U.O.");
        comprobanteVentaView.setNumeroComprobante("12445664333");
        comprobanteVentaView.setNumeroOperacion("67786555666544");
        return comprobanteVentaView;
    }

    /**
     * Genera el objeto ComprobanteCompraVentaView para la compra de dolares.
     *
     * @return the comprobante compra venta view
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    public static ComprobanteCompraVentaView generarComprobanteCompraView() throws CompraVentaDolaresException {
        ComprobanteCompraVentaView comprobanteCompraView = new ComprobanteCompraVentaView();
        comprobanteCompraView.setImporteCompraDolar(BigDecimal.valueOf(0, 7));
        comprobanteCompraView.setNroCuentaOrigen("123-456789/0");
        comprobanteCompraView.setTipoCuentaOrigen("Caja de Ahorro en $");
        comprobanteCompraView.setNroCuentaDestino("789-455633/4");
        comprobanteCompraView.setTipoCuentaDestino("Caja de Ahorro en u$s");
        comprobanteCompraView.setCotizacion(BigDecimal.valueOf(15, 56));
        comprobanteCompraView.setImporteDebitarPesos(BigDecimal.valueOf(213, 45));
        comprobanteCompraView.setFecha(new Date());
        comprobanteCompraView.setHora("14:07");
        comprobanteCompraView.setLegalesDos("Por la presente declaro bajo juramento que:"
                + "1) Las informaciones consignadas son exactas y verdaderas, en los términos previstos en el Régimen Penal Cambiario, del cual tengo pleno conocimiento de sus normas y sanciones."
                + "2) Al día de la fecha no me encuentro inhabilitado a operar en cambios en los términos de la normativa aplicable del BCRA"
                + "Asimismo, me comprometo por la presente a mantener indemne a Banco Santander Río S.A., por cualquier y contra cualesquiera responsabilidades y daños y perjuicios de cualquier clase o naturaleza, incurridos por el incumplimiento de la Declaración Jurada arriba realizada.");
        comprobanteCompraView.setLegalesUno("Conserve este ticket como comprobante S.E.U.O.");
        comprobanteCompraView.setNumeroComprobante("2346678767544");
        comprobanteCompraView.setNumeroOperacion("2234211223334333");
        return comprobanteCompraView;
    }

}