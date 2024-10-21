/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.SimulacionBaseOutDTO;

/**
 * Datos de salida del BO, simulacion rescate.
 *
 * @author marcelo.ruiz
 */
public class SimularRescateOutDTO extends SimulacionBaseOutDTO {

	/** Importe rescate comision retornado de la simulacion. */
	private String importeRescateComision;

	/** Importe rescate neto retornado de la simulacion. */
	private String importeRescateNeto;

	/** Cantidad de cuotapartes a rescatar. */
	private String cuotaPartes;

	/** Legales SuperAhorroPesos */
	private String legalSuperAhorroPesos;

	/**
	 * Gets the cuota partes.
	 *
	 * @return the cuota partes
	 */
	public String getCuotaPartes() {
		return cuotaPartes;
	}

	/**
	 * Sets the cuota partes.
	 *
	 * @param cuotaPartes
	 *            the new cuota partes
	 */
	public void setCuotaPartes(String cuotaPartes) {
		this.cuotaPartes = cuotaPartes;
	}

	/**
	 * Gets the importe rescate comision.
	 *
	 * @return the importe rescate comision
	 */
	public String getImporteRescateComision() {
		return importeRescateComision;
	}

	/**
	 * Sets the importe rescate comision.
	 *
	 * @param importeRescateComision
	 *            the new importe rescate comision
	 */
	public void setImporteRescateComision(String importeRescateComision) {
		this.importeRescateComision = importeRescateComision;
	}

	/**
	 * Gets the importe rescate neto.
	 *
	 * @return the importe rescate neto
	 */
	public String getImporteRescateNeto() {
		return importeRescateNeto;
	}

	/**
	 * Sets the importe rescate neto.
	 *
	 * @param importeRescateNeto
	 *            the new importe rescate neto
	 */
	public void setImporteRescateNeto(String importeRescateNeto) {
		this.importeRescateNeto = importeRescateNeto;
	}

	/**
	 * Sets the legales super ahorro pesos
	 *
	 * @param legalSuperAhorroPesos
	 *            the legales super ahorro pesos
	 */
	public void setLegalSuperAhorroPesos(String legalSuperAhorroPesos) {
		this.legalSuperAhorroPesos = legalSuperAhorroPesos;
	}

	/**
	 * Gets the legales super ahorro pesos
	 *
	 * @return the legales super ahorro pesos
	 */
	public String getLegalSuperAhorroPesos() {
		return legalSuperAhorroPesos;
	}

}
