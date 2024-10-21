package ar.com.santanderrio.obp.servicios.seguros.dao;

import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.seguros.bo.SeguroToken;
import ar.com.santanderrio.obp.servicios.seguros.dao.ConsultaSeguroTenenciasDAOIT.ConsultaSeguroTenenciasDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.seguros.entities.Poliza;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ConsultaSeguroTenenciasDAOITConfiguration.class})
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
//        "TENENCIA.URL=http://localhost:8089/segurosServicios/api/olb",
		"TENENCIA.URL=http://wasrioseghomo.ar.bsch:19085/segurosServicios/api/olb",
        "TENENCIA.TIMEOUT=10000",
        "TENENCIA.PALABRAS.SENSIBLES.IN=user,password,nup,numeroDocumento",
        "TENENCIA.PALABRAS.SENSIBLES.OUT=",
        "KEYSTORE.TENENCIA.TYPE=JKS",
        "KEYSTORE.TENENCIA.PATH=<mockeado>",
        "KEYSTORE.TENENCIA.IDSEGURIDAD=20104",
        "TENENCIA.JKS=TENENCIA",
        "APP.ENCODING = UTF-8",
        "SEGUROS.RAMO=0",
        "KEYSTORE.SEGUROS.IDSEGURIDAD=20104",
        "SEGUROS.USUARIO=HMBK",
        "SEGUROS.PASSWORD=HK2453690",
        "SEGUROS.CANAL=04",
        "SEGUROS.URL = _http://des4z1.ar.bsch:9190/CanalCryptServlet",
        "APP.ENCODING=UTF-8"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConsultaSeguroTenenciasDAOIT {
   
    @Autowired
    private SeguroTenenciasDAO seguroDAO;
    
    /** The seguro BO. */
    @Mock
    private SeguroToken seguroToken;
    
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
    @ComponentScan(basePackageClasses = {SeguroTenenciasDAOImpl.class, RestWebClientImpl.class,  Sign.class, KeyStoreHelperImpl.class, ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class ConsultaSeguroTenenciasDAOITConfiguration {
        
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

    }
    
    @Test
    public void testConsultaDAOIT(){
        try {
            String token = "eyJhbGciOiJIUzI1NiJ9.eyJTRVNJT05fSU5GTyI6eyJjaGFubmVsIjpudWxsLCJ1c2VybmFtZSI6bnVsbCwiaXAiOm51bGwsInJvbGVMaXN0IjpbXSwidXNlckRhdGEiOnsibnVwIjoiMDI2MTY2NDciLCJ0aXBvRG9jdW1lbnRvIjoiTiIsIm51bWVyb0RvY3VtZW50byI6IjAwMDIxNzY3MDU4IiwiZmVjaGFOYWNpbWllbnRvIjoiMTk3MTAzMDUiLCJudW1lcm9Vc3VhcmlvIjoiSE1CSyIsImluZGljYWRvciI6MH19LCJleHAiOjE1MTQzMDYxOTB9.o7QTTIKDYIRKv9PuuWYFqAIFc7MGnKSw8v_THHcjWZc";
            String url   = "http://des4z1.ar.bsch:9190/CanalCryptServlet";
            Respuesta<TokenDTO> respuestaMensaje = new Respuesta<TokenDTO>();
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setTokenFirmado(token);
            tokenDTO.setUrl(url);
            respuestaMensaje.setRespuesta(tokenDTO);
            respuestaMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
            when(seguroToken.armarToken(Matchers.any(MessageContext.class), Matchers.any(TokenExternoDTO.class))).thenReturn(respuestaMensaje);
            
            Cliente cliente = new Cliente();
            cliente.setNup("02616647");
            cliente.setFechaNacimiento("19710305");
            cliente.setDni("00021767058");
            cliente.setTipoDocumento(TipoDocumentoEnum.DNI.getCampo());
            TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(cliente);
            Respuesta<TokenDTO> respuestaToken = seguroToken.armarToken(null, tokenExternoDTO);
            List<Poliza> list = seguroDAO.consultarSeguros(respuestaToken.getRespuesta().getTokenFirmado(), cliente);
            Assert.assertNotNull(list);
        } catch (DAOException e) {
            TestCase.fail();
        }
    }
}
