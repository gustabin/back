/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ConsultarOrden.
 */
public class ConsultarOrdenLicitacion extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4509180086503494705L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultarLicitacion datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultarLicitacion getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosConsultarLicitacion datos) {
		this.datos = datos;
	}

}
