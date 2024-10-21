/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ClasificacionDeuda.
 */
public class ClasificacionDeuda {

	/** The situacion bcra. */
	private String situacionBcra;

	/** The descripcion. */
	private String descripcion;

	/** The fecha clasificacion. */
	private Date fechaClasificacion;

	/**
	 * Gets the situacion bcra.
	 *
	 * @return the situacion bcra
	 */
	public String getSituacionBcra() {
		return situacionBcra;
	}

	/**
	 * Sets the situacion bcra.
	 *
	 * @param situacionBcra
	 *            the new situacion bcra
	 */
	public void setSituacionBcra(String situacionBcra) {
		this.situacionBcra = situacionBcra;
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
	 * Gets the fecha clasificacion.
	 *
	 * @return the fechaClasificacion
	 */
	public Date getFechaClasificacion() {
		return fechaClasificacion;
	}

	/**
	 * Sets the fecha clasificacion.
	 *
	 * @param fechaClasificacion
	 *            the new fecha clasificacion
	 */
	public void setFechaClasificacion(Date fechaClasificacion) {
		this.fechaClasificacion = fechaClasificacion;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(situacionBcra);
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
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ClasificacionDeuda other = (ClasificacionDeuda) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(situacionBcra, other.situacionBcra);
		eb.append(descripcion, other.descripcion);
		return eb.isEquals();

	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("situacionBcra", situacionBcra).append("descripcion", descripcion)
				.append("fechaClasificacion", fechaClasificacion).toString();
	}
}
