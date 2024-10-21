/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultaOrdenesRequestEntity.
 */
public class ConsultaOrdenesRequestEntity extends RequestBaseFirmado{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4350604575306808883L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultaTitulosOrdenes datos;

	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaTitulosOrdenes getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosConsultaTitulosOrdenes datos) {
		this.datos = datos;
	}
	
	
	

}
