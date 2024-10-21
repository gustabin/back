/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import ar.com.santanderrio.obp.servicios.inversiones.comun.view.SimulacionOutView;

/**
 * Respuesta de simulacion de rescate.
 *
 * @author marcelo.ruiz
 */
public class SimulacionRescateOutView extends SimulacionOutView {

	/** Importe rescate comision retornado de la simulacion. */
	private String importeRescateComision;

	/** Importe rescate neto retornado de la simulacion. */
	private String importeRescateNeto;

	/** Cantidad de cuotapartes a rescatar. */
	private String cuotaPartes;

	/** The dentro del perfil. */
	private String dentroDelPerfil;

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
	 * Gets the dentro del perfil.
	 *
	 * @return the dentro del perfil
	 */
	public String getDentroDelPerfil() {
		return dentroDelPerfil;
	}

	/**
	 * Sets the dentro del perfil.
	 *
	 * @param dentroDelPerfil
	 *            the new dentro del perfil
	 */
	public void setDentroDelPerfil(String dentroDelPerfil) {
		this.dentroDelPerfil = dentroDelPerfil;
	}

}
