/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;

/**
 * The Class ChequeraOutDTO.
 */
public class ChequeraOutDTO {

	/** The cuenta. */
	private AbstractCuenta cuenta;

	/** The sucursal entrega. */
	private String sucursalEntrega;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The domicilio sucursal. */
	private String domicilioSucursal;

	/** The localidad sucursal. */
	private String localidadSucursal;

	/** The fecha entrega. */
	private String fechaEntrega;

	/** The fecha transaccion. */
	private String fechaTransaccion;

	/** The tipo chequera. */
	private String tipoChequera;

	/** The cantidad cheque. */
	private String cantidadCheque;

	/** The cantidad chequera. */
	private String cantidadChequera;

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public AbstractCuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(AbstractCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the sucursal entrega.
	 *
	 * @return the sucursalEntrega
	 */
	public String getSucursalEntrega() {
		return sucursalEntrega;
	}

	/**
	 * Sets the sucursal entrega.
	 *
	 * @param sucursalEntrega
	 *            the sucursalEntrega to set
	 */
	public void setSucursalEntrega(String sucursalEntrega) {
		this.sucursalEntrega = sucursalEntrega;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cuenta).append(cantidadCheque).append(cantidadChequera)
				.append(sucursalEntrega).append(numeroComprobante).toHashCode();
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

		ChequeraOutDTO other = (ChequeraOutDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(cuenta, other.cuenta).append(cantidadCheque, other.cantidadCheque)
				.append(cantidadChequera, other.cantidadChequera).append(sucursalEntrega, other.sucursalEntrega)
				.append(numeroComprobante, other.numeroComprobante).isEquals();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cuenta", cuenta).append("cantidadChequeComun", cantidadCheque)
				.append("cantidadChequeraComun", cantidadChequera).append("sucursalEntrega", sucursalEntrega)
				.append("numeroComprobante", numeroComprobante).toString();
	}

	/**
	 * Gets the domicilio sucursal.
	 *
	 * @return the domicilioSucursal
	 */
	public String getDomicilioSucursal() {
		return domicilioSucursal;
	}

	/**
	 * Sets the domicilio sucursal.
	 *
	 * @param domicilioSucursal
	 *            the domicilioSucursal to set
	 */
	public void setDomicilioSucursal(String domicilioSucursal) {
		this.domicilioSucursal = domicilioSucursal;
	}

	/**
	 * Gets the localidad sucursal.
	 *
	 * @return the localidadSucursal
	 */
	public String getLocalidadSucursal() {
		return localidadSucursal;
	}

	/**
	 * Sets the localidad sucursal.
	 *
	 * @param localidadSucursal
	 *            the localidadSucursal to set
	 */
	public void setLocalidadSucursal(String localidadSucursal) {
		this.localidadSucursal = localidadSucursal;
	}

	/**
	 * Gets the fecha entrega.
	 *
	 * @return the fechaEntrega
	 */
	public String getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * Sets the fecha entrega.
	 *
	 * @param fechaEntrega
	 *            the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * Gets the fecha transaccion.
	 *
	 * @return the fechaTransaccion
	 */
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}

	/**
	 * Sets the fecha transaccion.
	 *
	 * @param fechaTransaccion
	 *            the fechaTransaccion to set
	 */
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	/**
	 * Gets the tipo chequera.
	 *
	 * @return the tipoChequera
	 */
	public String getTipoChequera() {
		return tipoChequera;
	}

	/**
	 * Sets the tipo chequera.
	 *
	 * @param tipoChequera
	 *            the tipoChequera to set
	 */
	public void setTipoChequera(String tipoChequera) {
		this.tipoChequera = tipoChequera;
	}

	/**
	 * Gets the cantidad cheque.
	 *
	 * @return the cantidadCheque
	 */
	public String getCantidadCheque() {
		return cantidadCheque;
	}

	/**
	 * Sets the cantidad cheque.
	 *
	 * @param cantidadCheque
	 *            the cantidadCheque to set
	 */
	public void setCantidadCheque(String cantidadCheque) {
		this.cantidadCheque = cantidadCheque;
	}

	/**
	 * Gets the cantidad chequera.
	 *
	 * @return the cantidadChequera
	 */
	public String getCantidadChequera() {
		return cantidadChequera;
	}

	/**
	 * Sets the cantidad chequera.
	 *
	 * @param cantidadChequera
	 *            the cantidadChequera to set
	 */
	public void setCantidadChequera(String cantidadChequera) {
		this.cantidadChequera = cantidadChequera;
	}

}
