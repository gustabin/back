/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.custodia.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class CustodiaInEntity.
 */
public class CustodiaInEntity {

	/** The cuenta. */
	private String cuenta;

	/** The fecha. */
	private Date fecha = new Date();

	/** The tipo custodia. */
	private String tipoEspecie = "FCI";

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fechaDesde
	 *            the new fecha
	 */
	public void setFecha(Date fechaDesde) {
		this.fecha = fechaDesde;
	}

	/**
	 * Gets the tipo custodia.
	 *
	 * @return the tipo custodia
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo custodia.
	 *
	 * @param especie
	 *            the new tipo especie
	 */
	public void setTipoEspecie(String especie) {
		this.tipoEspecie = especie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustodiaInEntity [cuenta=" + cuenta + ", fecha=" + fecha + ", tipoCustodia=" + tipoEspecie + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(tipoEspecie);
		hcb.append(cuenta);
		hcb.append(fecha);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
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
		CustodiaInEntity other = (CustodiaInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(tipoEspecie, other.tipoEspecie);
		eb.append(cuenta, other.cuenta);
		eb.append(fecha, other.fecha);
		return eb.isEquals();
	}

}