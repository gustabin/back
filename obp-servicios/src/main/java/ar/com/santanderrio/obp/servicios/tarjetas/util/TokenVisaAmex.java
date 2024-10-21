/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class TokenVisaAmex.
 *
 * @author sergio.e.goldentair
 */
public final class TokenVisaAmex {

	/** The Constant SALTO_LINEA. */
	private static final String SALTO_LINEA = "\r\n";

	/** The dir cliente. */
	private String dirCliente;

	/** The expiracion. */
	private String expiracion;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The tarjeta. */
	private String tarjeta;

	/** The codigo banco. */
	private String codigoBanco;

	/**
	 * Instantiates a new token visa amex.
	 *
	 * @param tokenBuilder
	 *            the token builder
	 */
	private TokenVisaAmex(TokenVisaAmexBuilder tokenBuilder) {
		this.dirCliente = tokenBuilder.dirCliente;
		this.expiracion = tokenBuilder.expiracion;
		this.numeroCuenta = tokenBuilder.numeroCuenta;
		this.tarjeta = tokenBuilder.tarjeta;
		this.codigoBanco = tokenBuilder.codigoBanco;
	}

	/**
	 * The Class TokenVisaAmexBuilder.
	 */
	public static class TokenVisaAmexBuilder {

		/** The dir cliente. */
		private String dirCliente;

		/** The expiracion. */
		private String expiracion;

		/** The numero cuenta. */
		private String numeroCuenta;

		/** The tarjeta. */
		private String tarjeta;

		/** The codigo banco. */
		private String codigoBanco;

		/**
		 * Instantiates a new token visa amex builder.
		 */
		public TokenVisaAmexBuilder() {
			super();
		}

		/**
		 * Builds the.
		 *
		 * @return the token visa amex
		 */
		public TokenVisaAmex build() {
			return new TokenVisaAmex(this);
		}

		/**
		 * Sets the dir cliente.
		 *
		 * @param dirCliente
		 *            the dirCliente to set
		 * @return TokenVisaAmexBuilder
		 */
		public TokenVisaAmexBuilder setDirCliente(String dirCliente) {
			this.dirCliente = dirCliente;
			return this;
		}

		/**
		 * Sets the expiracion.
		 *
		 * @param expiracion
		 *            the expiracion to set
		 * @return TokenVisaAmexBuilder
		 */
		public TokenVisaAmexBuilder setExpiracion(String expiracion) {
			this.expiracion = expiracion;
			return this;
		}

		/**
		 * Sets the numero cuenta.
		 *
		 * @param numeroCuenta
		 *            the numeroCuenta to set
		 * @return TokenVisaAmexBuilder
		 */
		public TokenVisaAmexBuilder setNumeroCuenta(String numeroCuenta) {
			this.numeroCuenta = numeroCuenta;
			return this;
		}

		/**
		 * Sets the tarjeta.
		 *
		 * @param tarjeta
		 *            the tarjeta to set
		 * @return TokenVisaAmexBuilder
		 */
		public TokenVisaAmexBuilder setTarjeta(String tarjeta) {
			this.tarjeta = tarjeta;
			return this;
		}

		/**
		 * Sets the codigo banco.
		 *
		 * @param codigoBanco
		 *            the codigoBanco to set
		 * @return TokenVisaAmexBuilder
		 */
		public TokenVisaAmexBuilder setCodigoBanco(String codigoBanco) {
			this.codigoBanco = codigoBanco;
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoBanco);
		hcb.append(dirCliente);
		hcb.append(expiracion);
		hcb.append(numeroCuenta);
		hcb.append(tarjeta);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TokenVisaAmex other = (TokenVisaAmex) obj;
		return new EqualsBuilder().append(codigoBanco, other.codigoBanco).append(dirCliente, other.dirCliente)
				.append(expiracion, other.expiracion).append(numeroCuenta, other.numeroCuenta)
				.append(tarjeta, other.tarjeta).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder miToken = new StringBuilder();
		if (dirCliente != null) {
			miToken.append("Direccion-Cliente=").append(dirCliente).append(SALTO_LINEA);
		}
		if (expiracion != null) {
			miToken.append("Expiracion=").append(expiracion).append(SALTO_LINEA);
		}
		if (numeroCuenta != null) {
			miToken.append("Numero-Cuenta=").append(numeroCuenta).append(SALTO_LINEA);
		}
		if (tarjeta != null) {
			miToken.append("Tarjeta=").append(tarjeta).append(SALTO_LINEA);
		}
		if (codigoBanco != null) {
			miToken.append("Codigo-Banco=").append(codigoBanco).append(SALTO_LINEA);
		}

		return miToken.toString();
	}
}
