/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaCambioDireccionamientoInEntity.
 */
public class ConsultaCambioDireccionamientoInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The sucursal paquete. */
	private String sucursalPaquete;

	/** The numero paquete. */
	private String numeroPaquete;

	/** The indicador direcciona fondos. */
	private String indicadorDireccionaFondos;

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
	 * Gets the sucursal paquete.
	 *
	 * @return the sucursal paquete
	 */
	public String getSucursalPaquete() {
		return sucursalPaquete;
	}

	/**
	 * Sets the sucursal paquete.
	 *
	 * @param sucursalPaquete
	 *            the new sucursal paquete
	 */
	public void setSucursalPaquete(String sucursalPaquete) {
		this.sucursalPaquete = sucursalPaquete;
	}

	/**
	 * Gets the numero paquete.
	 *
	 * @return the numero paquete
	 */
	public String getNumeroPaquete() {
		return numeroPaquete;
	}

	/**
	 * Sets the numero paquete.
	 *
	 * @param numeroPaquete
	 *            the new numero paquete
	 */
	public void setNumeroPaquete(String numeroPaquete) {
		this.numeroPaquete = numeroPaquete;
	}

	/**
	 * Gets the indicador direcciona fondos.
	 *
	 * @return the indicador direcciona fondos
	 */
	public String getIndicadorDireccionaFondos() {
		return indicadorDireccionaFondos;
	}

	/**
	 * Sets the indicador direcciona fondos.
	 *
	 * @param indicadorDireccionaFondos
	 *            the new indicador direcciona fondos
	 */
	public void setIndicadorDireccionaFondos(String indicadorDireccionaFondos) {
		this.indicadorDireccionaFondos = indicadorDireccionaFondos;
	}

}
