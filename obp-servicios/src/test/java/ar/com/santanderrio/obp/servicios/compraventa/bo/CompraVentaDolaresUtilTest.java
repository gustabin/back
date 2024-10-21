
/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CompraVentaDolaresUtilTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class CompraVentaDolaresUtilTest {

    /** The compra venta dolares util. */
    @InjectMocks
    private CompraVentaDolaresUtil compraVentaDolaresUtil = new CompraVentaDolaresUtil();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Test para verificar el comportamiento del metodo obtenerAplicacion.
     * Validacion en caso de compra. Si el tipo_cuenta=[01|02] debe enviar ACAH
     * Si el tipo_cuenta=[00] debe enviar ACTE Si el tipo_cuenta=[04|02] debe
     * enviar ACAD Si el tipo_cuenta=[03] debe enviar ACCD
     */
    @Test
    public void obtenerAplicacionOKTest() {
        String respuesta = null;
        Cuenta cuenta = getCuenta();

        // valida caja de ahorro en pesos debe devolver ACAH
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        respuesta = compraVentaDolaresUtil.obtenerAplicacion(cuenta, "C");
        String retornoFactory = "ACAH";
        assertEquals(respuesta, retornoFactory);

        // valida caja de ahorro en dolares debe devolver ACAH
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        respuesta = compraVentaDolaresUtil.obtenerAplicacion(cuenta, "C");
        assertEquals(respuesta, retornoFactory);

        // valida cuenta corriente en pesos debe devolver ACTE
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        respuesta = compraVentaDolaresUtil.obtenerAplicacion(cuenta, "C");
        retornoFactory = "ACTE";
        assertEquals(respuesta, retornoFactory);

        // valida caja de ahorro en dolares debe devolver ACAD
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
        respuesta = compraVentaDolaresUtil.obtenerAplicacion(cuenta, "C");
        retornoFactory = "ACAD";
        assertEquals(respuesta, retornoFactory);

        // valida que otros debe devolver null
        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        respuesta = compraVentaDolaresUtil.obtenerAplicacion(cuenta, "V");
        assertEquals(null, respuesta);

        // valida caja de ahorro en dolares debe devolver ACAD para venta
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
        respuesta = compraVentaDolaresUtil.obtenerAplicacion(cuenta, "V");
        retornoFactory = "ACAD";
        assertEquals(respuesta, retornoFactory);

        // valida cuenta unica debe devolver ACAD
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        respuesta = compraVentaDolaresUtil.obtenerAplicacion(cuenta, "V");
        retornoFactory = "ACAD";
        assertEquals(respuesta, retornoFactory);

        // valida cuenta corriente dolares debe devolver ACCD
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        respuesta = compraVentaDolaresUtil.obtenerAplicacion(cuenta, "V");
        retornoFactory = "ACCD";
        assertEquals(respuesta, retornoFactory);

    }

    /**
     * Obtiene el codigo del tipo de cuenta, para la cuenta que ingresa por
     * parametro.
     */
    @Test
    public void obtenerCodigoTipoCuenta() {
        Cuenta cuentaOrigen = getCuenta();
        cuentaOrigen.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        Integer respuesta = compraVentaDolaresUtil.obtenerCodigoTipoCuenta(cuentaOrigen);
        assertTrue("CUENTA_CORRIENTE_PESOS", respuesta == 0);
    }

    /**
     * tipo Operacion Compra.
     *
     * @return the boolean
     */
    @Test
    public void esTipoOperacionCompraTest() {
        Boolean respuesta = compraVentaDolaresUtil.esTipoOperacionCompra(CompraVentaStringUtil.OPERACION_COMPRA);
        assertEquals(respuesta, true);
    }

    /**
     * 00: Cuenta Corriente en Pesos.
     *
     * @return the boolean
     */
    @Test
    public void esCuentaCorrienteEnPesosTest() {
        Boolean respuesta = compraVentaDolaresUtil
                .esCuentaCorrienteEnPesos(getTipoCuenta(TipoCuenta.CUENTA_CORRIENTE_PESOS));
        assertEquals(respuesta, true);
    }

    /**
     * 01: Caja de Ahorro en Pesos.
     *
     * @return the boolean
     */
    @Test
    public void esCajaDeAhorroEnPesosTest() {
        Boolean respuesta = compraVentaDolaresUtil.esCajaDeAhorroEnPesos(getTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS));
        assertEquals(respuesta, true);
    }

    /**
     * 03: Cuenta Corriente en Dolares.
     *
     * @return the boolean
     */
    @Test
    public void esCuentaCorrienteEnDolaresTest() {
        Boolean respuesta = compraVentaDolaresUtil
                .esCuentaCorrienteEnDolares(getTipoCuenta(TipoCuenta.CUENTA_CORRIENTE_DOLARES));
        assertEquals(respuesta, true);
    }

    /**
     * 04: Caja de Ahorro en Dolares.
     *
     * @return the boolean
     */
    @Test
    public void esCajaDeAhorroEnDolaresTest() {
        Boolean respuesta = compraVentaDolaresUtil
                .esCajaDeAhorroEnDolares(getTipoCuenta(TipoCuenta.CAJA_AHORRO_DOLARES));
        assertEquals(respuesta, true);
    }

    /**
     * 02 o 09 o 10: Cuenta Unica (pesos + dolares).
     *
     * @return the boolean
     */
    @Test
    public void esDelTipoCuentaUnicaTest() {
        Boolean respuesta = compraVentaDolaresUtil.esDelTipoCuentaUnica(getTipoCuenta(TipoCuenta.CUENTA_UNICA));
        assertTrue(respuesta);

        respuesta = compraVentaDolaresUtil.esDelTipoCuentaUnica(getTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS));
        assertTrue(respuesta);

        respuesta = compraVentaDolaresUtil.esDelTipoCuentaUnica(getTipoCuenta(TipoCuenta.CUENTA_UNICA_DOLARES));
        assertTrue(respuesta);

    }

    /**
     * 02: Cuenta Unica (pesos + dolares).
     *
     * @return the boolean
     */
    @Test
    public void esCuentaUnicaTest() {
        Boolean respuesta = compraVentaDolaresUtil.esCuentaUnica(getTipoCuenta(TipoCuenta.CUENTA_UNICA));
        assertEquals(respuesta, true);
    }

    /**
     * 09: Cuenta Unica en pesos.
     *
     * @return the boolean
     */
    @Test
    public void esCuentaUnicaEnPesosTest() {
        Boolean respuesta = compraVentaDolaresUtil.esCuentaUnicaEnPesos(getTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS));
        assertEquals(respuesta, true);
    }

    /**
     * 10: Cuenta Unica en dolares.
     *
     * @return the boolean
     */
    @Test
    public void esCuentaUnicaEnDolaresTest() {
        Boolean respuesta = compraVentaDolaresUtil
                .esCuentaUnicaEnDolares(getTipoCuenta(TipoCuenta.CUENTA_UNICA_DOLARES));
        assertEquals(respuesta, true);
    }

    /**
     * Test para verificar el comportamiento del metodo
     * obtenerCuentaDebitoOperante. IDECLTSDO.Nro_cuenta_Producto Enviar los 12
     * digitos de la derecha.
     */
    @Test
    public void obtenerCuentaDebitoOperanteTest() {
        Cuenta cuenta = getCuenta();
        cuenta.setNroCuentaProducto("0000000338501392");
        String respuesta = compraVentaDolaresUtil.obtenerCuentaDebitoOperante(cuenta);
        assertEquals(respuesta, "000338501392");
    }

    /**
     * Test para verificar el comportamiento del metodo obtenerImporteDebito
     * para importe en dolares. Si el importe ingresado es en pesos Enviar
     * [Importe ingresado] Si el importe ingresado es en dólares Enviar [Importe
     * Ingresado]*CNSCOTCN.COTIZ-SALIDA
     * 
     * Se trunca parte decimal a 2 decimales.
     */
    // @Test
    public void obtenerImporteDebitoTest() {
        // Respuesta Dolar OK
        try {
            String respuesta = compraVentaDolaresUtil.obtenerImporteDebito("45.0", "14,99", true);
            assertEquals(respuesta, "000000000067455");
        } catch (CompraVentaDolaresException e) {
            System.out.println(e);
        }
    }

    /**
     * Importe debito number format exception test.
     */
    @Test
    public void importeDebitoNumberFormatExceptionTest() {
        boolean thrown = false;
        // Error NumberFormatException
        try {
            String respuesta = compraVentaDolaresUtil.obtenerImporteDebito("45.0", "#14,99", true);
            assertEquals(respuesta, "000000000067455");
        } catch (CompraVentaDolaresException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Test para verificar el comportamiento del metodo obtenerImporteDebito
     * Importe en Pesos. Si el importe ingresado es en pesos Enviar [Importe
     * ingresado] Si el importe ingresado es en dólares Enviar [Importe
     * Ingresado]*CNSCOTCN.COTIZ-SALIDA
     * 
     * Se trunca parte decimal a 2 decimales.
     */
    @Test
    public void obtenerPesosImporteDebitoTest() {
        try {
            String respuesta = compraVentaDolaresUtil.obtenerImporteDebito("45.0", "14,99", false);
            assertEquals(respuesta, "000000000004500");
        } catch (CompraVentaDolaresException e) {
            System.out.println(e);
        }
    }

    /**
     * Test para verificar el comportamiento del metodo obtenerFechVal Fecha
     * SIMCPVTACN.FECHVAL Formato dd/mm/aaaa
     * 
     * Se trunca parte decimal a 2 decimales.
     */
    @Test
    public void obtenerFechValTest() {
        try {
            String respuesta = compraVentaDolaresUtil.obtenerFechVal("2016-06-14");
            assertNotNull(respuesta);
        } catch (CompraVentaDolaresException e) {
            System.out.println(e);
        }
    }

    /**
     * Corta el numero de sucursal obtenido desde la cuenta.
     */
    @Test
    public void obtenerSucursalTresDigitosTest() {
        String codSucursal = compraVentaDolaresUtil.obtenerSucursalTresDigitos(getCuenta());
        Assert.assertNotNull("Hubo un error al querer obtener el numero de sucursal de la cuenta.",
                codSucursal.toString());
        Assert.assertEquals("033", codSucursal);
    }

    /**
     * Gets the cliente.
     *
     * @return the cliente
     */
    private Cliente getCliente() {
        return mock(Cliente.class);
    }

    /**
     * Formatear fecha OK.
     */
    @Test
    public void formatearFecha_OK() {
        try {
            String fechaValida = compraVentaDolaresUtil.formatearFecha("20160614130515");
            String fechaEsperada = "14/06/2016 - 13:05";
            assertEquals(fechaValida, fechaEsperada);

        } catch (CompraVentaDolaresException e) {
            e.printStackTrace();
        }

    }

    /**
     * Formatear fecha ERRO R NULL.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test(expected = CompraVentaDolaresException.class)
    public void formatearFecha_ERROR_NULL() throws CompraVentaDolaresException {
        compraVentaDolaresUtil.formatearFecha(null);
    }

    /**
     * Formatear fecha out of bounds.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test(expected = CompraVentaDolaresException.class)
    public void formatearFecha_OutOfBounds() throws CompraVentaDolaresException {
        compraVentaDolaresUtil.formatearFecha("2016");
    }

    /**
     * Obtener hora OK.
     */
    @Test
    public void obtenerHora_OK() {
        try {
            String horaObtenida = compraVentaDolaresUtil.obtenerHora("20160614130515");
            String horaValida = "13:05";
            assertEquals(horaObtenida, horaValida);
        } catch (CompraVentaDolaresException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtener numero operacion venticuatro digitos.
     */
    @Test
    public void obtenerNumeroOperacionVenticuatroDigitos() {
        String resultado = compraVentaDolaresUtil.obtenerNumeroOperacionVenticuatroDigitos("000000000AAAAAAAAAABBB11");
        assertEquals(resultado, "000000000AAAAAAAAAABBB11");
    }

    /**
     * Convertir importe test.
     */
    @Test
    public void convertirImporteTest() {
        try {
            String respuesta = compraVentaDolaresUtil.convertirImporte("0000000000011+");
            assertEquals(respuesta, "11.00");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Setear espacios.
     */
    @Test
    public void setearEspacios() {
        String cadena1 = compraVentaDolaresUtil.setearEspacios(5);
        assertEquals(cadena1, "     ");

    }

    /**
     * Gets the cuenta.
     *
     * @return the cuenta
     */
    private Cuenta getCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("Alias Nombre");
        cuenta.setCliente(getCliente());
        cuenta.setIsFavorita(true);
        cuenta.setNroCuentaProducto("0000000338501392");
        cuenta.setNroSucursal("0033");
        return cuenta;
    }

    /**
     * Gets the tipo de cuenta.
     *
     * @param tipoCuentaEnum
     *            the tipo cuenta enum
     * @return the Integer
     */
    private Integer getTipoCuenta(TipoCuenta tipoCuentaEnum) {
        Cuenta cuenta = getCuenta();
        cuenta.setTipoCuentaEnum(tipoCuentaEnum);
        TipoCuenta tipoCuenta = cuenta.getTipoCuentaEnum();
        return tipoCuenta.getCodigo();
    }

    /**
     * N16(13e,2d,signo) importe del abono (dolares).
     */
    @Test
    public void convertirImporteSimulacionTest() {
        try {
            String respuesta = compraVentaDolaresUtil.convertirSimulacionImporteAComprar("000000000100000+");
            assertEquals(respuesta, "1000,00");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * N16(11e,4d,signo) cotización utilizada para la contra- valoración *
     * SIMCPVTACN.IMPCOTI
     */
    @Test
    public void obtenerCotizacionAplicableTest() {
        try {
            String respuesta = compraVentaDolaresUtil.obtenerCotizacionAplicable("000000000140500+");
            assertEquals(respuesta, "14,05");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * N16(13e,2d,signo) total importe cargo $ + SIMCPVTACN.TOTCARG
     */
    @Test
    public void convertirSimulacionImporteDebitarTest() {
        try {
            String respuesta = compraVentaDolaresUtil.convertirSimulacionImporteDebitar("000000000001405+");
            assertEquals(respuesta, "14,05");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * N15 (13e,2d) Recibe importe credito formato dos enteros (coma) dos
     * decimales, retorna formateado sin coma y rellena con ceros hasta
     * completar 15 digitos.
     */
    @Test
    public void obtenerImporteCredOperacionCompraTest() {
        try {
            String respuesta = compraVentaDolaresUtil.obtenerImporteCredOperacionCompra("15,10");
            assertEquals(respuesta, "000000000001510");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Obtener importe debito 2 test.
     */
    @Test
    public void obtenerImporteDebito2Test() {
        try {
            Locale.setDefault(Locale.ENGLISH);
            String respuesta = compraVentaDolaresUtil.obtenerImporteDebito("36,00", "15,10", true);
            assertEquals(respuesta, "000000000054360");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Genera un String que representa un decimal, con cantidad de decimales
     * segun el parametro ingresado.
     */
    @Test
    public void generarNumeroDecimalTest() {
        try {
            String dato = "00153300000";
            int digitosParteEntera = 4;
            Locale.setDefault(Locale.ENGLISH);
            String respuesta = compraVentaDolaresUtil.generarNumeroDecimal(dato, digitosParteEntera);
            assertEquals(respuesta, "15.33");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Obtener aplicacion CCD otros test.
     */
    @Test
    public void obtenerAplicacionCCDOtrosTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        assertEquals(CompraVentaStringUtil.TIPO_APLICACION_ACCD, compraVentaDolaresUtil.obtenerAplicacion(cuenta, ""));

        cuenta.setTipoCuentaEnum(TipoCuenta.ABON);
        assertEquals(null, compraVentaDolaresUtil.obtenerAplicacion(cuenta, ""));
    }

    /**
     * Obtener opcion toma cotizacion test.
     */
    @Test
    public void obtenerOpcionTomaCotizacionTest() {
        assertEquals(CompraVentaStringUtil.OPERACION_COMPRA_ENVIO,
                compraVentaDolaresUtil.obtenerOpcionTomaCotizacion("C"));
        assertEquals(CompraVentaStringUtil.OPERACION_VENTA_ENVIO,
                compraVentaDolaresUtil.obtenerOpcionTomaCotizacion("V"));
        assertEquals(null, compraVentaDolaresUtil.obtenerOpcionTomaCotizacion("P"));
    }

    /**
     * Obtener detalle operacion test.
     */
    @Test
    public void obtenerDetalleOperacionTest() {
        assertEquals(CompraVentaStringUtil.DETALLE_COMPRA, CompraVentaDolaresUtil.obtenerDetalleOperacion("C"));
        assertEquals(CompraVentaStringUtil.DETALLE_VENTA, CompraVentaDolaresUtil.obtenerDetalleOperacion("V"));
        assertEquals(null, CompraVentaDolaresUtil.obtenerDetalleOperacion("ASD"));
    }

    /**
     * Obtener tipo operacion test.
     */
    @Test
    public void obtenerTipoOperacionTest() {
        assertEquals(CompraVentaStringUtil.OPERACION_VENTA, compraVentaDolaresUtil.obtenerTipoOperacion("C"));
        assertEquals(CompraVentaStringUtil.OPERACION_COMPRA, compraVentaDolaresUtil.obtenerTipoOperacion("V"));
        assertEquals(null, compraVentaDolaresUtil.obtenerTipoOperacion("ASD"));
    }

    /**
     * Tipos varios operaciones test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void tiposVariosOperacionesTest() throws CompraVentaDolaresException {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("123456789101112");
        assertEquals("012", compraVentaDolaresUtil.obtenerSegmento(cuenta));
        assertEquals("012", compraVentaDolaresUtil.obtenerSucursalCtaDolar(cuenta));
        assertEquals("1234.59", compraVentaDolaresUtil.parcearCotizacionDosDecimales("12345876123"));
        assertEquals("1234.59", compraVentaDolaresUtil.parcearCotizacionCuatroDecimales("12345876123"));
        assertEquals("456789101112", compraVentaDolaresUtil.obtenerNumeroCtaDoceDigitos(cuenta));
        assertEquals(CompraVentaStringUtil.IND_TU_ATESORA, compraVentaDolaresUtil.obtenerIndTuAtesora());
        assertEquals(CompraVentaStringUtil.IND_TU_ATESORA_VENTA, compraVentaDolaresUtil.obtenerIndTuAtesoraA());
        assertEquals(CompraVentaStringUtil.CODIGO_DEBITO, compraVentaDolaresUtil.obtenerCodigoDebi());
        assertEquals(CompraVentaStringUtil.CODIGO_CREDITO, compraVentaDolaresUtil.obtenerCodigoCre());
        assertEquals(CompraVentaStringUtil.IND_TU_ATESORA, compraVentaDolaresUtil.obtenerCodTributa());
        assertEquals(CompraVentaStringUtil.COD_TRIBUTA, compraVentaDolaresUtil.obtenerCodTributaVenta());
        assertEquals(CompraVentaStringUtil.CODIGO_CONCEPTO, compraVentaDolaresUtil.obtenerCodigoConcepto());
        assertEquals(new SimpleDateFormat(CompraVentaStringUtil.FECHA_GUION).format(new Date()),
                compraVentaDolaresUtil.obtenerFechaValor());
        assertEquals(CompraVentaStringUtil.CONDICION_TITULAR, compraVentaDolaresUtil.obtenerCndCliente());
        assertEquals("1268,00", compraVentaDolaresUtil.obtenerCotizacionString("1268"));
        assertEquals(StringUtils.left("Tasdasdsd", CompraVentaStringUtil.LONGITUD_TPODOC_BCRA),
                compraVentaDolaresUtil.obtenerDocBcra("T"));
        assertEquals("12345678910.11", compraVentaDolaresUtil.convertirImpCoti("1234567891011"));
        assertEquals("123456789101112", compraVentaDolaresUtil.obtenerNumeroCtaDolar(cuenta));
        assertEquals("23456789", compraVentaDolaresUtil.obtenerNumeroComprobante("123456789"));
        assertEquals("1234567891011.12",
                compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto("123456789101112"));
        assertEquals("1234,57", compraVentaDolaresUtil.obtenerCotizacionAplicableVenta("1234567"));
        assertEquals("1234.57", compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto("1234567"));
        assertEquals("1234567891011.12",
                compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto("123456789101112"));
        assertEquals(CompraVentaStringUtil.CODIGO_CREDITO_VENTA, compraVentaDolaresUtil.obtenerCodigoCreVenta());
        assertEquals(CompraVentaStringUtil.CODIGO_DEBITO_VENTA, compraVentaDolaresUtil.obtenerCodigoDebiVenta());
    }

    /**
     * Generar numero decimal exception test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test(expected = NullPointerException.class)
    public void generarNumeroDecimalExceptionTest() throws CompraVentaDolaresException {
        compraVentaDolaresUtil.generarNumeroDecimal(null, 0);
    }

    /**
     * Obtener fech val exception test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test(expected = CompraVentaDolaresException.class)
    public void obtenerFechValExceptionTest() throws CompraVentaDolaresException {
        compraVentaDolaresUtil.obtenerFechVal("dasd");
    }

    /**
     * Obtener hora exception test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test(expected = CompraVentaDolaresException.class)
    public void obtenerHoraExceptionTest() throws CompraVentaDolaresException {
        compraVentaDolaresUtil.obtenerHora("dasd");
    }

    /**
     * Formatear fecha sin hora test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void formatearFechaSinHoraTest() throws CompraVentaDolaresException {
        assertEquals("18/01/2017", compraVentaDolaresUtil.formatearFechaSinHora("20170118"));
    }

    /**
     * Formatear fecha sin hora exception test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test(expected = CompraVentaDolaresException.class)
    public void formatearFechaSinHoraExceptionTest() throws CompraVentaDolaresException {
        compraVentaDolaresUtil.formatearFechaSinHora("dasd");
    }

    /**
     * Obtener importe coti test.
     */
    @Test
    public void obtenerImporteCotiTest() {
        String res = compraVentaDolaresUtil.obtenerImporteCoti("16,25");
        assertEquals("000000000162500", res);
    }

    /**
     * Obtener nom apell test.
     */
    @Test
    public void obtenerNomApellTest() {
        Cliente cliente = new Cliente();
        cliente.setNombre("jose");
        cliente.setApellido1("barreras");
        assertEquals("                                               jose barreras",
                compraVentaDolaresUtil.obtenerNomApell(cliente));
    }

    /**
     * Obtener importe debito venta test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void obtenerImporteDebitoVentaTest() throws CompraVentaDolaresException {
        assertEquals("000000000000200", compraVentaDolaresUtil.obtenerImporteDebitoVenta("2,00", "16,35", true));
        assertEquals("000000000000012", compraVentaDolaresUtil.obtenerImporteDebitoVenta("2,00", "16,35", false));
    }

    /**
     * Obtener importe debito venta exception test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test(expected = CompraVentaDolaresException.class)
    public void obtenerImporteDebitoVentaExceptionTest() throws CompraVentaDolaresException {
        compraVentaDolaresUtil.obtenerImporteDebitoVenta("yhj", "ppe", true);
    }

    /**
     * Obtener aplicacion dolar test.
     */
    @Test
    public void obtenerAplicacionDolarTest() {
        Cuenta cuenta = new Cuenta();
        assertEquals(CompraVentaStringUtil.TIPO_APLICACION_ACAD,
                compraVentaDolaresUtil.obtenerAplicacionDolar(cuenta, "C"));
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
        cuenta.setTipoCuentaSinUnificarDls("");
        // Venta
        assertEquals(CompraVentaStringUtil.TIPO_APLICACION_ACAD,
                compraVentaDolaresUtil.obtenerAplicacionDolar(cuenta, "V"));
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        assertEquals(CompraVentaStringUtil.TIPO_APLICACION_ACAD,
                compraVentaDolaresUtil.obtenerAplicacionDolar(cuenta, "V"));
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        assertEquals(CompraVentaStringUtil.TIPO_APLICACION_ACAD,
                compraVentaDolaresUtil.obtenerAplicacionDolar(cuenta, "V"));
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        assertEquals(CompraVentaStringUtil.TIPO_APLICACION_ACCD,
                compraVentaDolaresUtil.obtenerAplicacionDolar(cuenta, "V"));
        cuenta.setTipoCuentaSinUnificarDls("3");
        assertEquals(CompraVentaStringUtil.TIPO_APLICACION_ACCD,
                compraVentaDolaresUtil.obtenerAplicacionDolar(cuenta, "V"));
    }

}
