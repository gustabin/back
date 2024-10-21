/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import java.util.List;

/**
 * The Class ComprobanteAltaUsuarioBilleteraView.
 */
public class ComprobanteAltaTarjetaCreditoAdicionalView {

	/** The apellido nombre solicitante. */
	private String apellidoNombreSolicitante;

	/** The dni solicitante. */
	private String dniSolicitante;

	/** The comprobantes view. */
	private List<ComprobanteView> comprobantesView;

	/** The mensaje. */
	private String fecha;

	/** The legales SEO. */
	private String legalesSEO;

	/**
	 * Gets the apellido nombre solicitante.
	 *
	 * @return the apellido nombre solicitante
	 */
	public String getApellidoNombreSolicitante() {
		return apellidoNombreSolicitante;
	}

	/**
	 * Sets the apellido nombre solicitante.
	 *
	 * @param apellidoNombreSolicitante
	 *            the new apellido nombre solicitante
	 */
	public void setApellidoNombreSolicitante(String apellidoNombreSolicitante) {
		this.apellidoNombreSolicitante = apellidoNombreSolicitante;
	}

	/**
	 * Gets the dni solicitante.
	 *
	 * @return the dni solicitante
	 */
	public String getDniSolicitante() {
		return dniSolicitante;
	}

	/**
	 * Sets the dni solicitante.
	 *
	 * @param dniSolicitante
	 *            the new dni solicitante
	 */
	public void setDniSolicitante(String dniSolicitante) {
		this.dniSolicitante = dniSolicitante;
	}

	/**
	 * Gets the comprobantes view.
	 *
	 * @return the comprobantes view
	 */
	public List<ComprobanteView> getComprobantesView() {
		return comprobantesView;
	}

	/**
	 * Sets the comprobantes view.
	 *
	 * @param comprobantesView
	 *            the new comprobantes view
	 */
	public void setComprobantesView(List<ComprobanteView> comprobantesView) {
		this.comprobantesView = comprobantesView;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the legales SEO.
	 *
	 * @return the legalesSEO
	 */
	public String getLegalesSEO() {
		return legalesSEO;
	}

	/**
	 * Sets the legales SEO.
	 *
	 * @param legalesSEO
	 *            the legalesSEO to set
	 */
	public void setLegalesSEO(String legalesSEO) {
		this.legalesSEO = legalesSEO;
	}

}
