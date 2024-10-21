package ar.com.santanderrio.obp.servicios.agenda.destinatarios.manager;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.bval.jsr303.ApacheValidationProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AgendaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioAliasBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioCbuBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.EdicionDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.EliminacionDestinatarioAgendaBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioRSADTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.AgendaDestinatarioDTOMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConfiguracionAltaDestinatarioCBUDTOMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConfiguracionAltaDestinatarioInViewMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosRCAManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.impl.AgendaDestinatariosManagerImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioCBUOutView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioInView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioOutView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasInexistenteEliminadoException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.BanelcoDesafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasDesafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenDesafio;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class AgendaDestinatarioManagerTest.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendaDestinatariosManagerTest {

    /** The agenda manager. */
    @InjectMocks
    private AgendaDestinatariosManagerImpl agendaManager;

    /** The agenda BO. */
    @Mock
    private AgendaDestinatarioBO agendaDestinatarioBO;

    /** The agenda destinatario RSADTO. */
    @Mock
    private AgendaDestinatarioRSADTO agendaDestinatarioRSADTO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The agenda mock. */
    private AgendaDestinatarioDTOMock agendaMock = new AgendaDestinatarioDTOMock();

    /** The alta destinatario BO. */
    @Mock
    private AltaDestinatarioBO altaDestinatarioBO;

    /** Eliminacion agenda BO. */
    @Mock
    private EliminacionDestinatarioAgendaBO eliminacionAgendaBO;

    /** The casuistica. */
    @Mock
    private CasuisticaAgendaDestinatarios casuistica;

    /** The sesion. */
    @Mock
    private SesionAgendaDestinatarios sesionAgenda;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The alta CBUBO. */
    @Mock
    private AltaDestinatarioCbuBO altaCBUBO;

    /** The edicion bo. */
    @Mock
    private EdicionDestinatarioBO edicionDestinatarioBO;

    /** The alta destinatario alias BO. */
    @Mock
    private AltaDestinatarioAliasBO altaDestinatarioAliasBO;

    /** The validator. */
    @Mock
    private Validator validator;

    /** The agenda destinatarios RCA Manager. */
    @Mock
    private AgendaDestinatariosRCAManager agendaDestinatariosRCAManager;

    /** The Constant REINTENTAR. */
    private static final String REINTENTAR = "reintentar";

    /** The Constant CONTINUAR. */
    private static final String CONTINUAR = "continuar";

    /**
     * Inits the mocks.
     *
     */
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        ValidatorFactory validatorFactory = Validation.byProvider(ApacheValidationProvider.class).configure()
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * Cuando invoca obtener agenda destinatarios obtengo respuesta OK agenda
     * destinatario view.
     */
    @Test
    public void cuandoInvocaObtenerAgendaDestinatariosObtengoRespuestaOKAgendaDestinatarioView() {
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(
                agendaDestinatarioBO.obtenerAgendaDestinatarios(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(obtenerRespuestaAgendaDTO());
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);

        Mockito.when(dato.getFiltroDestinatario()).thenReturn(false);
        Respuesta<AgendaDestinatarioView> respAgendaView = agendaManager.obtenerAgendaDestinatarios(dato);

        Assert.assertEquals(EstadoRespuesta.OK, respAgendaView.getEstadoRespuesta());

        Assert.assertEquals(BancoEnum.SANTANDER_RIO.getDescripcion(), respAgendaView.getRespuesta().getListaDestinatarios().get(0).getBanco());
        Assert.assertEquals("23456789012345678901",
                respAgendaView.getRespuesta().getListaDestinatarios().get(0).getCbu());
        Assert.assertEquals("202345676792",
                respAgendaView.getRespuesta().getListaDestinatarios().get(0).getCuitOCuil());
        Assert.assertEquals("FE", respAgendaView.getRespuesta().getListaDestinatarios().get(0).getIniciales());
        Assert.assertEquals("123-678901/2",
                respAgendaView.getRespuesta().getListaDestinatarios().get(0).getNroCuenta());
        Assert.assertEquals("Fer Unica",
                respAgendaView.getRespuesta().getListaDestinatarios().get(0).getReferenciaApodo());
        Assert.assertEquals("Cuenta única",
                respAgendaView.getRespuesta().getListaDestinatarios().get(0).getTipoCuenta());
        Assert.assertEquals("Lopez Fernando",
                respAgendaView.getRespuesta().getListaDestinatarios().get(0).getTitular());
        Assert.assertEquals("RIO", respAgendaView.getRespuesta().getListaDestinatarios().get(0).getTipoDestinatario());

        Assert.assertEquals(BancoEnum.SANTANDER_RIO.getDescripcion(), respAgendaView.getRespuesta().getListaDestinatarios().get(1).getBanco());
        Assert.assertEquals("12345678901234567890",
                respAgendaView.getRespuesta().getListaDestinatarios().get(1).getCbu());
        Assert.assertEquals("202345676792",
                respAgendaView.getRespuesta().getListaDestinatarios().get(1).getCuitOCuil());
        Assert.assertEquals("LO", respAgendaView.getRespuesta().getListaDestinatarios().get(1).getIniciales());
        Assert.assertEquals("123-456789/1",
                respAgendaView.getRespuesta().getListaDestinatarios().get(1).getNroCuenta());
        Assert.assertNull(respAgendaView.getRespuesta().getListaDestinatarios().get(1).getReferenciaApodo());
        Assert.assertEquals("Caja de ahorro en $",
                respAgendaView.getRespuesta().getListaDestinatarios().get(1).getTipoCuenta());
        Assert.assertEquals("Lopez Fernando",
                respAgendaView.getRespuesta().getListaDestinatarios().get(1).getTitular());
        Assert.assertEquals("RIO", respAgendaView.getRespuesta().getListaDestinatarios().get(1).getTipoDestinatario());

        Assert.assertEquals("Banco Patagonia", respAgendaView.getRespuesta().getListaDestinatarios().get(2).getBanco());
        Assert.assertEquals("0070000000001234567890",
                respAgendaView.getRespuesta().getListaDestinatarios().get(2).getCbu());
        Assert.assertEquals("002000068148",
                respAgendaView.getRespuesta().getListaDestinatarios().get(2).getCuitOCuil());
        Assert.assertEquals("AA", respAgendaView.getRespuesta().getListaDestinatarios().get(2).getIniciales());
        Assert.assertEquals("212-123456789/0",
                respAgendaView.getRespuesta().getListaDestinatarios().get(2).getNroCuenta());
        Assert.assertEquals("AAA", respAgendaView.getRespuesta().getListaDestinatarios().get(2).getReferenciaApodo());
        Assert.assertEquals("Cuenta corriente en $",
                respAgendaView.getRespuesta().getListaDestinatarios().get(2).getTipoCuenta());
        Assert.assertEquals("Gerardo S. A.", respAgendaView.getRespuesta().getListaDestinatarios().get(2).getTitular());
        Assert.assertEquals("RIO", respAgendaView.getRespuesta().getListaDestinatarios().get(2).getTipoDestinatario());

        Assert.assertEquals("Banco Francés", respAgendaView.getRespuesta().getListaDestinatarios().get(3).getBanco());
        Assert.assertEquals("00113579246802468013579",
                respAgendaView.getRespuesta().getListaDestinatarios().get(3).getCbu());
        Assert.assertEquals("002000068147",
                respAgendaView.getRespuesta().getListaDestinatarios().get(3).getCuitOCuil());
        Assert.assertEquals("TR", respAgendaView.getRespuesta().getListaDestinatarios().get(3).getIniciales());
        Assert.assertEquals("122-246801357/9",
                respAgendaView.getRespuesta().getListaDestinatarios().get(3).getNroCuenta());
        Assert.assertEquals("Transporte",
                respAgendaView.getRespuesta().getListaDestinatarios().get(3).getReferenciaApodo());
        Assert.assertEquals("Caja de ahorro en $",
                respAgendaView.getRespuesta().getListaDestinatarios().get(3).getTipoCuenta());
        Assert.assertEquals("Transporte Carga Pesada S. A.",
                respAgendaView.getRespuesta().getListaDestinatarios().get(3).getTitular());
        Assert.assertEquals("RIO", respAgendaView.getRespuesta().getListaDestinatarios().get(3).getTipoDestinatario());

        Assert.assertEquals("Banco Nación", respAgendaView.getRespuesta().getListaDestinatarios().get(4).getBanco());
        Assert.assertEquals("0080000000002345678901",
                respAgendaView.getRespuesta().getListaDestinatarios().get(4).getCbu());
        Assert.assertEquals("002000068149",
                respAgendaView.getRespuesta().getListaDestinatarios().get(4).getCuitOCuil());
        Assert.assertEquals("ZA", respAgendaView.getRespuesta().getListaDestinatarios().get(4).getIniciales());
        Assert.assertEquals("345-234567890/1",
                respAgendaView.getRespuesta().getListaDestinatarios().get(4).getNroCuenta());
        Assert.assertNull(respAgendaView.getRespuesta().getListaDestinatarios().get(4).getReferenciaApodo());
        Assert.assertEquals("Caja de ahorro en $",
                respAgendaView.getRespuesta().getListaDestinatarios().get(4).getTipoCuenta());
        Assert.assertEquals("ZAutomóvil Club Argentino",
                respAgendaView.getRespuesta().getListaDestinatarios().get(4).getTitular());
        Assert.assertEquals("RIO", respAgendaView.getRespuesta().getListaDestinatarios().get(4).getTipoDestinatario());

    }

    /**
     * Obtener agenda destinatarios warning test.
     */
    @Test
    public void obtenerAgendaDestinatariosWarningTest() {
        Respuesta<AgendaDestinatarioDTO> toRet = obtenerRespuestaAgendaDTO();
        toRet.setEstadoRespuesta(EstadoRespuesta.WARNING);
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);

        Mockito.when(dato.getFiltroDestinatario()).thenReturn(false);
        Mockito.when(
                agendaDestinatarioBO.obtenerAgendaDestinatarios(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(toRet);
        Respuesta<AgendaDestinatarioView> respAgendaView = agendaManager.obtenerAgendaDestinatarios(dato);
        Assert.assertEquals(EstadoRespuesta.WARNING, respAgendaView.getEstadoRespuesta());
    }

    /**
     * Obtener agenda destinatarios error test.
     */
    @Test
    public void obtenerAgendaDestinatariosErrorTest() {
        Respuesta<AgendaDestinatarioDTO> toRet = obtenerRespuestaAgendaDTO();
        toRet.setEstadoRespuesta(EstadoRespuesta.ERROR);
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);

        Mockito.when(dato.getFiltroDestinatario()).thenReturn(false);
        Mockito.when(
                agendaDestinatarioBO.obtenerAgendaDestinatarios(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(toRet);
        Respuesta<AgendaDestinatarioView> respAgendaView = agendaManager.obtenerAgendaDestinatarios(dato);
        Assert.assertEquals(EstadoRespuesta.ERROR, respAgendaView.getEstadoRespuesta());
        agendaManager.grabarEstadisticaIngresoAltaDestinatarioRio();
    }

    /**
     * Cuando invoca obtener agenda destinatarios filtro OK test.
     */
    @Test
    public void cuandoInvocaObtenerAgendaDestinatariosFiltroOKTest() {
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(
                agendaDestinatarioBO.obtenerAgendaDestinatarios(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(obtenerRespuestaAgendaDTO());
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);

        Mockito.when(dato.getFiltroDestinatario()).thenReturn(true);
        Respuesta<AgendaDestinatarioView> respAgendaView = agendaManager.obtenerAgendaDestinatarios(dato);

        Assert.assertEquals(EstadoRespuesta.OK, respAgendaView.getEstadoRespuesta());
    }

    /**
     * Obtener agenda destinatarios filtro warning test.
     */
    @Test
    public void obtenerAgendaDestinatariosFiltroWarningTest() {
        Respuesta<AgendaDestinatarioDTO> toRet = obtenerRespuestaAgendaDTO();
        toRet.setEstadoRespuesta(EstadoRespuesta.WARNING);
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);

        Mockito.when(dato.getFiltroDestinatario()).thenReturn(true);
        Mockito.when(
                agendaDestinatarioBO.obtenerAgendaDestinatarios(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(toRet);
        Respuesta<AgendaDestinatarioView> respAgendaView = agendaManager.obtenerAgendaDestinatarios(dato);
        Assert.assertEquals(EstadoRespuesta.WARNING, respAgendaView.getEstadoRespuesta());
    }

    /**
     * Obtener agenda destinatarios filtro error test.
     */
    @Test
    public void obtenerAgendaDestinatariosFiltroErrorTest() {
        Respuesta<AgendaDestinatarioDTO> toRet = obtenerRespuestaAgendaDTO();
        toRet.setEstadoRespuesta(EstadoRespuesta.ERROR);
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);

        Mockito.when(dato.getFiltroDestinatario()).thenReturn(true);
        Mockito.when(
                agendaDestinatarioBO.obtenerAgendaDestinatarios(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(toRet);
        Respuesta<AgendaDestinatarioView> respAgendaView = agendaManager.obtenerAgendaDestinatarios(dato);
        Assert.assertEquals(EstadoRespuesta.ERROR, respAgendaView.getEstadoRespuesta());
        agendaManager.grabarEstadisticaIngresoAltaDestinatarioRio();
    }

    /**
     * Grabar estadistica detalle agenda.
     */
    @Test
    public void grabarEstadisticaDetalleAgenda() {
        agendaManager.grabarEstadisticaDetalleAgenda("ALIAS");
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(
                EstadisticasConstants.VER_DETALLE_ALTA_DESTINATARIO_ALIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        agendaManager.grabarEstadisticaDetalleAgenda("RIO");
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.ACCESO_VER_DETALLE_AGENDA_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        agendaManager.grabarEstadisticaDetalleAgenda("OTROS_BANCOS");
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.ACCESO_VER_DETALLE_OTROS_BANCOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        agendaManager.grabarEstadisticaDetalleAgenda("ENVIO_EFECTIVO");
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(
                EstadisticasConstants.ACCESO_VER_DETALLE_ENVIO_EFECTIVO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        agendaManager.grabarEstadisticaDetalleAgenda("PEPE");

    }

    /**
     * Obtener informacion destinatario test.
     */
    @Test
    public void obtenerInformacionDestinatarioTest() {
        // Validacion error
        Respuesta<ConfiguracionAltaDestinatarioInView> toRet = new Respuesta<ConfiguracionAltaDestinatarioInView>();
        Respuesta<Object> error = new Respuesta<Object>();
        error.setEstadoRespuesta(EstadoRespuesta.ERROR);
        toRet.setEstadoRespuesta(EstadoRespuesta.WARNING);
        Mockito.doReturn(toRet).when(respuestaFactory).validate(Matchers.any());
        Mockito.when(casuistica.respuestaErrorAltaConfiguracionCuentaInvalida()).thenReturn(error);
        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager
                .derivarServicioAltaCorrespondiente(new ConfiguracionAltaDestinatarioInView(), "User-Agent");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        // Validacion OK respuesta OK
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<ConfiguracionAltaDestinatarioDTO> resConfig = new Respuesta<ConfiguracionAltaDestinatarioDTO>();
        resConfig.setEstadoRespuesta(EstadoRespuesta.OK);
        resConfig.setRespuesta(new ConfiguracionAltaDestinatarioDTO());
        Mockito.when(altaDestinatarioBO.continuarConfiguracionAltaDestinatario(Matchers.any(Cliente.class),
                Matchers.anyString(), Matchers.anyString())).thenReturn(resConfig);

        res = agendaManager.derivarServicioAltaCorrespondiente(new ConfiguracionAltaDestinatarioInView(), "User-Agent");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

        // Caso warning
        resConfig.setEstadoRespuesta(EstadoRespuesta.WARNING);
        res = agendaManager.derivarServicioAltaCorrespondiente(new ConfiguracionAltaDestinatarioInView(), "User-Agent");
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());

        // Caso error
        resConfig.setEstadoRespuesta(EstadoRespuesta.ERROR);
        res = agendaManager.derivarServicioAltaCorrespondiente(new ConfiguracionAltaDestinatarioInView(), "User-Agent");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta agenda DTO.
     *
     * @return the respuesta
     */
    private Respuesta<AgendaDestinatarioDTO> obtenerRespuestaAgendaDTO() {
        Respuesta<AgendaDestinatarioDTO> respAgenda = new Respuesta<AgendaDestinatarioDTO>();
        respAgenda.setEstadoRespuesta(EstadoRespuesta.OK);
        respAgenda.setRespuesta(agendaMock.obtenerAgendaDestinatarioDTO());
        respAgenda.setRespuestaVacia(Boolean.FALSE);
        return respAgenda;
    }

    /**
     * Obtener agenda destinatarios exception test.
     */
    @Test
    public void obtenerAgendaDestinatariosExceptionTest2() {
        Respuesta<Object> respuestaError = new Respuesta<Object>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(casuistica.respuestaErrorServicios()).thenReturn(respuestaError);
        DatosEntradaAgendaDestinatario dato = Mockito.mock(DatosEntradaAgendaDestinatario.class);

        Mockito.when(dato.getFiltroDestinatario()).thenReturn(false);
        Mockito.when(dato.getGrabaEstadisticas()).thenReturn(true);
        Mockito.when(
                agendaDestinatarioBO.obtenerAgendaDestinatarios(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenThrow(new NullPointerException());

        Respuesta<AgendaDestinatarioView> res = agendaManager.obtenerAgendaDestinatarios(dato);
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.INICIO_AGENDA_DESTINATARIOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    // TESTS DE COBERTURA DE VIEWS Y ENTITIES USADOS EN EL SEI Y MANAGER

    /**
     * Comprobante alta destinatario view hash equal string test.
     */
    @Test
    public void comprobanteAltaDestinatarioViewHashEqualStringTest() {
        ConfirmacionAltaDestinatarioView view = new ConfirmacionAltaDestinatarioView();
        view.setNroComprobante("comprobante");
        view.setFecha("25/2/2017");
        view.setHora("hora");
        Assert.assertNotEquals(view.hashCode(), 0);
        ConfirmacionAltaDestinatarioView comprobanteAltaDestinatarioView2 = new ConfirmacionAltaDestinatarioView();
        Assert.assertTrue(!view.equals(comprobanteAltaDestinatarioView2));
        Assert.assertTrue(view.equals(view));
    }

    /**
     * Configuracion alta destinatario out view setter getter test.
     */
    @Test
    public void configuracionAltaDestinatarioOutViewSetterGetterTest() {
        ConfiguracionAltaDestinatarioOutView configuracionAltaDestinatarioOutView = new ConfiguracionAltaDestinatarioOutView(
                new ConfiguracionAltaDestinatarioDTO());
        configuracionAltaDestinatarioOutView.setNumeroCuil("nroCuil");
        configuracionAltaDestinatarioOutView.setTitular("titular");
        Assert.assertEquals("nroCuil", configuracionAltaDestinatarioOutView.getNumeroCuil());
        Assert.assertEquals("titular", configuracionAltaDestinatarioOutView.getTitular());
        ConfiguracionAltaDestinatarioOutView configuracionAltaDestinatarioOutView2 = new ConfiguracionAltaDestinatarioOutView(
                new ConfiguracionAltaDestinatarioDTO());
        // Equals
        Assert.assertTrue(!configuracionAltaDestinatarioOutView.equals(configuracionAltaDestinatarioOutView2));
        Assert.assertTrue(configuracionAltaDestinatarioOutView.equals(configuracionAltaDestinatarioOutView));

    }

    /**
     * Confirmacion alta destinatario in view setter getter test.
     */
    @Test
    public void confirmacionAltaDestinatarioInViewSetterGetterTest() {
        ConfirmacionAltaDestinatarioView confirmacion = new ConfirmacionAltaDestinatarioView();
        confirmacion.setReferencia("descripcion");
        confirmacion.setDireccionCorreo("direccionCorreo");
        confirmacion.setNumeroDocumento("dni");
        confirmacion.setNroCuenta("nroCuenta");
        confirmacion.setIdTipoCuenta("tipoCuenta");
        confirmacion.setIsCuit(true);
        confirmacion.setTitular("titular");
        Assert.assertEquals("descripcion", confirmacion.getReferencia());
        Assert.assertEquals("direccionCorreo", confirmacion.getDireccionCorreo());
        Assert.assertEquals("dni", confirmacion.getNumeroDocumento());
        Assert.assertEquals("nroCuenta", confirmacion.getNroCuenta());
        Assert.assertEquals("tipoCuenta", confirmacion.getIdTipoCuenta());
        Assert.assertTrue(confirmacion.getIsCuit());
        Assert.assertEquals("titular", confirmacion.getTitular());
    }

    /**
     * Datos entrada agenda destinatario hash code equal string test.
     */
    @Test
    public void datosEntradaAgendaDestinatarioHashCodeEqualStringTest() {
        DatosEntradaAgendaDestinatario datosEntrada = new DatosEntradaAgendaDestinatario();
        datosEntrada.setTipoDestinatario("02");

        Assert.assertNotEquals(datosEntrada.hashCode(), 0);
        Assert.assertEquals("DatosEntradaAgendaDestinatario [tipoDestinatario=02, id=null]", datosEntrada.toString());
        DatosEntradaAgendaDestinatario datosEntrada2 = new DatosEntradaAgendaDestinatario();
        Assert.assertTrue(!datosEntrada.equals(datosEntrada2));
        Assert.assertTrue(datosEntrada.equals(datosEntrada));
    }

    /**
     * Setter getter configuracion alta destinatario CBUDTO test.
     */
    @Test
    public void setterGetterConfiguracionAltaDestinatarioCBUDTOTest() {
        ConfiguracionAltaDestinatarioCBUDTO configToTest = new ConfiguracionAltaDestinatarioCBUDTO();
        configToTest.setBanco("banco");
        configToTest.setNumeroCuenta("cuenta");
        configToTest.setDigitoVerificador("digito");
        configToTest.setNumeroCuil("cuil");
        configToTest.setIsRio(true);
        configToTest.setSucursal("sucursal");
        configToTest.setTitular("titular");
        Assert.assertEquals("banco", configToTest.getBanco());
        Assert.assertEquals("cuenta", configToTest.getNumeroCuenta());
        Assert.assertEquals("digito", configToTest.getDigitoVerificador());
        Assert.assertEquals("cuil", configToTest.getNumeroCuil());
        Assert.assertEquals(true, configToTest.getIsRio());
        Assert.assertEquals("sucursal", configToTest.getSucursal());
        Assert.assertEquals("titular", configToTest.getTitular());
    }

    /**
     * Constructor rio configuracion alta destinatario CBUDTO test.
     */
    @Test
    public void constructorRioConfiguracionAltaDestinatarioCBUDTOTest() {
        ValidacionCuentaOutCBUEntity validacionOUT = new ValidacionCuentaOutCBUEntity();
        validacionOUT.setBandes("bandes");
        validacionOUT.setTitular("titular");
        // is RIO
        ConfiguracionAltaDestinatarioCBUDTO configToTest = new ConfiguracionAltaDestinatarioCBUDTO(validacionOUT, true,
                "000-1234567/9");
        Assert.assertEquals("000", configToTest.getSucursal());
        Assert.assertNull(configToTest.getBanco());

        // Not RIO
        configToTest = new ConfiguracionAltaDestinatarioCBUDTO(validacionOUT, false, "000-1234567/9");
        Assert.assertNull(configToTest.getSucursal());
        Assert.assertNotNull(configToTest.getBanco());
    }

    /**
     * Agenda destinatario out entity hash code equal string test.
     */
    @Test
    public void agendaDestinatarioOutEntityHashCodeEqualStringTest() {
        AgendaDestinatarioOutEntity datosEntrada = new AgendaDestinatarioOutEntity();
        datosEntrada.setHora("13");

        Assert.assertNotEquals(datosEntrada.hashCode(), 0);
        Assert.assertEquals(
                "AgendaDestinatarioOutEntity[CodigoRetornoExtendido=<null>,headerTrama=<null>,fecha=<null>,hora=13,nroComprobante=<null>]",
                datosEntrada.toString());
        AgendaDestinatarioOutEntity datosEntrada2 = new AgendaDestinatarioOutEntity();
        Assert.assertTrue(!datosEntrada.equals(datosEntrada2));
        Assert.assertTrue(datosEntrada.equals(datosEntrada));
    }

    /**
     * Consulta agenda destinatario out entity hash code equal string test.
     */
    @Test
    public void consultaAgendaDestinatarioOutEntityHashCodeEqualStringTest() {
        ConsultaAgendaDestinatarioOutEntity datosEntrada = new ConsultaAgendaDestinatarioOutEntity();
        datosEntrada.setCodigoRetornoExtendido("12");
        Assert.assertNull(datosEntrada.getHeaderTrama());
        Assert.assertNull(datosEntrada.getIndAgendado());
        Assert.assertNull(datosEntrada.getCantidadRegistros());
        Assert.assertNotEquals(datosEntrada.hashCode(), 0);
        ConsultaAgendaDestinatarioOutEntity datosEntrada2 = new ConsultaAgendaDestinatarioOutEntity("13");
        Assert.assertTrue(!datosEntrada.equals(datosEntrada2));
        Assert.assertTrue(datosEntrada.equals(datosEntrada));

    }

    /**
     * Obtener informacion destinatario transferencia CBU fallo validacion test.
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaCBUFalloValidacionTest() {
        Respuesta<ConfiguracionAltaDestinatarioInView> toRet = new Respuesta<ConfiguracionAltaDestinatarioInView>();
        ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView = new ConfiguracionAltaDestinatarioInView();
        Respuesta<Object> error = new Respuesta<Object>();
        error.setEstadoRespuesta(EstadoRespuesta.ERROR);
        toRet.setEstadoRespuesta(EstadoRespuesta.WARNING);
        Mockito.doReturn(toRet).when(respuestaFactory).validate(Matchers.any());
        configuracionAltaDestinatarioInView.setNroCbu("");
        Mockito.when(casuistica.respuestaErrorAltaConfiguracionCuentaInvalida()).thenReturn(error);
        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager
                .derivarServicioAltaCorrespondiente(configuracionAltaDestinatarioInView, "User-Agent");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Grabar estadistica ver comprobante rio test.
     */
    @Test
    public void grabarEstadisticaVerComprobanteRioTest() {
        agendaManager.grabarEstadisticaVerComprobanteRio();
        Mockito.verify(sesionAgenda).setContadorAlta(null);
        Mockito.verify(sesionAgenda).setCuitCuil(null);
        Mockito.verify(estadisticaManager).add(Matchers.anyString(), Matchers.anyString());
    }

    /**
     * Grabar estadistica ver comprobante rio test.
     */
    @Test
    public void grabarEstadisticaIngresoAltaDestinatarioCBUTest() {
        agendaManager.grabarEstadisticaIngresoAltaDestinatarioCBU();
        Mockito.verify(sesionAgenda).setContadorAlta(null);
        Mockito.verify(sesionAgenda).setCuitCuil(null);
        Mockito.verify(estadisticaManager).add(Matchers.anyString(), Matchers.anyString());
    }

    /**
     * Grabar estadistica ver comprobante rio test.
     */
    @Test
    public void grabarEstadisticaIngresoEnvioEfectivoTest() {
        agendaManager.grabarEstadisticaIngresoEnvioEfectivo();
        Mockito.verify(sesionAgenda).setContadorAlta(null);
        Mockito.verify(sesionAgenda).setCuitCuil(null);
        Mockito.verify(estadisticaManager).add(Matchers.anyString(), Matchers.anyString());
    }

    /**
     * Grabar estadistica ver comprobante rio test.
     */
    @Test
    public void grabarEstadisticaAltaEnvioEfectivoTest() {
        agendaManager.grabarEstadisticaAltaEnvioEfectivo();
        Mockito.verify(estadisticaManager).add(Matchers.anyString(), Matchers.anyString());
    }

    /**
     * Grabar estadistica ver comprobante rio test.
     */
    @Test
    public void grabarEstadisticaModificacionDestinatarioRioTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setTipoDestinatario("RIO");
        agendaManager.grabarEstadisticaConfiguracionModificacionDestinatario(datos);
        Mockito.verify(estadisticaManager).add(Matchers.anyString(), Matchers.anyString());
    }

    /**
     * Grabar estadistica modificacion destinatario alias test.
     */
    @Test
    public void grabarEstadisticaModificacionDestinatarioAliasTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setTipoDestinatario("ALIAS");
        agendaManager.grabarEstadisticaConfiguracionModificacionDestinatario(datos);
        Mockito.verify(estadisticaManager).add(Matchers.anyString(), Matchers.anyString());
    }

    /**
     * Grabar estadistica modificacion destinatario OB test.
     */
    @Test
    public void grabarEstadisticaModificacionDestinatarioOBTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setTipoDestinatario("OTROS_BANCOS");
        agendaManager.grabarEstadisticaConfiguracionModificacionDestinatario(datos);
        Mockito.verify(estadisticaManager).add(Matchers.anyString(), Matchers.anyString());
    }

    /**
     * Grabar estadistica modificacion destinatario efectivo test.
     */
    @Test
    public void grabarEstadisticaModificacionDestinatarioEfectivoTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setTipoDestinatario("ENVIO_EFECTIVO");
        agendaManager.grabarEstadisticaConfiguracionModificacionDestinatario(datos);
        Mockito.verify(estadisticaManager).add(Matchers.anyString(), Matchers.anyString());
    }

    /**
     * Obtener informacion destinatario transferencia CBU test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerInformacionDestinatarioTransferenciaCBUTest() {
        ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioInView = new ConfiguracionAltaDestinatarioInView();
        RegistroSesion reg = new RegistroSesion();
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> retContinuar = new Respuesta<ConfiguracionAltaDestinatarioCBUDTO>();
        ConfiguracionAltaDestinatarioCBUDTO respuesta = new ConfiguracionAltaDestinatarioCBUDTO();
        respuesta.setIsRio(Boolean.TRUE);
        Respuesta<ConfiguracionAltaDestinatarioInView> resValidacion = new Respuesta<ConfiguracionAltaDestinatarioInView>();
        Respuesta<ConfiguracionAltaDestinatarioInView> resValidacionError = new Respuesta<ConfiguracionAltaDestinatarioInView>();

        resValidacion.setEstadoRespuesta(EstadoRespuesta.OK);
        resValidacionError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        configuracionAltaDestinatarioInView.setIsPesos(true);
        configuracionAltaDestinatarioInView.setNroCbu("1500617400061732041378");
        reg.setIp("198.13.3.3.13");
        retContinuar.setEstadoRespuesta(EstadoRespuesta.OK);
        retContinuar.setRespuesta(respuesta);

        Mockito.doReturn(resValidacion).when(respuestaFactory).validate(Matchers.any());
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(reg);
        Mockito.when(altaCBUBO.continuarAltaDestinatarioCBU(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyBoolean(), Matchers.anyString(), Matchers.anyString())).thenReturn(retContinuar);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(ConfiguracionAltaDestinatarioCBUOutView.class))).thenReturn(resValidacion);
        respuestaFactory.validate(configuracionAltaDestinatarioInView);

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager
                .derivarServicioAltaCorrespondiente(configuracionAltaDestinatarioInView, "User-Agent");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

        retContinuar.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyList()))
                .thenReturn(resValidacionError);
        res = agendaManager.derivarServicioAltaCorrespondiente(configuracionAltaDestinatarioInView, "User-Agent");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener informacion destinatario transferencia alias rio test.
     *
     * @author florencia.n.martinez
     * @author Manuel.Vargas cambios en los throws
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasRioCUPDolaresTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("02");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setNroSucursal("033");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cliente.getCuentas().add(cuenta);
        ConfiguracionAltaDestinatarioInView inView = ConfiguracionAltaDestinatarioInViewMock
                .completarInfoParaAliasValido();
        inView.setIsPesos(Boolean.FALSE);
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaOk(
                ConfiguracionAltaDestinatarioCBUDTO.class, ConfiguracionAltaDestinatarioCBUDTOMock.completarInfoRio());

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(cuenta);
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);

        // Then
        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager
                .derivarServicioAltaCorrespondiente(inView, "User-Agent");

        // Expected
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener informacion destinatario transferencia alias rio CUP pesos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasRioCUPPesosTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("02");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setNroSucursal("033");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cliente.getCuentas().add(cuenta);
        ConfiguracionAltaDestinatarioInView inView = ConfiguracionAltaDestinatarioInViewMock
                .completarInfoParaAliasValido();
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaOk(
                ConfiguracionAltaDestinatarioCBUDTO.class, ConfiguracionAltaDestinatarioCBUDTOMock.completarInfoRio());

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(cuenta);
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);

        // Then
        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager
                .derivarServicioAltaCorrespondiente(inView, "User-Agent");

        // Expected
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener informacion destinatario transferencia alias rio CUD pesos test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasRioCUDPesosTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("02");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setNroSucursal("033");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        cliente.getCuentas().add(cuenta);
        ConfiguracionAltaDestinatarioInView inView = ConfiguracionAltaDestinatarioInViewMock
                .completarInfoParaAliasValido();
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaOk(
                ConfiguracionAltaDestinatarioCBUDTO.class, ConfiguracionAltaDestinatarioCBUDTOMock.completarInfoRio());

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(cuenta);
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);

        // Then
        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager
                .derivarServicioAltaCorrespondiente(inView, "User-Agent");

        // Expected
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener informacion destinatario transferencia alias rio CUD dolares
     * test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasRioCUDDolaresTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("02");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setNroSucursal("033");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        cliente.getCuentas().add(cuenta);
        ConfiguracionAltaDestinatarioInView inView = ConfiguracionAltaDestinatarioInViewMock
                .completarInfoParaAliasValido();
        inView.setIsPesos(Boolean.FALSE);
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaOk(
                ConfiguracionAltaDestinatarioCBUDTO.class, ConfiguracionAltaDestinatarioCBUDTOMock.completarInfoRio());

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(cuenta);
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);

        // Then
        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager
                .derivarServicioAltaCorrespondiente(inView, "User-Agent");

        // Expected
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener informacion destinatario transferencia alias no rio test.
     *
     * @author florencia.n.martinez
     * @author Manuel.Vargas cambios en los throws
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasNoRioTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaOk(
                ConfiguracionAltaDestinatarioCBUDTO.class,
                ConfiguracionAltaDestinatarioCBUDTOMock.completarInfoNoRio());

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(new Cuenta());
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager.derivarServicioAltaCorrespondiente(
                ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), "User-Agent");

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener informacion destinatario transferencia alias no rio sin cuenta
     * tipo moneda test.
     * 
     * @author florencia.n.martinez
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasNoRioSinCuentaTipoMonedaTest() {
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1453");
        mensaje.setMensaje("<p>No se pueden agendar cuentas propias.</p>");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(null);

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager.derivarServicioAltaCorrespondiente(
                ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), "User-Agent");

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.SIN_CUENTAS_TIPO_MONEDA.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener informacion destinatario transferencia alias rio warning alias
     * inexistente test.
     *
     * @author florencia.n.martinez
     * @author Manuel.Vargas cambios en los throws
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasRioWarningAliasInexistenteTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaWarning(
                ConfiguracionAltaDestinatarioCBUDTO.class, null, TipoError.ALIAS_INEXISTENTE, null, null);

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(new Cuenta());
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager.derivarServicioAltaCorrespondiente(
                ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), "User-Agent");

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    /**
     * Obtener informacion destinatario transferencia alias rio error cta
     * inactiva test.
     *
     * @author Manuel.Vargas cambios en los throws
     * @author florencia.n.martinez
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasRioErrorCtaInactivaTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1547");
        mensaje.setMensaje(
                "El alias que ingresaste es incorrecto. ( Cuenta del destinatario inactiva / Alias eliminado )");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaError(
                ConfiguracionAltaDestinatarioCBUDTO.class, null, TipoError.ALIAS_CON_CTA_INACTIVA,
                CodigoMensajeConstantes.ALIAS_INEXISTENTE);

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(new Cuenta());
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager.derivarServicioAltaCorrespondiente(
                ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), "User-Agent");

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ALIAS_CON_CTA_INACTIVA.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener informacion destinatario transferencia alias rio error moneda
     * seleccionada no coincide con cta destino test.
     *
     * @author florencia.n.martinez
     * @author Manuel.Vargas cambios en los throws
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasRioErrorMonedaSeleccionadaNoCoincideConCtaDestinoTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1594");
        mensaje.setMensaje(
                "El alias que ingresaste es incorrecto. ( Cuenta del destinatario inactiva / Alias eliminado )");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaError(
                ConfiguracionAltaDestinatarioCBUDTO.class, "", TipoError.MONEDA_CTA_NO_COINCIDE_MONEDA_SELECCION,
                CodigoMensajeConstantes.MONEDA_CTA_NO_COINCIDE_MONEDA_SELECCION);

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(new Cuenta());
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager.derivarServicioAltaCorrespondiente(
                ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), "User-Agent");

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.MONEDA_CTA_NO_COINCIDE_MONEDA_SELECCION.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener informacion destinatario transferencia alias rio error generico
     * test.
     *
     * @author florencia.n.martinez
     * @author Manuel.Vargas cambios en los throws
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasRioErrorGenericoTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1538");
        mensaje.setMensaje(
                "<p><b>Ocurrió un error en nuestros servicios</b>. Por favor, volvé a ingresar en unos minutos.</p>");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(new Cuenta());
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenThrow(new BusinessException("DAOException o error no contemplado."));

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager.derivarServicioAltaCorrespondiente(
                ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), "User-Agent");

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener informacion destinatario transferencia alias destinatario
     * existente RIO test.
     *
     * @author Manuel.Vargas cambios en los throws
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasDestinatarioExistenteRIOTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaOk(
                ConfiguracionAltaDestinatarioCBUDTO.class, ConfiguracionAltaDestinatarioCBUDTOMock.completarInfoRio());
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Atencion.. destinatario existente");

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(new Cuenta());
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "1"));
        Mockito.when(agendaDestinatarioBO.obtenerDestinatarioDTO(Matchers.any(DestinatarioEntity.class)))
                .thenReturn(new DestinatarioAgendaDTO());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager.derivarServicioAltaCorrespondiente(
                ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), "User-Agent");

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    /**
     * Obtener informacion destinatario transferencia alias destinatario
     * existente OB test.
     *
     * @author Manuel.Vargas cambios en los throws
     * @throws BusinessException
     *             the business exception
     * @throws ValidacionAliasInexistenteEliminadoException
     *             the validacion alias inexistente eliminado exception
     */
    @Test
    public void obtenerInformacionDestinatarioTransferenciaAliasDestinatarioExistenteOBTest()
            throws BusinessException, ValidacionAliasInexistenteEliminadoException {
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> respuestaDTO = respuestaFactory.crearRespuestaOk(
                ConfiguracionAltaDestinatarioCBUDTO.class,
                ConfiguracionAltaDestinatarioCBUDTOMock.completarInfoNoRio());
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Atencion.. destinatario existente");

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(altaCBUBO.obtenerCuentaPorMoneda(Matchers.any(Cliente.class), Matchers.anyBoolean()))
                .thenReturn(new Cuenta());
        Mockito.when(altaDestinatarioAliasBO.continuarAltaDestinatarioAlias(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class), Matchers.anyString(), Matchers.anyBoolean(), Matchers.anyString()))
                .thenReturn(respuestaDTO);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("OB ", "1"));
        Mockito.when(agendaDestinatarioBO.obtenerDestinatarioDTO(Matchers.any(DestinatarioEntity.class)))
                .thenReturn(new DestinatarioAgendaDTO());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<ConfiguracionAltaDestinatarioCBUOutView> res = agendaManager.derivarServicioAltaCorrespondiente(
                ConfiguracionAltaDestinatarioInViewMock.completarInfoParaAliasValido(), "User-Agent");

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    /**
     * Eliminacion destinatario rio error destinatario ya eliminado.
     */
    @Test
    public void eliminacionDestinatarioRioErrorDestinatarioYaEliminado() {
        List<DestinatarioEntity> destinatarios = getListaDestinatario("RIO", "1");

        Respuesta<Void> respuestaBO = new Respuesta<Void>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(
                ErrorAgendaDestinatariosEnum.ERROR_BAJA_DESTINATARIO_YA_ELIMINADO.getTipoError().getDescripcion());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaBO.add(item);

        RegistroSesion reg = new RegistroSesion();
        reg.setIp("123.123.123");

        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(destinatarios);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(reg);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(eliminacionAgendaBO.eliminacionDestinatario(Matchers.any(DestinatarioEntity.class),
                Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(respuestaBO);

        Respuesta<Void> respuestaManager = agendaManager.eliminacionDestinatario("1");

        Assert.assertEquals(CONTINUAR, respuestaManager.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.ELIMINACION_DESTINATARIO_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Eliminacion destinatario otros bancos error general puede reintentar.
     */
    @Test
    public void eliminacionDestinatarioOtrosBancosErrorGeneralPuedeReintentar() {
        List<DestinatarioEntity> destinatarios = getListaDestinatario("OB ", "1");

        Respuesta<Void> respuestaBO = new Respuesta<Void>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(
                ErrorAgendaDestinatariosEnum.ERROR_GENERAL_ELIMINACION_DESTINATARIO.getTipoError().getDescripcion());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaBO.add(item);

        RegistroSesion reg = new RegistroSesion();
        reg.setIp("123.123.123");

        Mockito.when(sesionAgenda.getContadorAlta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(destinatarios);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(reg);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(eliminacionAgendaBO.eliminacionDestinatario(Matchers.any(DestinatarioEntity.class),
                Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(respuestaBO);

        Respuesta<Void> respuestaManager = agendaManager.eliminacionDestinatario("1");

        Assert.assertEquals(REINTENTAR, respuestaManager.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(
                EstadisticasConstants.ELIMINACION_DESTINATARIO_OTROS_BANCOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Eliminacion destinatario envio efectivo error general reintentos
     * agotados.
     */
    @Test
    public void eliminacionDestinatarioEnvioEfectivoErrorGeneralReintentosAgotados() {
        List<DestinatarioEntity> destinatarios = getListaDestinatario("EXT", "1");

        Respuesta<Void> respuestaBO = new Respuesta<Void>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(
                ErrorAgendaDestinatariosEnum.ERROR_GENERAL_ELIMINACION_DESTINATARIO.getTipoError().getDescripcion());
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaBO.add(item);

        RegistroSesion reg = new RegistroSesion();
        reg.setIp("123.123.123");

        ContadorIntentos contador = new ContadorIntentos(2);
        contador.permiteReintentar();
        contador.permiteReintentar();

        Mockito.when(sesionAgenda.getContadorAlta()).thenReturn(contador);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(destinatarios);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(reg);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(eliminacionAgendaBO.eliminacionDestinatario(Matchers.any(DestinatarioEntity.class),
                Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(respuestaBO);

        Respuesta<Void> respuestaManager = agendaManager.eliminacionDestinatario("1");

        Assert.assertEquals(CONTINUAR, respuestaManager.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
        Mockito.verify(estadisticaManager, Mockito.times(1)).add(
                EstadisticasConstants.ELIMINACION_DESTINATARIO_ENVIO_EFECTIVO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        Mockito.verify(sesionAgenda).setContadorAlta(null);
    }

    /**
     * Eliminacion destinatario rio ok.
     */
    @Test
    public void eliminacionDestinatarioRioOk() {
        List<DestinatarioEntity> destinatarios = getListaDestinatario("RIO", "1");

        Respuesta<Void> respuestaBO = respuestaFactory.crearRespuestaOk(null);

        RegistroSesion reg = new RegistroSesion();
        reg.setIp("123.123.123");

        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(destinatarios);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(reg);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(eliminacionAgendaBO.eliminacionDestinatario(Matchers.any(DestinatarioEntity.class),
                Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(respuestaBO);

        Respuesta<Void> respuestaManager = agendaManager.eliminacionDestinatario("1");

        Mockito.verify(estadisticaManager, Mockito.times(1)).add(EstadisticasConstants.ELIMINACION_DESTINATARIO_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        Assert.assertEquals(respuestaManager, respuestaBO);
    }

    /**
     * Grabar estadistica comprobante CBU ok test.
     */
    @Test
    public void grabarEstadisticaComprobanteCBUOkTest() {
        agendaManager.grabarEstadisticaComprobanteCBU();
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.VER_COMPROBANTE_ALTA_DESTINATARIO_CBU,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener confirmacion edicion destinatario rio ok test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioRioOkTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setNroCuenta("000-063917/0");
        datosEntrada.setId("12");
        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "12"));
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(respuestaOkComprobanteAlta());
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertNotNull(res.getRespuesta());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener confirmacion edicion destinatario rio error envio efectivo
     * invalido test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioRioErrorEnvioEfectivoInvalidoTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setNroCuenta("000-063917/0");
        datosEntrada.setId("12");
        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "12"));
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(respuestaErrorComprobanteAlta());
        Mockito.when(sesionAgenda.getContadorAlta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertNull(res.getRespuesta());
        Assert.assertEquals("continuar", res.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Obtener confirmacion edicion destinatario rio error no contemplado test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioRioErrorNoContempladoTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setNroCuenta("000-063917/0");
        datosEntrada.setId("12");
        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "12"));
        Respuesta<ComprobanteAltaDestinatarioDTO> resEdicion = respuestaErrorComprobanteAlta();
        resEdicion.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.TIMEOUT.getDescripcion());
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(resEdicion);
        Mockito.when(sesionAgenda.getContadorAlta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertNull(res.getRespuesta());
        Assert.assertEquals("reintentar", res.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Obtener confirmacion edicion destinatario rio error no contemplado
     * continuar test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioRioErrorNoContempladoContinuarTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setNroCuenta("000-063917/0");
        datosEntrada.setId("12");
        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        contadorIntentos.permiteReintentar();
        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "12"));
        Respuesta<ComprobanteAltaDestinatarioDTO> resEdicion = respuestaErrorComprobanteAlta();
        resEdicion.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.TIMEOUT.getDescripcion());
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(resEdicion);
        Mockito.when(sesionAgenda.getContadorAlta()).thenReturn(contadorIntentos);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertNull(res.getRespuesta());
        Assert.assertEquals("continuar", res.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Obtener confirmacion edicion destinatario otros bancos error no
     * contemplado test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioOtrosBancosErrorNoContempladoTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setCbu("1500000800000100607578");
        datosEntrada.setId("12");
        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("OB ", "12"));
        Respuesta<ComprobanteAltaDestinatarioDTO> resEdicion = respuestaErrorComprobanteAlta();
        resEdicion.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.TIMEOUT.getDescripcion());
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(resEdicion);
        Mockito.when(sesionAgenda.getContadorAlta()).thenReturn(new ContadorIntentos(0));
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertNull(res.getRespuesta());
        Assert.assertEquals("reintentar", res.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_OTROS_BANCOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Obtener confirmacion edicion destinatario envio efectivo error no
     * contemplado test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioEnvioEfectivoErrorNoContempladoTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setNumeroDocumento("20-13238861-0");
        datosEntrada.setId("12");
        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("EXT", "12"));
        Respuesta<ComprobanteAltaDestinatarioDTO> resEdicion = respuestaErrorComprobanteAlta();
        resEdicion.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.TIMEOUT.getDescripcion());
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(resEdicion);
        Mockito.when(sesionAgenda.getContadorAlta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertNull(res.getRespuesta());
        Assert.assertEquals("reintentar", res.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_ENVIO_EFECTIVO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Respuesta error comprobante alta.
     *
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaDestinatarioDTO> respuestaErrorComprobanteAlta() {
        Respuesta<ComprobanteAltaDestinatarioDTO> res = new Respuesta<ComprobanteAltaDestinatarioDTO>();
        res.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje("");
        item.setTag("");
        item.setTipoError(TipoError.ERROR_EDITAR_DESTINATARIO_INVALIDO.getDescripcion());
        res.add(item);
        return res;
    }

    /**
     * Obtener confirmacion edicion destinatario otros bancos ok test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioOtrosBancosOkTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setCbu("1500000800000100607578");
        datosEntrada.setId("12");
        List<DestinatarioEntity> lista = getListaDestinatario("OB ", "12");
        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(lista);
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(respuestaOkComprobanteAlta());
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertNotNull(res.getRespuesta());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_OTROS_BANCOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener confirmacion edicion destinatario envio efectivo ok test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioEnvioEfectivoOkTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setNumeroDocumento("20-13238861-0");
        datosEntrada.setId("12");
        List<DestinatarioEntity> lista = getListaDestinatario("EXT", "12");
        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(lista);
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(respuestaOkComprobanteAlta());
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertNotNull(res.getRespuesta());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_ENVIO_EFECTIVO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener confirmacion edicion destinatario alias ok test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioAliasOkTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setAlias("Aliassss");
        datosEntrada.setId("12");

        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "12", "aliassss"));
        Mockito.when(
                edicionDestinatarioBO.editarDestinatario(Matchers.any(DestinatarioEntity.class), Matchers.anyString(),
                        Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class)))
                .thenReturn(respuestaOkComprobanteAlta());
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertNotNull(res.getRespuesta());
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.EDICION_DESTINATARIO_ALIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener confirmacion edicion destinatario RCA warning test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioRCACoordenadasWarningTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setAlias("Aliassss");
        datosEntrada.setId("12");
        datosEntrada.setDesafio(new AutentificacionDTO());

        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "12", "aliassss"));
        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionParametros.isExisteDesafioEnCurso()).thenReturn(true);
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(new CoordenadasDesafio());

        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    /**
     * Obtener confirmacion edicion destinatario RCA token warning test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioRCATokenWarningTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setAlias("Aliassss");
        datosEntrada.setId("12");
        datosEntrada.setDesafio(new AutentificacionDTO());

        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "12", "aliassss"));
        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionParametros.isExisteDesafioEnCurso()).thenReturn(true);
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(new TokenDesafio());

        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    /**
     * Obtener confirmacion edicion destinatario RCA banelco warning test.
     */
    @Test
    public void obtenerConfirmacionEdicionDestinatarioRCABanelcoWarningTest() {
        ConfirmacionAltaDestinatarioView datosEntrada = new ConfirmacionAltaDestinatarioView();
        datosEntrada.setAlias("Aliassss");
        datosEntrada.setId("12");
        datosEntrada.setDesafio(new AutentificacionDTO());

        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Mockito.when(sesionAgenda.getDestinatariosEntity()).thenReturn(getListaDestinatario("RIO", "12", "aliassss"));
        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);
        Mockito.when(sesionParametros.isExisteDesafioEnCurso()).thenReturn(true);
        Mockito.when(sesionParametros.getDesafioEnCurso()).thenReturn(new BanelcoDesafio());

        Respuesta<ConfirmacionAltaDestinatarioView> res = agendaManager
                .obtenerConfirmacionEdicionDestinatario(datosEntrada);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    /**
     * Grabar estadistica feedback modificacion destinatario alias ok test.
     */
    @Test
    public void grabarEstadisticaFeedbackModificacionDestinatarioAliasOkTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setTipoDestinatario("ALIAS");
        agendaManager.grabarEstadisticaComprobanteModificacionDestinatario(datos);
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.FEEDBACK_MODIFICACION_AGENDADESTINATARIOS_ALIAS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Grabar estadistica feedback modificacion destinatario rio ok test.
     */
    @Test
    public void grabarEstadisticaFeedbackModificacionDestinatarioRioOkTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setTipoDestinatario("RIO");
        agendaManager.grabarEstadisticaComprobanteModificacionDestinatario(datos);
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.FEEDBACK_MODIFICACION_AGENDADESTINATARIOS_RIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Grabar estadistica feedback modificacion destinatario otros bancos ok
     * test.
     */
    @Test
    public void grabarEstadisticaFeedbackModificacionDestinatarioOtrosBancosOkTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setTipoDestinatario("OTROS_BANCOS");
        agendaManager.grabarEstadisticaComprobanteModificacionDestinatario(datos);
        Mockito.verify(estadisticaManager).add(
                EstadisticasConstants.FEEDBACK_MODIFICACION_AGENDADESTINATARIOS_OTROS_BANCOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Grabar estadistica feedback modificacion destinatario envio efectivo ok
     * test.
     */
    @Test
    public void grabarEstadisticaFeedbackModificacionDestinatarioEnvioEfectivoOkTest() {
        DatosEntradaAgendaDestinatario datos = new DatosEntradaAgendaDestinatario();
        datos.setTipoDestinatario("ENVIO_EFECTIVO");
        agendaManager.grabarEstadisticaComprobanteModificacionDestinatario(datos);
        Mockito.verify(estadisticaManager).add(
                EstadisticasConstants.FEEDBACK_MODIFICACION_AGENDADESTINATARIOS_ENVIO_EFECTIVO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Respuesta ok comprobante alta.
     *
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaDestinatarioDTO> respuestaOkComprobanteAlta() {
        Respuesta<ComprobanteAltaDestinatarioDTO> res = new Respuesta<ComprobanteAltaDestinatarioDTO>();
        res.setEstadoRespuesta(EstadoRespuesta.OK);
        ComprobanteAltaDestinatarioDTO comprobante = new ComprobanteAltaDestinatarioDTO();
        res.setRespuesta(comprobante);
        return res;
    }

    /**
     * Gets the lista destinatario.
     *
     * @param tipoAgenda
     *            the tipo agenda
     * @param id
     *            the id
     * @return the lista destinatario
     */
    private List<DestinatarioEntity> getListaDestinatario(String tipoAgenda, String id) {
        List<DestinatarioEntity> lista = new ArrayList<DestinatarioEntity>();
        DestinatarioEntity destinatario = new DestinatarioEntity();
        destinatario.setTipoAgendaOcurrencia(tipoAgenda);
        destinatario.setId(id);
        destinatario.setTitular("MUÑOZ, CÉSAR ALBERTO");
        destinatario.setNumeroCuentaDestinatario("0001234567");
        destinatario.setSucursalCuentaDestinatario("0072");
        destinatario.setTipoCuentaDestinatario(TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda());
        destinatario.setBancoDestinatario(BancoEnum.SANTANDER_RIO.getDescripcion());
        destinatario.setDocumentoDestinatario("30-12123345-7");
        destinatario.setCbuDestinatario("0123456789012345678912");

        lista.add(destinatario);
        return lista;
    }

    /**
     * Gets the lista destinatario.
     *
     * @param tipoAgenda
     *            the tipo agenda
     * @param id
     *            the id
     * @param alias
     *            the alias
     * @return the lista destinatario
     */
    private List<DestinatarioEntity> getListaDestinatario(String tipoAgenda, String id, String alias) {
        List<DestinatarioEntity> lista = new ArrayList<DestinatarioEntity>();
        DestinatarioEntity destinatario = new DestinatarioEntity();
        destinatario.setTipoAgendaOcurrencia(tipoAgenda);
        destinatario.setId(id);
        destinatario.setAlias(alias);
        lista.add(destinatario);
        return lista;
    }

}
