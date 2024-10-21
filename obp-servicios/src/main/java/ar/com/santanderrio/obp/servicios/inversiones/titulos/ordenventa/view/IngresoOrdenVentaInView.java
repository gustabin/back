/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

/**
 * The Class IngresoOrdenVentaInView.
 */
public class IngresoOrdenVentaInView {

	/** The es banca privada. */
	private Boolean esBancaPrivada = false;
	
	/** The precio limite. */
	private String precioLimite;

	/**
	 * Gets the es banca privada.
	 *
	 * @return the esBancaPrivada
	 */
	public Boolean getEsBancaPrivada() {
		return esBancaPrivada;
	}

	/**
	 * Sets the es banca privada.
	 *
	 * @param esBancaPrivada
	 *            the esBancaPrivada to set
	 */
	public void setEsBancaPrivada(Boolean esBancaPrivada) {
		this.esBancaPrivada = esBancaPrivada;
	}

	/**
	 * Gets the precio limite.
	 *
	 * @return the precio limite
	 */
	public String getPrecioLimite() {
		return precioLimite;
	}

	/**
	 * Sets the precio limite.
	 *
	 * @param precioLimite
	 *            the new precio limite
	 */
	public void setPrecioLimite(String precioLimite) {
		this.precioLimite = precioLimite;
	}
}
