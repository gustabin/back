package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosRespuestaCuotaEntity {
	
	@JsonProperty("ValorCuotaparte")
	private String valorCuotaParte;

	public String getValorCuotaParte() {
		return valorCuotaParte;
	}

	public void setValorCuotaParte(String valorCuotaParte) {
		this.valorCuotaParte = valorCuotaParte;
	}
	
	

}
