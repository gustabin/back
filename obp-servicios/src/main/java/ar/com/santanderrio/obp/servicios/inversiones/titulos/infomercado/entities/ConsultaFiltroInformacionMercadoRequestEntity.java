/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class ConsultaFiltroInformacionMercadoRequestEntity.
 */
public class ConsultaFiltroInformacionMercadoRequestEntity extends RequestBaseFirmado {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultaFiltroInformacionMercadoRequestEntity datos = new DatosConsultaFiltroInformacionMercadoRequestEntity();

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaFiltroInformacionMercadoRequestEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosConsultaFiltroInformacionMercadoRequestEntity datos) {
		this.datos = datos;
	}

}
