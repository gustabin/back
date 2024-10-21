package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.offersservice.OffersServiceApi;
import ar.com.santanderrio.obp.servicios.api.offersservice.OffersServiceApiClientBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;
import ar.com.santanderrio.obp.servicios.oauth2.resource.ClientCredentialsResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthCachedTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.sql.SQLException;

@Configuration
public class OffersServiceApiClientConfig {

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Autowired
    private IatxObpContext iatxObpContext;

    @Bean
    @Autowired
    public OffersServiceApi offersServiceApi(@Qualifier("offersApiConfig") final ApiConfig offersApiConfig,
                                         final OBPOAuthContext obpOAuthContext,
                                         final ProxyConfig proxyConfig,
                                         final OAuthV2Connector oAuthV2Connector) {
        final OAuth2ResourceDetails resourceDetails = buildOAuthResourceDetails(offersApiConfig, obpOAuthContext);
        initIatxObpContext();
        OffersServiceApiClientBuilder builder = OffersServiceApiClientBuilder.builder()
                .withCustomersApiConfig(offersApiConfig)
                .withResourceDetails(resourceDetails)
                .withIatxObpContext(iatxObpContext)
                .withOAuthTokenProvider(new OAuthCachedTokenProvider(oAuthV2Connector));

        if (proxyConfig.getProxyRequired()) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyConfig.getProxyAddress(), proxyConfig.getProxyPort()));
            builder.withProxy(proxy);
        }
        return builder.build();
    }

    private void initIatxObpContext() {
        try {
            Credential credential = credentialSecurityFactory.getCredentialById(iatxObpContext.getSecurityId());
            iatxObpContext.setIatxPassword(credential.getPassword());
            iatxObpContext.setIatxUser(credential.getUsuario());
        } catch (SQLException e) {
            throw new IllegalStateException("Error at fetching offers security id credentials");
        }
    }

    private OAuth2ResourceDetails buildOAuthResourceDetails(final ApiConfig offersApiConfig, final OBPOAuthContext obpOAuthContext) {
        Credential credential = getCredentials(obpOAuthContext.getOauthV2SecurityId());
        ClientCredentialsResourceDetails offersOAuthContext = new ClientCredentialsResourceDetails();
        offersOAuthContext.setId("OFFERS-API");
        offersOAuthContext.setScope(offersApiConfig.getScope());
        offersOAuthContext.setAccessTokenUri(obpOAuthContext.getOauthUrl() + obpOAuthContext.getOauthTokenPath());
        offersOAuthContext.setScope(offersApiConfig.getScope());
        offersOAuthContext.setClientId(credential.getUsuario());
        offersOAuthContext.setClientSecret(credential.getPassword());
        return offersOAuthContext;
    }

    private Credential getCredentials(String securityId) throws IllegalStateException {
        try {
            return credentialSecurityFactory.getCredentialById(securityId);
        } catch (SQLException e) {
            throw new IllegalStateException("Error at fetching customers security id credentials");
        }
    }
}
