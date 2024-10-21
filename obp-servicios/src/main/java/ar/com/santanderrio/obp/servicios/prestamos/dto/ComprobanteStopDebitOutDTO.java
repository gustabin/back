/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.prestamos.view.PeriodosStopDebitPrestamo;

// TODO: Auto-generated Javadoc
/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author
 *
 */
public class ComprobanteStopDebitOutDTO {
	
	/** The periodos stop debit prestamos. */
	private List<PeriodosStopDebitPrestamo> periodosStopDebitPrestamos;
	
	/** The comprobante. */
	private String comprobante;

	/** The legal stop debit prestamos. */
	private String legalStopDebitPrestamos;

	/**
	 * Gets the periodos stop debit prestamos.
	 *
	 * @return the periodos stop debit prestamos
	 */
	public List<PeriodosStopDebitPrestamo> getPeriodosStopDebitPrestamos() {
		return periodosStopDebitPrestamos;
	}

	/**
	 * Sets the periodos stop debit prestamos.
	 *
	 * @param periodosStopDebitPrestamos the new periodos stop debit prestamos
	 */
	public void setPeriodosStopDebitPrestamos(List<PeriodosStopDebitPrestamo> periodosStopDebitPrestamos) {
		this.periodosStopDebitPrestamos = periodosStopDebitPrestamos;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante the new comprobante
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the legal stop debit prestamos.
	 *
	 * @return the legal stop debit prestamos
	 */
	public String getLegalStopDebitPrestamos() {
		return legalStopDebitPrestamos;
	}

	/**
	 * Sets the legal stop debit prestamos.
	 *
	 * @param legalStopDebitPrestamos the new legal stop debit prestamos
	 */
	public void setLegalStopDebitPrestamos(String legalStopDebitPrestamos) {
		this.legalStopDebitPrestamos = legalStopDebitPrestamos;
	}		

}