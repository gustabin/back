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
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.SimulacionClienteCompraDAOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;

/**
 * The Class SimulacionClienteCompraDAOTest.
 *
 * @author pablo.gargaglione
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        SimulacionClienteCompraDAOTest.SimulacionClienteCompraDAOTestConfiguration.class })
@Ignore
public class SimulacionClienteCompraDAOTest extends IatxBaseDAOTest {

    /** The tipo cuenta pesos 01 envio. */
    // "ACAH"
    public final String TIPO_CUENTA_PESOS_01_ENVIO = "01";

    /** The operacion compra. */
    public final String OPERACION_COMPRA = "C";

    /** The simulacion cliente compra DAO. */
    @Autowired
    @InjectMocks
    private SimulacionClienteCompraDAO simulacionClienteCompraDAO;;

    /**
     * The Class SimulacionClienteCompraDAOTestConfiguration.
     */
    @Configuration
    public static class SimulacionClienteCompraDAOTestConfiguration {

        /**
         * Simulacion cliente compra DAO.
         *
         * @return the simulacion cliente compra DAO
         */
        @Bean
        public SimulacionClienteCompraDAO simulacionClienteCompraDAO() {
            return new SimulacionClienteCompraDAOImpl();
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
    public void simulacionClienteCompraOk() throws IatxException, ServiceException, DAOException {

        // Realiza simulación de venta (Banco Vende / Cliente Compra) de
        // dólares.
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041100000000õ00720100007003750162õARSõ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
        Assert.assertNotEquals(null, simulacionClienteCompraEntity);
        Assert.assertEquals(simulacionClienteCompraEntity.getNumiden(), "00030219572");
        Assert.assertEquals(simulacionClienteCompraEntity.getNomclie(), "FRANCO");
        Assert.assertEquals(simulacionClienteCompraEntity.getPpriape(), "VITA");
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")));
    }

    /**
     * test de comportamiento excepcion OperacionFueraHorarioSucursalException
     * del DAO, SimulacionClienteCompraDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionClienteCompraDAOException() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041122222222õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
    }

    /**
     * test de comportamiento excepcion OperacionFueraHorarioSucursalException
     * del DAO, SimulacionClienteCompraDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionClienteCompraSinAcceso() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110000077õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
    }

    /**
     * test de comportamiento excepcion CambioCotizacionException del DAO,
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
    public void simulacionCambioCotizacion() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110000006õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
    }

    /**
     * test de comportamiento excepcion
     * MontoExcedeElPermitidoException(10000004) del DAO,
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
    public void simulacionMontoExcedeElPermitido_10000004() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110000004õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
    }

    /**
     * test de comportamiento excepcion
     * MontoExcedeElPermitidoException(10000002) del DAO,
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
    public void simulacionMontoExcedeElPermitido_10000002() throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110000002õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
    }

    /**
     * test de comportamiento excepcion
     * CuentaDolaresPesosInhabilitadaException(10002001) del DAO,
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
    public void simulacionCuentaDolaresPesosInhabilitada_10002001()
            throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110002001õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);

    }

    /**
     * test de comportamiento excepcion
     * CuentaDolaresPesosInhabilitadaException(10000008) del DAO,
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
    public void simulacionCuentaDolaresPesosInhabilitada_10000008()
            throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110000008õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
    }

    /**
     * test de comportamiento excepcion
     * CuentaDolaresPesosInhabilitadaException(10002065) del DAO,
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
    public void simulacionCuentaDolaresPesosInhabilitada_10002065()
            throws IatxException, ServiceException, DAOException {
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110002065õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
    }

    /**
     * test de comportamiento excepcion SaldoInsuficienteException(10002122) del
     * DAO, SimulacionClienteCompraDAO.
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
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110002122õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
    }

    /**
     * test de comportamiento excepcion SaldoInsuficienteException(10000515) del
     * DAO, SimulacionClienteCompraDAO.
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
        String simcpvtacnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413050900000000IBF47492000013006787SIMCPVTACN1100000            20064145    00        00 013006787201606141305060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041110000515õ00720100007003750162õ000õ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000025001+õ000000000000000+õ         õ000000000000000+õ000000000025001+õ         õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ00000000õ        õ000000000001405+õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("SIMCPVTACN", "110")))).thenReturn(simcpvtacnResponse);

        Cliente cliente = obtenerCliente();
        SimulacionDatosEntrada simulacionCompraVentaDolarData = obtenerSimulacionCompraVentaDolarData();
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = simulacionClienteCompraDAO
                .obtenerSimulacionClienteCompra(cliente, simulacionCompraVentaDolarData, null);
        System.out.println(simulacionClienteCompraEntity);
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
