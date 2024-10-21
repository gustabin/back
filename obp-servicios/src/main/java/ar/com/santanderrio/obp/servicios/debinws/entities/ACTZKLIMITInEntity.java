/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * ACTZKLIMITInEntity.
 *
 * @author leandro_ibaceta
 */
public class ACTZKLIMITInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The sucursal cuenta debito. */
	private String sucursalCuentaDebito;

	/** The nro cuenta debito. */
	private String nroCuentaDebito;

	/** The importe monto. */
	private String importeMonto;

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
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipoCuentaDebito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the tipoCuentaDebito to set
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursalCuentaDebito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the sucursalCuentaDebito to set
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the nro cuenta debito.
	 *
	 * @return the nroCuentaDebito
	 */
	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	/**
	 * Sets the nro cuenta debito.
	 *
	 * @param nroCuentaDebito
	 *            the nroCuentaDebito to set
	 */
	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	/**
	 * Gets the importe monto.
	 *
	 * @return the importeMonto
	 */
	public String getImporteMonto() {
		return importeMonto;
	}

	/**
	 * Sets the importe monto.
	 *
	 * @param importeMonto
	 *            the importeMonto to set
	 */
	public void setImporteMonto(String importeMonto) {
		this.importeMonto = importeMonto;
	}

}
