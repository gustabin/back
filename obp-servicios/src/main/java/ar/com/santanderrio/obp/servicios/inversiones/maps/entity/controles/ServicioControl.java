package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ServicioMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlServicioErrorValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlServicioValidationException;

public class ServicioControl extends ListaControl<ServicioMaps>{
    
    @Override
    public void validate() throws ControlMapValidationException {
        
        if(!ValidationEntity.validate(this)) {
            throw new ControlMapValidationException("Validation constraints");
        }
        List<ServicioMaps> serviciosInvalidos = new ArrayList<ServicioMaps>();
        for(ServicioMaps servicio : this.items) {
            try {
                servicio.validate();
            } catch (ControlMapValidationException e) {
                serviciosInvalidos.add(servicio);
            }
        } 
        if(!CollectionUtils.isEmpty(this.items) && !CollectionUtils.isEmpty(serviciosInvalidos)
                && (this.items.size() == serviciosInvalidos.size())) {
            this.items.removeAll(serviciosInvalidos);
            throw new ControlMapValidationException("Sin tarjetas Validas");
        }
        if (CollectionUtils.isEmpty(this.items)) {
            throw new ControlServicioValidationException("Sin datos");
        }
        if (!CollectionUtils.isEmpty(serviciosInvalidos)) {
            this.items.removeAll(serviciosInvalidos);
            throw new ControlServicioErrorValidationException("Con errores");
        }
    }
    
    @Override
    public Integer cantidadAdhesiones() {
        Integer cantidad = 0;
        for(ServicioMaps servicio : this.items) {
            try {
                cantidad += Integer.parseInt(servicio.getCantidadAdhesiones());
            } catch (NumberFormatException e) {
                
            }
        }
        return cantidad;
    }

}
