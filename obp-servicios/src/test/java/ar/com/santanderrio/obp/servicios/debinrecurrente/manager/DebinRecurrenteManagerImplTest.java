package ar.com.santanderrio.obp.servicios.debinrecurrente.manager;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.debinrecurrente.bo.DebinRecurrenteBO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.BuyerRecurrenceListRequestDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.DebinRecurrenteRSADTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.RecurrenceBuyerItemDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.RecurrenceDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.mock.DebinRecurrenteApiMock;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.CrearRecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.DatosComprobanteDebinRecurrente;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.RecurrenciasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.personas.dao.PersonDAO;
import ar.com.santanderrio.obp.servicios.personas.dto.Person;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class DebinRecurrenteManagerImplTest {

    public static final String BUYER_CUIT = "20209204100";
    public static final String SELLER_CUIT = "30693283724";
    public static final String VALOR_DESAFIO = "1";
    /** The debin recurrente BO. */
    @Mock
    private DebinRecurrenteBO debinRecurrenteBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @Spy
    RespuestaFactory respuestaFactory;

    /** The valor desafio. */
    private String valorDesafio;

    /** The autentificacion manager. */
    @Mock
    private AutentificacionManager autentificacionManager;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinRecurrenteManagerImpl.class);

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private ClienteManager clienteManager;

    @Mock
    private RsaManager rsaManager;

    @Mock
    private PersonDAO personDAO;

    @Captor
    ArgumentCaptor<AutentificacionDTO> autentificacionDTOCaptor;

    private DebinRecurrenteManagerImpl debinRecurrenteManagerImpl;

    /** The cliente. */
    private Cliente cliente = new Cliente();

    @Before
    public void init(){
        debinRecurrenteManagerImpl = new DebinRecurrenteManagerImpl(
                debinRecurrenteBO,
                sesionCliente,
                respuestaFactory,
                "1",
                autentificacionManager,
                estadisticaManager,
                sesionParametros,
                mensajeBO,
                clienteManager,
                rsaManager,
                personDAO,
                ""
        );

    }

    @Test
    public void crearRecurrenciaOk() throws Exception {

        //Define CrearRecurrenciaView
        CrearRecurrenciaView crearRecurrenciaView = DebinRecurrenteApiMock.obtenerCrearRecurrenciaView();

        //mock method
        Respuesta<CrearRecurrenciaView> respuestaCrearRecurrenciaView = DebinRecurrenteApiMock.respuestaCrearRecurrenciaView(EstadoRespuesta.OK);
        doReturn(respuestaCrearRecurrenciaView).when(autentificacionManager).verificarEstadoDesafio(crearRecurrenciaView.getDesafio(),
                Integer.parseInt(VALOR_DESAFIO));

        //mock method
        Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = DebinRecurrenteApiMock.respuestaAutentificacionDTO(EstadoRespuesta.OK);
        doReturn(respuestaAutentificacionDTO).when(autentificacionManager).ejecutarValidacionRSA(crearRecurrenciaView.getDesafio());

        //mock method
        doReturn(respuestaCrearRecurrenciaView).
                when(respuestaFactory).transformar(crearRecurrenciaView,respuestaAutentificacionDTO);

        //mock method
        Respuesta<RecurrenceDTO> respuestaRecurrenceDTO = DebinRecurrenteApiMock.respuestaOkRecurrenceDTO();
        doReturn(respuestaRecurrenceDTO).when(debinRecurrenteBO).crearRecurrencia(crearRecurrenciaView);

        //mock method
        Mensaje mensaje = new Mensaje();
        doReturn(mensaje).when(mensajeBO).obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINREC_FEEDBACK_OK_CREAR_RECURRENCIA);

        //Define comprobante
        DatosComprobanteDebinRecurrente comprobante =
                DebinRecurrenteApiMock.obtenerComprobante(crearRecurrenciaView, DebinRecurrenteApiMock.obtenerCliente());

        //Define cliente
        Cliente cliente = DebinRecurrenteApiMock.obtenerCliente();

        //mock method
        doReturn(cliente).when(sesionCliente).getCliente();

        //mock method
        doNothing().when(sesionParametros).setDatosComprobanteDebinRecurrente(comprobante);

        //mock method
        when(respuestaFactory.crearRespuestaOk(
                (CrearRecurrenciaView) any(),
                eq(StringUtils.EMPTY),
                eq(CodigoMensajeConstantes.DEBINREC_FEEDBACK_OK_CREAR_RECURRENCIA)
        )).thenReturn(DebinRecurrenteApiMock.obtenerRespuestaCrearRecurrenciaViewFinal(
                DebinRecurrenteApiMock.obtenerRespuestaFinal(),
                StringUtils.EMPTY,
                CodigoMensajeConstantes.DEBINREC_FEEDBACK_OK_CREAR_RECURRENCIA
        ));

        //mock method
        doReturn(true).when(estadisticaManager).add(EstadisticasConstants.FEEDBACK_ADHESION_DEBIN_RECURRENTE,EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        doReturn(true).when(sesionCliente).getTieneTokenRSA();

        doReturn(HashUtils.obtenerHash(debinRecurrenteManagerImpl.crearMapaDeLaVista(crearRecurrenciaView), HashUtils.ALGORITMO_MD5)).when(sesionParametros).getValidacionHash();
        //call method
        Respuesta<CrearRecurrenciaView> recurrencia  = debinRecurrenteManagerImpl.crearRecurrencia(crearRecurrenciaView);

        //asserts
        Assert.assertEquals("OK", recurrencia.getEstadoRespuesta().toString());
        Assert.assertNull(recurrencia.getRespuesta().getMensajeFeedback());
        Assert.assertEquals(false, recurrencia.getRespuesta().getDatosComprobante().getMostrarDevolucionDA());
        Assert.assertEquals("Prueba Aviso", recurrencia.getRespuesta().getDatosComprobante().getServicio());
        Assert.assertEquals("11/03/2021", recurrencia.getRespuesta().getDatosComprobante().getFechaOperacion());
        Assert.assertEquals("VALERIA ROCIO PE#ALVA", recurrencia.getRespuesta().getDatosComprobante().getEmpresa());
        Assert.assertEquals("pmc-servicio.jasper", recurrencia.getRespuesta().getDatosComprobante().getPmcServicioJasper());
        Assert.assertFalse(recurrencia.getRespuesta().getDatosComprobante().getEsDebitoAutomatico());
        Assert.assertEquals("30693283724", recurrencia.getRespuesta().getDatosComprobante().getCuitVendedor());
        Assert.assertEquals("mi cuit es 20270567860", recurrencia.getRespuesta().getDatosComprobante().getReferencia());
        Assert.assertEquals("000-063880/1", recurrencia.getRespuesta().getDatosComprobante().getNumeroCuenta());
        Assert.assertEquals("Cuenta única", recurrencia.getRespuesta().getDatosComprobante().getTipoCuenta());
        Assert.assertEquals("20-20920410-0", recurrencia.getRespuesta().getDatosComprobante().getCuitComprador());
        Assert.assertEquals("VAR", recurrencia.getRespuesta().getDatosComprobante().getConcepto());
        Assert.assertEquals("Varios", recurrencia.getRespuesta().getDatosComprobante().getDescripcion());
        Assert.assertEquals("PROXIMAMENTE", recurrencia.getRespuesta().getDatosComprobante().getNumeroComprobante());
        Assert.assertEquals("990975444311532750", recurrencia.getRespuesta().getDatosComprobante().getIdRecurrencia());
        Assert.assertEquals(1, recurrencia.getItemsMensajeRespuesta().size());
        Assert.assertNotNull(recurrencia.getItemsMensajeRespuesta().get(0));
        Assert.assertEquals(TipoError.OK.getDescripcion(), recurrencia.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(StringUtils.EMPTY, recurrencia.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals(CodigoMensajeConstantes.DEBINREC_FEEDBACK_OK_CREAR_RECURRENCIA, recurrencia.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals(false, recurrencia.getSkipLog());
    }

    @Test
    public void  crearRecurrenciaWarning_humanPerson()  throws Exception{
        this.crearRecurrenciaWarning(DebinRecurrenteManagerImpl.HUMAN_PERSON);
    }

    @Test
    public void crearRecurrenciaWarning_juridicalPerson()  throws Exception{
        this.crearRecurrenciaWarning(DebinRecurrenteManagerImpl.JURIDICAL_PERSON);

    }

    private void crearRecurrenciaWarning(String personType) throws Exception {

        //Define CrearRecurrenciaView
        CrearRecurrenciaView crearRecurrenciaView = DebinRecurrenteApiMock.obtenerCrearRecurrenciaView();

        //mock method
        Respuesta<CrearRecurrenciaView> respuestaCrearRecurrenciaView = DebinRecurrenteApiMock.respuestaCrearRecurrenciaView(EstadoRespuesta.WARNING);

        String valorDesafio = "1";
        doReturn(respuestaCrearRecurrenciaView).when(autentificacionManager).verificarEstadoDesafio(crearRecurrenciaView.getDesafio(),
                Integer.parseInt(valorDesafio));

        //mock method
        Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = DebinRecurrenteApiMock.respuestaAutentificacionDTO(EstadoRespuesta.WARNING);
        doReturn(respuestaAutentificacionDTO).when(autentificacionManager).ejecutarValidacionRSA(any(AutentificacionDTO.class));
        //mock method
        doReturn(respuestaCrearRecurrenciaView ).
                when(respuestaFactory).transformar(crearRecurrenciaView,respuestaAutentificacionDTO);

        doReturn(true).when(sesionCliente).getTieneTokenRSA();

        //Define cliente
        Cliente cliente = DebinRecurrenteApiMock.obtenerCliente();
        //mock method
        doReturn(cliente).when(sesionCliente).getCliente();

        Respuesta<List<BigDecimal>> antiguedadesRta = new Respuesta<List<BigDecimal>>();
        List<BigDecimal> antiguedades = new ArrayList<BigDecimal>();
        antiguedades.add(new BigDecimal("1.1"));
        antiguedades.add(new BigDecimal("2.2"));

        antiguedadesRta.setRespuesta(antiguedades);
        doReturn(antiguedadesRta).when(clienteManager).obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(cliente.getNup())) ;

        Person person = new Person();
        person.setPersonType(personType);
        doReturn(person).when(personDAO).findPersonInfoByCuit(crearRecurrenciaView.getCuitVendedor(), "bcra");

        //call method
        Respuesta<CrearRecurrenciaView> recurrencia  = debinRecurrenteManagerImpl.crearRecurrencia(crearRecurrenciaView);

        //asserts
        Assert.assertEquals("WARNING", recurrencia.getEstadoRespuesta().toString());

        verify(autentificacionManager, times(1)).ejecutarValidacionRSA(autentificacionDTOCaptor.capture());
        AutentificacionDTO autentificacionDTO = autentificacionDTOCaptor.getValue();
        Assert.assertEquals(EstadisticasConstants.SOLICITAR_COORDENADAS_NUEVA_RECURRENCIA, autentificacionDTO.getCodigoEstadisticaSolicitudCoordenadas());

        Assert.assertEquals(EstadisticasConstants.VALIDAR_COORDENADAS_NUEVA_RECURRENCIA, autentificacionDTO.getCodigoEstadisticaValidacionCoordenadas());
        Assert.assertEquals(EstadisticasConstants.VALIDAR_TOKEN_NUEVA_RECURRENCIA, autentificacionDTO.getCodigoEstadisticaValidacionToken());

        DebinRecurrenteRSADTO debinRecurrenteRSADTO = (DebinRecurrenteRSADTO)autentificacionDTO.getRsaDTO();
        Assert.assertTrue(debinRecurrenteRSADTO.getValidarBloqueo());
        Assert.assertFalse(debinRecurrenteRSADTO.getIgnorarRSA());

        Assert.assertEquals(null,debinRecurrenteRSADTO.getTipoDesafio());
        Assert.assertEquals(null,debinRecurrenteRSADTO.getDesafio());
        Assert.assertEquals(BUYER_CUIT,debinRecurrenteRSADTO.getCuitComprador());
        Assert.assertEquals(SELLER_CUIT,debinRecurrenteRSADTO.getCuitVendedor());
        Assert.assertEquals("peso",debinRecurrenteRSADTO.getMoneda());
        Assert.assertEquals(Integer.valueOf(1),debinRecurrenteRSADTO.getCantDiasUltimoCambioClave());
        Assert.assertEquals(Integer.valueOf(2),debinRecurrenteRSADTO.getCantDiasUltimoCambioToken());
        Assert.assertEquals(DebinRecurrenteManagerImpl.JURIDICAL_PERSON.equals(personType),debinRecurrenteRSADTO.isVendedorPersonaJuridica());
        Assert.assertEquals("VAR", debinRecurrenteRSADTO.getConcepto());
        Assert.assertEquals("Varios", debinRecurrenteRSADTO.getDescripcion());
        Assert.assertEquals("Prueba Aviso", debinRecurrenteRSADTO.getPrestacion());
        Assert.assertEquals(false, debinRecurrenteRSADTO.isMismoTitular());




    }


    @Test
    public void crearRecurrenciaError() throws Exception {

        //Define CrearRecurrenciaView
        CrearRecurrenciaView crearRecurrenciaView = DebinRecurrenteApiMock.obtenerCrearRecurrenciaView();

        //mock method
        Respuesta<CrearRecurrenciaView> respuestaCrearRecurrenciaView = DebinRecurrenteApiMock.respuestaCrearRecurrenciaView(EstadoRespuesta.ERROR);

        //Define cliente
        Cliente cliente = DebinRecurrenteApiMock.obtenerCliente();
        //mock method
        doReturn(cliente).when(sesionCliente).getCliente();


        String valorDesafio = "1";
        doReturn(respuestaCrearRecurrenciaView).when(autentificacionManager).verificarEstadoDesafio(crearRecurrenciaView.getDesafio(),
                Integer.parseInt(valorDesafio));

        doReturn(true).when(sesionCliente).getTieneTokenRSA();

        //call method
        Respuesta<CrearRecurrenciaView> recurrencia = debinRecurrenteManagerImpl.crearRecurrencia(crearRecurrenciaView);

        //asserts
        Assert.assertEquals("ERROR", recurrencia.getEstadoRespuesta().toString());

    }

    @Test
    public void testObtenerRecurrenciasPorComprador(){
        ObtenerRecurrenciasView view = new ObtenerRecurrenciasView();
        view.setNumeroPagina("1");
        view.setCbu("0720000701560000000178");

        //MOCK
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<BuyerRecurrenceListRequestDTO> respuestaMocked = new Respuesta<BuyerRecurrenceListRequestDTO>();
        BuyerRecurrenceListRequestDTO recurrencesDTO = DebinRecurrenteApiMock.getDefaultRecurrenceListDTO();
        respuestaMocked.setRespuesta(recurrencesDTO);
        respuestaMocked.setEstadoRespuesta(EstadoRespuesta.OK);
        doReturn(respuestaMocked).when(debinRecurrenteBO).obtenerRecurrenciasPorComprador(any(Cliente.class), any(ObtenerRecurrenciasView.class));

        //CALL METHOD
        Respuesta<RecurrenciasView> respuesta =debinRecurrenteManagerImpl.obtenerRecurrenciasPorComprador(view);

        //ASSERTS
        Assert.assertEquals("Pausada",respuesta.getRespuesta().getListaDebin().get(0).getEstado());
        Assert.assertEquals("Dada de baja",respuesta.getRespuesta().getListaDebin().get(1).getEstado());

        Assert.assertEquals("Solicitud pendiente",respuesta.getRespuesta().getListaDebin().get(2).getEstado());

    }



}
