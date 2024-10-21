/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * The Class RequestConsultarOperacionesTextEntity.
 */
public class RequestConsultarOperacionesTextEntity extends RequestBaseFirmado {
    
    /** The datos. */
    @JsonProperty("Datos")
    DatosConsultaOperacionesTextRequestEntity datos = new DatosConsultaOperacionesTextRequestEntity();

    /**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
    public DatosConsultaOperacionesTextRequestEntity getDatos() {
        return datos;
    }

    /**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
    public void setDatos(DatosConsultaOperacionesTextRequestEntity datos) {
        this.datos = datos;
    }

}
