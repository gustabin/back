/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import ar.com.santanderrio.obp.servicios.prestamos.view.ReporteView;

/**
 * The Class AdjuntarArchivosInEntity.
 */
public class AdjuntarArchivosInEntity {

	
	/** archivo. */
	private ReporteView archivo = new ReporteView();
	

	/**
	 * Gets the archivo.
	 *
	 * @return the archivo
	 */
	public ReporteView getArchivo() {
		return archivo;
	}

	/**
	 * Sets the archivo.
	 *
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(ReporteView archivo) {
		this.archivo = archivo;
	}
}
