/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * CambioTarjetaOperaExteriorInEntity.
 *
 * @author Silvina_Luque
 */
public class CambioTarjetaOperaExteriorInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The tipo llamado. */
	private String tipoLlamado;

	/** The tipo modificacion. */
	private String tipoModificacion;

	/** The clave modificacion. */
	private String claveModificacion;

	/** The codigo cambio. */
	private String codigoCambio;

	/** The datos variables. */
	private String datosVariables;

	/**
	 * Gets the tipo llamado.
	 *
	 * @return the tipo llamado
	 */
	public String getTipoLlamado() {
		return tipoLlamado;
	}

	/**
	 * Sets the tipo llamado.
	 *
	 * @param tipoLlamado
	 *            the new tipo llamado
	 */
	public void setTipoLlamado(String tipoLlamado) {
		this.tipoLlamado = tipoLlamado;
	}

	/**
	 * Gets the tipo modificacion.
	 *
	 * @return the tipo modificacion
	 */
	public String getTipoModificacion() {
		return tipoModificacion;
	}

	/**
	 * Sets the tipo modificacion.
	 *
	 * @param tipoModificacion
	 *            the new tipo modificacion
	 */
	public void setTipoModificacion(String tipoModificacion) {
		this.tipoModificacion = tipoModificacion;
	}

	/**
	 * Gets the clave modificacion.
	 *
	 * @return the clave modificacion
	 */
	public String getClaveModificacion() {
		return claveModificacion;
	}

	/**
	 * Sets the clave modificacion.
	 *
	 * @param claveModificacion
	 *            the new clave modificacion
	 */
	public void setClaveModificacion(String claveModificacion) {
		this.claveModificacion = claveModificacion;
	}

	/**
	 * Gets the codigo cambio.
	 *
	 * @return the codigo cambio
	 */
	public String getCodigoCambio() {
		return codigoCambio;
	}

	/**
	 * Sets the codigo cambio.
	 *
	 * @param codigoCambio
	 *            the new codigo cambio
	 */
	public void setCodigoCambio(String codigoCambio) {
		this.codigoCambio = codigoCambio;
	}

	/**
	 * Gets the datos variables.
	 *
	 * @return the datos variables
	 */
	public String getDatosVariables() {
		return datosVariables;
	}

	/**
	 * Sets the datos variables.
	 *
	 * @param datosVariables
	 *            the new datos variables
	 */
	public void setDatosVariables(String datosVariables) {
		this.datosVariables = datosVariables;
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
