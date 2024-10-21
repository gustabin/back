/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.view;

/**
 * The Class ValidacionesRespuestaEntity.
 */
public class ValidacionesRespuestaEntity {

	/** The modoEjecucion. */
	private boolean modoEjecucion;

	/** The fecha. */
	private String fecha;

	/**
	 * Checks if is modo ejecucion.
	 *
	 * @return the modoEjecucion
	 */
	public boolean isModoEjecucion() {
		return modoEjecucion;
	}

	/**
	 * Sets the modo ejecucion.
	 *
	 * @param modoEjecucion
	 *            the modoEjecucion to set
	 */
	public void setModoEjecucion(boolean modoEjecucion) {
		this.modoEjecucion = modoEjecucion;
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

}
