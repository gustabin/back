/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

/**
 * The Class SimularOrden.
 */
public class SimularOrden extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3687925214018509514L;

	/** The datos. */
	private DatosSimularOrden datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosSimularOrden getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosSimularOrden datos) {
		this.datos = datos;
	}

}
