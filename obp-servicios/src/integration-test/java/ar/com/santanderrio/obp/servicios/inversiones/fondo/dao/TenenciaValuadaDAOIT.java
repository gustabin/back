package ar.com.santanderrio.obp.servicios.inversiones.fondo.dao;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
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

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.datasource.SystemRoutingDataSource;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.config.executor.ExecutorConfig;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.fondo.dao.TenenciaValuadaDAOIT.TenenciaValuadaDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CarteraTenenciaValuadaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaProductoOnlineEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosDetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosTenenciaValuadaCarteraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleTenenciaValuadaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleTenenciaValuadaTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.Segmento;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TenenciaValuadaCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetalleTenenciaValuadaPFEntity;
import junit.framework.TestCase;


/**
 * 
 * @author marcelo.ruiz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TenenciaValuadaDAOITConfiguration.class,
        SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
      "FONDOS.TENENCIAS.URL=http://webbpsibehomo01.rio.ar.bsch:9060/PLTenenciaValuadaService/",
                            
//        "FONDOS.TENENCIAS.URL=http://localhost:8089/PLTenenciaValuadaServiceRest.svc/",
        "FONDOS.TENENCIAS.TIMEOUT=1000",
        "FONDOS.TENENCIAS.PALABRAS.SENSIBLES.OUT=",
        "APP.ENCODING = UTF-8"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TenenciaValuadaDAOIT {
    @Autowired
    private Sign sign;
    
    @Autowired
    private RestWebClient restWebClient;
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
    @ComponentScan(basePackageClasses = {RestWebClientImpl.class, Sign.class, KeyStoreHelperImpl.class, ContextHolder.class, KeyStoreHelperImpl.class}, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class TenenciaValuadaDAOITConfiguration {
        
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

    
    @Test
    public void obtenerTenenciaValuadaDetalleFondoOnlineRTL() throws DAOException {
        
        DetalleFondoRequestEntity request = new DetalleFondoRequestEntity();
        
        request.setDato("Prueba");
        
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        
        DatosDetalleFondoRequestEntity datos = new DatosDetalleFondoRequestEntity();
        datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
        datos.setNup("50115032");
        datos.setErrores(null);
        datos.setIp("180.166.16.134");
        datos.setUsuario("system");
        CuentaOPEntity cuenta1= new CuentaOPEntity();
        cuenta1.setNroCuentaOP("0000000013557926");
        cuenta1.setSucursal("000");
//        CuentaOPEntity cuenta2= new CuentaOPEntity();
//        cuenta2.setNroCuentaOP("454548");
//        cuenta2.setSucursal("250");
//        datos.getListaCuentas().add(cuenta2);
        datos.getListaCuentas().add(cuenta1);
        request.setDatos(datos);
        
        DatosServiciosEntity datosServicios = new DatosServiciosEntity();
        datosServicios.setCanalTipo("04");
        datosServicios.setCanalId("0099");
        datosServicios.setCanalVersion("03");
        datosServicios.setSubcanalTipo("12");
        datosServicios.setSubcanalId("0009");
        datosServicios.setUsuarioTipo("AA");
        
       
        datosServicios.setUsuarioId("50LLPK32");
        datosServicios.setUsuarioAtrib("AA");
        datosServicios.setUsuarioPwd("@GPEKN2K");
        datosServicios.setTipoPersona("I");
        datosServicios.setTipoId("N");
        datosServicios.setIdCliente("00029063920");
        datosServicios.setFechaNac("19811006");
        datosServicios.setErrores(null);
        datosServicios.setIp(null);
        datosServicios.setUsuario(null);
        
        datos.setDatosServicios(datosServicios);
        
        WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("FONDOS.TENENCIAS");
        tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.path("/ObtenerTenenciaValuadaDetalleFondoOnline/");
        DetalleTenenciaValuadaEntity rta  = new DetalleTenenciaValuadaEntity(); 
        try {
         rta =   tenenciaValuadaService.post(request, DetalleTenenciaValuadaEntity.class);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
        	TestCase.fail();
        }
    }
    
    @Test
    public void obtenerTenenciaValuadaDetallePFOnline() throws DAOException {
        
        DetalleFondoRequestEntity request = new DetalleFondoRequestEntity();
        
        request.setDato("Prueba");
        
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        DatosDetalleFondoRequestEntity datos = new DatosDetalleFondoRequestEntity();
        datos.setSegmento("RTL");
        datos.setNup("50115032");
        datos.setErrores(null);
        datos.setIp("180.166.16.134");
        datos.setUsuario("SYSTEM");
        CuentaOPEntity cuenta1= new CuentaOPEntity();
        cuenta1.setNroCuentaOP("0000000013557926");
        cuenta1.setSucursal("0000");
        datos.getListaCuentas().add(cuenta1);
        request.setDatos(datos);
        
        DatosServiciosEntity datosServicios = new DatosServiciosEntity();
        datosServicios.setCanalTipo("04");
        datosServicios.setCanalId("0099");
        datosServicios.setCanalVersion("03");
        datosServicios.setSubcanalTipo("12");
        datosServicios.setSubcanalId("0009");
        datosServicios.setUsuarioTipo("03");
        
       
        datosServicios.setUsuarioId("50LLPK32");
        datosServicios.setUsuarioAtrib("AA");
        datosServicios.setUsuarioPwd("@GPEKN2K");
        datosServicios.setTipoPersona("I");
        datosServicios.setTipoId("N");
        datosServicios.setIdCliente("00029063920");
        datosServicios.setFechaNac("19811006");
        datosServicios.setErrores(null);
        datosServicios.setIp(null);
        datosServicios.setUsuario(null);
        
        datos.setDatosServicios(datosServicios);
        
        WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("FONDOS.TENENCIAS");
        tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.path("/ObtenerTenenciaValuadaDetallePFOnline/");
        DetalleTenenciaValuadaPFEntity rta  = new DetalleTenenciaValuadaPFEntity(); 
        try {
         rta =   tenenciaValuadaService.post(request, DetalleTenenciaValuadaPFEntity.class);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
        	TestCase.fail();
        }
    }
    
        
    @Test
    public void obtenerTenenciaValuadaCarteraTotalOnline() throws DAOException {
        
      //  helper.getDatosFirmados("hola");
        
      
        
        
        
    	TenenciaValuadaCarteraRequestEntity request = new TenenciaValuadaCarteraRequestEntity();
    	
    	///request.setFirma("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");
    	request.setDato("Prueba");
    	
    	
    	
    	
    	
    	byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
       // return new String(firma);
 
    	
  /*  	String firma = "";
        try {
            firma = inversionWSHelper.getDatosFirmados("Prueba");
        } catch (Exception e) {
          System.out.println(e);;
        }*/
        request.setFirma(new String(firma));
    	
    	
    	
    	
    	
    	DatosTenenciaValuadaCarteraRequest datos = new DatosTenenciaValuadaCarteraRequest();
        datos.setCodigoProducto("TOD");
        datos.setNup("01390639");
        datos.setErrores(null);
        datos.setIp("180.166.16.134");
        datos.setUsuario("system");
        CuentaOPEntity cuenta1= new CuentaOPEntity();
        cuenta1.setNroCuentaOP("0000000009010132");
        cuenta1.setSucursal("0000");
        CuentaOPEntity cuenta2= new CuentaOPEntity();
        cuenta2.setNroCuentaOP("0000000031875860");
        cuenta2.setSucursal("0000");
        CuentaOPEntity cuenta3= new CuentaOPEntity();
        cuenta3.setNroCuentaOP("7003521458");
        cuenta3.setSucursal("0250");
        CuentaOPEntity cuenta4= new CuentaOPEntity();
        cuenta4.setNroCuentaOP("7003527692");
        cuenta4.setSucursal("0250");
        datos.getListaCuentasRTL().add(cuenta1);
        datos.getListaCuentasRTL().add(cuenta2);
        datos.getListaCuentasBP().add(cuenta3);
        datos.getListaCuentasBP().add(cuenta4);
        request.setDatos(datos);
        
        DatosServiciosEntity datosServicios = new DatosServiciosEntity();
        datosServicios.setCanalTipo("04");
        datosServicios.setCanalId("0099");
        datosServicios.setCanalVersion("03");
        datosServicios.setSubcanalTipo("12");
        datosServicios.setSubcanalId("0009");
        datosServicios.setUsuarioTipo("03");
        
       
        datosServicios.setUsuarioId("01NTKQ39");
        datosServicios.setUsuarioAtrib("AA");
        datosServicios.setUsuarioPwd("@BC0R1EJ");
        datosServicios.setTipoPersona("I");
        datosServicios.setTipoId("N");
        datosServicios.setIdCliente("00013304289");
        datosServicios.setFechaNac("19570516");
        datosServicios.setErrores(null);
        datosServicios.setIp(null);
        datosServicios.setUsuario(null);
        
        datos.setDatosServicios(datosServicios);
        

        
        

        
        
        WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("FONDOS.TENENCIAS");
        tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.path("/ObtenerTenenciaValuadaCarteraTotalOnline/");
        
        
        
        CarteraTenenciaValuadaEntity rta  = new CarteraTenenciaValuadaEntity(); 
        try {
         rta =   tenenciaValuadaService.post(request, CarteraTenenciaValuadaEntity.class);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
        	TestCase.fail();
        }
    }
    
    
    @Test
    public void obtenerTenenciaValuadaDetalleFondoOnlineBP() throws DAOException {
        
        DetalleFondoRequestEntity request = new DetalleFondoRequestEntity();
        
        request.setDato("Prueba");
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        DatosDetalleFondoRequestEntity datos = new DatosDetalleFondoRequestEntity();
        datos.setSegmento(Segmento.BANCA_PRIVADA.getCodigo());
        datos.setNup("01576531");
        datos.setErrores(null);
        datos.setIp("180.166.91.82");
        datos.setUsuario("system");
        CuentaOPEntity cuenta1= new CuentaOPEntity();
        cuenta1.setNroCuentaOP("7003523508");
        cuenta1.setSucursal("250");
//        CuentaOPEntity cuenta2= new CuentaOPEntity();
//        cuenta2.setNroCuentaOP("454548");
//        cuenta2.setSucursal("250");
//        datos.getListaCuentas().add(cuenta2);
        datos.getListaCuentas().add(cuenta1);
        request.setDatos(datos);
        
        DatosServiciosEntity datosServicios = new DatosServiciosEntity();
        datosServicios.setCanalTipo("04");
        datosServicios.setCanalId("0099");
        datosServicios.setCanalVersion("03");
        datosServicios.setSubcanalTipo("12");
        datosServicios.setSubcanalId("0009");
        datosServicios.setUsuarioTipo("03");
        
       
        datosServicios.setUsuarioId("01FRQF31");
        datosServicios.setUsuarioAtrib("AA");
        datosServicios.setUsuarioPwd("@DP33YRD");
        datosServicios.setTipoPersona("I");
        datosServicios.setTipoId("N");
        datosServicios.setIdCliente("00020956698");
        datosServicios.setFechaNac("19690922");
        datosServicios.setErrores(null);
        datosServicios.setIp(null);
        datosServicios.setUsuario(null);
        
        datos.setDatosServicios(datosServicios);
        
        WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("FONDOS.TENENCIAS");
        tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.path("/ObtenerTenenciaValuadaDetalleFondoOnline/");
        DetalleTenenciaValuadaEntity rta  = new DetalleTenenciaValuadaEntity(); 
        try {
         rta =   tenenciaValuadaService.post(request, DetalleTenenciaValuadaEntity.class);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
            TestCase.fail();
        }
    }
    
    
    @Test
    public void obtenerTenenciaValuadaCuentaProductoOnline() throws DAOException {
        
        DetalleFondoRequestEntity request = new DetalleFondoRequestEntity();
        
        request.setDato("Prueba");
        
        
        
        
        
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        
        DatosDetalleFondoRequestEntity datos = new DatosDetalleFondoRequestEntity();
        datos.setSegmento("RTL");
        datos.setNup("50115032");
        datos.setErrores(null);
        datos.setIp("180.166.16.134");
        datos.setUsuario("SYSTEM");
        CuentaOPEntity cuenta1= new CuentaOPEntity();
        cuenta1.setNroCuentaOP("0000000013557926");
        cuenta1.setSucursal("000");
        datos.getListaCuentas().add(cuenta1);
        request.setDatos(datos);
        
        DatosServiciosEntity datosServicios = new DatosServiciosEntity();
        datosServicios.setCanalTipo("04");
        datosServicios.setCanalId("0099");
        datosServicios.setCanalVersion("03");
        datosServicios.setSubcanalTipo("12");
        datosServicios.setSubcanalId("0009");
        datosServicios.setUsuarioTipo("03");
        
       
        datosServicios.setUsuarioId("50LLPK32");
        datosServicios.setUsuarioAtrib("AA");
        datosServicios.setUsuarioPwd("@GPEKN2K");
        datosServicios.setTipoPersona("I");
        datosServicios.setTipoId("N");
        datosServicios.setIdCliente("00029063920");
        datosServicios.setFechaNac("19811006");
        datosServicios.setErrores(null);
        datosServicios.setIp("180.166.16.134");
        datosServicios.setUsuario("system");
        
        datos.setDatosServicios(datosServicios);
        
        WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("FONDOS.TENENCIAS");
        tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.path("/ObtenerTenenciaValuadaCuentaProductoOnline/");
        CuentaProductoOnlineEntity rta  = new CuentaProductoOnlineEntity(); 
        try {
         rta =   tenenciaValuadaService.post(request, CuentaProductoOnlineEntity.class);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
            TestCase.fail();
        }
    }
    
    @Test
    public void obtenerTenenciaValuadaDetalleTitulosOnline() throws DAOException {
        
        DetalleFondoRequestEntity request = new DetalleFondoRequestEntity();
        
        request.setDato("Prueba");
        
        byte[] firma;
        try {
            firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA",  true);
        } catch (UnsupportedEncodingException e) {
         
            throw new DAOException(e);
        }
        
        request.setFirma(new String(firma));
        
        DatosDetalleFondoRequestEntity datos = new DatosDetalleFondoRequestEntity();
        datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
        datos.setNup("50115032");
        datos.setErrores(null);
        datos.setIp("180.166.16.134");
        datos.setUsuario("SYSTEM");
        CuentaOPEntity cuenta1= new CuentaOPEntity();
        cuenta1.setNroCuentaOP("0000000013557926");
        cuenta1.setSucursal("000");
//        CuentaOPEntity cuenta2= new CuentaOPEntity();
//        cuenta2.setNroCuentaOP("454548");
//        cuenta2.setSucursal("250");
//        datos.getListaCuentas().add(cuenta2);
        datos.getListaCuentas().add(cuenta1);
        request.setDatos(datos);
        
        DatosServiciosEntity datosServicios = new DatosServiciosEntity();
        datosServicios.setCanalTipo("04");
        datosServicios.setCanalId("0099");
        datosServicios.setCanalVersion("03");
        datosServicios.setSubcanalTipo("12");
        datosServicios.setSubcanalId("0009");
        datosServicios.setUsuarioTipo("AA");
        
       
        datosServicios.setUsuarioId("50LLPK32");
        datosServicios.setUsuarioAtrib("AA");
        datosServicios.setUsuarioPwd("@GPEKN2K");
        datosServicios.setTipoPersona("I");
        datosServicios.setTipoId("N");
        datosServicios.setIdCliente("00029063920");
        datosServicios.setFechaNac("19811006");
        datosServicios.setErrores(null);
        datosServicios.setIp(null);
        datosServicios.setUsuario(null);
        
        datos.setDatosServicios(datosServicios);
        
        WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("FONDOS.TENENCIAS");
        tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
        tenenciaValuadaService.path("/ObtenerTenenciaValuadaDetalleTitulosOnline/");
        DetalleTenenciaValuadaTitulosEntity rta  = new DetalleTenenciaValuadaTitulosEntity(); 
        try {
         rta =   tenenciaValuadaService.post(request, DetalleTenenciaValuadaTitulosEntity.class);
         assertNotNull(rta);
        }catch(ResponseProcessingException e){
        	TestCase.fail();
        }
    }
}
