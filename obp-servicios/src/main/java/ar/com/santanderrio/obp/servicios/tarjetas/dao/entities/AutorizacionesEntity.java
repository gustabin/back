/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class AutorizacionesEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/autorizaciones/
 */
@XmlRootElement(name = "autorizaciones")
@XmlAccessorType(XmlAccessType.FIELD)
public class AutorizacionesEntity {

	/** The totales. */
	@XmlElement(name = "totales")
	private ConsumosCuentasAuthEntity totales;

	/** The consumo pendiente confirmacion list. */
	@XmlElement(name = "tarjeta")
	private List<ConsumoPendienteConfirmacionEntity> consumoPendienteConfirmacionList;

	/**
	 * Gets the totales.
	 *
	 * @return the totales
	 */
	public ConsumosCuentasAuthEntity getTotales() {
		return totales;
	}

	/**
	 * Sets the totales.
	 *
	 * @param totales
	 *            the new totales
	 */
	public void setTotales(ConsumosCuentasAuthEntity totales) {
		this.totales = totales;
	}

	/**
	 * Gets the consumo pendiente confirmacion list.
	 *
	 * @return the consumo pendiente confirmacion list
	 */
	public List<ConsumoPendienteConfirmacionEntity> getConsumoPendienteConfirmacionList() {
		return consumoPendienteConfirmacionList;
	}

	/**
	 * Sets the tarjeta pendiente confirmacion list.
	 *
	 * @param consumoPendienteConfirmacionList
	 *            the new tarjeta pendiente confirmacion list
	 */
	public void setTarjetaPendienteConfirmacionList(
			List<ConsumoPendienteConfirmacionEntity> consumoPendienteConfirmacionList) {
		this.consumoPendienteConfirmacionList = consumoPendienteConfirmacionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(consumoPendienteConfirmacionList);
		hcb.append(totales);
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
		AutorizacionesEntity other = (AutorizacionesEntity) obj;
		return new EqualsBuilder().append(consumoPendienteConfirmacionList, other.consumoPendienteConfirmacionList)
				.append(totales, other.totales).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AutorizacionesEntity [totales=" + totales + ", consumoPendienteConfirmacionList="
				+ consumoPendienteConfirmacionList + "]";
	}

}
