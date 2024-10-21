/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * ConsultaCuentasExteriorInEntity.
 *
 * @author Silvina_Luque
 */
public class ConsultaTarjetasOperaExteriorInEntity {

	/** Cliente. */
	private Cliente cliente;

	/** Nup. */
	private String nup;

	/** Tipo consulta P - S. */
	private String tipoConsulta;

	/** datosADevolver T - F. */
	private String datosADevolver;

	/** cantidadPedida. */
	private String cantidadPedida;

	/** datos adicionales. */
	private String datosAdicionaes;

	/** clave segundo llamado. */
	private String claveSegundoLlamado;

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
	 * Gets the tipo consulta.
	 *
	 * @return the tipo consulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * Sets the tipo consulta.
	 *
	 * @param tipoConsulta
	 *            the new tipo consulta
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * Gets the datos A devolver.
	 *
	 * @return the datos A devolver
	 */
	public String getDatosADevolver() {
		return datosADevolver;
	}

	/**
	 * Sets the datos A devolver.
	 *
	 * @param datosADevolver
	 *            the new datos A devolver
	 */
	public void setDatosADevolver(String datosADevolver) {
		this.datosADevolver = datosADevolver;
	}

	/**
	 * Gets the cantidad pedida.
	 *
	 * @return the cantidad pedida
	 */
	public String getCantidadPedida() {
		return cantidadPedida;
	}

	/**
	 * Sets the cantidad pedida.
	 *
	 * @param cantidadPedida
	 *            the new cantidad pedida
	 */
	public void setCantidadPedida(String cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	/**
	 * Gets the datos adicionaes.
	 *
	 * @return the datos adicionaes
	 */
	public String getDatosAdicionaes() {
		return datosAdicionaes;
	}

	/**
	 * Sets the datos adicionaes.
	 *
	 * @param datosAdicionaes
	 *            the new datos adicionaes
	 */
	public void setDatosAdicionaes(String datosAdicionaes) {
		this.datosAdicionaes = datosAdicionaes;
	}

	/**
	 * Gets the clave segundo llamado.
	 *
	 * @return the clave segundo llamado
	 */
	public String getClaveSegundoLlamado() {
		return claveSegundoLlamado;
	}

	/**
	 * Sets the clave segundo llamado.
	 *
	 * @param claveSegundoLlamado
	 *            the new clave segundo llamado
	 */
	public void setClaveSegundoLlamado(String claveSegundoLlamado) {
		this.claveSegundoLlamado = claveSegundoLlamado;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

}
