package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemFondo;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemGrupoFondos;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class FondoCompuestoControl extends ControlMaps {

	@JsonProperty("Items")
	private List<ControlMaps> items;

	public List<ControlMaps> getItems() {
		return items;
	}

	public void setItems(List<ControlMaps> items) {
		this.items = items;

	}

	@Override
	public void validate() throws ControlMapValidationException {
		if (this.getBloqueado()) {
			return;
		}

		super.validate();
		if (this.items == null || this.items.size() != 2) {
			throw new ControlMapValidationException("Error validacion control fondo compuesto");
		}

		int contadorFondo = 0;
		int contadorLegal = 0;

		for (ControlMaps control : this.items) {
			control.validate();

			if (control instanceof FondoControl) {
				contadorFondo += 1;
			}

			if (control instanceof LegalControl) {
				contadorLegal += 1;
			}
		}

		if (!(contadorFondo == 1 && contadorLegal == 1)) {
			throw new ControlMapValidationException("Error validacion control fondo compuesto");
		}
	}

	@Override
	public ItemComprobante imprimirComprobante() {
		return this.items.get(0).imprimirComprobante();
	}
}
