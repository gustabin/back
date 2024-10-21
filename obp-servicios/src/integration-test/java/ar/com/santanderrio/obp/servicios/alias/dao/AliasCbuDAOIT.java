/**
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import java.sql.SQLException;

import org.junit.Assert;
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
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasCbuException;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaCBU;
import ar.com.santanderrio.obp.generated.webservices.alias.ResponseAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.TerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.UsuarioDTO;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAOIT.AliasCbuDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCBUCuentaInactivaException;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;

/**
 * The Class AliasCbuDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AliasCbuDAOITConfiguration.class,
		SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "ALIAS.POOL.ACTIVO=false",
		"ALIAS.URL=https://200.59.131.205/transf-api/external/AliasBCRAClient",
		// "ALIAS.URL=https://localhost:5443/transf-api/external/AliasBCRAClient",
		"ALIAS.TIMEOUT=10000", "ALIAS.POOL.SIZE=30", "ALIAS.POOL.MAXWAIT=50", "APP.ENCODING = UTF-8",
		"KEYSTORE.ALIAS.IDSEGURIDAD=20104", "ALIASCBU.ENCUSER=banelco_pub",
		"KEYSTORE.ALIAS.PATH=D:/Build/_work/18/s/src/test/resources/config/test/keyStore/hbkeyBilleteraDESA8.jks",
		// "KEYSTORE.ALIAS.PATH=C:/vistas/project/Obp-new/config/obp-config/production/keyStore/hbkey.jks",
		"KEYSTORE.ALIAS.TYPE=JKS", "ALIASCBU.TTL.TIMESTAMP=600", "KEYSTORE.FACTURAELECTRONICA.IDSEGURIDAD=20104",
		"TITULARIDADEXTENDIDA.POOL.ACTIVO=false",
		"TITULARIDADEXTENDIDA.URL=https://200.59.131.205/servicios/transferenciasV2",
		"TITULARIDADEXTENDIDA.TIMEOUT=100", "TITULARIDADEXTENDIDA.POOL.SIZE=30", "TITULARIDADEXTENDIDA.POOL.MAXWAIT=50",
		"APP.ENCODING = UTF-8", "KEYSTORE.TITULARIDADEXTENDIDA.IDSEGURIDAD=20104",
		"TITULARIDADEXTENDIDACBU.ENCUSER=banelco_pub",
		"KEYSTORE.TITULARIDADEXTENDIDA.PATH=D:/Build/_work/18/s/src/test/resources/config/test/keyStore/hbkeyBilleteraDESA8.jks",
		"KEYSTORE.TITULARIDADEXTENDIDA.TYPE=JKS", "TITULARIDADEXTENDIDACBU.TTL.TIMESTAMP=600", })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AliasCbuDAOIT {
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The alias DAO. */
	@Autowired
	private AliasCbuDAO aliasDAO;

	@Value("${KEYSTORE.ALIAS.IDSEGURIDAD}")
	private String idSeg;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class GestionWSTestConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { AliasCbuDAOImpl.class, AliasCbuGestionarWSImpl.class,
			TitularidadExtendidaWSImpl.class, Sign.class, KeyStoreHelperImpl.class,
			ContextHolder.class, CryptoHelper.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))
	public static class AliasCbuDAOITConfiguration {

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
					keyStore.setKeystorePath(
							Object.class.getResource("/config/test/keyStore/hbkeyBilleteraDESA8.jks").getPath());
					keyStore.setKeystoreAlias("bcorio");
					keyStore.setKeystorePassword("hbpassword");
					return keyStore;
				}

			});
		}

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

	}

	@Test
	public void altaCBU() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestAlias req = new RequestAlias();
		req.setAlias("nanyprueba");
		req.setCbu("0720033531000000837170");
		req.setCuit("20215871836");
		TerminalDTO terminal = new TerminalDTO();
		terminal.setCanal("A");
		terminal.setDatosTerminal(
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		terminal.setDireccionIp("180.166.8.241");
		req.setTerminalDTO(terminal);
		req.setTipoCta("12");
		req.setTipoPersona("F");
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNroDocumento("00021587183");
		usuarioDTO.setTipoDocumento("1");
		req.setUsuarioDTO(usuarioDTO);
		ResponseAlias response = aliasDAO.altaAlias(req);

		Assert.assertNotNull(response);
	}

	@Test
	public void bajaAlias() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestAlias req = new RequestAlias();
		req.setAlias("nanyprueba");
		req.setCbu("0720033531000000837170");
		req.setCuit("20215871836");
		TerminalDTO terminal = new TerminalDTO();
		terminal.setCanal("A");
		terminal.setDatosTerminal(
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		terminal.setDireccionIp("180.166.8.241");
		req.setTerminalDTO(terminal);
		req.setTipoCta("12");
		req.setTipoPersona("F");
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNroDocumento("00021587183");
		usuarioDTO.setTipoDocumento("1");
		req.setUsuarioDTO(usuarioDTO);
		ResponseAlias response = aliasDAO.bajaAlias(req);

		Assert.assertNotNull(response);
	}

	@Test
	public void obtenerCBUDesdeAlias() throws DAOException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestConsultaAlias req = new RequestConsultaAlias();
		req.setAlias("sdasdwww1");
		TerminalDTO terminal = new TerminalDTO();
		terminal.setCanal("A");
		terminal.setDatosTerminal(
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		terminal.setDireccionIp("180.166.8.241");
		req.setTerminalDTO(terminal);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNroDocumento("00021587183");
		usuarioDTO.setTipoDocumento("1");
		req.setUsuarioDTO(usuarioDTO);
		ResponseAlias response = aliasDAO.obtenerCBUDesdeAlias(req);

		Assert.assertNotNull(response);
	}

	@Test
	public void obtenerAliasDesdeCBU()
			throws DAOException, AliasCBUCuentaInactivaException, AliasCbuException, SQLException {
		Credential cred = new Credential();
		cred.setUsuario("bcorio");
		cred.setPassword("hbpassword");
		Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
		RequestConsultaCBU request = new RequestConsultaCBU();
		request.setCbu("0720033531000000837170");
		TerminalDTO terminal = new TerminalDTO();
		terminal.setCanal("A");
		terminal.setDatosTerminal(
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		terminal.setDireccionIp("180.166.8.241");
		request.setTerminalDTO(terminal);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNroDocumento("00021587183");
		usuarioDTO.setTipoDocumento("1");
		request.setUsuarioDTO(usuarioDTO);
		ResponseAlias response = aliasDAO.obtenerAliasDesdeCBU(request);

		Assert.assertNotNull(response);
	}

}
