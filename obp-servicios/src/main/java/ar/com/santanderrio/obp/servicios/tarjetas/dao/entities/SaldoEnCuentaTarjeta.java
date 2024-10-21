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
 * The Class SaldoEnCuentaTarjeta. Clase que representa el tag
 * /tarjetas/tarjeta/document/saldoenCuenta/../../
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SaldoEnCuentaTarjeta {

	/** The descripcion. */
	@XmlAttribute(name = "descripcion")
	private String descripcion;

	/** The pesos. */
	@XmlElement(name = "pesos")
	private String pesos;

	/** The dolares. */
	@XmlElement(name = "dolares")
	private String dolares;

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
	 *            the dolares to set
	 */
	public void setDolares(String dolares) {
		this.dolares = dolares;
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
		SaldoEnCuentaTarjeta other = (SaldoEnCuentaTarjeta) obj;
		return new EqualsBuilder().append(descripcion, other.descripcion).append(dolares, other.dolares)
				.append(pesos, other.pesos).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SaldoEnCuentaTarjeta [descripcion=" + descripcion + ", pesos=" + pesos + ", dolares=" + dolares + "]";
	}

}
