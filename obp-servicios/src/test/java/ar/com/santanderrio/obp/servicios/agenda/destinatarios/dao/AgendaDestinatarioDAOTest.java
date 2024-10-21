/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.transfers.recipients.RecipientsApi;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.RecipientsApiClient;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.exception.RecipientsApiException;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricRegisterBO;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.MetricBuilderImpl;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.MetricRegisterBOImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.iatx.IatxMatcherPaginadoAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.impl.AgendaDestinatarioDAOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.LlamadaAgendamientoEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoConsultaAgendamientoEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

/**
 * The Class AgendaDestinatarioDAOTest.
 *
 * @author
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        AgendaDestinatarioDAOTest.AgendaDestinatarioDAOTestConfiguration.class })
@TestPropertySource(properties = {
		   "CANAL.BANCA.PRIVADA=79",
		   "SUB.CANAL.BANCA.PRIVADA=00",
           "RECIPIENTS-API.ENABLED.NUPS.LIST=-",
           "RECIPIENTS-API.BASEPATH.URL=basepath",
           "RECIPIENTS-API.SEC.ID=clientId",
           "RECIPIENTS-API.RECIPIENTS.FETCH.LIMIT=40",
           "RECIPIENTS-API.TOKEN.PROVIDER.URL=providerUrl"
		})
public class AgendaDestinatarioDAOTest extends IatxBaseDAOTest {

    /** The fondo DAO. */
    @Autowired
    private AgendaDestinatarioDAO agendaDestinatarioDAO;

    /** The cliente. */
    private Cliente cliente;
    
	/** The canal banca privada. */
    @Value("${CANAL.BANCA.PRIVADA}")
    String canalBP;
    
	/** The sub canal banca privada. */
    @Value("${SUB.CANAL.BANCA.PRIVADA}")
    String subCanalBP;

    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("1234");
    }

    /**
     * The Class AgendaDestinatarioDAOTest.
     */
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao"})
    public static class AgendaDestinatarioDAOTestConfiguration {

        @Bean
        RecipientsApi recipientsApi() {
            return Mockito.mock(RecipientsApiClient.class);
        }

        @Bean
        MetricBuilder metricBuilder() {
            return Mockito.mock(MetricBuilderImpl.class);
        }

        @Bean
        MetricRegisterBO metricRegisterBO() {
            return Mockito.mock(MetricRegisterBOImpl.class);
        }

        @Bean
        AgendaDestinatarioDAO agendaDestinatarioDAO() {
            return new AgendaDestinatarioDAOImpl();
        }
        
    }

    /**
     * Consulta la agenda de destinatarios.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarAgendaDestinatarioTest() throws IatxException, DAOException {
        String servicio = "CNSAGEDEST";
        String version = "120";
        String tramaResponsePrimera = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF35886000000000000CNSAGEDEST1100000            22222225    00        00X000000000201611021214060000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0162100000000õ õSõ004õ01õ0176õ002000068147õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-12.34.32.247729õ04õ99  õ                õ                          õRIOõ02õ0059õ007003919305õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONTA ZEPEDA, VULPIANO ANDRES CUNIBALDO                         õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.54.16.272637õ04õ99  õ                õ                          õRIOõ02õ0121õ007000336347õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONSOLO DE NOVO, NIL CLOE                                       õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.59.21.167293õ04õ99  õ                õ                          õRIOõ02õ0365õ007003508211õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-11.21.50.587706õ04õ99  õ                õ                          õRIOõ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherPaginadoAgendaDestinatario(servicio, version, "PR"))))
                .thenReturn(tramaResponsePrimera);

        String tramaResponseSegunda = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF35886000000000000CNSAGEDEST1100000            22222225    00        00X000000000201611021214060000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0162100000000õ õNõ004õ01õ0176õ002000068147õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-12.34.32.247729õ04õ99  õ                õ                          õRIOõ02õ0059õ007003919305õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONTA ZEPEDA, VULPIANO ANDRES CUNIBALDO                         õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.54.16.272637õ04õ99  õ                õ                          õRIOõ02õ0121õ007000336347õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONSOLO DE NOVO, NIL CLOE                                       õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.59.21.167293õ04õ99  õ                õ                          õRIOõ02õ0365õ007003508211õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-11.21.50.587706õ04õ99  õ                õ                          õRIOõ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherPaginadoAgendaDestinatario(servicio, version, "CN"))))
                .thenReturn(tramaResponseSegunda);

        ConsultaAgendaDestinatarioInEntity entity = new ConsultaAgendaDestinatarioInEntity();
        entity.setCliente(cliente);

        entity.setTipoConsulta(TipoConsultaAgendamientoEnum.LISTADO.getCampo());

        entity.setLlamada(LlamadaAgendamientoEnum.PRIMERA.getCampo());

        entity.setTipoAgenda(TipoAgendaEnum.TODAS_LAS_AGENDAS.getCampo());

        ConsultaAgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.consultar(entity);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Consulta la agenda de destinatarios con un parametro de entrada con datos
     * erroneos.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarAgendaDestinatarioErrorValidacionTest() throws IatxException, DAOException {
        ConsultaAgendaDestinatarioInEntity entity = new ConsultaAgendaDestinatarioInEntity();
        entity.setCliente(cliente);

        entity.setTipoConsulta("N");

        entity.setLlamada(LlamadaAgendamientoEnum.PRIMERA.getCampo());

        entity.setTipoAgenda(TipoAgendaEnum.TODAS_LAS_AGENDAS.getCampo());

        ConsultaAgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.consultar(entity);
        Assert.assertEquals("0001", respuesta.getCodigoRetornoExtendido());
    }

    /**
     * Consulta la agenda de destinatarios, y lanza una excepcion de tipo
     * IatxException al hacerlo, el resultado esperado es un DAOException.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = DAOException.class)
    public void consultarAgendaDestinatarioIatxExceptionTest() throws IatxException, DAOException {
        ConsultaAgendaDestinatarioInEntity entity = new ConsultaAgendaDestinatarioInEntity();
        String servicio = "CNSAGEDEST";
        String version = "120";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherPaginadoAgendaDestinatario(servicio, version, "PR"))))
                .thenThrow(new IatxException());

        entity.setCliente(cliente);
        entity.setTipoConsulta(TipoConsultaAgendamientoEnum.LISTADO.getCampo());
        entity.setLlamada(LlamadaAgendamientoEnum.PRIMERA.getCampo());
        entity.setTipoAgenda(TipoAgendaEnum.TODAS_LAS_AGENDAS.getCampo());

        agendaDestinatarioDAO.consultar(entity);
    }

    /**
     * Consulta la agenda de destinatarios con informacion parcial.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultarAgendaDestinatarioParcialTest() throws IatxException, DAOException {
        String servicio = "CNSAGEDEST";
        String version = "120";

        String tramaResponsePrimera = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF35886000000000000CNSAGEDEST1100000            22222225    00        00X000000000201611021214060000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0162100000000õ õSõ004õ01õ0176õ002000068147õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-12.34.32.247729õ04õ99  õ                õ                          õRIOõ02õ0059õ007003919305õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONTA ZEPEDA, VULPIANO ANDRES CUNIBALDO                         õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.54.16.272637õ04õ99  õ                õ                          õRIOõ02õ0121õ007000336347õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONSOLO DE NOVO, NIL CLOE                                       õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.59.21.167293õ04õ99  õ                õ                          õRIOõ02õ0365õ007003508211õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-11.21.50.587706õ04õ99  õ                õ                          õRIOõ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherPaginadoAgendaDestinatario(servicio, version, "PR"))))
                .thenReturn(tramaResponsePrimera);

        String tramaResponseSegunda = "200000000000P04HTML0009900010303MKNM33  ********00454776000000132017010509572900000000        346000000000CNSAGEDEST1100000            03203233    00        00 000000000201701050957240000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ000ERROR GENERICO DEL SERVICIO                                               õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherPaginadoAgendaDestinatario(servicio, version, "CN"))))
                .thenReturn(tramaResponseSegunda);

        ConsultaAgendaDestinatarioInEntity entity = new ConsultaAgendaDestinatarioInEntity();
        entity.setCliente(cliente);

        entity.setTipoConsulta(TipoConsultaAgendamientoEnum.LISTADO.getCampo());

        entity.setLlamada(LlamadaAgendamientoEnum.PRIMERA.getCampo());

        entity.setTipoAgenda(TipoAgendaEnum.TODAS_LAS_AGENDAS.getCampo());

        ConsultaAgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.consultar(entity);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Agrega un destinatario a la agenda.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void agregarDestinatarioAAgendaTest() throws IatxException, DAOException {
        String servicio = "ACTAGEDEST";
        String version = "110";
        String tramaResponse = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF34132000000000000ACTAGEDEST1000000            22222225    00        00X000000000201611011234320000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0000900000000õ";
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        AgendaDestinatarioInEntity entity = new AgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        // A02
        entity.setTipoCuentaDestinatario("02");
        // A04
        entity.setSucursalCuentaDestinatario("0027");
        // A12
        entity.setNumeroCuentaDestinatario("000001234567");
        // A22
        entity.setCbuDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        // A50
//        entity.setBancoDestinatario(
//                BancoEnum.SANTANDER_RIO.getDescripcion() + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 37));
        // A02
        entity.setTipoDocumentoDestinatario(TipoDocumentoEnum.CUIL.getCampo() + ISBANStringUtils.ESPACIO_STRING);
        // A11
        entity.setDocumentoDestinatario("30173299881");
        // A30
        entity.setDescripcionCuentaDestinatario("Contador" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        // A10
        entity.setCaracteristicasCuentaDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 10));
        // A64
        entity.setTitular("MUÑOZ, CÉSAR ALBERTO." + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 43));
        // A100
        entity.setDireccionCorreo("cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 85));
        // A16
        entity.setTelefonoDestinatario(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 16));
        // A03
        entity.setTipoAgendaOcurrencia(TipoAgendaEnum.AGENDA_RIO.getCampo());
        // A15
        String ip = "123456789012345";

        AgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.eliminarUAgregar(entity, ip,
                TipoOperacionACTAGEDESTEnum.ALTA);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("00000000", respuesta.getCodigoRetornoExtendido());
        Assert.assertEquals(
                "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF34132000000000000ACTAGEDEST1000000            22222225    00        00X000000000201611011234320000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DH",
                respuesta.getHeaderTrama());
        Assert.assertEquals("20161101", respuesta.getFecha());
        Assert.assertEquals("12:34", respuesta.getHora());
        Assert.assertEquals("00000000", respuesta.getNroComprobante());
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    /**
     * Agregar destinatario a agenda pero falla el validate de las expresiones
     * regulares.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void agregarDestinatarioAAgendaFallaValidateTest() throws IatxException, DAOException {
        String servicio = "ACTAGEDEST";
        String version = "100";
        String tramaResponse = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF34132000000000000ACTAGEDEST1000000            22222225    00        00X000000000201611011234320000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0000900000000õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        AgendaDestinatarioInEntity entity = new AgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        // A12
        entity.setNumeroCuentaDestinatario("000001000000234567");
        // A15
        String ip = "123456789012345";

        AgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.eliminarUAgregar(entity, ip,
                TipoOperacionACTAGEDESTEnum.ALTA);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("0001", respuesta.getCodigoRetornoExtendido());
        Assert.assertNull(respuesta.getHeaderTrama());
        Assert.assertNull(respuesta.getFecha());
        Assert.assertNull(respuesta.getHora());
        Assert.assertNull(respuesta.getNroComprobante());
    }

    /**
     * Agregar destinatario A agenda falla email validate formato invalido test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void agregarDestinatarioAAgendaFallaEmailValidateFormatoInvalidoTest() throws IatxException, DAOException {
        String servicio = "ACTAGEDEST";
        String version = "100";
        String tramaResponse = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF34132000000000000ACTAGEDEST1000000            22222225    00        00X000000000201611011234320000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0000900000000õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        AgendaDestinatarioInEntity entity = new AgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        // A100
        entity.setDireccionCorreo("cesar@gmail.com@" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 84));
        // A15
        String ip = "123456789012345";

        AgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.eliminarUAgregar(entity, ip,
                TipoOperacionACTAGEDESTEnum.ALTA);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("0001", respuesta.getCodigoRetornoExtendido());
        Assert.assertNull(respuesta.getHeaderTrama());
        Assert.assertNull(respuesta.getFecha());
        Assert.assertNull(respuesta.getHora());
        Assert.assertNull(respuesta.getNroComprobante());
    }

    /**
     * Agregar destinatario A agenda falla email validate con espacio inicial
     * test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void agregarDestinatarioAAgendaFallaEmailValidateConEspacioInicialTest() throws IatxException, DAOException {
        String servicio = "ACTAGEDEST";
        String version = "100";
        String tramaResponse = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF34132000000000000ACTAGEDEST1000000            22222225    00        00X000000000201611011234320000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0000900000000õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        AgendaDestinatarioInEntity entity = new AgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        // A100
        entity.setDireccionCorreo(" cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 84));
        // A15
        String ip = "123456789012345";

        AgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.eliminarUAgregar(entity, ip,
                TipoOperacionACTAGEDESTEnum.ALTA);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("0001", respuesta.getCodigoRetornoExtendido());
        Assert.assertNull(respuesta.getHeaderTrama());
        Assert.assertNull(respuesta.getFecha());
        Assert.assertNull(respuesta.getHora());
        Assert.assertNull(respuesta.getNroComprobante());
    }
    
    /**
     * test de getter y setter de ValidacionCuenta
     */
    @Test
    public void getterSetterValidacionCuentaTest(){
        ValidacionCuentaOutCBUEntity val = new ValidacionCuentaOutCBUEntity("123");
        val.setHeaderTrama("headerTrama");
        val.setCuit2("cuit2");
        val.setCuit3("cuit3");
        val.setLongDestino("longDestino");
        val.setCuentaDestino("cuentaDestino");
        val.setTipoCuentaToBanelco("toBanelco");
        val.setTipoCuentaFromBanelco("fromBanelco");
        val.setBancoReceptor("bancoReceptor");
        val.setFiid("fiid");
        val.setUser("user");
        Assert.assertEquals("123", val.getCodigoRetornoExtendido());
        Assert.assertEquals("headerTrama", val.getHeaderTrama());
        Assert.assertEquals("cuit2", val.getCuit2());
        Assert.assertEquals("cuit3", val.getCuit3());
        Assert.assertEquals("longDestino", val.getLongDestino());
        Assert.assertEquals("cuentaDestino", val.getCuentaDestino());
        Assert.assertEquals("toBanelco", val.getTipoCuentaToBanelco());
        Assert.assertEquals("fromBanelco", val.getTipoCuentaFromBanelco());
        Assert.assertEquals("bancoReceptor", val.getBancoReceptor());
        Assert.assertEquals("fiid", val.getFiid());
        Assert.assertEquals("user", val.getUser());
        
        ValidacionCuentaOutCBUEntity val2 = new ValidacionCuentaOutCBUEntity("456");
        Assert.assertTrue(!val2.equals(val));
        Assert.assertTrue(val.equals(val));
        val.hashCode();//Para cubrir
     }

    /**
     * Agregar destinatario A agenda tipo cuenta no valido test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void agregarDestinatarioAAgendaTipoCuentaNoValidoTest() throws IatxException, DAOException {
        String servicio = "ACTAGEDEST";
        String version = "110";
        String tramaResponse = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF33836000000000000ACTAGEDEST1000000            22222225    00        00X000000000201612161044360000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0036810014001õCNLõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õTIPO DE CUENTA RIO INFORMADA NO VALIDA                                          õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        AgendaDestinatarioInEntity entity = new AgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        // A100
        entity.setDireccionCorreo("cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 85));
        // A15
        String ip = "123456789012345";

        AgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.eliminarUAgregar(entity, ip,
                TipoOperacionACTAGEDESTEnum.ALTA);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("10014001", respuesta.getCodigoRetornoExtendido());
        Assert.assertNull(respuesta.getHeaderTrama());
        Assert.assertNull(respuesta.getFecha());
        Assert.assertNull(respuesta.getHora());
        Assert.assertNull(respuesta.getNroComprobante());
    }

    /**
     * Agregar destinatario A agenda ya existe cuenta agendada test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void agregarDestinatarioAAgendaYaExisteCuentaAgendadaTest() throws IatxException, DAOException {
        String servicio = "ACTAGEDEST";
        String version = "110";
        String tramaResponse = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF34071000000000000ACTAGEDEST1000000            22222225    00        00X000000000201612161048150000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0036810011634õCNLõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õLA CUENTA QUE QUIERE AGREGAR YA SE ENCUENTRA AGENDADA                           õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        AgendaDestinatarioInEntity entity = new AgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        // A100
        entity.setDireccionCorreo("cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 85));
        // A15
        String ip = "123456789012345";

        AgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.eliminarUAgregar(entity, ip,
                TipoOperacionACTAGEDESTEnum.ALTA);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("10011634", respuesta.getCodigoRetornoExtendido());
        Assert.assertNull(respuesta.getHeaderTrama());
        Assert.assertNull(respuesta.getFecha());
        Assert.assertNull(respuesta.getHora());
        Assert.assertNull(respuesta.getNroComprobante());
    }

    /**
     * Agregar destinatario A agenda cuenta inexistente test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void agregarDestinatarioAAgendaCuentaInexistenteTest() throws IatxException, DAOException {
        String servicio = "ACTAGEDEST";
        String version = "110";
        String tramaResponse = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF30375000000000000ACTAGEDEST1000000            22222225    00        00X000000000201611011422050000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0036810000054õCNLõ03õEl tipo, sucursal o numero de cuenta no es valida. Por favor, verifique los datos ingresados.                                                                                                                                                                 õCTA INEXISTENTEõ54 ANIX509 INFORMA CTA. NO MIGRAD                                               õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        AgendaDestinatarioInEntity entity = new AgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        // A100
        entity.setDireccionCorreo("cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 85));
        // A15
        String ip = "123456789012345";

        AgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.eliminarUAgregar(entity, ip,
                TipoOperacionACTAGEDESTEnum.ALTA);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("10000054", respuesta.getCodigoRetornoExtendido());
        Assert.assertNull(respuesta.getHeaderTrama());
        Assert.assertNull(respuesta.getFecha());
        Assert.assertNull(respuesta.getHora());
        Assert.assertNull(respuesta.getNroComprobante());
    }

    /**
     * Agregar destinatario A agenda iatx exception test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = DAOException.class)
    public void agregarDestinatarioAAgendaIatxExceptionTest() throws IatxException, DAOException {
        String servicio = "ACTAGEDEST";
        String version = "110";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        AgendaDestinatarioInEntity entity = new AgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        // A100
        entity.setDireccionCorreo("cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 85));
        // A15
        String ip = "123456789012345";

        agendaDestinatarioDAO.eliminarUAgregar(entity, ip, TipoOperacionACTAGEDESTEnum.ALTA);
    }

    /**
     * Validar cuenta ok test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void validarCuentaOkTest() throws IatxException, DAOException {
        String servicio = "CNSCTATIT_";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00392277000000122016082217061100000000IBF22911000000000000CNSCTATIT_1100000            02615492    00        00 017035981201608221706060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006400000000õCONSOLO DE NOVO  NIL CLOE     õT õ27064859787õ02616733õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ValidacionCuentaInEntity entity = new ValidacionCuentaInEntity();
        entity.setCliente(new Cliente());
        DatosCliente res = agendaDestinatarioDAO.validarCuenta(entity);
        Assert.assertTrue(0 == res.getCodigoError());
    }

    /**
     * Validar cuenta error cuenta inexistente test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void validarCuentaErrorCuentaInexistenteTest() throws IatxException, DAOException {
        String servicio = "CNSCTATIT_";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00392237000000122016081216321400000000IBF24928000000000000CNSCTATIT_1100000            02615492    00        00 016337687201608121632090000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000054õCNLõ03õEl tipo, sucursal o numero de cuenta no es valida. Por favor, verifique los datos ingresados.                                                                                                                                                                 õCTA INEXISTENTEõ54 ANIX509 INFORMA CTA. NO MIGRAD                                               õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ValidacionCuentaInEntity entity = new ValidacionCuentaInEntity();
        entity.setCliente(new Cliente());
        DatosCliente res = agendaDestinatarioDAO.validarCuenta(entity);
        Assert.assertTrue(0 != res.getCodigoError());
    }

    /**
     * Validar cuenta iatx exception test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = DAOException.class)
    public void validarCuentaIatxExceptionTest() throws IatxException, DAOException {
        String servicio = "CNSCTATIT_";
        String version = "110";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        ValidacionCuentaInEntity entity = new ValidacionCuentaInEntity();
        entity.setCliente(new Cliente());
        agendaDestinatarioDAO.validarCuenta(entity);
    }

    /**
     * Validar cuenta transferencia CBU ok test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void validarCuentaTransferenciaCBUOkTest() throws IatxException, DAOException {
        String servicio = "CNSTITCBU_";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010300RONN15  ********00683633000000142016090612402400000000IBF28525000000000000CNSTITCBU_1100000            00743315    00        00 000000000201609061240200000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0018900000000õSIMON ZEITUNE E HIJO                                            õ30540487711õ           õ           õBANCO DEL TUCUMAN     õ16õ0000000100005005            õ01õ01õTUCUõ0000õ0000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ValidacionCuentaInCBUEntity entity = new ValidacionCuentaInCBUEntity();
        entity.setCliente(new Cliente());
        ValidacionCuentaOutCBUEntity res = agendaDestinatarioDAO.validarCuentaTransferenciaCBU(entity);
        Assert.assertTrue("00000000".equals(res.getCodigoRetornoExtendido()));
    }

    /**
     * Validar cuenta transferencia CBU error test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void validarCuentaTransferenciaCBUErrorTest() throws IatxException, DAOException {
        String servicio = "CNSTITCBU_";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010300RONN15  ********00683633000000182016090612404500000000IBF28545000000000000CNSTITCBU_1100000            00743315    00        00 000000000201609061240410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000072õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õERROR DE SISTEMA (LONGITUD DE MENSAJE ERRONEA)                                  õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ValidacionCuentaInCBUEntity entity = new ValidacionCuentaInCBUEntity();
        entity.setCliente(new Cliente());
        ValidacionCuentaOutCBUEntity res = agendaDestinatarioDAO.validarCuentaTransferenciaCBU(entity);
        Assert.assertTrue("10000072".equals(res.getCodigoRetornoExtendido()));
    }

    /**
     * Validar cuenta transferencia CBU iatx exception test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = DAOException.class)
    public void validarCuentaTransferenciaCBUIatxExceptionTest() throws IatxException, DAOException {
        String servicio = "CNSTITCBU_";
        String version = "110";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        ValidacionCuentaInCBUEntity entity = new ValidacionCuentaInCBUEntity();
        entity.setCliente(new Cliente());
        agendaDestinatarioDAO.validarCuentaTransferenciaCBU(entity);
    }

    @Test
    public void whenRecipientsApiThrowsException_thenGetRecipientsFromCoordinadorOk() throws IatxException, DAOException {

        String servicio = "CNSAGEDEST";
        String version = "120";
        String tramaResponsePrimera = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF35886000000000000CNSAGEDEST1100000            22222225    00        00X000000000201611021214060000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0162100000000õ õSõ004õ01õ0176õ002000068147õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-12.34.32.247729õ04õ99  õ                õ                          õRIOõ02õ0059õ007003919305õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONTA ZEPEDA, VULPIANO ANDRES CUNIBALDO                         õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.54.16.272637õ04õ99  õ                õ                          õRIOõ02õ0121õ007000336347õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONSOLO DE NOVO, NIL CLOE                                       õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.59.21.167293õ04õ99  õ                õ                          õRIOõ02õ0365õ007003508211õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-11.21.50.587706õ04õ99  õ                õ                          õRIOõ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherPaginadoAgendaDestinatario(servicio, version, "PR"))))
                .thenReturn(tramaResponsePrimera);

        String tramaResponseSegunda = "200000000000P04HTML00099500103FREEUSER  XXXXXXXX00000000000000010000000000000000000000IBF35886000000000000CNSAGEDEST1100000            22222225    00        00X000000000201611021214060000000000                            000000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  0000000000000XXXX  DHõ0162100000000õ õNõ004õ01õ0176õ002000068147õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-12.34.32.247729õ04õ99  õ                õ                          õRIOõ02õ0059õ007003919305õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONTA ZEPEDA, VULPIANO ANDRES CUNIBALDO                         õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.54.16.272637õ04õ99  õ                õ                          õRIOõ02õ0121õ007000336347õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCONSOLO DE NOVO, NIL CLOE                                       õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-13.59.21.167293õ04õ99  õ                õ                          õRIOõ02õ0365õ007003508211õ                      õSARASA SARASA SARASA SARASA   õ                                                  õ          õ           õ  õCARLOS FABRICIO LOPEZ SANCHEZ                                   õCARLOSSANCHEZ44@GMAIL.COM                                                                           õ2016-11-02-11.21.50.587706õ04õ99  õ                õ                          õRIOõ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcherPaginadoAgendaDestinatario(servicio, version, "CN"))))
                .thenReturn(tramaResponseSegunda);

        RecipientsApi recipientsApi = Mockito.mock(RecipientsApiClient.class);

        ReflectionTestUtils.setField(agendaDestinatarioDAO, "recipientsApi", recipientsApi);

        when(recipientsApi.getAllRecipients(anyString())).thenThrow(new RecipientsApiException(new HttpClientErrorException(HttpStatus.BAD_REQUEST)));

        ConsultaAgendaDestinatarioInEntity entity = new ConsultaAgendaDestinatarioInEntity();
        entity.setCliente(cliente);
        entity.getCliente().setNup("12349");

        entity.setTipoConsulta(TipoConsultaAgendamientoEnum.LISTADO.getCampo());

        entity.setLlamada(LlamadaAgendamientoEnum.PRIMERA.getCampo());

        entity.setTipoAgenda(TipoAgendaEnum.TODAS_LAS_AGENDAS.getCampo());

        ConsultaAgendaDestinatarioOutEntity respuesta = agendaDestinatarioDAO.consultar(entity);
        Assert.assertNotNull(respuesta);

    }

}