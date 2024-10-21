/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.comun.PidFileUtil;
import ar.com.santanderrio.obp.servicios.configuration.ObpCustomInitializer;
import ar.com.santanderrio.obp.servicios.lucene.MediosDePagoTextFileIndexer;
import ar.com.santanderrio.obp.servicios.pagos.bo.BuscadorEmpresaDAOIT.BuscadorEmpresaDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.BuscadorEmpresaDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * Probar el buscador lucene para el txt de medios de pagos.
 * 
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ObpCustomInitializer.class,
        BuscadorEmpresaDAOITConfiguration.class, FilePath.class, BuscadorEmpresaDAOImpl.class,
        MediosDePagoTextFileIndexer.class, PidFileUtil.class })
@ActiveProfiles(value = Profiles.TEST)
@TestPropertySource(properties = { "LUCENE.BASEPATH=/aplicaciones/hb/conf/obp-properties/obp-config/test/lucene/" })
public class BuscadorEmpresaDAOIT {
    /** The Constant logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BuscadorEmpresaDAOIT.class);

    @Autowired
    private BuscadorEmpresaDAO buscadorEmpresaDAO;

    /**
     * Inits the properties.
     */
    @BeforeClass
    public static void init() {
        Properties properties = System.getProperties();
        properties.setProperty("config/OBP_UBICACION_PROPIEDADES", Object.class.getResource("/config").getPath());
    }

    /**
     * Iniciar los mocks.
     */
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Configuration
    public static class BuscadorEmpresaDAOITConfiguration {
        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }

    @Test
    public void search() {
        Set<MedioPago> medioPagoSet = buscadorEmpresaDAO.search("uni");
        LOGGER.info("Listado de medios de pago {}", medioPagoSet);
        Assert.assertEquals("Se espera que se indexen 301 lineas.", 301, medioPagoSet.size());
    }

    @Test
    public void searchEmpresaNuevoPago() {
        Set<MedioPago> medioPagoSet = buscadorEmpresaDAO.searchEmpresaNuevoPago("uni");
        LOGGER.info("Listado de medios de pago {}", medioPagoSet);
        Assert.assertFalse(medioPagoSet.isEmpty());
    }

    @Test
    public void searchEmpresaByIdAcuerdoCompras() {
        Set<MedioPago> medioPagoSet = buscadorEmpresaDAO.searchEmpresaByIdAcuerdoCompras("307026529750");
        LOGGER.info("Listado de medios de pago {}", medioPagoSet);
        Assert.assertEquals(1, medioPagoSet.size());
        Assert.assertEquals("IPLAN COMUNICACIONES", medioPagoSet.iterator().next().getNombreFantasia());
    }
    
    @Test
    public void searchPorNombreFantasiaPagoAutomatico() {
        Set<MedioPago> medioPagoSet = buscadorEmpresaDAO.porNombreFantasiaPagoAutomatico("ypf GAS");
        LOGGER.info("Listado de medios de pago {}", medioPagoSet);
        Assert.assertEquals(1, medioPagoSet.size());
        Assert.assertFalse(medioPagoSet.isEmpty());
    }
}
