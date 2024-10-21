/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.entitites;

import java.math.BigDecimal;

/**
 * The Class TarjetaHomeDTO.
 */
public class TarjetaHomeDTO {

	/** The id. */
	private Integer id;

	/** The marca. */
	private String marca;

	/** The alias. */
	private String alias;

	/** The numero. */
	private String numero;

	/** The saldo pesos. */
	private BigDecimal saldoPesos;

	/** The has alias. */
	private Boolean hasAlias;

	/** The saldo dolares. */
	private BigDecimal saldoDolares;

	/** The cierre. */
	private String cierre;

	/** The vencimiento. */
	private String vencimiento;
	
	private boolean recargable;
	
	private String producto;
	
	private String subProducto;

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
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
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
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
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
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo pesos
	 */
	public BigDecimal getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(BigDecimal saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the checks for alias.
	 *
	 * @return the checks for alias
	 */
	public Boolean getHasAlias() {
		return hasAlias;
	}

	/**
	 * Sets the checks for alias.
	 *
	 * @param hasAlias
	 *            the new checks for alias
	 */
	public void setHasAlias(Boolean hasAlias) {
		this.hasAlias = hasAlias;
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldo dolares
	 */
	public BigDecimal getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the new saldo dolares
	 */
	public void setSaldoDolares(BigDecimal saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the cierre.
	 *
	 * @return the cierre
	 */
	public String getCierre() {
		return cierre;
	}

	/**
	 * Sets the cierre.
	 *
	 * @param cierre
	 *            the new cierre
	 */
	public void setCierre(String cierre) {
		this.cierre = cierre;
	}

	/**
	 * Gets the vencimiento.
	 *
	 * @return the vencimiento
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * Sets the vencimiento.
	 *
	 * @param vencimiento
	 *            the new vencimiento
	 */
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public boolean isRecargable() {
		return recargable;
	}

	public void setRecargable(boolean recargable) {
		this.recargable = recargable;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getSubProducto() {
		return subProducto;
	}

	public void setSubProducto(String subProducto) {
		this.subProducto = subProducto;
	}
	
}
