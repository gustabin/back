/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ChequeraInDTO.
 */
public class ChequeraInDTO {

	/** The cuenta. */
	private String cuenta;

	/** The cantidad cheque comun. */
	private String cantidadChequeComun;

	/** The cantidad cheque pago diferido. */
	private String cantidadChequePagoDiferido;

	/** The cantidad chequera comun. */
	private String cantidadChequeraComun;

	/** The cantidad chequera pago diferido. */
	private String cantidadChequeraPagoDiferido;

	/** The cliente. */
	private Cliente cliente;

	/** The pedido chequera comun. */
	private boolean pedidoChequeraComun;

	/** The pedido chequera pago diferido. */
	private boolean pedidoChequeraPagoDiferido;

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
	 * Gets the cantidad cheque comun.
	 *
	 * @return the cantidadChequeComun
	 */
	public String getCantidadChequeComun() {
		return cantidadChequeComun;
	}

	/**
	 * Sets the cantidad cheque comun.
	 *
	 * @param cantidadChequeComun
	 *            the cantidadChequeComun to set
	 */
	public void setCantidadChequeComun(String cantidadChequeComun) {
		this.cantidadChequeComun = cantidadChequeComun;
	}

	/**
	 * Gets the cantidad cheque pago diferido.
	 *
	 * @return the cantidadChequePagoDiferido
	 */
	public String getCantidadChequePagoDiferido() {
		return cantidadChequePagoDiferido;
	}

	/**
	 * Sets the cantidad cheque pago diferido.
	 *
	 * @param cantidadChequePagoDiferido
	 *            the cantidadChequePagoDiferido to set
	 */
	public void setCantidadChequePagoDiferido(String cantidadChequePagoDiferido) {
		this.cantidadChequePagoDiferido = cantidadChequePagoDiferido;
	}

	/**
	 * Gets the cantidad chequera comun.
	 *
	 * @return the cantidadChequeraComun
	 */
	public String getCantidadChequeraComun() {
		return cantidadChequeraComun;
	}

	/**
	 * Sets the cantidad chequera comun.
	 *
	 * @param cantidadChequeraComun
	 *            the cantidadChequeraComun to set
	 */
	public void setCantidadChequeraComun(String cantidadChequeraComun) {
		this.cantidadChequeraComun = cantidadChequeraComun;
	}

	/**
	 * Gets the cantidad chequera pago diferido.
	 *
	 * @return the cantidadChequeraPagoDiferido
	 */
	public String getCantidadChequeraPagoDiferido() {
		return cantidadChequeraPagoDiferido;
	}

	/**
	 * Sets the cantidad chequera pago diferido.
	 *
	 * @param cantidadChequeraPagoDiferido
	 *            the cantidadChequeraPagoDiferido to set
	 */
	public void setCantidadChequeraPagoDiferido(String cantidadChequeraPagoDiferido) {
		this.cantidadChequeraPagoDiferido = cantidadChequeraPagoDiferido;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cuenta).append(cantidadChequeComun).append(cantidadChequePagoDiferido)
				.append(cantidadChequeraComun).append(cantidadChequeraPagoDiferido).toHashCode();
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

		ChequeraInDTO other = (ChequeraInDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(cuenta, other.cuenta).append(cantidadChequeComun, other.cantidadChequeComun)
				.append(cantidadChequePagoDiferido, other.cantidadChequePagoDiferido)
				.append(cantidadChequeraComun, other.cantidadChequeraComun)
				.append(cantidadChequeraPagoDiferido, other.cantidadChequeraPagoDiferido).isEquals();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cuenta", cuenta).append("cantidadChequeComun", cantidadChequeComun)
				.append("cantidadChequePagoDiferido", cantidadChequePagoDiferido)
				.append("cantidadChequeraComun", cantidadChequeraComun)
				.append("cantidadChequeraPagoDiferido", cantidadChequeraPagoDiferido).toString();
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

	/**
	 * Checks if is pedido chequera comun.
	 *
	 * @return true, if is pedido chequera comun
	 */
	public boolean isPedidoChequeraComun() {
		return pedidoChequeraComun;
	}

	/**
	 * Sets the pedido chequera comun.
	 *
	 * @param pedidoChequeraComun
	 *            the new pedido chequera comun
	 */
	public void setPedidoChequeraComun(boolean pedidoChequeraComun) {
		this.pedidoChequeraComun = pedidoChequeraComun;
	}

	/**
	 * Checks if is pedido chequera pago diferido.
	 *
	 * @return true, if is pedido chequera pago diferido
	 */
	public boolean isPedidoChequeraPagoDiferido() {
		return pedidoChequeraPagoDiferido;
	}

	/**
	 * Sets the pedido chequera pago diferido.
	 *
	 * @param pedidoChequeraPagoDiferido
	 *            the new pedido chequera pago diferido
	 */
	public void setPedidoChequeraPagoDiferido(boolean pedidoChequeraPagoDiferido) {
		this.pedidoChequeraPagoDiferido = pedidoChequeraPagoDiferido;
	}
}
