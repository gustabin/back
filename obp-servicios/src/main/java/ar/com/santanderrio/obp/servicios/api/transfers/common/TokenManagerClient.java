package ar.com.santanderrio.obp.servicios.api.transfers.common;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class TokenManagerClient implements TokenManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManagerClient.class);

    private final TokenProvider tokenProvider;

    @Autowired
    public TokenManagerClient(TokenProvider tokenProvider) {

        this.tokenProvider = tokenProvider;

    }

    @Override
    public Token getToken(String tokenProviderUrl, Credential credential) throws UnsupportedEncodingException {

        LOGGER.info("Obteniendo token de comunicacion con la api");

        Token token = tokenProvider.getToken(tokenProviderUrl, credential);

        if (token.isExpired()) {

            return refreshToken(tokenProviderUrl, credential);

        }

        return token;

    }

    private Token refreshToken(String tokenProviderUrl, Credential credential) throws UnsupportedEncodingException {

        LOGGER.info("El token de comunicacion con la api expiro");

        tokenProvider.evictTokenCache(credential);

        return tokenProvider.getToken(tokenProviderUrl, credential);

    }

}
