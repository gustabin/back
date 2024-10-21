/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ModificarLimiteDebitoEntity.
 */
public class ModificarLimiteDebitoEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The num tarjeta banelco. */
	private String numTarjetaBanelco;

	/** The clase. */
	private String clase;

	/** The monto seleccionado. */
	private String montoSeleccionado;

	/** The monto actual. */
	private String montoActual;

	/**
	 * Gets the num tarjeta banelco.
	 *
	 * @return the num tarjeta banelco
	 */
	public String getNumTarjetaBanelco() {
		return numTarjetaBanelco;
	}

	/**
	 * Sets the num tarjeta banelco.
	 *
	 * @param numTarjetaBanelco
	 *            the new num tarjeta banelco
	 */
	public void setNumTarjetaBanelco(String numTarjetaBanelco) {
		this.numTarjetaBanelco = numTarjetaBanelco;
	}

	/**
	 * Gets the clase.
	 *
	 * @return the clase
	 */
	public String getClase() {
		return clase;
	}

	/**
	 * Sets the clase.
	 *
	 * @param clase
	 *            the new clase
	 */
	public void setClase(String clase) {
		this.clase = clase;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((numTarjetaBanelco == null) ? 0 : numTarjetaBanelco.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ModificarLimiteDebitoEntity other = (ModificarLimiteDebitoEntity) obj;
		if (clase == null) {
			if (other.clase != null) {
				return false;
			}
		} else if (!clase.equals(other.clase)) {
			return false;
		}
		if (cliente == null) {
			if (other.cliente != null) {
				return false;
			}
		} else if (!cliente.equals(other.cliente)) {
			return false;
		}
		if (numTarjetaBanelco == null) {
			if (other.numTarjetaBanelco != null) {
				return false;
			}
		} else if (!numTarjetaBanelco.equals(other.numTarjetaBanelco)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the monto seleccionado.
	 *
	 * @return the montoSeleccionado
	 */
	public String getMontoSeleccionado() {
		return montoSeleccionado;
	}

	/**
	 * Sets the monto seleccionado.
	 *
	 * @param montoSeleccionado
	 *            the montoSeleccionado to set
	 */
	public void setMontoSeleccionado(String montoSeleccionado) {
		this.montoSeleccionado = montoSeleccionado;
	}

	/**
	 * Gets the monto actual.
	 *
	 * @return the montoActual
	 */
	public String getMontoActual() {
		return montoActual;
	}

	/**
	 * Sets the monto actual.
	 *
	 * @param montoActual
	 *            the montoActual to set
	 */
	public void setMontoActual(String montoActual) {
		this.montoActual = montoActual;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModificarLimiteDebitoEntity [cliente=" + cliente + ", numTarjetaBanelco=" + numTarjetaBanelco
				+ ", clase=" + clase + "]";
	}

}
