package ar.com.santanderrio.obp.servicios.billetera.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultaCuentaResult;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultarCuenta;
import ar.com.santanderrio.obp.servicios.billetera.dao.AdhesionBilleteraDAOIT.AdhesionBilleteraDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.billetera.dao.impl.BilleteraCuentaGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.billetera.dao.impl.BilleteraDAOImpl;
import ar.com.santanderrio.obp.servicios.billetera.dao.impl.ConsultarXmlDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionBilletera;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;

/**
 * The Class AdhesionBilleteraDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AdhesionBilleteraDAOITConfiguration.class, SecurityProviderConfig.class, SesionBilletera.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "BILLETERA.POOL.ACTIVO=false", 
        "BILLETERA.URL=https://developers.todopago.com.ar/services/t/1.1/BilleteraCuenta",
//        "BILLETERA.URL=https://localhost:2643/services/t/1.1/BilleteraCuenta",
        "BILLETERA.TIMEOUT=3", "BILLETERA.POOL.SIZE=30", "BILLETERA.POOL.MAXWAIT=50",  "APP.ENCODING = UTF-8", 
        "BILLETERA.BILLETERACUENTA.URL=https://localhost:2643/services/t/1.1/BilleteraCuenta",
        "BILLETERA.BILLETERACUENTA.TIMEOUT=3", "BILLETERA.BILLETERACUENTA.POOL.SIZE=30", "BILLETERA.BILLETERACUENTA.POOL.MAXWAIT=50", 
        "KEYSTORE.BILLETERA.IDSEGURIDAD=20996", "BILLETERA.ENC_USER=todopago_sha2", 
        "KEYSTORE.BILLETERA.PATH=D:/Build/_work/18/s/src/test/resources/config/test/keyStore/hbkeyBilleteraDESA8.jks", 
//        "KEYSTORE.BILLETERA.PATH=C:/Users/sergio.e.goldentair/tbanco/Obp-new/obp-servicios/src/test/resources/config/test/keyStore/hbkey.jks", 
        "KEYSTORE.BILLETERA.TYPE=JKS", "BILLETERA.TTL_TIMESTAMP=600",
        "KEYSTORE.FACTURAELECTRONICA.IDSEGURIDAD=20104",
        "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "DB.TIMEOUT = 30000"})
// por integracion continua se hardcodea el path dentro del servidor ya que no se encontro la forma de colocar la ruta por classpath
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
//@Ignore
public class AdhesionBilleteraDAOIT extends HbankBaseDAOIT {

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The alias DAO. */
    @Autowired
    private BilleteraDAO adhesionBilleteraDao;

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
    @ComponentScan(basePackageClasses = { BilleteraDAOImpl.class, BilleteraCuentaGestionarWSImpl.class, Sign.class, KeyStoreHelperImpl.class, ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class , ConsultarXmlDAOImpl.class}))
    public static class AdhesionBilleteraDAOITConfiguration {
        
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
                    keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
                    keyStore.setKeystoreAlias("bcorio2");
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
    public void consultarCuenta() throws DAOException, SQLException {
        Credential cred1 = new Credential(); 
        cred1.setUsuario("bcorio2");
        cred1.setPassword("hbpassword");
        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(cred1);
        ConsultarCuenta parameters = new ConsultarCuenta();
        
        parameters.setBanco("072");
        parameters.setGenero("M");
        parameters.setNroDocumento(Integer.toString(16940691));
        parameters.setTipoDocumento("DNI");
        
        ConsultaCuentaResult response = adhesionBilleteraDao.consultarCuenta(parameters);
        Assert.assertNotNull(response);
        Assert.assertEquals("S", response.getExisteUsuario().getValue());
    }
}