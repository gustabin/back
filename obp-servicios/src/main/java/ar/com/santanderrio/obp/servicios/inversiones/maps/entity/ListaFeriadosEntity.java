package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaFeriadosEntity {
	
	@JsonProperty("ListaFeriados")
	private List<Feriados> listaFeriados;

	/**
	 * @return the listaFeriados
	 */
	public List<Feriados> getListaFeriados() {
		return listaFeriados;
	}

	/**
	 * @param listaFeriados the listaFeriados to set
	 */
	public void setListaFeriados(List<Feriados> listaFeriados) {
		this.listaFeriados = listaFeriados;
	}
	
	

}
