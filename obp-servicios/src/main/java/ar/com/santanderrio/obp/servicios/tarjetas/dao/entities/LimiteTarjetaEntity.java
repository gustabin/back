/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class LimiteTarjeta.
 */
@XmlRootElement(name = "limite")
@XmlAccessorType(XmlAccessType.FIELD)
public class LimiteTarjetaEntity {

	/** The descripcion. */
	@XmlAttribute(name = "descripcion")
	private String descripcion;

	/** The pesos. */
	@XmlElement(name = "pesos")
	private String pesos;

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 *            the pesos to set
	 */
	public void setPesos(String pesos) {
		this.pesos = pesos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(descripcion);
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
		LimiteTarjetaEntity other = (LimiteTarjetaEntity) obj;
		return new EqualsBuilder().append(descripcion, other.descripcion).append(pesos, other.pesos).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "LimiteTarjeta [descripcion=" + descripcion + ", pesos=" + pesos + "]";
	}

}
