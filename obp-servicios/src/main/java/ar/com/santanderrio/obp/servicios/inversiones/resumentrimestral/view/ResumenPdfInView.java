package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResumenPdfInView {
	
	@JsonProperty("ListaId")
	private int[] id;

	public int[] getId() {
		return id;
	}

	public void setId(int[] id) {
		this.id = id;
	}

	
	
	

}
