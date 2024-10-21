/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.PagosPendientesBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosTarjetaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjetaBuilder;

/**
 * The Class PagosPendientesBOTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class PagosPendientesBOTest {

    /** The pagos pendientes BO. */
    @InjectMocks
    private PagosPendientesBOImpl pagosPendientesBO;

    /** The datos tarjeta DAO. */
    @Mock
    private DatosTarjetaDAO datosTarjetaDAO;

    /**
     * Dado un cliente con una tarjeta Amex, cuando se invoca al metodo
     * "getDatosInicialesPagoTarjetas", obtengo un lista de datos tarjeta OK.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaGetDatosInicialesPagoTarjetasObtengoListaDatosTarjetaOK()
            throws BusinessException, DAOException {
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(new DatosTarjetaBuilder(DatosTarjetaMock.obtenerDatoTarjeta(null, null, "", "03", "TI", null, null, "20", "02",
                        new BigDecimal(5250.00), new BigDecimal(4625.00), "00003777920020216960", new BigDecimal(30.00),
                        new BigDecimal(-11.99), new BigDecimal(-220.00), new BigDecimal(-11.99),
                        new BigDecimal(-220.00), new BigDecimal(-11.99), new BigDecimal(-220.00),
                        TipoCuentaTarjeta.TIPOCTA_AMEX, BigDecimal.valueOf(60), BigDecimal.valueOf(58), BigDecimal.valueOf(10000), 
                        BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000))));

        List<DatosTarjeta> datosTarjeta = pagosPendientesBO
                .getDatosInicialesPagoTarjetas(ClienteMock.completarInfoCliente());

        Assert.assertNotNull(datosTarjeta);
        Assert.assertEquals("", datosTarjeta.get(0).getAlias());
        Assert.assertEquals("03", datosTarjeta.get(0).getCicloTarjetaCredito());
        Assert.assertEquals("TI", datosTarjeta.get(0).getCodigoTitularidad());
        Assert.assertEquals("20", datosTarjeta.get(0).getEstadoTarjetaCredito());
        Assert.assertEquals("02", datosTarjeta.get(0).getFormaPagoTarjetaCredito());
        Assert.assertEquals(new BigDecimal(5250), datosTarjeta.get(0).getLimiteCompraPesosTC());
        Assert.assertEquals(new BigDecimal(4625), datosTarjeta.get(0).getLimiteFinanciacionTC());
        Assert.assertEquals("00003777910057203120", datosTarjeta.get(0).getNumeroTarjeta());
        Assert.assertEquals(new BigDecimal(30), datosTarjeta.get(0).getPagoMinimoPesosTC());
        Assert.assertEquals(new BigDecimal(-11.9900000000000002131628207280300557613372802734375),
                datosTarjeta.get(0).getSaldoActualDolaresTC());
        Assert.assertEquals(new BigDecimal(-220), datosTarjeta.get(0).getSaldoActualPesosTC());
        Assert.assertEquals(new BigDecimal(-11.9900000000000002131628207280300557613372802734375),
                datosTarjeta.get(0).getSaldoDolaresTC());
        Assert.assertEquals(new BigDecimal(-220), datosTarjeta.get(0).getSaldoPesosTC());
        Assert.assertEquals(new BigDecimal(-11.9900000000000002131628207280300557613372802734375),
                datosTarjeta.get(0).getSaldoUltimoCierreDolaresTC());
        Assert.assertEquals(TipoCuentaTarjeta.TIPOCTA_AMEX, datosTarjeta.get(0).getTipoCuentaTarjeta());
    }

    /**
     * Gets the datos iniciales pago tarjetas OK amex y visa titular test.
     *
     * @return the datos iniciales pago tarjetas OK amex Y visa titular test
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getDatosInicialesPagoTarjetasOKAmexYVisaTitularTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta c1 = new Cuenta();
        c1.setTipoCuentaEnum(TipoCuenta.AMEX);
        c1.setCodigoTitularidad("TI");
        c1.setTipoCuenta("42");
        Cuenta c2 = new Cuenta();
        c2.setTipoCuentaEnum(TipoCuenta.VISA);
        c2.setCodigoTitularidad("TI");
        c2.setTipoCuenta("07");
        cuentas.add(c1);
        cuentas.add(c2);
        cliente.setCuentas(cuentas);
        DatosTarjeta datTarjeta = DatosTarjetaMock.obtenerDatoTarjeta(null, null, "", "03", "TI", null, null, "20",
                "02", new BigDecimal(5250.00), new BigDecimal(4625.00), "00003777920020216960", new BigDecimal(30.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), new BigDecimal(-11.99), new BigDecimal(-220.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), TipoCuentaTarjeta.TIPOCTA_VISA, 
                BigDecimal.valueOf(60), BigDecimal.valueOf(58), BigDecimal.valueOf(10000), 
                BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));

        // When
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(new DatosTarjetaBuilder(datTarjeta));

        // Then
        List<DatosTarjeta> datosTarjeta = pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente);

        // Expected
        Assert.assertNotNull(datosTarjeta);
        Assert.assertEquals("TI", datosTarjeta.get(0).getCodigoTitularidad());
        Assert.assertEquals(TipoCuentaTarjeta.TIPOCTA_AMEX, datosTarjeta.get(0).getTipoCuentaTarjeta());
    }

    /**
     * Gets the datos iniciales pago tarjetas OK master titular test.
     *
     * @return the datos iniciales pago tarjetas OK master titular test
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getDatosInicialesPagoTarjetasOKMasterTitularTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta c1 = new Cuenta();
        c1.setTipoCuentaEnum(TipoCuenta.MASTERCARD);
        c1.setCodigoTitularidad("TI");
        c1.setTipoCuenta("06");
        cuentas.add(c1);
        cliente.setCuentas(cuentas);
        DatosTarjeta datTarjeta = DatosTarjetaMock.obtenerDatoTarjeta(null, null, "", "03", "TI", null, null, "20",
                "02", new BigDecimal(5250.00), new BigDecimal(4625.00), "00003777920020216960", new BigDecimal(30.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), new BigDecimal(-11.99), new BigDecimal(-220.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), TipoCuentaTarjeta.TIPOCTA_MASTER, 
                BigDecimal.valueOf(60), BigDecimal.valueOf(58), BigDecimal.valueOf(10000), 
                BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));

        // When
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(new DatosTarjetaBuilder(datTarjeta));

        // Then
        List<DatosTarjeta> datosTarjeta = pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente);

        // Expected
        Assert.assertNotNull(datosTarjeta);
        Assert.assertEquals("TI", datosTarjeta.get(0).getCodigoTitularidad());
        Assert.assertEquals(TipoCuentaTarjeta.TIPOCTA_MASTER, datosTarjeta.get(0).getTipoCuentaTarjeta());
    }

    /**
     * Gets the datos iniciales pago tarjetas OK cuenta cotitular test.
     *
     * @return the datos iniciales pago tarjetas OK cuenta cotitular test
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getDatosInicialesPagoTarjetasOKCuentaCotitularTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta c1 = new Cuenta();
        c1.setTipoCuentaEnum(TipoCuenta.AMEX);
        c1.setCodigoTitularidad("CT");
        c1.setTipoCuenta("42");
        cuentas.add(c1);
        cliente.setCuentas(cuentas);

        // Then
        List<DatosTarjeta> datosTarjeta = pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente);

        // Expected
        Assert.assertNotNull(datosTarjeta);
    }

    /**
     * Gets the datos iniciales pago tarjetas OK visa adicional test.
     *
     * @return the datos iniciales pago tarjetas OK visa adicional test
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getDatosInicialesPagoTarjetasOKVisaAdicionalTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta c1 = new Cuenta();
        c1.setTipoCuentaEnum(TipoCuenta.VISA);
        c1.setCodigoTitularidad("AD");
        c1.setTipoCuenta("07");
        cuentas.add(c1);
        cliente.setCuentas(cuentas);
        DatosTarjeta datTarjeta = DatosTarjetaMock.obtenerDatoTarjeta(null, null, "", "03", "AD", null, null, "20",
                "02", new BigDecimal(5250.00), new BigDecimal(4625.00), "00003777920020216960", new BigDecimal(30.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), new BigDecimal(-11.99), new BigDecimal(-220.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), TipoCuentaTarjeta.TIPOCTA_VISA, BigDecimal.valueOf(60), 
                BigDecimal.valueOf(58), BigDecimal.valueOf(10000), 
                BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));

        // When
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(new DatosTarjetaBuilder(datTarjeta));

        // Then
        List<DatosTarjeta> datosTarjeta = pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente);

        // Expected
        Assert.assertNotNull(datosTarjeta);
        Assert.assertEquals("AD", datosTarjeta.get(0).getCodigoTitularidad());
        Assert.assertEquals(TipoCuentaTarjeta.TIPOCTA_VISA, datosTarjeta.get(0).getTipoCuentaTarjeta());
    }

    /**
     * Gets the datos iniciales pago tarjetas OK amex adicional test.
     *
     * @return the datos iniciales pago tarjetas OK amex adicional test
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getDatosInicialesPagoTarjetasOKAmexAdicionalTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta c1 = new Cuenta();
        c1.setTipoCuentaEnum(TipoCuenta.AMEX);
        c1.setCodigoTitularidad("AD");
        c1.setTipoCuenta("42");
        cuentas.add(c1);
        cliente.setCuentas(cuentas);
        DatosTarjeta datTarjeta = DatosTarjetaMock.obtenerDatoTarjeta(null, null, "", "03", "AD", null, null, "20",
                "02", new BigDecimal(5250.00), new BigDecimal(4625.00), "00003777920020216960", new BigDecimal(30.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), new BigDecimal(-11.99), new BigDecimal(-220.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), TipoCuentaTarjeta.TIPOCTA_AMEX, BigDecimal.valueOf(60), 
                BigDecimal.valueOf(58), BigDecimal.valueOf(10000), 
                BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));

        // When
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(new DatosTarjetaBuilder(datTarjeta));

        // Then
        List<DatosTarjeta> datosTarjeta = pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente);

        // Expected
        Assert.assertNotNull(datosTarjeta);
        Assert.assertEquals("AD", datosTarjeta.get(0).getCodigoTitularidad());
        Assert.assertEquals(TipoCuentaTarjeta.TIPOCTA_AMEX, datosTarjeta.get(0).getTipoCuentaTarjeta());
    }

    /**
     * Gets the datos iniciales pago tarjetas OK master adicional test.
     *
     * @return the datos iniciales pago tarjetas OK master adicional test
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getDatosInicialesPagoTarjetasOKMasterAdicionalTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta c1 = new Cuenta();
        c1.setTipoCuentaEnum(TipoCuenta.MASTERCARD);
        c1.setCodigoTitularidad("AD");
        c1.setTipoCuenta("06");
        cuentas.add(c1);
        cliente.setCuentas(cuentas);
        DatosTarjeta datTarjeta = DatosTarjetaMock.obtenerDatoTarjeta(null, null, "", "03", "AD", null, null, "20",
                "02", new BigDecimal(5250.00), new BigDecimal(4625.00), "00003777920020216960", new BigDecimal(30.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), new BigDecimal(-11.99), new BigDecimal(-220.00),
                new BigDecimal(-11.99), new BigDecimal(-220.00), TipoCuentaTarjeta.TIPOCTA_MASTER, BigDecimal.valueOf(60), 
                BigDecimal.valueOf(58), BigDecimal.valueOf(10000), 
                BigDecimal.valueOf(10000), BigDecimal.valueOf(10000), BigDecimal.valueOf(10000));

        // When
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(new DatosTarjetaBuilder(datTarjeta));

        // Then
        List<DatosTarjeta> datosTarjeta = pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente);

        // Expected
        Assert.assertNotNull(datosTarjeta);
        Assert.assertEquals("AD", datosTarjeta.get(0).getCodigoTitularidad());
        Assert.assertEquals(TipoCuentaTarjeta.TIPOCTA_MASTER, datosTarjeta.get(0).getTipoCuentaTarjeta());
    }

    /**
     * Gets the datos iniciales pago tarjetas OK visa recargable test.
     *
     * @return the datos iniciales pago tarjetas OK visa recargable test
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void getDatosInicialesPagoTarjetasOKVisaRecargableTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta c1 = new Cuenta();
        c1.setTipoCuentaEnum(TipoCuenta.VISA);
        c1.setCodigoTitularidad("TI");
        c1.setTipoCuenta("07");
        c1.setClaseCuenta("T");
        cuentas.add(c1);
        cliente.setCuentas(cuentas);

        // Then
        List<DatosTarjeta> datosTarjeta = pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente);

        // Expected
        Assert.assertNotNull(datosTarjeta);
    }

    /**
     * Dado un cliente, cuando se invoca al metodo
     * "getDatosInicialesPagoTarjetas", obtengo business exception.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = BusinessException.class)
    public void dadoClienteCuandoInvocaGetDatosInicialesPagoTarjetasObtengoBusinessException()
            throws BusinessException, DAOException {
        DAOException daoException = new DAOException("Error al acceder a los datos de tarjeta desde el dao.");
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenThrow(daoException);

        List<DatosTarjeta> datosTarjeta = pagosPendientesBO
                .getDatosInicialesPagoTarjetas(ClienteMock.completarInfoCliente());

    }

    /**
     * Dado un cliente, cuando se invoca al metodo
     * "getDatosInicialesPagoTarjetas", obtengo una lista de datos tarjeta
     * vacia.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoClienteCuandoInvocaGetDatosInicialesPagoTarjetasObtengoListaDatosTarjetaVacia()
            throws BusinessException, DAOException {
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(null);

        List<DatosTarjeta> datosTarjeta = pagosPendientesBO
                .getDatosInicialesPagoTarjetas(ClienteMock.completarInfoCliente());

        Assert.assertNotNull(datosTarjeta);
    }
}
