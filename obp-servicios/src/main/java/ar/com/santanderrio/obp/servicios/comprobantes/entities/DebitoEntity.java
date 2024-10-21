/*
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
 * The Class DebitoEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/informeDebitosAutomaticos/debitosTarjeta/debito/
 *
 * @author luis.pedro.lopez
 */
@XmlRootElement(name = "debito")
@XmlAccessorType(XmlAccessType.FIELD)
public class DebitoEntity {

	/** The establecimiento. */
	@XmlElement(name = "establecimiento")
	private EstablecimientoEntity establecimiento;

	/** The fecha. */
	@XmlElement(name = "fecha")
	private String fecha;

	/** The descripcion. */
	@XmlElement(name = "descripcion")
	private String descripcion;

	/** The importe. */
	@XmlElement(name = "importe")
	private ImporteEntity importe;

	/**
	 * Gets the establecimiento.
	 *
	 * @return the establecimiento
	 */
	public EstablecimientoEntity getEstablecimiento() {
		return establecimiento;
	}

	/**
	 * Sets the establecimiento.
	 *
	 * @param establecimiento
	 *            the establecimiento to set
	 */
	public void setEstablecimiento(EstablecimientoEntity establecimiento) {
		this.establecimiento = establecimiento;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public ImporteEntity getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(ImporteEntity importe) {
		this.importe = importe;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(establecimiento);
		hcb.append(fecha);
		hcb.append(descripcion);
		hcb.append(importe);
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
		DebitoEntity other = (DebitoEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(establecimiento, other.getEstablecimiento());
		eb.append(fecha, other.getFecha());
		eb.append(descripcion, other.getDescripcion());
		eb.append(importe, other.getImporte());
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
		sb.append(establecimiento);
		sb.append(fecha);
		sb.append(descripcion);
		sb.append(importe);
		return sb.toString();
	}

}
