/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.entities;

import java.math.BigDecimal;

/**
 * The Class ConsultaOperacionesEntity.
 *
 * @author IT Resources
 */
public class ConsultaOperacionesInEntity {

	/** The estado transferencia. */
	private String estadoTransferencia;
	
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

	/**
	 * Gets the estado transferencia.
	 *
	 * @return the estadoTransferencia
	 */
	public String getEstadoTransferencia() {
		return estadoTransferencia;
	}

	/**
	 * Sets the estado transferencia.
	 *
	 * @param estadoTransferencia
	 *            the estadoTransferencia to set
	 */
	public void setEstadoTransferencia(String estadoTransferencia) {
		this.estadoTransferencia = estadoTransferencia;
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
	
	
}
