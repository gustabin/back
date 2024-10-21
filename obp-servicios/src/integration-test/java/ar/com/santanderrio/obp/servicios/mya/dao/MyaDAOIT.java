/**
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
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

import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaBodyDatosRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaBodyRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCuenta;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaHeaderRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaNroMensaje;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaProductoReq;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaServiciosEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaTipoIdEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaTipoPersonaEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.servicios.mya.dao.MyaDAOIT.MyaDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.mya.dao.impl.MyaDAOImpl;
import ar.com.santanderrio.obp.servicios.mya.dao.impl.MyaGestionarWSImpl;

/**
 * The Class MyaDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { MyaDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "MYA.POOL.ACTIVO=false", 
//        "MYA.URL=http://localhost:8081/services/",
        "MYA.URL=http://wasinetdesamya01.ar.bsch:9081/services/",
        "MYA.TIMEOUT=10", "MYA.POOL.SIZE=30", "MYA.POOL.MAXWAIT=5",  "APP.ENCODING = UTF-8"  })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MyaDAOIT {

    /** The mya DAO. */
    @Autowired
    private MyaDAO myaDAO;

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
    @ComponentScan(basePackageClasses = { MyaDAOImpl.class, MyaGestionarWSImpl.class, Sign.class, KeyStoreHelperImpl.class, ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class MyaDAOITConfiguration {
        
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
    @Test
    public void getEstadoCliente() throws DAOException {
        MyaXmlRequest token = new MyaXmlRequest();
        MyaHeaderRequest header = new MyaHeaderRequest();
        header.setServicio(MyaServiciosEnum.ESTADO_CLIENTE);
        header.setCanal("04");
        header.setSubCanal("99");
        header.setNup("03003824");
        token.setHeader(header);
        MyaBodyRequest body = new MyaBodyRequest();
        MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
        body.setDatos(datos);
        token.setDatosAFirmar(body);

        MyaXmlResponse respuesta = myaDAO.invocarMya(token);
        Assert.assertNotNull(respuesta);

    }

    /**
     * Registrar.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void registrar() throws DAOException {
        MyaXmlRequest token = new MyaXmlRequest();
        MyaHeaderRequest header = new MyaHeaderRequest();
        header.setServicio(MyaServiciosEnum.REGISTRAR);
        header.setCanal("04");
        header.setSubCanal("99");
        header.setNup("00276937");
        token.setHeader(header);
        MyaBodyRequest body = new MyaBodyRequest();
        MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
        datos.setTipoId(MyaTipoIdEnum.DNI);
        datos.setClienteId("18287469");
        datos.setTipoPersona(MyaTipoPersonaEnum.INDIVIDUO);
        datos.setFechaNacimiento("19670321");
        datos.setNombre("ADRIANA");
        datos.setPrimerNombre("VEGA");
        datos.setSegundoNombre("VEGA");
        body.setDatos(datos);
        token.setDatosAFirmar(body);
        
        MyaXmlResponse respuesta = myaDAO.invocarMya(token);
        Assert.assertNotNull(respuesta);
        
    }

    /**
     * Registrar con destino.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void registrarConDestino() throws DAOException {
        MyaXmlRequest token = new MyaXmlRequest();
        MyaHeaderRequest header = new MyaHeaderRequest();
        header.setServicio(MyaServiciosEnum.REGISTRAR_CON_DESTINO);
        header.setCanal("04");
        header.setSubCanal("99");
        header.setNup("02615492"); 
        token.setHeader(header);
        MyaBodyRequest body = new MyaBodyRequest();
        MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
        datos.setTipoId(MyaTipoIdEnum.DNI);
        datos.setClienteId("00013238861");
        datos.setTipoPersona(MyaTipoPersonaEnum.INDIVIDUO);
        datos.setFechaNacimiento("19591005");
        datos.setNombre("VALERIANO PAUL TADEO");
        datos.setPrimerNombre("COMIGNAGHI");
        datos.setSegundoNombre("");
        
        MyaDestino destino = new MyaDestino();
        destino.setId("1");
        destino.setCodigoOperacion(MyaCodOperacionEnum.ALTA);
        destino.setTipo(MyaDestino.MAIL);
        destino.setSecuencia("1");
        destino.setDescripcion("tadeito@asda.com");
        destino.setEmpresaCel("");
        MyaDestino destino2 = new MyaDestino();
        destino2.setId("2");
        destino2.setCodigoOperacion(MyaCodOperacionEnum.ALTA);
        destino2.setTipo(MyaDestino.CEL);
        destino2.setSecuencia("1");
        destino2.setDescripcion("11-12341234");
        destino2.setEmpresaCel("CTI ");
        List<MyaDestino> listMyaDestino = new ArrayList<MyaDestino>();
        listMyaDestino.add(destino);
        listMyaDestino.add(destino2);
        datos.setListMyaDestino(listMyaDestino);
        body.setDatos(datos);
        token.setDatosAFirmar(body);
        
        MyaXmlResponse respuesta = myaDAO.invocarMya(token);
        Assert.assertNotNull(respuesta);
        
    }

    /**
     * Update destino.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void updateDestino() throws DAOException {
        MyaXmlRequest token = new MyaXmlRequest();
        MyaHeaderRequest header = new MyaHeaderRequest();
        header.setServicio(MyaServiciosEnum.UPDATE_DESTINOS);
        header.setCanal("70");
        header.setSubCanal("00");
        header.setNup("02615492");
        token.setHeader(header);
        MyaBodyRequest body = new MyaBodyRequest();
        MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
       
        MyaDestino destino = new MyaDestino();
        destino.setId("1");
        destino.setCodigoOperacion(MyaCodOperacionEnum.ALTA);
        destino.setTipo(MyaDestino.CEL);
        destino.setSecuencia("1");
        destino.setDescripcion("11-45683452");
        destino.setEmpresaCel("NEXT");
        List<MyaDestino> listMyaDestino = new ArrayList<MyaDestino>();
        listMyaDestino.add(destino);
        datos.setListMyaDestino(listMyaDestino);
        body.setDatos(datos);
        token.setDatosAFirmar(body);
        
        MyaXmlResponse respuesta = myaDAO.invocarMya(token);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getCodigoRetorno(), "1");
        
    }

    /**
     * Gets the suscripciones.
     *
     * @return the suscripciones
     * @throws DAOException the DAO exception
     */
    @Test
    public void getSuscripciones() throws DAOException {
        MyaXmlRequest token = new MyaXmlRequest();
        MyaHeaderRequest header = new MyaHeaderRequest();
        header.setServicio(MyaServiciosEnum.SUSCRIPCIONES);
        header.setCanal("04");
        header.setSubCanal("99");
        header.setNup("00276937");
        token.setHeader(header);
        MyaBodyRequest body = new MyaBodyRequest();
        MyaBodyDatosRequest datos = new MyaBodyDatosRequest();

        List<MyaCuenta> listMyaCuenta = new ArrayList<MyaCuenta>();
        MyaCuenta cuenta1 = new MyaCuenta();
        cuenta1.setTipoCuenta("02");
        cuenta1.setCodigoAplicacion("INFI");
        cuenta1.setCodigoPaquete("2101");
        cuenta1.setDepositoEfectivo("00000000000000");
        MyaCuenta cuenta3 = new MyaCuenta();
        cuenta3.setTipoCuenta("02");
        cuenta3.setCodigoAplicacion("INFI");
        cuenta3.setCodigoPaquete("2110");
        cuenta3.setDepositoEfectivo("00000000000000");
        MyaCuenta cuenta2 = new MyaCuenta();
        cuenta2.setTipoCuenta("00");
        cuenta2.setCodigoAplicacion("ACTE");
        cuenta2.setCodigoPaquete("5150");
        cuenta2.setDepositoEfectivo("00000000000000");
        MyaCuenta cuenta4 = new MyaCuenta();
        cuenta4.setTipoCuenta("04");
        cuenta4.setCodigoAplicacion("ACAD");
        cuenta4.setCodigoPaquete("0000");
        cuenta4.setDepositoEfectivo("00000000000000");
        MyaCuenta cuenta5 = new MyaCuenta();
        cuenta5.setTipoCuenta("05");
        cuenta5.setCodigoAplicacion("ABAE");
        cuenta5.setCodigoPaquete("0000");
        cuenta5.setDepositoEfectivo("00000000000000");
        MyaCuenta cuenta6 = new MyaCuenta();
        cuenta6.setTipoCuenta("77");
        cuenta6.setCodigoAplicacion("AVIS");
        cuenta6.setCodigoPaquete("0000");
        cuenta6.setDepositoEfectivo("00000000000100");
        MyaCuenta cuenta7 = new MyaCuenta();
        cuenta7.setTipoCuenta("07");
        cuenta7.setCodigoAplicacion("AVIS");
        cuenta7.setCodigoPaquete("0000");
        cuenta7.setDepositoEfectivo("00000001054800");
        MyaCuenta cuenta8 = new MyaCuenta();
        cuenta8.setTipoCuenta("77");
        cuenta8.setCodigoAplicacion("AVIS");
        cuenta8.setCodigoPaquete("0000");
        cuenta8.setDepositoEfectivo("00000000000100");
        
        listMyaCuenta.add(cuenta1);
        listMyaCuenta.add(cuenta2);
        listMyaCuenta.add(cuenta3);
        listMyaCuenta.add(cuenta4);
        listMyaCuenta.add(cuenta5);
        listMyaCuenta.add(cuenta6);
        listMyaCuenta.add(cuenta7);
        listMyaCuenta.add(cuenta8);
        datos.setListMyaCuenta(listMyaCuenta);
        body.setDatos(datos);
        token.setDatosAFirmar(body);

        MyaProductoReq producto = new MyaProductoReq();
        producto.setNumeroProducto("1");
        List<MyaNroMensaje> listMyaNroMensaje = new ArrayList<MyaNroMensaje>();
        producto.setListMyaNroMensaje(listMyaNroMensaje);

        MyaProductoReq producto15 = new MyaProductoReq();
        producto15.setNumeroProducto("15");
        List<MyaNroMensaje> listMyaNroMensaje15 = new ArrayList<MyaNroMensaje>();
        producto15.setListMyaNroMensaje(listMyaNroMensaje15);

        MyaProductoReq producto6 = new MyaProductoReq();
        producto6.setNumeroProducto("6");
        List<MyaNroMensaje> listMyaNroMensaje6 = new ArrayList<MyaNroMensaje>();
        producto6.setListMyaNroMensaje(listMyaNroMensaje6);
        
        List<MyaProductoReq> listMyaProducto = new ArrayList<MyaProductoReq>();
        listMyaProducto.add(producto);
        listMyaProducto.add(producto15);
        listMyaProducto.add(producto6);
        datos.setListMyaProductoReq(listMyaProducto);
        
        MyaXmlResponse respuesta = myaDAO.invocarMya(token);
        Assert.assertNotNull(respuesta);
        
    }
    
    /**
     * Response get estado cliente jaxb.
     *
     * @throws JAXBException the JAXB exception
     */
    @Test
    @Ignore
    public void responseGetEstadoClienteJaxb() throws JAXBException {
        MyaXmlResponse response = new MyaXmlResponse();
        response.setClienteEstado("SA");
        response.setCodigoRetorno("0");
        MyaDestino destino1 = new MyaDestino();
        destino1.setId("1");
        destino1.setTipo("CEL");
        destino1.setSecuencia("1");
        destino1.setDescripcion("11-12345678");
        destino1.setEmpresaCel("MOVI");
        destino1.setEstado("1");

        MyaDestino destino2 = new MyaDestino();
        destino2.setId("2");
        destino2.setTipo("MAIL");
        destino2.setSecuencia("1");
        destino2.setDescripcion("mail@mail.com");
        destino2.setEmpresaCel("INT");
        destino2.setEstado("1");
        List<MyaDestino> listDestinos = new ArrayList<MyaDestino>();
        listDestinos.add(destino1);
        listDestinos.add(destino2);
        response.setListMyaDestino(listDestinos);
        String xml = JaxbUtils.transformarObjetoAXml(response, "UTF-8", Boolean.TRUE, Boolean.FALSE, null);
        
        Assert.assertNotNull(xml);
    }
}
