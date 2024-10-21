/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.manager;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.impl.ClienteManagerImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConfirmacionAltaDestinatarioInViewMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosRCAManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AltaDestinatarioManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.impl.AltaDestinatarioManagerImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AltaDestinatarioManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class AltaDestinatarioManagerTest {

    /** The alta destinatario manager. */
    @InjectMocks
    private AltaDestinatarioManager altaDestinatarioManager = new AltaDestinatarioManagerImpl();

    /** The alta destinatario BO. */
    @Mock
    private AltaDestinatarioBO altaDestinatarioBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The casuistica. */
    @Mock
    private CasuisticaAgendaDestinatarios casuistica;

    /** The sesion. */
    @Mock
    private SesionAgendaDestinatarios sesion;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The agenda destinatarios RCA Manager. */
    @Mock
    private AgendaDestinatariosRCAManager agendaDestinatariosRCAManager;

    @Mock
    private ClienteManagerImpl clienteManagerImpl;

    @Mock
    private ClienteBO clienteBO;

    /**
     * Iniciar mocks.
     *
     */
    private void iniciarMocks(EstadoRespuesta validacionRSA) throws BusinessException {
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        Mockito.when(sesion.getContadorAlta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionCliente.getCliente()).thenReturn(ConfirmacionAltaDestinatarioInViewMock.obtenerCliente());
        Mockito.when(sesionParametros.getRegistroSession())
                .thenReturn(ConfirmacionAltaDestinatarioInViewMock.obtenerRegistroSesion());

        Respuesta<ConfirmacionAltaDestinatarioView> resRCA = new Respuesta<ConfirmacionAltaDestinatarioView>();
        resRCA.setEstadoRespuesta(validacionRSA);
        Mockito.when(agendaDestinatariosRCAManager.ejecutarValidacionRSA(
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.any(TipoOperacionACTAGEDESTEnum.class),
                Matchers.any(TipoAgendaEnum.class))).thenReturn(resRCA);

        Respuesta<List<BigDecimal>> antiguedadClaveToken;
        antiguedadClaveToken = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
        Mockito.when(clienteManagerImpl.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()))).thenReturn(antiguedadClaveToken);
    }

    /**
     * Dado ConfirmacionAltaDestinatarioInView con referencia, cuando seinvoca al
     * metodo "obtenerConfirmacionAltaDestinatarioRio", obtengo la respuesta OK del
     * ConfirmacionAltaDestinatarioView.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void confirmacionAltaDestinatarioRioOK() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);

        Mockito.when(altaDestinatarioBO.confirmarAltaDestinatarioRio(Matchers.anyString(), Matchers.any(Cliente.class),
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(obtenerRespuestaDTOOK());
        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuestaView = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioRio(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());
        // Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
        Assert.assertEquals("12:34", respuestaView.getRespuesta().getHora());
        Assert.assertEquals("00000001", respuestaView.getRespuesta().getNroComprobante());
        Assert.assertEquals(
                "<p>El destinatario <b>\"Contador\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>",
                respuestaView.getRespuesta().getMensajeEfectivizacion());
    }

    /**
     * Dado confirmacion alta destinatario in view con titular cuando invoca obtener
     * confirmacion alta destinatario obtengo respuesta view con error cuenta
     * invalida.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void confirmacionAltaDestinatarioRioErrorCuentaInvalida() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);
        Mockito.when(altaDestinatarioBO.confirmarAltaDestinatarioRio(Matchers.anyString(), Matchers.any(Cliente.class),
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(obtenerRespuestaDTOERRORCuentaInvalida());
        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuestaView = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioRio(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
        Assert.assertNull(respuestaView.getRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"MUÑOZ, CÉSAR ALBERTO.\"</b>a</p><p>tu<b>agenda de destinatarios</b>porque la cuenta ingresada</p><p>no es válida.</p>",
                respuestaView.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("cuentaInvalida", respuestaView.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("CUENTA_INVALIDA_ALTA_DESTINATARIO",
                respuestaView.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    @Test
    public void confirmacionAltaDestinatarioAliasOK() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);
        when(altaDestinatarioBO.confirmarAltaDestinatarioAlias(Matchers.anyString(), Matchers.any(Cliente.class),
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.anyString(), Matchers.anyString()))
                        .thenReturn(obtenerRespuestaDTOOK());

        when(sesionParametros.getValidacionHash()).thenReturn("1FE23446289B2BA9E04CB8073E6A0732");

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuestaView = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioAlias(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
        Assert.assertEquals("12:34", respuestaView.getRespuesta().getHora());
        Assert.assertEquals("00000001", respuestaView.getRespuesta().getNroComprobante());
        Assert.assertEquals(
                "<p>El destinatario <b>\"Contador\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>",
                respuestaView.getRespuesta().getMensajeEfectivizacion());
    }

    /**
     * Dado confirmacion alta destinatario in view con referencia cuando invoca
     * obtener confirmacion alta destinatario obtengo respuesta view con error
     * destinatario agendado.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void confirmacionAltaDestinatarioRioError() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);
        Mockito.when(altaDestinatarioBO.confirmarAltaDestinatarioRio(Matchers.anyString(), Matchers.any(Cliente.class),
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(obtenerRespuestaDTOERRORDestinatarioAgendado());
        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuestaView = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioRio(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());
        // Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
        Assert.assertNull(respuestaView.getRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de</b> </p><p><b>destinatarios</b>porque ya se encuentra agendado.</p>",
                respuestaView.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("destinatarioAgendado", respuestaView.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("DESTINATARIO_AGENDADO_ALTA_DESTINATARIO",
                respuestaView.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Dado confirmacion alta destinatario in view con referencia cuando invoca
     * obtener confirmacion alta destinatario obtengo respuesta view con error
     * indeseado.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void confirmacionAltaDestinatarioRioErrorServicio() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);
        Mockito.when(altaDestinatarioBO.confirmarAltaDestinatarioRio(Matchers.anyString(), Matchers.any(Cliente.class),
                Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(obtenerRespuestaDTOERRORIndeseado());
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        Mockito.when(sesion.getContadorAlta()).thenReturn(new ContadorIntentos(2));
        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuestaView = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioRio(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());
        // Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
        Assert.assertNull(respuestaView.getRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de destinatarios.</b></p>",
                respuestaView.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("errorServicio", respuestaView.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("ERROR_SERVICIO_ALTA_DESTINATARIO",
                respuestaView.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener respuesta DTOERROR indeseado.
     *
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaDestinatarioDTO> obtenerRespuestaDTOERRORIndeseado() {
        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = obtenerRespuestaDTOERRORCuentaInvalida();
        respuestaDTO.getItemsMensajeRespuesta().set(0, obtenerItem(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de destinatarios.</b></p>",
                "errorServicio", "ERROR_SERVICIO_ALTA_DESTINATARIO"));
        return respuestaDTO;
    }

    /**
     * Obtener respuesta DTOERROR destinatario agendado.
     *
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaDestinatarioDTO> obtenerRespuestaDTOERRORDestinatarioAgendado() {
        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = obtenerRespuestaDTOERRORCuentaInvalida();
        respuestaDTO.getItemsMensajeRespuesta().set(0, obtenerItem(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de</b> </p><p><b>destinatarios</b>porque ya se encuentra agendado.</p>",
                "destinatarioAgendado", "DESTINATARIO_AGENDADO_ALTA_DESTINATARIO"));
        return respuestaDTO;
    }

    /**
     * Obtener respuesta DTOERROR cuenta invalida.
     *
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaDestinatarioDTO> obtenerRespuestaDTOERRORCuentaInvalida() {
        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = new Respuesta<ComprobanteAltaDestinatarioDTO>();
        respuestaDTO.setRespuesta(null);
        respuestaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaDTO.setRespuestaVacia(Boolean.TRUE);
        respuestaDTO.setItemMensajeRespuesta(obtenerItems(
                "<p>No pudimos añadir al destinatario<b>\"MUÑOZ, CÉSAR ALBERTO.\"</b>a</p><p>tu<b>agenda de destinatarios</b>porque la cuenta ingresada</p><p>no es válida.</p>",
                "cuentaInvalida", TipoError.CUENTA_INVALIDA_ALTA_DESTINATARIO.getDescripcion()));
        return respuestaDTO;
    }

    /**
     * Obtener items.
     *
     * @param mensaje
     *            the mensaje
     * @param tag
     *            the tag
     * @param tipoError
     *            the tipo error
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItems(String mensaje, String tag, String tipoError) {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>(1);
        items.add(obtenerItem(mensaje, tag, tipoError));
        return items;
    }

    /**
     * Obtener item.
     *
     * @param mensaje
     *            the mensaje
     * @param tag
     *            the tag
     * @param tipoError
     *            the tipo error
     * @return the item mensaje respuesta
     */
    private ItemMensajeRespuesta obtenerItem(String mensaje, String tag, String tipoError) {
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje(mensaje);
        item.setTag(tag);
        item.setTipoError(tipoError);
        return item;
    }

    /**
     * Obtiene la respuesta OK del DTO.
     *
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaDestinatarioDTO> obtenerRespuestaDTOOK() {
        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = new Respuesta<ComprobanteAltaDestinatarioDTO>();
        respuestaDTO.setRespuesta(obtenerComprobanteAltaDestinatarioDTO());
        respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDTO.setRespuestaVacia(Boolean.FALSE);
        return respuestaDTO;
    }

    /**
     * Obtiene el comprobante alta destinatario DTO.
     *
     * @return the comprobante alta destinatario DTO
     */
    private ComprobanteAltaDestinatarioDTO obtenerComprobanteAltaDestinatarioDTO() {
        ComprobanteAltaDestinatarioDTO dto = new ComprobanteAltaDestinatarioDTO();
        dto.setFecha(new Date());
        dto.setHora("12:34");
        dto.setNroComprobante("00000001");
        dto.setMensajeEfectivizacion(
                "<p>El destinatario <b>\"Contador\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>");
        return dto;
    }

    /**
     * Dado ConfirmacionAltaDestinatarioInView con referencia, cuando seinvoca al
     * metodo "obtenerConfirmacionAltaDestinatarioOtrosBancos", obtengo la respuesta
     * OK del ConfirmacionAltaDestinatarioView.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void confirmacionAltaDestinatarioOtrosBancosOk() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);
        Mockito.when(altaDestinatarioBO.confirmarAltaDestinatarioOtrosBancos(Matchers.anyString(),
                Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(obtenerRespuestaDTOOK());

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuestaView = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioOtrosBancos(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
        Assert.assertEquals("12:34", respuestaView.getRespuesta().getHora());
        Assert.assertEquals("00000001", respuestaView.getRespuesta().getNroComprobante());
        Assert.assertEquals(
                "<p>El destinatario <b>\"Contador\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>",
                respuestaView.getRespuesta().getMensajeEfectivizacion());
    }

    /**
     * Dado confirmacion alta destinatario in view con referencia cuando invoca
     * obtener confirmacion alta destinatario obtengo respuesta view con error
     * indeseado.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void confirmacionAltaDestinatarioOtrosBancosErrorServicio() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);
        Mockito.when(altaDestinatarioBO.confirmarAltaDestinatarioOtrosBancos(Matchers.anyString(),
                Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(obtenerRespuestaDTOERRORIndeseado());

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuestaView = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioOtrosBancos(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
        Assert.assertNull(respuestaView.getRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de destinatarios.</b></p>",
                respuestaView.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("errorServicio", respuestaView.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("ERROR_SERVICIO_ALTA_DESTINATARIO",
                respuestaView.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener confirmacion alta destinatario envio efectivo ok.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void confirmacionAltaDestinatarioEnvioEfectivoOk() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);
        Respuesta<ComprobanteAltaDestinatarioDTO> resComprobante = new Respuesta<ComprobanteAltaDestinatarioDTO>();
        ComprobanteAltaDestinatarioDTO comprobante = new ComprobanteAltaDestinatarioDTO();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
        comprobante.setMensajeEfectivizacion(
                "<p>El destinatario <b>\"{0}\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>");
        resComprobante.setRespuesta(comprobante);

        Mockito.when(sesion.getCuitCuil()).thenReturn(" L");
        Mockito.when(altaDestinatarioBO.confirmarAltaDestinatarioEnvioEfectivo(Matchers.anyString(),
                Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class),
                Matchers.anyString())).thenReturn(resComprobante);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(
                "<p>El destinatario <b>\"{0}\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> res = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioEnvioEfectivo(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>El destinatario <b>\"Contador\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>",
                res.getRespuesta().getMensajeEfectivizacion());
    }

    /**
     * Obtener confirmacion alta destinatario envio efectivo error.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void confirmacionAltaDestinatarioEnvioEfectivoError() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.OK);

        Respuesta<ComprobanteAltaDestinatarioDTO> resComprobante = new Respuesta<ComprobanteAltaDestinatarioDTO>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTag("errorServicio");
        resComprobante.add(item);
        resComprobante.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(sesion.getCuitCuil()).thenReturn(" L");
        Mockito.when(altaDestinatarioBO.confirmarAltaDestinatarioEnvioEfectivo(Matchers.anyString(),
                Matchers.any(Cliente.class), Matchers.any(ConfirmacionAltaDestinatarioView.class),
                Matchers.anyString())).thenReturn(resComprobante);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> res = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioEnvioEfectivo(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals("reintentar", res.getItemsMensajeRespuesta().get(0).getDetalleTipoError());
    }
    
    @Test
    public void confirmacionAltaDestinatarioAliasErrorRSA() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.ERROR);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioAlias(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmacionAltaDestinatarioRioErrorRSA() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.ERROR);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioRio(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmacionAltaDestinatarioOtrosBancosErrorRSA() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.ERROR);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioOtrosBancos(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmacionAltaDestinatarioEnvioEfectivoErrorRSA() throws BusinessException {
        // When
        iniciarMocks(EstadoRespuesta.ERROR);

        // Then
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = altaDestinatarioManager
                .obtenerConfirmacionAltaDestinatarioEnvioEfectivo(
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView());

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

}
