/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemGenericoMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

/**
 * @author B042134
 *
 */
public class ListaControl<E extends ItemGenericoMaps> extends ControlMaps {
    
    @JsonProperty("PadreId")
    protected String padreId;
    @NotNull
    @JsonProperty("TipoDataValor")
    protected String tipoDataValor;
    @JsonProperty("Items")
    protected List<E> items;

    /**
     * @return the padreId
     */
    public String getPadreId() {
        return padreId;
    }

    /**
     * @param padreId
     *            the padreId to set
     */
    public void setPadreId(String padreId) {
        this.padreId = padreId;
    }

    /**
     * @return the tipoDataValor
     */
    public String getTipoDataValor() {
        return tipoDataValor;
    }

    /**
     * @param tipoDataValor
     *            the tipoDataValor to set
     */
    public void setTipoDataValor(String tipoDataValor) {
        this.tipoDataValor = tipoDataValor;
    }

    /**
     * @return the items
     */
    public List<E> getItems() {
        return items;
    }

    /**
     * @return the padreId
     */
    public String padreId() {
        return this.getPadreId();
    }

    /**
     * @param items
     *            the items to set
     */
    public void setItems(List<E> items) {
        this.items = items;
    }
    
	@Override
	public void validate() throws ControlMapValidationException {

		if (Boolean.TRUE.equals(this.getBloqueado())) {
			return;
		}

		if (!ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
		for (ItemGenericoMaps item : this.items) {
			item.validate();
		}
	}

	@Override
	public String valorComprobante() {
	    
	    E item = itemSeleccionado();
	    if(item!= null && item.getDesc() != null) {
	        return item.getDesc().toString();
	    }
		return "";
	}

    public E itemSeleccionado() {
        
        for (E item : this.items) {
            if(item.getSeleccionado()){
               return item; 
            }
       }
        return null;
    }
	
	
}
