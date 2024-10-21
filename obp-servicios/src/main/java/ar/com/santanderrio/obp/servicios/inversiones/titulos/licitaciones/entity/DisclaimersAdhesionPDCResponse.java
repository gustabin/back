/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DisclaimersAdhesionPDCResponse.
 */
public class DisclaimersAdhesionPDCResponse {
	
	/** The tipo. */
	@JsonProperty("Tipo")
	private String tipo;
	
	/** The formato. */
	@JsonProperty("Formato")
	private String formato;
	
	/** The titulo. */
	@JsonProperty("Titulo")
	private String titulo;
	
	/** The contenido. */
	@JsonProperty("Contenido")
	private String contenido;
	
	/** The lista checkboxs. */
	@JsonProperty("ListaCheckboxs")
	private List<CheckboxCrearAdhesionResponse> listaCheckboxs = new ArrayList<CheckboxCrearAdhesionResponse>();
	
	/** The url. */
	@JsonProperty("URL")
	private String url;

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the formato.
	 *
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}

	/**
	 * Sets the formato.
	 *
	 * @param formato
	 *            the new formato
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the contenido.
	 *
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * Sets the contenido.
	 *
	 * @param contenido
	 *            the new contenido
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * Gets the lista checkboxs.
	 *
	 * @return the lista checkboxs
	 */
	public List<CheckboxCrearAdhesionResponse> getListaCheckboxs() {
		return listaCheckboxs;
	}

	/**
	 * Sets the lista checkboxs.
	 *
	 * @param listaCheckboxs
	 *            the new lista checkboxs
	 */
	public void setListaCheckboxs(List<CheckboxCrearAdhesionResponse> listaCheckboxs) {
		this.listaCheckboxs = listaCheckboxs;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url
	 *            the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
