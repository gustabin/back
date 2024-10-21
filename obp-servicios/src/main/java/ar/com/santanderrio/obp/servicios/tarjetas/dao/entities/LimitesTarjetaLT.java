/*
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
 * The Class LimitesTarjeta. Clase que representa el tag
 * /tarjetas/tarjeta/document/saldoenCuenta/limites/
 */
@XmlRootElement(name = "limites")
@XmlAccessorType(XmlAccessType.FIELD)
public class LimitesTarjetaLT {

	/** The limites. */
	@XmlElement(name = "limite")
	private List<LimitesEntity> limites;

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public List<LimitesEntity> getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the limites to set
	 */
	public void setLimites(List<LimitesEntity> limites) {
		this.limites = limites;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(limites);
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
		LimitesTarjetaLT other = (LimitesTarjetaLT) obj;
		return new EqualsBuilder().append(limites, other.limites).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "LimitesTarjeta [limites=" + limites + "]";
	}

}
