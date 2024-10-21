/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Datos de entrada para el servicio CNSCTATIT 110.
 */
public class ValidacionCuentaInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** Tipo_Cuenta C2. */
	private String tipoCuenta;

	/** Sucursal_Cuenta N3. */
	private String sucursalCuenta;

	/** Nro_Cuenta N7. */
	private String numeroCuenta;

	/** Informar CUIL A1. */
	private String informarCuil;

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
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursalCuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the sucursalCuenta to set
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the informar cuil.
	 *
	 * @return the informarCuil
	 */
	public String getInformarCuil() {
		return informarCuil;
	}

	/**
	 * Sets the informar cuil.
	 *
	 * @param informarCuil
	 *            the informarCuil to set
	 */
	public void setInformarCuil(String informarCuil) {
		this.informarCuil = informarCuil;
	}

}
