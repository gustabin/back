/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ListarComprobantesViewResponse.
 */
public class ListarComprobantesViewResponse {

	/** The comprobantes. */
	private List<ComprobanteDisponibleView> comprobantes = new ArrayList<ComprobanteDisponibleView>();

	/**
	 * Gets the comprobantes.
	 *
	 * @return the comprobantes
	 */
	public List<ComprobanteDisponibleView> getComprobantes() {
		return comprobantes;
	}

	/**
	 * Sets the comprobantes.
	 *
	 * @param comprobantes
	 *            the new comprobantes
	 */
	public void setComprobantes(List<ComprobanteDisponibleView> comprobantes) {
		this.comprobantes = comprobantes;
	}
}
