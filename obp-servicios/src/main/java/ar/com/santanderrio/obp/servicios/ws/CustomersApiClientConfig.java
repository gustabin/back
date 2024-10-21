package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.customers.CustomersApi;
import ar.com.santanderrio.obp.servicios.api.customers.CustomersApiClientBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.resource.ClientCredentialsResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthCachedTokenProvider;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.sql.SQLException;

@Configuration
public class CustomersApiClientConfig {

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Autowired
    private IatxObpContext iatxObpContext;

    @Bean
    @Autowired
    public CustomersApi customersApi(@Qualifier("customersApiConfig") final ApiConfig customersApiConfig,
            final OBPOAuthContext obpOAuthContext,
            final ProxyConfig proxyConfig,
            final OAuthV2Connector oAuthV2Connector) {
        final OAuth2ResourceDetails resourceDetails = buildOAuthResourceDetails(customersApiConfig, obpOAuthContext);
        initIatxObpContext();
        CustomersApiClientBuilder builder = CustomersApiClientBuilder.builder()
                .withCustomersApiConfig(customersApiConfig)
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
            iatxObpContext.setIatxUser(credential.getUsuario());
            iatxObpContext.setIatxPassword(credential.getPassword());
        } catch (SQLException e) {
            throw new IllegalStateException("Error at fetching customers security id credentials");
        }
    }

    private OAuth2ResourceDetails buildOAuthResourceDetails(final ApiConfig customersApiConfig, final OBPOAuthContext obpOAuthContext) {
        Credential credential = getCredentials(obpOAuthContext.getOauthV2SecurityId());
        ClientCredentialsResourceDetails customersOAuthContext = new ClientCredentialsResourceDetails();
        customersOAuthContext.setId("CUSTOMERS-API");
        customersOAuthContext.setScope(customersApiConfig.getScope());
        customersOAuthContext.setAccessTokenUri(obpOAuthContext.getOauthUrl() + obpOAuthContext.getOauthTokenPath());
        customersOAuthContext.setScope(customersApiConfig.getScope());
        customersOAuthContext.setClientId(credential.getUsuario());
        customersOAuthContext.setClientSecret(credential.getPassword());
        return customersOAuthContext;
    }

    private Credential getCredentials(String securityId) throws IllegalStateException {
        try {
            return credentialSecurityFactory.getCredentialById(securityId);
        } catch (SQLException e) {
            throw new IllegalStateException("Error at fetching customers security id credentials");
        }
    }
}
