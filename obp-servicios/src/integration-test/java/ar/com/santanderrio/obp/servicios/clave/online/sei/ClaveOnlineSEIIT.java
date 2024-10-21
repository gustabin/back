package ar.com.santanderrio.obp.servicios.clave.online.sei;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.MessageContext;
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

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.manager.ClaveOnlineManager;
import ar.com.santanderrio.obp.servicios.clave.online.sei.ClaveOnlineSEIIT.ClaveOnlineSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.clave.online.sei.impl.ClaveOnlineSEIImpl;
import ar.com.santanderrio.obp.servicios.clave.online.view.HashView;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;

/**
 * Probar el SEI de claveOnline. validar que se pueda hacer el post segun lo
 * definido y que el request y response se generen segun lo esperado. Solo llega
 * al sei, no sigue la cadena del manager (mockeado).
 * 
 * @author B043069
 *
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ClaveOnlineSEITestConfiguration.class })
public class ClaveOnlineSEIIT extends AbstractSEITest {

    /** The claveOnline manager. */
    @Autowired
    private ClaveOnlineManager claveOnlineManager;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class ClaveOnlineSEITestConfiguration.
     */
    @Configuration
    public static class ClaveOnlineSEITestConfiguration {

        /**
         * ClaveOnline SEI.
         *
         * @return the claveOnline SEI
         */
        @Bean(name = "claveOnlineSEI")
        public ClaveOnlineSEI claveOnlineSEI() {
            return new ClaveOnlineSEIImpl();
        }

        /**
         * ClaveOnline manager.
         *
         * @return the claveOnline manager
         */
        @Bean
        ClaveOnlineManager claveOnlineManager() {
            return Mockito.mock(ClaveOnlineManager.class);
        }
    }

    /**
     * Logout.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */

    /**
     * ClaveOnline solucionarProblemas test.
     */

    @Test
    public void solucionarProblemasTest() {
        Mensaje mensaje = new Mensaje();
        Respuesta<Mensaje> respuesta = new Respuesta<Mensaje>();

        mensaje.setMensaje(CodigoMensajeConstantes.ACCESO_FUNCIONALIDAD_SOLUCIONAR);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(mensaje);

        Mockito.when(claveOnlineManager.grabarEstadisticaSolucionar()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/irSolucionar");
        super.addSleepTime(5000);

        Respuesta<Mensaje> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @Test
    public void identificarClienteSolucionarTest() {

        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(claveOnlineManager.identificarClienteSolucionar(Matchers.any(MessageContext.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/identificarClienteSolucionar");
        super.addSleepTime(5000);

        Respuesta<HashView> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @Test
    public void identificarClienteRegistrarTest() {

        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(claveOnlineManager.identificarClienteRegistrar(Matchers.any(MessageContext.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/identificarClienteRegistrar");
        super.addSleepTime(5000);

        Respuesta<HashView> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @Test
    public void identificarClienteRenovarTest() {

        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(claveOnlineManager.identificarClienteRenovar(Matchers.any(MessageContext.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/identificarClienteRenovar");
        super.addSleepTime(5000);

        Respuesta<HashView> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void confirmarRespuestaTest() {

        Respuesta<MetodoAutenticacionView> respuesta = new Respuesta<MetodoAutenticacionView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(
                claveOnlineManager.confirmarRespuesta(Matchers.any(HttpServletRequest.class), Matchers.anyString()))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/confirmarRespuesta");
        super.addSleepTime(5000);
        String datoEntrada = "ISBANCRYPTO_v1.0_IBFbx2cfOxLgEfSg6HupCsVL+ZUHwSn+7sI5mj9ef7x7plkG3Z/aagULX0Z3QpXBuhRd0H13AWFZHm2QXvwy/jNBMqGlpHkktTNWKIysJqhnS6XCOp7PaRJ+k/T3UM4FWdswT+btjf7zDAxfKEjKNRjxRSVqebe7DJgltcn4XbH7hUQTzwCxJuzVOGbTS8I2MyURWPjhXTKRiu1yFThC5Ksl+KRiQXfMrpGXl6hNeSeQqyj5z1eZpFoJB5rex4yT4e3/9lbQnvzua2CPgqaeRbww5azum4WrA658Rm+3eg3+P+p3a/TI0sJcdOlxknHcXUokGrlRYPni3y9RxrKmmA==|1536h4J0AM1JKwxcIpbAiZe4aD3fVK/OBQCixBO8TutrYJ0jFdQzo13hAaErrYxGrsjztR5HajH0r02zj6tZHlnR/9gshz6RlP4TXKUXICL3Z3IWEQeudcI0OV+4frWUV6BCTz8ZMMc7UqBTe+FxIUXdU+3eW7oORnLC8LkXkP15DSvFX58n0qWAPF1UBYa71d1PRQy6AEJWblefp7kZXV/uJBxmrYWBkcP5DkEO0jh06ioTY3x2io8QKEUguX6IAlURb2tGd5LVa90vUCbVHweRoLQZm8PMlUZdD8DJK/tUG0qLky7UafmHTRvgpziCXTPmCmatpWpeGFlJ76gfulZ8RyCUBPLVaPLi4cWN89AnnrP3LHl0e1RU2aDnrYQqtI+1Ws3N+CNd+Ouze2MjdENa1OEpYLLhi/YYAeOL+D7mXdcLsgOuaJo51TYYqjmRZ8e2HHZMIx91qZ4szpDbk/XrENIrqPFkfKNa3Rs1CJMpHMFk41B/qwyCHu6Akh4EYiaO72AHE6IZpG1F6tiOgIZiTxWzh3AcWz6VpZUvZQmX9QonCcZBL7y7t7fBOdlJmw8wV3Zu8dZ50fBtLHulbhrDkE2/zW2hP1oHh2MizXzAAmI8EZnBr8zcNvlRsftkSwZoLt/3zfj5SRMU056eqtm08E65jBakWidcOpW4H1LpbQ9srwZ9guQNaM48zVEsbjnZ7z8tZCmfpKjTN2ZGnOlo0UPMSUCsKKZQg+MC91aoN6BbClv1cA39pVqByOCN3U5c9ijzDQ/muG6myCULKl1Dr0a2Bii7hqWTWdPF6Chy3BklNsUhpailV8J71Phf332dhbv3YRGm+fLqblLsXaynZtaQ7i/ed/ChbmUwCwwk9N1lITF3iynbm+ZDhcuZLTHXgsLfE+U/pEKV3+r4ropVDGQjbKQSayQSU/zHEPziAzc3uWvFG98Lfx6/lgXfLr1BnpnQ9dH99r8H0lZhWtz0ofTA9g8ha8tEn5HU72SfMPsA5MwQlRyT6VK7ADFlhGc7Kt+8+cRdPumy8PUz1rQ0tCT2cbqYKdoFJzeOphaR4KZM3QpwZhNOkffnqZDXYO61whoh7BWvNnX+cZ7SyHl3WkuX7bRIWmiavb09cJYUW1ZsoMPQcnj50P1WGJltdWxcXIqACDRhedywsNKejxI7xmaVBmkZWWaHjqCycO2AxTec8S7N0qEtRcJ98f4xwug4x7sBth3eOEnhtkvyq41M49jSrBPpE/zjll0V7JYl6mlk0N0jC1e+QeNgwgLWBAyw49+wVMkP6oVO4UUyrJSPwPxdNclQ44/Um/0Pt4/rn30lKUPFGWOKcxI6Rs5fWT2vMl8F3TZJ29dQGL2LebX3AE8x8diBL5+ZOtcCbnDFDN857Q65vlEohqmc6GJMdsORlGerfB2wckKSJSzxVeTTPRZvRMv/80iaFfnX6F13cXrhvs57L2cE+3kDZ7LMqOUF8za/lcGmd9DrpZMfFRIKrRBbByysJSFf0wefxoFDDL0xVH477SUcOcGa7otikKmV";
        Form form = new Form();
        form.param("datoEntrada", datoEntrada);

        Respuesta<MetodoAutenticacionView> retorno = client.post(form, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void confirmarMetodoAutenticacionTest() {

        Respuesta<MetodoAutenticacionView> respuesta = new Respuesta<MetodoAutenticacionView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(claveOnlineManager.confirmarMetodoAutenticacion(Matchers.any(MessageContext.class),
                Matchers.anyString())).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/confirmarMetodoAutenticacion");
        super.addSleepTime(5000);

        String datoEntrada = "ISBANCRYPTO_v1.0_IBFbx2cfOxLgEfSg6HupCsVL+ZUHwSn+7sI5mj9ef7x7plkG3Z/aagULX0Z3QpXBuhRd0H13AWFZHm2QXvwy/jNBMqGlpHkktTNWKIysJqhnS6XCOp7PaRJ+k/T3UM4FWdswT+btjf7zDAxfKEjKNRjxRSVqebe7DJgltcn4XbH7hUQTzwCxJuzVOGbTS8I2MyURWPjhXTKRiu1yFThC5Ksl+KRiQXfMrpGXl6hNeSeQqyj5z1eZpFoJB5rex4yT4e3/9lbQnvzua2CPgqaeRbww5azum4WrA658Rm+3eg3+P+p3a/TI0sJcdOlxknHcXUokGrlRYPni3y9RxrKmmA==|1536h4J0AM1JKwxcIpbAiZe4aD3fVK/OBQCixBO8TutrYJ0jFdQzo13hAaErrYxGrsjztR5HajH0r02zj6tZHlnR/9gshz6RlP4TXKUXICL3Z3IWEQeudcI0OV+4frWUV6BCTz8ZMMc7UqBTe+FxIUXdU+3eW7oORnLC8LkXkP15DSvFX58n0qWAPF1UBYa71d1PRQy6AEJWblefp7kZXV/uJBxmrYWBkcP5DkEO0jh06ioTY3x2io8QKEUguX6IAlURb2tGd5LVa90vUCbVHweRoLQZm8PMlUZdD8DJK/tUG0qLky7UafmHTRvgpziCXTPmCmatpWpeGFlJ76gfulZ8RyCUBPLVaPLi4cWN89AnnrP3LHl0e1RU2aDnrYQqtI+1Ws3N+CNd+Ouze2MjdENa1OEpYLLhi/YYAeOL+D7mXdcLsgOuaJo51TYYqjmRZ8e2HHZMIx91qZ4szpDbk/XrENIrqPFkfKNa3Rs1CJMpHMFk41B/qwyCHu6Akh4EYiaO72AHE6IZpG1F6tiOgIZiTxWzh3AcWz6VpZUvZQmX9QonCcZBL7y7t7fBOdlJmw8wV3Zu8dZ50fBtLHulbhrDkE2/zW2hP1oHh2MizXzAAmI8EZnBr8zcNvlRsftkSwZoLt/3zfj5SRMU056eqtm08E65jBakWidcOpW4H1LpbQ9srwZ9guQNaM48zVEsbjnZ7z8tZCmfpKjTN2ZGnOlo0UPMSUCsKKZQg+MC91aoN6BbClv1cA39pVqByOCN3U5c9ijzDQ/muG6myCULKl1Dr0a2Bii7hqWTWdPF6Chy3BklNsUhpailV8J71Phf332dhbv3YRGm+fLqblLsXaynZtaQ7i/ed/ChbmUwCwwk9N1lITF3iynbm+ZDhcuZLTHXgsLfE+U/pEKV3+r4ropVDGQjbKQSayQSU/zHEPziAzc3uWvFG98Lfx6/lgXfLr1BnpnQ9dH99r8H0lZhWtz0ofTA9g8ha8tEn5HU72SfMPsA5MwQlRyT6VK7ADFlhGc7Kt+8+cRdPumy8PUz1rQ0tCT2cbqYKdoFJzeOphaR4KZM3QpwZhNOkffnqZDXYO61whoh7BWvNnX+cZ7SyHl3WkuX7bRIWmiavb09cJYUW1ZsoMPQcnj50P1WGJltdWxcXIqACDRhedywsNKejxI7xmaVBmkZWWaHjqCycO2AxTec8S7N0qEtRcJ98f4xwug4x7sBth3eOEnhtkvyq41M49jSrBPpE/zjll0V7JYl6mlk0N0jC1e+QeNgwgLWBAyw49+wVMkP6oVO4UUyrJSPwPxdNclQ44/Um/0Pt4/rn30lKUPFGWOKcxI6Rs5fWT2vMl8F3TZJ29dQGL2LebX3AE8x8diBL5+ZOtcCbnDFDN857Q65vlEohqmc6GJMdsORlGerfB2wckKSJSzxVeTTPRZvRMv/80iaFfnX6F13cXrhvs57L2cE+3kDZ7LMqOUF8za/lcGmd9DrpZMfFRIKrRBbByysJSFf0wefxoFDDL0xVH477SUcOcGa7otikKmV";
        Form form = new Form();
        form.param("datoEntrada", datoEntrada);

        Respuesta<MetodoAutenticacionView> retorno = client.post(form, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @Test
    public void reenviarSmsTest() {

        Respuesta<MetodoAutenticacionView> respuesta = new Respuesta<MetodoAutenticacionView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(claveOnlineManager.enviarSMS(Matchers.any(HttpServletRequest.class), Matchers.anyBoolean(),false))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/reenviarSms");
        super.addSleepTime(5000);

        Respuesta<MetodoAutenticacionView> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @Test
    public void reintentarPreguntasTest() {

        Respuesta<MetodoAutenticacionView> respuesta = new Respuesta<MetodoAutenticacionView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(claveOnlineManager.reintentarPreguntas(Matchers.any(HttpServletRequest.class)))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/reintentarPreguntas");
        super.addSleepTime(5000);

        Respuesta<MetodoAutenticacionView> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void confirmarDatosTest() {

        Respuesta<MetodoAutenticacionView> respuesta = new Respuesta<MetodoAutenticacionView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(claveOnlineManager.confirmarDatos(Matchers.any(HttpServletRequest.class), Matchers.anyString()))
                .thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/confirmarDatos");
        super.addSleepTime(5000);

        String datoEntrada = "ISBANCRYPTO_v1.0_IBFbx2cfOxLgEfSg6HupCsVL+ZUHwSn+7sI5mj9ef7x7plkG3Z/aagULX0Z3QpXBuhRd0H13AWFZHm2QXvwy/jNBMqGlpHkktTNWKIysJqhnS6XCOp7PaRJ+k/T3UM4FWdswT+btjf7zDAxfKEjKNRjxRSVqebe7DJgltcn4XbH7hUQTzwCxJuzVOGbTS8I2MyURWPjhXTKRiu1yFThC5Ksl+KRiQXfMrpGXl6hNeSeQqyj5z1eZpFoJB5rex4yT4e3/9lbQnvzua2CPgqaeRbww5azum4WrA658Rm+3eg3+P+p3a/TI0sJcdOlxknHcXUokGrlRYPni3y9RxrKmmA==|1536h4J0AM1JKwxcIpbAiZe4aD3fVK/OBQCixBO8TutrYJ0jFdQzo13hAaErrYxGrsjztR5HajH0r02zj6tZHlnR/9gshz6RlP4TXKUXICL3Z3IWEQeudcI0OV+4frWUV6BCTz8ZMMc7UqBTe+FxIUXdU+3eW7oORnLC8LkXkP15DSvFX58n0qWAPF1UBYa71d1PRQy6AEJWblefp7kZXV/uJBxmrYWBkcP5DkEO0jh06ioTY3x2io8QKEUguX6IAlURb2tGd5LVa90vUCbVHweRoLQZm8PMlUZdD8DJK/tUG0qLky7UafmHTRvgpziCXTPmCmatpWpeGFlJ76gfulZ8RyCUBPLVaPLi4cWN89AnnrP3LHl0e1RU2aDnrYQqtI+1Ws3N+CNd+Ouze2MjdENa1OEpYLLhi/YYAeOL+D7mXdcLsgOuaJo51TYYqjmRZ8e2HHZMIx91qZ4szpDbk/XrENIrqPFkfKNa3Rs1CJMpHMFk41B/qwyCHu6Akh4EYiaO72AHE6IZpG1F6tiOgIZiTxWzh3AcWz6VpZUvZQmX9QonCcZBL7y7t7fBOdlJmw8wV3Zu8dZ50fBtLHulbhrDkE2/zW2hP1oHh2MizXzAAmI8EZnBr8zcNvlRsftkSwZoLt/3zfj5SRMU056eqtm08E65jBakWidcOpW4H1LpbQ9srwZ9guQNaM48zVEsbjnZ7z8tZCmfpKjTN2ZGnOlo0UPMSUCsKKZQg+MC91aoN6BbClv1cA39pVqByOCN3U5c9ijzDQ/muG6myCULKl1Dr0a2Bii7hqWTWdPF6Chy3BklNsUhpailV8J71Phf332dhbv3YRGm+fLqblLsXaynZtaQ7i/ed/ChbmUwCwwk9N1lITF3iynbm+ZDhcuZLTHXgsLfE+U/pEKV3+r4ropVDGQjbKQSayQSU/zHEPziAzc3uWvFG98Lfx6/lgXfLr1BnpnQ9dH99r8H0lZhWtz0ofTA9g8ha8tEn5HU72SfMPsA5MwQlRyT6VK7ADFlhGc7Kt+8+cRdPumy8PUz1rQ0tCT2cbqYKdoFJzeOphaR4KZM3QpwZhNOkffnqZDXYO61whoh7BWvNnX+cZ7SyHl3WkuX7bRIWmiavb09cJYUW1ZsoMPQcnj50P1WGJltdWxcXIqACDRhedywsNKejxI7xmaVBmkZWWaHjqCycO2AxTec8S7N0qEtRcJ98f4xwug4x7sBth3eOEnhtkvyq41M49jSrBPpE/zjll0V7JYl6mlk0N0jC1e+QeNgwgLWBAyw49+wVMkP6oVO4UUyrJSPwPxdNclQ44/Um/0Pt4/rn30lKUPFGWOKcxI6Rs5fWT2vMl8F3TZJ29dQGL2LebX3AE8x8diBL5+ZOtcCbnDFDN857Q65vlEohqmc6GJMdsORlGerfB2wckKSJSzxVeTTPRZvRMv/80iaFfnX6F13cXrhvs57L2cE+3kDZ7LMqOUF8za/lcGmd9DrpZMfFRIKrRBbByysJSFf0wefxoFDDL0xVH477SUcOcGa7otikKmV";
        Form form = new Form();
        form.param("datoEntrada", datoEntrada);

        Respuesta<MetodoAutenticacionView> retorno = client.post(form, Respuesta.class);

        Assert.assertNotNull(retorno);
        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
    }

    @Test
    public void obtenerHashTest() {

        Respuesta<HashView> respuesta = new Respuesta<HashView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();

        Mockito.when(claveOnlineManager.generarHash()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/claveOnline/obtenerHash");
        super.addSleepTime(5000);

        Respuesta<HashView> retorno = client.post(credencialesClaveOnline, Respuesta.class);

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
        return applicationContext.getBean("claveOnlineSEI");
    }

}
