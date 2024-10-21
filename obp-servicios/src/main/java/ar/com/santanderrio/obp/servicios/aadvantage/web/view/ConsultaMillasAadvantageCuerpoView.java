/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.aadvantage.dto.DetallePuntosAadvantageDTO;

/**
 * The Class ConsultaMillasAadvantageCuerpoView.
 */
public class ConsultaMillasAadvantageCuerpoView {

	/** The hay mas millas. */
	private Boolean hayMasMillas;
	
	/** The tyc. */
	private String tyc;
	
	/** The legales. */
	private String legales;
	
	/** The grilla list. */
	private List<DetallePuntosAadvantageDTO> grillaList;
	
	/** The mensaje sin millas. */
	private String mensajeSinMillas;
	
	/** The link sin millas. */
	private String linkSinMillas;
	
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
	 * Gets the tyc.
	 *
	 * @return the tyc
	 */
	public String getTyc() {
		return tyc;
	}

	/**
	 * Sets the tyc.
	 *
	 * @param tyc
	 *            the new tyc
	 */
	public void setTyc(String tyc) {
		this.tyc = tyc;
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

	/**
	 * Gets the grilla list.
	 *
	 * @return the grilla list
	 */
	public List<DetallePuntosAadvantageDTO> getGrillaList() {
		return grillaList;
	}

	/**
	 * Sets the grilla list.
	 *
	 * @param grillaList
	 *            the new grilla list
	 */
	public void setGrillaList(List<DetallePuntosAadvantageDTO> grillaList) {
		this.grillaList = grillaList;
	}

	/**
	 * Gets the mensaje sin millas.
	 *
	 * @return the mensaje sin millas
	 */
	public String getMensajeSinMillas() {
		return mensajeSinMillas;
	}

	/**
	 * Sets the mensaje sin millas.
	 *
	 * @param mensajeSinMillas
	 *            the new mensaje sin millas
	 */
	public void setMensajeSinMillas(String mensajeSinMillas) {
		this.mensajeSinMillas = mensajeSinMillas;
	}

	/**
	 * Gets the link sin millas.
	 *
	 * @return the link sin millas
	 */
	public String getLinkSinMillas() {
		return linkSinMillas;
	}

	/**
	 * Sets the link sin millas.
	 *
	 * @param linkSinMillas
	 *            the new link sin millas
	 */
	public void setLinkSinMillas(String linkSinMillas) {
		this.linkSinMillas = linkSinMillas;
	}
	
}
