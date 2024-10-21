/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * ConsultaPrestamoOpenCreditInEntity.
 *
 * @author Silvina_Luque
 */
public class ConsultaPrestamoOpenCreditInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The tipo prestamo. */
	private String tipoPrestamo;

	/** The sucursal. */
	private String sucursal;

	/** The numero prestamo. */
	private String numeroPrestamo;

	/** The fecha del dia. */
	private String fechaDelDia;

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
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the tipo prestamo.
	 *
	 * @return the tipo prestamo
	 */
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * Sets the tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the new tipo prestamo
	 */
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
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
	 * Gets the numero prestamo.
	 *
	 * @return the numero prestamo
	 */
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	/**
	 * Sets the numero prestamo.
	 *
	 * @param numeroPrestamo
	 *            the new numero prestamo
	 */
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	/**
	 * Gets the fecha del dia.
	 *
	 * @return the fecha del dia
	 */
	public String getFechaDelDia() {
		return fechaDelDia;
	}

	/**
	 * Sets the fecha del dia.
	 *
	 * @param fechaDelDia
	 *            the new fecha del dia
	 */
	public void setFechaDelDia(String fechaDelDia) {
		this.fechaDelDia = fechaDelDia;
	}

}
