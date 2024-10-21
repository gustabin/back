package ar.com.santanderrio.obp.servicios.citi.dao;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
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

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.datasource.SystemRoutingDataSource;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAOIT.ResumenesCitiDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.citi.entities.FechasResumenCitiIn;
import ar.com.santanderrio.obp.servicios.citi.entities.ResumenFechaOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import junit.framework.TestCase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ResumenesCitiDAOITConfiguration.class})
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
		"RESUMEN.ONDEMAND.TARJETA.CITI.MASTER.URL = http://localhost:8081/olympus-web/olympus-web/api/ondemand/tarjetas/master",
      "RESUMEN.ONDEMAND.CITI.URL = http://wasolympo.ar.bsch:9080/olympus-web/api/ondemand/cuentas",
        "RESUMEN.ONDEMAND.CITI.TIMEOUT=20000",  
        "RESUMENES.CITY.OUT=",
        "APP.ENCODING = UTF-8"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ResumenesCitiDAOIT {
    
    @Autowired
    private ResumenesCitiDAO resumenesCitiDAO;
    
    
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    /**
     * The Class ResumenesCitiDAOITConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = {ResumenesCitiDAO.class, RestWebClientImpl.class,  Sign.class, KeyStoreHelperImpl.class, ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class ResumenesCitiDAOITConfiguration {
        
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
//                    "KEYSTORE.TENENCIA.PATH=",
//                    "KEYSTORE.TENENCIA.IDSEGURIDAD=20001",
                    KeyStore keyStore = new KeyStore();
                    keyStore.setKeystoreType("JKS");
                    keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
                    keyStore.setKeystoreAlias("seguros");
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
        
        /**
         * System routing data source.
         *
         * @return the system routing data source
         */
        @Bean
        public SystemRoutingDataSource SystemRoutingDataSource() {
            return Mockito.mock(SystemRoutingDataSource.class);
        }
        
        @Bean
        public IatxComm IatxComm(){
            return Mockito.mock(IatxComm.class);
        }

    }

    @Test
    public void testResumenesCitiFechasDAOIT(){
        try {
            
            Cliente cliente = new Cliente();
            cliente.setNup("02616647");
            cliente.setFechaNacimiento("19710305");
            cliente.setDni("00021767058");
            cliente.setTipoDocumento(TipoDocumentoEnum.DNI.getCampo());
            FechasResumenCitiIn fechasResumenCitiIn = new FechasResumenCitiIn();
            fechasResumenCitiIn.setSucursal("999");
            fechasResumenCitiIn.setDocumento("");
            fechasResumenCitiIn.setCuenta("0265222110");
            fechasResumenCitiIn.setFechaCierreDesde("2000/01/01");
            fechasResumenCitiIn.setFechaCierreHaste("2017/12/31");
            List<ResumenFechaOutEntity> fechasResumenes = resumenesCitiDAO.consultarFechasPorCuenta(fechasResumenCitiIn);
            Assert.assertNotNull(fechasResumenes);
        } catch (DAOException e) {
            TestCase.fail();
        }
    }
    
    
    @Test
    public void testConsultaDAOIT() throws WSODException, IOException{
        try {
            
            ResumenMensualCuenta resumenCitiIn = new ResumenMensualCuenta();
            resumenCitiIn.setFecha(new Date());
            resumenCitiIn.setDocId("v7126-5499-5500-5486-PFA1-556182FAAA-0-91117-0-91117-78-68-0-3-0-%5E%01999%01EGIDIO%20EZEQUIEL%20COVE%0120329494439%01265222110%0120170801%010");
            ReporteResumenMensualCuenta rep = resumenesCitiDAO.consultarResumenCityPorId(resumenCitiIn);
            Assert.assertNotNull(rep);
        } catch (DAOException e) {
            TestCase.fail();
        }
    }
    
    
    @Test
    public void testConsultaTarjetasPuntualDAOIT() throws WSODException, IOException, OnDemandDAOException{
            
        	ReporteSeleccionado reporteSeleccionado= new ReporteSeleccionado();
        	reporteSeleccionado.setFechaPuntual("11/11/11");
        	reporteSeleccionado.setDocId("v7126-5499-5500-5486-PFA1-556182FAAA-0-91117-0-91117-78-68-0-3-0-%5E%01999%01EGIDIO%20EZEQUIEL%20COVE%0120329494439%01265222110%0120170801%010");
            ReporteResumenPuntual rep = resumenesCitiDAO.consultarResumenesTarjetaMastercard(reporteSeleccionado, "VISA");
            Assert.assertNotNull(rep);
    }
}
