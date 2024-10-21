/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.dto;

import java.util.List;

/**
 * The Class SolicitudMillasAadvantageOutDTO.
 */
public class SolicitudMillasAadvantageOutDTO {

	/** The lista detalle millas. */
	private List<DetallePuntosAadvantageDTO> listaDetalleMillas;

	/** The hay mas millas. */
	private Boolean hayMasMillas;
	
	/** The millas. */
	private String millas;
	
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
	 * Gets the hay mas millas.
	 *
	 * @return the hay mas millas
	 */
	public Boolean getHayMasMillas() {
		return hayMasMillas;
	}

	/**
	 * Sets the hay mas millas.
	 *
	 * @param hayMasMillas
	 *            the new hay mas millas
	 */
	public void setHayMasMillas(Boolean hayMasMillas) {
		this.hayMasMillas = hayMasMillas;
	}

	/**
	 * Gets the millas.
	 *
	 * @return the millas
	 */
	public String getMillas() {
		return millas;
	}

	/**
	 * Sets the millas.
	 *
	 * @param millas
	 *            the new millas
	 */
	public void setMillas(String millas) {
		this.millas = millas;
	}
	
}
