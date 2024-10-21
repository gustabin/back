/**
 * 
 */
package ar.com.santanderrio.obp.servicios.database.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.config.ws.CacheConfig;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.database.dao.TarjetaRecargableDAOIT.TarjetaRecargableDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dao.TarjetaRecargableDAO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableInEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableOutEntity;

/**
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        TarjetaRecargableDAOITConfiguration.class, CacheConfig.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", 
        "DB.RECLAMOS.ID = 20013", "DB.RECLAMOS.DS = jdbc/hbreclamos",
        "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "DB.ALIAS.ID = 40011", "DB.ALIAS.DS = jdbc/hbalias",
        "DB.ALIAS.ID = 40011", "DB.ALIAS.DS = jdbc/hbalias",
        "DB.ONDEMAND.CONN.SECURITY.ID = 20016",
        "DB.BPRIV.ID = 95000", "DB.BPRIV.DS = jdbc/hbdbacciones",
        "DB.RSA.ID = 20024",
        "DB.JWT.ID = 20010",
        "DB.BPRIVRACF.ID = 21151",
        "DB.IMPUESTOS.ID = 20015", "DB.IMPUESTOS.DS = jdbc/hbdb0",
        "DB.CLASIFDEUDORES.ID = 20020", "DB.CLASIFDEUDORES.DS = jdbc/hbclasifdeuddesa",  
        "DB.PORTAL.DS = jdbc/PORTALDBDESA", "DB.PORTAL.ID = 20019",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })
public class TarjetaRecargableDAOIT extends HbankBaseDAOIT {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaRecargableDAOIT.class);

    /** The tarjRecargable DAO. */
    @Autowired
    private TarjetaRecargableDAO tarjetaRecargableDAO;

    /**
     * The Class LegalDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.tarjetarecargable.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class TarjetaRecargableDAOITConfiguration {
        
        @Bean
        public RespuestaFactory respuestaFactory() {
            return Mockito.mock(RespuestaFactory.class);
        }

        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

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
     * Obtener legales as map.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerLegalesAsMap() throws DAOException {
        String spAtributos = "|GCLI|HBAN|00276937|N|00021587183|F|MILANDO|CONSTANCIO PERCY|20041970|0000|IND| |0000|0000000000000000| |00| | |000000|0|| |4|                    29786|Apellido y Nombre del Titular de la Cuenta: CONSTANCIO PERCY, MILANDO\r\n" +
"Número de Documento: 00021587183\r\n" +
"Apellido y Nombre del adicional: ggg dfgdf\r\n" +
"Número de documento del adicional: 21212121\r\n" +
"Fecha de nacimiento del adicional: 02/10/1936\r\n" +
"Nacionalidad del adicional: Argentina\r\n" +
"Domicilio del adicional: wdefwd 666 6 y 1406 sdf Capital Federal\r\n" +
"Teléfono del adicional: 1122321212\r\n" +
"| |HBAN0001|00000000000000000000|INFI|0033|0000000003663936|ARS|01|07|0001|\r\n";
        SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity = new SolicitudTarjetaRecargableInEntity();
        solicitudTarjetaRecargableInEntity.setSpAtributos(spAtributos);
        long initTime1 = System.currentTimeMillis();
        Respuesta<SolicitudTarjetaRecargableOutEntity> respuestaAlta = tarjetaRecargableDAO
                .altaSolicitudTarjetaRecargable(solicitudTarjetaRecargableInEntity);
        LOGGER.info("Tiempo en miliseg consumido en la primer llamada {}.", System.currentTimeMillis() - initTime1);
        Assert.assertTrue(respuestaAlta.getEstadoRespuesta().equals(EstadoRespuesta.OK));

    }

}
