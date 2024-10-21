/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.entities;

import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;

/**
 * The Class AdjuntarArchivosInEntity.
 */
public class AdjuntarArchivosInEntity {

	/** The nro transferencia. */
	private Integer nroTransferencia;
	
	/** archivo. */
	private ReporteView archivo = new ReporteView();
	
	/**
	 * Gets the nro transferencia.
	 *
	 * @return the nroTransferencia
	 */
	public Integer getNroTransferencia() {
		return nroTransferencia;
	}

	/**
	 * Sets the nro transferencia.
	 *
	 * @param nroTransferencia
	 *            the nroTransferencia to set
	 */
	public void setNroTransferencia(Integer nroTransferencia) {
		this.nroTransferencia = nroTransferencia;
	}

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
