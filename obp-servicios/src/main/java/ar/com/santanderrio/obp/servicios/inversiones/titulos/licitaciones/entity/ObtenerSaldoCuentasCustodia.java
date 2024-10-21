/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ObtenerSaldoCuentasCustodia.
 */
public class ObtenerSaldoCuentasCustodia extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7841015077680374723L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosObtenerSaldoCuentasCustodia datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosObtenerSaldoCuentasCustodia getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosObtenerSaldoCuentasCustodia datos) {
		this.datos = datos;
	}

}
