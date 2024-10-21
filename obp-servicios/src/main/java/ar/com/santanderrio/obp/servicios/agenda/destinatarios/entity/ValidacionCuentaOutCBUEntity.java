/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class ValidacionCuentaOutCBUEntity.
 */
@Record
public class ValidacionCuentaOutCBUEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The titular. */
	@Field
	private String titular;

	/** The cuit. */
	@Field
	private String cuit;

	/** The cuit 2. */
	@Field
	private String cuit2;

	/** The cuit 3. */
	@Field
	private String cuit3;

	/** The bandes. */
	@Field
	private String bandes;

	/** The long destino. */
	@Field
	private String longDestino;

	/** The cuenta destino. */
	@Field
	private String cuentaDestino;

	/** The tipo cuenta to banelco. */
	@Field
	private String tipoCuentaToBanelco;

	/** The tipo cuenta from banelco. */
	@Field
	private String tipoCuentaFromBanelco;

	/** The banco receptor. */
	@Field
	private String bancoReceptor;

	/** The fiid. */
	@Field
	private String fiid;

	/** The user. */
	@Field
	private String user;

	/**
	 * Instantiates a new validacion cuenta out CBU entity.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigo retorno extendido
	 */
	public ValidacionCuentaOutCBUEntity(String codigoRetornoExtendido) {
		super();
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Instantiates a new validacion cuenta out CBU entity.
	 */
	public ValidacionCuentaOutCBUEntity() {
		super();
	}

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the cuit 2.
	 *
	 * @return the cuit 2
	 */
	public String getCuit2() {
		return cuit2;
	}

	/**
	 * Sets the cuit 2.
	 *
	 * @param cuit2
	 *            the new cuit 2
	 */
	public void setCuit2(String cuit2) {
		this.cuit2 = cuit2;
	}

	/**
	 * Gets the cuit 3.
	 *
	 * @return the cuit 3
	 */
	public String getCuit3() {
		return cuit3;
	}

	/**
	 * Sets the cuit 3.
	 *
	 * @param cuit3
	 *            the new cuit 3
	 */
	public void setCuit3(String cuit3) {
		this.cuit3 = cuit3;
	}

	/**
	 * Gets the bandes.
	 *
	 * @return the bandes
	 */
	public String getBandes() {
		return bandes;
	}

	/**
	 * Sets the bandes.
	 *
	 * @param bandes
	 *            the new bandes
	 */
	public void setBandes(String bandes) {
		this.bandes = bandes;
	}

	/**
	 * Gets the long destino.
	 *
	 * @return the long destino
	 */
	public String getLongDestino() {
		return longDestino;
	}

	/**
	 * Sets the long destino.
	 *
	 * @param longDestino
	 *            the new long destino
	 */
	public void setLongDestino(String longDestino) {
		this.longDestino = longDestino;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuenta destino
	 */
	public String getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the new cuenta destino
	 */
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the tipo cuenta to banelco.
	 *
	 * @return the tipo cuenta to banelco
	 */
	public String getTipoCuentaToBanelco() {
		return tipoCuentaToBanelco;
	}

	/**
	 * Sets the tipo cuenta to banelco.
	 *
	 * @param tipoCuentaToBanelco
	 *            the new tipo cuenta to banelco
	 */
	public void setTipoCuentaToBanelco(String tipoCuentaToBanelco) {
		this.tipoCuentaToBanelco = tipoCuentaToBanelco;
	}

	/**
	 * Gets the tipo cuenta from banelco.
	 *
	 * @return the tipo cuenta from banelco
	 */
	public String getTipoCuentaFromBanelco() {
		return tipoCuentaFromBanelco;
	}

	/**
	 * Sets the tipo cuenta from banelco.
	 *
	 * @param tipoCuentaFromBanelco
	 *            the new tipo cuenta from banelco
	 */
	public void setTipoCuentaFromBanelco(String tipoCuentaFromBanelco) {
		this.tipoCuentaFromBanelco = tipoCuentaFromBanelco;
	}

	/**
	 * Gets the banco receptor.
	 *
	 * @return the banco receptor
	 */
	public String getBancoReceptor() {
		return bancoReceptor;
	}

	/**
	 * Sets the banco receptor.
	 *
	 * @param bancoReceptor
	 *            the new banco receptor
	 */
	public void setBancoReceptor(String bancoReceptor) {
		this.bancoReceptor = bancoReceptor;
	}

	/**
	 * Gets the fiid.
	 *
	 * @return the fiid
	 */
	public String getFiid() {
		return fiid;
	}

	/**
	 * Sets the fiid.
	 *
	 * @param fiid
	 *            the new fiid
	 */
	public void setFiid(String fiid) {
		this.fiid = fiid;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(headerTrama).append(codigoRetornoExtendido).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		ValidacionCuentaOutCBUEntity other = (ValidacionCuentaOutCBUEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(headerTrama, other.headerTrama).append(codigoRetornoExtendido, other.codigoRetornoExtendido)
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("CodigoRetornoExtendido", codigoRetornoExtendido)
				.append("titular", titular).append("cuit", cuit).append("cuit2", cuit2).append("cuit3", cuit3)
				.append("bandes", bandes).append("longDestino", longDestino).append("cuentaDestino", cuentaDestino)
				.append("tipoCuentaToBanelco", tipoCuentaToBanelco)
				.append("tipoCuentaFromBanelco", tipoCuentaFromBanelco).append("bancoReceptor", bancoReceptor)
				.append("fiid", fiid).append("user", user).toString();
	}

}
