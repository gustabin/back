package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemCuentaTitulos;

public class CuentaTitulosControl extends ListaControl<ItemCuentaTitulos> {
    
    

    @Override
    public String valorComprobante() {
        
        ItemCuentaTitulos item = this.itemSeleccionado();
        if(item!= null && item.getValor() != null) {
            return ISBANStringUtils.formatearNumeroCuenta(item.getNumeroCtaTitulo());
        }
        return "";
    }

	
}
