/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class CrearAdhesionRequestEntity.
 *
 * @author B042583 Request para adhesion poder de compra (Ordenes)
 */
public class CrearAdhesionRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8366745397063345040L;
	
	/** The datos. */
	@JsonProperty("Datos")
	CrearAdhesionDatosRequest datos = new CrearAdhesionDatosRequest();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public CrearAdhesionDatosRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(CrearAdhesionDatosRequest datos) {
		this.datos = datos;
	}

}
