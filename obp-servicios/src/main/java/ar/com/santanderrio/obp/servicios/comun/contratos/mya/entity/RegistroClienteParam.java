/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class RegistroClienteParam.
 */
public class RegistroClienteParam {

	/**
	 * Gets the sucursal origen.
	 *
	 * @return the sucursal origen
	 */
	public String getSucursalOrigen() {
		return sucursalOrigen;
	}

	/**
	 * Sets the sucursal origen.
	 *
	 * @param sucursalOrigen
	 *            the new sucursal origen
	 */
	public void setSucursalOrigen(String sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	/** The dni. */
	private String dni;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The nombre. */
	private String nombre;

	/** The apellido. */
	private String apellido;

	/** The sucursal origen. */
	private String sucursalOrigen;

	/** The nup. */
	private String nup;

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

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
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(this.dni);
		hcb.append(this.fechaNacimiento);
		hcb.append(this.nup);
		return hcb.toHashCode();
	}

	/**
	 * Equaals.
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
		ContratoParam other = (ContratoParam) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(dni, other.getDni());
		eb.append(fechaNacimiento, other.getFechaNacimiento());
		eb.append(nup, other.getNup());
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
		return new ToStringBuilder(this).append("dni", dni).append("fechaNacimiento", fechaNacimiento)
				.append("nup", nup).toString();
	}

}
