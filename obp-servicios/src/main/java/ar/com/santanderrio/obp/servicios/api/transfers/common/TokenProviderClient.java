package ar.com.santanderrio.obp.servicios.api.transfers.common;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.api.transfers.common.dto.TokenProviderResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

@Component
public class TokenProviderClient implements TokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenProviderClient.class);

    private static final String AUTHORIZATION_BASIC = "Basic ";

    private static final String GRANT_TYPE = "grant_type";

    private static final String GRANT_TYPE_VALUE = "client_credentials";

    private static final String CREDENTIAL_DELIMITER = ":";

    private static final String ENCODING_METHOD = "UTF-8";

    private final RestTemplate restTemplate;

    @Autowired
    public TokenProviderClient(@Qualifier("tokenProviderRestTemplate") RestTemplate restTemplate) {

        this.restTemplate = restTemplate;

    }

    @Cacheable(value = CacheConstants.Names.CACHE_TRANSFERS_API_TOKENS, key = "#credential.getUsuario()", unless="#result.getValue() == null")
    @Override
    public Token getToken(String url, Credential credential) throws UnsupportedEncodingException {

        LOGGER.info("Obteniendo token desde {}", url);

        TokenProviderResponse response = restTemplate.postForEntity(url, getFormRequestEntity(credential), TokenProviderResponse.class).getBody();

        return new Token(response);

    }

    private HttpEntity<MultiValueMap<String, String>> getFormRequestEntity(Credential credential) throws UnsupportedEncodingException {

        final HttpHeaders headers = new HttpHeaders();
        String auth = credential.getUsuario() + CREDENTIAL_DELIMITER + credential.getPassword();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(HttpHeaders.AUTHORIZATION, AUTHORIZATION_BASIC + new String(Base64.encodeBase64(auth.getBytes(ENCODING_METHOD), false)));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add(GRANT_TYPE, GRANT_TYPE_VALUE);

        return new HttpEntity<MultiValueMap<String, String>>(map, headers);

    }

    @CacheEvict(value = CacheConstants.Names.CACHE_TRANSFERS_API_TOKENS, key = "#credential.getUsuario()")
    @Override
    public void evictTokenCache(Credential credential) {

        LOGGER.info("Se elimino de la cache el token con la key asociada {}", credential.getUsuario());

    }

}
