/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author juan.pablo.picate
 *
 */
public class ConsultaPlazoFijoBPrivInEntity {

	/** the cuenta. */
	private String cuenta;

	/** the tipo PF. */
	private String tipoPF;

	/** the fecha. */
	private String fecha;

	/** The cliente. */
	private Cliente cliente;

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
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the tipo PF.
	 *
	 * @return the tipoPF
	 */
	public String getTipoPF() {
		return tipoPF;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param tipoPF
	 *            the tipoPF to set
	 */
	public void setTipoPF(String tipoPF) {
		this.tipoPF = tipoPF;
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
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cuenta).append(tipoPF).append(fecha).toHashCode();
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

		ConsultaPlazoFijoBPrivInEntity other = (ConsultaPlazoFijoBPrivInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append(cuenta, other.cuenta).append(tipoPF, other.tipoPF).append(fecha,
				other.fecha);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cuenta", cuenta).append("tipoPF", tipoPF).append("fecha", fecha)
				.toString();
	}
}
