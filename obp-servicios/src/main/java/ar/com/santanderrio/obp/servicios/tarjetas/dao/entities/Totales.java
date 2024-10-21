/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class Totales. Clase que representa el tag
 * /tarjetas/tarjeta/document/CuotasPendientes/totales/
 */
@XmlRootElement(name = "totales")
@XmlAccessorType(XmlAccessType.FIELD)
public class Totales {

	/** The dolares. */
	@XmlElement(name = "dolares")
	private String dolares;

	/** The pesos. */
	@XmlElement(name = "pesos")
	private String pesos;

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
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(dolares).append(pesos).toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
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
		Totales other = (Totales) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(dolares, other.getDolares());
		eb.append(pesos, other.getPesos());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Totales [dolares=" + dolares + ", pesos=" + pesos + "]";
	}

}
