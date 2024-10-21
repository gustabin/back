package ar.com.santanderrio.obp.servicios.debinrecurrente.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.debinapi.dto.RecurrenceActionEnum;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dao.DebinRecurrenteApiDAO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dao.DebinRecurrenteDAO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.BuyerRecurrenceListRequestDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.manager.ObtenerRecurrenciasView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.mock.DebinRecurrenteApiMock;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.AccionRecurrenciaResponseView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.RecurrenciaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DebinRecurrenteBOImplTest {
    public static final String CBU = "0720175888000037544236";
    public static final String CUIT = "20111144443";
    public static final String PAGE_NUMBER  = "3";

    @InjectMocks
    private DebinRecurrenteBO debinRecurrenteBO = new DebinRecurrenteBOImpl();

    @Spy
    private RespuestaFactory respuestaFactory;

    @Mock
    private DebinRecurrenteApiDAO debinRecurrenteApiDAO;
    @Mock
    private DebinRecurrenteDAO debinRecurrenteDAO;

    @Mock
    private EstadisticaManager estadisticaManager;

    @Mock
    private MensajeBO mensajeBO;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obtenerRecurrenciasPorCompradorOK()throws DAOException {

        //MOCK
        BuyerRecurrenceListRequestDTO recurrencesMock = DebinRecurrenteApiMock.getDefaultRecurrenceListDTO();
        doReturn(recurrencesMock).when(debinRecurrenteApiDAO).getRecurrenceList(CBU, CUIT, PAGE_NUMBER);

        //CALL METHOD
        Cliente cliente = new Cliente();
        cliente.setNumeroCUILCUIT(CUIT);
        ObtenerRecurrenciasView obtenerRecurrenciasView = new ObtenerRecurrenciasView();
        obtenerRecurrenciasView.setCbu(CBU);
        obtenerRecurrenciasView.setNumeroPagina(PAGE_NUMBER);
        Respuesta<BuyerRecurrenceListRequestDTO> respuesta =debinRecurrenteBO.obtenerRecurrenciasPorComprador(cliente, obtenerRecurrenciasView);

        //ASSERTS
        BuyerRecurrenceListRequestDTO recurrences = respuesta.getRespuesta();
        Assert.assertEquals(3,recurrences.getRecurrences().size());
        Assert.assertEquals("INACTIVE_TEMP", recurrences.getRecurrences().get(0).getStatus());
        Assert.assertEquals("INACTIVE_DEF", recurrences.getRecurrences().get(1).getStatus());
        Assert.assertEquals("PENDING", recurrences.getRecurrences().get(2).getStatus());
    }

    @Test
    public void obtenerRecurrenciasPorCompradorERROR()throws DAOException {

        //MOCK
        doReturn(null).when(debinRecurrenteApiDAO).getRecurrenceList(CBU, CUIT, PAGE_NUMBER);

        //CALL METHOD
        Cliente cliente = new Cliente();
        cliente.setNumeroCUILCUIT(CUIT);
        ObtenerRecurrenciasView obtenerRecurrenciasView = new ObtenerRecurrenciasView();
        obtenerRecurrenciasView.setCbu(CBU);
        obtenerRecurrenciasView.setNumeroPagina(PAGE_NUMBER);
        Respuesta<BuyerRecurrenceListRequestDTO> respuesta =debinRecurrenteBO.obtenerRecurrenciasPorComprador(cliente, obtenerRecurrenciasView);

        //ASSERTS
        BuyerRecurrenceListRequestDTO recurrences = respuesta.getRespuesta();
        Assert.assertNull(recurrences);
        Assert.assertEquals(EstadoRespuesta.WARNING,respuesta.getEstadoRespuesta());

    }


    @Test
    public void obtenerRecurrenciasPorCompradorERROR_DAOException()throws DAOException {

        //MOCK
        doThrow(new DAOException("oops")).when(debinRecurrenteApiDAO).getRecurrenceList(CBU, CUIT, PAGE_NUMBER);

        ReflectionTestUtils.setField(respuestaFactory, "mensajeBO", mensajeBO);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("123");
        mensaje.setMensaje("test");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(anyString())).thenReturn(mensaje);

        //CALL METHO
        Cliente cliente = new Cliente();
        cliente.setNumeroCUILCUIT(CUIT);
        ObtenerRecurrenciasView obtenerRecurrenciasView = new ObtenerRecurrenciasView();
        obtenerRecurrenciasView.setCbu(CBU);
        obtenerRecurrenciasView.setNumeroPagina(PAGE_NUMBER);
        Respuesta<BuyerRecurrenceListRequestDTO> respuesta =debinRecurrenteBO.obtenerRecurrenciasPorComprador(cliente, obtenerRecurrenciasView);

        //ASSERTS
        BuyerRecurrenceListRequestDTO recurrences = respuesta.getRespuesta();
        Assert.assertNull(recurrences);
        Assert.assertEquals(EstadoRespuesta.ERROR,respuesta.getEstadoRespuesta());

    }

    @Test
    public void aceptarRecurrenciaOk() throws Exception{

        //mock recurrencia main method input
        RecurrenciaView recurrencia = new RecurrenciaView();
        recurrencia.setPending(true);

        //mock invokeApplyAction method
        String successfulMessage = "¡Listo! Adheriste a DEBIN Recurrente a [EMPRESA] / [SERVICIO].";
        RecurrenceActionEnum recurrenceAction = RecurrenceActionEnum.CONFIRM;

        //mock
        AccionRecurrenciaResponseView respuestaServicio = new AccionRecurrenciaResponseView(successfulMessage);

        Mockito.when(debinRecurrenteDAO.applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage)).thenReturn(respuestaServicio);

        //mock
        String estadisticasConstants = EstadisticasConstants.ACTIVAR_RECURRENCIA;
        Mockito.when(estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);

        //mock
        Respuesta<AccionRecurrenciaResponseView> respuestaMocked = new Respuesta<AccionRecurrenciaResponseView>();
        respuestaMocked.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaMocked.setRespuesta(respuestaServicio);

        //call method
        Respuesta<AccionRecurrenciaResponseView> respuesta = debinRecurrenteBO.reactivarRecurrencia(recurrencia);

        //asserts
        Assert.assertEquals(respuesta.getRespuesta().getMensaje(), respuestaMocked.getRespuesta().getMensaje());
        verify(debinRecurrenteDAO, times(1)).applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage);
        verify(estadisticaManager, times(1)).add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        verify(respuestaFactory, times(1)).crearRespuestaOk(respuestaServicio);
    }

    @Test
    public void pausarRecurrenciaOk() throws Exception{

        //mock recurrencia main method input
        RecurrenciaView recurrencia = new RecurrenciaView();

        //mock invokeApplyAction method
        String successfulMessage = "¡Listo! Pausaste tu adhesión a DEBIN Recurrente de [EMPRESA] / [SERVICIO].";
        RecurrenceActionEnum recurrenceAction = RecurrenceActionEnum.INACTIVATE_TEMP;

        //mock
        AccionRecurrenciaResponseView respuestaServicio = new AccionRecurrenciaResponseView(successfulMessage);

        Mockito.when(debinRecurrenteDAO.applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage)).thenReturn(respuestaServicio);

        //mock
        String estadisticasConstants = EstadisticasConstants.PAUSAR_RECURRENCIA;
        Mockito.when(estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);

        //mock
        Respuesta<AccionRecurrenciaResponseView> respuestaMocked = new Respuesta<AccionRecurrenciaResponseView>();
        respuestaMocked.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaMocked.setRespuesta(respuestaServicio);

        //call method
        Respuesta<AccionRecurrenciaResponseView> respuesta = debinRecurrenteBO.pausarRecurrencia(recurrencia);

        //asserts
        Assert.assertEquals(respuesta.getRespuesta().getMensaje(), respuestaMocked.getRespuesta().getMensaje());
        verify(debinRecurrenteDAO, times(1)).applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage);
        verify(estadisticaManager, times(1)).add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        verify(respuestaFactory, times(1)).crearRespuestaOk(respuestaServicio);
    }

    @Test
    public void reactivarRecurrenciaOk() throws Exception{

        //mock recurrencia main method input
        RecurrenciaView recurrencia = new RecurrenciaView();
        recurrencia.setPending(false);

        //mock invokeApplyAction method
        String successfulMessage = "¡Listo! Reactivaste tu adhesión a DEBIN Recurrente de [EMPRESA] / [SERVICIO].";
        RecurrenceActionEnum recurrenceAction = RecurrenceActionEnum.REACTIVATE;

        //mock
        AccionRecurrenciaResponseView respuestaServicio = new AccionRecurrenciaResponseView(successfulMessage);

        Mockito.when(debinRecurrenteDAO.applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage)).thenReturn(respuestaServicio);

        //mock
        String estadisticasConstants = EstadisticasConstants.REACTIVAR_RECURRENCIA;
        Mockito.when(estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);

        //mock
        Respuesta<AccionRecurrenciaResponseView> respuestaMocked = new Respuesta<AccionRecurrenciaResponseView>();
        respuestaMocked.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaMocked.setRespuesta(respuestaServicio);

        //call method
        Respuesta<AccionRecurrenciaResponseView> respuesta = debinRecurrenteBO.reactivarRecurrencia(recurrencia);

        //asserts
        Assert.assertEquals(respuesta.getRespuesta().getMensaje(), respuestaMocked.getRespuesta().getMensaje());
        verify(debinRecurrenteDAO, times(1)).applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage);
        verify(estadisticaManager, times(1)).add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        verify(respuestaFactory, times(1)).crearRespuestaOk(respuestaServicio);
    }

    @Test
    public void desactivarRecurrenciaOk() throws Exception{

        //mock recurrencia main method input
        RecurrenciaView recurrencia = new RecurrenciaView();
        recurrencia.setPending(true);

        //mock invokeApplyAction method
        String successfulMessage = "¡Listo! Rechazaste la solicitud de adhesión a DEBIN Recurrente de [EMPRESA] / [SERVICIO].";
        RecurrenceActionEnum recurrenceAction = RecurrenceActionEnum.REJECT;

        //mock
        AccionRecurrenciaResponseView respuestaServicio = new AccionRecurrenciaResponseView(successfulMessage);

        Mockito.when(debinRecurrenteDAO.applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage)).thenReturn(respuestaServicio);

        //mock
        String estadisticasConstants = EstadisticasConstants.DESACTIVAR_RECURRENCIA;
        Mockito.when(estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);

        //mock
        Respuesta<AccionRecurrenciaResponseView> respuestaMocked = new Respuesta<AccionRecurrenciaResponseView>();
        respuestaMocked.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaMocked.setRespuesta(respuestaServicio);

        //call method
        Respuesta<AccionRecurrenciaResponseView> respuesta = debinRecurrenteBO.desubscribirRecurrencia(recurrencia);

        //asserts
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

        Assert.assertEquals(respuesta.getRespuesta().getMensaje(), respuestaMocked.getRespuesta().getMensaje());
        verify(debinRecurrenteDAO, times(1)).applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage);
        verify(estadisticaManager, times(1)).add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        verify(respuestaFactory, times(1)).crearRespuestaOk(respuestaServicio);
    }

    @Test
    public void inactivarRecurrenciaOk() throws Exception{

        //mock recurrencia main method input
        RecurrenciaView recurrencia = new RecurrenciaView();
        recurrencia.setPending(false);

        //mock invokeApplyAction method
        String successfulMessage = "¡Listo! Diste de baja tu adhesión a DEBIN Recurrente de [EMPRESA] / [SERVICIO].";
        RecurrenceActionEnum recurrenceAction = RecurrenceActionEnum.INACTIVATE_DEF;

        //mock
        AccionRecurrenciaResponseView respuestaServicio = new AccionRecurrenciaResponseView(successfulMessage);

        Mockito.when(debinRecurrenteDAO.applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage)).thenReturn(respuestaServicio);

        //mock
        String estadisticasConstants = EstadisticasConstants.DESUBSCRIBIR_RECURRENCIA;
        Mockito.when(estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);

        //mock
        Respuesta<AccionRecurrenciaResponseView> respuestaMocked = new Respuesta<AccionRecurrenciaResponseView>();
        respuestaMocked.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaMocked.setRespuesta(respuestaServicio);

        //call method
        Respuesta<AccionRecurrenciaResponseView> respuesta = debinRecurrenteBO.desubscribirRecurrencia(recurrencia);

        //asserts
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

        Assert.assertEquals(respuesta.getRespuesta().getMensaje(), respuestaMocked.getRespuesta().getMensaje());
        verify(debinRecurrenteDAO, times(1)).applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage);
        verify(estadisticaManager, times(1)).add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        verify(respuestaFactory, times(1)).crearRespuestaOk(respuestaServicio);
    }

    @Test
    public void aceptarRecurrenciaERROR() throws Exception{

        ReflectionTestUtils.setField(respuestaFactory, "mensajeBO", mensajeBO);
        //mock recurrencia main method input
        RecurrenciaView recurrencia = new RecurrenciaView();
        recurrencia.setPending(true);

        //mock invokeApplyAction method
        String successfulMessage = "¡Listo! Adheriste a DEBIN Recurrente a [EMPRESA] / [SERVICIO].";
        RecurrenceActionEnum recurrenceAction = RecurrenceActionEnum.CONFIRM;

        //mock
        AccionRecurrenciaResponseView respuestaServicio = null;

        Mockito.when(debinRecurrenteDAO.applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage)).thenReturn(respuestaServicio);

        //mock
        String estadisticasConstants = EstadisticasConstants.ACTIVAR_RECURRENCIA;
        Mockito.when(estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);

        //mock
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("123");
        mensaje.setMensaje("test");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(anyString())).thenReturn(mensaje);


        //call method
        Respuesta<AccionRecurrenciaResponseView> respuesta = debinRecurrenteBO.reactivarRecurrencia(recurrencia);

        //asserts
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

        verify(debinRecurrenteDAO, times(1)).applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage);
        verify(estadisticaManager, times(1)).add(EstadisticasConstants.ACTIVAR_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        verify(respuestaFactory, times(1)).crearRespuestaError(null, TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
    }


    @Test
    public void activarRecurrenciaERROR_exception() throws Exception{

        ReflectionTestUtils.setField(respuestaFactory, "mensajeBO", mensajeBO);

        //mock recurrencia main method input
        RecurrenciaView recurrencia = new RecurrenciaView();
        recurrencia.setPending(true);

        //mock invokeApplyAction method
        String successfulMessage = "¡Listo! Adheriste a DEBIN Recurrente a [EMPRESA] / [SERVICIO].";
        RecurrenceActionEnum recurrenceAction = RecurrenceActionEnum.CONFIRM;

        //mock
        AccionRecurrenciaResponseView respuestaServicio = null;

        Mockito.when(debinRecurrenteDAO.applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage)).thenThrow(new Exception());

        //mock
        String estadisticasConstants = EstadisticasConstants.ACTIVAR_RECURRENCIA;
        Mockito.when(estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);

        //mock
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("123");
        mensaje.setMensaje("test");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(anyString())).thenReturn(mensaje);

        //call method
        Respuesta<AccionRecurrenciaResponseView> respuesta = debinRecurrenteBO.reactivarRecurrencia(recurrencia);

        //asserts
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        verify(debinRecurrenteDAO, times(1)).applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage);
        verify(estadisticaManager, times(1)).add(EstadisticasConstants.ACTIVAR_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        verify(respuestaFactory, times(1)).crearRespuestaError("", TipoError.ERROR_GENERICO,
                "6867",RecurrenceActionEnum.CONFIRM.toString());
    }



}
