package ar.com.santanderrio.obp.servicios.database.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.database.dao.AdvantageDAOIT.AdvantageDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.AdvantageDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GrupoAfinidadClienteOutEntity;

/**
 * The Class CuentaSaldoDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AdvantageDAOITConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ADVANTAGE.ID = 20291", "DB.ADVANTAGE.DS = jdbc/advantage",
        "DB.TIMEOUT = 40000", "APP.ENCODING = UTF-8" })
public class AdvantageDAOIT extends HbankBaseDAOIT {
    
	/** The cuenta saldo DAO. */
	@Autowired
	@InjectMocks
	private AdvantageDAO advantageDAO;

    /**
     * The Class AdvantageDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class AdvantageDAOITConfiguration {
        
    	/**
		 * Property configurer.
		 *
		 * @return the property sources placeholder configurer
		 */
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
		@Bean
		public static RespuestaFactory respuestaFactory() {
			return Mockito.mock(RespuestaFactory.class);
		}
		
		@Bean
		public static MensajeBO mensajeBO() {
			return Mockito.mock(MensajeBO.class);
		}
		
		@Bean
		public static ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient RestWebClient() {
			return Mockito.mock(ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient.class);
		}
		
		@Bean
		public static Sign Sign() {
			return Mockito.mock(Sign.class);
		}
    }

    /**
     * Test cuenta saldo DAO.
	 * 
     * @throws DAOException 
     */
    @Test
    public void testUsuarioSuperclub() throws DAOException {

        Cliente cliente = new Cliente();
		// cliente.setNup("00743315");
		cliente.setNup("01474108");
		Respuesta<GrupoAfinidadClienteOutEntity> esSuperclub = advantageDAO.consultarUsuarioEsSuperclub(cliente);
        
        Assert.assertTrue(esSuperclub.getRespuesta() != null);
    }

}
