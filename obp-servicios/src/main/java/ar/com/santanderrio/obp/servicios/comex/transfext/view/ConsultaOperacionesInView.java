/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.math.BigDecimal;

/**
 * The Class ConsultaOperacionesInView.
 *
 * @author IT Resources
 */
public class ConsultaOperacionesInView {
	
	/** The es buscador. */
	private Boolean esBuscador = Boolean.FALSE;

    /** The fecha desde. */
    private String fechaDesde;
    
    /** The fecha hasta. */
    private String fechaHasta;
    
    /** The moneda. */
    private String moneda;
    
    /** The monto desde. */
    private BigDecimal montoDesde;
    
    /** The monto hasta. */
    private BigDecimal montoHasta;
    
    /** The estado. */
    private String estado;
    
	/**
	 * Gets the es buscador.
	 *
	 * @return the esBuscador
	 */
	public Boolean getEsBuscador() {
		return esBuscador;
	}

	/**
	 * Sets the es buscador.
	 *
	 * @param esBuscador
	 *            the esBuscador to set
	 */
	public void setEsBuscador(Boolean esBuscador) {
		this.esBuscador = esBuscador;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the monto desde.
	 *
	 * @return the montoDesde
	 */
	public BigDecimal getMontoDesde() {
		return montoDesde;
	}

	/**
	 * Sets the monto desde.
	 *
	 * @param montoDesde
	 *            the montoDesde to set
	 */
	public void setMontoDesde(BigDecimal montoDesde) {
		this.montoDesde = montoDesde;
	}

	/**
	 * Gets the monto hasta.
	 *
	 * @return the montoHasta
	 */
	public BigDecimal getMontoHasta() {
		return montoHasta;
	}

	/**
	 * Sets the monto hasta.
	 *
	 * @param montoHasta
	 *            the montoHasta to set
	 */
	public void setMontoHasta(BigDecimal montoHasta) {
		this.montoHasta = montoHasta;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}    
 
	
}
