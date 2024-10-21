/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;

/**
 * The Class ClienteConSaldoResponse.
 */
public class ClienteConSaldoResponse {

	/** The cliente. */
	private Cliente cliente;

	/** The codigo respuesta. */
	private Integer codigoRespuesta;

	/** The estado respuesta. */
	private EstadoRespuesta estadoRespuesta;

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
	 * Gets the codigo respuesta.
	 *
	 * @return the codigoRespuesta
	 */
	public Integer getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * Sets the codigo respuesta.
	 *
	 * @param codigoRespuesta
	 *            the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * Gets the estado respuesta.
	 *
	 * @return the estadoRespuesta
	 */
	public EstadoRespuesta getEstadoRespuesta() {
		return estadoRespuesta;
	}

	/**
	 * Sets the estado respuesta.
	 *
	 * @param estadoRespuesta
	 *            the estadoRespuesta to set
	 */
	public void setEstadoRespuesta(EstadoRespuesta estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cliente", cliente).append("codigoRespuesta", codigoRespuesta)
				.append("estadoRespuesta", estadoRespuesta).toString();
	}

}
