package ar.com.santanderrio.obp.servicios.oauth2.connector;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.oauth.dto.OAuthTokenRequestDTO;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthGrantTypesEnum;

@Component
public class OAuthV2ConnectorImpl implements OAuthV2Connector {
	private static final Logger LOGGER = LoggerFactory.getLogger(OAuthV2ConnectorImpl.class);

	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String AUTHORIZATION_BASIC = "Basic ";

	@Autowired
	@Qualifier("oauthRestTemplate")
	private RestTemplate restTemplate;

	/** The prcTokens. */
	private static ConcurrentHashMap<String, Boolean> prcTokens = new ConcurrentHashMap<String, Boolean>();

	/** The curTokens. */
	private static ConcurrentHashMap<String, OAuth2AccessToken> curTokens = new ConcurrentHashMap<String, OAuth2AccessToken>();

	@Cacheable(value = CacheConstants.Names.CACHE_OAUTHV2, key = "#scope.concat(#credential.usuario)", unless="#result == null")
	@Override
	public OAuth2AccessToken getAccessToken(Credential credential, String scope, String url) throws DAOException {
		String tokenKey = scope.concat(credential.getUsuario());
		if (!Boolean.TRUE.equals(prcTokens.get(tokenKey))) {
			prcTokens.put(tokenKey, Boolean.TRUE);
			try {
				OAuth2AccessToken curToken = curTokens.get(tokenKey);
				OAuthGrantTypesEnum grantTypesEnum = (curToken != null && !curToken.getRefreshToken().isExpired()) ?
						OAuthGrantTypesEnum.REFRESH_TOKEN : OAuthGrantTypesEnum.CREDENTIALS;

				OAuthTokenRequestDTO request = new OAuthTokenRequestDTO()
					.setGrantType(grantTypesEnum)
					.setScope(scope)
					.setRefreshToken((curToken != null && OAuthGrantTypesEnum.REFRESH_TOKEN.equals(grantTypesEnum)) ?
							curToken.getRefreshToken().getRefreshToken() : null);

				curToken = getAuthToken(credential, request, url);
				curTokens.put(tokenKey, curToken);
				return curToken;
			} catch (Exception e) {
				throw new DAOException(e, e.getMessage());
			} finally {
				prcTokens.put(tokenKey, Boolean.FALSE);
			}
		} else {
			OAuth2AccessToken token = curTokens.get(tokenKey);
			if(token == null) {
				throw new DAOException("No token for scope {} found", scope);
			}
			return token;
		}
	}

	@CacheEvict(value = CacheConstants.Names.CACHE_OAUTHV2, allEntries = true)
	@Override
	public void vaciarCacheOAuthV2() {
		LOGGER.info("Deleting all cached access token.");
	}

	@CacheEvict(value = CacheConstants.Names.CACHE_OAUTHV2, key = "#scope.concat(#credential.usuario)")
	@Override
	public void deleteCachedToken(Credential credential, String scope) {
		LOGGER.info("Deleting cached token for scope: {}.", scope);
	}

	private OAuth2AccessToken getAuthToken(Credential credential, OAuthTokenRequestDTO request, String url)
			throws DAOException {
		try {
			return performRequest(this.getFormRequestEntity(request, credential), url);
		} catch (Exception e) {
			// if last GrantType was refresh try requesting a new credentials before exit
			if(OAuthGrantTypesEnum.REFRESH_TOKEN.equals(request.getGrantType())) {
				request.setGrantType(OAuthGrantTypesEnum.CREDENTIALS);
				return getAuthToken(credential, request, url);
			}
			throw new DAOException(e, "Error al invocar a oauthv2: " + e.getMessage());
		}
	}

	private OAuth2AccessToken performRequest(HttpEntity<?> requestEntity, String url) throws DAOException {
		try {
			return restTemplate.exchange(url, HttpMethod.POST, requestEntity, OAuth2AccessToken.class).getBody();
		} catch (Exception e) {
			throw new DAOException(e,"Error al invocar a oauthv2");
		}
	}

	private HttpEntity<MultiValueMap<String, String>> getFormRequestEntity(OAuthTokenRequestDTO requestDTO, Credential credential)
			throws UnsupportedEncodingException {
		final HttpHeaders headers = new HttpHeaders();
		String auth = credential.getUsuario() + ":" + credential.getPassword();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set(HEADER_AUTHORIZATION, AUTHORIZATION_BASIC + new String(Base64.encodeBase64(auth.getBytes("UTF-8"), false)));

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", requestDTO.getGrantType().getCode());
		if(StringUtils.isNotBlank(requestDTO.getScope())) {
			map.add("scope", requestDTO.getScope());
		}
		if (OAuthGrantTypesEnum.REFRESH_TOKEN.equals(requestDTO.getGrantType())) {
			map.add("refresh_token", requestDTO.getRefreshToken());
		}
		return new HttpEntity<MultiValueMap<String, String>>(map, headers);
	}
}
