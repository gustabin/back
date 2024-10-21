package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class ItemCuentaOperativa extends ItemGenericoMaps{

	@NotNull
	@JsonProperty("TipoCtaOperativa")
	private String tipoCtaOperativa;
	@NotNull
	@JsonProperty("Producto")
	private String producto;
	@NotNull
	@JsonProperty("SubProducto")
	private String subproducto;
	@NotNull
	@JsonProperty("SucursalCtaOperativa")
	private String sucursalCtaOperativa;
	@NotNull
	@JsonProperty("NumeroCtaOperativa")
	private String numeroCtaOperativa;
	@NotNull
	@JsonProperty("CodigoMoneda")
	private String codigoMoneda;
	
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("SaldoCta")
	private Double saldoCta;
	
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("Titulares")	
	private List<String> titulares;
	
	public String getTipoCtaOperativa() {
		return tipoCtaOperativa;
	}
	public void setTipoCtaOperativa(String tipoCtaOperativa) {
		this.tipoCtaOperativa = tipoCtaOperativa;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getSubproducto() {
		return subproducto;
	}
	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}
	public String getSucursalCtaOperativa() {
		return sucursalCtaOperativa;
	}
	public void setSucursalCtaOperativa(String sucursalCtaOperativa) {
		this.sucursalCtaOperativa = sucursalCtaOperativa;
	}
	public String getNumeroCtaOperativa() {
		return numeroCtaOperativa;
	}
	public void setNumeroCtaOperativa(String numeroCtaOperativa) {
		this.numeroCtaOperativa = numeroCtaOperativa;
	}
	public String getCodigoMoneda() {
		return codigoMoneda;
	}
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	public Double getSaldoCta() {
		return saldoCta;
	}
	public void setSaldoCta(Double saldoCta) {
		this.saldoCta = saldoCta;
	}
	public List<String> getTitulares() {
		return titulares;
	}
	public void setTitulares(List<String> titulares) {
		this.titulares = titulares;
	}
	
	public void validate() throws ControlMapValidationException {
		super.validate();
		if (!ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
	}
}
