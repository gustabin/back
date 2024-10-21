package ar.com.santanderrio.obp.servicios.obp.comun.sucursales;

import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.SucursalBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.impl.SucursalBOImpl;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.SucursalDAO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl.SucursalDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.sucursales.service.ConsultarSucursalesService;
import ar.com.santanderrio.obp.servicios.obp.comun.sucursales.ConsultarSucursalesServiceTest.ConsultarSucursalesServiceTestConfiguration;

/**
 * The Class ConsultarSucursalesServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ConsultarSucursalesServiceTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(Profiles.TEST)
public class ConsultarSucursalesServiceTest {

    /** The sucursales service. */
    @Autowired
    ConsultarSucursalesService sucursalesService;

    /**
     * Inits the.
     */
    @BeforeClass
    public static void init() {
        Properties properties = System.getProperties();
        properties.setProperty("config/OBP_UBICACION_PROPIEDADES", Object.class.getResource("/config").getPath());
    }

    /**
     * The Class ConsultarSucursalesServiceTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.sucursales",
            "ar.com.santanderrio.obp.base.comun" }, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {SucursalBO.class, SucursalBOImpl.class, SucursalDAO.class, SucursalDAOImpl.class}) })
    public static class ConsultarSucursalesServiceTestConfiguration {

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

    /**
     * Service tests.
     *
     * @throws JAXBException
     *             the JAXB exception
     */
    @Test
    public void serviceTests() throws JAXBException {
        Assert.assertNotNull(sucursalesService);
    }

    /**
     * Buscar sucursal test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void buscarSucursalTest() throws ServiceException {
        Respuesta<Sucursal> respuesta = sucursalesService.consultarSucursalPorId("0006");
        Assert.assertNotNull(respuesta.getRespuesta());
    }

    /**
     * Buscar sucursal test 2.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void buscarSucursalTest2() throws ServiceException {
        Respuesta<Sucursal> respuesta = sucursalesService.consultarSucursalPorId("0007");
        Assert.assertNotNull(respuesta.getRespuesta());
    }

    /**
     * Buscar sucursal fail test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void buscarSucursalFailTest() throws ServiceException {
        Respuesta<Sucursal> respuesta = sucursalesService.consultarSucursalPorId("0001");
        Assert.assertNull(respuesta.getRespuesta());
    }
}
