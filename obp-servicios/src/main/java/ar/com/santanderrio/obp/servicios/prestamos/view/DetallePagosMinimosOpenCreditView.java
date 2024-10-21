/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.List;

/**
 * DetallePagoMinimoOpenCreditView OLYMPUS OpenCredit.
 *
 * @author Silvina_Luque
 */
public class DetallePagosMinimosOpenCreditView {

	/** Historial de pagos por anio. */
	private List<PagosFechaOpenCreditView> pagosFecha;
	
	/** legal. */
	private String legales;

	/**
	 * Gets the pagos fecha.
	 *
	 * @return the pagos fecha
	 */
	public List<PagosFechaOpenCreditView> getPagosFecha() {
		return pagosFecha;
	}

	/**
	 * Sets the pagos fecha.
	 *
	 * @param pagosFecha
	 *            the new pagos fecha
	 */
	public void setPagosFecha(List<PagosFechaOpenCreditView> pagosFecha) {
		this.pagosFecha = pagosFecha;
	}

	/**
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * @param legales the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}


}
