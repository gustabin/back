package ar.com.santanderrio.obp.servicios.api.common;

import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxHeadersInterceptor;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {
        IatxObpContext.class,
        IatxHeadersInterceptorTest.HeadersInterceptorTestConfig.class
})
@TestPropertySource(locations = {"classpath:/config/files/obpcontext.properties"})
public class IatxHeadersInterceptorTest {

    IatxHeadersInterceptor headersInterceptor;

    @Autowired
    IatxObpContext obpContext;

    @Configuration
    public static class HeadersInterceptorTestConfig {

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        @Bean
        @Autowired
        public IatxHeadersInterceptor headersInterceptor(IatxObpContext obpContext) {
            obpContext.setIatxPassword("password");
            obpContext.setIatxUser("user");
            return Mockito.spy(new IatxHeadersInterceptor(obpContext));
        }
    }

    @Before
    public void setup() {
        obpContext.setIatxPassword("password");
        obpContext.setIatxUser("user");
        headersInterceptor = Mockito.spy(new IatxHeadersInterceptor(obpContext));
    }

    @Test
    public void addHeadersTest() throws Exception {
        ClientHttpRequest request = new MockClientHttpRequest();
        byte[] body = null;
        ClientHttpRequestExecution execution = new ClientHttpRequestExecution() {
            @Override
            public ClientHttpResponse execute(HttpRequest request, byte[] body) throws IOException {
                HttpHeaders headers = request.getHeaders();
                Assert.assertEquals(obpContext.getIatxUser(), headers.get(APIcConfigConstants.SAN_IATX_USER_ID).get(0));
                Assert.assertEquals(obpContext.getIatxPassword(), headers.get(APIcConfigConstants.SAN_IATX_USER_PASS).get(0));
                Assert.assertEquals(obpContext.getIatxChannelId(), headers.get(APIcConfigConstants.SAN_IATX_CHANNEL_ID).get(0));
                Assert.assertEquals(obpContext.getIatxChannelType(), headers.get(APIcConfigConstants.SAN_IATX_CHANNEL_TYPE).get(0));
                Assert.assertEquals(obpContext.getSubChannelId(), headers.get(APIcConfigConstants.SAN_IATX_SUBCHANNEL_ID).get(0));
                Assert.assertEquals(obpContext.getSubChannelType(), headers.get(APIcConfigConstants.SAN_IATX_SUBCHANNEL_TYPE).get(0));
                return new MockClientHttpResponse(body, HttpStatus.OK);
            }
        };
        headersInterceptor.intercept(request, null, execution);
    }
}

