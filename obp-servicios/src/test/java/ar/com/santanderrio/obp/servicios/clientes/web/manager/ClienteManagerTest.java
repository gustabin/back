package ar.com.santanderrio.obp.servicios.clientes.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.bo.CredencialesBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.clientes.service.ClienteService;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.impl.ClienteManagerImpl;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoResponse;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.login.manager.TokenManager;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginView;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosDomicilioView;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.segmento.manager.SegmentoClienteManager;

/**
 * The Class ClienteManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ClienteManagerTest {

    /** The cliente manager. */
    @InjectMocks
    private ClienteManagerImpl clienteManager;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The cliente service. */
    @Mock
    private ClienteService clienteService;

    /** The token manager. */
    @Mock
    private TokenManager tokenManager;

    /** The rsa manager. */
    @Mock
    private RsaManager rsaManager;

    /** The segmento cliente manager. */
    @Mock
    private SegmentoClienteManager segmentoClienteManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The registro session. */
    @Mock
    private RegistroSesion registroSession;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;;

    /** The registro sesion. */
    @Mock
    private RegistroSesion registroSesion;

    /** The credenciales BO. */
    @Mock
    private CredencialesBO credencialesBO;

    /** The Mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mya BO. */
    @Mock
    private MyaBO myaBO;

    @Mock
    private MensajeManager mensajeManager;

    @Mock
    private CambioDomicilioBO cambioDomicilioBO;

    @Mock
    private CambioDomicilioManager cambioDomicilioManager;

    @Mock
    private DesafioOperacionRSA<CambioDatosContactoView> desafioOperacionRSAEmailCelular;


    /**
     * Login fallo validar credenciales test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void loginFalloValidarCredencialesTest() throws ServiceException {
        CredencialCliente credencialCliente = new CredencialCliente();
        RsaGenericRequestData rsaGenericRequestData = new RsaGenericRequestData();
        LoginView loginView = new LoginView();

        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(httpServletRequest.getSession(Boolean.FALSE)).thenReturn(session);
        when(session.getAttribute("logFileName")).thenReturn("Mi archivo");
        when(session.getAttribute("pid")).thenReturn("1234");

        when(clienteService.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(ServiceException.class);

		Respuesta<LoginResponseView> respuesta = clienteManager.login(credencialCliente, rsaGenericRequestData,
                loginView, httpServletRequest, httpServletResponse);

        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Login falloverificar respuesta token test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void loginFalloverificarRespuestaTokenTest() throws ServiceException {

        CredencialCliente credencialCliente = new CredencialCliente();
        RsaGenericRequestData rsaGenericRequestData = new RsaGenericRequestData();
        LoginView loginView = new LoginView();
        Respuesta<ResumenCliente> respuestaResumenCliente = new Respuesta<ResumenCliente>();
        Respuesta<LoginResponseView> respuestaLoginResponseView = new Respuesta<LoginResponseView>();
        Respuesta<ResumenCliente> respuestaCliente = new Respuesta<ResumenCliente>();
        Cliente cliente = new Cliente();

        respuestaCliente.setRespuesta(cliente);
        respuestaResumenCliente.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLoginResponseView.setEstadoRespuesta(EstadoRespuesta.ERROR);
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(httpServletRequest.getSession(Boolean.FALSE)).thenReturn(session);
        when(session.getAttribute("logFileName")).thenReturn("Mi archivo");
        when(session.getAttribute("pid")).thenReturn("1234");

        when(clienteService.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenReturn(respuestaResumenCliente);
        when(tokenManager.verificarRespuestaToken(Matchers.any(ResumenCliente.class), Matchers.any(httpServletRequest.getClass()), Matchers.any(httpServletResponse.getClass())))
        		.thenReturn(respuestaLoginResponseView);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Error Control Sesion");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LOGIN_ERROR_CONTROL_SESSION))
                .thenReturn(mensaje);

        Respuesta<LoginResponseView> respuestaControlSesion = respuestaFactory.crearRespuestaError(null,
                TipoError.LOGIN_ERROR_CONTROL_SESSION, CodigoMensajeConstantes.LOGIN_ERROR_CONTROL_SESSION);
        when(tokenManager.verificarRespuestaToken(Matchers.any(ResumenCliente.class), Matchers.any(httpServletRequest.getClass()), Matchers.any(httpServletResponse.getClass())))
                .thenReturn(respuestaControlSesion);

		Respuesta<LoginResponseView> respuestaLogin = clienteManager.login(credencialCliente, rsaGenericRequestData,
                loginView, httpServletRequest, httpServletResponse);

        assertEquals(EstadoRespuesta.ERROR, respuestaLogin.getEstadoRespuesta());
        assertEquals(TipoError.LOGIN_ERROR_CONTROL_SESSION.getDescripcion(),
                respuestaLogin.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Login definir usuario error test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void loginDefinirUsuarioErrorTest() throws ServiceException {
        CredencialCliente credencialCliente = new CredencialCliente();
        Respuesta<ResumenCliente> respuestaResumenCliente = new Respuesta<ResumenCliente>();
        Mockito.when(credencialesBO.loginDefinirUsuario(Matchers.any(CredencialCliente.class)))
                .thenReturn(respuestaResumenCliente);
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        respuestaResumenCliente.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaResumenCliente.setItemMensajeRespuesta(itemsMensajeRespuesta);
        itemMensajeRespuesta.setTipoError(TipoError.LOGIN_ERROR_USUARIO_NO_DEFINIDO.getDescripcion());
        itemsMensajeRespuesta.add(itemMensajeRespuesta);

        Respuesta<FeedbackMensajeView> respuesta = clienteManager.loginDefinirUsuario(credencialCliente);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * Cambio usuario pendiente error test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void cambioUsuarioPendienteErrorTest() throws ServiceException {
        CredencialCliente credencialCliente = new CredencialCliente();
        Respuesta<ResumenCliente> respuestaResumenCliente = new Respuesta<ResumenCliente>();
        Mockito.when(credencialesBO.cambioUsuarioPendiente(Matchers.any(CredencialCliente.class)))
                .thenReturn(respuestaResumenCliente);
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        respuestaResumenCliente.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaResumenCliente.setItemMensajeRespuesta(itemsMensajeRespuesta);
        itemMensajeRespuesta.setTipoError(TipoError.LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE.getDescripcion());
        itemsMensajeRespuesta.add(itemMensajeRespuesta);

        Respuesta<FeedbackMensajeView> respuesta = clienteManager.cambioUsuarioPendiente(credencialCliente);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * Cambio usuario
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void cambioUsuarioTest() throws ServiceException {
        Cliente cliente = new Cliente();

        CambioUsuarioEntity entity = new CambioUsuarioEntity();
        entity.setCliente(cliente);
        entity.setStrOldPin("330A330A330A330A330A330A330A330A");
        entity.setStrNewPin("330A330A330A330A330A330A330A330A");
        entity.setStrOldUsr("348A304A37A304A57A297A271A239A315A315A315A315A315A315A315A315A315A315A315A315A");
        entity.setStrNewUsr("348A304A37A304A57A297A271A102A315A315A315A315A315A315A315A315A315A315A315A315A");

        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        MessageContext mc = Mockito.mock(MessageContext.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("180.166.1.1");
        Mockito.when(mc.getHttpServletRequest()).thenReturn(request);

        Mockito.when(credencialesBO.cambioUsuario(Matchers.any(HttpServletRequest.class),
                Matchers.any(entity.getClass()), Matchers.anyBoolean())).thenReturn(respuesta);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Cambio usuario
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void cambioUsuarioClaveErrorTest() throws ServiceException {
        Cliente cliente = new Cliente();

        CambioUsuarioEntity entity = new CambioUsuarioEntity();
        entity.setCliente(cliente);
        entity.setStrOldPin("330A330A330A330A330A330A330A330A");
        entity.setStrNewPin("330A330A330A330A330A330A330A330A");
        entity.setStrOldUsr("348A304A37A304A57A297A271A239A315A315A315A315A315A315A315A315A315A315A315A315A");
        entity.setStrNewUsr("348A304A37A304A57A297A271A102A315A315A315A315A315A315A315A315A315A315A315A315A");

        Respuesta<ResumenCliente> respuesta = new Respuesta<ResumenCliente>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.when(credencialesBO.cambioUsuario(Matchers.any(HttpServletRequest.class),
                Matchers.any(entity.getClass()), Matchers.anyBoolean())).thenReturn(respuesta);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void getDatosContactoTest() {
        MyaDTOIn in = new MyaDTOIn();
        in.setDni("945679999");
        in.setSoloPrincipales(false);
        MyaDTOOut out = new MyaDTOOut();
        out.setCelular("11-111111");
        out.setCelularId("1");
        out.setCelularSecundario("12-1212121212");
        out.setCelularSecundarioId("2");
        out.setClienteEstadoEnum(ClienteEstadoEnum.SUSCRIPTO_ACTIVO);
        out.setEmail("isban@isbanexternos.com.ar");
        out.setEmailSecundarioId("1");
        out.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        out.setEmailSecundarioId("2");
        out.setTipoCompaniaEnum(TipoCompaniaEnum.CLARO);
        out.setTipoCompaniaSecundarioEnum(TipoCompaniaEnum.MOVISTAR);
        Mockito.when(sesionCliente.getCliente()).thenReturn(Matchers.any(Cliente.class));

        Respuesta<DatosDomicilioView> rOut = new Respuesta<DatosDomicilioView>();
        rOut.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(myaBO.consultaWsStatusCliente(Matchers.any(Cliente.class), in)).thenReturn(out);
        Mockito.when(cambioDomicilioManager.obtenerDomiciliosContacto(Matchers.anyBoolean())).thenReturn(rOut);

        Respuesta<CambioDatosContactoResponse> respuesta = clienteManager.getDatosContacto();

        assertNotNull(respuesta);
    }

    @Test
    public void cambioDatosAltaContactoTest() throws IllegalAccessException {
        Respuesta<Void> respuestaMya = new Respuesta<Void>();
        CambioDatosContactoView cambioDatosContacto = new CambioDatosContactoView();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("HOLA");
        Cliente cliente = new Cliente();
        MyaDTOOut out = new MyaDTOOut();
        cliente.setDni("45345999");
        respuestaMya.setEstadoRespuesta(EstadoRespuesta.OK);

        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        credencialesMya.setCelularSecundario("22222222");
        credencialesMya.setCodigoAreaSecundario("11");
        credencialesMya.setCompaniaSeleccionadaSecundaria("Movistar");

        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getMyaDTOOut()).thenReturn(out);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_OK)).thenReturn(mensaje);
        Mockito.when(myaBO.registrarClienteMya(Matchers.any(Cliente.class), Matchers.any(CredencialesMyaIn.class)))
                .thenReturn(respuestaMya);
        Respuesta<CambioDatosContactoView> respuestaAutenticacion = new Respuesta<CambioDatosContactoView>();
        respuestaAutenticacion.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSAEmailCelular.validarOperacionRSA(Matchers.any(CambioDatosContactoView.class), 
        		Matchers.anyInt(), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaAutenticacion);
        FieldUtils.writeDeclaredField(clienteManager, "valorDesafioPerfilCambioCelular", 3, true);
        FieldUtils.writeDeclaredField(clienteManager, "valorDesafioPerfilCambioEmail", 3, true);
        FieldUtils.writeDeclaredField(clienteManager, "desafioOperacionRSAEmailCelular", desafioOperacionRSAEmailCelular, true);
        Respuesta<CambioDatosContactoView> respuesta = clienteManager.cambioDatosContacto(cambioDatosContacto,
                Boolean.FALSE);
        assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void cambioDatosModificacionDatosIgualesContactoTest() throws IllegalAccessException {
        Respuesta<Void> respuestaMya = new Respuesta<Void>();
        CambioDatosContactoView cambioDatosContacto = new CambioDatosContactoView();
        Cliente cliente = new Cliente();
        MyaDTOOut out = new MyaDTOOut();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("HOLA");

        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        credencialesMya.setCelularSecundario("22222222");
        credencialesMya.setCodigoAreaSecundario("11");
        credencialesMya.setCompaniaSeleccionadaSecundaria("Movistar");

        out.setCelular("11-1111111");
        out.setCelularId("1");
        out.setCelularSecundario("11-1212121");
        out.setCelularSecundarioId("2");
        out.setClienteEstadoEnum(ClienteEstadoEnum.SUSCRIPTO_ACTIVO);
        out.setEmail("isban@isbanexternos.com.ar");
        out.setEmailSecundarioId("1");
        out.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        out.setEmailSecundarioId("2");
        out.setTipoCompaniaEnum(TipoCompaniaEnum.CLARO);
        out.setTipoCompaniaSecundarioEnum(TipoCompaniaEnum.MOVISTAR);
        cliente.setDni("45345999");

        cambioDatosContacto.setCelularIn("1111111");
        cambioDatosContacto.setCodigoArea("11");
        cambioDatosContacto.setCompaniaCelular(TipoCompaniaEnum.CLARO.getDescripcion());
        cambioDatosContacto.setPosicionCelular("1");
        cambioDatosContacto.setEmailPrimario("isban@isbanexternos.com.ar");
        cambioDatosContacto.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");

        respuestaMya.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getMyaDTOOut()).thenReturn(out);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_OK)).thenReturn(mensaje);
        Mockito.when(myaBO.updateDestinos(Matchers.any(CredencialesMyaIn.class), Matchers.any(Cliente.class)))
                .thenReturn(respuestaMya);
        Respuesta<CambioDatosContactoView> respuestaAutenticacion = new Respuesta<CambioDatosContactoView>();
        respuestaAutenticacion.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSAEmailCelular.validarOperacionRSA(Matchers.any(CambioDatosContactoView.class), 
        		Matchers.anyInt(), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaAutenticacion);
        FieldUtils.writeDeclaredField(clienteManager, "valorDesafioPerfilCambioCelular", 3, true);
        FieldUtils.writeDeclaredField(clienteManager, "valorDesafioPerfilCambioEmail", 3, true);
        FieldUtils.writeDeclaredField(clienteManager, "desafioOperacionRSAEmailCelular", desafioOperacionRSAEmailCelular, true);
        Respuesta<CambioDatosContactoView> respuesta = clienteManager.cambioDatosContacto(cambioDatosContacto,
                Boolean.TRUE);
        assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void cambioDatosModificacionDatosDiferentesContactoTest() throws IllegalAccessException {
        Respuesta<Void> respuestaMya = new Respuesta<Void>();
        CambioDatosContactoView cambioDatosContacto = new CambioDatosContactoView();
        Cliente cliente = new Cliente();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("HOLA");

        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("isban@isbanexternos.com.ar");
        credencialesMya.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        credencialesMya.setCelular("11111111");
        credencialesMya.setCodigoArea("11");
        credencialesMya.setCompaniaSeleccionada("Claro");
        credencialesMya.setCelularSecundario("22222222");
        credencialesMya.setCodigoAreaSecundario("11");
        credencialesMya.setCompaniaSeleccionadaSecundaria("Movistar");

        MyaDTOOut out = new MyaDTOOut();
        out.setCelular("11-1111111");
        out.setCelularId("1");
        out.setCelularSecundario("11-1212222");
        out.setCelularSecundarioId("2");
        out.setClienteEstadoEnum(ClienteEstadoEnum.SUSCRIPTO_ACTIVO);
        out.setEmail("isban@isbanexternos.com.ar");
        out.setEmailSecundarioId("1");
        out.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");
        out.setEmailSecundarioId("2");
        out.setTipoCompaniaEnum(TipoCompaniaEnum.CLARO);
        out.setTipoCompaniaSecundarioEnum(TipoCompaniaEnum.MOVISTAR);
        cliente.setDni("45345999");

        cambioDatosContacto.setCelularIn("1111111");
        cambioDatosContacto.setCodigoArea("11");
        cambioDatosContacto.setCompaniaCelular(TipoCompaniaEnum.MOVISTAR.getDescripcion());
        cambioDatosContacto.setPosicionCelular("1");

        cambioDatosContacto.setCelularIn("1212121");
        cambioDatosContacto.setCodigoArea("11");
        cambioDatosContacto.setCompaniaCelular(TipoCompaniaEnum.CLARO.getDescripcion());
        cambioDatosContacto.setPosicionCelular("2");

        cambioDatosContacto.setEmailPrimario("isban@isbanexternos.com.ar");
        cambioDatosContacto.setEmailSecundario("isbanSecundario@isbanexternos.com.ar");

        respuestaMya.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getMyaDTOOut()).thenReturn(out);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_OK)).thenReturn(mensaje);
        Mockito.when(myaBO.updateDestinos(Matchers.any(CredencialesMyaIn.class), Matchers.any(Cliente.class)))
                .thenReturn(respuestaMya);
        Respuesta<CambioDatosContactoView> respuestaAutenticacion = new Respuesta<CambioDatosContactoView>();
        respuestaAutenticacion.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSAEmailCelular.validarOperacionRSA(Matchers.any(CambioDatosContactoView.class), 
        		Matchers.anyInt(), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaAutenticacion);
        FieldUtils.writeDeclaredField(clienteManager, "valorDesafioPerfilCambioCelular", 3, true);
        FieldUtils.writeDeclaredField(clienteManager, "valorDesafioPerfilCambioEmail", 3, true);
        FieldUtils.writeDeclaredField(clienteManager, "desafioOperacionRSAEmailCelular", desafioOperacionRSAEmailCelular, true);
        Respuesta<CambioDatosContactoView> respuesta = clienteManager.cambioDatosContacto(cambioDatosContacto,
                Boolean.FALSE);
        assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
}
