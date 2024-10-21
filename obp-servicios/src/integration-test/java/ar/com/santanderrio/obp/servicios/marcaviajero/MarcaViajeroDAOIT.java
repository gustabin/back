/**
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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

import com.ibm.icu.util.Calendar;

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
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ConfirmarViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EchoViajeResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EliminarViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerPaisesResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Pais;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Viaje;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Viaje.Paises;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Viaje.Tarjetas;
import ar.com.santanderrio.obp.servicios.marcaviajero.MarcaViajeroDAOIT.MarcaViajeroDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.marcaviajero.dao.GestionarMarcaViajero;
import ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO;
import ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAOImpl;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroException;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroNoExisteViajeException;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;

/**
 * The Class AliasCbuDAOIT.
 * 
 * <b>ATENCION!!!</b><br/>
 * Correr los test con el siguiente argumento en la virtual machine:<br/>
 * 
 * -Djava.net.useSystemProxies=true
 * 
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { MarcaViajeroDAOITConfiguration.class,
        GestionarMarcaViajero.class, MarcaViajeroDAOImpl.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "APP.ENCODING=UTF-8", 
        "MARCAVIAJERO.JKS=MARCAVIAJERO", "MARCAVIAJERO.TIMEOUT=10000",
        "MARCAVIAJERO.POOL.ACTIVO=true", "MARCAVIAJERO.POOL.MAXWAIT=5000",
        "MARCAVIAJERO.URL=https://ws1.visa.com.ar/visahomews/MarcaViajeroService", "MARCAVIAJERO.POOL.SIZE=5",
        "KEYSTORE.FACTURAELECTRONICA.IDSEGURIDAD=20104", "KEYSTORE.MARCAVIAJERO.IDSEGURIDAD=20104",
        "MARCAVIAJEROCBU.ENCUSER=marcacion_pub", "MARCAVIAJEROCBU.TTL.TIMESTAMP=600",
        "KEYSTORE.MARCAVIAJERO.PATH=/aplicaciones/hb/conf/keyStore/mbkey.jks", "KEYSTORE.MARCAVIAJERO.TYPE=JKS" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MarcaViajeroDAOIT {
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The marcaViajero DAO. */
    @Autowired
    private MarcaViajeroDAO marcaViajeroDAO;

    @Value("${KEYSTORE.MARCAVIAJERO.IDSEGURIDAD}")
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
    @ComponentScan(basePackageClasses = { Sign.class, KeyStoreHelperImpl.class, ContextHolder.class,
            CryptoHelper.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                    KeyStoreFactory.class }))
    public static class MarcaViajeroDAOITConfiguration {

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
                    keyStore.setKeystorePath("/aplicaciones/hb/conf/keyStore/mbkey.jks");
                    keyStore.setKeystoreAlias("marcacion");
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

    /**
     * Probar la configuracion de encriptacion.<br/>
     * Lanzar el test con el siguiente agregado: -Djava.net.useSystemProxies=true
     * 
     * @throws DAOException
     * @throws SQLException
     */
    @Test
    public void echoViaje() throws DAOException, SQLException {
        Credential cred = new Credential();
        cred.setUsuario("marcacion");
        cred.setPassword("hbpassword");
        Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
        EchoViajeResponse echoViaje = marcaViajeroDAO.echoViaje("Test");
        Assert.assertNotNull(echoViaje);
        Assert.assertThat(echoViaje.getMessage(), is(equalTo("Echo: Test")));
    }
    
    
    
    @Test
    public void nuevoViaje() throws DAOException, SQLException, DatatypeConfigurationException {
        ConfirmarViajeRequest confirmarViajeRequest = new ConfirmarViajeRequest();
        confirmarViajeRequest.setEmailSocio("fm@hotmail.com");
        confirmarViajeRequest.setNumeroDocumento("20370751");
        confirmarViajeRequest.setTipoDocumento("DNI");
        confirmarViajeRequest.setSexo("M");
        confirmarViajeRequest.setReintento(false);
        Viaje viaje = new Viaje();
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 12);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(calendar.getTime());
        XMLGregorianCalendar fechaDesde = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        c.setTime(calendar.getTime());
        XMLGregorianCalendar fechaHasta = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

        fechaDesde.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        fechaDesde.setTime(DatatypeConstants.FIELD_UNDEFINED,
        DatatypeConstants.FIELD_UNDEFINED,
        DatatypeConstants.FIELD_UNDEFINED,
        DatatypeConstants.FIELD_UNDEFINED);
        
        fechaHasta.setTimezone( DatatypeConstants.FIELD_UNDEFINED );
        fechaHasta.setTime(DatatypeConstants.FIELD_UNDEFINED,
        DatatypeConstants.FIELD_UNDEFINED,
        DatatypeConstants.FIELD_UNDEFINED,
        DatatypeConstants.FIELD_UNDEFINED);
        
        viaje.setFechaInicio(fechaDesde);
        viaje.setFechaFin(fechaHasta);
        Paises paises = new Paises();
        Pais pais = new Pais();
        pais.setCodPais("BS");
        pais.setDescripcionPais("BAHAMAS");
        List<Pais> paisList = new ArrayList<Pais>();
        paisList.add(pais);
        paises.setPais(paisList);
        viaje.setPaises(paises);

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setApellidoNombre("DIAZ S/MARIA M");
        tarjeta.setCondicion("ADICIONAL");
        tarjeta.setUltimosCuatro("4696");
        Tarjetas tarjetas = new Tarjetas();
        List<Tarjeta> listTarjetas = new ArrayList<Tarjeta>();
        listTarjetas.add(tarjeta);
        tarjetas.setTarjeta(listTarjetas);
        
        viaje.setTarjetas(tarjetas);
        confirmarViajeRequest.setViaje(viaje);
        
        Credential cred = new Credential();
        cred.setUsuario("marcacion");
        cred.setPassword("hbpassword");
        Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
        try {
            marcaViajeroDAO.confirmarViajeWS(confirmarViajeRequest);
        } catch (MarcaViajeroException e) {
            Assert.assertTrue(true);
        } catch (DAOException e) {
            Assert.fail("Falla marcaviajero confirmarviaje");
        }        
    }
    
    
    
    @Test
    public void obtenerPaises() throws DAOException, SQLException, DatatypeConfigurationException {

        Credential cred = new Credential();
        cred.setUsuario("marcacion");
        cred.setPassword("hbpassword");
        Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
        ObtenerPaisesResponse paises = marcaViajeroDAO.obtenerPaises();
        Assert.assertNotNull(paises);
    }
    
    
    @Test
    public void eliminarViaje() throws DAOException, SQLException {
        
        Credential cred = new Credential();
        cred.setUsuario("marcacion");
        cred.setPassword("hbpassword");
        Mockito.when(credentialSecurityFactory.getCredentialById(idSeg)).thenReturn(cred);
        EliminarViajeRequest request = new EliminarViajeRequest();
        request.setNumeroDocumento("4552899");
        request.setTipoDocumento("LE");
        request.setIdentificadorViaje(132850211);
        request.setSexo("M");
        request.setEmailSocio("hgomar@servexternos.isban-santander.com.ar");
        try {
            marcaViajeroDAO.eliminarViaje(request);
        } catch (MarcaViajeroNoExisteViajeException e) {
            Assert.assertTrue(true);
        } catch (DAOException e) {
            Assert.fail("Should not have thrown any exception");
        }
    }

}