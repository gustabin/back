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
 * The Class FechaTarjeta. Clase que representa el tag
 * /tarjetas/tarjeta/document/saldoenCuenta/fechas/fecha/
 */
@XmlRootElement(name = "fecha")
@XmlAccessorType(XmlAccessType.FIELD)
public class FechaTarjetaEntity {

	/** The descripcion. */
	@XmlAttribute(name = "descripcion")
	private String descripcion;

	/** The cierre. */
	@XmlElement(name = "cierre")
	private String cierre;

	/** The vencimiento. */
	@XmlElement(name = "vencimiento")
	private String vencimiento;

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
	 * Gets the cierre.
	 *
	 * @return the cierre
	 */
	public String getCierre() {
		return cierre;
	}

	/**
	 * Sets the cierre.
	 *
	 * @param cierre
	 *            the cierre to set
	 */
	public void setCierre(String cierre) {
		this.cierre = cierre;
	}

	/**
	 * Gets the vencimiento.
	 *
	 * @return the vencimiento
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * Sets the vencimiento.
	 *
	 * @param vencimiento
	 *            the vencimiento to set
	 */
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cierre);
		hcb.append(descripcion);
		hcb.append(vencimiento);
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
		FechaTarjetaEntity other = (FechaTarjetaEntity) obj;
		return new EqualsBuilder().append(cierre, other.cierre).append(descripcion, other.descripcion)
				.append(vencimiento, other.vencimiento).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FechaTarjeta [descripcion=" + descripcion + ", cierre=" + cierre + ", vencimiento=" + vencimiento + "]";
	}

}
