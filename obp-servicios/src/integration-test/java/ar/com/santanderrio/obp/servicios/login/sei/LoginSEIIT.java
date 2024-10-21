package ar.com.santanderrio.obp.servicios.login.sei;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.codehaus.jackson.JsonParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines;
import ar.com.santanderrio.obp.servicios.login.manager.LoginManager;
import ar.com.santanderrio.obp.servicios.login.sei.LoginSEIIT.LoginSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.login.sei.impl.LoginSEIImpl;
import ar.com.santanderrio.obp.servicios.login.view.CredencialesLoginView;
import ar.com.santanderrio.obp.servicios.login.view.InicioLoginView;
import ar.com.santanderrio.obp.servicios.login.view.LogOutResponseView;
import ar.com.santanderrio.obp.servicios.login.view.LoginResponseView;
import ar.com.santanderrio.obp.servicios.monitoreo.manager.MonitoreoManager;

/**
 * Probar el SEI de login. validar que se pueda hacer el post segun lo definido
 * y que el request y response se generen segun lo esperado. Solo llega al sei,
 * no sigue la cadena del manager (mockeado).
 * 
 * @author sergio.e.goldentair
 *
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { LoginSEITestConfiguration.class })
public class LoginSEIIT extends AbstractSEITest {

    /** The login manager. */
    @Autowired
    private LoginManager loginManager;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class LoginSEITestConfiguration.
     */
    @Configuration
    public static class LoginSEITestConfiguration {

        /**
         * Login SEI.
         *
         * @return the login SEI
         */
        @Bean(name = "loginSEI")
        public LoginSEI loginSEI() {
            return new LoginSEIImpl();
        }

        /**
         * Cliente manager.
         *
         * @return the cliente manager
         */
        @Bean
        public ClienteManager clienteManager() {
            return Mockito.mock(ClienteManager.class);
        }

        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         */
        @Bean(name = "respuestaFactory")
        public RespuestaFactory respuestaFactory() {
            return Mockito.mock(RespuestaFactory.class);
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean(name = "mensajeBO")
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

        /**
         * Login manager.
         *
         * @return the login manager
         */
        @Bean
        LoginManager loginManager() {
            return Mockito.mock(LoginManager.class);
        }

        /**
         * The monitoreo Manager.
         * 
         * @return
         */
        @Bean
        public MonitoreoManager monitoreoManager() {
            return Mockito.mock(MonitoreoManager.class);
        }

        /**
         * Manejar la trama encriptada.
         * 
         * @return
         */
        @Bean
        public EncryPines encryPines() {
            return Mockito.mock(EncryPines.class);
        }
    }

    /**
     * Login OK.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void loginOK() {
        Respuesta<LoginResponseView> respuesta = new Respuesta<LoginResponseView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        Mockito.when(loginManager.login(Matchers.anyString(), Matchers.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
        client.path("/login/doLogin");

        String datoEntrada = "ISBANCRYPTO_v1.0_IBFbx2cfOxLgEfSg6HupCsVL+ZUHwSn+7sI5mj9ef7x7plkG3Z/aagULX0Z3QpXBuhRd0H13AWFZHm2QXvwy/jNBMqGlpHkktTNWKIysJqhnS6XCOp7PaRJ+k/T3UM4FWdswT+btjf7zDAxfKEjKNRjxRSVqebe7DJgltcn4XbH7hUQTzwCxJuzVOGbTS8I2MyURWPjhXTKRiu1yFThC5Ksl+KRiQXfMrpGXl6hNeSeQqyj5z1eZpFoJB5rex4yT4e3/9lbQnvzua2CPgqaeRbww5azum4WrA658Rm+3eg3+P+p3a/TI0sJcdOlxknHcXUokGrlRYPni3y9RxrKmmA==|1536h4J0AM1JKwxcIpbAiZe4aD3fVK/OBQCixBO8TutrYJ0jFdQzo13hAaErrYxGrsjztR5HajH0r02zj6tZHlnR/9gshz6RlP4TXKUXICL3Z3IWEQeudcI0OV+4frWUV6BCTz8ZMMc7UqBTe+FxIUXdU+3eW7oORnLC8LkXkP15DSvFX58n0qWAPF1UBYa71d1PRQy6AEJWblefp7kZXV/uJBxmrYWBkcP5DkEO0jh06ioTY3x2io8QKEUguX6IAlURb2tGd5LVa90vUCbVHweRoLQZm8PMlUZdD8DJK/tUG0qLky7UafmHTRvgpziCXTPmCmatpWpeGFlJ76gfulZ8RyCUBPLVaPLi4cWN89AnnrP3LHl0e1RU2aDnrYQqtI+1Ws3N+CNd+Ouze2MjdENa1OEpYLLhi/YYAeOL+D7mXdcLsgOuaJo51TYYqjmRZ8e2HHZMIx91qZ4szpDbk/XrENIrqPFkfKNa3Rs1CJMpHMFk41B/qwyCHu6Akh4EYiaO72AHE6IZpG1F6tiOgIZiTxWzh3AcWz6VpZUvZQmX9QonCcZBL7y7t7fBOdlJmw8wV3Zu8dZ50fBtLHulbhrDkE2/zW2hP1oHh2MizXzAAmI8EZnBr8zcNvlRsftkSwZoLt/3zfj5SRMU056eqtm08E65jBakWidcOpW4H1LpbQ9srwZ9guQNaM48zVEsbjnZ7z8tZCmfpKjTN2ZGnOlo0UPMSUCsKKZQg+MC91aoN6BbClv1cA39pVqByOCN3U5c9ijzDQ/muG6myCULKl1Dr0a2Bii7hqWTWdPF6Chy3BklNsUhpailV8J71Phf332dhbv3YRGm+fLqblLsXaynZtaQ7i/ed/ChbmUwCwwk9N1lITF3iynbm+ZDhcuZLTHXgsLfE+U/pEKV3+r4ropVDGQjbKQSayQSU/zHEPziAzc3uWvFG98Lfx6/lgXfLr1BnpnQ9dH99r8H0lZhWtz0ofTA9g8ha8tEn5HU72SfMPsA5MwQlRyT6VK7ADFlhGc7Kt+8+cRdPumy8PUz1rQ0tCT2cbqYKdoFJzeOphaR4KZM3QpwZhNOkffnqZDXYO61whoh7BWvNnX+cZ7SyHl3WkuX7bRIWmiavb09cJYUW1ZsoMPQcnj50P1WGJltdWxcXIqACDRhedywsNKejxI7xmaVBmkZWWaHjqCycO2AxTec8S7N0qEtRcJ98f4xwug4x7sBth3eOEnhtkvyq41M49jSrBPpE/zjll0V7JYl6mlk0N0jC1e+QeNgwgLWBAyw49+wVMkP6oVO4UUyrJSPwPxdNclQ44/Um/0Pt4/rn30lKUPFGWOKcxI6Rs5fWT2vMl8F3TZJ29dQGL2LebX3AE8x8diBL5+ZOtcCbnDFDN857Q65vlEohqmc6GJMdsORlGerfB2wckKSJSzxVeTTPRZvRMv/80iaFfnX6F13cXrhvs57L2cE+3kDZ7LMqOUF8za/lcGmd9DrpZMfFRIKrRBbByysJSFf0wefxoFDDL0xVH477SUcOcGa7otikKmV";
        Form form = new Form();
        form.param("datoEntrada", datoEntrada);
        Respuesta<String> retorno = client.post(form, Respuesta.class);

        Assert.assertNotNull(retorno);
        assertThat(retorno.getEstadoRespuesta(), is(EstadoRespuesta.OK));
    }

    /**
     * Obtener inicio login.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void obtenerInicioLogin() throws JsonParseException, IOException {
        Respuesta<InicioLoginView> respuesta = new Respuesta<InicioLoginView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        Mockito.when(loginManager.obtenerIncioLogin()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/login/inicio");
        super.addSleepTime(2000);

        InicioLoginView inicioLoginView = new InicioLoginView();
        inicioLoginView.setMensajeLogin("Hola Login");
        respuesta.setRespuesta(inicioLoginView);

        Respuesta<InicioLoginView> retorno = client.post(inicioLoginView, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Logout.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void logout() throws JsonParseException, IOException {
        Respuesta<LogOutResponseView> respuesta = new Respuesta<LogOutResponseView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        Mockito.when(loginManager.logout(Matchers.any(HttpServletRequest.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/login/doLogout");
        super.addSleepTime(2000);

        LogOutResponseView logOutResponseView = new LogOutResponseView();
        respuesta.setRespuesta(logOutResponseView);

        Respuesta<InicioLoginView> retorno = client.post(logOutResponseView, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * Inicio clave usuario expirados.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void inicioClaveUsuarioExpirados() throws JsonParseException, IOException {
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        Mockito.when(loginManager.grabarEstadisticaUsuarioClaveExpiradas()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/login/CUEInicio");
        super.addSleepTime(2000);

        Respuesta<Void> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    /**
     * CUE confirmar usuario.
     *
     */
    @Test
    @SuppressWarnings("unchecked")
    public void cueConfirmarUsuarioTest() {
        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        Mockito.when(loginManager.loginConfirmarUsuario(Matchers.anyString(), Matchers.any(MessageContext.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
        client.path("/login/CUEConfirmarUsuario");
        super.addSleepTime(2000);

        String datoEntrada = "ISBANCRYPTO_v1.0_IBFbx2cfOxLgEfSg6HupCsVL+ZUHwSn+7sI5mj9ef7x7plkG3Z/aagULX0Z3QpXBuhRd0H13AWFZHm2QXvwy/jNBMqGlpHkktTNWKIysJqhnS6XCOp7PaRJ+k/T3UM4FWdswT+btjf7zDAxfKEjKNRjxRSVqebe7DJgltcn4XbH7hUQTzwCxJuzVOGbTS8I2MyURWPjhXTKRiu1yFThC5Ksl+KRiQXfMrpGXl6hNeSeQqyj5z1eZpFoJB5rex4yT4e3/9lbQnvzua2CPgqaeRbww5azum4WrA658Rm+3eg3+P+p3a/TI0sJcdOlxknHcXUokGrlRYPni3y9RxrKmmA==|1536h4J0AM1JKwxcIpbAiZe4aD3fVK/OBQCixBO8TutrYJ0jFdQzo13hAaErrYxGrsjztR5HajH0r02zj6tZHlnR/9gshz6RlP4TXKUXICL3Z3IWEQeudcI0OV+4frWUV6BCTz8ZMMc7UqBTe+FxIUXdU+3eW7oORnLC8LkXkP15DSvFX58n0qWAPF1UBYa71d1PRQy6AEJWblefp7kZXV/uJBxmrYWBkcP5DkEO0jh06ioTY3x2io8QKEUguX6IAlURb2tGd5LVa90vUCbVHweRoLQZm8PMlUZdD8DJK/tUG0qLky7UafmHTRvgpziCXTPmCmatpWpeGFlJ76gfulZ8RyCUBPLVaPLi4cWN89AnnrP3LHl0e1RU2aDnrYQqtI+1Ws3N+CNd+Ouze2MjdENa1OEpYLLhi/YYAeOL+D7mXdcLsgOuaJo51TYYqjmRZ8e2HHZMIx91qZ4szpDbk/XrENIrqPFkfKNa3Rs1CJMpHMFk41B/qwyCHu6Akh4EYiaO72AHE6IZpG1F6tiOgIZiTxWzh3AcWz6VpZUvZQmX9QonCcZBL7y7t7fBOdlJmw8wV3Zu8dZ50fBtLHulbhrDkE2/zW2hP1oHh2MizXzAAmI8EZnBr8zcNvlRsftkSwZoLt/3zfj5SRMU056eqtm08E65jBakWidcOpW4H1LpbQ9srwZ9guQNaM48zVEsbjnZ7z8tZCmfpKjTN2ZGnOlo0UPMSUCsKKZQg+MC91aoN6BbClv1cA39pVqByOCN3U5c9ijzDQ/muG6myCULKl1Dr0a2Bii7hqWTWdPF6Chy3BklNsUhpailV8J71Phf332dhbv3YRGm+fLqblLsXaynZtaQ7i/ed/ChbmUwCwwk9N1lITF3iynbm+ZDhcuZLTHXgsLfE+U/pEKV3+r4ropVDGQjbKQSayQSU/zHEPziAzc3uWvFG98Lfx6/lgXfLr1BnpnQ9dH99r8H0lZhWtz0ofTA9g8ha8tEn5HU72SfMPsA5MwQlRyT6VK7ADFlhGc7Kt+8+cRdPumy8PUz1rQ0tCT2cbqYKdoFJzeOphaR4KZM3QpwZhNOkffnqZDXYO61whoh7BWvNnX+cZ7SyHl3WkuX7bRIWmiavb09cJYUW1ZsoMPQcnj50P1WGJltdWxcXIqACDRhedywsNKejxI7xmaVBmkZWWaHjqCycO2AxTec8S7N0qEtRcJ98f4xwug4x7sBth3eOEnhtkvyq41M49jSrBPpE/zjll0V7JYl6mlk0N0jC1e+QeNgwgLWBAyw49+wVMkP6oVO4UUyrJSPwPxdNclQ44/Um/0Pt4/rn30lKUPFGWOKcxI6Rs5fWT2vMl8F3TZJ29dQGL2LebX3AE8x8diBL5+ZOtcCbnDFDN857Q65vlEohqmc6GJMdsORlGerfB2wckKSJSzxVeTTPRZvRMv/80iaFfnX6F13cXrhvs57L2cE+3kDZ7LMqOUF8za/lcGmd9DrpZMfFRIKrRBbByysJSFf0wefxoFDDL0xVH477SUcOcGa7otikKmV";
        Form form = new Form();
        form.param("datoEntrada", datoEntrada);

        Respuesta<FeedbackMensajeView> retorno = client.post(form, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    /**
     * CUE confirmar clave.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void cueConfirmarClave() throws JsonParseException, IOException {
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(loginManager.loginConfirmarClave(Matchers.anyString(), Matchers.any(MessageContext.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
        client.path("/login/CUEConfirmarClave");
        super.addSleepTime(2000);

        String datoEntrada = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        Form form = new Form();
        form.param("datoEntrada", datoEntrada);

        Respuesta<Void> retorno = client.post(form, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    /**
     * Login CUE ingreso home test.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void loginCUEIngresoHomeTest() throws JsonParseException, IOException {
        Respuesta<LoginResponseView> respuesta = new Respuesta<LoginResponseView>();
        CredencialesLoginView credencialesLoginView = new CredencialesLoginView();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(loginManager.loginIngresoHome(Mockito.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/login/ingresoHome");
        super.addSleepTime(2000);

        Respuesta<Void> retorno = client.post(credencialesLoginView, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    /**
     * Refresh test.
     */
    @Test
    public void refreshTest() {
        Respuesta<LoginResponseView> respuesta = new Respuesta<LoginResponseView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(loginManager.refresh(Matchers.any(HttpServletRequest.class), Matchers.any(HttpServletResponse.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/login/refresh");
        super.addSleepTime(2000);

        Respuesta<LoginResponseView> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());

    }

    /**
     * Login definir usuario test.
     */
    @Test
    public void loginDefinirUsuarioTest() {
        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(loginManager.loginDefinirUsuario(Matchers.any(HttpServletRequest.class), Matchers.anyString()))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
        client.path("/login/definirUsuario");
        super.addSleepTime(2000);

        String datoEntrada = "ISBANCRYPTO_v1.0_WOd+nV5D4zTWxJTwB1St5Tc3rvE7DNp5tvI3dw9rL3WcUXn8g2fPDsx2M+VodPoX3UHNtFJQJunAf8AO1WsLkiNcsjlGiOsF2xvrJr9PJDCVAzC7eDrG4FMrAc4gGMxymAUIU6sSsNpIxx+pB/prHnnRErYest6FUy0JyCDJdJ8wuqUV32JHthalH7LG0cNT5NstUnVRiMxt5fVlMcg3KONID/uspgw/XYl9QCR3BFEpqMFVc/j3BIBnbVbErxJjEL2E/18RDaXLk/VsiAeBX33FuPUIFYLp0L0O3iXpC3AtqeThJQbB0Rb/D6nRbQbOJIc5YXygoymjUUJJsV+boA==|1176BNm7FMotZfoXHcyi6HFxuJpzSdXmnJAnbN5lfPwD3HefyZyjsDv8+PJgVSx5MM9guFSHNZOsVJElMBYuXCuYAhqCn1EpOn+AQeavnNUWozQfKPt7MT3DfgpEfU6NbT7MSx/aEDpfv4fYHzWWq45FQJmmgNhRQJ31PX1BWKYEnMVJuS5a7qOM0on348xD9Uv261+o4tjbhHxZKSMP9Spmog6KVi/i4I2QeYfucylJTWROPHEGRFcOyoRqzVeHb38F3RyecQOwaQC9EyPkEL7dARU7UxvkZhsSuA5ckR1Xe9gQhXOvWzYbIMJp4NlbKUlW+bS7pMACJt6Fd34O3BqLG4fv6/cQ1z7GTAiUPWWvcBjwzbFsLm33JJF1CbHxlwroFBk1S2mySAh0hWHQOBeV8UMkyN2bOa2NYadwg2fx2zcbTlbqYf2fNuPhISbDdyzIjhCPx5vZn1S8KQumEFkdwmny9jD0bzJP8FdQidqxu0wg3Duf233AYwbyMb49/BAgEx9dQo9mj1RoJluE1mG6TPLQvkkN6DOnbnMHn+1yCW357o8pBblzpH00ND3CRracRmPsR5kBkE6RQtXsUxHl1yoghvdZlLELuNhAsCHSl0XU4gLNRsgFkMj8ID+L8/DIeeNF5J15mXduRPwIEl9g65czMSLCQLYBVuClL44yDP6pxYbIccQUX6QClZIF2pGtMlp8fTCOCEb79u0HHNPddxAhKGNGA7q0w0Z42uoClCOnxHxqz02shK4rK3XB96xiUr0J8Z7uSHF9WHSVpNodDK/YxD3HvK/PYsiA1MgIAwoPEpYX0P+ZzYU78JVLlPRkCMzN6a4Muz5BiSkGxaygnmOr9oJzwZmi4BvwpJFcgS58osAp391q0hZuVENz++jZW5cJe2Mbyb0POFhHHQ1JpaH9V6QJXgsQ0i7eE5iOeE9+qi9GpkE+3cUi3O8XfUSBxPzE1Z0lYiE6mEk6oDN3c+w1QkLy1yYAClY0qb5OkRcxNgezQVBhDCtnFxPBMLbkagsSChU3XStbE3/1XJKkpSz5NRHk72gZtFjbhPptAhfTeCtovDQgPU2Ivsn44sz3nKUPql3QQQI43uTfffjBla2GVog1JVW7n58SHwrd9UaSNr5CvvGz6GU8juxdJn872pp4bqBiqbaSpXCMEaoB+g==";
        Form form = new Form();
        form.param("datoEntrada", datoEntrada);

        Respuesta<FeedbackMensajeView> retorno = client.post(form, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());

    }

    /**
     * Cambio usuario pendiente test.
     */
    @Test
    public void cambioUsuarioPendienteTest() {
        CredencialesLoginView clw = new CredencialesLoginView();
        Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(loginManager.cambioUsuarioPendiente(Matchers.any(HttpServletRequest.class),
                Matchers.any(CredencialesLoginView.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/login/cambioUsuarioPendiente");
        super.addSleepTime(2000);

        Respuesta<FeedbackMensajeView> retorno = client.post(clw, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("loginSEI");
    }

}
