/**
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
 * The Class FechaUltimaLiquidacion. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/fechas/fecha/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "fecha")
@XmlAccessorType(XmlAccessType.FIELD)
public class FechaUltimaLiquidacion {

	/** The String cierre. */
	@XmlElement(name = "cierre")
	private String cierre;

	/** The String descripcion. */
	@XmlElement(name = "descripcion")
	private String descripcion;

	/** The String vencimiento. */
	@XmlElement(name = "vencimiento")
	private String vencimiento;

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

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cierre);
		hcb.append(descripcion);
		hcb.append(vencimiento);
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
		FechaUltimaLiquidacion other = (FechaUltimaLiquidacion) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cierre, other.getCierre());
		eb.append(descripcion, other.getDescripcion());
		eb.append(vencimiento, other.getVencimiento());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "FechaUltimaLiquidacion [cierre=" + cierre + ", descripcion=" + descripcion + ", vencimiento="
				+ vencimiento + "]";
	}

}
