/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.prestamos.view.PeriodosStopDebitPrestamo;

/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author
 *
 */
public class ComprobanteStopDebitInDTO {
	
	/** The periodos stop debit prestamos. */
	private List<PeriodosStopDebitPrestamo> periodosStopDebitPrestamos;

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

}