/**
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAOIT.SietePorVenticuatroV1DAOITConfiguration;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.CONFIG;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import junit.framework.TestCase;

/**
 * The Class MyaDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        SietePorVenticuatroV1DAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = {
        // "ERI.URL=http://webdesafront2.rio.ar.bsch:6480/ERIService.svc",
        "7x24V1.URL=http://aixhomo1.ar.bsch:9096/7x24/procesar.jsp",
        // "7x24V1.ENDPOINT=http://localhost:2670/7x24/procesar.jsp",
        "7x24v1.TIMEOUT=10000", "APP.ENCODING = UTF-8" , "7X24.ENCODING=ISO-8859-1"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SietePorVenticuatroV1DAOIT {

    /** The mya DAO. */
    @Autowired
    @Qualifier("SietePorVenticuatroV1WSDAO")
    private SietePorVenticuatroV1DAO sietePorVenticuatroV1DAO;

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
    @ComponentScan(basePackageClasses = { SietePorVenticuatroV1DAO.class, RestWebClientImpl.class, Sign.class,
            KeyStoreHelperImpl.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                    KeyStoreFactory.class }))
    public static class SietePorVenticuatroV1DAOITConfiguration {

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
    public void getEliminarEmpleado() {

        XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
        config.setVersionXML("2.0.0");

    }

    /**
     * Gets the estado cliente.
     *
     * @return the estado cliente
     */
    @Test
    public void getPagoHaberes() {

        // XMLRequest.CONFIG config = new XMLRequest.CONFIG();
        // config.setVersionXML("2.0.0");
        // config.setEcoDatosEntrada("N");
        //
        // XMLRequest.META meta = new XMLRequest.META();
        // meta.setNombre("PAGHABHON_");
        // meta.setVersion("100");
        //
        // XMLRequest.META.Cliente cliente = new XMLRequest.META.Cliente();
        // // Datos del cliente
        // cliente.setTipoPersona("I");
        // cliente.setTipoId("N");
        // cliente.setIdCliente("00013238861");
        // cliente.setFechaNac("19591005");
        // cliente.setNUP("02615492");
        // // FIN - Datos del cliente
        // meta.setCliente(cliente);
        //
        // XMLRequest.META.Usuario usuario = new XMLRequest.META.Usuario();
        // usuario.setUsuarioTipo("03");
        // // Usuario de RACF
        // usuario.setUsuarioId("FREEUSER");
        // usuario.setUsuarioPwd("FREEUSR0");
        // // FIN - Usuario de RACF
        // usuario.setUsuarioAtrib(" ");
        // meta.setUsuario(usuario);
        //
        // meta.setIndAuten("0");
        // meta.setIdSesionCnt(" ");
        //
        // XMLRequest.META.Canal canal = new XMLRequest.META.Canal();
        //
        // canal.setCanalTipo("04");
        // canal.setCanalId("HTML");
        // canal.setCanalVersion("000");
        //
        // XMLRequest.META.Subcanal subcanal = new XMLRequest.META.Subcanal();
        //
        // subcanal.setSubcanalTipo("99");
        // subcanal.setSubcanalId("0001");
        //
        //
        // meta.setCanal(canal);
        // meta.setCliente(cliente);
        // meta.setSubcanal(subcanal);
        // meta.setUsuario(usuario);
        //
        // XMLRequest.DATOSENTRADA datosentrada = new XMLRequest.DATOSENTRADA();
        //
        // XMLRequest.DATOSENTRADA.Parametros parametros = new
        // XMLRequest.DATOSENTRADA.Parametros();
        //
        // datosentrada.setParametros(parametros);
        //
        // XMLRequest consultaPerfilInversor = new XMLRequest();
        //
        // consultaPerfilInversor.setCONFIG(config);
        // consultaPerfilInversor.setMETA(meta);
        // consultaPerfilInversor.setDATOSENTRADA(datosentrada);

        XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
        config.setVersionXML("2.0.0");
        config.setEcoDatosEntrada("N");

        XMLRequestEntity.META meta = new XMLRequestEntity.META();
        meta.setNombre("CONSULTA_TX_AG");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();

        // Datos del cliente
        cliente.setTipoPersona("I");
        cliente.setTipoId("N");
        cliente.setIdCliente("00013238861");
        cliente.setFechaNac("19591005");
        cliente.setNUP("02615492");
        // FIN - Datos del cliente
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("FREEUSER");
        usuario.setUsuarioPwd("FREEUSR0");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt("        ");
        meta.setModoEjecucion("I");

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();

        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion("000");

        XMLRequestEntity.META.Subcanal subcanal = new XMLRequestEntity.META.Subcanal();

        subcanal.setSubcanalTipo("99");
        subcanal.setSubcanalId("0001");

        meta.setCanal(canal);
        meta.setCliente(cliente);
        meta.setSubcanal(subcanal);
        meta.setUsuario(usuario);

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();

        parametros.setNUPGuardado("02615492");
        parametros.setNombreGuardado("PAGHABHON_");
        parametros.setFechaEjecucionDesde(ISBANStringUtils.formatearFecha(new Date(), "yyyyMMdd"));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta(
                    ISBANStringUtils.formatearFecha(new Date(fechaEjecucionHasta), "yyyyMMdd").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }

    @Test
    public void getComprobanteTransferenciasRio() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        CONFIG config = new CONFIG();

        config.setEcoDatosEntrada("N");
        config.setVersionXML("2.0.0");

        META meta = new META();
        meta.setNombre("CONSULTA_TX_HIS");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        cliente.setTipoPersona("I");
        cliente.setTipoId("N");
        cliente.setIdCliente("00013238861");
        cliente.setFechaNac("19591005");
        cliente.setNUP("02615492");
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("FREEUSER");
        usuario.setUsuarioPwd("FREEUSR0");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt(StringUtils.repeat(" ", 8));

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion(StringUtils.repeat("0", 3));
        meta.setCanal(canal);

        XMLRequestEntity.META.Subcanal subCanal = new XMLRequestEntity.META.Subcanal();
        subCanal.setSubcanalTipo("99");
        subCanal.setSubcanalId("0001");
        meta.setSubcanal(subCanal);

        meta.setModoEjecucion("I");

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setNUPGuardado("02615492");
        parametros.setNombreGuardado("TRANSF_BCO_RIO");
        parametros.setModoEjecucionGuardado("I,IA");
        parametros.setEstado("E");
        parametros.setAccion("E");
        parametros.setFechaEjecucionDesde("20161026");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta("20170424");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }

    @Test
    public void getComprobanteTransferenciasACH() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        CONFIG config = new CONFIG();

        config.setEcoDatosEntrada("N");
        config.setVersionXML("2.0.0");

        META meta = new META();
        meta.setNombre("CONSULTA_TX_HIS");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        cliente.setTipoPersona("I");
        cliente.setTipoId("E");
        cliente.setIdCliente("00005134021");
        cliente.setFechaNac("19310212");
        cliente.setNUP("00627792");
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("00QMRR92");
        usuario.setUsuarioPwd("@DNVX$S3");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt(StringUtils.repeat(" ", 8));

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion(StringUtils.repeat("0", 3));
        meta.setCanal(canal);

        XMLRequestEntity.META.Subcanal subCanal = new XMLRequestEntity.META.Subcanal();
        subCanal.setSubcanalTipo("99");
        subCanal.setSubcanalId("0001");
        meta.setSubcanal(subCanal);

        meta.setModoEjecucion("I");

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setNUPGuardado("00627792");
        parametros.setNombreGuardado("TRFACH");
        parametros.setModoEjecucionGuardado("I,IA");
        parametros.setEstado("E");
        parametros.setAccion("E");
        parametros.setFechaEjecucionDesde("20161104");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta("20170503");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }

    @Test
    public void getComprobanteTransferenciasOtrosBancos() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        CONFIG config = new CONFIG();

        config.setEcoDatosEntrada("N");
        config.setVersionXML("2.0.0");

        META meta = new META();
        meta.setNombre("CONSULTA_TX_HIS");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        cliente.setTipoPersona("I");
        cliente.setTipoId("N");
        cliente.setIdCliente("00013238861");
        cliente.setFechaNac("19591005");
        cliente.setNUP("02615492");
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("FREEUSER");
        usuario.setUsuarioPwd("FREEUSR0");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt(StringUtils.repeat(" ", 8));

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion(StringUtils.repeat("0", 3));
        meta.setCanal(canal);

        XMLRequestEntity.META.Subcanal subCanal = new XMLRequestEntity.META.Subcanal();
        subCanal.setSubcanalTipo("99");
        subCanal.setSubcanalId("0001");
        meta.setSubcanal(subCanal);

        meta.setModoEjecucion("I");

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setNUPGuardado("02615492");
        parametros.setNombreGuardado("TRFCCI");
        parametros.setModoEjecucionGuardado("I,IA");
        parametros.setEstado("E");
        parametros.setAccion("E");
        parametros.setFechaEjecucionDesde(ISBANStringUtils.formatearFecha(new Date(), "yyyyMMdd"));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta(
                    ISBANStringUtils.formatearFecha(new Date(fechaEjecucionHasta), "yyyyMMdd").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }

    @Test
    public void getComprobanteTransferenciasPagoDeHaberes() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        CONFIG config = new CONFIG();

        config.setEcoDatosEntrada("N");
        config.setVersionXML("2.0.0");

        META meta = new META();
        meta.setNombre("CONSULTA_TX_HIS");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        cliente.setTipoPersona("I");
        cliente.setTipoId("N");
        cliente.setIdCliente("00013238861");
        cliente.setFechaNac("19591005");
        cliente.setNUP("02615492");
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("FREEUSER");
        usuario.setUsuarioPwd("FREEUSR0");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt(StringUtils.repeat(" ", 8));

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion(StringUtils.repeat("0", 3));
        meta.setCanal(canal);

        XMLRequestEntity.META.Subcanal subCanal = new XMLRequestEntity.META.Subcanal();
        subCanal.setSubcanalTipo("99");
        subCanal.setSubcanalId("0001");
        meta.setSubcanal(subCanal);

        meta.setModoEjecucion("I");

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setNUPGuardado("02615492");
        parametros.setNombreGuardado("PAGHABHON_");
        parametros.setModoEjecucionGuardado("I,IA");
        parametros.setEstado("E");
        parametros.setAccion("E");
        parametros.setFechaEjecucionDesde(ISBANStringUtils.formatearFecha(new Date(), "yyyyMMdd"));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta(
                    ISBANStringUtils.formatearFecha(new Date(fechaEjecucionHasta), "yyyyMMdd").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }

    @Test
    public void getComprobanteTransferenciasRioNoEfectuada() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        CONFIG config = new CONFIG();

        config.setEcoDatosEntrada("N");
        config.setVersionXML("2.0.0");

        META meta = new META();
        meta.setNombre("CONSULTA_TX_HIS");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        cliente.setTipoPersona("I");
        cliente.setTipoId("N");
        cliente.setIdCliente("00013238861");
        cliente.setFechaNac("19591005");
        cliente.setNUP("02615492");
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("FREEUSER");
        usuario.setUsuarioPwd("FREEUSR0");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt(StringUtils.repeat(" ", 8));

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion(StringUtils.repeat("0", 3));
        meta.setCanal(canal);

        XMLRequestEntity.META.Subcanal subCanal = new XMLRequestEntity.META.Subcanal();
        subCanal.setSubcanalTipo("99");
        subCanal.setSubcanalId("0001");
        meta.setSubcanal(subCanal);

        meta.setModoEjecucion("I");

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setNUPGuardado("02615492");
        parametros.setNombreGuardado("TRANSF_BCO_RIO");
        parametros.setModoEjecucionGuardado("I,IA");
        parametros.setEstado("X,Y,R,L,D");
        parametros.setAccion("E");
        parametros.setEjecutadoPorAgendamiento("S");
        parametros.setFechaEjecucionDesde("20161026");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta("20170424");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }

    @Test
    public void getComprobanteTransferenciasACHNoEfectuada() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        CONFIG config = new CONFIG();

        config.setEcoDatosEntrada("N");
        config.setVersionXML("2.0.0");

        META meta = new META();
        meta.setNombre("CONSULTA_TX_HIS");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        cliente.setTipoPersona("I");
        cliente.setTipoId("N");
        cliente.setIdCliente("00013238861");
        cliente.setFechaNac("19591005");
        cliente.setNUP("02615492");
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("FREEUSER");
        usuario.setUsuarioPwd("FREEUSR0");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt(StringUtils.repeat(" ", 8));

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion(StringUtils.repeat("0", 3));
        meta.setCanal(canal);

        XMLRequestEntity.META.Subcanal subCanal = new XMLRequestEntity.META.Subcanal();
        subCanal.setSubcanalTipo("99");
        subCanal.setSubcanalId("0001");
        meta.setSubcanal(subCanal);

        meta.setModoEjecucion("I");

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setNUPGuardado("02615492");
        parametros.setNombreGuardado("TRFACH");
        parametros.setModoEjecucionGuardado("I,IA");
        parametros.setEstado("X,Y,R,L,D");
        parametros.setAccion("E");
        parametros.setEjecutadoPorAgendamiento("S");
        parametros.setFechaEjecucionDesde(ISBANStringUtils.formatearFecha(new Date(), "yyyyMMdd"));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta(
                    ISBANStringUtils.formatearFecha(new Date(fechaEjecucionHasta), "yyyyMMdd").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }

    @Test
    public void getComprobanteTransferenciasOtrosBancosNoEfectuada() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        CONFIG config = new CONFIG();

        config.setEcoDatosEntrada("N");
        config.setVersionXML("2.0.0");

        META meta = new META();
        meta.setNombre("CONSULTA_TX_HIS");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        cliente.setTipoPersona("I");
        cliente.setTipoId("N");
        cliente.setIdCliente("00013238861");
        cliente.setFechaNac("19591005");
        cliente.setNUP("02615492");
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("FREEUSER");
        usuario.setUsuarioPwd("FREEUSR0");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt(StringUtils.repeat(" ", 8));

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion(StringUtils.repeat("0", 3));
        meta.setCanal(canal);

        XMLRequestEntity.META.Subcanal subCanal = new XMLRequestEntity.META.Subcanal();
        subCanal.setSubcanalTipo("99");
        subCanal.setSubcanalId("0001");
        meta.setSubcanal(subCanal);

        meta.setModoEjecucion("I");

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setNUPGuardado("02615492");
        parametros.setNombreGuardado("TRFCCI");
        parametros.setModoEjecucionGuardado("I,IA");
        parametros.setEstado("X,Y,R,L,D");
        parametros.setAccion("E");
        parametros.setEjecutadoPorAgendamiento("S");
        parametros.setFechaEjecucionDesde(ISBANStringUtils.formatearFecha(new Date(), "yyyyMMdd"));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta(
                    ISBANStringUtils.formatearFecha(new Date(fechaEjecucionHasta), "yyyyMMdd").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }

    @Test
    public void getComprobanteTransferenciasPagoDeHaberesNoEfectuada() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();

        CONFIG config = new CONFIG();

        config.setEcoDatosEntrada("N");
        config.setVersionXML("2.0.0");

        META meta = new META();
        meta.setNombre("CONSULTA_TX_HIS");
        meta.setVersion("100");

        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        cliente.setTipoPersona("I");
        cliente.setTipoId("N");
        cliente.setIdCliente("00013238861");
        cliente.setFechaNac("19591005");
        cliente.setNUP("02615492");
        meta.setCliente(cliente);

        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        usuario.setUsuarioTipo("03");
        // Usuario de RACF
        usuario.setUsuarioId("FREEUSER");
        usuario.setUsuarioPwd("FREEUSR0");
        // FIN - Usuario de RACF
        usuario.setUsuarioAtrib("  ");
        meta.setUsuario(usuario);

        meta.setIndAuten("0");
        meta.setIdSesionCnt(StringUtils.repeat(" ", 8));

        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalTipo("04");
        canal.setCanalId("HTML");
        canal.setCanalVersion(StringUtils.repeat("0", 3));
        meta.setCanal(canal);

        XMLRequestEntity.META.Subcanal subCanal = new XMLRequestEntity.META.Subcanal();
        subCanal.setSubcanalTipo("99");
        subCanal.setSubcanalId("0001");
        meta.setSubcanal(subCanal);

        meta.setModoEjecucion("I");

        XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setEjecutadoPorAgendamiento("S");
        parametros.setNUPGuardado("02615492");
        parametros.setNombreGuardado("PAGHABHON_");
        parametros.setModoEjecucionGuardado("I,IA");
        parametros.setEstado("X,Y,R,L,D");
        parametros.setAccion("E");
        parametros.setFechaEjecucionDesde(ISBANStringUtils.formatearFecha(new Date(), "yyyyMMdd"));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String fechaEjecucionHasta = df.format(today);
        try {
            fechaEjecucionHasta = FechaUtils.getFechaMasUnAño(fechaEjecucionHasta);

            parametros.setFechaEjecucionHasta(
                    ISBANStringUtils.formatearFecha(new Date(fechaEjecucionHasta), "yyyyMMdd").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datosentrada.setParametros(parametros);

        xmlRequest.setCONFIG(config);
        xmlRequest.setMETA(meta);
        xmlRequest.setDATOSENTRADA(datosentrada);

        try {
            XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
            response.getDATOSRESULTADO();
        } catch (DAOException e) {
            TestCase.fail();
        }

    }
}
