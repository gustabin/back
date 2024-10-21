/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class Linea.
 */
public class LineaView {

	/** The fecha. */
	private String fecha;

	/** The fecha mobile. */
	private String fechaMobile;

	/** The movimiento. */
	private String movimiento;

	/** The descripcion. */
	private String descripcion;

	/** The importe. */
	private String importe;

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
	 * Gets the fecha mobile.
	 *
	 * @return the fecha mobile
	 */
	public String getFechaMobile() {
		return fechaMobile;
	}

	/**
	 * Sets the fecha mobile.
	 *
	 * @param fechaMobile
	 *            the new fecha mobile
	 */
	public void setFechaMobile(String fechaMobile) {
		this.fechaMobile = fechaMobile;
	}

	/**
	 * Gets the movimiento.
	 *
	 * @return the movimiento
	 */
	public String getMovimiento() {
		return movimiento;
	}

	/**
	 * Sets the movimiento.
	 *
	 * @param movimiento
	 *            the new movimiento
	 */
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
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
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fecha);
		hcb.append(movimiento);
		hcb.append(descripcion);
		hcb.append(importe);

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
		LineaView other = (LineaView) obj;

		EqualsBuilder eb = new EqualsBuilder();

		eb.append(fecha, other.getFecha());
		eb.append(movimiento, other.getMovimiento());
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
		return "MonederoTag [fecha=" + fecha + "movimiento=" + movimiento + ", descripcion=" + descripcion
				+ ", importe=" + importe + "]";
	}
}
