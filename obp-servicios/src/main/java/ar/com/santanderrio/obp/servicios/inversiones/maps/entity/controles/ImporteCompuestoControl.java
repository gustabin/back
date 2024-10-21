package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemImporteCompuesto;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class ImporteCompuestoControl extends ControlMaps {
	
    /** The Constant DOS. */
    private static final int DOS = 2;
	
	 @JsonProperty("Items")
	    private List<InputNumberControl> items;
	 

	public List<InputNumberControl> getItems() {
		return items;
	}

	public void setItems(List<InputNumberControl> items) {
		this.items = items;
	}
	 
	
	 @Override
	    public void validate() throws ControlMapValidationException {		 
		 super.validate();
		 
		 if(CollectionUtils.isEmpty(this.items) || (this.items.size() != DOS)) {
	            throw new ControlMapValidationException("Error: numero de controles input-number,  menor a dos ");
	        }
		for (ControlMaps control : this.items) {
			control.validate();
		}
		 
	 }
	 
	 @Override
	 public ItemComprobante imprimirComprobante(){		
		    ItemImporteCompuesto itemImporteCompuesto = new ItemImporteCompuesto();
		    itemImporteCompuesto.setDesde(this.getItems().get(0).getEtiqueta());
		    itemImporteCompuesto.setDesdeValor(this.getItems().get(0).valorComprobante());
		    itemImporteCompuesto.setHasta(this.getItems().get(1).getEtiqueta());
		    itemImporteCompuesto.setHastaValor(this.getItems().get(1).valorComprobante());
		    itemImporteCompuesto.setEtiqueta(this.getEtiqueta());
			return itemImporteCompuesto;
		}
	 
}
