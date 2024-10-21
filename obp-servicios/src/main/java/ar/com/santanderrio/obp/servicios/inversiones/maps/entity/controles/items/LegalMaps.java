/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

/**
 * @author sergio.e.goldentair
 *
 */
public class LegalMaps extends ItemGenericoMaps {
	
    @JsonProperty("Items")
    private List<ItemLegal> items;

    /**
     * @return the items
     */
    public List<ItemLegal> getItems() {
        return items;
    }

    /**
     * @param items
     *            the items to set
     */
    public void setItems(List<ItemLegal> items) {
        this.items = items;
    }
    
    @Override
	public void validate() throws ControlMapValidationException{
		 if(!ValidationEntity.validate(this)) {
	            throw new ControlMapValidationException("Validation constraints");
	        }
		if(!CollectionUtils.isEmpty(this.getItems())){
			for (ItemLegal itemLegal : this.getItems()) {
				itemLegal.validate();
			}
			 
		}
	}
    
    
    

}
