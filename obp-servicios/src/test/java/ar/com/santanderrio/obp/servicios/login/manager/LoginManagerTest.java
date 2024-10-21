package ar.com.santanderrio.obp.servicios.login.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.generated.webservices.rsa.UserStatus;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager;
import ar.com.santanderrio.obp.servicios.clave.online.view.AltaClaveOnlineView;
import ar.com.santanderrio.obp.servicios.clave.online.view.DatosAutenticacion;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.impl.ClienteManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.WebContentView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.web.manager.OfertaComercialManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.bo.LoginBO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPinesUtil;
import ar.com.santanderrio.obp.servicios.login.manager.impl.LoginManagerImpl;
import ar.com.santanderrio.obp.servicios.login.view.CredencialesLoginView;
import ar.com.santanderrio.obp.servicios.login.view.DatosMyaView;
import ar.com.santanderrio.obp.servicios.login.view.InicioLoginView;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAResponse;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.session.manager.SessionControlManager;

/**
 * The Class LoginManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginManagerTest {

    /** The login manager. */
    @InjectMocks
    private LoginManagerImpl loginManager;

    /** The cliente manager. */
    @Mock
    private ClienteManagerImpl clienteManager;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The session control manager. */
    @Mock
    private SessionControlManager sessionControlManager;

    /** The login BO. */
    @Mock
    private LoginBO loginBO;

    /** The Respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The mya manager. */
    @Mock
    private MyaManager myaManager;

    /** The encryPines util. */
    @Mock
    private EncryPinesUtil encryPines;

    /** The rsa manager. */
    @Mock
    private RsaManager rsaManager;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    @Mock
    private MensajeManager mensajeManager;

    @Mock
    private ClaveOnlineManager claveOnlineManager;

    @Mock
    private OfertaComercialManager ofertaComercialManager;

    @Mock
    private ModuloPermisoBO moduloPermisoBO;
    /**
     * Obtener inicio login test.
     */
    @Test
    public void obtenerInicioLoginTest() {

        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();

        respuestaMensaje.setRespuesta(mensaje);
        respuestaMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
        mensaje.setMensaje("Mensaje del BO");

        when(loginBO.obtenerInicioLogin()).thenReturn(respuestaMensaje);

        Respuesta<InicioLoginView> respuesta = loginManager.obtenerIncioLogin();

        assertNotNull(respuesta);
        assertNotNull(respuesta.getRespuesta());
        assertEquals(respuesta.getRespuesta().getMensajeLogin(), "Mensaje del BO");
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    /**
     * Login OK.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Ignore
    @SuppressWarnings("unchecked")
    @Test
    public void loginOK() throws IllegalAccessException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Vector<String> headerNames = new Vector<String>();
        Cliente cliente = Mockito.mock(Cliente.class);
        Respuesta<CredencialesMya> respuestaCredencialesMya = new Respuesta<CredencialesMya>();
        Respuesta<LoginResponseView> respuestaClienteManager = new Respuesta<LoginResponseView>();
        
        LoginResponseView loginResponseView = new LoginResponseView();
        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("pepe@mail.com");
        loginResponseView.setCredencialesMya(credencialesMya);
        
        Respuesta<String> respuestaLegal = new Respuesta<String>();
        RegistroSesion registroSession = new RegistroSesion();
        registroSession.setIp("160.11.12.1");

        Respuesta<RSAResponse> respRSA = new Respuesta<RSAResponse>();
        RSAResponse rsaResponse = new RSAResponse();
        respRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        rsaResponse.setActionCode(ActionCode.ALLOW);
        rsaResponse.setUserStatus(UserStatus.VERIFIED.value());
        respRSA.setRespuesta(rsaResponse);

        respuestaCredencialesMya.setEstadoRespuesta(EstadoRespuesta.OK);
        loginResponseView.setToken("UnaRespuesta");
        respuestaClienteManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaClienteManager.setItemMensajeRespuesta(null);
        respuestaClienteManager.setRespuesta(loginResponseView);

        headerNames.add("Accept");
        headerNames.add("Accept-Charset");

        Enumeration<String> enumerations = headerNames.elements();

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestURI()).thenReturn("http://localhost:8080/");
        Mockito.when(request.getHeaderNames()).thenReturn(enumerations);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);
        Mockito.when(myaManager.obtenerEstadoMya(Matchers.any(Cliente.class))).thenReturn(respuestaCredencialesMya);
        Mockito.when(clienteManager.obtenerClienteSesion()).thenReturn(cliente);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
        Mockito.when(clienteManager.login(Matchers.any(UsernamePasswordAuthenticationToken.class),
                Matchers.any(LoginView.class), Matchers.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class)))
                .thenReturn(respuestaClienteManager);
        Mockito.when(myaManager.obtenerMya(respuestaClienteManager)).thenReturn(respuestaClienteManager);
        when(request.getHeader(Matchers.anyString())).thenReturn("MAC");

        String encriptado = "ISBANCRYPTO_v1.0_IBFbx2cfOxLgEfSg6HupCsVL+ZUHwSn+7sI5mj9ef7x7plkG3Z/aagULX0Z3QpXBuhRd0H13AWFZHm2QXvwy/jNBMqGlpHkktTNWKIysJqhnS6XCOp7PaRJ+k/T3UM4FWdswT+btjf7zDAxfKEjKNRjxRSVqebe7DJgltcn4XbH7hUQTzwCxJuzVOGbTS8I2MyURWPjhXTKRiu1yFThC5Ksl+KRiQXfMrpGXl6hNeSeQqyj5z1eZpFoJB5rex4yT4e3/9lbQnvzua2CPgqaeRbww5azum4WrA658Rm+3eg3+P+p3a/TI0sJcdOlxknHcXUokGrlRYPni3y9RxrKmmA==|1536h4J0AM1JKwxcIpbAiZe4aD3fVK/OBQCixBO8TutrYJ0jFdQzo13hAaErrYxGrsjztR5HajH0r02zj6tZHlnR/9gshz6RlP4TXKUXICL3Z3IWEQeudcI0OV+4frWUV6BCTz8ZMMc7UqBTe+FxIUXdU+3eW7oORnLC8LkXkP15DSvFX58n0qWAPF1UBYa71d1PRQy6AEJWblefp7kZXV/uJBxmrYWBkcP5DkEO0jh06ioTY3x2io8QKEUguX6IAlURb2tGd5LVa90vUCbVHweRoLQZm8PMlUZdD8DJK/tUG0qLky7UafmHTRvgpziCXTPmCmatpWpeGFlJ76gfulZ8RyCUBPLVaPLi4cWN89AnnrP3LHl0e1RU2aDnrYQqtI+1Ws3N+CNd+Ouze2MjdENa1OEpYLLhi/YYAeOL+D7mXdcLsgOuaJo51TYYqjmRZ8e2HHZMIx91qZ4szpDbk/XrENIrqPFkfKNa3Rs1CJMpHMFk41B/qwyCHu6Akh4EYiaO72AHE6IZpG1F6tiOgIZiTxWzh3AcWz6VpZUvZQmX9QonCcZBL7y7t7fBOdlJmw8wV3Zu8dZ50fBtLHulbhrDkE2/zW2hP1oHh2MizXzAAmI8EZnBr8zcNvlRsftkSwZoLt/3zfj5SRMU056eqtm08E65jBakWidcOpW4H1LpbQ9srwZ9guQNaM48zVEsbjnZ7z8tZCmfpKjTN2ZGnOlo0UPMSUCsKKZQg+MC91aoN6BbClv1cA39pVqByOCN3U5c9ijzDQ/muG6myCULKl1Dr0a2Bii7hqWTWdPF6Chy3BklNsUhpailV8J71Phf332dhbv3YRGm+fLqblLsXaynZtaQ7i/ed/ChbmUwCwwk9N1lITF3iynbm+ZDhcuZLTHXgsLfE+U/pEKV3+r4ropVDGQjbKQSayQSU/zHEPziAzc3uWvFG98Lfx6/lgXfLr1BnpnQ9dH99r8H0lZhWtz0ofTA9g8ha8tEn5HU72SfMPsA5MwQlRyT6VK7ADFlhGc7Kt+8+cRdPumy8PUz1rQ0tCT2cbqYKdoFJzeOphaR4KZM3QpwZhNOkffnqZDXYO61whoh7BWvNnX+cZ7SyHl3WkuX7bRIWmiavb09cJYUW1ZsoMPQcnj50P1WGJltdWxcXIqACDRhedywsNKejxI7xmaVBmkZWWaHjqCycO2AxTec8S7N0qEtRcJ98f4xwug4x7sBth3eOEnhtkvyq41M49jSrBPpE/zjll0V7JYl6mlk0N0jC1e+QeNgwgLWBAyw49+wVMkP6oVO4UUyrJSPwPxdNclQ44/Um/0Pt4/rn30lKUPFGWOKcxI6Rs5fWT2vMl8F3TZJ29dQGL2LebX3AE8x8diBL5+ZOtcCbnDFDN857Q65vlEohqmc6GJMdsORlGerfB2wckKSJSzxVeTTPRZvRMv/80iaFfnX6F13cXrhvs57L2cE+3kDZ7LMqOUF8za/lcGmd9DrpZMfFRIKrRBbByysJSFf0wefxoFDDL0xVH477SUcOcGa7otikKmV";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"clave\":\"330A330A330A330A282A297A271A349A\",\"usuario\":\"348A304A37A304A282A297A271A349A315A315A315A315A315A315A315A315A315A315A315A315A\",\"usuarioVacio\":\"315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A\",\"claveVacia\":\"330A330A330A330A330A330A330A330A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);

        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGIN_HBILITADO)).thenReturn(moduloPermiso);

        LoginView loginView = new LoginView();
        Mockito.when(encryPines.obtenerViewFromJson(Mockito.anyString(), Mockito.any(Class.class)))
                .thenReturn(loginView);
        Respuesta<LoginResponseView> respuesta = loginManager.login(encriptado, request, response);

        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().getToken(), "UnaRespuesta");

    }

    /**
     * Grabar estadistica usuario clave expiradas test.
     */
    @Test
    public void grabarEstadisticaUsuarioClaveExpiradasTest() {

        Respuesta<Void> respuesta = loginManager.grabarEstadisticaUsuarioClaveExpiradas();

        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        verify(estadisticaManager).add(EstadisticasConstants.INICIO_CLAVE_USUARIO_EXPIRADOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

    }

    /**
     * Confirmar clave expiradas test.
     */
    @Test
    public void confirmarClaveExpiradasTest() {
        RegistroSesion registroSesion = new RegistroSesion();
        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();

        credencialesLoginView.setClaveActual("12345");
        credencialesLoginView.setClaveNueva("54321");
        registroSesion.setClave("12345");
        registroSesion.setClaveNueva("54321");
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
        Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesLoginView.class))
                .thenReturn(credencialesLoginView);

        Respuesta<Void> respuesta = loginManager.loginConfirmarClave(encriptado, null);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Confirmar clave expiradas dos test.
     */
    @Test
    public void confirmarClaveExpiradasDosTest() {
        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();

        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(null);

        String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
        Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesLoginView.class))
                .thenReturn(credencialesLoginView);

        Respuesta<Void> respuesta = loginManager.loginConfirmarClave(encriptado, null);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Login definir usuario test.
     */
    @Test
    public void loginDefinirUsuarioTest() {
        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();
        RegistroSesion registroSesion = new RegistroSesion();
        Respuesta<FeedbackMensajeView> respuestaFeedbackMensajeView = new Respuesta<FeedbackMensajeView>();

        registroSesion.setIsAlta(true);
        registroSesion.setDniEncriptado("330A330A330A271A349A297A57A200A349A57A102A");
        registroSesion.setFechaNacimiento(null);
        registroSesion.setPrefijo("143A");
        registroSesion.setSufijo("A");

        credencialesLoginView.setClaveActual("12345");
        credencialesLoginView.setClaveNueva("54321");

        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(clienteManager.loginDefinirUsuario(Matchers.any(CredencialCliente.class)))
                .thenReturn(respuestaFeedbackMensajeView);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("180.166.1.1");
        
        String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
        Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesLoginView.class))
                .thenReturn(credencialesLoginView);
        
        Respuesta<FeedbackMensajeView> respuesta = loginManager.loginDefinirUsuario(request, encriptado);

        assertNotNull(respuesta);

    }

    /**
     * Cambio usuario pendiente.
     */
    @Test
    public void cambioUsuarioPendiente() {

        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();
        RegistroSesion registroSesion = new RegistroSesion();
        Respuesta<FeedbackMensajeView> respuestaFeedbackMensajeView = new Respuesta<FeedbackMensajeView>();
        FeedbackMensajeView feedbackMensajeView = new FeedbackMensajeView();

        feedbackMensajeView.setMensaje("Mensaje ok");
        respuestaFeedbackMensajeView.setRespuesta(feedbackMensajeView);

        when(clienteManager.cambioUsuarioPendiente(Matchers.any(CredencialCliente.class)))
                .thenReturn(respuestaFeedbackMensajeView);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("180.166.1.1");

        Respuesta<FeedbackMensajeView> respuesta = loginManager.cambioUsuarioPendiente(request, credencialesLoginView);

        assertNotNull(respuesta);
        assertEquals(respuesta.getRespuesta().getMensaje(), "Mensaje ok");

    }

    /**
     * Login mya SA primer acceso.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Ignore
    @SuppressWarnings("unchecked")
    @Test
    public void loginMyaSAPrimerAcceso() throws IllegalAccessException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Vector<String> headerNames = new Vector<String>();
        Respuesta<LoginResponseView> respuestaClienteManager = new Respuesta<LoginResponseView>();
        Respuesta<CredencialesMya> respuestaCredencialesMya = new Respuesta<CredencialesMya>();
        
        Respuesta<String> respuestaLegal = new Respuesta<String>();
        LoginResponseView loginResponseView = new LoginResponseView();
        Cliente cliente = new Cliente();
        RegistroSesion registroSession = new RegistroSesion();
        registroSession.setIp("160.11.12.1");

        Respuesta<RSAResponse> respRSA = new Respuesta<RSAResponse>();
        RSAResponse rsaResponse = new RSAResponse();
        respRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        rsaResponse.setActionCode(ActionCode.ALLOW);
        rsaResponse.setUserStatus(UserStatus.VERIFIED.value());
        respRSA.setRespuesta(rsaResponse);
        respuestaLegal.setRespuesta("Terminos y condiciones");
        respuestaCredencialesMya.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaClienteManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaClienteManager.setRespuesta(loginResponseView);
        headerNames.add("Accept");
        headerNames.add("Accept-Charset");
        Enumeration<String> enumerations = headerNames.elements();

        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegal);
        Mockito.when(myaManager.obtenerEstadoMya(Matchers.any(Cliente.class))).thenReturn(respuestaCredencialesMya);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession(false)).thenReturn(session);
        Mockito.when(request.getRequestURI()).thenReturn("http://localhost:8080/");
        Mockito.when(request.getHeaderNames()).thenReturn(enumerations);
        when(request.getHeader(Matchers.anyString())).thenReturn("MAC");
        Mockito.when(clienteManager.login(Matchers.any(UsernamePasswordAuthenticationToken.class),
                Matchers.any(LoginView.class), Matchers.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class)))
                .thenReturn(respuestaClienteManager);

        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSession);

        Respuesta<LoginResponseView> respuestaMya = new Respuesta<LoginResponseView>();
        respuestaMya = Respuesta.copy(LoginResponseView.class, respuestaLegal);
        respuestaMya.setEstadoRespuesta(EstadoRespuesta.WARNING);
        LoginResponseView logResponse = new LoginResponseView();
        CredencialesMya myaResponse = new CredencialesMya();
        myaResponse.setEmail("pepe@mail.com");
		logResponse.setCredencialesMya(myaResponse);
        respuestaMya.setRespuesta(logResponse);

        Respuesta<Void> rsaRespuesta = new Respuesta<Void>();
        rsaRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(myaManager.obtenerMya(respuestaClienteManager)).thenReturn(respuestaMya);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGIN_HBILITADO)).thenReturn(moduloPermiso);

        String encriptado = "ISBANCRYPTO_v1.0_IBFbx2cfOxLgEfSg6HupCsVL+ZUHwSn+7sI5mj9ef7x7plkG3Z/aagULX0Z3QpXBuhRd0H13AWFZHm2QXvwy/jNBMqGlpHkktTNWKIysJqhnS6XCOp7PaRJ+k/T3UM4FWdswT+btjf7zDAxfKEjKNRjxRSVqebe7DJgltcn4XbH7hUQTzwCxJuzVOGbTS8I2MyURWPjhXTKRiu1yFThC5Ksl+KRiQXfMrpGXl6hNeSeQqyj5z1eZpFoJB5rex4yT4e3/9lbQnvzua2CPgqaeRbww5azum4WrA658Rm+3eg3+P+p3a/TI0sJcdOlxknHcXUokGrlRYPni3y9RxrKmmA==|1536h4J0AM1JKwxcIpbAiZe4aD3fVK/OBQCixBO8TutrYJ0jFdQzo13hAaErrYxGrsjztR5HajH0r02zj6tZHlnR/9gshz6RlP4TXKUXICL3Z3IWEQeudcI0OV+4frWUV6BCTz8ZMMc7UqBTe+FxIUXdU+3eW7oORnLC8LkXkP15DSvFX58n0qWAPF1UBYa71d1PRQy6AEJWblefp7kZXV/uJBxmrYWBkcP5DkEO0jh06ioTY3x2io8QKEUguX6IAlURb2tGd5LVa90vUCbVHweRoLQZm8PMlUZdD8DJK/tUG0qLky7UafmHTRvgpziCXTPmCmatpWpeGFlJ76gfulZ8RyCUBPLVaPLi4cWN89AnnrP3LHl0e1RU2aDnrYQqtI+1Ws3N+CNd+Ouze2MjdENa1OEpYLLhi/YYAeOL+D7mXdcLsgOuaJo51TYYqjmRZ8e2HHZMIx91qZ4szpDbk/XrENIrqPFkfKNa3Rs1CJMpHMFk41B/qwyCHu6Akh4EYiaO72AHE6IZpG1F6tiOgIZiTxWzh3AcWz6VpZUvZQmX9QonCcZBL7y7t7fBOdlJmw8wV3Zu8dZ50fBtLHulbhrDkE2/zW2hP1oHh2MizXzAAmI8EZnBr8zcNvlRsftkSwZoLt/3zfj5SRMU056eqtm08E65jBakWidcOpW4H1LpbQ9srwZ9guQNaM48zVEsbjnZ7z8tZCmfpKjTN2ZGnOlo0UPMSUCsKKZQg+MC91aoN6BbClv1cA39pVqByOCN3U5c9ijzDQ/muG6myCULKl1Dr0a2Bii7hqWTWdPF6Chy3BklNsUhpailV8J71Phf332dhbv3YRGm+fLqblLsXaynZtaQ7i/ed/ChbmUwCwwk9N1lITF3iynbm+ZDhcuZLTHXgsLfE+U/pEKV3+r4ropVDGQjbKQSayQSU/zHEPziAzc3uWvFG98Lfx6/lgXfLr1BnpnQ9dH99r8H0lZhWtz0ofTA9g8ha8tEn5HU72SfMPsA5MwQlRyT6VK7ADFlhGc7Kt+8+cRdPumy8PUz1rQ0tCT2cbqYKdoFJzeOphaR4KZM3QpwZhNOkffnqZDXYO61whoh7BWvNnX+cZ7SyHl3WkuX7bRIWmiavb09cJYUW1ZsoMPQcnj50P1WGJltdWxcXIqACDRhedywsNKejxI7xmaVBmkZWWaHjqCycO2AxTec8S7N0qEtRcJ98f4xwug4x7sBth3eOEnhtkvyq41M49jSrBPpE/zjll0V7JYl6mlk0N0jC1e+QeNgwgLWBAyw49+wVMkP6oVO4UUyrJSPwPxdNclQ44/Um/0Pt4/rn30lKUPFGWOKcxI6Rs5fWT2vMl8F3TZJ29dQGL2LebX3AE8x8diBL5+ZOtcCbnDFDN857Q65vlEohqmc6GJMdsORlGerfB2wckKSJSzxVeTTPRZvRMv/80iaFfnX6F13cXrhvs57L2cE+3kDZ7LMqOUF8za/lcGmd9DrpZMfFRIKrRBbByysJSFf0wefxoFDDL0xVH477SUcOcGa7otikKmV";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"clave\":\"330A330A330A330A282A297A271A349A\",\"usuario\":\"348A304A37A304A282A297A271A349A315A315A315A315A315A315A315A315A315A315A315A315A\",\"usuarioVacio\":\"315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A\",\"claveVacia\":\"330A330A330A330A330A330A330A330A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);

        LoginView loginView = new LoginView();
        Mockito.when(encryPines.obtenerViewFromJson(Mockito.anyString(), Mockito.any(Class.class)))
                .thenReturn(loginView);

        Respuesta<LoginResponseView> respuesta = loginManager.login(encriptado, request, response);

        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

    }

    @Test
    public void loginCUEConfirmarUsuarioNoClaveOnlineTest() {

        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();
        RegistroSesion registroSesion = new RegistroSesion();
        DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
        registroSesion.setDatosAutenticacion(datosAutenticacion);
        Respuesta<FeedbackMensajeView> respuestaFeedbackMensajeView = new Respuesta<FeedbackMensajeView>();
        MessageContext mc = Mockito.mock(MessageContext.class);

        credencialesLoginView.setFromClaveOnline(Boolean.FALSE);
        when(clienteManager.loginConfirmarUsuario(Matchers.any(CredencialCliente.class), Matchers.any(HttpServletRequest.class)))
                .thenReturn(respuestaFeedbackMensajeView);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
        Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesLoginView.class))
                .thenReturn(credencialesLoginView);

        Respuesta<FeedbackMensajeView> respuesta = loginManager.loginConfirmarUsuario(encriptado, mc);

        assertNotNull(respuesta);
    }

    @Ignore
    @Test
    public void loginIngresoHomeTest() {

        ResumenCliente resumenCliente = new ResumenCliente();
        Respuesta<LoginResponseView> respuestaLoginResponseView = new Respuesta<LoginResponseView>();        
        LoginResponseView responseView = new LoginResponseView();
        CredencialesMya credencialesMya = new CredencialesMya();
        credencialesMya.setEmail("pepe@mail.com");
		responseView.setCredencialesMya(credencialesMya);
		respuestaLoginResponseView.setRespuesta(responseView);
        
        Cliente cliente = new Cliente();
        Respuesta<CredencialesMya> respuestaCredencialesMya = new Respuesta<CredencialesMya>();

        RegistroSesion registroSession = new RegistroSesion();
        registroSession.setIp("160.11.12.1");

        Respuesta<RSAResponse> respRSA = new Respuesta<RSAResponse>();
        RSAResponse rsaResponse = new RSAResponse();
        respRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        rsaResponse.setActionCode(ActionCode.ALLOW);
        rsaResponse.setUserStatus(UserStatus.VERIFIED.value());
        respRSA.setRespuesta(rsaResponse);

        respuestaCredencialesMya.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLoginResponseView.setEstadoRespuesta(EstadoRespuesta.OK);

        when(myaManager.obtenerEstadoMya(Matchers.any(Cliente.class))).thenReturn(respuestaCredencialesMya);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(clienteManager.realizarControlSesionObtenerClienteConSaldo(Matchers.any(ResumenCliente.class),
                Matchers.any(CredencialCliente.class), Matchers.any(LoginView.class),
                Matchers.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class))).thenReturn(respuestaLoginResponseView);
        when(sesionCliente.getResumenCliente()).thenReturn(resumenCliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSession);

        Respuesta<Void> rsaRespuesta = new Respuesta<Void>();
        rsaRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // Mockito.when(rsaManager.analyzeRsa()).thenReturn(rsaRespuesta);

        Mockito.when(myaManager.obtenerMya(respuestaLoginResponseView)).thenReturn(respuestaLoginResponseView);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Vector<String> headerNames = new Vector<String>();
        headerNames.add("Accept");
        headerNames.add("Accept-Charset");
        Enumeration<String> enumerations = headerNames.elements();
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestURI()).thenReturn("http://localhost:8080/");
        Mockito.when(request.getHeaderNames()).thenReturn(enumerations);
        when(request.getHeader(Matchers.anyString())).thenReturn("MAC");

        Respuesta<LoginResponseView> respuesta = loginManager.loginIngresoHome(request, response);

        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmarDatosMyaTest() {

        DatosMyaView datosMyaView = new DatosMyaView();
        Respuesta<CredencialesMya> respuestaCredencialesMya = new Respuesta<CredencialesMya>();
        Mensaje mensaje = new Mensaje();

        mensaje.setMensaje("Mi mensaje");
        respuestaCredencialesMya.setEstadoRespuesta(EstadoRespuesta.OK);

        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(myaManager.confirmarDatosMya(Matchers.any(DatosMyaView.class))).thenReturn(respuestaCredencialesMya);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Vector<String> headerNames = new Vector<String>();
        headerNames.add("Accept");
        headerNames.add("Accept-Charset");
        Enumeration<String> enumerations = headerNames.elements();
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestURI()).thenReturn("http://localhost:8080/");
        Mockito.when(request.getHeaderNames()).thenReturn(enumerations);
        when(request.getHeader(Matchers.anyString())).thenReturn("MAC");
        Respuesta<LoginResponseView> respuesta = loginManager.confirmarDatosMya(datosMyaView, request);

        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(respuesta.getRespuesta().getMensaje(), "Mi mensaje");
    }

    @Test
    public void confirmarDatosMyaWarningTest() {

        DatosMyaView datosMyaView = new DatosMyaView();
        Respuesta<CredencialesMya> respuestaCredencialesMya = new Respuesta<CredencialesMya>();
        Mensaje mensaje = new Mensaje();
        List<ItemMensajeRespuesta> listItemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        listItemMensajeRespuesta.add(itemMensajeRespuesta);
        mensaje.setMensaje("Mi mensaje");
        respuestaCredencialesMya.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaCredencialesMya.setItemMensajeRespuesta(listItemMensajeRespuesta);

        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(myaManager.confirmarDatosMya(Matchers.any(DatosMyaView.class))).thenReturn(respuestaCredencialesMya);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        Vector<String> headerNames = new Vector<String>();
        headerNames.add("Accept");
        headerNames.add("Accept-Charset");
        Enumeration<String> enumerations = headerNames.elements();
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestURI()).thenReturn("http://localhost:8080/");
        Mockito.when(request.getHeaderNames()).thenReturn(enumerations);
        when(request.getHeader(Matchers.anyString())).thenReturn("MAC");

        Respuesta<LoginResponseView> respuesta = loginManager.confirmarDatosMya(datosMyaView, request);

        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertEquals(respuesta.getRespuesta().getMensaje(), "Mi mensaje");
    }

    @Test
    public void loginCUEConfirmarUsuarioClaveOnlineTest() {

        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();
        RegistroSesion registroSesion = new RegistroSesion();
        DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
        CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
        registroSesion.setDatosAutenticacion(datosAutenticacion);
        Respuesta<FeedbackMensajeView> respuestaFeedbackMensajeView = new Respuesta<FeedbackMensajeView>();
        Respuesta<AltaClaveOnlineView> respuestaAltaClaveOnlineView = new Respuesta<AltaClaveOnlineView>();

        credencialesLoginView.setFromClaveOnline(Boolean.TRUE);
        credencialesLoginView.setClaveActual("1957");
        credencialesLoginView.setUsuarioActual("homo1957");

        respuestaFeedbackMensajeView.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAltaClaveOnlineView.setEstadoRespuesta(EstadoRespuesta.OK);

        when(claveOnlineManager.altaSGIClave(Matchers.any(CredencialCliente.class), Matchers.any(MessageContext.class)))
                .thenReturn(respuestaAltaClaveOnlineView);
        when(clienteManager.loginConfirmarUsuario(Matchers.any(CredencialCliente.class), Matchers.any(HttpServletRequest.class)))
                .thenReturn(respuestaFeedbackMensajeView);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);

        MessageContext mc = Mockito.mock(MessageContext.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("180.166.1.1");
        Mockito.when(mc.getHttpServletRequest()).thenReturn(request);

        String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
        Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesLoginView.class))
                .thenReturn(credencialesLoginView);

        Respuesta<FeedbackMensajeView> respuesta = loginManager.loginConfirmarUsuario(encriptado, mc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void loginCUEConfirmarUsuarioErrorClaveOnlineTest() {

        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();
        RegistroSesion registroSesion = new RegistroSesion();
        CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
        DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
        registroSesion.setDatosAutenticacion(datosAutenticacion);
        Respuesta<FeedbackMensajeView> respuestaFeedbackMensajeView = new Respuesta<FeedbackMensajeView>();
        Respuesta<AltaClaveOnlineView> respuestaAltaClaveOnlineView = new Respuesta<AltaClaveOnlineView>();

        credencialesLoginView.setFromClaveOnline(Boolean.TRUE);
        credencialesLoginView.setClaveActual("1957");
        credencialesLoginView.setUsuarioActual("homo1957");

        respuestaFeedbackMensajeView.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaAltaClaveOnlineView.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(claveOnlineManager.altaSGIClave(Matchers.any(CredencialCliente.class), Matchers.any(MessageContext.class)))
                .thenReturn(respuestaAltaClaveOnlineView);
        when(clienteManager.loginConfirmarUsuario(Matchers.any(CredencialCliente.class), Matchers.any(HttpServletRequest.class)))
                .thenReturn(respuestaFeedbackMensajeView);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
        MessageContext mc = Mockito.mock(MessageContext.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("180.166.1.1");
        Mockito.when(mc.getHttpServletRequest()).thenReturn(request);

        String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
        Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesLoginView.class))
                .thenReturn(credencialesLoginView);

        Respuesta<FeedbackMensajeView> respuesta = loginManager.loginConfirmarUsuario(encriptado, mc);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Test
    public void loginCUEConfirmarClaveTest() {

        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();
        RegistroSesion registroSesion = new RegistroSesion();
        DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
        registroSesion.setDatosAutenticacion(datosAutenticacion);
        credencialesLoginView.setFromClaveOnline(Boolean.FALSE);
        credencialesLoginView.setClaveActual("1957");
        credencialesLoginView.setUsuarioActual("homo1957");
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
        Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesLoginView.class))
                .thenReturn(credencialesLoginView);

        Respuesta<Void> respuesta = loginManager.loginConfirmarClave(encriptado, null);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void loginCUEConfirmarClaveOnlineTest() {

        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();
        RegistroSesion registroSesion = new RegistroSesion();
        DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
        registroSesion.setDatosAutenticacion(datosAutenticacion);
        credencialesLoginView.setFromClaveOnline(Boolean.TRUE);
        credencialesLoginView.setClaveActual("1957");
        credencialesLoginView.setUsuarioActual("homo1957");
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        String encriptado = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Mockito.when(encryPines.obtenerSoloCrypto(encriptado)).thenReturn(soloCrypto);
        Mockito.when(encryPines.obtenerViewFromJson(soloCrypto, CredencialesLoginView.class))
                .thenReturn(credencialesLoginView);

        Respuesta<Void> respuesta = loginManager.loginConfirmarClave(encriptado, null);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void cargarFlagFreeTbanco() throws IllegalAccessException {
        MessageContext mc = Mockito.mock(MessageContext.class);
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        String[] hostList = { "intranet.santanderrio.com.ar", "localhost" };
        FieldUtils.writeDeclaredField(loginManager, "hostList", Arrays.asList(hostList), true);
        when(mc.getHttpServletRequest()).thenReturn(httpServletRequest);
        when(httpServletRequest.getHeader("x-forwarded-for")).thenReturn("180.166.30.34");
        when(httpServletRequest.getHeader("Wil")).thenReturn("http://intranet.santanderrio.com.ar:8080/index.html");
        loginManager.cargarFlagFreeTbanco(mc);
        verify(sesionParametros, Mockito.times(1)).setIgnoraTablaTbanco(Boolean.TRUE);
        ;
    }

    @Test
    public void evaluarCampaignInLogin_GoToMicrofrontCentroDeAyuda_Ok() {

        String campaignMock = "gotoCentroDeAyudaPrivado()|queryParams!subreason=233|sourceLocation!bspruebalocation|prueba!conCientoCincuentaCaracteressssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
        WebContentView respuesta = loginManager.evaluarCampaignInLogin(campaignMock);

        assertNotNull(respuesta);
        assertEquals("?queryParams!subreason=233,sourceLocation!bspruebalocation,prueba!conCientoCincuentaCaracteressssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss;", respuesta.getData());
        assertEquals("gotoCentroDeAyudaPrivado()", respuesta.getLink());
        assertTrue(respuesta.getEsDeeplink());
    }

    @Test
    public void evaluarCampaignInLogin_GoToMicrofrontCentroDeAyuda_NotMatched() {

        String campaignMock = "gotoCentroDeAyudaPrivado()|";
        WebContentView respuesta = loginManager.evaluarCampaignInLogin(campaignMock);

        assertNotNull(respuesta);
        assertNull(respuesta.getData());
        assertNull(respuesta.getLink());
        assertFalse(respuesta.getEsDeeplink());

        String campaignMock2 = "gotoCentroDeAyudaPrivado()";
        WebContentView respuesta2 = loginManager.evaluarCampaignInLogin(campaignMock2);

        assertNotNull(respuesta2);
        assertNull(respuesta2.getData());
        assertNull(respuesta2.getLink());
        assertFalse(respuesta2.getEsDeeplink());
    }

    @Test
    public void evaluarCampaignInLogin_GoToMicrofrontCentroDeAyuda_ParametersExceeded() {

        String campaignMock = "gotoCentroDeAyudaPrivado()|param2|param3|param4|param5|param6|param7|param8|param9";
        WebContentView respuesta = loginManager.evaluarCampaignInLogin(campaignMock);

        assertNotNull(respuesta);
        assertNull(respuesta.getData());
        assertNull(respuesta.getLink());
        assertFalse(respuesta.getEsDeeplink());
    }

    @Test
    public void evaluarCampaignInLogin_GoToMicrofrontCentroDeAyuda_ParameterLengthExceeded() {

        String campaignMock = "gotoCentroDeAyudaPrivado()|CientoCincuentayUnCaracteressssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssCientoOchentaCaracteressssssssssssssssssssssssssssssssssssss";
        WebContentView respuesta = loginManager.evaluarCampaignInLogin(campaignMock);

        assertNotNull(respuesta);
        assertNull(respuesta.getData());
        assertNull(respuesta.getLink());
        assertFalse(respuesta.getEsDeeplink());
    }
}
