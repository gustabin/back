/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.custodia.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class CustodiaBPInEntity.
 */
public class CustodiaBPInEntity {

	/** The cuenta. */
	private String cuenta;

	/** The fecha. */
	private Date fecha;

	/** The canal. */
	private String canal;

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
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustodiaBPInEntity [cuenta=" + cuenta + ", fecha=" + fecha + ", canal=" + canal + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(canal);
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
		CustodiaBPInEntity other = (CustodiaBPInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(canal, other.canal);
		eb.append(cuenta, other.cuenta);
		eb.append(fecha, other.fecha);
		;
		return eb.isEquals();
	}

}
