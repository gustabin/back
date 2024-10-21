/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaDetalleDatosTarjetaInDTO.
 */
public class ConsultaDetalleDatosTarjetaInDTO {

	/** The cliente. */
	private Cliente cliente;

	/** The tipo tarjeta. */
	private String tipoTarjeta;

	/** The nro cuenta tarjeta. */
	private String nroCuentaTarjeta;

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
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipoTarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the tipoTarjeta to set
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Gets the nro cuenta tarjeta.
	 *
	 * @return the nroCuentaTarjeta
	 */
	public String getNroCuentaTarjeta() {
		return nroCuentaTarjeta;
	}

	/**
	 * Sets the nro cuenta tarjeta.
	 *
	 * @param nroCuentaTarjeta
	 *            the nroCuentaTarjeta to set
	 */
	public void setNroCuentaTarjeta(String nroCuentaTarjeta) {
		this.nroCuentaTarjeta = nroCuentaTarjeta;
	}

}
