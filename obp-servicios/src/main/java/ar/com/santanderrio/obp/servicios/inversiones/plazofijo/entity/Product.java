package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class Product {

	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("Label")
	private String label;
	
	@JsonProperty("ColorLabel")
	private String colorLabel;
	
	@JsonProperty("FirstRowDescriptions")
	private List<String> firstRowDescriptions;
	
	@JsonProperty("SecondRowDescriptions")
	private List<String> secondRowDescriptions;
	
	@JsonProperty("MoreInfo")
	private RecomendacionMoreInfo moreInfo;
	
	@JsonProperty("Action")
	private RecomendacionMoreInfo action;
	
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
