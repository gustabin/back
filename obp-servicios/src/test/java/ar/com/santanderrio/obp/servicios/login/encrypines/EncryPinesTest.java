/**
 * 
 */
package ar.com.santanderrio.obp.servicios.login.encrypines;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clave.online.manager.impl.ClaveOnlineManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * Probar la logica requerida para agregar encripcion de pines a los flujos de
 * login y cambio de clave.
 * 
 * @author sergio.e.goldentair
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EncryPinesTest {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClaveOnlineManagerImpl.class);

    /** Logica a testear. */
    @InjectMocks
    private EncryPinesUtil encryPinesUtil;

    /** Obtener las credenciales de la base de seguridad. */
    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    /** validar si hay permisos operatorios para realizar la tarea. */
    @Mock
    private ModuloPermisoBO moduloPermisoBO;

    /**
     * Entrada en la base de seguridad con el dato 20086.
     */
    private static final String ENC_3DES = "20086";
    /**
     * Entrada en la base de seguridad con el dato 20088 clave privada.
     */
    private static final String ENC_PRIV = "20088";

    @Test
    public void obtenerCadena() throws IllegalAccessException, SQLException {
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);

        Credential credential = new Credential();
        credential.setUsuario("encpines_priv");
        credential.setPassword("A1A2A3A4B1B2B3B4C1C2C3C4D1D2D3D4E1E2E3E4F1F2F3F4");
        String dato = "hola mundo";
        FieldUtils.writeDeclaredField(encryPinesUtil, "encpines3des", ENC_3DES, true);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN))
                .thenReturn(moduloPermiso);
        Mockito.when(credentialSecurityFactory.getCredentialById(ENC_3DES)).thenReturn(credential);
        String datoEnc = encryPinesUtil.obtenerCadena3Des(dato);
        assertThat(datoEnc, is("0024+8BXs4RzKDbrEQ4mq5oeEQ=="));
    }

    @Test
    public void cuandoModuloPermisoOcultoobtenerCadenaOriginal() throws IllegalAccessException, SQLException {
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.OCULTAR);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN))
                .thenReturn(moduloPermiso);
        String dato = "hola mundo";
        String datoEnc = encryPinesUtil.obtenerCadena3Des(dato);
        assertThat(datoEnc, is(dato));
    }

    @Test
    public void cuandoNoEstaClaveSegObtenerCadenaOriginal() throws IllegalAccessException, SQLException {
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN))
                .thenReturn(moduloPermiso);
        String dato = "hola mundo";
        String datoEnc = encryPinesUtil.obtenerCadena3Des(dato);
        assertThat(datoEnc, is(dato));
    }

    @Test
    public void cuandoDatoNullObtenerCadenaOriginal() throws IllegalAccessException, SQLException {
        Credential credential = new Credential();
        credential.setUsuario("encpines");
        credential.setPassword("A1A2A3A4B1B2B3B4C1C2C3C4D1D2D3D4E1E2E3E4F1F2F3F4");
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        FieldUtils.writeDeclaredField(encryPinesUtil, "encpines3des", ENC_3DES, true);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN))
                .thenReturn(moduloPermiso);
        Mockito.when(credentialSecurityFactory.getCredentialById(ENC_3DES)).thenReturn(credential);

        String dato = null;
        String datoEnc = encryPinesUtil.obtenerCadena3Des(dato);
        assertThat(datoEnc, is(dato));
    }

    /**
     * Cargar clave privada para hacer la prueba.
     * 
     * @throws IllegalAccessException
     * @throws SQLException
     */
    @Test
    public void dadoEncPublicaAplicarPrivada() throws IllegalAccessException, SQLException {
        String encriptado = "ISBANCRYPTO_v1.0_IBFbx2cfOxLgEfSg6HupCsVL+ZUHwSn+7sI5mj9ef7x7plkG3Z/aagULX0Z3QpXBuhRd0H13AWFZHm2QXvwy/jNBMqGlpHkktTNWKIysJqhnS6XCOp7PaRJ+k/T3UM4FWdswT+btjf7zDAxfKEjKNRjxRSVqebe7DJgltcn4XbH7hUQTzwCxJuzVOGbTS8I2MyURWPjhXTKRiu1yFThC5Ksl+KRiQXfMrpGXl6hNeSeQqyj5z1eZpFoJB5rex4yT4e3/9lbQnvzua2CPgqaeRbww5azum4WrA658Rm+3eg3+P+p3a/TI0sJcdOlxknHcXUokGrlRYPni3y9RxrKmmA==|1536h4J0AM1JKwxcIpbAiZe4aD3fVK/OBQCixBO8TutrYJ0jFdQzo13hAaErrYxGrsjztR5HajH0r02zj6tZHlnR/9gshz6RlP4TXKUXICL3Z3IWEQeudcI0OV+4frWUV6BCTz8ZMMc7UqBTe+FxIUXdU+3eW7oORnLC8LkXkP15DSvFX58n0qWAPF1UBYa71d1PRQy6AEJWblefp7kZXV/uJBxmrYWBkcP5DkEO0jh06ioTY3x2io8QKEUguX6IAlURb2tGd5LVa90vUCbVHweRoLQZm8PMlUZdD8DJK/tUG0qLky7UafmHTRvgpziCXTPmCmatpWpeGFlJ76gfulZ8RyCUBPLVaPLi4cWN89AnnrP3LHl0e1RU2aDnrYQqtI+1Ws3N+CNd+Ouze2MjdENa1OEpYLLhi/YYAeOL+D7mXdcLsgOuaJo51TYYqjmRZ8e2HHZMIx91qZ4szpDbk/XrENIrqPFkfKNa3Rs1CJMpHMFk41B/qwyCHu6Akh4EYiaO72AHE6IZpG1F6tiOgIZiTxWzh3AcWz6VpZUvZQmX9QonCcZBL7y7t7fBOdlJmw8wV3Zu8dZ50fBtLHulbhrDkE2/zW2hP1oHh2MizXzAAmI8EZnBr8zcNvlRsftkSwZoLt/3zfj5SRMU056eqtm08E65jBakWidcOpW4H1LpbQ9srwZ9guQNaM48zVEsbjnZ7z8tZCmfpKjTN2ZGnOlo0UPMSUCsKKZQg+MC91aoN6BbClv1cA39pVqByOCN3U5c9ijzDQ/muG6myCULKl1Dr0a2Bii7hqWTWdPF6Chy3BklNsUhpailV8J71Phf332dhbv3YRGm+fLqblLsXaynZtaQ7i/ed/ChbmUwCwwk9N1lITF3iynbm+ZDhcuZLTHXgsLfE+U/pEKV3+r4ropVDGQjbKQSayQSU/zHEPziAzc3uWvFG98Lfx6/lgXfLr1BnpnQ9dH99r8H0lZhWtz0ofTA9g8ha8tEn5HU72SfMPsA5MwQlRyT6VK7ADFlhGc7Kt+8+cRdPumy8PUz1rQ0tCT2cbqYKdoFJzeOphaR4KZM3QpwZhNOkffnqZDXYO61whoh7BWvNnX+cZ7SyHl3WkuX7bRIWmiavb09cJYUW1ZsoMPQcnj50P1WGJltdWxcXIqACDRhedywsNKejxI7xmaVBmkZWWaHjqCycO2AxTec8S7N0qEtRcJ98f4xwug4x7sBth3eOEnhtkvyq41M49jSrBPpE/zjll0V7JYl6mlk0N0jC1e+QeNgwgLWBAyw49+wVMkP6oVO4UUyrJSPwPxdNclQ44/Um/0Pt4/rn30lKUPFGWOKcxI6Rs5fWT2vMl8F3TZJ29dQGL2LebX3AE8x8diBL5+ZOtcCbnDFDN857Q65vlEohqmc6GJMdsORlGerfB2wckKSJSzxVeTTPRZvRMv/80iaFfnX6F13cXrhvs57L2cE+3kDZ7LMqOUF8za/lcGmd9DrpZMfFRIKrRBbByysJSFf0wefxoFDDL0xVH477SUcOcGa7otikKmV";
        String soloCrypto = "{\"dniOri\":\"21587183\",\"dni\":\"330A330A330A271A349A297A57A200A349A57A102A\",\"clave\":\"330A330A330A330A282A297A271A349A\",\"usuario\":\"348A304A37A304A282A297A271A349A315A315A315A315A315A315A315A315A315A315A315A315A\",\"usuarioVacio\":\"315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A\",\"claveVacia\":\"330A330A330A330A330A330A330A330A\",\"prefijo\":\"143A\",\"sufijo\":\"A\",\"dispositivo\":\"desktop\",\"navegador\":\"Chrome 68\",\"devicePrint\":\"version=3.0.0.0_5&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/68.0.3440.106 safari/537.36|5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36|Win32&pm_fpsc=24|1280|720|680&pm_fpsw=&pm_fptz=-3&pm_fpln=lang=en-US|syslang=|userlang=&pm_fpjv=0&pm_fpco=1&pm_fpasw=internal-pdf-viewer|mhjfbmdgcfjbbpaeojofohoefgiehjai|internal-nacl-plugin&pm_fpan=Netscape&pm_fpacn=Mozilla&pm_fpol=true&pm_fposp=&pm_fpup=&pm_fpsaw=1280&pm_fpspd=24&pm_fpsbd=&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpsfse=&pm_fpsui=&pm_os=Windows&pm_brmjv=68&pm_br=Chrome&pm_inpt=&pm_expt=\",\"tipoTeclado\":\"F\",\"fechaDeNacimiento\":\"\"}";
        Credential credential = new Credential();
        credential.setUsuario("encpines");
        credential.setPassword(
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCyUPKw6erLd9Rnkvht7aEBL89X6H7qkwLrA/izJbWXEOqVQWwauTpToe5CsOaS6NoK96/4bukF8sF1cc2TnaVoZDLJaJawIA8Yy0n1RAWpghzjwsQfnptud7msi1e+A31zp1SIel5Ewow78c5KYP98vGU8rGBHFmb+b6ikaqyjUh63hS8EAztZNVKrPa3yXXNvhkcpP0KNKaEWvigE+T6ylipvFEtsMlclaCTFodytWpVW219Lotz90PA7zrTMcL4PLNT+sUCVpUv86e+gNx/+UZpRDGzXmr3HHwF+mqc3QrBxjvqRK3f0fMUO2EOaI8OPD8lwBJRp0eRV08J3aRXNAgMBAAECggEADQnONRFlkasZZNliQOd3NlcAmjTt0skdFYIc1TyUoo8iF/fIp8uTcDoO6eF1eph8GQEzZL73Wu/9JVFE7t5AEfoBnKiBHC+76twydDSdFV/mLOE45fb69oDamrs1lzc6HRLHI5OPAQgYqOltZBLjm4NLA7y27Xu93Czd7+e859QiDAswV3NBh0XIuAucl0hFWi9GtiOmEAosnfZ5648P0hJId4eW2QfEQDeOCl7Z2nYs/1Hvuov8o0DGIF0R9HDwmu25TkXccq7/4UbvD5r5fAMqkf6dWDpnq+5zgSa2OY9KRzhkjefFxyjUHisi+87zp2qunP1kaR3D75FYmKPIIQKBgQDjoL64z1zq5Y1Z/5ByFI/9fESYtVP5SGwmCf/aaTKKLONc4DhaEq1yZIBls9RwRHi5cp3TwArBOr5krlARtKqe7OCCaABxFRo41jaGuwayW32/x/QAzi0sIbG1bXxaa1vTLHicmbbDBifCt5DpkPw41eyyJ3Qp1Af+a6ayTlzovwKBgQDIir4bddixVOvs0ejRuMnLS3oML9b5lDhRKMj+b0a9AmF3IXJ3g3ntTU9D4ay3jurc4U5DgZDi9hGd3oaiKEl8GI7EfDzYcq7WctZvz7YjwOxKO60vaSyc2NANQ25A0LxZShK/EpO1aliuM2swouhRiqRDv5bAkWYMhXW1cy14cwKBgE/f9yz4rt1KYdiD4lb1ujUz5wpDDs15yWFrvtkQcwijicDseTm6NhzfAWMuwEwI6ugAPTuxttRpXagMttIPuJN+AaYhAo6r3gC1wnpypH3vtueWLZwevSEwTaChY7KC27z1bPlewxAaV9du/K3JD4iOILm/OHb5DTkM43jMILQXAoGBAK0f4YiRTvWgC2BRwn0y2rpvOTjGH/8r7wZZgFLuu5G2OW//5vK4BQ1QnWpLq7B3Z9gxhUAP3YWhyomQLohU4UOJa87CNkDhmIA3eVnjZiktho97P0JbuJDbN5kLqmW4DbHDmbVsEyodWdthNlsxd//VAYQfJMSvgPq0SG7RtOJzAoGAY4kHmZ1kSYjh5BCYioP9wiXy0ivPjtOqiFHgQOzfHIJapDgUyHAChURZ7+0a0V9fQoqBDrh+V6BtDNOC89WpQP6Kq+OrmmrIMxa4I2mYs0yHpmGhTi1VPdelHcvlFB44h4vmdXU8IaQutQZ9COlXibleGygFH5zR7B3UA0LNZP0=");

        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        FieldUtils.writeDeclaredField(encryPinesUtil, "encpinesPriv", ENC_PRIV, true);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN))
                .thenReturn(moduloPermiso);
        Mockito.when(credentialSecurityFactory.getCredentialById(ENC_PRIV)).thenReturn(credential);
        String datoEnc = encryPinesUtil.obtenerSoloCrypto(encriptado);
        LOGGER.info("Entrada {} \r\nSalida {}\r\n", encriptado, datoEnc);
        assertThat(datoEnc, is(equalTo(soloCrypto)));
    }

    @Test
    public void dadoEncObtenerSinUrlEncode() throws UnsupportedEncodingException {
        String encriptadoUrlEncoded = "ISBANCRYPTO_v1.0_mhP2r5IlV5K5Udx0dRfPDRlcN1OwlYs3qfZeQ5SjAMM4N0hbmf1mVrZzBFgH%2BO2%2Bpsk3wo54xZ8y53xyOUNURNx0ofhEfEWarsnUDvSqMZjAtJKCVEtAgVkKxrOBorbkFyKDU%2B8EsJaVr1P4yjKI397sDuu9F6%2FDsERbEbE2KU0Cdgsai0wZTtuFsJScGBcB3Tbz8r891I2%2B4S6VkxZT8%2FwIlFD4jeOCSt1FuNlbtcPw42bVCTdPpGJk5ptMcybF58FVf%2FQAgMStRJ55DXfmdnpUtzDR8RB%2B8HqSSWyp35%2FaPDJFgZvQadrS3L7B8CNfE6gr8Z2UtXATfcqfBRcCww%3D%3D%7C1560EzESLAoQihgDs9TiL%2BCd4PE55uj5BQ9uor9fXumhN3vqzPYTIqpRVtWi1ZQh67m5UCPqZt3ybMDfwfufHIXciir6TaUDoZ0ZUwbfbiBf6ewiFbeD1%2BPj3Zfgd6UdmaKt2zYSSPzFv9GepnRv3BcpCQuehnTRlQmbjrmv%2BpBdlX1PJ9HOjvMv1MFvuxDdWc6WLeyRIimGigcssq4DaIQOwQlpJyjlTYD1SvN1I5SMj5B6pLGPb1tzKkNOmjnEcvm5uB6a%2BnXVQIXVMt8WWyZ6r9ZDnHgdYb5t7o6VhXz6YIj73WM6qdp9318kDbB3GxzZmWlcJelYMFzFgLB%2FqRMeqX5TlS%2BXCch%2Fi3mp2RYlKhXd1oUTVdfcTJbbHGz%2BoGwp3oz4zjONE7lVK%2Fos7KN8S%2F9OQ3SodXhBTL963c4Wicbj9x6Szgi%2FwtSq5a996eEnpVpJ6cXpzjX989dj7FYX7Vhlysa3kPkpLxHT58GyXZZrHG9RhI6YsIFbmOXuDeLNlyKcJqm1cPu7JHX62sFjf6dafdZMZFzzOSr5BqTmpcuKUJhWkY%2B2gGdMztxko56aUXsrMc71bmcIhY3liuIcza01va2E2AA%2FAL4%2B8OMkoJoPrLxHHx1%2BO2sbo6JuQr9groeZpYuG1M9KZTWkyBGUILJVGcz3U9Z3V%2BYHsW%2F%2BeVyZ6z3pDgG5GatfBDzViIS%2BSPdasyLM5jLRU1o%2BpWoCb%2BnyY0SCZhxvLP4CGB449%2BPdH0UIXqjSFaaf%2BBdEnMDjJudar%2FrHxbzmqWAPpj8xN0ixPK63bSWhnSiKZqkB6YL3pr5D2ZQ4HhMNDV4W%2FODJbXny0Xxm%2BrIQILoHE7qUyf2H%2B6PP%2BtXdpUuYFkXFaLxS%2B5FzTQfBd%2Bu1OhC%2BXKqbRPdNPGLgyoElbQuxzpoLc0ifCa9a9cHtdG0lHQuoyEmmRjiLzPF4k8WkWEm%2BsCgVKuphyxd%2Fj41l5AkadYtV8atj%2BGjiCpdgAvIuYo217h2lSppuDdw9jUYCQJDMzEfKA2WgMWNQO%2By9zW1mZOL%2FaWQWR8DiGoL8Hp4Qe8ZbVQiABoOESHRhVJP%2BEM%2Bztl0Zo7M1cI%2FntriR9%2FLZqqamq%2F1ZWlw%2Fcuv%2BVIzNlTp1k%2BUxzUrqAd%2FXyHD1CfIxs8TY4P1pibzcjBHcekQzrx5UEMU0Qx33oLsPC4D3t3SE0YZzoRYCrAwZj5rsY3hDVa8Wglf8IHHpDXpH%2BLZDAOG8o8vqksTM3wmG72VlkCBUnZmvXrQcqVtBd46oPIYTiZEyWZ38lbVajb7eO9Sg%2FFpLyIVrLrWb58IeAcL3CigCxgLbn0FYK4LmV1P7wlD9Z22w4%2B1W3HGHGZaOm7tzogrBgkNBn69BdZXaeqt0LqOGefHe9rkkoeM9YapOgNV%2FP9877d7BPZoFtKAw84tT5hxIZ8cI0aXB79hBNjvDOfmE41lPcM3VGpD0qANPbKrO3gwzXywz4vuEP3zUSSRHcPM2jkC7ICJ8Ype6CZnGBRQ1VOnpucUfh66gIFSt8XoJcuELBWCI34h1D1mjVjrv7bT3kQ%3D%3D";
        String encriptadoOK = "ISBANCRYPTO_v1.0_mhP2r5IlV5K5Udx0dRfPDRlcN1OwlYs3qfZeQ5SjAMM4N0hbmf1mVrZzBFgH+O2+psk3wo54xZ8y53xyOUNURNx0ofhEfEWarsnUDvSqMZjAtJKCVEtAgVkKxrOBorbkFyKDU+8EsJaVr1P4yjKI397sDuu9F6/DsERbEbE2KU0Cdgsai0wZTtuFsJScGBcB3Tbz8r891I2+4S6VkxZT8/wIlFD4jeOCSt1FuNlbtcPw42bVCTdPpGJk5ptMcybF58FVf/QAgMStRJ55DXfmdnpUtzDR8RB+8HqSSWyp35/aPDJFgZvQadrS3L7B8CNfE6gr8Z2UtXATfcqfBRcCww==|1560EzESLAoQihgDs9TiL+Cd4PE55uj5BQ9uor9fXumhN3vqzPYTIqpRVtWi1ZQh67m5UCPqZt3ybMDfwfufHIXciir6TaUDoZ0ZUwbfbiBf6ewiFbeD1+Pj3Zfgd6UdmaKt2zYSSPzFv9GepnRv3BcpCQuehnTRlQmbjrmv+pBdlX1PJ9HOjvMv1MFvuxDdWc6WLeyRIimGigcssq4DaIQOwQlpJyjlTYD1SvN1I5SMj5B6pLGPb1tzKkNOmjnEcvm5uB6a+nXVQIXVMt8WWyZ6r9ZDnHgdYb5t7o6VhXz6YIj73WM6qdp9318kDbB3GxzZmWlcJelYMFzFgLB/qRMeqX5TlS+XCch/i3mp2RYlKhXd1oUTVdfcTJbbHGz+oGwp3oz4zjONE7lVK/os7KN8S/9OQ3SodXhBTL963c4Wicbj9x6Szgi/wtSq5a996eEnpVpJ6cXpzjX989dj7FYX7Vhlysa3kPkpLxHT58GyXZZrHG9RhI6YsIFbmOXuDeLNlyKcJqm1cPu7JHX62sFjf6dafdZMZFzzOSr5BqTmpcuKUJhWkY+2gGdMztxko56aUXsrMc71bmcIhY3liuIcza01va2E2AA/AL4+8OMkoJoPrLxHHx1+O2sbo6JuQr9groeZpYuG1M9KZTWkyBGUILJVGcz3U9Z3V+YHsW/+eVyZ6z3pDgG5GatfBDzViIS+SPdasyLM5jLRU1o+pWoCb+nyY0SCZhxvLP4CGB449+PdH0UIXqjSFaaf+BdEnMDjJudar/rHxbzmqWAPpj8xN0ixPK63bSWhnSiKZqkB6YL3pr5D2ZQ4HhMNDV4W/ODJbXny0Xxm+rIQILoHE7qUyf2H+6PP+tXdpUuYFkXFaLxS+5FzTQfBd+u1OhC+XKqbRPdNPGLgyoElbQuxzpoLc0ifCa9a9cHtdG0lHQuoyEmmRjiLzPF4k8WkWEm+sCgVKuphyxd/j41l5AkadYtV8atj+GjiCpdgAvIuYo217h2lSppuDdw9jUYCQJDMzEfKA2WgMWNQO+y9zW1mZOL/aWQWR8DiGoL8Hp4Qe8ZbVQiABoOESHRhVJP+EM+ztl0Zo7M1cI/ntriR9/LZqqamq/1ZWlw/cuv+VIzNlTp1k+UxzUrqAd/XyHD1CfIxs8TY4P1pibzcjBHcekQzrx5UEMU0Qx33oLsPC4D3t3SE0YZzoRYCrAwZj5rsY3hDVa8Wglf8IHHpDXpH+LZDAOG8o8vqksTM3wmG72VlkCBUnZmvXrQcqVtBd46oPIYTiZEyWZ38lbVajb7eO9Sg/FpLyIVrLrWb58IeAcL3CigCxgLbn0FYK4LmV1P7wlD9Z22w4+1W3HGHGZaOm7tzogrBgkNBn69BdZXaeqt0LqOGefHe9rkkoeM9YapOgNV/P9877d7BPZoFtKAw84tT5hxIZ8cI0aXB79hBNjvDOfmE41lPcM3VGpD0qANPbKrO3gwzXywz4vuEP3zUSSRHcPM2jkC7ICJ8Ype6CZnGBRQ1VOnpucUfh66gIFSt8XoJcuELBWCI34h1D1mjVjrv7bT3kQ==";
        String decodeado = URLDecoder.decode(encriptadoUrlEncoded, CharEncoding.UTF_8);
        assertThat(encriptadoOK, is(equalTo(decodeado)));
    }

}
