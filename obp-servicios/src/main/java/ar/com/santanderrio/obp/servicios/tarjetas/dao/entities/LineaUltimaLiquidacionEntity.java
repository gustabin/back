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
 * The Class LineaUltimaLiquidacionEntity. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/detalleLiquidacion/linea/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "linea")
@XmlAccessorType(XmlAccessType.FIELD)
public class LineaUltimaLiquidacionEntity {

	/** The String descripcion. */
	@XmlElement(name = "descripcion")
	private String descripcion;

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
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(descripcion);
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
		LineaUltimaLiquidacionEntity other = (LineaUltimaLiquidacionEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(descripcion, other.getDescripcion());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "LineaUltimaLiquidacionEntity [descripcion=" + descripcion + "]";
	}

}
