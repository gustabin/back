package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class RecomendacionMoreInfo {

	
	@JsonProperty("Text")
	private String text;
	
	@JsonProperty("Link")
	private String link;
	
	@JsonProperty("Goto")
	private String goTo;
}
