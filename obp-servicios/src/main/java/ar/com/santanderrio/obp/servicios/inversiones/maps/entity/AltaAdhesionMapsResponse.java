package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.ResponseEntityBase;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;

public class AltaAdhesionMapsResponse extends ResponseEntityBase{

	@JsonProperty("Datos")
	private FormularioControl datos;

    /**
     * @return the datos
     */
    public FormularioControl getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(FormularioControl datos) {
        this.datos = datos;
    }

}
