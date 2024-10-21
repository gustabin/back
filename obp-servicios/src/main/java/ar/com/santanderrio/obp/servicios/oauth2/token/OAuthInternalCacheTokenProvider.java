package ar.com.santanderrio.obp.servicios.oauth2.token;

import ar.com.santanderrio.obp.base.webservice.interceptor.RestLoggingInterceptor;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthGrantTypesEnum;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2AuthorizationException;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OAuthInternalCacheTokenProvider implements OAuthTokenProvider {
    private final RestTemplate restTemplate;
    //TODO: Create delegate to update accessToken
    private OAuth2AccessToken accessToken;
    private OAuth2RefreshToken refreshToken;

    public OAuthInternalCacheTokenProvider() {
        super();
        restTemplate = initRestTemplate();
    }

    private RestTemplate initRestTemplate() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(
                new GsonBuilder()
                        .registerTypeAdapter(OAuth2AccessToken.class, new OAuth2AccessTokenTypeConverter())
                .create());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        messageConverters.add(gsonHttpMessageConverter);

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new RestLoggingInterceptor());

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        factory.setOutputStreaming(false);

        RestTemplate auxRestTemplate = new RestTemplate();
        auxRestTemplate.setMessageConverters(messageConverters);
        auxRestTemplate.setInterceptors(interceptors);
        auxRestTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));
        return auxRestTemplate;
    }

    @Override
    public OAuth2AccessToken requestAccessToken(OAuth2ResourceDetails resourceDetails)
            throws OAuth2AuthorizationException {
        if (accessToken == null || accessToken.isExpired()) {
            adquireAccessToken(resourceDetails, accessToken, refreshToken);
        }
        return accessToken;
    }

    private synchronized void adquireAccessToken(OAuth2ResourceDetails resourceDetails, OAuth2AccessToken accessTokenCopy, OAuth2RefreshToken refreshTokenCopy) {
        if(refreshToken != null && !refreshToken.isExpired()
                && refreshToken.equals(refreshTokenCopy)) {
            accessToken = requestRefreshToken(resourceDetails);
        } else if (accessToken == null || accessToken.equals(accessTokenCopy)) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("grant_type", OAuthGrantTypesEnum.CREDENTIALS.getCode());
            accessToken = performRequest(resourceDetails, map);
        }
        refreshToken = accessToken.getRefreshToken();
    }

    @Override
    public OAuth2AccessToken requestRefreshToken(OAuth2ResourceDetails resourceDetails)
            throws OAuth2AuthorizationException {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", OAuthGrantTypesEnum.REFRESH_TOKEN.getCode());
        map.add("refresh_token", refreshToken.getRefreshToken());
        return performRequest(resourceDetails, map);
    }

    private HttpHeaders getAuthenticationHeaders(OAuth2ResourceDetails resourceDetails)
            throws OAuth2AuthorizationException {
        final HttpHeaders headers = new HttpHeaders();
        final String auth = resourceDetails.getClientId() + ":" + resourceDetails.getClientSecret();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        try {
            headers.set(resourceDetails.getAuthorizationHeaderName(),
                    resourceDetails.getClientAuthenticationPrefix() +
                            new String(Base64.encodeBase64(auth.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException exception) {
            throw new OAuth2AuthorizationException("Ocurrio un error al solicitar access_token", exception);
        }
        return headers;
    }

    private OAuth2AccessToken performRequest(OAuth2ResourceDetails resourceDetails,
            MultiValueMap<String, String> paramsMap) {
        HttpHeaders headers = getAuthenticationHeaders(resourceDetails);
        HttpEntity<MultiValueMap<String, String>> httpEntity =
                new HttpEntity<MultiValueMap<String, String>>(paramsMap, headers);
        try {
            return restTemplate.exchange(
                    resourceDetails.getAccessTokenUri(),
                    HttpMethod.POST,
                    httpEntity,
                    OAuth2AccessToken.class).getBody();
        } catch (Exception e) {
            this.accessToken = null;
            this.refreshToken = null;
            throw new OAuth2AuthorizationException("Error al invocar a consent sso", e);
        }
    }
}
