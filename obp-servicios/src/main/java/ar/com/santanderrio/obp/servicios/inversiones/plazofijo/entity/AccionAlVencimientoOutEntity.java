/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

/**
 * Objeto utilizado para devolver datos del DAO.
 *
 * @author juan.pablo.picate
 */
public class AccionAlVencimientoOutEntity {

	/** The Codigo Accion. */
	private String codigoAccion;

	/** The descripcion. */
	private String descripcion;

	/** The detalle. */
	private String detalle;

	/**
	 * Gets the codigo accion.
	 *
	 * @return the codigoAccion
	 */
	public String getCodigoAccion() {
		return codigoAccion;
	}

	/**
	 * Sets the codigo accion.
	 *
	 * @param codigoAccion
	 *            the codigoAccion to set
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle
	 *            the new detalle
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}
