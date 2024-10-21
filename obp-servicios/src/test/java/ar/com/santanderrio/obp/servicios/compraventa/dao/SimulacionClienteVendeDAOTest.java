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
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.SimulacionClienteVendeDAOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteVendeEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;

/**
 * The Class SimulacionClienteVendeDAOTest.
 *
 * @author pablo.gargaglione
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        SimulacionClienteVendeDAOTest.SimulacionClienteVendeDAOTestConfiguration.class })
@Ignore
public class SimulacionClienteVendeDAOTest extends IatxBaseDAOTest {

    /** The tipo cuenta pesos 01 envio. */
    // "ACAH"
    public final String TIPO_CUENTA_PESOS_01_ENVIO = "01";

    /** The operacion compra. */
    public final String OPERACION_COMPRA = "C";

    /** The simulacion cliente vende DAO. */
    @Autowired
    @InjectMocks
    private SimulacionClienteVendeDAO simulacionClienteVendeDAO;;

    /**
     * The Class SimulacionClienteVendeDAOTestConfiguration.
     */
    @Configuration
    public static class SimulacionClienteVendeDAOTestConfiguration {

        /**
         * Simulacion cliente vende DAO.
         *
         * @return the simulacion cliente vende DAO
         */
        @Bean
        public SimulacionClienteVendeDAO simulacionClienteVendeDAO() {
            return new SimulacionClienteVendeDAOImpl();
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
     * test de comportamiento de obtenerSimulacionClienteCompra del DAO,
     * SimulacionClienteCompraDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionClienteVendeOk() throws IatxException, ServiceException, DAOException {

        // Realiza simulación de compra (Banco Compra / Cliente Vende) de
        // dólares.
        String simdolcnlsResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006900000000õ00137600000õ000000000001376õ000000000000100õ000000000000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simdolcnlsResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        Assert.assertNotEquals(null, simulacionClienteVendeEntity);
        System.out.println(simulacionClienteVendeEntity);
        Assert.assertNotEquals(null, simulacionClienteVendeEntity);
        Assert.assertEquals(simulacionClienteVendeEntity.getImporteCotizacion(), "00137600000");
        Assert.assertEquals(simulacionClienteVendeEntity.getImporteCredito(), "000000000001376");
        Assert.assertEquals(simulacionClienteVendeEntity.getImporteDebito(), "000000000000100");
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")));
    }

    /**
     * test de comportamiento excepcion DAOException del DAO,
     * SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionClienteVendeDAOException() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006922222222õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion OperacionFueraHorarioSucursalException
     * del DAO, SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionClienteVendeSinAcceso() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910000077õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion CambioCotizacionException del DAO,
     * SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionCambioCotizacion() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910000006õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion
     * MontoExcedeElPermitidoException(10000004) del DAO,
     * SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionMontoExcedeElPermitido_10000004() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910000004õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion
     * MontoExcedeElPermitidoException(10000002) del DAO,
     * SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionMontoExcedeElPermitido_10000002() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910000002õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion
     * CuentaDolaresPesosInhabilitadaException(10002001) del DAO,
     * SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionCuentaDolaresPesosInhabilitada_10002001()
            throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910002001õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion
     * CuentaDolaresPesosInhabilitadaException(10000008) del DAO,
     * SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionCuentaDolaresPesosInhabilitada_10000008()
            throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910000008õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion
     * CuentaDolaresPesosInhabilitadaException(10002065) del DAO,
     * SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionCuentaDolaresPesosInhabilitada_10002065()
            throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910002065õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion SaldoInsuficienteException(10002122) del
     * DAO, SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionSaldoInsuficiente_10002122() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910002122õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
    }

    /**
     * test de comportamiento excepcion SaldoInsuficienteException(10000515) del
     * DAO, SimulacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionSaldoInsuficiente_10000515() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054500000000IBF17019000013011164SIMDOLCNLS1000000            20064145    00        00 013011164201606141305410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006910000515õ123õ1õmensajeõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMDOLCNLS", "100")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = simulacionClienteVendeDAO
                .obtenerSimulacionClienteVende(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteVendeEntity);
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
     * Obtener simulacion compra venta dolar data.
     *
     * @return the simulacion datos entrada
     */
    private SimulacionDatosEntrada obtenerSimulacionCompraVentaDolarData() {
        SimulacionDatosEntrada simulacionDatosEntrada = new SimulacionDatosEntrada();
        simulacionDatosEntrada.setAplicacionPesos("ACAH");
        simulacionDatosEntrada.setSucursalCtaPesos("461");
        simulacionDatosEntrada.setNumeroCtaPesos("000000000000");
        simulacionDatosEntrada.setAplicacionDolar("ACAH");
        simulacionDatosEntrada.setSucursalCtaDolar("461");
        simulacionDatosEntrada.setNuemroCtaDolar("000000000000");
        simulacionDatosEntrada.setIndTuAtesora("t");
        simulacionDatosEntrada.setFechaValor("2016-08-04");
        simulacionDatosEntrada.setConcepDebito("ABCD");
        simulacionDatosEntrada.setConcepCred("ABCD");
        simulacionDatosEntrada.setCodigoDebi("ABCD");
        simulacionDatosEntrada.setCodigoCre("ABCD");
        simulacionDatosEntrada.setImporteCred("2000");
        simulacionDatosEntrada.setImporteDeb("300");
        simulacionDatosEntrada.setImporteCoti("500");
        simulacionDatosEntrada.setIndicCompraVta("C");
        simulacionDatosEntrada.setAutorizaAfip("ABCDEFG");
        simulacionDatosEntrada.setTipoCambio("U$S");
        simulacionDatosEntrada.setNumBoleCvta("C");
        simulacionDatosEntrada.setCodTributa("S");
        simulacionDatosEntrada.setFecIngrPais("00000000");
        simulacionDatosEntrada.setNomApell("Razon Social");
        simulacionDatosEntrada.setPaisEmisDoc("000");
        simulacionDatosEntrada.setPaisNac("000");
        simulacionDatosEntrada.setCodigoConcepto("12345");
        simulacionDatosEntrada.setTpoDocBcra("L");
        simulacionDatosEntrada.setNroDocBcra("24233456");
        simulacionDatosEntrada.setTipRefAfip("ABC");
        return simulacionDatosEntrada;
    }

}
