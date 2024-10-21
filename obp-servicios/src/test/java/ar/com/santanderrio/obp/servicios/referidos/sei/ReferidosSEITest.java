package ar.com.santanderrio.obp.servicios.referidos.sei;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.referidos.bo.ReferidosBO;
import ar.com.santanderrio.obp.servicios.referidos.manager.ReferidosManager;
import ar.com.santanderrio.obp.servicios.referidos.view.ReferidosInicioResponseView;


/**
 * Test para probar la funcionalidad de firmar nup para inicio de referidos
 * @author A309331
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, 
						classes = { ReferidosSEITest.ReferidosSEITestConfiguration.class, SecurityProviderConfig.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {
		"REFERIDOS.URL = http://wasdesainet05.ar.bsch:9099/exec/formulario-referidos/index.jsp",
		"REFERIDOS.PREMIO = Black,$ 8.000|Platinum,$ 5.000|Gold,$ 4.000|Infinity,$ 4.000|Supercuenta 3,$ 1.500|Supercuenta,$ 1.500",
		"APP.ENCODING = UTF-8",
		"TOKEN.REFERIDOS.PATH=/aplicaciones/hb/conf/keyStore/hbkey.jks",
		"TOKEN.REFERIDOS.TYPE=JKS",
		"TOKEN.REFERIDOS.ID=20104",
		"TOKEN.REFERIDOS.IDSEGURIDAD=20104"})
@ActiveProfiles(value = Profiles.TEST)
public class ReferidosSEITest {
	
	/**
	 * referidosManager
	 */
	@Autowired
	public ReferidosManager referidosManager;
	
	/**
	 * sesionCliente
	 */
	@Autowired
	public SesionCliente sesionCliente;
	
	
	/**
	 * Init method.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	ReferidosBO referidosBO;

	/**
	 * ReferidosSEITestConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { ReferidosManager.class, Sign.class, KeyStoreHelperImpl.class,
            RespuestaFactory.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                    KeyStoreFactory.class }))
	public static class ReferidosSEITestConfiguration {
		
		@Bean
		public SesionCliente sesionCliente() {
			return Mockito.mock(SesionCliente.class);
		}
		
		@Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
		
		@Bean
		public MensajeBO mensajeBO() {
			return Mockito.mock(MensajeBO.class);
		}
		
		@Bean
		public EstadisticaManager estadisticaManager() {
			return new EstadisticaManagerImpl();
		}

		@Bean
		public SesionParametros sesionParametros() {
			return new SesionParametros();
		}
		
		@Bean
		public EstadisticaBO estadisticaBO() {
			return Mockito.mock(EstadisticaBO.class);
		}
		
		@Bean
		public LegalBO LegalBO() {
			return Mockito.mock(LegalBO.class);
		}
		
		@Bean
        public KeyStoreFactory keyStoreFactory() {
            return Mockito.mock(KeyStoreFactory.class, new Answer<KeyStore>() {

                @Override
                public KeyStore answer(InvocationOnMock invocation) throws Throwable {
                    KeyStore keyStore = new KeyStore();
                    keyStore.setKeystoreType("JKS");
                    keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
                    keyStore.setKeystoreAlias("obp");
                    keyStore.setKeystorePassword("hbpassword");
                    return keyStore;
                }

            });
        }
		
		@Bean
        public ReferidosBO referidosBO() {
            return Mockito.mock(ReferidosBO.class);
        }
	}
	
	/**
	 * Test de inicio de referidos
	 */
	@Test
	public void referidosInicioTest() {
		
        try {
        	Cliente cliente = new Cliente();
        	cliente.setNup("20920410");
        	cliente.setProgramaBeneficios("S");
        	
        	when(sesionCliente.getCliente()).thenReturn(cliente);
			Respuesta<ReferidosInicioResponseView> viewResponse = referidosManager.obtenerInicioReferidos();
			
			Assert.assertNotNull(viewResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
