package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApiClientBuilder;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;
import ar.com.santanderrio.obp.servicios.oauth2.resource.ClientCredentialsResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthCachedTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.sql.SQLException;

@Configuration
public class PackagesApiClientConfig {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PackagesApiClientConfig.class);
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Autowired
    private IatxObpContext iatxObpContext;

    @Value("${PACKAGES.OAUTHV2.SEC.ID}")
    private String packagesSecId;

    @Bean
    @Autowired
    public PackagesApi packagesApi(@Qualifier("packagesApiConfig") final ApiConfig packagesApiConfig,
                                       final OBPOAuthContext obpOAuthContext,
                                       final ProxyConfig proxyConfig,
                                       final OAuthV2Connector oAuthV2Connector) {
        LOGGER.info("ML Entre packagesApi");
        final OAuth2ResourceDetails resourceDetailsPaq = buildOAuthResourceDetails(packagesApiConfig, obpOAuthContext);
        initIatxObpContext();
        PackagesApiClientBuilder builders = PackagesApiClientBuilder.builder()
                .withAccountApiConfig(packagesApiConfig)
                .withResourceDetails(resourceDetailsPaq)
                .withIatxObpContext(iatxObpContext)
                .withOAuthTokenProvider(new OAuthCachedTokenProvider(oAuthV2Connector));

        if (proxyConfig.getProxyRequired()) {
            Proxy proxyPaq = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyConfig.getProxyAddress(), proxyConfig.getProxyPort()));
            builders.withProxy(proxyPaq);
        }
        return builders.build();
    }

    private void initIatxObpContext() {
        try {
            Credential credPaq = credentialSecurityFactory.getCredentialById(iatxObpContext.getSecurityId());
            iatxObpContext.setIatxUser(credPaq.getUsuario());
            iatxObpContext.setIatxPassword(credPaq.getPassword());
        } catch (SQLException ex) {
            throw new IllegalStateException("Error at fetching api-packages securityId credentials");
        }
    }

     private Credential getDBCredentials(String securityId) throws IllegalStateException {
        try {
            return credentialSecurityFactory.getCredentialById(securityId);
        } catch (SQLException ex) {
            throw new IllegalStateException("Error at fetching api-packages securityId credentials");
        }
    }
    private OAuth2ResourceDetails buildOAuthResourceDetails(final ApiConfig packagesApiConfig, final OBPOAuthContext obpOAuthContext) {
        Credential cred = getDBCredentials(packagesSecId);
        ClientCredentialsResourceDetails accountsOAuthContext = new ClientCredentialsResourceDetails();
        accountsOAuthContext.setId("PACKAGES-API");
        accountsOAuthContext.setAccessTokenUri(obpOAuthContext.getOauthUrl() + obpOAuthContext.getOauthTokenPath());
        accountsOAuthContext.setScope(packagesApiConfig.getScope());
        accountsOAuthContext.setClientId(cred.getUsuario());
        accountsOAuthContext.setClientSecret(cred.getPassword());
        return accountsOAuthContext;
    }

}
