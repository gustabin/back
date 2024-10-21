/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.util.List;

/**
 * The Class TenenciaPlazoFijoBprivDTO.
 */
public class TenenciaPlazoFijoBprivDTO {

	/** The legales. */
	private String legales;

	/** The mensaje error. */
	private String mensajeError;

	/** The contenidos tenencia bpriv. */
	List<ContenidoTenenciaBprivDTO> contenidosTenenciaBpriv;

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
	 * Gets the mensaje error.
	 *
	 * @return the mensaje error
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Sets the mensaje error.
	 *
	 * @param mensajeError
	 *            the new mensaje error
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * Gets the contenidos tenencia bpriv.
	 *
	 * @return the contenidos tenencia bpriv
	 */
	public List<ContenidoTenenciaBprivDTO> getContenidosTenenciaBpriv() {
		return contenidosTenenciaBpriv;
	}

	/**
	 * Sets the contenidos tenencia bpriv.
	 *
	 * @param contenidosTenenciaBpriv
	 *            the new contenidos tenencia bpriv
	 */
	public void setContenidosTenenciaBpriv(List<ContenidoTenenciaBprivDTO> contenidosTenenciaBpriv) {
		this.contenidosTenenciaBpriv = contenidosTenenciaBpriv;
	}

}
