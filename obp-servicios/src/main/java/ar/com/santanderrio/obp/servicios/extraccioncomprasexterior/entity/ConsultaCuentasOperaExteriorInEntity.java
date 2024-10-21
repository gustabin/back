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
public class ConsultaCuentasOperaExteriorInEntity {

	/** Cliente. */
	private Cliente cliente;

	/** Tipo consulta T-F. */
	private String tipoConsulta;

	/** Cuentas relacionadas C-S. */
	private String cuentasRelacionadas;

	/** Numero de tarjeta. */
	private String numeroTarjeta;

	/** Tipo de tarjeta. */
	private String tipoTarjeta;

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
	 * Gets the cuentas relacionadas.
	 *
	 * @return the cuentas relacionadas
	 */
	public String getCuentasRelacionadas() {
		return cuentasRelacionadas;
	}

	/**
	 * Sets the cuentas relacionadas.
	 *
	 * @param cuentasRelacionadas
	 *            the new cuentas relacionadas
	 */
	public void setCuentasRelacionadas(String cuentasRelacionadas) {
		this.cuentasRelacionadas = cuentasRelacionadas;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

}
