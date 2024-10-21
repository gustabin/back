/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class EstablecimientoEntity. Representa el tag
 * /tarjetas/tarjeta/document/movimientos/tarjeta/../establecimiento/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "establecimiento")
@XmlAccessorType(XmlAccessType.FIELD)
public class EstablecimientoEntity {

	/** The String codigo. */
	@XmlAttribute(name = "codigo")
	private String codigo;

	/** The String descripcion. */
	@XmlValue
	private String descripcion;

	/**
	 * Getter de codigo.
	 * 
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Setter de codigo.
	 * 
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Getter de descripcion.
	 * 
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Setter de descripcion.
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
		hcb.append(codigo);
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
		EstablecimientoEntity other = (EstablecimientoEntity) obj;
		return new EqualsBuilder().append(codigo, other.codigo).append(descripcion, other.descripcion).isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Establecimiento [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}

}
