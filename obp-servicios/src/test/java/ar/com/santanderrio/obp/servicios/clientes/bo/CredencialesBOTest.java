package ar.com.santanderrio.obp.servicios.clientes.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpServletRequest;

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

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxInicial;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.impl.CredencialesBOImpl;
import ar.com.santanderrio.obp.servicios.clientes.dao.CredencialesDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClaveIndividuoVencidaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClavePinVencidaNotieneUsuarioException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClavesExpiradasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesDocumentoNoExisteException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioBloqueadoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioCambioPendienteException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioConSinonimoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioNoDefinidoException;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.bo.impl.ApiAuthBOImpl;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

/**
 * The Class CredencialesBOTest.
 */

@RunWith(MockitoJUnitRunner.class)
public class CredencialesBOTest {

    /** The credenciales BO. */
    @InjectMocks
    private CredencialesBOImpl credencialesBO;

    /** The credenciales DAO. */
    @Mock
    private CredencialesDAO credencialesDAO;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    
    /** The iatx inicial. */
    @Mock
    private IatxInicial iatxInicial;

	/** The modulo permiso BO. */
    @Mock
	private ModuloPermisoBO moduloPermisoBO;
    

    /**
     * Validar credenciales usuario cambio pendiente test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioCambioPendienteTest() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesUsuarioCambioPendienteException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales usuario no definido test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioNoDefinidoTest() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesUsuarioNoDefinidoException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_NO_DEFINIDO.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales usuario claves expiradas.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioClavesExpiradas() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesClavesExpiradasException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales usuario claves expiradas pin vencida notiene usuario.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioClavesExpiradasPinVencidaNotieneUsuario() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesClavePinVencidaNotieneUsuarioException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales usuario claves expiradas clave individuo vencida.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioClavesExpiradasClaveIndividuoVencida() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesClaveIndividuoVencidaException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales usuario bloqueado test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioBloqueadoTest() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesUsuarioBloqueadoException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_CLAVE_BLOQUEADA.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales documento invalido test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesDocumentoInvalidoTest() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesDocumentoNoExisteException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_DATOS_INCORRECTOS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales documento homonimo.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesDocumentoHomonimo() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesUsuarioConSinonimoException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_DOCUMENTO_HOMONIMO.getDescripcion());
        assertEquals("", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales datos incorrectos.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesDatosIncorrectos() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_DATOS_INCORRECTOS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales error total.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesErrorTotal() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredenciales(Matchers.any(CredencialCliente.class)))
                .thenThrow(RobotException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_TOTAL.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Login definir usuario test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void loginDefinirUsuarioTest() throws CredencialesException {
        CredencialCliente credencialCliente = new CredencialCliente();
        ResumenCliente resumenCliente = new ResumenCliente();

        Mockito.when(credencialesDAO.actualizarClaveUsuario(Matchers.any(CredencialCliente.class)))
                .thenReturn(resumenCliente);
        Mockito.when(iatxInicial.getRacfInicialId()).thenReturn(ResumenCliente.RACF_ID_INICIAL);
        Mockito.when(iatxInicial.getRacfInicialPwd()).thenReturn(ResumenCliente.RACF_PWD_INICIAL);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.loginDefinirUsuario(credencialCliente);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Cambio usuario pendientes test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void cambioUsuarioPendientesTest() throws CredencialesException {
        CredencialCliente credencialCliente = new CredencialCliente();
        ResumenCliente resumenCliente = new ResumenCliente();

        Mockito.when(credencialesDAO.actualizarClaveUsuario(Matchers.any(CredencialCliente.class)))
                .thenReturn(resumenCliente);
        Mockito.when(iatxInicial.getRacfInicialId()).thenReturn(ResumenCliente.RACF_ID_INICIAL);
        Mockito.when(iatxInicial.getRacfInicialPwd()).thenReturn(ResumenCliente.RACF_PWD_INICIAL);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        Respuesta<ResumenCliente> respuesta = credencialesBO.cambioUsuarioPendiente(credencialCliente);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Cambio usuario test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void cambioUsuarioTest() throws CredencialesException {
        CambioUsuarioEntity cambioUsuarioEntity = new CambioUsuarioEntity();
        RegistroSesion registroSesion = new RegistroSesion();
        ResumenCliente resumenCliente = new ResumenCliente();

        Mockito.when( sesionCliente.getCliente()).thenReturn(new Cliente());
        
        Mockito.when( sesionCliente.getResumenCliente()).thenReturn(resumenCliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(credencialesDAO.cambioUsuario(Matchers.any(CredencialCliente.class),
                Matchers.any(CambioUsuarioEntity.class))).thenReturn(resumenCliente);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

        MessageContext mc = Mockito.mock(MessageContext.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("180.166.1.1");
        Mockito.when(mc.getHttpServletRequest()).thenReturn(request);

        Respuesta<ResumenCliente> respuesta = credencialesBO.cambioUsuario(request, cambioUsuarioEntity, true);
        Assert.assertNotNull(respuesta.getRespuesta());
    }
    
    /**
     * Validar credenciales usuario cambio pendiente proxy login test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioCambioPendienteProxyLoginTest() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesUsuarioCambioPendienteException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales usuario no definido proxy login test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioNoDefinidoProxyLoginTest() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesUsuarioNoDefinidoException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_NO_DEFINIDO.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }


    /**
     * Validar credenciales usuario claves expiradas proxy login.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioClavesExpiradasProxyLogin() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesClavesExpiradasException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }


    /**
     * Validar credenciales usu claves exp pin venc sin usuario proxy login.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuClavesExpPinVencSinUsuarioProxyLogin() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesClavePinVencidaNotieneUsuarioException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales usuario claves exp clave ind vencida proxy login.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioClavesExpClaveIndVencidaProxyLogin() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesClaveIndividuoVencidaException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_USUARIO_CLAVE_EXPIRADAS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales usuario bloqueado proxy login test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesUsuarioBloqueadoProxyLoginTest() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesUsuarioBloqueadoException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_CLAVE_BLOQUEADA.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }


    /**
     * Validar credenciales documento invalido proxy login test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesDocumentoInvalidoProxyLoginTest() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesDocumentoNoExisteException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_DATOS_INCORRECTOS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales documento homonimo proxy login.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesDocumentoHomonimoProxyLogin() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesUsuarioConSinonimoException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_DOCUMENTO_HOMONIMO.getDescripcion());
        assertEquals("", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales datos incorrectos proxy login.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesDatosIncorrectosProxyLogin() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(CredencialesException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);
        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_DATOS_INCORRECTOS.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Validar credenciales error total proxy login.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void validarCredencialesErrorTotalProxyLogin() throws CredencialesException {
        CredencialCliente cc = new CredencialCliente();
        Mensaje mensaje = new Mensaje();

        cc.setDni("12345678");
        mensaje.setMensaje("MensajeError");

        Mockito.when(credencialesDAO.validarCredencialesProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenThrow(RobotException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.APIAUTH_VALIDATION)).thenReturn(false);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)).thenReturn(true);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN_VALCRED)).thenReturn(true);

        Respuesta<ResumenCliente> respuesta = credencialesBO.validarCredenciales(cc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.LOGIN_ERROR_TOTAL.getDescripcion());
        assertEquals("MensajeError", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Login definir usuario proxy login test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void loginDefinirUsuarioProxyLoginTest() throws CredencialesException {
        CredencialCliente credencialCliente = new CredencialCliente();
        ResumenCliente resumenCliente = new ResumenCliente();

        Mockito.when(credencialesDAO.actualizarClaveUsuarioProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenReturn(resumenCliente);
        Mockito.when(iatxInicial.getRacfInicialId()).thenReturn(ResumenCliente.RACF_ID_INICIAL);
        Mockito.when(iatxInicial.getRacfInicialPwd()).thenReturn(ResumenCliente.RACF_PWD_INICIAL);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);

        Respuesta<ResumenCliente> respuesta = credencialesBO.loginDefinirUsuario(credencialCliente);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Cambio usuario pendientes proxy login test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void cambioUsuarioPendientesProxyLoginTest() throws CredencialesException {
        CredencialCliente credencialCliente = new CredencialCliente();
        ResumenCliente resumenCliente = new ResumenCliente();

        Mockito.when(credencialesDAO.actualizarClaveUsuarioProxyLogin(Matchers.any(CredencialCliente.class)))
                .thenReturn(resumenCliente);
        Mockito.when(iatxInicial.getRacfInicialId()).thenReturn(ResumenCliente.RACF_ID_INICIAL);
        Mockito.when(iatxInicial.getRacfInicialPwd()).thenReturn(ResumenCliente.RACF_PWD_INICIAL);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);

        Respuesta<ResumenCliente> respuesta = credencialesBO.cambioUsuarioPendiente(credencialCliente);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Cambio usuario proxy login test.
     *
     * @throws CredencialesException the credenciales exception
     */
    @Test
    public void cambioUsuarioProxyLoginTest() throws CredencialesException {
        CambioUsuarioEntity cambioUsuarioEntity = new CambioUsuarioEntity();
        RegistroSesion registroSesion = new RegistroSesion();
        ResumenCliente resumenCliente = new ResumenCliente();

        Mockito.when( sesionCliente.getCliente()).thenReturn(new Cliente());
        
        Mockito.when( sesionCliente.getResumenCliente()).thenReturn(resumenCliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(credencialesDAO.cambioUsuarioProxyLogin(Matchers.any(CredencialCliente.class),
                Matchers.any(CambioUsuarioEntity.class), Matchers.anyBoolean())).thenReturn(resumenCliente);
        Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);

        MessageContext mc = Mockito.mock(MessageContext.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("180.166.1.1");
        Mockito.when(mc.getHttpServletRequest()).thenReturn(request);

        Respuesta<ResumenCliente> respuesta = credencialesBO.cambioUsuario(request, cambioUsuarioEntity, true);
        Assert.assertNotNull(respuesta.getRespuesta());
    }

}
