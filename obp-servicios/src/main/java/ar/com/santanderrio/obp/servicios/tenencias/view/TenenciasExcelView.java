/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * The Class TenenciasExcelView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TenenciasExcelView {

	/** The anio. */
	private String anio;

	/** The detalle. */
	private Respuesta<TenenciasDetalleView> detalle;

	/** The resumen. */
	private Respuesta<TenenciasView> resumen;

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public Respuesta<TenenciasDetalleView> getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(Respuesta<TenenciasDetalleView> detalle) {
		this.detalle = detalle;
	}

	/**
	 * Gets the resumen.
	 *
	 * @return the resumen
	 */
	public Respuesta<TenenciasView> getResumen() {
		return resumen;
	}

	/**
	 * Sets the resumen.
	 *
	 * @param resumen
	 *            the resumen to set
	 */
	public void setResumen(Respuesta<TenenciasView> resumen) {
		this.resumen = resumen;
	}

}
