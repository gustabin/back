/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class CuentaView.
 */
public class CuentaView {

	/** The id. */
	private String id;

	/** The cuenta. */
	private String cuenta;

	/** The alias. */
	private String alias;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The divisa. */
	private String divisa;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The numero. */
	private String numero;

	/**
	 * Instantiates a new cuenta view.
	 */
	public CuentaView() {
	    super();
	}
	
	/**
	 * Instantiates a new cuenta view.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	public CuentaView(Cuenta cuenta) {
	    this.id = cuenta.getId().toString();
	    this.divisa = cuenta.getMonedaAltair();
	    this.cuenta = cuenta.getNroCuentaProducto();
	    this.tipoCuenta = cuenta.getTipoCuenta();
	    this.saldoPesos = cuenta.obtenerSaldo();
	    this.alias = cuenta.getAlias();
	    this.descripcionTipoCuenta = cuenta.getTipoCuentaEnum().getDescripcionConMoneda();
	    this.numero = ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
	}
	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
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

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcionCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the new descripcion tipo cuenta
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the cuentaCanonica
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
