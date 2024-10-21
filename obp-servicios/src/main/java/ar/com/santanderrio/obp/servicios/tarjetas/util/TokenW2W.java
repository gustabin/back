package ar.com.santanderrio.obp.servicios.tarjetas.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class TokenW2W.
 */
public final class TokenW2W {

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

	/** The tipo documento. */
	private String tipoDocumento;

	/** The documento. */
	private String documento;

	/** The sexo. */
	private String sexo;

	/** The menu permisos. */
	private String menuPermisos;

	/** The visa nav bar W 2 W. */
	private String visaNavBarW2W;

	/**
	 * Instantiates a new TokenW2W.
	 *
	 * @param tokenBuilder
	 *            the token builder
	 */
	private TokenW2W(TokenW2WBuilder tokenBuilder) {
		this.dirCliente = tokenBuilder.dirCliente;
		this.expiracion = tokenBuilder.expiracion;
		this.numeroCuenta = tokenBuilder.numeroCuenta;
		this.tarjeta = tokenBuilder.tarjeta;
		this.codigoBanco = tokenBuilder.codigoBanco;
		this.tipoDocumento = tokenBuilder.tipoDocumento;
		this.documento = tokenBuilder.documento;
		this.sexo = tokenBuilder.sexo;
		this.menuPermisos = tokenBuilder.menuPermisos;
		this.visaNavBarW2W = tokenBuilder.visaNavBarW2W;
	}

	/**
	 * The Class TokenW2WBuilder.
	 */
	public static class TokenW2WBuilder {

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

		/** The tipo documento. */
		private String tipoDocumento;

		/** The documento. */
		private String documento;

		/** The sexo. */
		private String sexo;

		/** The menu permisos. */
		private String menuPermisos;

		/** The visaNavBarW2W. */
		private String visaNavBarW2W;

		/**
		 * Instantiates a new TokenW2W builder.
		 */
		public TokenW2WBuilder() {
			super();
		}

		/**
		 * Builds the.
		 *
		 * @return the TokenW2W
		 */
		public TokenW2W build() {
			return new TokenW2W(this);
		}

		/**
		 * Sets the dir cliente.
		 *
		 * @param dirCliente
		 *            the dir cliente
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setDirCliente(String dirCliente) {
			this.dirCliente = dirCliente;
			return this;
		}

		/**
		 * Sets the expiracion.
		 *
		 * @param expiracion
		 *            the expiracion
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setExpiracion(String expiracion) {
			this.expiracion = expiracion;
			return this;
		}

		/**
		 * Sets the numero cuenta.
		 *
		 * @param numeroCuenta
		 *            the numero cuenta
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setNumeroCuenta(String numeroCuenta) {
			this.numeroCuenta = numeroCuenta;
			return this;
		}

		/**
		 * Sets the tarjeta.
		 *
		 * @param tarjeta
		 *            the tarjeta
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setTarjeta(String tarjeta) {
			this.tarjeta = tarjeta;
			return this;
		}

		/**
		 * Sets the codigo banco.
		 *
		 * @param codigoBanco
		 *            the codigo Banco
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setCodigoBanco(String codigoBanco) {
			this.codigoBanco = codigoBanco;
			return this;
		}

		/**
		 * Sets the tipo documento.
		 *
		 * @param tipoDocumento
		 *            the tipo documento
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setTipoDocumento(String tipoDocumento) {
			this.tipoDocumento = tipoDocumento;
			return this;
		}

		/**
		 * Sets the documento.
		 *
		 * @param documento
		 *            the documento
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setDocumento(String documento) {
			this.documento = documento;
			return this;
		}

		/**
		 * Sets the sexo.
		 *
		 * @param sexo
		 *            the sexo
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setSexo(String sexo) {
			this.sexo = sexo;
			return this;
		}

		/**
		 * Sets the menu permisos.
		 *
		 * @param menuPermisos
		 *            the menu permisos
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setMenuPermisos(String menuPermisos) {
			this.menuPermisos = menuPermisos;
			return this;
		}

		/**
		 * Sets the visaNavBarW2W.
		 *
		 * @param visaNavBarW2W
		 *            the visa nav bar W 2 W
		 * @return TokenW2WBuilder
		 */
		public TokenW2WBuilder setVisaNavBarW2W(String visaNavBarW2W) {
			this.visaNavBarW2W = visaNavBarW2W;
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
		hcb.append(tipoDocumento);
		hcb.append(documento);
		hcb.append(sexo);
		hcb.append(menuPermisos);
		hcb.append(visaNavBarW2W);
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
		TokenW2W other = (TokenW2W) obj;
		return new EqualsBuilder().append(codigoBanco, other.codigoBanco).append(dirCliente, other.dirCliente)
		        .append(expiracion, other.expiracion).append(numeroCuenta, other.numeroCuenta)
		        .append(tarjeta, other.tarjeta).append(tipoDocumento, other.tipoDocumento)
		        .append(documento, other.documento).append(sexo, other.sexo).append(menuPermisos, other.menuPermisos)
		        .append(visaNavBarW2W, other.visaNavBarW2W).isEquals();
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
		if (tipoDocumento != null) {
			miToken.append("Tipo-Documento=").append(tipoDocumento).append(SALTO_LINEA);
		}
		if (documento != null) {
			miToken.append("Documento=").append(documento).append(SALTO_LINEA);
		}
		if (sexo != null) {
			miToken.append("Sexo=").append(sexo).append(SALTO_LINEA);
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
		if (menuPermisos != null) {
			miToken.append("Menu-Permisos=").append(menuPermisos).append(SALTO_LINEA);
		}
		if (visaNavBarW2W != null) {
			miToken.append("VisaNavBarW2W=").append(visaNavBarW2W).append(SALTO_LINEA);
		}
		return miToken.toString();
	}
}
