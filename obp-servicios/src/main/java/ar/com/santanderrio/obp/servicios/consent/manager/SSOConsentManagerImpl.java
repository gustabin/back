package ar.com.santanderrio.obp.servicios.consent.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionConsentSSO;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;
import ar.com.santanderrio.obp.servicios.consent.sei.view.UserConsentsResponse;
import ar.com.santanderrio.obp.servicios.consent.service.SSOConsentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SSOConsentManagerImpl implements SSOConsentManager {
    @Autowired
    SesionCliente sesionCliente;

    @Autowired
    SesionConsentSSO sesionConsentSSO;

    @Autowired
    SSOConsentServiceImpl ssoConsentService;

    @Override
    public Respuesta<UserConsentsResponse> getUserConsents() {
        Respuesta<UserInfoDTO> userInfoResponse = ssoConsentService.getUserConsents(sesionCliente.getCliente());
        Respuesta<UserConsentsResponse> response = Respuesta.copy(userInfoResponse);
        if (EstadoRespuesta.OK.equals(response.getEstadoRespuesta()) || EstadoRespuesta.WARNING.equals(response.getEstadoRespuesta())
                && userInfoResponse.getRespuesta() != null) {
            response.setRespuesta(new UserConsentsResponse(userInfoResponse.getRespuesta().getConsents()));
        }
        return response;
    }

    @Override
    public Respuesta<Void> revokeUserConsent(String consentedClientId) {
        return ssoConsentService.revokeUserConsent(sesionConsentSSO.getUserInfo().getId(), consentedClientId);
    }
}
