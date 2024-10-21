package ar.com.santanderrio.obp.servicios.consent.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.api.sso.SSOAssertions;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionConsentSSO;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;
import ar.com.santanderrio.obp.servicios.consent.sei.view.UserConsentsResponse;
import ar.com.santanderrio.obp.servicios.consent.service.SSOConsentServiceImpl;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SSOConsentManagerTests {
    private static final String CONSENTED_CLIENT_ID = "pkce-test";
    private static final String USER_ID = "ff35bbfe-f192-4ada-9422-2b625bd98ecb";

    @InjectMocks
    SSOConsentManagerImpl ssoConsentManager;

    @Spy
    SesionCliente sesionCliente;

    @Spy
    SesionConsentSSO sesionConsentSSO;

    @Mock
    SSOConsentServiceImpl ssoConsentService;

    @Spy
    @InjectMocks
    RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    MensajeBO mensajeBO;

    @Test
    public void getUserConsents_ok() {
        //setup
        Respuesta<UserInfoDTO> userInfoResponse =
                respuestaFactory.crearRespuestaOk(SSOAssertions.userInfoDto);
        when(ssoConsentService.getUserConsents(any(Cliente.class)))
                .thenReturn(userInfoResponse);

        //execution
        Respuesta<UserConsentsResponse> response = ssoConsentManager.getUserConsents();
        Assert.assertNotNull(response);
        Assert.assertEquals(EstadoRespuesta.OK, response.getEstadoRespuesta());
        Assert.assertNotNull(response.getRespuesta());
    }

    @Test
    public void revokeUserConsent() {
        //setup
        Respuesta<Void> respuesta = respuestaFactory.crearRespuestaOk(Void.class);
        when(ssoConsentService.revokeUserConsent(USER_ID, CONSENTED_CLIENT_ID))
                .thenReturn(respuesta);
        sesionConsentSSO.setUserInfo(SSOAssertions.userModel);

        //execution
        Respuesta<Void> response = ssoConsentManager.revokeUserConsent(CONSENTED_CLIENT_ID);
        Assert.assertNotNull(response);
        Assert.assertEquals(EstadoRespuesta.OK, response.getEstadoRespuesta());
    }
}
