package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfigFunds;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApiClientBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;
import ar.com.santanderrio.obp.servicios.oauth2.resource.ClientCredentialsResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthCachedTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.sql.SQLException;

@Configuration
public class FundsApiClientConfig {

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Autowired
    private IatxObpContext iatxObpContext;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FundsApiClientConfig.class);

    @Value("${FONDOS.OAUTHV2.SEC.ID}")
    private String fundsSecId;

    @Bean
    @Autowired
    public FundsApi fundsApi(final ApiConfigFunds fundsApiConfig,
                             final OBPOAuthContext obpOAuthContext,
                             final ProxyConfig proxyConfig,
                             final OAuthV2Connector oAuthV2Connector) {
        final OAuth2ResourceDetails resourceDetails = buildOAuthResourceDetails(fundsApiConfig, obpOAuthContext);
        initIatxObpCtx();
        FundsApiClientBuilder builder = FundsApiClientBuilder.builder()
                .withFundsApiConfig(fundsApiConfig)
                .withResourceDetails(resourceDetails)
                .withIatxObpContext(iatxObpContext)
                .withOAuthTokenProvider(new OAuthCachedTokenProvider(oAuthV2Connector));

        if (proxyConfig.getProxyRequired()) {
            Proxy proxyHttp = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyConfig.getProxyAddress(),
                    proxyConfig.getProxyPort()));
            builder.withProxy(proxyHttp);
        }

        return builder.build();
    }

    private OAuth2ResourceDetails buildOAuthResourceDetails(final ApiConfigFunds fundsApiConfig,
                                                            final OBPOAuthContext obpOAuthContext) {
        Credential cred = getDBCredentials(fundsSecId);
        ClientCredentialsResourceDetails fundsOAuthContext = new ClientCredentialsResourceDetails();
        fundsOAuthContext.setId("APIF-API_MUTUAL_FUNDS");
        fundsOAuthContext.setScope(fundsApiConfig.getCommonBasePath());
        fundsOAuthContext.setAccessTokenUri(obpOAuthContext.getOauthUrl() + obpOAuthContext.getOauthTokenPath());
        fundsOAuthContext.setScope(fundsApiConfig.getCommonScope());
        fundsOAuthContext.setClientId(cred.getUsuario());
        LOGGER.info("Se consulta la API-HOLDING {}", cred.getUsuario());
        fundsOAuthContext.setClientSecret(cred.getPassword());
        LOGGER.info("Se consulta la API-HOLDING {}", cred.getPassword());
        return fundsOAuthContext;
    }

    private Credential getDBCredentials(String securityId) throws IllegalStateException {
        try {
            return credentialSecurityFactory.getCredentialById(securityId);
        } catch (SQLException ex) {
            throw new IllegalStateException("Error at fetching apif-api_mutual_funds securityId credentials");
        }
    }

    private void initIatxObpCtx() {
        try {
            Credential cred = credentialSecurityFactory.getCredentialById(iatxObpContext.getSecurityId());
            iatxObpContext.setIatxUser(cred.getUsuario());
            iatxObpContext.setIatxPassword(cred.getPassword());
        } catch (SQLException ex) {
            throw new IllegalStateException("Error at fetching apif-api_mutual_funds securityId credentials");
        }
    }

}
