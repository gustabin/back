package ar.com.santanderrio.obp.servicios.consent.usecase;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.sso.SSOAssertions;
import ar.com.santanderrio.obp.servicios.api.sso.consent.ConsentApi;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionConsentSSO;
import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RevokeUserConsentUseCaseTest {

    @InjectMocks
    RevokeUserConsentUseCase revokeUserConsentUseCase;

    @Mock
    ConsentApi consentApi;

    @Spy
    SesionConsentSSO consentSSO = new SesionConsentSSO();

    @Before
    public void setup() {
        consentSSO.setUserConsents(new ArrayList<ConsentDTO>());
    }

    @Test
    public void execute_ok() throws BusinessException {
        revokeUserConsentUseCase.execute(SSOAssertions.userModel.getId(),
                SSOAssertions.consentModel.getClientId());

        Assert.assertTrue(consentSSO.getUserConsents().isEmpty());
    }

    @Test
    public void execute_error() {
        ApiException apiException = new ApiException(ErrorResponse.emptyErrorResponse(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        when(consentApi.revokeConsentByUserId(Mockito.anyString(), Mockito.anyString()))
                .thenThrow(apiException);
        try {
            revokeUserConsentUseCase.execute(SSOAssertions.userModel.getId(),
                    SSOAssertions.consentModel.getClientId());
        } catch (BusinessException exception) {
            Assert.assertEquals("there was an error executing /users/consent revoke", exception.getMessage());
            ApiException cause = (ApiException) exception.getCause();
            Assert.assertEquals("ERROR", cause.getErrorResponse().getCode());
            Assert.assertEquals("NO RESPONSE BODY FOUND", cause.getErrorResponse().getMessage());
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cause.getHttpStatus());
        }
    }
}
