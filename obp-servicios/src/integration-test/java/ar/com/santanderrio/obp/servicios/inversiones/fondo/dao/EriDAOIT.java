/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondo.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondo.dao.EriDAOIT.EriDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.perfil.ws.EriWSImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CabeceraOrdenesTitulosEntity;

/**
 * The Class MyaDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { EriDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "ERI.POOL.ACTIVO=false", 
//        "ERI.URL=http://webdesafront2.rio.ar.bsch:6480/ERIService.svc",
        "ERI.URL=http://localhost:8091/eriService",
        "ERI.TIMEOUT=10", "ERI.POOL.SIZE=30", "ERI.POOL.MAXWAIT=5",  "APP.ENCODING = UTF-8"  })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class EriDAOIT {

    /** The mya DAO. */
    @Autowired
    @Qualifier("InversionWSDAO")
    private InversionDAO inversionDAO;

    @Autowired
	private InversionWSHelper inversionWSHelper;

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
    @ComponentScan(basePackageClasses = { InversionDAO.class, EriWSImpl.class, Sign.class, KeyStoreHelperImpl.class, ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class EriDAOITConfiguration {
        
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
                    keyStore.setKeystoreAlias("obp");
                    keyStore.setKeystorePassword("hbpassword");
                    return keyStore;
                }

            });
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
     * Gets the estado cliente.
     *
     * @return the estado cliente
     * @throws DAOException the DAO exception
     */
    @Test(expected = DAOException.class)
    public void getEstadoCliente() throws DAOException {
    	DatosEvaluacionRiesgo parametroDatos = new DatosEvaluacionRiesgo();
    	parametroDatos.setCodCanal("OBP");
        parametroDatos.setCanalId("HTML");
        parametroDatos.setCanalTipo("04");
        parametroDatos.setCanalVersion("000");
        parametroDatos.setSubcanalId("0001");
        parametroDatos.setSubcanalTipo("99");
        parametroDatos.setTipoCliente("I");
    	inversionDAO.evaluacionDeRiesgo(parametroDatos);
    }
    
    @Test
    public void consultarPerfilInversor() throws DAOException{
    	Mockito.when(inversionWSHelper.getDatosFirmados(Mockito.anyString())).thenReturn("MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCAJIAEBlBydWViYQAAAAAAAKCAMIIIgTCCB2mgAwIBAgITGwAANoyF1RssBiQw0QABAAA2jDANBgkqhkiG9w0BAQsFADCCARUxCzAJBgNVBAYTAkFSMRQwEgYKCZImiZPyLGQBGRYEYnNjaDESMBAGCgmSJomT8ixkARkWAmFyMRMwEQYKCZImiZPyLGQBGRYDcmlvMRUwEwYDVQQIEwxCdWVub3MgQWlyZXMxFTATBgNVBAcTDEJ1ZW5vcyBBaXJlczEhMB8GA1UEChMYQmFuY28gU2FudGFuZGVyIFJpbyBTLkEuMSQwIgYDVQQLExtTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpb24xIzAhBgNVBAMTGlNhbnRhbmRlciBSaW8gQ0EyIFNlcnZpY2VzMSswKQYJKoZIhvcNAQkBFhxwa2lic3Jpb0BzYW50YW5kZXJyaW8uY29tLmFyMB4XDTE5MDgxNjE3Mjk0MVoXDTIzMDgxNTE3Mjk0MVowfDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBBaXJlczEYMBYGA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRgwFgYDVQQKEw9HUlVQTyBTQU5UQU5ERVIxEjAQBgNVBAsTCUFyZ2VudGluYTEOMAwGA1UEAxMFZGVzYTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC/g98dJRtX2uUYRuYYKomuhMDS5o0pyO4qjn1K1NrN6VU0JZorHENcY6tcKKK6TDcJAeglljqe2Ayd7Q9PDdeMvpj2+edWcvc3zuHWubybUQVUbWVe7LGqGZDkws2WacWEWnTEXmBzdARzlU6eKixDC7t6pnq5JvTAkmTEvzm6nknik20l7DwFwXw0XL0iXPrdYW+t38pRgLxlipvCIgqGXz8Nhr9d7O9A/kmBMN5nySq5h4Uh34oRNFarYw8GAhbXq1FGpQRfID/s/SliLsF0uOHo2+h0YRbrrOjNbzWTc+VPLT5g7G7aWCHZCh9EsAt4t+dQlDdq+RYakwUxmAjPAgMBAAGjggRfMIIEWzAdBgNVHQ4EFgQUQqT0bAyzIE13at2xVAS6hmt2QvUwHwYDVR0jBBgwFoAURqolt2D6weNmRgkomc751ZoogRIwggFzBgNVHR8EggFqMIIBZjCCAWKgggFeoIIBWoaBzGxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049c2ljYTJlbTAxLENOPUNEUCxDTj1QdWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0aW9uLERDPXJpbyxEQz1hcixEQz1ic2NoP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludIZHaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmyGQGh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmwwggHdBggrBgEFBQcBAQSCAc8wggHLMIHCBggrBgEFBQcwAoaBtWxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENOPUNvbmZpZ3VyYXRpb24sREM9cmlvLERDPWFyLERDPWJzY2g/Y0FDZXJ0aWZpY2F0ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwbAYIKwYBBQUHMAKGYGh0dHA6Ly9wa2kuc2FudGFuZGVycmlvLmNvbS5hci9haWEvc2ljYTJlbTAxLnJpby5hci5ic2NoX1NhbnRhbmRlciUyMFJpbyUyMENBMiUyMFNlcnZpY2VzKDEpLmNydDAvBggrBgEFBQcwAYYjaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL29jc3AwZQYIKwYBBQUHMAKGWWh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2FpYS9zaWNhMmVtMDEucmlvLmFyLmJzY2hfU2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMoMSkuY3J0MAsGA1UdDwQEAwIFoDA8BgkrBgEEAYI3FQcELzAtBiUrBgEEAYI3FQiF5vlFgYSHOYHdkzWBxYptg/qPeHuKwzmErpcyAgFkAgEKMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsGCSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwRAYJKoZIhvcNAQkPBDcwNTAOBggqhkiG9w0DAgICAIAwDgYIKoZIhvcNAwQCAgCAMAcGBSsOAwIHMAoGCCqGSIb3DQMHMA0GCSqGSIb3DQEBCwUAA4IBAQAgxulQuWrylCv0CKAKlMYlzoC3xUbQ1KJsYeAZhib6xO6BJh1Lt1g03IkdpMY1HhSKrkNQbQceaaO+2rotD3Hrw3Zx5mFoHO6fd4hk6Oc6RaVtEl1nMO6yvYH3221ficXyTHsYXQixXGOQZbA0XkGB203CSN4q/jUNJ7yKX/fKSfrgbQg96JCiYsWCPMcPWuRjTVNxsFx8RX80jfdqQm55iyIlE8n3QapI76YHnIpbZSIu4HR765L/tLHK+TLpUjmHgQyrRwFy8DuXU1VNB0ac0ZzH5vc1v/rK9/Np2MfPEaA62Kjvu3q1VjJzm17S+KP60+ppapbe0BA5FwibvJFUAAAxggLiMIIC3gIBATCCAS4wggEVMQswCQYDVQQGEwJBUjEUMBIGCgmSJomT8ixkARkWBGJzY2gxEjAQBgoJkiaJk/IsZAEZFgJhcjETMBEGCgmSJomT8ixkARkWA3JpbzEVMBMGA1UECBMMQnVlbm9zIEFpcmVzMRUwEwYDVQQHEwxCdWVub3MgQWlyZXMxITAfBgNVBAoTGEJhbmNvIFNhbnRhbmRlciBSaW8gUy5BLjEkMCIGA1UECxMbU2VndXJpZGFkIGRlIGxhIEluZm9ybWFjaW9uMSMwIQYDVQQDExpTYW50YW5kZXIgUmlvIENBMiBTZXJ2aWNlczErMCkGCSqGSIb3DQEJARYccGtpYnNyaW9Ac2FudGFuZGVycmlvLmNvbS5hcgITGwAANoyF1RssBiQw0QABAAA2jDAJBgUrDgMCGgUAoIGIMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8XDTE5MTIwNDIwMTgxNFowIwYJKoZIhvcNAQkEMRYEFNlqYaxZ88Pe8aHgWW7Vp9Du9g07MCkGCSqGSIb3DQEJNDEcMBowCQYFKw4DAhoFAKENBgkqhkiG9w0BAQEFADANBgkqhkiG9w0BAQEFAASCAQBGjtwpe6sr+MSNjqPNdnbAyyN/C7x7pBf7P6gG2zFgguLCEWaQjHazYmumC6Nvy6ELo6r7OIVKcACzApDVJMX3TyKKwOd0g8d54gnLR9u0bzZcbFP9cv25ZAWySlQVRpf5ZCdtuuGrS6cxVNFDoUdpfeaCmlR5E+KYcc5CpNtkM5Sg3Q10s5XQa554zlYSNGxg+9xScBmQAG+McXZLmOvIC5CN2mTSOGJEbPaMJVKLY8DcmI5nGlObkZ/KQGFN4lSgTlEag29zoqfO0ygF3o5OycyUV/nyqAGtlAoYaEroEmWXVZwJEpTTWqxJ/slf0en/Xdp1c3MRkJNfic2Zz4QAAAAAAAAA");
    	Cliente cliente = new Cliente();
    	cliente.setPasswordRacf("@C3MDXVD\"");
    	
    	PerfilInversorRequestEntity request = new PerfilInversorRequestEntity();
		request.getDatos().setUsuario("00KRSK48");
		request.getDatos().setIp("0:0:0:0:0:0:0:1");
		request.getDatos().setNup("01576531");

		CabeceraOrdenesTitulosEntity encabezado = new CabeceraOrdenesTitulosEntity();
		encabezado = CabeceraOrdenesTitulosEntity.generarCabeceraRequest(cliente); 
			encabezado.setCanalTipo("04");
			encabezado.setSubCanalTipo("00");
			encabezado.setCanalId("0001");
		request.getDatos().setEncabezado(encabezado);

		PerfilInversorResponse perfilInversorResponse = inversionDAO.consultaPerfilInversor(request);
		System.out.println(perfilInversorResponse);
    }
}
