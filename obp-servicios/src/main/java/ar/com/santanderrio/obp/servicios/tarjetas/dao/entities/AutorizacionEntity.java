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
 * The Class AutorizacionEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/autorizaciones/tarjeta/autorizacion/
 */
@XmlRootElement(name = "autorizacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class AutorizacionEntity {

	/** The Class Establecimiento. */
	@XmlElement(name = "establecimiento")
	private EstablecimientoEntity establecimiento;

	/** The codigo. */
	@XmlElement(name = "codigo")
	private String codigo;

	/** The descripcion. */
	@XmlElement(name = "descripcion")
	private String descripcion;

	/** The fecha. */
	@XmlElement(name = "fecha")
	private String fecha;

	/** The importe. */
	@XmlElement(name = "importe")
	private ImporteEntity importe;

	/** The internacional. */
	@XmlElement(name = "internacional")
	private String internacional;

	/** The moneda. */
	@XmlElement(name = "moneda")
	private String moneda;

	/** The tipo. */
	@XmlElement(name = "tipo")
	private String tipo;

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
	 *            the new establecimiento
	 */
	public void setEstablecimiento(EstablecimientoEntity establecimiento) {
		this.establecimiento = establecimiento;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 *            the new importe
	 */
	public void setImporte(ImporteEntity importe) {
		this.importe = importe;
	}

	/**
	 * Gets the internacional.
	 *
	 * @return the internacional
	 */
	public String getInternacional() {
		return internacional;
	}

	/**
	 * Sets the internacional.
	 *
	 * @param internacional
	 *            the new internacional
	 */
	public void setInternacional(String internacional) {
		this.internacional = internacional;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		hcb.append(establecimiento);
		hcb.append(fecha);
		hcb.append(importe);
		hcb.append(internacional);
		hcb.append(moneda);
		hcb.append(tipo);
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
		AutorizacionEntity other = (AutorizacionEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigo, other.getCodigo());
		eb.append(descripcion, other.getDescripcion());
		eb.append(establecimiento, other.getEstablecimiento());
		eb.append(fecha, other.getFecha());
		eb.append(importe, other.getImporte());
		eb.append(internacional, other.getInternacional());
		eb.append(moneda, other.getMoneda());
		eb.append(tipo, other.getTipo());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AutorizacionEntity [establecimiento=" + establecimiento + ", codigo=" + codigo + ", descripcion="
				+ descripcion + ", fecha=" + fecha + ", importe=" + importe + ", internacional=" + internacional
				+ ", moneda=" + moneda + ", tipo=" + tipo + "]";
	}

}
