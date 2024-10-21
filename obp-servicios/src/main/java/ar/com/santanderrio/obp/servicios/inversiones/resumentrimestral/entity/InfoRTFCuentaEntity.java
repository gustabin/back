package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class InfoRTFCuentaEntity {
	
	@JsonProperty("NumeroCuenta")
	private String numeroCuenta;
	
	@JsonProperty("Segmento")
	private String segmento;

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	
	
	
	

}
