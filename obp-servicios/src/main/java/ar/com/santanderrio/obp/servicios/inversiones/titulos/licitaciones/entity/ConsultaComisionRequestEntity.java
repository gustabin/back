/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultaCuentaTitulosRequestEntity.
 */
public class ConsultaComisionRequestEntity extends RequestBaseFirmado{


	/**
	 * 
	 */
	private static final long serialVersionUID = 299254438619535581L;
	
	@JsonProperty("Datos")
	private DatosConsultaComisionRequestEntity datos;
	

	 /** @return the datos
	 */
	public DatosConsultaComisionRequestEntity getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosConsultaComisionRequestEntity datos) {
		this.datos = datos;
	}
}
	