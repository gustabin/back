package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class AccountBonificationDto {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("dateApply")
	private String dateApply;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("dateFrom")
	private String dateFrom;
	
	@JsonProperty("dateTo")
	private String dateTo;
	
	@JsonProperty("percentaje")
	private String percentaje;
	
	@JsonProperty("months")
	private String months;
	
	@JsonProperty("product")
	private String product;
	
	@JsonProperty("subProduct")
	private String subProduct;
	
	@JsonProperty("bonificationCode")
	private String bonificationCode;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateApply() {
		return dateApply;
	}

	public void setDateApply(String dateApply) {
		this.dateApply = dateApply;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getPercentaje() {
		return percentaje;
	}

	public void setPercentaje(String percentaje) {
		this.percentaje = percentaje;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getSubProduct() {
		return subProduct;
	}

	public void setSubProduct(String subProduct) {
		this.subProduct = subProduct;
	}

	public String getBonificationCode() {
		return bonificationCode;
	}

	public void setBonificationCode(String bonificationCode) {
		this.bonificationCode = bonificationCode;
	}
	
		
}
