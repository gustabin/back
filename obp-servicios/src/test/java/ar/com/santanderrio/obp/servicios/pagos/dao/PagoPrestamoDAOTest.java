package ar.com.santanderrio.obp.servicios.pagos.dao;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcherRellamado;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.ImporteCuotaHipotecarioUVaException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.NoTieneFondosSuficientesException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoConAnterioridadException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoPrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * Test PagoPrestamoDAO.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PagoPrestamoDAOTest.PagoPrestamoDAOTestConfiguration.class })

public class PagoPrestamoDAOTest extends IatxBaseDAOTest {

    /** The pago prestamo DAO. */
    @Autowired
    @InjectMocks
    private PagoPrestamoDAOImpl pagoPrestamoDAO = new PagoPrestamoDAOImpl();

    /**
     * The Class PagoPrestamoDAOTestConfiguration.
     */
    @Configuration
    public static class PagoPrestamoDAOTestConfiguration {

        /**
         * Operacion cliente compra DAO.
         *
         * @return the pago prestamo DAO
         */
        @Bean
        public PagoPrestamoDAO operacionClienteCompraDAO() {
            return new PagoPrestamoDAOImpl();
        }

    }

    /**
     * Test OK pago prestamo.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws DAOException
     *             the DAO exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test
    public void pagarPrestamoOkTest()
            throws IatxException, PagoConAnterioridadException, NoTieneFondosSuficientesException, DAOException, ImporteCuotaHipotecarioUVaException {

        String pagpmocuoResponse = "200000000000P04HTML0009900010301DJAG39  ********00365444000000252016102812253300000000IBF38523000012221352PAGPMOCUO_1500000            01390639N   00        00 012221352201610281225300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0121800000000õ00000000000609600õ00000000000õ00056õ2015-08-05õ00000000000609600õARSõ020000000õ00000000000417500õ00000000000035900õ00000000000000000õ00000000000001300õ00000000000051600õ00000000000103300õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000001300õ00000000001736600õ00000000000000000õ00000000000000000õUGX86K2016-10-2812252700õ01õCONSUMIDOR FINAL                        õEL IVA DISCRIMINADO NO PUEDE COMPUTARSE COMO CREDITO FISCAL                     õ                                        õ021939108õ00000000002154100õ00000000000000000+õ000000000õ00000000000000000+õ00000000000000000+õ000000000õ    õ00000000000000000õ000000000õ00000000000000000õ00000000000000000õ000000000õ                    õ0001-01-01õ     õ    õ00000000000+õ0001-01-01õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherRellamado("PAGPMOCUO_", "150", "000"))))
                .thenReturn(pagpmocuoResponse);

        Cuenta cuenta = obtenerCuenta();

        Prestamo prestamo = obtenerPrestamo();

        ComprobantePrestamo comprobantePrestamo = pagoPrestamoDAO.pagar(prestamo, cuenta);

        Assert.assertNotNull(comprobantePrestamo);
        Assert.assertEquals(comprobantePrestamo.getCodigoMoneda(), "ARS");
        Assert.assertEquals(comprobantePrestamo.getCodIVA(), "01");
        Assert.assertEquals(comprobantePrestamo.getLeyenda1(),
                "EL IVA DISCRIMINADO NO PUEDE COMPUTARSE COMO CREDITO FISCAL                     ");

    }

    /**
     * Test Error Sin Fondos.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws DAOException
     *             the DAO exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test(expected = NoTieneFondosSuficientesException.class)
    public void pagarPrestamoErrorNoTieneFondosSuficientesTest()
            throws IatxException, PagoConAnterioridadException, DAOException, NoTieneFondosSuficientesException, ImporteCuotaHipotecarioUVaException {

        String pagpmocuoResponse = "200000000000P04HTML0009900010303DBHJ82  ********00850534000000312016040111064700000000IBF30363000011018316PAGPMOCUO_1400000            03317982N   00        00 011018316201604011107190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH�0034410000515�ABG�03�El saldo de la cuenta debito es insuficiente para realizar la transaccion.                                                                                                                                                                                    �DISPONIBLE INSU�BGE0515 - SE SOBREGIRARIA EN.               143,42  AUT.�";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherRellamado("PAGPMOCUO_", "150", "000"))))
                .thenReturn(pagpmocuoResponse);

        Cuenta cuenta = obtenerCuenta();

        Prestamo prestamo = obtenerPrestamo();

        pagoPrestamoDAO.pagar(prestamo, cuenta);

    }

    /**
     * Test Pago con anterioridad.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test(expected = PagoConAnterioridadException.class)
    public void pagarPrestamoErrorPagoConAnterioridadTest()
            throws IatxException, DAOException, NoTieneFondosSuficientesException, PagoConAnterioridadException, ImporteCuotaHipotecarioUVaException {

        String pagpmocuoResponse = "200000000000P04HTML0009900010303DBHJ82  ********00850534000000312016040111064700000000IBF30363000011018316PAGPMOCUO_1400000            03317982N   00        00 011018316201604011107190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH�0034410000100�ABG�03�El saldo de la cuenta debito es insuficiente para realizar la transaccion.                                                                                                                                                                                    �DISPONIBLE INSU�BGE0515 - SE SOBREGIRARIA EN.               143,42  AUT.�";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherRellamado("PAGPMOCUO_", "150", "000"))))
                .thenReturn(pagpmocuoResponse);

        Cuenta cuenta = obtenerCuenta();

        Prestamo prestamo = obtenerPrestamo();

        pagoPrestamoDAO.pagar(prestamo, cuenta);

    }

    /**
     * Pagar prestamo error DAO test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test(expected = DAOException.class)
    public void pagarPrestamoErrorDAOTest()
            throws IatxException, DAOException, NoTieneFondosSuficientesException, PagoConAnterioridadException, ImporteCuotaHipotecarioUVaException {

        String pagpmocuoResponse = "200000000000P04HTML0009900010303DBHJ82  ********00850534000000312016040111064700000000IBF30363000011018316PAGPMOCUO_1400000            03317982N   00        00 011018316201604011107190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherRellamado("PAGPMOCUO_", "150", "000"))))
                .thenReturn(pagpmocuoResponse);

        Cuenta cuenta = obtenerCuenta();

        Prestamo prestamo = obtenerPrestamo();
        pagoPrestamoDAO.pagar(prestamo, cuenta);

    }

    /**
     * Pagar prestamo error DAO 2 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws ImporteCuotaHipotecarioUVaException 
     */
    @Test(expected = DAOException.class)
    public void pagarPrestamoErrorDAO2Test()
            throws IatxException, DAOException, NoTieneFondosSuficientesException, PagoConAnterioridadException, ImporteCuotaHipotecarioUVaException {

        String pagpmocuoResponse = "200000000000P04HTML0009900010303DBHJ82  ********00850534000000312016040111064700000000IBF30363000011018316PAGPMOCUO_1400000            03317982N   00        00 011018316201604011107190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherRellamado("PAGPMOCUO_", "150", "000"))))
                .thenReturn(pagpmocuoResponse);

        Cuenta cuenta = obtenerCuenta();
        // cuenta invalida
        cuenta.setNroCuentaProducto("aa");

        Prestamo prestamo = obtenerPrestamo();
        pagoPrestamoDAO.pagar(prestamo, cuenta);

    }

    /**
     * Hardcode Cuenta.
     *
     * @return Cuenta
     */
    private Cuenta obtenerCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("8501392");
        cuenta.setNroSucursal("0033");
        cuenta.setTipoCuentaSinUnificar("07");
        cuenta.setCliente(obtenerCliente());
        return cuenta;
    }

    /**
     * Hardcode Prestamo.
     *
     * @return Prestamo
     */
    private Prestamo obtenerPrestamo() {
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setNroSucursal("033");
        cuentaPrestamo.setEstadoTarjetaCredito("03");
        cuentaPrestamo.setNroCuentaProducto("0000000000000000000000000011");
        Prestamo prestamo = new Prestamo();

        prestamo.setNumeroCuentaProducto("0000000000000000000000000011");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setCuenta(cuentaPrestamo);
        prestamo.setImporteTotalCuota(new BigDecimal(300));
        return prestamo;

    }

    /**
     * Hardcode Cliente.
     *
     * @return Cliente
     */
    private Cliente obtenerCliente() {
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        Cliente c1 = new Cliente();
        c1.setNombre("CONSTANCIO PERCY");
        c1.setApellido1("MILANDO");
        c1.setApellido2("M");
        c1.setSegmento(segmento);
        return c1;
    }

}
