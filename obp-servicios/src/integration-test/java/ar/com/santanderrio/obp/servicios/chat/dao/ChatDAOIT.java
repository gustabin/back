package ar.com.santanderrio.obp.servicios.chat.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.chat.dao.ChatDAOIT.ChatDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.chat.dao.impl.ChatDAOImpl;
import ar.com.santanderrio.obp.servicios.chat.dao.impl.ChatSecurityGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.chat.dto.ConectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.DesconectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatConfigEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailRequestEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatTokenEntity;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;

/*
TIMEOUT.WEBSERVICE.CHAT = 10000

CHAT.SSL.ACTIVO = false
CHAT.MAIL.TIMEOUT = 10000
CHAT.MAIL.JKS = CHAT
CHAT.DATA.TIMEOUT = 10000
CHAT.DATA.JKS = CHAT


 */

/**
 * The Class ChatDAOIT.
 * 
 * @author ITResources
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, 
classes = { ChatDAOITConfiguration.class,
		SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
		"CHAT.POOL.ACTIVO=true",
		"CHAT.URL=http://webdesabcatel01:8120/Security.svc",
		"CHAT.TIMEOUT=10000",
		"CHAT.POOL.SIZE=3", 
		"CHAT.POOL.MAXWAIT=5000",
		"KEYSTORE.CHAT.PATH=/aplicaciones/hb/conf/keyStore/hbkey.jks", 
		"KEYSTORE.CHAT.TYPE=JKS",
		"KEYSTORE.CHAT.IDSEGURIDAD=20010", 
		"CHAT.JKS=CHAT", 
		"CHAT.HORA.DESDE=08:00",
		"CHAT.HORA.HASTA=20:55",
		"CHAT.HORA.HASTA.MSJ=21:00",
		"CHAT.MAIL.URL=http://lxgnesysdesa01.ar.bsch:8090/genesys/2/email/PostEmail",
		"CHAT.DATA.URL=http://lxgnesysdesa01.ar.bsch:8090/genesys/2/chat/CE18_Digital_Chat",
		"CHAT.CSRF.URL=http://lxcmrsdesa01.ar.bsch:6090/webapi/api/v2/diagnostics/version",
		"CHAT.CLOSE.TIMEOUT=60",
		"CHAT.CONNECTION.TIMEOUT=120",
		"CHAT.SESSION.TIMEOUT=300000",
		"CHAT.CLIENTES.HAB.PATH=filtrochat.txt",
		"APP.ENCODING=UTF-8"
})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ChatDAOIT {

	/** The chat DAO. */
	@Autowired
	private ChatDAO chatDAO;

	@Autowired
	private ChatClientesHabilitadosDAO chatClientesHabilitadosDAO;

	/** The id seg. */
	@Value("${KEYSTORE.CHAT.IDSEGURIDAD}")
	private String idSeg;

	/** The Constant HBPASS. */
	private static final String HBPASS = "hbpassword";


	/**
	 * Inits the.
	 */
	@Before
	public void init() throws DAOException {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class GestionWSTestConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { 
			ChatDAOImpl.class, 
			ChatSecurityGestionarWSImpl.class, 
			Sign.class, 
			KeyStoreHelperImpl.class,
			ContextHolder.class,
			RestWebClientImpl.class
	}, 
	excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {KeyStoreFactory.class }))
	public static class ChatDAOITConfiguration {

		/**
		 * Key store factory.
		 *
		 * @return the key store factory
		 */
		@Bean
		public KeyStoreFactory keyStoreFactory() {
			return Mockito.mock(KeyStoreFactory.class, new Answer<KeyStore>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.mockito.stubbing.Answer#answer(org.mockito.invocation.
				 * InvocationOnMock)
				 */
				@Override
				public KeyStore answer(InvocationOnMock invocation) throws Throwable {
					KeyStore keyStore = new KeyStore();
					keyStore.setKeystoreType("JKS");
					keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkeyBilleteraDESA8.jks").getPath());
					keyStore.setKeystoreAlias("obp");
					keyStore.setKeystorePassword(HBPASS);
					return keyStore;
				}

			});
		}

		/**
		 * Credential security factory.
		 *
		 * @return the credential security factory
		 */
		@Bean
		public CredentialSecurityFactory credentialSecurityFactory() {
			return Mockito.mock(CredentialSecurityFactory.class);
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

		@Bean
		public static ArchivoDeRecursosDAO archivoDeRecursosDAO() {
			return Mockito.mock(ArchivoDeRecursosDAO.class);
		}
	}

	@Test
	public void obtenerConfiguracionOK() throws DAOException {
		ChatConfigEntity chatConfiguracion = chatDAO.obtenerConfiguracion("00011903");
		assertNotNull(chatConfiguracion);
	}

	@Test
	public void obtenerConfiguracionException() throws DAOException {
		Mockito.when(chatClientesHabilitadosDAO.obtenerListaClientesHabilitados()).thenThrow(new DAOException());
		ChatConfigEntity chatConfiguracion = chatDAO.obtenerConfiguracion("00011903");
		assertNotNull(chatConfiguracion);
	}

	@Test
	public void conectarGenesysOK() throws DAOException {
		ChatTokenEntity chatTokenEntity = new ChatTokenEntity();
		chatTokenEntity.setApellido("ALESSO SALVAY");
		chatTokenEntity.setDocumento("16940691");
		chatTokenEntity.setFechaNacimiento("14/07/1964");
		chatTokenEntity.setJSessionID("AE1B4D482F585747F63B6B4A1C188F00");
		chatTokenEntity.setMail("dgalindez4@servexternos.isban-santander.com.ar");
		chatTokenEntity.setNombre("CAIFAS JONAS");
		chatTokenEntity.setNumeroCuenta("100-200529/4");
		chatTokenEntity.setNup("00011903");
		chatTokenEntity.setSegmento(BancoEnum.SANTANDER_RIO.getDescripcion());
		chatTokenEntity.setTarjetaAmex("3715-950000-00745");
		chatTokenEntity.setTarjetaDebito("4517660020626023");
		chatTokenEntity.setTarjetaVisa("4660-5700-2335-1922");
		ConectarInDTO conectarInDTO = new ConectarInDTO();
		conectarInDTO.setApellido("ALESSO SALVAY");
		conectarInDTO.setJSessionId("AE1B4D482F585747F63B6B4A1C188F00");
		conectarInDTO.setNombre("CAIFAS JONAS");
		conectarInDTO.setNup("00011903");
		conectarInDTO.setToken(chatTokenEntity);
		ChatResponseEntity respuesta = chatDAO.conectarGenesys(conectarInDTO);		
		assertNotNull(respuesta);
	}


	@Test
	public void desconectarGenesysOK() throws DAOException {
		DesconectarInDTO desconectarInDto = new DesconectarInDTO();
		desconectarInDto.setAlias("261");
		desconectarInDto.setChatId("0001EaDK9R0J02E0");
		desconectarInDto.setSecureKey("c0bfcd3c1f0ef19428ad");
		desconectarInDto.setUserId("01055B7EB82001AD");
		ChatResponseEntity respuesta = chatDAO.desconectarGenesys(desconectarInDto);
		assertNotNull(respuesta);
	}

	@Test
	public void enviarEmailGenesysOK() throws DAOException {
		ChatEmailRequestEntity chatEmailRequestEntity = new ChatEmailRequestEntity();
		chatEmailRequestEntity.setFirstName("CAIFAS JONAS");
		chatEmailRequestEntity.setFromAddress("dgalindez4@servexternos.isban-santander.com.ar");
		chatEmailRequestEntity.setLastName("ALESSO SALVAY");
		chatEmailRequestEntity.setSubject("Mail origen Online Banking");
		chatEmailRequestEntity.setTenantName("Resources");
		chatEmailRequestEntity.setText("Test de envio de email");
		chatEmailRequestEntity.setUserData("NUP=00011903");
		chatEmailRequestEntity.setUserData2("WASID=948ADDF465751CE0F3E99EED92C76B64");
		ChatEmailResponseEntity respuesta = chatDAO.enviarEmailGenesys(chatEmailRequestEntity);
		assertNotNull(respuesta);

	}

}
