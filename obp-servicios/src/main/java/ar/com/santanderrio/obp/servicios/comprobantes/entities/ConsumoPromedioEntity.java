/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConsumoPromedioEntity.
 */
@XmlRootElement(name = "consumoPromedio")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsumoPromedioEntity {

	/** The String dolares. */
	@XmlAttribute(name = "dolares")
	private String dolares;

	/** The String pesos. */
	@XmlAttribute(name = "pesos")
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
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(dolares);
		hcb.append(pesos);
		return hcb.toHashCode();
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
		ConsumoPromedioEntity other = (ConsumoPromedioEntity) obj;

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
		StringBuilder sb = new StringBuilder();
		sb.append(dolares);
		sb.append(", ");
		sb.append(pesos);
		return sb.toString();
	}

}
