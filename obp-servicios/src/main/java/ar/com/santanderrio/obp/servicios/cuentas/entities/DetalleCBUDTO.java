/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class DetalleCBUDTO.
 */
public class DetalleCBUDTO {

	/** The cliente. */
	private Cliente cliente;

	/** The cuenta. */
	private Cuenta cuenta;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The detalles terminal. */
	private String detallesTerminal;

	/** The ip. */
	private String ip;

	/** The alias CBU. */
	private String aliasCBU;

	/** The alias anterior. */
	private String aliasAnterior;

	/** The cbu. */
	private String cbu;

	/** The cuit. */
	private String cuit;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The reasigna. */
	private String reasigna;

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
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the detalles terminal.
	 *
	 * @return the detalles terminal
	 */
	public String getDetallesTerminal() {
		return detallesTerminal;
	}

	/**
	 * Sets the detalles terminal.
	 *
	 * @param detallesTerminal
	 *            the new detalles terminal
	 */
	public void setDetallesTerminal(String detallesTerminal) {
		this.detallesTerminal = detallesTerminal;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the alias CBU.
	 *
	 * @return the alias CBU
	 */
	public String getAliasCBU() {
		return aliasCBU;
	}

	/**
	 * Sets the alias CBU.
	 *
	 * @param aliasCBU
	 *            the new alias CBU
	 */
	public void setAliasCBU(String aliasCBU) {
		this.aliasCBU = aliasCBU;
	}

	/**
	 * Gets the alias anterior.
	 *
	 * @return the alias anterior
	 */
	public String getAliasAnterior() {
		return aliasAnterior;
	}

	/**
	 * Sets the alias anterior.
	 *
	 * @param aliasAnterior
	 *            the new alias anterior
	 */
	public void setAliasAnterior(String aliasAnterior) {
		this.aliasAnterior = aliasAnterior;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the reasigna.
	 *
	 * @return the reasigna
	 */
	public String getReasigna() {
		return reasigna;
	}

	/**
	 * Sets the reasigna.
	 *
	 * @param reasigna
	 *            the new reasigna
	 */
	public void setReasigna(String reasigna) {
		this.reasigna = reasigna;
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

}
