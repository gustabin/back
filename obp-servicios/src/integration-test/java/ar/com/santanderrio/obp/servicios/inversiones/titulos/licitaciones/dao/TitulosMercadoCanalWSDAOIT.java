package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import org.junit.Before;
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
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.inversiones.licitaciones.ws.MercadosCanalWSImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.TitulosMercadoCanalWSDAOIT.TitulosMercadoCanalWSDAOITConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TitulosMercadoCanalWSDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "MERCADOSCANAL.POOL.ACTIVO=false", 
//		"MERCADOSCANAL.URL=http://webhomo3.rio.ar.bsch:9086/MercadosCanalService.svc", 
//		"MERCADOSCANAL.URL=http://localhost:8087/MercadosCanalService.svc",
//		"MERCADOSCANAL.URL=http://websmctest01.rio.ar.bsch:9086/mercadoscanalservice.svc",
		"MERCADOSCANAL.URL=http://webbpsibehomo01:6100/MWCanalesService/ConsultaDeOrdenes",
		"MERCADOSCANAL.TIMEOUT=10000", "MERCADOSCANAL.POOL.SIZE=30",
        "MERCADOSCANAL.POOL.MAXWAIT=5", "TAGS.A.ELIMINAR.XML=nombre", "APP.ENCODING = UTF-8" , 
        "MERCADOSCANAL.PALABRAS.SENSIBLES.OUT=passwordRacf"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TitulosMercadoCanalWSDAOIT {
	 /** The mya DAO. */
    @Autowired
    private TitulosMercadoCanalDAO titulosMercadoCanalWSDAO;

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
    @ComponentScan(basePackageClasses = { Sign.class, TitulosMercadoCanalDAO.class, TitulosMercadoCanalWSDAOImpl.class, 
    		MercadosCanalWSImpl.class, 
            KeyStoreHelperImpl.class, ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { KeyStoreFactory.class, FirmarCuentasDAOImpl.class }))
    public static class TitulosMercadoCanalWSDAOITConfiguration {

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
     * @throws DAOException
     *             the DAO exception
     */
//    @Test
//    public void obtenerCuentasTitulos() {


//        request.setDato("BEL");
//        String firma = "";
//        try {
//            firma = new String(sign.buildB64Signature(request.getDato().getBytes("UTF-8"), "MYA", true));
//        } catch (DAOException e1) {
//            e1.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        ConsultaOperaciones request1 = new ConsultaOperaciones();
//        request1.setDato("OBP");
//        DatosConsultaOperaciones datosConsultaOperaciones = new DatosConsultaOperaciones();
//        datosConsultaOperaciones.setCuentaTitulo("13946203,19979049,19979056,22694489");
//        Date fechaAntes = new Date(2017,01,01);
//        String fechaDesde = "\\/Date(" + fechaAntes.getTime() + ")\\/";
//		datosConsultaOperaciones.setFechaDesde(fechaDesde );
//		Date hoy = new Date();
//        String fechaHasta = "\\/Date(" + hoy.getTime() + ")\\/";;
//		datosConsultaOperaciones.setFechaHasta(fechaHasta);
//        datosConsultaOperaciones.setUsuario("25393619");
//        datosConsultaOperaciones.setIp("180.166.10.0");
//		request1.setDatos(datosConsultaOperaciones );
//        request1.setFirma("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\u003d");
//        
//        
//        //ConsultaOperacionesResponse rta = null;
//        List<DatosConsultaOperacionesResponse> rta = null;
//        
//        try {
//            rta = this.titulosMercadoCanalWSDAO.consultarOperacionesText(request1);
//        } catch (DAOException e) {
//            System.out.println("error");
//        }
//        Assert.notNull(rta);
//    }

   
    
    

}
