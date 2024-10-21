/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ComptaVtaTitulosRequestEntity.
 */
public class ComptaVtaTitulosRequestEntity extends RequestBaseFirmado{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3937014551724841058L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosCompraVtaTitulosEntity datos = new DatosCompraVtaTitulosEntity();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosCompraVtaTitulosEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosCompraVtaTitulosEntity datos) {
		this.datos = datos;
	}
	
	

}
