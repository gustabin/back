package ar.com.santanderrio.obp.servicios.biocatch.client;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplateBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class BiocatchApiClientBuilder {

    private BiocatchApiConfigProvider biocatchApiConfig;
    private OAuth2ResourceDetails resourceDetails;
    private OAuthTokenProvider oauthTokenProvider;
    private OAuth2RestTemplate oauth2RestTemplate;

    public static BiocatchApiClientBuilder builder() {
        return new BiocatchApiClientBuilder();
    }

    public BiocatchApiClientBuilder withBiocatchApiConfig(BiocatchApiConfigProvider biocatchApiConfig) {
        this.biocatchApiConfig = biocatchApiConfig;
        return this;
    }

    public BiocatchApiClientBuilder withResourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public BiocatchApiClientBuilder withOAuthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public BiocatchApi build() {
        Assert.notNull(biocatchApiConfig, "biocatch api Scope missing");

        if(oauth2RestTemplate == null) {
            Assert.notNull(oauthTokenProvider, "oauthTokenProvider missing");
            Assert.notNull(resourceDetails, "biocatch api resource details missing");
            oauth2RestTemplate = buildRestTemplate();
        }

        return new BiocatchApiClient(URI.create(biocatchApiConfig.getBasePath()), oauth2RestTemplate);
    }

    private OAuth2RestTemplate buildRestTemplate() {
        OAuth2RestTemplate restTemplate = OAuth2RestTemplateBuilder.builder()
                .resourceDetails(resourceDetails)
                .oauthTokenProvider(oauthTokenProvider)
                .build();

        restTemplate.setRequestFactory(buildFactory());
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new RestLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    private BufferingClientHttpRequestFactory buildFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(biocatchApiConfig.getReadTimeout());
        factory.setConnectTimeout(biocatchApiConfig.getConnectTimeout());
        factory.setOutputStreaming(false);
        return new BufferingClientHttpRequestFactory(factory);
    }
}
