/**
 * 
 */
package ar.com.santanderrio.obp.iatx;

import static org.mockito.Mockito.mock;

import java.util.HashMap;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.iatx.helpers.IatxInicial;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSender;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.PropertyMap;

/**
 * Test para probar integracion de capas del dao y mockear la trama de iatx.<br>
 * EXTENDER Solamente, de esta clase para probar los DAO que consuman una
 * funcionalidad provista por IATX.<br>
 * <p>
 * 
 * Si se desea sobreescribir una propiedad en el siguiente ejemplo se muestra
 * como pisar el valor de IATX.LOG de 1 a 10:
 * 
 * <pre>
 * <code>
 * 	&#64;TestPropertySource(properties = { "IATX.LOG = 10"})
 * </code>
 * </pre>
 * </p>
 * Ejemplo de implentacion
 * {@link ar.com.santanderrio.obp.servicios.compraVenta.dao.CotizacionDolaresDAOTest}
 * 
 * @author pablo.gargaglione
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        IatxBaseDAOTest.IatxBaseDAOTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "IATX.TIMEOUT = 90000", "IATX.PROGRAMA = FEINICIO", "IATX.TRANSID = FE04",
        "IATX.LOG = 1", "SERVICIO.PREFIJO.CNSCOTCN=CNSCOTCN__",
        "IATX.DIRLOG = /aplicaciones/hb/logs/http/wasobptbdesa-0", "IATX.GATENAME = mvscpum.ar.bsch",
        "IATX.GATEPORT = 2300", "IATX.CICS = FEINIH2", "SERVICIO.VERSION.CNSCOTCN=100",
        "SERVICIO.PREFIJO.CNSDOCXNUP = CNSDOCXNUP", "SERVICIO.VERSION.CNSDOCXNUP = 110",
        "SERVICIO.PREFIJO.SIMCPVTACN=SIMCPVTACN", "SERVICIO.VERSION.SIMCPVTACN=110",
        "SERVICIO.PREFIJO.SIMDOLCNLS=SIMDOLCNLS", "SERVICIO.VERSION.SIMDOLCNLS=100",
        "SERVICIO.PREFIJO.CPVTADOLCN = CPVTADOLCN", "SERVICIO.VERSION.CPVTADOLCN = 110",
        "SERVICIO.PREFIJO.CPADOLCNLS = CPADOLCNLS", "SERVICIO.VERSION.CPADOLCNLS = 100",
        "SERVICIO.PREFIJO.CNSCTAMOVS = CNSCTAMOVS", "SERVICIO.VERSION.160 = 160",
        "SERVICIO.PREFIJO.SEGINFORM = SEGINFORM", "SERVICIO.VERSION.130 = 130",
        "SERVICIO.PREFIJO.ACTSGIDESF = ACTSGIDESF", "SERVICIO.VERSION.ACTSGIDESF = 100",
        "SERVICIO.PREFIJO.IDESGICLIE = IDESGICLIE", "SERVICIO.VERSION.IDESGICLIE = 100",
        "SERVICIO.PREFIJO.VALSGIDESF = VALSGIDESF", "SERVICIO.VERSION.VALSGIDESF = 100",
        "SERVICIO.PREFIJO.VALSGITEL = VALSGITEL_", "SERVICIO.VERSION.VALSGITEL = 100",
        "SERVICIO.PREFIJO.SEGSGIAUT = SEGSGIAUT_", "SERVICIO.VERSION.SEGSGIAUT = 100",
        "SERVICIO.PREFIJO.CNSPTMODEU = CNSPTMODEU", "SERVICIO.VERSION.CNSPTMODEU = 110",
        "SERVICIO.PREFIJO.CNSCTADATO = CNSCTADATO", "SERVICIO.VERSION.CNSCTADATO = 160",
        "SERVICIO.PREFIJO.CNSCTATIT = CNSCTATIT_", "SERVICIO.VERSION.CNSCTATIT = 110",
        "SERVICIO.PREFIJO.TRFCTACRU = TRFCTACRU", "SERVICIO.VERSION.TRFCTACRU = 100",
        "SERVICIO.PREFIJO.CNSCTASDOM = CNSCTASDOM", "SERVICIO.VERSION.CNSCTASDOM150 = 150",
        "SERVICIO.PREFIJO.CNSTJCDET_ = CNSTJCDET_", "SERVICIO.VERSION.CNSTJCDET_ = 100",
        "SERVICIO.PREFIJO.CNSDMERLIN = CNSDMERLIN", "SERVICIO.VERSION.CNSDMERLIN = 100",
        "SERVICIO.PREFIJO.CNSPERSFIS = CNSPERSFIS", "SERVICIO.VERSION.CNSPERSFIS = 110",
        "SERVICIO.PREFIJO.CNSCTAMONE = CNSCTAMONE", "SERVICIO.VERSION.CNSCTAMONE = 100",
        "SERVICIO.PREFIJO.ALTTARJETA = ALTTARJETA", "SERVICIO.VERSION.ALTTARJETA = 100",
        "SERVICIO.PREFIJO.IDEPESBANE = IDEPESBANE", "SERVICIO.VERSION.IDEPESBANE = 100",
        "TAGS.A.ELIMINAR.DB="})

@ActiveProfiles(value = Profiles.TEST)
public abstract class IatxBaseDAOTest {

    /** The iatx sender. */
    @Autowired
    protected IatxSender iatxSender;
    
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
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.iatx.dao",
            "ar.com.santanderrio.obp.base.iatx.entities", "ar.com.santanderrio.obp.base.context" })
    public static class IatxBaseDAOTestConfiguration {

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
         * Poder utilizar SessionUsuarioData que lee el bean del scope session.
         *
         * @return the custom scope configurer
         */
        @Bean
        public CustomScopeConfigurer customScopeConfigurer() {
            CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();

            HashMap<String, Object> scopes = new HashMap<String, Object>();
            scopes.put("request", new SimpleThreadScope());
            scopes.put("session", new SimpleThreadScope());
            scopes.put("systemrequested", new SimpleThreadScope());
            scopeConfigurer.setScopes(scopes);

            return scopeConfigurer;
        }

        /**
         * Property map.
         *
         * @return the property map
         */
        @Bean
        public static PropertyMap propertyMap() {
            return Mockito.mock(PropertyMap.class);
        }

        /**
         * Establece para los TEST que se utilice en la trama el RACF hardcode, no
         * generan llamada real al cics.
         * 
         * @return
         */
        @Bean
        public IatxInicial iatxInicial() {
            IatxInicial iatxInicial = mock(IatxInicial.class);
            Mockito.when(iatxInicial.getRacfInicialId()).thenReturn("FREEUSER");
            Mockito.when(iatxInicial.getRacfInicialPwd()).thenReturn("FREEUSR0");
            return iatxInicial;
        }
        
        /* Clase a mockear para poder setear las tramas que se necesitan evaluar.
        *
        * @return the iatx sender
        */
       @Bean
       public IatxSender iatxSender() {
           return mock(IatxSender.class);
       }
        
    }
}