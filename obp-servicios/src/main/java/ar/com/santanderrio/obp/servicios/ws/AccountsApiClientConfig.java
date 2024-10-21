package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApi;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApiClientBuilder;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;
import ar.com.santanderrio.obp.servicios.oauth2.resource.ClientCredentialsResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthCachedTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.sql.SQLException;

@Configuration
public class AccountsApiClientConfig {

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Autowired
    private IatxObpContext iatxObpContext;

    @Value("${FONDOS.OAUTHV2.SEC.ID}")
    private String accountsSecId;

    @Bean
    @Autowired
    public AccountsApi accountsApi(@Qualifier("accountsApiConfig") final ApiConfig apiConfig,
            final OBPOAuthContext obpOAuthContext,
            final ProxyConfig proxyConfig,
            final OAuthV2Connector oAuthV2Connector) {
        final OAuth2ResourceDetails resourceDetails = buildOAuthResourceDetails(apiConfig, obpOAuthContext);
        initIatxObpContext();
        AccountsApiClientBuilder builder = AccountsApiClientBuilder.builder()
                .withAccountApiConfig(apiConfig)
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
            Credential cred = credentialSecurityFactory.getCredentialById(iatxObpContext.getSecurityId());
            iatxObpContext.setIatxUser(cred.getUsuario());
            iatxObpContext.setIatxPassword(cred.getPassword());
        } catch (SQLException ex) {
            throw new IllegalStateException("Error at fetching api-accounts securityId credentials");
        }
    }

    private OAuth2ResourceDetails buildOAuthResourceDetails(final ApiConfig accountsApiConfig, final OBPOAuthContext obpOAuthContext) {
        Credential cred = getDBCredentials(accountsSecId);
        ClientCredentialsResourceDetails accountsOAuthContext = new ClientCredentialsResourceDetails();
        accountsOAuthContext.setId("ACCOUNTS-API");
        accountsOAuthContext.setAccessTokenUri(obpOAuthContext.getOauthUrl() + obpOAuthContext.getOauthTokenPath());
        accountsOAuthContext.setScope(accountsApiConfig.getScope());
        accountsOAuthContext.setClientId(cred.getUsuario());
        accountsOAuthContext.setClientSecret(cred.getPassword());
        return accountsOAuthContext;
    }

    private Credential getDBCredentials(String securityId) throws IllegalStateException {
        try {
            return credentialSecurityFactory.getCredentialById(securityId);
        } catch (SQLException ex) {
            throw new IllegalStateException("Error at fetching api-accounts securityId credentials");
        }
    }
}
