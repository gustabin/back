package ar.com.santanderrio.obp.servicios.inversiones.maps.view;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ServicioControl;

public class ObtenerDisponiblesOutView extends FormularioControl{
    
    private Integer cantidadSuscripciones;

	public ObtenerDisponiblesOutView(FormularioControl formulario){
	    
		BeanUtils.copyProperties(formulario, this);
		calcularCantidadAdhesiones();
		eliminarServiciosBloqueados();
	}
	
	public Boolean tieneServicios() {
	    
		List<ControlMaps> controles = this.getItems();
		
		for(ControlMaps control:controles) {
			
		    if(control instanceof ServicioControl) {

		        return CollectionUtils.isNotEmpty(((ServicioControl) control).getItems());
		    }
		}
		return false;
	}

	/**
	 * @return the cantidadAdhesiones
	 */
	public void calcularCantidadAdhesiones() {
	    
		this.setCantidadSuscripciones(this.cantidadAdhesiones());
	}

    /**
     * @param cantidadSuscripciones the cantidadSuscripciones to set
     */
    public void setCantidadSuscripciones(Integer cantidadSuscripciones) {
        this.cantidadSuscripciones = cantidadSuscripciones;
    }

    /**
     * @return the cantidadSuscripciones
     */
    public Integer getCantidadSuscripciones() {
        return cantidadSuscripciones;
    }
    
    
}
