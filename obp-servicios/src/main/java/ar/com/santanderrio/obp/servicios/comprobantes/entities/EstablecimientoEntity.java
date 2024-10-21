/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class EstablecimientoEntity. Representa el tag
 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/debito/establecimiento/
 * 
 *
 */
@XmlRootElement(name = "establecimiento")
@XmlAccessorType(XmlAccessType.FIELD)
public class EstablecimientoEntity {

	/** The rubro. */
	@XmlElement(name = "rubro")
	private String rubro;

	/** The cod establecimiento. */
	@XmlElement(name = "codEstablecimiento")
	private String codEstablecimiento;

	/**
	 * Gets the rubro.
	 *
	 * @return the rubro
	 */
	public String getRubro() {
		return rubro;
	}

	/**
	 * Sets the rubro.
	 *
	 * @param rubro
	 *            the rubro to set
	 */
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	/**
	 * Gets the cod establecimiento.
	 *
	 * @return the codEstablecimiento
	 */
	public String getCodEstablecimiento() {
		return codEstablecimiento;
	}

	/**
	 * Sets the cod establecimiento.
	 *
	 * @param codEstablecimiento
	 *            the codEstablecimiento to set
	 */
	public void setCodEstablecimiento(String codEstablecimiento) {
		this.codEstablecimiento = codEstablecimiento;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(rubro);
		hcb.append(codEstablecimiento);
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
		EstablecimientoEntity other = (EstablecimientoEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(rubro, other.getRubro());
		eb.append(codEstablecimiento, other.getCodEstablecimiento());
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
		sb.append(rubro);
		sb.append(codEstablecimiento);
		return sb.toString();
	}

}
