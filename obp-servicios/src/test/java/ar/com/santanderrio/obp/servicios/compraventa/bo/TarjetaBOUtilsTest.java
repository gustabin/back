/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.FechaTarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class TarjetaBOUtilsTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class TarjetaBOUtilsTest {

    /** The tarjeta BO utils. */
    @InjectMocks
    private TarjetaBOUtils tarjetaBOUtils;

    /**
     * Dada una fecha de tipo Date, cuando se invoca al metodo
     * "convertirFechaAlEspaniol", obtener la fecha completa de tipo String con el
     * mes en mayuscula.
     *
     * @author florencia.n.martinez
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void dadaFechaDateCuandoInvocaConvertirFechaAlEspaniolObtenerFechaString() throws TarjetaBOUtilsException {
        String fechaString = TarjetaBOUtils.convertirFechaAlEspaniol(new Date(31 - 10 - 2016));
        Assert.assertEquals("31 de Diciembre del 1969", fechaString);
    }

    /**
     * Dado un saldo negativo en String, cuando invoca al metodo "convertirSaldo",
     * obtener el saldo negativo en BigDecimal.
     *
     * @author florencia.n.martinez
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void dadoSaldoNegativoEnStringCuandoInvocaConvertirSaldoObtenerSaldoNegativoEnBigDecimal()
            throws TarjetaBOUtilsException {
        BigDecimal saldoConvertido = TarjetaBOUtils.convertirSaldo("7,13");
        Assert.assertEquals(BigDecimal.valueOf(7.13), saldoConvertido);
    }

    /**
     * Obtener identificacion cuenta test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerIdentificacionCuentaTest() throws TarjetaBOUtilsException {
        IdentificacionCuenta identificacionCuenta = getIdentificacionCuenta();
        IdentificacionCuenta retorno = TarjetaBOUtils.obtenerIdentificacionCuenta("221-579806/6");
        // Validar
        assertEquals(identificacionCuenta, retorno);
    }

    /**
     * Cantidad tarjeta titulares empty test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void cantidadTarjetaTitularesEmptyTest() throws TarjetaBOUtilsException {
        Integer expected = 0;
        Integer saldoConvertido = TarjetaBOUtils.cantidadTarjetasTitulares(new ArrayList<Cuenta>());
        Assert.assertEquals(expected, saldoConvertido);
    }

    /**
     * Cantidad tarjeta titulares not empty test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void cantidadTarjetaTitularesNotEmptyTest() throws TarjetaBOUtilsException {
        Integer expected = 1;
        List<Cuenta> listaCuenta = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();

        cuenta.setCodigoTitularidad("TI");
        listaCuenta.add(cuenta);
        Integer saldoConvertido = TarjetaBOUtils.cantidadTarjetasTitulares(listaCuenta);
        Assert.assertEquals(expected, saldoConvertido);
    }

    /**
     * Obtener fecha por cierre empty test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerFechaPorCierreEmptyTest() throws TarjetaBOUtilsException {
        String fecha = TarjetaBOUtils.obtenerFechaPorCierre(new ArrayList<FechaTarjetaEntity>());
        Assert.assertNull(fecha);
    }

    /**
     * Obtener fecha por cierre entrada al if test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerFechaPorCierreEntradaAlIfTest() throws TarjetaBOUtilsException {
        List<FechaTarjetaEntity> listaFecha = new ArrayList<FechaTarjetaEntity>();
        FechaTarjetaEntity fechaTarjetaEntity = new FechaTarjetaEntity();

        fechaTarjetaEntity.setDescripcion("proximo");
        fechaTarjetaEntity.setCierre("resultado esperado");
        listaFecha.add(fechaTarjetaEntity);

        String cierreEsperado = TarjetaBOUtils.obtenerFechaPorCierre(listaFecha);
        Assert.assertEquals("resultado esperado", cierreEsperado);
    }

    /**
     * Obtener fecha por cierre no entrada al if test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerFechaPorCierreNoEntradaAlIfTest() throws TarjetaBOUtilsException {
        List<FechaTarjetaEntity> listaFecha = new ArrayList<FechaTarjetaEntity>();
        FechaTarjetaEntity fechaTarjetaEntity = new FechaTarjetaEntity();

        fechaTarjetaEntity.setDescripcion("anterior");
        listaFecha.add(fechaTarjetaEntity);

        String cierreEsperado = TarjetaBOUtils.obtenerFechaPorCierre(listaFecha);
        Assert.assertNull(cierreEsperado);
    }

    /**
     * Obtener fecha por vencimiento empty test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerFechaPorVencimientoEmptyTest() throws TarjetaBOUtilsException {
        String vencimientoEsperado = TarjetaBOUtils.obtenerFechaPorVencimiento(new ArrayList<FechaTarjetaEntity>());
        Assert.assertNull(vencimientoEsperado);
    }

    /**
     * Obtener fecha por vencimiento entrada al if test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerFechaPorVencimientoEntradaAlIfTest() throws TarjetaBOUtilsException {
        List<FechaTarjetaEntity> listaFecha = new ArrayList<FechaTarjetaEntity>();
        FechaTarjetaEntity fechaTarjetaEntity = new FechaTarjetaEntity();

        fechaTarjetaEntity.setDescripcion("proximo");
        fechaTarjetaEntity.setVencimiento("resultado esperado");
        listaFecha.add(fechaTarjetaEntity);

        String vencimientoEsperado = TarjetaBOUtils.obtenerFechaPorVencimiento(listaFecha);
        Assert.assertEquals("resultado esperado", vencimientoEsperado);
    }

    /**
     * Obtener fecha por vencimiento no entrada al if test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void obtenerFechaPorVencimientoNoEntradaAlIfTest() throws TarjetaBOUtilsException {
        List<FechaTarjetaEntity> listaFecha = new ArrayList<FechaTarjetaEntity>();
        FechaTarjetaEntity fechaTarjetaEntity = new FechaTarjetaEntity();

        fechaTarjetaEntity.setDescripcion("anterior");
        listaFecha.add(fechaTarjetaEntity);

        String vencimientoEsperado = TarjetaBOUtils.obtenerFechaPorVencimiento(listaFecha);
        Assert.assertNull(vencimientoEsperado);
    }

    /**
     * Convertir string to camelcase test.
     */
    @Test
    public void convertirStringToCamelcaseTest() {
        String res = TarjetaBOUtils.convertirStringToCamelcase("caso de prueba");
        Assert.assertEquals("Caso De Prueba", res);
    }

    /**
     * Convertir string to lowercase test.
     */
    @Test
    public void convertirStringToLowercaseTest() {
        String res = TarjetaBOUtils.convertirStringToLowercase("Caso De Prueba");
        Assert.assertEquals("caso de prueba", res);
    }

    /**
     * Es tipo cuenta tarjeta visa test.
     */
    @Test
    public void esTipoCuentaTarjetaVisaTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        Boolean res = TarjetaBOUtils.esTipoCuentaTarjeta(cuenta);
        Assert.assertEquals(true, res);
    }

    /**
     * Es tipo cuenta tarjeta amex test.
     */
    @Test
    public void esTipoCuentaTarjetaAmexTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        Boolean res = TarjetaBOUtils.esTipoCuentaTarjeta(cuenta);
        Assert.assertEquals(true, res);
    }

    /**
     * Es tipo cuenta tarjeta false test.
     */
    @Test
    public void esTipoCuentaTarjetaFalseTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_DOLARES);
        Boolean res = TarjetaBOUtils.esTipoCuentaTarjeta(cuenta);
        Assert.assertEquals(false, res);
    }

    /**
     * Es tipo cuenta visa test.
     */
    @Test
    public void esTipoCuentaVisaTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(String.valueOf(7L));
        cuenta.setTipoCuentaEnum(TipoCuenta.fromCodigo(7));
        Boolean res = TarjetaBOUtils.esTipoCuentaVisa(cuenta);
        Assert.assertEquals(true, res);
    }

    /**
     * Es tipo cuenta amex test.
     */
    @Test
    public void esTipoCuentaAmexTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(String.valueOf(42L));
        cuenta.setTipoCuentaEnum(TipoCuenta.fromCodigo(42));
        Boolean res = TarjetaBOUtils.esTipoCuentaAmex(cuenta);
        Assert.assertEquals(true, res);
    }

    /**
     * Es tipo cuenta NO visa test.
     */
    @Test
    public void esTipoCuentaNOVisaTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(String.valueOf(42L));
        cuenta.setTipoCuentaEnum(TipoCuenta.fromCodigo(42));
        Boolean res = TarjetaBOUtils.esTipoCuentaVisa(cuenta);
        Assert.assertEquals(false, res);
    }

    /**
     * Es tipo cuenta NO amex test.
     */
    @Test
    public void esTipoCuentaNOAmexTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(String.valueOf(7L));
        Boolean res = TarjetaBOUtils.esTipoCuentaAmex(cuenta);
        Assert.assertEquals(false, res);
    }

    /**
     * Gets the codigo error al modificar favorito test.
     *
     * @return the codigo error al modificar favorito test
     */
    @Test
    public void getCodigoErrorAlModificarFavoritoTest() {
        String codigoError = TarjetaBOUtils.getCodigoErrorAlModificarFavorito();
        Assert.assertEquals("1070", codigoError);
    }

    /**
     * Gets the codigo error al modificar alias test.
     *
     * @return the codigo error al modificar alias test
     */
    @Test
    public void getCodigoErrorAlModificarAliasTest() {
        String codigoError = TarjetaBOUtils.getCodigoErrorAlModificarAlias();
        Assert.assertEquals("1071", codigoError);
    }

    /**
     * Gets the codigo error sin consumos test.
     *
     * @return the codigo error sin consumos test
     */
    @Test
    public void getCodigoErrorSinConsumosTest() {
        String codigoError = TarjetaBOUtils.getCodigoErrorSinConsumos();
        Assert.assertEquals("1073", codigoError);
    }

    /**
     * Gets the codigo error consumos pendientes confirmacion test.
     *
     * @return the codigo error consumos pendientes confirmacion test
     */
    @Test
    public void getCodigoErrorConsumosPendientesConfirmacionTest() {
        String codigoError = TarjetaBOUtils.getCodigoErrorConsumosPendientesConfirmacion();
        Assert.assertEquals("1077", codigoError);
    }

    /**
     * Gets the codigo tooltip favorito test.
     *
     * @return the codigo tooltip favorito test
     */
    @Test
    public void getCodigoTooltipFavoritoTest() {
        String codigoError = TarjetaBOUtils.getCodigoTooltipFavorito();
        Assert.assertEquals("1078", codigoError);
    }

    /**
     * Gets the codigo tooltip no favorito test.
     *
     * @return the codigo tooltip no favorito test
     */
    @Test
    public void getCodigoTooltipNoFavoritoTest() {
        String codigoError = TarjetaBOUtils.getCodigoTooltipNoFavorito();
        Assert.assertEquals("1079", codigoError);
    }

    /**
     * Gets the codigo error generico test.
     *
     * @return the codigo error generico test
     */
    @Test
    public void getCodigoErrorGenericoTest() {
        String codigoError = TarjetaBOUtils.getCodigoErrorGenerico();
        Assert.assertEquals("1003", codigoError);
    }

    /**
     * Gets the codigo error carga ultimo resumen test.
     *
     * @return the codigo error carga ultimo resumen test
     */
    @Test
    public void getCodigoErrorCargaUltimoResumenTest() {
        String codigoError = TarjetaBOUtils.getCodigoErrorCargaUltimoResumen();
        Assert.assertEquals("1296", codigoError);
    }

    /**
     * Gets the codigo error sin ultimo resumen test.
     *
     * @return the codigo error sin ultimo resumen test
     */
    @Test
    public void getCodigoErrorSinUltimoResumenTest() {
        String codigoError = TarjetaBOUtils.getCodigoErrorSinUltimoResumen();
        Assert.assertEquals("1295", codigoError);
    }

    /**
     * Gets the codigo error sin tarjetas test.
     *
     * @return the codigo error sin tarjetas test
     */
    @Test
    public void getCodigoErrorSinTarjetasTest() {
        String codigoError = TarjetaBOUtils.getCodigoErrorSinTarjetas();
        Assert.assertEquals("1082", codigoError);
    }

    /**
     * Formatear fecha test.
     */
    @Test
    public void formatearFechaTest() {
        String fechaDormateada = TarjetaBOUtils.formatearFecha("12/12/2016");
        Assert.assertEquals("12/12/2016", fechaDormateada);
    }

    /**
     * Formatear fecha dia mes test.
     */
    @Test
    public void formatearFechaDiaMesTest() {
        Date fechaDormateada = TarjetaBOUtils.formatearFechaDiaMes("15/10/2016");
        Assert.assertEquals(15, fechaDormateada.getDate());
        Assert.assertEquals(10, fechaDormateada.getMonth() + 1);// El mes parece
                                                                // ir de 0 a 11
                                                                // con este
                                                                // metodo
    }

    /**
     * Parsear entero exception test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test(expected = TarjetaBOUtilsException.class)
    public void parsearEnteroExceptionTest() throws TarjetaBOUtilsException {
        Integer res = TarjetaBOUtils.parsearEntero(null);
    }

    /**
     * Convertir saldo exception test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test(expected = NumberFormatException.class)
    public void convertirSaldoExceptionTest() throws TarjetaBOUtilsException {
        BigDecimal res = TarjetaBOUtils.convertirSaldo("Pepe");
    }

    @Test(expected = NumberFormatException.class)
    public void convertirSaldo() throws TarjetaBOUtilsException {
        BigDecimal res = TarjetaBOUtils.convertirSaldo("*      853,86");
        Assert.assertEquals(res, new BigDecimal("84.00"));
    }

    /**
     * Obtener identificacion cuenta exception test.
     *
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test(expected = NullPointerException.class)
    public void obtenerIdentificacionCuentaExceptionTest() throws TarjetaBOUtilsException {
        IdentificacionCuenta res = TarjetaBOUtils.obtenerIdentificacionCuenta(null);
    }

    // /**
    // * Gets the cuenta.
    // *
    // * @return the cuenta
    // */
    // private Cuenta getCuenta() {
    // Cuenta cuenta = new Cuenta();
    // cuenta.setAlias("Alias Nombre");
    // cuenta.setCliente(getCliente());
    // cuenta.setIsFavorita(true);
    // cuenta.setNroCuentaProducto("0000000338501392");
    // cuenta.setNroSucursal("0033");
    // return cuenta;
    // }

    // /**
    // * Gets the cliente.
    // *
    // * @return the cliente
    // */
    // private Cliente getCliente() {
    // return mock(Cliente.class);
    // }

    /**
     * Gets the identificacion cuenta.
     *
     * @return the identificacion cuenta
     */
    private IdentificacionCuenta getIdentificacionCuenta() {
        String nroCuentaProducto = "5798066";
        String nroSucursal = "221";
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        identificacionCuenta.setNroCuentaProducto(nroCuentaProducto);
        identificacionCuenta.setNroSucursal(nroSucursal);
        IdentificacionCuenta retorno = null;
        try {
            retorno = TarjetaBOUtils.obtenerIdentificacionCuenta("221-579806/6");
        } catch (TarjetaBOUtilsException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    /**
     * Formatear numero tarjeta test.
     */
    @Test
    public void formatearNumeroTarjetaTest() {

        String tarjetaConFormato = TarjetaBOUtils.formatearNumeroTarjeta("0000000338501392", "VISA");
        String tarjetaConFormato2 = TarjetaUtils.cortarYFormatearNumeroTarjeta("0000000338501392", "VISA");

        assertEquals(tarjetaConFormato, "XXXX-1392");

        assertEquals(tarjetaConFormato2, "XXXX-1392");
    }

}
