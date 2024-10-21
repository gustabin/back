package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
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
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AltaDestinatarioBOImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.CasuisticaAgendaDestinatariosImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.CasuisticaAltaDestinatarioImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.AgendaDestinatarioOutEntityMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock.ConfirmacionAltaDestinatarioInViewMock;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAltaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AltaDestinatarioBOTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class AltaDestinatarioBOTest {

    /** The agenda destinatario DAO. */
    @Mock
    private AgendaDestinatarioDAO agendaDestinatarioDAO;

    /** The casuistica. */
    @InjectMocks
    @Spy
    private CasuisticaAgendaDestinatariosImpl casuistica = new CasuisticaAgendaDestinatariosImpl();

    /** The casuistica. */
    @InjectMocks
    @Spy
    private CasuisticaAltaDestinatarios casuisticaAlta = new CasuisticaAltaDestinatarioImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private javax.validation.Validator valid;

    /** The alta destinatario BO. */
    @InjectMocks
    private AltaDestinatarioBO altaDestinatarioBO = new AltaDestinatarioBOImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private SesionAgendaDestinatarios sesionAgenda;

    /**
     * Continuar configuracion alta destinatario test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void continuarConfiguracionAltaDestinatarioTest() throws DAOException {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("231237123");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cliente.getCuentas().add(cuenta);

        DatosCliente datosCliente = new DatosCliente();
        datosCliente.setCodigoError(0);
        datosCliente.setNombre("pepe");
        datosCliente.setNumeroCUILCUIT("20212312339");
        Mockito.when(agendaDestinatarioDAO.validarCuenta(Matchers.any(ValidacionCuentaInEntity.class)))
                .thenReturn(datosCliente);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

        Respuesta<ConfiguracionAltaDestinatarioDTO> res = altaDestinatarioBO
                .continuarConfiguracionAltaDestinatario(cliente, "012-123712/3", "02");
        Assert.assertEquals(TipoError.ERROR_CUENTA_PROPIA.getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());

        res = altaDestinatarioBO.continuarConfiguracionAltaDestinatario(cliente, "012-124112/3", "01");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

        datosCliente.setCodigoError(12);
        res = altaDestinatarioBO.continuarConfiguracionAltaDestinatario(cliente, "012-124112/3", "01");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());

        Mockito.when(agendaDestinatarioDAO.validarCuenta(Matchers.any(ValidacionCuentaInEntity.class)))
                .thenThrow(new DAOException());
        res = altaDestinatarioBO.continuarConfiguracionAltaDestinatario(cliente, "012-124112/3", "01");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Dado un destinatario, cuando se invoca al metodo "confirmarAltaDestinatario",
     * obtengo la confirmacion.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoDestinatarioCuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionOK()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityOK());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeOK());

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals("12:34", respuestaDTO.getRespuesta().getHora());
        Assert.assertEquals("00000001", respuestaDTO.getRespuesta().getNroComprobante());
        Assert.assertEquals(
                "<p>El destinatario <b>\"Contador\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>",
                respuestaDTO.getRespuesta().getMensajeEfectivizacion());
    }

    /**
     * Dado un destinatario, cuando se invoca al metodo "confirmarAltaDestinatario",
     * obtengo la respuesta de la confirmacion OK con error en el mensaje
     * efectivizar la operacion.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void dadoDestinatarioCuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionOKConErrorMensajeEfectivizar()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityOK());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenThrow(Exception.class);

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals("12:34", respuestaDTO.getRespuesta().getHora());
        Assert.assertEquals("00000001", respuestaDTO.getRespuesta().getNroComprobante());
        Assert.assertNull(respuestaDTO.getRespuesta().getMensajeEfectivizacion());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void confirmarAltaDestinatarioAliasOK() throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityOK());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenThrow(Exception.class);

        ConfirmacionAltaDestinatarioView confirmacion = new ConfirmacionAltaDestinatarioView();
        confirmacion.setReferencia("Valeriano");
        confirmacion.setTitular("COMIGNAGHI  VALERIANO PAUL TAD");
        confirmacion.setAlias("AliasDeVale");
        confirmacion.setNroCuenta("000-063917/0");
        confirmacion.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        confirmacion.setNumeroDocumento("20-13238861-0");
        confirmacion.setCbu("0720000788000006391704");
        confirmacion.setDireccionCorreo("valeriano@gmail.com");

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO
                .confirmarAltaDestinatarioAlias("123456789012345", obtenerCliente(), confirmacion, "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals("12:34", respuestaDTO.getRespuesta().getHora());
        Assert.assertEquals("00000001", respuestaDTO.getRespuesta().getNroComprobante());
        Assert.assertNull(respuestaDTO.getRespuesta().getMensajeEfectivizacion());
    }

    @Test(expected = BusinessException.class)
    public void confirmarAltaDestinatarioAliasError() throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityOK());

        altaDestinatarioBO.confirmarAltaDestinatarioAlias("123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");
    }

    /**
     * Dado un titular como destinatario y el codigo extendido de error "10014001",
     * cuando se invoca al metodo "confirmarAltaDestinatario", obtengo la respuesta
     * de la confirmacion con error de cuenta invalida.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoTitularComoDestinatarioYCodExtendidoError10014001CuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionErrorCuentaInvalida()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityTipoCuentaInvalido());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensaje1488());
        ConfirmacionAltaDestinatarioView datos = ConfirmacionAltaDestinatarioInViewMock
                .obtenerConfirmacionAltaDestinatarioInView();
        datos.setReferencia(StringUtils.repeat("", 30));

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO
                .confirmarAltaDestinatarioRio("123456789012345", obtenerCliente(), datos, "MUÑOZ, CÉSAR ALBERTO.", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"MUÑOZ, CÉSAR ALBERTO.\"</b>a</p><p>tu<b>agenda de destinatarios</b>porque la cuenta ingresada</p><p>no es válida.</p>",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("cuentaInvalida", respuestaDTO.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("CUENTA_INVALIDA_ALTA_DESTINATARIO",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Dado el codigo extendido de error "10011634", cuando se invoca al metodo
     * "confirmarAltaDestinatario", obtengo la respuesta de la confirmacion con
     * error de destinatario agendado.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoCodExtendidoError10011634CuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionErrorDestinatarioAgendado()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityDestinatarioAgendado());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensaje1455());

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de</b> </p><p><b>destinatarios</b>porque ya se encuentra agendado.</p>",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("destinatarioAgendado", respuestaDTO.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("DESTINATARIO_AGENDADO_ALTA_DESTINATARIO",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Dado el codigo extendido de error "10010091", cuando se invoca al metodo
     * "confirmarAltaDestinatario", obtengo la respuesta de la confirmacion con
     * error de cuenta invalida.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoCodExtendidoError10010091CuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionErrorCuentaInvalida()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityCuentaInvalida());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensaje1488());

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de destinatarios</b>porque la cuenta ingresada</p><p>no es válida.</p>",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("cuentaInvalida", respuestaDTO.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("CUENTA_INVALIDA_ALTA_DESTINATARIO",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Dado el codigo extendido de error "10000054", cuando se invoca al metodo
     * "confirmarAltaDestinatario", obtengo la respuesta de la confirmacion con
     * error de cuenta invalida.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoCodExtendidoError10000054CuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionErrorCuentaInvalida()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityCuentaInexistente());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensaje1488());

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de destinatarios</b>porque la cuenta ingresada</p><p>no es válida.</p>",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("cuentaInvalida", respuestaDTO.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("CUENTA_INVALIDA_ALTA_DESTINATARIO",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Dado el codigo extendido de error distinto a cero, cuando se invoca al metodo
     * "confirmarAltaDestinatario", obtengo la respuesta de la confirmacion con
     * error de servicio.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoCodExtendidoErrorDistintoCeroCuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionErrorServicio()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityErrorServicio());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensaje1489());

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de destinatarios.</b></p>",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("errorServicio", respuestaDTO.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("ERROR_SERVICIO_ALTA_DESTINATARIO",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Dado un destinatario, cuando se invoca al metodo "confirmarAltaDestinatario",
     * obtengo la respuesta de la confirmacion con error de DAO exception.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void dadoDestinatarioCuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionErrorDAOException()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenThrow(DAOException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensaje1489());

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>No pudimos añadir al destinatario<b>\"Contador\"</b>a</p><p>tu<b>agenda de destinatarios.</b></p>",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("errorServicio", respuestaDTO.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals("ERROR_SERVICIO_ALTA_DESTINATARIO",
                respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Dado un destinatario, cuando se invoca al metodo "confirmarAltaDestinatario",
     * obtengo business exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings({ "unchecked", "unused" })
    @Test(expected = BusinessException.class)
    public void dadoDestinatarioCuandoInvocaConfirmarAltaDestinatarioObtengoBusinessException()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenThrow(Exception.class);

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioInView(), "Contador", "T");
    }

    /**
     * Configuracion alta destinatario DTO.
     */
    @Test
    public void configuracionAltaDestinatarioDTO() {
        ConfiguracionAltaDestinatarioDTO altaDestinatarioDTO = new ConfiguracionAltaDestinatarioDTO();
        altaDestinatarioDTO.setNombreYApellido("Ramon perez");
        altaDestinatarioDTO.setCuitOCuil("cuitOCuil");
        altaDestinatarioDTO.setNumeroCuil("nro");
        altaDestinatarioDTO.setNup("nup");
        Assert.assertEquals("Ramon perez", altaDestinatarioDTO.getNombreYApellido());
        Assert.assertEquals("cuitOCuil", altaDestinatarioDTO.getCuitOCuil());
        Assert.assertEquals("nro", altaDestinatarioDTO.getNumeroCuil());
        Assert.assertEquals("nup", altaDestinatarioDTO.getNup());
        Assert.assertNotEquals(altaDestinatarioDTO.hashCode(), 0);
        Assert.assertEquals(
                "ConfiguracionAltaDestinatarioDTO[nombreYApellido=Ramon perez,cuitOCuil=cuitOCuil,numeroCuil=nro,nup=nup]",
                altaDestinatarioDTO.toString());

        Assert.assertTrue(altaDestinatarioDTO.equals(altaDestinatarioDTO));
    }

    /**
     * Obtiene el mensaje OK.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensajeOK() {
        Mensaje msj = new Mensaje();
        msj.setMensaje(
                "<p>El destinatario <b>\"{0}\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>");
        return msj;
    }

    /**
     * Obtiene el mensaje 1455.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensaje1455() {
        Mensaje msj = new Mensaje();
        msj.setMensaje(
                "<p>No pudimos añadir al destinatario<b>\"{0}\"</b>a</p><p>tu<b>agenda de</b> </p><p><b>destinatarios</b>porque ya se encuentra agendado.</p>");
        return msj;
    }

    /**
     * Obtiene el mensaje 1488.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensaje1488() {
        Mensaje msj = new Mensaje();
        msj.setMensaje(
                "<p>No pudimos añadir al destinatario<b>\"{0}\"</b>a</p><p>tu<b>agenda de destinatarios</b>porque la cuenta ingresada</p><p>no es válida.</p>");
        return msj;
    }

    /**
     * Obtiene el mensaje 1489.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensaje1489() {
        Mensaje msj = new Mensaje();
        msj.setMensaje(
                "<p>No pudimos añadir al destinatario<b>\"{0}\"</b>a</p><p>tu<b>agenda de destinatarios.</b></p>");
        return msj;
    }

    /**
     * Obtiene el cliente.
     *
     * @return the cliente
     */
    private Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        cliente.setApellido1("Muñoz");
        cliente.setNombre("Cesar Alberto");
        cliente.setNumeroCUILCUIT("30173299881");
        ;
        return cliente;
    }

    /**
     * Dado un destinatario, cuando se invoca al metodo "confirmarAltaDestinatario",
     * obtengo la confirmacion.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoDestinatarioCuandoInvocaConfirmarAltaDestinatarioObtengoRespuestaConfirmacionOKOtrosBancos()
            throws DAOException, BusinessException {
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(AgendaDestinatarioOutEntityMock.obtenerAgendaDestinatarioOutEntityOK());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeOK());

        Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO
                .confirmarAltaDestinatarioOtrosBancos("123456789012345", obtenerCliente(),
                        ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioOtrosBancosInView(),
                        "Contador", "T");

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
        Assert.assertEquals("12:34", respuestaDTO.getRespuesta().getHora());
        Assert.assertEquals("00000001", respuestaDTO.getRespuesta().getNroComprobante());
        Assert.assertEquals(
                "<p>El destinatario <b>\"Contador\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>",
                respuestaDTO.getRespuesta().getMensajeEfectivizacion());
    }

    @Test(expected = BusinessException.class)
    public void confirmarAltaDestinatarioOtrosBancosExceptionTest() throws BusinessException {
        altaDestinatarioBO.confirmarAltaDestinatarioOtrosBancos(null, null, null, null, null);
        // En caso de un nullPointer exception el metodo devuelve un
        // businessException
    }

    @Test
    public void confirmarAltaDestinatarioEnvioEfectivoOkTest() throws BusinessException, DAOException {
        ConfirmacionAltaDestinatarioView datosConfirmados = new ConfirmacionAltaDestinatarioView();
        AgendaDestinatarioOutEntity agendaDestinatarioOutEntity = new AgendaDestinatarioOutEntity();

        agendaDestinatarioOutEntity.setCodigoRetornoExtendido("00000000");
        datosConfirmados.setDireccionCorreo("dante_482_351@hotmail.com");

        Mockito.when(valid.validate(Matchers.any())).thenReturn(null);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(agendaDestinatarioOutEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> res = altaDestinatarioBO.confirmarAltaDestinatarioEnvioEfectivo(
                "1881663324", obtenerCliente(), datosConfirmados, "20378668817");
        Assert.assertNotNull(res);
    }

    @Test
    public void confirmarAltaDestinatarioEnvioEfectivoErrorYaAgendadoTest() throws BusinessException, DAOException {
        ConfirmacionAltaDestinatarioView datosConfirmados = new ConfirmacionAltaDestinatarioView();
        AgendaDestinatarioOutEntity agendaDestinatarioOutEntity = new AgendaDestinatarioOutEntity();

        agendaDestinatarioOutEntity.setCodigoRetornoExtendido("00014009");
        datosConfirmados.setDireccionCorreo("dante_482_351@hotmail.com");

        Mockito.when(valid.validate(Matchers.any())).thenReturn(null);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(agendaDestinatarioOutEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> res = altaDestinatarioBO.confirmarAltaDestinatarioEnvioEfectivo(
                "1881663324", obtenerCliente(), datosConfirmados, "20378668817");
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(
                ErrorAltaDestinatariosEnum.ERROR_SERVICIO_CBU_ENVIO_EFECTIVO.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    @Test
    public void confirmarAltaDestinatarioEnvioEfectivoErrorGeneralTest() throws BusinessException, DAOException {
        ConfirmacionAltaDestinatarioView datosConfirmados = new ConfirmacionAltaDestinatarioView();
        AgendaDestinatarioOutEntity agendaDestinatarioOutEntity = new AgendaDestinatarioOutEntity();

        agendaDestinatarioOutEntity.setCodigoRetornoExtendido("00015009");
        datosConfirmados.setDireccionCorreo("dante_482_351@hotmail.com");

        Mockito.when(valid.validate(Matchers.any())).thenReturn(null);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(agendaDestinatarioOutEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> res = altaDestinatarioBO.confirmarAltaDestinatarioEnvioEfectivo(
                "1881663324", obtenerCliente(), datosConfirmados, "20378668817");
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(
                ErrorAltaDestinatariosEnum.ERROR_SERVICIO_CBU_ENVIO_EFECTIVO.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    @Test
    public void crearRespuestaConfiguracionCbuOKTest() throws BusinessException, DAOException {
        ValidacionCuentaOutCBUEntity entity = new ValidacionCuentaOutCBUEntity();
        entity.setCodigoRetornoExtendido("00000000");
        entity.setCuit("0123456789012345678901");
        entity.setBancoReceptor("NACION");
        entity.setTitular("Test test");
        Mockito.when(valid.validate(Matchers.any())).thenReturn(null);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> res = casuisticaAlta.crearRespuestaConfiguracionCbu(entity);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

    }

    @Test
    public void crearRespuestaConfiguracionCbuErrorAltaManualTest() throws BusinessException, DAOException {
        ValidacionCuentaOutCBUEntity entity = new ValidacionCuentaOutCBUEntity();
        entity.setCodigoRetornoExtendido("10000065");
        entity.setCuit("01234567890123456789");
        Mockito.when(valid.validate(Matchers.any())).thenReturn(null);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> res = casuisticaAlta.crearRespuestaConfiguracionCbu(entity);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(ErrorAltaDestinatariosEnum.ERROR_CARGA_MANUAL.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void CrearRespuestaConfiguracionCbuErrorServicioCbuTest() throws BusinessException, DAOException {
        ValidacionCuentaOutCBUEntity entity = new ValidacionCuentaOutCBUEntity();
        entity.setCodigoRetornoExtendido("10000036");
        entity.setCuit("0123456789012345678901");
        entity.setBancoReceptor("NACION");
        entity.setTitular("Test test");
        Mockito.when(valid.validate(Matchers.any())).thenReturn(null);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> res = casuisticaAlta.crearRespuestaConfiguracionCbu(entity);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(ErrorAltaDestinatariosEnum.ERROR_SERVICIO_CBU.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void CrearRespuestaConfiguracionCbuErrorServicioTest() throws BusinessException, DAOException {
        ValidacionCuentaOutCBUEntity entity = new ValidacionCuentaOutCBUEntity();
        entity.setCodigoRetornoExtendido("123123123");
        entity.setCuit("0123456789012345678901");
        entity.setBancoReceptor("NACION");
        entity.setTitular("Test test");
        Mockito.when(valid.validate(Matchers.any())).thenReturn(null);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> res = casuisticaAlta.crearRespuestaConfiguracionCbu(entity);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(ErrorAltaDestinatariosEnum.ERROR_SERVICIO_CBU.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void crearRespuestaConfiguracionCbuEspaciosTest() throws BusinessException, DAOException {
        ValidacionCuentaOutCBUEntity entity = new ValidacionCuentaOutCBUEntity();
        entity.setCodigoRetornoExtendido("00000000");
        Mockito.when(valid.validate(Matchers.any())).thenReturn(null);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ConfiguracionAltaDestinatarioCBUDTO> res = casuisticaAlta.crearRespuestaConfiguracionCbu(entity);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals(ErrorAltaDestinatariosEnum.ERROR_CARGA_MANUAL.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void crearRespuestaErrorServicioEnvioEfectivoTest() {
        AgendaDestinatarioOutEntity outEntity = null;
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaAlta.crearRespuestaEnvioEfectivo(outEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(),
                ErrorAltaDestinatariosEnum.ERROR_SERVICIO_CBU_ENVIO_EFECTIVO.getTag());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_SERVICIO.getDescripcion());

    }

    @Test
    public void crearRespuestaErrorYaAgendadoEnvioEfectivoTest() {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("10011629");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> respuesta = casuisticaAlta.crearRespuestaEnvioEfectivo(outEntity);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(),
                ErrorAltaDestinatariosEnum.ERROR_YA_AGENDADO_ENVIO_EFECTIVO.getTag());
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_YA_AGENDADO.getDescripcion());

    }

    @Test
    public void errorEnComprobanteOtrosBancosCbuInvalidoTest() throws DAOException, BusinessException {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("10011643");
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> dto = altaDestinatarioBO.confirmarAltaDestinatarioOtrosBancos(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioOtrosBancosInView(),
                "Contador", "T");
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(dto.getItemsMensajeRespuesta().get(0).getTag(),
                ErrorAltaDestinatariosEnum.CBU_INVALIDO.getTag());

    }

    @Test
    public void errorEnComprobanteOtrosBancosCbuExisteTest() throws DAOException, BusinessException {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("10011636");
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> dto = altaDestinatarioBO.confirmarAltaDestinatarioOtrosBancos(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioOtrosBancosInView(),
                "Contador", "T");
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(dto.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.DESTINATARIO_AGENDADO_ALTA_DESTINATARIO.getDescripcion());

    }

    @Test
    public void errorEnComprobanteOtrosBancosErrorIndeseadoTest() throws DAOException, BusinessException {
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("8239048");
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobanteAltaDestinatarioDTO> dto = altaDestinatarioBO.confirmarAltaDestinatarioOtrosBancos(
                "123456789012345", obtenerCliente(),
                ConfirmacionAltaDestinatarioInViewMock.obtenerConfirmacionAltaDestinatarioOtrosBancosInView(),
                "Contador", "T");
        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(dto.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_SERVICIO_ALTA_DESTINATARIO.getDescripcion());

    }

}