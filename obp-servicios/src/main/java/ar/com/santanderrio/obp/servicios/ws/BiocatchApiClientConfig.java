package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.biocatch.client.BiocatchApi;
import ar.com.santanderrio.obp.servicios.biocatch.client.BiocatchApiClientBuilder;
import ar.com.santanderrio.obp.servicios.biocatch.client.BiocatchApiConfigProvider;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;
import ar.com.santanderrio.obp.servicios.oauth2.resource.ClientCredentialsResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthCachedTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class BiocatchApiClientConfig {

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Bean
    @Autowired
    public BiocatchApi biocatchApi(@Qualifier("biocatchApiConfig") final BiocatchApiConfigProvider biocatchApiConfig,
                                   final OBP3ScaleOAuthContext obpOAuthContext,
                                   final OAuthV2Connector oAuthV2Connector) {
        final OAuth2ResourceDetails resourceDetails = buildOAuthResourceDetails(biocatchApiConfig, obpOAuthContext);
        BiocatchApiClientBuilder builder = BiocatchApiClientBuilder.builder()
                .withBiocatchApiConfig(biocatchApiConfig)
                .withResourceDetails(resourceDetails)
                .withOAuthTokenProvider(new OAuthCachedTokenProvider(oAuthV2Connector));
        return builder.build();
    }

    private OAuth2ResourceDetails buildOAuthResourceDetails(final ApiConfig biocatchApiConfig, final OBP3ScaleOAuthContext authContext) {
        Credential credential = getCredentials(authContext.getOauthV2SecurityId());
        ClientCredentialsResourceDetails biocatchOAuthContext = new ClientCredentialsResourceDetails();
        biocatchOAuthContext.setId("BIOCATCH-API");
        biocatchOAuthContext.setScope(biocatchApiConfig.getScope());
        biocatchOAuthContext.setClientId(credential.getUsuario());
        biocatchOAuthContext.setClientSecret(credential.getPassword());
        biocatchOAuthContext.setAccessTokenUri(authContext.getOauthUrl() + authContext.getOauthTokenPath());
        return biocatchOAuthContext;
    }

    private Credential getCredentials(String securityId) throws IllegalStateException {
        try {
            return credentialSecurityFactory.getCredentialById(securityId);
        } catch (SQLException e) {
            throw new IllegalStateException("Error at fetching customers security id credentials");
        }
    }
}
