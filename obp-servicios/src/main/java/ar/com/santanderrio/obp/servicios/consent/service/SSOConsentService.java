package ar.com.santanderrio.obp.servicios.consent.service;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;

public interface SSOConsentService {
    Respuesta<UserInfoDTO> getUserConsents(final Cliente cliente);
    Respuesta<Void> revokeUserConsent(final String userId, final String consentedClientId);
}
