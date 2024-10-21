/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.BotonPanicoCompraventaException;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.CotizacionDolaresDAOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ConsultaCotizacionEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class CotizacionDolaresDAOTest.
 *
 * @author pablo.gargaglione
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        CotizacionDolaresDAOTest.CotizacionDolaresDAOTestConfiguration.class })
@Ignore
public class CotizacionDolaresDAOTest extends IatxBaseDAOTest {

    /** The tipo cuenta pesos 01 envio. */
    // "ACAH"
    public final String TIPO_CUENTA_PESOS_01_ENVIO = "01";

    /** The operacion compra. */
    public final String OPERACION_COMPRA = "C";

    /** The cotizacion dolares DAO. */
    @Autowired
    @InjectMocks
    private CotizacionDolaresDAO cotizacionDolaresDAO;

    /** The compra venta dolares util. */
    @Mock
    private CompraVentaDolaresUtil compraVentaDolaresUtil;

    /**
     * The Class CotizacionDolaresDAOTestConfiguration.
     */
    @Configuration
    public static class CotizacionDolaresDAOTestConfiguration {

        /**
         * Cotizacion dolares DAO.
         *
         * @return the cotizacion dolares DAO
         */
        @Bean
        public CotizacionDolaresDAO cotizacionDolaresDAO() {
            return new CotizacionDolaresDAOImpl();
        }

        /**
         * Compra venta dolares util.
         *
         * @return the compra venta dolares util
         */
        @Bean
        public CompraVentaDolaresUtil compraVentaDolaresUtil() {
            return Mockito.spy(new CompraVentaDolaresUtil());
        }
    }

    /**
     * Metodo para testear comportamiento de obtenerCotizacion del DAO,
     * CotizacionDolaresDAO, "Caso: Feliz".
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     * @throws BotonPanicoCompraventaException 
     */
    @Test
    public void consultaCotizacionOk() throws IatxException, ServiceException, DAOException, BotonPanicoCompraventaException {
        Cliente cliente = obtenerCliente();
        Cuenta cuenta = obtenerCuenta();
        Mockito.when(compraVentaDolaresUtil.obtenerAplicacion(Matchers.any(Cuenta.class), Matchers.anyString()))
                .thenReturn("ACAD");
        Mockito.when(compraVentaDolaresUtil.obtenerCuentaDebitoOperante(Matchers.any(Cuenta.class)))
                .thenReturn("123612361236123");
        Mockito.when(compraVentaDolaresUtil.obtenerOpcionTomaCotizacion(Matchers.anyString())).thenReturn("N");
        Mockito.when(compraVentaDolaresUtil.obtenerTipoOperacion(Matchers.anyString())).thenReturn("N");
        Mockito.when(compraVentaDolaresUtil.obtenerSegmento(Matchers.any(Cuenta.class))).thenReturn("N");
        // Realiza la consulta de cotización de moneda extranjera
        String cnscotcnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054000000000IBF16216000000000000CNSCOTCN__1000000            20064145    00        00 000000000201606141305360000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005300000000õ00137600000õ000000000000000õ000000000000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSCOTCN__", "100")))).thenReturn(cnscotcnResponse);

        ConsultaCotizacionEntity consultaCotizacion = cotizacionDolaresDAO.obtenerCotizacion(cliente, cuenta,
                OPERACION_COMPRA, false);
        Assert.assertNotNull(consultaCotizacion);
        Assert.assertEquals(consultaCotizacion.getCotizacionSalida(), "00137600000");
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher("CNSCOTCN__", "100")));
    }

    /**
     * Metodo para testear comportamiento de obtenerCotizacion del DAO,
     * CotizacionDolaresDAO, "Caso: Sucursal fuera de horario de atención".
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     * @throws BotonPanicoCompraventaException 
     */
    @Test(expected = DAOException.class)
    public void consultaCotizacionFueraHorario() throws IatxException, ServiceException, DAOException, BotonPanicoCompraventaException {
        Cliente cliente = obtenerCliente();
        Cuenta cuenta = obtenerCuenta();
        Mockito.when(compraVentaDolaresUtil.obtenerAplicacion(Matchers.any(Cuenta.class), Matchers.anyString()))
                .thenReturn("ACAD");
        Mockito.when(compraVentaDolaresUtil.obtenerCuentaDebitoOperante(Matchers.any(Cuenta.class)))
                .thenReturn("123612361236123");
        Mockito.when(compraVentaDolaresUtil.obtenerOpcionTomaCotizacion(Matchers.anyString())).thenReturn("N");
        Mockito.when(compraVentaDolaresUtil.obtenerTipoOperacion(Matchers.anyString())).thenReturn("N");
        Mockito.when(compraVentaDolaresUtil.obtenerSegmento(Matchers.any(Cuenta.class))).thenReturn("N");
        String cnscotcnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054000000000IBF16216000000000000CNSCOTCN__1000000            20064145    00        00 000000000201606141305360000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005310000077õ00137600000õ000000000000000õ000000000000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSCOTCN__", "100")))).thenReturn(cnscotcnResponse);
        cotizacionDolaresDAO.obtenerCotizacion(cliente, cuenta, OPERACION_COMPRA, false);
    }

    /**
     * Metodo para testear comportamiento de obtenerCotizacion del DAO,
     * CotizacionDolaresDAO, "Caso: Sin Acceso a la InformacionException".
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     * @throws BotonPanicoCompraventaException 
     */
    @Test(expected = DAOException.class)
    public void consultaCotizacionSinAccesoALaInformacion() throws IatxException, ServiceException, DAOException, BotonPanicoCompraventaException {
        Cliente cliente = obtenerCliente();
        Cuenta cuenta = obtenerCuenta();
        Mockito.when(compraVentaDolaresUtil.obtenerAplicacion(Matchers.any(Cuenta.class), Matchers.anyString()))
                .thenReturn("ACAD");
        Mockito.when(compraVentaDolaresUtil.obtenerCuentaDebitoOperante(Matchers.any(Cuenta.class)))
                .thenReturn("123612361236123");
        Mockito.when(compraVentaDolaresUtil.obtenerOpcionTomaCotizacion(Matchers.anyString())).thenReturn("N");
        Mockito.when(compraVentaDolaresUtil.obtenerTipoOperacion(Matchers.anyString())).thenReturn("N");
        Mockito.when(compraVentaDolaresUtil.obtenerSegmento(Matchers.any(Cuenta.class))).thenReturn("N");
        String cnscotcnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054000000000IBF16216000000000000CNSCOTCN__1000000            20064145    00        00 000000000201606141305360000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005310000000õ00137600000õ000000000000000õ000000000000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSCOTCN__", "100")))).thenReturn(cnscotcnResponse);
        cotizacionDolaresDAO.obtenerCotizacion(cliente, cuenta, OPERACION_COMPRA, false);
    }

    /**
     * Obtener cliente.
     *
     * @return the cliente
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

    /**
     * Obtener cuenta.
     *
     * @return the cuenta
     */
    private Cuenta obtenerCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(TIPO_CUENTA_PESOS_01_ENVIO);
        cuenta.setNroCuentaProducto("0000000338501392");
        cuenta.setNroSucursal("0033");
        return cuenta;
    }

}
