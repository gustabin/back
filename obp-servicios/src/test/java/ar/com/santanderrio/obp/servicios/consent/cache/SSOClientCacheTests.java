package ar.com.santanderrio.obp.servicios.consent.cache;


import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.sso.SSOAssertions;
import ar.com.santanderrio.obp.servicios.api.sso.client.SSOClientsApi;
import ar.com.santanderrio.obp.servicios.api.sso.client.entities.ClientRepresentationModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Collections;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                SSOClientsService.class,
                SSOClientCacheTests.SSOClientCacheTestsConfiguration.class
        })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SSOClientCacheTests {

    @Autowired
    SSOClientsApi ssoClientsApi;

    @Autowired
    SSOClientsService ssoClientsService;

    @Configuration
    static class SSOClientCacheTestsConfiguration{

        @Bean
        public SSOClientsApi ssoClientsApi() {
            return Mockito.mock(SSOClientsApi.class);
        }
    }

    @Test
    public void getClientFromService() {
        //Setup
        when(ssoClientsApi.getClientByClientId(anyString())).
                thenReturn(Collections.singletonList(SSOAssertions.clientModel));

        //execution
        ClientRepresentationModel client = ssoClientsService.getSSOClient("pkce-test");
        Assert.assertNotNull(client);
        Assert.assertEquals(SSOAssertions.clientModel.getName(), client.getName());
        Assert.assertEquals(SSOAssertions.clientModel.getClientId(), client.getClientId());
        Assert.assertEquals(SSOAssertions.clientModel.getId(), client.getId());
    }

    @Test
    public void getClientFromService_emptyResponse() {
        //Setup
        when(ssoClientsApi.getClientByClientId(anyString())).
                thenReturn(Collections.<ClientRepresentationModel>emptyList());

        //execution
        ClientRepresentationModel client = ssoClientsService.getSSOClient("pkce-test");
        Assert.assertNull(client);
    }

    @Test
    public void getClientFromService_error() {
        //Setup
        ApiException apiException = new ApiException(ErrorResponse.emptyErrorResponse(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        when(ssoClientsApi.getClientByClientId(anyString()))
                .thenThrow(apiException);

        //execution
        ClientRepresentationModel client = ssoClientsService.getSSOClient("pkce-test");
        Assert.assertNull(client);
    }
}
