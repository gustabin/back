/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.dto;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class SolicitudMillasAadvantageInDTO.
 */
public class SolicitudMillasAadvantageInDTO {

	/** The cliente. */
	private Cliente cliente;
	
	/** The mostrar mas meses. */
	private Boolean mostrarMasMeses;

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
	 * Gets the mostrar mas meses.
	 *
	 * @return the mostrar mas meses
	 */
	public Boolean getMostrarMasMeses() {
		return mostrarMasMeses;
	}

	/**
	 * Sets the mostrar mas meses.
	 *
	 * @param mostrarMasMeses
	 *            the new mostrar mas meses
	 */
	public void setMostrarMasMeses(Boolean mostrarMasMeses) {
		this.mostrarMasMeses = mostrarMasMeses;
	}
	
}
