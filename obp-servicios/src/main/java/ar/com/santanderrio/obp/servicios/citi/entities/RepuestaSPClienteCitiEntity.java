/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.entities;

import java.math.BigDecimal;

/**
 * The Class RepuestaSPClienteCitiEntity.
 */
public class RepuestaSPClienteCitiEntity {

    /** The po nup. */
    private String poNup;
    
    /** The po saldos. */
    private BigDecimal poSaldos; 
    
    /** The po productos. */
    private BigDecimal poProductos;
    
    /**
	 * Instantiates a new repuesta SP cliente citi entity.
	 *
	 * @param poNup
	 *            the po nup
	 * @param poSaldos
	 *            the po saldos
	 * @param poProductos
	 *            the po productos
	 */
    public RepuestaSPClienteCitiEntity(String poNup, BigDecimal poSaldos, BigDecimal poProductos) {
    	this.poNup = poNup;
    	this.poSaldos = poSaldos;
    	this.poProductos = poProductos;
    }
    
	
	/**
	 * Gets the po nup.
	 *
	 * @return the po nup
	 */
	public String getPoNup() {
		return poNup;
	}
	
	/**
	 * Sets the po nup.
	 *
	 * @param poNup
	 *            the new po nup
	 */
	public void setPoNup(String poNup) {
		this.poNup = poNup;
	}
	
	/**
	 * Gets the po saldos.
	 *
	 * @return the po saldos
	 */
	public BigDecimal getPoSaldos() {
		return poSaldos;
	}
	
	/**
	 * Sets the po saldos.
	 *
	 * @param poSaldos
	 *            the new po saldos
	 */
	public void setPoSaldos(BigDecimal poSaldos) {
		this.poSaldos = poSaldos;
	}
	
	/**
	 * Gets the po productos.
	 *
	 * @return the po productos
	 */
	public BigDecimal getPoProductos() {
		return poProductos;
	}
	
	/**
	 * Sets the po productos.
	 *
	 * @param poProductos
	 *            the new po productos
	 */
	public void setPoProductos(BigDecimal poProductos) {
		this.poProductos = poProductos;
	}
	
}
