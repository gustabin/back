/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author
 *
 */
public class CalificacionCrediticiaInEntity {
	// agregar los atributos segun el negocio que corrspondan. Ej: private
	// String extra;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */

	/** The opcion. */
	private String opcion;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal. */
	private String sucursal;

	/** The numero cuenta. */
	private String numeroCuenta;

	/**
	 * Gets the opcion.
	 *
	 * @return the opcion
	 */
	public String getOpcion() {
		return opcion;
	}

	/**
	 * Sets the opcion.
	 *
	 * @param opcion
	 *            the new opcion
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				// agregar los append con los atributos que correspondan
				// .append(extra)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		CalificacionCrediticiaInEntity other = (CalificacionCrediticiaInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb
				// agregar los appends que corresponda segun los atributos
				// cargados, Ej: .append(extra, other.getExtra())
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}
}