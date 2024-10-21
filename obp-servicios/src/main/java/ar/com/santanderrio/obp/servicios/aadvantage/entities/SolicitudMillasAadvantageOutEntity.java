/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.aadvantage.dto.DetallePuntosAadvantageDTO;

/**
 * The Class SolicitudMillasAadvantageOutEntity.
 */
public class SolicitudMillasAadvantageOutEntity {

	/** The is sin millas. */
	private Boolean isSinMillas;
	
	/** The is error tecnico. */
	private Boolean isErrorTecnico;
	
	/** The lista detalle millas. */
	private List<DetallePuntosAadvantageDTO> listaDetalleMillas;

	/**
	 * Gets the lista detalle millas.
	 *
	 * @return the lista detalle millas
	 */
	public List<DetallePuntosAadvantageDTO> getListaDetalleMillas() {
		return listaDetalleMillas;
	}

	/**
	 * Sets the lista detalle millas.
	 *
	 * @param listaDetalleMillas
	 *            the new lista detalle millas
	 */
	public void setListaDetalleMillas(List<DetallePuntosAadvantageDTO> listaDetalleMillas) {
		this.listaDetalleMillas = listaDetalleMillas;
	}

	/**
	 * Gets the checks if is sin millas.
	 *
	 * @return the checks if is sin millas
	 */
	public Boolean getIsSinMillas() {
		return isSinMillas;
	}

	/**
	 * Sets the checks if is sin millas.
	 *
	 * @param isSinMillas
	 *            the new checks if is sin millas
	 */
	public void setIsSinMillas(Boolean isSinMillas) {
		this.isSinMillas = isSinMillas;
	}

	/**
	 * Gets the checks if is error tecnico.
	 *
	 * @return the checks if is error tecnico
	 */
	public Boolean getIsErrorTecnico() {
		return isErrorTecnico;
	}

	/**
	 * Sets the checks if is error tecnico.
	 *
	 * @param isErrorTecnico
	 *            the new checks if is error tecnico
	 */
	public void setIsErrorTecnico(Boolean isErrorTecnico) {
		this.isErrorTecnico = isErrorTecnico;
	}
	
}
