package ar.com.santanderrio.obp.servicios.api.sso;

import ar.com.santanderrio.obp.servicios.api.sso.client.entities.ClientRepresentationModel;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserConsentRepresentationModel;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserRepresentationModel;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SSOAssertions {
    public static final UserRepresentationModel userModel = new UserRepresentationModel();
    public static final UserConsentRepresentationModel consentModel = new UserConsentRepresentationModel();
    public static final ClientRepresentationModel clientModel = new ClientRepresentationModel();
    public static final Cliente cliente = new Cliente();
    public static final ConsentDTO consentDTO = new ConsentDTO();
    //
    public static final UserInfoDTO userInfoDto = UserInfoDTO.builder()
            .withId("ff35bbfe-f192-4ada-9422-2b625bd98ecb")
            .withConsents(Collections.singletonList(consentDTO))
            .build();

    static {
        Map<String, List<String>> userAttributes = new HashMap<String, List<String>>();
        List<String> nup = Collections.singletonList("03007878");
        List<String> pkceTest = Collections.singletonList("0720000788000006013640");
        List<String> cuil = Collections.singletonList("20045068790");
        userAttributes.put("nup", nup);
        userAttributes.put("pkce-test", pkceTest);
        userAttributes.put("cuil", cuil);

        userModel.setAttributes(userAttributes);
        userModel.setId("ff35bbfe-f192-4ada-9422-2b625bd98ecb");
        userModel.setUsername("4506879");

        consentModel.setCreatedDate(new Date(1677865606520L));
        consentModel.setClientId("pkce-test");

        Map<String, String> clientAttributes = new HashMap<String, String>();
        clientAttributes.put("client_type", "wallet");
        clientModel.setAttributes(clientAttributes);
        clientModel.setId("33a9b634-87be-482f-988b-dfb273912b34");
        clientModel.setName("wallet-1");
        clientModel.setClientId("pkce-test");

        cliente.setNup("03007878");

        consentDTO.setClientName("wallet-1");
        consentDTO.setClientId("pkce-test");
        consentDTO.setCreatedDate(new Date(1677865606520L));
        consentDTO.setConsentedAccounts(pkceTest);
    }
}
