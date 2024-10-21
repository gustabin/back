/*
 * 
 */
package ar.com.santanderrio.obp.servicios.getnet.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class CuentaView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CuentaView {
	
	/** The id. */
	private	Integer id;
	
	/** The numero. */
	private String numero;
	
	/** The saldoPesos. */
	private String saldoPesos;
	
	/** The saldoDolares. */
	private String saldoDolares;
	
	/** The descripcionTipoCuenta. */
	private String descripcionTipoCuenta;
	
	/** The alias. */
	private String alias;
	
	/** The isSaldoPesosNegativo. */
	private boolean isSaldoPesosNegativo;
	
	/** The isSaldoDolaresNegativo. */
	private boolean isSaldoDolaresNegativo;

	/** The cbu. */
	private String cbu;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldoPesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the saldoPesos to set
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldoDolares
	 */
	public String getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the saldoDolares to set
	 */
	public void setSaldoDolares(String saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcionTipoCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripciontipoCuenta
	 *            the descripciontipoCuenta to set
	 */
	public void setDescripcionTipoCuenta(String descripciontipoCuenta) {
		this.descripcionTipoCuenta = descripciontipoCuenta;
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
	 * Gets the checks if is saldo pesos negativo.
	 *
	 * @return the isSaldoPesosNegativo
	 */
	public boolean getIsSaldoPesosNegativo() {
		return isSaldoPesosNegativo;
	}

	/**
	 * Sets the checks if is saldo pesos negativo.
	 *
	 * @param isSaldoPesosNegativo
	 *            the isSaldoPesosNegativo to set
	 */
	public void setIsSaldoPesosNegativo(boolean isSaldoPesosNegativo) {
		this.isSaldoPesosNegativo = isSaldoPesosNegativo;
	}

	/**
	 * Gets the checks if is saldo dolares negativo.
	 *
	 * @return the isSaldoDolaresNegativo
	 */
	public boolean getIsSaldoDolaresNegativo() {
		return isSaldoDolaresNegativo;
	}

	/**
	 * Sets the checks if is saldo dolares negativo.
	 *
	 * @param isSaldoDolaresNegativo
	 *            the isSaldoDolaresNegativo to set
	 */
	public void setIsSaldoDolaresNegativo(boolean isSaldoDolaresNegativo) {
		this.isSaldoDolaresNegativo = isSaldoDolaresNegativo;
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
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	
}
