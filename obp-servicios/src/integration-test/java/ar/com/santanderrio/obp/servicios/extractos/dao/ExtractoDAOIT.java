/**
 * 
 */
package ar.com.santanderrio.obp.servicios.extractos.dao;

import org.junit.Before;
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
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.extracto.dao.ExtractoDAO;
import ar.com.santanderrio.obp.servicios.extracto.dao.ExtractoDAOImpl;
import ar.com.santanderrio.obp.servicios.extracto.dao.GestionarExtractoWSImpl;
import ar.com.santanderrio.obp.servicios.extractos.dao.ExtractoDAOIT.ExtractoDAOITConfiguration;

/**
 * The Class ExtractoDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ExtractoDAOITConfiguration.class,
        SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "WSEXTRACTOS.POOL.ACTIVO=false",
        "WSEXTRACTOS.URL=http://webhomo3.rio.ar.bsch:8050/ws_extractos.svc",
        "WSEXTRACTOS.TIMEOUT=100", 
        "WSEXTRACTOS.POOL.SIZE=30", 
        "WSEXTRACTOS.POOL.MAXWAIT=50", 
        "APP.ENCODING = UTF-8",
        "KEYSTORE.WSEXTRACTOS.IDSEGURIDAD=20010",
        "KEYSTORE.WSEXTRACTOS.PATH=",
        "KEYSTORE.WSEXTRACTOS.TYPE=JKS"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExtractoDAOIT {
    
    @Autowired
    private ExtractoDAO extractoDAO;

    @Value("${KEYSTORE.WSEXTRACTOS.IDSEGURIDAD}")
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
    @ComponentScan(basePackageClasses = { ExtractoDAOImpl.class, GestionarExtractoWSImpl.class,
            Sign.class, KeyStoreHelperImpl.class,
            ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                    KeyStoreFactory.class }))
    public static class ExtractoDAOITConfiguration {

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
                            Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
                    keyStore.setKeystoreAlias("obp");
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

//    @Test
//    public void callExtractosWS() throws DAOException, ParseException, DatatypeConfigurationException {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        String ctaTITULOS = null;
//        String codFONDO = null;
//        GregorianCalendar gcd = new GregorianCalendar();
//        gcd.setTime(sdf.parse("31/10/2017"));
//        XMLGregorianCalendar desde = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcd);
//        GregorianCalendar gch = new GregorianCalendar();
//        gch.setTime(sdf.parse("31/10/2017"));
//        XMLGregorianCalendar hasta = DatatypeFactory.newInstance().newXMLGregorianCalendar(gch);
//        String nup = "32990";
//        MovFondosResponse response = extractoDAO.consultaMovimientosOly(ctaTITULOS, codFONDO, desde, hasta, nup);
//
//        Assert.assertNotNull(response);
//    }

}
