package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConsultaAgendamientoRequestEntity extends RequestBaseFirmado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Datos")
	private DatoConsultaAgedaRequest datos;

	/**
	 * @return the datos
	 */
	public DatoConsultaAgedaRequest getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatoConsultaAgedaRequest datos) {
		this.datos = datos;
	}

	
}
