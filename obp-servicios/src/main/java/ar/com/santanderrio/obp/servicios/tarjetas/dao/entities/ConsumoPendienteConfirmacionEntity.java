/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConsumoPendienteConfirmacionEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/autorizaciones/tarjeta/
 */
@XmlRootElement(name = "tarjeta")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsumoPendienteConfirmacionEntity {

	/** The codigo tarjeta. */
	@XmlAttribute(name = "codigoTarjeta")
	private String codigoTarjeta;

	/** The dolares. */
	@XmlElement(name = "dolares")
	private String dolares;

	/** The pesos. */
	@XmlElement(name = "pesos")
	private String pesos;

	/** The autorizacion list. */
	@XmlElement(name = "autorizacion")
	private List<AutorizacionEntity> autorizacionList;

	/**
	 * Gets the codigo tarjeta.
	 *
	 * @return the codigo tarjeta
	 */
	public String getCodigoTarjeta() {
		return codigoTarjeta;
	}

	/**
	 * Sets the codigo tarjeta.
	 *
	 * @param codigoTarjeta
	 *            the new codigo tarjeta
	 */
	public void setCodigoTarjeta(String codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}

	/**
	 * Gets the dolares.
	 *
	 * @return the dolares
	 */
	public String getDolares() {
		return dolares;
	}

	/**
	 * Sets the dolares.
	 *
	 * @param dolares
	 *            the new dolares
	 */
	public void setDolares(String dolares) {
		this.dolares = dolares;
	}

	/**
	 * Gets the pesos.
	 *
	 * @return the pesos
	 */
	public String getPesos() {
		return pesos;
	}

	/**
	 * Sets the pesos.
	 *
	 * @param pesos
	 *            the new pesos
	 */
	public void setPesos(String pesos) {
		this.pesos = pesos;
	}

	/**
	 * Gets the autorizacion list.
	 *
	 * @return the autorizacion list
	 */
	public List<AutorizacionEntity> getAutorizacionList() {
		return autorizacionList;
	}

	/**
	 * Sets the autorizacion list.
	 *
	 * @param autorizacionList
	 *            the new autorizacion list
	 */
	public void setAutorizacionList(List<AutorizacionEntity> autorizacionList) {
		this.autorizacionList = autorizacionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(autorizacionList);
		hcb.append(codigoTarjeta);
		hcb.append(dolares);
		hcb.append(pesos);
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
		ConsumoPendienteConfirmacionEntity other = (ConsumoPendienteConfirmacionEntity) obj;
		return new EqualsBuilder().append(autorizacionList, other.autorizacionList)
				.append(codigoTarjeta, other.codigoTarjeta).append(dolares, other.dolares).append(pesos, other.pesos)
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsumoPendienteConfirmacionEntity [codigoTarjeta=" + codigoTarjeta + ", dolares=" + dolares
				+ ", pesos=" + pesos + ", autorizacionList=" + autorizacionList + "]";
	}

}
