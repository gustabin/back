/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CuentaView.
 */
public class CuentaView implements Cloneable{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaView.class);
	
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
	private Boolean isSaldoPesosNegativo;
	
	/** The isSaldoDolaresNegativo. */
	private boolean isSaldoDolaresNegativo;

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
	public Boolean getIsSaldoPesosNegativo() {
		return isSaldoPesosNegativo;
	}

	/**
	 * Sets the checks if is saldo pesos negativo.
	 *
	 * @param isSaldoPesosNegativo
	 *            the isSaldoPesosNegativo to set
	 */
	public void setIsSaldoPesosNegativo(Boolean isSaldoPesosNegativo) {
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public CuentaView clone() {
		try {
			return (CuentaView) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Error clonando Objeto", e);
			return null;
		}
	}
	
}
