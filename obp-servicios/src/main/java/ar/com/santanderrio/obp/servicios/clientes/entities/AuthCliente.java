package ar.com.santanderrio.obp.servicios.clientes.entities;

public class AuthCliente {
	
	
	private String authJWT;
	
	private Integer expireJWT;
	
	private String refreshToken;
	
	private String lastToken;
	
	private Integer expireRefreshJWT;
	
	private Boolean apiAuthClient = Boolean.FALSE;

	/**
	 * @return the authJWT
	 */
	public String getAuthJWT() {
		return authJWT;
	}

	/**
	 * @param authJWT the authJWT to set
	 */
	public void setAuthJWT(String authJWT) {
		this.authJWT = authJWT;
	}

	/**
	 * @return the expireJWT
	 */
	public Integer getExpireJWT() {
		return expireJWT;
	}

	/**
	 * @param expireJWT the expireJWT to set
	 */
	public void setExpireJWT(Integer expireJWT) {
		this.expireJWT = expireJWT;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the lastToken
	 */
	public String getLastToken() {
		return lastToken;
	}

	/**
	 * @param lastToken the lastToken to set
	 */
	public void setLastToken(String lastToken) {
		this.lastToken = lastToken;
	}

	/**
	 * @return the expireRefreshJWT
	 */
	public Integer getExpireRefreshJWT() {
		return expireRefreshJWT;
	}

	/**
	 * @param expireRefreshJWT the expireRefreshJWT to set
	 */
	public void setExpireRefreshJWT(Integer expireRefreshJWT) {
		this.expireRefreshJWT = expireRefreshJWT;
	}

	/**
	 * @return the apiAuthClient
	 */
	public Boolean isApiAuthClient() {
		return apiAuthClient;
	}

	/**
	 * @param apiAuthClient the apiAuthClient to set
	 */
	public void setApiAuthClient(Boolean apiAuthClient) {
		this.apiAuthClient = apiAuthClient;
	}
	
}
