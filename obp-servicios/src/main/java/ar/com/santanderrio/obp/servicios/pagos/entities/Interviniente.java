/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class Interviniente.
 */
public class Interviniente extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nombre. */
	private String nombre;

	/** The apellido. */
	private String apellido;

	/** The tipo inscripcion. */
	private String tipoInscripcion;

	/** The cuitcuil. */
	private String cuitcuil;

	/** orden Participacion 001 es titular, el resto son cotitulares. */
	private String ordenParticipacion;

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the apellido.
	 *
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Sets the apellido.
	 *
	 * @param apellido
	 *            the new apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Gets the tipo inscripcion.
	 *
	 * @return the tipo inscripcion
	 */
	public String getTipoInscripcion() {
		return tipoInscripcion;
	}

	/**
	 * Sets the tipo inscripcion.
	 *
	 * @param tipoInscripcion
	 *            the new tipo inscripcion
	 */
	public void setTipoInscripcion(String tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	/**
	 * Gets the cuitcuil.
	 *
	 * @return the cuitcuil
	 */
	public String getCuitcuil() {
		return cuitcuil;
	}

	/**
	 * Sets the cuitcuil.
	 *
	 * @param cuitcuil
	 *            the new cuitcuil
	 */
	public void setCuitcuil(String cuitcuil) {
		this.cuitcuil = cuitcuil;
	}

	/**
	 * Gets the orden participacion.
	 *
	 * @return the orden participacion
	 */
	public String getOrdenParticipacion() {
		return ordenParticipacion;
	}

	/**
	 * Sets the orden participacion.
	 *
	 * @param ordenParticipacion
	 *            the new orden participacion
	 */
	public void setOrdenParticipacion(String ordenParticipacion) {
		this.ordenParticipacion = ordenParticipacion;
	}

	/**
	 * Tiene datos.
	 *
	 * @return true, if successful
	 */
	public boolean tieneDatos() {
		return StringUtils.isNotBlank(this.apellido) && StringUtils.isNotBlank(this.nombre)
				&& StringUtils.isNotBlank(this.cuitcuil);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("nombre", nombre).append("apellido", apellido)
				.append("tipoInscripcion", tipoInscripcion).append("cuitcuil", cuitcuil)
				.append("ordenParticipacion", ordenParticipacion).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nombre);
		hcb.append(apellido);
		hcb.append(tipoInscripcion);
		hcb.append(cuitcuil);
		hcb.append(ordenParticipacion);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
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
		Interviniente other = (Interviniente) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nombre, other.nombre);
		eb.append(apellido, other.apellido);
		eb.append(tipoInscripcion, other.tipoInscripcion);
		eb.append(cuitcuil, other.cuitcuil);
		eb.append(ordenParticipacion, other.ordenParticipacion);
		return eb.isEquals();
	}

}
