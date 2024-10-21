package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.AliasCuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteAltaCBUDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AliasCuentaManagerTest.
 *
 * @author dante.omar.olmedo
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        AliasCuentaManagerTest.AliasCuentaManagerTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "TRJCOORD.OPERAINDISTINTO.ALTAALIAS=1", "TRJCOORD.OPERAINDISTINTO.MODIFICAALIAS=1",
        "TRJCOORD.OPERAINDISTINTO.BAJAALIAS=1", "TRJCOORD.OPERAINDISTINTO.REASIGNAALIAS=1",
        "TRJCOORD.OPERAINDISTINTO.TRANSFERENCIAS_ND=1", "TRANSFERENCIA.TIT.AYUDA.AUTO=11" })

@ActiveProfiles(value = Profiles.TEST)
public class AliasCuentaManagerTest {

    /**
     * The Class AliasCuentaManagerTestConfiguration.
     */
    @ComponentScan(basePackageClasses = AliasCuentaManagerTest.class, useDefaultFilters = false, includeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AliasCuentaManagerTest.class) })
    public static class AliasCuentaManagerTestConfiguration {

        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        /**
         * Sesion parametros.
         *
         * @return the sesion parametros
         */
        @Bean
        public static SesionParametros sesionParametros() {
            return Mockito.mock(SesionParametros.class);
        }

        /**
         * Sesion cliente.
         *
         * @return the sesion cliente
         */
        @Bean
        public static SesionCliente sesionCliente() {
            return Mockito.mock(SesionCliente.class);
        }

        /**
         * Alias cuenta BO.
         *
         * @return the alias cuenta BO
         */
        @Bean
        public static AliasCuentaBO aliasCuentaBO() {
            return Mockito.mock(AliasCuentaBO.class);
        }

        /**
         * Estadistica manager.
         *
         * @return the estadistica manager
         */
        @Bean
        public static EstadisticaManager estadisticaManager() {
            return Mockito.mock(EstadisticaManager.class);
        }

        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         */
        @Bean
        public static RespuestaFactory respuestaFactory() {
            return new RespuestaFactory();
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean
        public static MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

        /**
         * Autentificacion manager.
         *
         * @return the autentificacion manager
         */
        @Bean
        public static AutentificacionManager autentificacionManager() {
            return Mockito.mock(AutentificacionManager.class);
        }

        /**
         * Alias cuenta manager.
         *
         * @return the alias cuenta manager
         */
        @Bean
        public static AliasCuentaManager aliasCuentaManager() {
            return new AliasCuentaManagerImpl();
        }
    }

    /** The valor desafio para alta alias. */
    @Value("${TRJCOORD.OPERAINDISTINTO.ALTAALIAS}")
    private String valorDesafioAltaAlias;

    /** The valor desafio para alta alias. */
    @Value("${TRJCOORD.OPERAINDISTINTO.MODIFICAALIAS}")
    private String valorDesafioModifiacionAlias;

    /** The valor desafio para baja alias. */
    @Value("${TRJCOORD.OPERAINDISTINTO.BAJAALIAS}")
    private String valorDesafioBajaAlias;

    /** The valor desafio para baja alias. */
    @Value("${TRJCOORD.OPERAINDISTINTO.REASIGNAALIAS}")
    private String valorDesafioReasignacionAlias;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /** The alias cuenta manager. */
    @Autowired
    @InjectMocks
    private AliasCuentaManager aliasCuentaManager;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The alias cuenta BO. */
    @Mock
    private AliasCuentaBO aliasCuentaBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The autentificacion manager. */
    @Autowired
    private AutentificacionManager autentificacionManager;

    /**
     * Obtener alias CBU ok test.
     */
    @Test
    public void obtenerAliasCBUOkTest() {
        Respuesta<DetalleCBUView> respuestaBo = new Respuesta<DetalleCBUView>();
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.OK);
        DetalleCBUView detalle = new DetalleCBUView();
        detalle.setAliasCbu("pepe");
        respuestaBo.setRespuesta(detalle);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());
        Mockito.when(aliasCuentaBO.obtenerAliasCBU(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString())).thenReturn(respuestaBo);
        Respuesta<DetalleCBUView> res = aliasCuentaManager.obtenerAliasCBU("01231231231",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals("pepe", res.getRespuesta().getAliasCbu());
    }

    /**
     * Obtener registro sesion.
     *
     * @return the registro sesion
     */
    private RegistroSesion obtenerRegistroSesion() {
        RegistroSesion rs = new RegistroSesion();
        rs.setIp("180.166.12.93");
        return rs;
    }

    /**
     * Obtener alias CBU error test.
     */
    @Test
    public void obtenerAliasCBUErrorTest() {
        Respuesta<DetalleCBUView> respuestaBo = new Respuesta<DetalleCBUView>();
        respuestaBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Mockito.when(aliasCuentaBO.obtenerAliasCBU(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString())).thenReturn(respuestaBo);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(new Respuesta<AutentificacionDTO>());
        Respuesta<DetalleCBUView> res = aliasCuentaManager.obtenerAliasCBU("01231231231",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertNull(res.getRespuesta());
    }

    /**
     * Continuar alta alias CBU ok test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void continuarAltaAliasCBUOkTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);
        Respuesta<DetalleCBUView> resBO = new Respuesta<DetalleCBUView>();
        resBO.setEstadoRespuesta(EstadoRespuesta.OK);
        DetalleCBUView detalle = new DetalleCBUView();
        detalle.setCbu("12345678910");
        detalle.setNumeroCuenta("272727827");
        detalle.setNombreCliente("Agustina Fernandez");
        detalle.setCuit("20-35669878-4");
        detalle.setNombreBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        detalle.setTipoCuenta("Cuenta unica");
        detalle.setNumeroSucursal("424-Las Palmas");
        detalle.setNumeroCuenta("424-002456/7");
        resBO.setRespuesta(detalle);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(aliasCuentaBO.obtenerDatosCliente(Matchers.anyString(), Matchers.anyString())).thenReturn(resBO);

        Respuesta<DetalleCBUView> res = aliasCuentaManager.continuarAliasCBU(detalle);

        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Continuar alias CBU error test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test

    public void continuarAliasCBUErrorTest() throws DAOException {
        Respuesta<DetalleCBUView> res = null;
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);
        Respuesta<DetalleCBUView> resBO = new Respuesta<DetalleCBUView>();
        DetalleCBUView detalle = new DetalleCBUView();
        detalle.setCbu("12345678910");
        detalle.setNumeroCuenta("272727827");
        resBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTag("errorGenericoAliasCBU");
        itemMensajeRespuesta.setTipoError("ERROR_GENERICO_ALIAS_CBU");
        itemMensajeRespuesta.setMensaje(
                "<p>Ocurrió un error en nuestros servicios.</p><p> Por favor volvé a ingresar en unos minutos.</p>");
        resBO.add(itemMensajeRespuesta);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getValidacionHash())
                .thenReturn(HashUtils.obtenerHash(crearMapaDeLaVista(detalle), HashUtils.ALGORITMO_MD5));
        Mockito.when(aliasCuentaBO.obtenerDatosCliente(Matchers.anyString(), Matchers.anyString())).thenReturn(resBO);
        res = aliasCuentaManager.continuarAliasCBU(detalle);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Grabar estadistica acceso ayuda test.
     */
    @Test
    public void grabarEstadisticaAccesoAyudaTest() {
        DetalleCBUView detalle = new DetalleCBUView();
        detalle.setNumeroCuenta("272727827");
        aliasCuentaManager.grabadoEstadisticaAyuda(detalle);
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.ACCESO_A_AYUDA_ALIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK,detalle.getNumeroCuenta());
    }

    /**
     * Grabar estadistica comprobante alta alias CBU test.
     */
    @Test
    public void grabarEstadisticaComprobanteAltaAliasCBUTest() {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cliente.getCuentas().add(cuenta);

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        aliasCuentaManager.grabarEstadisticaComprobanteAltaAliasCBU("012-123456/7");
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.COMPROBANTE_ALIAS_CBU, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, "012-123456/7");
    }

    /**
     * Grabar estadistica comprobante editar alias CBU test.
     */
    @Test
    public void grabarEstadisticaComprobanteEditarAliasCBUTest() {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cliente.getCuentas().add(cuenta);

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        aliasCuentaManager.grabarEstadisticaComprobanteEditarAliasCBU("012-123456/7");
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.COMPROBANTE_MODIFICACION_ALIAS_CBU, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, "012-123456/7");
    }

    /**
     * Confirmacion alias CBU ok test.
     */
    @Test
    public void confirmacionAliasCBUOkTest() {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);

        ComprobanteAltaDestinatarioView detalle = new ComprobanteAltaDestinatarioView();
        detalle.setCbu("12345678910");

        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("cbu", detalle.getCbu());
        mapaAtributos.put("cuit", detalle.getCuit());

        Respuesta<ComprobanteAltaCBUDTO> retBO = new Respuesta<ComprobanteAltaCBUDTO>();
        retBO.setEstadoRespuesta(EstadoRespuesta.OK);
        retBO.setRespuesta(new ComprobanteAltaCBUDTO("{0} y {1}", "pepe", "pipo"));
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(mapaAtributos));
        Mockito.when(aliasCuentaBO.confirmarAltaAlias(Matchers.any(DetalleCBUDTO.class))).thenReturn(retBO);

        Respuesta<AutentificacionDTO> respAutentificacion = new Respuesta<AutentificacionDTO>();
        respAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacion);

        Respuesta<ComprobanteAltaDestinatarioView> res = aliasCuentaManager.confirmacionCrearAliasCBU(detalle,
                "Mozilla");

        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.ALTA_ALIAS_CBU,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK, "012-123456/7");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals("pepe y pipo", res.getRespuesta().getMensajeEfectivizacion());
    }

    /**
     * Confirmacion alias CBU error test.
     */
    @Test
    public void confirmacionAliasCBUErrorTest() {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);
        ComprobanteAltaDestinatarioView detalle = new ComprobanteAltaDestinatarioView();
        detalle.setCbu("12345678910");
        Respuesta<ComprobanteAltaCBUDTO> retBO = new Respuesta<ComprobanteAltaCBUDTO>();
        retBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        retBO.add(new ItemMensajeRespuesta());
        Mockito.when(sesionParametros.getContadorAlias()).thenReturn(new ContadorIntentos(3));
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(obtenerRegistroSesion());
        Mockito.when(sesionParametros.getValidacionHash())
                .thenReturn(HashUtils.obtenerHash(crearMapaDeLaVista(detalle), HashUtils.ALGORITMO_MD5));
        Mockito.when(aliasCuentaBO.confirmarAltaAlias(Matchers.any(DetalleCBUDTO.class))).thenReturn(retBO);

        Respuesta<AutentificacionDTO> respuesta = new Respuesta<AutentificacionDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuesta);
        Respuesta<ComprobanteAltaDestinatarioView> res = aliasCuentaManager.confirmacionCrearAliasCBU(detalle,
                "Mozilla");
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.ALTA_ALIAS_CBU,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, "012-123456/7");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Continuar modificacion alias CBU error test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void continuarModificacionAliasCBUErrorTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);
        Respuesta<DetalleCBUView> res = null;
        Respuesta<DetalleCBUView> resBO = new Respuesta<DetalleCBUView>();
        DetalleCBUView detalle = new DetalleCBUView();
        detalle.setAliasCbu("prueba");
        detalle.setCbu("12345678910");
        detalle.setNumeroCuenta("272727827");
        resBO.setRespuesta(detalle);
        resBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTag("errorGenericoAliasCBU");
        itemMensajeRespuesta.setTipoError("ERROR_GENERICO_ALIAS_CBU");
        itemMensajeRespuesta.setMensaje(
                "<p>Ocurrió un error en nuestros servicios.</p><p> Por favor volvé a ingresar en unos minutos.</p>");
        resBO.add(itemMensajeRespuesta);
        Mockito.when(sesionParametros.getValidacionHash())
                .thenReturn(HashUtils.obtenerHash(crearMapaDeLaVista(detalle), HashUtils.ALGORITMO_MD5));
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(aliasCuentaBO.obtenerDatosCliente(Matchers.anyString(), Matchers.anyString())).thenReturn(resBO);
        res = aliasCuentaManager.continuarAliasCBU(detalle);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Grabar estadistica acceso ayuda modificacion test.
     */
    @Test
    public void grabarEstadisticaAccesoAyudaModificacionTest() {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);
        DetalleCBUView detalle = new DetalleCBUView();
        detalle.setAliasAnterior("prueba");
        detalle.setNumeroCuenta("272727827");
        aliasCuentaManager.grabadoEstadisticaAyuda(detalle);
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.ACCESO_A_AYUDA_MODIFICACION_ALIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK,detalle.getNumeroCuenta());
    }

    /**
     * Continuar modificacion alias CBU ok test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void continuarModificacionAliasCBUOkTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);
        Respuesta<DetalleCBUView> res = null;
        Respuesta<DetalleCBUView> resBO = new Respuesta<DetalleCBUView>();
        resBO.setEstadoRespuesta(EstadoRespuesta.OK);
        DetalleCBUView detalle = new DetalleCBUView();
        detalle.setAliasCbu("test");
        detalle.setCbu("12345678910");
        detalle.setNumeroCuenta("272727827");
        detalle.setNombreCliente("Agustina Fernandez");
        detalle.setCuit("20-35669878-4");
        detalle.setNombreBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        detalle.setTipoCuenta("Cuenta unica");
        detalle.setNumeroSucursal("424-Las Palmas");
        detalle.setNumeroCuenta("424-002456/7");
        resBO.setRespuesta(detalle);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(aliasCuentaBO.obtenerDatosCliente(Matchers.anyString(), Matchers.anyString())).thenReturn(resBO);
        res = aliasCuentaManager.continuarAliasCBU(detalle);

        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Confirmacion editar alias CBU ok test.
     */
    @Test
    public void confirmacionEditarAliasCBUOkTest() {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);

        ComprobanteAltaDestinatarioView detalle = new ComprobanteAltaDestinatarioView();
        detalle.setCbu("12345678910");

        Respuesta<ComprobanteAltaCBUDTO> retBO = new Respuesta<ComprobanteAltaCBUDTO>();
        retBO.setEstadoRespuesta(EstadoRespuesta.OK);
        retBO.setRespuesta(new ComprobanteAltaCBUDTO("{0} y {1}", "pepe", "pipo"));
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Mockito.when(aliasCuentaBO.confirmarAltaAlias(Matchers.any(DetalleCBUDTO.class))).thenReturn(retBO);
        Respuesta<AutentificacionDTO> respAutentificacion = new Respuesta<AutentificacionDTO>();
        respAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacion);

        Respuesta<ComprobanteAltaDestinatarioView> res = aliasCuentaManager.confirmacionEditarAliasCBU(detalle,
                "Mozilla");

        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.EDITAR_ALIAS_CBU,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK, "012-123456/7");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals("pepe y pipo", res.getRespuesta().getMensajeEfectivizacion());
    }

    /**
     * Confirmacion editar alias CBU error test.
     */
    @Test
    public void confirmacionEditarAliasCBUErrorTest() {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("12345678910");
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);

        ComprobanteAltaDestinatarioView detalle = new ComprobanteAltaDestinatarioView();
        detalle.setCbu("12345678910");

        Respuesta<ComprobanteAltaCBUDTO> retBO = new Respuesta<ComprobanteAltaCBUDTO>();
        retBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        retBO.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        retBO.add(new ItemMensajeRespuesta("pepe"));
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Mockito.when(sesionParametros.getContadorAlias()).thenReturn(new ContadorIntentos(2));
        Mockito.when(aliasCuentaBO.confirmarAltaAlias(Matchers.any(DetalleCBUDTO.class))).thenReturn(retBO);

        Respuesta<AutentificacionDTO> respAutentificacion = new Respuesta<AutentificacionDTO>();
        respAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacion);

        Respuesta<ComprobanteAltaDestinatarioView> res = aliasCuentaManager.confirmacionEditarAliasCBU(detalle,
                "Mozilla");
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.EDITAR_ALIAS_CBU,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, "012-123456/7");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear mapa de la vista.
     *
     * @param detalleCBUView
     *            the detalle CBU view
     * @return the map
     */
    private Map<String, Object> crearMapaDeLaVista(ComprobanteAltaDestinatarioView detalleCBUView) {
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("cbu", detalleCBUView.getCbu());
        mapaAtributos.put("cuit", detalleCBUView.getCuit());
        return mapaAtributos;
    }

    /**
     * Crear mapa de la vista.
     *
     * @param detalleCBUView
     *            the detalle CBU view
     * @return the map
     */
    private Map<String, Object> crearMapaDeLaVista(DetalleCBUView detalleCBUView) {
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("cbu", detalleCBUView.getCbu());
        mapaAtributos.put("cuit", detalleCBUView.getCuit());
        return mapaAtributos;
    }
}
