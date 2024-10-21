package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemFondo;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemGrupoFondos;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ItemGrupoFondosException;

public class FondoControl extends ControlMaps {

	@JsonProperty("PadreId")
	protected String padreId;
	@NotNull
	@JsonProperty("TipoDataValor")
	protected String tipoDataValor;
	@JsonProperty("MensajeBusqueda")
	protected String mensajeBusqueda;
	@NotNull
	@JsonProperty("Items")
	protected List<ItemGrupoFondos> items;

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

	public String getMensajeBusqueda() {
		return mensajeBusqueda;
	}

	public void setMensajeBusqueda(String mensajeBusqueda) {
		this.mensajeBusqueda = mensajeBusqueda;
	}

	public List<ItemGrupoFondos> getItems() {
		return items;
	}

	public void setItems(List<ItemGrupoFondos> items) {
		this.items = items;
	}

	public void validate() throws ControlMapValidationException {
		super.validate();
		if (!ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
		List<ItemGrupoFondos> gruposDesechados = new ArrayList<ItemGrupoFondos>();
		for (ItemGrupoFondos grupoFondos : this.items) {
			try {
				grupoFondos.validate();
			} catch (ItemGrupoFondosException e) {
				gruposDesechados.add(grupoFondos);
			}
		}

		this.getItems().removeAll(gruposDesechados);

		if (this.getItems().isEmpty()) {
			throw new ControlMapValidationException("Validation constraints");
		}
	}

	@Override
	public ItemComprobante imprimirComprobante() {
		ItemComprobante itemComprobante = new ItemComprobante();
		itemComprobante.setEtiqueta(this.getEtiqueta());
		for (ItemGrupoFondos item : this.getItems()) {
			for (ItemFondo itemFondo : item.getItems()) {
				if (itemFondo.getSeleccionado()) {
					itemComprobante.setValor(itemFondo.getDesc());
					return itemComprobante;
				}
			}
		}
		return itemComprobante;
	}

}
