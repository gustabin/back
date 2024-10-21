/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaCuentaView;

/**
 * The Class DetalleCustodiaDTO.
 */
public class DetalleCustodiaDTO {

	/** The lista detalle custodia cuenta view. */
	private List<DetalleCustodiaCuentaView> listaDetalleCustodiaCuentaView; 
	
	/** The hay error. */
	private Boolean hayError = false;
	

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
	 * Gets the hay error.
	 *
	 * @return the hay error
	 */
	public Boolean getHayError() {
		return hayError;
	}

	/**
	 * Sets the hay error.
	 *
	 * @param hayError
	 *            the new hay error
	 */
	public void setHayError(Boolean hayError) {
		this.hayError = hayError;
	}
	
	
}