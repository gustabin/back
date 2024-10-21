/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class TraspasoManualInEntity.
 */
public class TraspasoManualInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The cantidad de cuentas. */
	private String cantidadDeCuentas;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The numero cuenta. */
	private String numeroCuenta;

	/**
	 * Gets the cantidad de cuentas.
	 *
	 * @return the cantidad de cuentas
	 */
	public String getCantidadDeCuentas() {
		return cantidadDeCuentas;
	}

	/**
	 * Sets the cantidad de cuentas.
	 *
	 * @param cantidadDeCuentas
	 *            the new cantidad de cuentas
	 */
	public void setCantidadDeCuentas(String cantidadDeCuentas) {
		this.cantidadDeCuentas = cantidadDeCuentas;
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
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
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

}
