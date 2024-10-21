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
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.MonedaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TipoCuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.UsuarioDTO;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasCuentaNoActivaException;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasInexistenteEliminadoException;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasNoCoincidenMonedasException;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasNoExisteCuentaBanelcoException;
import ar.com.santanderrio.obp.servicios.marcaviajero.MarcaViajeroDAOIT.MarcaViajeroDAOITConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { MarcaViajeroDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "TITULARIDADEXTENDIDA.POOL.ACTIVO=false", 
        //"TITULARIDADEXTENDIDA.URL=https://localhost:6443/servicios/transferenciasV2",  
        //hacer tunel a 200.59.131.205 port 443 con local en 6443 
        "TITULARIDADEXTENDIDA.URL=https://200.59.131.205/servicios/transferenciasV2",
        "TITULARIDADEXTENDIDA.TIMEOUT=100", "TITULARIDADEXTENDIDA.POOL.SIZE=30", "TITULARIDADEXTENDIDA.POOL.MAXWAIT=50",  "APP.ENCODING = UTF-8", "KEYSTORE.TITULARIDADEXTENDIDA.IDSEGURIDAD=20104", "TITULARIDADEXTENDIDACBU.ENCUSER=banelco_pub", 
        "KEYSTORE.TITULARIDADEXTENDIDA.PATH=D:/Build/_work/18/s/src/test/resources/config/test/keyStore/hbkeyBilleteraDESA8.jks", 
        "KEYSTORE.TITULARIDADEXTENDIDA.TYPE=JKS", "TITULARIDADEXTENDIDACBU.TTL.TIMESTAMP=600",
        "KEYSTORE.FACTURAELECTRONICA.IDSEGURIDAD=20104",
        
        "ALIAS.POOL.ACTIVO=false", 
        "ALIAS.URL=https://localhost:6443/servicios/transferenciasV2",
        "ALIAS.TIMEOUT=100", "ALIAS.POOL.SIZE=30", "ALIAS.POOL.MAXWAIT=50",  "APP.ENCODING = UTF-8", "KEYSTORE.ALIAS.IDSEGURIDAD=20104", "ALIASCBU.ENCUSER=banelco_pub", 
        "KEYSTORE.ALIAS.PATH=D:/Build/_work/18/s/src/test/resources/config/test/keyStore/hbkeyBilleteraDESA8.jks", 
        "KEYSTORE.ALIAS.TYPE=JKS", "ALIASCBU.TTL.TIMESTAMP=600",
        "KEYSTORE.FACTURAELECTRONICA.IDSEGURIDAD=20104"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class DatosTitularExtendidoIT {
    
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The alias DAO. */
    @Autowired
    private AliasCbuDAO aliasDAO;

    @Value("${KEYSTORE.TITULARIDADEXTENDIDA.IDSEGURIDAD}")
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
    @ComponentScan(basePackageClasses = { AliasCbuDAOImpl.class, TitularidadExtendidaWSImpl.class, Sign.class, KeyStoreHelperImpl.class, ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class DatosTitularExtendidoITConfiguration {
        
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
    public void consultarDatosTitularidadExtendido() throws SQLException, DAOException, ValidacionAliasInexistenteEliminadoException, ValidacionAliasCuentaNoActivaException, ValidacionAliasNoCoincidenMonedasException, ValidacionAliasNoExisteCuentaBanelcoException {
    	
    	Credential cred = new Credential(); 
    	cred.setUsuario("bcorio");
    	cred.setPassword("hbpassword");
    	Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
    	ConsultarDatosTitularidadExtendido request = new ConsultarDatosTitularidadExtendido();
    	TerminalDTO terminal = new TerminalDTO();
    	terminal.setCanal("E");
    	terminal.setDatosTerminal("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    	terminal.setDireccionIp("180.166.12.141");
    	request.setTerminal(terminal);
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
    	usuarioDTO.setNroDocumento("00021587183");
    	usuarioDTO.setTipoDocumento("1");
    	request.setUsuario(usuarioDTO);
    	request.setAlias("aceitera-norteXXX");
    	CuentaDTO cuenta = new CuentaDTO();
    	cuenta.setNumero("000004042693       ");
    	cuenta.setNumeroCBU("0720000720000040426936");
    	MonedaDTO moneda = new MonedaDTO();
    	moneda.setCodigo("1");
    	cuenta.setMoneda(moneda);
    	TipoCuentaDTO tipo = new TipoCuentaDTO();
    	tipo.setCodigo("0");
    	cuenta.setTipo(tipo);
    	request.setCuentaOrigen(cuenta);
    	ConsultarDatosTitularidadExtendidoResponse response = aliasDAO.consultarDatosTitularidadExtendido(request);
    	
    	Assert.assertNotNull(response);
    }

}
