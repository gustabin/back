package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ItemGrupoFondosException;

public class ItemGrupoFondos {

	@NotNull
	@JsonProperty("Grupo")
	private String grupo;
	@NotNull
	@JsonProperty("Items")
	protected List<ItemFondo> items;

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public List<ItemFondo> getItems() {
		return items;
	}

	public void setItems(List<ItemFondo> items) {
		this.items = items;
	}

	public void validate() throws ItemGrupoFondosException {
		if (!ValidationEntity.validate(this)) {
			throw new ItemGrupoFondosException("Validation constraints");
		}
		List<ItemFondo> itemsDesechados = new ArrayList<ItemFondo>();
		
		for(ItemFondo itemFondo : this.items) {
			try {
				itemFondo.validate();
			} catch (ControlMapValidationException e) {
				itemsDesechados.add(itemFondo);
			}
		}
		
		this.getItems().removeAll(itemsDesechados);
		
		if(this.getItems().isEmpty()) {
			throw new ItemGrupoFondosException("Validation constraints");
		}
	}
}
