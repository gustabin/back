package ar.com.santanderrio.obp.servicios.inversiones.licitaciones.ws;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.util.Assert;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.inversiones.licitaciones.ws.LicitacionesWSIT.LicitacionesWSITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosWSDAOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConfirmarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConfirmarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovable;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovableResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConfirmarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarTenenciaRenovable;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosDownloadArchivo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCanalTramo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerSaldoCuentasCustodia;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosReversarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosSimularOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DownloadArchivo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DownloadArchivoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodia;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SimularOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SimularOrdenResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { LicitacionesWSITConfiguration.class,
        TitulosWSDAOImpl.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "BEL.POOL.ACTIVO=false",
        "BEL.URL=http://webbmgdesa01.rio.ar.bsch:9084/LicitacionesCanalService.svc", "BEL.TIMEOUT=10000",
        "BEL.POOL.SIZE=30", "BEL.POOL.MAXWAIT=5000", "TAGS.A.ELIMINAR.XML=passwordRacf", "APP.ENCODING = UTF-8",
        "BEL.PALABRAS.SENSIBLES.OUT=passwordRacf" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class LicitacionesWSIT {

    /** The mya DAO. */
    @Autowired
    private TitulosDAO licitacionesDAO;

    @Autowired
    private Sign sign;

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
    @ComponentScan(basePackageClasses = { Sign.class, LicitacionesWSImpl.class, KeyStoreHelperImpl.class,
            ContextHolder.class }, excludeFilters = {
                    @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = KeyStoreFactory.class),
                    @Filter(type = FilterType.REGEX, pattern = "ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.*") })
    public static class LicitacionesWSITConfiguration {

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
     * Gets the estado cliente.
     *
     * @return the estado cliente
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerCuentasTitulos() {

        ObtenerCuentasTitulos request = new ObtenerCuentasTitulos();

        request.setDato("BEL");
        String firma = "";
        try {
            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
        } catch (DAOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        DatosObtenerCuentasTitulos datos = new DatosObtenerCuentasTitulos();
        datos.setSegmento("BC");
        datos.setCanal("4");
        datos.setSubcanal("99");
        datos.setUsuario("00AAAA13");
        datos.setIp("180.166.10.0");
        datos.setNup("00000013");
        request.setDatos(datos);

        ObtenerCuentasTitulosResponse rta = null;
        try {
            rta = licitacionesDAO.obtenerCuentasTitulos(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        Assert.notNull(rta);
    }

    @Test
    public void confirmarOrden() {

        ConfirmarOrden request = new ConfirmarOrden();

        request.setDato("BEL");
        String firma = "MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCAJIAEBlBydWViYQAAAAAAAKCAMIIIgTCCB2mgAwIBAgITGwAANoyF1RssBiQw0QABAAA2jDANBgkqhkiG9w0BAQsFADCCARUxCzAJBgNVBAYTAkFSMRQwEgYKCZImiZPyLGQBGRYEYnNjaDESMBAGCgmSJomT8ixkARkWAmFyMRMwEQYKCZImiZPyLGQBGRYDcmlvMRUwEwYDVQQIEwxCdWVub3MgQWlyZXMxFTATBgNVBAcTDEJ1ZW5vcyBBaXJlczEhMB8GA1UEChMYQmFuY28gU2FudGFuZGVyIFJpbyBTLkEuMSQwIgYDVQQLExtTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpb24xIzAhBgNVBAMTGlNhbnRhbmRlciBSaW8gQ0EyIFNlcnZpY2VzMSswKQYJKoZIhvcNAQkBFhxwa2lic3Jpb0BzYW50YW5kZXJyaW8uY29tLmFyMB4XDTE5MDgxNjE3Mjk0MVoXDTIzMDgxNTE3Mjk0MVowfDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBBaXJlczEYMBYGA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRgwFgYDVQQKEw9HUlVQTyBTQU5UQU5ERVIxEjAQBgNVBAsTCUFyZ2VudGluYTEOMAwGA1UEAxMFZGVzYTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC/g98dJRtX2uUYRuYYKomuhMDS5o0pyO4qjn1K1NrN6VU0JZorHENcY6tcKKK6TDcJAeglljqe2Ayd7Q9PDdeMvpj2+edWcvc3zuHWubybUQVUbWVe7LGqGZDkws2WacWEWnTEXmBzdARzlU6eKixDC7t6pnq5JvTAkmTEvzm6nknik20l7DwFwXw0XL0iXPrdYW+t38pRgLxlipvCIgqGXz8Nhr9d7O9A/kmBMN5nySq5h4Uh34oRNFarYw8GAhbXq1FGpQRfID/s/SliLsF0uOHo2+h0YRbrrOjNbzWTc+VPLT5g7G7aWCHZCh9EsAt4t+dQlDdq+RYakwUxmAjPAgMBAAGjggRfMIIEWzAdBgNVHQ4EFgQUQqT0bAyzIE13at2xVAS6hmt2QvUwHwYDVR0jBBgwFoAURqolt2D6weNmRgkomc751ZoogRIwggFzBgNVHR8EggFqMIIBZjCCAWKgggFeoIIBWoaBzGxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049c2ljYTJlbTAxLENOPUNEUCxDTj1QdWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0aW9uLERDPXJpbyxEQz1hcixEQz1ic2NoP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludIZHaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmyGQGh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmwwggHdBggrBgEFBQcBAQSCAc8wggHLMIHCBggrBgEFBQcwAoaBtWxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENOPUNvbmZpZ3VyYXRpb24sREM9cmlvLERDPWFyLERDPWJzY2g/Y0FDZXJ0aWZpY2F0ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwbAYIKwYBBQUHMAKGYGh0dHA6Ly9wa2kuc2FudGFuZGVycmlvLmNvbS5hci9haWEvc2ljYTJlbTAxLnJpby5hci5ic2NoX1NhbnRhbmRlciUyMFJpbyUyMENBMiUyMFNlcnZpY2VzKDEpLmNydDAvBggrBgEFBQcwAYYjaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL29jc3AwZQYIKwYBBQUHMAKGWWh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2FpYS9zaWNhMmVtMDEucmlvLmFyLmJzY2hfU2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMoMSkuY3J0MAsGA1UdDwQEAwIFoDA8BgkrBgEEAYI3FQcELzAtBiUrBgEEAYI3FQiF5vlFgYSHOYHdkzWBxYptg/qPeHuKwzmErpcyAgFkAgEKMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsGCSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwRAYJKoZIhvcNAQkPBDcwNTAOBggqhkiG9w0DAgICAIAwDgYIKoZIhvcNAwQCAgCAMAcGBSsOAwIHMAoGCCqGSIb3DQMHMA0GCSqGSIb3DQEBCwUAA4IBAQAgxulQuWrylCv0CKAKlMYlzoC3xUbQ1KJsYeAZhib6xO6BJh1Lt1g03IkdpMY1HhSKrkNQbQceaaO+2rotD3Hrw3Zx5mFoHO6fd4hk6Oc6RaVtEl1nMO6yvYH3221ficXyTHsYXQixXGOQZbA0XkGB203CSN4q/jUNJ7yKX/fKSfrgbQg96JCiYsWCPMcPWuRjTVNxsFx8RX80jfdqQm55iyIlE8n3QapI76YHnIpbZSIu4HR765L/tLHK+TLpUjmHgQyrRwFy8DuXU1VNB0ac0ZzH5vc1v/rK9/Np2MfPEaA62Kjvu3q1VjJzm17S+KP60+ppapbe0BA5FwibvJFUAAAxggLiMIIC3gIBATCCAS4wggEVMQswCQYDVQQGEwJBUjEUMBIGCgmSJomT8ixkARkWBGJzY2gxEjAQBgoJkiaJk/IsZAEZFgJhcjETMBEGCgmSJomT8ixkARkWA3JpbzEVMBMGA1UECBMMQnVlbm9zIEFpcmVzMRUwEwYDVQQHEwxCdWVub3MgQWlyZXMxITAfBgNVBAoTGEJhbmNvIFNhbnRhbmRlciBSaW8gUy5BLjEkMCIGA1UECxMbU2VndXJpZGFkIGRlIGxhIEluZm9ybWFjaW9uMSMwIQYDVQQDExpTYW50YW5kZXIgUmlvIENBMiBTZXJ2aWNlczErMCkGCSqGSIb3DQEJARYccGtpYnNyaW9Ac2FudGFuZGVycmlvLmNvbS5hcgITGwAANoyF1RssBiQw0QABAAA2jDAJBgUrDgMCGgUAoIGIMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8XDTE5MTAyODEyMzQzMlowIwYJKoZIhvcNAQkEMRYEFNlqYaxZ88Pe8aHgWW7Vp9Du9g07MCkGCSqGSIb3DQEJNDEcMBowCQYFKw4DAhoFAKENBgkqhkiG9w0BAQEFADANBgkqhkiG9w0BAQEFAASCAQCxphZvxPcmqMSc+psUIjujrDBhM3xEdM3SpuqXuLRuULB2Fc3dMm14ToGuuOFAeR9V57WXxRJIG+9rDxoTQLf45HZjcxElRTakJPLqma2xUH7mJ8uqkPaBDbM40iEmArCQ4bEt3HIMxo49bbtIaFnRDz2FCklv8V/PuZq+it5WAHXQ8mxGR+bICcG5vMojtmM58u7Ahg5QMb63JGZAkR1eQKy2f+G7SLtB85p76r6lglPWMHYTgcM1VMcQAQdL0XARSyK1xWpwcvcGiwQy9jukQnMr+fgUQT4OOyHGt8ipSA8OIatGUzJKOH3clDzKXG2MpIsNx3PXdeQSMr+jZuCVAAAAAAAA";
//        try {
//            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
//        } catch (DAOException e1) {
//            e1.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        DatosConfirmarOrden datos = new DatosConfirmarOrden();
        datos.setMontoADeb(133064.4);
        datos.setNumOrden(123438);
        datos.setUsuarioRacf("10AIBA47");
        datos.setPasswordRacf("@@6HXE7X");
        datos.setCuentaOper(4031682);
        datos.setTipoCuentaOper("10");
        datos.setCanal("4");
        datos.setSubcanal("99");
        datos.setUsuario("01FRQF31");
        datos.setIp("180.166.16.134");
        request.setDatos(datos);

        ConfirmarOrdenResponse rta = null;
        try {
            rta = licitacionesDAO.confirmarOrden(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        Assert.notNull(rta);
    }

    @Test
    public void obtenerCanalTramo() {

        ObtenerCanalTramo request = new ObtenerCanalTramo();

        request.setDato("BEL");
        String firma = "MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCAJIAEBlBydWViYQAAAAAAAKCAMIIIgTCCB2mgAwIBAgITGwAANoyF1RssBiQw0QABAAA2jDANBgkqhkiG9w0BAQsFADCCARUxCzAJBgNVBAYTAkFSMRQwEgYKCZImiZPyLGQBGRYEYnNjaDESMBAGCgmSJomT8ixkARkWAmFyMRMwEQYKCZImiZPyLGQBGRYDcmlvMRUwEwYDVQQIEwxCdWVub3MgQWlyZXMxFTATBgNVBAcTDEJ1ZW5vcyBBaXJlczEhMB8GA1UEChMYQmFuY28gU2FudGFuZGVyIFJpbyBTLkEuMSQwIgYDVQQLExtTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpb24xIzAhBgNVBAMTGlNhbnRhbmRlciBSaW8gQ0EyIFNlcnZpY2VzMSswKQYJKoZIhvcNAQkBFhxwa2lic3Jpb0BzYW50YW5kZXJyaW8uY29tLmFyMB4XDTE5MDgxNjE3Mjk0MVoXDTIzMDgxNTE3Mjk0MVowfDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBBaXJlczEYMBYGA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRgwFgYDVQQKEw9HUlVQTyBTQU5UQU5ERVIxEjAQBgNVBAsTCUFyZ2VudGluYTEOMAwGA1UEAxMFZGVzYTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC/g98dJRtX2uUYRuYYKomuhMDS5o0pyO4qjn1K1NrN6VU0JZorHENcY6tcKKK6TDcJAeglljqe2Ayd7Q9PDdeMvpj2+edWcvc3zuHWubybUQVUbWVe7LGqGZDkws2WacWEWnTEXmBzdARzlU6eKixDC7t6pnq5JvTAkmTEvzm6nknik20l7DwFwXw0XL0iXPrdYW+t38pRgLxlipvCIgqGXz8Nhr9d7O9A/kmBMN5nySq5h4Uh34oRNFarYw8GAhbXq1FGpQRfID/s/SliLsF0uOHo2+h0YRbrrOjNbzWTc+VPLT5g7G7aWCHZCh9EsAt4t+dQlDdq+RYakwUxmAjPAgMBAAGjggRfMIIEWzAdBgNVHQ4EFgQUQqT0bAyzIE13at2xVAS6hmt2QvUwHwYDVR0jBBgwFoAURqolt2D6weNmRgkomc751ZoogRIwggFzBgNVHR8EggFqMIIBZjCCAWKgggFeoIIBWoaBzGxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049c2ljYTJlbTAxLENOPUNEUCxDTj1QdWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0aW9uLERDPXJpbyxEQz1hcixEQz1ic2NoP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludIZHaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmyGQGh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmwwggHdBggrBgEFBQcBAQSCAc8wggHLMIHCBggrBgEFBQcwAoaBtWxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENOPUNvbmZpZ3VyYXRpb24sREM9cmlvLERDPWFyLERDPWJzY2g/Y0FDZXJ0aWZpY2F0ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwbAYIKwYBBQUHMAKGYGh0dHA6Ly9wa2kuc2FudGFuZGVycmlvLmNvbS5hci9haWEvc2ljYTJlbTAxLnJpby5hci5ic2NoX1NhbnRhbmRlciUyMFJpbyUyMENBMiUyMFNlcnZpY2VzKDEpLmNydDAvBggrBgEFBQcwAYYjaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL29jc3AwZQYIKwYBBQUHMAKGWWh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2FpYS9zaWNhMmVtMDEucmlvLmFyLmJzY2hfU2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMoMSkuY3J0MAsGA1UdDwQEAwIFoDA8BgkrBgEEAYI3FQcELzAtBiUrBgEEAYI3FQiF5vlFgYSHOYHdkzWBxYptg/qPeHuKwzmErpcyAgFkAgEKMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsGCSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwRAYJKoZIhvcNAQkPBDcwNTAOBggqhkiG9w0DAgICAIAwDgYIKoZIhvcNAwQCAgCAMAcGBSsOAwIHMAoGCCqGSIb3DQMHMA0GCSqGSIb3DQEBCwUAA4IBAQAgxulQuWrylCv0CKAKlMYlzoC3xUbQ1KJsYeAZhib6xO6BJh1Lt1g03IkdpMY1HhSKrkNQbQceaaO+2rotD3Hrw3Zx5mFoHO6fd4hk6Oc6RaVtEl1nMO6yvYH3221ficXyTHsYXQixXGOQZbA0XkGB203CSN4q/jUNJ7yKX/fKSfrgbQg96JCiYsWCPMcPWuRjTVNxsFx8RX80jfdqQm55iyIlE8n3QapI76YHnIpbZSIu4HR765L/tLHK+TLpUjmHgQyrRwFy8DuXU1VNB0ac0ZzH5vc1v/rK9/Np2MfPEaA62Kjvu3q1VjJzm17S+KP60+ppapbe0BA5FwibvJFUAAAxggLiMIIC3gIBATCCAS4wggEVMQswCQYDVQQGEwJBUjEUMBIGCgmSJomT8ixkARkWBGJzY2gxEjAQBgoJkiaJk/IsZAEZFgJhcjETMBEGCgmSJomT8ixkARkWA3JpbzEVMBMGA1UECBMMQnVlbm9zIEFpcmVzMRUwEwYDVQQHEwxCdWVub3MgQWlyZXMxITAfBgNVBAoTGEJhbmNvIFNhbnRhbmRlciBSaW8gUy5BLjEkMCIGA1UECxMbU2VndXJpZGFkIGRlIGxhIEluZm9ybWFjaW9uMSMwIQYDVQQDExpTYW50YW5kZXIgUmlvIENBMiBTZXJ2aWNlczErMCkGCSqGSIb3DQEJARYccGtpYnNyaW9Ac2FudGFuZGVycmlvLmNvbS5hcgITGwAANoyF1RssBiQw0QABAAA2jDAJBgUrDgMCGgUAoIGIMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8XDTE5MTAyODEyMzQzMlowIwYJKoZIhvcNAQkEMRYEFNlqYaxZ88Pe8aHgWW7Vp9Du9g07MCkGCSqGSIb3DQEJNDEcMBowCQYFKw4DAhoFAKENBgkqhkiG9w0BAQEFADANBgkqhkiG9w0BAQEFAASCAQCxphZvxPcmqMSc+psUIjujrDBhM3xEdM3SpuqXuLRuULB2Fc3dMm14ToGuuOFAeR9V57WXxRJIG+9rDxoTQLf45HZjcxElRTakJPLqma2xUH7mJ8uqkPaBDbM40iEmArCQ4bEt3HIMxo49bbtIaFnRDz2FCklv8V/PuZq+it5WAHXQ8mxGR+bICcG5vMojtmM58u7Ahg5QMb63JGZAkR1eQKy2f+G7SLtB85p76r6lglPWMHYTgcM1VMcQAQdL0XARSyK1xWpwcvcGiwQy9jukQnMr+fgUQT4OOyHGt8ipSA8OIatGUzJKOH3clDzKXG2MpIsNx3PXdeQSMr+jZuCVAAAAAAAA";
//        try {
//            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
//        } catch (DAOException e1) {
//            e1.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        DatosObtenerCanalTramo datos = new DatosObtenerCanalTramo();
        datos.setTipoPliego("LICITACIONES");
        datos.setCanal("4");
        datos.setSubcanal("99");
        // datos.setUsuario("00AAAA13");
        // datos.setNup("00000013");
        datos.setUsuario("01NLQS42");
        datos.setNup("01316842");
        datos.setIp("180.166.10.0");
        request.setDatos(datos);

        ObtenerCanalTramoResponse rta = null;
        try {
            rta = licitacionesDAO.obtenerLicitaciones(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        Assert.notNull(rta);
    }

    @Test
    public void simularOrden() {

        SimularOrden request = new SimularOrden();

        request.setDato("BEL");
        String firma = "MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCAJIAEBlBydWViYQAAAAAAAKCAMIIIgTCCB2mgAwIBAgITGwAANoyF1RssBiQw0QABAAA2jDANBgkqhkiG9w0BAQsFADCCARUxCzAJBgNVBAYTAkFSMRQwEgYKCZImiZPyLGQBGRYEYnNjaDESMBAGCgmSJomT8ixkARkWAmFyMRMwEQYKCZImiZPyLGQBGRYDcmlvMRUwEwYDVQQIEwxCdWVub3MgQWlyZXMxFTATBgNVBAcTDEJ1ZW5vcyBBaXJlczEhMB8GA1UEChMYQmFuY28gU2FudGFuZGVyIFJpbyBTLkEuMSQwIgYDVQQLExtTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpb24xIzAhBgNVBAMTGlNhbnRhbmRlciBSaW8gQ0EyIFNlcnZpY2VzMSswKQYJKoZIhvcNAQkBFhxwa2lic3Jpb0BzYW50YW5kZXJyaW8uY29tLmFyMB4XDTE5MDgxNjE3Mjk0MVoXDTIzMDgxNTE3Mjk0MVowfDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBBaXJlczEYMBYGA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRgwFgYDVQQKEw9HUlVQTyBTQU5UQU5ERVIxEjAQBgNVBAsTCUFyZ2VudGluYTEOMAwGA1UEAxMFZGVzYTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC/g98dJRtX2uUYRuYYKomuhMDS5o0pyO4qjn1K1NrN6VU0JZorHENcY6tcKKK6TDcJAeglljqe2Ayd7Q9PDdeMvpj2+edWcvc3zuHWubybUQVUbWVe7LGqGZDkws2WacWEWnTEXmBzdARzlU6eKixDC7t6pnq5JvTAkmTEvzm6nknik20l7DwFwXw0XL0iXPrdYW+t38pRgLxlipvCIgqGXz8Nhr9d7O9A/kmBMN5nySq5h4Uh34oRNFarYw8GAhbXq1FGpQRfID/s/SliLsF0uOHo2+h0YRbrrOjNbzWTc+VPLT5g7G7aWCHZCh9EsAt4t+dQlDdq+RYakwUxmAjPAgMBAAGjggRfMIIEWzAdBgNVHQ4EFgQUQqT0bAyzIE13at2xVAS6hmt2QvUwHwYDVR0jBBgwFoAURqolt2D6weNmRgkomc751ZoogRIwggFzBgNVHR8EggFqMIIBZjCCAWKgggFeoIIBWoaBzGxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049c2ljYTJlbTAxLENOPUNEUCxDTj1QdWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0aW9uLERDPXJpbyxEQz1hcixEQz1ic2NoP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludIZHaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmyGQGh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmwwggHdBggrBgEFBQcBAQSCAc8wggHLMIHCBggrBgEFBQcwAoaBtWxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENOPUNvbmZpZ3VyYXRpb24sREM9cmlvLERDPWFyLERDPWJzY2g/Y0FDZXJ0aWZpY2F0ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwbAYIKwYBBQUHMAKGYGh0dHA6Ly9wa2kuc2FudGFuZGVycmlvLmNvbS5hci9haWEvc2ljYTJlbTAxLnJpby5hci5ic2NoX1NhbnRhbmRlciUyMFJpbyUyMENBMiUyMFNlcnZpY2VzKDEpLmNydDAvBggrBgEFBQcwAYYjaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL29jc3AwZQYIKwYBBQUHMAKGWWh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2FpYS9zaWNhMmVtMDEucmlvLmFyLmJzY2hfU2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMoMSkuY3J0MAsGA1UdDwQEAwIFoDA8BgkrBgEEAYI3FQcELzAtBiUrBgEEAYI3FQiF5vlFgYSHOYHdkzWBxYptg/qPeHuKwzmErpcyAgFkAgEKMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsGCSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwRAYJKoZIhvcNAQkPBDcwNTAOBggqhkiG9w0DAgICAIAwDgYIKoZIhvcNAwQCAgCAMAcGBSsOAwIHMAoGCCqGSIb3DQMHMA0GCSqGSIb3DQEBCwUAA4IBAQAgxulQuWrylCv0CKAKlMYlzoC3xUbQ1KJsYeAZhib6xO6BJh1Lt1g03IkdpMY1HhSKrkNQbQceaaO+2rotD3Hrw3Zx5mFoHO6fd4hk6Oc6RaVtEl1nMO6yvYH3221ficXyTHsYXQixXGOQZbA0XkGB203CSN4q/jUNJ7yKX/fKSfrgbQg96JCiYsWCPMcPWuRjTVNxsFx8RX80jfdqQm55iyIlE8n3QapI76YHnIpbZSIu4HR765L/tLHK+TLpUjmHgQyrRwFy8DuXU1VNB0ac0ZzH5vc1v/rK9/Np2MfPEaA62Kjvu3q1VjJzm17S+KP60+ppapbe0BA5FwibvJFUAAAxggLiMIIC3gIBATCCAS4wggEVMQswCQYDVQQGEwJBUjEUMBIGCgmSJomT8ixkARkWBGJzY2gxEjAQBgoJkiaJk/IsZAEZFgJhcjETMBEGCgmSJomT8ixkARkWA3JpbzEVMBMGA1UECBMMQnVlbm9zIEFpcmVzMRUwEwYDVQQHEwxCdWVub3MgQWlyZXMxITAfBgNVBAoTGEJhbmNvIFNhbnRhbmRlciBSaW8gUy5BLjEkMCIGA1UECxMbU2VndXJpZGFkIGRlIGxhIEluZm9ybWFjaW9uMSMwIQYDVQQDExpTYW50YW5kZXIgUmlvIENBMiBTZXJ2aWNlczErMCkGCSqGSIb3DQEJARYccGtpYnNyaW9Ac2FudGFuZGVycmlvLmNvbS5hcgITGwAANoyF1RssBiQw0QABAAA2jDAJBgUrDgMCGgUAoIGIMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8XDTE5MTAyODEyMzQzMlowIwYJKoZIhvcNAQkEMRYEFNlqYaxZ88Pe8aHgWW7Vp9Du9g07MCkGCSqGSIb3DQEJNDEcMBowCQYFKw4DAhoFAKENBgkqhkiG9w0BAQEFADANBgkqhkiG9w0BAQEFAASCAQCxphZvxPcmqMSc+psUIjujrDBhM3xEdM3SpuqXuLRuULB2Fc3dMm14ToGuuOFAeR9V57WXxRJIG+9rDxoTQLf45HZjcxElRTakJPLqma2xUH7mJ8uqkPaBDbM40iEmArCQ4bEt3HIMxo49bbtIaFnRDz2FCklv8V/PuZq+it5WAHXQ8mxGR+bICcG5vMojtmM58u7Ahg5QMb63JGZAkR1eQKy2f+G7SLtB85p76r6lglPWMHYTgcM1VMcQAQdL0XARSyK1xWpwcvcGiwQy9jukQnMr+fgUQT4OOyHGt8ipSA8OIatGUzJKOH3clDzKXG2MpIsNx3PXdeQSMr+jZuCVAAAAAAAA";
//        try {
//            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
//        } catch (DAOException e1) {
//            e1.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        DatosSimularOrden datos = new DatosSimularOrden();
        datos.setCodTramoCanal(5050);
        datos.setCodPliego(1405);
        datos.setCodTramo(1831);
        datos.setCanal("4");
        datos.setSubcanal("99");
        datos.setSegmento("RTL");
        datos.setTipoCuenta("O");
        datos.setSucursal("000");
        datos.setTipoCuentaOper("2");
        datos.setCuentaOper("4031682");
        datos.setCuentaTit("7584572");
        datos.setNup("01576531");
        datos.setEspecie("2538");
        datos.setMoneda("USD");
        datos.setCantidad("2000");
        datos.setPrecio("0");
        datos.setFechaOrden(new Date());
        datos.setUsuarioRacf("00EFIG29");
        datos.setPasswordRacf("00EFIG29");
        datos.setRenovacion("S");
        datos.setEspecieRenovacion("15202");
        datos.setLugarRenovacion(new Short("0"));
        datos.setUsuario("00EFIG29");
        datos.setIp("180.166.46.59");
        datos.setTipoEjecucion("0");
        datos.setCorreoElect("asdasd@asd.com");
        request.setDatos(datos);

        SimularOrdenResponse rta = null;
        try {
            rta = licitacionesDAO.simularOrden(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        Assert.notNull(rta);
    }

    @Test
    public void consultarOrden() {
        ConsultarOrdenLicitacion request = new ConsultarOrdenLicitacion();

        request.setDato("BEL");
        String firma = "MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCAJIAEBlBydWViYQAAAAAAAKCAMIIIgTCCB2mgAwIBAgITGwAANoyF1RssBiQw0QABAAA2jDANBgkqhkiG9w0BAQsFADCCARUxCzAJBgNVBAYTAkFSMRQwEgYKCZImiZPyLGQBGRYEYnNjaDESMBAGCgmSJomT8ixkARkWAmFyMRMwEQYKCZImiZPyLGQBGRYDcmlvMRUwEwYDVQQIEwxCdWVub3MgQWlyZXMxFTATBgNVBAcTDEJ1ZW5vcyBBaXJlczEhMB8GA1UEChMYQmFuY28gU2FudGFuZGVyIFJpbyBTLkEuMSQwIgYDVQQLExtTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpb24xIzAhBgNVBAMTGlNhbnRhbmRlciBSaW8gQ0EyIFNlcnZpY2VzMSswKQYJKoZIhvcNAQkBFhxwa2lic3Jpb0BzYW50YW5kZXJyaW8uY29tLmFyMB4XDTE5MDgxNjE3Mjk0MVoXDTIzMDgxNTE3Mjk0MVowfDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBBaXJlczEYMBYGA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRgwFgYDVQQKEw9HUlVQTyBTQU5UQU5ERVIxEjAQBgNVBAsTCUFyZ2VudGluYTEOMAwGA1UEAxMFZGVzYTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC/g98dJRtX2uUYRuYYKomuhMDS5o0pyO4qjn1K1NrN6VU0JZorHENcY6tcKKK6TDcJAeglljqe2Ayd7Q9PDdeMvpj2+edWcvc3zuHWubybUQVUbWVe7LGqGZDkws2WacWEWnTEXmBzdARzlU6eKixDC7t6pnq5JvTAkmTEvzm6nknik20l7DwFwXw0XL0iXPrdYW+t38pRgLxlipvCIgqGXz8Nhr9d7O9A/kmBMN5nySq5h4Uh34oRNFarYw8GAhbXq1FGpQRfID/s/SliLsF0uOHo2+h0YRbrrOjNbzWTc+VPLT5g7G7aWCHZCh9EsAt4t+dQlDdq+RYakwUxmAjPAgMBAAGjggRfMIIEWzAdBgNVHQ4EFgQUQqT0bAyzIE13at2xVAS6hmt2QvUwHwYDVR0jBBgwFoAURqolt2D6weNmRgkomc751ZoogRIwggFzBgNVHR8EggFqMIIBZjCCAWKgggFeoIIBWoaBzGxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049c2ljYTJlbTAxLENOPUNEUCxDTj1QdWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0aW9uLERDPXJpbyxEQz1hcixEQz1ic2NoP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludIZHaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmyGQGh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmwwggHdBggrBgEFBQcBAQSCAc8wggHLMIHCBggrBgEFBQcwAoaBtWxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENOPUNvbmZpZ3VyYXRpb24sREM9cmlvLERDPWFyLERDPWJzY2g/Y0FDZXJ0aWZpY2F0ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwbAYIKwYBBQUHMAKGYGh0dHA6Ly9wa2kuc2FudGFuZGVycmlvLmNvbS5hci9haWEvc2ljYTJlbTAxLnJpby5hci5ic2NoX1NhbnRhbmRlciUyMFJpbyUyMENBMiUyMFNlcnZpY2VzKDEpLmNydDAvBggrBgEFBQcwAYYjaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL29jc3AwZQYIKwYBBQUHMAKGWWh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2FpYS9zaWNhMmVtMDEucmlvLmFyLmJzY2hfU2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMoMSkuY3J0MAsGA1UdDwQEAwIFoDA8BgkrBgEEAYI3FQcELzAtBiUrBgEEAYI3FQiF5vlFgYSHOYHdkzWBxYptg/qPeHuKwzmErpcyAgFkAgEKMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsGCSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwRAYJKoZIhvcNAQkPBDcwNTAOBggqhkiG9w0DAgICAIAwDgYIKoZIhvcNAwQCAgCAMAcGBSsOAwIHMAoGCCqGSIb3DQMHMA0GCSqGSIb3DQEBCwUAA4IBAQAgxulQuWrylCv0CKAKlMYlzoC3xUbQ1KJsYeAZhib6xO6BJh1Lt1g03IkdpMY1HhSKrkNQbQceaaO+2rotD3Hrw3Zx5mFoHO6fd4hk6Oc6RaVtEl1nMO6yvYH3221ficXyTHsYXQixXGOQZbA0XkGB203CSN4q/jUNJ7yKX/fKSfrgbQg96JCiYsWCPMcPWuRjTVNxsFx8RX80jfdqQm55iyIlE8n3QapI76YHnIpbZSIu4HR765L/tLHK+TLpUjmHgQyrRwFy8DuXU1VNB0ac0ZzH5vc1v/rK9/Np2MfPEaA62Kjvu3q1VjJzm17S+KP60+ppapbe0BA5FwibvJFUAAAxggLiMIIC3gIBATCCAS4wggEVMQswCQYDVQQGEwJBUjEUMBIGCgmSJomT8ixkARkWBGJzY2gxEjAQBgoJkiaJk/IsZAEZFgJhcjETMBEGCgmSJomT8ixkARkWA3JpbzEVMBMGA1UECBMMQnVlbm9zIEFpcmVzMRUwEwYDVQQHEwxCdWVub3MgQWlyZXMxITAfBgNVBAoTGEJhbmNvIFNhbnRhbmRlciBSaW8gUy5BLjEkMCIGA1UECxMbU2VndXJpZGFkIGRlIGxhIEluZm9ybWFjaW9uMSMwIQYDVQQDExpTYW50YW5kZXIgUmlvIENBMiBTZXJ2aWNlczErMCkGCSqGSIb3DQEJARYccGtpYnNyaW9Ac2FudGFuZGVycmlvLmNvbS5hcgITGwAANoyF1RssBiQw0QABAAA2jDAJBgUrDgMCGgUAoIGIMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8XDTE5MTAyODEyMzQzMlowIwYJKoZIhvcNAQkEMRYEFNlqYaxZ88Pe8aHgWW7Vp9Du9g07MCkGCSqGSIb3DQEJNDEcMBowCQYFKw4DAhoFAKENBgkqhkiG9w0BAQEFADANBgkqhkiG9w0BAQEFAASCAQCxphZvxPcmqMSc+psUIjujrDBhM3xEdM3SpuqXuLRuULB2Fc3dMm14ToGuuOFAeR9V57WXxRJIG+9rDxoTQLf45HZjcxElRTakJPLqma2xUH7mJ8uqkPaBDbM40iEmArCQ4bEt3HIMxo49bbtIaFnRDz2FCklv8V/PuZq+it5WAHXQ8mxGR+bICcG5vMojtmM58u7Ahg5QMb63JGZAkR1eQKy2f+G7SLtB85p76r6lglPWMHYTgcM1VMcQAQdL0XARSyK1xWpwcvcGiwQy9jukQnMr+fgUQT4OOyHGt8ipSA8OIatGUzJKOH3clDzKXG2MpIsNx3PXdeQSMr+jZuCVAAAAAAAA";
//        try {
//            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
//        } catch (DAOException e1) {
//            e1.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        DatosConsultarLicitacion datos = new DatosConsultarLicitacion();
        datos.setTipoPliego("LICITACIONES");
        datos.setCanal("4");
        datos.setSubcanal("99");
        datos.setUsuario("00AAAA13");
        datos.setIp("180.166.10.0");
        datos.setNup("01576531");
        request.setDatos(datos);

        ConsultarOrdenResponse rta = null;
        try {
            rta = licitacionesDAO.consultarOrdenLicitacion(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        Assert.notNull(rta);

    }

    @Test
    public void obtenerSaldoCuentasCustodia() {

        ObtenerSaldoCuentasCustodia request = new ObtenerSaldoCuentasCustodia();

        request.setDato("BEL");
        String firma = "";
        try {
            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
        } catch (DAOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        DatosObtenerSaldoCuentasCustodia datos = new DatosObtenerSaldoCuentasCustodia();
        datos.setMoneda("ARS");
        datos.setSegmento("BC");
        datos.setCanal("4");
        datos.setSubcanal("99");
        datos.setUsuario("00AAAA13");
        datos.setIp("180.166.10.0");
        datos.setNup("00000013");
        request.setDatos(datos);

        ObtenerSaldoCuentasCustodiaResponse rta = null;
        try {
            rta = licitacionesDAO.obtenerSaldoCuentasCustodia(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        Assert.notNull(rta);

    }

    @Test
    public void ConsultarTenenciaRenovable() {

        ConsultarTenenciaRenovable request = new ConsultarTenenciaRenovable();

        request.setDato("BEL");
        String firma = "MIAGCSqGSIb3DQEHAqCAMIACAQExCzAJBgUrDgMCGgUAMIAGCSqGSIb3DQEHAaCAJIAEBlBydWViYQAAAAAAAKCAMIIIgTCCB2mgAwIBAgITGwAANoyF1RssBiQw0QABAAA2jDANBgkqhkiG9w0BAQsFADCCARUxCzAJBgNVBAYTAkFSMRQwEgYKCZImiZPyLGQBGRYEYnNjaDESMBAGCgmSJomT8ixkARkWAmFyMRMwEQYKCZImiZPyLGQBGRYDcmlvMRUwEwYDVQQIEwxCdWVub3MgQWlyZXMxFTATBgNVBAcTDEJ1ZW5vcyBBaXJlczEhMB8GA1UEChMYQmFuY28gU2FudGFuZGVyIFJpbyBTLkEuMSQwIgYDVQQLExtTZWd1cmlkYWQgZGUgbGEgSW5mb3JtYWNpb24xIzAhBgNVBAMTGlNhbnRhbmRlciBSaW8gQ0EyIFNlcnZpY2VzMSswKQYJKoZIhvcNAQkBFhxwa2lic3Jpb0BzYW50YW5kZXJyaW8uY29tLmFyMB4XDTE5MDgxNjE3Mjk0MVoXDTIzMDgxNTE3Mjk0MVowfDELMAkGA1UEBhMCQVIxFTATBgNVBAgTDEJ1ZW5vcyBBaXJlczEYMBYGA1UEBxMPQ2FwaXRhbCBGZWRlcmFsMRgwFgYDVQQKEw9HUlVQTyBTQU5UQU5ERVIxEjAQBgNVBAsTCUFyZ2VudGluYTEOMAwGA1UEAxMFZGVzYTEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC/g98dJRtX2uUYRuYYKomuhMDS5o0pyO4qjn1K1NrN6VU0JZorHENcY6tcKKK6TDcJAeglljqe2Ayd7Q9PDdeMvpj2+edWcvc3zuHWubybUQVUbWVe7LGqGZDkws2WacWEWnTEXmBzdARzlU6eKixDC7t6pnq5JvTAkmTEvzm6nknik20l7DwFwXw0XL0iXPrdYW+t38pRgLxlipvCIgqGXz8Nhr9d7O9A/kmBMN5nySq5h4Uh34oRNFarYw8GAhbXq1FGpQRfID/s/SliLsF0uOHo2+h0YRbrrOjNbzWTc+VPLT5g7G7aWCHZCh9EsAt4t+dQlDdq+RYakwUxmAjPAgMBAAGjggRfMIIEWzAdBgNVHQ4EFgQUQqT0bAyzIE13at2xVAS6hmt2QvUwHwYDVR0jBBgwFoAURqolt2D6weNmRgkomc751ZoogRIwggFzBgNVHR8EggFqMIIBZjCCAWKgggFeoIIBWoaBzGxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049c2ljYTJlbTAxLENOPUNEUCxDTj1QdWJsaWMlMjBLZXklMjBTZXJ2aWNlcyxDTj1TZXJ2aWNlcyxDTj1Db25maWd1cmF0aW9uLERDPXJpbyxEQz1hcixEQz1ic2NoP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RDbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludIZHaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmyGQGh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2NybC9TYW50YW5kZXIlMjBSaW8lMjBDQTIlMjBTZXJ2aWNlcy5jcmwwggHdBggrBgEFBQcBAQSCAc8wggHLMIHCBggrBgEFBQcwAoaBtWxkYXA6Ly8vQ049U2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMsQ049QUlBLENOPVB1YmxpYyUyMEtleSUyMFNlcnZpY2VzLENOPVNlcnZpY2VzLENOPUNvbmZpZ3VyYXRpb24sREM9cmlvLERDPWFyLERDPWJzY2g/Y0FDZXJ0aWZpY2F0ZT9iYXNlP29iamVjdENsYXNzPWNlcnRpZmljYXRpb25BdXRob3JpdHkwbAYIKwYBBQUHMAKGYGh0dHA6Ly9wa2kuc2FudGFuZGVycmlvLmNvbS5hci9haWEvc2ljYTJlbTAxLnJpby5hci5ic2NoX1NhbnRhbmRlciUyMFJpbyUyMENBMiUyMFNlcnZpY2VzKDEpLmNydDAvBggrBgEFBQcwAYYjaHR0cDovL3BraS5zYW50YW5kZXJyaW8uY29tLmFyL29jc3AwZQYIKwYBBQUHMAKGWWh0dHA6Ly9wa2kyLnJpby5hci5ic2NoL2FpYS9zaWNhMmVtMDEucmlvLmFyLmJzY2hfU2FudGFuZGVyJTIwUmlvJTIwQ0EyJTIwU2VydmljZXMoMSkuY3J0MAsGA1UdDwQEAwIFoDA8BgkrBgEEAYI3FQcELzAtBiUrBgEEAYI3FQiF5vlFgYSHOYHdkzWBxYptg/qPeHuKwzmErpcyAgFkAgEKMBMGA1UdJQQMMAoGCCsGAQUFBwMCMBsGCSsGAQQBgjcVCgQOMAwwCgYIKwYBBQUHAwIwRAYJKoZIhvcNAQkPBDcwNTAOBggqhkiG9w0DAgICAIAwDgYIKoZIhvcNAwQCAgCAMAcGBSsOAwIHMAoGCCqGSIb3DQMHMA0GCSqGSIb3DQEBCwUAA4IBAQAgxulQuWrylCv0CKAKlMYlzoC3xUbQ1KJsYeAZhib6xO6BJh1Lt1g03IkdpMY1HhSKrkNQbQceaaO+2rotD3Hrw3Zx5mFoHO6fd4hk6Oc6RaVtEl1nMO6yvYH3221ficXyTHsYXQixXGOQZbA0XkGB203CSN4q/jUNJ7yKX/fKSfrgbQg96JCiYsWCPMcPWuRjTVNxsFx8RX80jfdqQm55iyIlE8n3QapI76YHnIpbZSIu4HR765L/tLHK+TLpUjmHgQyrRwFy8DuXU1VNB0ac0ZzH5vc1v/rK9/Np2MfPEaA62Kjvu3q1VjJzm17S+KP60+ppapbe0BA5FwibvJFUAAAxggLiMIIC3gIBATCCAS4wggEVMQswCQYDVQQGEwJBUjEUMBIGCgmSJomT8ixkARkWBGJzY2gxEjAQBgoJkiaJk/IsZAEZFgJhcjETMBEGCgmSJomT8ixkARkWA3JpbzEVMBMGA1UECBMMQnVlbm9zIEFpcmVzMRUwEwYDVQQHEwxCdWVub3MgQWlyZXMxITAfBgNVBAoTGEJhbmNvIFNhbnRhbmRlciBSaW8gUy5BLjEkMCIGA1UECxMbU2VndXJpZGFkIGRlIGxhIEluZm9ybWFjaW9uMSMwIQYDVQQDExpTYW50YW5kZXIgUmlvIENBMiBTZXJ2aWNlczErMCkGCSqGSIb3DQEJARYccGtpYnNyaW9Ac2FudGFuZGVycmlvLmNvbS5hcgITGwAANoyF1RssBiQw0QABAAA2jDAJBgUrDgMCGgUAoIGIMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEwHAYJKoZIhvcNAQkFMQ8XDTE5MTAyODEyMzQzMlowIwYJKoZIhvcNAQkEMRYEFNlqYaxZ88Pe8aHgWW7Vp9Du9g07MCkGCSqGSIb3DQEJNDEcMBowCQYFKw4DAhoFAKENBgkqhkiG9w0BAQEFADANBgkqhkiG9w0BAQEFAASCAQCxphZvxPcmqMSc+psUIjujrDBhM3xEdM3SpuqXuLRuULB2Fc3dMm14ToGuuOFAeR9V57WXxRJIG+9rDxoTQLf45HZjcxElRTakJPLqma2xUH7mJ8uqkPaBDbM40iEmArCQ4bEt3HIMxo49bbtIaFnRDz2FCklv8V/PuZq+it5WAHXQ8mxGR+bICcG5vMojtmM58u7Ahg5QMb63JGZAkR1eQKy2f+G7SLtB85p76r6lglPWMHYTgcM1VMcQAQdL0XARSyK1xWpwcvcGiwQy9jukQnMr+fgUQT4OOyHGt8ipSA8OIatGUzJKOH3clDzKXG2MpIsNx3PXdeQSMr+jZuCVAAAAAAAA";
//        try {
//            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
//        } catch (DAOException e1) {
//            e1.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        DatosConsultarTenenciaRenovable datos = new DatosConsultarTenenciaRenovable();
        datos.setCanal("4");
        datos.setSubcanal("99");
        // datos.setUsuario("00AAJG14");
        // datos.setIp("180.166.10.0");
        datos.setIp("180.166.16.144");
//        datos.setSegmento("BC");
        // datos.setNup("00009614");

        // datos.setUsuario("01NLQS42");
        datos.setUsuario("pablo.d.gargaglione");
        datos.setNup("00152766");
        datos.setCuentaTitulo("2382720");
        datos.setMoneda("USD");
        datos.setEspecie("2538");
        datos.setTipoEjecucion("0");

        request.setDatos(datos);

        ConsultarTenenciaRenovableResponse rta = null;
        try {
            rta = licitacionesDAO.consultarTenenciaRenovable(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        Assert.notNull(rta);
    }

    @Test
    public void reversarOrden() {

        DatosReversarOrden datos = new DatosReversarOrden();
        datos.setNumOrden(2432);
        datos.setUsuarioRacf("10AIBA47");
        datos.setPasswordRacf("@@6HXE7X");
        datos.setCanal("4");
        datos.setSubcanal("99");
        datos.setUsuario("10AIBA47");
        datos.setIp("180.166.10.0");

        ReversarOrdenEntity request = new ReversarOrdenEntity(datos);

        request.setDato("BEL");
        String firma = "";
        try {
            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
        } catch (DAOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        ReversarOrdenResponse rta = null;
        try {
            rta = licitacionesDAO.reversarOrdenLicitacion(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        Assert.notNull(rta);
    }

    @Test
    @Ignore
    public void downloadArchivoTest() throws IOException {

        DatosDownloadArchivo datos = new DatosDownloadArchivo();
        datos.setNombreArchivo("PL010677  Licitacion 13-6.pdf");
        datos.setRepositorio("PLIEGOS");

        DownloadArchivo request = new DownloadArchivo();
        request.setDatos(datos);

        request.setDato("BEL");
        String firma = "";
        try {
            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
        } catch (DAOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        request.setFirma(firma);
        request.setDatosFirmado("0");
        request.setTipoHash("0");

        DownloadArchivoResponse rta = null;
        try {
            rta = licitacionesDAO.downloadArchivo(request);
        } catch (DAOException e) {
            System.out.println("error");
        }
        String archivo = rta.getDatos().getArchivoBase64();
        FileOutputStream fos = new FileOutputStream("c:/archivo.pdf");
        fos.write(archivo.getBytes());
        fos.flush();
        fos.close();
        Assert.notNull(rta);
    }

}