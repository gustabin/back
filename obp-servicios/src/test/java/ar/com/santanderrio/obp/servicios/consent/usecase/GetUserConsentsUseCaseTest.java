package ar.com.santanderrio.obp.servicios.consent.usecase;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.sso.SSOAssertions;
import ar.com.santanderrio.obp.servicios.api.sso.consent.ConsentApi;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserRepresentationModel;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionConsentSSO;
import ar.com.santanderrio.obp.servicios.consent.cache.SSOClientsService;
import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class GetUserConsentsUseCaseTest {

    @InjectMocks
    GetUserConsentsUseCase getUserConsentsUseCase;

    @Mock
    ConsentApi consentApi;

    @Mock
    SSOClientsService ssoClientsService;

    @Spy
    SesionConsentSSO sesionConsentSSO = Mockito.spy(new SesionConsentSSO());

    @Test
    public void execute_ok() throws BusinessException {
        //setup
        mockGetUserByNup();
        mockGetConsentsByUser();
        mockGetClientName();

        //execution
        UserInfoDTO userInfoDTO = getUserConsentsUseCase.execute(SSOAssertions.cliente);
        Assert.assertEquals(SSOAssertions.userModel.getId(), userInfoDTO.getUserId());
        Assert.assertNotNull(userInfoDTO);
        Assert.assertNotNull(userInfoDTO.getConsents());
        Assert.assertFalse(userInfoDTO.getConsents().isEmpty());
        Assert.assertEquals(SSOAssertions.userModel.getId(), userInfoDTO.getUserId());
        ConsentDTO consentDTO = userInfoDTO.getConsents().get(0);

        Assert.assertEquals(SSOAssertions.consentModel.getClientId(), consentDTO.getClientId());
        Assert.assertEquals(SSOAssertions.consentModel.getCreatedDate(), consentDTO.getCreatedDate());
        Assert.assertEquals(SSOAssertions.clientModel.getClientId(), consentDTO.getClientId());
        Assert.assertEquals(SSOAssertions.clientModel.getName(), consentDTO.getClientName());
        List<String> expectedConsentedAccounts = SSOAssertions.userModel.getAttributes().get("pkce-test");
        Assert.assertNotNull(consentDTO.getConsentedAccounts());
        Assert.assertFalse(consentDTO.getConsentedAccounts().isEmpty());
        Assert.assertEquals(expectedConsentedAccounts.get(0), consentDTO.getConsentedAccounts().get(0));
    }

    @Test
    public void execute_notMatchingUser() {
        //setup
        mockGetUserByNup_notFound();
        try {
            getUserConsentsUseCase.execute(SSOAssertions.cliente);
        } catch (BusinessException exception) {
            Assert.assertEquals("not matching user found for nup" + SSOAssertions.cliente.getNup(), exception.getMessage());
        }
    }

    @Test
    public void execute_apiFailure() {
        //setup
        mockGetUserByNup_apiFailure();
        try {
            getUserConsentsUseCase.execute(SSOAssertions.cliente);
        } catch (BusinessException exception) {
            Assert.assertEquals("there was an error executing /users requests", exception.getMessage());
            ApiException cause = (ApiException) exception.getCause();
            Assert.assertEquals("ERROR", cause.getErrorResponse().getCode());
            Assert.assertEquals("NO RESPONSE BODY FOUND", cause.getErrorResponse().getMessage());
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cause.getHttpStatus());
        }
    }

    private void mockGetUserByNup() {
        Mockito.when(consentApi.getUserDataByNup(Mockito.anyString()))
                .thenReturn(Collections.singletonList(SSOAssertions.userModel));
    }

    private void mockGetUserByNup_notFound() {
        UserRepresentationModel userModel = new UserRepresentationModel();
        Map<String, List<String>> userAttributes = new HashMap<String, List<String>>();
        userAttributes.put("nup", Collections.singletonList("12345678"));
        userModel.setAttributes(userAttributes);
        userModel.setId("ff35bbfe-f192-4ada-9422-2b625bd98ecb");
        userModel.setUsername("4506879");
        UserRepresentationModel userModel2 = new UserRepresentationModel();
        userModel.setId("ff35bbfe-f192-4ada-9422-2b625bd98ecb");
        userModel.setUsername("4506879");
        Mockito.when(consentApi.getUserDataByNup(Mockito.anyString()))
                .thenReturn(Arrays.asList(userModel, userModel2));
    }

    private void mockGetUserByNup_apiFailure() {
        ApiException apiException = new ApiException(ErrorResponse.emptyErrorResponse(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(consentApi.getUserDataByNup(Mockito.anyString()))
                .thenThrow(apiException);
    }

    private void mockGetConsentsByUser() {
        Mockito.when(consentApi.getConsentsByUserId(Mockito.anyString()))
                .thenReturn(Collections.singletonList(SSOAssertions.consentModel));
    }

    private void mockGetClientName() {
        Mockito.when(ssoClientsService.getSSOClient(Mockito.anyString()))
                .thenReturn(SSOAssertions.clientModel);
    }
}
