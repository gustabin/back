package ar.com.santanderrio.obp.servicios.todopago.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;
import ar.com.santanderrio.obp.servicios.todopago.dao.SolicitudAdhesionTodoPagoDAOIT.SolicitudAdhesionTodoPagoDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.todopago.dao.impl.SolicitudAdhesionTodoPagoDAOImpl;

/**
 * Test de integracion para la solicitud de todo pago.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SolicitudAdhesionTodoPagoDAOITConfiguration.class,
        BackEndSecurityConfig.class, SolicitudAdhesionTodoPagoDAOImpl.class})
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"APP.ENCODING = UTF-8", "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.DS = jdbc/hbalias", "DB.ESTADISTICAS.ID = 40011",
        "DB.TIMEOUT = 30000"})
public class SolicitudAdhesionTodoPagoDAOIT extends HbankBaseDAOIT {
    
    /**
     * Clase de configuracion para el test de todo pago
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { 
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class SolicitudAdhesionTodoPagoDAOITConfiguration {
        
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

    /** The contrato DAO. */
    @Autowired
    SolicitudAdhesionTodoPagoDAO solicitudAdhesionTodoPagoDAO;

    /**
     * Testa de grabar solicitud de todo pago.
     *
     */
    /*@Test
    public void grabarSolicitud() throws DAOException {
        SolicitudTodoPagoDTO solicitudTodoPago = new SolicitudTodoPagoDTO();
        Calendar cal = Calendar.getInstance();
        Date fechaSolicitud = new Date(cal.getTime().getTime());
        solicitudTodoPago.setFechaSolicitud(fechaSolicitud);
        solicitudTodoPago.setNup("1234");
        solicitudTodoPago.setTipoDocumento("1");
        solicitudTodoPago.setNumeroDocumento("12345678");
        solicitudTodoPago.setRazonSocial("testit");
        solicitudTodoPago.setCuitDni("20123456780");
        Date fechaNacimiento = new Date(cal.getTime().getTime());
        solicitudTodoPago.setFechaNacimiento(fechaNacimiento);
        solicitudTodoPago.setSexo("I");
        solicitudTodoPago.setPaisOrigen("ARGENTINA");
        solicitudTodoPago.setNumeroCuenta("123456");
        solicitudTodoPago.setCbu("1234567890123456789012");
        solicitudTodoPago.setEmail("pepe@pepe.com");
        solicitudTodoPago.setTelefonoFijo("555-5555");
        solicitudTodoPago.setCelular("15-555-5555");
        solicitudTodoPago.setEmpresaCelular("15-555-5555");
        solicitudTodoPago.setNombreContacto("nombre contacto");
        solicitudTodoPago.setApellidoContacto("apellido contacto");
        solicitudTodoPago.setCondicionIVA("condi");
        solicitudTodoPago.setActividad("actividad");
        solicitudTodoPago.setCondicionIIBB("IIBB");
        DomicilioDTO domicilioLegal = new DomicilioDTO();
        domicilioLegal.setCalle("uspalllata");
        domicilioLegal.setNumero("1234");
        domicilioLegal.setPiso("");
        domicilioLegal.setDepartamento("");
        domicilioLegal.setProvincia("d");
        domicilioLegal.setLocalidad("a");
        domicilioLegal.setCodigoPostal("123");
        solicitudTodoPago.setDomicilioLegal(domicilioLegal);
        DomicilioDTO domicilioFacturacion = new DomicilioDTO();
        domicilioFacturacion.setCalle("caseros");
        domicilioFacturacion.setNumero("12342");
        domicilioFacturacion.setPiso("");
        domicilioFacturacion.setDepartamento("");
        domicilioFacturacion.setProvincia("d");
        domicilioFacturacion.setLocalidad("a");
        domicilioFacturacion.setCodigoPostal("123");
        solicitudTodoPago.setDomicilioFacturacion(domicilioFacturacion);
        solicitudTodoPago.setEnvioMailSatisfactorio("S");
        
        try {
            solicitudAdhesionTodoPagoDAO.grabarSolicitud(solicitudTodoPago);
        } catch (DAOException e) {
            Assert.fail();
        }

    }*/

}