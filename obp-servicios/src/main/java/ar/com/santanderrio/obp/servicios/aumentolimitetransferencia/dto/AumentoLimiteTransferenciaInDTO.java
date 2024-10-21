/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AumentoLimiteTransferenciaInDTO.
 */
public class AumentoLimiteTransferenciaInDTO {

	/** The nro cuenta. */
	private String nroCuenta;

	/** The tipo cuenta descripcion. */
	private String tipoCuentaDescripcion;

	/** The nro cuenta destino. */
	private String nroCuentaDestino;

	/** The tipo cuenta destino descripcion. */
	private String tipoCuentaDestinoDescripcion;

	/** The importe. */
	private String importe;

	/** The moneda. */
	private String moneda;

	/** The fecha ejecucion. */
	private String fechaEjecucion;

	/** The cliente. */
	private Cliente cliente;

	/** The is rio rio. */
	private boolean isRioRio;

	/** The cuenta propia. */
	private boolean cuentaPropia;

	/** The alias destino. */
	private String aliasDestino;

	/** The titular. */
	private String titular;

	/** The cbu. */
	private String cbu;

	/** The banco. */
	private String banco;

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
	 * Gets the nro cuenta destino.
	 *
	 * @return the nro cuenta destino
	 */
	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	/**
	 * Sets the nro cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the new nro cuenta destino
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Checks if is rio rio.
	 *
	 * @return true, if is rio rio
	 */
	public boolean isRioRio() {
		return isRioRio;
	}

	/**
	 * Sets the rio rio.
	 *
	 * @param isRioRio
	 *            the new rio rio
	 */
	public void setRioRio(boolean isRioRio) {
		this.isRioRio = isRioRio;
	}

	/**
	 * Checks if is cuenta propia.
	 *
	 * @return true, if is cuenta propia
	 */
	public boolean isCuentaPropia() {
		return cuentaPropia;
	}

	/**
	 * Sets the cuenta propia.
	 *
	 * @param cuentaPropia
	 *            the new cuenta propia
	 */
	public void setCuentaPropia(boolean cuentaPropia) {
		this.cuentaPropia = cuentaPropia;
	}

	/**
	 * Gets the alias destino.
	 *
	 * @return the alias destino
	 */
	public String getAliasDestino() {
		return aliasDestino;
	}

	/**
	 * Sets the alias destino.
	 *
	 * @param aliasDestino
	 *            the new alias destino
	 */
	public void setAliasDestino(String aliasDestino) {
		this.aliasDestino = aliasDestino;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
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
	 * Gets the banco.
	 *
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * Sets the banco.
	 *
	 * @param banco
	 *            the new banco
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * Gets the tipo cuenta descripcion.
	 *
	 * @return the tipo cuenta descripcion
	 */
	public String getTipoCuentaDescripcion() {
		return tipoCuentaDescripcion;
	}

	/**
	 * Sets the tipo cuenta descripcion.
	 *
	 * @param tipoCuentaDescripcion
	 *            the new tipo cuenta descripcion
	 */
	public void setTipoCuentaDescripcion(String tipoCuentaDescripcion) {
		this.tipoCuentaDescripcion = tipoCuentaDescripcion;
	}

	/**
	 * Gets the tipo cuenta destino descripcion.
	 *
	 * @return the tipo cuenta destino descripcion
	 */
	public String getTipoCuentaDestinoDescripcion() {
		return tipoCuentaDestinoDescripcion;
	}

	/**
	 * Sets the tipo cuenta destino descripcion.
	 *
	 * @param tipoCuentaDestinoDescripcion
	 *            the new tipo cuenta destino descripcion
	 */
	public void setTipoCuentaDestinoDescripcion(String tipoCuentaDestinoDescripcion) {
		this.tipoCuentaDestinoDescripcion = tipoCuentaDestinoDescripcion;
	}

	/**
	 * Gets the fecha ejecucion.
	 *
	 * @return the fecha ejecucion
	 */
	public String getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * Sets the fecha ejecucion.
	 *
	 * @param fechaEjecucion
	 *            the new fecha ejecucion
	 */
	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
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
