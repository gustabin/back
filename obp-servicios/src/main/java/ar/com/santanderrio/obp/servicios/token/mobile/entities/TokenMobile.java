/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.mobile.entities;

/**
 * The Class TokenMobile.
 */
public class TokenMobile {

	/** The nup. */
	private String nup;

	/** The token. */
	private String token;

	/** The hash seguro. */
	private String hashSeguro;

	/** The estado. */
	private String estado;

	/**
	 * Instantiates a new token mobile.
	 *
	 * @param nup
	 *            the nup
	 * @param token
	 *            the token
	 * @param hashSeguro
	 *            the hash seguro
	 * @param estado
	 *            the estado
	 */
	private TokenMobile(String nup, String token, String hashSeguro, String estado) {
		this.nup = nup;
		this.token = token;
		this.hashSeguro = hashSeguro;
		this.estado = estado;
	}

	/**
	 * The Class TokenMobileBuilder.
	 */
	public static class TokenMobileBuilder {

		/** The nup. */
		private String nup;

		/** The token. */
		private String token;

		/** The hash seguro. */
		private String hashSeguro;

		/** The estado. */
		private String estado;

		/**
		 * Sets the nup.
		 *
		 * @param nup
		 *            the nup
		 * @return the token mobile builder
		 */
		public TokenMobileBuilder setNup(String nup) {
			this.nup = nup;
			return this;
		}

		/**
		 * Sets the token.
		 *
		 * @param token
		 *            the token
		 * @return the token mobile builder
		 */
		public TokenMobileBuilder setToken(String token) {
			this.token = token;
			return this;
		}

		/**
		 * Sets the hash seguro.
		 *
		 * @param hashSeguro
		 *            the hash seguro
		 * @return the token mobile builder
		 */
		public TokenMobileBuilder setHashSeguro(String hashSeguro) {
			this.hashSeguro = hashSeguro;
			return this;
		}

		/**
		 * Sets the estado.
		 *
		 * @param estado
		 *            the estado
		 * @return the token mobile builder
		 */
		public TokenMobileBuilder setEstado(String estado) {
			this.estado = estado;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the token mobile
		 */
		public TokenMobile build() {

			return new TokenMobile(this.nup, this.token, this.hashSeguro, this.estado);
		}

	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Gets the hash seguro.
	 *
	 * @return the hashSeguro
	 */
	public String getHashSeguro() {
		return hashSeguro;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

}
