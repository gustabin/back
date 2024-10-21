package ar.com.santanderrio.obp.servicios.consent.usecase;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.sso.client.entities.ClientRepresentationModel;
import ar.com.santanderrio.obp.servicios.api.sso.consent.ConsentApi;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserConsentRepresentationModel;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserRepresentationModel;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionConsentSSO;
import ar.com.santanderrio.obp.servicios.consent.cache.SSOClientsService;
import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;
import ar.com.santanderrio.obp.servicios.consent.exceptions.UserNotFoundException;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GetUserConsentsUseCase {
    @Autowired
    ConsentApi consentApi;

    @Autowired
    SSOClientsService ssoClientsService;

    @Autowired
    SesionConsentSSO sesionConsentSSO;

    public UserInfoDTO execute(Cliente cliente) throws BusinessException {
        try {
            UserRepresentationModel userInfo = getUserByNup(cliente.getNup());
            List<ConsentDTO> userConsents = getUserConsentsByUserId(userInfo);
            return UserInfoDTO.builder()
                    .withId(userInfo.getId())
                    .withConsents(userConsents)
                    .build();
        } catch (ApiException exception) {
            throw new BusinessException(exception, "there was an error executing /users requests");
        }
    }

    private List<ConsentDTO> getUserConsentsByUserId(UserRepresentationModel userInfo) {
        List<UserConsentRepresentationModel> usersConsentsResponse = consentApi.getConsentsByUserId(userInfo.getId());
        sesionConsentSSO.setUserConsents(mapUserConsents(usersConsentsResponse, userInfo));
        return sesionConsentSSO.getUserConsents();
    }

    private UserRepresentationModel getUserByNup(String nup) throws BusinessException {
        if (sesionConsentSSO.getUserInfo() == null) {
            List<UserRepresentationModel> usersResponse = consentApi.getUserDataByNup(nup);
            sesionConsentSSO.setUserInfo(getFirstUserByNup(usersResponse, nup));
        }
        return sesionConsentSSO.getUserInfo();
    }

    private UserRepresentationModel getFirstUserByNup(List<UserRepresentationModel> usersResponse, String nup)
            throws BusinessException {
        Predicate nupPredicate = getUserNupPredicate(nup);
        for(UserRepresentationModel user: usersResponse) {
            if (nupPredicate.evaluate(user)){
                return user;
            }
        }
        throw new UserNotFoundException("not matching user found for nup" + nup);
    }

    private List<ConsentDTO> mapUserConsents(List<UserConsentRepresentationModel> usersConsentsResponse,
            UserRepresentationModel user) {
        final List<ConsentDTO> consents = new ArrayList<ConsentDTO>();
        for(UserConsentRepresentationModel consentEntity: usersConsentsResponse) {
            List<String> consentedAccounts = user.getAttributes().get(consentEntity.getClientId());
            final ConsentDTO consent = new ConsentDTO();
            consent.setClientId(consentEntity.getClientId());
            consent.setCreatedDate(consentEntity.getCreatedDate());
            consent.setConsentedAccounts(consentedAccounts);
            mapClientName(consent);
            consents.add(consent);
        }
        return consents;
    }

    private void mapClientName(ConsentDTO consentDTO) {
        ClientRepresentationModel clientInfo = ssoClientsService.getSSOClient(consentDTO.getClientId());
        if (clientInfo != null) {
            consentDTO.setClientName(clientInfo.getName());
        }
    }

    private Predicate getUserNupPredicate(final String nup) {
        return new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                Map<String, List<String>> userAttributes = ((UserRepresentationModel) object).getAttributes();
                if (userAttributes != null && userAttributes.get("nup") != null && !userAttributes.get("nup").isEmpty()) {
                    return nup.equals(userAttributes.get("nup").get(0));
                }
                return false;
            }
        };
    }
}
