/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.List;

/**
 * The Class DetalleCustodiaView.
 */
public class DetalleCustodiaView {

	/** The lista detalle custodia cuenta view. */
	private List<DetalleCustodiaCuentaView> listaDetalleCustodiaCuentaView;
	
	/** The legales. */
	private String legales;

	
	/**
	 * Gets the lista detalle custodia cuenta view.
	 *
	 * @return the lista detalle custodia cuenta view
	 */
	public List<DetalleCustodiaCuentaView> getListaDetalleCustodiaCuentaView() {
		return listaDetalleCustodiaCuentaView;
	}

	/**
	 * Sets the lista detalle custodia cuenta view.
	 *
	 * @param listaDetalleCustodiaCuentaView
	 *            the new lista detalle custodia cuenta view
	 */
	public void setListaDetalleCustodiaCuentaView(List<DetalleCustodiaCuentaView> listaDetalleCustodiaCuentaView) {
		this.listaDetalleCustodiaCuentaView = listaDetalleCustodiaCuentaView;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}
		
}
