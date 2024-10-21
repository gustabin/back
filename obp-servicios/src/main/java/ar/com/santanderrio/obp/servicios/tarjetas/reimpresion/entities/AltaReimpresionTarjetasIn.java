/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AltaReimpresionTarjetasIn.
 */
public class AltaReimpresionTarjetasIn {

	/** The cliente. */
	private Cliente cliente;

	/** The tarjetas credito. */
	private List<TarjetaSolicitada> tarjetasCredito;

	/** The tarjetas debito. */
	private List<TarjetaSolicitada> tarjetasDebito;

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
	 * Gets the tarjetas credito.
	 *
	 * @return the tarjetas credito
	 */
	public List<TarjetaSolicitada> getTarjetasCredito() {
		return tarjetasCredito;
	}

	/**
	 * Sets the tarjetas credito.
	 *
	 * @param tarjetasCredito
	 *            the new tarjetas credito
	 */
	public void setTarjetasCredito(List<TarjetaSolicitada> tarjetasCredito) {
		this.tarjetasCredito = tarjetasCredito;
	}

	/**
	 * Gets the tarjetas debito.
	 *
	 * @return the tarjetas debito
	 */
	public List<TarjetaSolicitada> getTarjetasDebito() {
		return tarjetasDebito;
	}

	/**
	 * Sets the tarjetas debito.
	 *
	 * @param tarjetasDebito
	 *            the new tarjetas debito
	 */
	public void setTarjetasDebito(List<TarjetaSolicitada> tarjetasDebito) {
		this.tarjetasDebito = tarjetasDebito;
	}

}
