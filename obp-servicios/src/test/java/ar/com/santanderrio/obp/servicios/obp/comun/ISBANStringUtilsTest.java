
/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.comun;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class ISBANStringUtilsTest.
 *
 * @author B039636
 */
public class ISBANStringUtilsTest {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ISBANStringUtilsTest.class);
    
	@Test
	public void formatearConComaYVariosDecimales2() throws JAXBException, IOException {
		String nro = "123123123.1";
		System.out.println(ISBANStringUtils.formatearConComaYVariosDecimales2(nro,2));
	}

	@Test
	public void formatearConComaYVariosDecimales() {
		System.out.println(ISBANStringUtils.formatearConComaYVariosDecimalesBis("0.12345678",7));
	}
    /**
     * Dado un CUIL, cuando se invoca al metodo "agregarGuionesANumeroCuitCuil",
     * obtengo el numero de cuil formateado.
     * 
     * @author florencia.n.martinez
     */
    @Test
    public void dadoCuilCuandoInvocaAgregarGuionesANumeroCuitCuilObtengoCuilFormateado() {
        String s = ISBANStringUtils.agregarGuionesANumeroCuitCuil("23122343458");
        Assert.assertEquals("23-12234345-8", s);
    }

    /**
     * Dado un saldo de tipo String, cuando se invoca al metodo
     * "formatearSaldoString", obtengo el saldo de tipo BigDecimal.
     * 
     * @author florencia.n.martinez
     */
    @Test
    public void dadoSaldoStringCuandoInvocaFormatearSaldoStringObtengoSaldoBigDecimal() {
        BigDecimal saldobd = ISBANStringUtils.formatearSaldoString("1.500,55");
        Assert.assertEquals(BigDecimal.valueOf(1500.55), saldobd);
    }

    /**
     * Dado un numero en formato BigDecimal, cuando se invoca al metodo
     * "formatearSaldoConSigno", obtengo un numero en formato String.
     * 
     * @author florencia.n.martinez
     */
    @Test
    public void dadoNumeroBigDecimalCuandoInvocaFormatearSaldoConSignoObtengoNumeroString() {
        String sNum = ISBANStringUtils.formatearSaldoConSigno(BigDecimal.valueOf(-5847.13));
        Assert.assertEquals("-5.847,13", sNum);
    }

    /**
     * Test CBU null.
     */
    @Test
    public void testCBUNull() {
        Assert.assertFalse(ISBANStringUtils.validarCBU(null));
    }

    /**
     * Test CBU empty.
     */
    @Test
    public void testCBUEmpty() {
        Assert.assertFalse(ISBANStringUtils.validarCBU(""));
    }

    /**
     * Test CBU short.
     */
    @Test
    public void testCBUShort() {
        Assert.assertFalse(ISBANStringUtils.validarCBU("32323"));
    }

    /**
     * Test CBU longe.
     */
    @Test
    public void testCBULonge() {
        Assert.assertFalse(ISBANStringUtils.validarCBU("34325345345345345345345345345342323"));
    }

    /**
     * Test CBU letras.
     */
    @Test
    public void testCBULetras() {
        Assert.assertFalse(ISBANStringUtils.validarCBU("34325rerere45345342323"));
    }

    /**
     * Test CBU letras menos.
     */
    @Test
    public void testCBULetrasMenos() {
        Assert.assertFalse(ISBANStringUtils.validarCBU("285059-940090418135201"));
    }

    /**
     * Test CBUOK 1.
     */
    @Test
    public void testCBUOK1() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("2850590940090418135201"));
    }

    /**
     * Test CBUOK 2.
     */
    @Test
    public void testCBUOK2() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("0110261320026100336199"));
    }

    /**
     * Test CBUOK 3.
     */
    @Test
    public void testCBUOK3() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("1910293955029300609232"));
    }

    /**
     * Test CBUOK 4.
     */
    @Test
    public void testCBUOK4() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("3880044110100004860091"));
    }

    /**
     * Test CBUOK 5.
     */
    @Test
    public void testCBUOK5() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("0720117820000000782618"));
    }

    /**
     * Test CBUOK 6.
     */
    @Test
    public void testCBUOK6() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("1500054100030032591964"));
    }

    /**
     * Test CBUOK 7.
     */
    @Test
    public void testCBUOK7() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("0270005210000088270011"));
    }

    /**
     * Test CBUOK 8.
     */
    @Test
    public void testCBUOK8() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("0270006910000102550013"));
    }

    /**
     * Test CBUOK 9.
     */
    @Test
    public void testCBUOK9() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("0070002330004402734562"));
    }

    /**
     * Test CBUOK 10.
     */
    @Test
    public void testCBUOK10() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("1500000800000110751100"));
    }

    /**
     * Test CBUERROR 1.
     */
    @Test
    public void testCBUERROR1() {
        Assert.assertFalse(ISBANStringUtils.validarCBU("0070002330004402733333"));
    }

    /**
     * Test CBUOK.
     */
    @Test
    public void testCBUOK() {
        Assert.assertTrue(ISBANStringUtils.validarCBU("0070002330004402734333"));
    }

    /**
     * Test CBUERROR 2.
     */
    @Test
    public void testCBUERROR2() {
        Assert.assertFalse(ISBANStringUtils.validarCBU("0070002330004402734339"));
    }

    /**
     * Validar empty.
     */
    @Test
    public void validarEmpty() {
        Assert.assertFalse(ISBANStringUtils.validarCuil(""));
    }

    /**
     * Validar null.
     */
    @Test
    public void validarNull() {
        Assert.assertFalse(ISBANStringUtils.validarCuil(null));
    }

    /**
     * Validar menos.
     */
    @Test
    public void validarMenos() {
        Assert.assertFalse(ISBANStringUtils.validarCuil("3232"));
    }

    /**
     * Validar letras.
     */
    @Test
    public void validarLetras() {
        Assert.assertFalse(ISBANStringUtils.validarCuil("ewewewewew"));
    }

    /**
     * Validar cuit 1.
     */
    @Test
    public void validarCuit1() {
        Assert.assertTrue(ISBANStringUtils.validarCuil("20111111112"));
    }

    /**
     * Validar cuit 2 OK.
     */
    @Test
    public void validarCuit2OK() {
        Assert.assertTrue(ISBANStringUtils.validarCuil("20-22222222-3"));
    }

    /**
     * Validar cuit 2.
     */
    @Test
    public void validarCuit2() {
        Assert.assertTrue(ISBANStringUtils.validarCuil("20222222223"));
    }

    /**
     * Validar cuit 3.
     */
    @Test
    public void validarCuit3() {
        Assert.assertTrue(ISBANStringUtils.validarCuil("30111111118"));
    }

    /**
     * Validar cuit 4.
     */
    @Test
    public void validarCuit4() {
        Assert.assertTrue(ISBANStringUtils.validarCuil("30222222229"));
    }

    /**
     * Validar importe vacio.
     */
    @Test
    public void validarImporteVacio() {
        BigDecimal rta = null;
        try {
            rta = ISBANStringUtils.convertirImporte("");
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
    }

    /**
     * Validar importe letras.
     */
    @Test
    public void validarImporteLetras() {
        BigDecimal rta = null;
        try {
            rta = ISBANStringUtils.convertirImporte("xxxx");
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
    }

    /**
     * Validar importe OK 1000.
     */
    @Test
    public void validarImporteOK1000() {
        BigDecimal rta = null;
        String importe = "1000";
        try {

            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertEquals(1000, rta.intValue());
        System.out.println(importe + "=" + rta);
    }

    /**
     * Validar importe OK 100.
     */
    @Test
    public void validarImporteOK100() {
        BigDecimal rta = null;
        try {
            rta = ISBANStringUtils.convertirImporte("1000,01");
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        System.out.println(rta);
    }

    /**
     * Validar importe OK 2.
     */
    @Test
    public void validarImporteOK2() {
        BigDecimal rta = null;
        try {
            rta = ISBANStringUtils.convertirImporte("1.000,01");
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        System.out.println(rta);
    }

    /**
     * Validar importe mal 1.
     */
    @Test
    public void validarImporteMal1() {
        BigDecimal rta = null;
        try {
            rta = ISBANStringUtils.convertirImporte("1.0.00,01");
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        System.out.println(rta);
    }

    /**
     * Validar importe OK 1.
     */
    @Test
    public void validarImporteOK1() {
        BigDecimal rta = null;
        try {
            rta = ISBANStringUtils.convertirImporte(" 101.000,01");
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        System.out.println(rta);
    }

    /**
     * Validar importe OK 3.
     */
    @Test
    public void validarImporteOK3() {
        BigDecimal rta = null;
        String importe = "1.000.101.000,01";
        try {
            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        System.out.println(importe + "=" + rta);
    }

    /**
     * Validar importe OK 4.
     */
    @Test
    public void validarImporteOK4() {
        BigDecimal rta = null;
        String importe = "1.01";
        try {
            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        System.out.println(importe + "=" + rta);
    }

    /**
     * Validar importe OK 5.
     */
    @Test
    public void validarImporteOK5() {
        BigDecimal rta = null;
        String importe = "1.10";
        try {
            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        System.out.println(importe + "=" + rta);
    }
    //fix TRFIND-495: Completar decimales en la carga de importe de TIS
    @Test
    public void cuandoSeIngresa100ComoImporteConvertirImporteRetorna100Con00Centavos() {
        BigDecimal rta = null;
        String importe = "100";
        try {
            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.00"),rta);
        Assert.assertNotEquals(new BigDecimal("100"),rta);
    }

    @Test
    public void cuandoSeIngresa100Con2CentavosConvertirImporteNoRetorna100Con20Centavos() {
        BigDecimal rta = null;
        String importe = "100.2";
        try {
            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.2"),rta);
        Assert.assertNotEquals(new BigDecimal("100.20"),rta);
    }
    @Test
    public void cuandoSeIngresa102Con2CentavosConvertirImporteConDosDecimalesRetorna100Con20Centavos() {
        BigDecimal rta = null;
        String importe = "100.2";
        try {
            rta = ISBANStringUtils.convertirImporteConNCantDeDecimales(importe, 2);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.20"),rta);
    }

    @Test
    public void cuandoSeIngresa100ConvertirImporteConDosDecimalesRetorna100Con00Centavos() {
        BigDecimal rta = null;
        String importe = "100";
        try {
            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.00"),rta);
    }

    @Test
    public void cuandoSeIngresa100Con45CentavosConvertirImporteRetorna100Con45Centavos() {
        BigDecimal rta = null;
        String importe = "100.45";
        try {
            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.45"),rta);
    }

    @Test
    public void cuandoSeIngresa100Con455CentavosConvertirImporteRetorna100Con455Centavos() {
        BigDecimal rta = null;
        String importe = "100.455";
        try {
            rta = ISBANStringUtils.convertirImporte(importe);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.455"),rta);
    }
    @Test
    public void cuandoSeIngresa102Con45CentavosConvertirImporteConNCantDeDecimalesRetorna100Con45Centavos() {
        BigDecimal rta = null;
        String importe = "100.45";
        try {
            rta = ISBANStringUtils.convertirImporteConNCantDeDecimales(importe, 2);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.45"),rta);
    }
    @Test
    public void cuandoSeIngresa102Con455CentavosConvertirImporteConNCantDeDecimalesRetorna100Con45Centavos() {
        BigDecimal rta = null;
        String importe = "100.455";
        try {
            rta = ISBANStringUtils.convertirImporteConNCantDeDecimales(importe, 2);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.45"),rta);
    }

    @Test
    public void cuandoSeIngresa102Con458CentavosConvertirImporteConNCantDeDecimalesRetorna100Con45Centavos() {
        BigDecimal rta = null;
        String importe = "100.458";
        try {
            rta = ISBANStringUtils.convertirImporteConNCantDeDecimales(importe, 2);
        } catch (ImporteConvertException e) {
            Assert.assertNull(rta);
        }
        Assert.assertNotNull(rta);
        Assert.assertEquals(new BigDecimal("100.45"),rta);
    }
    //Fin fix TRFIND-495: Completar decimales en la carga de importe de TIS
    /**
     * Mail 1.
     */
    @Test
    public void mail1() {
        Assert.assertFalse(ISBANStringUtils.validarEmail("tito"));
    }

    /**
     * Mail 2.
     */
    @Test
    public void mail2() {
        Assert.assertFalse(ISBANStringUtils.validarEmail("tito@"));
    }

    /**
     * Mail 3.
     */
    @Test
    public void mail3() {
        Assert.assertTrue(ISBANStringUtils.validarEmail("tito@hotmail.com"));
    }

    /**
     * Testea el correcto funcionamiento del metodo
     * "convertirStringToCamelcase()".
     * 
     * @author florencia.n.martinez
     */
    @Test
    public void convertirStringToCamelcaseTest() {
        String string = ISBANStringUtils.convertirStringToCamelcase("ISBAN STRING UTILS");
        LOGGER.info("String to Camelcase: {}.", string.toString());
        Assert.assertNotNull("No se puedo convertir el String a Camelcase.", string);
    }

    /**
     * Testea el correcto funcionamiento del metodo
     * "convertirStringToLowercase()".
     * 
     * @author florencia.n.martinez
     */
    @Test
    public void convertirStringToLowercaseTest() {
        String string = ISBANStringUtils.convertirStringToLowercase("WWW.MERCADOLIBRE.COM.AR");
        LOGGER.info("String to Lowercase: {}.", string.toString());
        Assert.assertNotNull("No se puedo convertir el String a Lowercase.", string);
    }

    /**
     * Formateador saldo prestamo test.
     */
    @Test
    public void formateadorSaldoPrestamoTest() {
        assertEquals(ISBANStringUtils.formateadorSaldoPrestamo(new BigDecimal("1"), 17), "00000000000010000");
        assertEquals(ISBANStringUtils.formateadorSaldoPrestamo(new BigDecimal("1.1"), 17), "00000000000011000");
        assertEquals(ISBANStringUtils.formateadorSaldoPrestamo(new BigDecimal("1.1234"), 17), "00000000000011234");
        assertEquals(ISBANStringUtils.formateadorSaldoPrestamo(new BigDecimal("1.12345"), 17), "00000000000011234");
    }

    /**
     * Convertir str to big decimal test.
     *
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    @Test
    public void convertirStrToBigDecimalTest() throws ImporteConvertException {
        assertNotNull(ISBANStringUtils.convertirStrToBigDecimal("00000000000010000+", 4));
        assertNotNull(ISBANStringUtils.convertirStrToBigDecimal("00000000000010000-", 4));
    }

    /**
     * Remover caraceteres especiales test.
     *
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    @Test
    public void removerCaraceteresEspecialesTest() throws ImporteConvertException {
        // Assert.assertEquals(ISBANStringUtils.removerCaraceteresEspeciales("��������������u�������������������"),
        // "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC");
    }

    /**
     * Normalizar campo alfanumerico iatx.
     */
    @Test
    public void normalizarCampoAlfanumericoIatx() {
        Assert.assertEquals("abc", ISBANStringUtils.normalizarCampoAlfanumericoIatx("abcdefg", 3));
        Assert.assertEquals("abcdefg   ", ISBANStringUtils.normalizarCampoAlfanumericoIatx("abcdefg", 10));
        Assert.assertEquals("", ISBANStringUtils.normalizarCampoAlfanumericoIatx("abcdefg", 0));
        Assert.assertEquals("    ", ISBANStringUtils.normalizarCampoAlfanumericoIatx(null, 4));
        Assert.assertEquals(" abc ", ISBANStringUtils.normalizarCampoAlfanumericoIatx(" abc     ", 5));
    }

    /**
     * Parseo BigDecimals.
     */
    @Test
    public void parseStringToBigDecimalTest() {

        Assert.assertEquals(new BigDecimal("0.01"), ISBANStringUtils.parseStringToBigDecimal("000000001"));
        Assert.assertEquals(new BigDecimal("0.01"), ISBANStringUtils.parseStringToBigDecimal("000000001+"));
        Assert.assertEquals(new BigDecimal("0.10"), ISBANStringUtils.parseStringToBigDecimal("000000010+"));
        Assert.assertEquals(new BigDecimal("-0.10"), ISBANStringUtils.parseStringToBigDecimal("000000010-"));
        Assert.assertEquals(new BigDecimal("-0.01"), ISBANStringUtils.parseStringToBigDecimal("000000001-"));
        Assert.assertEquals(new BigDecimal("0.00"), ISBANStringUtils.parseStringToBigDecimal("00000000000000"));

    }

    /**
     * En importe pto fijo 2 canonico cuando ingresa un digito convierte
     * centavos.
     */
    @Test
    public void enImportePtoFijo2CanonicoCuandoIngresaUnDigitoConvierteCentavos() {
        // cuando
        String importe = "1";
        final String importeFormateado = "0.01";
        String msgError = "Error al tratar de formatear el valor: ".concat(importe).concat(" Valor esperado: ")
                .concat(importeFormateado);

        // entonces
        String resultado = ISBANStringUtils.importePtoFijo2Canonico(importe);

        // esperado
        Assert.assertEquals(msgError, importeFormateado, resultado);
    }

    /**
     * En importe pto fijo 2 canonico cuando ingresa un monto convierte
     * centavos.
     */
    @Test
    public void enImportePtoFijo2CanonicoCuandoIngresaUnMontoConvierteCentavos() {
        // cuando
        String importe = "1000";
        final String importeFormateado = "10.00";
        String msgError = "Error al tratar de formatear el valor: ".concat(importe).concat(" Valor esperado: ")
                .concat(importeFormateado);

        // entonces
        String resultado = ISBANStringUtils.importePtoFijo2Canonico(importe);

        // esperado
        Assert.assertEquals(msgError, importeFormateado, resultado);
    }

    /**
     * Prueba formateo saldos con ceros Y signos en centavos.
     */
    @Test
    public void pruebaFormateoSaldosConCerosYSignosEnCentavos() {

        // cuando
        String monto = "00000001";
        final String montoFormateado = "0,01";
        String msgError = "Error al tratar de formatear el valor: ".concat(monto).concat(" Valor esperado: ")
                .concat(montoFormateado);

        // entonces
        String resultado = ISBANStringUtils.formatearSaldosConCerosYSignos(monto);

        // esperado
        LOGGER.info("Monto formateado: {} ", resultado);
        Assert.assertEquals(msgError, montoFormateado, resultado);

    }

    /**
     * Prueba formateo saldos con ceros Y signos en cientos.
     */
    @Test
    public void pruebaFormateoSaldosConCerosYSignosEnCientos() {

        // cuando
        String monto = "000010000";
        final String montoFormateado = "100,00";
        String msgError = "Error al tratar de formatear el valor: ".concat(monto).concat(" Valor esperado: ")
                .concat(montoFormateado);

        // entonces
        String resultado = ISBANStringUtils.formatearSaldosConCerosYSignos(monto);

        // esperado
        LOGGER.info("Monto formateado: {} ", resultado);
        Assert.assertEquals(msgError, montoFormateado, resultado);

    }

    /**
     * Prueba formateo saldos con ceros Y signos en miles.
     */
    @Test
    public void pruebaFormateoSaldosConCerosYSignosEnMiles() {

        // cuando
        String monto = "00001000000";
        final String montoFormateado = "10.000,00";
        String msgError = "Error al tratar de formatear el valor: ".concat(monto).concat(" Valor esperado: ")
                .concat(montoFormateado);

        // entonces
        String resultado = ISBANStringUtils.formatearSaldosConCerosYSignos(monto);

        // esperado
        LOGGER.info("Monto formateado: {} ", resultado);
        Assert.assertEquals(msgError, montoFormateado, resultado);

    }

    /**
     * Prueba formateo saldos con ceros Y signos en millones.
     */
    @Test
    public void pruebaFormateoSaldosConCerosYSignosEnMillones() {

        // cuando
        String monto = "0000100000000";
        final String montoFormateado = "1.000.000,00";
        String msgError = "Error al tratar de formatear el valor: ".concat(monto).concat(" Valor esperado: ")
                .concat(montoFormateado);

        // entonces
        String resultado = ISBANStringUtils.formatearSaldosConCerosYSignos(monto);

        // esperado
        LOGGER.info("Monto formateado: {} ", resultado);
        Assert.assertEquals(msgError, montoFormateado, resultado);

    }

    /**
     * Prueba ajustador big decimal centena con decimales.
     */
    @Test
    public void pruebaAjustadorBigDecimalCentenaConDecimales() {
        // when
        BigDecimal valor = new BigDecimal("22000.1234");
        int cantidadRelleno = 14;
        // then
        String formatoTrama = ISBANStringUtils.ajustadorBigDecimalIatx(valor, cantidadRelleno);
        // expect
        Assert.assertEquals("Error al tranformar valor centena con decimales", "00000002200012", formatoTrama);
    }

    /**
     * Prueba ajustador big decimal centena sin decimales.
     */
    @Test
    public void pruebaAjustadorBigDecimalCentenaConDecimales2() {
        // when
        BigDecimal valor = new BigDecimal("200");
        int cantidadRelleno = 14;
        // then
        String formatoTrama = ISBANStringUtils.ajustadorBigDecimalIatx(valor, cantidadRelleno);
        // expect
        Assert.assertEquals("Error al tranformar valor centena con decimales", "00000000020000", formatoTrama);
    }

    /**
     * Prueba ajustador big decimal centena sin decimales.
     */
    @Test
    public void pruebaAjustadorBigDecimalCentenaSinDecimales() {
        // when
        BigDecimal valor = new BigDecimal("22");
        int cantidadRelleno = 14;
        // then
        String formatoTrama = ISBANStringUtils.ajustadorBigDecimalIatx(valor, cantidadRelleno);
        // expect
        Assert.assertEquals("Error al tranformar valor centena con decimales", "00000000002200", formatoTrama);
    }

    /**
     * Prueba ajustador big decimal centena con un decimal.
     */
    @Test
    public void pruebaAjustadorBigDecimalCentenaConUnDecimal() {
        // when
        BigDecimal valor = new BigDecimal("22.1");
        int cantidadRelleno = 14;
        // then
        String formatoTrama = ISBANStringUtils.ajustadorBigDecimalIatx(valor, cantidadRelleno);
        // expect
        Assert.assertEquals("Error al tranformar valor centena con decimales", "00000000002210", formatoTrama);
    }

    /**
     * Prueba ajustador big decimal valor nulo.
     */
    @Test
    public void pruebaAjustadorBigDecimalValorNulo() {
        // when
        BigDecimal valor = null;
        int cantidadRelleno = 14;
        // then
        String formatoTrama = ISBANStringUtils.ajustadorBigDecimalIatx(valor, cantidadRelleno);
        // expect
        Assert.assertEquals("Error al tranformar valor centena con decimales", StringUtils.EMPTY, formatoTrama);
    }

    /**
     * Prueba ajustador big decimal valor nulo.
     */
    @Test
    public void formatearCuatroDecimales() {
        String cuotaPartes = "999999999999";
        String saldoFormateado = ISBANStringUtils.formatearSaldoCuatroDecimales(cuotaPartes);
        String resultadoEsperado = "99.999.999,9999";
        Assert.assertEquals(saldoFormateado, resultadoEsperado);

    }

    
    @Test
    public void formatearCuatroDecimalesConPunto() {
        String cuotaPartes = "3.3456";
        cuotaPartes =  cuotaPartes.replaceAll(",", "");
        cuotaPartes = cuotaPartes.replaceAll("\\.", "");
        String saldoFormateado = ISBANStringUtils.formatearSaldoCuatroDecimales(cuotaPartes);
        String resultadoEsperado = "3,3456";
        Assert.assertEquals(saldoFormateado, resultadoEsperado);

    }
    
    @Test
    public void dadoUnSaldoNullDevuelveCero() {
    	// when
    	String formatoEsperado = "0,00";
        
    	// then
    	String saldo = ISBANStringUtils.formatearSaldo(null);
    	
    	// expect
    	Assert.assertEquals(saldo, formatoEsperado);
    }
    
    @Test
    public void formatearNumericosParaIatxSeparadoPorPunto(){
    	String numeroAformatear = "1523";
    	
    	String resultado = ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(numeroAformatear, 8, 4);
    	String esperado = "15230000";
    	Assert.assertEquals(esperado, resultado);
    }
    
    @Test
    public void isFormatoAliasValidoOK(){
    	Boolean isValido = ISBANStringUtils.isFormatoAliasValido("Alias-1234-Z");
    	Boolean esperado = true;
    	Assert.assertEquals(esperado, isValido);
    }
    
    @Test
    public void formatearNumeroDeCuentaTestOK(){
    	Cuenta cuenta = new Cuenta();
    	cuenta.setNroSucursal("000222");
    	cuenta.setNroCuentaProducto("000067899099");
		String resultado = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
    	Assert.assertEquals("222-6789909/9", resultado);
    }
    
    @Test
    public void obtenerPorcentajeTest(){
		BigDecimal resultado = ISBANStringUtils.obtenerPorcentaje(new BigDecimal("70"), new BigDecimal("300"));
		Assert.assertEquals(new BigDecimal("23.33"), resultado);
    }
    
    
    @Test
    public void esCeroTest() {
    	Boolean isValido = ISBANStringUtils.esCero("$ 10,00");
    	Boolean esperado = false;
    	Assert.assertEquals(esperado, isValido);
    }

    @Test
    public void isEmptyOrNull_empty() {
        Assert.assertTrue(ISBANStringUtils.isEmptyOrNull(null));
        Assert.assertTrue(ISBANStringUtils.isEmptyOrNull(StringUtils.EMPTY));
    }

    @Test
    public void isEmptyOrNull_notEmpty() {
        Assert.assertFalse(ISBANStringUtils.isEmptyOrNull("notEmpty"));
    }

    @Test
    public void cbuLength() {
        Assert.assertEquals(22, ISBANStringUtils.getCbuLength());
    }

    @Test
    public void normalizarCaracteres_empty() {
        Assert.assertEquals(StringUtils.EMPTY, ISBANStringUtils.normalizarCaraceteres(null));
    }

    @Test
    public void normalizarCaracteres_notEmpty() {
        String expected = "abcde";
        Assert.assertEquals(expected, ISBANStringUtils.normalizarCaraceteres(" abcde "));
        Assert.assertEquals(expected, ISBANStringUtils.normalizarCaraceteres("aBcDe"));
        Assert.assertEquals(expected, ISBANStringUtils.normalizarCaraceteres("ábcdé"));
    }

    @Test
    public void formatearDocumento_empty() {
        Assert.assertEquals(StringUtils.EMPTY, ISBANStringUtils.formatearDocumento(null));
    }

    @Test
    public void formatearDocumento_notEmpty() {
        Assert.assertEquals("1.234.567", ISBANStringUtils.formatearDocumento("1234567"));
        Assert.assertEquals("12.345.678", ISBANStringUtils.formatearDocumento("12345678"));
    }

    @Test
    public void ofuscarMail_empty() {
        Assert.assertNull(ISBANStringUtils.ofuscarMail(null));
        Assert.assertEquals(StringUtils.EMPTY, ISBANStringUtils.ofuscarMail(StringUtils.EMPTY));
    }

    @Test
    public void ofuscarMail_notEmpty() {
        Assert.assertEquals("san******@santandertecnologia.com.ar",
                ISBANStringUtils.ofuscarMail("santander@santandertecnologia.com.ar"));
    }

    @Test
    public void IsCBUCtaRecaudadora(){
        Assert.assertTrue(ISBANStringUtils.validarCBUCtaRecaudadora("0720999870000025825878"));
    }

    @Test
    public void IsNotCBUCtaRecaudadora(){
        Assert.assertFalse(ISBANStringUtils.validarCBUCtaRecaudadora("0720999820000025825877"));
    }
}
