/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.ondemand.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.comun.ondemand.utils.OnDemandClientTestIT.OnDemandClientTestITConfiguration;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ResumenMensualTarjetaDTO;

/**
 * The Class OnDemandClientTest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { OnDemandClientTestITConfiguration.class, RestWebClientImpl.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.ONDEMAND.CONN.SECURITY.ID=1",
        "ONDEMAND.ENDPOINT.URL=http://desawas.ar.bsch:9156/OnDemandWS/SOAPService",
        "ONDEMAND.ENDPOINT.TIMEOUT = 5",
        "ONDEMAND.ENDPOINT_ADVANCE.URL=http://wsod.ar.bsch:10500/wsod/SOAPService",
        "ONDEMAND.ENDPOINT_ADVANCE.TIMEOUT = 5",
        "RESUMENONDEMANDTRJ.URL=http://wsod.ar.bsch:10500/wsod/SOAPService",
        "RESUMENONDEMANDTRJ.TIMEOUT = 5",
        "RESUMENONDEMANDTRJ.MESES=18",
         "RESUMENONDEMAND.MESES=18" })
public class OnDemandClientTestIT {

    /** The ondemand DAO. */
    @InjectMocks
    @Autowired
    private OndemandDAO ondemandDAO;

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class OnDemandClientTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.ondemand.dao",
            "ar.com.santanderrio.obp.servicios.comun.ondemand.mapper",
            "ar.com.santanderrio.obp.servicios.comun.ondemand.utils" })
    public static class OnDemandClientTestITConfiguration {

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
     * Probar on demand.
     *
     * @throws SQLException
     *             the SQL exception
     * @throws WSODException
     *             the WSOD exception
     * @throws OnDemandDAOException
     *             the on demand DAO exception
     */
    @Test
    public void probarOnDemand() throws SQLException, WSODException, OnDemandDAOException {
        Credential credential = new Credential();
        credential.setUsuario("odtest");
        credential.setPassword("prueba2006");

        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
        ResumenParams params = new ResumenParams();

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("42728401");
        cuenta.setNroSucursal("0");

        params.setFechaDesde("14/03/15");
        params.setFechaHasta("19/09/16");
        params.setPaquete("P");
        params.setUsuarioConsulta("01576531");
        params.setProveedorTarjeta("VISA");
        params.setCuenta(cuenta);

        List<ResumenMensualTarjetaDTO> resultado = ondemandDAO.obtenerListaResumenesAnterioresTarjeta(params);
        Assert.assertNotNull(resultado);
        Assert.assertEquals("Se esperan 18 resumenes", 18, resultado.size());
    }
    
    
    /**
     * Prueba Obtener Marca de Impresion
     * 
     * @throws SQLException
     * @throws WSODException
     * @throws OnDemandDAOException
     */
    @Test
    public void probarMarcaImpresion() throws SQLException, WSODException, OnDemandDAOException {
        Credential credential = new Credential();
        credential.setUsuario("odtest");
        credential.setPassword("prueba2006");

        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("000000600439");
        cuenta.setNroSucursal("041");
        cuenta.setCodigoAplicacion("ACTE");

        ResumenParams params = new ResumenParams();

        params.setCuenta(cuenta);
        params.setUsuarioConsulta("00276937");

        String codAplicacion = ((Cuenta) cuenta).getCodigoAplicacion();
        params.setFiltroAplicacion("INFI".equals(codAplicacion) ? "ACTE" : codAplicacion);

        String marca = ondemandDAO.obtenerMarcaImpresion(params);
        if (StringUtils.isEmpty(marca))
            Assert.fail();
    }

    /**
     * Prueba modificar Marca de Impresion
     * 
     * @throws SQLException
     * @throws WSODException
     * @throws OnDemandDAOException
     */
    @Test
    public void probarCambiarMarcaImpresion() throws SQLException, WSODException, OnDemandDAOException {
        Credential credential = new Credential();
        credential.setUsuario("odtest");
        credential.setPassword("prueba2006");

        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("000000600439");
        cuenta.setNroSucursal("041");

        ResumenParams params = new ResumenParams();

        params.setFiltroAplicacion("ACTE");
        params.setCuenta(cuenta);
        params.setUsuarioConsulta("00276937");
        params.setMotivoConsulta("Porque me gusta archivar mis resumenes fisicos");
        params.setSoporte("P");
        params.setMoneda("ARS");

        ondemandDAO.modificarMarcaImpresion(params);
    }
}