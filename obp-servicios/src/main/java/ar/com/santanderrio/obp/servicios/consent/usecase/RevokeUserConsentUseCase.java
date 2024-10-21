package ar.com.santanderrio.obp.servicios.consent.usecase;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.sso.consent.ConsentApi;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionConsentSSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RevokeUserConsentUseCase {
    @Autowired
    ConsentApi consentApi;

    @Autowired
    SesionConsentSSO consentSSO;

    public void execute(String userId, String consentedClientId) throws BusinessException {
        try {
            consentApi.revokeConsentByUserId(userId, consentedClientId);
        } catch (ApiException exception) {
            throw new BusinessException(exception, "there was an error executing /users/consent revoke");
        } finally {
            consentSSO.getUserConsents().clear();
        }
    }
}
