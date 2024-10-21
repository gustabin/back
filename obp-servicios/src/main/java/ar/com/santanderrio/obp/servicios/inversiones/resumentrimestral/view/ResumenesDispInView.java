package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResumenesDispInView {
	
	@JsonProperty("segmento")
	private String segmento;

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	
}
