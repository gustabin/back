package ar.com.santanderrio.obp.servicios.segmento;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.segmento.DesencriptadorTest.DesencriptadoTestConfiguration;

/**
 * The Class DesencriptadorTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SecurityProviderConfig.class,
        DesencriptadoTestConfiguration.class })
@TestPropertySource(properties = { "APP.ENCODING = UTF-8" })
public class DesencriptadorTest {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DesencriptadorTest.class);

    /** The sign. */
    @Autowired
    private Sign sign;

    /** The app encoding. */
    @Value("${APP.ENCODING}")
    private String appEncoding;

    /**
     * The Class GestionWSTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = { Sign.class, KeyStoreHelperImpl.class,
            ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                    KeyStoreFactory.class }))
    public static class DesencriptadoTestConfiguration {

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
                 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.
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
     * Desencriptar test.
     */
    @Test
    public void desencriptarTest() {
//        String valorDesencriptado = "<parametros><nup>10047678</nup></parametros>";
        String valorDesencriptado = "OBP|2000|A282591|A282591|||MILANDO|MOLINA RIVERA|N|T|I|null|19700420|CONSTANCIO PERCY|20215314198|00021531419|00276937||";
//    	  String encriptado = "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";
        String encriptado = 
        		"MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCA\r\n" + 
        		"JIAEeE9CUHwyMDAwfEEyODI1OTF8QTI4MjU5MXx8fE1JTEFORE98TU9MSU5BIFJJ\r\n" + 
        		"VkVSQXxOfFR8SXxudWxsfDE5NzAwNDIwfENPTlNUQU5DSU8gUEVSQ1l8MjAyMTUz\r\n" + 
        		"MTQxOTh8MDAwMjE1MzE0MTl8MDAyNzY5Mzd8fAAAAAAAAKCAMIIIgTCCB2mgAwIB\r\n" + 
        		"AgITGwAANoyF1RssBiQw0QABAAA2jDANBgkqhkiG9w0BAQsFADCCARUxCzAJBgNV\r\n" + 
        		"BAYTAkFSMRQwEgYKCZImiZPyLGQBGRYEYnNjaDESMBAGCgmSJomT8ixkARkWAmFy\r\n" + 
        		"MRMwEQYKCZImiZPyLGQBGRYDcmlvMRUwEwYDVQQIEwxCdWVub3MgQWlyZXMxFTAT\r\n" + 
        		"BgNVBAcTDEJ1ZW5vcyBBaXJlczEhMB8GA1UEChMYQmFuY28gU2FudGFuZGVyIFJp\r\n" + 
        		"byBTLkEuMSQwIgYDVQQLExtTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpb24xIzAh\r\n" + 
        		"BgNVBAMTGlNhbnRhbmRlciBSaW8gQ0EyIFNlcnZpY2VzMSswKQYJKoZIhvcNAQkB\r\n" + 
        		"Fhxwa2lic3Jpb0BzYW50YW5kZXJyaW8uY29tLmFyMB4XDTE5MDgxNjE3Mjk0MVoX\r\n" + 
        		"DTIzMDgxNTE3Mjk0MVowfDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBB\r\n" + 
        		"aXJlczEYMBYGA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRgwFgYDVQQKEw9HUlVQTyBT\r\n" + 
        		"QU5UQU5ERVIxEjAQBgNVBAsTCUFyZ2VudGluYTEOMAwGA1UEAxMFZGVzYTEwggEi\r\n" + 
        		"MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC/g98dJRtX2uUYRuYYKomuhMDS\r\n" + 
        		"5o0pyO4qjn1K1NrN6VU0JZorHENcY6tcKKK6TDcJAeglljqe2Ayd7Q9PDdeMvpj2\r\n" + 
        		"+edWcvc3zuHWubybUQVUbWVe7LGqGZDkws2WacWEWnTEXmBzdARzlU6eKixDC7t6\r\n" + 
        		"pnq5JvTAkmTEvzm6nknik20l7DwFwXw0XL0iXPrdYW+t38pRgLxlipvCIgqGXz8N\r\n" + 
        		"hr9d7O9A/kmBMN5nySq5h4Uh34oRNFarYw8GAhbXq1FGpQRfID/s/SliLsF0uOHo\r\n" + 
        		"2+h0YRbrrOjNbzWTc+VPLT5g7G7aWCHZCh9EsAt4t+dQlDdq+RYakwUxmAjPAgMB\r\n" + 
        		"AAGjggRfMIIEWzAdBgNVHQ4EFgQUQqT0bAyzIE13at2xVAS6hmt2QvUwHwYDVR0j\r\n" + 
        		"BBgwFoAURqolt2D6weNmRgkomc751ZoogRIwggFzBgNVHR8EggFqMIIBZjCCAWKg\r\n" + 
        		"ggFeoIIBWoaBzGxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2Vy\r\n" + 
        		"dmljZXMsQ049c2ljYTJlbTAxLENOPUNEUCxDTj1QdWJsaWMlMjBLZXklMjBTZXJ2\r\n" + 
        		"aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0aW9uLERDPXJpbyxEQz1hcixE\r\n" + 
        		"Qz1ic2NoP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RDbGFz\r\n" + 
        		"cz1jUkxEaXN0cmlidXRpb25Qb2ludIZHaHR0cDovL3BraS5zYW50YW5kZXJyaW8u\r\n" + 
        		"Y29tLmFyL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmyG\r\n" + 
        		"QGh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2NybC9TYW50YW5kZXIlMjBSaW8lMjBD\r\n" + 
        		"QTIlMjBTZXJ2aWNlcy5jcmwwggHdBggrBgEFBQcBAQSCAc8wggHLMIHCBggrBgEF\r\n" + 
        		"BQcwAoaBtWxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2Vydmlj\r\n" + 
        		"ZXMsQ049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2Vz\r\n" + 
        		"LENOPUNvbmZpZ3VyYXRpb24sREM9cmlvLERDPWFyLERDPWJzY2g/Y0FDZXJ0aWZp\r\n" + 
        		"Y2F0ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwbAYI\r\n" + 
        		"KwYBBQUHMAKGYGh0dHA6Ly9wa2kuc2FudGFuZGVycmlvLmNvbS5hci9haWEvc2lj\r\n" + 
        		"YTJlbTAxLnJpby5hci5ic2NoX1NhbnRhbmRlciUyMFJpbyUyMENBMiUyMFNlcnZp\r\n" + 
        		"Y2VzKDEpLmNydDAvBggrBgEFBQcwAYYjaHR0cDovL3BraS5zYW50YW5kZXJyaW8u\r\n" + 
        		"Y29tLmFyL29jc3AwZQYIKwYBBQUHMAKGWWh0dHA6Ly9wa2kyLnJpby5hci5ic2No\r\n" + 
        		"L2FpYS9zaWNhMmVtMDEucmlvLmFyLmJzY2hfU2FudGFuZGVyJTIwUmlvJTIwQ0Ey\r\n" + 
        		"JTIwU2VydmljZXMoMSkuY3J0MAsGA1UdDwQEAwIFoDA8BgkrBgEEAYI3FQcELzAt\r\n" + 
        		"BiUrBgEEAYI3FQiF5vlFgYSHOYHdkzWBxYptg/qPeHuKwzmErpcyAgFkAgEKMBMG\r\n" + 
        		"A1UdJQQMMAoGCCsGAQUFBwMCMBsGCSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIw\r\n" + 
        		"RAYJKoZIhvcNAQkPBDcwNTAOBggqhkiG9w0DAgICAIAwDgYIKoZIhvcNAwQCAgCA\r\n" + 
        		"MAcGBSsOAwIHMAoGCCqGSIb3DQMHMA0GCSqGSIb3DQEBCwUAA4IBAQAgxulQuWry\r\n" + 
        		"lCv0CKAKlMYlzoC3xUbQ1KJsYeAZhib6xO6BJh1Lt1g03IkdpMY1HhSKrkNQbQce\r\n" + 
        		"aaO+2rotD3Hrw3Zx5mFoHO6fd4hk6Oc6RaVtEl1nMO6yvYH3221ficXyTHsYXQix\r\n" + 
        		"XGOQZbA0XkGB203CSN4q/jUNJ7yKX/fKSfrgbQg96JCiYsWCPMcPWuRjTVNxsFx8\r\n" + 
        		"RX80jfdqQm55iyIlE8n3QapI76YHnIpbZSIu4HR765L/tLHK+TLpUjmHgQyrRwFy\r\n" + 
        		"8DuXU1VNB0ac0ZzH5vc1v/rK9/Np2MfPEaA62Kjvu3q1VjJzm17S+KP60+ppapbe\r\n" + 
        		"0BA5FwibvJFUAAAxggLiMIIC3gIBATCCAS4wggEVMQswCQYDVQQGEwJBUjEUMBIG\r\n" + 
        		"CgmSJomT8ixkARkWBGJzY2gxEjAQBgoJkiaJk/IsZAEZFgJhcjETMBEGCgmSJomT\r\n" + 
        		"8ixkARkWA3JpbzEVMBMGA1UECBMMQnVlbm9zIEFpcmVzMRUwEwYDVQQHEwxCdWVu\r\n" + 
        		"b3MgQWlyZXMxITAfBgNVBAoTGEJhbmNvIFNhbnRhbmRlciBSaW8gUy5BLjEkMCIG\r\n" + 
        		"A1UECxMbU2VndXJpZGFkIGRlIGxhIEluZm9ybWFjaW9uMSMwIQYDVQQDExpTYW50\r\n" + 
        		"YW5kZXIgUmlvIENBMiBTZXJ2aWNlczErMCkGCSqGSIb3DQEJARYccGtpYnNyaW9A\r\n" + 
        		"c2FudGFuZGVycmlvLmNvbS5hcgITGwAANoyF1RssBiQw0QABAAA2jDAJBgUrDgMC\r\n" + 
        		"GgUAoIGIMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8X\r\n" + 
        		"DTIwMTIxMTE4NTA1OFowIwYJKoZIhvcNAQkEMRYEFNzu7Y8rmWZPyUi4UxZswrU8\r\n" + 
        		"r9oKMCkGCSqGSIb3DQEJNDEcMBowCQYFKw4DAhoFAKENBgkqhkiG9w0BAQEFADAN\r\n" + 
        		"BgkqhkiG9w0BAQEFAASCAQA4zKbFaXbuurN29uR8fK+Z4LBmWcOQDv2cV38rlpXo\r\n" + 
        		"rc7owk6X1wa39S5p8TOrcC0d8ll3Zt1APOU9AZe1beSqjlWQjM7u3IZrZIzFkMyN\r\n" + 
        		"5KP37HDd+YJxn5hmWTI6XPabfMprPsVh4uGLh1s29cz1IwPZqaOG2SCO0nbWAg2I\r\n" + 
        		"zcp+W7uUB11cH6rWmmMk/C7d/SXQe2qu96amhtkRfhejMEUd300vwu2zNXMDw93L\r\n" + 
        		"RhTrO5cs83ka86I3U11uXMR1Z4d2pZAQrUx01M+6SmPCmZg893dy9t55Po3zHmpf\r\n" + 
        		"0+yigAPYyXLoreMQ0iaNOq/pDTjC5gwEz7UuXf2skOJ6AAAAAAAA\r\n";
//        String desencriptado = sign.desencriptadorPKCS7Incluido(encriptado, "OBE", false);
      String desencriptado = sign.desencriptadorPKCS7Incluido(encriptado, "JKS", true);
        LOGGER.info("Valor desencriptado\r\n {}\r\n", desencriptado);
        Assert.assertEquals(desencriptado, valorDesencriptado);
    }

    @Test
    public void encriptarTest() throws UnsupportedEncodingException, DAOException {
//        String valorPorEncriptar = "<datos><ListaDeCuentas><Cuenta><TipoCta>02</TipoCta><CodigoAplicacion>INFI</CodigoAplicacion><CodigoPaquete>2101</CodigoPaquete><DepositoEfectivo>00000000000000</DepositoEfectivo></Cuenta><Cuenta><TipoCta>00</TipoCta><CodigoAplicacion>ACTE</CodigoAplicacion><CodigoPaquete>5150</CodigoPaquete><DepositoEfectivo>00000000000000</DepositoEfectivo></Cuenta><Cuenta><TipoCta>02</TipoCta><CodigoAplicacion>INFI</CodigoAplicacion><CodigoPaquete>2110</CodigoPaquete><DepositoEfectivo>00000000000000</DepositoEfectivo></Cuenta><Cuenta><TipoCta>04</TipoCta><CodigoAplicacion>ACAD</CodigoAplicacion><CodigoPaquete>0000</CodigoPaquete><DepositoEfectivo>00000000000000</DepositoEfectivo></Cuenta><Cuenta><TipoCta>05</TipoCta><CodigoAplicacion>ABAE</CodigoAplicacion><CodigoPaquete>0000</CodigoPaquete><DepositoEfectivo>00000000000000</DepositoEfectivo></Cuenta><Cuenta><TipoCta>77</TipoCta><CodigoAplicacion>AVIS</CodigoAplicacion><CodigoPaquete>0000</CodigoPaquete><DepositoEfectivo>00000000000100</DepositoEfectivo></Cuenta><Cuenta><TipoCta>07</TipoCta><CodigoAplicacion>AVIS</CodigoAplicacion><CodigoPaquete>0000</CodigoPaquete><DepositoEfectivo>00000001054800</DepositoEfectivo></Cuenta><Cuenta><TipoCta>77</TipoCta><CodigoAplicacion>AVIS</CodigoAplicacion><CodigoPaquete>0000</CodigoPaquete><DepositoEfectivo>00000000000100</DepositoEfectivo></Cuenta></ListaDeCuentas><ListaDeProductos><Producto><NumeroProducto>1</NumeroProducto><ListaDeMensajes/></Producto><Producto><NumeroProducto>15</NumeroProducto><ListaDeMensajes/></Producto><Producto><NumeroProducto>6</NumeroProducto><ListaDeMensajes/></Producto></ListaDeProductos></datos>";
    	String valorPorEncriptar = "OBP|2000|A282591|A282591|||MILANDO|MOLINA RIVERA|N|T|I|null|19700420|CONSTANCIO PERCY|20215314198|00021531419|00276937||";
//        byte[] contenido = sign.buildB64Signature(valorPorEncriptar.getBytes(appEncoding), "JKS", true);
    	String contenido = sign.buildPemSignature(valorPorEncriptar.getBytes(appEncoding), "JKS", true);
        Assert.assertNotNull(contenido);

        LOGGER.info("Valor desencriptado\r\n {}\r\n", valorPorEncriptar);
//        LOGGER.info("Valor encriptado\r\n {}\r\n", new String(contenido, "UTF_8"));
        LOGGER.info("Valor encriptado\r\n {}\r\n", contenido);
        Assert.assertNotNull(contenido);
    }

}
