/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ListarComprobantesCnvResponse.
 */
public class ListarComprobantesCnvResponse {

	/** The comprobantes list. */
	private List<ComprobanteCnvDTO> comprobantesList = new ArrayList<ComprobanteCnvDTO>();

	/**
	 * Gets the comprobantes list.
	 *
	 * @return the comprobantes list
	 */
	public List<ComprobanteCnvDTO> getComprobantesList() {
		return comprobantesList;
	}

	/**
	 * Sets the comprobantes list.
	 *
	 * @param comprobantesList
	 *            the new comprobantes list
	 */
	public void setComprobantesList(List<ComprobanteCnvDTO> comprobantesList) {
		this.comprobantesList = comprobantesList;
	}
	
}
