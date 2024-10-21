/*
 * 
 */
package ar.com.santanderrio.obp.servicios.resumen.entities;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;

/**
 * The Class ReporteResumenPuntual.
 */
public class ReporteResumenPuntual extends Reporte {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fecha puntual. */
	private String fechaPuntual;

	/** The folder. */
	private String folder;

	/**
	 * Gets the fecha puntual.
	 *
	 * @return the fecha puntual
	 */
	public String getFechaPuntual() {
		return fechaPuntual;
	}

	/**
	 * Sets the fecha puntual.
	 *
	 * @param fechaPuntual
	 *            the new fecha puntual
	 */
	public void setFechaPuntual(String fechaPuntual) {
		this.fechaPuntual = fechaPuntual;
	}

	/**
	 * Gets the folder.
	 *
	 * @return the folder
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * Sets the folder.
	 *
	 * @param folder
	 *            the new folder
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}

}
