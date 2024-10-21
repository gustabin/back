/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

/**
 * The Class ConsultarTenenciaRenovable.
 */
public class ConsultarTenenciaRenovable extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6453742255290872903L;

	/** The datos. */
	private DatosConsultarTenenciaRenovable datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultarTenenciaRenovable getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosConsultarTenenciaRenovable datos) {
		this.datos = datos;
	}
}
