/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DocumentacionAdjuntaView.
 */
public class DocumentacionAdjuntaView {
	
	/** The archivos. */
	private List<ReporteView> archivos =new ArrayList<ReporteView>();

	/**
	 * Gets the archivos.
	 *
	 * @return the archivos
	 */
	public List<ReporteView> getArchivos() {
		return archivos;
	}

	/**
	 * Sets the archivos.
	 *
	 * @param archivos
	 *            the archivos to set
	 */
	public void setArchivos(List<ReporteView> archivos) {
		this.archivos = archivos;
	}

}
