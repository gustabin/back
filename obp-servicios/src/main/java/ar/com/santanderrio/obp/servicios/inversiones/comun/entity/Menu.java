package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;


import org.codehaus.jackson.annotate.JsonProperty;


public class Menu {
	
	@JsonProperty("Titulo")
	private String Titulo;
	
	@JsonProperty("Link")
	private String Link;
	


	public void setTitulo(String titulo) {
		this.Titulo = titulo;
	}

	public String getTitulo() {
		return Titulo;
	}
	
	public void setLink(String link) {
		this.Link = link;
	}

	public String getLink() {
		return Link;
	}
	

}
