/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class FinalizarReversaOrdenesRequestEntity.
 */
public class FinalizarReversaOrdenesRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4124075330498676109L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosFinalizarReversarOrden datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosFinalizarReversarOrden getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosFinalizarReversarOrden datos) {
		this.datos = datos;
	}
	

}
