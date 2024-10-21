package ar.com.santanderrio.obp.servicios.inversiones.maps.dao;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.ResponseProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.Segmento;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.MapServiceDAOIT.MapServiceDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsDatosRequest;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.BajaAdhesionMapsDatosRequest;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.BajaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionRequestEntity;
import junit.framework.TestCase;


/**
 * @author B039920
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { MapServiceDAOITConfiguration.class,
        SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
      "INVERSIONES.MAPS.URL=http://webbpsibehomo01:9200/MAPSServiciosMaps/",
//      "INVERSIONES.MAPS.URL=http://webbpsibedesa01:9100/MapsMockService/",
      "INVERSIONES.MAPS.TIMEOUT=10000",
      "INVERSIONES.MAPS.PALABRAS.SENSIBLES.OUT=",
      "APP.ENCODING = UTF-8",
      "INVERSIONES.MAPS.MOCK.FILE=/aplicaciones/hb/conf/obp-properties/obp-config/desa-5/mock/files/mock"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MapServiceDAOIT {
	
	//PARA EJECUTAR ESTE TEST SE DEBERA DESCOMENTAR EL @COMPONENT QUE SE
	//ENCUENTRA EN LA CLASE MAPSERVICEDAOIMPL
	
    @Autowired
    private Sign sign;
    
    @Autowired
    MapServiceDAO mapServiceDAO;
    
    @Mock
    InversionWSHelper inversionWSHelper;
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    /**
     * The Class MapServiceDAOITConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = {MapServiceDAOImpl.class, 
            RestWebClientImpl.class, Sign.class, KeyStoreHelperImpl.class,
            ContextHolder.class, KeyStoreHelperImpl.class}, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class MapServiceDAOITConfiguration {
        
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
        @Bean
        public InversionWSHelper inversionWSHelper() {
            return new InversionWSHelper();
        }
    }

    @Test
    public void altaAdhesionMaps() throws DAOException {
        
    	AltaAdhesionMapsRequestEntity request = new AltaAdhesionMapsRequestEntity();
        
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        request.setDato("Prueba");
        request.setCanal("04");
        request.setSubCanal("0099");
        request.setDatosFirmados(0);
        request.setTipoHash("0");
        
        AltaAdhesionMapsDatosRequest datos = new AltaAdhesionMapsDatosRequest();
        datos.setSegmento(Segmento.BANCA_PRIVADA.getCodigo());
        datos.setNup("01576531");
        datos.setId(null);
        datos.setEstado("consulta");
        request.setDatos(datos);
        
        AltaAdhesionMapsResponse rta  = new AltaAdhesionMapsResponse(); 
        try {
         rta =   mapServiceDAO.altaAdhesionMaps(request);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
        	TestCase.fail();
        }
    }
    @Test
    public void altaAdhesionCargaMaps() throws DAOException {
        
        AltaAdhesionMapsRequestEntity request = new AltaAdhesionMapsRequestEntity();
        
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
            
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        request.setDato("Prueba");
        request.setCanal("04");
        request.setSubCanal("0099");
        request.setDatosFirmados(0);
        request.setTipoHash("0");
        
        AltaAdhesionMapsDatosRequest datos = new AltaAdhesionMapsDatosRequest();
        datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
        datos.setNup("00001312");
        datos.setIdServicio("SAF");
        datos.setTitulo("SAF");
        request.setDatos(datos);
        
        AltaAdhesionMapsResponse rta  = new AltaAdhesionMapsResponse(); 
        try {
            rta =   mapServiceDAO.altaAdhesionMaps(request);
            assertNotNull(rta);
        }catch(ResponseProcessingException e){
            TestCase.fail();
        }
    }
    
    @Test
    public void consultaAdhesionMaps() throws DAOException {
        
    	ConsultaAdhesionRequestEntity request = new ConsultaAdhesionRequestEntity();
        
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        request.setDato("Prueba");
        request.setCanal("04");
        request.setSubCanal("0099");
        request.setDatosFirmados(0);
        request.setTipoHash("0");
        
        AltaAdhesionMapsDatosRequest datos = new AltaAdhesionMapsDatosRequest();
        datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
        datos.setNup("00001312");
        request.setDatos(datos);
        
        ConsultaAdhesionMapsResponse rta  = new ConsultaAdhesionMapsResponse(); 
        try {
         rta =   mapServiceDAO.consultaAdhesionMaps(request);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
        	TestCase.fail();
        }
    }
    
    @Test
    public void bajaAdhesionSimulacionMaps() throws DAOException {
        
    	BajaAdhesionRequestEntity request = new BajaAdhesionRequestEntity();
        
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        request.setDato("Prueba");
        request.setCanal("04");
        request.setSubCanal("0099");
        request.setDatosFirmados(0);
        request.setTipoHash("0");
        
        BajaAdhesionMapsDatosRequest datos = new BajaAdhesionMapsDatosRequest();
        datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
        datos.setNup("00001312");
        datos.setIdAdhesion(1);
        datos.setSegmento("RTL");
        datos.setEstado("simulacion");
        request.setDatos(datos);
        
        ConsultaAdhesionMapsResponse rta  = new ConsultaAdhesionMapsResponse(); 
        try {
         rta =   mapServiceDAO.bajaAdhesionMaps(request);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
        	TestCase.fail();
        }
    }
}