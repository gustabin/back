package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemDisclaimer;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.DisclaimerReenvioException;

public class DisclaimerControl<E extends ItemDisclaimer> extends ControlMaps {

	@JsonProperty("PadreId")
	protected String padreId;
	@NotNull
	@JsonProperty("TipoDataValor")
	protected String tipoDataValor;
	@JsonProperty("Items")
	protected List<ItemDisclaimer> items;


	public String getPadreId() {
		return padreId;
	}

	public void setPadreId(String padreId) {
		this.padreId = padreId;
	}

	public String getTipoDataValor() {
		return tipoDataValor;
	}

	public void setTipoDataValor(String tipoDataValor) {
		this.tipoDataValor = tipoDataValor;
	}

	public List<ItemDisclaimer> getItems() {
		return items;
	}

	public void setItems(List<ItemDisclaimer> items) {
		this.items = items;
	}

	public String valorComprobante() {
		return null;
	}

	
	@Override
	public void validate() throws ControlMapValidationException {

		boolean itemsDistintosDeCero=false;
		boolean itemsConError = false;
		
		if (Boolean.TRUE.equals(this.getBloqueado())) {
			return;
		}

		if (!ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
		List<ItemDisclaimer> itemsInvalidos = new ArrayList<ItemDisclaimer>();
		for (ItemDisclaimer itemDisclaimer : this.items) {
			try {
				itemDisclaimer.validate();
				if(itemDisclaimer.getTipoDisclaimer()>0){
					itemsDistintosDeCero=true;
				}
			} catch (ControlMapValidationException e) {
				itemsConError=true;
				itemsInvalidos.add(itemDisclaimer);
			}

		}
		this.items.removeAll(itemsInvalidos);
		if (CollectionUtils.isEmpty(items) || (!itemsDistintosDeCero && itemsConError)) {
			throw new ControlMapValidationException("Validation constraints");
		}
		
		if(!itemsDistintosDeCero && !itemsConError){
			throw new DisclaimerReenvioException("Realizar reenvio");
		}

		Collections.sort(items, new Comparator<ItemDisclaimer>() {
			@Override
			public int compare(ItemDisclaimer o1, ItemDisclaimer o2) {
				return o2.getTipoDisclaimer().compareTo(o1.getTipoDisclaimer());
			}
		});
	}
	
	public String tipoComprobante() {
        return TIPO_DISCLAIMER;
    }

}
