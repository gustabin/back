package ar.com.santanderrio.obp.servicios.echeq.dto;

import java.util.List;

public class GrillaECheqOutDTO {

	private List<ECheqOutDTO> listaEcheqs;
	
	private String legal;
	
	private Boolean hayMasRegistros = Boolean.FALSE;
	
	public GrillaECheqOutDTO() {
	}

	public List<ECheqOutDTO> getListaEcheqs() {
		return listaEcheqs;
	}

	public void setListaEcheqs(List<ECheqOutDTO> listaEcheqs) {
		this.listaEcheqs = listaEcheqs;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public Boolean getHayMasRegistros() {
		return hayMasRegistros;
	}

	public void setHayMasRegistros(Boolean hayMasRegistros) {
		this.hayMasRegistros = hayMasRegistros;
	}

}
