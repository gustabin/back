/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemLegalComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.LegalMaps;

/**
 * @author sergio.e.goldentair
 *
 */
public class LegalControl extends ListaControl<LegalMaps> {

    @Override
	public ItemComprobante imprimirComprobante() {
		ItemLegalComprobante itemComprobante = new ItemLegalComprobante();
		itemComprobante.setEtiqueta(this.getEtiqueta());
		
		for (LegalMaps item : this.getItems()) {
			itemComprobante.agregarLegal(item.getValor());
		}
		return itemComprobante;
	}

	@Override
	public String tipoComprobante() {
	    
	    return "legal";
	}
	
	
}
