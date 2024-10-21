package ar.com.santanderrio.obp.servicios.oauth2.token;

public class OAuth2AccessToken extends OAuth2ExpirableToken {

	private String accessToken;
	private String tokenType;
	private String scope;
	private OAuth2RefreshToken refreshToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public OAuth2RefreshToken getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(OAuth2RefreshToken refreshToken) {
		this.refreshToken = refreshToken;
	}
}
