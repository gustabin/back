package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class ItemFondo extends ItemGenericoMaps {

	@NotNull
	@JsonProperty("CodMonedaEmision")
	private String codMonedaEmision;
	@JsonProperty("FactSheet")
	private String factsheet;
	@JsonProperty("ReglamentoFondo")
	private String reglamentoFondo;
	@JsonProperty("ToolTip")
	private ItemTooltipFondo tooltip;

	public String getCodMonedaEmision() {
		return codMonedaEmision;
	}

	public void setCodMonedaEmision(String codMonedaEmision) {
		this.codMonedaEmision = codMonedaEmision;
	}

	public String getFactsheet() {
		return factsheet;
	}

	public void setFactsheet(String factsheet) {
		this.factsheet = factsheet;
	}

	public String getReglamentoFondo() {
		return reglamentoFondo;
	}

	public void setReglamentoFondo(String reglamentoFondo) {
		this.reglamentoFondo = reglamentoFondo;
	}

	public ItemTooltipFondo getTooltip() {
		return tooltip;
	}

	public void setTooltip(ItemTooltipFondo tooltip) {
		this.tooltip = tooltip;
	}
	public void validate() throws ControlMapValidationException {

		super.validate();
		if (!ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
		
		if(this.tooltip != null) {
			this.tooltip.validate();
		}
	}
}
