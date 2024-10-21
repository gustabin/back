/**
 * 
 */
package ar.com.santanderrio.obp.servicios.status.manager;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.monitoreo.manager.MonitoreoManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.status.manager.StatusManagerTest.StatusManagerTestConfiguration;
import ar.com.santanderrio.obp.servicios.status.view.StatusView;

/**
 * The Class StatusManagerTest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { StatusManagerTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class StatusManagerTest {

    /** The status manager. */
    @InjectMocks
    @Autowired
    private StatusManager statusManager;

    /** generador de firmas digitales. */
    @Autowired
    private Sign sign;

    /** The legal DAO. */
    @Autowired
    private LegalDAO legalDAO;

    /** The subEmpresas DAO. */
    @Autowired
    private SubEmpresasDAO subEmpresasDAO;

    /** The ModuloPermiso DAO. */
    @Autowired
    private ModuloPermisoDAO moduloPermisoDAO;
    /** The Monitoreo BO. */
    @Autowired
    private MonitoreoManager monitoreoManager;
    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /**
     * Setup mock.
     */
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class IatxBaseDAOTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.status.manager" })
    @PropertySource(value = "classpath:servicioTest.properties")
    public static class StatusManagerTestConfiguration {
        /**
         * Poder leer properties de un properties file.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         */
        @Bean
        public RespuestaFactory respuestaFactory() {
            return Mockito.spy(new RespuestaFactory());
        }

        /**
         * Sign.
         *
         * @return the sign
         */
        @Bean
        public Sign sign() {
            return Mockito.mock(Sign.class);
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

        /**
         * SubEmpresasDAO.
         * 
         * @return
         */
        @Bean
        public SubEmpresasDAO subEmpresasDAO() {
            return Mockito.mock(SubEmpresasDAO.class);
        }

        /**
         * Legal DAO.
         *
         * @return the legal DAO
         */
        @Bean
        public LegalDAO legalDAO() {
            return Mockito.mock(LegalDAO.class);
        }

        /**
         * Destino prestamo DAO.
         *
         * @return the destino prestamo DAO
         */
        @Bean
        public DestinoPrestamoDAO destinoPrestamoDAO() {
            return Mockito.mock(DestinoPrestamoDAO.class);
        }

        /**
         * ModuloPermisoDAO.
         *
         * @return the ModuloPermiso DAO
         */
        @Bean
        public ModuloPermisoDAO moduloPermisoDAO() {
            return Mockito.mock(ModuloPermisoDAO.class);
        }

        @Bean
        public MonitoreoManager monitoreoManager() {
            return Mockito.mock(MonitoreoManager.class);
        }
    }

    /**
     * Gets the status.
     *
     * @return the status
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void getStatus() throws DAOException, IllegalAccessException {
        Map<String, ModuloPermiso> modExc = new HashMap<String, ModuloPermiso>();
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setMensaje("Mensaje de excluidos.");
        moduloPermiso.setAccionController(AccionController.IR_INICIO_CALENDARIO_DE_PAGOS);
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        modExc.put(AccionController.IR_INICIO_CALENDARIO_DE_PAGOS.getDescripcion(), moduloPermiso);

        Map<String, String> legales = new HashMap<String, String>();
        legales.put("1001", "legal de prueba");

        when(sign.buildPemSignature(Matchers.any(byte[].class), Matchers.anyString(), Matchers.anyBoolean()))
                .thenReturn("-----END PKCS7-----\n");
        when(legalDAO.obtenerLegales()).thenReturn(legales);
        when(subEmpresasDAO.cargoPesSubempresasFile()).thenReturn(true);
        when(moduloPermisoDAO.obtenerModulosPermisos()).thenReturn(modExc);
        when(monitoreoManager.validarBase()).thenReturn(Boolean.TRUE);
        when(monitoreoManager.validarIatx()).thenReturn(Boolean.TRUE);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        // consumir el manager.
        Respuesta<StatusView> respuesta = statusManager.getStatus();
        // asserts o validate mocks
        Assert.assertNotNull(respuesta);
    }

}
