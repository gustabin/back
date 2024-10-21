/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultaSuscripcionSaldoPDCRequest.
 */
public class ConsultaSuscripcionSaldoPDCRequest extends RequestBaseFirmado{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3149904751618688472L;
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultaSuscripcionSaldoPDCRequest datos = new DatosConsultaSuscripcionSaldoPDCRequest();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaSuscripcionSaldoPDCRequest getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosConsultaSuscripcionSaldoPDCRequest datos) {
		this.datos = datos;
	}
}
